<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>��༭</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript">
<!--
	var CLEARTime = 3000;
	function removeMessage() {
		$('#message').css('display', 'none');
	}
		
	window.onload = function() {
		window.setTimeout(removeMessage,CLEARTime);
	}
	
	function validate() {
		if(xtGroupForm.groupName.value == ''){
			alert('�����Ʋ�����Ϊ�գ�');
			return false;
		}
		if(xtGroupForm.orgName.value == ''){
			alert('������˾������Ϊ�գ�');
			return false;
		}
		return true;
	}
	
	function save() {
		if (validate()) {
			xtGroupForm.submit();
			forbidSubmit();	// ��ֹ���а�ť�����ӵȣ�λ��{ebizapp.js}
		}
	}
	
	function bk2lst() {
		xtGroupForm.method.value = 'list';
		xtGroupForm.submit();
	}
		
	function openOrgTree() {
		var url = '${pageContext.request.contextPath}/usermgr/org.do?method=listOrgTreeByLoingUser&rand=' + new Date().getTime();
		var value = window.showModalDialog(url, '', 'dialogHeight:500px;dialogWidth:400px;help:no;status:no');	
		if(value) {
			xtGroupForm.orgId.value = value[0];
			xtGroupForm.orgName.value = value[1];					
		}
	}
//-->
</script>
</HEAD>
<BODY scroll="no">
<html:form action="group" method="post">
<html:hidden property="method" value="edit" />
<html:hidden property="groupId" value="${group.groupId}" />
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
	<!----------------------------------------�������ʹ��󣨻����ݱ��֮��ļ��------------------------------------>
	 <TR>
	 	<TD class="tdDataDetailSpace">
		</TD>
	 </TR>
	<!----------------------------------------������ϸ���ʼ-------------------------------------------------------------->
	<TR>
		<TD valign="top" align="center" height="202">
		<TABLE class="detail">
			<TR>
				<TD class="tdDetailLable">������</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
                    <html:text property="groupName" value="${group.groupName}" styleClass="formDetailTxt" size="32" /><span style="color: red; padding-left: 5px;">*</span>
				</TD>
				<TD width="8"></TD>
			</TR>
			<TR><TD>&nbsp;</TD></TR>
			<TR>
				<TD class="tdDetailLable">������˾</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:hidden property="orgId" value="${group.xtOrganization.orgId}" />	             
	           		<html:text property="orgName" value="${group.xtOrganization.orgName}" readonly="true" onclick="openOrgTree()" styleClass="popWindow" size="32" title="��������򿪹�˾����ѡ��������˾" /><span class="required">*</span>
				</TD>
				<TD width="8" align="left">
				</TD>
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
					<a class="abtn3" href="javascript:save()"><span>��&nbsp;&nbsp;��</span></a>
					<a class="aBtn3" href="javascript:bk2lst()"><span>��&nbsp;&nbsp;��</span></a>
				</td>
			</tr>
		</table>
		</TD>
	</TR>
	<!--ҵ�������-->
	<!--�������һ��100��-->
	<TR height="100%">
	</TR>
</TABLE>
</html:form>
</BODY>
</html>