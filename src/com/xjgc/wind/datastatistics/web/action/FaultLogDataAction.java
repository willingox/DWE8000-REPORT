package com.xjgc.wind.datastatistics.web.action;

import java.io.*;

import jxl.*;
import jxl.write.*;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.csvreader.CsvReader;
import com.xjgc.wind.datastatistics.service.IGeneratorService;
import com.xjgc.wind.datastatistics.service.IPowerScatterService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.FaultLogDataForm;
import com.xjgc.wind.datastatistics.web.form.FaultQueryDataForm;
import com.xjgc.wind.querytree.vo.ResultVO;
import com.xjgc.wind.util.YMDHMSUtil;
import com.xjgc.wind.util.osinfo.OSinfo;


public class FaultLogDataAction extends DispatchAction {

	private static final Log log = LogFactory.getLog(FaultLogDataAction.class);

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

	
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("entering 'show' method ...");

		FaultLogDataForm dataForm = (FaultLogDataForm) form;
		int equipsize=generatorService.list().size();
		if (StringUtils.isEmpty(request.getParameter("isFirst"))) {	
			
			Calendar today = GregorianCalendar.getInstance();
			today.add(Calendar.DATE,-1);
			String strToday = new SimpleDateFormat("yyyy-MM-dd").format(today.getTime());
			if(equipsize!=0){
				dataForm.setEquipId(generatorService.list().get(0).getId());
				}
			dataForm.setStartDateDisp(strToday);
			dataForm.setEquipId(1);
		}	
	    String[] zhongwen = new String[]{
				"PLC主状态",
				"PLC上一个状态",
				"自检时当前子状态",
				"自检时上一个子状态",
				"停机程序",
				"复位程序",
				"系统ok",
				"并网开关",
				"风速1_1s平均值",
				"风速2_1s平均值",
				"风向1_1s平均值",
				"风向2_1s平均值",
				"机舱位置",
				"风轮转速1s平均值",
				"发电机转速瞬时值",
				"发电机转速1s平均值",
				"发电机转速30s平均值",
				"塔基控制柜内部温度30S平均值",
				"塔基控制柜外部温度30S平均值",
				"有功功率消耗",
				"机舱外环境温度30S平均值",
				"机舱柜外部温度30S平均值",
				"齿轮箱油槽油温30S平均值",
				"齿轮箱一级行星轴承温度30S平均值",
				"齿轮箱二级行星轴承温度30S平均值",
				"齿轮箱平行级轴承温度30S平均值",
				"发电机冷却水进口水温30S平均值",
				"机舱柜Ⅰ内部温度30S平均值",
				"机舱柜Ⅱ内部温度30S平均值",
				"齿轮箱润滑油外部加热器油温30S平均值",
				"齿轮箱润滑油入口油温30S平均值",
				"风轮主轴承温度1S平均值",
				"驱动侧发电机轴温1S平均值",
				"非驱动侧发电机轴温1S平均值",
				"发电机绕组U温度1S平均值",
				"发电机绕组V温度1S平均值",
				"发电机绕组W温度1S平均值",
				"齿轮箱润滑油入口压力1S平均值",
				"齿轮箱润滑油过滤器压差1S平均值",
				"发电机绕组温度U*10",
				"发电机绕组温度V*10",
				"发电机绕组温度W*10",
				"PLC计算变流器反馈的机侧散热器温度",
				"PLC计算变流器反馈的网侧散热器温度",
				"PLC通过编码器测得的轮毂转速",
				"PLC通过过速继电器测得的轮毂转速",
				"机舱前后方向振动加速度峰峰值",
				"机舱左右方向振动加速度峰峰值",
				"机舱前后方向振动加速度直流值",
				"机舱左右方向振动加速度直流值",
				"发电机定子温度",
				"60s平均风向，用于偏航启动的判断",
				"30s平均风向，用于偏航停止的判断",
				"Sineax测量瞬时总的有功功率",
				"Sineax测得的电压U12",
				"Sineax测得的电压U23",
				"Sineax测得的电压U31",
				"Sineax测得的电流I1",
				"Sineax测得的电流I2",
				"Sineax测得的电流I3",
				"Sineax测得瞬时的电流",
				"Sineax测得瞬时总的无功功率",
				"Sineax测得瞬时的电网频率",
				"变桨驱动柜1温度",
				"变桨驱动柜2温度",
				"变桨驱动柜3温度",
				"变桨变频器1温度",
				"变桨变频器2温度",
				"变桨变频器3温度",
				"变桨变频器1散热器温度",
				"变桨变频器2散热器温度",
				"变桨变频器3散热器温度",
				"变桨电机温度1",
				"变桨电机温度2",
				"变桨电机温度3",
				"电容柜1电容电压",
				"电容柜2电容电压",
				"电容柜3电容电压",
				"变桨变频器1母线电压",
				"变桨变频器2母线电压",
				"变桨变频器2母线电压",
				"叶片1角度",
				"叶片2角度", 
				"叶片3角度",
				"叶片1转矩", 
				"叶片2转矩", 
				"叶片3转矩",
				"偏航功率",
				"偏航驱动器温度",
				"偏航频率",
				"偏航计算速度",
				"电机转矩",
				"偏航给定转速",
				"偏航状态",
				"PLC与偏航系统通信(0：中断 1：正常)",
				"偏航达到需求速度",
				"偏航校准初始化完成标志",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"整个模块IO状态值（变换为2进制可对应出相应IO点变量的高低电平）",
				"PLC与变流器通信(0：中断 1：正常)",
				"主控下发给变流器的无功给定百分比*100",
				"主控下发给变流器的转矩百分比*100",
				"变流器测量的有功功率*10",
				"总的无功功率*10",
				"电网电压（U1-V1）u *10",
				"总的线电流*10",
				"转子侧变流器IGBT温度",
				"网侧变流器IGBT温度",
				"电机速度*10",
				"发电机转矩*10",
				"电网频率*100",
				"变流器主控制字",
				"变流器状态字1",
				"变流器故障字1",
				"告警字",
				"变流器故障字2",
				"变流器故障字3",
				"变流器故障字4",
				"变流器故障字5",
				"电容柜1状态",
				"电容柜2状态",
				"电容柜3状态",
				"PLC与变桨驱动器1通信(0：中断 1：正常)",
				"PLC与变桨驱动器2通信(0：中断 1：正常)",
				"PLC与变桨驱动器3通信(0：中断 1：正常)",
				"叶片1状态",
				"叶片2状态",
				"叶片3状态 ",
				"驱动器1故障字1_高16位",
				"驱动器2故障字1_高16位",
				"驱动器3故障字1_高16位",
				"驱动器1故障字2_高16位",
				"驱动器2故障字2_高16位",
				"驱动器3故障字2_高16位",
				"驱动器1故障字1_低16位",
				"驱动器2故障字1_低16位",
				"驱动器3故障字1_低16位",
				"驱动器1故障字2_低16位",
				"驱动器2故障字2_低16位",
				"驱动器3故障字2_低16位",
				"电容柜1故障字1",
				"电容柜2故障字1",
				"电容柜3故障字1",
				"变桨驱动器1控制字",
				"变桨驱动器2控制字",
				"变桨驱动器3控制字",
				"变桨驱动器1反馈的状态字0",
				"变桨驱动器1反馈的状态字1",
				"变桨驱动器2反馈的状态字0",
				"变桨驱动器2反馈的状态字1",
				"变桨驱动器3反馈的状态字0",
				"变桨驱动器3反馈的状态字1",
				"主控下发的变桨速度1(整型数据，其值是变桨速度的100倍)",
				"主控下发的变桨速度2(整型数据，其值是变桨速度的100倍)",
				"主控下发的变桨速度3(整型数据，其值是变桨速度的100倍)",
				"驱动器上传的变桨速度1(整型数据，其值是变桨速度的100倍)",
				"驱动器上传的变桨速度2(整型数据，其值是变桨速度的100倍)",
				"驱动器上传的变桨速度3(整型数据，其值是变桨速度的100倍)",
				"偏航变频器故障号 ",
				"偏航控制字_高16位",
				"偏航状态字_高16位",
				"偏航控制字_低16位",
				"偏航状态字_低16位",
				"主控与风速风向仪1通信(0：中断 1：正常)",
				"主控与风速风向仪2通信(0：中断 1：正常)",
				"变桨自检状态机",
				"轮毂故障字1",
				"轮毂故障字2",
				"轮毂故障字3",
				"安全运行结束1",
				"安全运行结束1",
				"安全运行结束1",
				"轮毂告警字1",
				"轮毂告警字2",
				"轮毂告警字3",
				"变桨1速度需求",
				"变桨2速度需求",
				"变桨3速度需求",
				"计算的电容值1",
				"计算的电容值2",
				"计算的电容值3",
				"轮毂的状态",
				"变桨位置控制转化速度需求1",
				"变桨位置控制转化速度需求2",
				"变桨位置控制转化速度需求3",
				"用电容值计算出来的能量消耗1",
				"用电容值计算出来的能量消耗2",
				"用电容值计算出来的能量消耗3",
				"变桨柜I摩擦力",
				"变桨柜II摩擦力",
				"变桨柜III摩擦力",
				"变桨自检故障字",
				"电容值累加值1",
				"电容值累加值2",
				"电容值累加值3",
				"计算直流母线电压平均值的计数1",
				"计算直流母线电压平均值的计数2",
				"计算直流母线电压平均值的计数3",
				"电容充电时间1",
				"电容充电时间2",
				"电容充电时间3",
				"变桨自检子状态",
				"刹车状态字",
				"PI控制器输入的功率额定限制值",
				"PI控制器输入的转速目标值",
				"1号加阻扭矩需求",
				"2号加阻扭矩需求",
				"加阻总扭矩需求",
				"解缆完成标志",
				"偏航位置偏差",
				"偏航刹车信号",
				"顺时针电机启动信号",
				"逆时针电机启动信号",
				"风向瞬时值风向1（未过0处理）",
				"风向瞬时值风向2",
				"环境温度1",
				"环境温度2",
				"驱动器1变桨子状态",
				"驱动器2变桨子状态",
				"驱动器3变桨子状态",
				"风机编号",
				"齿轮箱的限制导致的限制功率值",
				"因风速原因导致的限功率数值",
				"齿轮箱的限制导致的需求转速限制值",
				"电机目标转速限制值",
				"程序计算的目标转速值",
				"5分钟平均风速1",
				"5分钟平均风速2",
				"10s平均风向1",
				"10s平均风向2",
	};
		
