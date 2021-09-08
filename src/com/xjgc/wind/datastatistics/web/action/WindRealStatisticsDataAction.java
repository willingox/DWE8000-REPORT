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
import com.xjgc.wind.datastatistics.service.IWindRealStatisticsService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.vo.WgmeasinfoDataVo;
import com.xjgc.wind.datastatistics.vo.WgstinfoDataVo;
import com.xjgc.wind.datastatistics.web.form.WindGerRtMonitorDataForm;
import com.xjgc.wind.util.DoubleRound;
import com.xjgc.wind.util.UnitAnalyse;


public class WindRealStatisticsDataAction extends DispatchAction{

	private static final Log log = LogFactory.getLog(WindRealStatisticsDataAction.class);
	IWindRealStatisticsService windRealStatisticsService;

	
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servlet.getServletContext());
		windRealStatisticsService = (IWindRealStatisticsService) wac.getBean("windRealStatisticsService");
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
		List<WgstinfoDataVo> result =null;
		result = windRealStatisticsService.list();
		StringBuffer bufferTable = new StringBuffer(); 
		
		
		bufferTable.append("<tr style='background-color:#edf7f1;text-align:center;'><td style='text-align:center;word-break:break-all;' colspan='1' width='2.2%'>风机名称</td>");
		
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.5%'>日发电量</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.5%'>月发电量</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.5%'>年发电量</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.5%'>总发电量</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.5%'>总耗电量</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.8%'>PLC运行总时间</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.8%'>风机故障停机总时间</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.8%'>发电机最大轴承温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='2.1%'>发电机最大定子绕组温度</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='2.1%'>风机振动驱动方向峰峰值</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='2.2%'>风机振动非驱动方向峰峰值</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.6%'>刹车投入总时间</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='2.1%'>齿轮箱油泵高速运行总时间</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='2.1%'>齿轮箱油泵低速运行总时间</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='2.1%'>齿轮箱油冷风扇运行总时间</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='2%'>发电机水冷泵运行总时间</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.8%'>液压站泵运行总时间</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>左偏航总时间</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>右偏航总时间</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>当月可利用率</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>上月可利用率</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>当年可利用率</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>去年可利用率</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='2.0%'>风机月可利用小时数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>月运行小时数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>月发电小时数</td>");
			
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>月停机小时数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>月故障小时数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>月维护小时数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.8%'>电网月故障时间</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='2%'>故障可自动复位的次数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.6%'>停机总次数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.5%'>维护次数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.5%'>急停次数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.8%'>刹车投入总次数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='2.1%'>齿轮箱油泵高速启动总次数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='2.1%'>齿轮箱油泵低速启动总次数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='2.1%'>齿轮箱油冷风扇启动总次数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.9%'>液压站泵启动总次数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>左偏航总次数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>右偏航总次数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>故障停机次数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.5%'>并网次数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>大风停机次数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.5%'>切入次数</td>");
			bufferTable.append("<td style='text-align:center;word-break:break-all;' colspan='1' width='1.7%'>人工停机次数</td>");
			bufferTable.append("</tr>");
		request.setAttribute("top", bufferTable.toString());
		bufferTable.delete(0, bufferTable.length());
		
		request.setAttribute("result", result);
		
		return mapping.findForward("show");
		
	}
	
	
	
	
} 


