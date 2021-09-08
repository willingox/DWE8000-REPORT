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
import com.xjgc.wind.datastatistics.service.ILossEleReportService;
import com.xjgc.wind.datastatistics.service.IWindPlcReportService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.GeneratorDataForm;
import com.xjgc.wind.datastatistics.web.form.LossEleReportDataForm;
import com.xjgc.wind.datastatistics.web.form.WindJiReliabilityContrastDataForm;
import com.xjgc.wind.datastatistics.web.form.WindPlcReportDataForm;
import com.xjgc.wind.util.YearFormatUtil;


public class LossEleReportDataAction extends DispatchAction {

	private static final Log log = LogFactory.getLog(LossEleReportDataAction.class);

	ILossEleReportService lossEleReportService;

	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		lossEleReportService = (ILossEleReportService) wac.getBean("lossEleReportService");																														
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

		LossEleReportDataForm dataForm = (LossEleReportDataForm) form;
		List<DataStatisticsDataVo> result =null;
		List<DataStatisticsDataVo> result1 =null;
		
		if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {
			
			Date startDate = YearFormatUtil.get().parse(dataForm.getStartDateDisp());
			Date endDate = YearFormatUtil.get().parse(dataForm.getEndDateDisp());
			Calendar   calendar   =   new   GregorianCalendar(); 
			Calendar   calendar1   =   new   GregorianCalendar(); 
		  	calendar.setTime(startDate); 
		  	calendar1.setTime(endDate); 
			String startYear = new SimpleDateFormat("yyyy").format(calendar.getTime());
			String endYear = new SimpleDateFormat("yyyy").format(calendar1.getTime());
			
			if(startYear.compareTo(endYear)==0){
			result = lossEleReportService.list(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),dataForm.getStr(),1);
			
			}
			if(startYear.compareTo(endYear)!=0){
				
				result = lossEleReportService.list(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",dataForm.getStr(),1);
				result1 = lossEleReportService.list(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),dataForm.getStr(),2);	
				
				result.addAll(result1);
                
			}
			double gridErrStopHourAll=0;
			double weaErrStopHourAll=0;
			double hmiStopHourAll=0;
			double remoteStopHourAll=0;
			double errBreakHourAll=0;
			double powLimHourAll=0;
			double gridErrPowSumAll=0;
			double weaErrPowSumAll=0;
			double hmiStopPowSumAll=0;
			double remoteStopPowSumAll=0;
			double errBreakPowSumAll=0;
			double powLimPowSumAll=0;
			double hiddenPowAll=0;
			double capAvaAll=0;
			double avaHoursAll=0;
			double hoursSumAll=0;
			double lossGenSumAll=0;
			for(int i=0;i<result.size();i++){
				 gridErrStopHourAll+=result.get(i).getGridErrStopHour();
				 weaErrStopHourAll+=result.get(i).getWeaErrStopHour();
				 hmiStopHourAll+=result.get(i).getHmiStopHour();
				 remoteStopHourAll+=result.get(i).getRemoteStopHour();
				 errBreakHourAll+=result.get(i).getErrBreakHour();
				 powLimHourAll+=result.get(i).getPowLimHour();
				 gridErrPowSumAll+=result.get(i).getGridErrPowSum();
				 weaErrPowSumAll+=result.get(i).getWeaErrPowSum();
				 hmiStopPowSumAll+=result.get(i).getHmiStopPowSum();
				 remoteStopPowSumAll+=result.get(i).getRemoteStopPowSum();
				 errBreakPowSumAll+=result.get(i).getErrBreakPowSum();
				 powLimPowSumAll+=result.get(i).getPowLimPowSum();
				 hiddenPowAll+=result.get(i).getHiddenPow();
				 capAvaAll+=result.get(i).getCapAva();
				 avaHoursAll+=result.get(i).getAvaHours();
				 hoursSumAll+=result.get(i).getHoursSum();
				 lossGenSumAll+=result.get(i).getLossGenSum();
			}
			capAvaAll=capAvaAll/result.size();
			request.setAttribute("gridErrStopHourAll", saveTwo(gridErrStopHourAll));
			request.setAttribute("weaErrStopHourAll", saveTwo(weaErrStopHourAll));
			request.setAttribute("hmiStopHourAll", saveTwo(hmiStopHourAll));
			request.setAttribute("remoteStopHourAll", saveTwo(remoteStopHourAll));
			request.setAttribute("errBreakHourAll", saveTwo(errBreakHourAll));
			request.setAttribute("powLimHourAll", saveTwo(powLimHourAll));
			request.setAttribute("gridErrPowSumAll", saveTwo(gridErrPowSumAll));
			request.setAttribute("weaErrPowSumAll", saveTwo(weaErrPowSumAll));
			request.setAttribute("hmiStopPowSumAll", saveTwo(hmiStopPowSumAll));
			request.setAttribute("remoteStopPowSumAll", saveTwo(remoteStopPowSumAll));
			request.setAttribute("errBreakPowSumAll", saveTwo(errBreakPowSumAll));
			request.setAttribute("powLimPowSumAll", saveTwo(powLimPowSumAll));
			request.setAttribute("hiddenPowAll", saveTwo(hiddenPowAll));
			request.setAttribute("capAvaAll", saveTwo(capAvaAll));
			request.setAttribute("avaHoursAll", saveTwo(avaHoursAll));
			request.setAttribute("hoursSumAll", saveTwo(hoursSumAll));
			request.setAttribute("lossGenSumAll", saveTwo(lossGenSumAll));
			request.setAttribute("result", result);
				
		}
		

	
		return mapping.findForward("show");
	}
	//保留两位小数
		public static double saveTwo(double f){
				BigDecimal   b   =   new   BigDecimal(f);  
				double f1  =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				return f1;
		}

}







