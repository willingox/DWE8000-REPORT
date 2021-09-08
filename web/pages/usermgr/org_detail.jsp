<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk" %>
<%@ include file="../common/taglibs.jsp"%>

<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>公司详细</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript">
<!--
	function save() {
		if (validateXtOrgForm(xtOrgForm)) {
			xtOrgForm.submit();
			forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
		}
	}

	function bk2lst() {
		xtOrgForm.method.value = "listSubOrgsByOrgId";
		xtOrgForm.submit();
	}
//-->
</script>
</HEAD>
<BODY>
<html:form action="org" method="post">
<html:hidden property="fixOrgId" value="${fixOrgId}"/>
<html:hidden property="fixOrgName" value="${fixOrgName}"/>
<html:hidden property="selectedOrgId" value="${selectedOrgId}"/>
<input type="checkbox" name="bAllSubOrg" ${bAllSubOrg} style="display:none" />
<TABLE class="tabOutside" id="tabOutside">
	<!----------------------------------------错误或提示--------------------------------------------------------------->
	<!--如果有错误-->
	<TR height="1">
		<TD height="1">					
			<!-- 错误 -->				
			<logic:messagesPresent>
				<div class="error">							
					<img src="${pageContext.request.contextPath}/images/iconWarning.png">															
					<html:messages id="message">
					 	<bean:write name="message"/><br>          
					 </html:messages>
				</div>
			 </logic:messagesPresent>
			 <!-- 消息 -->
			 <logic:messagesPresent message="true">
				<div class="error" id="loginError">							
					<img src="${pageContext.request.contextPath}/images/iconInformation.png">															
					 <html:messages id="message" message="true">
					 	<bean:write name="message"/><br>          
					 </html:messages>
				</div>						
			 </logic:messagesPresent>								
		</TD>
	</TR>
	<!----------------------------------------工具栏和错误（或数据表格）之间的间隔------------------------------------>
	 <TR>
	 	<TD class="tdDataDetailSpace">
		</TD>
	 </TR>
	<!----------------------------------------数据详细表格开始-------------------------------------------------------------->
	<TR>
		<TD align="center">
		<TABLE class="detail">
		<!---------- 编辑时 ----------->
		<logic:equal name="postMode" parameter="postMode" value="edit">
		<html:hidden property="method" value="update" />
			<TR>
				<TD class="tdDetailLable">公司标识</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="orgId" value="${orgDetail.orgId}" readonly="true" styleClass="formDetailTxtDisable" style="width: 290px;" /><span class="required">*</span>
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">公司编号</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="orgCode" value="${orgDetail.orgCode}" styleClass="formDetailTxt" style="width: 290px;" /><span class="required">*</span>
				</TD>
		  	</TR>
			<TR>
				<TD class="tdDetailLable">公司名称</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:hidden property="orgId" value="${orgDetail.orgId}" />
 					<html:text property="orgName" value="${orgDetail.orgName}" styleClass="formDetailTxt" style="width: 290px;" /><span class="required">*</span>
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">上级公司</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:select property="xtOrganization.orgId" value="${orgDetail.xtOrganization.orgId}" styleClass="formDetailSelect" style="width:290px">
					<logic:present name="Organization">
					<html:options collection="Organization" labelProperty="orgName" property="orgId" />
					</logic:present>
					</html:select>							
				</TD>
		  	</TR>
		  	<TR>
				<TD class="tdDetailLable">公司类型</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:select property="orgTypeId" value="${orgDetail.orgTypeId}" styleClass="formDetailSelect" style="width:290px">
						<html:option value=""></html:option>
						<logic:present name="orgTypes">
							<html:options collection="orgTypes" labelProperty="orgTypeName" property="orgTypeId" />
						</logic:present>
					</html:select>		
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">无效？</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:checkbox name="orgDetail" property="orgIsInvalid"></html:checkbox>			
				</TD>
		  	</TR>
			<TR>
				<TD class="tdDetailLable">电话</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
				 	<html:text property="orgTel" value="${orgDetail.orgTel}" styleClass="formDetailTxt" style="width: 290px;" />
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">传真</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="orgFax" value="${orgDetail.orgFax}" styleClass="formDetailTxt" style="width: 290px;" />
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">邮编</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="orgPostcode" value="${orgDetail.orgPostcode}" styleClass="formDetailTxt" style="width: 290px;" />
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">地址</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="orgAddress" value="${orgDetail.orgAddress}" styleClass="formDetailTxt" style="width: 290px;" />
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">电子邮件</TD>
				<TH width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="orgEmail" value="${orgDetail.orgEmail}" styleClass="formDetailTxt" style="width: 290px;" />
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">网站</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="orgWebsite" value="${orgDetail.orgWebsite}" styleClass="formDetailTxt" style="width: 290px;" />
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">备注</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:textarea property="orgNote" value="${orgDetail.orgNote}"  styleClass="formDetailTxtarea" cols="40" rows="4" />
				</TD>
			</TR>
		</logic:equal>
		<!---------- 增加时 ----------->
		<logic:equal name="postMode" parameter="postMode" value="add">
		<html:hidden property="method" value="insert"/>
			<TR>
				<TD class="tdDetailLable">公司标识</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="orgId" styleClass="formDetailTxt" style="width: 290px;" /><span class="required">*</span>
				</TD>
		  	</TR>
		  	<TR>
		  		<TD class="tdDetailLable">公司编号</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="orgCode" styleClass="formDetailTxt" style="width: 290px;" /><span class="required">*</span>
				</TD>
		  	</TR>
			<TR>
				<TD class="tdDetailLable">公司名称</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
 					<html:text property="orgName" styleClass="formDetailTxt" style="width: 290px;" /><span class="required">*</span>
				</TD>
		  	</TR>
		  	<TR>
		  		<TD class="tdDetailLable">上级公司</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					 <html:hidden property="xtOrganization.orgId" value="${requestScope.parentOrg.orgId}" />
					 <html:text property="xtOrganization.orgName" value="${requestScope.parentOrg.orgName}" readonly="true" styleClass="formDetailTxtDisable" style="width: 290px;" />											
				</TD>
		  	</TR>
		  	<TR>
				<TD class="tdDetailLable">公司类型</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:select property="orgTypeId" styleClass="formDetailSelect" style="width:290px">
						<logic:present name="orgTypes">
							<option value=""></option>
							<html:options collection="orgTypes" labelProperty="orgTypeName" property="orgTypeId"/>
						</logic:present>
					</html:select>		
				</TD>
		  	</TR>
		  	<TR>
		  		<TD class="tdDetailLable">无效？</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:checkbox property="orgIsInvalid"></html:checkbox>			
				</TD>
		  	</TR>
			<TR>
				<TD class="tdDetailLable">电话</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
				 	<html:text property="orgTel" styleClass="formDetailTxt" style="width: 290px;" />
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">传真</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="orgFax" styleClass="formDetailTxt" style="width: 290px;" />
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">邮编</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="orgPostcode" styleClass="formDetailTxt" style="width: 290px;" />
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">地址</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="orgAddress" styleClass="formDetailTxt" style="width: 290px;" />
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">电子邮件</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="orgEmail" styleClass="formDetailTxt" style="width: 290px;" />
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">网站</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="orgWebsite" styleClass="formDetailTxt" style="width: 290px;" />
				</TD>
			</TR>
			<TR>
				<TD class="tdDetailLable">备注</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:textarea property="orgNote" styleClass="formDetailTxtarea" cols="40" rows="4" />
				</TD>
			</TR>						
		</logic:equal>				
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
<html:javascript formName="xtOrgForm" />
</BODY>
</HTML>