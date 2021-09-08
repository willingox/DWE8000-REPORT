<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/dhtmlx/dhtmlxGrid/dhtmlxgrid.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/dhtmlx/dhtmlx_skin_custom/dhtmlx_custom.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlx/dhtmlxcommon.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlx/dhtmlxGrid/dhtmlxgrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlx/dhtmlxGrid/dhtmlxgridcell.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/PowerCharts/FusionCharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/pages/loadmgr/loadPlan.js"></script>
<title>负荷限制设置</title>
<style type="text/css">
	body {
		font-size: 12pt;
		background-color: #FFFFFF;
	}
	
	td.tdTitle {
		 height: 25px; 
		 width: 100%; 
		 background-color: #E7F1F3; 
		 font-weight: bold; 
		 font-size: 9pt; 
		 padding: 3px 0 3px 10px;
	}
</style>
<script type="text/javascript">
<!--
	serverRoot = "${pageContext.request.contextPath}";
	ajaxServerURL = serverRoot + "/loadmgr/loadPlan.do";
	loadPlanGridXMLURL = ajaxServerURL + "?method=getLoadPlanGirdXML"  ;
	dhtmlxGrid_ImgPath = serverRoot + "/scripts/dhtmlx/dhtmlxGrid/imgs/";
	loadPlanGrid = null;
	//初始化负荷限制设置表格
	function initLoad() {
		//初始化表格
		//loadPlanGrid = createLoadPlanGrid();
		//var gridXmlUrl = loadPlanGridXMLURL +"&customerNo=all"+ "&_time=${currentDate}";
		//loadPlanGrid.loadXML(gridXmlUrl);	
		//当前日期赋值
		document.getElementById("loadSetDate").value="${currentDate}";
		//创建曲线
		drawLoadPlanChart("${currentDate}","all");
	}
	
	// 创建负荷限制设置表格
	function createLoadPlanGrid() {
		$("#loadPlan_grid_container").html("");	// 清空容器内容
		loadPlanGrid = new dhtmlXGridObject("loadPlan_grid_container");
		loadPlanGrid.setImagePath(dhtmlxGrid_ImgPath);
		loadPlanGrid.setHeader("序号,限制日期,开始时间,结束时间,负荷上限(kW),备注");
		loadPlanGrid.setInitWidths("80,200,200,200,200,*");
		loadPlanGrid.setColTypes("ro,ro,ed,ed,ed,ed");
		loadPlanGrid.setColAlign("center,center,center,center,center,center");
		//loadPlanGrid.setColSorting("int,str,int,int,int");
		loadPlanGrid.setSkin("dhx_skyblue");
		loadPlanGrid.enableMultiselect(false);
		//loadPlanGrid.attachEvent("onRowSelect", rowSelectHandler);
		loadPlanGrid.init();
		//loadPlanGrid.enableEditTabOnly(1);
		return loadPlanGrid;
	}
	
	function query(){
		
		var lsDate = document.getElementById("loadSetDate").value;
		var selectObj = document.getElementById("customers");
		var customerNo = selectObj.options[selectObj.selectedIndex].value;
		
		var queryGridXmlUrl = loadPlanGridXMLURL +"&customerNo="+customerNo+ "&_time="+lsDate;
		
		//loadPlanGrid.clearAndLoad(queryGridXmlUrl);//表格重新查询
		drawLoadPlanChart(lsDate,customerNo);//重画曲线
	}
	
	
	
//-->
</script>
</head>

<body scroll="no" onload="initLoad()">

<TABLE width="100%" height="100%" border="0" cellSpacing="0" cellPadding="0" >
	<TR>
		<TD class="tdTitle">
			<TABLE  border="0" cellSpacing="0" cellPadding="1">
				<TR>
					<TD  align=left  style="padding-left:40" width="200">										
						计划时间&nbsp&nbsp<input type="text" id="loadSetDate" name="loadSetDate" size="16"  onclick="WdatePicker({isShowWeek:true,isShowClear:false,readOnly:true,firstDayOfWeek:1})" id="fromDateId" class="dateInput" style=" background-color: #FFFFFF" title="点击选择负荷日期">
					</TD>
					<TD  align=left>
						客户&nbsp&nbsp
						<select name="customers" id="customers" >
							<option1 value="all" selected >全部客户</option>
							<logic:present name="customers">
								 <logic:iterate name="customers" id="element" scope="request" indexId="index">
								 	<option value="${element.customerNo}">${element.customerName}</option>
								 </logic:iterate>
							</logic:present>
						</select>

						
					</TD>
					<TD>
						&nbsp;&nbsp;&nbsp;&nbsp;<a class="abtnLT" href="javascript:query()"><span>查&nbsp;&nbsp;询</span></a>
					</TD>

					
					
				</TR>
			</TABLE>
		</TD>
	</TR>
	<TR>
		<TD valign="top" height="100%">
			
			<div id="loadPlanDiv" style="width: 100%; height: 100%;"></div>
		</TD>
	</TR>
	  
	<TR >
		<TD valign="top"  height="0">
			<div id="loadPlan_grid_container" style="width: 100%; height:0;display:none"></div>
		</TD>
	</TR>
	
</TABLE>
</body>

</html>