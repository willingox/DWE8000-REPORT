<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>�û��б�</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/ec/js/extremecomponents.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript">
<!--
	function roleListByUserId(userId, userName) {
		window.showModalDialog('${pageContext.request.contextPath}/usermgr/role.do?method=roleListByUserId&userId=' + userId + '&userName=' + userName + '&rand=' + new Date().getTime(), '', 'dialogHeight:400px;dialogWidth:600px;help:no;status:no');	
	}
	
	function openOrgTree() {
		var value = window.showModalDialog('${pageContext.request.contextPath}/usermgr/org.do?method=listOrgTreeByLoingUser&rand=' + new Date().getTime(), '', 'dialogHeight:500px;dialogWidth:400px;help:no;status:no');	
		if(value) {
			$('#fixOrgId').val(value[0]);
			$('#fixOrgName').val(value[1]);
		}
	}
	
	function add() {
		document.userFixQuery.action = '${pageContext.request.contextPath}/usermgr/user.do?method=add';
		document.userFixQuery.submit();
	}
	
	function edit(key) {
		document.userFixQuery.action = '${pageContext.request.contextPath}/usermgr/user.do?method=edit&key=' + key;
		document.userFixQuery.submit();
	}
	
	function del(key) {
		if(confirm('ȷ��ɾ�����û��Լ�������ص�������Ϣ��')) {
			document.userFixQuery.action = '${pageContext.request.contextPath}/usermgr/user.do?method=delete&key=' + key;
			document.userFixQuery.submit();
			forbidSubmit();	// ��ֹ���а�ť�����ӵȣ�λ��{ebizapp.js}
		}
	}
	
	var CLEARTime = 3000;
	function removeMessage(){
		$('#message').css('display', 'none');
	}
		
	window.onload = function(){
		window.setTimeout(removeMessage,CLEARTime);
	}
	
	function query() {
		userFixQuery.submit();
	}
	
	/* ��׽�س��¼� */
	function catchEnter() { 
		if ((event.keyCode==13)){ 
			query();
		} 
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
	<form action="${pageContext.request.contextPath}/usermgr/user.do?method=list" method="post" name="userFixQuery">
	<TR>			 
		<TD style="width: 100%; height: 50px; text-align: center;">					
			<TABLE>	
				<TR>
					<TD>������˾</TD>								
					<TD>
						<input type=hidden id="fixOrgId" name="fixOrgId" value="${fixOrgId}" />
						<input type=text id="fixOrgName" name="fixOrgName" value="${fixOrgName}" readonly="true" onclick="openOrgTree()" class="popWindow" size="25" title="��������򿪹�˾����ѡ��������˾" />
						<input type="checkbox" name="bAllSubOrg" ${bAllSubOrg} />���¼�
					</TD>
					<TD width="8"></TD>	
					<TD>�û��˺�</TD>
					<TD>
						<input type=text name="fixUserId" onkeyup="catchEnter()" value="${fixUserId}" class="formDetailTxt" size="15" />
					</TD>							
					<TD>�û�����</TD>
					<TD>			
						<input type=text name="fixUserName" onkeyup="catchEnter()" value="${fixUserName}" class="formDetailTxt" size="15" />
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
					items="users" 
					var="bean" 
					action="${pageContext.request.contextPath}/usermgr/user.do" 
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
						<ec:column property="userId" title="�û��ʺ�" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;" />
						<ec:column alias="userName" title="�û�����" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:15%;">
							<a href="javascript:edit('${bean.userId}')">${bean.userName}</a>
						</ec:column>
						<ec:column property="xtOrganization.orgName" title="������˾" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:20%;" />
						<ec:column property="xtDept.deptName" title="��������" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:20%;" />
						<ec:column alias="toUser" title="��Ӧ��ɫ" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;">
							<a href="javascript:roleListByUserId('${bean.userId}','${bean.userName}')">��ϸ</a>
						</ec:column>
						<ec:column alias="userInvalid" title="�ʺ���Ч" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;">
							<input type="checkbox" name="checkbox" disabled="true" <logic:equal name="bean" property="userInvalid" value="true">checked</logic:equal> >
						</ec:column>
						<ec:column alias="edit" title="�༭" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
							<a href="javascript:edit('${bean.userId}')"><img src="${pageContext.request.contextPath}/images/green/tool/edit.png" border="0" /></a>
						</ec:column>
						<ec:column alias="delete" title="ɾ��" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
							<a href="javascript:del('${bean.userId}')"><img src="${pageContext.request.contextPath}/images/green/tool/delete.png" border="0" /></a>
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