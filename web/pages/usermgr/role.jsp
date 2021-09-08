<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>��ɫ�б�</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/ec/js/extremecomponents.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript">
<!--
	function userListByRoleId(roleId,roleName) {
		window.showModalDialog('${pageContext.request.contextPath}/usermgr/role.do?method=userListByRoleId&roleId=' + roleId + '&roleName=' + roleName + '&rand=' + new Date().getTime(), '', 'dialogHeight:400px;dialogWidth:600px;help:no;status:no');	
	}
	
	function permListByRoleId(roleId,roleName) {
		window.showModalDialog('${pageContext.request.contextPath}/usermgr/permAssign.do?method=permListByRoleId&roleId=' + roleId + '&roleName=' + roleName + '&rand=' + new Date().getTime(), '', 'dialogHeight:600px;dialogWidth:550px;help:no;status:no');	
	}
	
	function roleOwnMenuTree(roleId) {
		window.showModalDialog('${pageContext.request.contextPath}/usermgr/role.do?method=getMenuTreeOfRole&roleId=' + roleId + '&rand=' + new Date().getTime(), '', 'dialogHeight:500px;dialogWidth:300px;help:no;status:no');	
	}
	
	function openOrgTree() {
		var value = window.showModalDialog('${pageContext.request.contextPath}/usermgr/org.do?method=listOrgTreeByLoingUser&rand=' + new Date().getTime(), '', 'dialogHeight:500px;dialogWidth:400px;help:no;status:no');	
		var orgName = $('#fixOrgName');
		if(value) {
			$('#fixOrgId').val(value[0]);
			$('#fixOrgName').val(value[1]);
		}
	}
	
	function query() {
		roleFixQuery.submit();
	}
	
	/* ��׽�س��¼� */
	function catchEnter() { 
		if ((event.keyCode==13)){ 
			query();
		} 
	}
	
	function add() {
		document.roleFixQuery.action = '${pageContext.request.contextPath}/usermgr/role.do?method=add';
		document.roleFixQuery.submit();
	}
	
	function edit(key) {
		document.roleFixQuery.action = '${pageContext.request.contextPath}/usermgr/role.do?method=edit&key=' + key;
		document.roleFixQuery.submit();
	}
	
	function del(key) {
		if(confirm('ȷ��Ҫɾ��������¼��')){
			document.roleFixQuery.action = '${pageContext.request.contextPath}/usermgr/role.do?method=delete&key=' + key;
			document.roleFixQuery.submit();
			forbidSubmit();	// ��ֹ���а�ť�����ӵȣ�λ��{ebizapp.js}
		}
	}
	
	var CLEARTime = 3000;
	function removeMessage() {
		$('#message').css('display', 'none');
	}
		
	window.onload = function() {
		window.setTimeout(removeMessage,CLEARTime);
	}
//-->
</script>
</HEAD>
<BODY scroll="no">
<TABLE class="tabOutside" id="tabOutside">
	<!----------------------------------------�������ʾ--------------------------------------------------------------->
	<!--����д���-->
	<TR height="1">
		<TD height="1">
			<div id="message">
			<!-- ���� -->
			<logic:messagesPresent>
				<div class="error">
					<img src="${pageContext.request.contextPath}/images/iconWarning.png">
					<html:messages id="message">
						<bean:write name="message" />
						<br>
					</html:messages>
				</div>
			</logic:messagesPresent>
			<!-- ��Ϣ -->
			<logic:messagesPresent message="true">
				<div class="error" id="loginError">
					<img src="${pageContext.request.contextPath}/images/iconInformation.png">
					<html:messages id="message" message="true">
						<bean:write name="message" />
						<br>
					</html:messages>
				</div>
			</logic:messagesPresent>
			</div>
		</TD>
	</TR>
	<!----------------------------------------������-------------------------------------------------------------->
	<!--����ҳ����Ҫ-->
	<form action="${pageContext.request.contextPath}/usermgr/role.do?method=fixQuery" method="post" name="roleFixQuery">
	<TR>			 
		<TD style="width: 100%; height: 50px; text-align: center;">					
			<TABLE>	
				<TR>
					<TD>������˾</TD>
					<TD>
						<input type="hidden" id="fixOrgId" name="fixOrgId" value="${fixOrgId}" >
						<input type="text" id="fixOrgName" name="fixOrgName" value="${fixOrgName}" readonly="true" onclick="openOrgTree()" class="popWindow" size="28" title="��������򿪹�˾����ѡ��������˾" />
						<input type="checkbox" name="bAllSubOrg" ${bAllSubOrg} />���¼�
					</TD>
					<TD width="8"></TD></TD>
					<TD>��ɫ����</TD>
					<TD>
						<input type="text" id="fixRoleName" name="fixRoleName" onkeyup="catchEnter()" value="${fixRoleName}" class="formDetailTxt" size="15" >
					</TD>
					<TD>
						<a class="abtn3" href="javascript:query()"><span>��&nbsp;&nbsp;ѯ</span></a>
					</TD>
				</TR>
			</TABLE>									
		</TD>				
	</TR>
	</form>
	<!----------------------------------------�������ʹ��󣨻����ݱ��֮��ļ��------------------------------------>
	 <TR>
	 	<TD class="tdDataGridSpace">
		</TD>
	 </TR>
	<!----------------------------------------���ݱ��ʼ-------------------------------------------------------------->
	<TR>
		<TD height="" valign="top" align="center">
		<div style="width: 100%; height: 100%; overflow: auto;">
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="bottom" align="center" width="100%">
				<ec:table 
					items="roles" 
					var="bean" 
					action="${pageContext.request.contextPath}/usermgr/role.do" 
					view="compact" 
					imagePath="${pageContext.request.contextPath}/images/ecTable/compact/*.gif"  
					filterable="false"
					sortable="false" 
					showExports="false" 
					autoIncludeParameters="true"
					width="100%" 
					styleClass="ecTable"
					rowsDisplayed="15">
					<ec:parameter name="method" value="list" />
					<ec:row highlightRow="true">
						<ec:column alias="NO" title="���" cell="rowCount" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;" />
						<ec:column alias="roleName" title="��ɫ����" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:20%;">
							<a href="javascript:edit('${bean.roleId}')">${bean.roleName}</a>
						</ec:column>
						<ec:column property="xtOrganization.orgName" title="������˾" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:45%;" />
						<ec:column alias="toUser" title="��Ӧ�û�" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;">
							<a href="javascript:userListByRoleId('${bean.roleId}','${bean.roleName}')">��ϸ</a>
						</ec:column>
						<ec:column alias="toPerm" title="��ӦȨ��" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;">
							<a href="javascript:roleOwnMenuTree('${bean.roleId}')">��ϸ</a>
						</ec:column>
						<ec:column alias="edit" title="�༭" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
							<a href="javascript:edit('${bean.roleId}')"><img src="${pageContext.request.contextPath}/images/green/tool/edit.png" border="0" /></a>
						</ec:column>
						<ec:column alias="delete" title="ɾ��" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
							<a href="javascript:del('${bean.roleId}')"><img src="${pageContext.request.contextPath}/images/green/tool/delete.png" border="0" /></a>
						</ec:column>
					</ec:row>
				</ec:table>
				</td>
			</tr>
			<tr>
				<td width="100%" align="center">
				<table border="0" width="99%" cellspacing="0" cellpadding="0">
					<tr>
						<td class="ecTableFootToolBar">											 
							<input type="button" onclick="add()" value="����" class="btn_add">
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="100%"></td>
			</tr>
		</table>
		</div>
		</TD>
	</TR>
</TABLE>
</BODY>
</html>
