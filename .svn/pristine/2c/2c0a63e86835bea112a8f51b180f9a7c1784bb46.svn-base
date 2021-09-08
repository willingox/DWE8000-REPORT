<%@ page contentType="text/html;charset=gbk" language="java"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>被授权当前角色的用户列表</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
</HEAD>
<body scroll="auto">
<TABLE id="tabOutside" width="100%" height="100%">
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
	 	<TD class="tdDataGridSpace">
	 	</TD>
	 </TR>
	<!----------------------------------------数据表格开始-------------------------------------------------------------->	 
	 <TR>
	 	<TD class="tdDataGridSpace">
			角色【${param.roleName}】被指派给以下用户
	 	</TD>
	 </TR>
	 <TR>
		<TD valign="top" align="center">
			<TABLE class="cmTable">
				<TR>
					<TD width="20%" class="cmTableHead">序号</TD>
					<TD width="40%" class="cmTableHead">用户帐号</TD>
					<TD width="40%" class="cmTableHead">用户名称</TD>														
				</TR>
				<logic:iterate id="element" name="userByRoleId" indexId="index">
					<TR>
						<TD width="20%" class='cmTableBody'><%= index.intValue()+1 %></TD>
						<TD width="40%" class='cmTableBody'><bean:write name="element" property="userId" /></TD>
						<TD width="40%" class='cmTableBody'><bean:write name="element" property="userName" /></TD>							
					</TR>
				</logic:iterate>						 					
			</TABLE>
		 </TD>
	</TR>
	<TR>
	 	<TD height="100%">
	 	</TD>
	 </TR>
	<!--业务表格结束-->
</TABLE>
</body>
</html>
