<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>资源分类树</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/dhtmlXTree.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXCommon.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXTree.js"></script>	
<script type="text/javascript">
<!--
	function init() {
		var node;
		var bool = document.getElementById('bool').value;	
		var clsId = document.getElementById('selectedClsId').value;
		if(bool=='true') {
			node = getFirstSubNodeId(0);
		}else{
			node = clsId;
		}
		if(node != '_999999999') {	// 不是刷新
			tree.openItem(node);
			tree.selectItem(node, true);	// 选中该节点
		}else{	// 展开根节点
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
		var nodeId = tree.getSelectedItemId();
		if(nodeId == '_999999999') {	//refresh tree
			nodeId = document.getElementById('selectedClsId').value;
			document.location = '${pageContext.request.contextPath}/usermgr/resClass.do?method=tree&selectedClsId=' + nodeId + '&random=' + new Date().getTime();
		}else{
			submitform.action = '${pageContext.request.contextPath}/usermgr/resClass.do?method=listByParentId&selectedClsId=' + nodeId;
			submitform.submit();
		}
	}
//-->
</script>
</HEAD>
<BODY scroll="no" onload="init()">
<form id="submitform" name="submitform" method="post" target="resClassIframe">
<input type="hidden" id="bool" name="bool" value="${empty selectedClsId}">
<input type="hidden" id="selectedClsId" name="selectedClsId" value="${selectedClsId}">
</form>
<TABLE width="100%" height="100%" cellSpacing="0" cellPadding="0" border="0">
	<!----------------------------------------数据表格开始-------------------------------------------------------------->
	<TR>
		<TD valign="top" align="center">
			<table  height="100%" width="100%">
				<tr>
					<td valign="top" height="100%" width="100%">
						<div id="treebox_tree" style="width:100%; height:100%; overflow: auto;"></div>
					</td>
				</tr>
			</table>
			<script type="text/javascript">
			<!--
				tree = new dhtmlXTreeObject("treebox_tree", "100%", "100%", 0);
				tree.setImagePath("${pageContext.request.contextPath}/images/dhtmlX/dhtmlxTree/");	
				tree.setOnClickHandler(tonclick);
				var str = "${treeStr}";
				tree.loadXMLString(str);
				var im0, im1, im2;
				im0 = "refresh.png";
				im1 = "refresh.png";
				im2 = "refresh.png";
				tree.insertNewItem(0, "_999999999", "刷新树", 0, im0, im1, im2);
			//-->
			</script>
	  	</TD>
	</TR>
	<!--业务表格结束-->
</TABLE>
</head>
</html>