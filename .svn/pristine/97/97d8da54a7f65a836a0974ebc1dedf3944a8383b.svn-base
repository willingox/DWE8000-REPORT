<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>菜单详细</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript">
<!--
	window.onload = function onload() {
		if(xtMenuForm.addMenuActive) {
			xtMenuForm.addMenuActive.checked = true;			
		}
	}
			
	function openMenuTree() {
		var menuId = xtMenuForm.menuId.value;
		var value = window.showModalDialog('${pageContext.request.contextPath}/usermgr/menu.do?method=getMenuTreeExc&menuId=' + menuId + '&rand=' + new Date().getTime(), '', 'dialogHeight:500px;dialogWidth:400px;help:no;status:no');	
		if(value) {
			$('#parentMenuId').val(value[0]);
			$('#parentMenuName').val(value[1]);
			autoInputMenuOrder(value[0]);	// 自动填充菜单排序号
		}
	}
	
	function autoInputMenuOrder(menuId) {
		var url = '${pageContext.request.contextPath}/usermgr/menu.do?method=getMenuOrder&menuId=' + menuId + '&time=' + new Date().getTime();
		$.ajax({
			type: 'POST',
			url: url,
		  	success: function (data) {
		  		xtMenuForm.menuOrder.value = data;
		  	}
		});
	}
	
	function openResTree() {	
		var value = window.showModalDialog('${pageContext.request.contextPath}/usermgr/res.do?method=openResSelectDialog&rand=' + new Date().getTime(), '', 'dialogHeight:500px;dialogWidth:700px;help:no;status:no');	
		if(value) {
			$('#resId').val(value[0]);
			$('#resName').val(value[1]);
		}
	}
	
	function save() {
		if (validateXtMenuForm(xtMenuForm)) {
			xtMenuForm.submit();
			forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
		}
	}
	
	function bk2lst() {
		xtMenuForm.method.value = 'listSubMenusByMenuId';
		xtMenuForm.submit();
	}
