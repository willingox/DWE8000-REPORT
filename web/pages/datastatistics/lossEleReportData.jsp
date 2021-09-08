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
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>��ʧ������������</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/popWaiting/popWaiting.css"/>
<link rel="stylesheet" type="text/css" href ="${pageContext.request.contextPath}/ecside/css/ecside_style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/ecside/js/prototype_mini.js"></script>     
<script type="text/javascript" src="${pageContext.request.contextPath}/ecside/js/ecside.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ecside/js/ecside_msg_utf8_cn.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ecside/js/ecside_msg_gbk_cn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/popWaiting/ymPrompt.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/popWaiting/popWaiting.js"></script>
   <script src="${pageContext.request.contextPath}/scripts/echarts/build/dist/echarts.js"></script>
        <script src="${pageContext.request.contextPath}/scripts/echarts/esl.js"></script>


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
	
	<script type="text/javascript">
      
		
	 $(document).ready(function() {
			//remain();
			
			showTree();
		});
        //*********************************************
        //��ӡobj��������
        function writeObj(obj){ 
		    var description = ""; 
		    for(var i in obj){   
		        var property=obj[i];   
		        description+=i+" = "+property+"\n";  
		    }   
			    alert(description); 
		} 
		
		function getTreeSelected() {
			if (parent) {
					parent.getEuTreeSelected();
					
					
			}
		}	
		function singleLeafOnSelect(id,mode){
			//query();
		} 
	 function showTree() {
	 		//alert(parent);
			if (parent) {
				/**��������ʼ��********************************/
				var treeFlType="301";//��ʱû��
				var treeType="lossEleReportData"; //������
				var treeDepth="2"; //�����
				var treeMode="check"; //��ѡ��ģʽ(��ѡradio����ѡcheck)
				var disLevel="1,2"; //��ʱû��
				/*********************************************/
				if(treeType!=""){
					parent.showEuTree( treeType, treeDepth, treeMode, treeFlType);
					parent.disLevel(disLevel);
				}
			}
		}
		function query() {
		
		var msg = [];
		if (document.lossEleReportDataForm.startDateDisp.value == '') 
			msg.push('��ʼ����');
		
		if (document.lossEleReportDataForm.endDateDisp.value == '') 
			msg.push('��ֹ����');
		if(document.lossEleReportDataForm.endDateDisp.value < document.lossEleReportDataForm.startDateDisp.value)
			msg.push('��ȷʱ��');	
		
		
		
		  var data = parent.getEuTreeSelected();
		 
		  lossEleReportDataForm.str.value = data;
		
	      
   	  	   
		  if(data=="")
		  {
		  
			msg.push('���');
		  }
		
		if (msg.length > 0) {
			alert('��ѡ�� ' + msg.join('��') + '!');
			return;
		}
	
		lossEleReportDataForm.method.value = 'show';
		lossEleReportDataForm.submit();
		forbidSubmit();	// ��ֹ���а�ť�����ӵȣ�λ��{ebizapp.js}
		popWaiting('���ڲ�ѯ');
	}
	
	

	

 </script> 	

  </head>
  
  <body>
  	<TABLE style="border: 0; border-collapse: collapse; width:3600; " id="tabOutside">
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

       <html:form action="lossEleReportData" method="post">
	<html:hidden property="method" styleId="method" value="show" />  
	<html:hidden property="str"/>    
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

	
	<!----------------------------------------�������ʹ��󣨻����ݱ��֮��ļ��------------------------------------>
	
	<!----------------------------------------ͼ�ο�ʼ--<div style="width:100%; height:5%;align:center; color:#ff3300;font-weight:bold;padding-left:0px 0px; position:relative; left:80px; overflow: hidden;"> ����</div>				
			 	  ------------------------------------------------------------>

	<TR>
		<TD style="width: 100%; text-align: left">
			<TABLE>	
    			<tr><td height="10px"></td></tr>	
				<TR>	
				    <TD width="10"></TD>
					<TD>��ʼ����      </TD>
					<TD id="Select" >										
						<html:text  property="startDateDisp" styleId="startDateId" styleClass="dateInput" size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowWeek:true,isShowClear:true,readOnly:true,firstDayOfWeek:1,maxDate:'2022-12-28 23:59:59'})" title="���ѡ����ʼ����" />
					</TD>				
				     <TD width="10"></TD>
					<TD>��������      </TD>
					<TD id="Select" >										
						<html:text  property="endDateDisp" styleId="startDateId" styleClass="dateInput" size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowWeek:true,isShowClear:true,readOnly:true,firstDayOfWeek:1,maxDate:'2022-12-28 23:59:59'})" title="���ѡ����ʼ����" />
					</TD>				
				    
				    
				  <TD width="30"></TD>
					<TD valign="middle"; align="center" width="76" background="../images/green/tool/btn_bg.gif" background-repeat="no repeat" margin-left="10px">			
						<a class="abtn3" href="javascript:query()"><span ><img  padding-left="10px" src="../images/green/tool/search.png" alt="" />&nbsp;&nbsp;��&nbsp;&nbsp;ѯ</span></a>
					   
					</TD> 	
 				
			
		 
		</TR>
    							
			</TABLE>
		</TD>
	</tr>	
	</html:form>
	
	<tr>
	<td id="chartTD" width="100%" height="100%" valign="top">

				<table width="100%" height="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>

						<td valign="top" align="left" width="100%" height="100%"><ec:table
								items="result" var="bean"
								action="${pageContext.request.contextPath}/datastatistics/lossEleReportData.do?method=show"
								retrieveRowsCallback="process" sortRowsCallback="process"
								filterRowsCallback="process" xlsFileName="lossEleReportData.xls"
								showTitle="ture" listWidth="100%" listHeight="100%" width="3600"
								sortable="true" filterable="true" showPrint="true"
								toolbarLocation="top" pageSizeList="all,30"
								rowsDisplayed="100" useAjax="false"
								autoIncludeParameters="true">

								<ec:extendrow location="top">
                    	        
    							</ec:extendrow>
					<ec:row highlightRow="true">
					<ec:column property="name" title="�������" headerClass="ecTableHead" styleClass="ecTableBody"  width="180" style="text-align:center"/>
					
					<ec:column property="gridErrStopHour" title="��������ͣ��Сʱ��" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody" width="200" style="text-align:center" />
					<ec:column property="weaErrStopHour" title="����ԭ��ͣ��Сʱ��" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody" width="200" style="text-align:center" />  
		                              <ec:column property="hmiStopHour" title="�͵�ͣ��ͣ��Сʱ��" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody" width="200" style="text-align:center" />
		                              <ec:column property="remoteStopHour" title="Զ��ͣ��ͣ��Сʱ��" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody" width="200" style="text-align:center" />
		                              <ec:column property="errBreakHour" title="����ͣ��ͣ��Сʱ��" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody" width="200" style="text-align:center" />
		                              <ec:column property="powLimHour" title="�޹���Сʱ��" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody" width="200" style="text-align:center" />
		                              <ec:column property="gridErrPowSum" title="��������ͣ����ʧ������" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody" width="200" style="text-align:center" />
					<ec:column property="weaErrPowSum" title="����ԭ����ʧ������" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody" width="200" style="text-align:center" />  
		                              <ec:column property="hmiStopPowSum" title="�͵�ͣ��ͣ����ʧ������" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody" width="200" style="text-align:center" />
		                              <ec:column property="remoteStopPowSum" title="Զ��ͣ��ͣ����ʧ������" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody" width="200" style="text-align:center" />
		                              <ec:column property="errBreakPowSum" title="����ͣ����ʧ����" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody" width="200" style="text-align:center" />
		                              <ec:column property="powLimPowSum" title="�޹�����ʧ����" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody" width="200" style="text-align:center" />
		                              <ec:column property="hiddenPow" title="Ǳ�ܷ�����" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody" width="200" style="text-align:center" />
		                              <ec:column property="capAva" title="���ܿ�������" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody" width="200" style="text-align:center" />
		                              <ec:column property="avaHours" title="������Сʱ��" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody" width="200" style="text-align:center" />
		                              <ec:column property="hoursSum" title="ʱ���ϼ�" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody" width="200" style="text-align:center" />
		                              <ec:column property="lossGenSum" title="��ʧ�����ϼ�" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody" width="200" style="text-align:center" />
                               </ec:row>
				<ec:extendrow>
					<tr>
                               <td class="ecTableBody" style="text-align:center">�ϼ�</td>
					 <td class="ecTableBody" cell="number" format="0.00" style="width:200px;text-align:center;color:#339900;font-weight:bold"> ${gridErrStopHourAll}</td>
					  <td class="ecTableBody" cell="number" format="0.00" style="width:200px;text-align:center;color:#339900;font-weight:bold"> ${weaErrStopHourAll}</td>
					 <td class="ecTableBody" cell="number" format="0.00" style="width:200px;text-align:center;color:#339900;font-weight:bold">${hmiStopHourAll}</td>
					 <td class="ecTableBody" cell="number" format="0.00" style="width:200px;text-align:center;color:#339900;font-weight:bold"> ${remoteStopHourAll}</td>
					 <td class="ecTableBody" cell="number" format="0.00" style="width:200px;text-align:center;color:#339900;font-weight:bold"> ${errBreakHourAll}</td>
					 <td class="ecTableBody" cell="number" format="0.00" style="width:200px;text-align:center;color:#339900;font-weight:bold">${powLimHourAll}</td>
					 <td class="ecTableBody" cell="number" format="0.00" style="width:200px;text-align:center;color:#339900;font-weight:bold"> ${gridErrPowSumAll}</td>
					 <td class="ecTableBody" cell="number" format="0.00" style="width:200px;text-align:center;color:#339900;font-weight:bold">${weaErrPowSumAll}</td>
					 <td class="ecTableBody" cell="number" format="0.00" style="width:200px;text-align:center;color:#339900;font-weight:bold"> ${hmiStopPowSumAll}</td>
					 <td class="ecTableBody" cell="number" format="0.00" style="width:200px;text-align:center;color:#339900;font-weight:bold">${remoteStopPowSumAll}</td>
					 <td class="ecTableBody" cell="number" format="0.00" style="width:200px;text-align:center;color:#339900;font-weight:bold"> ${errBreakPowSumAll}</td>
					 <td class="ecTableBody" cell="number" format="0.00" style="width:200px;text-align:center;color:#339900;font-weight:bold">${powLimPowSumAll}</td>
					 <td class="ecTableBody" cell="number" format="0.00" style="width:200px;text-align:center;color:#339900;font-weight:bold"> ${hiddenPowAll}</td>
					 <td class="ecTableBody" cell="number" format="0.00" style="width:200px;text-align:center;color:#339900;font-weight:bold">${capAvaAll}</td>
					 <td class="ecTableBody" cell="number" format="0.00" style="width:200px;text-align:center;color:#339900;font-weight:bold"> ${avaHoursAll}</td>
					 <td class="ecTableBody" cell="number" format="0.00" style="width:200px;text-align:center;color:#339900;font-weight:bold">${hoursSumAll}</td>
					 <td class="ecTableBody" cell="number" format="0.00" style="width:200px;text-align:center;color:#339900;font-weight:bold"> ${lossGenSumAll}</td>	
					</tr>
					</ec:extendrow>
				</ec:table>
             </td>
			</tr>
					</table>
			</td>
		</tr>

	</TABLE>

	
  </body>
</html>
