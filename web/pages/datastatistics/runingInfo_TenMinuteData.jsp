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
<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>运行数据一分钟查询</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
 		<script src="${pageContext.request.contextPath}/scripts/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/scripts/echarts/build/dist/echarts.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/popWaiting/popWaiting.css"/>
<link rel="stylesheet" type="text/css" href ="${pageContext.request.contextPath}/ecside/css/ecside_style.css"/>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/layui-v2.1.7/layui/css/layui.css" />
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/layui-v2.1.7/layui.js"></script>
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
function daysDiff() {
	var startDateDisp = document.runingInfo_TenMinuteDataForm.startDateDisp.value;
	var endDateDisp = document.runingInfo_TenMinuteDataForm.endDateDisp.value;
	
	var startDate = null;
 	var endDate = null;
 	startDate =  new Date(Date.parse(startDateDisp.replace(/-/g, "/")));
 	endDate =  new Date(Date.parse(endDateDisp.replace(/-/g, "/")));
 	var diff = endDate.getTime() - startDate.getTime();
	var diffDays = diff / (24 * 60 * 60 * 1000);
	return diffDays;
	}	
function query() {
	      var daysdiff = daysDiff();
		var msg = [];
		if (document.runingInfo_TenMinuteDataForm.startDateDisp.value == '') 
			msg.push('起始日期');
		
		if (document.runingInfo_TenMinuteDataForm.endDateDisp.value == '') 
			msg.push('终止日期');
	
		if(document.runingInfo_TenMinuteDataForm.endDateDisp.value < document.runingInfo_TenMinuteDataForm.startDateDisp.value)
			msg.push('正确时间');
            if(daysdiff>8)
			msg.push('时间间隔小于7天');

		if (msg.length > 0) {
			alert('请选择 ' + msg.join('，') + '!');
			return;
		}  
		 runingInfo_TenMinuteDataForm.method.value = 'show';
             runingInfo_TenMinuteDataForm.submit();
             forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
             popWaiting('正在查询时数据...');
	}

	
	function select() {
	var  flagVal = $("input[name='flag']:checked").val();
		runingInfo_TenMinuteDataForm.flag.value = flagVal;
         var daysdiff = daysDiff();
          var msg = []; 
        if (document.runingInfo_TenMinuteDataForm.startDateDisp.value == '') 
			msg.push('起始日期');
		
		if (document.runingInfo_TenMinuteDataForm.endDateDisp.value == '') 
			msg.push('终止日期');
	
		if(document.runingInfo_TenMinuteDataForm.endDateDisp.value < document.runingInfo_TenMinuteDataForm.startDateDisp.value)
			msg.push('正确时间');
            if(daysdiff>8)
			msg.push('时间间隔小于7天');
			if(runingInfo_TenMinuteDataForm.flag.value=='')
		  {
		  
			msg.push('时间间隔');
		  }
		if (msg.length > 0) {
			alert('请 ' + msg.join('，') + '!');
			return;
		}  
          var key=2;  
         // window.showModalDialog('${pageContext.request.contextPath}/datastatistics/runingInfo_SelectTenMinuteData.do?key='+key, window, 'dialogHeight:600px;dialogWidth:500px;help:no;status:no');	
	if (window.showModalDialog!=null)//IE判断
           {
          window.showModalDialog('${pageContext.request.contextPath}/datastatistics/runingInfo_SelectTenMinuteData.do?key='+key, window, 'dialogHeight:600px;dialogWidth:500px;help:no;status:no');	
          }
      
          else{
          window.open('${pageContext.request.contextPath}/datastatistics/runingInfo_SelectTenMinuteData.do?key='+key,'','width=500,height=600,status=no,modal=yes');
          }
	
	}
	
</script>





