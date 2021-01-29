<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Division Master</title>
<meta charset="utf-8">
</head>

<body onload="disableDropdown()">
<%@include file="header.jsp"%>
<script type="text/javascript">
	function disableDropdown() {
		if(<c:out value='${divisionMaster.modelHindi.id != null}' />){
			$('#officeId').prop('disabled', false);
		$('#deptId').prop('disabled', false);
			
				
		}else{
			
		$('#officeId').prop('disabled', true);
		$('#deptId').prop('disabled', true);
		
	}
	}
	</script>
<%@include file="sidenev.jsp"%>
<main>
<script>
function getOfficeList(companyCommonId) {

	$('#officeId').find('option').remove().end().append(
			'<option value="0">Select Office:ऑफिस का चयन करें</option>').val('');
	$('.loaderImage').show();
	$.ajax({
		type : "POST",
		url : "get_office_list",
		data : {
			"companyCommonId" : companyCommonId
		}

	}).done(
			function(officeList) {
				document.getElementById("officeId").disabled = false;
				var opt = '';
				for (i in officeList) {
					opt += '<option value="'+officeList[i].modelEnglish.commonId+'">'
							+ officeList[i].modelEnglish.name +'::'+ officeList[i].modelHindi.name + '</option>';
				}
				$('.loaderImage').hide();
				$('#officeId').append(opt);
				$('#officeId').material_select();
			});
}

function getDeptList(officeCommonId) {

	$('#deptId').find('option').remove().end().append(
			'<option value="0">Select Department:विभाग का चयन करें</option>').val('');
	$('.deptLoaderImage').show();
	$.ajax({
		type : "POST",
		url : "get_dept_list",
		data : {
			"officeCommonId" : officeCommonId
		}

	}).done(
			function(deptList) {
				document.getElementById("deptId").disabled = false;
				var opt = '';
				for (i in deptList) {
					opt += '<option value="'+deptList[i].modelEnglish.commonId+'">'
							+ deptList[i].modelEnglish.deptName +'::'+ deptList[i].modelHindi.deptName + '</option>';
				}
				$('.deptLoaderImage').hide();
				$('#deptId').append(opt);
				$('#deptId').material_select();
			});
}

function checkDivisionExist(val) {
	var companyCommonId = document.getElementById("companyId").value;
	var officeCommonId = document.getElementById("officeId").value;
	var deptCommonId = document.getElementById("deptId").value;
	
	if(deptCommonId != 0 && companyCommonId != 0 && officeCommonId != 0){
		$.ajax({		
			url : 'existDivision?companyCommonId='+companyCommonId+'&officeCommonId='+officeCommonId+'&deptCommonId='+deptCommonId+'&division='+val,
			success : function(response) {
				if (response == true) {
					document.getElementById("isDivisionExist").style.color = "#ff0000";
					$("#isDivisionExist").text("Division Already Exist !");
	
				} else {
					$("#isDivisionExist").text("");
				}
			}
		});
	}else{
		alert("Please Select Company,Office and Department !");
		return false;
	};
};


