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
import com.xjgc.wind.datastatistics.service.IWindRoseService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindRoseDataForm;


public class WindRoseDataAction extends DispatchAction {

	private static final Log log = LogFactory.getLog(WindRoseDataAction.class);

	IWindRoseService windRoseService;
	IGeneratorService generatorService;

	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		windRoseService = (IWindRoseService) wac.getBean("windRoseService");	
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

	// ?????????????????????????????????????????
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("entering 'show' method ...");

		// ?????????????????
		WindRoseDataForm dataForm = (WindRoseDataForm) form;
		List<DataStatisticsDataVo> result =null;
		if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {
			result = windRoseService.list(dataForm);
			if(result.size()!=0){
			for(int i=0;i<result.size();i++)
			{
				double f0   = result.get(i).getFrequency1();  
				BigDecimal   b   =   new   BigDecimal(f0);  
				double f1  =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				result.get(i).setFrequency1(f1);
				
				if(result.get(i).getAvgWindVelvalDu()== null){
					result.get(i).setAvgWindVelval(0);
				}
				
			if(result.get(i).getWindDirVal().equals("5")){
				result.get(i).setWindDirVal("-11.25??~11.25??");
			}
			if(result.get(i).getWindDirVal().equals("4")){
				result.get(i).setWindDirVal("11.25??~33.75??");
			}
			if(result.get(i).getWindDirVal().equals("3")){
				result.get(i).setWindDirVal("33.75??~56.25??");
			}
			if(result.get(i).getWindDirVal().equals("2")){
				result.get(i).setWindDirVal("56.25??~78.75??");
			}
			if(result.get(i).getWindDirVal().equals("1")){
				result.get(i).setWindDirVal("78.75??~101.25??");
			}
			if(result.get(i).getWindDirVal().equals("16")){
				result.get(i).setWindDirVal("101.25??~123.75??");
			}
			if(result.get(i).getWindDirVal().equals("15")){
				result.get(i).setWindDirVal("123.75??~146.25??");
			}
			if(result.get(i).getWindDirVal().equals("14")){
				result.get(i).setWindDirVal("146.25??~168.75??");
			}
			if(result.get(i).getWindDirVal().equals("13")){
				result.get(i).setWindDirVal("168.75??~191.25??");
			}
			if(result.get(i).getWindDirVal().equals("12")){
				result.get(i).setWindDirVal("191.25??~213.75??");
			}
			if(result.get(i).getWindDirVal().equals("11")){
				result.get(i).setWindDirVal("213.75??~236.25??");
			}
			if(result.get(i).getWindDirVal().equals("10")){
				result.get(i).setWindDirVal("236.25??~258.75??");
			}
			if(result.get(i).getWindDirVal().equals("9")){
				result.get(i).setWindDirVal("258.75??~281.25??");
			}
			if(result.get(i).getWindDirVal().equals("8")){
				result.get(i).setWindDirVal("281.25??~303.75??");
			}
			if(result.get(i).getWindDirVal().equals("7")){
				result.get(i).setWindDirVal("303.75??~326.25??");
			}
			if(result.get(i).getWindDirVal().equals("6")){
				result.get(i).setWindDirVal("326.25??~348.75??");
			}
			}
			  request.setAttribute("generator", generatorService.list());
			
			request.setAttribute("result1", result.get(0));
			request.setAttribute("result2", result.get(1));	
			request.setAttribute("result3", result.get(2));
			request.setAttribute("result4", result.get(3));
			request.setAttribute("result5", result.get(4));
			request.setAttribute("result6", result.get(5));
			request.setAttribute("result7", result.get(6));
			request.setAttribute("result8", result.get(7));
			request.setAttribute("result9", result.get(8));
			request.setAttribute("result10", result.get(9));
			request.setAttribute("result11", result.get(10));
			request.setAttribute("result12", result.get(11));
			request.setAttribute("result13", result.get(12));
			request.setAttribute("result14", result.get(13));
			request.setAttribute("result15", result.get(14));
			request.setAttribute("result16", result.get(15));
		}
		
		}
		request.setAttribute("result", result);
		return mapping.findForward("show");
	}	
	
}







