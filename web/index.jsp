<%@ page contentType="text/html;charset=gbk" language="java"%>
<%@ include file="./pages/common/taglibs.jsp"%>

<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/Mmenu/milonic_src.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/Mmenu/mmenudom.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/Mmenu/mmstyle.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<title></title>

<script type="text/javascript">
<!--

	var lastSelectedName;

	function app_exit() {
		parent.close();
	}

	function exit() {
		if(window.confirm('是否确定要退出系统？')) {
			document.submitform.action = 'logoff.do';
			document.submitform.target = '_parent';
			document.submitform.submit();
		}
	}
	
	function help() {
								
	}

	//var URL_HOME_PAGE = '${firstPage}' + (!'${firstPage}' ? '' : '|首页');
	var URL_HOME_PAGE = '${firstPage}';

	/** goto home page */
	function home() {
		openPage(URL_HOME_PAGE);
		URL_HOME_PAGE=null;
	}

	/** goto gis page */
	function gis() {
		/** TODO */
	}


	/** 菜单点击入口函数 */
	function onClickMenu() {
		hideEuTree();
		menuBgChange();
		var url = _mi[_itemRef][2];
		var menuId = getItem(url);
		autoChangeCurrLoct(menuId); //current position
		//closeAllMenus();
	}

	function getItem(url) {
		var item = '';
		if (url) {
			var bpos = url.indexOf('menuId=');
			var epos = url.indexOf('&', url.indexOf('menuId'));
			if (epos == -1)
				item = url.substring(bpos + 'menuId='.length);
			else 
				item = url.substring(bpos + 'menuId='.length, epos);
		}
		return item;
	}
	
	function autoChangeCurrLoct(menuId) {
		var url = "${pageContext.request.contextPath}/usermgr/menu.do";
		$.ajax({
			type: "GET",
			url: url,
			data: "method=getFullPath&menuId=" + menuId + "&_time=" + new Date().getTime(), 
		  	success: function(data){
				if (data)
					$("#currentLocation").html(data);
		  	}
		});
	}
	
	function openPage(page) {
		if('' != page) {
			var fullurl = page;
			if (page.indexOf("http://") == -1)
				fullurl = "${pageContext.request.contextPath}/" + page;
			var arr = fullurl.split('|');
			if (page.indexOf('newWindow') != -1) {
				window.open(arr[0], '', 'scrollbars=yes,resizable=yes,channelmode');
			} else {	// 弹出窗口不需要改变当前位置
				$("#currentLocation").html(arr[1]);
				$("iframe[id=contents]").attr("src", arr[0]);
			}
		}
	}

	/**
	 * @parameter 
	 * type:  [ jz(建筑), flfx(分类分项), fl(分类), reneTree(风力发电智能运维树) ]
	 * depth: 1-3 
	 * mode: check, radio 
	 * fltypes: '1','2',or '1,2', '1,2,3'...when type=fl
	 
	 * @author hb 2016-5-19
	 */
	function showEuTree(type, depth, mode, fltypes) {
		$('#tdQueryTree').css('display', 'block');
		window.frames['queryTree'].showTree(type, depth, mode, fltypes);
	}
	
	function hideEuTree() {
		$('#tdQueryTree').css('display', 'none');
	}
	
	function getEuTreeSelected() {
		return window.frames['queryTree'].getSelectedId();
	}
	
	function onChangeTopMenu(id){
		//alert(id);
		var url = '${pageContext.request.contextPath}/chooseMode.do?locationMode=${locationMode}&time=' + new Date().getTime();
			if ('tree' != '${requestScope.tree}') {
				url += "&hStyle=alwaysvisible = 1;left = 15; margin = 0;orientation = 'horizontal';style = horizStyle;top = 59;"
					+ "&vStyle=margin = 1;style = vertStyle;overflow = 'scroll';" 
					+ "&sTarget=contents";
			}
		url+="&topMenuSelected="+id;
		url+="&homePageDisabled=true";
		window.location.href=url;
		var allMenus=document.getElementsByName("mM1");
			for(var i=0; i<allMenus.length; i++) {
			allMenus[i].parentNode.style.setAttribute("border-top","2px solid transparent");
			allMenus[i].parentNode.style.setAttribute("padding","0");
			//allMenus[i].setAttribute("onmouseout","cellOnMouseOut(_mi[_itemRef][1]);");
			allMenus[i].style.setAttribute("padding","5px");
			}
	}
	
		function menuBgChangeIter(name){
			var allMenus=document.getElementsByName("mM1");
			
			for(var i=0; i<allMenus.length; i++) {
				if(allMenus[i].innerHTML==name)
				{
					//alert(allMenus[i]);
					//document.getElementById("menu0").setAttribute("onmouseout","");
					//allMenus[i].style.setAttribute("padding","2px");
					//二级菜单背景色 
					allMenus[i].style.setAttribute("background","grey");
					//allMenus[i].style.setAttribute("color","red");
					allMenus[i].style.setAttribute("opacity","0.9");
					allMenus[i].parentNode.style.setAttribute("border-top","2px solid white");
					//allMenus[i].style.setAttribute("border-bottom","2px solid transparent");
					allMenus[i].parentNode.style.setAttribute("padding","0");
					//alert("选中"+name);
					//allMenus[i].style.setAttribute("color","#b5e0fd");
				}else{
				allMenus[i].parentNode.style.setAttribute("border-top","2px solid transparent");
				allMenus[i].style.setAttribute("background","transparent");
					//allMenus[i].style.setAttribute("background","none");
				}
			} 
		}
	function menuBgChange(){
		//alert("menuBgchanged");
		var name=_mi[_itemRef][1];
		var jsonObj=$.parseJSON('${rootMenuList}');
		//alert(name);
		//writeObj(jsonObj);
		//alert(jsonObj[name]);
		if(typeof(jsonObj[name])=="undefined"){
		//alert("true");
		menuBgChangeIter(name);
		}else{
		//alert("false");
		menuBgChangeIter(jsonObj[name]);
		}//alert(name);
		//alert(id);
		//alert(lastSelectedMenuId);
		//if(lastSelectedMenuId!=null)
		/* {
		test[lastSelectedMenuId].style.setAttribute("padding","0px");
		test[lastSelectedMenuId].style.setAttribute("color","white");
		test[lastSelectedMenuId].style.setAttribute("background","null");
		test[lastSelectedMenuId].parentNode.style.setAttribute("padding","5px");
		} */
		
		//alert(obj);
		/* //test[0].parentNode.setAttribute("onmouseover","");
		
		test[id].style.setAttribute("padding","5px");
		test[id].style.setAttribute("color","red");
		test[id].style.setAttribute("background","white");
		
		test[id].parentNode.style.setAttribute("padding","1px"); */
		
		
	}
	
	$(document).ready(function() { 
	/**初始化页面后设置选中的tab背景色、字体颜色*/
		var selectedTab=document.getElementById("${topMenuSelected}");
		selectedTab.style.backgroundColor="#FFFFFF";
		selectedTab.style.opacity="0.8";
		selectedTab.style.color="#2a6496";
	}); 
	
	function cellOnMouseOut(name){
		//alert(name);
		var allMenus=document.getElementsByName("mM1");
		
		for(var i=0; i<allMenus.length; i++) {
			if(allMenus[i].innerHTML==name)
			{
			//alert("2");
			allMenus[i].parentNode.style.setAttribute("background","transparent");
			}
			
		}  
	}
	
	function cellOnMouseOver(name){
		//alert(name);
		var allMenus=document.getElementsByName("mM1");
		
		for(var i=0; i<allMenus.length; i++) {
			if(allMenus[i].innerHTML==name)
			{
			allMenus[i].parentNode.style.setAttribute("background","white");
			}
			
		}  
	}
	//move by hb 2016年5月6日
	window.onload = function() {
	
		if (!URL_HOME_PAGE) 
			URL_HOME_PAGE = 'station/stationStat.do|首页';
			//document.getElementById("menu0").setAttribute("onmouseout","");
			//document.getElementById("menu0").setAttribute("onmouseover","");
			var allMenus=document.getElementsByName("mM1");
			for(var i=0; i<allMenus.length; i++) {
			allMenus[i].parentNode.style.setAttribute("border-top","2px solid transparent");
			allMenus[i].parentNode.style.setAttribute("padding","0");
			//allMenus[i].parentNode.removeAttribute("onmouseover");
			//allMenus[i].setAttribute("onmouseover","cellOnMouseOver(_mi[_itemRef][1]);");
			//allMenus[i].setAttribute("onmouseout","cellOnMouseOut(_mi[_itemRef][1]);");
			allMenus[i].style.setAttribute("padding","5px");
			}
		//document.getElementsByName("mM1")[0].parentNode.setAttribute("onmouseover","");
		//alert("${homePageDisabled}");
		//第一次打开时打开home页，其余时间打开当前一级菜单的第一页
		//后期得设置一下打开home页时对应的tab
		if("${homePageDisabled}"=="true"){
			openPage(_mi[0][2]);
			
			//一级菜单默认页面载入时菜单背景变换
			//考虑设置每个一级菜单的默认载入页面，完善页面首次加载
			menuBgChangeIter(_mi[0][1]);
		}else{
			//alert("home");
			home();
		}
		/** 未设定首选功能时, 设置默认首页. added by zn, since 2011-06-09 */
		//alert(URL_HOME_PAGE);
		
		
	}
	
	// 请求参数中传递过来的生成菜单脚本
	${requestScope.sMenu}
