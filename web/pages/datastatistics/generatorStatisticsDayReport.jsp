
<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1   
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0   
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server 
	
%>

<%@ page contentType="text/html;charset=gbk" language="java"%>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/ecside.tld" prefix="ec"%>
<%@ page import="java.util.ArrayList" %>
<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>发电设备统计信息日报表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script
	src="${pageContext.request.contextPath}/scripts/jquery/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/scripts/echarts/build/dist/echarts.js"></script>
<script src="${pageContext.request.contextPath}/scripts/echarts/esl.js"></script>
</head>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/default.css" />
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

<style type="text/css">
/*页面的body*/

</style>

<script type="text/javascript">
	$(document).ready(function() {
		if(${smgSize}==1){
		 $("#Select1").hide();
		 $("#Select2").hide();
		 $("#Select3").show();
		 $("#Select4").hide();
		}
		if(${smgSize}!=1){
		 $("#Select1").show();
		 $("#Select2").show();
		  $("#Select3").hide();
		 $("#Select4").show();
		}
	});

</script>
<script type="text/javascript">
function query1() {
	
	
		var msg = [];
		
		if (document.generatorStatisticsDayReportDataForm.dateDisp.value == '') 
			msg.push('日期');
		if (msg.length > 0) {
			alert('请选择 ' + msg.join('，') + '!');
			return;
		}
		generatorStatisticsDayReportDataForm.method.value = 'show';
		generatorStatisticsDayReportDataForm.submit();
		forbidSubmit(); // 禁止所有按钮或超链接等，位于{ebizapp.js}
		popWaiting('正在查询数据...');
	}
	function query2() {
	var smgValue = $('input:radio[name="smgValue"]:checked').val();
	
		var msg = [];
		if(smgValue==null){
		msg.push('风电场');
		}
		if (document.generatorStatisticsDayReportDataForm.dateDisp.value == '') 
			msg.push('日期');
		if (msg.length > 0) {
			alert('请选择 ' + msg.join('，') + '!');
			return;
		}
		generatorStatisticsDayReportDataForm.method.value = 'show';
		generatorStatisticsDayReportDataForm.submit();
		forbidSubmit(); // 禁止所有按钮或超链接等，位于{ebizapp.js}
		popWaiting('正在查询数据...');
	}
</script>
</HEAD>

