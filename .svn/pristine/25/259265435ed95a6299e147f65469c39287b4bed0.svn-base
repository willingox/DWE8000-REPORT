<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>部门详细</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript">
<!--
	var currDeptId = '${deptDetail.deptId}';	// 保存当前部门ID
	var oldOwnOrgId = '${deptDetail.xtOrganization.orgId}';	// 保留当前部门所属公司ID

	function openOrgTree() {
		var value = window.showModalDialog('${pageContext.request.contextPath}/usermgr/org.do?method=listOrgTreeByLoingUser&rand=' + new Date().getTime(), '', 'dialogHeight:500px;dialogWidth:400px;help:no;status:no');	
		if(value) {
			$('#oOrgId').val(value[0]);
			$('#oOrgName').val(value[1]);
			xtDeptForm.ownOrgId.value = value[0];
			$('#prtDeptId').val('');
			$('#prtDeptName').val('');
			xtDeptForm.parentDeptId.value = '';
		}
	}
	
	function openDeptList () {
		var ownOrgId = $('#oOrgId');
		if(ownOrgId.val() != '') {
			var value = window.showModalDialog('${pageContext.request.contextPath}/usermgr/dept.do?method=listDeptByOrgId&ownOrgId=' + ownOrgId.val() + '&rand=' + new Date().getTime(), '', 'dialogHeight:400px;dialogWidth:600px;help:no;status:no');	
			if(value) {
				$('#prtDeptId').val(value[0]);
				$('#prtDeptName').val(value[1]);
			}
		}else{
			alert('请先选择所属公司！');
		}
	}
	
	/**排除自己**/
	function openDeptListEx() {
		var ownOrgId = $('#oOrgId');
		if(ownOrgId.val() != '') {
			var deptId = $('#deptId');
			url = '${pageContext.request.contextPath}/usermgr/dept.do?method=listDeptByOrgIdExSelf';
			url += '&ownOrgId=' + ownOrgId.val() + '&deptId=' + deptId.val() + '&time=' + new Date().getTime();
			var value = window.showModalDialog(url, '', 'dialogHeight:400px;dialogWidth:600px;help:no;status:no');	
			if(value) {
				$('#prtDeptId').val(value[0]);
				$('#prtDeptName').val(value[1]);
			}
		}else{
			alert('请先选择所属公司！');
		}
	}
	
	function toSave() {
		if (validateXtDeptForm(xtDeptForm)) {
			if (currDeptId != '') {	// 编辑
				if ($('#oOrgId').val() != oldOwnOrgId) {	// 修改了所属公司
					if (confirm('修改所属公司将会丢失所有下级部门，确定修改吗？')) {
						save()
					}
				} else {
					save()
				}
			} else {	// 增加
				save()
			}
		}
	}
	
	function save() {
		xtDeptForm.submit();
		forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
	}
	
	function bk2lst() {
		xtDeptForm.method.value = 'listSubDeptByDeptId';
		xtDeptForm.submit();
	}
	
	var CLEARTime = 3000;
	function removeMessage() {
		$('#message').css('display', 'none');
	}
		
	window.onload = function() {
		window.setTimeout(removeMessage, CLEARTime);
	}
//-->
</script>
</HEAD>
<BODY scroll="no">
<html:form action="dept" method="post">
<html:hidden property="method" value="save"/>
<html:hidden property="ownOrgId" />
<html:hidden property="parentDeptId" />
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
			<logic:present name="deptDetail" >
			<TR>
				<TD class="tdDetailLable">部门名称</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
                    <html:hidden property="deptId" styleId="deptId" value="${deptDetail.deptId}" />
                    <html:text property="deptName" value="${deptDetail.deptName}" styleClass="formDetailTxt" size="32" /><span class="required">*</span>
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">所属公司</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:hidden property="xtOrganization.orgId" styleId="oOrgId" value="${deptDetail.xtOrganization.orgId}" />
                  	<html:text property="xtOrganization.orgName" styleId="oOrgName" value="${deptDetail.xtOrganization.orgName}" readonly="true" onclick="openOrgTree()" styleClass="popWindow" size="32" title="单击这里打开公司树，选择所属公司" /><span class="required">*</span>
				</TD>
		  	</TR>		 
			<TR>
				<TD class="tdDetailLable">电话</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="deptTel" value="${deptDetail.deptTel}" styleClass="formDetailTxt" size="32" />
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">传真</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="deptFax" value="${deptDetail.deptFax}" styleClass="formDetailTxt" size="32" />
				</TD>
		  	</TR>	
			<TR>
				<TD class="tdDetailLable">上级部门</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:hidden property="xtDept.deptId" styleId="prtDeptId" value="${deptDetail.xtDept.deptId}" />
                  	<html:text property="xtDept.deptName" styleId="prtDeptName" value="${deptDetail.xtDept.deptName}" readonly="true" onclick="openDeptListEx()" styleClass="popWindow" size="32" title="单击这里打开部门列表，选择上级部门" />
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">地址</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="deptAddr" value="${deptDetail.deptAddr}" styleClass="formDetailTxt" size="32" />
				</TD>
		  	</TR>	
		</logic:present>
		<!---------- 增加时 ----------->
		<logic:notPresent name="deptDetail" >
			<TR>
				<TD class="tdDetailLable">部门名称</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
                    <html:text property="deptName" styleClass="formDetailTxt" size="32" /><span class="required">*</span>
				</TD>
				<TD width="8"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">所属公司</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:hidden property="xtOrganization.orgId" styleId="oOrgId"  />
                  	<html:text property="xtOrganization.orgName" styleId="oOrgName" readonly="true" styleClass="formDetailTxt" size="32" /><span class="required">*</span>
				</TD>
		  	</TR>		 
			<TR>
				<TD class="tdDetailLable">电话</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="deptTel" styleClass="formDetailTxt" size="32" />
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">传真</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="deptFax" styleClass="formDetailTxt" size="32" />
				</TD>
		  	</TR>	
			<TR>
				<TD class="tdDetailLable">上级部门</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:hidden property="xtDept.deptId" styleId="prtDeptId" />
                  	<html:text property="xtDept.deptName" styleId="prtDeptName" readonly="true" styleClass="formDetailTxt" size="32" />
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">地址</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="deptAddr" styleClass="formDetailTxt" size="32" />
				</TD>
		  	</TR>						
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
					<a class="abtn3" href="javascript:toSave()"><span>保&nbsp;&nbsp;存</span></a>
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
 <html:javascript formName="xtDeptForm" />
</BODY>
</html>