<%@ page contentType="text/html;charset=gbk" language="java"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/TreeView.js"></script>
<title>��˾��</title>
<script type="text/javascript">
<!--
	function returnCurrentNode(){
		//��ȡ�ָ���
		var d = tree.divider;
		//�õ���ǰѡ�нڵ��ֵ����ֵ��parentId��id��ɣ��м�ӷָ�����
		var sourceIndex = tree.currentNode.sourceIndex;
		var orgId = sourceIndex.substr(sourceIndex.indexOf(d) + d.length);//ȡ����ǰ�ڵ����֯ID	
		var source = tree.nodes[sourceIndex];//�õ���ǰ�ڵ�Ķ���
		var orgName  = tree.getAttribute(source, "text");//�ӵ�ǰ�ڵ�����еõ���ǰ�ڵ��text��Ҳ������֯���ơ�
		//��������
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
					<a class="abtn3" href="#" onclick="javascript:returnCurrentNode()"><span>ȷ&nbsp;&nbsp;��</span></a>
					<a class="aBtn3" href="#" onclick="javascript:returnClose()"><span>ȡ&nbsp;&nbsp;��</span></a>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
