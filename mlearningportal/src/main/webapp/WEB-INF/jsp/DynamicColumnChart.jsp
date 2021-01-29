<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
 
<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
 
map = new HashMap<Object,Object>(); map.put("label", "Management Wing"); map.put("y", 135); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Production Lab"); map.put("y", 322); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Cafetaria"); map.put("y", 76); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Library"); map.put("y", 52); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Recreation Centre"); map.put("y", 34); list.add(map);
 
String dataPoints = gsonObj.toJson(list);
%>
 
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
window.onload = function() { 
 
var chart = new CanvasJS.Chart("chartContainer", {
	title: {
		text: "People on Campus"
	},
	subtitles:[{
		fontSize: 16,
		text: null
	}],
	axisY:{
		title: "Number of People"
	},
	data: [{
		type: "column",
		yValueFormatString: "#,##0",
		indexLabel: "{y}",
		dataPoints : <%out.print(dataPoints);%>
	}]
});
 
function updateChart() {
	var performance, deltaY, yVal;
	var dps = chart.options.data[0].dataPoints;
	var totalPeople = 0;
	for (var i = 0; i < dps.length; i++) {
		deltaY = Math.round(2 + Math.random() * (-2 - 2));
		yVal = deltaY + dps[i].y > 0 ? dps[i].y + deltaY : 0;
		dps[i].y = yVal;
		totalPeople += yVal;
	}
	chart.options.subtitles[0].text = "Total People on Campus: " + totalPeople;
	chart.render();
};
updateChart();
 
setInterval(function () { updateChart() }, 1000);
 
}
</script>
</head>
<body>
<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html> 