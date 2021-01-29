<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Sub Department Master</title>
<meta charset="utf-8">
</head>

<body>
<%@include file="header.jsp"%>
<%@include file="sidenev.jsp"%>
<main>
<script>
function checkSubDepartmentExist(val) {
	var deptCommonId = document.getElementById("subDept").value;
	if(deptCommonId != 0){
		$.ajax({		
			url : 'existSubDept?deptCommonId='+deptCommonId+'&subDept='+val,
			success : function(response) {
				if (response == true) {
					document.getElementById("isSubDeptExist").style.color = "#ff0000";
					$("#isSubDeptExist").text("Sub Department Already Exist !");
	
				} else {
					$("#isSubDeptExist").text("");
				}
			}
		});
	}else{
		alert("Please Select Department");
		return false;
	};
};

</script>
	<div class="container-fluid">
        	<section class="section card mb-5">
            	<div class="card-body">
                	<h1 class="bg-color heading">Sub Department Master</h1>
                    <!-- <h5 class="pb-5">Input fields</h5> -->
                    
                    <c:if test="${not empty success}">
						<div class="alert alert-success">
							<a href="#" class="close" data-dismiss="alert">&times;</a><strong>
							${success};</strong>
						</div>
					</c:if>
					<form:form action="saveOrUpdateSubDeptMaster" id="myForm"
						modelAttribute="subDeptMaster" method="post"
						onsubmit="return createSubDeptValidation();">
						<form:hidden path="modelHindi.id" />
						<form:hidden path="modelEnglish.id" />
						<form:hidden path="modelEnglish.commonId" />
						<form:hidden path="modelHindi.commonId" />
					
					

					<div class="row">						
					    <div class="col-md-6 mb-4">
	                         <div class="md-form">
	                            <form:select class="mdb-select validate" required="true"
								   path="modelEnglish.deptId.commonId" id="subDept">
									<form:option value="0">Select Department:विभाग चयन करें </form:option>
									<c:forEach items="${deptMasterList}" var="sml">
										<form:option value="${sml.modelEnglish.commonId}">${sml.modelEnglish.deptName}:${sml.modelHindi.deptName}</form:option>
									</c:forEach>
					            </form:select>
	                          </div>
	                          <span id="errorSubDept" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                        </div>
					</div>
					<div class="row">		
						<div class="col-md-4 ">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="modelEnglish.subDeptName" pattern="[A-Za-z ]{3,}"
                                 id="subDeptNameEnglish"  maxlength="100" required="true" onblur="checkSubDepartmentExist(this.value)"/>
                                <label style="width: 100%" for="subDeptNameEnglish" data-error="Only characters with min 3 max 50 length">Enter Sub Department Name</label>
                            </div>
                            <span id="isSubDeptExist"></span>
                        </div>
                        <div class="col-md-4">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="modelHindi.subDeptName"
											id="subDeptNameHindi"  maxlength="100" required="true"/>
                                <label style="width: 100%" for="subDeptNameHindi" >विभाग का नाम </label>
                            </div>
                        </div>
                      </div>							
					</br>
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
						<br/>
					</form:form>
				</div>
				<!-- /.col-lg-12 -->
				</section>
			</div>
			<!-- /.row -->
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
										<th>Department:विभाग</th>
										<th>Sub Department Name</th>
										<th>उप-विभाग का नाम </th>
										
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="count" value="0" scope="page" />
									<c:forEach items="${subDeptMasterList}" var="fl">
										<tr class="odd gradeX">
											<td><c:set var="count" value="${count + 1}" scope="page" />
												<c:out value="${count}" /></td>
											<td>${fl.modelEnglish.deptId.deptName}:${fl.modelHindi.deptId.deptName}</td>
											<td><c:out value="${fl.modelEnglish.subDeptName}" /></td>
											<td><c:out value="${fl.modelHindi.subDeptName}" /></td>
                                            <td><a href="<c:url value="/subDeptMaster?commonId=${fl.modelEnglish.commonId} "/>"
											 class="btn-floating btn-sm blue-gradient" title="Edit"><i class="fa fa-edit"></i></a>
											    <a href="<c:url value="/deleteSubDeptMaster?commonId=${fl.modelEnglish.commonId}" /> "
											     class="btn-floating btn-sm blue-gradient" onclick="return confirm('Do you want to delete the record?')" title="Delete">
											          <i class="fa fa-trash-o"></i>     
											    </a>
											</td>
											
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
		</section>
		<!-- /#page-wrapper -->
</div>

	</main>

	<%@include file="footer.jsp"%>
</body>

</html>
