package com.xjgc.wind.app.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.DynaActionForm;

import com.bjxj.Constants;
import com.bjxj.MacSerialNum;
import com.bjxj.base.util.StringUtil;
import com.bjxj.base.webapp.action.BaseAction;
import com.bjxj.usermgr.model.XtUser;
import com.bjxj.usermgr.service.IXtUserService;
import com.bjxj.usermgr.util.JsonUtils;
import com.bjxj.usermgr.util.SystemLogerUtils;
import com.xjgc.wind.app.util.HtmlUtil;

public class LoginAPPAction extends BaseAction {

	private IXtUserService mUserService;
	private ServletContext mServletContext;

	public void setServlet(ActionServlet actionServlet) {
		super.setServlet(actionServlet);
		mUserService = (IXtUserService) getBean(Constants.USER_SERVICE);
		mServletContext = actionServlet.getServletContext();
		initPasswordEncrypt();

	}

	/**
	 * ��ȡweb.xml����ȡ�Ƿ�����������
	 */
	private void initPasswordEncrypt() {
		String encryptPassword = this.mServletContext
				.getInitParameter("encryptPassword");
		if ("true".equals(encryptPassword)) {
			this.mServletContext.setAttribute(Constants.ENCRYPT_PASSWORD,
					new Boolean(true));
			this.mServletContext.setAttribute(Constants.ENC_ALGORITHM, "SHA");
		}
	}

	protected IXtUserService getUserService() {
		return this.mUserService;
	}

	private boolean serialValid() {
		String validate = this.mServletContext.getInitParameter("god");
		if ("1".equals(validate)) {
			return MacSerialNum.valid(getSerialNum());
		} else {
			return true;
		}
	}

	private String getSerialNum() {
		Properties properties = new Properties();
		try {
			properties.load(this.servlet.getServletContext()
					.getResourceAsStream(Constants.SYSTEM_CONFIG_FILE_PATH));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("�Ҳ���ICS8000Config.properties�����ļ���");
		}
		return properties.getProperty("system.sn");
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if (!serialValid()) {
			HtmlUtil.writeStrToHtml("�빺����ȷ��ϵͳ���кţ�", response);
			return null;
		}
		DynaActionForm userForm = (DynaActionForm) form;
		String username = (String) userForm.get("username");
		String password = (String) userForm.get("password");
		System.out.println("�û��������룺" + username + "    " + password);
		Boolean encrypt = (Boolean) this.mServletContext
				.getAttribute(Constants.ENCRYPT_PASSWORD);
		if (encrypt != null && encrypt.booleanValue()) {
			String algorithm = (String) this.mServletContext
					.getAttribute(Constants.ENC_ALGORITHM);
			if (algorithm == null) { // should only happen for test case
				algorithm = "SHA";
			}
			password = StringUtil.encodePassword(password, algorithm);
		}
		int status = getUserService().checkUser(username, password,
				Calendar.getInstance().getTime());
		// HtmlUtil.writeStrToHtml("��������", response);

		switch (status) {
		case 1:
			HtmlUtil.writeStrToHtml("�û��������벻��ȷ��", response);
			return null;
		case 2:
			HtmlUtil.writeStrToHtml("�û��Ѿ�ʧЧ��", response);
			return null;
		case 3:
			HtmlUtil.writeStrToHtml("�����Ѿ����ڣ�", response);
			return null;
		default:
			break;
		}

		HttpSession session = request.getSession();
		if (null != session.getAttribute(Constants.USER_KEY))
			session.removeAttribute(Constants.USER_KEY);
		if (null != session.getAttribute(Constants.CTRL_KEY))
			session.removeAttribute(Constants.CTRL_KEY);
		// �ѵ�ǰ��½���û�������Ϣѹ��Seesion����������Ӧ��ģ��ʹ�á�
		XtUser user = new XtUser();
		user = (XtUser) getUserService().getEntityByKey(username);
		String strUserName = user.getUserName();
		String strOrgId = user.getXtOrganization().getOrgId();
		String strOrgCode = user.getXtOrganization().getOrgCode();
		request.getSession().setAttribute(Constants.CURRENT_USER_ID, username);

		request.getSession().setAttribute(Constants.CURRENT_USER_NAME,
				strUserName);
		request.getSession().setAttribute(Constants.CURRENT_ORG_ID, strOrgId);
		request.getSession().setAttribute(Constants.CURRENT_ORG_CODE,
				strOrgCode);

		// ��¼��־
		SystemLogerUtils.logger(Constants.LOG_CLASS_LOGON,
				"��" + user.getUserName() + "����½ϵͳ", request);

		System.out.println("��֤ͨ��   " + username + "    " + password);
		HtmlUtil.writeStrToHtml("��¼�ɹ�", response);
		return null;
	}

	
}
