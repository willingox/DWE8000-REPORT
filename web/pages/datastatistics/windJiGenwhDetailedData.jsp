<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1   
response.setHeader("Pragma","no-cache"); //HTTP 1.0   
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server

%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/ecside.tld" prefix="ec"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>风机功率详情</title>
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
      a{text-decoration:none;}
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
  		function query() {
			
		var msg = [];
		if (document.windGenwhDetailedDataForm.startDateDisp.value == '') 
			msg.push('起始日期');
		if (document.windGenwhDetailedDataForm.endDateDisp.value == '') 
			msg.push('终止日期');
				
		
		if (msg.length > 0) {
			alert('请选择 ' + msg.join('，') + '!');
			return;
		}

		 windGenwhDetailedDataForm.method.value = 'showWind';
             windGenwhDetailedDataForm.submit();
             forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
             popWaiting('正在查询时数据...');
	}
 </script> 	
  
 
</HEAD>
  
  <body>
   <html:form action="windGenwhDetailedData" method="post">
	<html:hidden property="method" styleId="method" value="showWind" />             
	
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
	<TR>
		<TD style="width: 100%; text-align: left">
			<TABLE>	
    			<tr><td height="5px"></td></tr>
				<TR>	
				    <TD width="5"></TD>
					<TD valign="bottom"; align="center" height="23" width="88" background="../images/green/tool/level3_2.png"  >
					<a class="abtnx" href="${pageContext.request.contextPath}/datastatistics/windGenwhDetailedData.do"><span><font size="2" color="#302422">全场功率详情</font></span></a>
					</TD>
					<TD valign="bottom"; align="center" height="23" width="88" background="../images/green/tool/level3_1.png" background-repeat="no repeat" margin-left="10px">
					<a  class="abtnx" href="${pageContext.request.contextPath}/datastatistics/windGenwhDetailedData.do?method=showWind"><span><font size="2" color="#302422">风机功率详情</font></span></a>
					</TD>
					
	         
					
					
				</TR>
   							
			</TABLE>
		</TD>
	</tr>
					<tr>
				<TD valign="middle"; align="center" height="5" width="1930" background="../images/green/tool/top1.png">
				</td></tr>
	<TR>
		<TD style="width: 100%; text-align: left">
			<TABLE>	
    			<tr><td height="10px"></td></tr>	
				<TR>	
				    <TD width="10"></TD>
					<TD>开始日期      </TD>
					<TD id="Select" >										
						<html:text  property="startDateDisp" styleId="startDateId" styleClass="dateInput" size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowWeek:true,isShowClear:true,readOnly:true,firstDayOfWeek:1,maxDate:'2022-12-28 23:59:59'})" title="点击选择起始日期" />
					</TD>				
				     <TD width="10"></TD>
					<TD>结束日期      </TD>
					<TD id="Select" >										
						<html:text  property="endDateDisp" styleId="startDateId" styleClass="dateInput" size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowWeek:true,isShowClear:true,readOnly:true,firstDayOfWeek:1,maxDate:'2022-12-28 23:59:59'})" title="点击选择起始日期" />
					</TD>				
				    
				    <TD width="30"></TD>
					<TD valign="middle"; align="center" width="76" background="../images/green/tool/btn_bg.gif" background-repeat="no repeat" margin-left="10px">			
						<a class="abtn3" href="javascript:query()"><span ><img  padding-left="10px" src="../images/green/tool/search.png" alt="" />&nbsp;&nbsp;查&nbsp;&nbsp;询</span></a>
					   
					</TD> 
				</TR>
    							
			</TABLE>
		</TD>
	</tr>	
	</html:form>	
	
	<!----------------------------------------简单条件-------------------------------------------------------------->
	<!--如有页面需要-->

	
	<!----------------------------------------工具栏和错误（或数据表格）之间的间隔------------------------------------>
	
	<!----------------------------------------图形开始--<div style="width:100%; height:5%;align:center; color:#ff3300;font-weight:bold;padding-left:0px 0px; position:relative; left:80px; overflow: hidden;"> 当天</div>				
			 	  ------------------------------------------------------------>

	
	
	<tr>
	<td id="chartTD" width="100%" height="100%" valign="top">
		<table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" >
			<tr>
			  <TD id="chartTD1"   width="100%" height="100%" valign="top" align="left">
			  <div style="width: 100%; height: 100%; overflow: hidden;">
			  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" >
			  <tr>			     
			         
				<td valign="top" align="left" width="100%" height="100%" >
				<ec:table 
					
					items="resultTable"
					var="bean"
					action="${pageContext.request.contextPath}/datastatistics/windGenwhDetailedData.do?method=showWind"
					retrieveRowsCallback="process"
					sortRowsCallback="process"
					filterRowsCallback="process"
					xlsFileName="windJiGenwhDetailedData.xls"
                              showTitle="ture"
					listWidth ="100%"
					listHeight="100%"
					width="100%"
					sortable="true" 
					filterable="true" 
					showPrint="true"
					toolbarLocation="top"
                    pageSizeList="all"
                  	rowsDisplayed="2000"
                    useAjax="false"  
                    autoIncludeParameters="true">                    	 

					<ec:row highlightRow="true">
					  <ec:column   property="name" title="风机名称"  headerClass="ecTableHead" styleClass="ecTableBody"  width="200" style="text-align:center"/>
					  <ec:column   property="maxCurp" title="功率(KW)" cell="number" format="0.000" headerClass="ecTableHead" styleClass="ecTableBody"  width="120" style="text-align:center"/>
					  <ec:column   property="maxWind" title="风速" cell="number" format="0.000" headerClass="ecTableHead" styleClass="ecTableBody"  width="120" style="text-align:center"/>
					  <ec:column   property="maxTime" title="时间"  headerClass="ecTableHead" styleClass="ecTableBody"  width="200" style="text-align:center"/>
					  
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
