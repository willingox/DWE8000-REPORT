<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1   
response.setHeader("Pragma","no-cache"); //HTTP 1.0   
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server

%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/ecside.tld" prefix="ec"%>
<%@ page import="java.util.ArrayList" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>故障日志</title>
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
			
			 $("#SelectWind").change(function(){
                    faultLogDataForm.submit();
                    });
                     $("#SelectDate").change(function(){
                    faultLogDataForm.submit();
                    });
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
	 		
			if (parent) {
				/**树参数初始化********************************/
				var treeFlType="301";//暂时没用
				var treeType="faultLogData"; //树类型
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
		
		
				
		  var data = parent.getEuTreeSelected();
		
		 faultLogDataForm.str.value = data;
		
	       var type = data.substring(0,data.indexOf("-"));

	       if(type != "generator")
   	  	 {
	    	   faultLogDataForm.id = 0;
   	  	 }
   	  	   
		  if(data=="")
		  {
		  	alert('请选择左遥测或遥信!');
			return;
		  }
		
		if (msg.length > 0) {
			alert('请选择 ' + msg.join('，') + '!');
			return;
		}
		 faultLogDataForm.method.value = 'show';
             faultLogDataForm.submit();
             forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
             popWaiting('正在查询时数据...');
	}
	
 </script> 	
	
	

  
 
</HEAD>
  
  <body>
   <html:form action="faultLogData" method="post">
	<html:hidden property="method" styleId="method" value="show" />             
	<html:hidden property="str"/>   
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
				    <TD width="15"></TD>
					<TD style="color:#000000">选择风机      </TD>

					<TD id="SelectWind" >
						<html:select property="equipId" value="${faultLogDataForm.equipId}" styleClass="formDataFormSelect" style="width:150px;border-width:1;">
                           <html:option value=""></html:option>
                           <logic:present name="windName">
                           		<html:options collection="windName" labelProperty="name" property="id"/>
                           </logic:present>
                       	</html:select>
						
					</TD>
				   <TD width="15"></TD>
					<TD style="color:#000000">选择日期</TD>

					<TD id="SelectDate">
						<html:select property="dateEquipId" value="${faultLogDataForm.dateEquipId}" styleClass="formDataFormSelect" style="width:150px;border-width:1;">
                           <html:option value=""></html:option>
                           <logic:present name="dateName">
                           		<html:options collection="dateName" labelProperty="name" property="id"/>
                           </logic:present>
                       	</html:select>
					</TD>		
					
					  <TD width="15"></TD>
					<TD style="color:#000000">故障文件</TD>

					<TD >
						<html:select property="txtEquipId" value="${faultLogDataForm.txtEquipId}" styleClass="formDataFormSelect" style="width:190px;border-width:1;">
                           <html:option value=""></html:option>
                           <logic:present name="txtName">
                           		<html:options collection="txtName" labelProperty="name" property="id"/>
                           </logic:present>
                       	</html:select>
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
		 <td style="font-size:20px;color:#ffffff;background-color:rgba(255,255,255,0.1);" >  
		 <H5 style="color: #000000;"align="center">故障码:${faultNumber}</H5>
	 		</td> 
            </tr>	
            
        <tr >
	<TD style="width: 100%;text-align: center;text-valign: top;">
	<table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0">

	<td valign="top" align="center">
	<table>
		<div id="main" ></div>
	
	</table>
	</td>
	
	</table>
	</TD>
	</tr>


	
<script type="text/javascript">
//获取窗口的高度，并设置“main”的高度
document.getElementById("main").style.height=window.innerHeight*0.8+"px";
// 路径配置

    var myChart = echarts.init(document.getElementById('main'));
    
  	var colors = ['#cc33cc', '#d14a61', '#675bba','#5793f3','#cc6600','#669933', '#339999', '#cc9933','#99cc66','#6699ff'];
    
    option = {
      color: colors,
   backgroundColor: '#f9ffff',
     title : {
      text: '故障日志',
     
},
    tooltip : {
        trigger: 'axis',
         axisPointer: {
            type: 'cross'
        }
    },
    grid: {
   left: '10%',
   right: '0%'
  },
    toolbox: {
        feature : {
            
            dataView : {show: true, readOnly: false},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
     
  
    legend: {
     
          data:[
          <% 
                  ArrayList list1 = (ArrayList)request.getAttribute("size"); 
                           for(int i=0;i<list1.size();i++){
                           out.print("'"+request.getAttribute("chinaName"+i)+"',");
                           }
          %>
          ],
       
                        
       
       
   },
    xAxis : [
        {
              type: 'category',
            axisTick: {
                alignWithLabel: true
            },
     		data : ${lablex}
        }

    ],
    yAxis : [
      <% 
               ArrayList list3 = (ArrayList)request.getAttribute("size"); 
                           for(int i=0;i<list3.size();i++){
                       out.print("{");
                       out.print("  type : 'value',"); 
           out.print("position: 'left',"); 
           out.print("  offset: "+i*30+","); 
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
    series : [    
        
              <% 
              ArrayList list = (ArrayList)request.getAttribute("size"); 
                           for(int i=0;i<list.size();i++){
                       out.print("{");
           out.print(" name:'"+request.getAttribute("chinaName"+i)+"',");
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
        //window.onresize = myChart.resize;
 myChart.setOption(option); 
                     


</script>
	
  </body>
</html>
