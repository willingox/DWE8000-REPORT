package com.xjgc.wind.datastatistics.web.action;

import java.math.BigDecimal;
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
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.LimitFactory;
import org.extremecomponents.table.limit.TableLimit;
import org.extremecomponents.table.limit.TableLimitFactory;

import com.bjxj.base.webapp.util.RequestUtil;
import com.xjgc.wind.datastatistics.service.IBayService;
import com.xjgc.wind.datastatistics.service.IGeneratStatisticsService;
import com.xjgc.wind.datastatistics.service.IGeneratorStatisticsReportService;
import com.xjgc.wind.datastatistics.service.IRunGeneratorNumService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;
import com.xjgc.wind.datastatistics.web.form.GeneratStatisticsDataForm;
import com.xjgc.wind.datastatistics.web.form.GeneratorStatisticsReportDataForm;
import com.xjgc.wind.util.HourMTimeUtil;
import com.xjgc.wind.util.YMDHMSUtil;
import com.xjgc.wind.util.YearFormatUtil;



public class GeneratorStatisticsReportDataAction2 extends DispatchAction {

	private static final Log log = LogFactory.getLog(GeneratorStatisticsReportDataAction2.class);

	IGeneratorStatisticsReportService generatorStatisticsReportService;
	IBayService  bayService;
	IRunGeneratorNumService  runGeneratorNumService;
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		generatorStatisticsReportService = (IGeneratorStatisticsReportService) wac.getBean("generatorStatisticsReportService");	
		bayService = (IBayService) wac.getBean("bayService");	
		runGeneratorNumService = (IRunGeneratorNumService) wac.getBean("runGeneratorNumService");	
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

