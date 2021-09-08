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
import com.xjgc.wind.datastatistics.service.IWindPlcReportService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.GeneratorDataForm;
import com.xjgc.wind.datastatistics.web.form.WindJiReliabilityContrastDataForm;
import com.xjgc.wind.datastatistics.web.form.WindPlcReportDataForm;
import com.xjgc.wind.util.YMDHMSUtil;
import com.xjgc.wind.util.YearFormatUtil;


public class WindPlcReportDataAction extends DispatchAction {

	private static final Log log = LogFactory.getLog(WindPlcReportDataAction.class);

	IWindPlcReportService windPlcReportService;

	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		windPlcReportService = (IWindPlcReportService) wac.getBean("windPlcReportService");																														
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

	// ��ȡ��ʷԤ�������б�
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("entering 'show' method ...");

		WindPlcReportDataForm dataForm = (WindPlcReportDataForm) form;
		List<DataStatisticsDataVo> result =null;
		List<DataStatisticsDataVo> result1 =null;
		
		if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {
			
			Date startDate = YearFormatUtil.get().parse(dataForm.getStartDateDisp());
			Date endDate = YearFormatUtil.get().parse(dataForm.getEndDateDisp());
			Calendar   calendar   =   new   GregorianCalendar(); 
			Calendar   calendar1   =   new   GregorianCalendar(); 
		  	calendar.setTime(startDate); 
		  	calendar1.setTime(endDate); 
			String startYear = new SimpleDateFormat("yyyy").format(calendar.getTime());
			String endYear = new SimpleDateFormat("yyyy").format(calendar1.getTime());
			
			if(startYear.compareTo(endYear)==0){
			result = windPlcReportService.list(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),dataForm.getStr(),1);
			
			}
			if(startYear.compareTo(endYear)!=0){
				
				result = windPlcReportService.list(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",dataForm.getStr(),1);
				result1 = windPlcReportService.list(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),dataForm.getStr(),2);	
				
				result.addAll(result1);
                
			}
			Date endDate1 = YMDHMSUtil.get().parse(dataForm.getEndDateDisp());	
              
			if(result.size()>0)
			{
				for(int i = 0;i < result.size();i++)
				{
					//DataStatisticsDataVo obj=result.get(i);
					//Date removedate=obj.getRemoveTime();
					
					if(null!=result.get(i).getEndTime()){
						long time = result.get(i).getEndTime().getTime()-result.get(i).getCurTime().getTime();
						result.get(i).setFaultTime(time*0.001/3600);
					}else{
						
						//Date nowDate=new Date();
						long time = endDate1.getTime()-result.get(i).getCurTime().getTime();
						result.get(i).setFaultTime(time*0.001/3600);
					}
					
				}
			}
			
			request.setAttribute("result", result);
				
		}
		

	
		return mapping.findForward("show");
	}
	

}







