<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="../common/taglibs.jsp"%>
<%@ page import="java.util.List" %>
<%@ page import="com.bjxj.usermgr.model.XtGroup" %>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<title>���ݹ�˾�г���</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript">
<!--
	var groupArray = new Array();
	function returnGroup() {
		var selectedGroupId;
		var allGroup = document.getElementsByName('groupId');
		for(var i=0; i<allGroup.length; ++i) {
			if(allGroup[i].checked) {
				selectedGroupId = allGroup[i].value;
				break;
			}
		}
		var returnArray = new Array();
		for(var i=0; i<groupArray.length; ++i) {
			if(groupArray[i][0] == selectedGroupId) {						
				returnArray[0] = groupArray[i][0];
				returnArray[1] = groupArray[i][1];
				break;
			}
		}
		if(returnArray.length > 0) {
			window.returnValue = returnArray;
		}
		self.close();
	}
	
	function returnClose(){
		self.close();
	}
	
	window.onload = function(){	
		<%
			List groups = (List) request.getAttribute("groupByOrgId");
			for (int i=0; i<groups.size(); ++i) {
				XtGroup element = (XtGroup) groups.get(i);
				out.println("groupArray[" + i + "]=new Array();");			
				out.println("groupArray[" + i + "][0]='" + element.getGroupId() + "';");			
				out.println("groupArray[" + i + "][1]='" + element.getGroupName() + "';");									
			}
		%>
	}
//-->
</script>
</HEAD>
<body scroll="yes">
<TABLE id="tabOutside" width="100%" height="100%">
	<!----------------------------------------�������ʹ��󣨻����ݱ��֮��ļ��------------------------------------>
	 <TR>
	 	<TD class="tdDataGridSpace">
		</TD>
	 </TR>
	<!----------------------------------------���ݱ��ʼ-------------------------------------------------------------->	 
	 <TR>
		<TD valign="top" align="center">
			<TABLE class="cmTable">
				<TR>
					<TD width="20%" class="cmTableHead">ѡ��</TD>
					<TD width="40%" class="cmTableHead">������</TD>
					<TD width="40%" class="cmTableHead">������˾</TD>														
				</TR>
				<logic:iterate id="element" name="groupByOrgId">
					<TR>
						<TD width="20%" class='cmTableBody'><input type="radio" name="groupId" value=<bean:write name="element" property="groupId" /> ></TD>
						<TD width="40%" class='cmTableBody'><bean:write name="element" property="groupName" /></TD>
						<TD width="40%" class='cmTableBody'><bean:write name="element" property="xtOrganization.orgName" /></TD>							

					</TR>
				</logic:iterate>						 					
			</TABLE>
		 </TD>
	</TR>
	<TR>
		<td height="50">
		<hr />
		<table width="100%" height="100%">
			<tr>
				<td width="70%">&nbsp;</td>
				<td width="30%" valign="top">
					<a class="abtn3" href="#" onclick="javascript:returnGroup()"><span>ȷ&nbsp;&nbsp;��</span></a>
					<a class="aBtn3" href="#" onclick="javascript:returnClose()"><span>ȡ&nbsp;&nbsp;��</span></a>
				</td>
			</tr>
		</table>
		</td>
	</TR>
	<!--ҵ�������-->
</TABLE>
<!--�б�Ĭ���е�һ�� -->
<script type="text/javascript">
<!--
	var groupList = document.getElementsByName('groupId');
	if(groupList.length>0){
		groupList[0].checked = true;
	}
//-->
</script>
</body>
</html>