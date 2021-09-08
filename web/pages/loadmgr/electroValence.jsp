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
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/PowerCharts/FusionCharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/pages/loadmgr/electroValence.js"></script>
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
	ajaxServerURL = serverRoot + "/loadmgr/electroValence.do";
	electroValenceGridXMLURL = ajaxServerURL + "?method=getElectroValenceGirdXML"  ;
	dhtmlxGrid_ImgPath = serverRoot + "/scripts/dhtmlx/dhtmlxGrid/imgs/";
	electroValenceGrid = null;
	//初始化电价设置表格
	function initLoad() {
		//初始化表格
		electroValenceGrid = createElectroValenceGrid();
		var gridXmlUrl = electroValenceGridXMLURL ;
		electroValenceGrid.loadXML(gridXmlUrl);	

		//创建曲线
		drawElectroValenceChart();
	}
	
	// 创建负荷限制设置表格
	function createElectroValenceGrid() {
		$("#electroValence_grid_container").html("");	// 清空容器内容
		electroValenceGrid = new dhtmlXGridObject("electroValence_grid_container");
		electroValenceGrid.setImagePath(dhtmlxGrid_ImgPath);
		electroValenceGrid.setHeader("序号,时段名称,开始时间,结束时间,电价(元/度),备注");
		electroValenceGrid.setInitWidths("80,200,200,200,200,*");
		electroValenceGrid.setColTypes("ro,coro,ed,ed,ed,ed");
		electroValenceGrid.setColAlign("center,center,center,center,center,center");
		//electroValenceGrid.setColSorting("int,str,int,int,int");
		electroValenceGrid.setSkin("dhx_skyblue");
		electroValenceGrid.enableMultiselect(false);
		electroValenceGrid.init();
		var combo = electroValenceGrid.getCombo(1);
		combo.put("J","尖");
		combo.put("F","峰");
		combo.put("P","平");
		combo.put("G","谷");
		//electroValenceGrid.enableEditTabOnly(1);
		return electroValenceGrid;
	}
	
	//表格数据增删改
	function insert(){
		var selectRowId = electroValenceGrid.getSelectedRowId();
		if(!selectRowId){
			alert("请选择要在哪一行之后插入!");
			return false;
		}
		var insertRowIndex = electroValenceGrid.getRowIndex(selectRowId) + 1;
		var rowId = electroValenceGrid.uid();
		var rowData = ","+","+","+",,,";
		electroValenceGrid.addRow(rowId,rowData,insertRowIndex);
		
		renewSortNo();
	}
	//增加
	function add(){
		var rowsNum = electroValenceGrid.getRowsNum() + 1;
		var rowId = electroValenceGrid.uid();
		var rowData = rowsNum + ","+","+","+",,,";
		
		electroValenceGrid.addRow(rowId,rowData);
		
	}
	function del(){
		
			var selectRowId = electroValenceGrid.getSelectedRowId();
			if(!selectRowId){
				alert("请选择要删除的行!");
				return false;
			}
			if(confirm('确定删除选定的行?')) {
				electroValenceGrid.deleteSelectedRows();
				renewSortNo();
			}
	}
	function renewSortNo(){
		var rowsNum = electroValenceGrid.getRowsNum();
		for(var i=0;i<rowsNum;i++ ){
			var cellObj = electroValenceGrid.cellByIndex(i,0);
			cellObj.setValue(i+1);
		}
	}
	
	function save(){
		
		if(!validate()) return false;
		var valenceNames="";
		var startTimes="" ;
		var endTimes="";
		var ratedPowers="" ;//电价
		var lsDescs="";//备注
	
		var rowsNum = electroValenceGrid.getRowsNum();
		if(rowsNum == 0){
			//如果列表没有数据直接删除,不执行保存
			doDelete();
		}else{
			for(var i=0;i<rowsNum;i++ ){
				valenceNames = valenceNames + electroValenceGrid.cellByIndex(i,1).getValue() + ",";//时段名称
				startTimes = startTimes + electroValenceGrid.cellByIndex(i,2).getValue() + ",";//开始时间
				endTimes = endTimes + electroValenceGrid.cellByIndex(i,3).getValue() + ",";//结束时间
				ratedPowers = ratedPowers + electroValenceGrid.cellByIndex(i,4).getValue()+",";//电价
				var lsDesc = electroValenceGrid.cellByIndex(i,5).getValue();//备注
				if(lsDesc=="") lsDesc="_isB"; //如果没有录入内容,用_isB替代
				lsDescs = lsDescs + lsDesc +",_,";//备注,",_,"为间隔符
			}
			
			doSave(valenceNames,startTimes,endTimes,ratedPowers,lsDescs);
		}
		
	}
	
	function doSave(valenceNames,startTime,endTime,ratedPowers,lsDesc){
		var lsData = "&" + "valenceNames=" + valenceNames;
		lsData = lsData + "&" + "startTimes=" + startTime;
		lsData = lsData + "&" + "endTimes=" + endTime;
		lsData = lsData + "&" + "ratedPowers=" + ratedPowers;
		lsData = lsData + "&" + "lsDescs=" + lsDesc;
		//alert(lsData);
		$.ajax({
			type: "POST",
			url: ajaxServerURL,
			dataType: "text",
			data: "method=doSave" + lsData +"&_time=" + new Date().getTime(),
			success: function(data){
				if (data) {
					if(data=="1"){
						alert("保存成功!");
						//重画曲线
						drawElectroValenceChart();
					}else{
						alert("保存失败,请与系统管理员联系!");
					}
				}
			}
		});
	}
	function doDelete(){
		//var lsData = "&" + "lsDate=" + lsDate;
		$.ajax({
			type: "POST",
			url: ajaxServerURL,
			dataType: "text",
			data: "method=doDelete" + "&_time=" + new Date().getTime(),
			success: function(data){
				if (data) {
					if(data=="1"){
						alert("保存成功!");
						//重画曲线
						drawElectroValenceChart();
					}else{
						alert("保存失败,请与系统管理员联系!");
					}
				}
			}
		});
	}
	
	function validate(){
		
		var message="";
		var rowsNum = electroValenceGrid.getRowsNum();
		for(var i=0;i<rowsNum;i++ ){
			var j = i + 1;
			var startTime = electroValenceGrid.cellByIndex(i,2).getValue(); //开始时间
			if(!validateTime(startTime)){
				message= message + "第"+j+"行 [开始时间]列 不满足格式要求\n\r" ;
			}
			
			var endTime = electroValenceGrid.cellByIndex(i,3).getValue();//结束时间
			if(!validateTime(endTime)){
				message= message + "第"+j+"行 [结束时间]列 不满足格式要求\n\r" ;
			}
			
			var ratedPower = electroValenceGrid.cellByIndex(i,4).getValue();//电价
			if(!validateRatedPower(ratedPower)){
				message= message + "第"+j+"行 [电价]列 不满足格式要求\n\r" ;
			}
		}
		if(message!=""){
			var mes1="\n\r正确的时间格式为xx:xx 如 08:08\n\r正确的电价为0-100之间";
			message = message + mes1;
			alert(message);
			return false;
		}
		
		
		if(!validateTimeEq()) return false;
		
		return true; 
	}
	function validateTime(strTime){
		var a = strTime.match(/^(\d{0,2}):(\d{0,2})$/); 
		if (a == null) return false; 
		if (a[1]>=24 || a[2]>=60) return false; 
		return true;
	}
	function validateRatedPower(strRatedPower){
		if(isNaN(strRatedPower)) return false;
		if( strRatedPower >= 0 && strRatedPower < 100 ){
			return true;
		}else{
			return false;
		}
	}
	//验证两个逻辑:本行开始时间是否小于本行结束时间,本行开始时间等于下一行的结束时间,
	function validateTimeEq(){

		var rowsNum = electroValenceGrid.getRowsNum();
		rowsNum = rowsNum - 1;
		for(var i=0;i<rowsNum;i++ ){
			var startTime = electroValenceGrid.cellByIndex(i,2).getValue();//本行开始时间
			var endTime = electroValenceGrid.cellByIndex(i,3).getValue();//本行结束时间
			if(startTime >= endTime){
				alert("第"+(i+1)+"行 [开始时间]列 必须小于 [结束时间]列");
				return false;
			}
			
			var nextj = i+1;
			var nextRowStartTime = electroValenceGrid.cellByIndex(nextj,2).getValue();//下一行开始时间
			if(nextRowStartTime!=endTime){
				alert("第"+(i+1)+"行 [结束时间]列 必须等于 第"+(nextj+1)+"行 [开始时间]列");
				return false;
			}
		}
		
		return true;
	}
	

	
//-->
</script>

</head>
<body scroll="no" onload="initLoad()">

<TABLE width="100%" height="100%" border="0" cellSpacing="0" cellPadding="0" >
	<TR>
		<TD class="tdTitle">
			<TABLE width="100%" border="0" cellSpacing="0" cellPadding="1">
				<TR>
					<TD  align=left width="100%" style="padding-left:40">										
						
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
			<div id="electroValence_grid_container" style="width: 100%; height: 100%;"></div>
		</TD>
	</TR>
	<TR>
		<TD valign="top"  height="100%">
			<div id="electroValenceDiv" style="width: 100%; height: 100%;"></div>
		</TD>
	</TR>
</TABLE>
</body>
</html>
