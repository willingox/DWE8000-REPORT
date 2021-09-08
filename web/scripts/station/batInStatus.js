/** jquery v1.5.1 required */

var REFRESH_INTERVAL_BATINSTATUS = 1000 * 60 * 2;

$(document).ready(function() {

	initBatInStatusGrid();
	startRefreshBatInStatus();

});

/** status of batteries in station */

/** initialize methods */
function initBatInStatusGrid() {
	gridBatInStatus = new dhtmlXGridObject('gridbox_batin');
	gridBatInStatus.setImagePath('../scripts/dhtmlx/dhtmlxGrid/imgs/');
	/** columns:[areaId,fullCount,chargingCount,errorCount]. */
	gridBatInStatus.setHeader("\u533a\u57df\u53f7,\u5df2\u5145\u6ee1,\u5145\u7535\u4e2d,\u6545\u969c", 
		null, 
		["text-align:center;","text-align:center;","text-align:center;","text-align:center;"]
	);
	gridBatInStatus.setInitWidthsP("25,25,25,25");
	gridBatInStatus.setColTypes("ro,ro,ro,ro");
	gridBatInStatus.setColAlign("center,center,center,center");
	gridBatInStatus.setColSorting("na,na,na,na");
	gridBatInStatus.setSkin("dhx_blue");
	gridBatInStatus.enableResizing("false");
	gridBatInStatus.enableTooltips("false");
	gridBatInStatus.preventIECaching(true); /** enable random values in URLs */
	//only available in professional edition
	//gridBatInStatus.attachFooter('\u603b\u8ba1,#stat_total,#stat_total,#stat_total');
	gridBatInStatus.init();
}

/** refresh methods */
var tidBatInStatus = null;

function startRefreshBatInStatus() {
	setTimeout(function() {
		getBatInStatus();
	}, 0);
	tidBatInStatus = setInterval(getBatInStatus, REFRESH_INTERVAL_BATINSTATUS);
}

//----- version 1
function getBatInStatus_V1() {
	try {
		gridBatInStatus.clearAndLoad('batteryStatus.do?method=listBatIn', 'json');
	} catch(e) {}
}

//----- version 2
function getBatInStatus() {
	$.ajax({
		url: 'batteryStatus.do', 
		cache: false, 
		dataType: 'json', 
		data: 'method=listBatIn', 
		success: _refreshBatInStatus 
	});
}

function _refreshBatInStatus(data) {
	if (data) {
		try {
			gridBatInStatus.clearAll();
			gridBatInStatus.parse(data, 'json');
		} catch(e) {}
	}
}
