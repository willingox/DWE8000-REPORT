<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>角色列表</title>
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
	
	/* 捕捉回车事件 */
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
		if(confirm('确定要删除该条记录？')){
			document.roleFixQuery.action = '${pageContext.request.contextPath}/usermgr/role.do?method=delete&key=' + key;
			document.roleFixQuery.submit();
			forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
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
	<!----------------------------------------错误或提示--------------------------------------------------------------->
	<!--如果有错误-->
	<TR height="1">
		<TD height="1">
			<div id="message">
			<!-- 错误 -->
			<logic:messagesPresent>
				<div class="error">
					<img src="${pageContext.request.contextPath}/images/iconWarning.png">
					<html:messages id="message">
						<bean:write name="message" />
						<br>
					</html:messages>
				</div>
			</logic:messagesPresent>
			<!-- 消息 -->
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
	<!----------------------------------------简单条件-------------------------------------------------------------->
	<!--如有页面需要-->
	<form action="${pageContext.request.contextPath}/usermgr/role.do?method=fixQuery" method="post" name="roleFixQuery">
	<TR>			 
		<TD style="width: 100%; height: 50px; text-align: center;">					
			<TABLE>	
				<TR>
					<TD>所属公司</TD>
					<TD>
						<input type="hidden" id="fixOrgId" name="fixOrgId" value="${fixOrgId}" >
						<input type="text" id="fixOrgName" name="fixOrgName" value="${fixOrgName}" readonly="true" onclick="openOrgTree()" class="popWindow" size="28" title="单击这里打开公司树，选择所属公司" />
						<input type="checkbox" name="bAllSubOrg" ${bAllSubOrg} />含下级
					</TD>
					<TD width="8"></TD></TD>
					<TD>角色名称</TD>
					<TD>
						<input type="text" id="fixRoleName" name="fixRoleName" onkeyup="catchEnter()" value="${fixRoleName}" class="formDetailTxt" size="15" >
					</TD>
					<TD>
						<a class="abtn3" href="javascript:query()"><span>查&nbsp;&nbsp;询</span></a>
					</TD>
				</TR>
			</TABLE>									
		</TD>				
	</TR>
	</form>
	<!----------------------------------------工具栏和错误（或数据表格）之间的间隔------------------------------------>
	 <TR>
	 	<TD class="tdDataGridSpace">
		</TD>
	 </TR>
	<!----------------------------------------数据表格开始-------------------------------------------------------------->
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
						<ec:column alias="NO" title="序号" cell="rowCount" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;" />
						<ec:column alias="roleName" title="角色名称" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:20%;">
							<a href="javascript:edit('${bean.roleId}')">${bean.roleName}</a>
						</ec:column>
						<ec:column property="xtOrganization.orgName" title="所属公司" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:45%;" />
						<ec:column alias="toUser" title="对应用户" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;">
							<a href="javascript:userListByRoleId('${bean.roleId}','${bean.roleName}')">详细</a>
						</ec:column>
						<ec:column alias="toPerm" title="对应权限" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;">
							<a href="javascript:roleOwnMenuTree('${bean.roleId}')">详细</a>
						</ec:column>
						<ec:column alias="edit" title="编辑" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
							<a href="javascript:edit('${bean.roleId}')"><img src="${pageContext.request.contextPath}/images/green/tool/edit.png" border="0" /></a>
						</ec:column>
						<ec:column alias="delete" title="删除" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
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
							<input type="button" onclick="add()" value="增加" class="btn_add">
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
