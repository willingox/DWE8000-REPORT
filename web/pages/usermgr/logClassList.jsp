<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<title>ϵͳ��־����ά��</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/ec/js/extremecomponents.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript">
<!--
	var CLEARTime = 3000;
	function removeMessage(){
		$('#message').css('display', 'none');
	}
		
	window.onload = function(){
		window.setTimeout(removeMessage,CLEARTime);
	}
	
	function saveOrUpdate() {
		if (validateXtLogClassForm(xtLogClassForm)) {
			xtLogClassForm.method.value = 'saveOrUpdate';
			xtLogClassForm.submit();
			forbidSubmit();	// ��ֹ���а�ť�����ӵȣ�λ��{ebizapp.js}
		}
	}
	
	function edit(id, name) {
		xtLogClassForm.logClassId.readOnly = true;
		xtLogClassForm.logClassId.value = id;
		xtLogClassForm.logClassName.value = name;
		$('#btn_sou').html('��&nbsp;&nbsp;��');
	}
	
	function clear() {
		xtLogClassForm.logClassId.readOnly = false;
		xtLogClassForm.logClassId.value = '';
		xtLogClassForm.logClassName.value = '';
		$('#btn_sou').html('��&nbsp;&nbsp;��');
	}
	
	function remove(key){
		if(confirm('ȷ��Ҫɾ��������¼��')){
			xtLogClassForm.logClassId.value = key;
			xtLogClassForm.method.value = 'delete';
			xtLogClassForm.submit();
			forbidSubmit();	// ��ֹ���а�ť�����ӵȣ�λ��{ebizapp.js}
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
	<!----------------------------------------�������ʹ��󣨻����ݱ��֮��ļ��------------------------------------>
	<TR>
		<html:form action="logClass" method="post">
		<html:hidden property="method" value="list" />
	 	<TD style="width: 100%; height: 50px; text-align: center;">
	 	<TABLE height="100%" border="0" cellspacing="0" cellpadding="0">
	 		<TR>
				<TD class="tdDetailLable">��־������</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="logClassId" value="" styleClass="formDetailTxt" size="25" /><span class="required">*</span>
				</TD>
				<TD class="tdDetailLable">��־��������</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="logClassName" value="" styleClass="formDetailTxt" size="25" /><span class="required">*</span>
				</TD>
				<TD>
				<table width="200" height="100%" border="0" cellspacing="0" cellpadding="0" style="text-align: center;">
					<tr>
						<td width="">&nbsp;</td>
						<td>
							<a class="abtn3" href="javascript:saveOrUpdate()"><span id="btn_sou">��&nbsp;&nbsp;��</span></a>&nbsp;&nbsp;
							<a class="aBtn3" href="javascript:clear()"><span>��&nbsp;&nbsp;��</span></a>
						</td>
					</tr>
				</table>
				</TD>
		  	</TR>
	 	</TABLE>
		</TD>
		</html:form>
	</TR>
	<TR>
	 	<TD class="tdDataGridSpace">
	 	<hr />
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
						items="list" 
						var="entity" 
						action="${pageContext.request.contextPath}/usermgr/logClass.do" 
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
							<ec:column property="logClassId" title="��־������" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:40%;" />
							<ec:column property="logClassName" title="��־��������" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:45%;" />
							<ec:column alias="edit" title="�༭" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
								<a href="javascript:edit('${entity.logClassId}', '${entity.logClassName}')"><img src="${pageContext.request.contextPath}/images/green/tool/edit.png" border="0" /></a>
							</ec:column>
							<ec:column alias="delete" title="ɾ��" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
								<a href="javascript:remove('${entity.logClassId}')"><img src="${pageContext.request.contextPath}/images/green/tool/delete.png" border="0" /></a>
							</ec:column>
						</ec:row>
					</ec:table>
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
<html:javascript formName="xtLogClassForm" />
</BODY>
</html>