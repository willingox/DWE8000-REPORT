<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1   
response.setHeader("Pragma","no-cache"); //HTTP 1.0   
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server 
%>
<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/ecside.tld" prefix="ec"%>
<%@ page import="java.util.ArrayList" %>
<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>选择遥测</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script src="${pageContext.request.contextPath}/scripts/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/scripts/echarts/build/dist/echarts.js"></script>
<script src="${pageContext.request.contextPath}/scripts/echarts/esl.js"></script></head>
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

 <style type="text/css">
	#popupcontent{ 
position: absolute; 
visibility: hidden; 
overflow: hidden; 
border:1px solid #CCC; 
background-color:#F9F9F9; 
border:1px solid #333; 
padding:5px; 
} 
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
 //window.dialogArguments.window.navigate('${pageContext.request.contextPath}/datastatistics/runingInfoData.do?');
		//window.dialogArguments.reloadPage();
		});
	 function writeObj(obj){ 
		    var description = ""; 
		    for(var i in obj){   
		        var property=obj[i];   
		        description+=i+" = "+property+"\n";  
		    }   
			    alert(description); 
		} 
	function returnCurrentNode() {
		 obj = document.getElementsByName("test");
		  check_val = [];
		    for(i=0;i<obj.length;i++){
		        if(obj[i].checked){
		            check_val.push(obj[i].value);
		        }
		    }
		  if(check_val=="")
		  {
		  
			alert('请选择遥测 !');
			return;
		  } 
 if (window.showModalDialog!=null)//IE判断
           {
   window.dialogArguments.runingInfo_OneDayReportDataForm.action = '${pageContext.request.contextPath}/datastatistics/runingInfo_OneDayReportData.do?check_val='+check_val;
		
		window.dialogArguments.runingInfo_OneDayReportDataForm.submit();
		window.close();
          }
      
          else{
          window.opener.runingInfo_OneDayReportDataForm.action = '${pageContext.request.contextPath}/datastatistics/runingInfo_OneDayReportData.do?check_val='+check_val;
		
		window.opener.runingInfo_OneDayReportDataForm.submit();
	
		top.close();
           
           
          }
		
	}
	function returnClose() {
		 
		top.close();
	}
	
	//全选
    function selectAll(){
        var ids = document.getElementsByName("test");                            
        for(var i=0;i<ids.length;i++){

            ids[i].checked=true;
        }
    }
    //全不
    function selectNone(){
        var ids = document.getElementsByName("test");                            
        for(var i=0;i<ids.length;i++){
            ids[i].checked=false;
        }
    }
</script> 
</HEAD>
<BODY scroll="auto" style="background-image:url(../images/backgroundcolor.jpg); background-repeat:repeat;">
<html:form action="runingInfo_OneDayReportData"  method="post">
<html:hidden property="method" value="select" />
<TABLE class="tabOutside" id="tabOutside">
	<!----------------------------------------错误或提示--------------------------------------------------------------->
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
	<!----------------------------------------工具栏和错误（或数据表格）之间的间隔------------------------------------>
	
	<!----------------------------------------数据详细表格开始-------------------------------------------------------------->
	  <TR height="100">
		<TD align="center">
		<table width="100%" heigth="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="25%">&nbsp;</td>
				<td>
					<a class="abtn2" href="javascript:selectAll()"  ><span>全选</span></a>
				
					<a class="abtn2" href="javascript:selectNone()" ><span>全不选</span></a>
					
				</td>
			</tr>
		</table>
		</TD>
	</TR>
	
	
	
	<TR>
		<TD style="width: 100%; text-align: left">
			<TABLE>	
    			<tr><td height="10px"></td></tr>	
				<TR>	
				 <TD width="10"></TD>
					
					<TD  >									
						<div width="100%" height="15">
						 <% 
						  ArrayList list = (ArrayList)request.getAttribute("resultCheckName"); 
						   for(int i=0;i<list.size();i++){
                               out.print(" <input id = 'iput0' type='checkbox' name='test' value="+request.getAttribute("resultcheckvalue"+i) +">"+request.getAttribute("resultcheckname"+i));
                               out.print("<br />");
                           }
                          %>	

	                    </div>
					</TD>	
				  
				</TR>

    							
			</TABLE>
		</TD>
	</tr>
	
		 
	       <TR height="100">
		<TD align="center">
		<table width="100%" heigth="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="25%">&nbsp;</td>
				<td>
					<a class="abtn2" href="#" onclick="javascript:returnCurrentNode()" ><span><img align = "left" valign = "center" src="${pageContext.request.contextPath}/images/green/tool/save.png" alt="" />确&nbsp;&nbsp;定</span></a>
				
					<a class="aBtn2" href="#" onclick="javascript:returnClose()"><span><img align = "left" valign = "center" src="${pageContext.request.contextPath}/images/green/tool/back.png" alt="" />取&nbsp;&nbsp;消</span></a>
					
				</td>
			</tr>
		</table>
		</TD>
	</TR>
	
	

	
</html:form>
</BODY>
</html>