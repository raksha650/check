<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Office Master</title>
<meta charset="utf-8">
</head>

<body>
<%@include file="header.jsp"%>
<%@include file="sidenev.jsp"%>
<main>
<script>
function checkDesignationExist(val) {
	
	var companyCommonId = document.getElementById("companyId").value;
	
	if(companyCommonId != 0){
		$.ajax({		
			url : 'existOffice?companyCommonId='+companyCommonId+'&office='+val,
			success : function(response) {
				if (response == true) {
					document.getElementById("isOfficeExist").style.color = "#ff0000";
					$("#isOfficeExist").text("Office Already Exist !");
	
				} else {
					$("#isOfficeExist").text("");
				}
			}
		});
	}else{
		alert("Please Select Department");
		return false;
	};
}
</script>
	<div class="container-fluid">
        	<section class="section card mb-5">
            	<div class="card-body">
                	<h1 class="bg-color heading">Office Master</h1>
                    <!-- <h5 class="pb-5">Input fields</h5> -->
                    
                    <c:if test="${not empty success}">
						<div class="alert alert-success">
							<a href="#" class="close" data-dismiss="alert">&times;</a><strong>
							${success}</strong>
						</div>
					</c:if>
					<form:form action="saveOrUpdateOfficeMaster" id="myForm"
						modelAttribute="officeMaster" method="post"
						onsubmit="return createOfficeValidation();">
						<form:hidden path="modelHindi.id" />
						<form:hidden path="modelEnglish.id" />
						<form:hidden path="modelEnglish.commonId" />
						<form:hidden path="modelHindi.commonId" />
					
					

				<div class="row">						
					<div class="col-md-4 mb-4">
                         <div class="md-form">
                            <form:select class="mdb-select validate" required="true"
							   path="modelEnglish.companyId.commonId" id="companyId">
								<form:option value="0">Select Company:कंपनी का चयन करें </form:option>
								<c:forEach items="${companyMasterList}" var="cml">
									<form:option value="${cml.modelEnglish.commonId}">${cml.modelEnglish.name}:${cml.modelHindi.name}</form:option>
								</c:forEach>
				            </form:select>
                         </div>
                         <span id="errorCompany" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                      </div>
							
						<div class="col-md-4 ">
	                          <div class="md-form">
	                              <form:input type="text" class="form-control validate" path="modelEnglish.name" pattern="[A-Za-z ]{3,}" maxlength="50"
										id="officeName1" required="true" onblur="checkCompanyExist(this.value)"/>
	                              <label style="width: 100%" for="officeName1" data-error="Only characters with min 3 max 50 length" >Enter Office Name</label>
	                          </div>
	                          <span id="isOfficeExist"></span>
	                      </div>
	                      <div class="col-md-4">
	                          <div class="md-form">
	                              <form:input type="text" class="form-control validate" path="modelHindi.name"
										id="officeName2"  maxlength="100" required="true"/>
	                              <label style="width: 100%" for="officeName2" >ऑफिस का नाम </label>
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
						<br/>
					</form:form>
				</div>
				
				<div class="modal" id="showLoader" style="display: none">
				    <div class="center">
				        <img alt="" src="images/loader.gif" width="150px" height="150px"/>
				    </div>
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
										<th>Sr.No.</th>
										<th>Company:कंपनी</th>
										<th>Office Name</th>
										<th>ऑफिस का नाम </th>										
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="count" value="0" scope="page" />
									<c:forEach items="${officeMasterList}" var="fl">
										<tr class="odd gradeX">
											<td><c:set var="count" value="${count + 1}" scope="page" />
												<c:out value="${count}" /></td>
												<td>${fl.modelEnglish.companyId.name}:${fl.modelHindi.companyId.name}</td>
											<td><c:out value="${fl.modelEnglish.name}" /></td>
											<td><c:out value="${fl.modelHindi.name}" /></td>
										
                                            <td><a href="<c:url value="/officeMaster?commonId=${fl.modelEnglish.commonId} "/>"
											 class="btn-floating btn-sm blue-gradient" title="Edit">
											        <i class="fa fa-edit"></i></a>
											    <a href="<c:url value="/deleteOfficeMaster?commonId=${fl.modelEnglish.commonId}" /> "
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
<script>
$(document).ready(function(){
    
    if($("success").is(":visible")){
    	$('#modal').hide();
    }
});
</script>
</body>
</html>
