package com.xjgc.wind.app.district.action;


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

import com.bjxj.usermgr.util.JsonUtils;
import com.xjgc.wind.app.district.service.DistrictAPPService;
import com.xjgc.wind.app.overview.service.OverviewAPPService;
import com.xjgc.wind.app.util.HtmlUtil;

public class DistrictAPPAction extends DispatchAction  {
	
	DistrictAPPService district_APP_Service;
	
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		district_APP_Service = (DistrictAPPService) wac.getBean("districtAPPService");																														
	}
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("entering 'execute' method ...");

		String method = request.getParameter("method");
		if (StringUtils.isBlank(method))
			return districtList(mapping, form, request, response);
		else
			return super.execute(mapping, form, request, response);
	}
	
	public ActionForward districtList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String method=request.getParameter("method");
			
			if(method==null||method.equals(""))
				method="districtList";
			
			HtmlUtil.writeStrToHtml(district_APP_Service.getDistrictListStr(method), response);
			return null;
	}
	
	public ActionForward genInfoByDistrictId (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String method=request.getParameter("method");
			if(method==null||method.equals(""))
				method="genInfoByDistrictId";
			
			String id=request.getParameter("id");
			if(id==null||id.equals(""))
				id="1";
			
			HtmlUtil.writeStrToHtml(district_APP_Service.getGenInfoStrByDistrictId(method,id), response);
			return null;
	}
	
	public ActionForward realtimePowerByDistrictId (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String method=request.getParameter("method");
			if(method==null||method.equals(""))
				method="realtimePowerByDistrictId";
			
			String id=request.getParameter("id");
			if(id==null||id.equals(""))
				id="1";
			
			HtmlUtil.writeStrToHtml(district_APP_Service.getRealtimePowerDataStrByDistrictId(method, id), response);
			return null;
	}
	
	public ActionForward hisPowerByDistrictId (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String method=request.getParameter("method");
			if(method==null||method.equals(""))
				method="hisPowerByDistrictId";
			
			String id=request.getParameter("id");
			if(id==null||id.equals(""))
				id="1";
			
			HtmlUtil.writeStrToHtml(district_APP_Service.getHisPowerDataStrByDistrictId(method, id), response);
			return null;
	}
	
	public ActionForward hisDayGenWhByDistrictId (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String method=request.getParameter("method");
			if(method==null||method.equals(""))
				method="hisDayGenWhByDistrictId";
			
			String id=request.getParameter("id");
			if(id==null||id.equals(""))
				id="1";
			
			HtmlUtil.writeStrToHtml(district_APP_Service.getHisDayGenWhStrByDistrictId(method, id), response);
			return null;
	}
	
	public ActionForward hisMonthGenWhByDistrictId (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String method=request.getParameter("method");
			
			if(method==null||method.equals(""))
				method="hisMonthGenWhByDistrictId";
			
			String id=request.getParameter("id");
			if(id==null||id.equals(""))
				id="1";
			
			HtmlUtil.writeStrToHtml(district_APP_Service.getHisMonthGenWhStrByDistrictId(method, id), response);
			return null;
	}
	
	public ActionForward genInfoByMgId (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String method=request.getParameter("method");
			if(method==null||method.equals(""))
				method="genInfoByMgId";
			
			String id=request.getParameter("id");
			if(id==null||id.equals(""))
				id="1";
			
			HtmlUtil.writeStrToHtml(district_APP_Service.getGenInfoStrByMgId(method,id), response);
			return null;
	}
	
	public ActionForward generatorListByMgId (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String method=request.getParameter("method");
			if(method==null||method.equals(""))
				method="generatorListByMgId";
			
			String id=request.getParameter("id");
			if(id==null||id.equals(""))
				id="1";
			
			HtmlUtil.writeStrToHtml(district_APP_Service.getGeneratorListStrByMgId(method,id), response);
			return null;
	}
	
	public ActionForward realtimePowerByMgId (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String method=request.getParameter("method");
			if(method==null||method.equals(""))
				method="realtimePowerByMgId";
			
			String id=request.getParameter("id");
			if(id==null||id.equals(""))
				id="1";
			
			HtmlUtil.writeStrToHtml(district_APP_Service.getRealtimePowerDataStrByMgId(method, id), response);
			return null;
	}
	
	public ActionForward hisPowerByMgId (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String method=request.getParameter("method");
			if(method==null||method.equals(""))
				method="hisPowerByMgId";
			
			String id=request.getParameter("id");
			if(id==null||id.equals(""))
				id="1";
			
			HtmlUtil.writeStrToHtml(district_APP_Service.getHisPowerDataStrByMgId(method, id), response);
			return null;
	}
	
	public ActionForward hisDayGenWhByMgId (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String method=request.getParameter("method");
			if(method==null||method.equals(""))
				method="hisDayGenWhByMgId";
			
			String id=request.getParameter("id");
			if(id==null||id.equals(""))
				id="1";
			
			HtmlUtil.writeStrToHtml(district_APP_Service.getHisDayGenWhStrByMgId(method,id), response);
			return null;
	}
	
	
	public ActionForward dayProfitByMgId (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String method=request.getParameter("method");
			if(method==null||method.equals(""))
				method="dayProfitByMgId";
			
			String id=request.getParameter("id");
			if(id==null||id.equals(""))
				id="1";
			
			HtmlUtil.writeStrToHtml(district_APP_Service.getHisDayGenWhStrByMgId(method, id), response);
			return null;
	}
	
	public ActionForward hisAvgSpdByMgId (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String method=request.getParameter("method");
			if(method==null||method.equals(""))
				method="hisAvgSpdByMgId";
			
			String id=request.getParameter("id");
			if(id==null||id.equals(""))
				id="1";
			
			HtmlUtil.writeStrToHtml(district_APP_Service.getHisAvgSpdStrByMgId(method, id), response);
			return null;
	}
	
	public ActionForward totalGenWhByMgId (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String method=request.getParameter("method");
			if(method==null||method.equals(""))
				method="totalGenWhByMgId";
			
			String id=request.getParameter("id");
			if(id==null||id.equals(""))
				id="1";
			
			HtmlUtil.writeStrToHtml(district_APP_Service.getTotalGenWhStrByMgId(method, id), response);
			return null;
	}
	
	
}