//-->
</script>
</HEAD>
<BODY scroll="no">
<html:form action="menu" method="post">
<html:hidden property="method" value="save"/>
<html:hidden property="selectedMenuId" />
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
		<logic:present name="menu" >
			<TR>
				<TD class="tdDetailLable">菜单名称</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
                	<html:hidden property="menuId" value="${menu.menuId}" />
                	<html:text property="menuName" value="${menu.menuName}" styleClass="formDetailTxt" size="40" /><span class="required">*</span>
				</TD>
				<TD width="8"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">上级菜单</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
               		<html:hidden property="xtMenu.menuId" styleId="parentMenuId" value="${menu.xtMenu.menuId}" />
               		<html:text property="xtMenu.menuName" styleId="parentMenuName" value="${menu.xtMenu.menuName}" readonly="true" onclick="openMenuTree()" styleClass="popWindow" size="40" title="单击这里打开系统菜单树，选择上级菜单" />
				</TD>
				<TD width="8">
				</TD>
		  	</TR>
		  	<TR>
				<TD class="tdDetailLable">是否有效</TD>
				<TD width="4" class="tdDetailNeed"></TD>
				<TD class="tdDetailText">
					<html:checkbox property="menuActive" value="true"></html:checkbox>
				</TD>
				<TD width="8"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">对应资源</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
                 	<html:hidden property="xtResource.resId" styleId="resId" value="${menu.xtResource.resId}" />
                  	<html:text property="xtResource.resName" styleId="resName" value="${menu.xtResource.resName}" readonly="true" onclick="openResTree()" styleClass="popWindow" size="40" title="单击这里打开资源列表，选择对应资源" />
				</TD>
				<TD width="8">
				</TD>
		  	</TR>
		  	<TR>
				<TD class="tdDetailLable">菜单提示</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="menuHint" value="${menu.menuHint}" styleClass="formDetailTxt" size="40" />
				</TD>
				<TD width="8"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">菜单描述</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="menuDesc" value="${menu.menuDesc}" styleClass="formDetailTxt" size="40" />
				</TD>
				<TD width="8"></TD>
		  	</TR>
		  	<TR>
				<TD class="tdDetailLable">初始状态图标路径</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="menuIconPath" value="${menu.menuIconPath}" styleClass="formDetailTxt" size="40" />
				</TD>
				<TD width="8"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">初始状态图标文件名</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="menuIcon" value="${menu.menuIcon}" styleClass="formDetailTxt" size="40" />
				</TD>
				<TD width="8"></TD>
		  	</TR>
			<TR>
				<TD class="tdDetailLable">打开状态图标路径</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="menuIconPathOpen" value="${menu.menuIconPathOpen}" styleClass="formDetailTxt" size="40" />
				</TD>
				<TD width="8"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">打开状态图标文件名</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="menuIconOpen" value="${menu.menuIconOpen}" styleClass="formDetailTxt" size="40" />
				</TD>
				<TD width="8"></TD>
		  	</TR>				  		
		  	<TR>
				<TD class="tdDetailLable">排序号</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="menuOrder" value="${menu.menuOrder}" styleClass="formDetailTxt" size="40" /><span class="required">*</span>
				</TD>
				<TD width="8"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">在新窗口上打开页面</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:checkbox property="menuIsNewWindowOpen" value="true"></html:checkbox>
				</TD>
				<TD width="8"></TD>
		  	</TR>					  						  		
		</logic:present>
		<!---------- 增加时 ----------->
		<logic:notPresent name="menu" >
			<TR>
				<TD class="tdDetailLable">菜单名称</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
                	<html:text property="menuName" value="${menu.menuName}" styleClass="formDetailTxt" size="40" /><span class="required">*</span>
				</TD>
				<TD width="8"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">上级菜单</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:hidden property="xtMenu.menuId" styleId="parentMenuId" value="${requestScope.parentMenu.menuId}" />
               		<html:text property="xtMenu.menuName" styleId="parentMenuName" value="${requestScope.parentMenu.menuName}" readonly="true" styleClass="formDetailTxtDisable" size="40" />
				</TD>
				<TD width="8"></TD>
		  	</TR>
		  	<TR>
				<TD class="tdDetailLable">是否有效</TD>
				<TD width="4" class="tdDetailNeed"></TD>
				<TD class="tdDetailText">
					<html:checkbox property="menuActive" styleId="addMenuActive"></html:checkbox>
				</TD>
				<TD width="8"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">对应资源</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
               		<html:hidden property="xtResource.resId" styleId="resId" value="${menu.xtResource.resId}" />
               		<html:text property="xtResource.resName" styleId="resName" value="${menu.xtResource.resName}" readonly="true" onclick="openResTree()" styleClass="popWindow" size="40" title="单击这里打开资源列表，选择对应资源" />
				</TD>
				<TD width="8">
				</TD>
		  	</TR>
		  	<TR>
				<TD class="tdDetailLable">菜单提示</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="menuHint" value="${menu.menuHint}" styleClass="formDetailTxt" size="40" />
				</TD>
				<TD width="8"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">菜单描述</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="menuDesc" value="${menu.menuDesc}" styleClass="formDetailTxt" size="40" />
				</TD>
				<TD width="8"></TD>
		  	</TR>
		  	<TR>
				<TD class="tdDetailLable">初时状态图标路径</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="menuIconPath" value="${menu.menuIconPath}" styleClass="formDetailTxt" size="40" />
				</TD>
				<TD width="8"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">初时状态图标文件名</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="menuIcon" value="${menu.menuIcon}" styleClass="formDetailTxt" size="40" />
				</TD>
				<TD width="8"></TD>
		  	</TR>
			<TR>
				<TD class="tdDetailLable">打开状态图标路径</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="menuIconPathOpen" value="${menu.menuIconPathOpen}" styleClass="formDetailTxt" size="40" />
				</TD>
				<TD width="8"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">打开状态图标文件名</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="menuIconOpen" value="${menu.menuIconOpen}" styleClass="formDetailTxt" size="40" />
				</TD>
				<TD width="8"></TD>
		  	</TR>				  			
		  	<TR>
				<TD class="tdDetailLable">排序号</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="menuOrder" value="${menu.menuOrder}" styleClass="formDetailTxt" size="40" /><span class="required">*</span>
				</TD>
				<TD width="8"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">在新窗口上打开页面</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:checkbox property="menuIsNewWindowOpen" value="true"></html:checkbox>
				</TD>
				<TD width="8"></TD>
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
<html:javascript formName="xtMenuForm" />
</BODY>
</html>