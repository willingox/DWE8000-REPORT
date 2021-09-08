package com.xjgc.wind.datastatistics.web.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

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

import com.xjgc.wind.datastatistics.service.IGeneratorService;
import com.xjgc.wind.datastatistics.service.IRunGeneratorNumService;
import com.xjgc.wind.datastatistics.service.IWindSpeedService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindSpeedDataForm;
import com.xjgc.wind.util.YMDHMSUtil;
import com.xjgc.wind.util.YearFormatUtil;

public class WindSpeedDataAction extends DispatchAction{

private static final Log log = LogFactory.getLog(WindGenwhDataAction.class);
	
 	IWindSpeedService windSpeedService;
 	IGeneratorService generatorService;
	IRunGeneratorNumService  runGeneratorNumService;
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		windSpeedService = (IWindSpeedService) wac.getBean("windSpeedService");
		generatorService = (IGeneratorService) wac.getBean("generatorService");
		runGeneratorNumService = (IRunGeneratorNumService) wac.getBean("runGeneratorNumService");	
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
		
		WindSpeedDataForm dataForm = (WindSpeedDataForm) form;
		List<DataStatisticsDataVo> result =null;
		List<DataStatisticsDataVo> resultMaxTime =null;
		List<DataStatisticsDataVo> resultMinTime =null;
		List<DataStatisticsDataVo> resultMaxTime1 =null;
		List<DataStatisticsDataVo> resultMinTime1 =null;
		List<DataStatisticsDataVo> resultMaxTime2 =null;
		List<DataStatisticsDataVo> resultMinTime2 =null;
		List<DataStatisticsDataVo> resultavg =null;
		List<DataStatisticsDataVo> result1 =null;
		List<DataStatisticsDataVo> resultavg1 =null;
		List<DataStatisticsDataVo> resultcount =null;
		List<DataStatisticsDataVo> resultmax =null;
		List<DataStatisticsDataVo> resultmin =null;
		List<DataStatisticsDataVo> resultmax1 =null;
		List<DataStatisticsDataVo> resultmin1 =null;
		double maxWind=0;
		double minWind=0;
		String maxTime=null;
		String minTime=null;
		if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {
			Date startDate = YearFormatUtil.get().parse(dataForm.getStartDateDisp());
			Date endDate = YearFormatUtil.get().parse(dataForm.getEndDateDisp());
			Calendar   calendar   =   new   GregorianCalendar(); 
			Calendar   calendar1   =   new   GregorianCalendar(); 
		  	calendar.setTime(startDate); 
		  	calendar1.setTime(endDate); 
			String startYear = new SimpleDateFormat("yyyy").format(calendar.getTime());
			String endYear = new SimpleDateFormat("yyyy").format(calendar1.getTime());
			int runGenSize = 0;
			String str=dataForm.getStr();
			String[] arr =null;
			arr = str.split(",");
			if(startYear.compareTo(endYear)==0){
				
				result = windSpeedService.list(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),dataForm.getStr(),1);
				resultavg = windSpeedService.listavg(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),1);
				resultcount = windSpeedService.listcount(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),1);
				resultmax = windSpeedService.listMax(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),1);
				resultmin = windSpeedService.listMin(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),1);
				
				if(str.length()>11){
					if(arr[0].compareTo("district-1")==0){
					resultMaxTime=windSpeedService.listMaxTime(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),Integer.parseInt(arr[1].substring(arr[1].lastIndexOf("-")+1,arr[1].length())),1);
					resultMinTime=windSpeedService.listMinTime(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),Integer.parseInt(arr[1].substring(arr[1].lastIndexOf("-")+1,arr[1].length())),1);	
				for(int i=2;i<arr.length;i++){
					resultMaxTime1=windSpeedService.listMaxTime(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length())),1);
					resultMinTime1=windSpeedService.listMinTime(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length())),1);
					resultMaxTime.addAll(resultMaxTime1);
					resultMinTime.addAll(resultMinTime1);
					}
					}
					if(arr[0].compareTo("district-1")!=0){
						resultMaxTime=windSpeedService.listMaxTime(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),Integer.parseInt(arr[0].substring(arr[0].lastIndexOf("-")+1,arr[0].length())),1);
						resultMinTime=windSpeedService.listMinTime(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),Integer.parseInt(arr[0].substring(arr[0].lastIndexOf("-")+1,arr[0].length())),1);	
					for(int i=1;i<arr.length;i++){
						resultMaxTime1=windSpeedService.listMaxTime(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length())),1);
						resultMinTime1=windSpeedService.listMinTime(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length())),1);
						resultMaxTime.addAll(resultMaxTime1);
						resultMinTime.addAll(resultMinTime1);
						}
						}
				}
				if(str.length()<11){
					
					resultMaxTime=windSpeedService.listMaxTime(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),generatorService.list().get(0).getId(),1);
					resultMinTime=windSpeedService.listMinTime(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),generatorService.list().get(0).getId(),1);	
				for(int i=1;i<generatorService.list().size();i++){
					resultMaxTime1=windSpeedService.listMaxTime(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),generatorService.list().get(i).getId(),1);
					resultMinTime1=windSpeedService.listMinTime(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),generatorService.list().get(i).getId(),1);
					resultMaxTime.addAll(resultMaxTime1);
					resultMinTime.addAll(resultMinTime1);
					}
				}
				for(int i=0;i<result.size();i++){
					if(resultMaxTime.size()!=0){
					for(int j=0;j<resultMaxTime.size();j++){
					if(result.get(i).getId()==resultMaxTime.get(j).getId()){
						result.get(i).setMaxTime(resultMaxTime.get(j).getMaxTime());
					}
					}
					}
					if(resultMinTime.size()!=0){
					for(int j=0;j<resultMinTime.size();j++){
						if(result.get(i).getId()==resultMinTime.get(j).getId()){
							result.get(i).setMinTime(resultMinTime.get(j).getMinTime());
						}
						}
					}
				}
				if(resultmax.size()!=0){
				maxWind=resultmax.get(0).getMaxWind();
				maxTime=resultmax.get(0).getMaxTime();
				}
				if(resultmin.size()!=0){
				minWind=resultmin.get(0).getMinWind();
				minTime=resultmin.get(0).getMinTime();
				}
			}
			if(startYear.compareTo(endYear)!=0){
				resultmax = windSpeedService.listMax(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",1);
				resultmax1 = windSpeedService.listMax(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),2);
				resultmin = windSpeedService.listMin(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",1);
				resultmin1 = windSpeedService.listMax(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),2);
				result = windSpeedService.list(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",dataForm.getStr(),1);
				result1 = windSpeedService.list(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),dataForm.getStr(),2);
				resultavg = windSpeedService.listavg(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",1);
				resultavg1 = windSpeedService.listavg(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),2);
                resultcount = windSpeedService.listcount(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),2);	
                if(resultmax.size()!=0){
    				maxWind=resultmax.get(0).getMaxWind();
    				maxTime=resultmax.get(0).getMaxTime();
    			if(resultmax1.size()!=0){
    				if(resultmax1.get(0).getMaxWind()>maxWind){
    					maxWind=resultmax1.get(0).getMaxWind();
    					maxTime=resultmax1.get(0).getMaxTime();
    				}
    			}
    				}
                if(resultmin.size()!=0){
    				minWind=resultmin.get(0).getMinWind();
    				minTime=resultmin.get(0).getMinTime();
    			if(resultmin1.size()!=0){
    				if(resultmin1.get(0).getMaxWind()<minWind){
    					minWind=resultmin1.get(0).getMinWind();
    					minTime=resultmin1.get(0).getMinTime();
    				}
    			}
    				}
                
                
                
                
                if(result.size()!=0 && result1.size()!=0){
				for(int i=0;i<result.size();i++){
					for(int j=0;j<result1.size();j++){
						if(result.get(i).getId()==result1.get(j).getId()){
							result.get(i).setAvgWindVelval((result.get(i).getAvgWindVelval()+result1.get(j).getAvgWindVelval())/2);
							if(result1.get(j).getMaxData()>result.get(i).getMaxData()){
							result.get(i).setMaxData(result1.get(j).getMaxData());
							}
							if(result1.get(j).getMinData()<result.get(i).getMinData()){
								result.get(i).setMinData(result1.get(j).getMinData());
								}
						}
					}
				}
			}
              //每个风机的最大风速和最小风速时间 
                if(str.length()>11){
                	if(arr[0].compareTo("district-1")==0){
                		resultMaxTime1=windSpeedService.listMaxTime(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",Integer.parseInt(arr[1].substring(arr[1].lastIndexOf("-")+1,arr[1].length())),1);
    					resultMaxTime2=windSpeedService.listMaxTime(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),Integer.parseInt(arr[1].substring(arr[1].lastIndexOf("-")+1,arr[1].length())),2);
    					if(resultMaxTime1.size()!=0&&resultMaxTime2.size()!=0){
    						resultMaxTime=resultMaxTime1;
    						if(resultMaxTime1.get(0).getMaxWind()<resultMaxTime2.get(0).getMaxWind()){
    						resultMaxTime=resultMaxTime2;
    					}
    					}
    					resultMinTime1=windSpeedService.listMinTime(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",Integer.parseInt(arr[1].substring(arr[1].lastIndexOf("-")+1,arr[1].length())),1);	
    					resultMinTime2=windSpeedService.listMinTime(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),Integer.parseInt(arr[1].substring(arr[1].lastIndexOf("-")+1,arr[1].length())),2);	
    					if(resultMinTime1.size()!=0&&resultMinTime2.size()!=0){
    						resultMinTime=resultMinTime1;
    						if(resultMinTime1.get(0).getMinWind()>resultMinTime2.get(0).getMinWind()){
    						resultMinTime=resultMinTime2;
    					}
    					}
    					for(int i=2;i<arr.length;i++){
    	                	
    						resultMaxTime1=windSpeedService.listMaxTime(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length())),1);
    						resultMaxTime2=windSpeedService.listMaxTime(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length())),2);
    						if(resultMaxTime1.size()!=0&&resultMaxTime2.size()!=0){
    							if(resultMaxTime1.get(0).getMaxWind()>resultMaxTime2.get(0).getMaxWind()||resultMaxTime1.get(0).getMaxWind()==resultMaxTime2.get(0).getMaxWind()){
    								resultMaxTime.addAll(resultMaxTime1);
    							}
    		
    							if(resultMaxTime1.get(0).getMaxWind()<resultMaxTime2.get(0).getMaxWind()){
    								resultMaxTime.addAll(resultMaxTime2);
    						}
    						}
    						resultMinTime1=windSpeedService.listMinTime(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length())),1);	
    						resultMinTime2=windSpeedService.listMinTime(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length())),2);	
    						if(resultMinTime1.size()!=0&&resultMinTime2.size()!=0){
    							if(resultMinTime1.get(0).getMinWind()<resultMinTime2.get(0).getMinWind()||resultMinTime1.get(0).getMinWind()==resultMinTime2.get(0).getMinWind()){
    								resultMinTime.addAll(resultMinTime1);
    							}
    							if(resultMinTime1.get(0).getMinWind()>resultMinTime2.get(0).getMinWind()){
    								resultMinTime.addAll(resultMinTime2);
    						}
    						}
    						
    						
    	
    					}
                	}
                	if(arr[0].compareTo("district-1")!=0){
					resultMaxTime1=windSpeedService.listMaxTime(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",Integer.parseInt(arr[0].substring(arr[0].lastIndexOf("-")+1,arr[0].length())),1);
					resultMaxTime2=windSpeedService.listMaxTime(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),Integer.parseInt(arr[0].substring(arr[0].lastIndexOf("-")+1,arr[0].length())),2);
					if(resultMaxTime1.size()!=0&&resultMaxTime2.size()!=0){
						resultMaxTime=resultMaxTime1;
						if(resultMaxTime1.get(0).getMaxWind()<resultMaxTime2.get(0).getMaxWind()){
						resultMaxTime=resultMaxTime2;
					}
					}
					resultMinTime1=windSpeedService.listMinTime(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",Integer.parseInt(arr[0].substring(arr[0].lastIndexOf("-")+1,arr[0].length())),1);	
					resultMinTime2=windSpeedService.listMinTime(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),Integer.parseInt(arr[0].substring(arr[0].lastIndexOf("-")+1,arr[0].length())),2);	
					if(resultMinTime1.size()!=0&&resultMinTime2.size()!=0){
						resultMinTime=resultMinTime1;
						if(resultMinTime1.get(0).getMinWind()>resultMinTime2.get(0).getMinWind()){
						resultMinTime=resultMinTime2;
					}
					}
					for(int i=1;i<arr.length;i++){
	                	
						resultMaxTime1=windSpeedService.listMaxTime(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length())),1);
						resultMaxTime2=windSpeedService.listMaxTime(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length())),2);
						if(resultMaxTime1.size()!=0&&resultMaxTime2.size()!=0){
							if(resultMaxTime1.get(0).getMaxWind()>resultMaxTime2.get(0).getMaxWind()||resultMaxTime1.get(0).getMaxWind()==resultMaxTime2.get(0).getMaxWind()){
								resultMaxTime.addAll(resultMaxTime1);
							}
		
							if(resultMaxTime1.get(0).getMaxWind()<resultMaxTime2.get(0).getMaxWind()){
								resultMaxTime.addAll(resultMaxTime2);
						}
						}
						resultMinTime1=windSpeedService.listMinTime(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length())),1);	
						resultMinTime2=windSpeedService.listMinTime(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length())),2);	
						if(resultMinTime1.size()!=0&&resultMinTime2.size()!=0){
							if(resultMinTime1.get(0).getMinWind()<resultMinTime2.get(0).getMinWind()||resultMinTime1.get(0).getMinWind()==resultMinTime2.get(0).getMinWind()){
								resultMinTime.addAll(resultMinTime1);
							}
							if(resultMinTime1.get(0).getMinWind()>resultMinTime2.get(0).getMinWind()){
								resultMinTime.addAll(resultMinTime2);
						}
						}
						
						
	
					}
                	}
				}
                if(str.length()<11){
					resultMaxTime1=windSpeedService.listMaxTime(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",generatorService.list().get(0).getId(),1);
					resultMaxTime2=windSpeedService.listMaxTime(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),generatorService.list().get(0).getId(),2);
					if(resultMaxTime1.size()!=0&&resultMaxTime2.size()!=0){
						resultMaxTime=resultMaxTime1;
						if(resultMaxTime1.get(0).getMaxWind()<resultMaxTime2.get(0).getMaxWind()){
						resultMaxTime=resultMaxTime2;
					}
					}
					resultMinTime1=windSpeedService.listMinTime(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",generatorService.list().get(0).getId(),1);	
					resultMinTime2=windSpeedService.listMinTime(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),generatorService.list().get(0).getId(),2);	
					if(resultMinTime1.size()!=0&&resultMinTime2.size()!=0){
						resultMinTime=resultMinTime1;
						if(resultMinTime1.get(0).getMinWind()>resultMinTime2.get(0).getMinWind()){
						resultMinTime=resultMinTime2;
					}
					}
					for(int i=1;i<generatorService.list().size();i++){
						resultMaxTime1=windSpeedService.listMaxTime(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",generatorService.list().get(i).getId(),1);
						resultMaxTime2=windSpeedService.listMaxTime(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),generatorService.list().get(i).getId(),2);
						if(resultMaxTime1.size()!=0&&resultMaxTime2.size()!=0){
							if(resultMaxTime1.get(0).getMaxWind()>resultMaxTime2.get(0).getMaxWind()||resultMaxTime1.get(0).getMaxWind()==resultMaxTime2.get(0).getMaxWind()){
								resultMaxTime.addAll(resultMaxTime1);
							}
		
							if(resultMaxTime1.get(0).getMaxWind()<resultMaxTime2.get(0).getMaxWind()){
								resultMaxTime.addAll(resultMaxTime2);
						}
						}
						resultMinTime1=windSpeedService.listMinTime(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",generatorService.list().get(i).getId(),1);	
						resultMinTime2=windSpeedService.listMinTime(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),generatorService.list().get(i).getId(),2);	
						if(resultMinTime1.size()!=0&&resultMinTime2.size()!=0){
							if(resultMinTime1.get(0).getMinWind()<resultMinTime2.get(0).getMinWind()||resultMinTime1.get(0).getMinWind()==resultMinTime2.get(0).getMinWind()){
								resultMinTime.addAll(resultMinTime1);
							}
							if(resultMinTime1.get(0).getMinWind()>resultMinTime2.get(0).getMinWind()){
								resultMinTime.addAll(resultMinTime2);
						}
						}
						
						
	
					}
				}
				for(int i=0;i<result.size();i++){
					for(int j=0;j<resultMaxTime.size();j++){
					if(result.get(i).getId()==resultMaxTime.get(j).getId()){
						result.get(i).setMaxTime(resultMaxTime.get(j).getMaxTime());
					}
					}
					for(int j=0;j<resultMinTime.size();j++){
						if(result.get(i).getId()==resultMinTime.get(j).getId()){
							result.get(i).setMinTime(resultMinTime.get(j).getMinTime());
						}
						}	
				}
                if(resultavg.size()!=0 && resultavg1.size()!=0){
    				result.get(0).setAvgWindVelval((result.get(0).getAvgWindVelval()+result1.get(0).getAvgWindVelval())/2);
    								
    				
    				}
			
			}
			
		
			
			 runGenSize = resultcount.size();
			double avgWindSpeed=0;
			if(resultavg.size()>0){
				for(int i = 0;i < resultavg.size();i++){
				avgWindSpeed+=resultavg.get(i).getAvgWindVelval();
				}
				if(runGenSize!=0){
				avgWindSpeed=avgWindSpeed/runGenSize;
				}
			}
			
			avgWindSpeed=(new BigDecimal(avgWindSpeed)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
			double allAvgWindVelval=0;
			double allMaxData=0;
			double allMinData=25;
			int size = result.size();
			if(size >0)
			{
				for(int i = 0;i < size;i++){
				
					allAvgWindVelval += result.get(i).getAvgWindVelval();
					if(result.get(i).getMaxData()>allMaxData){
						allMaxData=result.get(i).getMaxData();
					}
					if(result.get(i).getMinData()<allMinData){
						allMinData=result.get(i).getMinData();
					}
					double f0   = result.get(i).getAvgWindVelval();
					BigDecimal   b   =   new   BigDecimal(f0);  
					double f1  =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
					result.get(i).setAvgWindVelval(f1);
					
					double f2   = result.get(i).getMaxData();
					BigDecimal   b1   =   new   BigDecimal(f2);  
					double f3  =   b1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
					result.get(i).setMaxData(f3);
					
					double f4   = result.get(i).getMinData();
					BigDecimal   b2   =   new   BigDecimal(f4);  
					double f5  =   b2.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
					result.get(i).setMinData(f5);
					
				}
				allAvgWindVelval = allAvgWindVelval/size;
				allAvgWindVelval  =   (new   BigDecimal(allAvgWindVelval)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMaxData  =   (new   BigDecimal(allMaxData)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMinData  =   (new   BigDecimal(allMinData)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				maxWind  =   (new   BigDecimal(maxWind)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				minWind  =   (new   BigDecimal(minWind)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				
			}
			request.setAttribute("allAvgWindVelval", allAvgWindVelval);
			request.setAttribute("allMaxData", allMaxData);
			request.setAttribute("allMinData", allMinData);
			request.setAttribute("avgWindSpeed", avgWindSpeed);
			request.setAttribute("maxWind", maxWind);
			request.setAttribute("minWind", minWind);
			request.setAttribute("maxTime", maxTime.substring(0, 19));
			request.setAttribute("minTime", minTime.substring(0, 19));
			//合计最大风速时刻和合计最小风速时刻
			Double tableMaxWind=0.0;
			Double tableMinWind=30.0;
			String tableMaxTime=null;
			String tableMinTime=null;
			if(result.size()!=0){
			for(int i = 0;i < result.size();i++){
				if(result.get(i).getMaxData()>tableMaxWind){
					tableMaxWind=result.get(i).getMaxData();
					tableMaxTime=result.get(i).getMaxTime();
				}
				if(result.get(i).getMinData()<tableMinWind){
					tableMinWind=result.get(i).getMinData();
					tableMinTime=result.get(i).getMinTime();
				}
			}
			}
			request.setAttribute("tableMaxTime", tableMaxTime);
			request.setAttribute("tableMinTime", tableMinTime);
		}

		request.setAttribute("result", result);
		
		
		
		lineChartXml(result, request);
		
		return mapping.findForward("show");
	}

	private void lineChartXml(List<DataStatisticsDataVo> list, HttpServletRequest request) {
		
		if (list != null && !list.isEmpty()) {
			StringBuffer barXml = new StringBuffer();
			barXml.append("[");
			
			Iterator<DataStatisticsDataVo> iter = list.iterator();
			while (iter.hasNext()) {
				DataStatisticsDataVo elem = iter.next();
				//if(elem.getSaveTime().toString().contains(":15:00") || elem.getSaveTime().toString().contains(":30:00")
					//	|| elem.getSaveTime().toString().contains(":45:00") || elem.getSaveTime().toString().contains(":00:00"))
				{
					barXml.append("{").append("name").append(":\"").append(elem.getName());
					barXml.append("\",").append("avgwindval").append(":\"").append(elem.getAvgWindVelval());
					barXml.append("\"},");
					
				}
			}
			barXml.append("]");
			
			if (barXml.length() > 0) 
				request.setAttribute("barXml", barXml);
		}
		else
		{
			StringBuffer barXml = new StringBuffer();
			barXml.append("[{").append("name").append(":\"").append(0);
			barXml.append("\",").append("avgwindval").append(":\"").append(0).append("\"}]");
			
			request.setAttribute("barXml", barXml);
		}
	}
}
