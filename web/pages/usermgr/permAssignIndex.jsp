<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>权限指派</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/usermgr.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript">
<!--
	function openOrgTree() {
		var value = window.showModalDialog('${pageContext.request.contextPath}/usermgr/org.do?method=listOrgTreeByLoingUser&rand=' + new Date().getTime(), '', 'dialogHeight:500px;dialogWidth:400px;help:no;status:no');	
		if(value) {
			$('#orgId').val(value[0]);
			$('#orgName').val(value[1]);				
		}
	}
	
	/* 捕捉回车事件 */
	function catchEnter() { 
		if ((event.keyCode == 13)) { 
			query();
		} 
	}
	
	function query() {
		permAssignIframe.location = '${pageContext.request.contextPath}/usermgr/permAssign.do?method=listPerms&resClassId=';
		resClassTreeIframe.location = '${pageContext.request.contextPath}/usermgr/permAssign.do?method=resClassTree';
		document.queryForm.submit();
	}
//-->
</script>
</HEAD>
<BODY scroll="no">
<TABLE class="tabOutside" id="tabOutside">
	<TR>
	<form action="${pageContext.request.contextPath}/usermgr/permAssign.do?method=fixQuery" onsubmit="query();" id="queryForm" name="queryForm" method="post" target="roleListIframe">
		<TD style="width: 100%; height: 50px; text-align: center;">					
		<TABLE>	
			<TR>
				<TD>所属公司</TD>								
				<TD>
					<input type="hidden" id="orgId" name="orgId" value="${orgId}">
					<input type="text" id="orgName" name="orgName" value="${orgName}" readonly="true" onclick="openOrgTree()" class="popWindow" size="30" title="单击这里打开公司树，选择所属公司" />
				</TD>
				</TD>								
				<TD>角色名称</TD>
				<TD>			
					<input type="text" id="fixRoleName" name="fixRoleName" onkeyup="catchEnter()" value="" class="formDetailTxt" size="15">
				</TD>
				<TD>		
					<a class="abtn3" href="javascript:query()"><span>查&nbsp;&nbsp;询</span></a>
				</TD>
			</TR>
		</TABLE>
		</TD>
	</form>
	</TR>
	<TR><TD height="1" width="100%"><hr /></TD></TR>
	<TR>
	    <TD height="100%">
    	<TABLE height="100%" width="100%" cellspacing="0" cellpadding="0" border="0">
    		<tr>
    			<td colspan="2" width="100%" height="60">
    				<IFRAME name="roleListIframe" id="roleListIframe" src="permAssign.do?method=roleList"  frameborder="0" width="100%" height="100%" align="middle" scrolling="auto" ></IFRAME>
    			</td>
    		</tr>
    		<tr>
    			<td width="22%" height="100%">
    				<IFRAME name="resClassTreeIframe" id="resClassTreeIframe" src="permAssign.do?method=resClassTree"  frameborder="0" width="100%" height="100%" align="middle" scrolling="auto" ></IFRAME>			    				
    			</td>
    			<td width="78%" height="100%">
	    			<IFRAME name="permAssignIframe" id="permAssignIframe" src="${pageContext.request.contextPath}/usermgr/permAssign.do?method=listPerms&resClassId="  frameborder="0" width="100%" height="100%" align="middle" scrolling="auto"></IFRAME>		    				
    			</td>
    		</tr>
    	</TABLE>
	    </TD>
    </TR>	
</TABLE>
</BODY>
</html>