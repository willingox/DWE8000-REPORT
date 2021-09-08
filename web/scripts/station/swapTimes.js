/** jquery v1.5.1 required */

var REFRESH_INTERVAL_SWAPTIMES = 1000 * 60 * 2;

$(document).ready(function() {

	startRefreshSwapTimes();

});

/** swap times */
var tidSwapTimes = null;

function startRefreshSwapTimes() {
	setTimeout(function() {
		getSwapTimes();
	}, 0);
	tidSwapTimes = setInterval(getSwapTimes, REFRESH_INTERVAL_SWAPTIMES);
}

function getSwapTimes() {
	$.ajax({
		url: 'stationSwap.do', 
		cache: false, 
		dataType: 'json', 
		success: _refreshSwapTimes 
	});
}

function _refreshSwapTimes(data) {
	if (data) {
		for (var prop in data) 
			$('#'+prop) && $('#'+prop).text(data[prop]);
	}
}
