var activeTopMenuA;	// 顶级活动菜单A
var activeTopMenuUl; // 活动菜单a对象(顶级)的UL
var activeTopMenuDiv; // 顶级活动菜单DIV
var activeMenu;	// 活动菜单a对象(非顶级)
var url = "foldMenu.do";
var root;	// 应用跟目录

/**
 * @author IceWee
 * @description 折叠菜单点击事件绑定
 */
$(function(){
	$("a[href=#]").click(function(){
	    
		
		if($(this).parent().attr("class") != 'L1'){	// 处理非顶级菜单		
		  	
			if($(this).parent().attr("class") != 'L21' && $(this).parent().attr("class") != 'L31'){	// 处理不含有子菜单的菜单
			
		
				if(activeMenu){
					activeMenu.className = "";
				}
				activeMenu = $("#" + $(this).attr("id"))[0];
				if(activeMenu){
					activeMenu.className = "active";
				}
			}else{	// 带子菜单的菜单
				var subUl = $("#u_" + $(this).attr("id"));
				if(subUl.size() > 0){	// 存在子菜单
					if(subUl.css("display") == "none"){
						subUl.show();
						$(this).addClass("active");
					}else{
						subUl.hide();
						$(this).removeClass("active");
					}
				}else{
					$(this).addClass("active");
				}
			}
		}else{	// 处理顶级菜单(有子菜单)
		
			if(activeTopMenuA && (activeTopMenuA != $("#" + $(this).attr("id"))[0])){	// 点击自己无效，无改变			
			
	    	$("ul").each(function(){
			$(this).hide();					
		});
		
			
				activeTopMenuA.className = "";
				activeTopMenuA = $("#" + $(this).attr("id"))[0];
				if(activeTopMenuA){
					activeTopMenuA.className = "active";
				}	
				
				var cur_div=activeTopMenuDiv;
				var cur_ul=activeTopMenuUl;
				if(cur_div){
					$(cur_div).css("overflow","hidden");
					$(cur_div).parent().slideUp(500,function(){
						$(cur_div).parent().height(0);	// div的父为td
						$(cur_div).parent().hide();	
						
						$(activeTopMenuDiv).css("overflow","hidden");				
					});	
				}
				activeTopMenuDiv = $("#div_" + $(this).attr("id"))[0];
				activeTopMenuUl = $("#u_" + $(this).attr("id"))[0];
					$(activeTopMenuUl).css("display","inline");
				if(activeTopMenuDiv){			
					$(activeTopMenuDiv).parent().height("100%");
					$(activeTopMenuDiv).parent().show();
				}		
			}else{	// 顶级菜单无子菜单
				if(activeTopMenuA){
					activeTopMenuA.className = "";
				}
				activeTopMenuA = $("#" + $(this).attr("id"))[0];
				if(activeTopMenuA){
					activeTopMenuA.className = "active";
				}
			}
		}
		// 获取功能链接提交请求
		$.ajax({
			type: "GET",
			url: url,
			data: "method=getUrl&menuId=" + $(this).attr("id") + "&_time=" + new Date().getTime(), 
		  	success: function(data){
		  		if(data != ""){
					var strArray = data.split('|');
					if (strArray.length == 2) {
						var tarUrl = strArray[0];
						if (strArray[0].indexOf("http://") == -1)
							tarUrl = root + strArray[0];
						var currLoct = strArray[1];
						if (tarUrl.indexOf("newWindow") != -1) {
							window.open(tarUrl, "", "scrollbars=yes,resizable=yes,channelmode");
						} else {	// 弹出功能不改变当前位置内容
							$("#currentLocation", parent.document).html(currLoct);	// 修改当前位置文字
							var targetIframeName = $("iframe[name=pageContentIframe]", parent.document).attr("name");
							var $foldMenuForm = $("form[name=foldMenuForm]");
							$foldMenuForm.attr("target", targetIframeName);
							$foldMenuForm.attr("action", tarUrl);
							$("#pname").val(strArray[1]);
							$foldMenuForm.submit();
						}
					}
				}
		  	}
		});
		
	});
});