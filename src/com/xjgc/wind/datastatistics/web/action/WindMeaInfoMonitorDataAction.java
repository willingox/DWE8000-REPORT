package com.xjgc.wind.datastatistics.web.action;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.google.gson.Gson;
import com.xjgc.wind.datastatistics.service.IWindGenwhService;
import com.xjgc.wind.datastatistics.service.IWindGerRtMonitorService;
import com.xjgc.wind.datastatistics.service.IWindMeaInfoMonitorService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.vo.WgmeasinfoDataVo;
import com.xjgc.wind.datastatistics.web.form.WindGerRtMonitorDataForm;
import com.xjgc.wind.util.DoubleRound;
import com.xjgc.wind.util.UnitAnalyse;


public class WindMeaInfoMonitorDataAction extends DispatchAction{

	private static final Log log = LogFactory.getLog(WindMeaInfoMonitorDataAction.class);
	IWindMeaInfoMonitorService windMeaInfoMonitorService;

	
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servlet.getServletContext());
		windMeaInfoMonitorService = (IWindMeaInfoMonitorService) wac.getBean("windMeaInfoMonitorService");
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
		
		
		
		
		if (StringUtils.isEmpty(request.getParameter("isFirst"))) {
			
		}
		List<WgmeasinfoDataVo> result =null;
		result = windMeaInfoMonitorService.list();
		StringBuffer bufferTable = new StringBuffer(); 
		
		
		bufferTable.append("<tr style='background-color:#edf7f1;text-align:center;'><td style='text-align:center;word-break:break-all;' colspan='1' width='1.6%'>风机名称</td>");
		
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.1%'>无功功率</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.1%'>叶轮转速</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.1%'>叶片1桨角</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>变桨电容1电压</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>变桨电机1扭矩</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>变桨电机1温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>变桨柜1温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>变桨变频器1温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>变桨变频器1直流母线电压</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>变桨变频器1散热板温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.2%'>叶片2桨角</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>变桨电容2电压</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>变桨电机2扭矩</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>变桨电机2温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>变桨柜2温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>变桨变频器2温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>变桨变频器2直流母线电压</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>变桨变频器2散热板温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.2%'>叶片3桨角</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>变桨电容3电压</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>变桨电机3扭矩</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>变桨电机3温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>变桨柜3温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>变桨变频器3温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>变桨变频器3直流母线电压</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>变桨变频器3散热板温度</td>");
			
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>齿轮箱油泵压力</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>齿轮箱进油口压力</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>齿轮箱进油口温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>齿轮箱油箱温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.4%'>齿轮箱油加热器温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.4%'>齿轮箱一级轴承温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.4%'>齿轮箱二级轴承温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.4%'>齿轮箱三级轴承温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.4%'>齿轮箱最大轴承温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.6%'>发电机轴承驱动端温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>发电机轴承非驱动端温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.4%'>发电机进水口温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>发电机A相定子绕组温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>发电机B相定子绕组温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>发电机C相定子绕组温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.1%'>电网相电流</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.1%'>电网频率</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>风机有功消耗</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>机侧IGBT温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>变流器入水口温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>风速湍流强度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>风向与机舱方向的偏差</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.1%'>环境温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.1%'>机舱温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.2%'>偏航电机转速</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.2%'>塔基柜温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.2%'>机舱柜温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.1%'>塔筒温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.1%'>功率因数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.3%'>有功功率限值</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.1%'>扭缆角度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.2%'>线电压A-B</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.2%'>线电压B-C</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.2%'>线电压C-A</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.1%'>相电流I1</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.1%'>相电流I2</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.1%'>相电流I3</td>");

			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.4%'>有功功率限制值</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.4%'>无功功率限制值</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.5%'>发电机转速限制值</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.5%'>风速仪1瞬时风速</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.5%'>风速仪2瞬时风速</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.4%'>辅助变压器温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.1%'>有功功率</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1%'>风速</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1%'>风向</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.2%'>发电机转速</td>");
			bufferTable.append("</tr>");
		request.setAttribute("top", bufferTable.toString());
		bufferTable.delete(0, bufferTable.length());
		
		request.setAttribute("result", result);
		
		return mapping.findForward("show");
		
	}
	
	
	
	
} 


