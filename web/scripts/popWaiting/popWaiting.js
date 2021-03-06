/*
 * jQuery JavaScript Library v 1.0 beta1
 * http://icewee.cnblogs.com
 *
 * Copyright (c) 2009 
 * author: IceWee
 * blog: http://icewee.cnblogs.com
 * Dual licensed under the MIT and GPL licenses.
 *
 * Depend on: ymPrompt.js
 *
 * Date: 2011-04-04 12:33:15 -0500 (Mon, 04 Apr. 2011)
 */
function popWaiting(text) {
	var defText = '\u6b63\u5728\u5904\u7406\u4e2d...';
	if (text) {
		defText = text;
	}
	var html = '';
	html += '<table class="pw_table">';
	html += '	<tr>';
	html += '		<td class="pw_img"></td>';
	html += '	</tr>';
	html += '	<tr>';
	html += '		<td class="pw_text"><span>' + defText + '</span></td>';
	html += '	</tr>';
	html += '</table>'

	ymPrompt.win(
		{message: html,
		width: 300,
		titleBar: false
	});
}
