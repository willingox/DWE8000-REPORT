/*
vision left tree
*/


/********** xtree utilities **********/

function getFullPathForNode(node) {
	var paths = [];
	if (node) {
		var root = node;
		while (root.parentNode) {
			paths.push(root.text);
			root = root.parentNode;
		}
	}
	return paths.reverse().join('/');
}


/********** xtree plugin **********/

webFXTreeHandler.toggle = function (oItem) {
	var node = this.all[oItem.id.replace('-plus','')];
	if (node.ready) {
		node.toggle();
	}
	else {
		if (node.folder) {
			// get data from server
			var dirPaths = new Array();
			var root = node;
			while (root.parentNode) {
				dirPaths.push(root.text);
				root = root.parentNode;
			}
			root.fetchAction( node.id, dirPaths.reverse().join('/') );
			node.ready = true;
		}
	}
};


/********** vision tree **********/

function VisionTree(treeName, ctxPath, imagePath, onclick) {
	
	if (!treeName) {
		treeName = "\u76d1\u89c6\u9875\u9762";
	}
	
	// image path
	if (imagePath) {
		for (var p in webFXTreeConfig) {
			if (p.indexOf("Icon") > -1) {
				webFXTreeConfig[p] = imagePath + "/" + webFXTreeConfig[p];
			}
		}
	}
	// context path
	webFXTreeConfig.ctxPath = ctxPath ? ctxPath : "";

	//webFXTreeHandler.onSelect = onclick;  // onclick is a function object
	webFXTreeConfig.defaultAction = onclick;  // onclick is a string
	
	var tree = new WebFXTree(treeName);
	tree.folder = true;
	tree.fetchAction = getChildreFromServer;
	document.write(tree);
	webFXTreeHandler.toggle(tree);
}


function getChildreFromServer(nodeId, path) {
	var action = webFXTreeConfig.ctxPath + "/servlet/visionTree?nodeId=" + nodeId;
	if (path) {
		action += "&path=" + path;
	}
		
	new net.ContentLoader(action, onLoadComplete, onLoadError);
}

function onLoadComplete() {
	var retObjects = eval("(" + this.req.responseText + ")");  //properties: parentNodeId, children.
	
	var parentNode = webFXTreeHandler.all[retObjects.parentNodeId];
	var cs = retObjects.children;
	for (var i = 0; i < cs.length; i++) {
		var child = cs[i];
		var node = new WebFXTreeItem(child.name);
		node.folder = !child.isLeaf;
		
		parentNode.add(node, true);
	}
	
	if (parentNode.parentNode) {
		parentNode.toggle();  // for speeding up
	}
}

function onLoadError() {
	window.status = "error: " + this.req.status;
}
