<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Course Master</title>
<meta charset="utf-8">
<style>
</style>
</head>
<body>

<%@include file="header.jsp"%>
<%@include file="sidenev.jsp"%>
<main>
<script>
function checkCourseExist(val) {
	var courseCategoryCommonId = document.getElementById("courseCategoryCommonId").value;
	if(courseCategoryCommonId != 0){
		$.ajax({		
			url : 'existCourse?courseCategoryCommonId='+courseCategoryCommonId+'&course='+val,
			success : function(response) {
				if (response == true) {
					document.getElementById("isCourseExist").style.color = "#ff0000";
					$("#isCourseExist").text("Course Already Exist !");
	
				} else {
					$("#isCourseExist").text("");
				}
			}
		});
	}else{
		alert("Please Select Course Category");
		return false;
	};
};

</script>
	<div class="container-fluid">
		<section class="section card mb-1">
			<div class="card-body">

				<h1 class="bg-color heading">Course Master</h1>

				<c:if test="${not empty success}">
					<div class="alert alert-success">
						<a href="#" class="close" data-dismiss="alert">&times;</a><strong>
						${success}</strong>
					</div>
				</c:if>

				<form:form action="saveOrUpdateCourse" modelAttribute="course"
					method="post" enctype="multipart/form-data"  id="myForm"
					onsubmit="return createCourseValidation();">
					<form:hidden path="modelEnglish.id" />
					<form:hidden path="modelHindi.id" />
					<form:hidden path="modelEnglish.CreatedBy" />
					<form:hidden path="modelHindi.CreatedBy" />
					<form:hidden path="modelEnglish.commonId" />
					<form:hidden path="modelHindi.commonId" />
					<form:hidden path="modelEnglish.courseImagePath" />
					<form:hidden path="modelHindi.courseImagePath" />

					<div class="row" >
						<div class="col-lg-6 col-md-3">
							<form:select class="mdb-select" required="true"
								path="modelEnglish.courseCategoryId.commonId" id="courseCategoryCommonId">
								<form:option value="0">Select Course Category</form:option>
								<c:forEach items="${courseCategoryList}" var="ccl">
									<form:option value="${ccl.modelEnglish.commonId}">${ccl.modelEnglish.courseType} :: ${ccl.modelHindi.courseType}</form:option>
								</c:forEach>
							</form:select>
						</div>
						<span id="errorCourseCategory" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
					</div>

					<div class="row">
						<div class="col-lg-3 col-md-3">
							<div class="md-form">
								<form:input type="text" class="form-control validate"
									path="modelEnglish.name" id="name"  pattern="[A-Za-z ]{3,}" maxlength="50" required="true"
									onblur="checkCourseExist(this.value)" />
								<label style="width: 100%" for="name"
									data-error="Only characters with min 3 max 50 length">Name(In English)</label>
							</div>
							<span id="isCourseExist"></span>
						</div>

						<div class="col-lg-3 col-md-3">
							<div class="md-form">
								<form:input type="text" class="form-control validate"
									path="modelHindi.name" id="name1" maxlength="100"
									required="true" />
								<label for="name1" class="">नाम </label>
							</div>
						</div>
						<div class="col-lg-3 col-md-3">
							<div class="custom-control custom-checkbox">
								<form:checkbox class="custom-control-input" id="defaultChecked2"
									path="modelEnglish.isCompliance" value="true" />
								<label class="custom-control-label" for="defaultChecked2">Is
									Compliance</label>
							</div>
						</div>
					</div>
					
					<div class="row">
                    	<div class="col-lg-5 col-md-3">
                            <div class="md-form">
                                <form:input type="text" class="form-control" path="modelEnglish.shortDesc"
											id="shortDesc"/>
                                <label for="shortDesc" class="">Short Description(In English)</label>
                            </div>
                        </div>
                        
                        <div class="col-lg-5 col-md-3">
                            <div class="md-form">
                                <form:input type="text" class="form-control" path="modelHindi.shortDesc"
											 id="shortDesc1"/>
                                <label for="shortDesc1" >सक्षिप्त विवरण  </label>
                            </div>
                        </div>                       
                    </div>
                    
                    <div class="row">
                    	<div class="col-lg-5 col-md-3">
                            <div class="md-form">
                              <form:textarea type="text"  class="md-textarea form-control" rows="2" path="modelEnglish.longDesc" id="longDesc"></form:textarea>
                                <%-- <form:input type="text" class="form-control" path="modelEnglish.longDesc"
											onkeypress="return onlyAlphabets(event,this);"/> --%>
                                <label for="longDesc" >Long Description(In English)</label>
                            </div>
                        </div>
                        
                        <div class="col-lg-5 col-md-3">
                            <div class="md-form">
                                <form:textarea type="text"  class="md-textarea form-control" rows="2" path="modelHindi.longDesc" id="longDesc1"></form:textarea>
                                <%-- <form:input type="text" class="form-control" path="modelHindi.longDesc"
											 onkeypress="return onlyAlphabets(event,this);"/> --%>
                                <label for="longDesc1">दीर्घ विवरण  </label>
                            </div>
                        </div>                                             
                    </div>
                    
                    <div class="row">
                      <div class="col-lg-4 col-md-6 mb-4">
	                        <div class="md-form" style="margin-top: 10px;">
	                           <div class="file-field">
	                               <div class="btn btn-primary btn-sm float-left">
	                                   <span>Choose file</span>
	                                   <input type="file" name="courseImage">
	                               </div>
	                               <div class="file-path-wrapper">
	                                   <input class="file-path validate" id="userImg" required="required" readonly="readonly" type="text" placeholder="Upload course image">
	                               </div>
	                               <span id="errorUserImg" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
	                           </div>
	                         </div>
                         </div>
                    </div>

					<div class="form-group" style="margin-top: 20px;">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<button id="btn-signup" type="submit"
								class="btn blue-gradient btn-rounded">
								<i class="icon-hand-right"></i> Save
							</button>
							<button id="btn-signup1" type="reset"
								class="btn blue-gradient btn-rounded">
								<i class="icon-hand-right"></i> Reset
							</button>
						</div>
					</div>
				</form:form>
			</div>
		</section>
	</div>
	
	<div class="container-fluid mb-5">
		<section>
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-body">
							<table id="datatables"
								class="table table-striped table-bordered table-responsive-md"
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>Sr. No.</th>
										<th>Course Category</th>
										<th>Course</th>

										<th>Action</th>
									</tr>
								</thead>
								<!-- <tfoot>
									<tr>
										<th>Sr. No.</th>
										<th>Course Category</th>
										<th>Course</th>

										<th>Action</th>
									</tr>
								</tfoot> -->
								<tbody>
									<c:set var="count" value="0" scope="page" />
									<c:forEach items="${courseList}" var="sl">
										<tr class="odd gradeX">
											<td><c:set var="count" value="${count + 1}" scope="page" />
												<c:out value="${count}" /></td>
											<td><c:out
													value="${sl.modelEnglish.courseCategoryId.courseType}" />
												:: <c:out
													value="${sl.modelHindi.courseCategoryId.courseType}" /></td>
											<td><c:out value="${sl.modelEnglish.name}" /> :: <c:out
													value="${sl.modelHindi.name}" /></td>


											<td><a
												href="<c:url value="/course?commonId=${sl.modelEnglish.commonId} "/>"
												class="btn-floating btn-sm blue-gradient" title="Edit">
													<i class="fa fa-edit"></i>
											</a> <br> <a
												href="<c:url value="/deleteCourse?commonId=${sl.modelEnglish.commonId}" /> "
												class="btn-floating btn-sm blue-gradient"
												onclick="return confirm('Do you want to delete the record?')"
												title="Delete"> <i class="fa fa-trash-o"></i>
											</a></td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	</main>
	<%@include file="footer.jsp"%>

	<script>
		// SideNav Initialization
		$(".button-collapse").sideNav();

		var container = document.querySelector('.custom-scrollbar');
		Ps.initialize(container, {
			wheelSpeed : 2,
			wheelPropagation : true,
			minScrollbarLength : 20
		});

		$(document).ready(function() {
			$('#datatables').DataTable();
		});

		// Material Select Initialization
		$(document).ready(function() {
			$('select[name="datatables_length"]').material_select();
		});
	</script>

</body>
</html>