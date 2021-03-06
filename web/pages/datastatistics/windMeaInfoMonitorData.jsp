<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1   
response.setHeader("Pragma","no-cache"); //HTTP 1.0   
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server 

%>
<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/ecside.tld" prefix="ec"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>风机实时信息</title>
		
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/popWaiting/popWaiting.css"/>
<link rel="stylesheet" type="text/css" href ="${pageContext.request.contextPath}/ecside/css/ecside_style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/ecside/js/ecside.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ecside/js/ecside_msg_utf8_cn.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ecside/js/ecside_msg_gbk_cn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/popWaiting/ymPrompt.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/popWaiting/popWaiting.js"></script>
   <script src="${pageContext.request.contextPath}/scripts/echarts/build/dist/echarts.js"></script>
        

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		
	</style>
	
	<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
 <style type="text/css">
	.menu_top_sys{
		position: absolute;
		z-index:30;
	}
	.menu_top_sys a{
		text-align:center;
		font-size:13px;
		vertical-align:middle;
		width:100%;
		height:100%;
		text-decoration:none;
		color:black;
		padding:3px 13px;
		margin:0;
	}
	.menu_top_sys a:hover{
		color:#ff3300;
		font-weight:bold;
	}
	</style>







<BODY style="width:10000; " scroll="auto">
<!--如有页面需要-->
	<html:form action="windMeaInfoMonitorData" method="post">
	<html:hidden property="method" styleId="method" value="show" />
	
	<input type="hidden" name="isFirst" value="0">
<TABLE class="tabOutside" id="tabOutside">
	<!--如果有错误-->
	<TR height="1">
		<TD height="1">
			<div id="message">
			<!-- 错误 -->
			<logic:messagesPresent>
				<div class="error">
					<img src="${pageContext.request.contextPath}/images/iconWarning.png">
					<html:messages id="message">
						<bean:write name="message" />
						<br>
					</html:messages>
				</div>
			</logic:messagesPresent>
			<!-- 消息 -->
			<logic:messagesPresent message="true">
				<div class="error" id="loginError">
					<img src="${pageContext.request.contextPath}/images/iconInformation.png">
					<html:messages id="message" message="true">
						<bean:write name="message" />
						<br>
					</html:messages>
				</div>
			</logic:messagesPresent>
			</div>
		</TD>
	</TR>
	
	<!----------------------------------------简单条件-------------------------------------------------------------->
	<!--如有页面需要-->
	

	

	

	
