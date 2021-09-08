<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1   
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0   
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%@ page contentType="text/html;charset=gbk" language="java"%>
<%@ include file="../common/taglibs.jsp"%>
<%@ page import="java.util.ArrayList"%>
<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>选择指标</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">

</head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/default.css" />
<script
	src="${pageContext.request.contextPath}/scripts/jquery/jquery.min.js"></script>
<style type="text/css">
.selectbox {
	width: 500px;
	height: 220px;
	margin: 0px auto;
}

.selectbox div {
	float: left;
}

.selectbox .select-bar {
	padding: 0 20px;
}

.selectbox .select-bar select {
	width: 150px;
	height: 200px;
	border: 1px #A0A0A4 solid;
	padding: 4px;
	font-size: 14px;
	font-family: "microsoft yahei";
}

.btn-bar {
	
}

.btn-bar p {
	margin-top: 16px;
}

.btn-bar p .btn {
	width: 50px;
	height: 30px;
	cursor: pointer;
	font-family: simsun;
	font-size: 14px;
}

#popupcontent {
	position: absolute;
	visibility: hidden;
	overflow: hidden;
	border: 1px solid #CCC;
	background-color: #F9F9F9;
	border: 1px solid #333;
	padding: 5px;
}

.menu_top_sys {
	position: absolute;
	z-index: 30;
}

.menu_top_sys a {
	text-align: center;
	font-size: 13px;
	vertical-align: middle;
	width: 100%;
	height: 100%;
	text-decoration: none;
	color: black;
	padding: 3px 13px;
	margin: 0;
}

