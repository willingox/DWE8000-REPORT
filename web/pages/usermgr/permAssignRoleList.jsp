<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>Ȩ��ָ��</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/usermgr.js"></script>
<script type="text/javascript">
<!--
	function roleSelect(sobj){
		var nodeId = parent.resClassTreeIframe.tree.getSelectedItemId();
		document.select_role.action = "${pageContext.request.contextPath}/usermgr/permAssign.do?method=listValidPerms&resClassId="+nodeId+"&roleId="+sobj.value;
		document.select_role.submit();
	}
//-->
</script>
</HEAD>
<BODY scroll="no">
<TABLE class="tabOutside" id="tabOutside">
	<!----------------------------------------�������ʾ--------------------------------------------------------------->
	<!--����д���-->
	<TR height="1">
		<TD height="1">
			<div id="message">
			<!-- ���� -->
			<logic:messagesPresent>
				<div class="error">
					<img src="${pageContext.request.contextPath}/images/iconWarning.png">
					<html:messages id="message">
						<bean:write name="message" />
						<br>
					</html:messages>
				</div>
			</logic:messagesPresent>
			<!-- ��Ϣ -->
			<logic:messagesPresent message="true">
				<div class="error" id="loginError">
					<img src="${pageContext.request.contextPath}/images/iconInformation.png">
					<html:messages id="message" message="true">
						<bean:write name="message" />
						<br>
					</html:messages>
				</div>
			</logic:messagesPresent>
			</div>
		</TD>
	</TR>
	<!----------------------------------------�������ʹ��󣨻����ݱ��֮��ļ��------------------------------------>
	 <TR>
	 	<TD class="tdDataGridSpace">
		</TD>
	 </TR>
	<!----------------------------------------���ݱ��ʼ-------------------------------------------------------------->
	<form action="" id="select_role" name="select_role" method="post" target="permAssignIframe">
	<TR>
		<TD valign="top" align="center">					
		<table width="100%" border="0" cellspacing="1" cellpadding="1"> 
			<tr> 
				<p>
				<td width="73" align="right">
					<div align="center">��ѡ���ɫ:</div>
				</td>
			    <td width="300">
			    	<div align="left">
					<select name="role" onChange="roleSelect(this)"  class="selectDrop" > 
				    <logic:iterate id="element" name="roles">									       
				    	<option selected value=<bean:write name="element" property="roleId"/>>
					    	<bean:write name="element" property="roleName"/>
					    </option>						      							
					</logic:iterate> 
					</select>                   
			        </div>
			    </td>
			</tr>
		</table>					
		</TD>
	</TR>
	</form>
	<!--ҵ�������-->
</TABLE>
</BODY>
</html>
