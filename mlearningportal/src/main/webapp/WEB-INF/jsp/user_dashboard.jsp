<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<style>
.scrollbar
{
	
	float: left;
	height: 100px;
	width: 100%;
	background: #fff;
	overflow-y: scroll;
	margin-bottom: 25px;
}

.force-overflow
{
	min-height: 200px;
}

#style-4::-webkit-scrollbar-track
{
	-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
	background-color: #F5F5F5;
}

#style-4::-webkit-scrollbar
{
	width: 5px;
	background-color: #F5F5F5;
}

#style-4::-webkit-scrollbar-thumb
{
	background-color: #000000;
	border: 2px solid #555555;
}
</style>


</head>


<body>

	<%@include file="header.jsp"%>

	<%@include file="sidenev.jsp"%>
	
	
	
 <script type="text/javascript">
window.onload = function() {
	
var dps = [[]];
var chart = new CanvasJS.Chart("chartContainer", {
	exportEnabled: true,
	animationEnabled: true,
	theme: "light2", // "light1", "dark1", "dark2"
	title: {
		text: ""
	},
	subtitles: [{
		text: ""
	}],
	data: [{
		type: "pie",
		yValueFormatString: "#,##0\"%\"",
		indexLabel: "{label} - {y}",
		dataPoints: dps[0]
	}]
});
 
var yValue;
var label;
 
<c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">
	<c:forEach items="${dataPoints}" var="dataPoint">
		yValue = parseFloat("${dataPoint.y}");
		label = "${dataPoint.label}";
		dps[parseInt("${loop.index}")].push({
			label : label,
			y : yValue,
		});
	</c:forEach>
</c:forEach>
 
chart.render();
 
}
</script>
	
 <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
 
	<main _ngcontent-c0="" class="pt-5 mx-lg-5"
		ng-reflect-klass="pt-5 mx-lg-5" ng-reflect-ng-class="[object Object]">
	<div _ngcontent-c0="" class="mt-5">
		<router-outlet _ngcontent-c0=""></router-outlet>
		<app-dashboard1 _nghost-c3="">
		 <section _ngcontent-c4="" >
			<div _ngcontent-c4="" class="row">
					<div _ngcontent-c4="" class="col-xl-3 col-lg-3 col-md-3 col-sm-6 m-b-3">
					<div _ngcontent-c4=""
						class="card card-cascade cascading-admin-card">
						<div _ngcontent-c4="" class="admin-up">
							<i _ngcontent-c4="" class="fa fa-user primary-color" style="background:#63b4ed !important;"></i>
							<div _ngcontent-c4="" class="data">
								<p _ngcontent-c4="">Total Modules</p>
								<h4 _ngcontent-c4="">
									<strong _ngcontent-c4="">${userDashboardLogs.totalCourseModeulesInDB}</strong>
								</h4>
							</div>
						</div>	
					</div>
				</div>
				<div _ngcontent-c4="" class="col-xl-3 col-lg-3 col-md-3 col-sm-6 m-b-3">
					<div _ngcontent-c4=""
						class="card card-cascade cascading-admin-card">
						<div _ngcontent-c4="" class="admin-up">
							<i _ngcontent-c4="" class="fa fa-user primary-color"  style="background:#45d75e  !important;"></i>
							<div _ngcontent-c4="" class="data">
								<p _ngcontent-c4="">Total Assigned Modules </p>
								<h4 _ngcontent-c4="">
									<strong _ngcontent-c4="">${userDashboardLogs.totalCourseModeulesAssignedToUser}</strong>
								</h4>
							</div>
						</div>	
					</div>
				</div>
				<div _ngcontent-c4="" class="col-xl-3 col-lg-3 col-md-3 col-sm-6 m-b-3">
					<div _ngcontent-c4=""
						class="card card-cascade cascading-admin-card">
						<div _ngcontent-c4="" class="admin-up">
							<i _ngcontent-c4="" class="fa fa-user primary-color"  style="background:#f4de42 !important;"></i>
							<div _ngcontent-c4="" class="data">
								<p _ngcontent-c4="">Total Modules Completed</p>
								<h4 _ngcontent-c4="">
									<strong _ngcontent-c4="">${userDashboardLogs.totalCompleteModulesNonComplianceCount+userDashboardLogs.totalCompleteModulesComplianceCount }</strong>
								</h4>
							</div>
						</div>	
					</div>
				</div>
				<div _ngcontent-c4="" class="col-xl-3 col-lg-3 col-md-3 col-sm-6 m-b-3">
					<div _ngcontent-c4=""
						class="card card-cascade cascading-admin-card">
						<div _ngcontent-c4="" class="admin-up">
							<i _ngcontent-c4="" class="fa fa-user warning-color"  style="background:#ff9109 !important;"></i>
							<div _ngcontent-c4="" class="data">
								<p _ngcontent-c4="">Total Modules Pending</p>
								<h4 _ngcontent-c4="">
									<strong _ngcontent-c4="">${userDashboardLogs.totalCourseModulePendingCompliance+userDashboardLogs.totalCourseModulePendingNonCompliance }</strong>
								</h4>
							</div>
						</div>	
					</div>
				</div>
				
			</div>
		</section>
		
		
		 <section _ngcontent-c4="" class="mt-lg-5">
