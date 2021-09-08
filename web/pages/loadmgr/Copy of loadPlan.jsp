<%@ page contentType="text/html;charset=gbk" language="java"%>


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
	loadSetGridXMLURL = ajaxServerURL + "?method=getLoadLimitSetGirdXML"  ;
	dhtmlxGrid_ImgPath = serverRoot + "/scripts/dhtmlx/dhtmlxGrid/imgs/";
	loadSetGrid = null;
	//初始化负荷限制设置表格
	function initLoad() {
		//初始化表格
		loadSetGrid = createLoadSetGrid();
		var gridXmlUrl = loadSetGridXMLURL + "&_time=${currentDate}";
		loadSetGrid.loadXML(gridXmlUrl);	
		//当前日期赋值
		document.getElementById("loadSetDate").value="${currentDate}";
		//创建曲线
		drawLoadLimitChart("${currentDate}");
	}
	
	// 创建负荷限制设置表格
	function createLoadSetGrid() {
		$("#loadSet_grid_container").html("");	// 清空容器内容
		loadSetGrid = new dhtmlXGridObject("loadSet_grid_container");
		loadSetGrid.setImagePath(dhtmlxGrid_ImgPath);
		loadSetGrid.setHeader("序号,限制日期,开始时间,结束时间,负荷上限(kW),备注");
		loadSetGrid.setInitWidths("80,200,200,200,200,*");
		loadSetGrid.setColTypes("ro,ro,ed,ed,ed,ed");
		loadSetGrid.setColAlign("center,center,center,center,center,center");
		//loadSetGrid.setColSorting("int,str,int,int,int");
		loadSetGrid.setSkin("dhx_skyblue");
		loadSetGrid.enableMultiselect(false);
		//loadSetGrid.attachEvent("onRowSelect", rowSelectHandler);
		loadSetGrid.init();
		//loadSetGrid.enableEditTabOnly(1);
		return loadSetGrid;
	}
	
	//表格数据增删改
	function insert(){
		var selectRowId = loadSetGrid.getSelectedRowId();
		if(!selectRowId){
			alert("请选择要在哪一行之后插入!");
			return false;
		}
		var insertRowIndex = loadSetGrid.getRowIndex(selectRowId) + 1;
		var rowId = loadSetGrid.uid();
		var curDate = document.getElementById("loadSetDate").value;
		var rowData = ","+curDate+","+",,,";
		loadSetGrid.addRow(rowId,rowData,insertRowIndex);
		
		renewSortNo();
	}
	//增加
	function add(){
		var rowsNum = loadSetGrid.getRowsNum() + 1;
		var rowId = loadSetGrid.uid();
		
		var curDate = document.getElementById("loadSetDate").value;
		var rowData = rowsNum + ","+curDate+","+",,,";
		
		loadSetGrid.addRow(rowId,rowData);
		
	}
	function del(){
		
			var selectRowId = loadSetGrid.getSelectedRowId();
			if(!selectRowId){
				alert("请选择要删除的行!");
				return false;
			}
			if(confirm('确定删除选定的行?')) {
				loadSetGrid.deleteSelectedRows();
				renewSortNo();
			}
	}
	function renewSortNo(){
		var rowsNum = loadSetGrid.getRowsNum();
		for(var i=0;i<rowsNum;i++ ){
			var cellObj = loadSetGrid.cellByIndex(i,0);
			cellObj.setValue(i+1);
		}
	}
	
	function save(){
		
		if(!validate()) return false;
		var lsDate;
		var startTimes="" ;
		var endTimes="";
		var ratedPowers="" ;
		var lsDescs="";
		
		lsDate = document.getElementById("loadSetDate").value;
		
		var rowsNum = loadSetGrid.getRowsNum();
		if(rowsNum == 0){
			//如果列表没有数据直接删除,不执行保存
			doDeleteByDate(lsDate);
		}else{
			for(var i=0;i<rowsNum;i++ ){
				startTimes = startTimes + loadSetGrid.cellByIndex(i,2).getValue() + ",";//开始时间
				endTimes = endTimes + loadSetGrid.cellByIndex(i,3).getValue() + ",";//结束时间
				ratedPowers = ratedPowers + loadSetGrid.cellByIndex(i,4).getValue()+",";//限制容量
				var lsDesc = loadSetGrid.cellByIndex(i,5).getValue();
				if(lsDesc=="") lsDesc="_isB"; //如果没有录入内容,用_isB替代
				lsDescs = lsDescs + lsDesc +",_,";//备注,",_,"为间隔符
			}
			
			doSave(lsDate,startTimes,endTimes,ratedPowers,lsDescs);
		}
		
	}
	
	function doSave(lsDate,startTime,endTime,ratedPowers,lsDesc){
		var lsData = "&" + "lsDate=" + lsDate;
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
						drawLoadLimitChart(lsDate);
					}else{
						alert("保存失败,请与系统管理员联系!");
					}
				}
			}
		});
	}
	function doDeleteByDate(lsDate){
		var lsData = "&" + "lsDate=" + lsDate;
		$.ajax({
			type: "POST",
			url: ajaxServerURL,
			dataType: "text",
			data: "method=doDeleteByDate" + lsData +"&_time=" + new Date().getTime(),
			success: function(data){
				if (data) {
					if(data=="1"){
						alert("保存成功!");
						//重画曲线
						drawLoadLimitChart(lsDate);
					}else{
						alert("保存失败,请与系统管理员联系!");
					}
				}
			}
		});
	}
	
	function validate(){
		
		var message="";
		var rowsNum = loadSetGrid.getRowsNum();
		for(var i=0;i<rowsNum;i++ ){
			var j = i + 1;
			var startTime = loadSetGrid.cellByIndex(i,2).getValue(); //开始时间
			if(!validateTime(startTime)){
				message= message + "第"+j+"行 [开始时间]列 不满足格式要求\n\r" ;
			}
			
			var endTime = loadSetGrid.cellByIndex(i,3).getValue();//结束时间
			if(!validateTime(endTime)){
				message= message + "第"+j+"行 [结束时间]列 不满足格式要求\n\r" ;
			}
			
			var ratedPower = loadSetGrid.cellByIndex(i,4).getValue();//限制容量
			if(!validateRatedPower(ratedPower)){
				message= message + "第"+j+"行 [负荷上限]列 不满足格式要求\n\r" ;
			}
		}
		if(message!=""){
			var mes1="\n\r正确的时间格式为xx:xx 如 08:08\n\r正确的负荷上限为0-1000000000之间";
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
		var a = strRatedPower.match(/^(\d{1,999999999})$/); 
		if (a == null) return false; 
		return true;
	}
	//验证两个逻辑:本行开始时间是否小于本行结束时间,本行开始时间等于下一行的结束时间,
	function validateTimeEq(){

		var rowsNum = loadSetGrid.getRowsNum();
		rowsNum = rowsNum - 1;
		for(var i=0;i<rowsNum;i++ ){
			var startTime = loadSetGrid.cellByIndex(i,2).getValue();//本行开始时间
			var endTime = loadSetGrid.cellByIndex(i,3).getValue();//本行结束时间
			if(startTime >= endTime){
				alert("第"+(i+1)+"行 [开始时间]列 必须小于 [结束时间]列");
				return false;
			}
			
			var nextj = i+1;
			var nextRowStartTime = loadSetGrid.cellByIndex(nextj,2).getValue();//下一行开始时间
			if(nextRowStartTime!=endTime){
				alert("第"+(i+1)+"行 [结束时间]列 必须等于 第"+(nextj+1)+"行 [开始时间]列");
				return false;
			}
		}
		
		return true;
	}
	
	
	
	
	//日期选择
	function pickingFunc(dp){
		//alert(dp.cal.getDateStr() + dp.cal.getNewDateStr());
		var dateStr = dp.cal.getDateStr();
		var newDateStr = dp.cal.getNewDateStr();
		if(dateStr != newDateStr){
			var selectGridXmlUrl = loadSetGridXMLURL + "&_time="+newDateStr;
			loadSetGrid.clearAndLoad(selectGridXmlUrl);//表格重新查询
			drawLoadLimitChart(newDateStr);//重画曲线
		}
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
						计划时间&nbsp&nbsp<input type="text" id="loadSetDate" name="loadSetDate" size="16"  onclick="WdatePicker({isShowWeek:true,isShowClear:false,readOnly:true,firstDayOfWeek:1,onpicking:pickingFunc})" id="fromDateId" class="dateInput" style=" background-color: #FFFFFF" title="点击选择负荷日期">
					</TD>
					<TD  align=left>
						客户&nbsp&nbsp
						<select name="customers" style="width:130" >
							<option value="all" selected >全部客户</option>
					
							<logic:present name="customers">
								 <logic:iterate name="customers" id="element" scope="request" indexId="index">
									<option value="${element.customerNO}">${element.customerName}</option>
								 </logic:iterate>
							</logic:present>
							</select>
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
		<TD valign="top" height="320">
			<!-- 信息表格 -->
			<div id="loadLimitSetDiv" style="width: 100%; height: 100%;"></div>
		</TD>
	</TR>
	<TR>
		<TD valign="top"  height="100%">
			
			<div id="loadSet_grid_container" style="width: 100%; height: 100%;"></div>
		</TD>
	</TR>
</TABLE>
</body>
</html>