		List<String> chinaName = new ArrayList<String>();
		List<String> maxValue = new ArrayList<String>();
			//for(int i=0;i<zhongwen.length;i++){
			//	chinaName.add(zhongwen[i]);
			//}
			
		 
		String selectWindName=null;
         String selectDateName=null;
         String selectTxtName=null;
         List<DataStatisticsDataVo> result = new ArrayList<DataStatisticsDataVo>() ;
        List<DataStatisticsDataVo> windName = new ArrayList<DataStatisticsDataVo>() ;
		List<DataStatisticsDataVo> dateName = new ArrayList<DataStatisticsDataVo>() ;
		List<DataStatisticsDataVo> txtName = new ArrayList<DataStatisticsDataVo>() ;
		
		String pathStr="";
		
		if (OSinfo.isWindows()){
			pathStr="E:/风机日志";
		}
		if(OSinfo.isLinux()){
			pathStr="/usr/local/tomcat/windLog";
		}
		
		File windfile = new File(pathStr);
	
		File datefile = null ;
		File txtfile = null ;
        String [] windFileName = windfile.list();
    	if(windFileName!=null){
        String [] dateFileName=null;
        String [] txtFileName = null;
	        if(equipsize!=0){
	        for(int i=0;i<windFileName.length;i++){
	        	windName.add(generatorService.list().get(0));
	}
	        }
	        for(int i=0;i<windFileName.length;i++){
	        	windName.get(i).setId(i+1);
	        	windName.get(i).setName(windFileName[i]);
	}
	        for(int i=0;i<windFileName.length;i++){
	        	if(windName.get(i).getId()==dataForm.getEquipId()){
	        		selectWindName=windName.get(i).getName();
	        	}
	        }
	       
	        if(selectWindName!=null){
	        datefile = new File(pathStr+"/"+selectWindName+"/LogData/ErrorLog");
	        dateFileName = datefile.list();
	        if(equipsize!=0){
		        for(int i=0;i<dateFileName.length;i++){
		        	dateName.add(generatorService.list().get(0));
		}
		        }
	        
		        for(int i=0;i<dateFileName.length;i++){
		        	dateName.get(i).setId(i+1);
		        	dateName.get(i).setName(dateFileName[i]);
		}
		           
	       if(dataForm.getDateEquipId()!=null){
		       for(int i=0;i<dateFileName.length;i++){
		        	if(dateName.get(i).getId()==dataForm.getDateEquipId()){
		        		selectDateName=dateName.get(i).getName();
		        	}
		        }
		     
	       }
	        
	        }
	      
	        if(selectDateName!=null){
	        	txtfile = new File(pathStr+"/"+selectWindName+"/LogData/ErrorLog/"+selectDateName);
		        txtFileName = txtfile.list();
		        if(equipsize!=0){
			        for(int i=0;i<txtFileName.length;i++){
			        	txtName.add(generatorService.list().get(0));
			}
			        }
		        
			        for(int i=0;i<txtFileName.length;i++){
			        	txtName.get(i).setId(i+1);
			        	txtName.get(i).setName(txtFileName[i]);
			}
			       
		       if(dataForm.getTxtEquipId()!=null){
			       for(int i=0;i<txtFileName.length;i++){
			        	if(txtName.get(i).getId()==dataForm.getTxtEquipId()){
			        		selectTxtName=txtName.get(i).getName();
			        	}
			        }
			      
		       }
		       
		        
		        }
    	} 
	        ArrayList<String> lablex = new ArrayList<String>();
	        ArrayList<String> labley = new ArrayList<String>();
	        ArrayList<String> size = new ArrayList<String>();
	        if(selectTxtName==null){
	        	lablex.add("'0'");
	        	labley.add("'0'");
	        	request.setAttribute("lablex", lablex);
	        	request.setAttribute("labley0", labley);
	        	size.add("1");
	        }
    	 
