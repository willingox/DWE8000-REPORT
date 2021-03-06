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
<title>风机实时统计</title>
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
<!--如有页面需要-->
	<html:form action="windRealStatisticsData" method="post">
	<html:hidden property="method" styleId="method" value="show" />
	
	<input type="hidden" name="isFirst" value="0">

	
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
					<ec:column  headerSpan="0"  property="name" title="风机名称"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2.2%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="actPowDaySum" title="日发电量"  cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="actPowMonthSum" title="月发电量"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="actPowYearSum" title="年发电量"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="actPowSum" title="总发电量"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="actPowSumCsm" title="总耗电量"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					
					<ec:column  headerSpan="0" property="plcRunSecSUM" title="PLC运行总时间"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.8%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="winTurErrSecSUM" title="风机故障停机总时间"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.8%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="maxGenBearTemp" title="发电机最大轴承温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.8%" style="text-align:center"/>

					<ec:column  headerSpan="0" property="genStatorTemp" title="发电机最大定子绕组温度"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="accDriPp" title="风机振动驱动方向峰峰值"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="accNonDriPp" title="风机振动非驱动方向峰峰值"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2.2%" style="text-align:center"/>
					 <ec:column headerSpan="0"  property="braOffTimeHour" title="刹车投入总时间"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.6%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="oilPumpHigCouHour" title="齿轮箱油泵高速运行总时间"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="oilPumpCouTimHour" title="齿轮箱油泵低速运行总时间"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="gearOilFanRunTim" title="齿轮箱油冷风扇运行总时间"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2.1%" style="text-align:center"/>

					<ec:column  headerSpan="0" property="genWaterPumpTim" title="发电机水冷泵运行总时间"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="hydPumpCouTimHour" title="液压站泵运行总时间"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.8%" style="text-align:center"/>

					<ec:column  headerSpan="0" property="yawMotorLftHour" title="左偏航总时间"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
	                        <ec:column  headerSpan="0" property="yawMotorRtghHour" title="右偏航总时间"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					 <ec:column headerSpan="0"  property="curMonAvlbltRat" title="当月可利用率"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="lasMonAvlbltRat" title="上月可利用率"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="curYeaAvlbltRat" title="当年可利用率"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="lasYeaAvlbltRat" title="去年可利用率"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>

					<ec:column  headerSpan="0" property="curMonAvlbltHour" title="风机月可利用小时数"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="curMonNormTim" title="月运行小时数"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="curMonRunTim" title="月发电小时数"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					
					<ec:column  headerSpan="0"  property="curMonStopTim" title="月停机小时数"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0"  property="curMonErrEmeTim" title="月故障小时数"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="curMonSerTim" title="月维护小时数"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="curMonGridErrTim" title="电网月故障时间"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.8%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="autoResetTimes" title="故障可自动复位的次数"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="2%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="winTurStCovCont" title="停机总次数"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.6%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="serModTimes" title="维护次数"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="emeStoTimes" title="急停次数"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="braCou" title="刹车投入总次数"   cell="number" format="0.00"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.8%" style="text-align:center"/>
					
					<ec:column  headerSpan="0" property="oilPmpHigCou" title="齿轮箱油泵高速启动总次数"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="2.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="oilPmpLowCou" title="齿轮箱油泵低速启动总次数"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="2.1%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="oilFanRunCoun" title="齿轮箱油冷风扇启动总次数"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="2.1%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="hydPumpCou" title="液压站泵启动总次数"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.9%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="yawMotorCWCou" title="左偏航总次数"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="yawMotorCCWCou" title="右偏航总次数"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					
				<ec:column  headerSpan="0" property="winTurErrCovCont" title="故障停机次数"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="convMaiSwitchCou" title="并网次数"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					<ec:column  headerSpan="0" property="winHigErrTimes" title="大风停机次数"   cell="number" format="0.00"  headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="winTurCatInCont" title="切入次数"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.5%" style="text-align:center"/>
					 <ec:column  headerSpan="0" property="winTurArtStpCont" title="人工停机次数"    cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="1.7%" style="text-align:center"/>


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

