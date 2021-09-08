<%@ page contentType="text/html;charset=gbk" language="java"%>
<%@ include file="./pages/common/taglibs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<link rel="stylesheet" type="text/css" href="styles/default.css">
		<script language="JavaScript" src="scripts/TreeView.js"></script>
		<title>菜单树</title>
		<style type="text/css">
			body 
			{
				background-color: #5C77A6;
			}
			a:link{text-decoration : none ;color : #000000 ;} 
			a:visited {text-decoration : none ;color : #000000 ;} 
			a:hover {text-decoration : underline ;color : #f60 ;} 
			a:active {text-decoration : none ;color : #000000 ;valign :middle; } 
			
		</style>
	</head>
	<body scroll="no">
		<form action="treemenu.html?method=listTreeMenu" method="get">
			<html:hidden property="method" value="listTreeMenu"></html:hidden>
		</form>
		<table width="100%" height="100%" cellSpacing="0" cellPadding="0" border=0>
			<tr>
				<td height="53" width="100%" background="images/frame/left_welcome.gif">
				</td>
			</tr>
			<tr>
				<td width="100%" height="100%" valign="valign" >
					<div align="left">
						<script language="JavaScript">
							 <logic:present name="Menu">
							    var tree = new MzTreeView("tree");
								tree.setIconPath("images/treeview/");
								tree.setTarget("main");    
						        <logic:iterate name="Menu" id="Menu" scope="request" indexId="index">						        
									<logic:empty name="Menu" property="xtMenu">
									   tree.nodes["0_${Menu.menuId}"] = "text:${Menu.menuName}";
									</logic:empty>
									
									<logic:notEmpty name="Menu" property="xtMenu">
									
										<logic:equal name="Menu" property="menuIsNewWindowOpen" value="true"> 
											tree.nodes["${Menu.xtMenu.menuId}_${Menu.menuId}"]
										     ="text:${Menu.menuName};icon:property;hint:${Menu.menuHint};url:${Menu.xtResource.resUrl}?userId=<%=session.getAttribute("gUserId").toString()%>;target:_blank";
										</logic:equal>
										<logic:notEqual name="Menu" property="menuIsNewWindowOpen" value="true"> 
										   	tree.nodes["${Menu.xtMenu.menuId}_${Menu.menuId}"]
										    ="text:${Menu.menuName};icon:property;hint:${Menu.menuHint};url:${Menu.xtResource.resUrl};";
									</logic:notEqual>										 
											 
									</logic:notEmpty>
						        </logic:iterate>
								document.write(tree.toString());
							</logic:present>
						</script>
					</div>
				</td>
			</tr>
			<tr>
				<td height="21" width="100%" background="images/frame/left_bottom.gif">&nbsp;</td>
			</tr>
		</table>
	</body>
</html>
