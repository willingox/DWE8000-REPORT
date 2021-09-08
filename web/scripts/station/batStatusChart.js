/** jquery v1.5.1 required */

var REFRESH_INTERVAL_BATSTATUS = 1000 * 60 * 2;

$(document).ready(function() {

	initBatStatusChart();
	startRefreshBatStatusChart();

});

/** status of batteries */

//var CHART_XML_BEGIN_BATSTATUS = "<chart caption='\u7535\u6c60\u72b6\u6001' palette='2' enableSmartLabels='1' showLabels='0' showLegend='1' showPercentValues='0' showPercentInToolTip='1' formatNumberScale='0' numberSuffix='\u7bb1' pieSliceDepth='20' showAboutMenuItem='0'>";
//var CHART_XML_BEGIN_BATSTATUS = "<chart caption='\u7535\u6c60\u72b6\u6001' palette='3' enableSmartLabels='0' showBorder='0' showLabels='1' showValues='1' showLegend='0' showPercentValues='0' showPercentInToolTip='1' formatNumberScale='0' numberSuffix='\u7bb1' showAboutMenuItem='0'>";
var CHART_XML_BEGIN_BATSTATUS = "<chart palette='3' enableSmartLabels='0' showBorder='0' showLabels='1' showValues='1' showLegend='0' showPercentValues='0' showPercentInToolTip='1' formatNumberScale='0' numberSuffix='\u7bb1' showAboutMenuItem='0'>";

//var CHART_XML_BEGIN_BATINSTATUS = "<chart caption='\u7ad9\u5185\u7535\u6c60\u72b6\u6001' palette='2' enableSmartLabels='1' showPercentValues='0' showPercentInToolTip='1' formatNumberScale='0' numberSuffix='\u7bb1' pieSliceDepth='20' showAboutMenuItem='0'>";
//var CHART_XML_BEGIN_BATINSTATUS = "<chart caption='\u7ad9\u5185\u7535\u6c60\u72b6\u6001' palette='3' enableSmartLabels='1' showBorder='0' showLabels='1' showValues='1' showLegend='0' showPercentValues='0' showPercentInToolTip='1' formatNumberScale='0' numberSuffix='\u7bb1' showAboutMenuItem='0'>";
var CHART_XML_BEGIN_BATINSTATUS = "<chart palette='3' enableSmartLabels='0' showBorder='0' showLabels='1' showValues='1' showLegend='0' showPercentValues='0' showPercentInToolTip='1' formatNumberScale='0' numberSuffix='\u7bb1' showAboutMenuItem='0'>";

var CHART_XML_END = "</chart>";

var CHART_CFG_OBJECT = {
	'ChartNoDataText': '\u5bf9\u4e0d\u8d77\uff0c\u6ca1\u6709\u8981\u663e\u793a\u7684\u6570\u636e\uff01', 
	'InvalidXMLText': '\u6570\u636e\u683c\u5f0f\u4e0d\u5408\u6cd5\uff01', 
	'XMLLoadingText': '\u6b63\u5728\u83b7\u53d6\u6570\u636e\uff0c\u8bf7\u7a0d\u5019\uff01', 
	'ParsingDataText': '\u6b63\u5728\u89e3\u6790\u6570\u636e\uff0c\u8bf7\u7a0d\u5019\uff01'
};

/** initialize methods */
function initBatStatusChart() {
	/** chart for bat status */
	chartBatStatus = new FusionCharts('../scripts/FusionCharts/charts/Pie2D.swf', 'BatStatusChartId', '100%', '100%', '0', '0');
	chartBatStatus.configure(CHART_CFG_OBJECT);
	//chartBatStatus.setXMLData(CHART_XML_BEGIN_BATSTATUS + CHART_XML_END);
	chartBatStatus.render('chart_batStatus');
	
	/** chart for bat inside status */
	chartBatInStatus = new FusionCharts('../scripts/FusionCharts/charts/Pie2D.swf', 'BatStatusInChartId', '100%', '100%', '0', '0');
	chartBatInStatus.configure(CHART_CFG_OBJECT);
	//chartBatInStatus.setXMLData(CHART_XML_BEGIN_BATINSTATUS + CHART_XML_END);
	chartBatInStatus.render('chart_batInStatus');
}

/** refresh methods */
var tidBatStatusChart = null;

function startRefreshBatStatusChart() {
	setTimeout(function() {
		getBatStatusChartXml();
	}, 0);
	tidBatStatusChart = setInterval(getBatStatusChartXml, REFRESH_INTERVAL_BATSTATUS);
}

/** this will return two charts data together */
function getBatStatusChartXml() {
	try {
		$.ajax({
			url: 'batteryStatus.do', 
			cache: false, 
			dataType: 'json', 
			data: 'method=chart', 
			success: _refreshBatStatusChart 
		});
	}
	catch (e) {}
}

function _refreshBatStatusChart(data) {
	if (data) {//alert(data.batStatus);
		if (data.batStatus) {
			chartBatStatus.setXMLData(CHART_XML_BEGIN_BATSTATUS + data.batStatus + CHART_XML_END);
		}
		if (data.batInsideStatus) {
			chartBatInStatus.setXMLData(CHART_XML_BEGIN_BATINSTATUS + data.batInsideStatus + CHART_XML_END);
		}
	}
}
