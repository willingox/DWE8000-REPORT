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
<title>月运行信息对比</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
 		<script src="${pageContext.request.contextPath}/scripts/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/scripts/echarts/build/dist/echarts.js"></script>
        <script src="${pageContext.request.contextPath}/scripts/echarts/esl.js"></script></head>
  
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
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
<script src="${pageContext.request.contextPath}/scripts/echarts/esl.js"></script></head>

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

			hideTree();
		});
	 
	 function hideTree() {
		 parent.hideEuTree();
	 }
	</script>
	<script type="text/javascript">
		function query() {

		 	smgMonthInfoDataForm.method.value = 'show';
             smgMonthInfoDataForm.submit();
             forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
             popWaiting('正在查询时数据...');
	}
 </script> 	
</HEAD>

<BODY >
<!--如有页面需要-->
	<html:form action="smgMonthInfoData" method="post">
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
    		
	
				    <TD width="10"></TD>
					<TD style="color:#000000">选择月     </TD>
					<TD id="Select" >										
						<html:text  property="startDateDisp" styleId="startDateId" styleClass="dateInput" size="16" onclick="WdatePicker({dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: false, maxDate:'2022-12'})" title="点击选择起始日期" />
					</TD>				
				    <TD width="30"></TD>
					<TD valign="middle"; align="center" width="76" background="../images/green/tool/btn_bg.gif" background-repeat="no repeat" margin-left="10px">			
						<a class="abtn3"  href="javascript:query()"><span ><img  padding-left="10px" src="../images/green/tool/search.png" alt="" color="#ffffff"/>&nbsp;&nbsp;查&nbsp;&nbsp;询</span></a>
					   
					</TD> 
				</TR>
    							
			</TABLE>
		</TD>
	</tr>
	
	<tr>
	<TD style="width: 100%;text-align: center;">
	<table>
	<td id="chartTD1" valign="top" align="left">
	 	<div id="namegen"></div>
	 </td>
	 <td id="chartTD0" valign="top" align="right">
	 	<div id="timegen" ></div>
	 </td>
	</table>
    </TD>
    </tr>
    
    <tr>
	<TD style="width: 100%;text-align: left;text-valign: top;">
	<table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0">
	<tr>

	<div width="100%" height="15">
	  选择：<label><input  type="radio" name="showtype" value="0" checked="checked" onclick="selecttype(0)"/>竖排</label>
    <label><input  type="radio" name="showtype" value="1" onclick="selecttype(1)"/>横排</label>
	</div>

	</tr>
	<td>
	<table>
	<div id="type0" width="100%" height="100%">
	
	<iframe id="reportFramed0"  src="/WebReport/ReportServer?reportlet=/month2.cpt&date=${smgMonthInfoDataForm.startDateDisp}-01">
	
	</iframe>
	</div>
	</table>
	</td>
	
	<td>
	<table>
	<div id="type1" width="100%" height="100%">
	
	<iframe id="reportFramed1"  src="/WebReport/ReportServer?reportlet=/month_1.cpt&date=${smgMonthInfoDataForm.startDateDisp}-01">
	</iframe>
	</div>
	</table>
	</td>
	
    	</table>
    	</TD>
	 </tr>
	 
	 	 	 <script type="text/javascript">
document.getElementById("reportFramed0").style.height=window.innerHeight*0.55+"px";
document.getElementById("reportFramed0").style.width=window.innerWidth*0.98+"px";

document.getElementById("reportFramed1").style.height=window.innerHeight*0.55+"px";
document.getElementById("reportFramed1").style.width=window.innerWidth*0.98+"px";

</script>
	
	<script type="text/javascript">
	function selecttype(a){
   		for(var i=0;i<2;i++){
    		if(i==a){
    			$("#type"+i).show();
    		}else{
    			$("#type"+i).hide();
    		}
    	}

   }
</script> 

	<script type="text/javascript">
window.onload=function(){  
    selecttype(0);

}  
</script>

