<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>客户维护</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/popCheck/popCheck.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>

<script type="text/javascript">
<!--
	function save() {
	
		if (validateCustomerForm(customerForm)) {
			document.customerForm.submit();
			forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
		}
	
	}
	function bk2lst() {
		document.customerForm.method.value = "firstList";
		document.customerForm.submit();
	}
//-->
</script> 
</HEAD>
<BODY scroll="auto">

<html:form action="customer"  method="post">

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
		
		<html:hidden property="method" value="doUpdate"/>	
			<TR>
				<TD class="tdDetailLable">客户编号</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="customerNo" value="${customer.customerNo}" readonly="true" styleClass="formDetailTxtDisable" style="width:234px"/><span class="required">*</span>
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">客户名称</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text  property="customerName" value="${customer.customerName}" styleClass="formDetailTxt" style="width:234px" /><span class="required">*</span>
				</TD>
				<TD width="4"></TD>
		  	</TR>
			<TR>
				<TD class="tdDetailLable">联系人</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
               		<html:text  property="contactname"  value="${customer.contactname}" style="width:234px" />
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">固定电话</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
               		<html:text property="contactphone1" value="${customer.contactphone1}"  style="width:234px" />
				</TD>
				<TD width="4"></TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">移动电话</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
               		<html:text  property="contactphone2" value="${customer.contactphone2}"  style="width:234px" />
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">地址</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
               		<html:text property="addr" value="${customer.addr}"  style="width:234px" />
				</TD>
				<TD width="4"></TD>
			</TR>
			
			
			<TR>
				<TD class="tdDetailLable">用电行业</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
						<html:select property="bmIndustry.indNo" value="${customer.bmIndustry.indNo}" styleClass="formDataFormSelect" style="width:235px">
                           <html:option value=""></html:option>
                           <logic:present name="bmIndustrys">
                           		<html:options collection="bmIndustrys" labelProperty="indName" property="indNo"/>
                           </logic:present>
                       	</html:select>	
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">报装容量(kVA)</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
               		<html:text property="capability" value="${customer.capability}"  style="width:234px" />
				</TD>
				<TD width="4"></TD>
			</TR>
			
			<TR>
				<TD class="tdDetailLable">送变电压</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
						<html:select property="bmVolDegree.volNo" value="${customer.bmVolDegree.volNo}"  styleClass="formDataFormSelect" style="width:235px">
                           <html:option value=""></html:option>
                           <logic:present name="bmVolDegrees">
                           		<html:options collection="bmVolDegrees" labelProperty="volName" property="volNo"/>
                           </logic:present>
                       	</html:select>	
				</TD>
				<TD width="8"></TD>
				<TD class="tdDetailLable">终端地址</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
               		
               		<html:text  property="terminalAddr" value="${customer.terminalAddr}" style="width:234px" />
               		 
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
<html:javascript formName="customerForm" />
</BODY>
</html>