		//选择的遥测
		// String rowsDisplayed = request.getParameter("rowsDisplayed");
		// System.out.println(rowsDisplayed);
		// RequestUtil.getPageNo(request);
		GeneratorStatisticsReportDataForm dataForm = (GeneratorStatisticsReportDataForm) form;
		List<GeneratorStatisticsReportVo> result =null;
		List<GeneratorStatisticsReportVo> resultWind=null;
		List<GeneratorStatisticsReportVo> result1 =null;
		List<GeneratorStatisticsReportVo> resultWind1=null;
		List<GeneratorStatisticsReportVo> resultRun=null;
		List<DataStatisticsDataVo> resultSmg=null;
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
			Date startDate = YMDHMSUtil.get().parse(dataForm.getStartDateDisp());
			Date endDate = YMDHMSUtil.get().parse(dataForm.getEndDateDisp());
			  //  Date startDate =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataForm.getStartDateDisp());
				//Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataForm.getEndDateDisp());
				Calendar   calendar   =   new   GregorianCalendar(); 
				Calendar   calendar1   =   new   GregorianCalendar(); 
                int diff = (int)((endDate.getTime() - startDate.getTime())/(24 * 60 * 60 * 1000));			
			  	calendar.setTime(startDate); 
			  	calendar1.setTime(endDate); 
				String startYear = new SimpleDateFormat("yyyy").format(calendar.getTime());
				String endYear = new SimpleDateFormat("yyyy").format(calendar1.getTime());
				Date start =YMDHMSUtil.get().parse(dataForm.getStartDateDisp());
				
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
				if(startYear.compareTo(endYear)==0){
					String startDateDisp = YMDHMSUtil.get().format(start.getTime());
					 start.setTime(start.getTime()+ 60 * 60 * 1000);
					 String endDateDisp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start.getTime());	
					result = generatorStatisticsReportService.list(startDateDisp,endDateDisp,smgId,1);
					
					
					 for(int i=0;i<diff*24;i++){
					startDateDisp = YMDHMSUtil.get().format(start.getTime());
					 start.setTime(start.getTime()+ 60 * 60 * 1000);
					 endDateDisp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start.getTime());	
					 result1 = generatorStatisticsReportService.list(startDateDisp,endDateDisp,smgId,1);
					 result.addAll(result1);
					}
					
					resultWind=generatorStatisticsReportService.listWind(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),smgId,1);
					resultRun = generatorStatisticsReportService.listcount(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),smgId,1);
				}
				if(startYear.compareTo(endYear)!=0){
					endDate = YMDHMSUtil.get().parse(endYear+"-01-01 00:00:00");
					  diff = (int)((endDate.getTime() - startDate.getTime())/(24 * 60 * 60 * 1000));	
					  String startDateDisp = YMDHMSUtil.get().format(start.getTime());
						 start.setTime(start.getTime()+ 60 * 60 * 1000);
						 String endDateDisp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start.getTime());	
						result = generatorStatisticsReportService.list(startDateDisp,endDateDisp,smgId,1);
						
						
						 for(int i=0;i<diff*24;i++){
						startDateDisp = YMDHMSUtil.get().format(start.getTime());
						 start.setTime(start.getTime()+ 60 * 60 * 1000);
						 endDateDisp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start.getTime());	
						 result1 = generatorStatisticsReportService.list(startDateDisp,endDateDisp,smgId,1);
						 result.addAll(result1);
						}
						 
						  startDate = YMDHMSUtil.get().parse(endYear+"-01-01 00:00:00");
							 endDate = YMDHMSUtil.get().parse(dataForm.getEndDateDisp());
						  diff = (int)((endDate.getTime() - startDate.getTime())/(24 * 60 * 60 * 1000));	
						 
							 for(int j=0;j<diff*24;j++){
								 start =YMDHMSUtil.get().parse(endYear+"-01-01 00:00:00");
							startDateDisp = YMDHMSUtil.get().format(start.getTime());
							 start.setTime(start.getTime()+ 60 * 60 * 1000);
							 endDateDisp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start.getTime());	
							 result1 = generatorStatisticsReportService.list(startDateDisp,endDateDisp,smgId,2);
							 result.addAll(result1);
							}
					
					
					
					
					
					
					
					resultWind = generatorStatisticsReportService.listWind(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",smgId,1);
					resultWind1 = generatorStatisticsReportService.listWind(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),smgId,2);
					resultWind.addAll(resultWind1);
					resultRun = generatorStatisticsReportService.listcount(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),smgId,2);
				}
			
			
			for(int i=0;i<result.size();i++){
				for(int j=0;j<resultWind.size();j++){
					if(resultWind.get(j).getId()==result.get(i).getId()){
						result.get(i).setHours(resultWind.get(j).getHours());
					}
				}
				
			}
			//求和
			double allDaygenwh=0;//合计发电量
			double allHours=0;//合计有效风速数
			double allWinTurErrSec=0;//合计故障时间
		
			//求最大
			double allMax_windspeed =0;//合计最大风速
			double allMax_P =0;//合计最高有功
			
			//求最小
			double allMin_windspeed=0;//合计最小风速
			double allMin_P=0;//合计最低有功
			
			//求平均
			double allAvg_windspeed =0;//合计平均风速
			double allAvg_P =0;//合计平均有功
			double allAvg_ava =0;//合计平均可利用率
			//得到合计值
			if(result.size()!=0){
				//最小值初始化
				 allMin_windspeed=result.get(0).getMin_windspeed();//合计最小风速
				 allMin_P=result.get(0).getMin_P();//合计最低有功
				
				for(int i=0;i<result.size();i++){
					//求和
					allDaygenwh+=result.get(i).getDaygenwh();
					allHours+=result.get(i).getHours();
					allWinTurErrSec+=result.get(i).getWinTurErrSec();
					
					//求最大
					if(result.get(i).getMax_windspeed()>allMax_windspeed){
						allMax_windspeed = result.get(i).getMax_windspeed();
					}
					if(result.get(i).getMax_P()>allMax_P){
						allMax_P = result.get(i).getMax_P();
					}
				
					
					//求最小
					if(result.get(i).getMin_windspeed()<allMin_windspeed){
						allMin_windspeed = result.get(i).getMin_windspeed();
					}
					if(result.get(i).getMin_P()<allMin_P){
						allMin_P = result.get(i).getMin_P();
					}
					
					//求平均但是也要先求和
					allAvg_windspeed+=result.get(i).getAvg_windspeed();
					allAvg_P+=result.get(i).getAvg_P();
					allAvg_ava+=result.get(i).getAvailability();
					
				}
				
				//平均值除以总个数
				
				int runGenSize = 0;
				
			
				//暂时先按照result的大小处理吧
					if(result.size()!=0){
				
							
							runGenSize=result.size();
				
			
			allAvg_windspeed=allAvg_windspeed/runGenSize;
			allAvg_P=allAvg_P/runGenSize;
			allAvg_ava=allAvg_ava/runGenSize;
			}
				//保留两位小数
				allDaygenwh  =   (new   BigDecimal(allDaygenwh)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allHours  =   (new   BigDecimal(allHours)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allWinTurErrSec  =   (new   BigDecimal(allWinTurErrSec)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMax_windspeed  =   (new   BigDecimal(allMax_windspeed)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMax_P  =   (new   BigDecimal(allMax_P)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				
				allMin_windspeed  =   (new   BigDecimal(allMin_windspeed)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allMin_P  =   (new   BigDecimal(allMin_P)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				
				allAvg_windspeed  =   (new   BigDecimal(allAvg_windspeed)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				allAvg_P  =   (new   BigDecimal(allAvg_P)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				
				allAvg_ava  =   (new   BigDecimal(allAvg_ava)).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				

			}
			
			request.setAttribute("allDaygenwh", allDaygenwh);
			request.setAttribute("allHours", allHours);
			request.setAttribute("allWinTurErrSec", allWinTurErrSec);
			request.setAttribute("allMax_windspeed", allMax_windspeed);
			request.setAttribute("allMax_P", allMax_P);
			request.setAttribute("allMin_windspeed", allMin_windspeed);
			request.setAttribute("allMin_P", allMin_P);
			request.setAttribute("allAvg_windspeed", allAvg_windspeed);
			request.setAttribute("allAvg_P", allAvg_P);
			request.setAttribute("allAvg_ava", allAvg_ava);
			
		}
		
		request.setAttribute("smgSize", smgSize);
		request.setAttribute("smgsysinfo", resultSmg);
			
		request.setAttribute("result", result);
		return mapping.findForward("show");
		
	}
	

	
	
}








