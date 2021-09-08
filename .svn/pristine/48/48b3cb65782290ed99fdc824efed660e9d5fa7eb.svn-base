<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>部门列表</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/ec/js/extremecomponents.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript">
<!--
	function add() {
		xtDeptForm.method.value = 'add';
		xtDeptForm.submit();
	}
	
	function edit(key) {
		xtDeptForm.method.value = 'edit';
		xtDeptForm.key.value = key;
		xtDeptForm.submit();
	}
	
	function del(key){
		if(confirm("确定要删除该条记录？")) {
			xtDeptForm.method.value = 'delete';
			xtDeptForm.key.value = key;
			xtDeptForm.submit();
			forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
		}
	}

	var CLEARTime = 3000;
	function removeMessage() {
		$('#message').css('display', 'none');
	}
		
	window.onload = function() {
		window.setTimeout(removeMessage, CLEARTime);
	}
//-->
</script>
</HEAD>
<BODY scroll="no">
<html:form action="dept" method="post">
<html:hidden property="ownOrgId" />
<html:hidden property="parentDeptId" />
<html:hidden property="method" value="listSubDeptByDeptId" />
<html:hidden property="key" value="" />
</html:form>
<TABLE class="tabOutside" id="tabOutside">
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
	<TR>
		<TD class="tdDataGridSpace">
		</TD>
	</TR>
	<TR>
		<TD height="" valign="top" align="center">
		<div style="width: 100%; height: 100%; overflow: auto;">
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="bottom" align="center" width="100%">
				<ec:table 
					items="depts" 
					var="bean" 
					action="${pageContext.request.contextPath}/usermgr/dept.do" 
					view="compact" 
					imagePath="${pageContext.request.contextPath}/images/ecTable/compact/*.gif"  
					filterable="false"
					sortable="false" 
					showExports="false" 
					autoIncludeParameters="true"
					width="100%" 
					styleClass="ecTable"
					rowsDisplayed="15">
					<ec:parameter name="method" value="listSubDeptByDeptId" />
					<ec:row highlightRow="true">
						<ec:column alias="NO" title="序号" cell="rowCount" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;" />
						<ec:column alias="orgName" title="部门名称" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:25%;">
							<a href="javascript:edit('${bean.deptId}')">${bean.deptName}</a>
						</ec:column>
						<ec:column property="xtOrganization.orgName" title="所属公司" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:20%;" />
						<ec:column property="deptTel" title="电话" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:15%;" />
						<ec:column property="xtDept.deptName" title="上级部门" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:25%;" />
						<ec:column alias="edit" title="编辑" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
							<a href="javascript:edit('${bean.deptId}')"><img src="${pageContext.request.contextPath}/images/green/tool/edit.png" border="0" /></a>
						</ec:column>
						<ec:column alias="delete" title="删除" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
							<a href="javascript:del('${bean.deptId}')"><img src="${pageContext.request.contextPath}/images/green/tool/delete.png" border="0" /></a>
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