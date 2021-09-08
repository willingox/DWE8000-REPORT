<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>当前角色已授权的全部菜单</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/dhtmlxTree/dhtmlxtree.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlxTree/dhtmlxcommon.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlxTree/dhtmlxtree.js"></script>
<script type="text/javascript">
<!--
	window.onload = function() {
		if (tree) {
			tree.openAllItems('-1');
		}
	}
//-->
</script>
</HEAD>
<BODY scroll="no">
<TABLE width="100%" height="100%" cellSpacing="0" cellPadding="0" border="0">
	<TR>
		<TD valign="top">
		<table height="100%" width="100%" border="0">
			<tr>
				<td valign="top" height="100%" width="70%">
					<div id="menu_tree" style="border: 1px solid #DBECE6; width:100%; height:100%; overflow:auto; padding-top: 10px;"></div>
				</td>
			</tr>
			<tr><td height="10"></td></tr>
		</table>
		<script type="text/javascript">
		<!--
			tree = new dhtmlXTreeObject("menu_tree","100%","100%",0);
			tree.setSkin('dhx_skyblue');
			tree.setImagePath("${pageContext.request.contextPath}/scripts/dhtmlxTree/imgs/csh_bluebooks/");
			tree.enableCheckBoxes(false);
			tree.enableThreeStateCheckboxes(false);
			// next line code is set tree readonly
			//tree.attachEvent("onBeforeCheck", function(id,state){return false});
			var treeStr = "${menuTree}";
			tree.loadXMLString(treeStr);
		//-->
		</script>
	  	</TD>
	</TR>
</TABLE>
</BODY>
</html>