<BODY>

	<TABLE style="border: 0; border-collapse: collapse; width:3900; " id="tabOutside">
		<!--如果有错误-->
		<TR height="1">
			<TD height="1">
				<div id="message">
					<!-- 错误 -->
					<logic:messagesPresent>
						<div class="error">
							<img
								src="${pageContext.request.contextPath}/images/iconWarning.png">
							<html:messages id="message">
								<bean:write name="message" />
								<br>
							</html:messages>
						</div>
					</logic:messagesPresent>
					<!-- 消息 -->
					<logic:messagesPresent message="true">
						<div class="error" id="loginError">
							<img
								src="${pageContext.request.contextPath}/images/iconInformation.png">
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
		<html:form action="generatorStatisticsDayReportData" method="post">
			<html:hidden property="method" styleId="method" value="show" />
			<input type="hidden" name="isFirst" value="0">
			<TR>
				<TD style="width: 100%; text-align: left">
					<TABLE>
						<tr>
							<td height="10px"></td>
						</tr>

						<TD width="10"></TD>
						<TD style="color:#000000">选择日</TD>
						<TD ><html:text property="dateDisp"
								styleId="dateId" styleClass="dateInput" size="20"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowWeek:true,isShowClear:true,readOnly:true,firstDayOfWeek:1,maxDate:'2022-12-28'})"
								title="点击选择日期" /></TD>
								<TD width="15"></TD>
							<TD id="Select1" style="color:#000000">选择风场:</TD>

							<TD id="Select2"><% 
						  ArrayList list = (ArrayList)request.getAttribute("resultSmg"); 
						   for(int i=0;i<list.size();i++){
                               out.print(" <input id = 'iput0' type='radio' name='smgValue' value="+request.getAttribute("resultcheckvalue"+i) +" "+request.getAttribute("check"+i)+">"+request.getAttribute("resultcheckname"+i));
                              
                           }
                          %>	</TD>
								
						<TD width="30"></TD>
						<TD id="Select3" valign="middle" ; align="center" width="76"
							background="../images/green/tool/btn_bg.gif"
							background-repeat="no repeat" margin-left="10px"><a
							class="abtn3" href="javascript:query1()"><span><img
									padding-left="10px" src="../images/green/tool/search.png"
									alt="" color="#ffffff" />&nbsp;&nbsp;查&nbsp;&nbsp;询</span></a></TD>
						<TD id="Select4" valign="middle" ; align="center" width="76"
							background="../images/green/tool/btn_bg.gif"
							background-repeat="no repeat" margin-left="10px"><a
							class="abtn3" href="javascript:query2()"><span><img
									padding-left="10px" src="../images/green/tool/search.png"
									alt="" color="#ffffff" />&nbsp;&nbsp;查&nbsp;&nbsp;询</span></a></TD>
					
						</TR>
					</TABLE>
				</TD>
			</tr>
		</html:form>
		<tr>
			<TD style="width: 100%;text-align: center;"></TD>
		</tr>
		<tr>
			<td id="chartTD" width="100%" height="100%" valign="top">

				<table width="100%" height="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>

						<td valign="top" align="left" width="100%" height="100%"><ec:table
								items="result" var="bean"
								action="${pageContext.request.contextPath}/datastatistics/generatorStatisticsDayReportData.do?method=show"
								retrieveRowsCallback="process" sortRowsCallback="process"
								filterRowsCallback="process" xlsFileName="generatorStatisticsDayReportData.xls"
								showTitle="ture" listWidth="100%" listHeight="100%" width="4500"
								sortable="true" filterable="true" showPrint="true"
								toolbarLocation="top" pageSizeList="all,30"
								rowsDisplayed="100" useAjax="false"
								autoIncludeParameters="true">

								<ec:extendrow location="top">
                    	        
    							</ec:extendrow>
								<ec:row highlightRow="true">
									<ec:column property="name" title="风机名称"
										headerClass="ecTableHead" styleClass="ecTableBody" width="150"
										style="text-align:center" />
									<ec:column property="hours" title="有效风时数" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="daygenwh" title="发电量" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="max_windspeed" title="最大风速" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="min_windspeed" title="最小风速" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="avg_windspeed" title="平均风速" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="max_P" title="最大有功" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="min_P" title="最小有功" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="avg_P" title="平均有功" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="max_Q" title="最大无功" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="min_Q" title="最小无功" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="avg_Q" title="平均无功" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="max_Temp" title="最高环境温度" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="min_Temp" title="最低环境温度" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="avg_Temp" title="平均环境温度" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="yawHour" title="偏航总时间" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="yawCWCou" title="偏航总次数" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="lftYawMotorCWCou" title="左偏航次数" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="rtghYawMotorCCWCou" title="右偏航次数" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="winTurStCovCont" title="停机次数" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="winHigErrTimes" title="大风停机次数" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="winTurArtStpCont" title="人工停机次数" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="winTurErrCovCont" title="故障停机次数" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="convMaiSwitchCou" title="并网次数" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="winTurCatInCont" title="切入次数" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="serModTimes" title="维护次数" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />	
								</ec:row>
								<ec:extendrow>
					<tr>
                        <td class="ecTableBody" style="text-align:center"></td>
                        <td class="ecTableBody" style="width:120px;text-align:center;color:#339900;font-weight:bold">合计有效风时数： ${allHours}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计发电量： ${allDaygenwh}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计最大风速： ${allMax_windspeed}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计最小风速： ${allMin_windspeed}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计平均风速： ${allAvg_windspeed}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计最大有功： ${allMax_P}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计最小有功： ${allMin_P}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计平均有功： ${allAvg_P}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计最大无功： ${allMax_Q}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计最小无功： ${allMin_Q}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计平均无功： ${allAvg_Q}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计最高环境温度： ${allMax_Temp}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计最低环境温度： ${allMin_Temp}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计平均环境温度： ${allAvg_Temp}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计偏航总时间： ${allYawHour}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计偏航总次数： ${allYawCWCou}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计左偏航次数： ${allLftYawMotorCWCou}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计右偏航次数： ${allRtghYawMotorCCWCou}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计停机次数： ${allWinTurStCovCont}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计大风停机次数： ${allWinHigErrTimes}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计人工停机次数： ${allWinTurArtStpCont}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计故障停机次数： ${allWinTurErrCovCont}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计并网次数： ${allConvMaiSwitchCou}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计切入次数： ${allWinTurCatInCont}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计维护次数： ${allSerModTimes}</td>
					</tr>
					</ec:extendrow>
							</ec:table></td>
					</tr>
				</table>
			</td>
		</tr>

	</TABLE>

	

</body>
</html>

