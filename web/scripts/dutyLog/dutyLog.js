/** JavaScript数组函数扩展 */
/**
 * 删除数组元素
 */
Array.prototype.remove = function(val){
	if (!val) {
		return;
	}
	for(var i = 0; i < this.length; i++){
		if (this[i] == val) {
			for(var j = i; j < this.length; j++){
				this[j] = this[j+1];
			}
			this.length = this.length - 1;
			break;
		}
	}
};

function getTodayString() {
	var today = new Date();
	return today.getYear() + "-" + (today.getMonth() + 1) + "-" + today.getDate() + " 00:00:00";
}

/** 全局变量定义 */
CLEARTime = 3000;
CLGZ = "clgz";	// 车辆故障
CLGZ_ITEM_RM = "删除该条车辆故障描述";
HDSBGZ = "hdsbgz";	// 换电设备故障
HDSBGZ_ITEM_RM = "删除该条换电设备故障描述";
JKXTGZ = "jkxtgz";	// 监控系统故障
JKXTGZ_ITEM_RM = "删除该条监控系统故障描述";
CDSBGZ = "cdsbgz";	// 充电设备故障
CDSBGZ_ITEM_RM = "删除该条充电设备故障描述";
DCWHGZ = "dcwhgz";	// 电池维护故障
DCWHGZ_ITEM_RM = "删除该条电池维护故障描述";
QTGZ = "qtgz";	// 其他故障
QTGZ_ITEM_RM = "删除该条其他故障描述";
OTHER_ITEM_RM = "删除该条其他需要交接班或长期跟踪的事宜描述";

$(document).ready(function() {
	window.setTimeout(removeMessage, CLEARTime);
	
	if ($("#battDistributed").val() == "") {
		$("#battDistributed").html("1A:&#13;1B:&#13;2A:&#13;2B:&#13;3A:&#13;3B:&#13;车辆:&#13;其他:&#13;");
	}
		
	
});

/**
 * 保存修改
 */
function save() {
	cleanOutItem();
	dutyLogForm.method.value = "save";
	dutyLogForm.submit();
	forbidSubmit();
	popWaiting("正在保存日志，请稍候...");
}

/**
 * 移交下值并保存
 */
function lock() {
	if (confirm("移交下值后当前日志将不再被允许编辑或删除，并自动产生空白下值日志，确定移交吗？")) {
		cleanOutItem();
		dutyLogForm.method.value = "lock";
		dutyLogForm.submit();
		forbidSubmit();
		popWaiting("正在保存日志，请稍候...");
	}
}

/**
 * 返回日志列表
 */
function bk2lst() {
	if (confirm("信息尚未保存，确定放弃保存吗？")) {
		dutyLogForm.method.value = "list";
		dutyLogForm.submit();
	}
}

/**
 * 导出EXCEL
 */
function excel() {
	cleanOutItem();
	dutyLogForm.method.value = "toExcel";
	dutyLogForm.submit();
}

/**
 * 整理日志条目
 */
function cleanOutItem() {
	$("#clgzSerial").val(clgzSerialArray.join());	// 车辆故障描述表单序号字符串，以英文逗号分隔
	$("#hdsbgzSerial").val(hdsbgzSerialArray.join());	// 换电设备故障描述
	$("#jkxtgzSerial").val(jkxtgzSerialArray.join());	// 监控系统故障描述
	$("#cdsbgzSerial").val(cdsbgzSerialArray.join());	// 充电设备故障描述
	$("#dcwhgzSerial").val(dcwhgzSerialArray.join());	// 电池维护故障描述
	$("#qtgzSerial").val(qtgzSerialArray.join());	// 其他故障描述
	$("#otherSerial").val(otherSerialArray.join());	// 其他事宜
}

function removeMessage() {
	$("#message").css("display", "none");
}

function toNext(prefix, serial) {
	var checkbox = "#" + prefix + "_toNext_" + serial;
	var text = "#" + prefix + "_actualClearTimeShow_" + serial;
	if ($(checkbox).attr("checked")) {
		$(text).val("移交下值");
	} else {
		$(text).val("");
	}
}

/**
 * 新增条目
 * 
 * @param tableId
 */