//-->
</script>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" scroll="no">
<form id="submitform" name="submitform" style="visibility:hidden" action="" method="post"></form>
<TABLE width="100%" height="100%" cellSpacing="0" cellPadding="0" border="0" >
	<%@include file="header.jsp" %>
	<!-- 内容区开始 -->
	<TR>
		<TD>
			
			<TABLE cellSpacing="0" cellPadding="0" height="100%" width="100%" border="0">
				<TR>
					<!-- 内容区左侧树开始-->
					<TD id="tdQueryTree" vAlign=top align="center" width="230" height="100%" style="display:none;background-image:url(${pageContext.request.contextPath}/images/backgroundcolor.jpg); background-repeat:repeat;">
						<iframe id="queryTree" name="queryTree" src="querytree/tree.do?" align="left" frameBorder="no" width="100%" height="100%" scrolling="no"></iframe>
					</TD>
					<!-- 内容区左侧树结束-->
					<!-- 内容区右侧主页面开始-->
					<!--liux火狐浏览器     <TD vAlign=top align="center" height="100%" width="100%" > -->
					<!-- Windows ie11浏览器   <TD vAlign=top align="center" height="100%" > --> <TD vAlign=top align="center" height="100%" width="100%" >
						<iframe id="contents" name="contents" src="main.htm" align="left" frameBorder="no" width="100%"  height="100%" scrolling="auto"></iframe>
					</TD>
					<!-- 内容区右侧主页面结束-->
				</TR>
			</TABLE>
		</TD>
	</TR>
	<!--底部内容区结束 -->
	<TR style="background-color: red" height="0">
		<TD>
			<TABLE cellSpacing="0" cellPadding="0" height="100%" width="100%" border="0" >
				<TR>
					<TD ></TD>
				</TR>
			</TABLE>
		</TD>
	</TR>
	<!--底部内容区结束 -->
