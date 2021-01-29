<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Category Master</title>
<meta charset="utf-8">
</head>
<body>

<%@include file="header.jsp"%>
<%@include file="sidenev.jsp"%> 
<main>
<script>
function checkCourseExist(val) {
	
	$.ajax({		
		url : 'existCourseCategory?courseTypeName=' + val,

		success : function(response) {
			
			if (response == true) {
				document.getElementById("isCourseExist").style.color = "#ff0000";
				$("#isCourseExist").text("Course Type Already Exist !");

			} else {
				$("#isCourseExist").text("");
			}
		}
	});
};

</script>  
<div class="container-fluid">
        	<section class="section card mb-5">
            	<div class="card-body">
                	<h1 class="bg-color heading">Course Category</h1>
                    <!-- <h5 class="pb-5">Input fields</h5> -->
                    
                    <c:if test="${not empty success}">
						<div class="alert alert-success">
							<a href="#" class="close" data-dismiss="alert">&times;</a><strong>
							${success};</strong>
						</div>
					</c:if>
                    
                    <form:form action="saveOrUpdateCourseCategory" id="myForm" onsubmit="return myFormSubmitWithConfirmationAndLoader();"
						modelAttribute="courseCategory" method="post">
						<form:hidden path="modelHindi.id" />
						<form:hidden path="modelEnglish.id" />
						<form:hidden path="modelEnglish.CreatedBy" />
						<form:hidden path="modelHindi.CreatedBy" />
						<form:hidden path="modelEnglish.commonId" />
						<form:hidden path="modelHindi.commonId" />
								
					
                    <div class="row">
                    	<div class="col-md-4 mb-4">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="modelEnglish.courseType" pattern="[A-Za-z ]{3,}" maxlength="50"
											id="courseType" required="true" onblur="checkCourseExist(this.value)"/>
                                <label style="width: 100%" for="courseType" data-error="Only characters with min 3 max 50 length" >Course Type(In English)</label>
                            </div>
                            <span id="isCourseExist"></span>
                        </div>
                        
                        <div class="col-md-4 mb-4">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="modelHindi.courseType"
											 id="courseType11" required="true" maxlength="100"/>
                                <label for="courseType11" >कोर्स का  टाइप लिखें </label>
                            </div>
                        </div>
                                             
                    </div>
                    
                    <div class="form-group">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<button  type="submit" class="btn blue-gradient btn-rounded" >
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
											<th>Course Type</th>
											<th>कोर्स टाइप</th>
											<th>Action</th>
                                        </tr>
                                    </thead>
                                    <!-- <tfoot>
                                        <tr>
                                            <th>Sr. No.</th>
											<th>Course Type</th>
											<th>कोर्स टाइप</th>
											<th>Action</th>
                                        </tr>
                                    </tfoot> -->
                                    <tbody>
                                    <c:set var="count" value="0" scope="page" />
									<c:forEach items="${courseCategoryList}" var="ccl">
										<tr class="odd gradeX">
											<td><c:set var="count" value="${count + 1}" scope="page" />
												<c:out value="${count}" /></td>
											<td><c:out value="${ccl.modelEnglish.courseType}" /></td>
											<td><c:out value="${ccl.modelHindi.courseType}" /></td>

											<td><a href="<c:url value="/courseCategory?commonId=${ccl.modelEnglish.commonId} "/>"
											 class="btn-floating btn-sm blue-gradient" title="Edit">
											        <i class="fa fa-edit"></i>            
											    </a>
											    <a href="<c:url value="/deleteCourseCategory?commonId=${ccl.modelEnglish.commonId}" /> "
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