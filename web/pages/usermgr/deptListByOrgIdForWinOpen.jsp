<%@ page contentType="text/html;charset=gbk" language="java"%>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<base target="_self"/><!-- һ��Ҫ�� ����ύ�����´��ڴ� -->
<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>�����б�</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/ec/js/extremecomponents.js"></script>
<script type="text/javascript">
<!--
	function rtnChoice(busStopId, busStopName) {
		window.returnValue = new Array(busStopId, busStopName);
		self.close();
	}
//-->
</script>
</HEAD>
<body scroll="no">
<html:form action="dept" method="post" target="_self">
<html:hidden property="method" value="listDeptByOrgId" />
<html:hidden property="ownOrgId" />
<html:hidden property="deptId" />
</html:form>
<TABLE id="tabOutside" width="100%" height="100%">
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
						items="deptByOrgId" 
						var="dept"
						action="${pageContext.request.contextPath}/usermgr/dept.do"
						view="compact"
						imagePath="${pageContext.request.contextPath}/ec/images/table/compact/*.gif"
						filterable="true" 
						sortable="false"
						autoIncludeParameters="true" 
						showTitle="false"
						showExports="false" 
						showStatusBar="true"
						showPagination="true" 
						width="90%" 
						styleClass="ecTable">
						<ec:parameter name="method" value="listDeptByOrgId" />
						<ec:row highlightRow="true">
							<ec:column alias="choice" title="ѡ��" filterable="false" viewsAllowed="html,compact" headerClass="ecTableHead" styleClass="ecTableBody" style="width:20%;">
								<input type="radio" id="choice" name="choice" value="${dept.deptId}" onclick="rtnChoice('${dept.deptId}', '${dept.deptName}')">
							</ec:column>
							<ec:column property="deptName" title="��������" filterable="true" styleClass="ecTableBody" headerClass="ecTableHead" style="width:40%;" />
							<ec:column property="xtOrganization.orgName" title="������˾" filterable="false" styleClass="ecTableBody" headerClass="ecTableHead" style="width:40%;" />
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
</body>
</html>
