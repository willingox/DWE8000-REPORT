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
import org.apache.commons.lang.math.NumberUtils;
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
import com.xjgc.wind.datastatistics.service.ISmgsysinfoService;
import com.xjgc.wind.datastatistics.service.IWindAllReportService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.FaultQueryDataForm;
import com.xjgc.wind.datastatistics.web.form.WindAllReportDataForm;

public class WindAllReportDataAction extends DispatchAction{

	private static final Log log = LogFactory.getLog(WindAllReportDataAction.class);
	
	IWindAllReportService windAllReportService;
	IGeneratorService generatorService;
	ISmgsysinfoService smgsysinfoService;
	
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		windAllReportService = (IWindAllReportService) wac.getBean("windAllReportService");
		generatorService = (IGeneratorService) wac.getBean("generatorService");
		smgsysinfoService = (ISmgsysinfoService) wac.getBean("smgsysinfoService");
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
		
		WindAllReportDataForm dataForm = (WindAllReportDataForm) form;
		
		List<DataStatisticsDataVo> result =null;
		
		if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {
			
			result = windAllReportService.list(dataForm);
			
			if(result.size()>0)
			{
				for(int i = 0;i < result.size();i++)
				{
					//DataStatisticsDataVo obj=result.get(i);
					//Date removedate=obj.getRemoveTime();
					
					if(null!=result.get(i).getRemoveTime()){
						long time = result.get(i).getRemoveTime().getTime()-result.get(i).getHappenTime().getTime();
						result.get(i).setCount(time*0.001/3600);
					}else{
						
						Date nowDate=new Date();
						long time = nowDate.getTime()-result.get(i).getHappenTime().getTime();
						result.get(i).setCount(time*0.001/3600);
					}
					
				}
			}
			
			request.setAttribute("result", result);
			
		
		}
	
		request.setAttribute("generator", generatorService.list());
	
		return mapping.findForward("show");
	}
	
}
