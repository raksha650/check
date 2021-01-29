<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Question</title>
<meta charset="utf-8">


<script type="text/javascript">
function getCourseModule(courseCommonId) {
	
	$('#courseModuleId').find('option').remove();
	$('.courseLoaderImage').show();
	$.ajax({
		type : "POST",
		url : "get_course_module_list",
		data : {
			"courseCommonId" : courseCommonId
		}

	}).done(
			function(courseModuleList) {
				document.getElementById("courseModuleId").disabled = false;
				var opt = '';
				opt += '<option value="0"> Select Course Module </option>';
				for (i in courseModuleList) {
				
					opt += '<option value="'+courseModuleList[i].modelEnglish.commonId+'">'
							+ courseModuleList[i].modelEnglish.name +'::'+ courseModuleList[i].modelHindi.name + '</option>';
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
				function(courseCategoryList) {
					document.getElementById("courseId").disabled = false;
					var opt = '';
					opt += '<option value="0"> Select Course Category </option>';
					for (i in courseCategoryList) {
						opt += '<option value="'+courseCategoryList[i].modelEnglish.commonId+'">'
								+ courseCategoryList[i].modelEnglish.name +'::'+ courseCategoryList[i].modelHindi.name + '</option>';
					}
					$('.loaderImage').hide();
					$('#courseId').html(opt);
					$('#courseId').material_select();
				});
}
</script>
</head>
<body onload="disableDropdown()">

<%@include file="header.jsp"%>
<script type="text/javascript">
	function disableDropdown() {
		if(<c:out value='${question.modelEnglish.id != null}' />){
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
            		
            			<h1 class="bg-color heading">Question</h1>
					
                	
                    <!-- <h5 class="pb-5">Input fields</h5> -->
                    
                    <c:if test="${not empty success}">
						<div class="alert alert-success">
							<a href="#" class="close" data-dismiss="alert">&times;</a><strong>
							${success};</strong>
						</div>
					</c:if>
					
				<form:form action="saveOrUpdateQuestion" id="myForm"
					modelAttribute="question" method="post"
					onsubmit="return createQuestionValidation();">
					<form:hidden path="modelEnglish.id" />
					<form:hidden path="modelHindi.id" />
					<form:hidden path="modelEnglish.createdBy" />
					<form:hidden path="modelHindi.createdBy" />
					<form:hidden path="modelEnglish.commonId" />
					<form:hidden path="modelHindi.commonId" />		
						
					
							
							
					<div class="row">
					   <div class="col-lg-5 col-md-3">
                            <form:select class="mdb-select" path="modelEnglish.courseModuleId.courseId.courseCategoryId.commonId"
                             required="true" onchange="getCourse(this.value)" id="courseCategory">
									<form:option value="0">Select Course Category</form:option>
									<c:forEach items="${courseCategoryList}" var="ccl">
										<form:option value="${ccl.modelEnglish.commonId}">${ccl.modelEnglish.courseType} :: ${ccl.modelHindi.courseType}</form:option>
									</c:forEach>
							</form:select>
							<span id="errorCourseCategory" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                        </div>
                        <img class="loaderImage" src="images/ajax-loader.gif" style="display:none; margin-top:12px;" width="20px" height="20px">
                        <div class="col-lg-5 col-md-3">
                            <form:select class="mdb-select" path="modelEnglish.courseModuleId.courseId.commonId"
                             required="true" id="courseId" onchange="getCourseModule(this.value)">
									<form:option value="0">Select Course</form:option>
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
                            <form:select class="mdb-select" path="modelEnglish.courseModuleId.commonId" 
                            required="true" id="courseModuleId">
									<form:option value="0">Select Course Module</form:option>
									<c:forEach items="${courseModuleList}" var="ccl">
										<form:option value="${ccl.modelEnglish.commonId}">${ccl.modelEnglish.name} :: ${ccl.modelHindi.name}</form:option>
									</c:forEach>
							</form:select>
							<span id="errorCourseModule" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                        </div>
                    </div>
                    
                    
                    <div class="row">
                    	<div class="col-lg-5 col-md-3">
                            <div class="md-form">
                              <form:textarea type="text"  class="md-textarea form-control validate" rows="1" path="modelEnglish.question"
                              id="question12" maxlength="500" required="true"></form:textarea>
                                
                                <label style="width: 100%" for="question12" >Enter question</label>
                            </div>
                        </div>
                        
                        <div class="col-lg-5 col-md-3">
                            <div class="md-form">
                                <form:textarea type="text" id="question1" maxlength="500" required="true" class="md-textarea form-control" rows="1" path="modelHindi.question"></form:textarea>
                                
                                <label for="question1" >सवाल लिखें  </label>
                            </div>
                        </div>               
                    </div>
					
					
                    <div class="row">
                    	<div class="col-lg-5 col-md-3">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="modelEnglish.option1"
							    id="option1" maxlength="200" required="true"/>
                                <label style="width: 100%" for="option1">Enter Option1</label>
                            </div>
                        </div>
                        
                        <div class="col-lg-5 col-md-3">
                            <div class="md-form">
                                <form:input type="text" class="form-control" path="modelHindi.option1"
											 id="option11" maxlength="200" required="true"/>
                                <label style="width: 100%" for="option11">आप्शन  1</label>
                            </div>
                        </div>
                                             
                    </div>
                    
                    <div class="row">
                    	<div class="col-lg-5 col-md-3">
                            <div class="md-form">
                                <form:input type="text" class="form-control" path="modelEnglish.option2"
											 id="option2" maxlength="200" required="true"/>
                                <label style="width: 100%" for="option2">Enter Option2</label>
                            </div>
                        </div>
                        
                        <div class="col-lg-5 col-md-3">
                            <div class="md-form">
                                <form:input type="text" class="form-control" path="modelHindi.option2"
											 id="option22" maxlength="200" required="true"/>
                                <label style="width: 100%" for="option22">आप्शन  2</label>
                            </div>
                        </div>
                                             
                    </div>
                    
                    <div class="row">
                    	<div class="col-lg-5 col-md-3">
                            <div class="md-form">
                                <form:input type="text" class="form-control" path="modelEnglish.option3"
											id="option3" maxlength="200" required="true"/>
                                <label style="width: 100%" for="option3">Enter Option3</label>
                            </div>
                        </div>
                        
                        <div class="col-lg-5 col-md-3">
                            <div class="md-form">
                                <form:input type="text" class="form-control" path="modelHindi.option3"
											id="option33" maxlength="200" required="true"/>
                                <label style="width: 100%" for="option33">आप्शन  3</label>
                            </div>
                        </div>              
                    </div>
                    
                    <div class="row">
                    	<div class="col-lg-5 col-md-3">
                            <div class="md-form">
                                <form:input type="text" class="form-control" path="modelEnglish.option4"
											id="option4" maxlength="200" required="true"/>
                                <label style="width: 100%" for="option4">Enter Option4</label>
                            </div>
                        </div>
                        
                        <div class="col-lg-5 col-md-3">
                            <div class="md-form">
                                <form:input type="text" class="form-control" path="modelHindi.option4"
											 id="option44" maxlength="200" required="true"/>
                                <label style="width: 100%" for="option44">आप्शन  4</label>
                            </div>
                        </div>              
                    </div>
                    
                    <div class="row">
					   <div class="col-lg-5 col-md-3">
                            <form:select class="mdb-select" path="modelEnglish.answer" required="true">
									<form:option value="">Select Answer</form:option>
									<form:option value="option1">option 1</form:option>
									<form:option value="option2">option 2</form:option>
									<form:option value="option3">option 3</form:option>
									<form:option value="option4">option 4</form:option>
							</form:select>
                        </div>
					                        
                        <div class="col-lg-5 col-md-3">
                            <form:select class="mdb-select" path="modelEnglish.level" required="true" id="level">
									<form:option value="">Select Level</form:option>
									<form:option value="1">1</form:option>
									<form:option value="2">2</form:option>
									<form:option value="3">3</form:option>
									<form:option value="4">4</form:option>
							</form:select>
                        </div>
                    </div>
                    
                     <div class="row">
                    	<div class="col-lg-5 col-md-3">
                            <div class="md-form">
                              <form:textarea type="text" id="description" class="md-textarea form-control" rows="1" path="modelEnglish.description"></form:textarea>
                                
                                <label for="description" class="">Enter Description</label>
                            </div>
                        </div>
                        
                        <div class="col-lg-5 col-md-3">
                            <div class="md-form">
                                <form:textarea type="text" id="description1" class="md-textarea form-control" rows="1" path="modelHindi.description"></form:textarea>
                                
                                <label for="description1" >विवरण  लिखें  </label>
                            </div>
                        </div>               
                    </div>       
                   
                    
                    <div class="md-form">
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
											<th>question</th>
											<th>Action</th>
                                        </tr>
                                    </thead>
                                    <!-- <tfoot>
                                        <tr>
                                            <th>Sr. No.</th>
											<th>Subject</th>
											<th>question</th>
											<th>Action</th>
                                        </tr>
                                    </tfoot> -->
                                    <tbody>
                                    <c:set var="count" value="0" scope="page" />
									<c:forEach items="${questionList}" var="sl">
										<tr class="odd gradeX">
											<td><c:set var="count" value="${count + 1}" scope="page" />
												<c:out value="${count}" /></td>
											
											<td><c:out value="${sl.modelEnglish.courseModuleId.name}" /> :: <c:out value="${sl.modelHindi.courseModuleId.name}" /></td>
											<td><c:out value="${sl.modelEnglish.question}" /> :: <c:out value="${sl.modelHindi.question}" /></td>

											<td><a href="<c:url value="/question?commonId=${sl.modelEnglish.commonId}"/>"
											 class="btn-floating btn-sm blue-gradient" title="Edit">
											        <i class="fa fa-edit"></i>            
											    </a>
											    <a href="<c:url value="/deleteQuestionr?commonId=${sl.modelEnglish.commonId}"/>"
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