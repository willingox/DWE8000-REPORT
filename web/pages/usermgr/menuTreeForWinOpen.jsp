<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>�˵���</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/dhtmlXTree.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXCommon.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXTree.js"></script>
<script type="text/javascript">
<!--
	function add(){
		var nodeId = tree.getSelectedItemId()
		if(nodeId == '') {
			alert('��ѡ��ĳһ�����࣡');
			return;
		}
	}
	
	function returnCurrentNode() {
		var systemTopMenuId = document.getElementById('systemTopMenuId').value;
		var nodeId = tree.getSelectedItemId();
		var nodeText;
		if(nodeId == '') {
			alert('��ѡ��ĳһ�����࣡');
			return;
		}
		if(nodeId==systemTopMenuId) {	//���Ϊ������˵�����ID��������Ϊ��
			nodeId = '';
			nodeText = '';
		}else{
			nodeText = tree.getSelectedItemText();
		}
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
		var node = getFirstSubNodeId(0);
		if(node != '') {
			tree.openItem(node);
		}else{
			tree.openItem(0);
		}				
	}
	
	/** ��ȡ��һ���ӽڵ�ID�����ܻ�ȡ���� */
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
<TABLE width="100%" height="100%" >
	<TR>
		<TD height="8">				
		</TD>
	</TR>
	<TR>
		<TD valign="top" align="center">
		<table  height="100%" width="100%">
			<tr>
				<td valign="top"  height="100%" width="100%">
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
			tree.setXMLAutoLoading("${pageContext.request.contextPath}/usermgr/menu.do?method=listNextMenu");
			var str = "${treeStr}";
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
					<a class="abtn3" href="#" onclick="javascript:returnCurrentNode()"><span>ȷ&nbsp;&nbsp;��</span></a>
					<a class="aBtn3" href="#" onclick="javascript:returnClose()"><span>ȡ&nbsp;&nbsp;��</span></a>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</TABLE>
<form>
<input type="hidden" id="systemTopMenuId" name="systemTopMenuId" value="${sessionScope.systemTopMenuId}">
</form>
</BODY>
</html>
