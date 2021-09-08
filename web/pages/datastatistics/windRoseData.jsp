<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1   
response.setHeader("Pragma","no-cache"); //HTTP 1.0   
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server 
%>
<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/ecside.tld" prefix="ec"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>风况统计</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/popWaiting/popWaiting.css"/>
<link rel="stylesheet" type="text/css" href ="${pageContext.request.contextPath}/ecside/css/ecside_style.css"/>
    <link rel="stylesheet" href="demos.css" type="text/css" media="screen" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/animations.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ModalDialog.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/website.css" />
  
   <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/libraries/RGraph.common.dynamic.js" ></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/libraries/RGraph.common.key.js" ></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/libraries/RGraph.drawing.rect.js" ></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/libraries/RGraph.common.core.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/libraries/RGraph.common.effects.js" ></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/libraries/RGraph.rose.js"  ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ecside/js/prototype_mini.js"></script>     
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
			query();
		} 
	 function showTree() {
	 		//alert(parent);
			if (parent) {
				/**树参数初始化********************************/
				var treeFlType="301";//暂时没用
				var treeType="windRoseData"; //树类型
				var treeDepth="2"; //树深度
				var treeMode="radio"; //树选择模式(单选radio、多选check)
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
		if (document.windRoseDataForm.startDateDisp.value == '') 
			msg.push('起始日期');
		if (document.windRoseDataForm.endDateDisp.value == '') 
			msg.push('终止日期');
		
		  var data = parent.getEuTreeSelected();
		  
	   
    	  
    		  windRoseDataForm.id.value = data.substring(data.lastIndexOf("-")+1,data.length);
           
		  if(windRoseDataForm.id.value<=0)
		  {
		  alert('请选择风机!');
			return;
		  }
		
		if (msg.length > 0) {
			alert('请选择 ' + msg.join('，') + '!');
			return;
		}

		 windRoseDataForm.method.value = 'show';
             windRoseDataForm.submit();
             forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
             popWaiting('正在查询时数据...');
            
	}
 </script> 	
  	
 <script>
        window.onload = function ()
        { 
             var data = [[${result5.avgWindVelvalDu}],[${result4.avgWindVelvalDu}],[${result3.avgWindVelvalDu}],[${result2.avgWindVelvalDu}],[${result1.avgWindVelvalDu}],[${result16.avgWindVelvalDu}],[${result15.avgWindVelvalDu}],[${result14.avgWindVelvalDu}],[${result13.avgWindVelvalDu}],[${result12.avgWindVelvalDu}],[${result11.avgWindVelvalDu}],[${result10.avgWindVelvalDu}],[${result9.avgWindVelvalDu}],[${result8.avgWindVelvalDu}],[${result7.avgWindVelvalDu}],[${result6.avgWindVelvalDu}]]; 
            var rose = new RGraph.Rose('cvs', data);
            rose.Set('background.grid.spokes.overhang', 0);
            rose.Set('background.grid', true);
            rose.Set('labels', ['N','','','','','','','','','','','','','E','','','','','','','','','','','','','S','','','','','','','','','','','','','','W','','','','','','','','','','','','','']);
            rose.Set('key', ['平均风速']);
            rose.Set('key.halign','left');
            rose.Set('key.position.x',20);
            rose.Set('colors', ['Chocolate']);
            rose.Set('angles.start', -(HALFPI/8));
            rose.Set('margin', 3);
            rose.Set('gutter.left', 90);   //距离左边位置 
            rose.Set('labels.axes', 'n');
            rose.Draw();  
            RGraph.Effects.Rose.RoundRobin(rose);
            var data1 = [[${result5.frequency1}],[${result4.frequency1}],[${result3.frequency1}],[${result2.frequency1}],[${result1.frequency1}],[${result16.frequency1}],[${result15.frequency1}],[${result14.frequency1}],[${result13.frequency1}],[${result12.frequency1}],[${result11.frequency1}],[${result10.frequency1}],[${result9.frequency1}],[${result8.frequency1}],[${result7.frequency1}],[${result6.frequency1}]]; 
            var rose1 = new RGraph.Rose('cvs1', data1);
            rose1.Set('background.grid.spokes.overhang', 0);
            rose1.Set('background.grid', true);
            rose.Set('labels', ['N','','','','','','','','','','','E','','','','','','','','','','','S','','','','','','','','','','','','W','','','','','','','','','','','']);
            rose1.Set('key', ['风向频率']);
            rose1.Set('key.halign','left');
            rose1.Set('key.position.x',20);
            rose1.Set('colors', ['Chocolate']);
            rose1.Set('angles.start', -(HALFPI/8));
            rose1.Set('margin', 3);
            rose1.Set('gutter.left', 90);   //距离左边位置 
            rose1.Set('labels.axes', 'n');
            rose1.Draw();  
            RGraph.Effects.Rose.RoundRobin(rose1);
            var data2 = [[${result5.avgWindVelval}],[${result4.avgWindVelval}],[${result3.avgWindVelval}],[${result2.avgWindVelval}],[${result1.avgWindVelval}],[${result16.avgWindVelval}],[${result15.avgWindVelval}],[${result14.avgWindVelval}],[${result13.avgWindVelval}],[${result12.avgWindVelval}],[${result11.avgWindVelval}],[${result10.avgWindVelval}],[${result9.avgWindVelval}],[${result8.avgWindVelval}],[${result7.avgWindVelval}],[${result6.avgWindVelval}]]; 
            var rose2 = new RGraph.Rose('cvs2', data2);
            rose2.Set('background.grid.spokes.overhang', 0);
            rose2.Set('background.grid', true);
            rose.Set('labels', ['N','','','','','','','','','','','E','','','','','','','','','','','S','','','','','','','','','','','','W','','','','','','','','','','','']);
            rose2.Set('key', ['风能']);
            rose2.Set('key.halign','left');
            rose2.Set('key.position.x',20);
            rose2.Set('colors', ['Chocolate']);
            rose2.Set('angles.start', -(HALFPI/8));
            rose2.Set('margin', 3);
            rose2.Set('gutter.left', 90);   //距离左边位置 
            rose2.Set('labels.axes', 'n');
            rose2.Draw();  
            RGraph.Effects.Rose.RoundRobin(rose2);      
        }
    </script>
</head>
<!-- BODY style="overflow-y: hidden" -->
<BODY>
 <!--如有页面需要-->
	<html:form action="windRoseData" method="post">
	<html:hidden property="method" styleId="method" value="show" />
	<html:hidden property="id"  />
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
		
	
	<TR>
		<TD style="width: 100%; text-align: left">
			<TABLE>	
    			<tr><td height="10px"></td></tr>	
				<TR>	
				      <TD width="10"></TD>
					<TD>起始日期      </TD>
					<TD id="Select" >										
						<html:text  property="startDateDisp" styleId="startDateId" styleClass="dateInput" size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowWeek:true,isShowClear:true,readOnly:true,firstDayOfWeek:1,maxDate:'2022-12-28 00:00:00'})" title="点击选择起始日期" />
					</TD>
					<TD width="10"></TD>
					<TD>终止日期      </TD>
					<TD>										
						<html:text property="endDateDisp" styleId="endDateId" styleClass="dateInput" size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowWeek:true,isShowClear:true,readOnly:true,firstDayOfWeek:1,maxDate:'2022-12-28 00:00:00'})" title="点击选择终止日期" />
					</TD>
					
				    <TD width="30"></TD>
					<TD valign="middle"; align="center" width="76" background="../images/green/tool/btn_bg.gif" background-repeat="no repeat" margin-left="10px">			
						<a class="abtn3"  href="javascript:query()"><span ><img  padding-left="10px" src="../images/green/tool/search.png" alt="" />&nbsp;&nbsp;查&nbsp;&nbsp;询</span></a>
					   
					</TD> 
				</TR>
    							
			</TABLE>
		</TD>
	</tr>
 
	<!----------------------------------------工具栏和错误（或数据表格）之间的间隔------------------------------------>
	
