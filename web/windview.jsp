<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1   
response.setHeader("Pragma","no-cache"); //HTTP 1.0   
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
response.setHeader("refresh" , "900" ); 
%>
<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="pages/common/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/ecside.tld" prefix="ec"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>风场总览</title>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/FusionCharts/js/FusionCharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/FusionCharts/js/FusionChartsExportComponent.js"></script>
   <script src="${pageContext.request.contextPath}/scripts/echarts/build/dist/echarts.js"></script>
        <script src="${pageContext.request.contextPath}/scripts/echarts/esl.js"></script>
<script type="text/javascript" name="baidu-tc-cerfication" data-appid="7418552" src="http://apps.bdimg.com/cloudaapi/lightapp.js"></script>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=yWC7wAidp74u0yMyAWDwMk4C"></script>
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
	
  
</HEAD>
<BODY>
<!--如有页面需要-->
	
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
	 <tr>
	 <td  style="width: 25%;" valign="top">
	 
			  <table > 
			  <tr style="background-color: #14306f;">&nbsp;选择风场

				<html:select property="equipId" value="${windRoseDataForm.equipId}" styleClass="formDataFormSelect" style="width:300px;border-width:1;">
                 <html:option value=""></html:option>
                 <logic:present name="generator">
                 <html:options collection="generator" labelProperty="name" property="id"/>
                 </logic:present>
                 </html:select><span class="required">*</span>

			  </tr>
				&nbsp;
             <TR>&nbsp;
	 		
	 	<div id="main" style="height:25%; width:100%; "></div>
	 	   
	 	 </TR>
	 	 
	 	<TR>&nbsp;	
			       
		<div id="main1" style="height:25%; width:100%; "></div>
	 </TR>
       <TR>	&nbsp;				       
                 <div id="main2" style="height:75%; width:100%; "></div>
	 </TR>
    