</script>
	<div class="container-fluid">
        	<section class="section card mb-5">
            	<div class="card-body">
                	<h1 class="bg-color heading">Division Master</h1>
                    <!-- <h5 class="pb-5">Input fields</h5> -->
                    
                    <c:if test="${not empty success}">
						<div class="alert alert-success">
							<a href="#" class="close" data-dismiss="alert">&times;</a><strong>
							${success};</strong>
						</div>
					</c:if>
					<form:form action="saveOrUpdateDivisionMaster" id="myForm" 
						modelAttribute="divisionMaster" method="post"
						onsubmit="return createDivisionValidation();">
						<form:hidden path="modelHindi.id" />
						<form:hidden path="modelEnglish.id" />
						<form:hidden path="modelEnglish.commonId" />
						<form:hidden path="modelHindi.commonId" />
					
					<div class="row">						
					  <div class="col-md-4 mb-4">
                         <div class="md-form">
                            <form:select class="mdb-select validate" required="true"
							   path="modelEnglish.companyId.commonId" id="companyId" onchange="getOfficeList(this.value);">
								<form:option value="0">Select Company:कंपनी का चयन करें </form:option>
								<c:forEach items="${companyMasterList}" var="cml">
									<form:option value="${cml.modelEnglish.commonId}">${cml.modelEnglish.name}:${cml.modelHindi.name}</form:option>
								</c:forEach>
				            </form:select>
                         </div>
                         <span id="errorCompany" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                      </div>
                      <img class="loaderImage" src="images/ajax-loader.gif" style="display:none; margin-top:35px;" width="20px" height="20px">
                      <div class="col-md-4 mb-4">
                         <div class="md-form">
                            <form:select class="mdb-select validate" required="true"
							   path="modelEnglish.officeId.commonId" id="officeId" onchange="getDeptList(this.value);">
								<form:option value="0">Select Office:ऑफिस का चयन करें </form:option>
								<c:forEach items="${officeMasterList}" var="oml">
									<form:option value="${oml.modelEnglish.commonId}">${oml.modelEnglish.name}:${oml.modelHindi.name}</form:option>
								</c:forEach>
				            </form:select>
                         </div>
                         <span id="errorOffice" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                      </div>
                      <img class="deptLoaderImage img-responsive" src="images/ajax-loader.gif" style="display:none; margin-top: 35px;" width="20px" height="20px">
                      <div class="col-md-4 mb-4">
                         <div class="md-form">
                            <form:select class="mdb-select validate" required="true"
							   path="modelEnglish.deptId.commonId" id="deptId">
								<form:option value="0">Select Department:विभाग का चयन करें </form:option>
								<c:forEach items="${deptMasterList}" var="dml">
									<form:option value="${dml.modelEnglish.commonId}">${dml.modelEnglish.deptName}:${dml.modelHindi.deptName}</form:option>
								</c:forEach>
				            </form:select>
                         </div>
                         <span id="errorDept" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                      </div>
                      
                    </div>  

					<div class="row">							
						<div class="col-md-4 mb-4">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="modelEnglish.name" pattern="[A-Za-z ]{3,}" maxlength="50"
											id="divisionName" required="true" onblur="checkDivisionExist(this.value)"/>
                                <label style="width: 100%" for="divisionName" data-error="Only characters with min 3 max 50 length" >Enter Division Name</label>
                            </div>
                            <span id="isDivisionExist"></span>
                        </div>
                        <div class="col-md-4 mb-4">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="modelHindi.name"
											id="divisionName1"  maxlength="100" required="true"/>
                                <label style="width: 100%" for="divisionName1" >डिवीजन का नाम </label>
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
										<th>Company:कंपनी</th>
										<th>Office:ऑफिस</th>
										<th>Department:विभाग</th>
										<th>Division Name</th>
										<th>डिवीजन का नाम </th>										
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="count" value="0" scope="page" />
									<c:forEach items="${divisionMasterList}" var="dml">
										<tr class="odd gradeX">
											<td><c:set var="count" value="${count + 1}" scope="page" />
												<c:out value="${count}" /></td>
											<td>${dml.modelEnglish.companyId.name}:${dml.modelHindi.companyId.name}</td>
											<td>${dml.modelEnglish.officeId.name}:${dml.modelHindi.officeId.name}</td>
											<td>${dml.modelEnglish.deptId.deptName}:${dml.modelHindi.deptId.deptName}</td>
											<td><c:out value="${dml.modelEnglish.name}" /></td>
											<td><c:out value="${dml.modelHindi.name}" /></td>										
                                            <td><a href="<c:url value="/divisionMaster?commonId=${dml.modelEnglish.commonId} "/>"
											 class="btn-floating btn-sm blue-gradient" title="Edit">
											        <i class="fa fa-edit"></i>            
											    </a>
											    <a href="<c:url value="/deleteDivisionMaster?commonId=${dml.modelEnglish.commonId}" /> "
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
