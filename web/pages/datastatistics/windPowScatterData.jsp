<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1   
response.setHeader("Pragma","no-cache"); //HTTP 1.0   
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server 

%>
<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/ecside.tld" prefix="ec"%>
<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>����Դ����ɢ��ͼ</title>
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
        <script src="${pageContext.request.contextPath}/scripts/echarts/esl.js"></script> <style type="text/css">
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
			query();
		} 
	 function showTree() {
	 		//alert(parent);
			if (parent) {
				/**��������ʼ��********************************/
				var treeFlType="301";//��ʱû��
				var treeType="windPowerScatterData"; //������
				var treeDepth="2"; //�����
				var treeMode="radio"; //��ѡ��ģʽ(��ѡradio����ѡcheck)
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
		if (document.windPowScatterDataForm.startDateDisp.value == '') 
			msg.push('��ʼ����');
		
		if (document.windPowScatterDataForm.endDateDisp.value == '') 
			msg.push('��ֹ����');
		
		if(document.windPowScatterDataForm.endDateDisp.value < document.windPowScatterDataForm.startDateDisp.value)
			msg.push('��ȷʱ��');
	
		if (msg.length > 0) {
			alert('��ѡ�� ' + msg.join('��') + '!');
			return;
		}
		var data = parent.getEuTreeSelected();
		  
	   
    	  
    		  windPowScatterDataForm.equipId.value = data.substring(data.lastIndexOf("-")+1,data.length);
           
		  if(windPowScatterDataForm.equipId.value<=0)
		  {
		  alert('��ѡ����!');
			return;
		  }
		windPowScatterDataForm.method.value = 'show';
		windPowScatterDataForm.submit();
		forbidSubmit();	// ��ֹ���а�ť�����ӵȣ�λ��{ebizapp.js}
		popWaiting('���ڲ�ѯ����Ĺ���ɢ��ͼ');
	}
	
 
//-->
</script>
</HEAD>
<BODY scroll="auto">
<!--����ҳ����Ҫ-->
	<html:form action="windPowScatterData" method="post" >
	<html:hidden property="method" value="show" />
	<html:hidden property="equipId"  />
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
	<TR>
		<TD style="width: 100%; text-align: left">
			<TABLE>	
    			<tr><td height="10px"></td></tr>	
				<TR>
					
				    <TD width="10"></TD>
					<TD>��ʼ����      </TD>
					<TD>										
						<html:text property="startDateDisp" styleId="startDateId" styleClass="dateInput" size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowWeek:true,isShowClear:true,readOnly:true,firstDayOfWeek:1,maxDate:'2020-12-28'})" title="���ѡ����ʼ����" />
					</TD>
					<TD width="10"></TD>
					<TD>��ֹ����      </TD>
					<TD>										
						<html:text property="endDateDisp" styleId="endDateId" styleClass="dateInput" size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowWeek:true,isShowClear:true,readOnly:true,firstDayOfWeek:1,maxDate:'2022-12-28'})" title="���ѡ����ֹ����" />
					</TD>
					
					
				    <TD width="30"></TD>
					<TD valign="middle"; align="center" width="76" background="../images/green/tool/btn_bg.gif" background-repeat="no repeat" margin-left="10px">			
						<a class="abtn3"  href="javascript:query()"><span ><img  padding-left="10px" src="../images/green/tool/search.png" alt="" />&nbsp;&nbsp;��&nbsp;&nbsp;ѯ</span></a>
					   
					</TD> 		
				    
				</TR>
    							
			</TABLE>
		</TD>
	</tr>
	<!----------------------------------------�������ʹ��󣨻����ݱ���֮��ļ��------------------------------------>
	
	 <TR>
	 	<TD style="height:5%;">
		</TD>
	 </TR>
	</html:form>
	<!----------------------------------------ͼ�ο�ʼ--<div style="width:100%; height:5%;align:center; color:#ff3300;font-weight:bold;padding-left:0px 0px; position:relative; left:80px; overflow: hidden;"> ����</div>				
			 	  ------------------------------------------------------------>		
			 	  
	 	  <tr>
	 	  <td  valign="top" align="left">
	 	  <table>
	 	   <div id="chartbox_powerscatter" style="width:100%; height:100%;"></div>
	 	  </table>
	 	  </td>
	 	  </tr>
 		<tr>
		<td id="chartTD" width="100%" height="100%" valign="top">
		<table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" >
			<tr>
			 
			  <TD id="chartTD1"   width="30%" height="100%" valign="top" align="left">
			  <div style="width: 100%; height: 100%; overflow: hidden;">
			  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			  
				<td valign="top" align="center" width="50%" height="100%">
				<ec:table 					
					items="result"
					var="bean"
					action="${pageContext.request.contextPath}/datastatistics/windPowScatterData.do?method=show"
					retrieveRowsCallback="process"
					sortRowsCallback="process"
					filterRowsCallback="process"
					xlsFileName="windPowScatterData.xls"
                              showTitle="ture"
					listWidth ="100%"
					listHeight="400"
					width="100%"
					sortable="true" 
					filterable="true" 
					showPrint="true"
					toolbarLocation="top"
                    pageSizeList="all"
                    rowsDisplayed="100"
                    useAjax="false"  
                    autoIncludeParameters="true">                    	 
                    	<ec:extendrow location="top">
                    	        ${top}
    					</ec:extendrow> 
					<ec:row highlightRow="true">
					<ec:column  property="windVelval1" title="����" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="120" style="text-align:center;"/>
					
					 <ec:column  property="power" title="����(kW)" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="120" style="text-align:center"/>
					
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

	<script type="text/javascript">
	document.getElementById("chartbox_powerscatter").style.height=window.innerHeight*0.5+"px";
	document.getElementById("chartbox_powerscatter").style.width=window.innerWidth*0.98+"px";
	
	    var myChart = echarts.init(document.getElementById('chartbox_powerscatter'));
	    
	    var echartdata=${powersXml};

	    option = {
	    backgroundColor: '#f9ffff',
	    title : {
	        text: '�繦��ɢ��ͼ'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    grid:{
	    x:60,
	    y:35,
	    x2:45,
	    y2:20
	    },
	    legend: {
	        data:['����(KW)'],
	        x:'right'
	    },
	    toolbox: {
	        show : false,
	        feature : {
	            mark : {show: false},
	            dataView : {show: false, readOnly: true},
	            magicType : {show: false, type: ['line', 'bar', 'stack', 'tiled']},
	            restore : {show: false},
	            saveAsImage : {show: false}
	        }
	    },
	    calculable : false,
	    xAxis : [
	             {
	                 type : 'value',
	                 scale:true,
	                 axisLabel : {
	                     formatter: '{value} m/s'
	                 }
	             }
	         ],
	         yAxis : [
	                  {
	                      type : 'value',
	                      scale:true,
	                      axisLabel : {
	                          formatter: '{value} kW'
	                      }
	                  }
	              ],
	    
	    series : [
	        {
	            name:'����(KW)',
	            type:'scatter',
	            itemStyle : {  
                    normal : {  
                        color: '#56d859' ,
                    } ,
                },
	            data: echartdata,
	            
	        }
	    ]
	};
	 	
	 	 myChart.setOption(option); 

	
	</script>

</BODY>
</html>

	
	
	