</table>
</td>	
		<td style="width: 1px;height:95% background-color: #2E8A73;"></td>
	
	
		 <td style="width: 63%;" valign="top">
		 <table > 
		 <tr valign="top">
				  <div id="allmap" style="height: 91%; "  ></div>
				  </tr>
				  		  <tr>
		<td style="height: 0.5px; background-color: #ffffff;"></td>
	</tr>
				  <tr>
		<td style="height: 1px; background-color: #2E8A73;"></td>
	</tr>
				  <tr >
					<TD valign="middle"; align="left" width="1000" height="65" bgcolor="#14306f">
							
					<H4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="color: #ffffff;" padding-left="15px"><span ><img   src="images/green/tool/zjrl.png" alt="" />&nbsp;总装机容量：&nbsp;300&nbsp;MW</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span ><img   src="images/green/tool/fc.png" alt="" />&nbsp;总装机台数：&nbsp;7&nbsp;台</span>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span ><img   src="images/green/tool/ts.png" alt="" />&nbsp;运行容量：&nbsp;200&nbsp;MW</span>
					</a></H4>	
					   
					</TD> 
				  </tr>
				   </table>
			  </td>
			  <td   style="width: 12%;" valign="top">
			  <table > 

	
             <TR>
             
	 	<TD style="height:2%;">
	 	</TD>
	 	 </TR>
	 	<TR>
			       
		<div id="main4" style="height:25%; width:100%; "></div>
		
	 </TR>
         <TR>
	 	<TD style="height:10;">
	 	</TD>
	 	 </TR>
	 	<TR>
	 		<TD style="height:540;width: 250;background-color: #14306f;color:#ffffff" valign="top">&nbsp;
			<H4><a style="color: #ffffff;" padding-left="15px"><span ><img   src="images/green/tool/zr.png" alt="" />昨日发电量：</span></a></H4>
			<H2>&nbsp;&nbsp;&nbsp;<a style="color: #00ff00;" >44&nbsp;MWh</a></H2>
			<H4><a style="color: #ffffff;" padding-left="15px"><span ><img   src="images/green/tool/by.png" alt="" />本月发电量：</span></a></H4>
			<H2>&nbsp;&nbsp;&nbsp;<a style="color: #00ff00;" >500&nbsp;MWh</a></H2>
			<H4><a style="color: #ffffff;" padding-left="15px"><span ><img   src="images/green/tool/bn.png" alt="" />本年发电量：</span></a></H4>
			<H2>&nbsp;&nbsp;&nbsp;<a style="color: #00ff00;" >1500&nbsp;MWh</a></H2>	
			<H4><a style="color: #ffffff;" padding-left="15px"><span ><img   src="images/green/tool/lj.png" alt="" />累计发电量：</span></a></H4>
			<H2>&nbsp;&nbsp;&nbsp;<a style="color: #00ff00;" >23,191&nbsp;MWh</a></H2>		
    			</TD>
		
	 </TR>   
			</table>
			  </td>
  
	</TR>
	<script type="text/javascript">
	// 百度地图API功能	
	
	var map = new BMap.Map("allmap",{mapType: BMAP_HYBRID_MAP});  
	map.centerAndZoom(new BMap.Point(113.1664,32.7453), 7);
	
	var point1 = new BMap.Point(113.1664,32.7453);
	var point2 = new BMap.Point(113.2664,32.1453);
	var point3 = new BMap.Point(113.3664,31.7453);
	var point4 = new BMap.Point(113.4664,30.9453);
	var point5 = new BMap.Point(113.2064,32.0453);
	var point6 = new BMap.Point(113.3164,31.2453);
	var point7 = new BMap.Point(113.4064,32.7453);
	
	var data_info = [[113.1664,32.7453,"风机名称：7#风机<br/> 运行状态：满功率运行<br/>日发电量：20000kWH<br/>总发电量：20000kWH<br/>总耗电量：20000kWH<br/>风速：5.0m/s"],
	                        [113.2664,32.1453,"风机名称：1#风机<br/> 运行状态：满功率运行<br/>日发电量：20000kWH<br/>总发电量：20000kWH<br/>总耗电量：20000kWH<br/>风速：5.0m/s"],
					[113.3664,31.7453,"风机名称：2#风机<br/> 运行状态：满功率运行<br/>日发电量：20000kWH<br/>总发电量：20000kWH<br/>总耗电量：20000kWH<br/>风速：5.0m/s"],
					[113.4664,30.9453,"风机名称：3#风机<br/> 运行状态：满功率运行<br/>日发电量：20000kWH<br/>总发电量：20000kWH<br/>总耗电量：20000kWH<br/>风速：5.0m/s"],
					[113.2064,32.0453,"风机名称：4#风机<br/> 运行状态：满功率运行<br/>日发电量：20000kWH<br/>总发电量：20000kWH<br/>总耗电量：20000kWH<br/>风速：5.0m/s"],
					[113.3164,31.2453,"风机名称：5#风机<br/> 运行状态：满功率运行<br/>日发电量：20000kWH<br/>总发电量：20000kWH<br/>总耗电量：20000kWH<br/>风速：5.0m/s"],
					[113.4064,32.7453,"风机名称：6#风机<br/> 运行状态：满功率运行<br/>日发电量：20000kWH<br/>总发电量：20000kWH<br/>总耗电量：20000kWH<br/>风速：5.0m/s"]
					];
	var opts = {
				width : 250,     // 信息窗口宽度
				height: 80,     // 信息窗口高度
				title : "" , // 信息窗口标题
				enableMessage:true//设置允许信息窗发送短息
			   };
			   
	map.centerAndZoom(point1, 7);
  	map.centerAndZoom(point2, 7);
  	map.centerAndZoom(point3, 7);
  	map.centerAndZoom(point4, 7);
  	map.centerAndZoom(point5, 7);
  	map.centerAndZoom(point6, 7);
  	map.centerAndZoom(point7, 7);	
  	
  	var marker1 = new BMap.Marker(point1);  // 创建标注
	var marker2 = new BMap.Marker(point2);
	var marker3 = new BMap.Marker(point3);  // 创建标注
	var marker4 = new BMap.Marker(point4);
	var marker5 = new BMap.Marker(point5);  // 创建标注
	var marker6 = new BMap.Marker(point6);
	var marker7 = new BMap.Marker(point7);  // 创建标注

 	map.addOverlay(marker1);              // 将标注添加到地图中
	map.addOverlay(marker2); 
	map.addOverlay(marker3);              // 将标注添加到地图中
	map.addOverlay(marker4); 
	map.addOverlay(marker5);              // 将标注添加到地图中
	map.addOverlay(marker6); 
	map.addOverlay(marker7);              // 将标注添加到地图中	   
	
		var label1 = new BMap.Label("#1机组,当前功率:20.5MW",{offset:new BMap.Size(20,-10)});
  var label2 = new BMap.Label("#2机组,当前功率:18.2MW",{offset:new BMap.Size(20,-10)});
  var label3 = new BMap.Label("#3机组,当前功率:21.2MW",{offset:new BMap.Size(-20,20)});
  var label4 = new BMap.Label("#4机组,当前功率:11.2MW",{offset:new BMap.Size(20,-10)});
  var label5 = new BMap.Label("#5机组,当前功率:5.2MW",{offset:new BMap.Size(20,-10)});
  var label6 = new BMap.Label("#6机组,当前功率:28.2MW",{offset:new BMap.Size(20,20)});
  var label7 = new BMap.Label("#7机组,当前功率:15.2MW",{offset:new BMap.Size(20,-10)});
	
	 label1.setStyle({
			 color : "#036100",
			 fontSize : "10px",
                   border :"1", 
			 height : "20px",
			 lineHeight : "20px",
			 fontFamily:"微软雅黑",
			 background:"#CEFFCC"
		 });

		   label2.setStyle({
			 color : "#036100",
			 fontSize : "10px",
                   border :"0", 
			 height : "20px",
			 lineHeight : "20px",
			 fontFamily:"微软雅黑",
			 background:"#CAE8C9"
		 });
		 label3.setStyle({
			 color : "#036100",
			 fontSize : "10px",
                   border :"0", 
			 height : "20px",
			 lineHeight : "20px",
			 fontFamily:"微软雅黑",
			 background:"#CAE8C9"
		 });
		 label4.setStyle({
			 color : "#036100",
			 fontSize : "10px",
                   border :"0", 
			 height : "20px",
			 lineHeight : "20px",
			 fontFamily:"微软雅黑",
			 background:"#CAE8C9"
		 });
		 label5.setStyle({
			 color : "#036100",
			 fontSize : "10px",
                   border :"0", 
			 height : "20px",
			 lineHeight : "20px",
			 fontFamily:"微软雅黑",
			 background:"#CAE8C9"
		 });
		 label6.setStyle({
			 color : "#036100",
			 fontSize : "10px",
                   border :"0", 
			 height : "20px",
			 lineHeight : "20px",
			 fontFamily:"微软雅黑",
			 background:"#CAE8C9"
		 });
		 label7.setStyle({
			 color : "#036100",
			 fontSize : "10px",
                   border :"0", 
			 height : "20px",
			 lineHeight : "20px",
			 fontFamily:"微软雅黑",
			 background:"#CEFFCC"
		 });
		 

	marker1.setLabel(label1);
  marker2.setLabel(label2);
  marker3.setLabel(label3);
  marker4.setLabel(label4);
  marker5.setLabel(label5);
  marker6.setLabel(label6);
  marker7.setLabel(label7);
	map.enableScrollWheelZoom(true);		   

