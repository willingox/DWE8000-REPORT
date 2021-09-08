/*
 * jQuery JavaScript Library v 1.0 beta1
 * http://icewee.cnblogs.com
 *
 * Copyright (c) 2009 
 * author: IceWee
 * blog: http://icewee.cnblogs.com
 * Dual licensed under the MIT and GPL licenses.
 *
 * Depend on: jquery-1.5.1.min.js
 *
 * Date: 2011-06-08 10:30:00 -0500 (Wed, 08 June 2011)
 */
(function($){
	$.fn.btn = function(){
		var btn = this.data("_self");;
		if(btn){
			return btn;
		};

		this.init = function(){
			var obj = $(this);
			var id = $(this).attr("id") || "gen" + Math.random();
			var icon = $(this).attr("icon") || "icon-btncom";
			var bntStr=[
				'<table id="',id,'" class="z-btn" cellSpacing=0 cellPadding=0 border=0><tbody><tr>',
					'<TD class=z-btn-left><i>&nbsp;</i></TD>',
					'<TD class=z-btn-center><EM unselectable="on">',
						'<BUTTON class="z-btn-text ',icon,'" >',$(this).attr('value'),'</BUTTON>',
					'</EM></TD>',
					'<TD class=z-btn-right><i>&nbsp;</i></TD>',
				'</tr></tbody></table>'
			];
			var bnt = $(bntStr.join("")).btn();
			bnt._click = eval(obj.attr("onclick"));
			bnt.disable();
			if(obj.attr("disabled"))
				bnt.disable();
			else bnt.enable();
			$(this).replaceWith(bnt);
			bnt.data("_self", bnt);  
			return bnt;
		};

		this.enable = function(){
			this.removeClass("z-btn-dsb");
			this.click(this._click);
			this.hover(
				  function () {
				    $(this).addClass("z-btn-over");
				  },
				  function () {
				    $(this).removeClass("z-btn-over");
				  }
				)
		};

		this.disable = function(){
			 this.addClass("z-btn-dsb");
			 this.unbind("hover");
			 this.unbind("click");
		};  
		return this;
	};
	
	$(function(){
		$("input[type='button'][class='jquery_button']").each(function(){
			$(this).btn().init();
		});

		$("input[type='reset'][class='jquery_reset']").each(function(){
			$(this).btn().init().click(function(){
				var form = $(this).parents("form")[0];
				if(form)
					form.reset();
			});
		});

		$("input[type='submit'][class='jquery_submit']").each(function(){
			$(this).btn().init().click(function(){
				var form = $(this).parents("form")[0];
				if(form)
					form.submit();
			});
		});
	})
})(jQuery);	