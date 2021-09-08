<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<title>菜单授权——角色树</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/dhtmlXTree.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXCommon.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXTree.js"></script>	
<script type="text/javascript">
<!--
	function init() {
		var node = getFirstSubNodeId(0);
		if(node)
			tree.openItem(node);
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
		var nodeId = tree.getSelectedItemId();
		if (-1 != nodeId) {
			parent.menuTreeIframe.location = '${pageContext.request.contextPath}/usermgr/menuAuthorize.do?method=getMenuTree&roleId=' + nodeId;
		}
	}
//-->
</script>
</head>
<body scroll="no" onload="init()">
<form name="submitform"></form>
<TABLE width="100%" height="100%" cellSpacing="0" cellPadding="0" border="0">
	<TR height="1">
		<TD height="1">
		</TD>
	</TR>
	<TR>
		<TD valign="top">
		<table height="100%" width="100%" border="0">
			<tr>
				<td valign="middle" style="height: 30px; width: 100%; text-align: center;">
					<span>请从角色树上选择要授权的角色</span>
				</td>
			</tr>
			<tr>
				<td valign="top" style="height: 100%; width: 100%; text-align: center;">
					<div id="role_tree" style="border: 1px solid #DBECE6; width:100%; height:100%; overflow:auto;"></div>
				</td>
			</tr>
			<tr><td height="10"></td></tr>
		</table>
		<script type="text/javascript">
		<!--
			tree = new dhtmlXTreeObject('role_tree', '100%', '100%', 0);
			tree.setImagePath('${pageContext.request.contextPath}/images/dhtmlX/dhtmlxTree/');	
			tree.setOnClickHandler(tonclick);
			var treeStr = "${roleTree}";
			tree.loadXMLString(treeStr);
		//-->
		</script>
	  	</TD>
	</TR>
</TABLE>
</body>
</html>
