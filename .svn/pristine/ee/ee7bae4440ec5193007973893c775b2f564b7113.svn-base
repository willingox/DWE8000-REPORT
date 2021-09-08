<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>用户修改自己密码</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript">
<!--
	function save() {
		pwdForm.submit();
		forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
	}
	
	var CLEARTime = 3000;
	function removeMessage() {
		$("#message").css("display", "none");
	}
		
	window.onload = function() {
		window.setTimeout(removeMessage, CLEARTime);
	}
//-->
</script>
</HEAD>
<BODY scroll="no">
<form action="modifyPwd.do" name="pwdForm" method="post">
<input type="hidden" name="method" value="modifyPwd">
<TABLE class="tabOutside" id="tabOutside">
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
	 <TR>
	 	<TD class="tdDataDetailSpace">
		</TD>
	 </TR>
	<TR>
		<TD valign="top" align="center" height="202">
			<TABLE class="detail">					
				<TR>
					<TD class="tdDetailLable">用户账号</TD>
					<TD width="4"></TD>
					<TD class="tdDetailText">
						<input name="userId" type="text" readonly="true" class="formDetailTxtDisable" style="width:234px" value="${userId}">
					</TD>
					<TD width="4"></TD>
			  	</TR>
			  	<TR>
					<TD class="tdDetailLable">用户名称</TD>
					<TD width="4"></TD>
					<TD class="tdDetailText">
						<input name="userName" type="text" readonly="true" class="formDetailTxtDisable" style="width:234px" value="${userName}">
					</TD>
					<TD width="4"></TD>
			  	</TR>
				<TR>
					<TD class="tdDetailLable">旧密码</TD>
					<TD width="4"></TD>
					<TD class="tdDetailText">
						<input name="oldPsw" type="password" class="pswd" style="width:234px"><span class="required">*</span>
					</TD>
					<TD width="4"></TD>
				</TR>
				<TR>
					<TD class="tdDetailLable">新密码</TD>
					<TD width="4"></TD>
					<TD class="tdDetailText">
						<input name="newPsw" type="password" class="pswd" style="width:234px"><span class="required">*</span>
					</TD>
					<TD width="4"></TD>
				</TR>
				<TR>
					<TD class="tdDetailLable">确认新密码</TD>
					<TD width="4"></TD>
					<TD class="tdDetailText">
						<input name="comfirmNewPsw" type="password" class="pswd" style="width:234px"><span class="required">*</span>
					</TD>
					<TD width="4"></TD>
				</TR>
			</TABLE>
  	  </TD>
	</TR>
	<TR height="100">
		<TD align="center">
		<table width="100%" heigth="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="30%">&nbsp;</td>
				<td>
					<a class="abtn3" href="javascript:save()" onclick="javascript:save()"><span>保&nbsp;&nbsp;存</span></a>
				</td>
			</tr>
		</table>
		</TD>
	</TR>
	<!--大表格最后一行100％-->
	<TR height="100%">
	</TR>
</TABLE>
</form>
</BODY>
</html>
