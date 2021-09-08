/** jquery v1.5.1 required */

var REFRESH_INTERVAL_VEHICLECOUNT = 1000 * 60 * 2;

$(document).ready(function() {

	startRefreshVehicleCount();

});

/** evehicle count */
var tidVehicleCount = null;

function startRefreshVehicleCount() {
	setTimeout(function() {
		getVehicleCount();
	}, 0);

	tidVehicleCount = setInterval(function() {
		getVehicleCount();
	}, REFRESH_INTERVAL_VEHICLECOUNT);
}

function getVehicleCount() {
	$.ajax({
		url: 'vehicle.do', 
		cache: false, 
		dataType: 'json', 
		success: _refreshVehicleCount 
	});
}

function _refreshVehicleCount(data) {
	if (data) {
		for (var prop in data) 
			$('#'+prop) && $('#'+prop).text(data[prop]);
	}
}
