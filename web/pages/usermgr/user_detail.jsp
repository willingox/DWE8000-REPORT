<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>�û�ά��</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/popCheck/popCheck.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/popCheck/ymPrompt.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/popCheck/popCheck.js"></script>
<script type="text/javascript">
<!--
	var currOrgId = "${currOrgId}";	// ��ǰ�û�������˾
	var currRoleIds = "${roleIds}";	// ��ǰ�û����߱��Ľ�ɫ
	var currRoleNames = "${roleNames}";
	window.onload = function() {
		if (document.userForm.addUserInvalid) {
			document.userForm.addUserInvalid.checked = true;	//������ʱ��Ĭ��Ϊѡ��		
		}
		if (document.userForm.addUserPswInvalid) {
			document.userForm.addUserPswInvalid.checked = true;//������ʱ��Ĭ��Ϊѡ��	
		}
		//���������������ڡ���ѡ��ʱ���ѡ�ָ����������ա���ң������ͱ༭ʱ����Ч��
		if (document.userForm.userPswInvalid.checked) {
			document.userForm.userPswEndDateDiplay.disabled = true;
			document.userForm.userPswEndDateDiplay.className = "formDetailTxtDisable";
		}
	};
		
	function CheckPws() {
		if ($("#password").val() != $("#confirmPassword").val()) {
			alert("������������벻һ��������������!");
			$("#password").focus();
			return false;
		}
		return true;
	}
		
	function openOrgTree() {
		if (isAdmin()) {
			alert("ϵͳ����Ա���ܸ���������˾��");
			return false;
		}
		var value = window.showModalDialog("${pageContext.request.contextPath}/usermgr/org.do?method=listOrgTreeByLoingUser&rand=" + new Date().getTime(), "", "dialogHeight:500px;dialogWidth:400px;help:no;status:no");	
		if (value) {
			var orgId = value[0];
			$("#orgId").val(orgId);
			$("#orgName").val(value[1]);
			$("#deptId").val("");
			$("#deptName").val("");
			if (orgId == currOrgId) {
				$("#roleIds").val(currRoleIds);
				$("#roleNames").val(currRoleNames);
			} else {
				$("#roleIds").val("");
				$("#roleNames").val("");
			}
			updateRoleArray(value[0]);
		}
	}
	
	function openDeptList() {
		var orgId = $("#orgId");
		if (orgId.val() != "") {
			var value = window.showModalDialog("${pageContext.request.contextPath}/usermgr/dept.do?method=listDeptByOrgId&ownOrgId=" + orgId.val() + "&rand=" + new Date().getTime(), "", "dialogHeight:400px;dialogWidth:600px;help:no;status:no");	
			if(value){
				$("#deptId").val(value[0]);
				$("#deptName").val(value[1]);
			}
		} else {
			alert("����ѡ��������˾��");
		}
	}
		
	function openGroupList() {
		var orgId = $("#orgId");
		if (orgId.val() != "") {
			var url = "${pageContext.request.contextPath}/usermgr/group.do?method=listGroupByOrgId&orgId=" + orgId.val() + "&rand=" + new Date().getTime();
			var value = window.showModalDialog(url, "", "dialogHeight:400px;dialogWidth:600px;help:no;status:no");	
			if (value) {
				$("#groupId").val(value[0]);
				$("#groupName").val(value[1]);
			}
		} else {
			alert("����ѡ��������˾��");
		}
	}
	
	// �򿪲˵���ѡ����ѡ���ܲ˵�
	function openMenuTree() {
		var value = window.showModalDialog("${pageContext.request.contextPath}/usermgr/menu.do?method=chooseChiefMenu&_time=" + new Date().getTime(), "", "dialogHeight:500px;dialogWidth:400px;help:no;status:no");	
		if (value) {
			$("#chiefMenuId").val(value[0]);
			$("#chiefMenuName").val(value[1]);
		}
	}
		
	function onUserPswClick(obj) {
		if (isAdmin()) {
			alert("admin�ʻ������������ڣ�");
			obj.checked = true;
		}
		if (obj.checked) {
			document.userForm.userPswEndDateDiplay.value = "";
			document.userForm.userPswEndDateDiplay.disabled = true;
			document.userForm.userPswEndDateDiplay.className = "formDetailTxtDisable";
		} else {
			document.userForm.userPswEndDateDiplay.disabled = false;
			document.userForm.userPswEndDateDiplay.className = "formDetailTxt";
		}
	}
	
	function onUserInvalid(obj) {
		if (isAdmin()) {
			alert("Ĭ�Ϲ���Ա�ʻ����ܱ����ã�");
			obj.checked = true;
		}
	}

	function isAdmin() {
		var userId = document.userForm.userId.value;
		if (userId == "admin") {
			return true;
		} else {
			return false;
		}
	}
	
	function save() {
		if (validateXtUserForm(xtUserForm) && validatePwd()) {
			xtUserForm.submit();
			forbidSubmit();	// ��ֹ���а�ť�����ӵȣ�λ��{ebizapp.js}
		}
	}
	
	function validatePwd() {
		if (xtUserForm.userPassword.value != xtUserForm.confirmUserPassword.value) {
			alert("�����������벻һ�£����������룡");
			return false;
		}
		return true;
	}
	
	function bk2lst() {
		xtUserForm.method.value = "list";
		xtUserForm.submit();
	}
	
	// �ص�������������ȡѡ�еĽ�ɫID�ַ���
	function callback(roleIds) {
		$("#roleIds").val(roleIds);
	}
	
	var roleArray = ${requestScope.roleArray};
	function chooseRole() {
		var orgId = $("#orgId");
		if (orgId.val() != "") {
			$("#roleNames").popCheck({
				title: "�빴ѡ����Ȩ�Ľ�ɫ",
				data: roleArray,	// ��������
				height: 280,
				width: 360,
				showClear: true,	// �Ƿ���ʾ�����ť
				handler: callback
			});
		} else{
			alert("����ѡ��������˾��");
		}
	}
	
	var ajaxServerURL = "${pageContext.request.contextPath}/usermgr/user.do";
	// ���µ�ǰ��ɫ���飬������ѡ��˾�Զ�����
	function updateRoleArray(orgId) {
		$.ajax({
			type: "GET",
			url: ajaxServerURL,
			dataType: "json",
			data: "method=getRoleArray&orgId=" + orgId + "&_time=" + new Date().getTime(),
			success: function(data){
				if ($.isArray(data)) {
					roleArray = data;
				}
			}
		});
	}
