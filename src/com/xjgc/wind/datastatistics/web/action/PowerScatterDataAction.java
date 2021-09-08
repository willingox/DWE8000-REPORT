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

import com.xjgc.wind.util.DateFormatUtil;
import com.xjgc.wind.datastatistics.service.IGeneratorService;
import com.xjgc.wind.datastatistics.service.IPowerScatterService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.PowerScatterDataForm;
import com.xjgc.wind.util.YMDHMSUtil;


public class PowerScatterDataAction extends DispatchAction {

	private static final Log log = LogFactory.getLog(PowerScatterDataAction.class);

	IPowerScatterService powerScatterService;
	IGeneratorService generatorService;

	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		powerScatterService = (IPowerScatterService) wac.getBean("powerScatterService");
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

	
		PowerScatterDataForm dataForm = (PowerScatterDataForm) form;
		List<DataStatisticsDataVo> resultfre =null;
		List<DataStatisticsDataVo> resultgenpwd =null;
		
		int equipsize = generatorService.list().size();
		if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {
			int id=dataForm.getEquipId();
			resultfre = powerScatterService.listfre(dataForm, id);
			resultgenpwd= powerScatterService.listgenpwd(id);
		
			
			for(int i=0;i<resultfre.size();i++)
			{
				double f0   = resultfre.get(i).getCurp(); 
				BigDecimal   b   =   new   BigDecimal(f0);  
				double f1  =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				resultfre.get(i).setCurp(f1);
				
				
				if(resultfre.get(i).getAvgWindVelval()==3){
					resultfre.get(i).setAvgWindVelval(1.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==4){
					resultfre.get(i).setAvgWindVelval(2f);
				}
				if(resultfre.get(i).getAvgWindVelval()==5){
					resultfre.get(i).setAvgWindVelval(2.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==6){
					resultfre.get(i).setAvgWindVelval(3f);
				}
				if(resultfre.get(i).getAvgWindVelval()==7){
					resultfre.get(i).setAvgWindVelval(3.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==8){
					resultfre.get(i).setAvgWindVelval(4f);
				}
				if(resultfre.get(i).getAvgWindVelval()==9){
					resultfre.get(i).setAvgWindVelval(4.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==10){
					resultfre.get(i).setAvgWindVelval(5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==11){
					resultfre.get(i).setAvgWindVelval(5.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==12){
					resultfre.get(i).setAvgWindVelval(6f);
				}
				if(resultfre.get(i).getAvgWindVelval()==13){
					resultfre.get(i).setAvgWindVelval(6.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==14){
					resultfre.get(i).setAvgWindVelval(7f);
				}
				if(resultfre.get(i).getAvgWindVelval()==15){
					resultfre.get(i).setAvgWindVelval(7.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==16){
					resultfre.get(i).setAvgWindVelval(8f);
				}
				if(resultfre.get(i).getAvgWindVelval()==17){
					resultfre.get(i).setAvgWindVelval(8.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==18){
					resultfre.get(i).setAvgWindVelval(9f);
				}
				if(resultfre.get(i).getAvgWindVelval()==19){
					resultfre.get(i).setAvgWindVelval(9.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==20){
					resultfre.get(i).setAvgWindVelval(10f);
				}
				if(resultfre.get(i).getAvgWindVelval()==21){
					resultfre.get(i).setAvgWindVelval(10.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==22){
					resultfre.get(i).setAvgWindVelval(11f);
				}
				if(resultfre.get(i).getAvgWindVelval()==23){
					resultfre.get(i).setAvgWindVelval(11.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==24){
					resultfre.get(i).setAvgWindVelval(12f);
				}
				if(resultfre.get(i).getAvgWindVelval()==25){
					resultfre.get(i).setAvgWindVelval(12.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==26){
					resultfre.get(i).setAvgWindVelval(13f);
				}
				if(resultfre.get(i).getAvgWindVelval()==27){
					resultfre.get(i).setAvgWindVelval(13.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==28){
					resultfre.get(i).setAvgWindVelval(14f);
				}
				if(resultfre.get(i).getAvgWindVelval()==29){
					resultfre.get(i).setAvgWindVelval(14.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==30){
					resultfre.get(i).setAvgWindVelval(15f);
				}
				if(resultfre.get(i).getAvgWindVelval()==31){
					resultfre.get(i).setAvgWindVelval(15.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==32){
					resultfre.get(i).setAvgWindVelval(16f);
				}
				if(resultfre.get(i).getAvgWindVelval()==33){
					resultfre.get(i).setAvgWindVelval(16.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==34){
					resultfre.get(i).setAvgWindVelval(17f);
				}
				if(resultfre.get(i).getAvgWindVelval()==35){
					resultfre.get(i).setAvgWindVelval(17.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==36){
					resultfre.get(i).setAvgWindVelval(18f);
				}
				if(resultfre.get(i).getAvgWindVelval()==37){
					resultfre.get(i).setAvgWindVelval(18.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==38){
					resultfre.get(i).setAvgWindVelval(19f);
				}
				if(resultfre.get(i).getAvgWindVelval()==39){
					resultfre.get(i).setAvgWindVelval(19.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==40){
					resultfre.get(i).setAvgWindVelval(20f);
				}
				if(resultfre.get(i).getAvgWindVelval()==41){
					resultfre.get(i).setAvgWindVelval(20.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==42){
					resultfre.get(i).setAvgWindVelval(21f);
				}
				if(resultfre.get(i).getAvgWindVelval()==43){
					resultfre.get(i).setAvgWindVelval(21.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==44){
					resultfre.get(i).setAvgWindVelval(22f);
				}
				if(resultfre.get(i).getAvgWindVelval()==45){
					resultfre.get(i).setAvgWindVelval(22.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==46){
					resultfre.get(i).setAvgWindVelval(23f);
				}
				if(resultfre.get(i).getAvgWindVelval()==47){
					resultfre.get(i).setAvgWindVelval(23.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==48){
					resultfre.get(i).setAvgWindVelval(24f);
				}
				if(resultfre.get(i).getAvgWindVelval()==49){
					resultfre.get(i).setAvgWindVelval(24.5f);
				}
				if(resultfre.get(i).getAvgWindVelval()==50){
					resultfre.get(i).setAvgWindVelval(25f);
				}
			}
			
		if(resultgenpwd.get(0).getConfigure()==1){
			
			for(int i=0;i<resultgenpwd.size();i++)
			{
				resultgenpwd.get(i).setPwdCurp(resultgenpwd.get(i).getGenpwd());
				  for(int j=0;j<resultfre.size();j++){
					if(resultfre.get(j).getAvgWindVelval()==resultgenpwd.get(i).getAvgWindVelval()){
						resultgenpwd.get(i).setPwdCurp(new BigDecimal(resultfre.get(j).getPwdCurp().setScale(3,   BigDecimal.ROUND_HALF_UP).doubleValue()*(1-resultgenpwd.get(i).getWeight())+resultgenpwd.get(i).getGenpwd().setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue()*resultgenpwd.get(i).getWeight()));
					}
					
					
					
				}
				  
			}
		}
	if(resultgenpwd.get(0).getConfigure()==2){
				
				for(int i=0;i<resultgenpwd.size();i++)
				{
					  for(int j=0;j<resultfre.size();j++){
						if(resultfre.get(j).getAvgWindVelval()==resultgenpwd.get(i).getAvgWindVelval()){
							resultgenpwd.get(i).setPwdCurp(resultfre.get(j).getPwdCurp());
							
						}

						
						
					}
				}
				
				}
			
			
		
		}
			
		
			
		
		
		if(equipsize > 0)
			request.setAttribute("generator", generatorService.list());
		
		lineChartXml(resultgenpwd, request);
		request.setAttribute("result", resultgenpwd);
		
		return mapping.findForward("show");
	}

	private void lineChartXml(List<DataStatisticsDataVo> list, HttpServletRequest request){
		if (list != null && !list.isEmpty()) {
			StringBuffer lineXml = new StringBuffer();
			lineXml.append("[");
			Iterator<DataStatisticsDataVo> iter = list.iterator();
			while (iter.hasNext()) {
				DataStatisticsDataVo elem = iter.next();
				lineXml.append("{").append("windvelval").append(":\"").append(elem.getAvgWindVelval());
				lineXml.append("\",").append("curp").append(":\"").append(elem.getPwdCurp());
				lineXml.append("\",").append("genpwg").append(":\"").append(elem.getGenpwd());
				lineXml.append("\"},");
			}
			lineXml.append("]");
			if (lineXml.length() > 0) 
				request.setAttribute("lineXml", lineXml);
		}
		else
		{
			StringBuffer lineXml = new StringBuffer();
			lineXml.append("[{").append("windvelval").append(":\"").append(0);
			lineXml.append("\",").append("curp").append(":\"").append(0);
			lineXml.append("\",").append("genpwg").append(":\"").append(0);
			lineXml.append("\"}]");
			
			request.setAttribute("lineXml", lineXml);
		}
	}
	
	

}
