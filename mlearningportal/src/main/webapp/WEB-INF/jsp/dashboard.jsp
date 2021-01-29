<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
</head>


<body  background-color: palegoldenrod;>

	<%@include file="header.jsp"%>
	<%@include file="sidenev.jsp"%>

	<main _ngcontent-c0="" class="pt-5 mx-lg-5"
		ng-reflect-klass="pt-5 mx-lg-5" ng-reflect-ng-class="[object Object]">
	<div _ngcontent-c0="" class="mt-5">
		<router-outlet _ngcontent-c0=""></router-outlet>
		<app-dashboard1 _nghost-c3="">
		<div _ngcontent-c3="" class="card mb-4 wow fadeIn"
			style="visibility: visible; animation-name: fadeIn;">
			<!-- <div _ngcontent-c3=""
				class="card-body d-sm-flex justify-content-between">
				<h4 _ngcontent-c3="" class="mb-sm-0 pt-3">
					<a _ngcontent-c3=""
						href="https://mdbootstrap.com/material-design-for-bootstrap/"
						target="_blank">Home Page</a><span _ngcontent-c3="">/</span><span
						_ngcontent-c3="">Dashboard</span>
				</h4>
				<form _ngcontent-c3=""
					class="d-flex md-form justify-content-center ng-untouched ng-pristine ng-valid"
					novalidate="">
					<input _ngcontent-c3="" aria-label="Search" class="form-control"
						placeholder="Type your query" type="search">
					<button _ngcontent-c3="" class="btn btn-primary btn-sm my-0 p"
						type="submit">
						<i _ngcontent-c3="" class="fa fa-search"></i>
					</button>
				</form>
			</div> -->
		</div>
		<app-stats-card _ngcontent-c3="" _nghost-c4="">
		<section _ngcontent-c4="" class="mt-lg-5">
			<div _ngcontent-c4="" class="row">
				<div _ngcontent-c4="" class="col-xl-4 col-md-6 mb-r">
					<div _ngcontent-c4=""
						class="card card-cascade cascading-admin-card">
						<div _ngcontent-c4="" class="admin-up">
							<i _ngcontent-c4="" class="fa fa-book primary-color"></i>
							<div _ngcontent-c4="" class="data">
								<p _ngcontent-c4="">TOTAL NO. OF COURSES</p>
								<h4 _ngcontent-c4="">
									<strong _ngcontent-c4="">200</strong>
								</h4>
							</div>
						</div>
						<!-- <div _ngcontent-c4="" class="card-body">
							<div _ngcontent-c4="" class="progress">
								<div _ngcontent-c4="" aria-valuemax="100" aria-valuemin="0"
									aria-valuenow="25" class="progress-bar bg-primary"
									role="progressbar" style="width: 25%"></div>
							</div>
							<p _ngcontent-c4="" class="card-text">Better than last week
								(25%)</p>
						</div> -->
					</div>
				</div>
				<div _ngcontent-c4="" class="col-xl-4 col-md-6 mb-r">
					<div _ngcontent-c4=""
						class="card card-cascade cascading-admin-card">
						<div _ngcontent-c4="" class="admin-up">
							<i _ngcontent-c4="" class="fa fa-line-chart warning-color"></i>
							<div _ngcontent-c4="" class="data">
								<p _ngcontent-c4="">COMPLIANCE COURSES</p>
								<h4 _ngcontent-c4="">
									<strong _ngcontent-c4="">200</strong>
								</h4>
							</div>
						</div>
						<!-- <div _ngcontent-c4="" class="card-body">
							<div _ngcontent-c4="" class="progress">
								<div _ngcontent-c4="" aria-valuemax="100" aria-valuemin="0"
									aria-valuenow="25" class="progress-bar bg grey darken-2"
									role="progressbar" style="width: 25%"></div>
							</div>
							<p _ngcontent-c4="" class="card-text">Worse than last week
								(25%)</p>
						</div> -->
					</div>
				</div>
				<div _ngcontent-c4="" class="col-xl-4 col-md-6 mb-r">
					<div _ngcontent-c4=""
						class="card card-cascade cascading-admin-card">
						<div _ngcontent-c4="" class="admin-up">
							<i _ngcontent-c4="" class="fa fa-pie-chart light-blue lighten-1"></i>
							<div _ngcontent-c4="" class="data">
								<p _ngcontent-c4="">NON-COMPLIANCE COURSES</p>
								<h4 _ngcontent-c4="">
									<strong _ngcontent-c4="">150</strong>
								</h4>
							</div>
						</div>
						<!-- <div _ngcontent-c4="" class="card-body">
							<div _ngcontent-c4="" class="progress">
								<div _ngcontent-c4="" aria-valuemax="100" aria-valuemin="0"
									aria-valuenow="75" class="progress-bar grey darken-2"
									role="progressbar" style="width: 75%"></div>
							</div>
							<p _ngcontent-c4="" class="card-text">Worse than last week
								(75%)</p>
						</div> -->
					</div>
				</div>
				<!-- <div _ngcontent-c4="" class="col-xl-3 col-md-6 mb-r">
					<div _ngcontent-c4=""
						class="card card-cascade cascading-admin-card">
						<div _ngcontent-c4="" class="admin-up">
							<i _ngcontent-c4="" class="fa fa-bar-chart red accent-2"></i>
							<div _ngcontent-c4="" class="data">
								<p _ngcontent-c4="">ORGANIC TRAFFIC</p>
								<h4 _ngcontent-c4="">
									<strong _ngcontent-c4="">2000</strong>
								</h4>
							</div>
						</div>
						<div _ngcontent-c4="" class="card-body">
							<div _ngcontent-c4="" class="progress">
								<div _ngcontent-c4="" aria-valuemax="100" aria-valuemin="0"
									aria-valuenow="25" class="progress-bar bg-primary"
									role="progressbar" style="width: 25%"></div>
							</div>
							<p _ngcontent-c4="" class="card-text">Better than last week
								(25%)</p>
						</div>
					</div>
				</div> -->
			</div>
		</section>
		</app-stats-card>
		
		<app-stats-card _ngcontent-c3="" _nghost-c4="">
		<section _ngcontent-c4="" class="mt-lg-5">
			<div _ngcontent-c4="" class="row">
				<div _ngcontent-c4="" class="col-xl-4 col-md-6 mb-r">
					<div _ngcontent-c4=""
						class="card card-cascade cascading-admin-card">
						<div _ngcontent-c4="" class="admin-up">
							<i _ngcontent-c4="" class="fa fa-user primary-color"></i>
							<div _ngcontent-c4="" class="data">
								<p _ngcontent-c4="">COMPLIED USERS</p>
								<h4 _ngcontent-c4="">
									<strong _ngcontent-c4="">200</strong>
								</h4>
							</div>
						</div>
						<!-- <div _ngcontent-c4="" class="card-body">
							<div _ngcontent-c4="" class="progress">
								<div _ngcontent-c4="" aria-valuemax="100" aria-valuemin="0"
									aria-valuenow="25" class="progress-bar bg-primary"
									role="progressbar" style="width: 25%"></div>
							</div>
							<p _ngcontent-c4="" class="card-text">Better than last week
								(25%)</p>
						</div> -->
					</div>
				</div>
				<div _ngcontent-c4="" class="col-xl-4 col-md-6 mb-r">
					<div _ngcontent-c4=""
						class="card card-cascade cascading-admin-card">
						<div _ngcontent-c4="" class="admin-up">
							<i _ngcontent-c4="" class="fa fa-user warning-color"></i>
							<div _ngcontent-c4="" class="data">
								<p _ngcontent-c4="">NON-COMPLIED USERS</p>
								<h4 _ngcontent-c4="">
									<strong _ngcontent-c4="">200</strong>
								</h4>
							</div>
						</div>
						<!-- <div _ngcontent-c4="" class="card-body">
							<div _ngcontent-c4="" class="progress">
								<div _ngcontent-c4="" aria-valuemax="100" aria-valuemin="0"
									aria-valuenow="25" class="progress-bar bg grey darken-2"
									role="progressbar" style="width: 25%"></div>
							</div>
							<p _ngcontent-c4="" class="card-text">Worse than last week
								(25%)</p>
						</div> -->
					</div>
				</div>
				<!-- <div _ngcontent-c4="" class="col-xl-4 col-md-6 mb-r">
					<div _ngcontent-c4=""
						class="card card-cascade cascading-admin-card">
						<div _ngcontent-c4="" class="admin-up">
							<i _ngcontent-c4="" class="fa fa-pie-chart light-blue lighten-1"></i>
							<div _ngcontent-c4="" class="data">
								<p _ngcontent-c4="">NON-COMPLIANCE COURSES</p>
								<h4 _ngcontent-c4="">
									<strong _ngcontent-c4="">150</strong>
								</h4>
							</div>
						</div>
						<div _ngcontent-c4="" class="card-body">
							<div _ngcontent-c4="" class="progress">
								<div _ngcontent-c4="" aria-valuemax="100" aria-valuemin="0"
									aria-valuenow="75" class="progress-bar grey darken-2"
									role="progressbar" style="width: 75%"></div>
							</div>
							<p _ngcontent-c4="" class="card-text">Worse than last week
								(75%)</p>
						</div>
					</div>
				</div> -->
				
			</div>
		</section>
		</app-stats-card>
		
		<!-- <div id="chartContainer" style="height: 370px; width: 100%;"></div> -->
		
		<app-stats-card2 _ngcontent-c3="" _nghost-c6="">
		<section _ngcontent-c4="" class="mt-lg-5">
		  <div id="chartContainer" style="height: 370px; width: 100%;"></div>
		  </section>
		</app-stats-card2>
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
