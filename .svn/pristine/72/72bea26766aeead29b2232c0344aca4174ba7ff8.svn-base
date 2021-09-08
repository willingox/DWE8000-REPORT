
/*
$(document).ready(function() {
	var lsDate = document.getElementById("loadSetDate").value;
	alert(lsDate);
	drawLoadLimitChart(lsDate);
});
*/
function drawLoadLimitChart1(){
	
	var swfUrl = "http://localhost:8080/dsm8000/scripts/PowerCharts/Charts/MSStepLine.swf";
	var chart = new FusionCharts(swfUrl, "ChartId", "530", "420", "0", "0");
	chart.setXMLUrl("http://localhost:8080/dsm8000/scripts/PowerCharts/MSStepLine1.xml");
	chart.render("loadLimitSetDiv");
	
}

// 画负荷设置曲线图
function drawLoadLimitChart(lsDate) {
	
	CATEGORIES = "";
	DATA_SET = "";
	var swf = serverRoot + "/scripts/PowerCharts/Charts/MSStepLine.swf";
	$.ajax({
		type: "GET",
		url: ajaxServerURL,
		dataType: "json",
		data: "method=getLoadLimitData" + "&loadSetDate="+ lsDate + "&_time=" + new Date().getTime(),
		success: function(data){
			//alert(data);
			CATEGORIES = getCategories(data);
			DATA_SET = getDataset(data);
			
			CHART_BEGIN = "	<chart caption='负荷限制曲线' yAxisName='kW'   numVDivLines='30' divLineAlpha='30' anchorAlpha='0'>";
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
			loadSetChartDataXML = CHART_BEGIN + CATEGORIES + DATA_SET + STYLES + CHART_END;
			loadSetChart = new FusionCharts(swf, "loadSetChartId", "100%", "100%", "0", "0");
			loadSetChart.setDataXML(loadSetChartDataXML);
			loadSetChart.render("loadLimitSetDiv");
		}
	});
}

// 获得x轴标签
function getCategories(data) {
	if ($.isArray(data)) {
		var categories = "<categories>";
		for (var i = 0; i < data.length; ++i) {
			categories += "<category name='" + data[i].loadTime + "' />";
		}
		categories += "</categories>";
		return categories;
	}
	return "";
}

// 获取负荷设置曲线dataset
function getDataset(data) {
	if ($.isArray(data)) {
		var dataset	= "<dataset seriesName='负荷限制时段曲线' showValues='0' color='AE81DB' parentYAxis='P'>";
		for (var i = 0; i < data.length; ++i) {
			dataset += "<set value='" + data[i].ratedPower + "' />";
		}
		dataset += "</dataset>";
		return dataset;
	}
	return "<dataset seriesName='负荷限制时段曲线' showValues='0' color='AE81DB' parentYAxis='P' />";
}