</html:form>
	
	<tr>
	<td id="chartTD" width="100%" height="100%" valign="top">
		<table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" >
			<tr>
			  <TD id="chartTD1"   width="30%" height="100%" valign="top" align="left">
			  <div style="width: 100%; height: 100%; overflow: hidden;">
			  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" >
			  <tr>			     
			         
				<td valign="top" align="left" width="50%" height="100%" >
				<ec:table 
					
					items="result"
					var="bean"
					action="${pageContext.request.contextPath}/datastatistics/windMeaInfoMonitorData.do?method=show"
					retrieveRowsCallback="process"
					sortRowsCallback="process"
					filterRowsCallback="process"
					xlsFileName="windMeaInfoMonitorData.xls"
                              showTitle="ture"
					listWidth ="100%"
					listHeight="100%"
					width="100%"
					sortable="true" 
					filterable="true" 
					showPrint="true"
					toolbarLocation="top"
                    pageSizeList="all,30"
                    rowsDisplayed="10000"
                    useAjax="false"  
                    autoIncludeParameters="true">   
                                	 
                    	<ec:extendrow location="top">
                    	        ${top}
    					</ec:extendrow> 
					<ec:row highlightRow="true">
					<ec:column  headerSpan="0" property="name" title="风机名称"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.6%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="rctPowGri" title="无功功率"  cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="meanRotorSpeed" title="叶轮转速"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="positionActual1" title="叶片1桨角"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="batVoltageUdc1" title="变桨电容1电压"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="bladeTorque1" title="变桨电机1扭矩"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="motorTemp1" title="变桨电机1温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="cabTemp1" title="变桨柜1温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="moduleTemp1" title="变桨变频器1温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="voltageUdc1" title="变桨变频器1直流母线电压"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="heaterSinkTemp1" title="变桨变频器1散热板温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="positionActual2" title="叶片2桨角"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.2%" style="text-align:center"/>
					 <ec:column headerSpan="0"  property="batVoltageUdc2" title="变桨电容2电压"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="bladeTorque2" title="变桨电机2扭矩"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="motorTemp2" title="变桨电机2温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="cabTemp2" title="变桨柜2温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="moduleTemp2" title="变桨变频器2温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="voltageUdc2" title="变桨变频器2直流母线电压"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="heaterSinkTemp2" title="变桨变频器2散热板温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
	                        <ec:column  headerSpan="0" property="positionActual3" title="叶片3桨角"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.2%" style="text-align:center"/>
					 <ec:column headerSpan="0"  property="batVoltageUdc3" title="变桨电容3电压"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="bladeTorque3" title="变桨电机3扭矩"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="motorTemp3" title="变桨电机3温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="cabTemp3" title="变桨柜3温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="moduleTemp3" title="变桨变频器3温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="voltageUdc3" title="变桨变频器3直流母线电压"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="heaterSinkTemp3" title="变桨变频器3散热板温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					
					<ec:column  headerSpan="0"  property="gearOilPumPres" title="齿轮箱油泵压力"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0"  property="gearOilInPres" title="齿轮箱进油口压力"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="gearOilInTemp" title="齿轮箱进油口温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="geaOilTankTemp" title="齿轮箱油箱温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="geaOilHeaTemp" title="齿轮箱油加热器温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="geaBeaTemp1" title="齿轮箱一级轴承温度"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.4%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="geaBeaTemp2" title="齿轮箱二级轴承温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.4%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="geaBeaTemp3" title="齿轮箱三级轴承温度"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.4%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="maxGeaBeaTemp" title="齿轮箱最大轴承温度"   cell="number" format="0.00"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.4%" style="text-align:center"/>
					
					<ec:column  headerSpan="0" property="genBearDETemp" title="发电机轴承驱动端温度"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.6%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="genBearNDETemp" title="发电机轴承非驱动端温度"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="genWatInTemp" title="发电机进水口温度"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.4%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="genTempU" title="发电机A相定子绕组温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="genTempV" title="发电机B相定子绕组温度"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="genTempW" title="发电机C相定子绕组温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					
				<ec:column  headerSpan="0" property="powMeaSorCurI" title="电网相电流"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="powMeaLinFrq" title="电网频率"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="actPowAuxiliary" title="风机有功消耗"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="meanGscTemp" title="机侧IGBT温度"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="meanLscTemp" title="变流器入水口温度"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="winSpeTur0" title="风速湍流强度"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="nacPsnErrDem" title="风向与机舱方向的偏差"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="outsideTemp" title="环境温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="nacBoxOutsideTemp" title="机舱温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>

                              <ec:column  headerSpan="0" property="yawCalspeed" title="偏航电机转速"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.2%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="meanTBCInTemp" title="塔基柜温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.2%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="meanNC310InTemp" title="机舱柜温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.2%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="meanTBCOutTemp" title="塔筒温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="powMeaPf" title="功率因数"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="powRat" title="有功功率限值"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="cableTwistTotal" title="扭缆角度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="volU12" title="线电压A-B"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.2%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="volU23" title="线电压B-C"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.2%" style="text-align:center"/>
	                        <ec:column  headerSpan="0" property="volU31" title="线电压C-A"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.2%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="curI1" title="相电流I1"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="curI2" title="相电流I2"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="curI3" title="相电流I3"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
				
				      <ec:column  headerSpan="0" property="powLmtHMIWPC" title="有功功率限制值"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.4%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="rePowLmtHMIWPC" title="无功功率限制值"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.4%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="speLmtHMIWPC" title="发电机转速限制值"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="winSpeVal0" title="风速仪1瞬时风速"  cell="number" format="0.00"   headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="winSpeVal1" title="风速仪2瞬时风速"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="auxTransforTemp" title="辅助变压器温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.4%" style="text-align:center"/>
	                        <ec:column  headerSpan="0" property="actPowGri" title="有功功率"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="windSpeed" title="风速"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="winDirNor" title="风向"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="meanGenSpeed" title="发电机转速"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.2%" style="text-align:center"/>
                   </ec:row>
				
				</ec:table>
             </td>
			</tr>
		</table>
		</div>
		</TD>
			
    	</table>
	  </td>
	</TR>
	
  </body>
</html>

