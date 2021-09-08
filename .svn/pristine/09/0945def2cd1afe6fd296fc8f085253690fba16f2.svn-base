<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1   
response.setHeader("Pragma","no-cache"); //HTTP 1.0   
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server 
%>
<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@ include file="../common/taglibs.jsp"%>

<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>query tree</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/dhtmlxTree/codebase/dhtmlxtree.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/dhtmlx/dhtmlxDataView/dhtmlxdataview.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlxTree/sources/dhtmlxCommon/codebase/dhtmlxcommon.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlxTree/codebase/dhtmlxtree.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dhtmlx/dhtmlxDataView/dhtmlxdataview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.5.1.min.js"></script>
<script type="text/javascript">
<!--
	//check by hb 2016-5-19
	$(document).ready(function() {
		$('#eqFilter').change(function() {
			if (!$(this).val()) {
			//	$('#euList').css('display', 'none');
			//	$('#euList').hide();
				$('#euList').slideUp('500', function() {
					$('#euList').hide();
				});

				var suffix = _getTreeSuffix( curTreeConfig );
    			$('#euTree_' + suffix).css('display', 'block');
    			_adjustTreeDivSize( $('#euTree_' + suffix) );
    			$('#treecheck').css('display', 'block');
			}
		});
	});

	/** tree config info */
	/** type:  [ jz, flfx, fl ] */
	/** depth: 1-3 */
	/** mode: check, radio */
	/** fltypes: '1','2',or '1,2', '1,2,3'...when type=fl */
	var DEFAULT_TREE_CONFIG = { type: 'jz', depth: 3, mode: 'radio', fltypes: '1' };

	var curTreeConfig = DEFAULT_TREE_CONFIG;

    function getSelectedId() {
		/** tree */
     	var curTree = window['euTree_' + _getTreeSuffix(curTreeConfig)];
     	if (curTree) {
	     	if ('check' == curTreeConfig.mode)
	     	{	//var atest=curTree.getAllChecked();
	     		//alert("1");
	     		return curTree.getAllChecked();
	     	}else 
	     		return curTree.getSelectedItemId();
     	}
    }
	
	// check by hb 2016-5-19
    function showTree(type, depth, mode, fltypes) {
   
    	var treeConfig = _getTreeConfig(type, depth, mode, fltypes);

		var treeSuffix = _getTreeSuffix( treeConfig );
		//判断树对应的div是否存在,若不存在,新建div
    	var oTreeDiv = document.getElementById('euTree_' + treeSuffix);
    	if (!oTreeDiv) {
    		//div不存在，新建树
    		_buildTree(treeConfig, treeSuffix);
    		oTreeDiv = document.getElementById('euTree_' + treeSuffix);
    	}
    	if (oTreeDiv) {
    		//div存在
    		$('#euList').css('display', 'none');
    		$('.euTree:visible').css('display', 'none');
    		$('#euTree_' + treeSuffix).css('display', 'block');
    		_adjustTreeDivSize( $('#euTree_' + treeSuffix) );
    		//保存当前树的配置
    		curTreeConfig = treeConfig;
    	//==============================================	
    		 var a=getSelectedId().split(",");
    		if(1==a.length && a[0]==""){
    			setCheckCount(0);
    		}else{
    			setCheckCount(a.length);
    		}	
    	}
    }
    
	//调整树对应div的大小
	// check by hb 2016-5-19  
	function _adjustTreeDivSize(oEl) {
		if (!oEl) return;

		var vpH = $('#treeContDiv').innerHeight();
		var lineH = $('#euLine').outerHeight();
		var treeH = vpH - lineH;
		if (treeH > 0) 
			oEl.height(treeH-15-60);
	}
	// check by hb 2016-5-19
    function _getTreeConfig(type, depth, mode, fltypes) {
    	var treeConfig = DEFAULT_TREE_CONFIG;
    	//if ( 'jz'==type || 'flfx'==type || 'fl'==type || 'reneTree'==type ) 
    	if ( typeof(type)!='undefined') 
    		treeConfig.type = type;
    	if (depth >=1 && depth <= 5) 
    		treeConfig.depth = depth;
    	if ( 'check'==mode || 'radio'==mode ) 
    		treeConfig.mode = mode;
    	if ( 'fl'==type ) 
    		treeConfig.fltypes = fltypes;
    	
    	return treeConfig;
    }
	// check by hb 2016-5-19
    function _getTreeSuffix(config) {
    	var suffix = '';
    	if (config) {
    		suffix = config.type;
    		if ( 'fl'==config.type ) 
    			suffix += config.fltypes;
    		suffix += '_l' + config.depth + '_' + config.mode;
    	}
    	return suffix;
    }
	//edit by hb 2016-5-19
    function _buildTree(config, suffix) {
    	if (!config)  return;

    	$('#treeContDiv').append('<div id="euTree_' + suffix + '" class="euTree" style="display:none;"></div>');
		var rootId = 'r0_' + suffix;
    	var tree = new dhtmlXTreeObject('euTree_' + suffix,'100%','100%',rootId);
    	tree.setSkin('dhx_skyblue');
		tree.setImagePath("${pageContext.request.contextPath}/scripts/dhtmlxTree/codebase/imgs/dhxtree_skyblue/");
		tree.enableTreeImages(false);
		tree.rootId = rootId;
    	tree.config = config;
    	//开启三状态多选框，默认为radio
    	if ('check' == config.mode) 
    	{
    		tree.enableCheckBoxes(true);
    		tree.enableThreeStateCheckboxes(true);
    		tree.enableSmartCheckboxes(true);
    	}
    	//tree.enableSmartRendering(true); //有错
		tree.preventIECaching(true); 
    	//自动传递id至action	
    	tree.setXMLAutoLoadingBehaviour("id");
    	tree.setXMLAutoLoading("${pageContext.request.contextPath}/querytree/treeData.do?method=expand"
    							+"&treetype=" + tree.config.type 
    							+ "&depth=" + tree.config.depth 
    							+ "&fltypes=" + tree.config.fltypes );
    	
    	tree.setDataMode("xml");
    	tree.load("${pageContext.request.contextPath}/querytree/treeData.do?method=display"
    				+"&rootId="+tree.rootId
    				+"&treetype=" + tree.config.type 
    				+"&depth=" + tree.config.depth 
    				+"&fltypes=" + tree.config.fltypes );
		//数量巨大时可以分页显示
		
    	//tree.attachEvent("onCheck", function(){
    		//setCheckCount(1);
    		//alert(this.getAllChecked());
		//});
		tree.attachEvent("onSelect", function(id, mode){
			if(typeof(eval("parent.window.frames['contents'].singleLeafOnSelect")) == "function")
			{
				parent.window.frames['contents'].singleLeafOnSelect(id,mode);
			}else{
    			alert("按取消按钮返回主页面选择风机！");
			}
		});
		
    	window['euTree_' + suffix] = tree;
    	//alert("buildtree finished");
    }
    //=================================================
    var disLevel="";
  
    function treeonCheck(id){
    	var suffix=_getTreeSuffix(curTreeConfig);
    	var curTree = window['euTree_' + suffix]; 	
    	var a=getSelectedId().split(",");
    	if(!curTree) return;
    	if(!window["disCheck_"+suffix]) window["disCheck_"+suffix]="";
    	if(!window["level_"+suffix]) window["level_"+suffix]=0;
    	
    	if(disLevel.indexOf(id.split("_").length.toString())>-1){
    		curTree.setCheck(id,0);
    		curTree.disableCheckbox(id,true);
    		window["disCheck_"+suffix]+=id+",";
    		return;
    	}
    	if(1==a.length && a[0]==""){//无已选项
    		setCheckCount(0);
    		enableAllCheckBox();
    		return;
    	}
    	if(1==a.length && a[0].length>0){
    		window["level_"+suffix]=a[0].split("_").length;
    		setCheckCount(a.length);
    	}else{
    		if(id.split("_").length!=window["level_"+suffix]){
    			curTree.setCheck(id,0);
    			curTree.disableCheckbox(id,true);
    			window["disCheck_"+suffix]+=id+",";
    		}else{
    			setCheckCount(a.length);
    		}
    	}
    }
    
    function _disLevel(a){
    	disLevel=a;
    	if("0"==a){
    		$("#note").text(" ");
    	}else{
    		$("#note").text("在本页树的 "+a+" 级将不可选！");
    	}
    }
    
    function enableAllCheckBox(){
    	var suffix=_getTreeSuffix(curTreeConfig);
    	var curTree = window['euTree_' + suffix];
    	if(!curTree) return;
    	if(!window["disCheck_"+suffix]) window["disCheck_"+suffix]="";
    	var c=window["disCheck_"+suffix].split(",");
    	for(var i=0;i<c.length;i++){
    		curTree.disableCheckbox(c[i],false);
    	}
    }
    
    function setCheckCount(a){
    	$("#checkCount").text("已选中 "+a+" 项");
    }
    
    //*********************************************
        //打印obj所有内容
        function writeObj(obj){ 
		    var description = ""; 
		    for(var i in obj){   
		        var property=obj[i];   
		        description+=i+" = "+property+"\n";  
		    }   
			    alert(description); 
		} 	
    //edit by hb 2016-5-19 
    function cancelcheck(){
    	var curTree = window['euTree_' + _getTreeSuffix(curTreeConfig)];
    	if(curTree){
    		curTree.setSubChecked(curTree.rootId,false);
    	}
    }
    
    //edit by hb 2016-5-19 
    function openAllLeafs(){
    	var curTree = window['euTree_' + _getTreeSuffix(curTreeConfig)];
    	if(curTree){
    		curTree.openAllItems(curTree.rootId);
    	}
    }
    
     //edit by hb 2016-5-19 
    function closeAllLeafs(){
    	var curTree = window['euTree_' + _getTreeSuffix(curTreeConfig)];
    	if(curTree){
    		curTree.closeAllItems(curTree.rootId);
    	}
    }
     //edit by hb 2016-5-19 
    function refreshAll(){
    	//alert("refresh");
    	var curTree = window['euTree_' + _getTreeSuffix(curTreeConfig)];
    	if(curTree){
    		curTree.refreshItem();
    	}
    }
    
    function showDivShadow(){ 
     	$("#div_shadow").fadeTo(260,0.30);
    }
     
    function hideDivShadow(){
     	$("#div_shadow").fadeOut(460);
    }
	//=================================================
    function _search(){
    	var strFilter = $('#eqFilter').val();
    	if (!strFilter) return;

		if (!window['eqView']) {
			var dataView = new dhtmlXDataView({
				container:'data_container',
				type:{
					template: '#name#',
					template_loading: '数据加载中...',
					height:20,
					margin:0,
					padding:5
				},
				pager:{
					container: 'paging_here',
					size:15,
        			group:5
				}
			});
			
			//custom
			var pager = dataView.config.pager;
		//	pager.define("template","{common.first()}{common.pages()}{common.last()}");
			pager.define("template","{common.first()}{common.prev()}{common.next()}{common.last()} {common.page()} / #limit#");

			window['eqView'] = dataView;
		}
		if (window['eqView']) {
			window['eqView'].clearAll();
			window['eqView'].load( 
				encodeURI(encodeURI('${pageContext.request.contextPath}/querytree/treeData.do?method=query&eqName=' + strFilter)) );

			$('.euTree:visible').css('display', 'none');
			$('#euList').css('display', 'block');
			_adjustListDivSize();
		}
    }

	function _adjustListDivSize() {
		var vpH = $('#treeContDiv').innerHeight();
		var lineH = $('#euLine').outerHeight();
		var treeH = vpH - lineH;
		if (treeH > 0) {
		//	$('#euList').height(treeH);
			$('#data_container').height( treeH - $('#paging_here').outerHeight() - 15);
			$('#treecheck').css('display', 'none');
		}
	}
	

