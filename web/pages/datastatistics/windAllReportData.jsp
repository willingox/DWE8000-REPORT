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
<title>风机统计状态报表</title>
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
	
	<script type="text/javascript">
      
		
	 $(document).ready(function() {
			//remain();
			
			showTree();
		});
        //*********************************************
        //打印obj所有内容
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
				/**树参数初始化********************************/
				var treeFlType="301";//暂时没用
				var treeType="windAllReportData"; //树类型
				var treeDepth="2"; //树深度
				var treeMode="check"; //树选择模式(单选radio、多选check)
				var disLevel="1,2"; //暂时没用
				/*********************************************/
				if(treeType!=""){
					parent.showEuTree( treeType, treeDepth, treeMode, treeFlType);
					parent.disLevel(disLevel);
				}
			}
		}
		function query() {
		
		var msg = [];
		if (document.windAllReportDataForm.startDateDisp.value == '') 
			msg.push('起始日期');
		
		if (document.windAllReportDataForm.endDateDisp.value == '') 
			msg.push('终止日期');
		if(document.windAllReportDataForm.endDateDisp.value < document.windAllReportDataForm.startDateDisp.value)
			msg.push('正确时间');	
		
		
		
		  var data = parent.getEuTreeSelected();
		 
		  windAllReportDataForm.str.value = data;
		
	      
   	  	   
		  if(data=="")
		  {
		  
			msg.push('风机');
		  }
		
		if (msg.length > 0) {
			alert('请选择 ' + msg.join('，') + '!');
			return;
		}
	
		windAllReportDataForm.method.value = 'show';
		windAllReportDataForm.submit();
		forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
		popWaiting('正在查询');
	}
	
	

	

 </script> 	

  </head>
  
  <body>
       <html:form action="windAllReportData" method="post">
	<html:hidden property="method" styleId="method" value="show" />  
	<html:hidden property="str"/>    
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

	
	<!----------------------------------------工具栏和错误（或数据表格）之间的间隔------------------------------------>
	
	<!----------------------------------------图形开始--<div style="width:100%; height:5%;align:center; color:#ff3300;font-weight:bold;padding-left:0px 0px; position:relative; left:80px; overflow: hidden;"> 当天</div>				
			 	  ------------------------------------------------------------>

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
					action="${pageContext.request.contextPath}/datastatistics/windAllReportData.do?method=show"
					retrieveRowsCallback="process"
					sortRowsCallback="process"
					filterRowsCallback="process"
					xlsFileName="windAllReportData.xls"
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
    					</ec:extendrow> 
					<ec:row highlightRow="true">
					<ec:column  property="name1" title="风机名称"  headerClass="ecTableHead" styleClass="ecTableBody"  width="200" style="text-align:center"/>
					<ec:column  property="name" title="遥信名称"  headerClass="ecTableHead" styleClass="ecTableBody"  width="200" style="text-align:center"/>
					<ec:column  property="name2" title="遥信类型"  headerClass="ecTableHead" styleClass="ecTableBody"  width="200" style="text-align:center"/>
					<ec:column  property="happenTime" title="故障发生时间" cell="date" format="yyyy-MM-dd HH:mm:ss" headerClass="ecTableHead" styleClass="ecTableBody"  width="200" style="text-align:center"/>
					<ec:column  property="removeTime" title="故障消除时间" cell="date" format="yyyy-MM-dd HH:mm:ss" headerClass="ecTableHead" styleClass="ecTableBody"  width="200" style="text-align:center"/>
					<ec:column  property="count" title="故障持续时间(h)" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="120" style="text-align:center"/>
					  
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
