<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>菜单权限分配（菜单->角色）</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript">
<!--
	var CLEARTime = 3000;
	function removeMessage() {
		$('#message').css('display', 'none');
	}
		
	window.onload = function() {
		window.setTimeout(removeMessage,CLEARTime);
		freshRoleTree();
	}

	function freshRoleTree() {
		var orgId = $('#orgId');
		if (orgId != '')
			document.menuAuthForm.submit();
	}

	function openOrgTree() {
		var value = window.showModalDialog('${pageContext.request.contextPath}/usermgr/org.do?method=listOrgTreeByLoingUser&_time=' + new Date().getTime(), '', 'dialogHeight:500px;dialogWidth:400px;help:no;status:no');	
		if(value) {
			$('#orgId').val(value[0]);
			$('#orgName').val(value[1]);
			document.menuAuthForm.submit();
		}
	}
//-->
</script>
<style type="text/css">
<!--
	#sidebar{ 
  		width: 21px; 
  		height: 100%; 
		background: #fff url(${pageContext.request.contextPath}/images/green/tool/separator.png) repeat-y center center; 
	} 
//-->
</style>
</head>
<body scroll="no">
<TABLE class="tabOutside" id="tabOutside">
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
		<TD style="width: 100%; height: 50px; padding-left: 10px;">
		<form name="menuAuthForm" action="${pageContext.request.contextPath}/usermgr/menuAuthorize.do" target="roleTreeIframe">
		<input type="hidden" name="method" value="getRoleTreeByOrg">		
		<TABLE>	
			<TR>
				<TD><span style="font-weight: bold;">请选择所属公司：</span></TD>								
				<TD>
					<input type="hidden" id="orgId" name="orgId" value="${orgId}" >
					<input type="text" id="orgName" name="orgName" value="${orgName}" readonly="true" onclick="openOrgTree()" class="popWindow" size="32" title="单击这里打开公司树，选择所属公司" /><span class="required">*</span>
				</TD>
			</TR>
		</TABLE>
		</form>
		</TD>				
	</TR>
	<TR><TD height="1" width="100%"><hr /></TD></TR>
	<TR>
		<TD height="100%">
		<TABLE height="100%" width="100%" cellspacing="0" cellpadding="0" border="0">
			<TR>
				<TD height="100%" width="35%">
					<iframe name="roleTreeIframe" id="roleTreeIframe" src="" frameborder="0" width="100%" height="100%" align="middle" scrolling="auto"></iframe>
		  		</TD>
		  		<TD id="sidebar">&nbsp;</TD>
		  		<TD height="100%" width="65%">
		  			<iframe name="menuTreeIframe" id="menuTreeIframe" src="" frameborder="0" width="100%" height="100%" align="middle" scrolling="auto"></iframe>
		  		</TD>
			</TR>
		</TABLE>
		</TD>
    </TR>	
</TABLE>
</body>
</html>
