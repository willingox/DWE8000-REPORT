/*
vision left tree
*/


/********** xtree utilities **********/

function getAlarmIdsForNode(node) {
	switch (node.depth) {
		case 0:
		case 3:
			if (!node.alarmIds) {
				node.alarmIds = node.alarmId;
			}
			break;

		case 1:
		case 2:
			if (!node.alarmIds) {
				getAlarmIdsFromServer(node);
			}
			break;

		default:
			break;
	}
}

function getAlarmIdsFromServer(node) {
	var action = webFXTreeConfig.ctxPath + "/servlet/alarmTree?method=getAlarmIds&nodeId=" + node.id;
	action += "&depth=" + node.depth;
	action += "&parentId=" + node.alarmId;

	sendXMLHttpRequest(action, null, "GET", onGetAlarmIds);
}

function onGetAlarmIds(responseData) {
	var retObject = eval("(" + responseData + ")");
	
	var parentNode = webFXTreeHandler.all[retObject.parentNodeId];
	if (parentNode) {
		parentNode.alarmIds = retObject.alarmIds;
	}
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
			var root = node;
			while (root.parentNode) {
				root = root.parentNode;
			}
			root.fetchAction( node.id, node.depth+1, node.alarmId );
			node.ready = true;
		}
	}
};


/********** vision tree **********/

function AlarmTree(treeName, ctxPath, imagePath, onclick) {
	
	if (!treeName) {
		treeName = "\u5168\u90e8\u7c7b\u578b";
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

	//webFXTreeHandler.onSelect = onclick;
	webFXTreeConfig.defaultAction = onclick;
	
	var tree = new WebFXTree(treeName);
	tree.folder = true;
	tree.fetchAction = getChildrenFromServer;
	tree.depth = 0;
	tree.alarmId = -1;
	document.write(tree);
	webFXTreeHandler.toggle(tree);
}


function getChildrenFromServer(nodeId, depth, parentId) {
	var action = webFXTreeConfig.ctxPath + "/servlet/alarmTree?nodeId=" + nodeId;
	action += "&depth=" + depth;
	action += "&parentId=" + parentId;
	
	sendXMLHttpRequest(action, null, "GET", onLoadComplete, onLoadError);
}

function onLoadComplete(responseData) {
	var retObjects = eval("(" + responseData + ")");  //properties: parentNodeId, children.

	var parentNode = webFXTreeHandler.all[retObjects.parentNodeId];
	var cs = retObjects.children;
	var clen = cs.length;
	for (var i = 0; i < clen; i++) {
		var child = cs[i];
		var node = new WebFXTreeItem(child.name);
		node.folder = !child.isLeaf;
		node.depth = child.depth;
		node.alarmId = child.alarmId;
		
		parentNode.add(node, true);
	}
	if (parentNode.depth>0 && clen>0) {
		getAlarmIdsForNode(parentNode);
	}
	
	if (parentNode.parentNode) {
		parentNode.toggle();  // for speeding up
	}
}

function onLoadError(lstatus, statusTxt) {
	window.status = "error[" + lstatus + "] " + statusTxt;
}
