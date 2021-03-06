
<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1   
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0   
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
	 
%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/ecside.tld" prefix="ec"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page
	import="com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo" %>
	
	
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

<title>电场实时总览</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/ecTable.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/scripts/popWaiting/popWaiting.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/ecside/css/ecside_style.css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/ecside/js/ecside.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ecside/js/ecside_msg_utf8_cn.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ecside/js/ecside_msg_gbk_cn.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/popWaiting/ymPrompt.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/popWaiting/popWaiting.js"></script>
   <script src="${pageContext.request.contextPath}/scripts/echarts/build/dist/echarts.js"></script>
<script type="text/javascript"
	src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>

<style type="text/css">
body,html,#allmap {
	width: 100%;
	height: 100%;
	margin: 0;
	font-family: "微软雅黑";
}
</style>

<style type="text/css">

.item {
	width:120px;
	height:150px;
	float: left;
	margin: 5px;
	display: block;
	text-decoration: none;
	border: 0px solid #333;
	/* BACKGROUND-IMAGE: url(../images/green/tool/pic_S2_h.png); */
	background-color:#DBf2f2;
}

.item img {
	/* border: 1px solid #333; */
	width:40px;
	height:40px;
	
}

.item p {
 	margin: 0;
 	text-align: left;
 	padding:3px;
/*	font-weight: bold;*/
	text-align: center;
	color: #000000; 
}
</style>


<script>
	function query() {

		

		windGerRtMonitorDataForm.method.value = 'show';
		windGerRtMonitorDataForm.submit();
		forbidSubmit(); // 禁止所有按钮或超链接等，位于{ebizapp.js}
		popWaiting('正在查询');
	}
</script>

</HEAD>
<!-- border="1" -->
<body style="background-color:#edf7f1">
<html:form action="windGerRtMonitorData" method="post">
	<html:hidden property="method" styleId="method" value="show" />
	<input type="hidden" name="isFirst" value="0">
	
	<table   width="100%"   cellspacing="10"  style = " " >
	
		
		
		<tr>
			<td width="100%" >
				<table border=1  style = "border-collapse: collapse; width="100%">
					<tr>

						
						
						
						<td  width="37%">
							<div style=" float:left; " ></div>
							<div id="mainpie"></div>
						</td>
						
						<td width="40%">
							<table width="100%" style="border-collapse: collapse;  ">
								<tr>
									<td style="color:#478b2d;font-weight:bolder;">当前总功率</td>
									<td style="color:#478b2d;font-weight:bolder;">${curp}</td>
								</tr>
								<tr>
									<td style="color:#478b2d;font-weight:bolder;">日最大出力</td>
									<td style="color:#478b2d;font-weight:bolder;">${maxgenpw}</td>
								</tr>
								<tr>
									<td style="color:#478b2d;font-weight:bolder;">日最大出力时间</td>
									<td style="color:#478b2d;font-weight:bolder;">${maxgenptime}</td>
								</tr>
								<tr>
									<td style="color:#478b2d;font-weight:bolder;">日发电量</td>
									<td style="color:#478b2d;font-weight:bolder;">${todaygenwh}</td>
								</tr>
								<tr>
									<td style="color:#478b2d;font-weight:bolder;">日上网电量</td>
									<td style="color:#478b2d;font-weight:bolder;">${todayupnetwh}</td>
								</tr>
								<tr>
									<td style="color:#478b2d;font-weight:bolder;">辐照值</td>
									<td style="color:#478b2d;font-weight:bolder;">${sunlightval}W/m2</td>
								</tr>
								<tr>
									<td style="color:#478b2d;font-weight:bolder;">环境温度</td>
									<td style="color:#478b2d;font-weight:bolder;">${tempval}℃</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

			</td>
		</tr>
		
		
		
		<tr >
			<td width="100%" >
				<table   style = "border-collapse: collapse;  "  >
					<tr >
						<td width="30%" style="border:0;color:#da9234;background:transparent;BACKGROUND-IMAGE: url(../images/green/tool/pic_S2_h.png);background-size: 100%;font-size:18px;font-weight:bolder;">风机列表</td> 
						<td style=" width:14%; color:#1f1f1f;font-size:10px;font-weight:bolder;"> 获取时间：${time}</td>
						
						
						<td style="color:#478b2d;font-weight:bolder;" >筛选：状态 &nbsp;&nbsp;&nbsp;<html:select property="stateType" value="${windGerRtMonitorDataForm.stateType}" styleClass="formDataFormSelect" style="width:150px;border-width:1;color:#478b2d">
                           <logic:present name="stateTypes">
                           		<html:options collection="stateTypes" labelProperty="name" property="id"/>
                           </logic:present>
                       	</html:select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       	</td>
                       	
						<td style="color:#478b2d;font-weight:bolder;" >排序：指标 &nbsp;&nbsp;&nbsp;<html:select property="targetType" value="${windGerRtMonitorDataForm.targetType}" styleClass="formDataFormSelect" style="width:150px;border-width:1;color:#478b2d">
                         
                           <logic:present name="targetTypes">
                           		<html:options collection="targetTypes" labelProperty="name" property="id"/>
                           </logic:present>
                       	</html:select>
                       	</td>
                       	<td>
                       		<div width="100%" height="15">
								<div id="staTypedown" style="float:left;color:#478b2d;font-weight:bolder;">
									<html:radio property="sortType" styleId="down" value="1"></html:radio>
									降序
								</div>
								<div id="staTypeup" style="float:left;color:#478b2d;font-weight:bolder;">
									<html:radio property="sortType" styleId="up" value="2"></html:radio>
									升序
								</div>
							</div>
                       	</td>
                       	
                       	<td width="5%"></td>
                       	<TD style="color:#aa45ba;font-weight:bolder;" valign="middle" ; align="center" width="100"
								background="../../images/green/tool/btn_bg.gif"
								background-repeat="no repeat" margin-left="10px"><a
								class="abtn3" href="javascript:query()"><span><img
								padding-left="10px" src="../images/green/tool/refresh.png"
								alt="" />&nbsp;&nbsp;立即刷新&nbsp;&nbsp;</span></a>
						</TD>
					</tr>
				
				</table>
			</td>
		</tr>

		
		<tr>
			<td width="100%" >
				<table  width="100%"   style = "border-collapse: collapse; margin: 10px;" >
					<tr >
						<td width="100%">
						<%
							List<DataStatisticsDataVo> result = (List<DataStatisticsDataVo>) request.getAttribute("result");
							for (int i = 0; i < result.size(); i++) {
								DataStatisticsDataVo DataStatisticsDataVo = result.get(i);			
								int state =DataStatisticsDataVo.getCurcmpState();
															
						%>
							<div class="item" style="font-size:12px;color:#da9234;align:left;">
							<c:choose >
	                           	<c:when test="<%=state==0%>"><img src="../images/dwe8000/stop.png" /><font style="font-weight:bolder;color:#0982E3;">停运</font></c:when>
	                           	<c:when test="<%=state==1%>"><img src="../images/dwe8000/gridOnStandby.png" /><font style="font-weight:bolder;color:#D5E802;">并网待机</font></c:when>
	                           	<c:when test="<%=state==2%>"><img src="../images/dwe8000/fullRun.png" /><font style="font-weight:bolder;color:#478b2d;">全功率运行</font></c:when>
	                           	<c:when test="<%=state==3%>"><img src="../images/dwe8000/limitRun.png" /><font style="font-weight:bolder;color:#05DFC9;">限功率运行</font></c:when>
	                           	<c:when test="<%=state==4%>"><img src="../images/dwe8000/overhaul.png" /><font style="font-weight:bolder;color:#FCA41B;">检修</font></c:when>
	                           	<c:when test="<%=state==5%>"><img src="../images/dwe8000/breakdown.png" /><font style="font-weight:bolder;color:#DB0101;"">故障</font></c:when>
	                           	<c:when test="<%=state==6%>"><img src="../images/dwe8000/gridOff.png" /><font style="font-weight:bolder;color:#ABABAB;">离网</font></c:when>
		                        
                             </c:choose>
								
								<div >
									<p style="font-size:10px;color:#000000;align:center;">风机：<%=DataStatisticsDataVo.getName()%></p>
									<p style="font-size:9px;color:#000000;align:center;">功率：<%=DataStatisticsDataVo.getCurp()%>  ${generatorListCurpUnit}</p>
									<p style="font-size:9px;color:#000000;align:center;">发电量:<%=DataStatisticsDataVo.getTodayGenwh()%>  ${generatorListTodayGenwhUnit}</p>
									<p style="font-size:9px;color:#000000;align:center;">风速：<%=DataStatisticsDataVo.getWindSpeed()%>  m/s</p>
									<p style="font-size:9px;color:#000000;align:center;">转速:<%=DataStatisticsDataVo.getMeanGenSpeed()%>  rpm</p>
								</div>
							</div>
						<%
							}
						%>
							
						</td> 
					</tr>
					
				</table>
				
			</td> 
		</tr>
		
		
	</table>