//-->
</script> 
</HEAD>
<BODY scroll="auto">
<div id="outerDiv" style="width: 400px; height: 300px; display: none;">
<div>
<table width="100%" height="100%">
	<tr>
		<td width="65%">&nbsp;</td>
		<td width="35%" height="20">
			<a class="aBtn2" href="javascript:rtnChkVals();"><span>ȷ��</span></a>
			<a class="aBtn2" href="javascript:ymPrompt.close();"><span>ȡ��</span></a>
		</td>
	</tr>
	<tr><td colspan="2" height="1" width="100%"><hr/></td></tr>
	<tr>
		<td height="100%" colspan="2">
		<div style="height: 100%; width: 100%; overflow: auto; padding-left: 5px; padding-top: 5px;">
		<table id="tb_roleList">
			<tr id="tr_roleList">
			</tr>
		</table>
		</div>
		</td>
	</tr>
</table>
</div>
</div>
<html:form action="user" styleId="userForm" method="post">
<html:hidden property="fixOrgId" value="${fixOrgId}"/>
<html:hidden property="fixOrgName" value="${fixOrgName}"/>
<html:hidden property="fixUserId" value="${fixUserId}"/>
<html:hidden property="fixUserName" value="${fixUserName}"/>
<input type="checkbox" name="bAllSubOrg" ${bAllSubOrg} style="display:none" />
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
		<!---------- �༭ʱ ----------->					
		<logic:equal name="postMode" parameter="postMode" value="edit">
		<logic:present name="userDetail" >
		<html:hidden property="method" value="update"/>	
			<TR>
				<TD class="tdDetailLable">�û��˺�</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userId" value="${userDetail.userId}" readonly="true" styleClass="formDetailTxtDisable" style="width:234px" /><span class="required">*</span>
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">�û�����</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
	           		<html:text styleId="userName" property="userName" value="${userDetail.userName}" styleClass="formDetailTxt" style="width:234px" /><span class="required">*</span>
				</TD>
				<TD width="4"></TD>
		  	</TR>
			<TR>
				<TD class="tdDetailLable">�û�����</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:password styleId="password" property="userPassword" onfocus="javascript:this.value='';xtUserForm.confirmUserPassword.value='';" styleClass="pswd" style="width:234px"/><span class="required">*</span>
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">У������</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:password styleId="confirmPassword" property="confirmUserPassword" styleClass="pswd" style="width:234px" /><span class="required">*</span>
				</TD>
				<TD width="4"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">������˾</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:hidden property="xtOrganization.orgId" styleId="orgId" value="${userDetail.xtOrganization.orgId}" />
	           		<html:text property="xtOrganization.orgName" styleId="orgName" value="${userDetail.xtOrganization.orgName}" readonly="true" onclick="openOrgTree()" styleClass="popWindow" style="width:234px" title="��������򿪹�˾����ѡ��������˾" /><span class="required">*</span>
				</TD>
				<TD width="8">
				</TD>
				<TD class="tdDetailLable">��������</TD>
				<TD width="4" class="tdDetailNeed"></TD>
				<TD class="tdDetailText">
					<html:hidden property="xtDept.deptId" styleId="deptId" value="${userDetail.xtDept.deptId}" />
	           		<html:text property="xtDept.deptName" styleId="deptName" value="${userDetail.xtDept.deptName}" readonly="true" onclick="openDeptList()" styleClass="popWindow" style="width:234px" title="��������򿪲����б�ѡ����������" />
				</TD>
				<TD width="4">
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">��ѡ�˵�</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:hidden property="chiefMenuId" styleId="chiefMenuId" value="${requestScope.chiefMenuId}" />
	           		<html:text property="chiefMenuName" styleId="chiefMenuName" value="${requestScope.chiefMenuName}" readonly="true" onclick="openMenuTree()" styleClass="popWindow" style="width:234px" title="��������򿪲˵�����ѡ����ѡ�˵�" />
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">ָ�����������</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userPswEndDateDiplay" readonly="true" style="cursor: text;" onclick="WdatePicker({skin:'ext',minDate:'%y-%M-{%d+1}',isShowWeek:true,isShowClear:false,readOnly:true})" styleClass="formDetailTxt" style="width:234px"/>
				</TD>
				<TD width="4"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">�绰</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userTel" value="${userDetail.userTel}" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">�ֻ�</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userMobile" value="${userDetail.userMobile}" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="4"></TD>
		  	</TR>
			<TR>
				<TD class="tdDetailLable">�����ʼ�</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userEmail" value="${userDetail.userEmail}" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">��ַ</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userAddr" value="${userDetail.userAddr}" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="4"></TD>
		  	</TR>
		  	<!-- IceWee 09-05-24 -->
		  	<logic:equal value="true" name="useGroup">
		  	<TR>
		  		<TD class="tdDetailLable">������</TD>
				<TD width="4" class="tdDetailNeed"></TD>
				<TD class="tdDetailText">
					<logic:empty name="user" property="xtGroup">
						<html:hidden property="groupId" styleId="groupId" />
	               		<html:text property="groupName" styleId="groupName" readonly="true" onclick="openGroupList()" styleClass="popWindow" style="width:234px" title="�����������֯����ѡ��������" />
					</logic:empty>
					<logic:notEmpty name="user" property="xtGroup">
						<html:hidden property="groupId" styleId="groupId" value="${user.xtGroup.groupId}" />
	               		<html:text property="groupName" styleId="groupName" value="${user.xtGroup.groupName}" readonly="true" onclick="openGroupList()" styleClass="popWindow" style="width:234px" title="�����������֯����ѡ��������" />
					</logic:notEmpty>
				</TD>
				<TD width="4">
				</TD>
				<TD colspan="4">&nbsp;</TD>
			</TR>
			</logic:equal>
		  	<TR>
				<TD class="tdDetailLable">������������</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:checkbox property="userPswInvalid" value="true" onclick="onUserPswClick(this)"></html:checkbox>
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">�ʺ��Ƿ���Ч</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:checkbox property="userInvalid" value="true" onclick="onUserInvalid(this)"></html:checkbox>
				</TD>
				<TD width="4"></TD>
		  	</TR> 
			<TR>
				<TD class="tdDetailLable">��ע</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText" colspan="6">
					<html:textarea property="userNote" value="${userDetail.userNote}" styleClass="formDetailTxtarea" cols="110" rows="5" />							
				</TD>
				<TD width="8"></TD>
			</TR>
		</logic:present>
		</logic:equal>
		<!---------- ����ʱ ----------->
		<logic:notEqual name="postMode" parameter="postMode"  value="edit">
		<html:hidden property="method" value="insert"/>	
			<TR>
				<TD class="tdDetailLable">�û��˺�</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userId" value="" styleClass="formDetailTxt" style="width:234px"/><span class="required">*</span>
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">�û�����</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text styleId="userName" property="userName" value="" styleClass="formDetailTxt" style="width:234px" /><span class="required">*</span>
				</TD>
				<TD width="4"></TD>
		  	</TR>
			<TR>
				<TD class="tdDetailLable">�û�����</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
               		<html:password styleId="password" property="userPassword" styleId="password" redisplay="true" styleClass="pswd" style="width:234px" /><span class="required">*</span>
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">У������</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
               		<html:password property="confirmUserPassword" styleId="confirmPassword" redisplay="true" onchange="CheckPws()" styleClass="pswd" style="width:234px" /><span class="required">*</span>
				</TD>
				<TD width="4"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">������˾</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<!-- �����ֶβ�����֤ -->
					<html:hidden property="xtOrganization.orgId" styleId="orgId" value="${orgId}" />	             
               		<html:text property="xtOrganization.orgName" styleId="orgName" value="${orgName}" readonly="true"onclick="openOrgTree()" styleClass="popWindow" style="width:234px" title="��������򿪹�˾����ѡ��������˾" /><span class="required">*</span>
				</TD>
				<TD width="8">
				</TD>
				<TD class="tdDetailLable">��������</TD>
				<TD width="4" class="tdDetailNeed"></TD>
				<TD class="tdDetailText">
					<!-- �����ֶβ�����֤ -->
					<html:hidden property="xtDept.deptId" styleId="deptId" value="" />
           			<html:text property="xtDept.deptName" styleId="deptName" value="" readonly="true" onclick="openDeptList()" styleClass="popWindow" style="width:234px" title="��������򿪲����б�ѡ����������" />
				</TD>
				<TD width="4">
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">��ѡ����</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:hidden property="chiefMenuId" styleId="chiefMenuId" value="" />
	           		<html:text property="chiefMenuName" styleId="chiefMenuName" value="" readonly="true" onclick="openMenuTree()" styleClass="popWindow" style="width:234px" title="��������򿪲˵�����ѡ����ѡ�˵�" />
				</TD>
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">ָ�����������</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userPswEndDateDiplay" readonly="true" style="cursor: text;" onclick="WdatePicker({skin:'ext',minDate:'%y-%M-{%d+1}',isShowWeek:true,isShowClear:false,readOnly:true})" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="4"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">�绰</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userTel" value="" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">�ֻ�</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userMobile" value="" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="4"></TD>
		  	</TR>
			<TR>
				<TD class="tdDetailLable">�����ʼ�</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userEmail" value="" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">��ַ</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userAddr" value="" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="4"></TD>
		  	</TR>
		  	<!-- IceWee 09-05-24 -->
		  	<logic:equal value="true" name="useGroup">
		  	<TR>
		  		<TD class="tdDetailLable">������</TD>
				<TD width="4" class="tdDetailNeed"></TD>
				<TD class="tdDetailText">
					<html:hidden property="groupId" styleId="groupId" value="" />
               		<html:text property="groupName" styleId="groupName" value="" readonly="true" onclick="openGroupList()" styleClass="popWindow" style="width:234px" title="�����������֯����ѡ��������" />
				</TD>
				<TD width="4">
				</TD>
				<TD colspan="4">&nbsp;</TD>
			</TR>
			</logic:equal>
		  	<TR>
				<TD class="tdDetailLable">������������</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:checkbox property="userPswInvalid" styleId="addUserPswInvalid" value="true" onclick="onUserPswClick(this)" ></html:checkbox>
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">�ʺ��Ƿ���Ч</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:checkbox property="userInvalid" styleId="addUserInvalid" onclick="onUserInvalid(this)" ></html:checkbox>
				</TD>
				<TD width="4"></TD>
		  	</TR> 
			<TR>
				<TD class="tdDetailLable">��ע</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText" colspan="6">
					<html:textarea property="userNote" value="" styleClass="formDetailTxtarea" cols="110" rows="5" />							
				</TD>
				<TD width="8"></TD>
			</TR>
			</logic:notEqual>
			<TR>
				<TD class="tdDetailLable">��Ȩ��ɫ</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText" colspan="6">
					<input type="hidden" id="roleIds" name="roleIds" value="${requestScope.roleIds}">
					<input type="text" id="roleNames" name="roleNames" value="${requestScope.roleNames}" onclick="chooseRole()" style="width: 790px; cursor: text;" readonly="true" class="popWindow" title="����˴�ѡ����Ȩ��ɫ" />
				</TD>
				<TD width="8"></TD>
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
<html:javascript formName="xtUserForm" />
</BODY>
</html>