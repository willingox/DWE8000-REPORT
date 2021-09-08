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
<title>һ����ң�ⱨ��</title>
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
				var treeType="runingInfo_OneDayReportData"; //������
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
		if (document.runingInfo_OneMinuteReportDataForm.startDateDisp.value == '') 
			msg.push('��ʼ����');
		
		if (document.runingInfo_OneMinuteReportDataForm.endDateDisp.value == '') 
			msg.push('��ֹ����');
		if(document.runingInfo_OneMinuteReportDataForm.endDateDisp.value < document.runingInfo_OneMinuteReportDataForm.startDateDisp.value)
			msg.push('��ȷʱ��');	
		
		
		var check_value = '<%=(String[])request.getAttribute("check_value") %>';
		alert(check_value);
		  var data = parent.getEuTreeSelected();
		 
		  runingInfo_OneMinuteReportDataForm.str.value = data;
		
	       
   	  	   
		  if(data=="")
		  {
		  
			msg.push('���');
		  }
		
		if (msg.length > 0) {
			alert('��ѡ�� ' + msg.join('��') + '!');
			return;
		}
	
		runingInfo_OneMinuteReportDataForm.method.value = 'show';
		runingInfo_OneMinuteReportDataForm.submit();
		forbidSubmit();	// ��ֹ���а�ť�����ӵȣ�λ��{ebizapp.js}
		popWaiting('���ڲ�ѯ');
	}
	
	function select() {
        var msg = [];
		if (document.runingInfo_OneDayReportDataForm.startDateDisp.value == '') 
			msg.push('��ʼ����');
		
		if (document.runingInfo_OneDayReportDataForm.endDateDisp.value == '') 
			msg.push('��ֹ����');
		if(document.runingInfo_OneDayReportDataForm.endDateDisp.value < document.runingInfo_OneDayReportDataForm.startDateDisp.value)
			msg.push('��ȷʱ��');	
		
		
		  var data = parent.getEuTreeSelected();
		 
		  runingInfo_OneDayReportDataForm.str.value = data;
		
	       
   	  	   
		  if(data=="")
		  {
		  
			msg.push('���');
		  }
		
		if (msg.length > 0) {
			alert('��ѡ�� ' + msg.join('��') + '!');
			return;
		}
	  if (window.showModalDialog!=null)//IE�ж�
           {
          window.showModalDialog('${pageContext.request.contextPath}/datastatistics/runingInfo_OneDayReportData.do?method=select&data='+data, window, 'dialogHeight:500px;dialogWidth:400px;help:no;status:no');	
          }
      
          else{
          window.open('${pageContext.request.contextPath}/datastatistics/runingInfo_OneDayReportData.do?method=select&data='+data,'','width=400,height=500,status=no,modal=yes');
          }	
          //window.showModalDialog('${pageContext.request.contextPath}/datastatistics/runingInfo_OneDayReportData.do?method=select&data='+data, window, 'dialogHeight:500px;dialogWidth:400px;help:no;status:no');	
	 
	
	}
 </script> 	

  </head>
  
  <body>
       <html:form action="runingInfo_OneDayReportData" method="post">
	<html:hidden property="method" styleId="method" value="show" />             
	<html:hidden property="str"  />
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
    			<tr><td height="5px"></td></tr>
				<TR>	
				    <TD width="5"></TD>
					<TD valign="bottom"; align="center" height="23" width="88" background="../images/green/tool/level3_2.png"  background-repeat="no repeat" margin-left="10px">
					<a class="abtnx" href="${pageContext.request.contextPath}/datastatistics/runingInfo_OneMinuteReportData.do"><span><font size="2" color="#302422">��Ҫ������Ϣ</font></span></a>
					</TD>
					<TD valign="bottom"; align="center" height="23" width="88" background="../images/green/tool/level3_2.png">
					<a  class="abtnx" href="${pageContext.request.contextPath}/datastatistics/runingInfo_TenMinuteReportData.do"><span><font size="2" color="#302422">������Ϣ</font></span></a>
					</TD>
					<TD valign="bottom"; align="center" height="23" width="88" background="../images/green/tool/level3_1.png">
					<a  class="abtnx" href="${pageContext.request.contextPath}/datastatistics/runingInfo_OneDayReportData.do"><span><font size="2" color="#302422">ͳ����Ϣ</font></span></a>
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
					<TD>��ʼ����      </TD>
					<TD id="Select" >										
						<html:text  property="startDateDisp" styleId="startDateId" styleClass="dateInput" size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowWeek:true,isShowClear:true,readOnly:true,firstDayOfWeek:1,maxDate:'2022-12-28'})" title="���ѡ����ʼ����" />
					</TD>				
				     <TD width="10"></TD>
					<TD>��������      </TD>
					<TD id="Select" >										
						<html:text  property="endDateDisp" styleId="startDateId" styleClass="dateInput" size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowWeek:true,isShowClear:true,readOnly:true,firstDayOfWeek:1,maxDate:'2022-12-28'})" title="���ѡ����ʼ����" />
					</TD>				
				    
				    
			
			 <TD width="30"></TD>
		<TD valign="middle"; align="center" width="76" background="../images/green/tool/btn_bg.gif" background-repeat="no repeat" margin-left="10px">			
			<a class="abtn3" href="#" onclick="select()"  ><span ><img  padding-left="10px" src="../images/green/tool/dj.png" alt="" />&nbspѡ��ң��</span></a>
		   
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
					
					items="resulttu"
					var="bean"
					action="${pageContext.request.contextPath}/datastatistics/runingInfo_OneDayReportData.do?method=show"
					retrieveRowsCallback="limit"
					sortRowsCallback="process"
					filterRowsCallback="process"
					csvFileName="runingInfo_OneDayReportData.csv"
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
					<ec:column  headerSpan="0" property="saveTime" title="ʱ��" cell="date" format="yyyy-MM-dd HH:mm" headerClass="ecTableHead" styleClass="ecTableBody"  width="200" style="text-align:center"/>
					
					<ec:column  headerSpan="0" property="name" title="����" headerClass="ecTableHead" styleClass="ecTableBody"  width="200" style="text-align:center"/>
					
       
					 <%
					           String[] check_value = (String[])request.getAttribute("check_value"); 
					            String a;
					            for(int i =0; i<check_value.length; i++)
								{
									a="calValue"+String.valueOf(i);
									%>
						<ec:column headerSpan="0" property="<%=a%>" title="<%=check_value[i] %>" cell="number" format="0.0" headerClass="ecTableHead" styleClass="ecTableBody"  width="200" style="text-align:center"/>
						
					<%}%>	
					
					
					
					
					
					
					
					
					  
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
