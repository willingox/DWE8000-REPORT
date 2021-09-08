<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<title>���б�</title>
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
		if(confirm('ȷ��Ҫɾ��������¼��')) {
			xtGroupForm.groupId.value = key;
			xtGroupForm.method.value = 'delete';
			xtGroupForm.submit();
			forbidSubmit();	// ��ֹ���а�ť�����ӵȣ�λ��{ebizapp.js}
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
	<TR>			 
		<TD style="width: 100%; height: 50px; text-align: center;">					
		<TABLE>	
			<TR>
				<TD>������˾</TD>								
				<TD>
					<html:hidden property="orgId" />
					<html:text property="orgName" readonly="true" onclick="openOrgTree()" styleClass="popWindow" size="25" title="��������򿪹�˾����ѡ��������˾" />
				</TD>
				<TD>&nbsp;</TD>
				<TD>		
					<a class="abtn3" href="javascript:query()"><span>��&nbsp;&nbsp;ѯ</span></a>
				</TD>	
			</TR>
		</TABLE>									
		</TD>				
	</TR>
	</html:form>
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
							<ec:column alias="no" cell="rowCount" title="���" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;" />
							<ec:column property="groupName" title="������" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:40%;" />
							<ec:column property="xtOrganization.orgName" title="������˾" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:45%;" />
							<ec:column alias="edit" title="�༭" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
								<a href="javascript:edit('${entity.groupId}')"><img src="${pageContext.request.contextPath}/images/green/tool/edit.png" border="0" /></a>
							</ec:column>
							<ec:column alias="delete" title="ɾ��" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
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