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
	function init(){
		var nodeId = '${selectNodeId}';
		if(nodeId != ''){
			tree.openItem(nodeId);
			tree.selectItem(nodeId);
		}else{
			tree.openAllItems(0);
		}
	}
	
	function tonclick(){
		roleId = parent.roleListIframe.select_role.role.value;
		var nodeId = tree.getSelectedItemId();
		if(roleId == ''){
			alert('请选择要进行授权的角色！');
		}
		parent.permAssignIframe.location = '${pageContext.request.contextPath}/usermgr/permAssign.do?method=listValidPerms&resClassId=' + nodeId + '&roleId=' + roleId;
	}
//-->
</script>
</HEAD>
<BODY scroll="no" onload="">
<TABLE class="tabOutside" id="tabOutside">
	<TR>
		<TD class="tdDataGridSpace">
	</TD>
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
				tree = new dhtmlXTreeObject("treebox_tree","100%","100%",0);
				tree.setImagePath("${pageContext.request.contextPath}/images/dhtmlX/dhtmlxTree/");	
				tree.setOnClickHandler(tonclick);
				var str = "${xmlStr}";
				tree.loadXMLString(str);
			//-->
			</script>
	  	</TD>
	</TR>
</TABLE>
</BODY>
</html>
