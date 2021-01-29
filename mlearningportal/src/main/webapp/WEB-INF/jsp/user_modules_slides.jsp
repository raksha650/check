<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<style>
.showLoaderClass {
   background-color: rgba(0,0,0,.2);
   color: #666666;
   position: fixed;
   height: 100%;
   width: 100%;
   z-index: 5000;
   top: 0;
   left:0;
   float: left;
   padding-top: 20%;
   text-align: center;
        
}
</style>
<style>
.timer, .timer-done, .timer-loop {
	font-size: 16px;
	color: #fff;
	font-weight: bold;
	float: right;
	padding-right: 10px;
}
/*.timer:before
  {
	  content:'Remaining Time :';
	  float:left;
	  margin-right:8px;
  }*/
.jst-hours {
	float: left;
}

.jst-minutes {
	float: left;
}

.jst-seconds {
	float: left;
}

.jst-clearDiv {
	clear: both;
}

.jst-timeout {
	color: red;
}

.time-remainig {
	font-size: 16px;
	color: #fff;;
	font-weight: bold;
	float: right;
	padding-right: 10px;
}
</style>


</head>


<body style="height: 100%; width: 100%; overflow-y: hidden;">

	<div style="display: none !important"><%@include
			file="header.jsp"%></div>
	<div style="display: none !important"><%@include
			file="sidenev.jsp"%></div>

	<main _ngcontent-c0="" class="" style="padding:0px !important"
		ng-reflect-klass="pt-5 mx-lg-5" ng-reflect-ng-class="[object Object]">
	<div _ngcontent-c0="" class="mt-5">
		<router-outlet _ngcontent-c0=""></router-outlet>
		<app-dashboard1 _nghost-c3="">
		<section _ngcontent-c4="">



			<audio controls id="music" style="display: none;"
				onended="audioEnded()"></audio>
			<div class="content-wrapper">
				<div class="content">
					<!--<div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            This is some text within a card block.
                        </div>
                    </div>
                </div>
            </div>-->
					<div class="row">
						<div class="col-md-12">
							<!--<div class="timer-quick" data-seconds-left="300"></div>-->
							<form id="myForm" class="form-horizontal form" action=""
								method="post">
								<div class="exam-box">
									<div class="exam-bg">
										<div>

											<input type="hidden" name="isCourseFinished"
												id="isCourseFinished" value="true"> <input
												type="hidden" name="startTime" value="${startTime }">
											<input type="hidden" name="courseModuleId"
												value="${courseModuleId }">
											<div id="hidediv">
												<!--<div class="top-heading">&nbsp;</div>-->
												<div align="center">
													<img src="user/user/images/cinfy.gif" class="img-size-logo" />
												</div>
											</div>
											<!--<div class="timer-quick" data-seconds-left="120" style="color:#fff;"></div>-->
											<span class="timer pull-right" data-seconds-left=120></span>
											<span id="time-remaining" class="pull-right time-remainig"></span>
											<div id="show" style="display: none;">
												<c:set var="count" value="0" scope="page" />
												<c:forEach items="${courseUploads}" var="sl">
													<c:set var="count" value="${count + 1}" scope="page" />
													<c:if test="${not sl.isImgOrVideo}">
														<div class="step">
															<div class="top-heading">
																<c:out value="${count}" />
															</div>
															<div class="slide-img">
																<img src="${sl.slidePath}" class="img-size" />
															</div>
															<div class="walk">



																<%--   <c:if test="${not empty sl.audioPath}">
                                            <img src="user/user/images/meena_talking.gif" class="walk-img-size" />
                                            </c:if>
                                             <c:if test="${empty sl.audioPath}">
                                            <img src="user/user/images/meena-smile.gif" class="walk-img-size" />
                                            </c:if>  --%>
															</div>
														</div>
													</c:if>

													<c:if test="${sl.isImgOrVideo}">
														<div class="step">
															<div class="top-heading">
																<c:out value="${count}" />
															</div>
															<div>

																<video id="video_width" controls 
																	poster="user/user/images/cinfy.gif"
																	class="img-size-480 videoClasses">
																	<source src="${sl.slidePath}" type="video/mp4">
																</video>

															</div>
														</div>
													</c:if>
												</c:forEach>
												<img src="user/user/images/meena_talking.gif"
													class="walk-img-size meenaDisplay" id="meenaaa" />
												<!--  <div class="step" >
                                        <div class="top-heading">Slide 22</div>
                                        <div>
                                           
                                                <video id="video-width" controls poster="user/user/video/cinfy-poster.jpg">
                                                    <source src="user/user/video/email-video-2.mp4" type="video/mp4">
                                                </video>
                                            
                                        </div>
                                    </div> -->

											</div>


										</div>


									</div>

								</div>
								<div class="bottom-btn-div1">
									<div id="hidestart" class="start-btn" align="center">
										<button type="button" class="my-btn btn-success  btn-loaderClass" id="start">Start</button>
									</div>

									<div id="show1" style="display: none;">
										<div class="next-btn-div">
											<button type="button"
												class="action text-capitalize back my-btn btn-success"
												onclick="pauseVid()">Back</button>
											<button type="button"
												class="action text-capitalize my-btn btn-success playFile">Pause</button>
											<button type="button"
												class="action text-capitalize next my-btn btn-success"
												onclick="pauseVid()" id="btnNext">Next</button>

											<button type="button"
												class="cancelConfirm1 action text-capitalize cancel my-btn btn-success  btn-loaderClass">Cancel</button>


											<button type="button"
												class="cancelConfirmAssessment action text-capitalize exit my-btn btn-success btn-loaderClass">Assessment</button>
											<!-- <button type="submit" class="cancelConfirmFinishReading action text-capitalize finishReading my-btn btn-success" onclick="submitFinishReading()" >Finish</button> -->
											<button type="button"
												class="cancelConfirmFinishReading finishReading action text-capitalize my-btn btn-success btn-loaderClass">Finish</button>
										</div>
										<div class="progress-div">
											<div class="progress margin-lr-15" id="show2"
												style="display: none;">
												<div
													class="progress-bar progress-bar-success progress-bar-striped active progress-bar-animated"
													role="progressbar" aria-valuemin="0" aria-valuemax="100">
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
	</div>

	<!--  Modal Start  -->



	<div class="modal" id="certificate">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header green-bg">
					<h4 class="modal-title text-white">Congratulations you
						complete certificate</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div align="center">
								<img src="user/user/images/certificate.jpg"
									class="img-responsive mx-auto" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--  Modal End  --> </main>

	<%@include file="footer.jsp"%>

	<script src="<c:url value="/user/user/dist/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/user/user/js/jquery.validate.js"/>"></script>
	<script
		src="<c:url value="/user/user/dist/bootstrap/js/bootstrap.min.js"/>"></script>

	<script type="text/javascript">
		

		function pauseVid() {

			var vid = document.getElementsByClassName("videoClasses");
			
			for(var i=0; i<vid.length; i++) {
				vid[i].pause();
			}
			
		}

		$(document)
				.ready(
						function() {
							var isPlaying = false;

							var courseUploads = JSON.parse('${courseUploadJson}');
							$('#cancel_btn').click(function() {
								location.reload(true);
							});

							/* $("#slide17").on('show').trigger('displayShow'); */

							var current = 1;

							widget = $(".step");
							btnnext = $(".next");
							btnback = $(".back");
							btnsubmit = $(".submit");
							btnstart = $(".start");
							btndownload = $(".download");
							btncancel = $(".cancel");
							btntakecourse = $(".takecourse");
							btnexit = $(".exit");
							btnFinishReading = $(".finishReading");
							playFile = $(".playFile");
							pauseFile = $(".pauseFile");

							// Init buttons and UI
							widget.not(':eq(0)').hide();
							hideButtons(current);
							setProgress(current);

							//play pause audio
							var audio1 = document.getElementById('music');

							playFile
									.click(function() {

										if (isPlaying) {
											audio1.play();
											isPlaying = false;

											document.getElementById('meenaaa').src = 'user/user/images/meena_talking.gif';

											playFile.text("Pause");

										} else {
											audio1.pause();
											isPlaying = true;
											document.getElementById('meenaaa').src = 'user/user/images/meena_smile.gif';

											playFile.text("Play");
										}

									});

							// Next button click action
							btnnext
									.click(function() {

										document.getElementById('meenaaa').src = 'user/user/images/meena_talking.gif';
										isPlaying = false;
										playFile.text("Pause");

										if (current < widget.length) {
											if ($(".form").valid()) {
												widget.show();
												widget.not(
														':eq(' + (current++)
																+ ')').hide();
												setProgress(current);
											}

											if (current == courseUploads.length) {
												hideButtons(current);
											}
											
										}

										hideButtons(current);

									})

							// Back button click action
							btnback.click(function() {

								if (current > 1) {

									current = current - 2;
									if (current < widget.length) {
										widget.show();
										widget.not(':eq(' + (current++) + ')')
												.hide();
										setProgress(current);
									}

								}
								if (current == courseUploads[0].srno) {
									audio1.src = courseUploads[0].audioPath;
									audio1.play();

								}
								
								hideButtons(current);

							})

						});

		// Change progress bar action
		setProgress = function(currstep) {
			var percent = parseFloat((100 / widget.length) * (currstep));
			percent = percent.toFixed();
			$(".progress-bar").css("width", percent + "%").html(percent + "%");
		}

		// meena img change
		var audioENd = document.getElementById('music');
		audioENd.onended = function() {

			document.getElementById('meenaaa').src = 'user/user/images/meena_smile.gif';
		}

		// Hide buttons according to the current step
		hideButtons = function(current) {
			var limit = parseInt(widget.length);

			$(".action").hide();

			if (current < limit)
				btnnext.show(), btncancel.show();
			if (current > 1)
				btnback.show();
			if (current) {
				playFile.show();
				pauseFile.show();
			}
			var audio1 = document.getElementById('music');

			var courseUploads = JSON.parse('${courseUploadJson}');

			$('#start').click(function() {

				if (courseUploads.length > 0) {

					if (current == courseUploads[0].srno) {
						audio1.src = courseUploads[0].audioPath;
						audio1.play();
						 $('.showLoaderClass').hide();

					}
					
				}

			})

			for (var i = 0; i < courseUploads.length; i++) {
				if (i > 0) {
					if (current == courseUploads[i].srno) {
						 audio1.src = courseUploads[i].audioPath;
						audio1.play(); 

						if (courseUploads[i].isImgOrVideo) {
							
							document.getElementById("meenaaa").style.display = "none";
						} else {
							document.getElementById("meenaaa").style.display = "block";
						}
					}
				}

			}

			

			if (current == limit) {
				// Show entered values
				$(".display label:not(.control-label)").each(
						function() {
							console.log($(this).find(
									"label:not(.control-label)").html(
									$("#" + $(this).data("id")).val()));
						});
				btnnext.hide();
				btnsubmit.hide();
				btnstart.show();
				btnexit.show();
				btnFinishReading.show();
				btncancel.hide();
				btnback.show();
				btntakecourse.show();
			}

			function getdate() {

				var today = new Date();
				var h = today.getHours();
				var m = today.getMinutes();
				var s = today.getSeconds();
				if (s < 10) {
					s = "0" + s;
				}

				$("h1").text(h + " : " + m + " : " + s);
				setTimeout(function() {
					getdate()
				}, 500);
			}

		}
	</script>

	<script src="<c:url value="/user/user/dist/js/kit.js"/>"></script>
	<script
		src="<c:url value="/user/user/dist/plugins/raphael/raphael-min.js"/>"></script>
	<script src="<c:url value="/user/user/dist/plugins/morris/morris.js"/>"></script>
	<script
		src="<c:url value="/user/user/dist/plugins/functions/dashboard1.js"/>"></script>
	<script
		src="<c:url value="/user/user/dist/plugins/peity/jquery.peity.min.js"/>"></script>
	<script
		src="<c:url value="/user/user/dist/plugins/functions/jquery.peity.init.js"/>"></script>

	<script>
		$(document).ready(function() {
			$("button").click(function() {
				$("#show").fadeIn("slow");
				$("#show1").fadeIn(0);
				$("#show2").fadeIn(0);
				$("#hidediv").fadeOut(0);
				$("#hidestart").fadeOut(0);
			});
		});
	</script>


	<script src="<c:url value="/user/user/js/jquery.simple.timer.js"/>"></script>
	<script
		src="<c:url value="/user/user/jquery.confirm/jquery.confirm.js"/>"></script>
	<script src="<c:url value="/user/user/js/script.js"/>"></script>
	
<div class="modal showLoaderClass" style="display: none">
		<div class="center">
			<img alt="" src="images/loader.gif" width="150px" height="150px" />
		</div>
	</div>

</body>


</html>
