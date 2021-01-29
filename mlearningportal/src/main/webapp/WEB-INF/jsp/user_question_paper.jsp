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
</head>


<body style="height: 100%; width: 100%;">

	<div style="display: none !important"><%@include
			file="header.jsp"%></div>
	<div style="display: none !important"><%@include
			file="sidenev.jsp"%></div>

	<main _ngcontent-c0="" class="pt-5 mx-lg-5"
		style="padding:0px !important" ng-reflect-klass="pt-5 mx-lg-5"
		ng-reflect-ng-class="[object Object]">
	<div _ngcontent-c0="" class="mt-5">
		<router-outlet _ngcontent-c0=""></router-outlet>
		<app-dashboard1 _nghost-c3="">
		<section _ngcontent-c4="">


			<div class="row">
				<div class="col-sm-12">
					<div class="modal-content">
						<div class=" text-center"
							style="padding: 10px; background-color: #046076;">
							<h4 class="modal-title text-white">Assessment</h4>

							<!--         		<button type="button" class="close" data-dismiss="modal">&times;</button> -->
						</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-md-12">
									<div class="total-marks">
										<strong>Total Assessment Question :
											${courseContent.totalAssessmentQuestion }</strong>
									</div>
									<c:set var="count" value="0" scope="page" />

									<form:form method="post" action="user_question_paper_save"
										modelAttribute="questionListAccess" id="myForm">
										
												
										<input type="hidden" name="startTime"
											value="${questionListAccess.startTime}" />

										<%--   <input type="hidden" name="totalNoOfQuestion" value="${questionListAccess.totalNoOfQuestion}"/> --%>


										<c:forEach items="${questionListAccess.questions}" var="sl"
											varStatus="status">
											<input type="hidden" name="questions[${status.index}].id"
												value="${sl.id}" />
											<input type="hidden" name="courseModuleId"
												value="${sl.courseModuleId.id}" />
											<div class="row margin-b-10">
												<div class="col-md-12">
													<div class="card">
														<div class="card-header gradient-bg text-white">
															Question# ${status.count} - ${sl.question }:</div>
														<div class="row">
															<div class="col-sm-6">
																<div class="card-body">

																	<div class="custom-control custom-radio form-group">
																		<strong>A.</strong> <input type="radio"
																			class="custom-control-input"
																			id="defaultGroupExample${count+1}"
																			name="questions[${status.index}].answer"
																			value="option1"> <label
																			class="custom-control-label"
																			for="defaultGroupExample${count+1}">${sl.option1 }</label>
																	</div>
																	<div class="custom-control custom-radio form-group">
																		<strong>B.</strong> <input type="radio"
																			class="custom-control-input"
																			id="defaultGroupExample${count+2}"
																			name="questions[${status.index}].answer"
																			value="option2"> <label
																			class="custom-control-label"
																			for="defaultGroupExample${count+2}">${sl.option2 }</label>
																	</div>
																</div>
															</div>

															<div class="col-sm-6">
																<div class="card-body">
																	<div class="custom-control custom-radio form-group">
																		<strong>C.</strong> <input type="radio"
																			class="custom-control-input"
																			id="defaultGroupExample${count+3}"
																			name="questions[${status.index}].answer"
																			value="option3"> <label
																			class="custom-control-label"
																			for="defaultGroupExample${count+3}">${sl.option3 }</label>
																	</div>
																	<div class="custom-control custom-radio form-group">
																		<strong>D.</strong> <input type="radio"
																			class="custom-control-input"
																			id="defaultGroupExample${count+4}"
																			name="questions[${status.index}].answer"
																			value="option4"> <label
																			class="custom-control-label"
																			for="defaultGroupExample${count+4}">${sl.option4 }</label>
																	</div>
																	<c:set var="count" value="${count + 4}" scope="page" />
																	<%--   <div class="form-group">
                                          <div class="custom-control custom-radio"><strong>A.</strong>
                                                <input type="radio" class="custom-control-input" name="questions[${status.index}].answer" value="option1"/>
                                                <label class="custom-control-label">${sl.option1 }</label>
                                           </div>
                                      </div> 
                                
                                     <div class="form-group">
                                         <div class="checkbox"><strong>B.</strong>
                                              <input type="radio" name="questions[${status.index}].answer" value="option2" />
                                                <label class="checkbox-label">${sl.option2 }</label>
                                           </div>
                                      </div> 
                                    </div>
                                    </div>
                                    <div class="col-sm-6">
                                    <div class="card-body">
                                    <div class="form-group">
                                          <div class="radio"><strong>C.</strong>
                                              <input type="radio" name="questions[${status.index}].answer"  value="option3"/>
                                                <label class="checkbox-label">${sl.option3 }</label>
                                           </div>
                                      </div> 
                                
                                     <div class="form-group">
                                         <div class="radio"><strong>D.</strong>
                                              <input type="radio" name="questions[${status.index}].answer" value="option4" />
                                                <label class="checkbox-label">${sl.option4 }</label>
                                           </div>
                                      </div>  --%>
																</div>
															</div>
														</div>
														<div class="card-footer"
															style="background-color: #046076;"></div>
													</div>
												</div>
											</div>
										</c:forEach>





										<div class="modal-footer">
											<!-- 	<button type="submit" class="btn btn-success" data-dismiss="modal">Save</button> -->
											<button type="button"
												class="cancelConfirmPaperCancel action text-capitalize cancel my-btn btn-success btn-loaderClass">Cancel</button>
											<button type="button" id="btn-signup"
												class="cancelConfirmPaperSubmit action text-capitalize cancel my-btn btn-success btn-loaderClass">Submit</button>
										</div>

									</form:form>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>


		</section>

		</app-dashboard1>
	</div>
	</main>

	<%@include file="footer.jsp"%>



<div class="modal showLoaderClass" style="display: none">
		<div class="center">
			<img alt="" src="images/loader.gif" width="150px" height="150px" />
		</div>
	</div>



</body>

</html>
