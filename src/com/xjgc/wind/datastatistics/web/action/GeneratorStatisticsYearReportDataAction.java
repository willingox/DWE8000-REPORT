package com.xjgc.wind.datastatistics.web.action;

import java.math.BigDecimal;
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
import com.xjgc.wind.datastatistics.service.IGeneratorStatisticsYearReportService;
import com.xjgc.wind.datastatistics.service.IRunGeneratorNumService;
import com.xjgc.wind.datastatistics.service.IWindAvailabilityContrastService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;
import com.xjgc.wind.datastatistics.web.form.GeneratorStatisticsYearReportDataForm;
import com.xjgc.wind.util.YMDHMSUtil;



public class GeneratorStatisticsYearReportDataAction extends DispatchAction {

	private static final Log log = LogFactory.getLog(GeneratorStatisticsYearReportDataAction.class);

	IGeneratorStatisticsYearReportService generatorStatisticsYearReportService;
	IBayService  bayService;
	IRunGeneratorNumService  runGeneratorNumService;
	IWindAvailabilityContrastService windAvailabilityContrastService;
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		generatorStatisticsYearReportService = (IGeneratorStatisticsYearReportService) wac.getBean("generatorStatisticsYearReportService");	
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


		GeneratorStatisticsYearReportDataForm dataForm = (GeneratorStatisticsYearReportDataForm) form;
		List<GeneratorStatisticsReportVo> result =null;
		List<GeneratorStatisticsReportVo> resultWind =null;
		List<GeneratorStatisticsReportVo> resultHour1 =null;
		List<GeneratorStatisticsReportVo> resultHour2 =null;
		List<GeneratorStatisticsReportVo> resultHour3 =null;
		List<GeneratorStatisticsReportVo> resultHour4 =null;
		List<GeneratorStatisticsReportVo> resultHour5 =null;
		List<GeneratorStatisticsReportVo> resultHour6 =null;
		List<GeneratorStatisticsReportVo> resultHour7 =null;
		List<GeneratorStatisticsReportVo> resultHour8 =null;
		List<GeneratorStatisticsReportVo> resultHour9 =null;
		List<GeneratorStatisticsReportVo> resultHour10 =null;
		List<GeneratorStatisticsReportVo> resultHour11 =null;
		List<GeneratorStatisticsReportVo> resultHour12 =null;
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
		resultHour1=generatorStatisticsYearReportService.listHour(dataForm.getDateDisp()+"-01",smgId);
		resultHour2=generatorStatisticsYearReportService.listHour(dataForm.getDateDisp()+"-02",smgId);
		resultHour3=generatorStatisticsYearReportService.listHour(dataForm.getDateDisp()+"-03",smgId);
		resultHour4=generatorStatisticsYearReportService.listHour(dataForm.getDateDisp()+"-04",smgId);
		resultHour5=generatorStatisticsYearReportService.listHour(dataForm.getDateDisp()+"-05",smgId);
		resultHour6=generatorStatisticsYearReportService.listHour(dataForm.getDateDisp()+"-06",smgId);
		resultHour7=generatorStatisticsYearReportService.listHour(dataForm.getDateDisp()+"-07",smgId);
		resultHour8=generatorStatisticsYearReportService.listHour(dataForm.getDateDisp()+"-08",smgId);
		resultHour9=generatorStatisticsYearReportService.listHour(dataForm.getDateDisp()+"-09",smgId);
		resultHour10=generatorStatisticsYearReportService.listHour(dataForm.getDateDisp()+"-10",smgId);
		resultHour11=generatorStatisticsYearReportService.listHour(dataForm.getDateDisp()+"-11",smgId);
		resultHour12=generatorStatisticsYearReportService.listHour(dataForm.getDateDisp()+"-12",smgId);
		result=generatorStatisticsYearReportService.list(dataForm.getDateDisp(),smgId);
		resultWind=generatorStatisticsYearReportService.listWind(dataForm.getDateDisp(),smgId);
		
