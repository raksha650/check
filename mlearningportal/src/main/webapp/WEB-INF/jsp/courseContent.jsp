<html>
<head>
<title>Course Content</title>
<<meta charset="utf-8">
</head>


<script type="text/javascript">
	function getCourseModule(courseCommonId) {
		
		$('#courseModuleId').find('option').remove();
		$('.courseLoaderImage').show();
		$.ajax({
			type : "POST",
			url : "get_course_module_model__list",
			data : {
				"courseCommonId" : courseCommonId
			}

		}).done(
				function(courseModuleList) {
					document.getElementById("courseModuleId").disabled = false;
					var opt = '';
					opt += '<option value=""> Select Course Module </option>';
					for (i in courseModuleList) {
						opt += '<option value="'+courseModuleList[i].id+'">'
								+ courseModuleList[i].name + '</option>';
					}
					$('.courseLoaderImage').hide();
					$('#courseModuleId').html(opt);
					$('#courseModuleId').material_select();
				});
	}
	
function getCourse(courseCategoryCommonId) {
		
		$('#courseId').find('option').remove();
		$('.loaderImage').show();
		$.ajax({
			type : "POST",
			url : "get_course_list",
			data : {
				"courseCategoryCommonId" : courseCategoryCommonId
			}

		}).done(
				function(courseList) {
					document.getElementById("courseId").disabled = false;
					var opt = '';
					opt += '<option value=""> Select Course </option>';
					for (i in courseList) {
						opt += '<option value="'+courseList[i].modelEnglish.commonId+'">'
								+ courseList[i].modelEnglish.name +'::'+ courseList[i].modelHindi.name + '</option>';
					}
					$('.loaderImage').hide();
					$('#courseId').html(opt);
					$('#courseId').material_select();
				});
	}
</script>
<body onload="disableDropdown()">

<%@include file="header.jsp"%>
<script type="text/javascript">
	function disableDropdown() {
		if(<c:out value='${courseContent.id != null}' />){
			$('#courseId').prop('disabled', false);
			$('#courseModuleId').prop('disabled', false);
				
		}else{
			
		$('#courseId').prop('disabled', true);
		$('#courseModuleId').prop('disabled', true);
	}
	}
	</script>
<%@include file="sidenev.jsp"%> 

