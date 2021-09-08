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
	function add() {
		var nodeId = tree.getSelectedItemId()
		if(nodeId == '') {
			alert('请选择某一个分类！');
			return;
		}
	}
	
	function returnCurrentNode() {
		var nodeId = tree.getSelectedItemId();
		if(nodeId == '') {
			alert('请选择某一个分类！');
			return;
		}
		var nodeText =  tree.getSelectedItemText();
		var returnArray = new Array();
		returnArray[0] = nodeId;
		returnArray[1] = nodeText;
		window.returnValue = returnArray;
		self.close();
	}
	
	function returnClose(){
		self.close();
	}
	
	function init() {
		var nodeId = '${selectNodeId}';
		if(nodeId != '') {
			tree.openItem(nodeId);
			tree.selectItem(nodeId);
		}else{
			tree.openAllItems(0);
		}
	}
//-->
</script>
</HEAD>
<BODY scroll="no" onload="init()">
	<table width="100%" height="100%" >
		<tr>
			<td height="8">				
			</td>
		</tr>
		<TR>
			<TD valign="top" align="center">
			<TABLE  height="100%" width="100%">
				<tr>
					<td valign="top" height="100%" width="100%">
						<div id="treebox_tree" style="width:100%; height:100%; overflow:auto;"></div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			<script type="text/javascript">
			<!--
				tree = new dhtmlXTreeObject("treebox_tree","100%","100%",0);
				tree.setImagePath("${pageContext.request.contextPath}/images/dhtmlX/dhtmlxTree/");	
				var str = "${str}";
				tree.loadXMLString(str);
			//-->
			</script>
		  	</TD>
		</TR>
		<tr>
			<td height="50">
			<hr />
			<table width="100%" height="100%">
				<tr>
					<td width="60%">&nbsp;</td>
					<td width="40%" valign="top">
						<a class="abtn3" href="#" onclick="javascript:returnCurrentNode()"><span>确&nbsp;&nbsp;定</span></a>
						<a class="aBtn3" href="#" onclick="javascript:returnClose()"><span>取&nbsp;&nbsp;消</span></a>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</TABLE>
</BODY>
</html>
