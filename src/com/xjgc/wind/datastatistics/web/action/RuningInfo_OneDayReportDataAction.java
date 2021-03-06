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
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.LimitFactory;
import org.extremecomponents.table.limit.TableLimit;
import org.extremecomponents.table.limit.TableLimitFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fr.third.org.apache.poi.hssf.record.formula.functions.Int;
import com.google.gson.Gson;
import com.xjgc.wind.datastatistics.service.IRuningInfo_OneDayReportService;
import com.xjgc.wind.datastatistics.service.IRuningInfo_OneDayService;
import com.xjgc.wind.datastatistics.service.IGeneratorService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.vo.DataStatisticsReportDataVo;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_OneDayDataForm;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_OneDayReportDataForm;
import com.xjgc.wind.util.HourMTimeUtil;
import com.xjgc.wind.util.MonthDayHourMTimeUtil;
import com.xjgc.wind.util.YearFormatUtil;


public class RuningInfo_OneDayReportDataAction extends DispatchAction{


	private static final Log log = LogFactory.getLog(RuningInfo_OneDayReportDataAction.class);
	IRuningInfo_OneDayReportService runingInfo_OneDayReportService; 
	IGeneratorService generatorService;
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		runingInfo_OneDayReportService = (IRuningInfo_OneDayReportService) wac.getBean("runingInfo_OneDayReportService");																														
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

		// ?????????????????
		RuningInfo_OneDayReportDataForm dataForm = (RuningInfo_OneDayReportDataForm) form;
		List<DataStatisticsReportDataVo> resulttu =null;
		List<DataStatisticsReportDataVo> resultcheck =null;
		 String[] check_value=null;
		 int len=0;
		 String check_val=null;
		request.setAttribute("totalRows",100000);
		  if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {
			
			  Context context = new HttpServletRequestContext(request);   
				LimitFactory limitFactory = new TableLimitFactory(context);
				Limit limit = new TableLimit(limitFactory);
				limit.setRowAttributes(Integer.MAX_VALUE, 100); 
				int pageSize=limit.getCurrentRowsDisplayed();
				int pageNo=limit.getPage();
				request.setAttribute("totalRows",100000);
				System.out.println(pageSize);
				System.out.println(pageNo);	
				//???????????????id
			  String str=dataForm.getStr();
				
				
				//???????????????
			  check_val = request.getParameter("check_val");
				
			 
				 if(check_val!=null){
				 check_value=check_val.split(",");
				 }	 
			   
				 resulttu = runingInfo_OneDayReportService.list(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),str,check_value,dataForm.getFlag(),1,pageNo,pageSize);
				
			//?????????????????????
				resultcheck = runingInfo_OneDayReportService.listVal();
				
			 for(int i=0;i<check_value.length;i++){
					for(int j=0;j<resultcheck.size();j++){
						if(check_value[i].equals(resultcheck.get(j).getColumndes())){
							check_value[i]=resultcheck.get(j).getColumnName();
							
						}
						
					 }
					len++;
				 }
			
			// resultlend.add(e);
			StringBuffer buffer = new StringBuffer(); 
			buffer.append("<tr style='text-align:center;'>");
				buffer.append("<td colspan='1'>??????</td><td colspan='1'>??????</td><td colspan='1'>");
			for(int i = 0;i < check_value.length;i++){
				
				if(i+1 < check_value.length){
					buffer.append(check_value[i]).append("</td><td colspan='1'>");
				}
				else{
					buffer.append(check_value[i]).append("</td>");
				
				}
				


			}
			 buffer.append("</tr>");
			 request.setAttribute("top", buffer.toString());
			 buffer.delete(0, buffer.length());
			
			
		}
		
		request.setAttribute("resulttu", resulttu);
		request.setAttribute("check_value", check_value);
		return mapping.findForward("show");
	}
	public ActionForward select(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<DataStatisticsReportDataVo> resultcheck =null;
		
		
		resultcheck = runingInfo_OneDayReportService.listVal();
		ArrayList<String> resultCheckName = new ArrayList<String>();
		ArrayList<String> resultCheckValue = new ArrayList<String>();
		
		if(resultcheck.size()!=0){
			for(int i=0;i<resultcheck.size();i++ ){
			resultCheckName.add(resultcheck.get(i).getColumnName());
			resultCheckValue.add(resultcheck.get(i).getColumndes());
		    request.setAttribute("resultcheckname"+i, resultCheckName.get(i));
		    request.setAttribute("resultcheckvalue"+i, resultCheckValue.get(i));
			}
		}
		request.setAttribute("resultCheckName", resultCheckName);
		return mapping.findForward("select");
	}

	
		
}
