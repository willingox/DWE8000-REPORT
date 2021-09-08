


// 画负荷设置曲线图
function drawLoadPlanChart(lsDate,customerNo) {
	
	CATEGORIES = "";
	DATA_SET = "";
	var swf = serverRoot + "/scripts/PowerCharts/Charts/MSStepLine.swf";
	$.ajax({
		type: "GET",
		url: ajaxServerURL,
		dataType: "json",
		data: "method=getLoadPlanData" + "&loadSetDate="+ lsDate + "&customerNo=" + customerNo + "&_time=" + new Date().getTime(),
		success: function(data){
			
			CATEGORIES = getLoadPlanDataset(data) + getCategories(data);
			
			DATA_SET =  getLoadLimitDataset(data) ;
			
			CHART_BEGIN = "	<chart caption='日计划负荷曲线' yAxisName='kW'   numVDivLines='30' divLineAlpha='30' anchorAlpha='0'>";
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
			loadSetChart.render("loadPlanDiv");
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

// 获取计划负荷曲线dataset
function getLoadPlanDataset(data) {
	if ($.isArray(data)) {
		var dataset	= "<dataset seriesName='计划负荷曲线' showValues='0' color='AE81DB' parentYAxis='P'>";
		for (var i = 0; i < data.length; ++i) {
			dataset += "<set value='" + data[i].ratedPower + "' />";
		}
		dataset += "</dataset>";
		return dataset;
	}
	return "<dataset seriesName='计划负荷曲线' showValues='0' color='AE81DB' parentYAxis='P' />";
}

// 获取负荷上限曲线dataset
function getLoadLimitDataset(data) {
	if ($.isArray(data)) {
		var dataset	= "<dataset seriesName='负荷限制曲线' showValues='0' color='CD2304' parentYAxis='P'>";
		for (var i = 0; i < data.length; ++i) {
			dataset += "<set value='" + data[i].limitPower + "' />";
		}
		dataset += "</dataset>";
		return dataset;
	}
	return "<dataset seriesName='负荷限制曲线' showValues='0' color='red' parentYAxis='P' />";
}


