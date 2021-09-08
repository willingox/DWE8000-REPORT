<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>可复选菜单树</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/dhtmlxTree/dhtmlxtree.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlxTree/dhtmlxcommon.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlxTree/dhtmlxtree.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript">
<!--
	var CLEARTime = 3000;
	function removeMessage(){
		$('#message').css('display', 'none');
	}

	function permission() {
		if (tree) {
			var ids = tree.getAllChecked();
			document.submitForm.menuIds.value = ids;
			document.submitForm.action = '${pageContext.request.contextPath}/usermgr/menuAuthorize.do';
			document.submitForm.submit();
			forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
		}
	}
	
	window.onload = function() {
		if (tree) {
			tree.openAllItems('-1');
		
		}
		window.setTimeout(removeMessage, CLEARTime);
	}
	
	function selectAll(){
		if (tree) {
			tree.setCheck('-1', true);
		}
	}
	
	function unselectAll(){
		if (tree) {
			tree.setCheck('-1', false);
		}
	}
	
	function openAll(){
		if (tree) {
			tree.openAllItems('-1')
		}
	}
	
	function closeAll(){
		if (tree) {
			tree.closeAllItems('-1');
		}
	}
//-->
</script>
</HEAD>
<BODY scroll="no">
<TABLE width="100%" height="100%" cellSpacing="0" cellPadding="0" border="0">
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
		<TD valign="top" align="center">
		<table height="100%" width="100%" border="0">
			<tr>
				<td valign="middle" style="height: 30px; width: 50%; text-align: center;">
					<span>请勾选需要授权的菜单项</span>
				</td>
				<td width="50%">&nbsp;
				</td>
			</tr>
			<tr>
				<td valign="top" height="100%" width="50%">
					<div id="menu_tree" style="border: 1px solid #DBECE6; width:100%; height:100%; overflow:auto; padding-left: 50px;"></div>
				</td>
				<td height="100%" width="50%">
				<table height="100%" width="100%" border="0">
					<tr>
						<td valign="middle" style="height: 40px; padding-left: 5px;">
							<a class="abtn4" href="javascript:openAll()"><span>全部展开</span></a>
						</td>
					</tr>
					<tr>
						<td valign="middle" style="height: 40px; padding-left: 5px;">
							<a class="abtn4" href="javascript:closeAll()"><span>全部关闭</span></a>
						</td>
					</tr>
					<tr>
						<td valign="middle" style="height: 40px; padding-left: 5px;">
							<a class="abtn4" href="javascript:selectAll()"><span>全部选中</span></a>
						</td>
					</tr>
					<tr>
						<td valign="middle" style="height: 40px; padding-left: 5px;">
							<a class="abtn4" href="javascript:unselectAll()"><span>取消全选</span></a>
						</td>
					</tr>
					<tr>
						<td height="10"></td>
					</tr>
					<tr>
						<td valign="middle" style="height: 40px; padding-left: 5px;">
							<a class="abtn4" href="javascript:permission()" onclick="javascript:permission()"><span>保存授权</span></a>
						</td>
					</tr>
					<tr>
						<td height="100%"></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr><td height="10"></td></tr>
		</table>
		<script type="text/javascript">
		<!--
			tree = new dhtmlXTreeObject("menu_tree","100%","100%",0);
			tree.setSkin('dhx_skyblue');
			tree.setImagePath("${pageContext.request.contextPath}/scripts/dhtmlxTree/imgs/csh_bluebooks/");
			tree.enableCheckBoxes(true);
			tree.enableThreeStateCheckboxes(true);
			var treeStr = "${menuTree}";
			tree.loadXMLString(treeStr);
		//-->
		</script>
	  	</TD>
	</TR>
</TABLE>
<form name="submitForm" action="" method="post">
<input type="hidden" name="method" value="savePermAssign">
<input type="hidden" name="roleId" value="${roleId}">
<input type="hidden" name="menuIds">
</form>
</BODY>
</html>