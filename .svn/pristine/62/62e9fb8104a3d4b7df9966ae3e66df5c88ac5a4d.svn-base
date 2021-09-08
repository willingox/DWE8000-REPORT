package com.xjgc.wind.datastatistics.web.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.xjgc.wind.datastatistics.service.IOperatRecordService;
import com.xjgc.wind.datastatistics.service.IBayService;
import com.xjgc.wind.datastatistics.service.IMeasclassService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.OperatRecordDataForm;

public class OperatRecordDataAction extends DispatchAction {

	private static final Log log = LogFactory.getLog(OperatRecordDataAction.class);
	
	IBayService bayService;
	IMeasclassService measclassService;
	IOperatRecordService operatRecordService;
	
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		bayService = (IBayService) wac.getBean("bayService");
		measclassService = (IMeasclassService) wac.getBean("measclassService");
		operatRecordService = (IOperatRecordService) wac.getBean("operatRecordService");
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
		
		OperatRecordDataForm dataForm = (OperatRecordDataForm) form;
		
		List<DataStatisticsDataVo> result =null;
		List<DataStatisticsDataVo> resulttype = null;
		
		int equipsize = bayService.list().size();
		if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {
			result = operatRecordService.list(dataForm);	
		}
		
		
		
		
		request.setAttribute("result", result);
		request.setAttribute("bay", bayService.list());
		//request.setAttribute("operatetype", resulttype);
		
		return mapping.findForward("show");
	}

}
