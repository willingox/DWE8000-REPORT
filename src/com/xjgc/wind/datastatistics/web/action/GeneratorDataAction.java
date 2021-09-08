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

import com.xjgc.wind.datastatistics.service.IGeneratorService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.GeneratorDataForm;


public class GeneratorDataAction extends DispatchAction {

	private static final Log log = LogFactory.getLog(GeneratorDataAction.class);

	IGeneratorService generatorService;

	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
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

	// 获取历史预测数据列表
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("entering 'show' method ...");

		// 查询条件
		List<DataStatisticsDataVo> result =null;
		List<DataStatisticsDataVo> resultu =null;
		GeneratorDataForm dataForm = (GeneratorDataForm) form;
		
		result = generatorService.list();
		
		if(result.size()> 0)
		{
			String str = result.get(0).getName1();
			char str1 = str.charAt(3);
			if(str.contains("YM")){
				resultu = generatorService.listu("YM",str1);
			}
			else{
				resultu = generatorService.listu("YC",str1);
			}
		}
		if(resultu.size() > 0)
			dataForm.setTotalGenUnit(resultu.get(0).getName2());
		
		request.setAttribute("result", result);
		
		
		return mapping.findForward("show");
	}
	

}







