<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<meta http-equiv="Pragma" contect="no-cache">
<title>������</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/dhtmlXTree.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXCommon.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXTree.js"></script>	
<script type="text/javascript">
<!--
	window.name = 'targetWin';
	function add() {
		var nodeId = tree.getSelectedItemId()
		if(nodeId == '') {
			alert('��ѡ��һ��������˾��');
			return;
		}
	}
	
	function returnCurrentNode() {
		var systemTopMenuId = document.getElementById('systemTopMenuId').value;
		var nodeId = tree.getSelectedItemId();
		var nodeText;
		if(nodeId == '') {
			alert('��ѡ��ĳ����˾�µĲ��ţ�');
			return;
		}
		if(nodeId==systemTopMenuId){	//���Ϊ������˵�����ID��������Ϊ��
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
		if(nodes != ''){
			index = nodes.indexOf(',');
			if(index != -1) {
				nodes = nodes.substring(0, index);
			}
		}
		return nodes;
	}

	function selectOrg() {
		document.selectOrgForm.submit();
	}
//-->
</script>
<style type="text/css">
<!--
	table.search {
		border-right: 0px solid  #000080;
		border-top: 0px solid #000080;
		border-left: 0px solid #000080;
		border-bottom: 1px solid #000080;
	    border-collapse: collapse;
	    width: 100%; 
		height: 50;
	}
//-->
</style>
</HEAD>
<BODY scroll="no" onload="init()">
<form name="selectOrgForm" action="dept.do" target='targetWin' method="post">
<input type="hidden" name="method" value="getDeptTreeForWin">
<TABLE cellSpacing="0" cellPadding="0" class="search">	
	<TR>
		<TD align="right"><b>&nbsp;��˾��</b></TD>							
		<TD align="left">
			<select name="orgId" onchange="selectOrg();" class="selectDrop"> 
				<logic:iterate id="element" name="allOrgs">									       
		        	<option <logic:equal name="element" property="orgId" value="${orgId}">selected</logic:equal> value=<bean:write name="element" property="orgId"/>>
			        	<bean:write name="element" property="orgName"/>
			      	</option>						      							
			  	</logic:iterate> 
			</select>   
		</TD>
	</TR>
</TABLE>									
</form>	
<table width="100%" height="90%">
	<tr>
		<td height="1">				
		</td>
	</tr>
	<TR>
		<TD valign="top" align="center">
			<table height="100%" width="100%">
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
				var str = "${treeStr}";
				tree.loadXMLString(str);
			//-->
			</script>
	  	</TD>
	</TR>
	<tr>
		<td height="12" valign="top"><hr>
			<input type="hidden" id="systemTopMenuId" name="systemTopMenuId" value="${sessionScope.systemTopMenuId}">
			<a class="abtn3" href="#" onclick="javascript:returnCurrentNode()"><span>ȷ&nbsp;&nbsp;��</span></a>
			<a class="aBtn3" href="#" onclick="javascript:returnClose()"><span>ȡ&nbsp;&nbsp;��</span></a>
		</td>
	</tr>
</table>
</BODY>
</html>