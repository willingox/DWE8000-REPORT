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
<title>���ʵʱͳ��</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
 		<script src="${pageContext.request.contextPath}/scripts/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/scripts/echarts/build/dist/echarts.js"></script>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/FusionCharts/js/FusionCharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/FusionCharts/js/FusionChartsExportComponent.js"></script>
<script type="text/javascript" name="baidu-tc-cerfication" data-appid="7418552" src="http://apps.bdimg.com/cloudaapi/lightapp.js"></script>
<script src="${pageContext.request.contextPath}/scripts/JSON-js-master/json2.js"></script>

 





<BODY style="width:10000; " scroll="auto">
<!--����ҳ����Ҫ-->
	<html:form action="windRealStatisticsData" method="post">
	<html:hidden property="method" styleId="method" value="show" />
	
	<input type="hidden" name="isFirst" value="0">

	
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
					action="${pageContext.request.contextPath}/datastatistics/windRealStatisticsData.do?method=show"
					retrieveRowsCallback="process"
					sortRowsCallback="process"
					filterRowsCallback="process"
					xlsFileName="windRealStatisticsData.xls"
                              showTitle="ture"
					listWidth ="100%"
					listHeight="100%"
					width="100%"
					sortable="true" 
					filterable="true" 
					showPrint="true"
					toolbarLocation="top"
                    pageSizeList="all,30"
                    rowsDisplayed="100"
                    useAjax="false"  
                    autoIncludeParameters="true">   
                                	 
                    	<ec:extendrow location="top">
                    	        ${top}
    					</ec:extendrow> 
					<ec:row highlightRow="true">
					<ec:column  headerSpan="0"  property="name" title="�������"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2.2%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="actPowDaySum" title="�շ�����"  cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="actPowMonthSum" title="�·�����"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="actPowYearSum" title="�귢����"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="actPowSum" title="�ܷ�����"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="actPowSumCsm" title="�ܺĵ���"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					
					<ec:column  headerSpan="0" property="plcRunSecSUM" title="PLC������ʱ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.8%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="winTurErrSecSUM" title="�������ͣ����ʱ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.8%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="maxGenBearTemp" title="������������¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.8%" style="text-align:center"/>

					<ec:column  headerSpan="0" property="genStatorTemp" title="���������������¶�"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="accDriPp" title="���������������ֵ"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="accNonDriPp" title="����񶯷�����������ֵ"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2.2%" style="text-align:center"/>
					 <ec:column headerSpan="0"  property="braOffTimeHour" title="ɲ��Ͷ����ʱ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.6%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="oilPumpHigCouHour" title="�������ͱø���������ʱ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="oilPumpCouTimHour" title="�������ͱõ���������ʱ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="gearOilFanRunTim" title="�������������������ʱ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2.1%" style="text-align:center"/>

					<ec:column  headerSpan="0" property="genWaterPumpTim" title="�����ˮ���������ʱ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="hydPumpCouTimHour" title="Һѹվ��������ʱ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.8%" style="text-align:center"/>

					<ec:column  headerSpan="0" property="yawMotorLftHour" title="��ƫ����ʱ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
	                        <ec:column  headerSpan="0" property="yawMotorRtghHour" title="��ƫ����ʱ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					 <ec:column headerSpan="0"  property="curMonAvlbltRat" title="���¿�������"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="lasMonAvlbltRat" title="���¿�������"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="curYeaAvlbltRat" title="�����������"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="lasYeaAvlbltRat" title="ȥ���������"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>

					<ec:column  headerSpan="0" property="curMonAvlbltHour" title="����¿�����Сʱ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="curMonNormTim" title="������Сʱ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="curMonRunTim" title="�·���Сʱ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					
					<ec:column  headerSpan="0"  property="curMonStopTim" title="��ͣ��Сʱ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0"  property="curMonErrEmeTim" title="�¹���Сʱ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="curMonSerTim" title="��ά��Сʱ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="curMonGridErrTim" title="�����¹���ʱ��"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.8%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="autoResetTimes" title="���Ͽ��Զ���λ�Ĵ���"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="winTurStCovCont" title="ͣ���ܴ���"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.6%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="serModTimes" title="ά������"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="emeStoTimes" title="��ͣ����"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="braCou" title="ɲ��Ͷ���ܴ���"   cell="number" format="0.00"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.8%" style="text-align:center"/>
					
					<ec:column  headerSpan="0" property="oilPmpHigCou" title="�������ͱø��������ܴ���"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="2.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="oilPmpLowCou" title="�������ͱõ��������ܴ���"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="2.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="oilFanRunCoun" title="������������������ܴ���"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="2.1%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="hydPumpCou" title="Һѹվ�������ܴ���"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.9%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="yawMotorCWCou" title="��ƫ���ܴ���"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="yawMotorCCWCou" title="��ƫ���ܴ���"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					
				<ec:column  headerSpan="0" property="winTurErrCovCont" title="����ͣ������"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="convMaiSwitchCou" title="��������"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="winHigErrTimes" title="���ͣ������"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="winTurCatInCont" title="�������"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="winTurArtStpCont" title="�˹�ͣ������"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>


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
