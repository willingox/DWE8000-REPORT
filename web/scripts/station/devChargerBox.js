/** jquery v1.5.1 required */

var REFRESH_INTERVAL_DEVCHARGERBOX = 1000 * 60 * 2;

$(document).ready(function() {

	initDevChargerBoxGrid();
	startRefreshDevChargerBox();

});

/** status of swap devices in station */

/** initialize methods */
function initDevChargerBoxGrid() {
	gridDevChargerBox = new dhtmlXGridObject('gridbox_devchargerbox');
	gridDevChargerBox.setImagePath('../scripts/dhtmlx/dhtmlxGrid/imgs/');
	/** columns:[areaId,workingCount,idleCount,errorCount]. */
	gridDevChargerBox.setHeader("\u533a\u57df\u53f7,\u5de5\u4f5c,\u5f85\u673a,\u6545\u969c", 
		null, 
		["text-align:center;","text-align:center;","text-align:center;","text-align:center;"]
	);
	gridDevChargerBox.setInitWidthsP("25,25,25,25");
	gridDevChargerBox.setColTypes("ro,ro,ro,ro");
	gridDevChargerBox.setColAlign("center,center,center,center");
	gridDevChargerBox.setColSorting("na,na,na,na");
	gridDevChargerBox.setSkin("dhx_blue");
	gridDevChargerBox.enableResizing("false");
	gridDevChargerBox.enableTooltips("false");
	gridDevChargerBox.preventIECaching(true); /** enable random values in URLs */
	//below is only available in professional edition
	//gridDevChargerBox.attachFooter('\u603b\u8ba1,#stat_total,#stat_total,#stat_total');
	gridDevChargerBox.init();
}

/** refresh methods */
var tidDevChargerBox = null;

function startRefreshDevChargerBox() {
	setTimeout(function() {
		getDevChargerBox();
	}, 0);
	tidDevChargerBox = setInterval(getDevChargerBox, REFRESH_INTERVAL_DEVCHARGERBOX);
}

//----- version 1
function getDevChargerBox_V1() {
	try {
		gridDevChargerBox.clearAndLoad('stationDevice.do?method=getBoxChargerInfo', 'json');
	} catch(e) {}
}

//----- version 2
function getDevChargerBox() {
	$.ajax({
		url: 'stationDevice.do', 
		cache: false, 
		dataType: 'json', 
		data: 'method=getBoxChargerInfo', 
		success: _refreshDevChargerBox 
	});
}

function _refreshDevChargerBox(data) {
	if (data) {
		try {
			gridDevChargerBox.clearAll();
			gridDevChargerBox.parse(data, 'json');
		} catch(e) {}
	}
}
