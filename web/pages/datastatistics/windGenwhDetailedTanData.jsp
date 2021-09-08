<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1   
response.setHeader("Pragma","no-cache"); //HTTP 1.0   
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
response.setHeader("refresh" , "600" ); 
%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/ecside.tld" prefix="ec"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>风机发电量细节显示</title>
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
	
  	
  
 
</HEAD>
  
  <body>
   <html:form action="windGenwhData" method="post">
	<html:hidden property="method" styleId="method" value="showDetailed" />             
	
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
					action="${pageContext.request.contextPath}/datastatistics/windGenwhData.do?method=showDetailed"
					retrieveRowsCallback="process"
					sortRowsCallback="process"
					filterRowsCallback="process"
					xlsFileName="windGenwhData.xls"
                              showTitle="ture"
					listWidth ="100%"
					listHeight="400"
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
					  <ec:column   property="saveTime" title="时间"  headerClass="ecTableHead" styleClass="ecTableBody"  width="200" style="text-align:center"/>
					  <ec:column   property="todayGenwh" title="发电量(MWh)" cell="number" format="0.000" headerClass="ecTableHead" styleClass="ecTableBody"  width="120" style="text-align:center"/>
					  <ec:column   property="avgWindVelval" title="平均风速" cell="number" format="0.000" headerClass="ecTableHead" styleClass="ecTableBody"  width="120" style="text-align:center"/>
					  
					
					
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
//获取窗口的高度，并设置“main”的高度
document.getElementById("main").style.height=window.innerHeight*0.5+"px";
// 路径配置

    var myChart = echarts.init(document.getElementById('main'));
    
  	var echartsdata = ${barXml};
    
    option = {
   backgroundColor: '#f9ffff',
     title : {
      text: '风机发电量对比',
      textStyle: {
	fontFamily: 'Arial, Verdana, sans...',
	fontSize: 15,
	color:'#000000',
	fontStyle: 'normal',
	fontWeight: 'bolder',
	 x:'center',
	 y:'top'
	 }
},
    tooltip : {
        trigger: 'axis'
    },
    toolbox: {
        show : false,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : false,
     grid: {
    x:50,
    y:50,
    x2:25,
    y2:35
    },
    legend: {
        data:['发电量'],
		x:'right'
       
    },
    xAxis : [
        {
            type : 'category',
            axisLabel: {
     		 show : true,
			 textStyle : {
				color: '#000000'
			 }
			},
     		data : (function () {
                var data1 = [];
               
                echartsdata.forEach(function (item) {
                    data1.push(item.name)
                })
                return data1;
            })()
        }

    ],
    yAxis : [
        {
            type : 'value',
            
            axisLabel: {
      formatter: '{value}',
      show : true,
	textStyle : {
	color: '#000000'
	}
	}
           
        }
    ],
    series : [    
        {
            name:'发电量',
            type:'bar',
            itemStyle : {  
                        normal : {  
                            color: '#76EEC6' ,
                            barBorderRadius: 3,
                           	
                        } ,
                        emphasis: {barBorderRadius: 3} 
                    },
            data : (function () {
                var data1 = [];
               
                echartsdata.forEach(function (item) {
                    data1.push(parseFloat(item.totalgenwh))
                })
                return data1;
            })()
        }
    ]
};
        //window.onresize = myChart.resize;
 myChart.setOption(option); 
                     

</script>
	
  </body>
</html>
