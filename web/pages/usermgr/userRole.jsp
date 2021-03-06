<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>角色->用户</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/usermgr.js"></script>
<script type="text/javascript">
<!--
	function insertall() {
		var oDest = document.all.lstSelected;
		for (var i=0; i<document.all.yuanselect.options.length; i++) {
			var e = document.all.yuanselect.options[i];
			insert(oDest,e.innerText,e.value);
	    }
	    oDest = document.all.yuanselect;
		removeAll(oDest);
	}

	function insert2(){
		var oDest = document.all.lstSelected;
		for (var i=0; i<document.all.yuanselect.options.length; i++) {
			var e = document.all.yuanselect.options[i];
			if (e.selected) {
				insert(oDest,e.innerText,e.value);
			}
	   }
	   oDest = document.all.yuanselect;
	   removeSelected(oDest);
	}

	function yichu2() {
		var oDest = document.all.yuanselect;
		for (var i=0; i<document.all.lstSelected.options.length; i++) {
			var e = document.all.lstSelected.options[i];
			if (e.selected) {
				insert(oDest,e.innerText,e.value);
			}
	   }
		oDest = document.all.lstSelected;
		removeSelected(oDest);
	}

	function quanyi2() {
		var oDest = document.all.yuanselect;
		for (var i=0; i<document.all.lstSelected.options.length; i++) {
			var e = document.all.lstSelected.options[i];
			insert(oDest,e.innerText,e.value);
	    }
		oDest = document.all.lstSelected;
		removeAll(oDest);
	}

	function doSubmit() {
		if(document.all.select_role.user.value == '') {
			alert('请选择某一需要授权的用户');
			return;
		}
		var oDest = document.all.lstSelected;
		var returnValue = buildValue(oDest);
		document.all.remultiValues.value = returnValue;
	    document.form_modify.submit(); 
	    forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
	}

	function doCancel() {
		window.returnValue = null;
	  	window.close();
	}
	
	var CLEARTime = 3000;
	function removeMessage() {
		$('#message').css('display', 'none');
	}
		
	window.onload = function() {
		window.setTimeout(removeMessage, CLEARTime);
	}