		resultAva=windAvailabilityContrastService.list(""+dataForm.getDateDisp()+"-01-01 00:00:00",""+dataForm.getDateDisp()+"-12-31 23:59:59","district-1");
		Date endDate = YMDHMSUtil.get().parse(""+dataForm.getDateDisp()+"-12-31 23:59:59");
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
		
		if(resultHour1.size()!=0){
			if(resultHour2.size()!=0){	
		for(int i=0;i<resultHour1.size();i++){
			for(int j=0;j<resultHour2.size();j++){
				if(resultHour2.get(j).getId()==resultHour1.get(i).getId()){
					resultHour1.get(i).setMonAvlbltHour(resultHour1.get(i).getMonAvlbltHour()+resultHour2.get(i).getMonAvlbltHour());;
				}
			}
		}
			}
		if(resultHour3.size()!=0){	
			for(int i=0;i<resultHour1.size();i++){
				for(int j=0;j<resultHour3.size();j++){
					if(resultHour3.get(j).getId()==resultHour1.get(i).getId()){
						resultHour1.get(i).setMonAvlbltHour(resultHour1.get(i).getMonAvlbltHour()+resultHour3.get(i).getMonAvlbltHour());;
					}
				}
			}
		}
			if(resultHour4.size()!=0){	
				for(int i=0;i<resultHour1.size();i++){
					for(int j=0;j<resultHour4.size();j++){
						if(resultHour4.get(j).getId()==resultHour1.get(i).getId()){
							resultHour1.get(i).setMonAvlbltHour(resultHour1.get(i).getMonAvlbltHour()+resultHour4.get(i).getMonAvlbltHour());;
						}
					}
				}
			}
				if(resultHour5.size()!=0){	
					for(int i=0;i<resultHour1.size();i++){
						for(int j=0;j<resultHour5.size();j++){
							if(resultHour5.get(j).getId()==resultHour1.get(i).getId()){
								resultHour1.get(i).setMonAvlbltHour(resultHour1.get(i).getMonAvlbltHour()+resultHour5.get(i).getMonAvlbltHour());;
							}
						}
					}
				}
					if(resultHour6.size()!=0){	
						for(int i=0;i<resultHour1.size();i++){
							for(int j=0;j<resultHour6.size();j++){
								if(resultHour6.get(j).getId()==resultHour1.get(i).getId()){
									resultHour1.get(i).setMonAvlbltHour(resultHour1.get(i).getMonAvlbltHour()+resultHour6.get(i).getMonAvlbltHour());;
								}
							}
						}
					}
						if(resultHour7.size()!=0){	
							for(int i=0;i<resultHour1.size();i++){
								for(int j=0;j<resultHour7.size();j++){
									if(resultHour7.get(j).getId()==resultHour1.get(i).getId()){
										resultHour1.get(i).setMonAvlbltHour(resultHour1.get(i).getMonAvlbltHour()+resultHour7.get(i).getMonAvlbltHour());;
									}
								}
							}
						}
							if(resultHour8.size()!=0){	
								for(int i=0;i<resultHour1.size();i++){
									for(int j=0;j<resultHour8.size();j++){
										if(resultHour8.get(j).getId()==resultHour1.get(i).getId()){
											resultHour1.get(i).setMonAvlbltHour(resultHour1.get(i).getMonAvlbltHour()+resultHour8.get(i).getMonAvlbltHour());;
										}
									}
								}
							}
								if(resultHour9.size()!=0){	
									for(int i=0;i<resultHour1.size();i++){
										for(int j=0;j<resultHour9.size();j++){
											if(resultHour9.get(j).getId()==resultHour1.get(i).getId()){
												resultHour1.get(i).setMonAvlbltHour(resultHour1.get(i).getMonAvlbltHour()+resultHour9.get(i).getMonAvlbltHour());;
											}
										}
									}
								}
									if(resultHour10.size()!=0){	
										for(int i=0;i<resultHour1.size();i++){
											for(int j=0;j<resultHour10.size();j++){
												if(resultHour10.get(j).getId()==resultHour1.get(i).getId()){
													resultHour1.get(i).setMonAvlbltHour(resultHour1.get(i).getMonAvlbltHour()+resultHour10.get(i).getMonAvlbltHour());;
												}
											}
										}
									}
										if(resultHour11.size()!=0){	
											for(int i=0;i<resultHour1.size();i++){
												for(int j=0;j<resultHour11.size();j++){
													if(resultHour11.get(j).getId()==resultHour1.get(i).getId()){
														resultHour1.get(i).setMonAvlbltHour(resultHour1.get(i).getMonAvlbltHour()+resultHour11.get(i).getMonAvlbltHour());;
													}
												}
											}
										}
											if(resultHour12.size()!=0){	
												for(int i=0;i<resultHour1.size();i++){
													for(int j=0;j<resultHour12.size();j++){
														if(resultHour12.get(j).getId()==resultHour1.get(i).getId()){
															resultHour1.get(i).setMonAvlbltHour(resultHour1.get(i).getMonAvlbltHour()+resultHour12.get(i).getMonAvlbltHour());;
														}
													}
												}
		}
		}
		for(int i=0;i<result.size();i++){
			for(int j=0;j<resultWind.size();j++){
				if(resultWind.get(j).getId()==result.get(i).getId()){
					result.get(i).setHours(resultWind.get(j).getHours());
				}
			}
			for(int t=0;t<resultHour1.size();t++){
				if(resultHour1.get(t).getId()==result.get(i).getId()){
					result.get(i).setMonAvlbltHour(resultHour1.get(t).getMonAvlbltHour());
				}
			}
			
				double faultTime = 0;
				
				for(int k=0;k<resultAva.size();k++){
					if(resultAva.get(k).getBayId()==result.get(i).getId()){
						faultTime+=resultAva.get(k).getFaultTime();
						
					}
					}
				result.get(i).setYeaAvlbltRat((1-faultTime/(24*365))*100);
		}
		//求和
		double allMonAvlbltHour=0;//合计利用小时
		double allYeargenwh=0;//合计发电量
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
        double allYeaAvlbltRat=0;//合计年可利用率
		//得到合计值
		if(result.size()!=0){
			//最小值初始化
			 allMin_windspeed=result.get(0).getMin_windspeed();//合计最小风速
			 allMin_P=result.get(0).getMin_P();//合计最低有功
			 allMin_Q=result.get(0).getMin_Q();//	合计最低无功
			 allMin_Temp=result.get(0).getMin_Temp();//合计最低环境温度
			for(int i=0;i<result.size();i++){
				//求和
				allMonAvlbltHour+=result.get(i).getMonAvlbltHour();
				allYeargenwh+=result.get(i).getYeargenwh();
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
				allYeaAvlbltRat+=result.get(i).getYeaAvlbltRat();
				
			}
			//平均值除以总个数
			int runGenSize = generatorStatisticsYearReportService.listcount(dataForm.getDateDisp(),smgId).size();
			if(runGenSize!=0){
			allAvg_windspeed=allAvg_windspeed/runGenSize;
			allAvg_P=allAvg_P/runGenSize;
			allAvg_Q=allAvg_Q/runGenSize;
			allAvg_Temp=allAvg_Temp/runGenSize;
			allHours=allHours/runGenSize;
			allYeaAvlbltRat=allYeaAvlbltRat/runGenSize;
			}
			//保留两位小数
			allMonAvlbltHour =(new   BigDecimal(allMonAvlbltHour)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
			allYeargenwh  =   (new   BigDecimal(allYeargenwh)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
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
			allYeaAvlbltRat  =   (new   BigDecimal(allYeaAvlbltRat)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
			
		}
		request.setAttribute("allMonAvlbltHour", allMonAvlbltHour);
		request.setAttribute("allYeargenwh", allYeargenwh);
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
		request.setAttribute("allYeaAvlbltRat", allYeaAvlbltRat);
		}
		request.setAttribute("smgSize", smgSize);
		request.setAttribute("smgsysinfo", resultSmg);
		request.setAttribute("result", result);
		return mapping.findForward("show");
		
	}
	

	
	
}








