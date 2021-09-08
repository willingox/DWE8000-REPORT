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
import com.xjgc.wind.datastatistics.service.IRuningInfo_TenMinuteReportService;
import com.xjgc.wind.datastatistics.service.IRuningInfo_TenMinuteService;
import com.xjgc.wind.datastatistics.service.IGeneratorService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.vo.DataStatisticsReportDataVo;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_TenMinuteDataForm;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_TenMinuteReportDataForm;
import com.xjgc.wind.util.HourMTimeUtil;
import com.xjgc.wind.util.MonthDayHourMTimeUtil;
import com.xjgc.wind.util.YearFormatUtil;


public class RuningInfo_TenMinuteReportDataAction extends DispatchAction{


	private static final Log log = LogFactory.getLog(RuningInfo_TenMinuteReportDataAction.class);
	IRuningInfo_TenMinuteReportService runingInfo_TenMinuteReportService; 
	IGeneratorService generatorService;
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		runingInfo_TenMinuteReportService = (IRuningInfo_TenMinuteReportService) wac.getBean("runingInfo_TenMinuteReportService");																														
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

		List<String> check = new ArrayList<String>();
		for(int i=0;i<3;i++){
			check.add(i, null);	
		}
		
		
		String[] flag = request.getParameterValues("flag");
		int length = 0;
		if(flag != null)
		{
			length = flag.length;
		}
		if(length!=0){
			 for(int i=0;i<length;i++){
					if(flag[i].compareTo("1")==0){
						check.add(0,"checked");
					}
					if(flag[i].compareTo("2")==0){
						check.add(1,"checked");
					}
					if(flag[i].compareTo("3")==0){
						check.add(2,"checked");
					}
					
						
				}
		}
		for(int i=0;i<3;i++){
			request.setAttribute("check"+i, check.get(i));	
		}
		RuningInfo_TenMinuteReportDataForm dataForm = (RuningInfo_TenMinuteReportDataForm) form;
		List<DataStatisticsReportDataVo> resulttu =null;
		List<DataStatisticsReportDataVo> resultcheck =null;
		 String[] check_value=null;
		 int len=0;
		 String check_val=null;
	
		request.setAttribute("totalRows",100000);  
		  if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {
			
					
				//选择的间隔id
			  String str=dataForm.getStr();
				
				//选择的遥测
			  check_val = request.getParameter("check_val");
				
			 
				 if(check_val!=null){
				 check_value=check_val.split(",");
				 }	 
			   
				
				
					Context context = new HttpServletRequestContext(request);   
					LimitFactory limitFactory = new TableLimitFactory(context);
					Limit limit = new TableLimit(limitFactory);
					limit.setRowAttributes(Integer.MAX_VALUE, 100); 
					int pageSize=limit.getCurrentRowsDisplayed();
					int pageNo=limit.getPage();
					request.setAttribute("totalRows",100000);
					System.out.println(pageSize);
					System.out.println(pageNo);
					resulttu = runingInfo_TenMinuteReportService.list(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),str,check_value,dataForm.getFlag(),1,pageNo,pageSize);
					
				
				
			//获取表格的名字
				resultcheck = runingInfo_TenMinuteReportService.listVal();
				
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
				buffer.append("<td colspan='1'>时间</td><td colspan='1'>名称</td><td colspan='1'>");
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
		List<DataStatisticsDataVo> queryTime =new ArrayList<DataStatisticsDataVo>();
		DataStatisticsDataVo flag1=new DataStatisticsDataVo();
		flag1.setId(1);
		flag1.setName("10分钟");
		queryTime.add(flag1);
		DataStatisticsDataVo flag2=new DataStatisticsDataVo();
		flag2.setId(2);
		flag2.setName("30分钟");
		queryTime.add(flag2);
		DataStatisticsDataVo flag3=new DataStatisticsDataVo();
		flag3.setId(3);
		flag3.setName("1小时");
		queryTime.add(flag3);
	
		request.setAttribute("queryTime", queryTime);
		request.setAttribute("resulttu", resulttu);
		request.setAttribute("check_value", check_value);
		return mapping.findForward("show");
	}
	public ActionForward select(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<DataStatisticsReportDataVo> resultcheck =null;
		
		
		resultcheck = runingInfo_TenMinuteReportService.listVal();
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
