<%@ page contentType="text/html;charset=gbk" language="java"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>��ǰ��Դ����Ȩ�����½�ɫ</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
</HEAD>
<body scroll="auto">
<TABLE id="tabOutside" width="100%" height="100%">
	 <TR>
	 	<TD class="tdDataGridSpace">
	 	</TD>
	 </TR>
	 <TR>
	 	<TD class="tdDataGridSpace">
			��Դ��${param.resName}������Ȩ�����½�ɫ
	 	</TD>
	 </TR>
	 <TR>
		<TD valign="top" align="center">
		<TABLE class="cmTable">
			<TR>
				<TD width="40%" class="cmTableHead">���</TD>
				<TD width="60%" class="cmTableHead">��ɫ����</TD>														
			</TR>
			<logic:iterate id="element" name="roleByResId" indexId="index">
				<TR>
					<TD width="40%" class='cmTableBody'><%= index.intValue()+1 %></TD>
					<TD width="60%" class='cmTableBody'><bean:write name="element" property="roleName" /></TD>							
				</TR>
			</logic:iterate>						 					
		</TABLE>
		 </TD>
	</TR>
	<TR>
	 	<TD height="100%">
	 	</TD>
	 </TR>
</TABLE>
</body>
</html>
