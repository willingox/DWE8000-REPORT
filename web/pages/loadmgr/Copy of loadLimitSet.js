/**
 * 电池模块实时信息脚本库
 */
/*** 全局变量定义区域开始 ***/
battModRTGrid = null;	// 电池模块实时信息表格
batModRTChart = null;	// 电池模块实时曲线图对象
batModRTChartDataXML = null;	// 电池模块实时曲线图XML
batCellRTChart = null;	// 电池单体电压柱状图
currentBatModCode = null;
refreshInterval = 10000;	// 刷新频率
/*** 全局变量定义区域结束 ***/



$(document).ready(function() {
	drawLoadLimitChart();
});

// 画电池模块实时曲线图
function drawLoadLimitChart() {
	
	CATEGORIES = "";
	DATA_SET = "";
	var swf = serverRoot + "/scripts/FusionCharts/charts/MSLine.swf";
	$.ajax({
		type: "GET",
		url: ajaxServerURL,
		dataType: "json",
		data: "method=getLoadLimitData" +"&_time=" + new Date().getTime(),
		success: function(data){
			CATEGORIES = getCategories(data);
			//DATA_SET = getHighTempDataset(data) + getLowTempDataset(data) + getHighVolDataset(data) + getLowVolDataset(data);
			DATA_SET = getHighTempDataset(data);
			
			CHART_BEGIN = "	<chart caption='负荷实时曲线' yAxisName='kW'  labelStep='1' numVDivLines='30' divLineAlpha='30' >";
			STYLES		= "		<styles>"
						+ "			<definition>"
						+ "				<style type='font' name='captionFont' size='14' />"
						+ "				<style type='font' name='axisFont' size='12' />"
						+ "			</definition>"
						+ "			<application>"
						+ "				<apply toObject='Caption' styles='captionFont' />"
						+ "				<apply toObject='Realtimevalue' styles='axisFont' />"
						+ "			</application>"
						+ "		</styles>";
			CHART_END	= "	</chart>";
			batModRTChartDataXML = CHART_BEGIN + CATEGORIES + DATA_SET + STYLES + CHART_END;
			batModRTChart = new FusionCharts(swf, "batModRTChartId1", "100%", "100%", "0", "0");
			batModRTChart.setDataXML(batModRTChartDataXML);
			batModRTChart.render("loadLimitSetDiv");
		}
	});
}

// 获得x轴标签
function getCategories(data) {
	if ($.isArray(data)) {
		var categories = "<categories>";
		for (var i = 0; i < data.length; ++i) {
			//categories += "<category label='" + data[i].gpsTime + "' />";
			categories += "<category name='" + data[i].gpsTime + "' />";
		}
		categories += "</categories>";
		return categories;
	}
	return "";
}

// 获取负荷曲线dataset
function getHighTempDataset(data) {
	if ($.isArray(data)) {
		var dataset	= "<dataset seriesName='负荷限制时段曲线' showValues='0' color='AE81DB' parentYAxis='P'>";
		for (var i = 0; i < data.length; ++i) {
			dataset += "<set value='" + data[i].maxTemp + "' />";
		}
		dataset += "</dataset>";
		return dataset;
	}
	return "<dataset seriesName='负荷限制时段曲线' showValues='0' color='AE81DB' parentYAxis='P' />";
}


