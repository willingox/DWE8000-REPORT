package com.xjgc.wind.datastatistics.web.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.xjgc.wind.datastatistics.service.IGeneratorStatisticsMonReportService;
import com.xjgc.wind.datastatistics.service.IRunGeneratorNumService;
import com.xjgc.wind.datastatistics.service.IWindAvailabilityContrastService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;
import com.xjgc.wind.datastatistics.web.form.GeneratorStatisticsMonReportDataForm;
import com.xjgc.wind.util.YMDHMSUtil;



public class GeneratorStatisticsMonReportDataAction extends DispatchAction {

	private static final Log log = LogFactory.getLog(GeneratorStatisticsMonReportDataAction.class);

	IGeneratorStatisticsMonReportService generatorStatisticsMonReportService;
	IBayService  bayService;
	IRunGeneratorNumService  runGeneratorNumService;
	IWindAvailabilityContrastService windAvailabilityContrastService;
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		generatorStatisticsMonReportService = (IGeneratorStatisticsMonReportService) wac.getBean("generatorStatisticsMonReportService");	
		bayService = (IBayService) wac.getBean("bayService");	
		runGeneratorNumService = (IRunGeneratorNumService) wac.getBean("runGeneratorNumService");
		windAvailabilityContrastService = (IWindAvailabilityContrastService) wac.getBean("windAvailabilityContrastService");	
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


		GeneratorStatisticsMonReportDataForm dataForm = (GeneratorStatisticsMonReportDataForm) form;
		List<GeneratorStatisticsReportVo> result =null;
		List<GeneratorStatisticsReportVo> resultWind =null;
		List<DataStatisticsDataVo> resultSmg=null;
		List<DataStatisticsDataVo> resultAva=null;
		
		resultSmg=bayService.listSmg();
		int smgSize=resultSmg.size();
		ArrayList<String> resultCheckName = new ArrayList<String>();
		ArrayList<String> resultCheckValue = new ArrayList<String>();
		List<String> check = new ArrayList<String>();
	
		if(resultSmg.size()!=0){
			for(int i=0;i<resultSmg.size();i++ ){
			resultCheckName.add(resultSmg.get(i).getName());
			resultCheckValue.add(String.valueOf(resultSmg.get(i).getId()));
			check.add(i, null);	
			request.setAttribute("check"+i, check.get(i));	
			
		    request.setAttribute("resultcheckname"+i, resultCheckName.get(i));
		    request.setAttribute("resultcheckvalue"+i, resultCheckValue.get(i));
			}
		}
		request.setAttribute("resultSmg", resultSmg);
		
