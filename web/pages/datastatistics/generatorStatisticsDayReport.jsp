
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
<title>�����豸ͳ����Ϣ�ձ���</title>
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
/*ҳ���body*/

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
			msg.push('����');
		if (msg.length > 0) {
			alert('��ѡ�� ' + msg.join('��') + '!');
			return;
		}
		generatorStatisticsDayReportDataForm.method.value = 'show';
		generatorStatisticsDayReportDataForm.submit();
		forbidSubmit(); // ��ֹ���а�ť�����ӵȣ�λ��{ebizapp.js}
		popWaiting('���ڲ�ѯ����...');
	}
	function query2() {
	var smgValue = $('input:radio[name="smgValue"]:checked').val();
	
		var msg = [];
		if(smgValue==null){
		msg.push('��糡');
		}
		if (document.generatorStatisticsDayReportDataForm.dateDisp.value == '') 
			msg.push('����');
		if (msg.length > 0) {
			alert('��ѡ�� ' + msg.join('��') + '!');
			return;
		}
		generatorStatisticsDayReportDataForm.method.value = 'show';
		generatorStatisticsDayReportDataForm.submit();
		forbidSubmit(); // ��ֹ���а�ť�����ӵȣ�λ��{ebizapp.js}
		popWaiting('���ڲ�ѯ����...');
	}
</script>
</HEAD>

<BODY>

	<TABLE style="border: 0; border-collapse: collapse; width:3900; " id="tabOutside">
		<!--����д���-->
		<TR height="1">
			<TD height="1">
				<div id="message">
					<!-- ���� -->
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
					<!-- ��Ϣ -->
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

		<!----------------------------------------������-------------------------------------------------------------->
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
						<TD style="color:#000000">ѡ����</TD>
						<TD ><html:text property="dateDisp"
								styleId="dateId" styleClass="dateInput" size="20"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowWeek:true,isShowClear:true,readOnly:true,firstDayOfWeek:1,maxDate:'2022-12-28'})"
								title="���ѡ������" /></TD>
								<TD width="15"></TD>
							<TD id="Select1" style="color:#000000">ѡ��糡:</TD>

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
									alt="" color="#ffffff" />&nbsp;&nbsp;��&nbsp;&nbsp;ѯ</span></a></TD>
						<TD id="Select4" valign="middle" ; align="center" width="76"
							background="../images/green/tool/btn_bg.gif"
							background-repeat="no repeat" margin-left="10px"><a
							class="abtn3" href="javascript:query2()"><span><img
									padding-left="10px" src="../images/green/tool/search.png"
									alt="" color="#ffffff" />&nbsp;&nbsp;��&nbsp;&nbsp;ѯ</span></a></TD>
					
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
									<ec:column property="name" title="�������"
										headerClass="ecTableHead" styleClass="ecTableBody" width="150"
										style="text-align:center" />
									<ec:column property="hours" title="��Ч��ʱ��" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="daygenwh" title="������" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="max_windspeed" title="������" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="min_windspeed" title="��С����" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="avg_windspeed" title="ƽ������" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="max_P" title="����й�" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="min_P" title="��С�й�" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="avg_P" title="ƽ���й�" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="max_Q" title="����޹�" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="min_Q" title="��С�޹�" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="avg_Q" title="ƽ���޹�" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="max_Temp" title="��߻����¶�" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="min_Temp" title="��ͻ����¶�" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="avg_Temp" title="ƽ�������¶�" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="yawHour" title="ƫ����ʱ��" cell="number"
										format="0.00" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="yawCWCou" title="ƫ���ܴ���" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="lftYawMotorCWCou" title="��ƫ������" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="rtghYawMotorCCWCou" title="��ƫ������" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="winTurStCovCont" title="ͣ������" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="winHigErrTimes" title="���ͣ������" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="winTurArtStpCont" title="�˹�ͣ������" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="winTurErrCovCont" title="����ͣ������" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="convMaiSwitchCou" title="��������" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="winTurCatInCont" title="�������" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />
									<ec:column property="serModTimes" title="ά������" cell="number"
										format="0" headerClass="ecTableHead"
										styleClass="ecTableBody" width="150" style="text-align:center" />	
								</ec:row>
								<ec:extendrow>
					<tr>
                        <td class="ecTableBody" style="text-align:center"></td>
                        <td class="ecTableBody" style="width:120px;text-align:center;color:#339900;font-weight:bold">�ϼ���Ч��ʱ���� ${allHours}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼƷ������� ${allDaygenwh}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ������٣� ${allMax_windspeed}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ���С���٣� ${allMin_windspeed}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ�ƽ�����٣� ${allAvg_windspeed}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ�����й��� ${allMax_P}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ���С�й��� ${allMin_P}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ�ƽ���й��� ${allAvg_P}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ�����޹��� ${allMax_Q}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ���С�޹��� ${allMin_Q}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ�ƽ���޹��� ${allAvg_Q}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ���߻����¶ȣ� ${allMax_Temp}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ���ͻ����¶ȣ� ${allMin_Temp}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ�ƽ�������¶ȣ� ${allAvg_Temp}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ�ƫ����ʱ�䣺 ${allYawHour}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ�ƫ���ܴ����� ${allYawCWCou}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ���ƫ�������� ${allLftYawMotorCWCou}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ���ƫ�������� ${allRtghYawMotorCCWCou}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ�ͣ�������� ${allWinTurStCovCont}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼƴ��ͣ�������� ${allWinHigErrTimes}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ��˹�ͣ�������� ${allWinTurArtStpCont}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼƹ���ͣ�������� ${allWinTurErrCovCont}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼƲ��������� ${allConvMaiSwitchCou}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ���������� ${allWinTurCatInCont}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">�ϼ�ά�������� ${allSerModTimes}</td>
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

