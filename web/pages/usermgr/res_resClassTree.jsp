<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>资源分类树</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/dhtmlXTree.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXCommon.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXTree.js"></script>
<script type="text/javascript">
<!--
	function init() {
		tree.openItem(0);
		var node = getFirstSubNodeId(0);
		if (node) {
			tree.openItem(node);
			tree.selectItem(node, true);	// 选中该节点
		}
	}
	
	function tonclick() {
		var nodeId = tree.getSelectedItemId()
		document.submitform.action = '${pageContext.request.contextPath}/usermgr/res.do?method=listResByClassId&resClassId=' + nodeId;
		document.submitform.submit();
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
//-->
</script>
</HEAD>
<BODY scroll="no" onload="init()">
<form id="submitform" name="submitform"  method="post" target="resListIframe"></form>
<table width="100%" height="100%" cellSpacing=0 cellPadding=0 border=0>
	<TR>
		<TD valign="top" align="center">
		<TABLE  height="100%" width="100%">
			<tr>
				<td valign="top"  height="100%" width="100%">
					<div id="treebox_tree" style="width:100%; height:100%; overflow:auto;"></div>
				</td>
			</tr>
		</table>
		<script type="text/javascript">
		<!--
			tree = new dhtmlXTreeObject("treebox_tree","100%","100%",0);
			tree.setImagePath("${pageContext.request.contextPath}/images/dhtmlX/dhtmlxTree/");	
			tree.setOnClickHandler(tonclick);
			var str = "${str}";
			tree.loadXMLString(str);
		//-->
		</script>
	  	</TD>
	</TR>
</TABLE>
</BODY>
</html>