.menu_top_sys a:hover {
	color: #ff3300;
	font-weight: bold;
}
</style>
<script type="text/javascript">

	var JSONObject = ${data};
	var JSONObject1 = ${data1};

	check_val = [];
	check_id = [];

	var nowId = ${runingInfo_SelectOneMinuteDataForm.id};

	
					
	$(document)
	.ready(
			function() {
				$("#SelectWind")
						.change(
								function() {

									var newId = document.runingInfo_SelectOneMinuteDataForm.id.value;
									//alert("--"+nowId+"--"+newId);

									
									var selectsTargetValues = [];
									
									//读取现在id的状态
									selectsTargetValues = JSONObject[""
											+ newId + ""].targetValues;
									//alert("读取"+selectsTargetValues);
									//选择元素全部放到左边
									$('#select2 option').appendTo(
											'#select1');
									//左边的选择状态设为未选择
									$('#select1 option').attr(
											"selected", false);

									for (j = 0; j < selectsTargetValues.length; j++) {
										$(
												'#select1 option[value='
														+ selectsTargetValues[j]
														+ ']')
												.appendTo('#select2');

									}
									nowId = newId;

								});
			});
	
	
	function change(){
		var obj = document.getElementById("select2").options;
		var selectsTargetValues = [];
		var selectsTargetValueNames = [];
		for (i = 0; i < obj.length; i++) {
			selectsTargetValues.push(obj[i].value);
			selectsTargetValueNames.push(JSONObject1[""+ obj[i].value+ ""]);
		}
		
		changeSelect3(nowId,JSONObject["" + nowId + ""].targetValues,selectsTargetValues);
		JSONObject["" + nowId + ""].targetValues = selectsTargetValues;
		JSONObject["" + nowId + ""].targetValueNames = selectsTargetValueNames;
		//alert("保存"+selectsTargetValues);
	}
					
					
	function changeSelect3(id, oldSelect, newSelect){
		
		//判断旧的是否被删除
		for (j = 0; j < oldSelect.length; j++) {
			var a=oldSelect[j];
			if(-1==$.inArray(a,newSelect)){
				$('#select3 option[value='+id+a+']').remove();
				
			}
		}
		//判断新的是否是新添加的
		for (j = 0; j < newSelect.length; j++) {
			var a=newSelect[j];
			if(-1==$.inArray(a,oldSelect)){
				$('#select3').append("<option value="
						+ id+a
						+ ">" +JSONObject["" + nowId + ""].name+"-"+JSONObject1[""+ a+ ""]
						+ "</option>");
			
			}
		}
		
	}
	
	

	$(function() {
		//移到右边
		$('#add').click(function() {
			//先判断是否有选中
			if (!$("#select1 option").is(":selected")) {
				alert("请选择需要移动的选项")
			}
			//获取选中的选项，删除并追加给对方
			else {
				$('#select1 option:selected').appendTo('#select2');
				change();
			}
		});

		//移到左边
		$('#remove').click(function() {
			//先判断是否有选中
			if (!$("#select2 option").is(":selected")) {
				alert("请选择需要移动的选项")
			} else {
				$('#select2 option:selected').appendTo('#select1');
				change();
			}
		});

		//全部移到右边
		$('#add_all').click(function() {
			//获取全部的选项,删除并追加给对方
			$('#select1 option').appendTo('#select2');
			change();
			
		});

		//全部移到左边
		$('#remove_all').click(function() {
			$('#select2 option').appendTo('#select1');
			change();
			
		});

		//双击选项
		$('#select1').dblclick(function() { //绑定双击事件
			//获取全部的选项,删除并追加给对方
			$("option:selected", this).appendTo('#select2'); //追加给对方
			change();
		});

		//双击选项
		$('#select2').dblclick(function() {
			$("option:selected", this).appendTo('#select1');
			change();
		});

	});

	

	
	function returnCurrentNode() {

/* 		var obj = document.getElementById("select2").options;
		var selectsTargetValues = [];
		var selectsTargetValueNames = [];
		for (i = 0; i < obj.length; i++) {
			selectsTargetValues.push(obj[i].value);
			selectsTargetValueNames.push(JSONObject1["" + obj[i].value + ""]);
		}
		//保存当前状态
		JSONObject["" + nowId + ""].targetValues = selectsTargetValues;
		JSONObject["" + nowId + ""].targetValueNames = selectsTargetValueNames; */

		var text = "";
		var num = 0;
		jQuery.each(JSONObject, function(i, val) {

			//text = text + "Key:" + i + ", Value:" + val; 
			num = num + val.targetValues.length;

		});
		//console.log(text); 
		//alert(num);

		if (num == 0) {
			alert("请选择遥测");
			return;
		}
		if (num >= 6) {
			alert("请选择遥测不要超过5个");
			return;
		}

		var reg=new RegExp("\"","g"); //创建正则RegExp对象  
		var str=JSON.stringify(JSONObject);
		var str1=str.replace(reg,"'");
		
		//alert(str1);
		//alert(str);
		
		/* window.dialogArguments.runingInfo_OneMinuteDataForm.action = '${pageContext.request.contextPath}/datastatistics/runingInfo_OneMinuteData.do?check_val='
				+ str1; 
				//+'{t!e\"st}'; */
		 if (window.showModalDialog!=null)//IE判断
           {
         window.dialogArguments.runingInfo_OneMinuteDataForm.check_val.value =str1;
		//window.dialogArguments.runingInfo_OneMinuteDataForm.check_val.value ='{t!e\"st}';
		

		window.dialogArguments.runingInfo_OneMinuteDataForm.submit();
		window.close();
          }
      
          else{
           window.opener.runingInfo_OneMinuteDataForm.check_val.value =str1;
		//window.dialogArguments.runingInfo_OneMinuteDataForm.check_val.value ='{t!e\"st}';
		

		window.opener.runingInfo_OneMinuteDataForm.submit();
		top.close();
          }		
		
	}

	function returnClose() {

		top.close();  
	}
	
