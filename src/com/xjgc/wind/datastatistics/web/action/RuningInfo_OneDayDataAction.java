package com.xjgc.wind.datastatistics.web.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
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
import com.xjgc.wind.datastatistics.service.IRuningInfo_OneDayService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_OneDayDataForm;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_OneMinuteDataForm;
import com.xjgc.wind.util.MonthDayHourMTimeUtil;
import com.xjgc.wind.util.YearFormatUtil;


public class RuningInfo_OneDayDataAction extends DispatchAction{


	private static final Log log = LogFactory.getLog(RuningInfo_OneDayDataAction.class);
	IRuningInfo_OneDayService runingInfo_OneDayService; 
	
	public void setServlet(ActionServlet servlet) {
		super.setServlet(servlet);

		WebApplicationContext wac = WebApplicationContextUtils
		.getRequiredWebApplicationContext(servlet.getServletContext());
		runingInfo_OneDayService = (IRuningInfo_OneDayService) wac.getBean("runingInfo_OneDayService");																														
	
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

		// ��ѯ���
		RuningInfo_OneDayDataForm dataForm = (RuningInfo_OneDayDataForm) form;
		List<DataStatisticsDataVo> resulttu =null;
		List<DataStatisticsDataVo> resulttu1 =null;
		ArrayList<String> resultlend = new ArrayList<String>();
		  ArrayList<Double> labley0 = new ArrayList<Double>();
		  ArrayList<Double> labley1 = new ArrayList<Double>();
		  ArrayList<Double> labley2 = new ArrayList<Double>();
		  ArrayList<Double> labley3 = new ArrayList<Double>();
		  ArrayList<Double> labley4 = new ArrayList<Double>();
		if (!StringUtils.isEmpty(request.getParameter("isFirst"))) {
			String check_val = request.getParameter("check_val");
			 
			 ArrayList<ArrayList<String>> check_value = new ArrayList<ArrayList<String>>();
			 ArrayList<ArrayList<String>> check_valueName = new ArrayList<ArrayList<String>>();
			 ArrayList<String> check_id = new ArrayList<String>();
			 ArrayList<String> check_name = new ArrayList<String>();
			 
			
			 if(check_val!=null&&!check_val.equals("")){
				 //byte[] b=check_val.getBytes("ISO-8859-1");
				 //check_val=new String(b,"gbk"); 
				 check_val=check_val.replaceAll("'", "\"");
				 Gson gson=new Gson();
				 Map resultMap=gson.fromJson(check_val,Map.class);
				 
				 //ArrayList<String > targetValues = new ArrayList<String >();
				// ArrayList<String> targetId = new ArrayList<String>();
				 for (Object key : resultMap.keySet()) {  
					 Map value = (Map)resultMap.get(key);  
					 ArrayList<String >  targetValues=(ArrayList<String>) value.get("targetValues");
					 ArrayList<String >  targetValuesName=(ArrayList<String>) value.get("targetValueNames");
					 String   name= (String)value.get("name");
					 
					 if(targetValues.size()!=0){
						  check_value.add(targetValues);
						  check_valueName.add(targetValuesName);
						  check_id.add((String)key);
						  check_name.add(name);
					  }
					  
				    
				 }  
				
					
			 }	
			 
			 Date startDate = YearFormatUtil.get().parse(dataForm.getStartDateDisp());
				Date endDate = YearFormatUtil.get().parse(dataForm.getEndDateDisp());
				Calendar   calendar   =   new   GregorianCalendar(); 
				Calendar   calendar1   =   new   GregorianCalendar(); 
			  	calendar.setTime(startDate); 
			  	calendar1.setTime(endDate); 
				String startYear = new SimpleDateFormat("yyyy").format(calendar.getTime());
				String endYear = new SimpleDateFormat("yyyy").format(calendar1.getTime());
				if(startYear.compareTo(endYear)==0){
			     resulttu = runingInfo_OneDayService.list(dataForm.getStartDateDisp(),dataForm.getEndDateDisp(),check_id,check_value,dataForm.getFlag(),1);
				}
				if(startYear.compareTo(endYear)!=0){
					resulttu = runingInfo_OneDayService.list(dataForm.getStartDateDisp(),startYear+"-12-31 23:59:59",check_id,check_value,dataForm.getFlag(),1);
					resulttu1 = runingInfo_OneDayService.list(endYear+"-01-01 00:00:00",dataForm.getEndDateDisp(),check_id,check_value,dataForm.getFlag(),2);
					resulttu.addAll(resulttu1);
					
				}
			
			
				 for(int i=0;i<check_name.size();i++){
						for(int j=0;j<check_valueName.get(i).size();j++){	
							resultlend.add(check_name.get(i)+"-"+check_valueName.get(i).get(j));
						 }
					 }
				
				int length = 0;
				 for(int i=0;i<check_id.size();i++){
						for(int j=0;j<check_value.get(i).size();j++){	
							length=length+1;
						 }
					 }
				if(length==0){
					labley0.add(0.0);
					request.setAttribute("labley0", labley0);
				   	
				}
			
				if(length==1){
					if(resulttu.size()!=0){
					     for(int i=0;i<resulttu.size();i++){	 
					         resulttu.get(i).setCalValue0(saveTwo(resulttu.get(i).getCalValue0()));
					    	 labley0.add(resulttu.get(i).getCalValue0());
					    	 }
						}
						request.setAttribute("labley0", labley0);
				}
				if(length==2){
					if(resulttu.size()!=0){
					     for(int i=0;i<resulttu.size();i++){
					     resulttu.get(i).setCalValue0(saveTwo(resulttu.get(i).getCalValue0()));
					     resulttu.get(i).setCalValue1(saveTwo(resulttu.get(i).getCalValue1()));
					     labley0.add(resulttu.get(i).getCalValue0());
					     labley1.add(resulttu.get(i).getCalValue1());
					     
					     }
						}
						request.setAttribute("labley0", labley0);
						request.setAttribute("labley1", labley1);
				}
				if(length==3){
					if(resulttu.size()!=0){
					     for(int i=0;i<resulttu.size();i++){
					    	 resulttu.get(i).setCalValue0(saveTwo(resulttu.get(i).getCalValue0()));
						     resulttu.get(i).setCalValue1(saveTwo(resulttu.get(i).getCalValue1()));
						     resulttu.get(i).setCalValue2(saveTwo(resulttu.get(i).getCalValue2()));
						     labley0.add(resulttu.get(i).getCalValue0());
						     labley1.add(resulttu.get(i).getCalValue1());	
						     labley2.add(resulttu.get(i).getCalValue2());	
					     
					     }
					    
						}
						request.setAttribute("labley0", labley0);
						request.setAttribute("labley1", labley1);
						request.setAttribute("labley2", labley2);
				}
				if(length==4){
					if(resulttu.size()!=0){
					     for(int i=0;i<resulttu.size();i++){
					    	 resulttu.get(i).setCalValue0(saveTwo(resulttu.get(i).getCalValue0()));
						     resulttu.get(i).setCalValue1(saveTwo(resulttu.get(i).getCalValue1()));
						     resulttu.get(i).setCalValue2(saveTwo(resulttu.get(i).getCalValue2()));
						     resulttu.get(i).setCalValue3(saveTwo(resulttu.get(i).getCalValue3()));
						     labley0.add(resulttu.get(i).getCalValue0());
						     labley1.add(resulttu.get(i).getCalValue1());	
						     labley2.add(resulttu.get(i).getCalValue2());	
						     labley3.add(resulttu.get(i).getCalValue3());	
					    
					   
					     }
						}
						request.setAttribute("labley0", labley0);
						request.setAttribute("labley1", labley1);
						request.setAttribute("labley2", labley2);
						request.setAttribute("labley3", labley3);
				}
				if(length==5){
					if(resulttu.size()!=0){
					     for(int i=0;i<resulttu.size();i++){
					    	 resulttu.get(i).setCalValue0(saveTwo(resulttu.get(i).getCalValue0()));
						     resulttu.get(i).setCalValue1(saveTwo(resulttu.get(i).getCalValue1()));
						     resulttu.get(i).setCalValue2(saveTwo(resulttu.get(i).getCalValue2()));
						     resulttu.get(i).setCalValue3(saveTwo(resulttu.get(i).getCalValue3()));
						     resulttu.get(i).setCalValue4(saveTwo(resulttu.get(i).getCalValue4()));
						     labley0.add(resulttu.get(i).getCalValue0());
						     labley1.add(resulttu.get(i).getCalValue1());	
						     labley2.add(resulttu.get(i).getCalValue2());	
						     labley3.add(resulttu.get(i).getCalValue3());	
						     labley4.add(resulttu.get(i).getCalValue4());	
					    
					     }
					    
						}
						request.setAttribute("labley0", labley0);
						request.setAttribute("labley1", labley1);
						request.setAttribute("labley2", labley2);
						request.setAttribute("labley3", labley3);
						request.setAttribute("labley4", labley4);
				}
			
				StringBuffer buffer = new StringBuffer(); 
				buffer.append("<tr style='text-align:center;'>");
					buffer.append("<td colspan='1'>时间</td><td colspan='1'>");
				for(int i = 0;i < length;i++){
					
					if(i+1 < length){
						buffer.append(resultlend.get(i)).append("</td><td colspan='1'>");
					}
					else{
						buffer.append(resultlend.get(i)).append("</td>");
					
					}
					


				}
				 buffer.append("</tr>");
				 request.setAttribute("top", buffer.toString());
				 buffer.delete(0, buffer.length());
				
				
				
				request.setAttribute("length", length);
		}
		
		 
		 List<DataStatisticsDataVo> queryTime =new ArrayList<DataStatisticsDataVo>();
			DataStatisticsDataVo flag1=new DataStatisticsDataVo();
			flag1.setId(1);
			flag1.setName("一分钟");
			queryTime.add(flag1);
			DataStatisticsDataVo flag2=new DataStatisticsDataVo();
			flag2.setId(2);
			flag2.setName("十分钟");
			queryTime.add(flag2);
			DataStatisticsDataVo flag3=new DataStatisticsDataVo();
			flag3.setId(3);
			flag3.setName("半小时");
			queryTime.add(flag3);
			request.setAttribute("queryTime", queryTime);
			 request.setAttribute("resultlend", resultlend);
				request.setAttribute("resulttu", resulttu);
		lineChartXml(resulttu, request);
		
		return mapping.findForward("show");
	}
	//保留两位小数
	public static double saveTwo(double f){
			BigDecimal   b   =   new   BigDecimal(f);  
			double f1  =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
			return f1;
			}
	
	private void lineChartXml(List<DataStatisticsDataVo> list, HttpServletRequest request) {
		if (list != null && !list.isEmpty()) {
			
			StringBuffer lineXml = new StringBuffer();
			lineXml.append("[");
			Iterator<DataStatisticsDataVo> iter = list.iterator();
			while (iter.hasNext()) {
				DataStatisticsDataVo elem = iter.next();
				
						lineXml.append("{\"");
						lineXml.append("time\"").append(":\"").append(MonthDayHourMTimeUtil.get().format(elem.getSaveTime())).append("\"},");
			
			}	
			lineXml.append("]");
			
			if (lineXml.length() > 0) 
				request.setAttribute("lineXml", lineXml);
		}
		
		else
		{
			StringBuffer lineXml = new StringBuffer();
			lineXml.append("[");
			lineXml.append("{\"");
			lineXml.append("time\"").append(":\"").append(0).append("\"},");
			lineXml.append("]");
			request.setAttribute("lineXml", lineXml);
		}
	}
		
}
