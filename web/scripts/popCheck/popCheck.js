/*
 * jQuery JavaScript Library v 1.0 beta1
 * http://icewee.cnblogs.com
 *
 * Copyright (c) 2009 
 * author: IceWee
 * blog: http://icewee.cnblogs.com
 * Dual licensed under the MIT and GPL licenses.
 *
 * Depend on: ymPrompt.js && jQuery.js
 *
 * Date: 2011-03-30 17:28:25 -0500 (Wed, 30 Mar 2011)
 */
(function($) {
	
	$.fn.popCheck = function(options) { 
		var $input = $(this);
		var settings = 
		{
			title: "\u6807\u9898-\u8BF7\u52FE\u9009",	// popWin's Title
			data: null,	// checkbox dataset, default null
			height: 300,	// popWin's height, default 300
			width: 400,	// popWin's width, default 400
			showClear: true,	// whether show clear button
			handler: function(outVals) {}	// callback function return the checked values with string joined with ','
		};

		if (options){
			jQuery.extend(settings, options);
		}

		function initialise(){
			initContent();
			initEvent();
		}

		// init button event
		function initEvent() {
			$("#_bt_ok").click(function() {
				var text = '';
				var vals = '';
				$('input:checked[id=_chk_boxes]').each(function(){ 
					var array = $(this).val().split('^');
					var lbl = array[0];
					var vl = array[1];
					vals += vl + ',';
					text += lbl + ';';
				});
				if (text != '') {
					text = text.substring(0, text.lastIndexOf(';'));
				}
				if (vals != '') {
					vals = vals.substring(0, vals.lastIndexOf(','));
				}
				$input.val(text);	// modify the text input's value
				settings.handler(vals);	//	callback function, pass the checked value out
				ymPrompt.close();
			});

			$("#_bt_clear").click(function() {
				var text = '';
				var vals = '';
				$('input[@type="checkbox"][id="_chk_boxes"][@checked]').each(function(){ 
					$(this).attr('checked', false);
				});
				$input.val(text);	// modify the text input's value
				settings.handler(vals);	//	callback function, pass the checked value out
			});

			$("#_bt_cls").click(function() {
				ymPrompt.close();
			});
		}

		// init popWin content
		function initContent() {
			var html = '';	
			html += '<table width="100%" height="100%" border="0">';
			html += '    <tr>';
			html += '        <td height="20" width="100%">';
			html += '            <table height="100%" width="100%" border="0">';
			html += '                <tr>';
			html += '                    <td width="">&nbsp;</td>';
			if (settings.showClear) {
			html += '                    <td width="50" height="20">';
			html += '                        <input type="button" id="_bt_clear" class="pc_btn" value="\u6E05&nbsp;&nbsp;\u7A7A">';
			html += '                    </td>';
			}
			html += '                    <td width="50" height="20">';
			html += '                        <input type="button" id="_bt_ok" class="pc_btn" value="\u786E&nbsp;&nbsp;\u5B9A">';
			html += '                    </td>';
			html += '                    <td width="50" height="20">';
			html += '                        <input type="button" id="_bt_cls" class="pc_btn" value="\u53D6&nbsp;&nbsp;\u6D88">';
			html += '                    </td>';
			html += '                </tr>';
			html += '            </table>';
			html += '        </td>';
			html += '    </tr>';
			html += '    <tr><td height="1" width="100%"><hr/></td></tr>';
			html += '    <tr>';
			html += '        <td height="100%">';
			html += '            <div class="pc_div"style="height: 100%; width: 100%; overflow: auto; padding-left: 5px; padding-top: 5px;">';
			html += '                <table width="100%" border="0">';
			html += '			           <tr>';

			var dataArray = settings.data;
			if (dataArray != null){
				var len = dataArray.length;
				for(var i=0; i<len; i++){
					var lbl = dataArray[i].label;
					var val = dataArray[i].value;
					if (i%3 == 0) {
			html += '			           </tr>';
			html += '			           <tr>';
					}
					var state = getState(lbl);
			html += '						<td valign="top"><input type="checkbox" id="_chk_boxes" ' + state + ' value="' + lbl + '^' + val + '"><span class="pc_span">' + lbl + '</span></td>';
				}
			}

			html += '                    </tr>';
			html += '                </table>';
			html += '            </div>';
			html += '        </td>';
			html += '    </tr>';
			html += '</table>';
			
			ymPrompt.win({
				message: html,
				width: settings.width,
				height: settings.height,
				closeBtn: false,	// cancel close button
				useSlide: false,	// fade-in fade-out
				slideCfg: {increment: 0.3, interval:100},	// custom effect
				title: settings.title
			})
		}

		// return the state with the label, so the label must be unique
		function getState(lbl){
			var content = $input.val();
			if (jQuery.trim(content) == ''){
				return '';
			}
			var array = content.split(';');
			for(var i=0; i<array.length; i++){
				if(array[i] == lbl){
					return 'checked';
				}
			}
		}
		
		initialise();
	}

})(jQuery);
