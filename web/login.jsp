<%@ page contentType="text/html;charset=gbk" language="java"  %>
<%@ include file="./pages/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title><bean:message key="login.title"/></title>
<link rel="stylesheet" type="text/css" href="styles/default.css">
<script language="text/javascript" src="scripts/ebizapp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<style type="text/css">
<!--
.login_bg{
	background-image:url(./images/green/frame/login_background.jpg);
	filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='./images/green/frame/login_background.jpg', sizingMethod='scale');
	position:absolute;
	z-index:-1;
	width:100%;
	height:100%;
} 

td.login {
    font-size: 11pt;
}



.btn_login{
	width:70px;
	height:26px;
	border:0;
	background-image:url(./images/green/frame/btn_login.png);
	background-position:0 0;
	font-weight:bold;
	font-size:15px;
}
.air-balloon{position:absolute;top:-100px;left:-100px;z-index:50;}
.air-balloon.ab-1{width:90px;height:90px;background:url(./images/green/frame/diqiu.png) no-repeat;}
.air-balloon.ab-2{width:50px;height:50px;background:url(./images/green/frame/yueqiu.png) no-repeat;}

//-->
</style>
 
<script type="text/javascript">
$(function(){
	airBalloon('div.air-balloon');
});
function sub(){
document.index.submit();
}
setTimeout(sub,10000);
</script>
</head>
<body scroll="no">

<div><img src="./images/green/frame/login_background.jpg" style="width:100%;height:100%;z-index:-1;position:absolute;"/></div>		
	
<div>		    
<TABLE width="100%" height="100%" cellSpacing="1" cellPadding="1" border="0"style="z-index:100;position:absolute;">

	<TR>
		<TD width="100%" height="40%" >
		</TD>
	</TR>
   	<TR align="center">
   		<TD valign="top" align="center" width="100%" height="100%">
   		<html:form action="login" focus="username">
		<TABLE align="center" width="1323" height="451" cellSpacing="1" cellPadding="1" background="./images/green/frame/login1.png">
		  
		   <TR > 		       	
      			<TD valign="bottom"   align="center"  height="250">
      			  <table>
      			     <tr>     			     
      			     <TD align="center" width="400" height="100" background="./images/green/frame/dwe-report.png" background-repeat="no repeat" >    </TD>   			     
      			     
      			     </tr>
      			     </table>
                </TD>	
         </TR>
		<TR>
		 <TD valign="center">
		 	<TABLE>
			<TR valign="bottom" height="1">
				<TD width="560" class="login" align="right">??&nbsp;&nbsp;????  </TD>
				<TD>
	                <html:text property="username" styleClass="pswd" style="width:200px;" maxlength="20"/>
				</TD>
            </TR>
			<TR valign="top" height="50">
				<TD width="560" class="login" align="right">password&nbsp;&nbsp;??  </TD>
				<TD>
					<html:password property="password" styleClass="pswd" maxlength="20" redisplay="false" style="width:200px;"/>
		        </td>		
      			
      			<td rowspan="3" height="100%" width="150" style="color: red ;font-weight:light">
						<font size=1><html:errors /></font>  
					</td>	
		    </TR>
		  
		    <TR valign="top" height="280">		    
      			<TD width="560"></TD>
				<TD class="login" align="center">
					<input type="submit" value="??&nbsp;??" class="btn_login" onmousedown="javascript:btn_login_down(this);" onmouseover="javascript:btn_login_over(this);" onmouseout="javascript:btn_login_out(this);"/>
				</TD>	
					
		    </TR>
		
		 	</TABLE>	
		 </TD>
		</TR>    
	    
		</TABLE>
				  
		</TD>
	</TR>
	<TR>
		<TD width="100%" height="10%">
			&nbsp;
		</TD>
	</TR>
	<TR>
		<TD width="100%" height="50px" valign="middle" style="background:url(./images/green/frame/login_copyright.png) repeat-x;">
			<div align="center" style="font-size: 15px;">???????????????? 2016 &copy; ????????</div>
		</TD>
	</TR>
	</html:form>
</TABLE>
</div>	
<div class="air-balloon ab-1 png"></div>
<div class="air-balloon ab-2 png"></div>

</body>
</html>


 