</html:form>	
	

<script type="text/javascript">
		//获取窗口的高度，并设置“main”的高度
		document.getElementById("mainpie").style.height = window.innerHeight
				* 0.15 + "px";
		document.getElementById("mainpie").style.width = window.innerWidth
				* 0.37 + "px";
		// 路径配置

		var myChart = echarts.init(document.getElementById('mainpie'));

		//var echartsdata = ${barXml};

		option = {
		
			color:['#08C931', '#05DFC9','#DB0101','#FCA41B','#0982E3','#E9FC0C','#ABABAB']  ,
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b}: {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				x : 'left',
				data : [ '全功率运行:${fullRun}台', '限功率运行:${limitRun}台', '故障:${breakdown}台', '检修;${overhaul}台', '停运:${stop}台', '并网待机:${gridOnStandby}台', '离网（待并网）:${gridOff}台' ]
			},
			series : [ {
				name : '运行状态',
				type : 'pie',
				
				center: ['70%', '50%'],
				
				avoidLabelOverlap : false,
				label : {
					normal : {
						show : false,
						position : 'center'
					},
					emphasis : {
						show : true,
						textStyle : {
							fontSize : '20',
							fontWeight : 'bold'
						}
					}
				},
				labelLine : {
					normal : {
						show : false
					}
				},
				data : [ {
					value : ${fullRun},
					name : '全功率运行:${fullRun}台'
				}, {
					value : ${limitRun},
					name : '限功率运行:${limitRun}台'
				}, {
					value : ${breakdown},
					name : '故障:${breakdown}台'
				}, {
					value : ${overhaul},
					name : '检修;${overhaul}台'
				}, {
					value : ${stop},
					name : '停运:${stop}台'
				} 
				, {
					value : ${gridOnStandby},
					name : '并网待机:${gridOnStandby}台'
				}
				, {
					value : ${gridOff},
					name : '离网（待并网）:${gridOff}台'
				}]
			} ]
		};
		myChart.setOption(option);
	</script>
</body>

</html>
