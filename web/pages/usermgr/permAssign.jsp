<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bjxj.usermgr.webapp.form.XtPermAssignDto" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>Ȩ??ָ??</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/dhtmlXGrid.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXCommon.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXGrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlXGridCell.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ebizapp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/usermgr.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript">
<!--		
	var CLEARTime = 3000;
	function removeMessage() {
		$('#message').css('display', 'none');
	}
	
	var permsArray = new Array();
	function onload() {
		window.setTimeout(removeMessage,CLEARTime);
<% 
	List perms = (List)request.getAttribute("permsView");
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
		mygrid.setHeader("??Դ?б?,??,д,ִ??");
		mygrid.setInitWidths("400,50,50,50");
		mygrid.setColAlign("left,center,center,center");
		mygrid.setColTypes("ro,ch,ch,ch");
		mygrid.setColSorting("str,str,str,str");
		mygrid.setSkin("xp");
		mygrid.setOnCheckHandler(doOnCheck);
		mygrid.init();	
		for(var i=permsArray.length - 1; i>=0; i--){
			mygrid.addRow(permsArray[i][0],[permsArray[i][1],permsArray[i][2],permsArray[i][3],permsArray[i][4]],0);
		}				
	}

	function doOnCheck(rowId,cellInd,state) {
		if(state){
			setState0(mygrid,rowId,cellInd);
		}else{
			setState1(mygrid,rowId,cellInd);
		}
	}
	
	//????
	function doSubmit() {
		if(mygrid.getRowsNum() < 1) 
			return ;
		var returnValue = buildPerms(mygrid);		
		document.all.remultiValues.value = returnValue;
	    document.form_modify.submit();
	    forbidSubmit();	// ??ֹ???а?ť???????ӵȣ?λ??{ebizapp.js}
	}
	
	//???ص??ַ?????ʽΪ  ??Դ,????1|??Դ,????2
	function buildPerms(mygrid) {
		var rows = mygrid.getRowsNum();
		var rowid;
		var ret= '';
		operSplit = ',';
		resSplit = '#';				
		if(rows > 0) {
			for(var i=0;i<rows;i++) {
				rowid = mygrid.getRowId(i);
				var boolNull = false;
				if(mygrid.cells(rowid,1).getValue() == 1) {
					ret = ret + resSplit + rowid + operSplit + 'R';
					boolNull = true;
				}
				if(mygrid.cells(rowid,2).getValue() == 1) {
					ret = ret + resSplit + rowid + operSplit + 'W';
					boolNull = true;
				}
				if(mygrid.cells(rowid,3).getValue() == 1) {
					ret = ret + resSplit + rowid  + operSplit + 'E';
					boolNull = true;
				}
				if(!boolNull){
					ret = ret + resSplit + rowid + operSplit + 'NULL';
				}
			}					
			ret = ret.substring(1, ret.length);
		}
		return ret;
	}
	
	function setSelect(aobj, col) {
		var rows = mygrid.getRowsNum();
		if(aobj.state == 0) {	// δѡ״̬
			switch(col) {
				case 1:
					$('#rspan').html('??(ȫ??ѡ)');
					$('#ralink').attr('state', 1);
					break;
				case 2:
					$('#rspan').html('??(ȫ??ѡ)');
					$('#ralink').attr('state', 1);
					$('#wspan').html('д(ȫ??ѡ)');
					$('#walink').attr('state', 1);
					break;
				case 3:
					$('#rspan').html('??(ȫ??ѡ)');
					$('#ralink').attr('state', 1);
					$('#wspan').html('д(ȫ??ѡ)');
					$('#walink').attr('state', 1);
					$('#espan').html('ִ??(ȫ??ѡ)');
					$('#ealink').attr('state', 1);
			}
			if(rows > 0){
				for(var i=0; i<rows; i++){
					rowid = mygrid.getRowId(i);
					setState0(mygrid,rowid,col);
				}
			}	
		}else{
			switch(col) {
				case 1:
					$('#rspan').html('??(ȫ&emsp;ѡ)');
					$('#ralink').attr('state', 0);
					$('#wspan').html('д(ȫ&emsp;ѡ)');
					$('#walink').attr('state', 0);
					$('#espan').html('ִ??(ȫ&emsp;ѡ)');
					$('#ealink').attr('state', 0);
					break;
				case 2:
					$('#wspan').html('д(ȫ&emsp;ѡ)');
					$('#walink').attr('state', 0);
					$('#espan').html('ִ??(ȫ&emsp;ѡ)');
					$('#ealink').attr('state', 0);
					break;
				case 3:
					$('#espan').html('ִ??(ȫ&emsp;ѡ)');
					$('#ealink').attr('state', 0);
			}
			if(rows > 0){
				for(var i=0; i<rows; i++){
					rowid = mygrid.getRowId(i);
					setState1(mygrid,rowid,col);
				}
			}	
		}
	}
	
	function setState0(grid,rowid,col) {
		switch (col){
			case 1 :	
				mygrid.cells(rowid,col).setValue(1);
				break;
			case 2 :
				mygrid.cells(rowid,1).setValue(1);
				mygrid.cells(rowid,col).setValue(1);
				break;
			case 3 :
				mygrid.cells(rowid,1).setValue(1);
				mygrid.cells(rowid,2).setValue(1);
				mygrid.cells(rowid,col).setValue(1);
				break;
		}				
	}
	
	function setState1(grid,rowid,col) {
		switch (col){
			case 1 :
				mygrid.cells(rowid,col).setValue(0);
				mygrid.cells(rowid,2).setValue(0);
				mygrid.cells(rowid,3).setValue(0);
				break;
			case 2 :
				mygrid.cells(rowid,col).setValue(0);
				mygrid.cells(rowid,3).setValue(0);
				break;
			case 3 :
				mygrid.cells(rowid,col).setValue(0);
				break;
		}				
	}
	//-->
</script>
</HEAD>
<BODY scroll="no" onload="onload();">
<TABLE class="tabOutside" id="tabOutside">
<form action=<html:rewrite action="permAssign"/>?method=excAssign name="form_modify" method="post">
<input type="hidden" name="remultiValues" value="" />
<input type="hidden" name="resClassId" value="${resClassId}" />
<input type="hidden" name="roleId" value="${roleId}"/>			
</form>
	<TR height="1">
		<TD height="1">
			<div id="message">
			<!-- ???? -->
			<logic:messagesPresent>
				<div class="error">
					<img src="${pageContext.request.contextPath}/images/iconWarning.png">
					<html:messages id="message">
						<bean:write name="message" />
						<br>
					</html:messages>
				</div>
			</logic:messagesPresent>
			<!-- ??Ϣ -->
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
		<TD width="100%" height="100%">					
			<div id="gridbox" width="100%" height="100%"></div>
		</TD>
	</TR>
	<TR>
		<TD valign="top"height="35">	
			<a class="abtnLT" id="ralink" href="javascript:void(0)" onclick="javascript:setSelect(this, 1)" state="0"><span id="rspan">??(ȫ&emsp;ѡ)</span><a/>					
			<a class="abtnLT" id="walink" href="javascript:void(0)" onclick="javascript:setSelect(this, 2)" state="0"><span id="wspan">д(ȫ&emsp;ѡ)</span><a/>
			<a class="abtnLT" id="ealink" href="javascript:void(0)" onclick="javascript:setSelect(this, 3)" state="0"><span id="espan">ִ??(ȫ&emsp;ѡ)</span><a/>
			<a class="abtn3" href="javascript:doSubmit()"><span>??&nbsp;&nbsp;??</span></a>
		</TD>
	</TR>
</TABLE>
</BODY>
</html>
