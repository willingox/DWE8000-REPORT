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
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.FaultQueryDataForm;
import com.xjgc.wind.util.YMDHMSUtil;

public class FaultQueryDataAction extends DispatchAction{

	private static final Log log = LogFactory.getLog(FaultQueryDataAction.class);
	
	IFaultQueryService faultQueryService;
	IGeneratorService generatorService;
	ISmgsysinfoService smgsysinfoService;
	
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		faultQueryService = (IFaultQueryService) wac.getBean("faultQueryService");
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
		
		FaultQueryDataForm dataForm = (FaultQueryDataForm) form;
		List<String> check = new ArrayList<String>();
		for(int i=0;i<3;i++){
			check.add(i, null);	
		}
		
		
		String[] alarmType = request.getParameterValues("alarmType");
		int length = 0;
		if(alarmType != null)
		{
			length = alarmType.length;
		}
		if(length!=0){
			 for(int i=0;i<length;i++){
					if(alarmType[i].compareTo("5")==0){
						check.add(0,"checked");
					}
					if(alarmType[i].compareTo("6")==0){
						check.add(1,"checked");
					}
					if(alarmType[i].compareTo("2")==0){
						check.add(2,"checked");
					}	
				}
		}
		for(int i=0;i<3;i++){
			request.setAttribute("check"+i, check.get(i));	
		}
		List<DataStatisticsDataVo> result =null;
		List<DataStatisticsDataVo> resulttype =null;
		
		int equipsize = generatorService.list().size();
		if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {
			Date endDate1 = YMDHMSUtil.get().parse(dataForm.getEndDateDisp());
			result = faultQueryService.list(dataForm);
			
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
						
						//Date nowDate=new Date();
						long time = endDate1.getTime()-result.get(i).getHappenTime().getTime();
						result.get(i).setCount(time*0.001/3600);
					}
					
				}
			}
			
			request.setAttribute("result", result);
			
		
		}
	/*	//???????????? ????????????????????????
		resulttype = new ArrayList<DataStatisticsDataVo>();
		DataStatisticsDataVo d1=new DataStatisticsDataVo();
		d1.setId(5);
		d1.setName("??????");
		resulttype.add(d1);
		DataStatisticsDataVo d2=new DataStatisticsDataVo();
		d2.setId(6);
		d2.setName("??????");
		resulttype.add(d2);
		request.setAttribute("statustype", resulttype);*/
		request.setAttribute("generator", generatorService.list());
	
		return mapping.findForward("show");
	}
	
}
