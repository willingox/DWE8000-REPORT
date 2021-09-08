<%@ page contentType="text/html;charset=gbk" language="java"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/TreeView.js"></script>
<title>公司树</title>
<script type="text/javascript">
<!--
	function returnCurrentNode(){
		//获取分隔符
		var d = tree.divider;
		//得到当前选中节点的值（该值由parentId和id组成，中间加分隔符）
		var sourceIndex = tree.currentNode.sourceIndex;
		var orgId = sourceIndex.substr(sourceIndex.indexOf(d) + d.length);//取出当前节点的组织ID	
		var source = tree.nodes[sourceIndex];//得到当前节点的对象
		var orgName  = tree.getAttribute(source, "text");//从当前节点对象中得到当前节点的text，也就是组织名称。
		//返回数组
		var returnArray = new Array();
		returnArray[0] = orgId;
		returnArray[1] = orgName;
		window.returnValue = returnArray;
		self.close();
	}

	function returnClose(){
		self.close();
	}
//-->
</script>
</head>
<body scroll="auto">
<table width="100%" height="100%" border="0">
	<tr>
		<td height="5">				
		</td>
	</tr>
	<tr>
		<td width="100%" height="100%" valign="top">
		<div align="left">
		<script type="text/javascript">
		<!--
		<logic:present name="Org">
			var tree = new MzTreeView("tree");
			tree.setIconPath("${pageContext.request.contextPath}/images/treeview/");
			tree.setTarget("iframe2");    
      		<logic:iterate name="Org" id="orgElement" scope="request" indexId="index">
		 		<logic:empty name="orgElement" property="xtOrganization">
		 			tree.nodes["0_${orgElement.orgId}"] = "text:${orgElement.orgName}";
		 		</logic:empty>									 
		 		<logic:notEmpty name="orgElement" property="xtOrganization">									 				
			   		tree.nodes["${orgElement.xtOrganization.orgId}_${orgElement.orgId}"] = "text:${orgElement.orgName};icon:property;";																				 								 
		 		</logic:notEmpty>
      		</logic:iterate>
			document.write(tree.toString());
		</logic:present>
		//-->
		</script>
		</div>
		</td>
	</tr>
	<tr>
		<td height="50">
		<hr />
		<table width="100%" height="100%" border="0">
			<tr>
				<td width="50%">&nbsp;</td>
				<td width="50%" valign="top">
					<a class="abtn3" href="#" onclick="javascript:returnCurrentNode()"><span>确&nbsp;&nbsp;定</span></a>
					<a class="aBtn3" href="#" onclick="javascript:returnClose()"><span>取&nbsp;&nbsp;消</span></a>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
