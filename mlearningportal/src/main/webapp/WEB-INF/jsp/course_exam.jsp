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
<div class="container-fluid">
        	<section class="section card mb-5">
            	<div class="card-body">
                	<h1 class="bg-color heading">Course Exam</h1>
                    <!-- <h5 class="pb-5">Input fields</h5> -->
                    
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
											<th>User</th>
											<th>Course</th>
											<th>Exam Date</th>
										    <th>Attempt No</th>
											<th>Total Question</th>
											<th>Total Attempt</th>
											<th>Total Right</th>
											<th>Total Time</th>
											<th>Result</th>
                                        </tr>
                                    </thead>
                                    <!-- <tfoot>
                                        <tr>
                                            <th>Sr. No.</th>
											<th>User</th>
											<th>Course</th>
											<th>Exam Date</th>
										    <th>Attempt No</th>
											<th>Total Question</th>
											<th>Total Attempt</th>
											<th>Total Right</th>
											<th>Total Time</th>
											<th>Result</th>
                                        </tr>
                                    </tfoot> -->
                                    <tbody>
                                    <c:set var="count" value="0" scope="page" />
									<c:forEach items="${courseExamList}" var="sl">
										<tr class="odd gradeX">
											<td><c:set var="count" value="${count + 1}" scope="page" />
												<c:out value="${count}" /></td>
											
											<td><c:out value="${sl.userId.firstName}" /> <c:out value="${sl.userId.middleName}" /> <c:out value="${sl.userId.lastName}" /></td>
											<td><c:out value="${sl.courseModuleId.courseId.courseCategoryId.courseType}" />::<c:out value="${sl.courseModuleId.name}" /></td>
											<td><fmt:formatDate value="${sl.examDate}" pattern="dd-MMM-yyyyy"/></td>
											<td><c:out value="${sl.attemptNo}" /></td>
											<td><c:out value="${sl.totalQuestion}" /></td>
											<td><c:out value="${sl.totalAttempt}" /></td>
											<td><c:out value="${sl.totalRight}" /></td>
											<td><c:out value="${sl.totalTime}" /></td>
											<td><c:out value="${sl.result}" /></td>

											<%-- <td><a
												href="<c:url value="/question?commonId=${sl.modelEnglish.commonId} "/> "
												class="btn btn-register btn-sm"> Edit</a> <a
												href="<c:url value="/deleteQuestionr?commonId=${sl.modelEnglish.commonId}" /> "
												onclick="return confirm('Do you want to delete the record?')"
												class="btn btn-register btn-sm"> Delete</a></td> --%>
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