</script>
</HEAD>
<BODY scroll="auto"
	style="background-image:url(../images/backgroundcolor.jpg); background-repeat:repeat;">
	<html:form action="runingInfo_SelectOneMinuteData" method="post">
		<html:hidden property="method" value="show" />
		<TABLE class="tabOutside" id="tabOutside">
			<!----------------------------------------错误或提示--------------------------------------------------------------->
			<!--如果有错误-->
			<TR height="1">
				<TD height="1">
					<div id="message">
						<!-- 错误 -->
						<logic:messagesPresent>
							<div class="error">
								<img
									src="${pageContext.request.contextPath}/images/iconWarning.png">
								<html:messages id="message">
									<bean:write name="message" />
									<br>
								</html:messages>
							</div>
						</logic:messagesPresent>
						<!-- 消息 -->
						<logic:messagesPresent message="true">
							<div class="error" id="loginError">
								<img
									src="${pageContext.request.contextPath}/images/iconInformation.png">
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

			<!----------------------------------------数据详细表格开始-------------------------------------------------------------->
			<TR>
				<TD>
					<TABLE>

						<TR>

							<TD width="25"></TD>
							<TD style="color:#000000">选择间隔</TD>

							<TD id="SelectWind"><html:select property="id"
									value="${runingInfo_SelectOneMinuteDataForm.id}"
									styleClass="formDataFormSelect"
									style="width:120px;border-width:1;">

									<logic:present name="bayList">
										<html:options collection="bayList" labelProperty="name"
											property="id" />
									</logic:present>
								</html:select></TD>

						</TR>

					</TABLE>
				</TD>
			</tr>
			<TR>
				<TD>
					<TABLE>
						<TR>
							<TD>
								<div class="selectbox">
									<div class="select-bar">
										<select multiple="multiple" id="select1">
											<%
												ArrayList<String> checkNamelist = (ArrayList<String>) request.getAttribute("resultCheckName");
												ArrayList<String> checkValuelist = (ArrayList<String>) request.getAttribute("resultCheckValue");
											
													for (int i = 0; i < checkNamelist.size(); i++) {
														out.print("  <option value="
																+ checkValuelist.get(i)
																+ ">" + checkNamelist.get(i)
																+ "</option>");
														
													}
											%>
										</select>
									</div>

									<div class="btn-bar">
										<p>
											<span id="add"><input type="button" class="btn"
												value=">" title="移动选择项到右侧" /></span>
										</p>
										<p>
											<span id="add_all"><input type="button" class="btn"
												value=">>" title="全部移到右侧" /></span>
										</p>
										<p>
											<span id="remove"><input type="button" class="btn"
												value="<" title=" 移动选择项到左侧"/></span>
										</p>
										<p>
											<span id="remove_all"><input type="button" class="btn"
												value="<<" title=" 全部移到左侧"/></span>
										</p>
									</div>
									<div class="select-bar">
										<select multiple="multiple" id="select2"></select>
									</div>
								</div>

							</TD>

						</TR>

					</TABLE>
				</TD>
			</tr>
	         <TR>
				<TD>
					<TABLE>

						<TR>

							<TD width="25"></TD>
							<TD style="color:#000000">已选择风机的指标：</TD>

						</TR>

					</TABLE>
				</TD>
			</tr>
			<TR height="100">
				<TD align="center">
					<table width="100%" heigth="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td width="5%">&nbsp;</td>
							<td>
								<div>
									<select multiple="multiple" id="select3" style="width: 380px;height: 100px;border: 1px #A0A0A4 solid;padding: 4px;font-size: 14px;font-family:"microsoft yahei";"  ></select>
								</div>
							</td>

						</tr>
					</table>
				</TD>
			</TR>

			<TR height="100">
				<TD align="center">
					<table width="100%" heigth="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td width="25%">&nbsp;</td>
							<td><a class="abtn2" href="#"
								onclick="javascript:returnCurrentNode()"><span><img
										align="left" valign="center"
										src="${pageContext.request.contextPath}/images/green/tool/save.png"
										alt="" />确&nbsp;&nbsp;定</span></a> <a class="aBtn2" href="#"
								onclick="javascript:returnClose()"><span><img
										align="left" valign="center"
										src="${pageContext.request.contextPath}/images/green/tool/back.png"
										alt="" />取&nbsp;&nbsp;消</span></a></td>
						</tr>
					</table>
				</TD>
			</TR>

			</html:form>
</BODY>
</html>




