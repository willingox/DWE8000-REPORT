<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>资源列表</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/dhtmlXTree.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXCommon.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXTree.js"></script>
<script type="text/javascript">
<!--
	function tonclick() {
		var nodeId = tree.getSelectedItemId()
		document.submitform.action = '${pageContext.request.contextPath}/usermgr/res.do?method=listDialogResByClassId&fixResClassId=' + nodeId;
		document.submitform.submit();
	}

	function returnRes() {
 		var returnArray = new Array();
		var allRes = resListIframe.document.getElementsByName('resId');
		for(var i=0; i<allRes.length; i++) {
			if(allRes[i].checked) {
			  	returnArray[0] = allRes[i].getAttribute("resId");
				returnArray[1] = allRes[i].getAttribute("resName");
				//returnArray[0] = allRes[i].resId;
				//returnArray[1] = allRes[i].resName;
				break;
			}
		}
		if(returnArray != '') {
			window.returnValue = returnArray;
		}else{
			window.returnValue = new Array('' ,'');
		}
		self.close();
	}
	
	function returnClose(){
		self.close();
	}
//-->
</script>
</HEAD>
<BODY scroll="no">
<form id="submitform" name="submitform"  method="post" target="resListIframe"></form>
<TABLE width="100%" height="100%" cellSpacing="0" cellPadding="0" border="0">
	<TR>
		<TD>					
		<table width="100%" height="100%" cellSpacing="0" cellPadding="0">
			<tr>
				<td id=leftTree width="28%" height="100%" valign="top" style="border: 1px solid #E2F5E6;">
					<div id="treebox_tree" style="width:100%; height:100%; overflow:auto;"></div>
				</td>					
				<td id="rightConext" width="72%" style="border-bottom: 1px solid #E2F5E6;">										
					<iframe id="resListIframe" name="resListIframe" src="${pageContext.request.contextPath}/usermgr/res.do?method=listDialogResByClassId&fixResClassId="; width="100%" height="100%" frameborder="0"></iframe>										
				</td>
			</tr>
		</table>
		</TD>
	</TR>
	<TR>
		<td height="50">
		<table width="100%" height="100%">
			<tr>
				<td width="70%">&nbsp;</td>
				<td width="30%" valign="middle">
					<a class="abtn3" href="#" onclick="javascript:returnRes()"><span>确&nbsp;&nbsp;定</span></a>
					<a class="aBtn3" href="#" onclick="javascript:returnClose()"><span>取&nbsp;&nbsp;消</span></a>
				</td>
			</tr>
		</table>
		</td>
	</TR>
</TABLE>
<script type="text/javascript">
<!--
		tree = new dhtmlXTreeObject("treebox_tree","100%","100%",0);
		tree.setImagePath("${pageContext.request.contextPath}/images/dhtmlX/dhtmlxTree/");	
		tree.setOnClickHandler(tonclick);
		var str = "${str}";
		tree.loadXMLString(str);
//-->
</script>
</BODY>
</html>
      