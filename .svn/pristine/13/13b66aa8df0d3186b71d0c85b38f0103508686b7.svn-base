package com.xjgc.wind.app.overview.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.actions.DispatchAction;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xjgc.wind.app.overview.service.OverviewAPPService;
import com.xjgc.wind.app.util.HtmlUtil;

public class OverviewAPPAction extends DispatchAction	{
	
	OverviewAPPService overview_APP_Service;
	
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		overview_APP_Service = (OverviewAPPService) wac.getBean("overviewAPPService");																														
	}
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("entering 'execute' method ...");

		String method = request.getParameter("method");
		if (StringUtils.isBlank(method))
			return null;
			//return test(mapping, form, request, response);
		else
			return super.execute(mapping, form, request, response);
	}
	
	
	/**
	 * 实时运行指标数据
	 * @param mapping 
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward generalInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String _resultStr=overview_APP_Service.getGeneralInfoStr();
			//System.out.println(_resultStr);
			HtmlUtil.writeStrToHtml(_resultStr, response);
			return null;
	}
	
	/**
	 * 实时发电功率
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward powerRealtime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String _resultStr=overview_APP_Service.getPowerRealtimeData();
			System.out.println(_resultStr);
			HtmlUtil.writeStrToHtml(_resultStr, response);
			return null;
	}
	
	/**
	 * 
	 * 历史发电功率
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hisPower(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String _str=overview_APP_Service.getHisPowerDataStr();
			//System.out.println(_str);
			HtmlUtil.writeStrToHtml(_str, response);
			return null;
	}
	
	public ActionForward hisWindSpeed(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String _str=overview_APP_Service.getHisWindSpeedDataStr();
			//System.out.println(_str);
			HtmlUtil.writeStrToHtml(_str, response);
			return null;
	}
	
	
	public ActionForward hisDayGenWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String _str=overview_APP_Service.getHisDayGenWhStr();
			//System.out.println(_str);
			HtmlUtil.writeStrToHtml(_str, response);
			return null;
	}
	
	public ActionForward hisMonthGenWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String _str=overview_APP_Service.getHisMonthGenWhStr();
			//System.out.println(_str);
			HtmlUtil.writeStrToHtml(_str, response);
			return null;
	}
	
	
	
	

		
}
