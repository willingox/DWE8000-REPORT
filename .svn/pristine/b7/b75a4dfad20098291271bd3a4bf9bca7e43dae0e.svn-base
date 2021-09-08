<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>资源列表</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/ec/js/extremecomponents.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript">
<!--
	function roleListByResId(resId, resName) {
		window.showModalDialog('${pageContext.request.contextPath}/usermgr/role.do?method=roleListByResId&resId=' + resId + '&resName=' + resName + '&rand=' + new Date().getTime(), '', 'dialogHeight:400px;dialogWidth:600px;help:no;status:no');	
	}
	
	function add() {
		xtResForm.method.value = 'add';
		xtResForm.submit();
	}
	
	function edit(key) {
		xtResForm.method.value = 'edit';
		xtResForm.key.value = key;
		xtResForm.submit();
	}
	
	function del(key) {
		if(confirm('确定要删除该条记录？')) {
			xtResForm.method.value = 'delete';
			xtResForm.key.value = key;
			xtResForm.submit();
			forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
		}
	}
	
	function query() {
		xtResForm.method.value = 'listResByClassId';
		xtResForm.submit();
	}
	
	/* 捕捉回车事件 */
	function catchEnter() { 
		if ((event.keyCode == 13)) { 
			query();
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
	<!----------------------------------------简单条件-------------------------------------------------------------->
	<html:form action="res" method="post">
	<html:hidden property="method" value="listResByClassId" />
	<html:hidden property="resClassId" />
	<html:hidden property="key" value="" />
	<TR>			 
		<TD style="width: 100%; height: 50px; text-align: center;">					
		<TABLE>	
			<TR>
				<TD>资源编号</TD>
				<TD>
					<html:text property="srchResId" onkeyup="catchEnter()" styleClass="formDetailTxt" size="15" />
				</TD>							
				<TD>资源名称</TD>
				<TD>
					<html:text property="srchResName" onkeyup="catchEnter()" styleClass="formDetailTxt" size="15" />
				</TD>
				<TD>资源类型</TD>
				<TD>
					<html:select property="srchResTypeId">
						<html:option value=""></html:option>
						<logic:present name="resTypes">
	                   		<html:options collection="resTypes" labelProperty="resTypeName" property="resTypeId" />
	                    </logic:present>	
					</html:select>
				</TD>
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
					items="reses" 
					var="bean" 
					action="${pageContext.request.contextPath}/usermgr/res.do" 
					view="compact" 
					imagePath="${pageContext.request.contextPath}/images/ecTable/compact/*.gif"  
					filterable="false"
					sortable="false" 
					showExports="false" 
					autoIncludeParameters="true"
					width="100%" 
					styleClass="ecTable"
					rowsDisplayed="15">
					<ec:parameter name="method" value="listResByClassId" />
					<ec:row highlightRow="true">
						<ec:column alias="NO" title="序号" cell="rowCount" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;" />
						<ec:column property="resId" title="资源编号" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:25%;" />
						<ec:column alias="resName" title="资源名称" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:30%;">
							<a href="javascript:edit('${bean.resId}')">${bean.resName}</a>
						</ec:column>
						<ec:column property="xtResType.resTypeName" title="资源类型" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:15%;" />
						<ec:column alias="toRole" title="对应角色" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;">
							<a href="javascript:roleListByResId('${bean.resId}','${bean.resName}')">详细</a>
						</ec:column>
						<ec:column alias="resActive" title="有效" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
							<input type="checkbox"  name="resActive" disabled="true" <logic:equal name="bean" property="resActive" value="true">checked</logic:equal> >
						</ec:column>
						<ec:column alias="edit" title="编辑" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
							<a href="javascript:edit('${bean.resId}')"><img src="${pageContext.request.contextPath}/images/green/tool/edit.png" border="0" /></a>
						</ec:column>
						<ec:column alias="delete" title="删除" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
							<a href="javascript:del('${bean.resId}')"><img src="${pageContext.request.contextPath}/images/green/tool/delete.png" border="0" /></a>
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