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
import com.xjgc.wind.datastatistics.service.IWindJiReliabilityContrastService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindJiReliabilityContrastDataForm;
import com.xjgc.wind.util.HourMTimeUtil;
import com.xjgc.wind.util.MonthDayTimeUtil;
import com.xjgc.wind.util.YearFormatUtil;


public class WindJiReliabilityContrastDataAction extends DispatchAction {

	private static final Log log = LogFactory.getLog(WindJiReliabilityContrastDataAction.class);

	IWindJiReliabilityContrastService windJiReliabilityContrastService;
	IGeneratorService generatorService;
	
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		windJiReliabilityContrastService = (IWindJiReliabilityContrastService) wac.getBean("windJiReliabilityContrastService");
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

	// ��ȡ��ʷԤ������б�
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("entering 'show' method ...");
		
		WindJiReliabilityContrastDataForm dataForm = (WindJiReliabilityContrastDataForm) form;
		
		List<DataStatisticsDataVo> resultHour =null;
		List<DataStatisticsDataVo> resultHour1 =null;
		List<DataStatisticsDataVo> resultWind =null;
		List<DataStatisticsDataVo> resultWind1 =null;
		List<DataStatisticsDataVo> resultrandhour =null;
		List<DataStatisticsDataVo> resultrandhour1 =null;
		List<DataStatisticsDataVo> resultWindhour =null;
		List<DataStatisticsDataVo> resultWindhour1 =null;
		double avaHour=0;
		double allHour=0;
		
