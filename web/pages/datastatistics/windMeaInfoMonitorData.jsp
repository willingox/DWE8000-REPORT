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
<title>���ʵʱ��Ϣ</title>
		
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
		body, html,#allmap {width: 100%;height: 100%;margin:0;font-family:"΢���ź�";}
		
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
<!--����ҳ����Ҫ-->
	<html:form action="windMeaInfoMonitorData" method="post">
	<html:hidden property="method" styleId="method" value="show" />
	
	<input type="hidden" name="isFirst" value="0">
<TABLE class="tabOutside" id="tabOutside">
	<!--����д���-->
	<TR height="1">
		<TD height="1">
			<div id="message">
			<!-- ���� -->
			<logic:messagesPresent>
				<div class="error">
					<img src="${pageContext.request.contextPath}/images/iconWarning.png">
					<html:messages id="message">
						<bean:write name="message" />
						<br>
					</html:messages>
				</div>
			</logic:messagesPresent>
			<!-- ��Ϣ -->
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
	
	<!----------------------------------------������-------------------------------------------------------------->
	<!--����ҳ����Ҫ-->
	

	

	

	
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
					<ec:column  headerSpan="0" property="name" title="�������"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.6%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="rctPowGri" title="�޹�����"  cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="meanRotorSpeed" title="Ҷ��ת��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="positionActual1" title="ҶƬ1����"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="batVoltageUdc1" title="�佰����1��ѹ"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="bladeTorque1" title="�佰���1Ť��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="motorTemp1" title="�佰���1�¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="cabTemp1" title="�佰��1�¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="moduleTemp1" title="�佰��Ƶ��1�¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="voltageUdc1" title="�佰��Ƶ��1ֱ��ĸ�ߵ�ѹ"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="heaterSinkTemp1" title="�佰��Ƶ��1ɢ�Ȱ��¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="positionActual2" title="ҶƬ2����"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.2%" style="text-align:center"/>
					 <ec:column headerSpan="0"  property="batVoltageUdc2" title="�佰����2��ѹ"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="bladeTorque2" title="�佰���2Ť��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="motorTemp2" title="�佰���2�¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="cabTemp2" title="�佰��2�¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="moduleTemp2" title="�佰��Ƶ��2�¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="voltageUdc2" title="�佰��Ƶ��2ֱ��ĸ�ߵ�ѹ"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="heaterSinkTemp2" title="�佰��Ƶ��2ɢ�Ȱ��¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
	                        <ec:column  headerSpan="0" property="positionActual3" title="ҶƬ3����"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.2%" style="text-align:center"/>
					 <ec:column headerSpan="0"  property="batVoltageUdc3" title="�佰����3��ѹ"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="bladeTorque3" title="�佰���3Ť��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="motorTemp3" title="�佰���3�¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="cabTemp3" title="�佰��3�¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="moduleTemp3" title="�佰��Ƶ��3�¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="voltageUdc3" title="�佰��Ƶ��3ֱ��ĸ�ߵ�ѹ"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="heaterSinkTemp3" title="�佰��Ƶ��3ɢ�Ȱ��¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					
					<ec:column  headerSpan="0"  property="gearOilPumPres" title="�������ͱ�ѹ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0"  property="gearOilInPres" title="��������Ϳ�ѹ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="gearOilInTemp" title="��������Ϳ��¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="geaOilTankTemp" title="�����������¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="geaOilHeaTemp" title="�������ͼ������¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="geaBeaTemp1" title="������һ������¶�"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.4%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="geaBeaTemp2" title="�������������¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.4%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="geaBeaTemp3" title="��������������¶�"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.4%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="maxGeaBeaTemp" title="�������������¶�"   cell="number" format="0.00"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.4%" style="text-align:center"/>
					
					<ec:column  headerSpan="0" property="genBearDETemp" title="���������������¶�"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.6%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="genBearNDETemp" title="�������з��������¶�"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="genWatInTemp" title="�������ˮ���¶�"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.4%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="genTempU" title="�����A�ඨ�������¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="genTempV" title="�����B�ඨ�������¶�"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="genTempW" title="�����C�ඨ�������¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					
				<ec:column  headerSpan="0" property="powMeaSorCurI" title="���������"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="powMeaLinFrq" title="����Ƶ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="actPowAuxiliary" title="����й�����"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="meanGscTemp" title="����IGBT�¶�"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="meanLscTemp" title="��������ˮ���¶�"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="winSpeTur0" title="��������ǿ��"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="nacPsnErrDem" title="��������շ����ƫ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="outsideTemp" title="�����¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="nacBoxOutsideTemp" title="�����¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>

                              <ec:column  headerSpan="0" property="yawCalspeed" title="ƫ�����ת��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.2%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="meanTBCInTemp" title="�������¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.2%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="meanNC310InTemp" title="���չ��¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.2%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="meanTBCOutTemp" title="��Ͳ�¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="powMeaPf" title="��������"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="powRat" title="�й�������ֵ"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="cableTwistTotal" title="Ť�½Ƕ�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="volU12" title="�ߵ�ѹA-B"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.2%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="volU23" title="�ߵ�ѹB-C"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.2%" style="text-align:center"/>
	                        <ec:column  headerSpan="0" property="volU31" title="�ߵ�ѹC-A"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.2%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="curI1" title="�����I1"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="curI2" title="�����I2"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="curI3" title="�����I3"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
				
				      <ec:column  headerSpan="0" property="powLmtHMIWPC" title="�й���������ֵ"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.4%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="rePowLmtHMIWPC" title="�޹���������ֵ"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.4%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="speLmtHMIWPC" title="�����ת������ֵ"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="winSpeVal0" title="������1˲ʱ����"  cell="number" format="0.00"   headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="winSpeVal1" title="������2˲ʱ����"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.3%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="auxTransforTemp" title="������ѹ���¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.4%" style="text-align:center"/>
	                        <ec:column  headerSpan="0" property="actPowGri" title="�й�����"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="windSpeed" title="����"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="winDirNor" title="����"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="meanGenSpeed" title="�����ת��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.2%" style="text-align:center"/>
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