function addItem(tableId) {
	var serial, title;
	if (CLGZ == tableId) {	// 车辆故障
		serial = ++clgzItemCount;
		title = CLGZ_ITEM_RM;
		clgzSerialArray.push(serial);
	} else if (HDSBGZ == tableId) {	// 换电设备故障
		serial = ++hdsbgzItemCount;
		title = HDSBGZ_ITEM_RM;
		hdsbgzSerialArray.push(serial);
	} else if (JKXTGZ == tableId) {	// 监控系统故障
		serial = ++jkxtgzItemCount;
		title = JKXTGZ_ITEM_RM;
		jkxtgzSerialArray.push(serial);
	} else if (CDSBGZ == tableId) {	// 充电设备故障
		serial = ++cdsbgzItemCount;
		title = CDSBGZ_ITEM_RM;
		cdsbgzSerialArray.push(serial);
	} else if (DCWHGZ == tableId) {	// 电池维护故障
		serial = ++dcwhgzItemCount;
		title = DCWHGZ_ITEM_RM;
		dcwhgzSerialArray.push(serial);
	} else if (QTGZ == tableId) {	// 其他故障
		serial = ++qtgzItemCount;
		title = QTGZ_ITEM_RM;
		qtgzSerialArray.push(serial);
	}
	var head = getHead(tableId, serial, title);
	var content = getContent(tableId, serial);
	$("#" + tableId).append($("<tr id=\"" + tableId + "_" + serial + "_title\"><th>" + head + "</th></tr>"));
	$("#" + tableId).append($("<tr id=\"" + tableId + "_" + serial + "_content\"><td>" + content + "</td></tr>"));
}

/**
 * 删除条目
 * 
 * @param prefix
 */
function removeItem(prefix, serial) {
	$("#" + prefix + "_" + serial + "_title").remove();
	$("#" + prefix + "_" + serial + "_content").remove();
	if (CLGZ == prefix) {	// 车辆故障
		clgzSerialArray.remove(serial);
	} else if (HDSBGZ == prefix) {	// 换电设备故障
		hdsbgzSerialArray.remove(serial);
	} else if (JKXTGZ == prefix) {	// 监控系统故障
		jkxtgzSerialArray.remove(serial);
	} else if (CDSBGZ == prefix) {	// 充电设备故障
		cdsbgzSerialArray.remove(serial);
	} else if (DCWHGZ == prefix) {	// 电池维护故障
		dcwhgzSerialArray.remove(serial);
	} else if (QTGZ == prefix) {	// 其他故障
		qtgzSerialArray.remove(serial);
	}
}

/**
 * 显示/隐藏条目
 * 
 * @param divId
 * @param prefix
 * @param serial
 */
function showHideItem(divId, prefix, serial) {
	var div = $("#" + divId);
	if (div.hasClass("expand")) {
		div.removeClass("expand");
		div.addClass("shrink");
		div.attr("title", "收起");
		$("#" + prefix + "_" + serial + "_content").show();
	} else {
		div.removeClass("shrink");
		div.addClass("expand");
		div.attr("title", "展开");
		$("#" + prefix + "_" + serial + "_content").hide();
	}
}

/**
 * 获取序号小标题
 * 
 * @param prefix 条目前缀
 * @param serial 序号
 * @param title 标题
 * @returns {String}
 */
function getHead(prefix, serial, title) {
	var divId = prefix + "_" + serial + "_bar";
	var html = 	"<div id=\"" + divId + "\" class=\"serial shrink\" onclick=\"showHideItem('" + divId + "', '" + prefix + "', " + serial + ")\" title=\"收起\">"
			+	"	<div class=\"rmimage\">"
			+	"		<a href='javascript:removeItem(\"" + prefix + "\", " + serial + ")'>"
			+	"			<img src=\"" + removeItemImage + "\" style=\"cursor: hand; border: none;\" title=\"" + title + "\" />"
			+	"		</a>"
			+	"	</div>"
			+	"	<div class=\"no\">编号：" + serial + "</div>"
			+	"</div>";
	return html;
}

/**
 * 获取表单模板
 * 
 * @param prefix 表单前缀
 * @param serial 序号
 * @returns
 */
function getContent(prefix, serial) {
	var html = 	"<table class=\"dutyLog_itemctxTable\">"
				+  	"	<tr>"
				+	"		<th style=\"width: 30%;\">发现人：</th>"
				+ 	"		<td><input type=\"text\" name=\"" + prefix + "_finder_" + serial + "\" style=\"width: 99%;\" /></td>"
				+ 	"	</tr>"
				+ 	"	<tr>"
				+ 	"		<th>发生时间：</th>"
				+ 	"		<td><input type=\"text\" name=\"" + prefix + "_findTimeShow_" + serial + "\" value=\"" + getTodayString() + "\" class=\"dateInput\" style=\"width: 200px;\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,readOnly:true,maxDate:'%y-%M-%d %H:%m:%s'})\" title=\"点击选择发现时间\" /></td>"
				+ 	"	</tr>"
				+ 	"	<tr>"
				+ 	"		<th>现象及原因分析：</th>"
				+ 	"		<td><input type=\"text\" name=\"" + prefix + "_reasonDesc_" + serial + "\" style=\"width: 99%\" /></td>"
				+ 	"	</tr>"
				+ 	"	<tr>"
				+ 	"		<th>处理方案：</th>"
				+ 	"		<td><input type=\"text\" name=\"" + prefix + "_dealPlan_" + serial + "\" style=\"width: 99%\" /></td>"
				+ 	"	</tr>"
				+ 	"	<tr>"
				+ 	"		<th>处理联系人及联系方式：</th>"
				+ 	"		<td><input type=\"text\" name=\"" + prefix + "_dealContact_" + serial + "\" style=\"width: 99%\" /></td>"
				+ 	"	</tr>"
				+ 	"	<tr>"
				+ 	"		<th>预计消除时间：</th>"
				+ 	"		<td><input type=\"text\" name=\"" + prefix + "_expectClearTimeShow_" + serial + "\" value=\"" + getTodayString() + "\" class=\"dateInput\" style=\"width: 200px;\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,readOnly:true})\" title=\"点击选择预计消除时间\" /></td>"
				+ 	"	</tr>"
				+ 	"	<tr>"
				+ 	"		<th>实际消除时间：</th>"
				+ 	"		<td>"
				+	"			<input type=\"text\" id=\"" + prefix + "_actualClearTimeShow_" + serial + "\" name=\"" + prefix + "_actualClearTimeShow_" + serial + "\" style=\"width: 200px;\" />"
				+	"			<input type=\"checkbox\" id=\"" + prefix + "_toNext_" + serial + "\" name=\"" + prefix + "_toNext_" + serial + "\" onclick=\"toNext('" + prefix + "', " + serial + ")\" />移交下值"
				+	"		</td>"
				+ 	"	</tr>"
				+ 	"</table>";
	return html;
}


