package com.xjgc.wind.datastatistics.web.action;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.xjgc.wind.datastatistics.service.IWindGerRtMonitorService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindGerRtMonitorDataForm;
import com.xjgc.wind.util.DoubleRound;
import com.xjgc.wind.util.UnitAnalyse;


public class WindGerRtMonitorDataAction extends DispatchAction{

	private static final Log log = LogFactory.getLog(WindGerRtMonitorDataAction.class);
	
	IWindGerRtMonitorService windGerRtMonitorService;
	
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servlet.getServletContext());
		windGerRtMonitorService = (IWindGerRtMonitorService) wac.getBean("windGerRtMonitorService");
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
		
		
		List<Option> targetTypes =null;
		List<Option> stateTypes =null;
		List<DataStatisticsDataVo> result =null;
		Map map=null;
		WindGerRtMonitorDataForm dataForm = (WindGerRtMonitorDataForm) form;
		
		if (StringUtils.isEmpty(request.getParameter("isFirst"))) {
			// 第一次进入页面
			dataForm.setTargetType(1);
			dataForm.setSortType(1);
			dataForm.setStateType(-1);
		}
		//设置类型 列表为故障和告警
		targetTypes = new ArrayList<Option>();
		Option d1=new Option();
		d1.setId(1);
		d1.setName("功率");
		targetTypes.add(d1);
		Option d2=new Option();
		d2.setId(2);
		d2.setName("发电量");
		targetTypes.add(d2);
		request.setAttribute("targetTypes",targetTypes);
		
		
		//设置设备状态类型
		stateTypes = new ArrayList<Option>();
		Option o1=new Option();
		o1.setId(-1);
		o1.setName("全部");
		stateTypes.add(o1);
		Option o2=new Option();
		o2.setId(2);
		o2.setName("全功率运行");
		stateTypes.add(o2);
		Option o3=new Option();
		o3.setId(3);
		o3.setName("限功率运行");
		stateTypes.add(o3);
		Option o4=new Option();
		o4.setId(5);
		o4.setName("故障");
		stateTypes.add(o4);
		Option o5=new Option();
		o5.setId(4);
		o5.setName("检修");
		stateTypes.add(o5);
		Option o6=new Option();
		o6.setId(0);
		o6.setName("停运");
		stateTypes.add(o6);
		Option o7=new Option();
		o7.setId(1);
		o7.setName("并网待机");
		stateTypes.add(o7);
		Option o8=new Option();
		o8.setId(6);
		o8.setName("离网（待并网）");
		stateTypes.add(o8);
		
		request.setAttribute("stateTypes",stateTypes);
		
		
		
		
		
		result = windGerRtMonitorService.generatorCurStateList(dataForm.getTargetType(),dataForm.getSortType(),dataForm.getStateType());
		//result = pvGerRtMonitorService.generatorCurStateList();
		//System.out.println(result.size());
		handlGeneratorCurStateList(result,request);
		

		map=windGerRtMonitorService.getStates();
		//System.out.println(map);
		handleStateMap(map,request);
		
		return mapping.findForward("show");
		
	}
	
	
	private void handleStateMap(Map map,HttpServletRequest request){
	/*	int a=map.size();
		System.out.println(a);*/
	
			/*String generatorState="全功率运行:"+(String)map.get("fullRun")+"台, 限功率运行:"+(String)map.get("limitRun")
			+"台, 故障:"+(String)map.get("breakdown")+"台, 检修:"+(String)map.get("overhaul")+"台, 停运:"+(String)map.get("stop")
			+"台, 并网待机:"+(String)map.get("gridOnStandby")+"台, 离网（待并网）:"+(String)map.get("gridOff")+"台";
			request.setAttribute("generatorState",generatorState);*/
		
			if(null!=map.get("fullRun")){
				request.setAttribute("fullRun",(String)map.get("fullRun"));
				request.setAttribute("limitRun",(String)map.get("limitRun"));
				request.setAttribute("breakdown",(String)map.get("breakdown"));
				request.setAttribute("overhaul",(String)map.get("overhaul"));
				request.setAttribute("stop",(String)map.get("stop"));
				request.setAttribute("gridOnStandby",(String)map.get("gridOnStandby"));
				request.setAttribute("gridOff",(String)map.get("gridOff"));
			}
			if(null!=map.get("curp")){
				request.setAttribute("curp",UnitAnalyse.powerFormat((String)map.get("curp"))+" "+UnitAnalyse.powerFormatUnit((String)map.get("curp")));
				//request.setAttribute("maxgenpw",UnitAnalyse.powerFormat((String)map.get("maxgenpw"))+" "+UnitAnalyse.powerFormatUnit((String)map.get("maxgenpw"))+"    "+(String)map.get("maxgenptime"));
				request.setAttribute("sunlightval",DoubleRound.formatDouble1((String)map.get("sunlightval")));
				request.setAttribute("tempval",DoubleRound.formatDouble1((String)map.get("tempval")));
			}
		
			if(null!=map.get("maxgenpw")){
				request.setAttribute("maxgenpw",UnitAnalyse.powerFormat((String)map.get("maxgenpw"))+" "+UnitAnalyse.powerFormatUnit((String)map.get("maxgenpw")));
				request.setAttribute("maxgenptime",(String)map.get("maxgenptime"));
				request.setAttribute("todaygenwh",UnitAnalyse.generationFormat((String)map.get("todaygenwh"))+" "+UnitAnalyse.generationFormatUnit((String)map.get("todaygenwh")));
				request.setAttribute("todayupnetwh",UnitAnalyse.generationFormat((String)map.get("todayupnetwh"))+" "+UnitAnalyse.generationFormatUnit((String)map.get("todayupnetwh")));
				
			}
			
		request.setAttribute("time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
	
	
	
	private void handlGeneratorCurStateList(List<DataStatisticsDataVo> result,HttpServletRequest request){
		
		int num=result.size();
		double maxCurp =0;
		double maxTodayGenwh=0;
		for (int i=0;i<num;i++){
		/*  maxCurp=maxCurp>result.get(i).getCurp()?maxCurp:result.get(i).getCurp();
			maxTodaygenwh=maxTodaygenwh>result.get(i).getTodaygenwh()?maxTodaygenwh:result.get(i).getTodaygenwh();*/
			if (result.get(i).getCurp()>maxCurp){
				maxCurp=result.get(i).getCurp();
			}
			if (result.get(i).getTodayGenwh()>maxTodayGenwh){
				maxTodayGenwh=result.get(i).getTodayGenwh();
			}
		}
		
		String generatorListCurpUnit=UnitAnalyse.powerFormatUnit(maxCurp);
		String generatorListTodayGenwhUnit=UnitAnalyse.generationFormatUnit(maxTodayGenwh);
		request.setAttribute("generatorListCurpUnit", generatorListCurpUnit);
		request.setAttribute("generatorListTodayGenwhUnit", generatorListTodayGenwhUnit);
		
		for (int i=0;i<num;i++){

			if(generatorListCurpUnit.endsWith("kW")){
				result.get(i).setCurp(DoubleRound.formatDouble1(result.get(i).getCurp()));
			}else if(generatorListCurpUnit.endsWith("MW")){
				result.get(i).setCurp(DoubleRound.formatDouble1(result.get(i).getCurp()/1000));
			}else if(generatorListCurpUnit.endsWith("GW")){
				result.get(i).setCurp(DoubleRound.formatDouble1(result.get(i).getCurp()/1000000));
			}
			
			if(generatorListTodayGenwhUnit.endsWith("kWh")){
				result.get(i).setTodayGenwh(DoubleRound.formatDouble1(result.get(i).getTodayGenwh()));
			}else if(generatorListTodayGenwhUnit.endsWith("MWh")){
				result.get(i).setTodayGenwh(DoubleRound.formatDouble1(result.get(i).getTodayGenwh()/1000));
			}else if(generatorListTodayGenwhUnit.endsWith("GWh")){
				result.get(i).setTodayGenwh(DoubleRound.formatDouble1(result.get(i).getTodayGenwh()/1000000));
			}
			
		}
		request.setAttribute("result", result);
		
	}
	
	
	
} 


