
function getQuery(name)
{
  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
  var r = window.location.search.substr(1).match(reg);
  if (r!=null) return unescape(r[2]); return null;
}
function getObjectById(id)
{
  if (typeof(id) != "string" || id == "") return null;
  if (document.all) return document.all(id);
  if (document.getElementById) return document.getElementById(id);
  try {return eval(id);} catch(e){ return null;}
}
function reRun()
{
  var url = "demo.html";
  url += "\?total="+ document.forms[0].total.value;
  url += "&childNodes="+ document.forms[0].childNodes.value;
  window.location.href = url;
}
window.onload = function()
{

}

function runHelpURL(strURL)
{
  window.open(strURL);    
}

/**
 * IceWee
 *
 * 禁用页面中所有能产生响应的元素，用于提交请求后
 * 该函数要依赖于JQuery，使用需在页面中引入JQuery库
 */
function forbidSubmit() {
	/** 禁用所有超链接以及用超链接实现的按钮 */
	$('a').each(
		function() {
			this.disabled = true;
			// 这里需要注意：超链接设置disabled后虽然样式变了，href后的脚本函数依然有效，所以进行此设置
			this.href = 'javascript:void(0)';
		}
	);
	/** 禁用所有input普通按钮 */
	$('input:button').attr('disabled', 'disabled');
	/** 禁用所有input提交按钮 */
	$('input:submit').attr('disabled', 'disabled');
	/** 禁用所有input单选钮 */
	$('input:radio').attr('disabled', 'disabled');
	/** 禁用所有input复选框 */
	$('input:checkbox').attr('disabled', 'disabled');
	/** 禁用所有select下拉列表 */
	$('select').attr('disabled', 'disabled');
	/** 禁用所有button按钮 */
	$('button').attr('disabled', 'disabled');
	/** 禁用点击所有图片 */
	$('img').attr('disabled', 'disabled');
}