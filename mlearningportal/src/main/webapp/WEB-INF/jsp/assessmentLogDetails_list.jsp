<!DOCTYPE html>
<html lang="en">

<head>
<title>User Details</title>
<meta charset="utf-8">

</head>

<body>
<%@include file="header.jsp"%>
<%@include file="sidenev.jsp"%>      
	
<main>    	        
		<div class="container-fluid mb-5">
        	<section>
        	<div class="card p-2 mb-3 card-cascade narrower ">
	        	<div class="view gradient-card-header blue-gradient narrower py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">
	              <div></div>
	               <a href="#" class="white-text mx-3">Assessment Log Details</a>
	              <div></div>
	            </div>
               
               <form method="post" action="searchAssessmentLogDetails">
                    <!--Grid row-->
                    <div class="row">
                        <div class="col-lg-5 col-md-12">
                           <select class="mdb-select colorful-select dropdown-primary mx-2" id="userId" name="userId" oninvalid="this.setCustomValidity('Choose Office !')" oninput="setCustomValidity('')">
	                           <option value="" disabled selected>Select User </option>
							   <c:forEach var="userList" items="${userList}">
							     <option value="${userList.userId}">${userList.firstName} ${userList.middleName} ${userList.lastName}</option>
							   </c:forEach>
                           </select>
                        </div>
                        <div class="col-lg-5 col-md-12">
                           <select class="mdb-select colorful-select dropdown-primary mx-2" id="courseModuleId" name="courseModuleId" oninvalid="this.setCustomValidity('Choose Office !')" oninput="setCustomValidity('')">
	                           <option value="" disabled selected>Select Course Module</option>
							   <c:forEach var="courseModuleList" items="${courseModuleList}">
							     <option value="${courseModuleList.id}">${courseModuleList.name}</option>
							   </c:forEach>
                           </select>
                        </div>
                        
                        <div class="col-lg-5 col-md-6" style="margin-top: 50px;">
                            <a class="btn blue-gradient btn-rounded btn-sm" href="dashboard"
								title="Cancel" type="button">Back</a>
							<input type="submit" name="search" value="Search" class="btn blue-gradient btn-rounded btn-sm">							
                        </div>      
                                        
                      </div>
                </form>
             </div>
                
                <div class="row"> 
                	<div class="col-md-12">                    
                    	<div class="card">
                            <div class="card-body">
                                <table id="datatables" class="table table-striped table-bordered table-responsive-md" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th>Sr.No</th>
											<th>Assessment Date</th>
											<th>User</th>
											<th>Course Module</th>
											<th>Start Time</th>
											<th>End Time</th>
											<th>Spend Time</th>
											<th>NoOfQuestion</th>
											<th>Attempted</th>
											<th>Corrected</th>
											<th>Status</th>
                                        </tr>
                                    </thead>
                                    <!-- <tfoot>
                                        <tr>
                                            <th>Sr.No</th>
											<th>Assessment Date</th>
											<th>User</th>
											<th>Course Module</th>
											<th>Start Time</th>
											<th>End Time</th>
											<th>Spend Time</th>
											<th>NoOfQuestion</th>
											<th>Attempted</th>
											<th>Corrected</th>
											<th>Status</th>
                                        </tr>
                                    </tfoot> -->
                                    <tbody>
                                    <c:set var="count" value="0" scope="page" />
										<c:forEach items="${assessmentLogDetailsList}" var="aLDL">
											<tr class="odd gradeX">
												<td>
												<input type="hidden" name="ids" value="${fl.userId}">
												<c:set var="count" value="${count + 1}"
														scope="page" /> <c:out value="${count}" /> </td>
												<td><c:out value="${aLDL.assessmentDate}" /></td>
												<td><c:out value="${aLDL.userId.firstName}"/> <c:out value="${aLDL.userId.middleName}"/> <c:out value="${aLDL.userId.lastName}"/></td>
												<td><c:out value="${aLDL.courseModuleId.name}" /></td>
												<td><c:out value="${aLDL.startTime}" /></td>
												<td><c:out value="${aLDL.endTime}" /></td>
												<td><c:out value="${aLDL.totalSpendTime}" /></td>
												<td><c:out value="${aLDL.totalNoOfQuestion}" /></td>
												<td><c:out value="${aLDL.questionAttempted}" /></td>
												<td><c:out value="${aLDL.questionCorrected}" /></td>												
                           						<td><c:out value="${fn:toUpperCase(aLDL.status)}"/></td>
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
