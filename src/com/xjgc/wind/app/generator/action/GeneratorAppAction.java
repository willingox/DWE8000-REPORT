package com.xjgc.wind.app.generator.action;

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

import com.xjgc.wind.app.generator.service.GeneratorAppService;
import com.xjgc.wind.app.overview.service.OverviewAPPService;
import com.xjgc.wind.app.util.HtmlUtil;

public class GeneratorAppAction extends DispatchAction	 {

	GeneratorAppService generatorAppService;
	
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		generatorAppService = (GeneratorAppService) wac.getBean("generatorAppService");																														
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
	public ActionForward getGeneratorInfoById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String generatorid=request.getParameter("generatorid");
			System.out.println(generatorid);
			
			if(generatorid==null||generatorid.equals(""))
				generatorid="1";
		
			String _resultStr=generatorAppService.getGeneratorInfoByIdStr(generatorid);
			System.out.println(_resultStr);
			HtmlUtil.writeStrToHtml(_resultStr, response);
			return null;
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
	public ActionForward generatorPowerRealtime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
			String generatorid=request.getParameter("generatorid");
			System.out.println(generatorid);
			
			if(generatorid==null||generatorid.equals(""))
				generatorid="1";
		
			String _resultStr=generatorAppService.getGeneratorPowerRealtimeDataStr(generatorid);
			System.out.println(_resultStr);
			HtmlUtil.writeStrToHtml(_resultStr, response);
			return null;
	}
	
	
	public ActionForward generatorHisPower(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String generatorid=request.getParameter("generatorid");
			//System.out.println(generatorid);
			
			if(generatorid==null||generatorid.equals(""))
				generatorid="1";
		
			String _resultStr=generatorAppService.getGeneratorHisPowerDataListStr(generatorid);
			System.out.println(_resultStr);
			HtmlUtil.writeStrToHtml(_resultStr, response);
			return null;
	}

	
	public ActionForward generatorHisWindSpeed(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String generatorid=request.getParameter("generatorid");
			//System.out.println(generatorid);
			
			if(generatorid==null||generatorid.equals(""))
				generatorid="1";
		
			String _resultStr=generatorAppService.getGeneratorHisWindSpeedDataListStr(generatorid);
			System.out.println(_resultStr);
			HtmlUtil.writeStrToHtml(_resultStr, response);
			return null;
	}
	
	
	public ActionForward generatorHisDayGenWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String generatorid=request.getParameter("generatorid");
			//System.out.println(generatorid);
			
			if(generatorid==null||generatorid.equals(""))
				generatorid="1";
		
			String _resultStr=generatorAppService.getGeneratorHisDayGenWhListStr(generatorid);
			System.out.println(_resultStr);
			HtmlUtil.writeStrToHtml(_resultStr, response);
			return null;
	}

	
	public ActionForward generatorHisMonthGenWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String generatorid=request.getParameter("generatorid");
			//System.out.println(generatorid);
			
			if(generatorid==null||generatorid.equals(""))
				generatorid="1";
		
			String _resultStr=generatorAppService.getGeneratorHisMonthGenWhListStr(generatorid);
			System.out.println(_resultStr);
			HtmlUtil.writeStrToHtml(_resultStr, response);
			return null;
	}


	/**
	 * 获取发电设备所在间隔中的所有设备id
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getGeneratorBayEquipmentList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String generatorid=request.getParameter("generatorid");
			//System.out.println(generatorid);
			
			if(generatorid==null||generatorid.equals(""))
				generatorid="10000000000";
		
			String _resultStr=generatorAppService.getGeneratorBayEquipmentListStr(generatorid);
			System.out.println(_resultStr);
			HtmlUtil.writeStrToHtml(_resultStr, response);
			return null;
	}
	
	
/*	*//**
	 * 获取发电设备所在间隔中的所有设备的信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward getEquipmentsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String generatorid=request.getParameter("generatorid");
			//System.out.println(generatorid);
			
			if(generatorid==null||generatorid.equals(""))
				generatorid="10000000000";
			
			String _resultStr=generatorAppService.getEquipmentsInfoStr(generatorid);
			System.out.println(_resultStr);
			HtmlUtil.writeStrToHtml(_resultStr, response);
			return null;
	}*/
	
	
	/**
	 * 获取指定设备的信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getEquipmentMeasurePointInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String equipmentid=request.getParameter("equipmentid");
			//System.out.println(id);
			
			if(equipmentid==null||equipmentid.equals(""))
				equipmentid="10000000000";
			
			String _resultStr=generatorAppService.getEquipmentMeasurePointListInStatusAndAnalogStr(equipmentid);
			System.out.println(_resultStr);
			HtmlUtil.writeStrToHtml(_resultStr, response);
			return null;
			
	}
	
	
}
