<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>�ͻ��б�</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/ec/js/extremecomponents.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript">
<!--
	function add() {
		document.customerFixQuery.action = '${pageContext.request.contextPath}/customer/customer.do?method=toAddPage';
		document.customerFixQuery.submit();
	}
	
	function edit(key) {
		document.customerFixQuery.action = '${pageContext.request.contextPath}/customer/customer.do?method=toEditPage&key=' + key;
		document.customerFixQuery.submit();
	}
	
	function del(key) {
		if(confirm('ȷ��ɾ�� �ͻ�['+key+'] �Լ���ص�������Ϣ��')) {
			document.customerFixQuery.action = '${pageContext.request.contextPath}/customer/customer.do?method=doDelete&key=' + key;
			document.customerFixQuery.submit();
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
	<!----------------------------------------������-------------------------------------------------------------->
	<!--����ҳ����Ҫ-->
	<form action="${pageContext.request.contextPath}/customer/customer.do?method=list" method="post" name="customerFixQuery">
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
					items="customers" 
					var="bean" 
					action="${pageContext.request.contextPath}/customer/customer.do" 
					view="compact" 
					imagePath="${pageContext.request.contextPath}/images/ecTable/compact/*.gif"  
					filterable="false"
					sortable="false" 
					showExports="false" 
					autoIncludeParameters="true"
					width="100%" 
					styleClass="ecTable"
					rowsDisplayed="15">
					<ec:parameter name="method" value="firstList" />
					<ec:row highlightRow="true">
						<ec:column alias="NO" title="���" cell="rowCount" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;" />
						<ec:column property="customerNo" title="�ͻ����" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;" />
						<ec:column alias="customerName" title="�ͻ�����" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:15%;">
							<a href="javascript:edit('${bean.customerNo}')">${bean.customerName}</a>
						</ec:column>
						<ec:column property="contactname" title="��ϵ��" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;" />
						<ec:column property="contactphone2" title="�ƶ��绰" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;" />
						<ec:column property="capability" title="��װ����(kVA)" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;" />
						
						<ec:column property="addr" title="�û���ַ" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:20%;" />
					
						<ec:column alias="edit" title="�༭" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
							<a href="javascript:edit('${bean.customerNo}')"><img src="${pageContext.request.contextPath}/images/green/tool/edit.png" border="0" /></a>
						</ec:column>
						<ec:column alias="delete" title="ɾ��" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
							<a href="javascript:del('${bean.customerNo}')"><img src="${pageContext.request.contextPath}/images/green/tool/delete.png" border="0" /></a>
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