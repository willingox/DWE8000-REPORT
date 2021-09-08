<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>资源列表</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
</HEAD>
<BODY scroll="auto">
<TABLE width="100%" height="100%" cellSpacing="0" cellPadding="0" border="0">
	<TR>
		<TD class="tdDataGridSpace">
		</TD>
	</TR>
	<TR>
		<TD valign="top" align="center">
		<TABLE class="cmTable">
			<TR>
				<TD width="10%" class="cmTableHead">选择</TD>
				<TD width="45%" class="cmTableHead">资源编号</TD>
				<TD width="45%" class="cmTableHead">资源名称</TD>
			</TR>
			<logic:present name="reses">
			<logic:iterate name="reses" id="res" scope="request" indexId="index">
			<TR>
				<TD class='cmTableBody' align="center"><input type="radio" name="resId" resId="${res.resId}" resName="${res.resName}"></TD>
				<TD class='cmTableBody'>${res.resId}</TD>
				<TD class='cmTableBody'>${res.resName}</TD>
			</TR>
		</logic:iterate>
		</logic:present>
		</TABLE>
	  	</TD>
	</TR>
</TABLE>
</BODY>
</html>
      