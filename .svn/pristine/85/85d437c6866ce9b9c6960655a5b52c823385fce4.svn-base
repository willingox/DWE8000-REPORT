<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>角色->用户</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/usermgr.js"></script>
<script type="text/javascript">
<!--
	function openOrgTree() {
		var value = window.showModalDialog('${pageContext.request.contextPath}/usermgr/org.do?method=listOrgTreeByLoingUser&rand=' + new Date().getTime(), '', 'dialogHeight:500px;dialogWidth:400px;help:no;status:no');	
		if(value){
			$('#orgId').val(value[0]);
			$('#orgName').val(value[1]);
		}
	}
	
	function query() {
		useroleForm.submit();
	}
	
	/* 捕捉回车事件 */
	function catchEnter() { 
		if ((event.keyCode==13)){ 
			query();
		} 
	}
//-->
</script>
</HEAD>
<BODY scroll="no">
<TABLE class="tabOutside" id="tabOutside">
	<TR>
	<form action="userrole.do?method=fixQuery" name="useroleForm" method="post" target="userRoleIframe">
		<TD style="width: 100%; height: 50px; text-align: center;">					
		<TABLE>	
			<TR>
				<TD>所属公司</TD>								
				<TD>
					<input type="hidden" id="orgId" name="orgId" value="${orgId}" />
					<input type="text" id="orgName" name="orgName" value="${orgName}" readonly="true" onclick="openOrgTree()" class="popWindow" size="30" title="单击这里打开公司树，选择所属公司" />
				</TD>
				</TD>	
				<TD>用户账号</TD>
				<TD>
					<input type="text" id="fixUserId" name="fixUserId" onkeyup="catchEnter()" class="formDetailTxt" size="15" />
				</TD>							
				<TD>用户名称</TD>
				<TD>			
					<input type="text" id="fixUserName" name="fixUserName" onkeyup="catchEnter()" class="formDetailTxt" size="15" />
				</TD>
				<TD>		
					<a class="abtn3" href="javascript:query()"><span>查&nbsp;&nbsp;询</span></a>
				</TD>
			</TR>
		</TABLE>
		</TD>
	</form>
	</TR>
	<TR>
	    <TD height="100%">
	    	<IFRAME name="userRoleIframe" id="userRoleIframe" src="userrole.do?method=first"  frameborder="0" width="100%" height="100%" align="middle" scrolling="auto"></IFRAME>
	    </TD>
    </TR>						
</TABLE>
</BODY>
</html>