<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Course Module Master</title>
<meta charset="utf-8">
<style>


</style>
</head>
<body>

<%@include file="header.jsp"%>
<%@include file="sidenev.jsp"%> 
<main>

<script>
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

function checkCourseModuleExist(val) {
	var courseCommonId = document.getElementById("courseCommonId").value;
	//alert("response1--"+courseCommonId);
	if(courseCommonId != 0){
		$.ajax({		
			url : 'existCourseModule?courseCommonId='+courseCommonId+'&module='+val,
			success : function(response) {
				if (response == true) {
					alert("response--"+response);
					document.getElementById("isCourseModuleExist").style.color = "#ff0000";
					$("#isCourseModuleExist").text("Course Module Already Exist !");
	
				} else {
					$("#isCourseModuleExist").text("");
				}
			}
		});
	}else{
		alert("Please Select Course !");
		return false;
	};
};

</script>  
<div class="container-fluid">
        	<section class="section card mb-1">
            	<div class="card-body">
            		
            			<h1 class="bg-color heading">Course Module Master</h1>
					
                	
                    <!-- <h5 class="pb-5">Input fields</h5> -->
                    
                    <c:if test="${not empty success}">
						<div class="alert alert-success">
							<a href="#" class="close" data-dismiss="alert">&times;</a><strong>
							${success};</strong>
						</div>
					</c:if>
                    
                    <form:form action="saveOrUpdateCourseModule"
						modelAttribute="courseModule" method="post" id="myForm"
						onsubmit="return createCourseModuleValidation();">
						<form:hidden path="modelEnglish.id" />
						<form:hidden path="modelHindi.id" />
						<form:hidden path="modelEnglish.CreatedBy" />
						<form:hidden path="modelHindi.CreatedBy" />
						<form:hidden path="modelEnglish.commonId" />
						<form:hidden path="modelHindi.commonId" />
								
					<div class="row">  
					   <div class="col-lg-5 col-md-3">
                            <form:select class="mdb-select" path="modelEnglish.courseId.courseCategoryId.commonId"
                             required="true" onchange="getCourse(this.value)" id="courseCategory">
								<form:option value="0">Select Course Category</form:option>
								<c:forEach items="${courseCategoryList}" var="ccl">
									<form:option value="${ccl.modelEnglish.commonId}">${ccl.modelEnglish.courseType} :: ${ccl.modelHindi.courseType}</form:option>
								</c:forEach>
							</form:select>
							<span id="errorCourseCategory" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                        </div> 
                        <img class="loaderImage" src="images/ajax-loader.gif" style="display:none; margin-top:20px;" width="20px" height="20px">                     
                        <div class="col-lg-5 col-md-3">
                            <form:select class="mdb-select" required="true"
							   path="modelEnglish.courseId.commonId" id="courseId">
							  <form:option value="0">Select Course</form:option>
								<c:forEach items="${courseList}" var="ccl">
									<form:option value="${ccl.modelEnglish.commonId}">${ccl.modelEnglish.name} :: ${ccl.modelHindi.name}</form:option>
								</c:forEach>
				            </form:select>
                        </div>
                        <span id="errorCourse" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                    </div>
					
					
                    <div class="row">
                    	<div class="col-lg-5 col-md-3">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="modelEnglish.name"
											id="name" pattern="[A-Za-z ]{3,}" maxlength="50" required="true" onblur="checkCourseModuleExist(this.value)"/>
                                <label style="width: 100%" for="name" data-error="Only characters with min 3 max 50 length" >Name(In English)</label>
                            </div>
                            <span id="isCourseModuleExist"></span>
                        </div>
                        
                        <div class="col-lg-5 col-md-3">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="modelHindi.name"
											 id="name1" maxlength="100" required="true"/>
                                <label for="name1" class="">नाम </label>
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
                    
                    <div class="form-group">
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
											<th>Course</th>
											<th>Module</th>
											<th>Sort Desc</th>
											<th>Long Desc</th>
											<th>Action</th>
                                        </tr>
                                    </thead>
                                    <!-- <tfoot>
                                        <tr>
                                            <th>Sr. No.</th>
											<th>Course</th>
											<th>Module</th>
											<th>Sort Desc</th>
											<th>Long Desc</th>
											<th>Action</th>
                                        </tr>
                                    </tfoot> -->
                                    <tbody>
                                    <c:set var="count" value="0" scope="page" />
									<c:forEach items="${courseModuleList}" var="sl">
										<tr class="odd gradeX">
											<td><c:set var="count" value="${count + 1}" scope="page" />
												<c:out value="${count}" /></td>
											<td><c:out value="${sl.modelEnglish.courseId.name}" /> :: <c:out value="${sl.modelHindi.courseId.name}" /></td>
											<td><c:out value="${sl.modelEnglish.name}" /> :: <c:out value="${sl.modelHindi.name}" /></td>
											<td><c:out value="${sl.modelEnglish.shortDesc}" /> :: <c:out value="${sl.modelHindi.shortDesc}" /></td>
											<td><c:out value="${sl.modelEnglish.longDesc}" /> :: <c:out value="${sl.modelHindi.longDesc}" /></td>

											<td><a href="<c:url value="/courseModule?commonId=${sl.modelEnglish.commonId} "/>"
											 class="btn-floating btn-sm blue-gradient" title="Edit">
											        <i class="fa fa-edit"></i>            
											    </a>
											    <br>
											    <a href="<c:url value="/deleteCourseModule?commonId=${sl.modelEnglish.commonId}" /> "
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