<BODY scroll="auto">
<!--如有页面需要-->
	<html:form action="runingInfo_TenMinuteData" method="post">
	<html:hidden property="method" styleId="method" value="show" />
	<html:hidden property="equipId"/>
	<html:hidden property="check_val"/>
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
    			<tr><td height="5px"></td></tr>
				<TR>	
				    <TD width="5"></TD>
					<TD valign="bottom"; align="center" height="23" width="88" background="../images/green/tool/level3_2.png"  background-repeat="no repeat" margin-left="10px">
					<a class="abtnx" href="${pageContext.request.contextPath}/datastatistics/runingInfo_OneMinuteData.do"><span><font size="2" color="#302422">重要量测信息</font></span></a>
					</TD>
					<TD valign="bottom"; align="center" height="23" width="88" background="../images/green/tool/level3_1.png">
					<a  class="abtnx" href="${pageContext.request.contextPath}/datastatistics/runingInfo_TenMinuteData.do"><span><font size="2" color="#302422">量测信息</font></span></a>
					</TD>
					<TD valign="bottom"; align="center" height="23" width="88" background="../images/green/tool/level3_2.png">
					<a  class="abtnx" href="${pageContext.request.contextPath}/datastatistics/runingInfo_OneDayData.do"><span><font size="2" color="#302422">统计信息</font></span></a>
					</TD>
	         
					
					
				</TR>
   							
			</TABLE>
		</TD>
	</tr>
					<tr>
				<TD valign="middle"; align="center" height="5" width="1930" background="../images/green/tool/top1.png">
				</td></tr>

		<tr>
		<TD style="width: 100%; text-align: left">
	<TABLE>	
	  <TD width="10"></TD>
			<TD>开始日期      </TD>
					<TD id="Select" >										
						<html:text  property="startDateDisp" styleId="startDateId" styleClass="dateInput" size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowWeek:true,isShowClear:true,readOnly:true,firstDayOfWeek:1,maxDate:'2022-12-28 23:59:59'})" title="点击选择起始日期" />
					</TD>				
				     <TD width="10"></TD>
					<TD>结束日期      </TD>
					<TD id="Select" >										
						<html:text  property="endDateDisp" styleId="startDateId" styleClass="dateInput" size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowWeek:true,isShowClear:true,readOnly:true,firstDayOfWeek:1,maxDate:'2022-12-28 23:59:59'})" title="点击选择结束日期" />
					</TD>	
					<TD width="25"></TD>
											 <% 
                                       out.print(" <input id = 'flag1'  type='radio' name='flag' value="+1+" "+request.getAttribute("check0")+" />"+"10分钟");
                                       out.print(" <input id = 'flag2'  type='radio' name='flag' value="+2+" "+request.getAttribute("check1")+" />"+"30分钟");
                                       out.print(" <input id = 'flag3'  type='radio' name='flag' value="+3+" "+request.getAttribute("check2")+" />"+"1小时");  
                                     %>	
			 <TD width="30"></TD>
		<TD valign="middle"; align="center" width="76" background="../images/green/tool/btn_bg.gif" background-repeat="no repeat" margin-left="10px">			
			<a class="abtn3" href="#" onclick="select()"  ><span ><img  padding-left="10px" src="../images/green/tool/dj.png" alt="" />&nbsp选择指标</span></a>
		   
		</TD> 
		
		
		</TABLE>
		</TD>
	</tr>


	

	
	 
	<!----------------------------------------工具栏和错误（或数据表格）之间的间隔------------------------------------>
	
	 <TR>
	 	<TD style="height:1%;">
		</TD>
	 </TR>
	
	<!----------------------------------------图形开始--<div style="width:100%; height:5%;align:center; color:#ff3300;font-weight:bold;padding-left:0px 0px; position:relative; left:80px; overflow: hidden;"> 当天</div>				
			 	  ------------------------------------------------------------>
	 <tr>
		<td id="chartTD0" width="100%" height="50%" valign="top">
	 		<table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" >
	 		<div id="main" style=" width:auto;"></div>
	 </table>
	 </td>
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
					var="entity"
					action="${pageContext.request.contextPath}/datastatistics/runingInfo_TenMinuteData.do?method=show"
					retrieveRowsCallback="process"
					sortRowsCallback="process"
					filterRowsCallback="process"
					xlsFileName="runingInfo_TenMinuteData.xls"
                              showTitle="ture"
					listWidth ="100%"
					listHeight="400"
					width="100%"
					sortable="true" 
					filterable="true" 
					showPrint="true"
					toolbarLocation="top"
                    pageSizeList="all"
                    rowsDisplayed="1000"
                    useAjax="false"  
                    autoIncludeParameters="true">    
                                  	 
                    	<ec:extendrow location="top">
                    	        ${top}
    					</ec:extendrow> 
					<ec:row highlightRow="true">
					<ec:column  headerSpan="0" property="saveTime" title="时间" cell="date" format="yyyy-MM-dd HH:mm" headerClass="ecTableHead" styleClass="ecTableBody"  width="200" style="text-align:center"/>
					
					
					
          %>
					 <%
					                    ArrayList list = (ArrayList)request.getAttribute("resultlend"); 
					            String a;
					            for(int i =0; i<list.size(); i++)
								{
									a="calValue"+String.valueOf(i);
									%>
						<ec:column headerSpan="0" property="<%=a%>" title="测点" cell="number" format="0.0" headerClass="ecTableHead" styleClass="ecTableBody"  width="200" style="text-align:center"/>
						
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
</TABLE>
<script type="text/javascript">
document.getElementById("main").style.height=window.innerHeight*0.5+"px";
   
  var myChart = echarts.init(document.getElementById('main'));
    var colors = ['#cc33cc', '#d14a61', '#675bba','#5793f3','#cc6600','#669933', '#339999', '#cc9933','#99cc66','#6699ff'];
  
   var echartdata = ${lineXml};
  
    option = {
    title : {
        text: '测点趋势图',
        textStyle: {
        color:'#ffffff',
        }
    },
    tooltip : {
        trigger: 'axis',
        textStyle: {
        color:'#ffffff',
        }
    },
   grid: {
   left: '10%',
   right: '2%'
  },
    legend: {
         data:[
           <% 
           ArrayList<String> resultlend = (ArrayList<String>) request.getAttribute("resultlend");
	 
                           for(int i=0;i<resultlend.size();i++){
                           out.print("'"+resultlend.get(i)+"',");
                           }
          %>
          ],
      
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: false},
            dataView : {show: false, readOnly: true},
            magicType : {show: false, type: ['line']},
            restore : {show: false},
            saveAsImage : {show: false}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
             boundaryGap : false,
             axisLabel : {
                textStyle : {color: '#000000'}
            },
            splitLine:{
            	lineStyle : {
            		color: '#6d6b6a'
            		}
            },
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
        <% 
        ArrayList<String> resultlend1 = (ArrayList<String>) request.getAttribute("resultlend");
                           for(int i=0;i<resultlend1.size();i++){
                       out.print("{");
                          out.print("  show : true,");
                       out.print("  type : 'value',"); 
           out.print("position: 'left',"); 
           out.print("  offset: "+i*40+","); 
            out.print(" axisLine: {" ); 
             out.print("  lineStyle: {" );
              out.print(" color: colors["+i+"]" ); 
              out.print(" }" ); 
               out.print(" }," );             
    out.print(" axisLabel: {" ); 
    out.print(" formatter: '{value}'," );
            out.print("   }" ); 
       out.print("   }," ); 
                           }
             %>
    ],
    calculable : false,
    series : [
           <% 
           ArrayList<String> resultlend2 = (ArrayList<String>) request.getAttribute("resultlend");
                           for(int i=0;i<resultlend2.size();i++){
                       out.print("{");
           out.print(" name:'"+resultlend2.get(i)+"',");
           out.print(" type:'line',"); 
           out.print(" smooth:true,");
            out.print(" symbol:'none',");
           out.print(" yAxisIndex:"+i+","); 
             
                    out.print("  data :"+ request.getAttribute("labley"+i) );     
                      out.print("   }, "); 
            
       
                           }
                           %>	
           
    ]
};

       
 	 myChart.setOption(option); 


 </script>  


</BODY>
</html>