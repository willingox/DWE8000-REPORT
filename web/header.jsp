<%@ page language="java" pageEncoding="GBK"%>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<style type="text/css">
	.titleText{
		font: 10pt 楷体, Helvetica, sans-serif;
		font-weight:bold;
		filter:
			Dropshadow(offx=3,offy=0,color=#045949)
			Dropshadow(offx=0,offy=3,color=#045949)
			Dropshadow(offx=0,offy=-2,color=#045949)
			Dropshadow(offx=-2,offy=0,color=#045949);
		position:absolute;
		width:100%;
		top:5px;
		text-align:center;
		letter-spacing:3px;
		height:10px;
		color: #E0F6F3;
	}

	.titleTextNew {
		font-family: 微软雅黑, Helvetica, sans-serif;
		font-weight:bold;
		filter:
			Dropshadow(offx=3,offy=0,color=#045949)
			Dropshadow(offx=0,offy=3,color=#045949)
			Dropshadow(offx=0,offy=-2,color=#045949)
			Dropshadow(offx=-2,offy=0,color=#045949);
		position:absolute;
		top: 7px;
		text-align:center;
		letter-spacing:3px;
		color: #E0F6F3;
	}

	.titleTextNew .topLine {
		padding-top:18px;
		padding-left:150px;
		font-size: 15pt;
		text-align:left;
		letter-spacing:1px;
		height:50px;
		width:100%;
		color: #E0F6F3;
		vertical-align: middle;
		
	}

	.titleTextNew .secondLine {
		font-size: 10pt;
		top:10px;
		text-align:center;
		letter-spacing:3px;
		height:1px;
		width:100%;
		color: #E0F6F3;
		clear: left;
		float: left;
		margin-top:10px;
	}
	
	.headLineBackgroundImg{
		background-image:url("images/green/frame/title/title_dwe.png");
	}
	.headLineBackgroundColor{
	 background-color:grey;
	}
</style>
<div class="titleText" style="display:none;">许继集团风力发电分析系统</div>
<div class="titleTextNew">
<div class="topLine"></div>
</div>
	<TR width="100%" height="90px">
		<TD width="100%" height="90px" valign="top" class="headLineBackgroundImg" >
			<!-- background属性 设置整个标题栏的背景 -->
			<table width="100%"  cellSpacing="0" cellPadding="0" border="0" >
				<tr >
					<td width="100%" height="60px"  align="right" style="vertical-align:bottom;">
						<table  cellpadding="0" cellspacing="0" border="0" >
							<tr id="topMenusTab" valign="bottom">
								${topMenusTabStr}
								<!-- <td class="tabMainWithoutHover"><img src="images/green/frame/user.png" class="tabimg"><span>${sessionScope.gUserName}</span></td> -->
							</tr>
							
						</table>
					</td>
				<!-- 菜单栏分割线，由美工作图，暂时取消 -->
				<tr>
					<td style="height: 0px; background-color: white;"></td>
				</tr>
				<tr align="right" valign="bottom" height="28px">
					<td>
						<table>
							<tr>
								<td class="tabMainWithoutHover"><!--<img src="images/green/frame/user.png" class="tabimg" style="color:white;height:15px;">-->当前用户:${sessionScope.gUserName}</td>
								<td title="注销" onclick="exit()" class="tabMainWithoutHover" style="cursor: hand;">  <img src="images/green/frame/exit.png" class="tabimg" style="height: 17px;"> 注销</td>
							</tr>
						</table>
					</td>	
				</tr>
			</table>
		</TD>
	</TR>