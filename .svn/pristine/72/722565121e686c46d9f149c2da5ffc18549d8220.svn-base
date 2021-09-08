package com.xjgc.wind.app.breakdown.action;

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

import com.xjgc.wind.app.breakdown.service.BreakdownAppService;

import com.xjgc.wind.app.util.HtmlUtil;

public class BreakdownAppAction extends DispatchAction	{
	
	
	BreakdownAppService breakdownAppService;
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		breakdownAppService = (BreakdownAppService) wac.getBean("breakdownAppService");		
		
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
	
	
	//获取所有的故障
	public ActionForward getAllBreakdown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String method=request.getParameter("method");
		if(method==null||method.equals(""))
		method="nomethod";


		HtmlUtil.writeStrToHtml(breakdownAppService.getAllBreakdown(method), response);
		return null;
		
	}
	

}
