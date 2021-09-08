package com.xjgc.wind.app.station.action;


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

import com.xjgc.wind.app.station.service.StationAppService;
import com.xjgc.wind.app.util.HtmlUtil;

public class StationAppAction extends DispatchAction{

	StationAppService stationAppService;

	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		stationAppService = (StationAppService) wac.getBean("stationAppService");																														
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
	 * 
	 * @param mapping 
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCapacity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String _resultStr=stationAppService.getGeneratorsCapacityStr();
			//System.out.println(_resultStr);
			HtmlUtil.writeStrToHtml(_resultStr, response);
			return null;
	}
	
	/**
	 * 
	 * @param mapping 
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCurp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String _resultStr=stationAppService.getGeneratorsCurpStr();
			//System.out.println(_resultStr);
			HtmlUtil.writeStrToHtml(_resultStr, response);
			return null;
	}
	
	/**
	 * 
	 * @param mapping 
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getWindSpeed(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String _resultStr=stationAppService.getGeneratorsWindSpeedStr();
			//System.out.println(_resultStr);
			HtmlUtil.writeStrToHtml(_resultStr, response);
			return null;
	}
	/**
	 * 
	 * @param mapping 
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getTodaygenwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String _resultStr=stationAppService.getGeneratorsTodaygenwhStr();
			//System.out.println(_resultStr);
			HtmlUtil.writeStrToHtml(_resultStr, response);
			return null;
	}
	/**
	 * 
	 * @param mapping 
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getTotalgenwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String _resultStr=stationAppService.getGeneratorsTotalgenwhStr();
			//System.out.println(_resultStr);
			HtmlUtil.writeStrToHtml(_resultStr, response);
			return null;
	}

	/**
	 * 
	 * @param mapping 
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getGeneratorsCurstate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String _resultStr=stationAppService.getGeneratorsCurstateStr();
			System.out.println(_resultStr);
			HtmlUtil.writeStrToHtml(_resultStr, response);
			return null;
	}
	

}
