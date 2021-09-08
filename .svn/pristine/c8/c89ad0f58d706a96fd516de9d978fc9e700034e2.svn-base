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
import com.xjgc.wind.datastatistics.service.IGeneratStatisticsService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.GeneratStatisticsDataForm;
import com.xjgc.wind.util.HourMTimeUtil;



public class GeneratStatisticsDataAction extends DispatchAction {

	private static final Log log = LogFactory.getLog(GeneratStatisticsDataAction.class);

	IGeneratStatisticsService generatStatisticsService;

	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		generatStatisticsService = (IGeneratStatisticsService) wac.getBean("generatStatisticsService");	

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


		GeneratStatisticsDataForm dataForm = (GeneratStatisticsDataForm) form;
		List<DataStatisticsDataVo> resultpw =null;
		List<DataStatisticsDataVo> resultgenwh =null;

		if (StringUtils.isEmpty(request.getParameter("isFirst"))) {
			
			Calendar today = GregorianCalendar.getInstance();
			today.add(Calendar.DATE,-1);
			String strToday = new SimpleDateFormat("yyyy-MM-dd").format(today.getTime());
			dataForm.setStartDateDisp(strToday);

		}

		resultpw = generatStatisticsService.listpw(dataForm);
		resultgenwh = generatStatisticsService.listgenwh(dataForm);
	
		if(resultpw.size()!=0){
			for(int i=0;i<resultpw.size();i++)
			{
				double f0   = resultpw.get(i).getCurp();  
				BigDecimal   b   =   new   BigDecimal(f0);  
				double f1  =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				resultpw.get(i).setCurp(f1);
				
				String str = resultpw.get(i).getWindVelval();
				
				double f2   = resultpw.get(i).getSunLightVal();  
				BigDecimal   b1   =   new   BigDecimal(f2);  
				double f3  =   b1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				resultpw.get(i).setSunLightVal(f3);
			}
		}
		
		if(resultgenwh.size()!=0){
			for(int i=0;i<resultgenwh.size();i++)
			{
			double f0   = resultgenwh.get(i).getTodayGenwh();  
			BigDecimal   b   =   new   BigDecimal(f0);  
			double f1  =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
			resultgenwh.get(i).setTodayGenwh(f1);
			
			}
		}
	

		lineChartpwXml(resultpw, request);
		lineChartgenwhXml(resultgenwh, request);

		return mapping.findForward("show");
	}
	
	private void lineChartpwXml(List<DataStatisticsDataVo> list,HttpServletRequest request) {
		if (list != null && !list.isEmpty()) {
			
			StringBuffer pwXml = new StringBuffer();
			pwXml.append("[");
			
			Iterator<DataStatisticsDataVo> iter = list.iterator();
			while (iter.hasNext()) {
				DataStatisticsDataVo elem = iter.next();
				pwXml.append("{\"").append("time\"").append(":\"").append(HourMTimeUtil.get().format(elem.getSaveTime())).append("\",\"");
				pwXml.append("curp\"").append(":\"").append(elem.getCurp()).append("\",\"").append("sunlight\"");
				pwXml.append(":\"").append(elem.getSunLightVal()).append("\"},");
				
			}
			pwXml.append("]");

			if (pwXml.length() > 0)
				request.setAttribute("pwXml", pwXml);
			request.setAttribute("size", list.size());
		}
		else
		{
			StringBuffer pwXml = new StringBuffer();
			pwXml.append("[");
			pwXml.append("{\"").append("time\"").append(":\"").append(0).append("\",\"");
			pwXml.append("curp\"").append(":\"").append(0).append("\"}]");
			request.setAttribute("pwXml", pwXml);
		}
	}
	
	private void lineChartgenwhXml(List<DataStatisticsDataVo> list, HttpServletRequest request) {
		if (list != null && !list.isEmpty()) {
			
			StringBuffer genwhXml = new StringBuffer();
			genwhXml.append("[");
			
			Iterator<DataStatisticsDataVo> iter = list.iterator();
			while (iter.hasNext()) {
				DataStatisticsDataVo elem = iter.next();
				
				genwhXml.append("{\"").append("name\"").append(":\"").append(elem.getName()).append("\",\"");
				genwhXml.append("todayGenwh\"").append(":\"").append(elem.getTodayGenwh()).append("\"},");	
			}
			genwhXml.append("]");

			if (genwhXml.length() > 0) 
				request.setAttribute("genwhXml", genwhXml);
			request.setAttribute("size", list.size());
		}
		else
		{
			StringBuffer genwhXml = new StringBuffer();
			genwhXml.append("[{\"").append("name\"").append(":\"").append(0).append("\",\"");
			genwhXml.append("todayGenwh\"").append(":\"").append(0).append("\"}]");	

			request.setAttribute("genwhXml", genwhXml);
		}
	}

	
	private void lineChartsunlightXml(List<DataStatisticsDataVo> list, HttpServletRequest request) {
		if (list != null && !list.isEmpty()) {
			
			StringBuffer sunXml = new StringBuffer();
			sunXml.append("[");
			
			Iterator<DataStatisticsDataVo> iter = list.iterator();
			while (iter.hasNext()) {
				DataStatisticsDataVo elem = iter.next();
				sunXml.append("{\"").append("time\"").append(":\"").append(HourMTimeUtil.get().format(elem.getSaveTime())).append("\",\"");
				sunXml.append("sunlight\"").append(":\"").append(elem.getSunLightVal()).append("\"},");	
				
			}
			sunXml.append("]");

	
			if (sunXml.length() > 0) 
				request.setAttribute("sunXml", sunXml);
		}
		else
		{
			StringBuffer sunXml = new StringBuffer();
			sunXml.append("[{\"").append("name\"").append(":\"").append(0).append("\",\"");
			sunXml.append("totalGenwh\"").append(":\"").append(0).append("\"}]");	

			request.setAttribute("sunXml", sunXml);
		}
	}
	
	
}








