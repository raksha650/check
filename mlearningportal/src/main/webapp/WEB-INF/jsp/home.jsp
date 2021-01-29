<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
</head>


<body>

	<%@include file="header.jsp"%>
	<%@include file="sidenev.jsp"%>

	<main _ngcontent-c0="" class="pt-5" style="margin-left:0px;"
		ng-reflect-klass="pt-5 mx-lg-5" ng-reflect-ng-class="[object Object]">
	<div _ngcontent-c0="" class="">
		<router-outlet _ngcontent-c0=""></router-outlet>
		<app-dashboard1 _nghost-c3="">
		<img src="images/shakti_bhavan.jpg" class="img-responsive" style="width:82%;  position:fixed; height:92%; margin: 10px;
    padding: 10px;">
		  
		
		</app-dashboard1>
	</div>
	</main>

<%@include file="footer.jsp"%>
<script>
window.onload = function () {

var chart = new CanvasJS.Chart("chartContainer", {
	title:{
		text: "Course attend by employee"
	},
	axisY: {
		title: "Number of Courses",
		lineColor: "#4F81BC",
		tickColor: "#4F81BC",
		labelFontColor: "#4F81BC"
	},
	axisY2: {
		title: "Percent",
		suffix: "%",
		lineColor: "#C0504E",
		tickColor: "#C0504E",
		labelFontColor: "#C0504E"
	},
	data: [{
		type: "column",
		dataPoints: [
			{ label: "Core Java", y: 44853 },
			{ label: "Hibernate", y: 36525 },
			{ label: "Email Security", y: 23768 },
			{ label: "EVM", y: 19420 },
			{ label: "Social", y: 13528 },
			{ label: "Protecting-against-ransomware", y: 11906 }
		]
	}]
});
chart.render();
createPareto();	

function createPareto(){
	var dps = [];
	var yValue, yTotal = 0, yPercent = 0;

	for(var i = 0; i < chart.data[0].dataPoints.length; i++)
		yTotal += chart.data[0].dataPoints[i].y;

	for(var i = 0; i < chart.data[0].dataPoints.length; i++){
		yValue = chart.data[0].dataPoints[i].y;
		yPercent += (yValue / yTotal * 100);
		dps.push({label: chart.data[0].dataPoints[i].label, y: yPercent});
	}
	
	chart.addTo("data",{type:"line", yValueFormatString: "0.##\"%\"", dataPoints: dps});
	chart.data[1].set("axisYType", "secondary", false);
	chart.axisY[0].set("maximum", yTotal);
	chart.axisY2[0].set("maximum", 100);
}

}
</script>
</body>

</html>