//-->
</script>
<style type="text/css">

div .euLine {
	text-align: left;
	margin: 0px;
	margin-top: 5px;
	margin-bottom: 10px;
	padding: 0;
	border:0px solid red;
}

div .euTree {
	width:100%;
	overflow:auto;
	border:0px solid yellow;
}

.queryedit{
	padding-top:5px;
	padding-left:30px;
	width:137px;
	height:26px;
	border:0px;
	margin-left:0px;
	background:url(${pageContext.request.contextPath}/images/green/tool/search_bg.png) no-repeat;
}

.div_shadow{
    background-color:#000000;
    width:100%;
    height:100%;
    display:none;
    position:absolute;
    z-index:100;
    filter:alpha(opacity=30);
}
</style>
</HEAD>
<BODY scroll="no" style="background-color:#ffffff;width:100%;height:100%;">
 
<div class="div_shadow" id="div_shadow"></div>
<div style="padding: 0px; padding-left: 10px; margin: 0px auto; height:100%;width: 100%;">

	<div id="treeContDiv" style="width: 100%;height:100%; padding: 0px; margin: 0px 0px;border-right:1px solid #dedede;">
		<div id="euLine" class="euLine">
		<table>
		<tr style='display:none'>
			<td><input maxlength="7" type="text" id="eqFilter" name="eqFilter" value="" class="queryedit"/></td>
			<td><a class="abtn3" href="javascript:void(0);" onclick="_search()" style="width:60px;"><span style="margin-left:3px;"><img align="left" src="${pageContext.request.contextPath}/images/green/tool/search.png"/>搜&nbsp;索</span></a></td>
		</tr>
		</table>
		</div>
		<div style="height: 60px;" id="treecheck">
			<!-- <span id="note" style="color:#ff0000;"></span><br>
			 <span id="checkCount" style="margin-right: 30px;">已选中 0 项</span>-->			
			<a class="abtnlt" href="javascript:void(0);" onclick="openAllLeafs()"><span style="margin-left:3px;"><img  src="${pageContext.request.contextPath}/images/green/tool/show.png" alt=""/>展开</span></a>				
			<a class="abtnlt" href="javascript:void(0);" onclick="closeAllLeafs()"><span style="margin-left:3px;"><img  src="${pageContext.request.contextPath}/images/green/tool/hide.png" alt=""/>收起</span></a><br>				
			<%--<a class="abtnlt" href="javascript:void(0);" onclick="cancelcheck()"><span style="margin-left:3px;"><img  src="${pageContext.request.contextPath}/images/green/tool/delete.png" alt=""/>取消已选</span></a>				
			<a class="abtnlt" href="javascript:void(0);" onclick="refreshAll()"><span style="margin-left:3px;"><img  src="${pageContext.request.contextPath}/images/green/tool/delete.png" alt=""/>刷新</span></a>				
			<a class="abtnlt" href="javascript:void(0);" onclick="getSelectedId()"><span style="margin-left:3px;"><img  src="${pageContext.request.contextPath}/images/green/tool/delete.png" alt=""/>获取选中</span></a>				
			
			--%></div>
		<div id="euList" style="width:100%;overflow:hidden;display:none;">
			<div id="paging_here" style="width:100%;height:36px;margin:0;clear:left;"></div>
			<div id="data_container" style="border:1px solid #A4BED4; background-color:white; width:100%;overflow:auto;"></div>
		</div>
	</div>
	
</div>	
</BODY>
</html>