<main>  
<div class="container-fluid">
        	<section class="section card mb-1">
            	<div class="card-body">
            		
            			<h1 class="bg-color heading">Course Content</h1>
					
                	
                    <!-- <h5 class="pb-5">Input fields</h5> -->
                    
                    <c:if test="${not empty param.success}">
						<div class="alert alert-success">
							<a href="#" class="close" data-dismiss="alert">&times;</a><strong>Success!</strong>
							${param.success}
						</div>
					</c:if>
					<c:if test="${not empty error}">
						<div class="alert alert-error">
							<a href="#" class="close" data-dismiss="alert">&times;</a><strong>Error!</strong>
							${error}
						</div>
					</c:if>
                    
                    <form:form action="saveOrUpdateCourseContent" modelAttribute="courseContent" method="post" id="myForm"
						onsubmit="return createCourseContentValidation();">
					<form:hidden path="id" />	
					<form:hidden path="createdBy" />				
					<div class="row">
					   <div class="col-lg-5 col-md-3">
                            <form:select class="mdb-select" path="courseModuleId.courseId.courseCategoryId.commonId"
                              id="courseCategory" onchange="getCourse(this.value)">
									<form:option value="">Select Course Category</form:option>
									<c:forEach items="${courseCategoryList}" var="ccl">
										<form:option value="${ccl.modelEnglish.commonId}">${ccl.modelEnglish.courseType} :: ${ccl.modelHindi.courseType}</form:option>
									</c:forEach>
							</form:select>
							<span id="errorCourseCategory" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                        </div>
                        <img class="loaderImage" src="images/ajax-loader.gif" style="display:none; margin-top:12px;" width="20px" height="20px">
                        <div class="col-lg-5 col-md-3">
                            <form:select class="mdb-select" path="courseModuleId.courseId.commonId" 
                            id="courseId" onchange="getCourseModule(this.value)">
									<form:option value="">Select Course</form:option>
									<c:forEach items="${courseList}" var="ccl">
										<form:option value="${ccl.modelEnglish.commonId}">${ccl.modelEnglish.name} :: ${ccl.modelHindi.name}</form:option>
									</c:forEach>
							</form:select>
							<span id="errorCourse" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                        </div>
                   </div>
                   <div class="row">
					    <img class="courseLoaderImage" src="images/ajax-loader.gif" style="display:none; margin-top:12px;" width="20px" height="20px">                    
                        <div class="col-lg-8 col-md-3">
                            <form:select class="mdb-select" path="courseModuleId.id" id="courseModuleId">
									<form:option value="">Select Course Module</form:option>
									<c:forEach items="${courseModuleList}" var="ccl">
										<form:option value="${ccl.id}">${ccl.name}</form:option>
									</c:forEach>
							</form:select>
							<span id="errorCourseModule" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                        </div>
                   </div>
                    
					
					
                    <div class="row">
                    	<div class="col-md-3">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="totalSlide"
											id="totalSlide" pattern="[0-9]{1,}" maxlength="3" required="true"/>
                                <label style="width: 100%" for="totalSlide" data-error="Only Number with atleast 1 digit">Total Slides</label>
                            </div>
                        </div>
                        
                        <div class="col-md-3">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="totalAllottedTime"
											 id="totalAllottedTime" pattern="[0-9,:]{5}" maxlength="5" required="true"/>
                                <label style="width: 100%" for="totalAllottedTime" data-error="Only in formate hh:mm">Total Allotted Time</label>
                            </div>
                        </div>
                        
                        <div class="col-md-3">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="totalAssessmentQuestion"
											id="totalAssessmentQuestion" pattern="[0-9]{1,}" maxlength="3" required="true"/>
                                <label style="width: 100%" for="totalAssessmentQuestion" data-error="Only Number with atleast 1 digit">Total Assessment Question</label>
                            </div>
                        </div>
                                             
                    </div>
                    
                    
                    <div class="row">
                    	<div class="col-md-3">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="minPassNo"
											id="minPassNo" pattern="[0-9]{1,}" maxlength="3" required="true"/>
                                <label style="width: 100%" for="minPassNo" data-error="Only Number with atleast 1 digit">Minimum Passing Question</label>
                            </div>
                        </div>
                                             
                    </div>             
                   
                    
                    <div class="form-group" style="margin-top: 15px;">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<button id="btn-signup" type="submit" class="btn blue-gradient btn-rounded">
								<i class="icon-hand-right"></i> Save
							</button>
							<button id="btn-signup1" type="reset" class="btn blue-gradient btn-rounded">
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
                                <table id="datatables" class="table table-striped table-bordered table-responsive-md" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th>Sr. No.</th>
											<th>Subject</th>
											<th>Total Slide</th>
											<th>Total Allotted Time</th>
											<th>Total Assessment Que</th>
											<th>Min Pass No</th>
											<th>Action</th>
                                        </tr>
                                    </thead>
                                    <!-- <tfoot>
                                        <tr>
                                            <th>Sr. No.</th>
											<th>Subject</th>
											<th>Total Slide</th>
											<th>Total Allotted Time</th>
											<th>Total Assessment Que</th>
											<th>Min Pass No</th>
											<th>Action</th>
                                        </tr>
                                    </tfoot> -->
                                    <tbody>
                                    <c:set var="count" value="0" scope="page" />
									<c:forEach items="${courseContentList}" var="ccl">
										<tr class="odd gradeX">
											<td><c:set var="count" value="${count + 1}" scope="page" />
												<c:out value="${count}" /></td>
											<td><c:out value="${ccl.courseModuleId.name}" /></td>
											<td><c:out value="${ccl.totalSlide}" /></td>
											<td><c:out value="${ccl.totalAllottedTime}" /></td>
											<td><c:out value="${ccl.totalAssessmentQuestion}" /></td>
											<td><c:out value="${ccl.minPassNo}" /></td>

											<td><a href="<c:url value="/courseContent?id=${ccl.id} "/>"
											 class="btn-floating btn-sm blue-gradient" title="Edit">
											        <i class="fa fa-edit"></i>            
											    </a>
											    <br>
											    <a href="<c:url value="/deleteCourseContent?id=${ccl.id}" />"
											     class="btn-floating btn-sm blue-gradient" onclick="return confirm('Do you want to delete the record?')" title="Delete">
											          <i class="fa fa-trash-o"></i>     
											    </a>
											</td>
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
       wheelSpeed: 2,
       wheelPropagation: true,
       minScrollbarLength: 20
   });

   $(document).ready(function() {
       $('#datatables').DataTable();
   });

   // Material Select Initialization
   $(document).ready(function () {
       $('select[name="datatables_length"]').material_select();
   });

</script>

</body>
</html>