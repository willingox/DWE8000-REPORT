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
<title>运行统计风速</title>
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
				var treeType="windSpeedData"; //树类型
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
		if (document.windSpeedDataForm.startDateDisp.value == '') 
			msg.push('起始日期');
		if (document.windSpeedDataForm.endDateDisp.value == '') 
			msg.push('终止日期');
				
		  var data = parent.getEuTreeSelected();
	
		 windSpeedDataForm.str.value = data;
		
	       
   	  	   
		  if(data=="")
		  {
		  	alert('请选择风机!');
			return;
		  }
		
		if (msg.length > 0) {
			alert('请选择 ' + msg.join('，') + '!');
			return;
		}

		 windSpeedDataForm.method.value = 'show';
             windSpeedDataForm.submit();
             forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
             popWaiting('正在查询时数据...');
	}
 </script> 	
	
  
 
</HEAD>
  
  <body>
  <TABLE style="border: 0; border-collapse: collapse; width:2000; " id="tabOutside">
 <html:form action="windSpeedData" method="post">
	<html:hidden property="method" styleId="method" value="show" />             
	<html:hidden property="str"  />
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
		 <td style="font-size:18px;color:#ffffff;background-color:rgba(255,255,255,0.1);" >  
		 <H4 style="color: #000000;"align="center">全场平均风速:${allAvgWindVelval}m/s&nbsp;&nbsp;&nbsp;全场最大风速:${allMaxData}m/s(${tableMaxTime})&nbsp;&nbsp;&nbsp;全场最小风速:${allMinData}m/s(${tableMinTime})</H4>
	 		</td> 
            </tr>	
	<tr >
	<TD style="width: 100%;text-align: left;text-valign: top;">
	<table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0">

	<td valign="top" align="left">
	<table>
		<div id="main" ></div>
	
	</table>
	</td>
	
	</table>
	</TD>
	</tr>
	
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
					action="${pageContext.request.contextPath}/datastatistics/windSpeedData.do?method=show"
					retrieveRowsCallback="process"
					sortRowsCallback="process"
					filterRowsCallback="process"
					xlsFileName="windSpeedData.xls"
                              showTitle="ture"
					listWidth ="100%"
					listHeight="100%"
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
					<ec:column  property="name" title="风机"  headerClass="ecTableHead" styleClass="ecTableBody"  width="200" style="text-align:center"/>
					<ec:column  property="avgWindVelval" title="平均风速(m/s)" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="200" style="text-align:center"/>
					<ec:column  property="maxData" title="最大风速(m/s)" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="200" style="text-align:center"/>
					<ec:column  property="maxTime" title="最大风速时刻"  headerClass="ecTableHead" styleClass="ecTableBody"  width="400" style="text-align:center"/>
					<ec:column  property="minData" title="最小风速(m/s)" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="200" style="text-align:center"/>  
					<ec:column  property="minTime" title="最小风速时刻"  headerClass="ecTableHead" styleClass="ecTableBody"  width="400" style="text-align:center"/>
                    </ec:row>
                    <ec:extendrow>
					<tr>
                        <td class="ecTableBody" style="text-align:center"></td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计平均风速： ${allAvgWindVelval}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计最大风速： ${allMaxData}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计最大风速时刻： ${tableMaxTime}</td>						
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计最小风速： ${allMinData}</td>
						<td class="ecTableBody" style="width:150px;text-align:center;color:#339900;font-weight:bold">合计最小风速时刻： ${tableMinTime}</td>					
					</tr>
					</ec:extendrow>
				
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
      text: '风机风速对比',
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
        data:['风速(m/s)'],
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
            name:'风速(m/s)',
            type:'bar',
            smooth:'true',
            symbol:'none',
            itemStyle : {  
                normal : {  
               
                   color: '#90EE90' ,                            
                   lineStyle : {                           	
                              width : 2 
                          }      	
                     } 
                  },   

            data : (function () {
                var data1 = [];
               
                echartsdata.forEach(function (item) {
                    data1.push(parseFloat(item.avgwindval))
                })
                return data1;
            })(),
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
           
        }
    ]
};
        //window.onresize = myChart.resize;
 myChart.setOption(option); 
                     

</script>	 
  </body>
</html>
