<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>部门树</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/dhtmlXTree.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXCommon.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXTree.js"></script>	
<script type="text/javascript">
<!--
	window.onload = function() {
		var node = getFirstSubNodeId(0);	// default root node
		var parentDeptId = document.getElementById('parentDeptId').value;	
		if(parentDeptId != '') {
			node = parentDeptId;
		}
		if(node != '_999999999') {	//not refresh
			tree.openItem(node);
			tree.selectItem(node, true);	//select the first sub node
		}else{	//expand root node
			tree.openItem(0);
		}	
	}
			
	/** 获取第一个子节点ID，可能获取到空 */
	function getFirstSubNodeId(nodeId) {
		var index;
		var nodes = tree.getSubItems(nodeId);
		if(nodes != '') {
			index = nodes.indexOf(',');
			if(index != -1) {
				nodes = nodes.substring(0, index);
			}
		}
		return nodes;
	}
	
	function tonclick() {	
		var ownOrgId = getFirstSubNodeId(0);
		var parentDeptId;
		var nodeId = tree.getSelectedItemId();
		if(ownOrgId == nodeId){	// 点击的是组织节点
			parentDeptId = '';
		}else{
			if(nodeId!='_999999999'){
				parentDeptId = nodeId;
				document.getElementById('parentDeptId').value = nodeId;
			}else{
				parentDeptId = document.getElementById('parentDeptId').value;
			}
		}
		if(nodeId=='_999999999'){	//refresh tree
			document.location = '${pageContext.request.contextPath}/usermgr/dept.do?method=getDeptTree&ownOrgId=' + ownOrgId + '&parentDeptId=' + parentDeptId + '&random=' + new Date().getTime();
		}else{
			submitform.action = '${pageContext.request.contextPath}/usermgr/dept.do?method=listSubDeptByDeptId&ownOrgId=' + ownOrgId + '&parentDeptId=' + parentDeptId;
			submitform.target = 'deptListIframe';
			submitform.submit();
		}
	}

	function openOrgTree(){
		var ownOrgId = document.getElementById('ownOrgId');
		var ownOrgName = document.getElementById('ownOrgName');
		var value = window.showModalDialog('${pageContext.request.contextPath}/usermgr/org.do?method=listOrgTreeByLoingUser&rand=' + new Date().getTime(), '', 'dialogHeight:500px;dialogWidth:400px;help:no;status:no');	
		if(value){
			ownOrgId.value = value[0];
			document.location = '${pageContext.request.contextPath}/usermgr/dept.do?method=getDeptTree&ownOrgId=' + ownOrgId.value + '&random=' + new Date().getTime();
		}
	}
//-->
</script>
<style type="text/css">
<!--
	table.search {
		border-right: 0px solid #000080;
		border-top: 0px solid #000080;
		border-left: 0px solid #000080;
		border-bottom: 1px solid #000080;
	    border-collapse: collapse;
	    width: 100%; 
		height: 50;
	}
	
	.fLine {
		width: 100%;
		border: 1px solid red;
	}
//-->
</style>
</HEAD>
<BODY scroll="no">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td height="50">
	<TABLE width="100%" height="100%" cellSpacing="0" cellPadding="0">
		<form id="submitform" name="submitform" action="dept.do" method="post">
		<input type="hidden" name="method" value="getDeptTree">
		<input type="hidden" name="ownOrgId" id="ownOrgId" value="${ownOrgId}">
		<input type="hidden" id="parentDeptId" name="parentDeptId" value="${parentDeptId}">
		<TR>
			<TD><b>&nbsp;选择所属公司:</b></TD>
		</TR>
		<TR>
			<TD>
				&nbsp;<input name="ownOrgName" id="ownOrgName" value="${ownOrgName}" readonly="true" onclick="openOrgTree()" class="popWindow" size="28" title="单击这里打开公司树，选择所属公司" />
			</TD>
		</TR>
		</form>
	</TABLE>
	</td>	
</tr>
<tr>
	<td>
	<TABLE width="100%" height="100%" cellSpacing="0" cellPadding="0" border="0">
		<TR>
			<TD height="20"><b>&nbsp;部门树:</b></TD>
		</TR>
		<TR>
			<TD valign="top" align="center">
			<table height="100%" width="100%">
				<tr>
					<td valign="top" height="100%" width="100%">
						<div id="treebox_tree" style="width:100%; height:100%; overflow:auto;"></div>
					</td>
				</tr>
			</table>
			<script type="text/javascript">
			<!--
				tree = new dhtmlXTreeObject('treebox_tree', '100%', '100%', 0);
				tree.setImagePath('${pageContext.request.contextPath}/images/dhtmlX/dhtmlxTree/');	
				tree.setOnClickHandler(tonclick);
				var str = "${treeStr}";
				tree.loadXMLString(str);
				var im0, im1, im2;
				im0 = 'refresh.png';
				im1 = 'refresh.png';
				im2 = 'refresh.png';
				tree.insertNewItem(0, '_999999999', '刷新树', 0, im0, im1, im2);
			//-->
			</script>
		  	</TD>
		</TR>
	</TABLE>
	</td>	
</tr>
</table>
</BODY>
</html>