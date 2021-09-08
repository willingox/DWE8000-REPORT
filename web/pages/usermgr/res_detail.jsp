<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>资源详细</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript">
<!--
	function onload() {
		if(xtResForm.addResActive) {
			xtResForm.addResActive.checked = true;			
		}
	}
	
	function openResClassTree() {
		var value = window.showModalDialog("${pageContext.request.contextPath}/usermgr/resClass.do?method=listResClassTree" + "&_time=" + new Date().getTime(), "", "dialogHeight:500px;dialogWidth:400px;help:no;status:no");	
		if(value) {
			$("#resClassId").val(value[0]);
			$("#resClassName").val(value[1]);
		}
	}
	
	function save() {
		if (validateXtResForm(xtResForm)) {
			xtResForm.submit();
			forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
		}
	}
	
	function bk2lst() {
		xtResForm.method.value = "listResByClassId";
		xtResForm.submit();
	}
//-->
</script>
</HEAD>
<BODY scroll="no" onload="onload()">
<html:form action="res" method="post">
<html:hidden property="method" value="save"/>
<html:hidden property="resClassId" />
<html:hidden property="srchResId" />
<html:hidden property="srchResName" />
<html:hidden property="srchResTypeId" />
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
		<!---------- 编辑时 ----------->					
		<logic:equal value="edit" name="flag">
			<TR>
				<TD class="tdDetailLable">资源编号</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
                	<html:text property="resId" readonly="true" styleClass="formDetailTxt" style="width:290px" /><span class="required">*</span>
				</TD>
				<TD width="8"></TD>
			</TR>
			<TR>					
				<TD class="tdDetailLable">资源名称</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
                	<html:text property="resName" styleClass="formDetailTxt" style="width:290px" /><span class="required">*</span>
				</TD>
				<TD width="8"></TD>							
		  	</TR>		 
			<TR>												
				<TD class="tdDetailLable">资源类型</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:select property="xtResType.resTypeId" styleClass="formDetailSelect" style="width:290px">
                    	<html:option value=""></html:option>
                        <logic:present name="resTypes">
                        	<html:options collection="resTypes" labelProperty="resTypeName" property="resTypeId"/>
                        </logic:present>
                    </html:select>
				</TD>
				<TD width="8"></TD>
			</TR>		 
			<TR>							
				<TD class="tdDetailLable">对应链接</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="resUrl" styleClass="formDetailTxt" style="width:290px" />
				</TD>
				<TD width="8"></TD>
		  	</TR>
			<TR>
		  		<TD class="tdDetailLable">资源分类</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
	            	<html:hidden property="xtResClass.resClassId" styleId="resClassId" />
	               	<html:text property="xtResClass.resClassName" styleId="resClassName" readonly="true" onclick="openResClassTree()" styleClass="popWindow" style="width:290px" title="单击这里打开资源分类树，选择所属资源分类" /><span class="required">*</span>
				</TD>
				<TD width="8">
				</TD>						
		  	</TR>
			<TR>							
				<TD class="tdDetailLable">是否有效</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:checkbox property="resActive" value="true"></html:checkbox>	
				</TD>
				<TD width="8"></TD>
			</TR>		 
			<TR>
				<TD class="tdDetailLable">可链接到菜单</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:checkbox property="resUsebymenu" value="true"></html:checkbox>
				</TD>
				<TD width="8"></TD>
		  	</TR>					  	
		  	<TR>
				<TD class="tdDetailLable">对象名</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">								
					<html:text property="resObjectName" styleClass="formDetailTxt" style="width:290px" />
				</TD>
				<TD width="8"></TD>	
			</TR>		 
			<TR>
				<TD class="tdDetailLable">可链接到流程</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:checkbox property="resUsebywf" value="true"></html:checkbox>
				</TD>
				<TD width="8"></TD>															
		  	</TR>
		  	<TR>
				<TD class="tdDetailLable">帮助手册路径</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">								
					<html:text property="resHelpPath" styleClass="formDetailTxt" style="width:290px" />																					
				</TD>
				<TD width="8"></TD>	
			</TR>		 
		</logic:equal>
		<!---------- 增加时 ----------->
		<logic:notEqual value="edit" name="flag">
			<TR>
				<TD class="tdDetailLable">资源编号</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
                	<html:text property="resId" value="" styleClass="formDetailTxt" style="width:290px" /><span class="required">*</span>
				</TD>
				<TD width="8"></TD>
			</TR>		 
			<TR>	
				<TD class="tdDetailLable">资源名称</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
               		<html:text property="resName" value="" styleClass="formDetailTxt" style="width:290px" /><span class="required">*</span>
				</TD>
				<TD width="8"></TD>
		  	</TR>
		  	<TR>
				<TD class="tdDetailLable">资源类型</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
			        <html:select property="xtResType.resTypeId" value="" styleClass="formDetailSelect" style="width:290px">
                    	<html:option value=""></html:option>
                        <logic:present name="resTypes">
                           	<html:options collection="resTypes" labelProperty="resTypeName" property="resTypeId"/>
                        </logic:present>
                   	</html:select>
				</TD>
				<TD width="8"></TD>
			</TR>		 
			<TR>
				<TD class="tdDetailLable">对应链接</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="resUrl" value="" styleClass="formDetailTxt" style="width:290px" />
				</TD>
				<TD width="8"></TD>
		  	</TR>		 
			<TR>
				<TD class="tdDetailLable">资源分类</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
                    <html:hidden property="xtResClass.resClassId" styleId="resClassId"  />													
                    <html:text property="xtResClass.resClassName" styleId="resClassName" readonly="true" onclick="openResClassTree()" styleClass="popWindow" style="width:290px" title="单击这里打开资源分类树，选择所属资源分类" /><span class="required">*</span>
				</TD>
				<TD width="8">
				</TD>
		  	</TR>
			<TR>							
				<TD class="tdDetailLable">是否有效</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
                	<html:checkbox property="resActive" styleId="addResActive"></html:checkbox>                            
				</TD>
				<TD width="8"></TD>
			</TR>		 
			<TR>
				<TD class="tdDetailLable">可链接到菜单</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:checkbox property="resUsebymenu" value="true"></html:checkbox>															
				</TD>
				<TD width="8"></TD>
		  	</TR>	
		  	<TR>							
				<TD class="tdDetailLable">对象名</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="resObjectName" value="" styleClass="formDetailTxt" style="width:290px" />
				</TD>
				<TD width="8"></TD>	
			</TR>		 
			<TR>
				<TD class="tdDetailLable">可链接到流程</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:checkbox property="resUsebywf" value="true"></html:checkbox>
				</TD>
				<TD width="8"></TD>
		  	</TR>	
		  	<TR>							
				<TD class="tdDetailLable">帮助手册路径</TD>
				<TD width="4"></TD>
				<TD class="tdDetailText">
					<html:text property="resHelpPath" value="" styleClass="formDetailTxt" style="width:290px" />
				</TD>
				<TD width="8"></TD>
			</TR>		 
		</logic:notEqual>
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
<html:javascript formName="xtResForm" />
</BODY>
</html>