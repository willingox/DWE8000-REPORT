<%@ page contentType="text/html;charset=gbk" language="java"%>
<%@ page import="java.util.List" %>
<%@ page import="com.bjxj.usermgr.webapp.form.XtPermAssignDto" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>列出角色被指派的权限</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/dhtmlXGrid.css" />
<script type="text/javascript"src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/scripts/dhtmlXCommon.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXGrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXGridCell.js"></script>	
<script type="text/javascript">
<!--
	var permsArray = new Array();
	
	function doOnCellEdit(stage,rowId,cellInd){

	}
			
	function onload(){
<% 
	List perms = (List)request.getAttribute("SelectedPerms");
	for(int i=0;i<perms.size();i++){
		XtPermAssignDto element = (XtPermAssignDto)perms.get(i);
		out.println("permsArray["+i+"]=new Array();");			
		out.println("permsArray["+i+"][0]='"+element.getResId()+"';");			
		out.println("permsArray["+i+"][1]='"+element.getResName()+"';");
		out.println("permsArray["+i+"][2]='"+element.getOperReadId()+"';");	
		out.println("permsArray["+i+"][3]='"+element.getOperWriteId()+"';");	
		out.println("permsArray["+i+"][4]='"+element.getOperExecuteId()+"';");	
	}
%>
		mygrid = new dhtmlXGridObject('gridbox');
		mygrid.setImagePath("${pageContext.request.contextPath}/images/dhtmlX/");
		mygrid.setHeader("资源,读,写,执行");
		mygrid.setInitWidths("255,50,50,50")
		mygrid.setColAlign("left,center,center,center")
		mygrid.setColTypes("ro,ch,ch,ch");
		mygrid.setColSorting("str,str,str,str")
		mygrid.setOnEditCellHandler(doOnCellEdit);
		mygrid.enableMultiselect(true)
		mygrid.setSkin("xp");
		mygrid.init();	
		for(var i=permsArray.length - 1;i>=0;i--){
			mygrid.addRow(permsArray[i][0],[permsArray[i][1],permsArray[i][2],permsArray[i][3],permsArray[i][4]],0);
		}				
	}			
//-->	
</script>
</HEAD>
<body scroll="no" onload="onload()">
<TABLE id="tabOutside" width="100%" height="100%">
	 <TR>
	 	<TD class="tdDataGridSpace"></TD>
	 </TR>
	 <TR>
	 	<TD class="tdDataGridSpace">
			指派给角色【<%=request.getParameter("roleName")%>】的权限列表
	 	</TD>
	 </TR>
	 <TR>
	 	<TD>
	 		<div id="gridbox" width="100%" height="541"></div>
		</TD>
	</TR>
	<TR>
	 	<TD height="100%">
	 	</TD>
	 </TR>
	<!--业务表格结束-->
	</TABLE>
</body>
</html>
