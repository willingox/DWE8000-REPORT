package com.xjgc.wind.app.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;

import com.bjxj.Constants;
import com.bjxj.base.webapp.action.BaseAction;
import com.bjxj.usermgr.model.XtUser;
import com.bjxj.usermgr.service.IXtUserService;
import com.bjxj.usermgr.util.SystemLogerUtils;
import com.xjgc.wind.app.util.HtmlUtil;

public final class LogoffAPPAction extends BaseAction {

	
	private IXtUserService userService;
	/*public LoginOffAPPAction() {
		// TODO Auto-generated constructor stub
	}*/
	
	public void setServlet(ActionServlet actionServlet) {
		super.setServlet(actionServlet);
		userService = (IXtUserService) getBean(Constants.USER_SERVICE);
	}

	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 记录日志，将相关信息记入log
		String userId = (String) request.getSession().getAttribute(Constants.CURRENT_USER_ID);
		if (userId != null) {
			XtUser user = (XtUser) userService.getEntityByKey(userId);
			SystemLogerUtils.logger(Constants.LOG_CLASS_LOGOFF, "【" + user.getUserName() + "】手机端注销", request);
			HttpSession session = request.getSession();
			session.removeAttribute(Constants.USER_KEY);
			session.removeAttribute(Constants.CURRENT_USER_ID);
			session.removeAttribute(Constants.CURRENT_USER_NAME);
			session.removeAttribute(Constants.CURRENT_ORG_ID);
			session.removeAttribute(Constants.CURRENT_ORG_CODE);
			session.invalidate();
			HtmlUtil.writeStrToHtml("注销成功", response);
			System.out.println(user.getUserName()+"手机注销成功");
		}
		return null;
	}
	
	
}
