<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>用户维护</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/popCheck/popCheck.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>

<script type="text/javascript">
<!--
	function save() {
		if (validateEquipmentForm(equipmentForm)) {
			document.equipmentForm.submit();
			forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
		}
	}
	function bk2lst() {
		document.equipmentForm.method.value = "findList";
		document.equipmentForm.submit();
	}
	
//-->
</script> 
</HEAD>
<BODY scroll="auto">

<html:form action="equipment"  method="post">
<html:hidden property="method" value="doUpdate"/>
<html:hidden property="findCustomerNo" />	
<html:hidden property="findEqNo" />
<html:hidden property="findEqName" />
<TABLE class="tabOutside" id="tabOutside">
	<!----------------------------------------错误或提示--------------------------------------------------------------->
	<!--如果有错误-->
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
	<!----------------------------------------工具栏和错误（或数据表格）之间的间隔------------------------------------>
	 <TR>
	 	<TD class="tdDataDetailSpace">
		</TD>
	 </TR>
	<!----------------------------------------数据详细表格开始-------------------------------------------------------------->
	<TR>
		<TD valign="top" align="center" height="202">
		<TABLE class="detail">
	
			<html:hidden property="eqId" value="${equipment.eqId}"/>	
			<TR>
				<TD class="tdDetailLable">设备编号</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="eqNo" value="${equipment.eqNo}" styleClass="formDetailTxt" style="width:234px"/><span class="required">*</span>
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">设备名称</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text  property="eqName" value="${equipment.eqName}" styleClass="formDetailTxt" style="width:234px" /><span class="required">*</span>
				</TD>
				<TD width="4"></TD>
		  	</TR>
			
			<TR>
				<TD class="tdDetailLable">所属客户</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
						<html:select property="customer.customerNo" value="${equipment.customer.customerNo}" styleClass="formDataFormSelect" style="width:235px">
                           <html:option value=""></html:option>
                           <logic:present name="customers">
                           		<html:options collection="customers" labelProperty="customerName" property="customerNo"/>
                           </logic:present>
                       	</html:select>	
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">额定功率(kW)</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
               		<html:text property="ratedpower" value="${equipment.ratedpower}"  style="width:234px" />
				</TD>
				<TD width="4"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">额定电压(V)</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="limitvol" value="${equipment.limitvol}" styleClass="formDetailTxt" style="width:234px"/>
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">最大功率(kW)</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text  property="maxpower" value="${equipment.maxpower}" styleClass="formDetailTxt" style="width:234px" />
				</TD>
				<TD width="4"></TD>
		  	</TR>
			<TR>
				<TD class="tdDetailLable">最小功率(kW)</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="minpower" value="${equipment.minpower}" styleClass="formDetailTxt" style="width:234px"/>
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable"></TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					
				</TD>
				<TD width="4"></TD>
		  	</TR>
		</TABLE>
		</TD>
	</TR>
	<TR height="100">
		<TD align="center">
		<table width="100%" heigth="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="30%">&nbsp;</td>
				<td>
					<a class="abtn3" href="javascript:save()"><span>保&nbsp;&nbsp;存</span></a>
					<a class="aBtn3" href="javascript:bk2lst()"><span>返&nbsp;&nbsp;回</span></a>
				</td>
			</tr>
		</table>
		</TD>
	</TR>
	<!--业务表格结束-->
	<!--大表格最后一行100％-->
	<TR height="100%">
	</TR>
	
</TABLE>
</html:form>
<html:javascript formName="equipmentForm" />
</BODY>
</html>