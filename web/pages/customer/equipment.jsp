<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>�豸�б�</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/ec/js/extremecomponents.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript">
<!--
	function add() {
		document.equipmentForm.action = '${pageContext.request.contextPath}/customer/equipment.do?method=toAddPage';
		document.equipmentForm.submit();
	}
	
	function edit(key) {
		document.equipmentForm.action = '${pageContext.request.contextPath}/customer/equipment.do?method=toEditPage&key=' + key;
		document.equipmentForm.submit();
	}
	
	function del(key,eqNo) {
		if(confirm('ȷ��ɾ�� �豸['+eqNo+'] �Լ���ص�������Ϣ��')) {
			document.equipmentForm.action = '${pageContext.request.contextPath}/customer/equipment.do?method=doDelete&key=' + key;
			document.equipmentForm.submit();
			forbidSubmit();	// ��ֹ���а�ť�����ӵȣ�λ��{ebizapp.js}
		}
	}
	function query() {
		document.equipmentForm.submit();
	}
	
	/* ��׽�س��¼� */
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
	<html:form action="equipment" method="post" >
	<html:hidden property="method" value="findList" />	
	<TR>			 
		<TD style="width: 100%; height: 50px; text-align: center;">					
			<TABLE>	
				<TR>
					<TD>�����ͻ�</TD>								
					<TD  width="50">
						<html:select property="findCustomerNo" >
							<html:option value=""></html:option>
							<logic:present name="customers">
		                   		<html:options collection="customers" labelProperty="customerName" property="customerNo" />
		                    </logic:present>	
						</html:select>
					</TD>
					<TD width="8"></TD>	
					<TD>�豸���</TD>
					<TD>
						<html:text property="findEqNo" onkeyup="catchEnter()" styleClass="formDetailTxt" size="15" />
					</TD>							
					<TD>�豸����</TD>
					<TD>	
						<html:text property="findEqName" onkeyup="catchEnter()" styleClass="formDetailTxt" size="15" />	
					</TD>
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
					items="equipments" 
					var="bean" 
					action="${pageContext.request.contextPath}/customer/equipment.do" 
					view="compact" 
					imagePath="${pageContext.request.contextPath}/images/ecTable/compact/*.gif"  
					filterable="false"
					sortable="false" 
					showExports="false" 
					autoIncludeParameters="true"
					width="100%" 
					styleClass="ecTable"
					rowsDisplayed="15">
					<ec:parameter name="method" value="findList" />
					<ec:row highlightRow="true">
						<ec:column alias="NO" title="���" cell="rowCount" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;" />
						<ec:column property="eqNo" title="�豸���" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;" />
						<ec:column alias="eqName" title="�豸����" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:15%;">
							<a href="javascript:edit('${bean.eqId}')">${bean.eqName}</a>
						</ec:column>
						<ec:column property="customer.customerName" title="�����ͻ�" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:20%;" />
						<ec:column property="ratedpower" title="�����(kW)" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;" />
						<ec:column property="limitvol" title="���ѹ(V)" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;" />						
						<ec:column property="maxpower" title="�����(kW)" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;" />
						<ec:column property="minpower" title="��С����(kW)" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:10%;" />
						<ec:column alias="edit" title="�༭" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
							<a href="javascript:edit('${bean.eqId}')"><img src="${pageContext.request.contextPath}/images/green/tool/edit.png" border="0" /></a>
						</ec:column>
						<ec:column alias="delete" title="ɾ��" viewsAllowed="html,compact" sortable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:5%;">
							<a href="javascript:del('${bean.eqId}','${bean.eqNo}')"><img src="${pageContext.request.contextPath}/images/green/tool/delete.png" border="0" /></a>
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