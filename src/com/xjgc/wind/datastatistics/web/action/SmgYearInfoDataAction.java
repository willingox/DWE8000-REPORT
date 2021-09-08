package com.xjgc.wind.datastatistics.web.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.xjgc.wind.datastatistics.service.ISmgYearInfoService;
import com.xjgc.wind.datastatistics.service.ISmgsysinfoService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.SmgYearInfoDataForm;
import com.xjgc.wind.util.YearMonthFormatUtil;

public class SmgYearInfoDataAction extends DispatchAction {

	private static final Log log = LogFactory.getLog(SmgYearInfoDataAction.class);
	
	ISmgYearInfoService smgYearInfoService;
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		smgYearInfoService = (ISmgYearInfoService) wac.getBean("smgYearInfoService");

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
		
		SmgYearInfoDataForm dataForm = (SmgYearInfoDataForm) form;
		List<DataStatisticsDataVo> resulttime =null;
		List<DataStatisticsDataVo> resultname =null;
		
		
		if (StringUtils.isEmpty(request.getParameter("isFirst"))) {
			//
			Calendar today = GregorianCalendar.getInstance();
			String strToday = new SimpleDateFormat("yyyy").format(today.getTime());
			dataForm.setStartDateDisp(strToday);
			

		}
		
		resulttime = smgYearInfoService.listtime(dataForm);
		resultname = smgYearInfoService.listname(dataForm);
		
		if(resulttime.size()!=0){
			for(int i=0;i<resulttime.size();i++)
			{
			double f0   = resulttime.get(i).getTodayGenwh()*0.001;  
			BigDecimal   b   =   new   BigDecimal(f0);  
			double f1  =   b.setScale(1,   BigDecimal.ROUND_HALF_UP).doubleValue();
			resulttime.get(i).setTodayGenwh(f1);
			}
		}
		if(resultname.size()!=0){
			for(int i=0;i<resultname.size();i++)
			{
			double f0   = resultname.get(i).getTodayGenwh()*0.001;  
			BigDecimal   b   =   new   BigDecimal(f0);  
			double f1  =   b.setScale(1,   BigDecimal.ROUND_HALF_UP).doubleValue();
			resultname.get(i).setTodayGenwh(f1);
			}
		}
		
		EChartsTimeXml(resulttime,request);
		EChartsNameXml(resultname,request);

		
		return mapping.findForward("show");
	}
	
private void EChartsTimeXml(List<DataStatisticsDataVo> list,HttpServletRequest request){
		
		if (list != null && !list.isEmpty()) {
			
			StringBuffer timegenwhXml = new StringBuffer();
			timegenwhXml.append("[");
			
			Iterator<DataStatisticsDataVo> iter = list.iterator();
			while (iter.hasNext()) {
				DataStatisticsDataVo elem = iter.next();
				
				timegenwhXml.append("{\"").append("time\"").append(":\"").append(YearMonthFormatUtil.get().format(elem.getSaveTime())).append("\",\"");
				timegenwhXml.append("todayGenwh\"").append(":\"").append(elem.getTodayGenwh()).append("\"},");	

			}
			timegenwhXml.append("]");

			//
			if (timegenwhXml.length() > 0) 
				request.setAttribute("timegenwhXml", timegenwhXml);
		}
		else
		{
			StringBuffer timegenwhXml = new StringBuffer();
			timegenwhXml.append("[{\"").append("name\"").append(":\"").append(0).append("\",\"");
			timegenwhXml.append("todayGenwh\"").append(":\"").append(0).append("\"}]");	

			request.setAttribute("timegenwhXml", timegenwhXml);
		}
	}
	
	private void EChartsNameXml(List<DataStatisticsDataVo> list,HttpServletRequest request){
		
if (list != null && !list.isEmpty()) {
			
			StringBuffer namegenwhXml = new StringBuffer();
			namegenwhXml.append("[");
			
			Iterator<DataStatisticsDataVo> iter = list.iterator();
			while (iter.hasNext()) {
				DataStatisticsDataVo elem = iter.next();
				
				namegenwhXml.append("{\"").append("name\"").append(":\"").append(elem.getName()).append("\",\"");
				namegenwhXml.append("todayGenwh\"").append(":\"").append(elem.getTodayGenwh()).append("\"},");	
				
			}
			namegenwhXml.append("]");

			//
			if (namegenwhXml.length() > 0) 
				request.setAttribute("namegenwhXml", namegenwhXml);
		}
		else
		{
			StringBuffer namegenwhXml = new StringBuffer();
			namegenwhXml.append("[{\"").append("name\"").append(":\"").append(0).append("\",\"");
			namegenwhXml.append("todayGenwh\"").append(":\"").append(0).append("\"}]");	

			request.setAttribute("namegenwhXml", namegenwhXml);
		}
	}
}
