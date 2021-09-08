/**
 * ���ģ��ʵʱ��Ϣ�ű���
 */
/*** ȫ�ֱ�����������ʼ ***/
battModRTGrid = null;	// ���ģ��ʵʱ��Ϣ���
batModRTChart = null;	// ���ģ��ʵʱ����ͼ����
batModRTChartDataXML = null;	// ���ģ��ʵʱ����ͼXML
batCellRTChart = null;	// ��ص����ѹ��״ͼ
currentBatModCode = null;
refreshInterval = 10000;	// ˢ��Ƶ��
/*** ȫ�ֱ�������������� ***/



$(document).ready(function() {
	drawLoadLimitChart();
});

// �����ģ��ʵʱ����ͼ
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
			
			CHART_BEGIN = "	<chart caption='����ʵʱ����' yAxisName='kW'  labelStep='1' numVDivLines='30' divLineAlpha='30' >";
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

// ���x���ǩ
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

// ��ȡ��������dataset
function getHighTempDataset(data) {
	if ($.isArray(data)) {
		var dataset	= "<dataset seriesName='��������ʱ������' showValues='0' color='AE81DB' parentYAxis='P'>";
		for (var i = 0; i < data.length; ++i) {
			dataset += "<set value='" + data[i].maxTemp + "' />";
		}
		dataset += "</dataset>";
		return dataset;
	}
	return "<dataset seriesName='��������ʱ������' showValues='0' color='AE81DB' parentYAxis='P' />";
}


