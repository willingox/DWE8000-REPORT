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

import com.xjgc.wind.datastatistics.service.IBayService;
import com.xjgc.wind.datastatistics.service.IGeneratorService;
import com.xjgc.wind.datastatistics.service.IWindGenwhDetailedService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.GeneratorDataForm;
import com.xjgc.wind.datastatistics.web.form.WindGenwhDetailedDataForm;
import com.xjgc.wind.util.YearFormatUtil;


public class WindGenwhDetailedDataAction extends DispatchAction {

	private static final Log log = LogFactory.getLog(WindGenwhDetailedDataAction.class);

	IWindGenwhDetailedService windGenwhDetailedService;
	IBayService bayService;
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		windGenwhDetailedService = (IWindGenwhDetailedService) wac.getBean("windGenwhDetailedService");		
		bayService = (IBayService) wac.getBean("bayService");												
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

	// ��ȡ��ʷԤ�������б�
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("entering 'show' method ...");

		// ��ѯ����
		List<DataStatisticsDataVo> result =null;
		List<DataStatisticsDataVo> result1 =null;
		List<DataStatisticsDataVo> resultTable =null;
		double maxCurp=0;
		String maxTime=null;
		double maxWind=0;
		if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {
		WindGenwhDetailedDataForm dataForm = (WindGenwhDetailedDataForm) form;
		
		Date startDate = YearFormatUtil.get().parse(dataForm.getStartDateDisp());
		Date endDate = YearFormatUtil.get().parse(dataForm.getEndDateDisp());
		Calendar   calendar   =   new   GregorianCalendar(); 
		Calendar   calendar1   =   new   GregorianCalendar(); 
	  	calendar.setTime(startDate); 
	  	calendar1.setTime(endDate); 
		String startYear = new SimpleDateFormat("yyyy").format(calendar.getTime());
		String endYear = new SimpleDateFormat("yyyy").format(calendar1.getTime());
		if(startYear.compareTo(endYear)==0){
			
			result = windGenwhDetailedService.listMax(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),1);
			if(result.size()!=0){
			resultTable = windGenwhDetailedService.listTable(result.get(0).getMaxTime().substring(0, 19));
			maxCurp  =   (new   BigDecimal(result.get(0).getMaxCurp())).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
			maxWind  =   (new   BigDecimal(result.get(0).getMaxWind())).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
			maxTime=result.get(0).getMaxTime().substring(0, 19);

			}
			
		}
		if(startYear.compareTo(endYear)!=0){
			result = windGenwhDetailedService.listMax(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",1);
			result1 = windGenwhDetailedService.listMax(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),2);
			if(result.size()!=0){
				resultTable = windGenwhDetailedService.listTable(result.get(0).getMaxTime().substring(0, 19));
				maxCurp  =   (new   BigDecimal(result.get(0).getMaxCurp())).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				maxWind  =   (new   BigDecimal(result.get(0).getMaxWind())).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				
				maxTime=result.get(0).getMaxTime().substring(0, 19);
				if(result1.size()!=0){
					if(result1.get(0).getMaxCurp()>result.get(0).getMaxCurp()){
						resultTable = windGenwhDetailedService.listTable(result1.get(0).getMaxTime().substring(0, 19));
						maxCurp  =   (new   BigDecimal(result1.get(0).getMaxCurp())).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
						maxWind  =   (new   BigDecimal(result1.get(0).getMaxWind())).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
						
						maxTime=result1.get(0).getMaxTime().substring(0, 19);	
					}
				}
			
			
			}
		}
		}
		request.setAttribute("maxCurp", maxCurp);
		request.setAttribute("maxTime", maxTime);
		request.setAttribute("maxWind", maxWind);
		request.setAttribute("resultTable", resultTable);
		
		
		return mapping.findForward("show");
	}
	public ActionForward showWind(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("entering 'showWind' method ...");

		
		
		List<DataStatisticsDataVo> resultBay =null;
		List<DataStatisticsDataVo> result =null;
		List<DataStatisticsDataVo> result1 =null;
		List<DataStatisticsDataVo> resultTable =null;
		List<DataStatisticsDataVo> resultTable1 =null;
		if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {
		WindGenwhDetailedDataForm dataForm = (WindGenwhDetailedDataForm) form;
		
		Date startDate = YearFormatUtil.get().parse(dataForm.getStartDateDisp());
		Date endDate = YearFormatUtil.get().parse(dataForm.getEndDateDisp());
		Calendar   calendar   =   new   GregorianCalendar(); 
		Calendar   calendar1   =   new   GregorianCalendar(); 
	  	calendar.setTime(startDate); 
	  	calendar1.setTime(endDate); 
		String startYear = new SimpleDateFormat("yyyy").format(calendar.getTime());
		String endYear = new SimpleDateFormat("yyyy").format(calendar1.getTime());
		if(startYear.compareTo(endYear)==0){
			resultBay=bayService.list();
			if(resultBay.size()!=0){
				resultTable=windGenwhDetailedService.listWind(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),1,resultBay.get(0).getId());
				for(int i=1;i<resultBay.size();i++){
					result = windGenwhDetailedService.listWind(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),1,resultBay.get(i).getId());
					resultTable.addAll(result);
				}
				
			}
			
		}
		if(startYear.compareTo(endYear)!=0){
			resultBay=bayService.list();
			
			if(resultBay.size()!=0){
				resultTable=windGenwhDetailedService.listWind(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),1,resultBay.get(0).getId());
				resultTable1=windGenwhDetailedService.listWind(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),2,resultBay.get(0).getId());
				if(resultTable.size()!=0){
					if(resultTable1.size()!=0){
						if(resultTable1.get(0).getMaxCurp()>resultTable.get(0).getMaxCurp()){
							resultTable.get(0).setMaxCurp(resultTable1.get(0).getMaxCurp());
							resultTable.get(0).setMaxTime(resultTable1.get(0).getMaxTime());
							resultTable.get(0).setMaxWind(resultTable1.get(0).getMaxWind());
						}
					}
				}
				
				for(int i=1;i<resultBay.size();i++){
					result = windGenwhDetailedService.listWind(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",1,resultBay.get(i).getId());
					result1 = windGenwhDetailedService.listWind(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),2,resultBay.get(i).getId());
					
				
				if(result.size()!=0){
					if(result1.size()!=0){
						if(result1.get(0).getMaxCurp()<result.get(0).getMaxCurp()||result1.get(0).getMaxCurp()==result.get(0).getMaxCurp()){
							resultTable.addAll(result);
						}
						if(result1.get(0).getMaxCurp()>result.get(0).getMaxCurp()){
							resultTable.addAll(result1);
						}
					}
				}
				}
			}
			}
		}
		request.setAttribute("resultTable", resultTable);
	
		return mapping.findForward("showWind");
	}

}







