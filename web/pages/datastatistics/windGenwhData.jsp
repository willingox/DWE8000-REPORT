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
<title>"运行统计发电量"</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/popWaiting/popWaiting.css"/>
<link rel="stylesheet" type="text/css" href ="${pageContext.request.contextPath}/ecside/css/ecside_style.css"/>  
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui-v2.1.7/layui/css/layui.css" />   
<script type="text/javascript" src="${pageContext.request.contextPath}/ecside/js/ecside.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ecside/js/ecside_msg_utf8_cn.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ecside/js/ecside_msg_gbk_cn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/popWaiting/ymPrompt.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/popWaiting/popWaiting.js"></script>
<script src="${pageContext.request.contextPath}/scripts/echarts/build/dist/echarts.js"></script>
        
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/layui-v2.1.7/layui.js"></script>
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
	
  	<script> 

	
	
	//跳到编辑页面
	function toEdit(stationId, forcasttime, savetime, method,forcastp,repairp) {
		document.shortForMtDataForm.action = '${pageContext.request.contextPath}/shortforcast/shortForMtData.do?method=toEdit';
		document.shortForMtDataForm.stationId.value = stationId;
		document.shortForMtDataForm.forcasttimeDisp.value = forcasttime;
		document.shortForMtDataForm.savetimeDisp.value = savetime;
		document.shortForMtDataForm.methodl.value = method;
		document.shortForMtDataForm.forcastp.value = forcastp;
		document.shortForMtDataForm.repairp.value = repairp;
		document.shortForMtDataForm.submit();
	}
	
	//以弹出框的形式弹出编辑页面
	function toEditNew(equipId,startDateDisp,endDateDisp) {
           // var startDateDisp = document.windGenwhDataForm.startDateDisp.value;
            //var endDateDisp = document.windGenwhDataForm.endDateDisp.value;
		var obj = new Object();
		layui.use('layer', function(){
		  var layer = layui.layer;
		  
		//单个使用
		layer.open({
		  type: 2, 
		  title: ['发电量细节', 'font-size:18px;'],
		  skin: 'demo-class',
		  shade: 0,
		  content: '${pageContext.request.contextPath}/datastatistics/windGenwhData.do?method=showDetailed&equipId='+equipId+'&startDateDisp='+
        		startDateDisp+'&endDateDisp='+endDateDisp,
		  area: ['650px', '500px'],
		  offset: ['100px', '500px'],
		});

		//全局使用。即所有弹出层都默认采用，但是单个配置skin的优先级更高
		layer.config({
		  skin: 'demo-class'
		})
		});   
            //window.showModalDialog('${pageContext.request.contextPath}/datastatistics/windGenwhData.do?method=showDetailed&equipId='+equipId+'&startDateDisp='+
        		//startDateDisp+'&endDateDisp='+endDateDisp,obj,
        		//"dialogWidth=550px;dialogHeight=400px");
	}
	
  	function query() {

	
		var msg = [];
		if (document.windGenwhDataForm.startDateDisp.value == '') 
			msg.push('起始日期');
		
		if (document.windGenwhDataForm.endDateDisp.value == '') 
			msg.push('终止日期');
	
		if(document.windGenwhDataForm.endDateDisp.value < document.windGenwhDataForm.startDateDisp.value)
			msg.push('正确时间');
		
		if (msg.length > 0) {
			alert('请选择 ' + msg.join('，') + '!');
			return;
		}
		
		windGenwhDataForm.method.value = 'show';
		windGenwhDataForm.submit();
		forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
		popWaiting('正在查询');
	}

</script> 
  
 
</HEAD>
  
  <body>
   <html:form action="windGenwhData" method="post">
	<html:hidden property="method" styleId="method" value="show" />             
	
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
						<html:text  property="startDateDisp" styleId="startDateId" styleClass="dateInput" size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowWeek:true,isShowClear:true,readOnly:true,firstDayOfWeek:1,maxDate:'2022-12-28'})" title="点击选择起始日期" />
					</TD>				
				     <TD width="10"></TD>
					<TD>结束日期      </TD>
					<TD id="Select" >										
						<html:text  property="endDateDisp" styleId="startDateId" styleClass="dateInput" size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowWeek:true,isShowClear:true,readOnly:true,firstDayOfWeek:1,maxDate:'2022-12-28 23:59:59'})" title="点击选择结束日期" />
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
	
	<TR>

	<td valign="top" align="left">
	<table>
		<div id="main" ></div>
	
	</table>
	</td>
	 </TR>

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
					
					items="resultg"
					var="bean"
					action="${pageContext.request.contextPath}/datastatistics/windGenwhData.do?method=show"
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
                  	rowsDisplayed="100"
                    useAjax="false"  
                    autoIncludeParameters="true">                    	 

					<ec:row highlightRow="true">
					  <ec:column   property="name" title="风机名称"  headerClass="ecTableHead" styleClass="ecTableBody"  width="120" style="text-align:center"/>
					  <ec:column   property="totalGenwh" title="发电量(MWh)" cell="number" format="0.000" headerClass="ecTableHead" styleClass="ecTableBody"  width="120" style="text-align:center"/>
					  <ec:column   property="hour" title="发电时间(h)" cell="number" format="0.000" headerClass="ecTableHead" styleClass="ecTableBody"  width="120" style="text-align:center"/>
					  <ec:column alias="edit" title="选项" viewsAllowed="html,compact"
											sortable="false" headerClass="ecTableHead"
											styleClass="ecTableBody" width="120" style="text-align:center;color:#4169E1">
											<a href="javascript:toEditNew('${bean.strId}','${windGenwhDataForm.startDateDisp}','${windGenwhDataForm.endDateDisp}')">显示细节</a>
										</ec:column>
					</ec:row>
					<ec:extendrow>
					<tr>
                               <td class="ecTableBody" style="text-align:center"></td>
					 <td class="ecTableBody" style="width:120px;text-align:center;color:#339900;font-weight:bold">合计发电量： ${genSum}</td>
					 <td class="ecTableBody" style="width:120px;text-align:center;color:#339900;font-weight:bold">合计发电时间： ${allRunTime}</td>
					 <td class="ecTableBody" style="text-align:center"></td>	
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