</TABLE>

<DIV style="display:none">
	<ul>
		<li>To edit the menu, open the file <a href=menu_data.js>menu_data.js</a> in a text editor and edit the details inside the <b>aI()</b> text strings<br><br>
		<li>To add more menu items, just create another <b>aI()</b> string on a new line and enter your properties<br><br>
		<li>add a <b>showmenu=<i>MENUNAME;</i></b> property if you want this menu to open a sub menu<br><br>
		<li>To Add more menus, copy another menu and paste it inside your data file. A menu starts: <b>with(milonic=new menuname("<i>MENUNAME</i>")){</b> and ends: <b>}</b><br><br>
		<li>Any problems, visit http://www.milonic.com/forum. Beginners guide is at <a href=http://www.milonic.com/beginner.php>http://www.milonic.com/beginner.php</a><br><br>
		<li>To get the menu onto your website, insert the following HTML into each page using the menu:
		<pre>
		&lt;noscript&gt;&lt;a href=&quot;http://www.milonic.com/&quot;&gt;DHTML Menu By Milonic JavaScript&lt;/a&gt;&lt;/noscript&gt;			
		&lt;script type=&quot;text/javascript&quot; src=&quot;milonic_src.js&quot;&gt;&lt;/script&gt; 
		&lt;script type=&quot;text/javascript&quot; src=&quot;mmenudom.js&quot;&gt;&lt;/script&gt; 
		&lt;!-- The next file contains your menu data, links and menu structure etc --&gt;
		&lt;script type=&quot;text/javascript&quot; src=&quot;menu_data.js&quot;&gt;&lt;/script&gt;
		</pre>
	</ul>
	<HR>
	Links to other menu demos are:
	<ul>
		<li><a href=extras/dynamic_menus_API/index.html>Dynamic Menu Building API</a></li>
		<li><a href=extras/tooltips/index.html>Tooltips</a></li>
		<li><a href=extras/listbased_menu/index.html>DHTML Menu based on HTML Lists</a></li>
		<li><a href=extras/frames_menu/index.htm>Frames based menu for version 5.0</a></li>
		<li><a href=extras/php_based_menu/index.php>Menu built using PHP - You'll need PHP for this</a></li>
		<li><a href=extras/xml_based_menu/index.php>Menu built using XML - You'll need PHP for this</a></li>
	</ul>
	<HR>
	<p>	
		Please note that users of Microsoft Windows XP with <b>Service Pack 2</b> will need to unblock this page in order to see the menu. 
		The reason for this is due to the fact that the menu has been written in JavaScript. 
		This feature is considered unsafe when used locally and Service Pack 2 now blocks this by default for your safety. 
		This only happens when you run the menu on a local machine, live use on the Internet will not be affected. 
		Milonic Solutions Ltd assure you that there is absolutely no danger of allowing content from the DHTML Menu provided it has been downloaded from www.milonic.com
		<br><br>
		For instructions on removing the forced popup message, please visit <a href=http://www.milonic.com/removelink.php>http://www.milonic.com/removelink.php</a>
		<br><br>
		For information on menu properties, please see <a href=http://www.milonic.com/menuproperties.php>http://www.milonic.com/menuproperties.php</a> <br>
		For information on menu item properties, please see <a href=http://www.milonic.com/itemproperties.php>http://www.milonic.com/itemproperties.php</a> <br>
		For information on style properties, please see <a href=http://www.milonic.com/styleproperties.php>http://www.milonic.com/styleproperties.php</a> <br>
		<br>
		Installation instructions - <a href=docs/install.html>install.html</a><br>
		How to build menus - <a href=docs/howtobuild.html>howtobuild.html</a><br>
		Read Me - <a href=docs/readme.html>readme.html</a><br>
		License Details - <a href=docs/license.html>license.html</a><br>
	</p>
	<br><br><br>
	<table cellSpacing="0" cellPadding="13" border="0" style="border:1px solid #aaaaaa;">
		<tr>
			<td class=backG align=center>
			   <b>Milonic Home Menu</td>
			</tr>
		<tr>
			<td class=form1>
				The <b><i>Milonic Home Menu</i></b> sample is a copy of our own menu here at Milonic.com. <br>
				<br>
				Our menu style compliments the Milonic color scheme and utilises the following properties:<br>
				<br>
				Horizontal Orientation <br>
				Scroll Bars<br>
				Margins<br>
				Solid Borders<br>
				Solid Mouseover Borders<br>
				90% Opacity / 10% Transparency for Submenus<br>
				Drop Shadow for Submenus<br>
				Random Dissolve Outfilter for Submenus<br>
				Item Icons <br>
				Background Images<br>
				Mouseover Background Images<br>
				Sub Menu Indicators <br>
				80% Separators, aligned right for specific items in Submenus<br>
				<p>Downloading this sample will deliver all files needed to run this sample on your local machine.
				<br>
			</td>
		</tr>		
	</table>
</DIV>
</body>
</html>
