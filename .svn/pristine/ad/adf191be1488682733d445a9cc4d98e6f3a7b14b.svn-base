<%@ page language="java" pageEncoding="GBK"%>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">

<html>
<head>
<title>风力发电分析系统</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript">
<!--
	var isFullScreen = false;

	function exit() {
		if(window.confirm('是否确定要退出系统？')) {
			document.submitform.action = 'logoff.do';
			document.submitform.target = '_parent';
			document.submitform.submit();
		}
	}
	
	function help() {							

	}
	
	function changeWin() {
		isFullScreen = !isFullScreen;
		changeImage();
		show_hidden();
	}
	
	function changeImage() {
		if(isFullScreen){
			$('#ImgArrow').attr('src','images/foldMenu/scroll_right.png');
		}else{
			$('#ImgArrow').attr('src','images/foldMenu/scroll_left.png');
		}
	}
	
	function show_hidden() {
		if(isFullScreen){
			$('#foldMenu').css('display', 'none');	
		}else{
			$('#foldMenu').css('visibility', 'visible');
			$('#foldMenu').css('display', 'block');
		}
	}
	
	/** query tree begins --> */

	function disLevel(a){
		window.frames['queryTree']._disLevel(a);
	}
	
	function showTreeShadow(){
		window.frames['queryTree'].showDivShadow();
	}
	
	function hideTreeShadow(){
		window.frames['queryTree'].hideDivShadow();
	}
	
	function showEuTreefault(type, depth, mode, fltypes) {
		$('#tdQueryTree').css('display', 'block');
		window.frames['queryTree'].showTree(type, depth, mode, fltypes);
		window.frames['queryTree'].hideDivShadow();
	}
	
	function hideEuTree() {
		$('#tdQueryTree').css('display', 'none');
	}
	
	function getEuTreeSelected() {
		return window.frames['queryTree'].getSelectedId();
	}

	/** query tree ends <-- */
//-->
</script>
</head>
<body class="">
<form id="submitform" name="submitform" style="visibility:hidden" action="" method="post"></form>
<TABLE width="100%" height="100%" cellSpacing="0" cellPadding="0" border="0">
<!-- 引入系统头部 -->
<%@include file="header.jsp" %>
	<!-- 标题下部空白区域 -->
	<tr>
		<td style="height: 1px; background-color: #65b9f7;"></td>
	</tr>
	<TR>
		<TD height="100%" width="100%">
		<TABLE height="100%" cellSpacing="0" cellPadding="0" width="100%" border="0" class="frameTableBorder">
			<TR>
				<TD width="1" bgcolor="#65b9f7"></TD>
				<!-- 左侧折叠菜单 -->
				<TD id="foldMenu" height="100%" width="200px">
				<TABLE height="100%" cellSpacing="1" cellPadding="1" width="100%" border="0" id="td1">
					<TR>
						<TD height="100%" valign="middle">
							<IFRAME style="visibility: inherit; width: 100%; height: 100%" name="foldMenuIframe" id="foldMenuIframe" src="${pageContext.request.contextPath}/usermgr/foldMenu.do" frameBorder="0"></IFRAME>
						</TD>
					</TR>
				</TABLE>
				</TD>
				<!-- 显示/隐藏竖条 -->
				<td>
					<table height="100%" cellSpacing="0" cellPadding="0" width="100%" border="0">
						<TR>
							<TD style="margin: 20px;" height="27" colspan="2" background="${pageContext.request.contextPath}/images/green/frame/locationBar.jpg">
								<img align="absmiddle" src="${pageContext.request.contextPath}/images/green/frame/nav.png">
								当前位置：<span id="currentLocation">风力发电分析系统</span>
							</TD>
						</TR>
						<tr>
							<TD id="treeBar" height="100%" width="15">
								<TABLE cellSpacing="0" cellPadding="0" height="100%">
									<TR>
										<TD></TD>
									</TR>
									<TR>
										<TD><img height="60"  width="15" src="images/foldMenu/scroll_left.png" onclick="changeWin()" border="0" id="ImgArrow" name="ImgArrow" style="cursor:hand;" alt="显示/隐藏菜单导航"></TD>
									</TR>
									<TR>
										<TD></TD>
									</TR>
								</TABLE>
							</TD>
							<TD id="pageContent" height="100%" width="">
								<TABLE height="100%" cellSpacing="0" cellPadding="0" width="100%" border="0">
					
									<TR>
										<TD id="tdQueryTree" vAlign=top align="center" height="100%" width="230px" style="display:none;background-image:url(${pageContext.request.contextPath}/images/backgroundcolor.jpg); background-repeat:repeat;">				
											<iframe id="queryTree" name="queryTree" src="querytree/tree.do?" align="left" frameBorder="no" width="100%" height="100%" scrolling="no"></iframe>				
										</TD>
										<TD height="100%" >
											<IFRAME style="width: 100%; height: 100%" name="pageContentIframe" id="pageContentIframe" frameBorder="0" frameBorder="0"></IFRAME>
										</TD>
									</TR>
								</TABLE>
							</TD>
					</table>
				</td>	
				<TD width="2" bgcolor="#000000" colspan="2"></TD>
			</TR>
		</TABLE>
		</TD>
	</TR>
</TABLE>
</body>
</html>