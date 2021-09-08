<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html>
<head>
<title>折叠式菜单</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">   
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/styles/foldMenu.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/foldMenu.js"></script>
<script type="text/javascript">
<!--
	window.onload = function() {
		root = '${pageContext.request.contextPath}/';
		var reqUrl = '';
		if('' != '${firstPage}') {
			reqUrl = root + '${requestScope.firstPage}';
			var arr = reqUrl.split('|');
			if (reqUrl.indexOf('newWindow') != -1) {	// 弹出新窗口，不改变当前位置内容
				window.open(arr[0], '', 'scrollbars=yes,resizable=yes,channelmode');
			} else {
				$('#currentLocation', parent.document).html(arr[1]);	// 当前位置
				$('iframe[name=pageContentIframe]', parent.document).attr('src', arr[0]);
			}
		}
		
		$("ul").each(function(){
			$(this).hide();					
		});
		
		/** 生成菜单绿色图标，图片必须写绝对路径才可显示，服务器端写好img标签图片不显示 */
		$("span").each(function() {
			var level = $(this).attr("level");
			if(1==level) return;
			var imgTag = $("<img></img>");
			$(imgTag).attr("src", "${pageContext.request.contextPath}/images/foldMenu/li_icon.gif");
			$(imgTag).attr("align", "middle");
			var text = $(this).html();
			$(this).html("");
			$(this).append(imgTag);
			var img = $(this).html();
			$(this).html(img + text);
			$("span").css("line-height","30px");
		});
		
		expandMenu();
		activeTopMenuA = $('#${defTopMenuId}')[0];	// 保存顶级活动菜单A
		activeTopMenuUl = $('#u_${defTopMenuId}')[0];	// 保存顶级活动菜单UL
		activeTopMenuDiv = $('#div_${defTopMenuId}')[0];	// 保存顶级活动菜单DIV
		/** 初始化菜单高度 */
		initMenuHeight();
	}
	
	/** 展开菜单，并高亮显示点击菜单项 */
	function expandMenu() {
		<logic:empty name="menuLayer">
			if('' != '${defaultMenuId}') {
				var firstTopMenu = $('#${defaultMenuId}')[0];
				firstTopMenu.className = 'active';
				var ul = $('#u_${defaultMenuId}');
				if(ul.size() != 0){	// 存在子菜单
					ul.show();
				}
			}
		</logic:empty>
		<logic:notEmpty name="menuLayer">
			<logic:iterate id="menuId" name="menuLayer">
				var defaultMenu = $('#${menuId}')[0];
				defaultMenu.className = 'active';
				/** 后面为1的样式都是具有子菜单可展开的节点 */
				if(defaultMenu.parentNode.className!='L1' && defaultMenu.parentNode.className!='L21' && defaultMenu.parentNode.className!='L31'){
					activeMenu = defaultMenu;
				}
				/** 获取下级ul列表 */
				var ul = $('#u_${menuId}');
				if(ul.size() != 0){	// 存在子菜单
					ul.show();
				}
			</logic:iterate>
		</logic:notEmpty>
	}
	
	function initMenuHeight() {
		if(activeTopMenuDiv) {
			$(activeTopMenuDiv).parent().height('100%');
		}
		
	}
//-->
</script>
</head>
<BODY scroll="no" class="panel">
<form name="foldMenuForm" action="" method="post">
<input type="hidden" name="pname" id="pname" />
</form>

<div id="menuDiv">
	<table height="100%" width="100%" cellSpacing="0" cellPadding="1" border="0">
		${menuHtml}
	</table>
</div>
</BODY>
</html>