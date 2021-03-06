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
import com.xjgc.wind.datastatistics.service.IWindPowScatterService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindPowScatterDataForm;
import com.xjgc.wind.util.YMDHMSUtil;

public class WindPowScatterDataAction  extends DispatchAction{
	
	private static final Log log = LogFactory.getLog(WindPowScatterDataAction.class);
	
	IWindPowScatterService windPowScatterService;
	IGeneratorService generatorService;
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		windPowScatterService = (IWindPowScatterService) wac.getBean("windPowScatterService");
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

		WindPowScatterDataForm dataForm = (WindPowScatterDataForm)form;
		
		List<DataStatisticsDataVo> result =null;
		int equipsize = generatorService.list().size();
		if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {
			result = windPowScatterService.list(dataForm,dataForm.getEquipId());
			
			request.setAttribute("result", result);
			
		}
		if(equipsize > 0)
			request.setAttribute("generator", generatorService.list());
		lineChartXml(result, request);
		
		return mapping.findForward("show");
	}
	
	private void lineChartXml(List<DataStatisticsDataVo> list, HttpServletRequest request) {
		if (list != null && !list.isEmpty()) {
			
			StringBuffer powersXml = new StringBuffer();
			powersXml.append("[");
			Iterator<DataStatisticsDataVo> iter = list.iterator();
			while (iter.hasNext()) {
				DataStatisticsDataVo elem = iter.next();				
	
				powersXml.append("[").append(elem.getWindVelval1()).append(",").append(elem.getPower()).append("],");
				
			}
			powersXml.append("]");
			//?????????????????????????????
			if (powersXml.length() > 0) 
				request.setAttribute("powersXml", powersXml);

		}
		
		else{
			StringBuffer powersXml = new StringBuffer();
			powersXml.append("[[").append(0).append(",").append(0).append("]]");
			request.setAttribute("powersXml", powersXml);
		}
	}

}
