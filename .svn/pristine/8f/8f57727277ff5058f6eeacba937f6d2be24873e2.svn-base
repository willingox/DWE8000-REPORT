<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>公司列表</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/ec/js/extremecomponents.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript">
<!--
	function openOrgTree(){
		var value = window.showModalDialog('${pageContext.request.contextPath}/usermgr/org.do?method=listOrgTreeByLoingUser&rand=' + new Date().getTime(), '', 'dialogHeight:500px;dialogWidth:400px;help:no;status:no');	
		if(value){
			$('#fixOrgId').val(value[0]);
			$('#fixOrgName').val(value[1]);
		}
	}
	
	function add(){
		document.orgFixQuery.action = '${pageContext.request.contextPath}/usermgr/org.do?method=add&orgId=${requestScope.selectedOrgId}';
		document.orgFixQuery.submit();
	}
	
	function edit(key){
		document.orgFixQuery.action = '${pageContext.request.contextPath}/usermgr/org.do?method=edit&key=' + key;
		document.orgFixQuery.submit();
	}
	
	function del(key){
		if(confirm('确定要删除该条记录？')){
			document.orgFixQuery.action = '${pageContext.request.contextPath}/usermgr/org.do?method=delete&key=' + key;
			document.orgFixQuery.submit();
			forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
		}
	}
	
	var CLEARTime = 3000;
	function removeMessage(){
		$('#message').css('display', 'none');
	}
		
	window.onload = function(){
		window.setTimeout(removeMessage,CLEARTime);
	}
//-->
</script>
</HEAD>
<BODY scroll="no">
<form action="${pageContext.request.contextPath}/usermgr/org.do" method="post" name="orgFixQuery">
<input type="hidden" name="method" value="listSubOrgsByOrgId">
<input type="hidden" name="selectedOrgId" value="${selectedOrgId}">
</form>
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
					items="Organization" 
					var="bean" 
					action="${pageContext.request.contextPath}/usermgr/org.do" 
					view="compact" 
					imagePath="${pageContext.request.contextPath}/images/ecTable/compact/*.gif"  
					filterable="false"
					sortable="false" 
					showExports="false" 
					autoIncludeParameters="true"
					width="100%" 
					styleClass="ecTable"
					rowsDisplayed="15">
					<ec:parameter name="method" value="listSubOrgsByOrgId" />
					<ec:row highlightRow="true">
						<ec:column alias="NO" title="序号" cell="rowCount" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;" />
						<ec:column property="orgId" title="公司标识" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;" />
						<ec:column property="orgCode" title="公司编号" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;" />
						<ec:column alias="orgName" title="公司名称" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:35%;">
							<a href="javascript:edit('${bean.orgId}')">${bean.orgName}</a>
						</ec:column>
						<ec:column property="xtOrganization.orgName" title="上级公司" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:30%;" />
						<ec:column alias="edit" title="编辑" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
							<a href="javascript:edit('${bean.orgId}')"><img src="${pageContext.request.contextPath}/images/green/tool/edit.png" border="0" /></a>
						</ec:column>
						<ec:column alias="delete" title="删除" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
							<a href="javascript:del('${bean.orgId}')"><img src="${pageContext.request.contextPath}/images/green/tool/delete.png" border="0" /></a>
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