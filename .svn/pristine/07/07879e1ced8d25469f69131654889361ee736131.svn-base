/** jquery v1.5.1 required */

var REFRESH_INTERVAL_PWHTHISMONTH = 1000 * 60 * 1;

$(document).ready(function() {

	initPwhThisMonthChart();
	startRefreshPwhThisMonthChart();

});

/** status of this month pwhs */

var CHART_XML_BEGIN_PWHTHISMONTH = "<chart palette='3' enableSmartLabels='1' showBorder='0' showLabels='1' showValues='1' showLegend='0' showPercentValues='0' showPercentInToolTip='1' formatNumberScale='0' numberSuffix='kWh' showAboutMenuItem='0'>";

var CHART_XML_END = "</chart>";

var CHART_CFG_OBJECT = {
	'ChartNoDataText': '\u5bf9\u4e0d\u8d77\uff0c\u6ca1\u6709\u8981\u663e\u793a\u7684\u6570\u636e\uff01', 
	'InvalidXMLText': '\u6570\u636e\u683c\u5f0f\u4e0d\u5408\u6cd5\uff01', 
	'XMLLoadingText': '\u6b63\u5728\u83b7\u53d6\u6570\u636e\uff0c\u8bf7\u7a0d\u5019\uff01', 
	'ParsingDataText': '\u6b63\u5728\u89e3\u6790\u6570\u636e\uff0c\u8bf7\u7a0d\u5019\uff01'
};

/** initialize methods */
function initPwhThisMonthChart() {
	/** chart for this month pwh */
	chartPwhThisMonth = new FusionCharts('../scripts/FusionCharts/charts/Pie2D.swf', 'PwhThisMonthChartId', '100%', '100%', '0', '0');
	chartPwhThisMonth.configure(CHART_CFG_OBJECT);
	//chartPwhThisMonth.setXMLData(CHART_XML_BEGIN_PWHTHISMONTH + CHART_XML_END);
	chartPwhThisMonth.render('chartbox_pwhthismonth');
}

/** refresh methods */
var tidPwhThisMonthChart = null;

function startRefreshPwhThisMonthChart() {
	setTimeout(function() {
		getPwhThisMonthChartXml();
	}, 0);
	tidPwhThisMonthChart = setInterval(getPwhThisMonthChartXml, REFRESH_INTERVAL_PWHTHISMONTH);
}

/** this will return chart data */
function getPwhThisMonthChartXml() {
	try {
		$.ajax({
			url: 'stationPwh.do', 
			cache: false, 
			dataType: 'json', 
			data: 'method=thisMonth', 
			success: _refreshPwhThisMonthChart 
		});
	}
	catch (e) {}
}

function _refreshPwhThisMonthChart(data) {
	if (data) {
		if (data.thisMonth) {
			chartPwhThisMonth.setXMLData(CHART_XML_BEGIN_PWHTHISMONTH + data.thisMonth + CHART_XML_END);
		}
	}
}
