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




import com.xjgc.wind.datastatistics.service.IWindGenwhService;
import com.xjgc.wind.datastatistics.service.IGeneratorService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindGenwhDataForm;
import com.xjgc.wind.datastatistics.web.form.GeneratorDataForm;
import com.xjgc.wind.util.DateFormatUtil;
import com.xjgc.wind.util.YMDHMSUtil;
import com.xjgc.wind.util.YearFormatUtil;

public class WindGenwhDataAction extends DispatchAction{

	private static final Log log = LogFactory.getLog(WindGenwhDataAction.class);
	
	IWindGenwhService windGenwhService;
	IGeneratorService generatorService;
	
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		windGenwhService = (IWindGenwhService) wac.getBean("windGenwhService");
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
		
		WindGenwhDataForm dataForm = (WindGenwhDataForm) form;
		List<DataStatisticsDataVo> result =null;
		List<DataStatisticsDataVo> resultg =null;
		List<DataStatisticsDataVo> result1 =null;
		List<DataStatisticsDataVo> result2 =null;
		List<DataStatisticsDataVo> result3 =null;
		double genSum = 0;
		 double allRunTime = 0;
		if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {
			Date startDate = YearFormatUtil.get().parse(dataForm.getStartDateDisp());
			Date endDate = YearFormatUtil.get().parse(dataForm.getEndDateDisp());
			Calendar   calendar   =   new   GregorianCalendar(); 
			Calendar   calendar1   =   new   GregorianCalendar(); 
		  	calendar.setTime(startDate); 
		  	calendar1.setTime(endDate); 
			String startYear = new SimpleDateFormat("yyyy").format(calendar.getTime());
			String endYear = new SimpleDateFormat("yyyy").format(calendar1.getTime());
			Date endDate1 = YMDHMSUtil.get().parse(dataForm.getEndDateDisp());	
			resultg= generatorService.list();
			if(startYear.compareTo(endYear)==0){
				result = windGenwhService.list(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),1);
				result2 = windGenwhService.listTime(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),1);
				for(int i=0;i<resultg.size();i++){
					for(int j=0;j<result.size();j++){
					if(resultg.get(i).getId()==result.get(j).getId()){
					resultg.get(i).setTotalGenwh(result.get(j).getTotalGenwh());
					}
					}
				
					for(int j=0;j<result2.size();j++){
						if(resultg.get(i).getId()==result2.get(j).getId()){
									resultg.get(i).setHour(result2.get(j).getHour());
								}
								}
						
				}
					
			
			}
			
            if(startYear.compareTo(endYear)!=0){
				
            	result = windGenwhService.list(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",1);
            	result1 = windGenwhService.list(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),2);	
            	result2 = windGenwhService.listTime(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",1);
            	result3 = windGenwhService.listTime(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),2);	
				
            	for(int i=0;i<resultg.size();i++){
                if(result.size()!=0){
				for(int j=0;j<result.size();j++){
						if(resultg.get(i).getId()==result.get(j).getId()){
							resultg.get(i).setTotalGenwh(resultg.get(i).getTotalGenwh()+result.get(j).getTotalGenwh());
						}
					}
				}
                if(result1.size()!=0){
				for(int j=0;j<result1.size();j++){
						if(resultg.get(i).getId()==result1.get(j).getId()){
							resultg.get(i).setTotalGenwh(resultg.get(i).getTotalGenwh()+result1.get(j).getTotalGenwh());
						}
					}
				}
                
                if(result2.size()!=0){
    				for(int j=0;j<result2.size();j++){
    						if(resultg.get(i).getId()==result2.get(j).getId()){
    							resultg.get(i).setHour(resultg.get(i).getHour()+result2.get(j).getHour());
    						}
    					}
    				}
                    if(result3.size()!=0){
    				for(int j=0;j<result3.size();j++){
    						if(resultg.get(i).getId()==result3.get(j).getId()){
    							resultg.get(i).setHour(resultg.get(i).getHour()+result3.get(j).getHour());
    						}
    					}
    				}
                
			}
			
			
			
			}
			
			int size = resultg.size();
			if(size >0)
			{
				for(int i = 0;i < size;i++){
					genSum+=resultg.get(i).getTotalGenwh();
					allRunTime+=resultg.get(i).getHour();
					double f0   = resultg.get(i).getTotalGenwh(); 
					BigDecimal   b   =   new   BigDecimal(f0);  
					double f1  =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
					resultg.get(i).setTotalGenwh(f1);
					resultg.get(i).setStrId(String.valueOf(resultg.get(i).getId()));
				}
			}
			
	
		}
		StringBuffer buffer = new StringBuffer(); 
		buffer.append("<tr style='text-align:center;'>");
		buffer.append("<td colspan='1'>风机名称</td>");
		buffer.append("<td colspan='1'>发电量(").append("kWh").append(")</td>");
		buffer.append("</tr>");
		request.setAttribute("top", buffer.toString());
		//合计发电量保留两位小数
				double f0   = genSum; 
				BigDecimal   b   =   new   BigDecimal(f0);  
				double f1  =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				genSum=f1;
		//合计运行时间保留两位小数
				double f2   = allRunTime; 
				BigDecimal   b1   =   new   BigDecimal(f2);  
				double f3  =   b1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allRunTime=f3;
				request.setAttribute("genSum", genSum);
				request.setAttribute("resultg", resultg);
		lineChartXml(resultg, request);
		request.setAttribute("allRunTime", allRunTime);
		return mapping.findForward("show");
	}
	public ActionForward showDetailed(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List<DataStatisticsDataVo> result =null;
		List<DataStatisticsDataVo> result1 =null;
		
		
		String equipId = request.getParameter("equipId");
		String startDateDisp = request.getParameter("startDateDisp");
		String endDateDisp = request.getParameter("endDateDisp");
		Date startDate = YearFormatUtil.get().parse(startDateDisp);
		Date endDate = YearFormatUtil.get().parse(endDateDisp);
		Calendar   calendar   =   new   GregorianCalendar(); 
		Calendar   calendar1   =   new   GregorianCalendar(); 
	  	calendar.setTime(startDate); 
	  	calendar1.setTime(endDate); 
		String startYear = new SimpleDateFormat("yyyy").format(calendar.getTime());
		String endYear = new SimpleDateFormat("yyyy").format(calendar1.getTime());
		
		if(startYear.compareTo(endYear)==0){
			result = windGenwhService.listDetailed(equipId,startDateDisp,endDateDisp,1);
		}
		 if(startYear.compareTo(endYear)!=0){
				
         	result = windGenwhService.listDetailed(equipId,startDateDisp,startYear+"-12-31 23:59:59",1);
         	result1 = windGenwhService.listDetailed(equipId,endYear+"-01-01 00:00:00",endDateDisp,2);	
         	result.addAll(result1);	
			}
		 
	
			
		request.setAttribute("result", result);
		return mapping.findForward("showDetailed");
	}
	private void lineChartXml(List<DataStatisticsDataVo> list, HttpServletRequest request) {
		
		if (list != null && !list.isEmpty()) {
			StringBuffer barXml = new StringBuffer();
			barXml.append("[");
			
			Iterator<DataStatisticsDataVo> iter = list.iterator();
			while (iter.hasNext()) {
				DataStatisticsDataVo elem = iter.next();
				
				barXml.append("{").append("name").append(":\"").append(elem.getName());
				barXml.append("\",").append("totalgenwh").append(":\"").append(elem.getTotalGenwh());
				barXml.append("\"},");
			}
			barXml.append("]");
			
			if (barXml.length() > 0) 
				request.setAttribute("barXml", barXml);
		}
		else
		{
			StringBuffer barXml = new StringBuffer();
			barXml.append("[{").append("name").append(":\"").append(0);
			barXml.append("\",").append("totalgenwh").append(":\"").append(0).append("\"}]");
			
			request.setAttribute("barXml", barXml);
		}
	}
} 
