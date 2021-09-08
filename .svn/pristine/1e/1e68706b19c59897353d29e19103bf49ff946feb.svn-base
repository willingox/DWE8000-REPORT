/** jquery v1.5.1 required */

var REFRESH_INTERVAL_DEVSWAP = 1000 * 60 * 2;

$(document).ready(function() {

	initDevSwapGrid();
	startRefreshDevSwap();

});

/** status of swap devices in station */

/** initialize methods */
function initDevSwapGrid() {
	gridDevSwap = new dhtmlXGridObject('gridbox_devswap');
	gridDevSwap.setImagePath('../scripts/dhtmlx/dhtmlxGrid/imgs/');
	/** columns:[areaId,leftSwapDev,rightSwapDev]. */
	gridDevSwap.setHeader("\u533a\u57df\u53f7,\u5de6\u4fa7\u8bbe\u5907,\u53f3\u4fa7\u8bbe\u5907", 
		null, 
		["text-align:center;","text-align:center;","text-align:center;"]
	);
	gridDevSwap.setIconPath('../scripts/station/images/');
	gridDevSwap.setInitWidthsP("30,35,35");
	gridDevSwap.setColTypes("ro,img,img");
	gridDevSwap.setColAlign("center,center,center");
	gridDevSwap.setColSorting("na,na,na");
	gridDevSwap.setSkin("dhx_blue");
	gridDevSwap.enableResizing("false");
	gridDevSwap.enableTooltips("false");
	gridDevSwap.preventIECaching(true); /** enable random values in URLs */
	//below is only available in professional edition
	//gridDevSwap.attachFooter('\u603b\u8ba1,#stat_total,#stat_total');
	gridDevSwap.init();
}

/** refresh methods */
var tidDevSwap = null;

function startRefreshDevSwap() {
	setTimeout(function() {
		getDevSwap();
	}, 0);
	tidDevSwap = setInterval(getDevSwap, REFRESH_INTERVAL_DEVSWAP);
}

//----- version 1
function getDevSwap_V1() {
	try {
		gridDevSwap.clearAndLoad('stationDevice.do?method=getSwapDeviceInfo', 'json');
	} catch(e) {}
}

//----- version 2
function getDevSwap() {
	$.ajax({
		url: 'stationDevice.do', 
		cache: false, 
		dataType: 'json', 
		data: 'method=getSwapDeviceInfo', 
		success: _refreshDevSwap 
	});
}

function _refreshDevSwap(data) {
	if (data) {
		try {
			gridDevSwap.clearAll();
			gridDevSwap.parse(data, 'json');
		} catch(e) {}
	}
}