	       // System.out.println(lablex);
	        //System.out.println(labley);
	        if(selectTxtName!=null){
	        	ArrayList<String[]> csvList = new ArrayList<String[]>(); //用来保存数据
	        	String pathName=pathStr+"/"+selectWindName+"/LogData/ErrorLog/"+selectDateName+"/"+selectTxtName;
				CsvReader reader = new CsvReader(pathName,',',Charset.forName("gbk"));    //一般用这编码读就可以了    
				  while(reader.readRecord()){ //逐行读入除表头的数据    
		                csvList.add(reader.getValues());
		            } 

				  ArrayList<String> faultNumber = new ArrayList<String>(); 
				  ArrayList<String> resultValue = new ArrayList<String>(); 
				  faultNumber.add(csvList.get(3)[0]);
				     String xvalue0=null;
				     String xvalue1=null;
				     String[] xvalue=null;
				     String[] xvaluetime=null;
			         xvalue0= csvList.get(6)[0];
			         xvalue1= csvList.get(7)[0];
			          xvalue=xvalue0.split("	");
			          xvaluetime=xvalue1.split("	");
			          if(xvalue[0].compareTo("Time:")== 0){
			        	 
			        	
			        	  for(int i=1;i< xvalue.length;i++){
			        	  lablex.add(xvalue[i]);
			          }
			          request.setAttribute("lablex", lablex);
				    String str=dataForm.getStr();
					String[] arr =null;
					arr = str.split(",");
					for(int i=0;i<arr.length;i++){
						size.add(arr[i]);
					}
					 
		 			for(int i=0;i<arr.length;i++){
		 		         String yvalue0=null;
		 		         String[] yvalue=null;
		 		         int row=Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length()))+6;
		 		     
		 		         chinaName.add(zhongwen[Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length()))-1]);
		 		         labley = new ArrayList<String>();
		 		         yvalue0= csvList.get(row)[0];
		 		         yvalue=yvalue0.split("	");
		 		          for(int j=1;j<yvalue.length;j++){
		 		        	  labley.add(yvalue[j]);
		 		        	 resultValue.add(i,yvalue[j]);
		 		          }
		 		        
		 		          request.setAttribute("labley"+i, labley); 
		 		         request.setAttribute("chinaName", chinaName);
		 		         request.setAttribute("chinaName"+i, chinaName.get(i)); 
		 	
		 			}
			          }
			          if(xvaluetime[0].compareTo("Time:")== 0){
			        	  
			        	  for(int i=1;i< xvaluetime.length;i++){
				        	  lablex.add(xvaluetime[i]);
				          }
			        	  
				          request.setAttribute("lablex", lablex);
					    String str=dataForm.getStr();
						String[] arr =null;
						arr = str.split(",");
						for(int i=0;i<arr.length;i++){
							size.add(arr[i]);
						}
						 
			 			for(int i=0;i<arr.length;i++){
			 		         String yvalue0=null;
			 		         String[] yvalue=null;
			 		         int row=Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length()))+7;
			 		        chinaName.add(zhongwen[Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length()))-1]);
			 		         labley = new ArrayList<String>();
			 		         yvalue0= csvList.get(row)[0];
			 		         yvalue=yvalue0.split("	");
			 		          for(int j=1;j<yvalue.length;j++){
			 		        	  labley.add(yvalue[j]);
			 		          }
			 		       
			 		          request.setAttribute("labley"+i, labley); 
			 		         request.setAttribute("chinaName", chinaName);
			 		          request.setAttribute("chinaName"+i, chinaName.get(i));
			 		         
			 			}
				          }
			          request.setAttribute("faultNumber", faultNumber);
	        }
	    
         
		
	        request.setAttribute("size", size);
    	
          request.setAttribute("windName", windName);
          request.setAttribute("dateName", dateName);
          request.setAttribute("txtName", txtName);
		 
          return mapping.findForward("show");
		}

 
}
