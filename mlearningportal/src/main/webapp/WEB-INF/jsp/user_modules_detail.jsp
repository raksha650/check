<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
</head>


<body>

	<%@include file="header.jsp"%>
	<%@include file="sidenev.jsp"%>

	<main _ngcontent-c0="" class="pt-5 mx-lg-5"
		ng-reflect-klass="pt-5 mx-lg-5" ng-reflect-ng-class="[object Object]">
	<div _ngcontent-c0="" class="mt-5">
		<router-outlet _ngcontent-c0=""></router-outlet>
		<app-dashboard1 _nghost-c3="">
		 
		
	<section _ngcontent-c4="" class="mt-lg-5">
<div class="row">
	
             <div class="row">
      			<div class="col-sm-7 m-b-3"> 
            		<div class="card"> 
                    	<img class="card-img-top img-responsive" src="${courseModule.courseId.courseImagePath }" style="height:280px;" >
              			<div class="card-body">
                			<h4 class="card-title"><strong>${courseModule.name }</strong></h4>
                			<p class="card-text text-justify">
                            	${courseModule.shortDesc } 
                            </p>
                            <p class="card-text text-justify">
                            	${courseModule.longDesc } 
                            </p>
                            <form action="" method="post" id="myForm">
                            <input type="hidden" name="courseModuleId" value="${courseModule.id}"/>
                             <input type="hidden" name="isCourseFinished" value="${isCourseFinished}"/>
                             <input type="hidden" name="startTime" value="${startTime}"/>
                            
                			
                			<button type="submit" class="action text-capitalize cancel my-btn btn-success btn-loaderClass" onclick="submitSlids()">Start The Course</button>
                			<c:if test="${not isCourseFinished}"> 
                            <button type="button" class="cancelConfirmStartExam action text-capitalize cancel my-btn btn-success" >Start The Exam</button>
                            </c:if>
                            <c:if test="${isCourseFinished}"> 
                            <button type="submit" class="action text-capitalize cancel my-btn btn-success btn-loaderClass" onclick="submitPaper()">Start The Exam</button>
                            </c:if>
                            
                            </form> 
                        </div>
            		</div> 
          		</div>
                <div class="col-sm-5 m-b-3"> 
                	<div class="card"> 
                    	<div class="col-sm-12 m-b-3 m-t-3">
            				<h4 class="text-black" style="padding-top:20px;"><strong>Course Module Details</strong></h4>
                            <p>&nbsp;</p>
            				<p>This ${courseModule.name } Module is intended to teach employees and working professionals. </p>
            				<ul class="list-group" style="padding-bottom:30px;">
                                <li class="list-group-item d-flex justify-content-between align-items-center"> 
                                	<strong>Course Name</strong>   <span class="badge badge-primary badge-pill">${courseModule.courseId.name}</span> 
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center"> 
                                	<strong>Course Category </strong>   <span class="badge badge-primary badge-pill">${courseModule.courseId.courseCategoryId.courseType}</span> 
                                </li>
              					<li class="list-group-item d-flex justify-content-between align-items-center"> 
                                	<strong>Course type </strong> <span class="badge badge-primary badge-pill">${courseModule.courseId.isCompliance?"Compliance":"Non Compliance"}</span> 
                                </li>
              					<li class="list-group-item d-flex justify-content-between align-items-center"> 
                                	<strong>Course Duration</strong>  <span class="badge badge-primary badge-pill">${courseContent.totalAllottedTime }</span> 
                                </li>
              					<li class="list-group-item d-flex justify-content-between align-items-center"> 
                                	<strong>Course Language</strong>  <span class="badge badge-primary badge-pill"> English </span> 
                                </li>
              					<%-- <li class="list-group-item d-flex justify-content-between align-items-center"> 
                                	<strong> Course Compliance Date </strong>  <span class="badge badge-primary badge-pill">${courseAssign.completionDate }</span> 
                                </li> --%>
                                <li class="list-group-item d-flex justify-content-between align-items-center"> 
                                	<strong>Total Assessment Questions </strong>   <span class="badge badge-primary badge-pill">${courseContent.totalAssessmentQuestion }</span> 
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center"> 
                                	<strong>Minimum correct answers to pass the course </strong>   <span class="badge badge-primary badge-pill">${courseContent.minPassNo }</span> 
                                </li>
            				</ul>
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
<script>
function submitSlids(){
	
	var myForm = document.getElementById("myForm");
	
	
	document.getElementById('myForm').action = "user_modules_slides";
}
function submitPaper(){
	
	var myForm = document.getElementById("myForm");
	
	document.getElementById('myForm').action = "user_question_paper";
	
}
</script>

</body>

</html>
