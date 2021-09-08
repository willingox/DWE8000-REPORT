<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>用户维护</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/popCheck/popCheck.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/popCheck/ymPrompt.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/popCheck/popCheck.js"></script>
<script type="text/javascript">
<!--
	var currOrgId = "${currOrgId}";	// 当前用户所属公司
	var currRoleIds = "${roleIds}";	// 当前用户所具备的角色
	var currRoleNames = "${roleNames}";
	window.onload = function() {
		if (document.userForm.addUserInvalid) {
			document.userForm.addUserInvalid.checked = true;	//新增加时，默认为选中		
		}
		if (document.userForm.addUserPswInvalid) {
			document.userForm.addUserPswInvalid.checked = true;//新增加时，默认为选中	
		}
		//当【密码永不过期】被选中时，把【指定密码过期日】便灰，新增和编辑时都有效。
		if (document.userForm.userPswInvalid.checked) {
			document.userForm.userPswEndDateDiplay.disabled = true;
			document.userForm.userPswEndDateDiplay.className = "formDetailTxtDisable";
		}
	};
		
	function CheckPws() {
		if ($("#password").val() != $("#confirmPassword").val()) {
			alert("两次输入的密码不一样，请重新输入!");
			$("#password").focus();
			return false;
		}
		return true;
	}
		
	function openOrgTree() {
		if (isAdmin()) {
			alert("系统管理员不能更改所属公司！");
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
			alert("请先选择所属公司！");
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
			alert("请先选择所属公司！");
		}
	}
	
	// 打开菜单树选择首选功能菜单
	function openMenuTree() {
		var value = window.showModalDialog("${pageContext.request.contextPath}/usermgr/menu.do?method=chooseChiefMenu&_time=" + new Date().getTime(), "", "dialogHeight:500px;dialogWidth:400px;help:no;status:no");	
		if (value) {
			$("#chiefMenuId").val(value[0]);
			$("#chiefMenuName").val(value[1]);
		}
	}
		
	function onUserPswClick(obj) {
		if (isAdmin()) {
			alert("admin帐户密码永不过期！");
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
			alert("默认管理员帐户不能被禁用！");
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
			forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
		}
	}
	
	function validatePwd() {
		if (xtUserForm.userPassword.value != xtUserForm.confirmUserPassword.value) {
			alert("两次密码输入不一致，请重新输入！");
			return false;
		}
		return true;
	}
	
	function bk2lst() {
		xtUserForm.method.value = "list";
		xtUserForm.submit();
	}
	
	// 回调函数，用来获取选中的角色ID字符串
	function callback(roleIds) {
		$("#roleIds").val(roleIds);
	}
	
	var roleArray = ${requestScope.roleArray};
	function chooseRole() {
		var orgId = $("#orgId");
		if (orgId.val() != "") {
			$("#roleNames").popCheck({
				title: "请勾选欲授权的角色",
				data: roleArray,	// 对象数组
				height: 280,
				width: 360,
				showClear: true,	// 是否显示清除按钮
				handler: callback
			});
		} else{
			alert("请先选择所属公司！");
		}
	}
	
	var ajaxServerURL = "${pageContext.request.contextPath}/usermgr/user.do";
	// 更新当前角色数组，根据所选公司自动更新
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
			<a class="aBtn2" href="javascript:rtnChkVals();"><span>确定</span></a>
			<a class="aBtn2" href="javascript:ymPrompt.close();"><span>取消</span></a>
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
		<logic:equal name="postMode" parameter="postMode" value="edit">
		<logic:present name="userDetail" >
		<html:hidden property="method" value="update"/>	
			<TR>
				<TD class="tdDetailLable">用户账号</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userId" value="${userDetail.userId}" readonly="true" styleClass="formDetailTxtDisable" style="width:234px" /><span class="required">*</span>
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">用户名称</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
	           		<html:text styleId="userName" property="userName" value="${userDetail.userName}" styleClass="formDetailTxt" style="width:234px" /><span class="required">*</span>
				</TD>
				<TD width="4"></TD>
		  	</TR>
			<TR>
				<TD class="tdDetailLable">用户密码</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:password styleId="password" property="userPassword" onfocus="javascript:this.value='';xtUserForm.confirmUserPassword.value='';" styleClass="pswd" style="width:234px"/><span class="required">*</span>
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">校验密码</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:password styleId="confirmPassword" property="confirmUserPassword" styleClass="pswd" style="width:234px" /><span class="required">*</span>
				</TD>
				<TD width="4"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">所属公司</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:hidden property="xtOrganization.orgId" styleId="orgId" value="${userDetail.xtOrganization.orgId}" />
	           		<html:text property="xtOrganization.orgName" styleId="orgName" value="${userDetail.xtOrganization.orgName}" readonly="true" onclick="openOrgTree()" styleClass="popWindow" style="width:234px" title="单击这里打开公司树，选择所属公司" /><span class="required">*</span>
				</TD>
				<TD width="8">
				</TD>
				<TD class="tdDetailLable">所属部门</TD>
				<TD width="4" class="tdDetailNeed"></TD>
				<TD class="tdDetailText">
					<html:hidden property="xtDept.deptId" styleId="deptId" value="${userDetail.xtDept.deptId}" />
	           		<html:text property="xtDept.deptName" styleId="deptName" value="${userDetail.xtDept.deptName}" readonly="true" onclick="openDeptList()" styleClass="popWindow" style="width:234px" title="单击这里打开部门列表，选择所属部门" />
				</TD>
				<TD width="4">
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">首选菜单</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:hidden property="chiefMenuId" styleId="chiefMenuId" value="${requestScope.chiefMenuId}" />
	           		<html:text property="chiefMenuName" styleId="chiefMenuName" value="${requestScope.chiefMenuName}" readonly="true" onclick="openMenuTree()" styleClass="popWindow" style="width:234px" title="单击这里打开菜单树，选择首选菜单" />
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">指定密码过期日</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userPswEndDateDiplay" readonly="true" style="cursor: text;" onclick="WdatePicker({skin:'ext',minDate:'%y-%M-{%d+1}',isShowWeek:true,isShowClear:false,readOnly:true})" styleClass="formDetailTxt" style="width:234px"/>
				</TD>
				<TD width="4"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">电话</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userTel" value="${userDetail.userTel}" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">手机</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userMobile" value="${userDetail.userMobile}" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="4"></TD>
		  	</TR>
			<TR>
				<TD class="tdDetailLable">电子邮件</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userEmail" value="${userDetail.userEmail}" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">地址</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userAddr" value="${userDetail.userAddr}" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="4"></TD>
		  	</TR>
		  	<!-- IceWee 09-05-24 -->
		  	<logic:equal value="true" name="useGroup">
		  	<TR>
		  		<TD class="tdDetailLable">所属组</TD>
				<TD width="4" class="tdDetailNeed"></TD>
				<TD class="tdDetailText">
					<logic:empty name="user" property="xtGroup">
						<html:hidden property="groupId" styleId="groupId" />
	               		<html:text property="groupName" styleId="groupName" readonly="true" onclick="openGroupList()" styleClass="popWindow" style="width:234px" title="单击这里打开组织树，选择所属组" />
					</logic:empty>
					<logic:notEmpty name="user" property="xtGroup">
						<html:hidden property="groupId" styleId="groupId" value="${user.xtGroup.groupId}" />
	               		<html:text property="groupName" styleId="groupName" value="${user.xtGroup.groupName}" readonly="true" onclick="openGroupList()" styleClass="popWindow" style="width:234px" title="单击这里打开组织树，选择所属组" />
					</logic:notEmpty>
				</TD>
				<TD width="4">
				</TD>
				<TD colspan="4">&nbsp;</TD>
			</TR>
			</logic:equal>
		  	<TR>
				<TD class="tdDetailLable">密码永不过期</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:checkbox property="userPswInvalid" value="true" onclick="onUserPswClick(this)"></html:checkbox>
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">帐号是否有效</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:checkbox property="userInvalid" value="true" onclick="onUserInvalid(this)"></html:checkbox>
				</TD>
				<TD width="4"></TD>
		  	</TR> 
			<TR>
				<TD class="tdDetailLable">备注</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText" colspan="6">
					<html:textarea property="userNote" value="${userDetail.userNote}" styleClass="formDetailTxtarea" cols="110" rows="5" />							
				</TD>
				<TD width="8"></TD>
			</TR>
		</logic:present>
		</logic:equal>
		<!---------- 增加时 ----------->
		<logic:notEqual name="postMode" parameter="postMode"  value="edit">
		<html:hidden property="method" value="insert"/>	
			<TR>
				<TD class="tdDetailLable">用户账号</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userId" value="" styleClass="formDetailTxt" style="width:234px"/><span class="required">*</span>
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">用户名称</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text styleId="userName" property="userName" value="" styleClass="formDetailTxt" style="width:234px" /><span class="required">*</span>
				</TD>
				<TD width="4"></TD>
		  	</TR>
			<TR>
				<TD class="tdDetailLable">用户密码</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
               		<html:password styleId="password" property="userPassword" styleId="password" redisplay="true" styleClass="pswd" style="width:234px" /><span class="required">*</span>
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">校验密码</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
               		<html:password property="confirmUserPassword" styleId="confirmPassword" redisplay="true" onchange="CheckPws()" styleClass="pswd" style="width:234px" /><span class="required">*</span>
				</TD>
				<TD width="4"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">所属公司</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<!-- 隐藏字段不能验证 -->
					<html:hidden property="xtOrganization.orgId" styleId="orgId" value="${orgId}" />	             
               		<html:text property="xtOrganization.orgName" styleId="orgName" value="${orgName}" readonly="true"onclick="openOrgTree()" styleClass="popWindow" style="width:234px" title="单击这里打开公司树，选择所属公司" /><span class="required">*</span>
				</TD>
				<TD width="8">
				</TD>
				<TD class="tdDetailLable">所属部门</TD>
				<TD width="4" class="tdDetailNeed"></TD>
				<TD class="tdDetailText">
					<!-- 隐藏字段不能验证 -->
					<html:hidden property="xtDept.deptId" styleId="deptId" value="" />
           			<html:text property="xtDept.deptName" styleId="deptName" value="" readonly="true" onclick="openDeptList()" styleClass="popWindow" style="width:234px" title="单击这里打开部门列表，选择所属部门" />
				</TD>
				<TD width="4">
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">首选功能</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:hidden property="chiefMenuId" styleId="chiefMenuId" value="" />
	           		<html:text property="chiefMenuName" styleId="chiefMenuName" value="" readonly="true" onclick="openMenuTree()" styleClass="popWindow" style="width:234px" title="单击这里打开菜单树，选择首选菜单" />
				</TD>
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">指定密码过期日</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userPswEndDateDiplay" readonly="true" style="cursor: text;" onclick="WdatePicker({skin:'ext',minDate:'%y-%M-{%d+1}',isShowWeek:true,isShowClear:false,readOnly:true})" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="4"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">电话</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userTel" value="" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">手机</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userMobile" value="" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="4"></TD>
		  	</TR>
			<TR>
				<TD class="tdDetailLable">电子邮件</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userEmail" value="" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">地址</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="userAddr" value="" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="4"></TD>
		  	</TR>
		  	<!-- IceWee 09-05-24 -->
		  	<logic:equal value="true" name="useGroup">
		  	<TR>
		  		<TD class="tdDetailLable">所属组</TD>
				<TD width="4" class="tdDetailNeed"></TD>
				<TD class="tdDetailText">
					<html:hidden property="groupId" styleId="groupId" value="" />
               		<html:text property="groupName" styleId="groupName" value="" readonly="true" onclick="openGroupList()" styleClass="popWindow" style="width:234px" title="单击这里打开组织树，选择所属组" />
				</TD>
				<TD width="4">
				</TD>
				<TD colspan="4">&nbsp;</TD>
			</TR>
			</logic:equal>
		  	<TR>
				<TD class="tdDetailLable">密码永不过期</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:checkbox property="userPswInvalid" styleId="addUserPswInvalid" value="true" onclick="onUserPswClick(this)" ></html:checkbox>
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">帐号是否有效</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:checkbox property="userInvalid" styleId="addUserInvalid" onclick="onUserInvalid(this)" ></html:checkbox>
				</TD>
				<TD width="4"></TD>
		  	</TR> 
			<TR>
				<TD class="tdDetailLable">备注</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText" colspan="6">
					<html:textarea property="userNote" value="" styleClass="formDetailTxtarea" cols="110" rows="5" />							
				</TD>
				<TD width="8"></TD>
			</TR>
			</logic:notEqual>
			<TR>
				<TD class="tdDetailLable">授权角色</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText" colspan="6">
					<input type="hidden" id="roleIds" name="roleIds" value="${requestScope.roleIds}">
					<input type="text" id="roleNames" name="roleNames" value="${requestScope.roleNames}" onclick="chooseRole()" style="width: 790px; cursor: text;" readonly="true" class="popWindow" title="点击此处选择授权角色" />
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
<html:javascript formName="xtUserForm" />
</BODY>
</html>