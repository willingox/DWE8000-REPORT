<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>系统日志</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ecTable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/popWaiting/popWaiting.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/ec/js/extremecomponents.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/popWaiting/ymPrompt.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/popWaiting/popWaiting.js"></script>
<script type="text/javascript">
<!--
	function query() {
		logQueryForm.method.value = 'listForPage';
		logQueryForm.submit();
	}
        
	/** 删除选中 */
	function remove(){
       	var idString;
       	$("input:checked[name='choice']").each(function(i){
       		if(!idString){
       			idString = $(this).val();
    		}else{
    			idString += ',' + $(this).val();
    		}
		});
       	if(idString) {
       		if(confirm('确定要删除选中的日志吗？')) {
				logQueryForm.method.value = 'remove';
				$('#logids').val(idString);
				logQueryForm.submit();
				forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
				popWaiting('正在删除所选日志...');
			}
       	}
	}
       
	/** 删除全部 */
	function removeAll() {
		if(confirm('确定要删除查询到的全部日志吗？')){
			logQueryForm.method.value = 'removeAll';
			logQueryForm.submit();
			forbidSubmit();	// 禁止所有按钮或超链接等，位于{ebizapp.js}
			popWaiting('正在删除查询到的日志...');
		}
	}
	
	var CLEARTime = 3000;
	function removeMessage() {
		$('#message').css('display', 'none');
	}
		
	window.onload = function() {
		window.setTimeout(removeMessage, CLEARTime);
	}
	
	/* 捕捉回车事件 */
	function catchEnter() { 
		if ((event.keyCode==13)){ 
			query();
		} 
	}
//-->
</script>
</HEAD>
<BODY scroll="no">
<TABLE class="tabOutside" id="tabOutside">
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
	<!----------------------------------------简单条件-------------------------------------------------------------->
	<!--如有页面需要-->
	<TR>
	<html:form action="logQuery" method="post">
	<html:hidden property="method" styleId="method" value="listForPage" />
	<input type="hidden" name="logids" id="logids" />
		<TD style="width: 100%; height: 50px; text-align: center;">
			<TABLE>	
				<TR>
					<TD>帐号</TD>
					<TD>										
						<html:text property="userId" styleClass="formDetailTxt" onkeyup="catchEnter()" size="16" />
					</TD>
					<TD>起始日期</TD>
					<TD>										
						<html:text property="beginDateShow" styleId="fromDateId" styleClass="dateInput" size="16" onclick="WdatePicker({isShowWeek:true,isShowClear:true,readOnly:true,firstDayOfWeek:1,maxDate:'%y-%M-%d'})" title="点击选择起始日期" />
					</TD>
					<TD>截止日期</TD>
					<TD>										
						<html:text property="endDateShow" styleId="toDateId" styleClass="dateInput" size="16" onclick="WdatePicker({isShowWeek:true,isShowClear:true,readOnly:true,firstDayOfWeek:1,maxDate:'%y-%M-%d'})" title="点击选择截止日期" />
					</TD>
					<TD>操作类型</TD>
					<TD>
						<html:select property="logClass">
							<html:option value=""></html:option>
							<html:options collection="logClasses" property="logClassId" labelProperty="logClassName" />
						</html:select>
					</TD>
					<TD>			
						<a class="abtnLT" href="javascript:query()"><span>查&nbsp;&nbsp;询</span></a>
					</TD>
				</TR>
			</TABLE>
		</TD>
	</html:form>
	</TR>
	<!----------------------------------------工具栏和错误（或数据表格）之间的间隔------------------------------------>
	 <TR>
	 	<TD class="tdDataGridSpace">
		</TD>
	 </TR>
	<!----------------------------------------数据表格开始-------------------------------------------------------------->
	<TR>
		<TD height="" valign="top" align="center">
		<div style="width: 100%; height: 100%; overflow: auto;">
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="bottom" align="center" width="100%">
				<ec:table 
					items="logList"
					var="entity"
					action="${pageContext.request.contextPath}/usermgr/logQuery.do"
					imagePath="${pageContext.request.contextPath}/images/ecTable/compact/*.gif"
					width="100%"
					view="compact" 
					filterable="false" 
					showExports="false" 
					styleClass="ecTable"
					retrieveRowsCallback="limit"
			        filterRowsCallback="limit"
			        sortRowsCallback="limit"
					autoIncludeParameters="true">
					<ec:exportXls fileName="系统日志.xls" tooltip="导出 Excel"/>
					<ec:parameter name="method" value="listForPage" />
					<ec:row highlightRow="true">
						<ec:column alias="choice" title="选择" viewsAllowed="html,compact" headerClass="ecTableHead" styleClass="ecTableBody" style="width:5%;">
							<input type="checkbox" id="choice" name="choice" value="${entity.xtOplogid}">
						</ec:column>
						<ec:column property="xtUser.userId" alias="userId" title="帐号" headerClass="ecTableHead" styleClass="ecTableBody" style="width:10%;" />
						<ec:column property="xtUser.userName" alias="userName" title="用户名" headerClass="ecTableHead" styleClass="ecTableBody" style="width:10%;" />
						<ec:column property="xtOplogtime" title="操作时间" cell="date" format="yyyy-MM-dd HH:mm" headerClass="ecTableHead" styleClass="ecTableBody" style="width:15%;" />
						<ec:column property="xtLogClass.logClassName" alias="logClassName" title="操作类型" headerClass="ecTableHead" styleClass="ecTableBody" style="width:10%;" />
						<ec:column property="xtOplogdescribe" sortable="false" title="描述" headerClass="ecTableHead" styleClass="ecTableBody" style="width:40%;" />
						<ec:column property="xtOplogipaddress" title="登陆机器IP" headerClass="ecTableHead" styleClass="ecTableBody" style="width:15%;" />
					</ec:row>
				</ec:table>	
			  	</td>
			</tr>
			<tr>
				<td width="100%" align="center">
				<table border="0" width="99%" cellspacing="0" cellpadding="0">
					<tr>
						<td class="ecTableFootToolBar">											 
							<input type="button" onclick="remove()" value="删除选中" class="btn_delete_long">
							<input type="button" onclick="removeAll()" value="删除全部" class="btn_delete_long">
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="100%"></td>
			</tr>
		</table>
		</div>
		</TD>
	</TR>
</TABLE>
</BODY>
</html>