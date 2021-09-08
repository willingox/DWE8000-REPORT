package com.xjgc.wind.datastatistics.web.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.actions.DispatchAction;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xjgc.wind.datastatistics.service.IFaultQueryService;
import com.xjgc.wind.datastatistics.service.IGeneratorService;
import com.xjgc.wind.datastatistics.service.IRunGeneratorNumService;
import com.xjgc.wind.datastatistics.service.IWindAvailabilityContrastService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindAvailabilityContrastDataForm;
import com.xjgc.wind.querytree.vo.ResultVO;
import com.xjgc.wind.util.DateFormatUtil;
import com.xjgc.wind.util.HourMTimeUtil;
import com.xjgc.wind.util.MonthDayTimeUtil;
import com.xjgc.wind.util.YMDHMSUtil;
import com.xjgc.wind.util.YearFormatUtil;


public class WindAvailabilityContrastDataAction extends DispatchAction {

	private static final Log log = LogFactory.getLog(WindAvailabilityContrastDataAction.class);

	IFaultQueryService faultQueryService;
	IWindAvailabilityContrastService windAvailabilityContrastService;
	IGeneratorService  generatorService;
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		windAvailabilityContrastService = (IWindAvailabilityContrastService) wac.getBean("windAvailabilityContrastService");	
		generatorService = (IGeneratorService) wac.getBean("generatorService");	
		faultQueryService = (IFaultQueryService) wac.getBean("faultQueryService");	
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
	
		List<DataStatisticsDataVo> result3 =null;
		List<DataStatisticsDataVo> result1 =null;
		List<DataStatisticsDataVo> result2 =new ArrayList<DataStatisticsDataVo>() ;
		if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {
		WindAvailabilityContrastDataForm dataForm = (WindAvailabilityContrastDataForm) form;
		//dataForm.setAlarmType(5);
		String str=dataForm.getStr();
		result3=generatorService.list();
		String[] arr =null;
		arr = str.split(",");
		int size=arr.length;
		if(str.length()>11){
			if(arr[0].compareTo("district-1")==0){
		for(int i=1;i<size;i++){
			DataStatisticsDataVo StatisticsDataVo=new DataStatisticsDataVo();
			StatisticsDataVo.setId(Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length())));
			result2.add(StatisticsDataVo);
			} 
			}
			else{
				for(int i=0;i<size;i++){
					DataStatisticsDataVo StatisticsDataVo=new DataStatisticsDataVo();
					StatisticsDataVo.setId(Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length())));
					result2.add(StatisticsDataVo);
					} 	
			}
		}
		if(str.length()<11){
			for(int i=0;i<result3.size();i++){
				DataStatisticsDataVo StatisticsDataVo=new DataStatisticsDataVo();
				StatisticsDataVo.setId(result3.get(i).getId());
				result2.add(StatisticsDataVo);
				} 
			}
		if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {
			result1=windAvailabilityContrastService.list(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),str);
			
			Date startDate = YMDHMSUtil.get().parse(dataForm.getStartDateDisp());
			Date endDate = YMDHMSUtil.get().parse(dataForm.getEndDateDisp());	
		  	long time1 =endDate.getTime()-startDate.getTime();
			Date endDate1 = YMDHMSUtil.get().parse(dataForm.getEndDateDisp());	
			
			if( result1.size()!=0){
				
				for(int i=0;i<result1.size();i++){
			
							if(null!=result1.get(i).getRemoveTime()){
								long time=0;
								if(result1.get(i).getRemoveTime().getTime()-endDate1.getTime()>0){
									 time = endDate1.getTime()-result1.get(i).getHappenTime().getTime();	
								}
								else{
									 time = result1.get(i).getRemoveTime().getTime()-result1.get(i).getHappenTime().getTime();
								}
								
								result1.get(i).setFaultTime(time*0.001/3600);
								
							}else{
								
								long time = endDate.getTime()-result1.get(i).getHappenTime().getTime();
								result1.get(i).setFaultTime(time*0.001/3600);
							}
						
						}
					}
					
			for(int i=0;i<result2.size();i++){
				double faultTime = 0;
				String name=null;
				for(int k=0;k<result3.size();k++){
					if(result2.get(i).getId()==result3.get(k).getId()){
						name=result3.get(k).getName();
					}
				}
				for(int j=0;j<result1.size();j++){
				if(result2.get(i).getId()==result1.get(j).getEquipId()){
					faultTime+=result1.get(j).getFaultTime();
					
				}
				}
				result2.get(i).setName(name);
				result2.get(i).setFaultTime(faultTime);
				result2.get(i).setHour(time1*0.001/3600);
				result2.get(i).setAvaTime(result2.get(i).getHour()-result2.get(i).getFaultTime());
				result2.get(i).setAvailability((1-result2.get(i).getFaultTime()/result2.get(i).getHour())*100);
			}
			
				

			double hourSum = 0;
			double avaTimeAvg = 0;
			double faultTimeSum = 0;
			double avaAvailability = 0;
            
			if(result2.size()!=0){
				
				for(int i=0;i<result2.size();i++)
				{
				avaAvailability += result2.get(i).getAvailability();
				hourSum += result2.get(i).getHour();	
				avaTimeAvg += result2.get(i).getAvaTime();
				faultTimeSum += result2.get(i).getFaultTime();
				
				
				}
				avaAvailability = avaAvailability/result2.size();
				avaTimeAvg = avaTimeAvg/result2.size();
				
				hourSum  =   (new   BigDecimal(hourSum)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				avaTimeAvg  =   (new   BigDecimal(avaTimeAvg)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				faultTimeSum  =   (new   BigDecimal(faultTimeSum)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				avaAvailability  =   (new   BigDecimal(avaAvailability)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
			
			}
			
			request.setAttribute("hourSum", hourSum);	
			request.setAttribute("avaTimeAvg", avaTimeAvg);	
			request.setAttribute("avaAvailability", avaAvailability);	
			request.setAttribute("faultTimeSum", faultTimeSum);	
		}
		request.setAttribute("result", result2);
		}
		
		lineChartmap1Xml(result2, request);

		return mapping.findForward("show");
	}


	private void lineChartmap1Xml(List<DataStatisticsDataVo> list, HttpServletRequest request) {
		if (list != null && !list.isEmpty()) {
			
			StringBuffer barXml = new StringBuffer();
			barXml.append("[");
			
			Iterator<DataStatisticsDataVo> iter = list.iterator();
			while (iter.hasNext()) {
				DataStatisticsDataVo elem = iter.next();
				barXml.append("{").append("name").append(":\"").append(elem.getName());
				barXml.append("\",").append("availability").append(":\"").append(elem.getAvailability());
				barXml.append("\"},");
				
			}
			barXml.append("]");

			
			if (barXml.length() > 0) 
				request.setAttribute("barXml", barXml);
		}
		else{
			StringBuffer barXml = new StringBuffer();
		
			barXml.append("[{").append("name").append(":\"").append(0);
			barXml.append("\",").append("availability").append(":\"").append(0);
			barXml.append("\"}]");
			request.setAttribute("barXml", barXml);

		}
	}

	
}