//-->
</script>
</HEAD>
<BODY scroll="no">
<TABLE class="tabOutside" id="tabOutside">
	<TR height="1">
		<TD height="1">
			<div id="message">
			<!-- 错误 -->
			<logic:messagesPresent>
				<div class="error">
					<img src="${pageContext.request.contextPath}/images/iconWarning.png">
					<html:messages id="message">
						<bean:write name="message" />
						<br>
					</html:messages>
				</div>
			</logic:messagesPresent>
			<!-- 消息 -->
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
	 <TR>
	 	<TD class="tdDataGridSpace">
		</TD>
	 </TR>
	<!----------------------------------------数据表格开始-------------------------------------------------------------->
	<form action=<html:rewrite action="userrole"/>?method=selectUser name="select_role" method="post">
	<input type="hidden" name="orgId" value="${orgId}" />
	<input type="hidden" name="fixUserId" value="${fixUserId}" />
	<input type="hidden" name="fixUserName" value="${fixUserName}" />
	<TR>
		<TD valign="top">					
		<table width="100%" border="0" cellspacing="1" cellpadding="1"> 
			<tr> 
				<td width="">&nbsp;</td>
				<td width="280" style="text-align: right;">
			    	请选择用户:
			    </td>
			    <td width="70">&nbsp;</td>
		        <td width="280" style="text-align: left;">
					<select name="user" onChange="document.select_role.submit();" class="selectDrop"> 										  
					<logic:iterate id="element" name="users">
					<logic:equal name="element" property="userId"  value="${userId}"  >
						<option selected value=<bean:write name="element" property="userId"/>>
							<bean:write name="element" property="userName"/>
						</option>	
					</logic:equal>
					<logic:notEqual name="element" property="userId"  value="${userId}"  >
					    <option value=<bean:write name="element" property="userId"/>>
							<bean:write name="element" property="userName"/>
						</option>
					</logic:notEqual>	
					</logic:iterate> 	
		            </select>                 					               					            				               
		    	</td>
		    	<td width="">&nbsp;</td>
		  	</tr>
		</table>
		</TD>
	</TR>
	</form>
	<TR>
	<form action=<html:rewrite action="userrole"/>?method=updateRole name="form_modify" method="post">
	<input type="hidden" name="orgId" value="${orgId}" />
	<input type="hidden" name="fixUserId" value="${fixUserId}" />
	<input type="hidden" name="fixUserName" value="${fixUserName}" />
	<input type="hidden" name="user" value="${userId}"/>
		<TD valign="top" align="center">
		<table width="100%" border="0" cellspacing="1" cellpadding="1">
			<tr> 
				<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="1" align="center">
		        	<tr> 
		        		<td>&nbsp;</td>
		            	<td style="text-align: left; padding-left: 7px;">未授权角色</td>
		      			<td>&nbsp;</td>
		            	<td style="text-align: right; padding-right: 7px;">已授权角色</td>
		            	<td>&nbsp;</td>
		          	</tr>
		          	<tr>
		          		<td width="">&nbsp;</td>
		            	<td width="280" valign="top" align="right"> 		   
							<select name="yuanselect" multiple id="select" style="width:280; height:300;">
			                <logic:iterate id="element" name="unSelectedRoles">
	                        	<option value=<bean:write name="element" property="roleId"/> >
	                            	<bean:write name="element" property="roleName"/>
	                          	</option>							
						    </logic:iterate> 
							</select>
						</td>
		            	<td width="70" align="middle">
			            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
			               		<tr> 
			                  		<td height="20"> 
			                  		<div align="center">
			                  			<a class="abtn2" style="margin-left: 10px;" href="javascript:insert2()"><span>添加</span></a>
			                    	</div>
			                    	</td>
			                	</tr>
			                	<tr> 
			                  		<td height="5"><div align="center"></div></td>
			                	</tr>
			                	<tr> 
			                  		<td height="23"> 
			                  		<div align="center"> 
			                  			<a class="abtn2" style="margin-left: 10px;" href="javascript:yichu2()"><span>移除</span></a>
			                    	</div>
			                    	</td>
			                	</tr>
			                	<tr> 
			                  		<td height="5"><div align="center"></div></td>
			                	</tr>
			                	<tr> 
			                  		<td height="28"> 
			                  		<div align="center"> 
			                  			<a class="abtn2" style="margin-left: 10px;" href="javascript:insertall()"><span>全加</span></a>
			                    	</div>
			                    	</td>
			                	</tr>
			                	<tr> 
			                  		<td height="5"><div align="center"></div></td>
			                	</tr>
			                	<tr> 
			                  		<td height="24">
			                  		<div align="center">
			                  			<a class="abtn2" style="margin-left: 10px;" href="javascript:quanyi2()"><span>全移</span></a>
			                    	</div>
			                    	</td>
			                	</tr>
			                	<tr> 
			                  		<td height="24"><div align="center"></div></td>
			                	</tr>
			            		<tr> 					        
			                  		<td height="24">
			                  		<div align="center"> 
			                  			<a class="abtn2" style="margin-left: 10px;" href="javascript:doSubmit()"><span>保存</span></a>
			                    	</div>
			                    	</td>
			                	</tr>
			               	 	<tr> 
			                  		<td height="24">&nbsp;</td>
			                	</tr>
			              	</table>
		            	</td>
		            	<td width="280" valign="top" align="left">
						    <select name="lstSelected" multiple id="select3" style="width:280; height:300;">	
						   		<logic:iterate id="element" name="roles">
			                    	<option value=<bean:write name="element" property="roleId"/> >
			                        	<bean:write name="element" property="roleName"/>
			                        </option>							
						     	</logic:iterate>	
			                </select>
			                <input type="hidden" name="remultiValues" value=""/>									
						</td>
						<td>&nbsp;</td>
		          	</tr>
		        </table>
		        </td>
		    </tr>
		</table>					
		</TD>
	</form>
	</TR>
</TABLE>
</BODY>
</html>