function addOthItem() {
	var	serial = ++otherItemCount;
	var	title = OTHER_ITEM_RM;
	otherSerialArray.push(serial);
	var head = getOtherHead(serial, title);
	var content = getOtherContent(serial);
	$("#oth").append($("<tr id=\"oth_" + serial + "_title\"><th>" + head + "</th></tr>"));
	$("#oth").append($("<tr id=\"oth_" + serial + "_content\"><td>" + content + "</td></tr>"));
}

function removeOthItem(serial) {
	$("#oth_" + serial + "_title").remove();
	$("#oth_" + serial + "_content").remove();
	otherSerialArray.remove(serial);
}

function showHideOthItem(divId, serial) {
	var div = $("#" + divId);
	if (div.hasClass("expand")) {
		div.removeClass("expand");
		div.addClass("shrink");
		div.attr("title", "收起");
		$("#oth_" + serial + "_content").show();
	} else {
		div.removeClass("shrink");
		div.addClass("expand");
		div.attr("title", "展开");
		$("#oth_" + serial + "_content").hide();
	}
}

function getOtherHead(serial, title) {
	var divId = "oth_" + serial + "_bar";
	var html = 	"<div id=\"" + divId + "\" class=\"serial shrink\" onclick=\"showHideOthItem('" + divId + "', " + serial + ")\" title=\"收起\">"
			+	"	<div class=\"rmimage\">"
			+	"		<a href='javascript:removeOthItem(" + serial + ")'>"
			+	"			<img src=\"" + removeItemImage + "\" style=\"cursor: hand; border: none;\" title=\"" + title + "\" />"
			+	"		</a>"
			+	"	</div>"
			+	"	<div class=\"no\">编号：" + serial + "</div>"
			+	"</div>";
	return html;
}

function getOtherContent(serial) {
	var html = 	"<table class=\"dutyLog_itemctxTable\">"
			+	"	<tr>"
			+	"		<th>说明：</th>"
			+	"		<td><textarea rows=\"4\" name=\"describe_" + serial + "\" class=\"ta_adjust_80\"></textarea></td>"
			+	"	</tr>"
			+	"</table>";
	return html;
}

function isNumber(num) {
	if (!num)
		return false;
	if ($.trim(num) == "")
		return false;
	if (isNaN(num))
		return false;
	return true;
}

function checkNum(input) {
	if (!isNumber(input.value))
		input.value = 0;
}

/**
 * 选择值长
 * 
 * @param id
 */
function chooseManager(id) {
	$("#" + id).popCheck({
		title: "请勾选值长",
		data: managerArray,	// 对象数组
		height: 280,
		width: 360,
		showClear: true	// 是否显示清除按钮
	});
}

/**
 * 选择监控员
 */
function chooseMonitor() {
	$("#monitorStaff").popCheck({
		title: "请勾选监控员",
		data: monitorArray,	// 对象数组
		height: 280,
		width: 360,
		showClear: true	// 是否显示清除按钮
	});
}

/**
 * 选择操作员
 */
function chooseOperater() {
	$("#operater").popCheck({
		title: "请勾选操作员",
		data: operaterArray,	// 对象数组
		height: 280,
		width: 360,
		showClear: true	// 是否显示清除按钮
	});
}

/**
 * 选择检修员
 */
function chooseOverhaul() {
	$("#overhaul").popCheck({
		title: "请勾选检修员",
		data: overhaulArray,	// 对象数组
		height: 280,
		width: 360,
		showClear: true	// 是否显示清除按钮
	});
}

/**
 * 选择安检员
 */
function chooseSecurity() {
	$("#security").popCheck({
		title: "请勾选安检员",
		data: securityArray,	// 对象数组
		height: 280,
		width: 360,
		showClear: true	// 是否显示清除按钮
	});
}