<!DOCTYPE html>
<html lang="en">

<head>
<title>User Designation</title>
<meta charset="utf-8">
</head>

<body>

	<div id="wrapper">

		<%@include file="header.jsp"%>
		<%@include file="sidenev.jsp"%>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h4 class="page-header">Course Category</h4>
					<c:if test="${not empty success}">
						<div class="alert alert-success">
							<a href="#" class="close" data-dismiss="alert">&times;</a><strong>Success!</strong>
							${success};
						</div>
					</c:if>
					<c:if test="${not empty error}">
						<div class="alert alert-error">
							<a href="#" class="close" data-dismiss="alert">&times;</a><strong>Error!</strong>
							${error};
						</div>
					</c:if>
					<form:form action="saveOrUpdateCourseCategory" id="myForm" onsubmit="return myFormSubmitWithConfirmationAndLoader();"
						modelAttribute="courseCategory" method="post">
						<form:hidden path="id" />
						<%--   <form:hidden path="entryDate"/> --%>
						<%-- <form:hidden path="updatedDate"/>
                                      <form:hidden path="user"/> --%>
						<div class="col-lg-4">
							<div class="form-group ">
								<form:input class="form-control" path="courseType"
									required="true" placeholder="Enter Course Type" />

							</div>
						</div>

						<div class="col-lg-4">
							<button type="submit" id="btn-signup" class="btn btn-default">Submit</button>
							<button type="reset" class="btn btn-default">Reset</button>
						</div>
					</form:form>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Course Category Search</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<table width="100%"
								class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>Sr. No.</th>
										<th>Course Category</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="count" value="0" scope="page" />
									<c:forEach items="${designationList}" var="fl">
										<tr class="odd gradeX">
											<td><c:set var="count" value="${count + 1}" scope="page" />
												<c:out value="${count}" /></td>
											<td><c:out value="${fl.designation}" /></td>

											<td><a
												href="<c:url value="/designation?designationId=${fl.id} "/> "
												class="btn btn-register btn-sm"> Edit</a> <a
												href="<c:url value="/deleteDesignation?id=${fl.id}" /> "
												onclick="return confirm('Do you want to delete the record?')"
												class="btn btn-register btn-sm"> Delete</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>

	<%@include file="footer.jsp"%>
</body>

</html>
