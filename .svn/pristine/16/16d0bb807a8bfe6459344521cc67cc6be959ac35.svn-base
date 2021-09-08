<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<title>资源类型列表</title>
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
	
	function saveOrUpdate() {
		if (validateXtResTypeForm(xtResTypeForm)) {
			xtResTypeForm.method.value = 'saveOrUpdate';
			xtResTypeForm.submit();
			forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
		}
	}
	
	function edit(id, name) {
		xtResTypeForm.resTypeId.readOnly = true;
		xtResTypeForm.resTypeId.value = id;
		xtResTypeForm.resTypeName.value = name;
		$('#btn_sou').html('修&nbsp;&nbsp;改');
	}
	
	function clear() {
		xtResTypeForm.resTypeId.readOnly = false;
		xtResTypeForm.resTypeId.value = '';
		xtResTypeForm.resTypeName.value = '';
		$('#btn_sou').html('增&nbsp;&nbsp;加');
	}
	
	function remove(key){
		if(confirm('确定要删除该条记录？')){
			xtResTypeForm.resTypeId.value = key;
			xtResTypeForm.method.value = 'delete';
			xtResTypeForm.submit();
			forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
		}
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
	<!----------------------------------------工具栏和错误（或数据表格）之间的间隔------------------------------------>
	<TR>
		<html:form action="resType" method="post">
		<html:hidden property="method" value="list" />
	 	<TD style="width: 100%; height: 60px; text-align: center;">
	 	<TABLE height="100%" border="0" cellspacing="0" cellpadding="0">
	 		<TR>
	 			<TD class="tdDetailLable">资源类型编号</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="resTypeId" value="" styleClass="formDetailTxt" size="25" /><span class="required">*</span>
				</TD>
				<TD class="tdDetailLable">资源类型名称</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="resTypeName" value="" styleClass="formDetailTxt" size="25" /><span class="required">*</span>
				</TD>
				<TD align="center">
				<table width="200" height="100%" border="0" cellspacing="0" cellpadding="0" style="text-align: center;">
					<tr>
						<td width="">&nbsp;</td>
						<td>
							<a class="abtn3" href="javascript:saveOrUpdate()"><span id="btn_sou">增&nbsp;&nbsp;加</span></a>&nbsp;&nbsp;
							<a class="aBtn3" href="javascript:clear()"><span>清&nbsp;&nbsp;除</span></a>
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
	<!----------------------------------------数据表格开始-------------------------------------------------------------->
	<TR>
		<TD height="" valign="top" align="center">
		<div style="width: 100%; height: 100%; overflow: auto;">
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="bottom" align="center" width="100%">
					<ec:table 
						items="resTypes" 
						var="entity" 
						action="${pageContext.request.contextPath}/usermgr/resType.do" 
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
							<ec:column alias="no" cell="rowCount" title="序号" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;" />
							<ec:column property="resTypeId" title="资源类型编号" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:35%;" />
							<ec:column property="resTypeName" title="资源类型名称" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:35%;" />
							<ec:column alias="edit" title="编辑" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;">
								<a href="javascript:edit('${entity.resTypeId}', '${entity.resTypeName}')"><img src="${pageContext.request.contextPath}/images/green/tool/edit.png" border="0" /></a>
							</ec:column>
							<ec:column alias="delete" title="删除" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;">
								<a href="javascript:remove('${entity.resTypeId}')"><img src="${pageContext.request.contextPath}/images/green/tool/delete.png" border="0" /></a>
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
<html:javascript formName="xtResTypeForm" />
</BODY>
</html>