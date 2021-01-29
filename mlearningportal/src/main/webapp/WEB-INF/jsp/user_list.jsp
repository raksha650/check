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
                <div class="row"> 
                	<div class="col-md-12">                    
                    	<div class="card">
                            <div class="card-body">
                                <table id="datatables" class="table table-striped table-bordered table-responsive-md" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th>Sr.No</th>
											<th>Name</th>
											<th>Email</th>
											<th>Mobile</th>
											<th>Company</th>
											<!-- <th>Reporting</th> -->
											<th>District</th>
											<th>Action</th>
                                        </tr>
                                    </thead>
                                    <!-- <tfoot>
                                        <tr>
                                            <th>Sr.No</th>
											<th>Name</th>
											<th>Email</th>
											<th>Mobile</th>
											<th>Company</th>
											<th>Reporting</th>
											<th>District</th>
											<th>Action</th>
                                        </tr>
                                    </tfoot> -->
                                    <tbody>
                                    <c:set var="count" value="0" scope="page" />
										<c:forEach items="${userList}" var="fl">
											<tr class="odd gradeX">
												<td>
												<input type="hidden" name="ids" value="${fl.userId}">
												<c:set var="count" value="${count + 1}"
														scope="page" /> <c:out value="${count}" /> </td>
												<td><c:out value="${fl.firstName}"/> <c:out value="${fl.middleName}"/> <c:out value="${fl.lastName}"/></td>
												<td><c:out value="${fl.email}" /></td>
												<td><c:out value="${fl.phone}" /></td>
												<td><c:out value="${fl.companyId.name}" /></td>
												<%-- <td><c:out value="${fl.reportingHead.userName}" /></td> --%>
										
												<td><span class="label label-warning"><c:out value="${fl.districtId.name}" /></span></td>
											<%-- <td><a href="<c:url value="/create_user?id=${fl.userId} "/>">Edit</a></td> --%>
											
											 <td>
											 <div class="dropdown" >
												<a href="#"  data-toggle="dropdown" style="padding: 0rem 0rem; color:black !important;">
											    	<i class="fa fa-ellipsis-v"></i>
											    	<b class="caret"></b>
												</a>
												<ul class="dropdown-menu" style="font-size:0.9rem;">
													<li><a href="<c:url value="/create_user_new?id=${fl.userId} "/>">Edit</a></li>
													<%-- <li><a href="assign_course_touser?id=${fl.userId}">Assign Courses</a></li> --%>
													
												</ul>
											</div></td>
                           
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
