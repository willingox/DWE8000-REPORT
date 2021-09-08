<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>角色详细</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript">
<!--
	function openOrgTree() {
		var value = window.showModalDialog('${pageContext.request.contextPath}/usermgr/org.do?method=listOrgTreeByLoingUser' + '&rand=' + new Date().getTime(), '', 'dialogHeight:500px;dialogWidth:400px;help:no;status:no');	
		if(value) {
			$('#orgId').val(value[0]);
			$('#orgName').val(value[1]);
		}
	}
	
	function openGroupList() {
		var orgId = $('#orgId');
		if(orgId.value != '') {
			var url = '${pageContext.request.contextPath}/usermgr/group.do?method=listGroupByOrgId&orgId=' + orgId.value + '&rand=' + new Date().getTime();
			var value = window.showModalDialog(url, '', 'dialogHeight:400px;dialogWidth:600px;help:no;status:no');	
			if(value) {
				$('#groupId').val(value[0]);
				$('#groupName').val(value[1]);
			}
		}else{
			alert('请先选择所属组！');
		}
	}
	
	function save() {
		if (validateXtRoleForm(xtRoleForm)) {
			xtRoleForm.submit();
			forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
		}
	}
	
	function bk2lst() {
		xtRoleForm.method.value = 'list';
		xtRoleForm.submit();
	}
//-->
</script>
</HEAD>
<BODY scroll="no">
<html:form action="role" method="post">
<html:hidden property="method" value="save"/>
<html:hidden property="fixOrgId" value="${fixOrgId}"/>
<html:hidden property="fixOrgName" value="${fixOrgName}"/>
<html:hidden property="fixRoleName" value="${fixRoleName}"/>
<input type="checkbox" name="bAllSubOrg" ${bAllSubOrg} style="display:none" />
<TABLE class="tabOutside" id="tabOutside">
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
	 	<TD class="tdDataDetailSpace">
		</TD>
	 </TR>
	<!----------------------------------------数据详细表格开始-------------------------------------------------------------->
	<TR>
		<TD valign="top" align="center" height="202">
		<TABLE class="detail">
		<!---------- 编辑时 ----------->					
		<logic:present name="role" >
			<TR>
				<TD class="tdDetailLable">角色名称</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:hidden property="roleId" value="${role.roleId}" />
					<html:text property="roleName" value="${role.roleName}" styleClass="formDetailTxt" size="32" /><span class="required">*</span>
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">所属公司</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:hidden property="xtOrganization.orgId" styleId="orgId" value="${role.xtOrganization.orgId}" />
               		<html:text property="xtOrganization.orgName" styleId="orgName" value="${role.xtOrganization.orgName}" readonly="true" onclick="openOrgTree()" styleClass="popWindow" size="32" title="单击这里打开公司树，选择所属公司" /><span class="required">*</span>
				</TD>	
		  	</TR>
		  	<logic:equal value="true" name="useGroup">
		  	<TR>
		  		<TD class="tdDetailLable">所属组</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<logic:empty name="role" property="xtGroup">
						<html:hidden property="groupId" styleId="groupId" />
	               		<html:text property="groupName" styleId="groupName" readonly="true" onclick="openGroupList()" styleClass="popWindow" size="32" title="单击这里打开组织树，选择所属组" />
					</logic:empty>
					<logic:notEmpty name="role" property="xtGroup">
						<html:hidden property="groupId" styleId="groupId" value="${role.xtGroup.groupId}" />
	               		<html:text property="groupName" styleId="groupName" value="${role.xtGroup.groupName}" readonly="true" onclick="openGroupList()" styleClass="popWindow" size="32" title="单击这里打开组织树，选择所属组" />
					</logic:notEmpty>
				</TD>
			</TR>
			</logic:equal>
		</logic:present>
		<!---------- 增加时 ----------->
		<logic:notPresent name="role" >
			<TR>
				<TD class="tdDetailLable">角色名称</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="roleName" value="${role.roleName}" styleClass="formDetailTxt" size="32" /><span class="required">*</span>
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">所属公司</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<!-- 隐藏字段不能验证 -->
					<html:hidden property="xtOrganization.orgId" styleId="orgId" value="${role.xtOrganization.orgId}" />	             
               		<html:text property="xtOrganization.orgName" styleId="orgName" value="${role.xtOrganization.orgName}" readonly="true" onclick="openOrgTree()" styleClass="popWindow" size="32" title="单击这里打开公司树，选择所属公司" /><span class="required">*</span>
				</TD>
		  	</TR>	
		  	<logic:equal value="true" name="useGroup">
		  	<TR>
		  		<TD class="tdDetailLable">所属组</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:hidden property="groupId" styleId="groupId" value="${group.groupId}" />
               		<html:text property="groupName" styleId="groupName" value="${group.groupName}" readonly="true" onclick="openGroupList()" styleClass="popWindow" size="32" title="单击这里打开组织树，选择所属组" />
				</TD>
			</TR>
			</logic:equal>
		</logic:notPresent>
		</TABLE>
  	  	</TD>
	</TR>
	<TR height="100">
		<TD align="center">
		<table width="100%" heigth="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="30%">&nbsp;</td>
				<td>
					<a class="abtn3" href="javascript:save()"><span>保&nbsp;&nbsp;存</span></a>
					<a class="aBtn3" href="javascript:bk2lst()"><span>返&nbsp;&nbsp;回</span></a>
				</td>
			</tr>
		</table>
		</TD>
	</TR>
	<!--业务表格结束-->
	<!--大表格最后一行100％-->
	<TR height="100%">
	</TR>
</TABLE>
</html:form>
<html:javascript formName="xtRoleForm" />
</BODY>
</html>