<script type="text/javascript">
//获取窗口的高度，并设置“main”的高度
document.getElementById("namegen").style.height=window.innerHeight*0.3+"px";
document.getElementById("namegen").style.width=window.innerWidth*0.48+"px";

    var myChart = echarts.init(document.getElementById('namegen'));

	var echartdata = ${namegenwhXml};
    option = {
    backgroundColor: '#f9ffff', 
    title : {
        text: '月发电量对比',
       	textStyle: {
		color:'#000000',
		},
    },
    tooltip : {
        trigger: 'axis'
    },
    grid:{
    x:50,
    y:35,
    x2:45,
    y2:28
    },
    legend: {
   		 textStyle: {
		color:'#000000',
		},
        data:['月发电量(MWh)']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: false},
            dataView : {show: false, readOnly: true},
            magicType : {show: false, type: ['line', 'bar']},
            restore : {show: false},
            saveAsImage : {show: false}
        }
    },
    xAxis : [
        {
            type : 'category',
            splitLine:{lineStyle:{color:'#acacac'}},
            boundaryGap : true,
            axisLabel:{textStyle:{color:'#000000'} },
            data : (function () {
                var data1 = [];
               
                echartdata.forEach(function (item) {
                    data1.push(item.name)
                })
                return data1;
            })()
        }
    ],
    yAxis : [
        {
            type : 'value',
            splitLine:{lineStyle:{color:'#acacac'}},
            axisLabel : {
                formatter: '{value}',
                textStyle:{color:'#000000'},
            }
        }
    ],
    calculable : false,
    series : [
       {
            name:'月发电量(MWh)',
            type:'bar',
            itemStyle : {  
                        normal : {  
                            color: '#2ea4c6' ,
                            barBorderRadius: 3,
                           	
                        } ,
                        emphasis: {barBorderRadius: 3} 
                    },
            data : (function () {
                var data = [];
               
                echartdata.forEach(function (item) {
                    data.push(item.todayGenwh)
                })
                return data;
            })(),
          
        }
    ]
};
 	
 	 myChart.setOption(option); 

</script>

<script type="text/javascript">
document.getElementById("timegen").style.height=window.innerHeight*0.3+"px";
document.getElementById("timegen").style.width=window.innerWidth*0.48+"px";
 
    var myChart = echarts.init(document.getElementById('timegen'));
    var data = [];
    var echartdata = ${timegenwhXml};
    option = {
    backgroundColor: '#f9ffff', 
    title : {
        text: '日发电量',
        textStyle: {
		color:'#000000',
		},
    },
    tooltip : {
        trigger: 'axis'
    },
    grid:{
    x:60,
    y:35,
    x2:45,
    y2:28
    },
    legend: {
    textStyle: {
		color:'#000000',
		},
        data:['日发电量(MWh)']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: false},
            dataView : {show: false, readOnly: true},
            magicType : {show: false, type: ['line', 'bar', 'stack', 'tiled']},
            restore : {show: false},
            saveAsImage : {show: false}
        }
    },

    xAxis : [
        {
            type : 'category',
            boundaryGap : true,
            splitLine:{lineStyle:{color:'#acacac'}},
            axisLabel:{textStyle:{color:'#000000'} },
            data : (function () {
                var data1 = [];
               
                echartdata.forEach(function (item) {
                    data1.push(item.time)
                })
                return data1;
            })()
        }
    ],
    yAxis : [
        {
            type : 'value',
            name : '',
            splitLine:{lineStyle:{color:'#acacac'}},
            axisLabel : {
                formatter: '{value}',
                textStyle:{color:'#000000'} 
            } 
        }
      
    ],
    calculable : false,
    series : [
        {
            name:'日发电量(MWh)',
            type:'bar',

            itemStyle : {  
                  normal : {  
                     areaStyle: {type: 'default'},
                     color: '#c7a2de' ,                            
                     lineStyle : {                           	
                                width : 1  
                            }      	
                       } 
                    },   
          data : (function () {
                var data1 = [];
               
                echartdata.forEach(function (item) {
                    data1.push(item.todayGenwh)
                })
                return data1;
            })()
           
        }
    ]
};
 
 	 myChart.setOption(option); 


</script>

</html:form>
  </body>
</html>