		if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {	
			String smgValue = request.getParameter("smgValue");
			int  smgId=1;
			if(resultSmg.size()==1){
				smgId=resultSmg.get(0).getId();
			}
			if(resultSmg.size()>1){
				smgId=Integer.valueOf(smgValue).intValue();
				int a=smgId-1;
				check.add(a,"checked");
			}
			for(int i=0;i<resultSmg.size();i++){
				request.setAttribute("check"+i, check.get(i));	
			}
			
			
			Date startDate = null;
	 		Calendar startCalendar=Calendar.getInstance();
			try {
				startDate=new SimpleDateFormat("yyyy-MM").parse(dataForm.getDateDisp());
				startCalendar.setTime(startDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			int year=startCalendar.get(Calendar.YEAR);//年份
			int month=startCalendar.get(Calendar.MONTH)+1;//月份
			resultAva=windAvailabilityContrastService.list(""+dataForm.getDateDisp()+"-01 00:00:00",""+dataForm.getDateDisp()+"-"+getDaysByYearMonth(year, month)+" 23:59:59","district-1");
		  	
		  	Date endDate = YMDHMSUtil.get().parse(""+dataForm.getDateDisp()+"-"+getDaysByYearMonth(year, month)+" 23:59:59");;
		  	long time1 =endDate.getTime()-startDate.getTime();
			if( resultAva.size()!=0){
				
				for(int i=0;i<resultAva.size();i++){
			
							if(null!=resultAva.get(i).getRemoveTime()){
								long time=0;
								if(resultAva.get(i).getRemoveTime().getTime()-endDate.getTime()>0){
									 time = endDate.getTime()-resultAva.get(i).getHappenTime().getTime();	
								}
								else{
									 time = resultAva.get(i).getRemoveTime().getTime()-resultAva.get(i).getHappenTime().getTime();
								}
								
								resultAva.get(i).setFaultTime(time*0.001/3600);
								
							}else{
								
								long time = endDate.getTime()-resultAva.get(i).getHappenTime().getTime();
								resultAva.get(i).setFaultTime(time*0.001/3600);
							}
						
						}
					}
			
			
		
			result=generatorStatisticsMonReportService.list(dataForm.getDateDisp(),smgId);
			resultWind=generatorStatisticsMonReportService.listWind(dataForm.getDateDisp(),smgId);
			
				
			for(int i=0;i<result.size();i++){
				double faultTime = 0;
				for(int j=0;j<resultWind.size();j++){
					if(resultWind.get(j).getId()==result.get(i).getId()){
						result.get(i).setHours(resultWind.get(j).getHours());
					}
				}
				for(int k=0;k<resultAva.size();k++){
					if(resultAva.get(k).getBayId()==result.get(i).getId()){
						faultTime+=resultAva.get(k).getFaultTime();
						
					}
					}
				int day=getDaysByYearMonth(year, month);
				result.get(i).setMonAvlbltRat((1-faultTime/(24*day))*100);
			
				
			}

			
			//求和
			double allMonthgenwh=0;//合计发电量
			double allYawHour=0;//合计偏航总时间
			double allYawCWCou =0;//合计偏航总次数
			double allLftYawMotorCWCou =0;//合计左偏航次数
			double allRtghYawMotorCCWCou =0;//合计右偏航次数
			double allWinTurStCovCont =0;//合计停机次数
			double allWinHigErrTimes =0;//合计大风停机次数
			double allWinTurArtStpCont =0;//合计人工停机次数
			double allWinTurErrCovCont =0;// 合计故障停机次数
			double allConvMaiSwitchCou =0;//合计并网次数
			double allWinTurCatInCont =0;//合计切入次数
			double allSerModTimes =0;//合计维护次数
			double allMonAvlbltHour =0;//合计月可利用小时
			double allMonNormTim =0;//合计月运行小时
			double allMonRunTim =0;//合计月发电小时数
			double allMonStopTim =0;//合计月停机小时数
			double allMonErrEmeTim =0;//合计月故障小时数
			double allMonSerTim =0;//合计月维护小时数
			double allMonGridErrTim =0;//合计电网月故障时间
			
			
			//求最大
			double allMax_windspeed =0;//合计最大风速
			double allMax_P =0;//合计最高有功
			double allMax_Q =0;//	合计最高无功
			double allMax_Temp =0;//合计最高环境温度
			//求最小
			double allMin_windspeed=0;//合计最小风速
			double allMin_P=0;//合计最低有功
			double allMin_Q=0;//	合计最低无功
			double allMin_Temp=0;//合计最低环境温度
			//求平均
			double allAvg_windspeed =0;//合计平均风速
			double allAvg_P =0;//合计平均有功
			double allAvg_Q =0;//	合计平均无功
			double allAvg_Temp =0;//合计平均环境温度
			double allHours=0;//合计有效风速数
			double allMonAvlbltRat=0;//合计月可利用率

			//得到合计值
			if(result.size()!=0){
				//最小值初始化
				 allMin_windspeed=result.get(0).getMin_windspeed();//合计最小风速
				 allMin_P=result.get(0).getMin_P();//合计最低有功
				 allMin_Q=result.get(0).getMin_Q();//	合计最低无功
				 allMin_Temp=result.get(0).getMin_Temp();//合计最低环境温度
				for(int i=0;i<result.size();i++){
					//求和
					allMonthgenwh+=result.get(i).getMonthgenwh();
					allYawHour+=result.get(i).getYawHour();
					allYawCWCou+=result.get(i).getYawCWCou();
					allLftYawMotorCWCou+=result.get(i).getLftYawMotorCWCou();
					allRtghYawMotorCCWCou+=result.get(i).getRtghYawMotorCCWCou();
					allWinTurStCovCont+=result.get(i).getWinTurStCovCont();
					allWinHigErrTimes+=result.get(i).getWinHigErrTimes();
					allWinTurArtStpCont+=result.get(i).getWinTurArtStpCont();
					allWinTurErrCovCont+=result.get(i).getWinTurErrCovCont();
					allConvMaiSwitchCou+=result.get(i).getConvMaiSwitchCou();
					allWinTurCatInCont+=result.get(i).getWinTurCatInCont();
					allSerModTimes+=result.get(i).getSerModTimes();
					allMonAvlbltHour+=result.get(i).getMonAvlbltHour();
					allMonNormTim+=result.get(i).getMonNormTim();
					allMonRunTim+=result.get(i).getMonRunTim();
					allMonStopTim+=result.get(i).getMonStopTim();
					allMonErrEmeTim+=result.get(i).getMonErrEmeTim();
					allMonSerTim+=result.get(i).getMonSerTim();
					allMonGridErrTim+=result.get(i).getMonGridErrTim();
				
					//求最大
					if(result.get(i).getMax_windspeed()>allMax_windspeed){
						allMax_windspeed = result.get(i).getMax_windspeed();
					}
					if(result.get(i).getMax_P()>allMax_P){
						allMax_P = result.get(i).getMax_P();
					}
					if(result.get(i).getMax_Q()>allMax_Q){
						allMax_Q = result.get(i).getMax_Q();
					}
					if(result.get(i).getMax_Temp()>allMax_Temp){
						allMax_Temp = result.get(i).getMax_Temp();
					}
					
					//求最小
					if(result.get(i).getMin_windspeed()<allMin_windspeed){
						allMin_windspeed = result.get(i).getMin_windspeed();
					}
					if(result.get(i).getMin_P()<allMin_P){
						allMin_P = result.get(i).getMin_P();
					}
					if(result.get(i).getMin_Q()<allMin_Q){
						allMin_Q = result.get(i).getMin_Q();
					}
					if(result.get(i).getMin_Temp()<allMin_Temp){
						allMin_Temp = result.get(i).getMin_Temp();
					}
					//求平均但是也要先求和
					allAvg_windspeed+=result.get(i).getAvg_windspeed();
					allAvg_P+=result.get(i).getAvg_P();
					allAvg_Q+=result.get(i).getAvg_Q();
					allAvg_Temp+=result.get(i).getAvg_Temp();
					allHours+=result.get(i).getHours();
					allMonAvlbltRat+=result.get(i).getMonAvlbltRat();
					
					
				}
				//平均值除以总个数
				int runGenSize = generatorStatisticsMonReportService.listcount(dataForm.getDateDisp(),smgId).size();
				if(runGenSize!=0){
				allAvg_windspeed=allAvg_windspeed/runGenSize;
				allAvg_P=allAvg_P/runGenSize;
				allAvg_Q=allAvg_Q/runGenSize;
				allAvg_Temp=allAvg_Temp/runGenSize;
				allHours=allHours/runGenSize;
				allMonAvlbltRat=allMonAvlbltRat/runGenSize;
				}
				//保留两位小数
				allMonthgenwh  =   (new   BigDecimal(allMonthgenwh)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allYawHour  =   (new   BigDecimal(allYawHour)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allYawCWCou  =   (new   BigDecimal(allYawCWCou)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allLftYawMotorCWCou  =   (new   BigDecimal(allLftYawMotorCWCou)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allRtghYawMotorCCWCou  =   (new   BigDecimal(allRtghYawMotorCCWCou)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allWinTurStCovCont  =   (new   BigDecimal(allWinTurStCovCont)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allWinHigErrTimes  =   (new   BigDecimal(allWinHigErrTimes)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allWinTurArtStpCont  =   (new   BigDecimal(allWinTurArtStpCont)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allWinTurErrCovCont  =   (new   BigDecimal(allWinTurErrCovCont)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allConvMaiSwitchCou  =   (new   BigDecimal(allConvMaiSwitchCou)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allWinTurCatInCont  =   (new   BigDecimal(allWinTurCatInCont)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allSerModTimes  =   (new   BigDecimal(allSerModTimes)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMonAvlbltHour  =   (new   BigDecimal(allMonAvlbltHour)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMonNormTim  =   (new   BigDecimal(allMonNormTim)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMonRunTim  =   (new   BigDecimal(allMonRunTim)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMonStopTim  =   (new   BigDecimal(allMonStopTim)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMonErrEmeTim  =   (new   BigDecimal(allMonErrEmeTim)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMonSerTim  =   (new   BigDecimal(allMonSerTim)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMonGridErrTim  =   (new   BigDecimal(allMonGridErrTim)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMax_windspeed  =   (new   BigDecimal(allMax_windspeed)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMax_P  =   (new   BigDecimal(allMax_P)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMax_Q  =   (new   BigDecimal(allMax_Q)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMax_Temp  =   (new   BigDecimal(allMax_Temp)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMin_windspeed  =   (new   BigDecimal(allMin_windspeed)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMin_P  =   (new   BigDecimal(allMin_P)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMin_Q  =   (new   BigDecimal(allMin_Q)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMin_Temp  =   (new   BigDecimal(allMin_Temp)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allAvg_windspeed  =   (new   BigDecimal(allAvg_windspeed)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allAvg_P  =   (new   BigDecimal(allAvg_P)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allAvg_Q  =   (new   BigDecimal(allAvg_Q)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allAvg_Temp  =   (new   BigDecimal(allAvg_Temp)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allHours  =   (new   BigDecimal(allHours)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMonAvlbltRat  =   (new   BigDecimal(allMonAvlbltRat)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				
			}
			
			request.setAttribute("allMonthgenwh", allMonthgenwh);
			request.setAttribute("allYawHour", allYawHour);
			request.setAttribute("allYawCWCou", allYawCWCou);
			request.setAttribute("allLftYawMotorCWCou", allLftYawMotorCWCou);
			request.setAttribute("allRtghYawMotorCCWCou", allRtghYawMotorCCWCou);
			request.setAttribute("allWinTurStCovCont", allWinTurStCovCont);
			request.setAttribute("allWinHigErrTimes", allWinHigErrTimes);
			request.setAttribute("allWinTurArtStpCont", allWinTurArtStpCont);
			request.setAttribute("allWinTurErrCovCont", allWinTurErrCovCont);
			request.setAttribute("allConvMaiSwitchCou", allConvMaiSwitchCou);
			request.setAttribute("allWinTurCatInCont", allWinTurCatInCont);
			request.setAttribute("allSerModTimes", allSerModTimes);
			request.setAttribute("allMonAvlbltHour", allMonAvlbltHour);
			request.setAttribute("allMonNormTim", allMonNormTim);
			request.setAttribute("allMonRunTim", allMonRunTim);
			request.setAttribute("allMonStopTim", allMonStopTim);
			request.setAttribute("allMonErrEmeTim", allMonErrEmeTim);
			request.setAttribute("allMonSerTim", allMonSerTim);
			request.setAttribute("allMonGridErrTim", allMonGridErrTim);
			request.setAttribute("allMax_windspeed", allMax_windspeed);
			request.setAttribute("allMax_P", allMax_P);
			request.setAttribute("allMax_Q", allMax_Q);
			request.setAttribute("allMax_Temp", allMax_Temp);
			request.setAttribute("allMin_windspeed", allMin_windspeed);
			request.setAttribute("allMin_P", allMin_P);
			request.setAttribute("allMin_Q", allMin_Q);
			request.setAttribute("allMin_Temp", allMin_Temp);
			request.setAttribute("allAvg_windspeed", allAvg_windspeed);
			request.setAttribute("allAvg_P", allAvg_P);
			request.setAttribute("allAvg_Q", allAvg_Q);
			request.setAttribute("allAvg_Temp", allAvg_Temp);
			request.setAttribute("allHours", allHours);
			request.setAttribute("allMonAvlbltRat", allMonAvlbltRat);
		}
		    request.setAttribute("smgSize", smgSize);
		    request.setAttribute("smgsysinfo", resultSmg);
			request.setAttribute("result", result);	
		
		return mapping.findForward("show");
		
	}
	

	public static int getDaysByYearMonth(int year, int month) {

		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		  return maxDate;
	}
	
}








