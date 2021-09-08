<%@ page contentType="text/html;charset=gbk" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

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
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/FusionCharts/js/FusionCharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/My97DatePicker/WdatePicker.js"></script>


<script type="text/javascript" src="${pageContext.request.contextPath}/pages/loadmgr/loadLimitSet.js"></script>
<title>电池模块详细信息</title>
<script type="text/javascript">
<!--
	//vin = "${batGroupInfo.vin}";
	serverRoot = "${pageContext.request.contextPath}";
	ajaxServerURL = serverRoot + "/loadmgr/loadLimitSet.do";
	dhtmlxGrid_ImgPath = serverRoot + "/scripts/dhtmlx/dhtmlxGrid/imgs/";
	battModXMLURL = ajaxServerURL + "?method=getBattModXML"  + "&_time=" + new Date().getTime();	// 电池模块温度实时信息获取地址
	battModRTGrid = null;
	
	//初始化负荷限制设置表格
	function initLoadSetGrid() {
		var battModRTGrid = getBattModRTGrid();
		battModRTGrid.loadXML(battModXMLURL);	
		
	}
	
	// 获取电池模块实时信息表格
	function getBattModRTGrid() {
		if (!battModRTGrid)
			battModRTGrid = createBattModRTGrid();
		return battModRTGrid;
	}
	
	// 创建电池模块实时信息表格
	function createBattModRTGrid() {

		$("#batMod_grid_container").html("");	// 清空容器内容
		battModRTGrid = new dhtmlXGridObject("batMod_grid_container");
		battModRTGrid.setImagePath(dhtmlxGrid_ImgPath);
		//battModRTGrid.setHeader("主要信息,#cspan,#cspan,#cspan,#cspan,#cspan,#cspan,#cspan,采样温度,#cspan,#cspan,#cspan,#cspan");
		

		battModRTGrid.setHeader("序号,开始时间,结束时间,负荷上限,备注");
		battModRTGrid.setInitWidths("80,200,200,200,*");
		battModRTGrid.setColTypes("ed,ed,ed,ed,ed");
		battModRTGrid.setColAlign("center,center,center,center,center");
		//battModRTGrid.setColSorting("int,str,int,int,int");
		battModRTGrid.setSkin("dhx_skyblue");
		battModRTGrid.enableMultiselect(false);
		//battModRTGrid.attachEvent("onRowSelect", rowSelectHandler);
		battModRTGrid.init();
		//battModRTGrid.enableEditTabOnly(1);
		return battModRTGrid;
	}
	
	//表格数据增删改
	function insert(){
		var selectRowId = battModRTGrid.getSelectedRowId();
		if(!selectRowId){
			alert("请选择要在哪一行之后插入!");
			return false;
		}
		var rowsNum = battModRTGrid.getRowsNum();
		var rowId = rowsNum + 1 ;
		var rowData = rowId + ",,,,,";
		battModRTGrid.addRow(rowId,rowData,selectRowId);
		renewNum();
	}
	function add(){
		var rowsNum = battModRTGrid.getRowsNum() + 1;
		var rowId = battModRTGrid.uid();
		var rowData = rowsNum + ",,,,,";
		
		battModRTGrid.addRow(rowId,rowData);
	}
	function del(){
		var selectRowId = battModRTGrid.getSelectedRowId();
		if(!selectRowId){
			alert("请选择要删除的行!");
			return false;
		}
		battModRTGrid.deleteSelectedRows();
		renewNum();
	}
	function renewNum(){
		var rowsNum = battModRTGrid.getRowsNum();
		for(var i=0;i<rowsNum;i++ ){
			var cellObj = battModRTGrid.cellByIndex(i,0);
			cellObj.setValue(i+1);
		}
	}
	
	//日期选择
	function pickedFunc(dp){
		alert(dp.cal.getDateStr() + dp.cal.getNewDateStr());
		
	}
	
//-->
</script>
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
</head>
<body scroll="no" onload="initLoadSetGrid()">
<TABLE width="100%" height="100%" border="0" cellSpacing="0" cellPadding="0" >
	<TR>
		<TD class="tdTitle">
			<TABLE width="100%" border="0" cellSpacing="0" cellPadding="1">
				<TR>
					<TD  align=left width="100%" style="padding-left:40">										
						负荷设置时间&nbsp&nbsp<input type="text" name="loadSetDate" size="16"  onclick="WdatePicker({isShowWeek:true,isShowClear:false,readOnly:true,firstDayOfWeek:1,maxDate:'%y-%M-%d',onpicking:pickedFunc})" id="fromDateId" class="dateInput" style=" background-color: #FFFFFF" title="点击选择负荷日期">
					</TD>
					<TD ><input type="button" onclick="insert()" value="插入" class="btn_edit"></TD>
					<TD ><input type="button" onclick="add()" value="增加" class="btn_add"></TD>
					<TD ><input type="button" onclick="del()" value="删除" class="btn_delete"></TD>
					<TD ><input type="button" onclick="save()" value="保存" class="btn_save"></TD>
					<TD style="padding-right:60">&nbsp</TD>
				</TR>
			</TABLE>
		</TD>
	</TR>
	<TR>
		<TD valign="top" height="240">
			<!-- 信息表格 -->
			<div id="batMod_grid_container" style="width: 100%; height: 100%;"></div>
		</TD>
	</TR>
	<TR>
		<TD valign="top"  height="100%">
			<div id="loadLimitSetDiv" style="width: 100%; height: 100%;"></div>
		</TD>
	</TR>
</TABLE>
</body>
</html>
