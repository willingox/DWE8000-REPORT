<%@ page language="java" pageEncoding="gbk" contentType="text/html; charset=gbk" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Vision Left Tree</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/xtree/xtree.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/xtree/xtree.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/net.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/visiontree.js"></script>

		<script type="text/javascript">

			function visionTreeHandler() {
				var node = webFXTreeHandler.selected;
				//alert(node.id + ', text=' + node.text + ', parentNode=' + node.parentNode);

				//如果是文件夹节点，则跳过
				if (!node.folder) {
					var path = getFullPathForNode(node);
					// V1 -- url params
				//	parent.frames['visionMain'].location = "visionView.jsp?svgfile=" + path;
					// V2 -- form post
					window.setTimeout(function() {
					//	if (!!path && path.indexOf('#')!=-1) 
					//		path = path.replace('#', '*');
						document.getElementById('svgfile').value = path;
						svgForm.target = 'visionMain';
						svgForm.submit();
					}, 0);
				}
			}

		</script>
		<style type="text/css">

			.divScroll {
				width: 98%; 
				height: 100%;
				font-size: 12px;
				padding: 0px;
				margin: 0px;
				overflow: auto;
				scrollbar-face-color: #ffffff;
				scrollbar-highlight-color: #ffffff;
				white-space: nowrap;
				/*border: 5px solid red;*/
			}

		</style>
	</head>

	<body scroll="no" style="background-color: #ECE9D8;">
		<form id="svgForm" name="svgForm" method="post" action="visionView.jsp">
			<input type="hidden" id="svgfile" name="svgfile" value="">
		</form>
		<div class="divScroll">

		<script type="text/javascript">

			//"监视页面"
			//var tree = new VisionTree("\u76d1\u89c6\u9875\u9762", "${pageContext.request.contextPath}", "${pageContext.request.contextPath}/scripts/xtree", visionTreeHandler); 
			var tree = new VisionTree("\u76d1\u89c6\u9875\u9762", "${pageContext.request.contextPath}", "${pageContext.request.contextPath}/scripts/xtree", "javascript:visionTreeHandler();"); 

		</script>

		</div>

	</body>
</html>
