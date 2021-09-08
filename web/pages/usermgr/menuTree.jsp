<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>菜单树</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/dhtmlXTree.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXCommon.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXTree.js"></script>	
<script type="text/javascript">
<!--
	function init() {
		var node;
		var selectedMenuId = xtMenuForm.selectedMenuId.value;
		if(selectedMenuId == '') {
			node = getFirstSubNodeId(0);
		}else{
			node = selectedMenuId;
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
		var selectedNodeId = tree.getSelectedItemId();
		var lastSelectedMenuId = xtMenuForm.selectedMenuId.value;
		if(selectedNodeId == '_999999999') {	// 刷新树
			document.location = '${pageContext.request.contextPath}/usermgr/menu.do?method=getMenuTree&selectedMenuId=' + lastSelectedMenuId + '&random=' + new Date().getTime();
		}else{
			xtMenuForm.selectedMenuId.value = selectedNodeId;
			xtMenuForm.target = 'menuListIframe';
			xtMenuForm.submit();
		}
	}
//-->
</script>
</HEAD>
<BODY scroll="no" onload="init()">
<html:form action="menu" method="post">
<html:hidden property="method" value="listSubMenusByMenuId" />
<html:hidden property="selectedMenuId" />
</html:form>
<TABLE width="100%" height="100%" cellSpacing="0" cellPadding="0" border="0">
	<TR>
		<TD valign="top" align="center">
		<table  height="100%" width="100%">
			<tr>
				<td valign="top" height="100%" width="100%">
					<div id="treebox_tree" style="width:100%; height:100%; overflow:auto;"></div>
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
</BODY>
</html>