</html:form>
	
	<!----------------------------------------图形开始--<div style="width:100%; height:5%;align:center; color:#ff3300;font-weight:bold;padding-left:0px 0px; position:relative; left:80px; overflow: hidden;"> 当天</div>				
			 	  ------------------------------------------------------------>
	
		
		
		 <tr>
		 <td id="chartTD0" width="100%" height="100%" valign="middle">
		<table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" >
		<TD width="4%"></TD>
		<td  width="30%" height="100%" valign="middle" align="center">
		 <canvas id="cvs" width="400" height="480">[No canvas support]</canvas>	
		 </td>
		 <td  width="30%" height="100%" valign="middle" align="center">
		 <canvas id="cvs1" width="400" height="480">[No canvas support]</canvas>	
		 </td>
		 
		  <td  width="30%" height="100%" valign="middle" align="center">
		 <canvas id="cvs2" width="400" height="480">[No canvas support]</canvas>	
		 </td>
		
		 <TD width="2%"></TD>
		 </table>
		 </td>
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
					action="${pageContext.request.contextPath}/datastatistics/windRoseData.do?method=show"
					retrieveRowsCallback="process"
					sortRowsCallback="process"
					filterRowsCallback="process"
					xlsFileName="WindRoseData.xls"
                              showTitle="ture"
					listWidth ="100%"
					listHeight="750"
					width="100%"
					sortable="true" 
					filterable="true" 
					showPrint="true"
					toolbarLocation="top"
                    pageSizeList="16,all"
                    rowsDisplayed="100"
                    useAjax="false"  
                    autoIncludeParameters="true">                    	 
                    	<ec:extendrow location="top">
                    	        ${top}
    					</ec:extendrow> 
					<ec:row highlightRow="true">
					
					<ec:column  property="windDirVal" title="风向区"  headerClass="ecTableHead" styleClass="ecTableBody"  width="120" style="text-align:center"/>
					 <ec:column  property="avgWindVelvalDu" title="平均风速(m/s)" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="120" style="text-align:center"/>
					  <ec:column  property="frequency1" title="风向频率(%)" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="120" style="text-align:center"/>
					  <ec:column  property="avgWindVelval" title="风能" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="120" style="text-align:center"/>
					  <ec:column  property="power" title="平均功率(kW)" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="120" style="text-align:center"/>
 					  <ec:column  property="counts" title="频次" cell="number" format="0.00" headerClass="ecTableHead" styleClass="ecTableBody"  width="120" style="text-align:center"/>
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
	document.getElementById("righttrend").style.height=window.innerHeight*0.6+"px";
	 require.config({
        paths: {
            echarts: '${pageContext.request.contextPath}/scripts/echarts/build/dist'
        },
        packages: [
            {
                name: 'BMap',
                location: '${pageContext.request.contextPath}/scripts/echarts/extension/BMap/src',
                main: 'main'
            }
        ]
    });

    require(
    [
        'echarts',
        'echarts/chart/bar',
         'echarts/chart/line'
    ],
    function (echarts) {
    var myChart = echarts.init(document.getElementById('righttrend'));
    var echartdata = ${freXml};
	
    option = {
    title : {
        text: '风频曲线图'
    },
    tooltip : {
        trigger: 'axis'
    },
    grid:{
    x:80,
    y:35,
    x2:45,
    y2:20
    },
    legend: {
        data:['频率']
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
    calculable : false,
    xAxis : [
        {
           type : 'category',

            data : (function () {
                var data = [];
               
                echartdata.forEach(function (item) {
                    data.push(item.windvelval)
                })
                return data;
            })()
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value} (%)'
            }
        }
    ],
    series : [
       {
            name:'频率',
            type:'bar',
            barWidth : 10,
            itemStyle : {  
                        normal : {  
                            color: '#6495ed' ,
                            barBorderRadius: 3,
                           	
                        } ,
                        emphasis: {barBorderRadius: 3} 
                    },
             data : (function () {
                var data = [];
               
                echartdata.forEach(function (item) {
                    data.push(item.frequency)
                })
                return data;
            })(),
            markPoint : {
                data : [
              		{type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                   
                ]
            }
        }
    ]
};
 	
 	 myChart.setOption(option); 

});
</script>
</BODY>
</html>
    