<div class="row">
	<div class="col-lg-6 m-b-3" style="margin-top:50px;">
                	<div class="box box-widget widget-user-2">
                    	<div class="widget-user-header bg-yellow bg-color ">
                      		<div class="widget-user-image"> <img class="img-circle" src="<%=loginUser.getProfileImagePath()%>"/> </div>
                          	<h3 class="widget-user-username"><c:out value = "${fn:toUpperCase(loginUser.getFirstName())}"/> <c:out value = "${fn:toUpperCase(loginUser.getLastName())}"/></h3>
                          <%-- 	<h5 class="widget-user-desc"><%=loginUser.getDesignationId().getName()%></h5> --%>
                          	
                    	</div>
                    		
                        <div class="box-footer no-padding">
                        	<ul class="nav nav-stacked">
                         
                                <li data-toggle="collapse" data-target="#demo1"><a >Total Incomplete Modules (left in-between)  <span class="pull-right badge bg-yellow">${userDashboardLogs.totalIncompleteModulesNonComplianceCount+userDashboardLogs.totalIncompleteModulesComplianceCount }</span></a>
                                  <div id="${(userDashboardLogs.totalIncompleteModulesNonComplianceCount+userDashboardLogs.totalIncompleteModulesComplianceCount)>0?'demo1':'' }" class="collapse" style="padding: 20px">
								   <ul class="dashboar">
								  
								   	<c:forEach items="${userDashboardLogs.totalIncompleteModulesCompliance}" var="val">
								  <a href="user_modules_detail?courseModuleId=${val.id}" class="btn-loaderClass"> <li>${val.name }</li></a>
								   </c:forEach>
								    <c:forEach items="${userDashboardLogs.totalIncompleteModulesNonCompliance}" var="val">
								   <a href="user_modules_detail?courseModuleId=${val.id}" class="btn-loaderClass"><li>${val.name }</li></a>
								   </c:forEach>
								   </ul>
								  </div>
                                </li>
                                <li data-toggle="collapse" data-target="#demo2"><a>Total Complete Modules (Completed)  <span class="pull-right badge bg-green">${userDashboardLogs.totalCompleteModulesNonComplianceCount+userDashboardLogs.totalCompleteModulesComplianceCount }</span></a>
                                 <div id="${(userDashboardLogs.totalCompleteModulesNonComplianceCount+userDashboardLogs.totalCompleteModulesComplianceCount)>0?'demo2':'' }" class="collapse" style="padding: 20px">
								    <ul class="dashboar">								   
								   	<c:forEach items="${userDashboardLogs.totalCompleteModulesCompliance}" var="val">
								  <a href="user_modules_detail?courseModuleId=${val.id}" class="btn-loaderClass"> <li>${val.name }</li></a>
								   </c:forEach>
								   	<c:forEach items="${userDashboardLogs.totalCompleteModulesNonCompliance}" var="val">
								  <a href="user_modules_detail?courseModuleId=${val.id}" class="btn-loaderClass"> <li>${val.name }</li></a>
								   </c:forEach>
								   </ul>
								  </div>
								  </li>
                                <li data-toggle="collapse" data-target="#demo3"><a >Total Modules passed <span class="pull-right badge bg-green">${userDashboardLogs.totalPassNonComplianceCount+userDashboardLogs.totalPassComplianceCount }</span></a>
                                 <div id="${(userDashboardLogs.totalPassNonComplianceCount+userDashboardLogs.totalPassComplianceCount)>0?'demo3':'' }" class="collapse" style="padding: 20px">
								   <ul class="dashboar">								   
								   	<c:forEach items="${userDashboardLogs.totalPassCompliance}" var="val">
								  <a href="user_modules_detail?courseModuleId=${val.id}" class="btn-loaderClass"> <li>${val.name }</li></a>
								   </c:forEach>
								   	<c:forEach items="${userDashboardLogs.totalPassNonCompliance}" var="val">
								  <a href="user_modules_detail?courseModuleId=${val.id}" class="btn-loaderClass"> <li>${val.name }</li></a>
								   </c:forEach>
								   </ul>
								  </div>
								  </li>
                                <li data-toggle="collapse" data-target="#demo4"><a >Total Modules Failed <span class="pull-right badge bg-red">${userDashboardLogs.totalFailNonComplianceCount+userDashboardLogs.totalFailComplianceCount }</span></a>
                                 <div id="${(userDashboardLogs.totalFailNonComplianceCount+userDashboardLogs.totalFailComplianceCount)>0?'demo4':'' }" class="collapse" style="padding: 20px">
								    <ul class="dashboar">								   
								   	<c:forEach items="${userDashboardLogs.totalFailCompliance}" var="val">
								<a href="user_modules_detail?courseModuleId=${val.id}" class="btn-loaderClass">   <li>${val.name }</li></a>
								   </c:forEach>
								   	<c:forEach items="${userDashboardLogs.totalFailNonCompliance}" var="val">
								 <a href="user_modules_detail?courseModuleId=${val.id}" class="btn-loaderClass">  <li>${val.name }</li></a>
								   </c:forEach>
								   </ul>
								  </div>
								  </li>
                                <li data-toggle="collapse" data-target="#demo5"><a >Total Modules pending (compliance) <span class="pull-right badge bg-red">${userDashboardLogs.totalCourseModulePendingCompliance }</span></a>
                                 <div id="${(userDashboardLogs.totalCourseModulePendingCompliance)>0?'demo5':'' }" class="collapse" style="padding: 20px">
								    <ul class="dashboar">								   
								   	<c:forEach items="${userDashboardLogs.courseModulePendingCompliance}" var="val">
								<a href="user_modules_detail?courseModuleId=${val.id}" class="btn-loaderClass">   <li>${val.name }</li></a>
								   </c:forEach>
								   	
								   </ul>
								  </div>
								  </li>
                                  <li data-toggle="collapse" data-target="#demo6"><a >Total Modules pending (non-compliance) <span class="pull-right badge bg-red">${userDashboardLogs.totalCourseModulePendingNonCompliance }</span></a>
                                   <div id="${(userDashboardLogs.totalCourseModulePendingNonCompliance)>0?'demo6':'' }" class="collapse" style="padding: 20px">
								   <ul class="dashboar">								   
								   	<c:forEach items="${userDashboardLogs.courseModulePendingNonCompliance}" var="val">
								 <a href="user_modules_detail?courseModuleId=${val.id}" class="btn-loaderClass">  <li>${val.name }</li></a>
								   </c:forEach>
								   	
								   </ul>
								  </div>
								  </li>
                               
                                <li>&nbsp;</li>
                          	</ul>
                        </div>
                  	</div>
                </div>
                
                <div class="col-lg-6 m-b-3" style="margin-top:50px;">
                 <div class="info-box box box-widget widget-user-2">
            <div class="col-12 bg-color ">
              <div class="d-flex flex-wrap">
                <div>
                  <h5 class="m-b-15">Statistics</h5>
                </div>
              </div>
            </div>
        <!-- <div class="row" align="center">
            <div class="col-lg-6 m-auto m-top-40 m-bot-18">
             <div id="donut"><svg height="340" version="1.1" width="336" xmlns="http://www.w3.org/2000/svg" style="overflow: hidden; position: relative; left: -0.1px; top: -0.6px;">
             <desc style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Created with Raphaël 2.1.2</desc>
             <defs style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></defs>
             <path fill="none" stroke="#ff4558" d="M168.219,279.6793333333333A105.47933333333333,105.47933333333333,0,0,0,170.73532991339826,68.75068589975854" stroke-width="2" opacity="0" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); opacity: 0;"></path><path fill="#ff4558" stroke="#ffffff" d="M168.219,282.6793333333333A108.47933333333333,108.47933333333333,0,0,0,170.80689833824164,65.75153969449187L171.87421416202514,21.024605858415583A153.219,153.219,0,0,1,168.219,327.419Z" stroke-width="3" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path><path fill="none" stroke="#ff7d4d" d="M170.73532991339826,68.75068589975854A105.47933333333333,105.47933333333333,0,0,0,69.94291620395813,212.510587494292" stroke-width="2" opacity="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); opacity: 1;"></path><path fill="#ff7d4d" stroke="#ffffff" d="M170.80689833824164,65.75153969449187A108.47933333333333,108.47933333333333,0,0,0,67.14778807723386,213.60020153384676L20.804874305937204,231.665881241438A158.219,158.219,0,0,1,171.9934948700974,16.02602884963781Z" stroke-width="3" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path><path fill="none" stroke="#00a5a8" d="M69.94291620395813,212.510587494292A105.47933333333333,105.47933333333333,0,0,0,168.18586269067464,279.6793281281369" stroke-width="2" opacity="0" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); opacity: 0;"></path><path fill="#00a5a8" stroke="#ffffff" d="M67.14778807723386,213.60020153384676A108.47933333333333,108.47933333333333,0,0,0,168.18492021289407,282.6793279800928L168.1708648323129,327.41899243894545A153.219,153.219,0,0,1,25.463421183810993,229.84985784218006Z" stroke-width="3" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
             <text x="168.219" y="164.2" text-anchor="middle" font="10px &quot;Arial&quot;" stroke="none" fill="#404e67" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font: 800 15px Arial;" font-size="15px" font-weight="800" transform="matrix(1.3983,0,0,1.3983,-67.0885,-69.7023)" stroke-width="0.7151545747771549">
             <tspan dy="6.012499999999989" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); font-size:11px;">Learning Points gained </tspan>
             </text>
             <text x="168.219" y="184.2" text-anchor="middle" font="10px &quot;Arial&quot;" stroke="none" fill="#404e67" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font: 14px Arial;" font-size="14px" transform="matrix(2.1975,0,0,2.1975,-201.6361,-211.0008)" stroke-width="0.4550654472598108"><tspan dy="4.809374999999989" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">359</tspan></text>
             </svg>
             
             
             </div>
             </div> 
               <div class="col-lg-6 m-auto m-top-40 m-bot-18" align="center">
              <div class="col-xl-4">
                <h6 class="f-14">Attempted Course</h6>
                <h4 style="font-size:18px;">21</h4>
              </div>
              <div class="col-xl-4 ">
                <h6 class="f-14">Completed courses</h6>
                <h4 style="font-size:18px;">17</h4>
              </div>
              <div class="col-xl-4">
                <h6 class="f-14">Pending Courses</h6>
                <h4 style="font-size:18px;">12</h4>
              </div>
            </div>
             </div> -->
                 <div id="chartContainer" style="height: 305px; width: 100%;"></div>  
          </div>
         
                </div>
			</div>
		</section>
	
 <section _ngcontent-c4="" class="mt-lg-5">
		
	<div class="row">
	
	
	 <c:forEach items="${Compliance}" var="sl">
                 <div class="col-md-4 col-sm-6 m-b-3">
                    <div class="pricingTable">
                        <img src="${sl.courseImagePath}" class="img-responsive" width="50%"/>
                        <div class="pricing-content">
                            <ul>
                                <li>${sl.name} </li>
                                <li> 
                                <div class="scrollbar" id="style-4">
                                 <div class="force-overflow">
		                       <p>${sl.shortDesc}</p>
		                       </div>
	                         </div>
                                
                                </li>
                            </ul>
                            <a href="user_modules?courseId=${sl.id}" class="pricingTable-signup btn-loaderClass">Read More...</a>
                        </div>
                    </div>
                </div>
                            </c:forEach>
	      
	
                 <%--  <c:forEach items="${NonCompliance}" var="sl">
                             <div class="col-md-4 col-sm-6 m-b-3">
                    <div class="pricingTable">
                        <img src="${sl.courseImagePath}" class="img-responsive" width="50%"/>
                        <div class="pricing-content">
                            <ul>
                                <li>${sl.name} </li>
                                <li> 
                                <div class="scrollbar" id="style-4">
                                 <div class="force-overflow">
		                       <p>${sl.shortDesc}</p>
		                       </div>
	                         </div>
                                
                                </li>
                            </ul>
                            <a href="user_modules?courseId=${sl.id}" class="pricingTable-signup">Read More...</a>
                        </div>
                    </div>
                </div>
                            </c:forEach> --%>
                           <%--  <c:forEach items="${Others}" var="sl">
                              <div class="col-md-4 col-sm-6 m-b-3">
                    <div class="pricingTable">
                        <img src="${sl.courseImagePath}" class="img-responsive" width="50%"/>
                        <div class="pricing-content">
                            <ul>
                                <li>${sl.name} </li>
                                <li> 
                                <div class="scrollbar" id="style-4">
                                 <div class="force-overflow">
		                       <p>${sl.shortDesc}</p>
		                       </div>
	                         </div>
                                
                                </li>
                            </ul>
                            <a href="user_modules?courseId=${sl.id}" class="pricingTable-signup">Read More...</a>
                        </div>
                    </div>
                </div>
                            </c:forEach> --%>
               
	</div>
    </section>  		
		
		
		
		
		
		
		
		</app-dashboard1>
	</div>
	</main>

<%@include file="footer.jsp"%>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>

</html>
