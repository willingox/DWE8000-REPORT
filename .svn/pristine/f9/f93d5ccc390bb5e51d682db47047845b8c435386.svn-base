<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1   
response.setHeader("Pragma","no-cache"); //HTTP 1.0   
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server 
%>
<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>query tree</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript">
<!--
	function getCurSelect() {
     	if (parent) 
     		$('#curSelectId').html( parent.getEuTreeSelected() );
     	else 
     		$('#curSelectId').html( '' );
    }
	
	function showTree() {
		if (parent) {
			parent.showEuTreefault( 
				$('input:radio[name=treeType]:checked').val(), 
				$('input:radio[name=treeLevel]:checked').val(), 
				$('input:radio[name=treeMode]:checked').val(), 
				$('input:radio[name=flType]:checked').val() );
		}
	}
	
	function hideTree() {
		if (parent) 
			parent.hideEuTree();
	}

//-->
</script>
</HEAD>
<BODY scroll="no" style="background-image:url(${pageContext.request.contextPath}/images/backgroundcolor.jpg); background-repeat:repeat;">

<div style="padding: 0px; margin: 0px auto; height:100%;">

	<div style="width:400px;height:100px;left:30px;top: 10px;position: absolute;">
		<input type="radio" name="treeMode" value="check" checked>checkbox
		<input type="radio" name="treeMode" value="radio">radio
		<div style="height:10px;"></div>
		<input type="radio" name="treeLevel" value="1">1级
		<input type="radio" name="treeLevel" value="2">2级
		<input type="radio" name="treeLevel" value="3">3级
		<div style="height:10px;"></div>
		<input type="radio" name="treeType" value="jz"><span style="color:blue; width:50px;overflow:auto;">地理位置</span>
		<div style="height:5px;"></div>
		<input type="radio" name="treeType" value="flfx"><span style="color:blue; width:50px;overflow:auto;">分类分项</span>
		<div style="height:5px;"></div>
		<input type="radio" name="treeType" value="fl"><span style="color:blue; width:50px;overflow:auto;">分类</span>
		<div></div>
		<div style="margin:0;margin-top:2px;margin-left:30px;">
		<input type="radio" name="flType" value="301">电
		<input type="radio" name="flType" value="302">水
		<input type="radio" name="flType" value="303">燃气
		</div>
		<div style="height:20px;"></div>
		<input type="button" value="show tree" onclick="showTree()">
		<input type="button" value="hide tree" onclick="hideTree()">
		<div style="height:20px;"></div>
		<input type="button" value="current selected id" onclick="getCurSelect()">
		<span id="curSelectId" style="color:red; width:100%;height:50px;overflow:auto;"></span>
	</div>

</div>
</BODY>
</html>
