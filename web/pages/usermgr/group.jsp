<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<title>组列表</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/ec/js/extremecomponents.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript">
<!--
	var CLEARTime = 3000;
	function removeMessage() {
		$('#message').css('display', 'none');
	}
		
	window.onload = function() {
		window.setTimeout(removeMessage,CLEARTime);
	}

	function openOrgTree() {
		var url = '${pageContext.request.contextPath}/usermgr/org.do?method=listOrgTreeByLoingUser&rand=' + new Date().getTime();
		var value = window.showModalDialog(url, '', 'dialogHeight:500px;dialogWidth:400px;help:no;status:no');	
		if(value){
			xtGroupForm.orgId.value = value[0];
			xtGroupForm.orgName.value = value[1];					
		}
	}
	
	function query() {
		xtGroupForm.submit();
	}
	
	function add() {
		xtGroupForm.method.value = 'toAdd';
		xtGroupForm.submit();
	}
	
	function edit(key) {
		xtGroupForm.groupId.value = key;
		xtGroupForm.method.value = 'toEdit';
		xtGroupForm.submit();
	}
	
	function remove(key) {
		if(confirm('确定要删除该条记录？')) {
			xtGroupForm.groupId.value = key;
			xtGroupForm.method.value = 'delete';
			xtGroupForm.submit();
			forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
		}
	}
//-->
</script>
</HEAD>
<BODY scroll="no">
<TABLE class="tabOutside" id="tabOutside">
	<html:form action="group" method="post">
	<html:hidden property="method" value="list" />
	<html:hidden property="groupId" />
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
	<TR>			 
		<TD style="width: 100%; height: 50px; text-align: center;">					
		<TABLE>	
			<TR>
				<TD>所属公司</TD>								
				<TD>
					<html:hidden property="orgId" />
					<html:text property="orgName" readonly="true" onclick="openOrgTree()" styleClass="popWindow" size="25" title="单击这里打开公司树，选择所属公司" />
				</TD>
				<TD>&nbsp;</TD>
				<TD>		
					<a class="abtn3" href="javascript:query()"><span>查&nbsp;&nbsp;询</span></a>
				</TD>	
			</TR>
		</TABLE>									
		</TD>				
	</TR>
	</html:form>
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
						items="groups" 
						var="entity" 
						action="${pageContext.request.contextPath}/usermgr/group.do" 
						view="compact" 
						imagePath="${pageContext.request.contextPath}/ec/images/table/compact/*.gif"  
						filterable="false"
						sortable="false" 
						autoIncludeParameters="true" 
						showTitle="false"
						showExports="false" 
						showStatusBar="false"
						showPagination="false"
						width="90%" 
						styleClass="ecTable">
						<ec:parameter name="method" value="list" />
						<ec:row highlightRow="true">
							<ec:column alias="no" cell="rowCount" title="序号" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;" />
							<ec:column property="groupName" title="组名称" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:40%;" />
							<ec:column property="xtOrganization.orgName" title="所属公司" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:45%;" />
							<ec:column alias="edit" title="编辑" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
								<a href="javascript:edit('${entity.groupId}')"><img src="${pageContext.request.contextPath}/images/green/tool/edit.png" border="0" /></a>
							</ec:column>
							<ec:column alias="delete" title="删除" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
								<a href="javascript:remove('${entity.groupId}')"><img src="${pageContext.request.contextPath}/images/green/tool/delete.png" border="0" /></a>
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