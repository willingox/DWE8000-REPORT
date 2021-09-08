/** JavaScript���麯����չ */
/**
 * ɾ������Ԫ��
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

/** ȫ�ֱ������� */
CLEARTime = 3000;
CLGZ = "clgz";	// ��������
CLGZ_ITEM_RM = "ɾ������������������";
HDSBGZ = "hdsbgz";	// �����豸����
HDSBGZ_ITEM_RM = "ɾ�����������豸��������";
JKXTGZ = "jkxtgz";	// ���ϵͳ����
JKXTGZ_ITEM_RM = "ɾ���������ϵͳ��������";
CDSBGZ = "cdsbgz";	// ����豸����
CDSBGZ_ITEM_RM = "ɾ����������豸��������";
DCWHGZ = "dcwhgz";	// ���ά������
DCWHGZ_ITEM_RM = "ɾ���������ά����������";
QTGZ = "qtgz";	// ��������
QTGZ_ITEM_RM = "ɾ������������������";
OTHER_ITEM_RM = "ɾ������������Ҫ���Ӱ���ڸ��ٵ���������";

$(document).ready(function() {
	window.setTimeout(removeMessage, CLEARTime);
	
	if ($("#battDistributed").val() == "") {
		$("#battDistributed").html("1A:&#13;1B:&#13;2A:&#13;2B:&#13;3A:&#13;3B:&#13;����:&#13;����:&#13;");
	}
		
	
});

/**
 * �����޸�
 */
function save() {
	cleanOutItem();
	dutyLogForm.method.value = "save";
	dutyLogForm.submit();
	forbidSubmit();
	popWaiting("���ڱ�����־�����Ժ�...");
}

/**
 * �ƽ���ֵ������
 */
function lock() {
	if (confirm("�ƽ���ֵ��ǰ��־�����ٱ�����༭��ɾ�������Զ������հ���ֵ��־��ȷ���ƽ���")) {
		cleanOutItem();
		dutyLogForm.method.value = "lock";
		dutyLogForm.submit();
		forbidSubmit();
		popWaiting("���ڱ�����־�����Ժ�...");
	}
}

/**
 * ������־�б�
 */
function bk2lst() {
	if (confirm("��Ϣ��δ���棬ȷ������������")) {
		dutyLogForm.method.value = "list";
		dutyLogForm.submit();
	}
}

/**
 * ����EXCEL
 */
function excel() {
	cleanOutItem();
	dutyLogForm.method.value = "toExcel";
	dutyLogForm.submit();
}

/**
 * ������־��Ŀ
 */
function cleanOutItem() {
	$("#clgzSerial").val(clgzSerialArray.join());	// ������������������ַ�������Ӣ�Ķ��ŷָ�
	$("#hdsbgzSerial").val(hdsbgzSerialArray.join());	// �����豸��������
	$("#jkxtgzSerial").val(jkxtgzSerialArray.join());	// ���ϵͳ��������
	$("#cdsbgzSerial").val(cdsbgzSerialArray.join());	// ����豸��������
	$("#dcwhgzSerial").val(dcwhgzSerialArray.join());	// ���ά����������
	$("#qtgzSerial").val(qtgzSerialArray.join());	// ������������
	$("#otherSerial").val(otherSerialArray.join());	// ��������
}

function removeMessage() {
	$("#message").css("display", "none");
}

function toNext(prefix, serial) {
	var checkbox = "#" + prefix + "_toNext_" + serial;
	var text = "#" + prefix + "_actualClearTimeShow_" + serial;
	if ($(checkbox).attr("checked")) {
		$(text).val("�ƽ���ֵ");
	} else {
		$(text).val("");
	}
}

/**
 * ������Ŀ
 * 
 * @param tableId
 */
