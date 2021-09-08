<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<title>资源分类列表</title>
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
		if (validateXtResClassForm(xtResClassForm)) {
			xtResClassForm.method.value = 'saveOrUpdate';
			xtResClassForm.submit();
			forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
		}
	}
	
	function edit(id, name) {
		xtResClassForm.resClassId.readOnly = true;
		xtResClassForm.resClassId.value = id;
		xtResClassForm.resClassName.value = name;
		$('#btn_sou').html('修&nbsp;&nbsp;改');
	}
	
	function clear() {
		xtResClassForm.resClassId.readOnly = false;
		xtResClassForm.resClassId.value = '';
		xtResClassForm.resClassName.value = '';
		$('#btn_sou').html('增&nbsp;&nbsp;加');
	}
	
	function remove(key){
		if(confirm('确定要删除该条记录？')){
			xtResClassForm.resClassId.value = key;
			xtResClassForm.method.value = 'delete';
			xtResClassForm.submit();
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
		<html:form action="resClass" method="post">
		<html:hidden property="method" value="list" />
		<html:hidden property="selectedClsId" />
	 	<TD style="width: 100%; height: 60px; text-align: center;">
	 	<TABLE height="100%" border="0" cellspacing="0" cellpadding="0">
	 		<TR>
				<TD class="tdDetailLable">资源分类编号</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="resClassId" value="" styleClass="formDetailTxt" size="25" /><span class="required">*</span>
				</TD>
				<TD class="tdDetailLable">资源分类名称</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="resClassName" value="" styleClass="formDetailTxt" size="25" /><span class="required">*</span>
				</TD>
			</TR>
			<TR><TD>&nbsp;</TD></TR>
			<TR>
				<TD class="tdDetailLable">上级分类名称</TD>
				<TD width="4" class="tdDetailNeed"></TD>
				<TD class="tdDetailText">
					<html:hidden property="xtResClass.resClassId" value="${parentCls.resClassId}" />
					<html:text property="xtResClass.resClassName" value="${parentCls.resClassName}" readonly="true" styleClass="formDetailTxtDisable" size="25" />
				</TD>
				<TD colspan="3">
				<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" style="text-align: center;">
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
						items="list" 
						var="entity" 
						action="${pageContext.request.contextPath}/usermgr/resClass.do" 
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
							<ec:column property="resClassId" title="资源分类编号" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:25%;" />
							<ec:column property="resClassName" title="资源分类名称" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:30%;" />
							<ec:column property="xtResClass.resClassName" title="上级分类" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:30%;" />
							<ec:column alias="edit" title="编辑" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
								<a href="javascript:edit('${entity.resClassId}', '${entity.resClassName}')"><img src="${pageContext.request.contextPath}/images/green/tool/edit.png" border="0" /></a>
							</ec:column>
							<ec:column alias="delete" title="删除" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
								<a href="javascript:remove('${entity.resClassId}')"><img src="${pageContext.request.contextPath}/images/green/tool/delete.png" border="0" /></a>
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
<html:javascript formName="xtResClassForm" />
</BODY>
</html>