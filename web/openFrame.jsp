<%@ page contentType="text/html;charset=gbk" language="java"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>页面跳转中...</title>
<script type="text/javascript">
<!--
	window.onload = function openWindow(){
		screenWidth = window.screen.width - 10;
		screenHeight = window.screen.height;
		/*window.opener = null; 
		window.open('', '_self');	// 不提示是否关闭窗口
		window.close();*/

		var url = '${pageContext.request.contextPath}/chooseMode.do?locationMode=${requestScope.tree}&time=' + new Date().getTime();
		if ('tree' != '${requestScope.tree}') {
			url += "&hStyle=alwaysvisible = 1;left = 15; margin = 0;orientation = 'horizontal';style = horizStyle;top = 59;"
				+ "&vStyle=margin = 1;style = vertStyle;overflow = 'scroll';" 
				+ "&sTarget=contents";
		}
		window.open(url, '_self', 'scrollbars=yes,resizable=yes,channelmode');	// 开启一个被F11化后的窗口
		return false;
	}
//-->
</script>
</head>
<body bgcolor="#EAEAF4" scroll="no">
</body>
</html>