		if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {
			
			Date startDate = YearFormatUtil.get().parse(dataForm.getStartDateDisp());
			Date endDate = YearFormatUtil.get().parse(dataForm.getEndDateDisp());
			Calendar   calendar   =   new   GregorianCalendar(); 
			Calendar   calendar1   =   new   GregorianCalendar(); 
		  	calendar.setTime(startDate); 
		  	calendar1.setTime(endDate); 
			String startYear = new SimpleDateFormat("yyyy").format(calendar.getTime());
			String endYear = new SimpleDateFormat("yyyy").format(calendar1.getTime());
			resultrandhour=windJiReliabilityContrastService.list(dataForm.getStr());
			resultWindhour=windJiReliabilityContrastService.list(dataForm.getStr());
			if(startYear.compareTo(endYear)==0){
			
				resultHour = windJiReliabilityContrastService.listrandhour(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),dataForm.getStr(),1);
				resultWind = windJiReliabilityContrastService.listWind(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),dataForm.getStr(),1);
				for(int i=0;i<resultrandhour.size();i++){
					for(int j=0;j<resultHour.size();j++){
						if(resultrandhour.get(i).getId()==resultHour.get(j).getId()){
							resultrandhour.get(i).setTotalGenwh(resultHour.get(j).getTotalGenwh());
							resultrandhour.get(i).setCapacity(resultHour.get(j).getCapacity());
							resultrandhour.get(i).setHour(resultHour.get(j).getHour());
						}
						}
				}
				for(int i=0;i<resultWindhour.size();i++){
					for(int j=0;j<resultWind.size();j++){
						if(resultWindhour.get(i).getId()==resultWind.get(j).getId()){
							resultWindhour.get(i).setWindHours(resultWind.get(j).getWindHours());
						}
						}
				}
				}
			if(startYear.compareTo(endYear)!=0){
				
				resultHour = windJiReliabilityContrastService.listrandhour(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",dataForm.getStr(),1);
				resultHour1 = windJiReliabilityContrastService.listrandhour(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),dataForm.getStr(),2);	
				resultWind = windJiReliabilityContrastService.listWind(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",dataForm.getStr(),1);
				resultWind1 = windJiReliabilityContrastService.listWind(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),dataForm.getStr(),2);	
				for(int i=0;i<resultrandhour.size();i++){
	                if(resultHour.size()!=0){
					for(int j=0;j<resultHour.size();j++){
							if(resultrandhour.get(i).getId()==resultHour.get(j).getId()){
								resultrandhour.get(i).setTotalGenwh(resultrandhour.get(i).getTotalGenwh()+resultHour.get(j).getTotalGenwh());
								resultrandhour.get(i).setHour(resultrandhour.get(i).getHour()+resultHour.get(j).getHour());
								resultrandhour.get(i).setCapacity(resultHour.get(j).getCapacity());
							}
						}
					}
	                if(resultHour1.size()!=0){
						for(int j=0;j<resultHour1.size();j++){
								if(resultrandhour.get(i).getId()==resultHour1.get(j).getId()){
									resultrandhour.get(i).setTotalGenwh(resultrandhour.get(i).getTotalGenwh()+resultHour1.get(j).getTotalGenwh());
									resultrandhour.get(i).setHour(resultrandhour.get(i).getHour()+resultHour1.get(j).getHour());
								}
							}
						}
				}
				
				for(int i=0;i<resultWindhour.size();i++){
	                if(resultWind.size()!=0){
					for(int j=0;j<resultWind.size();j++){
							if(resultWindhour.get(i).getId()==resultWind.get(j).getId()){
								resultWindhour.get(i).setWindHours(resultWindhour.get(i).getWindHours()+resultWind.get(j).getWindHours());
								
							}
						}
					}
	                if(resultHour1.size()!=0){
						for(int j=0;j<resultHour1.size();j++){
								if(resultWindhour.get(i).getId()==resultHour1.get(j).getId()){
									resultWindhour.get(i).setWindHours(resultWindhour.get(i).getWindHours()+resultWind1.get(j).getWindHours());
								}
							}
						}
				}
				
				
				
				
			
			
               
			
			}
			
			
			
		
			if(resultrandhour.size()!=0){
				//把有效风时数加进来
				if(resultWindhour.size()!=0){
					for(int i=0;i<resultrandhour.size();i++)
					{
						for(int j=0;j<resultWindhour.size();j++){
							if(resultWindhour.get(j).getId()==resultrandhour.get(i).getId()){
								resultrandhour.get(i).setWindHour(resultWindhour.get(j).getWindHours());
							}
								
						}
					}
				}
				for(int i=0;i<resultrandhour.size();i++)
				{
				//有效利用小时数
				allHour += resultrandhour.get(i).getHour();
				avaHour += resultrandhour.get(i).getHour();
				//有效利用小时数
				resultrandhour.get(i).setHour(resultrandhour.get(i).getHour());
				
				}
				avaHour = avaHour/resultrandhour.size();
				allHour  =   (new   BigDecimal(allHour)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				avaHour  =   (new   BigDecimal(avaHour)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			
			request.setAttribute("allHour", allHour);
			request.setAttribute("avaHour", avaHour);
			
			request.setAttribute("resultrandhour", resultrandhour);
				
		}
		

	
		lineChartbar1Xml(resultrandhour, request);
		
		return mapping.findForward("show");
	}
	
	private void lineChartbar1Xml(List<DataStatisticsDataVo> list, HttpServletRequest request) {
		if (list != null && !list.isEmpty()) {
			
			StringBuffer bar1Xml = new StringBuffer();
			bar1Xml.append("[");
			
			Iterator<DataStatisticsDataVo> iter = list.iterator();
			while (iter.hasNext()) {
				DataStatisticsDataVo elem = iter.next();
					
				bar1Xml.append("{\"").append("hour\"").append(":\"").append(elem.getHour()).append("\",\"");
				bar1Xml.append("name\"").append(":\"").append(elem.getName()).append("\"},");
				
			}
			bar1Xml.append("]");

			//���������Ϣ
			if (bar1Xml.length() > 0) 
				request.setAttribute("bar1Xml", bar1Xml);
			request.setAttribute("size", list.size());
		}
		else{
			StringBuffer bar1Xml = new StringBuffer();
			bar1Xml.append("[{\"").append("hour\"").append(":\"").append(0).append("\",\"");
			bar1Xml.append("name\"").append(":\"").append(0).append("\"}]");
			request.setAttribute("bar1Xml", bar1Xml);
		}
	}
	
}