</script>

<script type="text/javascript">
//获取窗口的高度，并设置“main”的高度
document.getElementById("main").style.height=window.innerHeight*0.28+"px";
// 路径配置
(function () {
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
        'BMap',
        'echarts/chart/map',
         'echarts/chart/gauge',
         'echarts/chart/line'
    ],
    function (echarts, BMapExtension) {
    var myChart = echarts.init(document.getElementById('main'));
    
   option = {
   backgroundColor: '#14306f',
	title : {
        text: '平均风速            发电功率             今日发电量',
       textStyle: {
	fontFamily: 'Arial, Verdana, sans...',
	fontSize: 13,
	color:'#ffffff',
	fontStyle: 'normal',
	fontWeight: 'bolder'
	 },
	 x:'center',
	 y:'bottom'
    },
    tooltip : {
        formatter: "{a} <br/>{c} {b}"
    },
    toolbox: {
        show : false,
        feature : {
            mark : {show: false},
            restore : {show: false},
            saveAsImage : {show: false}
        }
    },
    series : [
        {
            name:'当前功率',
            type:'gauge',
            z: 3,
            min:0,
            max:220,
            splitNumber:11,
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    color:  [[0.2, '#ff4500'],[0.8, '#00ff00'],[1, '#48b']], 
                    width: 8,
                    shadowColor : '#fff', //默认透明
                    shadowBlur: 10
                }
            },
            axisLabel: {            // 坐标轴小标记
                textStyle: {       // 属性lineStyle控制线条样式
                    fontWeight: 'bolder',
                    color: 'auto'
                }
            },
            axisTick: {            // 坐标轴小标记
                length :15,        // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: 'auto',
                    shadowColor : '#fff', //默认透明
                    shadowBlur: 10
                }
            },
            splitLine: {           // 分隔线
                length :20,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    width:3,
                    color: '#fff',
                    shadowColor : '#fff', //默认透明
                    shadowBlur: 10
                }
            },
            pointer: {           // 分隔线
                shadowColor : '#fff', //默认透明
                shadowBlur: 5
            },
            title : {
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder',
                    fontSize: 20,
                    fontStyle: 'italic',
                    color: '#fff',
                    shadowColor : '#fff', //默认透明
                    shadowBlur: 10
                }
            },
            detail : {
                borderWidth: 0,
                
                shadowColor : '#fff', //默认透明
                shadowBlur: 1,
                offsetCenter: [0, '50%'],       // x, y，单位px
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder',
                    color: 'auto'
                }
            },
            data:[{value: 80, name: 'MW'}]
        },
        {
            name:'风速',
            type:'gauge',
            center : ['25%', '55%'],    // 默认全局居中
            radius : '50%',
            min:0,
            max:30,
            endAngle:45,
            splitNumber:10,
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                	color:  [[0.2, '#00ff00'],[0.3, '#00ff00'],[1, '#ff4500']], 
                    width: 6,
                    shadowColor : '#fff', //默认透明
                    shadowBlur: 8
                }
            },
			axisLabel: {            // 坐标轴小标记
                textStyle: {       // 属性lineStyle控制线条样式
                  fontSize: 8,
                    color: 'auto'
                }
            },
            axisTick: {            // 坐标轴小标记
             
                length :0
            },
            splitLine: {           // 分隔线
                length :12,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    color: '#E8E8E8',
                    shadowColor : '#fff', //默认透明
                    shadowBlur: 5
                }
            },
            pointer: {
                width:4
            },
            title : {
                offsetCenter: [0, '-45%'],       // x, y，单位px
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder',
                    fontSize: 15,
                    fontStyle: 'italic',
                    color: '#fff',
                    shadowColor : '#fff', //默认透明
                    shadowBlur: 10
                }
            },
           
            detail : {
               borderWidth: 0,
                
                shadowColor : '#fff', //默认透明
                shadowBlur: 1,
                offsetCenter: [0, '50%'],       // x, y，单位px
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder',
                  	fontSize: 20,
                    color: 'auto'
                }
            },
            data:[{value: 7.5, name: 'm/s'}]
        },
        {
            name:'今日发电量',
            type:'gauge',
            center : ['75%', '55%'],    // 默认全局居中
            radius : '50%',
            min:0,
            max:500,
            startAngle:135,
            endAngle:-70,
            splitNumber:10,
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: [[0.2, '#ff4500'],[0.8, '#00ff00'],[1, '#48b']], 
                    width: 6,
                    shadowColor : '#fff', //默认透明
                    shadowBlur: 8
                }
            },
            axisLabel: {            // 坐标轴小标记
                textStyle: {       // 属性lineStyle控制线条样式
                  fontSize: 7,
                    color: 'auto'
                }
            },
            axisTick: {            // 坐标轴小标记
             
                length :0
            },
            splitLine: {           // 分隔线
                length :12,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    color: '#E8E8E8',
                    shadowColor : '#fff', //默认透明
                    shadowBlur: 5
                }
            },
            pointer: {
                width:4
            },
            title : {
                offsetCenter: [5, '-45%'],       // x, y，单位px
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder',
                    fontSize: 12,
                    fontStyle: 'italic',
                    color: '#fff',
                    shadowColor : '#fff', //默认透明
                    shadowBlur: 10
                }
            },
           
            detail : {
               borderWidth: 0,
                
                shadowColor : '#fff', //默认透明
                shadowBlur: 1,
                offsetCenter: [0, '50%'],       // x, y，单位px
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder',
                  	fontSize: 18,
                    color: 'auto'
                }
            },
            data:[{value: 120, name: 'kWh'}]
        }
      
    ]
};


        //window.onresize = myChart.resize;
 myChart.setOption(option); 
                     
 var ecConfig = require('echarts/config');

}
);
})();
</script>
<script type="text/javascript">
//获取窗口的高度，并设置“main”的高度
document.getElementById("main1").style.height=window.innerHeight*0.3+"px";
// 路径配置
(function () {
    require.config({
        paths: {
            echarts: '${pageContext.request.contextPath}/scripts/echarts/build/dist'
        },
        packages: [
            {
                name: 'BMap',
                location: '${pageContext.request.contextPath}/scripts/echarts/extension/BMap/src',
                main: 'main1'
            }
        ]
    });

    require(
    [
        'echarts',
        'BMap',
        'echarts/chart/map',
         'echarts/chart/line'
    ],
    function (echarts, BMapExtension) {
    var myChart = echarts.init(document.getElementById('main1'));
 option = {

     backgroundColor: '#14306f',
     title : {
      text: '24小时功率-风速图',
      textStyle: {
	fontFamily: 'Arial, Verdana, sans...',
	fontSize: 12,
	color:'#ffffff',
	fontStyle: 'normal',
	fontWeight: 'bolder',
	 x:'center',
	 }
},
    tooltip : {
        trigger: 'axis'
    },
     grid: {
    x:25,
    y:50,
    x2:25,
    y2:35
    },
    legend: {
        data:['功率(MW)','风速(m/s)'],
        x: 'right',
        textStyle: {
		fontFamily: 'Arial, Verdana, sans...',
		fontSize: 12,
		fontStyle: 'normal',
		fontWeight: 'normal',
 		color:'#ffffff'}
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
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            axisLabel:{
            textStyle : {color: '#ffffff'}},
            data : ['00:00','00:15','00:30','00:45','01:00','01:15','01:30',
            '01:45','02:00','02:15','02:30','02:45','03:00','03:15','03:30',
            '03:45','04:00','04:15','04:30','04:45','05:00','05:15','05:30',
            '05:45','06:00','06:15','06:30','06:45','07:00','07:15','07:30',
            '07:45','08:00','08:15','08:30','08:45','09:00','09:15','09:30',
            '09:45','10:00','10:15','10:30','10:45','11:00','11:15','11:30',
            '11:45','12:00','12:15','12:30','12:45','13:00','13:15','13:30',
            '13:45','14:00','14:15','14:30','14:45','15:00','15:15','15:30',
            '15:45','16:00','16:15','16:30','16:45','17:00','17:15','17:30',
            '17:45','18:00','18:15','18:30','18:45','19:00','19:15','19:30',
            '19:45','20:00','20:15','20:30','20:45','21:00','21:15','21:30',
            '22:45','23:00','23:15','23:30','23:45'
            ]
        }
    ],
    yAxis : [
        {
            type : 'value',
            name : '功率',
            axisLabel: {
      formatter: '{value}',
      show : true,
	textStyle : {
	color: '#ffffff'
	}
	}
           
        },
        {
            type : 'value',
            name : '风速',
              axisLabel: {
      formatter: '{value}',
      show : true,
	textStyle : {
	color: '#ffffff'
	}
	}
        }
    ],
    
    series : [
        {
            name:'功率(MW)',
            type:'line',
          	smooth:'true',
          	symbol:'none',
            itemStyle : {  
                  normal : {  
                     areaStyle: {type: 'default',
                     color : (function (){
                            var zrColor = require('zrender/tool/color');
                            return zrColor.getLinearGradient(
                                0, 200, 0, 400,
                                [[0, 'rgba(255,127,80,0.9)'],[1, 'rgba(255,255,255,0.4)']]
                            )
                        })()},
                     color: '#ff7f50' ,  
                                              
                     lineStyle : {                           	
                                width : 3  
                            }      	
                       } 
                    },   
            data:[1, 1.2, 1.25, 1.325, 1.5, 1.6, 1.525,1.61,1.8,2,2,2,2,2,1.85,1.8,1.65,
            1.5,1.475,1.46,1.45,1.425,1.4,1.425,1.475,1.425,1.375,1.415,1.45,1.475,1.5,1.46,
            1.36,1.33,1.3,1.275,1.26,1.16,1.1,1.05,1.115,1.1,1.175,1.18,1.15,1.16,1.11,1.147,1.175,
            1.113,1.109,1.095,1.105,1.115,1.117,1.123,1.125,1.135,1.132,1.136,1.142,1.139,1.136,1.15,
            1.11,1.121,1.129,1.25,1.325,1.425,1.525,1.475,1.51,1.56,1.61,1.72,1.756,1.801,1.832,
            1.85,1.901,2,2,2,2,2,2,2,1.95,1.91,1.93,1.85]
           
        },
        {
            name:'风速(m/s)',
            type:'line',
            smooth:true,
            symbol: 'none',
            itemStyle : {  
                        normal : {  
                            color: '#00bfff', 
                            lineStyle : {                           	
                                width : 2  
                            }
                        }  
                    },
                    yAxisIndex: 1,
            data:[3, 3.2, 3.25, 3.325, 3.5, 3.6, 3.525,3.61,3.8,4.6,4.1,3.8,4,4,3.85,3.8,3.65,
            3.23,3.46,3.26,3.5,3.25,3.4,3.425,3.475,3.425,3.35,3.415,3.45,3.475,3.25,3.46,
            3.34,3.33,3.23,3.275,3.26,3.16,3.21,3.05,3.115,3.1,3.175,3.18,3.15,3.16,3.11,3.247,3.15,
            3.113,3.109,3.095,3.15,3.115,3.12,3.23,3.125,3.135,3.132,3.136,3.142,3.139,3.15,3.2,
            3.12,3.121,3.129,3.25,3.325,3.425,3.525,3.475,3.51,3.6,3.61,3.72,3.756,3.801,3.832,
            3.85,3.901,4.2,4,4.1,4.07,3.98,4,4,3.95,3.91,3.93,3.85]
            
        }
    ]
};
        //window.onresize = myChart.resize;
 myChart.setOption(option); 
                     
 var ecConfig = require('echarts/config');

}
);
})();
</script>
<script type="text/javascript">
//获取窗口的高度，并设置“main”的高度
document.getElementById("main2").style.height=window.innerHeight*0.33+"px";
// 路径配置
(function () {
    require.config({
        paths: {
            echarts: '${pageContext.request.contextPath}/scripts/echarts/build/dist'
        },
        packages: [
            {
                name: 'BMap',
                location: '${pageContext.request.contextPath}/scripts/echarts/extension/BMap/src',
                main: 'main2'
            }
        ]
    });

    require(
    [
        'echarts',
        'BMap',
        'echarts/chart/map',
         'echarts/chart/bar',
         'echarts/chart/line'
    ],
    function (echarts, BMapExtension) {
    var myChart = echarts.init(document.getElementById('main2'));
    option = {
   backgroundColor: '#14306f',
     title : {
      text: '各机组当前发电功率对比',
      textStyle: {
	fontFamily: 'Arial, Verdana, sans...',
	fontSize: 12,
	color:'#ffffff',
	fontStyle: 'normal',
	fontWeight: 'normal',
	 x:'center',
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
    calculable : true,
     grid: {
    x:25,
    y:50,
    x2:25,
    y2:35
    },
    legend: {
        data:['当前功率(MW)'],
        x: 'right',
        textStyle: {
fontFamily: 'Arial, Verdana, sans...',
fontSize: 12,
fontStyle: 'normal',
fontWeight: 'normal',
 color:'#ffffff'
},
       
    },
    xAxis : [
        {
            type : 'category',
               axisLabel: {
      show : true,
	textStyle : {
	color: '#ffffff'
	}
	},
            data : ['#1组','#2组','#3组','#4组','#5组','#6组','#7组','#8组','#9组','#10组']
        }
    ],
    yAxis : [
        {
            type : 'value',
            name : '当前功率(MW)',
            axisLabel: {
      formatter: '{value}',
      show : true,
	textStyle : {
	color: '#ffffff'
	}
	}
           
        }
    ],
    series : [    
        {
            name:'当前功率(MW)',
            type:'bar',
            barWidth : 22,
            itemStyle : {  
                        normal : {  
                            color: '#ff7f50' ,
                            barBorderRadius: 3,
                           	
                        } ,
                        emphasis: {barBorderRadius: 3} 
                    },
            data:[3, 2.2, 3.3, 3.5, 3.3, 2.7, 2.3, 3.4, 3.0, 3.5, 4.0, 2.2]
        }
    ]
};
        //window.onresize = myChart.resize;
 myChart.setOption(option); 
                     
 var ecConfig = require('echarts/config');

}
);
})();
</script>
<script type="text/javascript">
//获取窗口的高度，并设置“main”的高度
document.getElementById("main3").style.height=window.innerHeight*0.25+"px";
// 路径配置
(function () {
    require.config({
        paths: {
            echarts: '${pageContext.request.contextPath}/scripts/echarts/build/dist'
        },
        packages: [
            {
                name: 'BMap',
                location: '${pageContext.request.contextPath}/scripts/echarts/extension/BMap/src',
                main: 'main3'
            }
        ]
    });

    require(
    [
        'echarts',
        'BMap',
        'echarts/chart/map',
         'echarts/chart/gauge',
         'echarts/chart/line'
    ],
    function (echarts, BMapExtension) {
    var myChart = echarts.init(document.getElementById('main3'));

   
     option = {
   backgroundColor: '#14306f',
       title : {
      text: '当前功率(MW)',
      textStyle: {
	fontFamily: 'Arial, Verdana, sans...',
	fontSize: 12,
	color:'#ffffff',
	fontStyle: 'normal',
	fontWeight: 'normal',
	 x:'center',
	 }
},
    tooltip : {
        formatter: "{a} <br/>{b} : {c}"
    },
    toolbox: {
        show : false,
        feature : {
            mark : {show: true},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    series : [
        {
            name:'当前功率(MW)',
            type:'gauge',
             min:0,
            max:300,
            splitNumber: 10,       // 分割段数，默认为5
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    color:  [[0.2, '#ff4500'],[0.8, '#00ff00'],[1, '#48b']], 
                    width: 8
                }
            },
            axisTick: {            // 坐标轴小标记
                splitNumber: 10,   // 每份split细分多少段
                length :12,        // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: 'auto'
                }
            },
            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
                show:false,    
textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    color: 'auto'
                }
            },
            axisTick: {            // 坐标轴小标记
                length :12,        // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: 'auto',
                    shadowColor : '#fff', //默认透明
                    shadowBlur: 10
                },
            },
            splitLine: {           // 分隔线
                length :20,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    width:3,
                    color: '#fff',
                    shadowColor : '#fff', //默认透明
                    shadowBlur: 10
                }
            },
            pointer : {
                width : 5
            },
            title : {
                show : true,
                offsetCenter: [0, '-40%'],       // x, y，单位px
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder'
                }
            },
            detail : {
                formatter:'{value}',
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    color: 'auto',
                    fontWeight: 'bolder'
                }
            },
            data:[{value: 215.8, name: ''}]
        }
    ]
};

  myChart.setOption(option);

