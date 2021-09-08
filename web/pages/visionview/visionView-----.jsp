<%@ page language="java" pageEncoding="gbk" contentType="text/html; charset=gbk" import="java.io.File" %>

<%
	// 检查svg文件是否存在
	boolean isExist = false;

	String svgFileName = request.getParameter("svgfile");
	if (svgFileName!=null && !"".equals(svgFileName)) {

		String rootPath = this.getServletContext().getRealPath("/");
		// dir path + filename + extension
		String svgFullPath = rootPath + "svg/" + svgFileName + (svgFileName.lastIndexOf(".svg")==-1 ? ".svg" : "");
		File file = new File( svgFullPath );
		if (file != null) {
			isExist = file.exists();
		}
	}

	if (!isExist) {
		// if the svg file requested does not exist
		request.setAttribute("svgfile", svgFileName);

		String path = "/pages/visionview/svgNotFound.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(path);
		if (rd == null) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
					"Cannot get request dispatcher for path " + path);
			return;
		}
		rd.forward(request, response);
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
	<%-- title 取页面文件的名字 --%>
	<title>"${param.svgfile}"</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gbk">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ajaxutil.js"></script>
	<script type="text/javascript">

		// constants
		var REFRESH_DELAY_TIME = 10 * 1000;  //30s

		var PARAMS_EXCLUDED = ["svgfile", "_rand_"];
		
		// svg file information
		var svgFile = "${param.svgfile}";
		var svgFileParams = "";


		// initialization
		function init() {
			// check if the svgviewer plugin exists
			if (!isInstalledASV()) {
				//if (confirm("要浏览实时图形页面，必须安装Adobe SVG Viewer。\n\n您确定现在安装吗？")) 
				//	window.open('${pageContext.request.contextPath}/pages/visionview/downloadSvg.jsp', '', 'height=100, width=200, top=100,left=100, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');

				// NOTE: IE 浏览器 可能会阻止弹出窗口
				//window.location.href = "${pageContext.request.contextPath}/pages/visionview/downloadSvg.jsp";
				return;
			}
			
			// TODO 浏览器字体检测

			// get the parameters for svg file
			getSvgParams();
			
			// change the background color of page
			
			// the amount of time is in milliseconds
			setTimeout( "getSvgXML()", 1000 );
			
			// change the width and height of svg root element
			changeSvgViewport();
			
			// disable auto updating
			svgplayer.window.disableAutoUpdate();
			
			// 加载svg菜单
		}
		
		// check if the svgviewer plugin exists
		function isInstalledASV() {
			var bInstalled = false;
			var version = '-';

			if (navigator.plugins && navigator.plugins.length) {
				var plugins = navigator.plugins;
				var length = plugins.length;
				for (var ii=0; ii < length; ii++) {
					if( plugins[ii].name.indexOf( 'SVG Document') != -1 ) {
						bInstalled = true;
						version = plugins[ii].description.split('SVG Document ')[1];
						break;
					}
				}
			}
			else if (window.ActiveXObject) {
				for (var ii=5; ii>=1; ii--) {
					try{
						var bc = eval("new ActiveXObject('Adobe.SVGCtl."+ii+"');" );
						if( bc ) {
							bInstalled = true;
							version = ii + '.0';
							break;
						}
					}catch(e){}
				}
			}
			return bInstalled;
		}
		
		// change the viewport size of the svg file
		function changeSvgViewport() {
			var docWidth = Geometry.getDocumentWidth();
			var docHeight = Geometry.getDocumentHeight();
			
			var svgRoot = svgplayer.getSVGDocument().rootElement;
			var svgDocWidth = svgRoot.getAttribute("width");
			var svgDocHeight = svgRoot.getAttribute("height");
			
			if (docWidth!==svgDocWidth || docHeight!==svgDocHeight) {
				svgRoot.setAttribute("width", docWidth);
				svgRoot.setAttribute("height", docHeight);
			}
		}
		

		function getSvgParams() {
			var url = window.location.href;
			var offset = url.indexOf('?');
			if (offset != -1) {
				url = url.substring(offset+1);

				var len = PARAMS_EXCLUDED.length;
				for (var i = 0; i < len; i++) {
					var sIdx = url.indexOf(PARAMS_EXCLUDED[i]);
					if (sIdx != -1) {
						var eIdx = url.indexOf('&', sIdx);
						if (eIdx == -1) 
							eIdx = url.length;
						else 
							eIdx += 1;
						url = url.replace( url.substring(sIdx, eIdx), '');
					}
				}
				url = url.replace(/^(\&)+/, '').replace(/(\&)+$/, '');
				
				svgFileParams = url;
			}
		}
		
		// request for dynamic data
		function getSvgXML() {
			if (svgFile) {
				var url = "${pageContext.request.contextPath}/servlet/visionView?fileName=" + svgFile;
				if (svgFileParams) 
					url += "&" + svgFileParams;
				//new net.ContentLoader(url, onLoadSvgXml, onLoadSvgError);
				
				sendXMLHttpRequest(url, null, null, onLoadSvgXml);
			}
		}
		
		// Version I
		/*
		function onLoadSvgXml() {
			// do update for svg

			// 将返回结果字符串解析成XML DOM对象, 有两种处理方案: 
			//   1)通过svgviewer-plugin的parseXML()函数; 2)通过浏览器XML解析函数集.
			// 此处采用的是方案1.
			
			var responseText = this.req.responseText;
			//alert("content:" + responseText);
			var oXml = svgplayer.window.parseXML( responseText, svgplayer.getSVGDocument() );
			oXml = oXml.firstChild;  // documentElement
			if (oXml) 
				updateSvg(svgplayer.getSVGDocument(), oXml);
			
			// next request
			setTimeout( "getSvgXML()", REFRESH_DELAY_TIME );
		}
		*/
		
		// Version II
		function onLoadSvgXml(responseText) {
			// do update for svg

			// 将返回结果字符串解析成XML DOM对象, 有两种处理方案: 
			//   1)通过svgviewer-plugin的parseXML()函数; 2)通过浏览器XML解析函数集.
			// 此处采用的是方案1.

			//alert("content:" + responseText);
			if (responseText == null || '' == responseText) 
				return;
			var oXml = svgplayer.window.parseXML( responseText, svgplayer.getSVGDocument() );
			oXml = oXml.firstChild;  // documentElement
			if (oXml) 
				updateSvg(svgplayer.getSVGDocument(), oXml);
			
			// next request
			setTimeout( "getSvgXML()", REFRESH_DELAY_TIME );
		}
		
		function updateSvg(svgDoc, oXml) {
			if (!svgDoc || !oXml.hasChildNodes()) 
				return;

			var childNodes = oXml.childNodes;
			var childCount = (childNodes ? childNodes.length : 0);
			for (var i = 0; i < childCount; i++) {
				var curNode = childNodes.item(i);
				
				if (curNode && curNode.nodeType === 1) {
					//alert(curNode.tagName + ", nodeId:" + curNode.getAttribute("id"));

					// Element
					var preNode = null;
					var nodeId = curNode.getAttribute("id");
					//if (nodeId == "Text218") 
					//	alert("Text218:\n" + svgplayer.window.printNode(curNode));

					if (nodeId) 
						preNode = svgDoc.getElementById(nodeId);
					if (preNode) {
						var parentNode = preNode.parentNode;
						parentNode.replaceChild(curNode, preNode);
					}
				}
			}
		}
		
		function onLoadSvgError() {
		}
		
		
		// page hyperlink
		
		var VOID_LINK_A = "javascript:void(0)";
		
		function openPage(evt, url, target, params) {
			if (!url) return;

			var svgFile = url;
			var idx = svgFile.lastIndexOf('.svg');
			if (idx != -1) 
				svgFile = svgFile.substring(0, idx);

			var fullUrl = "${pageContext.request.contextPath}/pages/visionview/visionView.jsp?svgfile=" + svgFile + "&_rand_=" + new Date();
			
			if (params) {
				//params = params.replace(/\&/g, "&amp;");
				fullUrl += "&amp;" + params;
			}
			
			var ancestorA = null;
			for (var p = evt.getTarget(); p; p = p.parentNode) {
				if (p.tagName == 'a') {
					ancestorA = p;
					break;
				}
			}
			if (ancestorA) {
				if (ancestorA.getAttribute("xlink:href") !== VOID_LINK_A) {
					ancestorA.setAttribute("xlink:href", VOID_LINK_A);
				}
				if (ancestorA.hasAttribute("target")) {
					ancestorA.removeAttribute("target");
				}
			}

			//alert('(' + fullUrl + ')');
			//window.open(fullUrl, (!target ? "" : target));
			setTimeout(function() {
				window.open(fullUrl, target);
			}, 0);
		}


	</script>
</head>





<body scroll="no" topmargin="0" leftmargin="0" onload="init();" onResize="">

	<table id="maintable" width="100%" height="100%" border="0">
		<tr>
			<td width="100%" align="left" valign="top">
<iframe src="${pageContext.request.contextPath}/svg/rect.svg" width="300" height="100"> </iframe>
	
			</td>
		</tr>
	</table>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/geometry.js"></script>
	<script type="text/javascript">
	
		//init();
	
	</script>

</body>
</html>
