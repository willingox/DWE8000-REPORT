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
<title>������������</title>
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

	 function returnCurrentNode() {
		
		    var  flagVal = $("input[name='flag']:checked").val();
		
		  if(flagVal=="")
		  {
		  
			alert('������ !');
			return;
		  } 
	 if (window.showModalDialog!=null)//IE�ж�
           {
      window.dialogArguments.powerScatterDataForm.action = '${pageContext.request.contextPath}/datastatistics/powerScatterData.do?flag='+flagVal;
		
		window.dialogArguments.powerScatterDataForm.submit();
		window.close();
          }
      
          else{
           window.opener.runingInfo_OneMinuteReportDataForm.action = '${pageContext.request.contextPath}/datastatistics/runingInfo_OneMinuteReportData.do?check_val='+check_val;
		
		 window.opener.powerScatterDataForm.submit();
		top.close();
           
          }	
		
	}
	function returnClose() {
		 
		top.close();
	}
	
	
</script> 
</HEAD>
<BODY scroll="auto" style="background-image:url(../images/backgroundcolor.jpg); background-repeat:repeat;">
<html:form action="powerScatterData"  method="post">
<html:hidden property="method" value="deploy" />
<TABLE class="tabOutside" id="tabOutside">
	<!----------------------------------------�������ʾ--------------------------------------------------------------->
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
	<!----------------------------------------�������ʹ��󣨻����ݱ��֮��ļ��------------------------------------>
	
	<!----------------------------------------������ϸ���ʼ-------------------------------------------------------------->
	
	
	 
	
	<TR>
		<TD style="width: 100%; text-align: left">
			<TABLE>	
    			<tr><td height="10px"></td></tr>	
				<TR>	
				 <TD width="10"></TD>
					
					<TD  >									
						<div width="100%" height="15">
						 out.print(" <input id = 'alarmType1'  type='radio' name='alarmType' value='1' "checkd" />����;
                                       out.print(" <input id = 'alarmType2'  type='radio' name='alarmType' value="+2+" "+request.getAttribute("check1")+" />"+"�澯"); 

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
					<a class="abtn2" href="#" onclick="javascript:returnCurrentNode()" ><span><img align = "left" valign = "center" src="${pageContext.request.contextPath}/images/green/tool/save.png" alt="" />ȷ&nbsp;&nbsp;��</span></a>
				
					<a class="aBtn2" href="#" onclick="javascript:returnClose()"><span><img align = "left" valign = "center" src="${pageContext.request.contextPath}/images/green/tool/back.png" alt="" />ȡ&nbsp;&nbsp;��</span></a>
					
				</td>
			</tr>
		</table>
		</TD>
	</TR>
	
	

	
</html:form>
</BODY>
</html>