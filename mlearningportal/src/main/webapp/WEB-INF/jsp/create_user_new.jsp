<!DOCTYPE html>
<html lang="en">
<head>

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>User Registration</title>


<script type="text/javascript">
function getOfficeList(companyCommonId) {

	$('#officeId').find('option').remove().end().append(
			'<option value="0">Select Office</option>').val('');
	$('.officeLoaderImage').show();
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
				$('.officeLoaderImage').hide();
				$('#officeId').append(opt);
				$('#officeId').material_select();
			});
}

function getDeptList(officeCommonId) {

	$('#deptId').find('option').remove().end().append(
			'<option value="0">Select Department</option>').val('');
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

function getDivisionList(deptCommonId) {

	$('#divisionId').find('option').remove().end().append(
			'<option value="0">Select Division</option>').val('');
	$.ajax({
		type : "POST",
		url : "get_division_list",
		data : {
			"deptCommonId" : deptCommonId
		}

	}).done(
			function(divisionList) {
				document.getElementById("divisionId").disabled = false;
				var opt = '';
				for (i in divisionList) {
					opt += '<option value="'+divisionList[i].modelEnglish.commonId+'">'
							+ divisionList[i].modelEnglish.name +'::'+ divisionList[i].modelHindi.name + '</option>';
				}

				$('#divisionId').append(opt);
				$('#divisionId').material_select();
			});
}



function getDesignationList(deptCommonId) {

	$('#designation').find('option').remove().end().append(
			'<option value="0">Select Designation</option>').val('');
	$.ajax({
		type : "POST",
		url : "get_designation_list",
		data : {
			"deptCommonId" : deptCommonId
		}

	}).done(
			function(designationList) {
			
				var opt = '';
				for (i in designationList) {
					opt += '<option value="'+designationList[i].modelEnglish.commonId+'">'
							+ designationList[i].modelEnglish.name +'::'+ designationList[i].modelHindi.name + '</option>';
				}

				$('#designation').append(opt);
				$('#designation').material_select();
			});
}
	
function getManagerList(deptCommonId) {

	$('#manager123').find('option').remove().end().append(
			'<option value="0">Select Manager</option>').val('');
	$.ajax({
		type : "POST",
		url : "get_manager_list",
		data : {
			"deptCommonId" : deptCommonId
		}

	}).done(
			function(managerList) {
				
				var opt = '';
				for (i in managerList) {
					opt += '<option value="'+managerList[i].userId+'">'
							+ managerList[i].firstName +' '+managerList[i].middleName +' ' +managerList[i].lastName+ '</option>';
				}

				$('#manager123').append(opt);
				$('#manager123').material_select();
			});
}


function checkEmailExist(val) {
	//alert('sdsds='+val);

	$.ajax({		
		url : 'existEmail?email=' + val,

		success : function(response) {
			
			if (response == true) {
				document.getElementById("isEmailExist").style.color = "#ff0000";
				$("#isEmailExist").text("Email Already Exist !");

			} else {
				$("#isEmailExist").text("");
			}
		}
	});
};

function checkContactExist(val) {
	
	$.ajax({		
		url : 'existContact?contact=' + val,

		success : function(response) {
			
			if (response == true) {
				document.getElementById("isContactExist").style.color = "#ff0000";
				$("#isContactExist").text("Contact Already Exist !");

			} else {
				$("#isContactExist").text("");
			}
		}
	});
};

</script>


</head>

<body class="fixed-sn white-skin" onload="disableDropdown()">
<%@include file="header.jsp"%>

<script type="text/javascript">
	function disableDropdown() {
		if(<c:out value='${divisionMaster.modelHindi.id != null}' />){
			$('#officeId').prop('disabled', false);
		$('#deptId').prop('disabled', false);
		$('#divisionId').prop('disabled', false);
			
				
		}else{
			
		$('#officeId').prop('disabled', true);
		$('#deptId').prop('disabled', true);
		$('#divisionId').prop('disabled', true);
		
	}
	}
	</script>
<%@include file="sidenev.jsp"%>
    <main>
    	<div class="container-fluid">
        	<section class="">
            	<div class="card-body">
                	<h1 class="bg-color heading">User Registration</h1>
                    <!-- <h5 class="pb-5">Input fields</h5> -->
                    <form:form class="container" action="save_update_user_new" enctype="multipart/form-data"
								modelAttribute="registration" novalidate="" method="post" id="myForm"
								onsubmit="return createUserValidation();">
								
						<form:hidden path="userId"/>
						
						<c:if test="${not empty Message}">
							<div class="alert alert-success">
								<a href="#" class="close" data-dismiss="alert">&times;</a><strong>
								${Message};</strong>
							</div>
						</c:if>
								
								

                    
                    	<div class="col-md-4">
                            <div class="input-group-prepend">
                             <label style="width: 100%" for="firstName" data-error="Only characters with atleast 3 letter">First Name<span style="color: red;">*</span></label>         
                                <form:input type="text" class="form-control validate" path="firstName" 
											id="firstName"  maxlength="15"/>
                                                      
                            </div>
                            <span id="errorFirstName" style="color: #ff0000; font-size: 12px; "></span>
                        </div>
                        
                        <div class="col-md-4">
                            <div class="input-group-prepend">
                            <label style="width: 100%" for="middleName" data-error="Only characters with atleast 3 letter">Middle Name</label>      
                                <form:input type="text" class="form-control validate" path="middleName"
										id="middleName"	 maxlength="15"/>                          
                            </div>
                               <span id="errorMiddleName" style="color: #ff0000; font-size: 12px; "></span>
                        </div>
                        
                        <div class="col-md-4">
                            <div class="input-group-prepend">
                             <label style="width: 100%" for="lastName" data-error="Only characters with atleast 3 letter">Last Name<span style="color: red;">*</span></label>
                                <form:input type="text" class="form-control" path="lastName" id="lastName"/>
                                </div>
                            <span id="errorLastName" style="color: #ff0000; font-size: 12px; "></span>
                        </div>                        
                   
                    
                    
                    	<div class="col-md-4 mb-4">
                            <div class="input-group-prepend">
                            <label style="width: 100%" for="email1" data-error="In-valid email format">Email<span style="color: red;">*</span></label>
                               <form:input type="text" class="form-control validate" path="email"
											id="email1" onblur="checkEmailExist(this.value)"/>
                                </div>
                            <span id="errorEmail" style="color: #ff0000; font-size: 12px; "></span>
                            <span id="isEmailExist"></span>
                        </div>
                        
                        <div class="col-md-4 mb-4">
                            <div class="input-group-prepend">
                            <label style="width: 100%" for="password" data-error="Must contain at least one number and one uppercase and lowercase letter, and at least 6 or more characters">Password<span style="color: red;">*</span></label>
                                <form:input type="text" class="form-control validate" path="password"
											 id="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"/>
                                </div>
                            <span id="errorPassword" style="color: #ff0000; font-size: 12px; "></span>
                        </div>
                        
                        <div class="col-md-4 mb-4">
                            <div class="input-group-prepend">
                            <label style="width: 100%" for="contact" data-error="10 digit number" >Mobile No<span style="color: red;">*</span></label>
                                <form:input type="text" id="contact" class="form-control validate" path="phone"
											pattern="[0-9]{10}" maxlength="10" onblur="checkContactExist(this.value)"/>
                                 </div>
                            <span id="errorContact" style="color: #ff0000; font-size: 12px; "></span>
                            <span id="isContactExist"></span>
                        </div>                        
                   
                    
                    				
					  <div class="col-md-4 mb-4">
                         <div class="input-group-prepend">
                         <label style="width: 100%" for="contact" data-error="10 digit number" >Company<span style="color: red;">*</span></label>
                            <form:select class="mdb-select validate" required="true"
							   path="companyId.commonId" id="companyId" onchange="getOfficeList(this.value);">
								<form:option value="0">Select Company</form:option>
								<c:forEach items="${companyMasterList}" var="cml">
									<form:option value="${cml.modelEnglish.commonId}">${cml.modelEnglish.name}:${cml.modelHindi.name}</form:option>
								</c:forEach>
				            </form:select>
                         </div>
                         <span id="errorCompany" style="color: #ff0000; font-size: 12px; "></span>
                      </div>
                      <img class="officeLoaderImage" src="images/ajax-loader.gif" style="display:none; margin-top:12px;" width="20px" height="20px">
                      <div class="col-md-4 mb-4">
                          <div class="input-group-prepend">
                         <label style="width: 100%" for="contact" data-error="10 digit number" >Office<span style="color: red;">*</span></label>
                            <form:select class="mdb-select validate" required="true"
							   path="officeId.commonId" id="officeId" onchange="getDeptList(this.value);">
								<form:option value="0">Select Office</form:option>
								<c:forEach items="${officeMasterList}" var="oml">
									<form:option value="${oml.modelEnglish.commonId}">${oml.modelEnglish.name}:${oml.modelHindi.name}</form:option>
								</c:forEach>
				            </form:select>
                         </div>
                         <span id="errorOffice" style="color: #ff0000; font-size: 12px; "></span>
                      </div>
                      <img class="deptLoaderImage" src="images/ajax-loader.gif" style="display:none; margin-top:12px;" width="20px" height="20px">
                      <div class="col-md-3 mb-4">
                          <div class="input-group-prepend">
                         <label style="width: 100%" for="contact" data-error="10 digit number" >Department<span style="color: red;">*</span></label>
                            <form:select class="mdb-select validate" required="true"
							   path="deptId.commonId" id="deptId" onchange="getDivisionList(this.value);">
								<form:option value="0">Select Department</form:option>
								<c:forEach items="${deptList}" var="dml">
									<form:option value="${dml.modelEnglish.commonId}">${dml.modelEnglish.deptName}:${dml.modelHindi.deptName}</form:option>
								</c:forEach>
				            </form:select>
                         </div>
                         <span id="errorDept" style="color: #ff0000; font-size: 12px; "></span>
                      </div>
                      
                  
                    
                      <div class="col-lg-4 col-md-6 mb-4">
                       <div class="input-group-prepend">
                         <label style="width: 100%" for="contact" data-error="10 digit number" >Division<span style="color: red;">*</span></label>
                            <form:select class="mdb-select validate" id="divisionId"
							   path="divisionId.commonId" >
							  <form:option value="0">Select Division</form:option>
							  <c:forEach items="${divisionMasterList}" var="dl">
								<form:option value="${dl.modelEnglish.commonId}">${dl.modelEnglish.name} :: ${dl.modelHindi.name}</form:option>
							  </c:forEach>
				           </form:select>
				           <span id="errorDept" style="color: #ff0000; font-size: 12px; "></span>
                        </div>
                        </div>
                        
                        <div class="col-lg-4 col-md-6 mb-4">
                         <div class="input-group-prepend">
                         <label style="width: 100%" for="contact" data-error="10 digit number" >Designation<span style="color: red;">*</span></label>
                            <form:select class="mdb-select validate"
								path="designationId.commonId"  id="designation">
								<form:option value="0">Select Designation</form:option>
								<c:forEach items="${designationList}" var="cl">
									<form:option value="${cl.modelEnglish.commonId}">${cl.modelEnglish.name}::${cl.modelHindi.name}</form:option>
								</c:forEach>
							</form:select>
							<span id="errorDesignation" style="color: #ff0000; font-size: 12px; "></span>
                        </div>
                        </div>

                        <div class="col-lg-4 col-md-6 mb-4">
                         <div class="input-group-prepend">
                         <label style="width: 100%" for="contact" data-error="10 digit number" >Manager<span style="color: red;">*</span></label>
                            <form:select class="mdb-select validate"
								path="managerId.userId"  id="manager123">
								<form:option value="0">Select Manager</form:option>
								<c:forEach items="${managerList}" var="cl">
									<form:option value="${cl.userId}">${cl.firstName} ${cl.middleName} ${cl.lastName}</form:option>
								</c:forEach>
							</form:select>
							<span id="errorManager" style="color: #ff0000; font-size: 12px; "></span>
                        </div>
                        </div>
                       
                      
                    
                         <div class="col-lg-4 col-md-6 mb-4">
	                        <div class="input-group-prepend">
	                           <div class="file-field">
	                               <div class="btn btn-primary btn-sm float-left">
	                                   <span>Choose file</span>
	                                   <input type="file" name="uploadProfileImage">
	                               </div>
	                               <div class="file-path-wrapper">
	                                   <input class="file-path validate"  required="required" readonly="readonly" type="text" placeholder="Upload your image">
	                               </div>
	                               
	                           </div>
	                         </div>
                         </div>
                           <div class="col-md-4 mb-4">
                          <div>
                           <div class="input-group-prepend">
                           <label style="width: 100%" for="empId" data-error="10 digit number" >Employee Id<span style="color: red;">*</span></label>
                                <form:input type="text" id="empId" class="form-control validate" path="empId"
											 maxlength="10" />
                                 </div>
                            </div>
                            <span id="errorEmpId" style="color: #ff0000; font-size: 12px; "></span>
                        </div>   
                   
                    
                    
                                       
                        <div class="col-lg-4 col-md-4 mb-4">
                         <div class="input-group-prepend">
                         <label style="width: 100%" for="contact" data-error="10 digit number" >District<span style="color: red;">*</span></label>
                            <form:select class="mdb-select validate" id="districtId" path="districtId.id">
								<form:option value="0">Select District</form:option>
								<c:forEach items="${districtList}" var="dl">
									<form:option value="${dl.id}">${dl.name}</form:option>
								</c:forEach>
							</form:select>
							<span id="errorDistrict" style="color: #ff0000; font-size: 12px; "></span>
                        </div>
                        </div>
                        
                        <div class="col-lg-4 col-md-6 mb-4">
                         <div class="input-group-prepend">
                         <label style="width: 100%" for="contact" data-error="10 digit number" >Role<span style="color: red;">*</span></label>
                            <form:select class="mdb-select validate"
								path="role" id="role">
								<form:option value="0">Select Role</form:option>
								<form:option value="Admin">Admin</form:option>
								<form:option value="User">User</form:option>								
							</form:select>
							<span id="errorRole" style="color: #ff0000; font-size: 12px; "></span>
                        </div>
                        </div>
                   
                   <div class="col-lg-4 col-md-6 mb-4">
                    <div class="custom-control custom-checkbox">
					  <form:checkbox class="custom-control-input" id="defaultChecked2" path="isManager" value="1"/>
					  <label class="custom-control-label" for="defaultChecked2">Is Manager</label>
					</div></div>
					
                   
                    
                    <div class="form-group" style="margin-top: 20px;">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<input id="btn-signup" type="submit" class="btn blue-gradient btn-rounded" value="Sign Up" />	
						</div>
					</div>                   
                 </form:form> 
                </div>
            </section>
        </div>
    </main>
<%@include file="footer.jsp"%>
  
</body>
</html>