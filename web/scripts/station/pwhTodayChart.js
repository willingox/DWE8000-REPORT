/** jquery v1.5.1 required */

var REFRESH_INTERVAL_PWHTODAY = 1000 * 60 * 2;

$(document).ready(function() {

	initPwhTodayChart();
	startRefreshPwhTodayChart();

});

/** status of today pwhs */

var CHART_XML_BEGIN_PWHTODAY = "<chart palette='3' enableSmartLabels='1' showBorder='0' showLabels='1' showValues='1' showLegend='0' showPercentValues='0' showPercentInToolTip='1' formatNumberScale='0' numberSuffix='kWh' showAboutMenuItem='0'>";

var CHART_XML_END = "</chart>";

var CHART_CFG_OBJECT = {
	'ChartNoDataText': '\u5bf9\u4e0d\u8d77\uff0c\u6ca1\u6709\u8981\u663e\u793a\u7684\u6570\u636e\uff01', 
	'InvalidXMLText': '\u6570\u636e\u683c\u5f0f\u4e0d\u5408\u6cd5\uff01', 
	'XMLLoadingText': '\u6b63\u5728\u83b7\u53d6\u6570\u636e\uff0c\u8bf7\u7a0d\u5019\uff01', 
	'ParsingDataText': '\u6b63\u5728\u89e3\u6790\u6570\u636e\uff0c\u8bf7\u7a0d\u5019\uff01'
};

/** initialize methods */
function initPwhTodayChart() {
	/** chart for today pwh */
	chartPwhToday = new FusionCharts('../scripts/FusionCharts/charts/Pie2D.swf', 'PwhTodayChartId', '100%', '100%', '0', '0');
	chartPwhToday.configure(CHART_CFG_OBJECT);
	//chartPwhToday.setXMLData(CHART_XML_BEGIN_PWHTODAY + CHART_XML_END);
	chartPwhToday.render('chartbox_pwhtoday');
}

/** refresh methods */
var tidPwhTodayChart = null;

function startRefreshPwhTodayChart() {
	setTimeout(function() {
		getPwhTodayChartXml();
	}, 0);
	tidPwhTodayChart = setInterval(getPwhTodayChartXml, REFRESH_INTERVAL_PWHTODAY);
}

/** this will return chart data */
function getPwhTodayChartXml() {
	try {
		$.ajax({
			url: 'stationPwh.do', 
			cache: false, 
			dataType: 'json', 
			data: 'method=today', 
			success: _refreshPwhTodayChart 
		});
	}
	catch (e) {}
}

function _refreshPwhTodayChart(data) {
	if (data) {//alert(data.today);
		if (data.today) {
			chartPwhToday.setXMLData(CHART_XML_BEGIN_PWHTODAY + data.today + CHART_XML_END);
		}
	}
}