function addItem(tableId) {
	var serial, title;
	if (CLGZ == tableId) {	// ��������
		serial = ++clgzItemCount;
		title = CLGZ_ITEM_RM;
		clgzSerialArray.push(serial);
	} else if (HDSBGZ == tableId) {	// �����豸����
		serial = ++hdsbgzItemCount;
		title = HDSBGZ_ITEM_RM;
		hdsbgzSerialArray.push(serial);
	} else if (JKXTGZ == tableId) {	// ���ϵͳ����
		serial = ++jkxtgzItemCount;
		title = JKXTGZ_ITEM_RM;
		jkxtgzSerialArray.push(serial);
	} else if (CDSBGZ == tableId) {	// ����豸����
		serial = ++cdsbgzItemCount;
		title = CDSBGZ_ITEM_RM;
		cdsbgzSerialArray.push(serial);
	} else if (DCWHGZ == tableId) {	// ���ά������
		serial = ++dcwhgzItemCount;
		title = DCWHGZ_ITEM_RM;
		dcwhgzSerialArray.push(serial);
	} else if (QTGZ == tableId) {	// ��������
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
 * ɾ����Ŀ
 * 
 * @param prefix
 */
function removeItem(prefix, serial) {
	$("#" + prefix + "_" + serial + "_title").remove();
	$("#" + prefix + "_" + serial + "_content").remove();
	if (CLGZ == prefix) {	// ��������
		clgzSerialArray.remove(serial);
	} else if (HDSBGZ == prefix) {	// �����豸����
		hdsbgzSerialArray.remove(serial);
	} else if (JKXTGZ == prefix) {	// ���ϵͳ����
		jkxtgzSerialArray.remove(serial);
	} else if (CDSBGZ == prefix) {	// ����豸����
		cdsbgzSerialArray.remove(serial);
	} else if (DCWHGZ == prefix) {	// ���ά������
		dcwhgzSerialArray.remove(serial);
	} else if (QTGZ == prefix) {	// ��������
		qtgzSerialArray.remove(serial);
	}
}

/**
 * ��ʾ/������Ŀ
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
		div.attr("title", "����");
		$("#" + prefix + "_" + serial + "_content").show();
	} else {
		div.removeClass("shrink");
		div.addClass("expand");
		div.attr("title", "չ��");
		$("#" + prefix + "_" + serial + "_content").hide();
	}
}

/**
 * ��ȡ���С����
 * 
 * @param prefix ��Ŀǰ׺
 * @param serial ���
 * @param title ����
 * @returns {String}
 */
function getHead(prefix, serial, title) {
	var divId = prefix + "_" + serial + "_bar";
	var html = 	"<div id=\"" + divId + "\" class=\"serial shrink\" onclick=\"showHideItem('" + divId + "', '" + prefix + "', " + serial + ")\" title=\"����\">"
			+	"	<div class=\"rmimage\">"
			+	"		<a href='javascript:removeItem(\"" + prefix + "\", " + serial + ")'>"
			+	"			<img src=\"" + removeItemImage + "\" style=\"cursor: hand; border: none;\" title=\"" + title + "\" />"
			+	"		</a>"
			+	"	</div>"
			+	"	<div class=\"no\">��ţ�" + serial + "</div>"
			+	"</div>";
	return html;
}

/**
 * ��ȡ��ģ��
 * 
 * @param prefix ��ǰ׺
 * @param serial ���
 * @returns
 */
function getContent(prefix, serial) {
	var html = 	"<table class=\"dutyLog_itemctxTable\">"
				+  	"	<tr>"
				+	"		<th style=\"width: 30%;\">�����ˣ�</th>"
				+ 	"		<td><input type=\"text\" name=\"" + prefix + "_finder_" + serial + "\" style=\"width: 99%;\" /></td>"
				+ 	"	</tr>"
				+ 	"	<tr>"
				+ 	"		<th>����ʱ�䣺</th>"
				+ 	"		<td><input type=\"text\" name=\"" + prefix + "_findTimeShow_" + serial + "\" value=\"" + getTodayString() + "\" class=\"dateInput\" style=\"width: 200px;\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,readOnly:true,maxDate:'%y-%M-%d %H:%m:%s'})\" title=\"���ѡ����ʱ��\" /></td>"
				+ 	"	</tr>"
				+ 	"	<tr>"
				+ 	"		<th>����ԭ�������</th>"
				+ 	"		<td><input type=\"text\" name=\"" + prefix + "_reasonDesc_" + serial + "\" style=\"width: 99%\" /></td>"
				+ 	"	</tr>"
				+ 	"	<tr>"
				+ 	"		<th>��������</th>"
				+ 	"		<td><input type=\"text\" name=\"" + prefix + "_dealPlan_" + serial + "\" style=\"width: 99%\" /></td>"
				+ 	"	</tr>"
				+ 	"	<tr>"
				+ 	"		<th>������ϵ�˼���ϵ��ʽ��</th>"
				+ 	"		<td><input type=\"text\" name=\"" + prefix + "_dealContact_" + serial + "\" style=\"width: 99%\" /></td>"
				+ 	"	</tr>"
				+ 	"	<tr>"
				+ 	"		<th>Ԥ������ʱ�䣺</th>"
				+ 	"		<td><input type=\"text\" name=\"" + prefix + "_expectClearTimeShow_" + serial + "\" value=\"" + getTodayString() + "\" class=\"dateInput\" style=\"width: 200px;\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,readOnly:true})\" title=\"���ѡ��Ԥ������ʱ��\" /></td>"
				+ 	"	</tr>"
				+ 	"	<tr>"
				+ 	"		<th>ʵ������ʱ�䣺</th>"
				+ 	"		<td>"
				+	"			<input type=\"text\" id=\"" + prefix + "_actualClearTimeShow_" + serial + "\" name=\"" + prefix + "_actualClearTimeShow_" + serial + "\" style=\"width: 200px;\" />"
				+	"			<input type=\"checkbox\" id=\"" + prefix + "_toNext_" + serial + "\" name=\"" + prefix + "_toNext_" + serial + "\" onclick=\"toNext('" + prefix + "', " + serial + ")\" />�ƽ���ֵ"
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
		div.attr("title", "����");
		$("#oth_" + serial + "_content").show();
	} else {
		div.removeClass("shrink");
		div.addClass("expand");
		div.attr("title", "չ��");
		$("#oth_" + serial + "_content").hide();
	}
}

function getOtherHead(serial, title) {
	var divId = "oth_" + serial + "_bar";
	var html = 	"<div id=\"" + divId + "\" class=\"serial shrink\" onclick=\"showHideOthItem('" + divId + "', " + serial + ")\" title=\"����\">"
			+	"	<div class=\"rmimage\">"
			+	"		<a href='javascript:removeOthItem(" + serial + ")'>"
			+	"			<img src=\"" + removeItemImage + "\" style=\"cursor: hand; border: none;\" title=\"" + title + "\" />"
			+	"		</a>"
			+	"	</div>"
			+	"	<div class=\"no\">��ţ�" + serial + "</div>"
			+	"</div>";
	return html;
}

function getOtherContent(serial) {
	var html = 	"<table class=\"dutyLog_itemctxTable\">"
			+	"	<tr>"
			+	"		<th>˵����</th>"
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
 * ѡ��ֵ��
 * 
 * @param id
 */
function chooseManager(id) {
	$("#" + id).popCheck({
		title: "�빴ѡֵ��",
		data: managerArray,	// ��������
		height: 280,
		width: 360,
		showClear: true	// �Ƿ���ʾ�����ť
	});
}

/**
 * ѡ����Ա
 */
function chooseMonitor() {
	$("#monitorStaff").popCheck({
		title: "�빴ѡ���Ա",
		data: monitorArray,	// ��������
		height: 280,
		width: 360,
		showClear: true	// �Ƿ���ʾ�����ť
	});
}

/**
 * ѡ�����Ա
 */
function chooseOperater() {
	$("#operater").popCheck({
		title: "�빴ѡ����Ա",
		data: operaterArray,	// ��������
		height: 280,
		width: 360,
		showClear: true	// �Ƿ���ʾ�����ť
	});
}

/**
 * ѡ�����Ա
 */
function chooseOverhaul() {
	$("#overhaul").popCheck({
		title: "�빴ѡ����Ա",
		data: overhaulArray,	// ��������
		height: 280,
		width: 360,
		showClear: true	// �Ƿ���ʾ�����ť
	});
}

/**
 * ѡ�񰲼�Ա
 */
function chooseSecurity() {
	$("#security").popCheck({
		title: "�빴ѡ����Ա",
		data: securityArray,	// ��������
		height: 280,
		width: 360,
		showClear: true	// �Ƿ���ʾ�����ť
	});
}