var ecConfig = require('echarts/config');  
}
);
})();
</script>

<script type="text/javascript">
//获取窗口的高度，并设置“main”的高度
document.getElementById("main4").style.height=window.innerHeight*0.35+"px";
// 路径配置
(function () {
    require.config({
        paths: {
            echarts: '${pageContext.request.contextPath}/scripts/echarts/build/dist'
        },
        packages: [
            {
                name: 'BMap',
                location: '${pageContext.request.contextPath}/scripts/echarts/extension/BMap/src',
                main: 'main3'
            }
        ]
    });

    require(
    [
        'echarts',
        'BMap',
        'echarts/chart/map',
         'echarts/chart/pie',
         'echarts/chart/line'
    ],
    function (echarts, BMapExtension) {
    var myChart = echarts.init(document.getElementById('main4'));

   
    option = {
    backgroundColor: '#14306f',
    title : {
        text: '机组运行情况',
       textStyle: {
	fontFamily: 'Arial, Verdana, sans...',
	fontSize: 16,
	color:'#ffffff',
	fontStyle: 'normal',
	fontWeight: 'bolder'
	 },
	 x:'center',
    },
   
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
 legend: {
        x : 'center',
        y : 'bottom',
        textStyle: {
	
	fontSize: 12,
	color:'#ffffff'
	 },
        data:['运行台数','待机台数','检修台数','故障台数']
    },
    color:['#2ec7c9','#5ab1ef','#ffb980','#d87a80'],
    toolbox: {
        show : false,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {
                show: true, 
                type: ['pie', 'funnel'],
                option: {
                    funnel: {
                        x: '25%',
                        width: '50%',
                        funnelAlign: 'left',
                        max: 1548
                    }
                }
            },
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    series : [
        {
            
            type:'pie',
            radius : '45%',
            center: ['45%', '50%'],
             itemStyle : {
                normal : {
                    label : {
                        show : false
                    },
                    labelLine : {
                        show : false
                    }
                },
                emphasis : {
                    label : {
                        show : true
                    },
                    labelLine : {
                        show : true
                    }
                }
            },
            data:[
                {value:15, name:'运行台数'},
                {value:3, name:'待机台数'},
                {value:5, name:'检修台数'},
                {value:2, name:'故障台数'}
            ]
        }
    ]
};

  myChart.setOption(option);

var ecConfig = require('echarts/config');  
}
);
})();
</script>
</BODY>
</html>
	
	
	


	

