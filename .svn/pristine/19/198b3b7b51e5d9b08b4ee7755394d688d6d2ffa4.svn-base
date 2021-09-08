package com.xjgc.wind.datastatistics.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.actions.DispatchAction;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.xjgc.wind.datastatistics.service.IRuningInfo_SelectOneMinuteService;
import com.xjgc.wind.datastatistics.service.IGeneratorService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_SelectOneMinuteDataForm;


public class RuningInfo_SelectOneMinuteDataAction extends DispatchAction{


	private static final Log log = LogFactory.getLog(RuningInfo_SelectOneMinuteDataAction.class);
	IRuningInfo_SelectOneMinuteService runingInfo_SelectOneMinuteService; 
	IGeneratorService generatorService;
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		runingInfo_SelectOneMinuteService = (IRuningInfo_SelectOneMinuteService) wac.getBean("runingInfo_SelectOneMinuteService");																														
		generatorService = (IGeneratorService) wac.getBean("generatorService");
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("entering 'execute' method ...");

		String method = request.getParameter("method");
		if (StringUtils.isBlank(method))
			return show(mapping, form, request, response);
		else
			return super.execute(mapping, form, request, response);
	}
	
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("entering 'show' method ...");


		RuningInfo_SelectOneMinuteDataForm dataForm = (RuningInfo_SelectOneMinuteDataForm) form;
		

		List<DataStatisticsDataVo> resultcheck =null;
		List<DataStatisticsDataVo> bayList =null;
		
		String key = request.getParameter("key");
		Integer flag = NumberUtils.createInteger(key);
		
		resultcheck = runingInfo_SelectOneMinuteService.list(flag);
		bayList = runingInfo_SelectOneMinuteService.BayList(flag);
		
		//第一进入界面初始化
		if (StringUtils.isEmpty(request.getParameter("isFirst"))) {
			if(null!=bayList&&bayList.size()!=0){
				dataForm.setId(bayList.get(0).getId());
			}
		}
		
	
		ArrayList<String> resultCheckName = new ArrayList<String>();
		ArrayList<String> resultCheckValue = new ArrayList<String>();
		if(resultcheck.size()!=0){
			for(int i=0;i<resultcheck.size();i++ ){
			resultCheckName.add(resultcheck.get(i).getColumndes());
			resultCheckValue.add(resultcheck.get(i).getColumnName());

			}
		}
		request.setAttribute("resultCheckName", resultCheckName);
		request.setAttribute("resultCheckValue", resultCheckValue);
		request.setAttribute("bayList", bayList);
		
		

		
		Map dataMap=new HashMap();
		for(int i=0;i<bayList.size();i++){
			Map map=new HashMap();
			
			String[] values={};
			String[] valueNames={};
			map.put("targetValues", values);
			map.put("targetValueNames", valueNames);
			map.put("name", bayList.get(i).getName());
			dataMap.put(bayList.get(i).getId(),map);
		}
		Map dataMap1=new HashMap();
		if(resultcheck.size()!=0){
			for(int i=0;i<resultcheck.size();i++ ){
				dataMap1.put(resultcheck.get(i).getColumnName(),resultcheck.get(i).getColumndes());
			}
		}
		
		Gson gson = new Gson();
		request.setAttribute("data", gson.toJson(dataMap));
		request.setAttribute("data1", gson.toJson(dataMap1));
		
		return mapping.findForward("show");
		
	}
	
	
}
