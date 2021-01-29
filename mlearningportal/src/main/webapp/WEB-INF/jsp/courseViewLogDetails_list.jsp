<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script>

function getCourseModule(courseCommonId) {
	
	$('#courseModuleId').find('option').remove();
	
	$.ajax({
		type : "POST",
		url : "get_course_module_list",
		data : {
			"courseCommonId" : courseCommonId
		}

	}).done(
			function(courseModuleList) {
				
				var opt = '';
				opt += '<option value="0"> Select Course Module </option>';
				for (i in courseModuleList) {
				
					opt += '<option value="'+courseModuleList[i].modelEnglish.commonId+'">'
							+ courseModuleList[i].modelEnglish.name +'::'+ courseModuleList[i].modelHindi.name + '</option>';
				}
				$('#courseModuleId').html(opt);
				$('#courseModuleId').material_select();
			});
}
	
function getCourse(courseCategoryCommonId) {
		
		$('#courseId').find('option').remove();
		
		$.ajax({
			type : "POST",
			url : "get_course_list",
			data : {
				"courseCategoryCommonId" : courseCategoryCommonId
			}

		}).done(
				function(courseCategoryList) {

					var opt = '';
					opt += '<option value="0"> Select Course Category </option>';
					for (i in courseCategoryList) {
						opt += '<option value="'+courseCategoryList[i].modelEnglish.commonId+'">'
								+ courseCategoryList[i].modelEnglish.name +'::'+ courseCategoryList[i].modelHindi.name + '</option>';
					}
					$('#courseId').html(opt);
					$('#courseId').material_select();
				});
}

function getOfficeList(companyCommonId) {

	$('#officeId').find('option').remove().end().append(
			'<option value="0">Select Office:ऑफिस का चयन करें</option>').val('');
	$('.officeLoaderImage').show();
	$.ajax({
		type : "POST",
		url : "get_office_list",
		data : {
			"companyCommonId" : companyCommonId
		}

	}).done(
			function(officeList) {

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
			'<option value="0">Select Division:डिवीजन का चयन करें</option>').val('');
	$.ajax({
		type : "POST",
		url : "get_division_list",
		data : {
			"deptCommonId" : deptCommonId
		}

	}).done(
			function(divisionList) {

				var opt = '';
				for (i in divisionList) {
					opt += '<option value="'+divisionList[i].modelEnglish.commonId+'">'
							+ divisionList[i].modelEnglish.name +'::'+ divisionList[i].modelHindi.name + '</option>';
				}

				$('#divisionId').append(opt);
				$('#divisionId').material_select();
			});
}

function getUserList() {
	//alert("hi----");
	/* var companyCommonId = document.getElementById("companyId").value;
	//alert("companyCommonId--"+companyCommonId);
	var officeCommonId = document.getElementById("officeId").value;
	//alert("officeCommonId--"+officeCommonId);
	var deptCommonId = document.getElementById("deptId").value;
	var divisionCommonId = document.getElementById("divisionCommonId").value;
	//get_user_list_by_companyIdOfficeIdDeptIdDivisionId
	alert("companyCommonId--"+companyCommonId);
	alert("officeCommonId--"+officeCommonId);
	alert("deptCommonId--"+deptCommonId);
	alert("divisionCommonId--"+divisionCommonId); */
	
	//if(deptCommonId != 0 && companyCommonId != 0 && officeCommonId != 0){
  
	
	/* $('#userId').find('option').remove().end().append(
			'<option value="0">Select User</option>').val(''); */
	
	/* var params = {
		    type: "POST",
		    dataType: dataType
		};
	if(companyCommonId != 0){
	    params.url = get_user_list_by_companyId;
	    params.data = companyCommonId;
	} */
	/* if(companyCommonId != 0 && officeCommonId != 0){		
		params.url = get_user_list_by_companyIdOfficeId;
		params.data = companyCommonId;
	    params.data = officeCommonId;		
	} */
	/* if(companyCommonId != 0 && officeCommonId != 0 && deptCommonId != 0){
		url : "get_user_list_by_companyIdOfficeIdDeptId",
		data : {
			"companyCommonId" : companyCommonId,
			"officeCommonId" : officeCommonId,
			"deptCommonId" : deptCommonId
		}
	}
	if(companyCommonId != 0 && officeCommonId != 0 && deptCommonId != 0 && divisionCommonId != 0){
		url : "get_user_list_by_companyIdOfficeIdDeptIdDivisionId",
		data : {
			"companyCommonId" : companyCommonId,
			"officeCommonId" : officeCommonId,
			"deptCommonId" : deptCommonId,
			"divisionCommonId" : divisionCommonId
		}
	} */
	var companyCommonId = document.getElementById("companyId").value;
	var officeCommonId = document.getElementById("officeId").value;
	
	var data;
	var url;
	if(companyCommonId != 0 && officeCommonId != 0 && deptCommonId != 0 && divisionCommonId != 0){		    
		data = {
	    		"companyCommonId" : companyCommonId,
				"officeCommonId" : officeCommonId,
				"deptCommonId" : deptCommonId
	        }
		url = 'get_user_list_by_companyIdOfficeIdDeptIdDivisionId'
	}else if(companyCommonId != 0 && officeCommonId != 0 && deptCommonId != 0){		    
		data = {
	    		"companyCommonId" : companyCommonId,
				"officeCommonId" : officeCommonId,
				"deptCommonId" : deptCommonId
	        }
		url = 'get_user_list_by_companyIdOfficeIdDeptId'
	}else if(companyCommonId != 0 && officeCommonId != 0){		    
		data = {
	    		"companyCommonId" : companyCommonId,
				"officeCommonId" : officeCommonId
	        }
		url = 'get_user_list_by_companyIdOfficeId'
	}else if(companyCommonId != 0){
		data = {
	          'companyCommonId'  : companyCommonId
	        }
		url = 'get_user_list_by_companyId'
	}
	
	$('#userId').find('option').remove().end().append(
	'<option value="0">Select User</option>').val('');
	
	$.ajax({
		type : "POST",
		url : url,
		data :data

	}).done(
			function(userList) {
             
				var opt = '';
				for (i in userList) {					
					opt += '<option value="'+userList[i].userId+'">'
							+ userList[i].firstName+' '+userList[i].middleName+' '+userList[i].lastName+'</option>';
				}

				$('#userId').append(opt);
				$('#userId').material_select();
			});
}



</script>  	        
		<div class="container-fluid mb-5">
       	<section>
       	 <div class="card p-2 mb-3 card-cascade narrower ">
        	<div class="view gradient-card-header blue-gradient narrower py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">
              <div></div>
               <a href="#" class="white-text mx-3">Course Log Details</a>
              <div></div>
            </div>
               
               <form method="post" action="searchCourseViewLogDetails" id="myForm" onsubmit="return myFormSubmitWithConfirmationAndLoader();">
                    <!--Grid row-->
                    <div class="row">
                        <div class="col-lg-4 col-md-12">
                           <select class="mdb-select colorful-select dropdown-primary mx-2" id="companyId" name="companyId"
                            oninvalid="this.setCustomValidity('Choose Office !')" oninput="setCustomValidity('')" onchange="getOfficeList(this.value),getUserList();">
	                           <option value="" disabled selected>Select Company:कंपनी का चयन करें</option>
							   <c:forEach var="cml" items="${companyMasterList}">
							     <option value="${cml.modelEnglish.commonId}">${cml.modelEnglish.name}:${cml.modelHindi.name}</option>
							   </c:forEach>
                           </select>
                        </div>
                        <img class="officeLoaderImage img-responsive" src="images/ajax-loader.gif" style="display:none; margin-top: 35px;" width="20px" height="20px">
                        <div class="col-lg-4 col-md-12">
                           <select class="mdb-select colorful-select dropdown-primary mx-2" name="officeId" oninvalid="this.setCustomValidity('Choose Office !')"
                            oninput="setCustomValidity('')" id="officeId" onchange="getDeptList(this.value),getUserList();">
	                           <option value="" disabled selected>Select Office:ऑफिस का चयन करें</option>
							   <%-- <c:forEach var="userList" items="${userList}">
							     <option value="${userList.userId}">${userList.firstName} ${userList.middleName} ${userList.lastName}</option>
							   </c:forEach> --%>
                           </select>
                        </div>
                        <img class="deptLoaderImage" src="images/ajax-loader.gif" style="display:none; margin-top:35px;" width="20px" height="20px">
                        <div class="col-lg-4 col-md-12">
                           <select class="mdb-select colorful-select dropdown-primary mx-2" id="deptId" name="deptId"
                            oninvalid="this.setCustomValidity('Choose Office !')" oninput="setCustomValidity('')" onchange="getDivisionList(this.value),getUserList();">
	                           <option value="" disabled selected>Select Department:विभाग का चयन करें</option>
							   <%-- <c:forEach var="userList" items="${userList}">
							     <option value="${userList.userId}">${userList.firstName} ${userList.middleName} ${userList.lastName}</option>
							   </c:forEach> --%>
                           </select>
                        </div>
                    </div>
                    <div class="row">   
                        <div class="col-lg-4 col-md-12">
                           <select class="mdb-select colorful-select dropdown-primary mx-2" id="divisionId" name="divisionId"
                            oninvalid="this.setCustomValidity('Choose Office !')" oninput="setCustomValidity('')" onchange="getUserList();">
	                           <option value="" disabled selected>Select Division:डिवीजन का चयन करें </option>
							   <%-- <c:forEach var="userList" items="${userList}">
							     <option value="${userList.userId}">${userList.firstName} ${userList.middleName} ${userList.lastName}</option>
							   </c:forEach> --%>
                           </select>
                        </div>
                        
                        <div class="col-lg-4 col-md-12">
                           <select class="mdb-select colorful-select dropdown-primary mx-2" id="userId" name="userId"
                            oninvalid="this.setCustomValidity('Choose Office !')" oninput="setCustomValidity('')">
	                           <option value="" disabled selected>Select User </option>
							   <%-- <c:forEach var="userList" items="${userList}">
							     <option value="${userList.userId}">${userList.firstName} ${userList.middleName} ${userList.lastName}</option>
							   </c:forEach> --%>
                           </select>
                        </div>
                      </div>
                        <%-- <div class="col-lg-5 col-md-12">
                           <select class="mdb-select colorful-select dropdown-primary mx-2" id="courseModuleId" name="courseModuleId" oninvalid="this.setCustomValidity('Choose Office !')" oninput="setCustomValidity('')">
	                           <option value="" disabled selected>Select Course Module</option>
							   <c:forEach var="courseModuleList" items="${courseModuleList}">
							     <option value="${courseModuleList.id}">${courseModuleList.name}</option>
							   </c:forEach>
                           </select>
                        </div>  --%>           
                    
                    <div class="row">
                        <div class="col-lg-5 col-md-6" style="margin-top: 50px;">
                            <a class="btn blue-gradient btn-rounded btn-sm" href="dashboard"
								title="Cancel" type="button">Back</a>
							<input type="submit" name="search" value="Search" id="btn-signup" class="btn blue-gradient btn-rounded btn-sm">							
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
											<th>View Date</th>
											<th>User</th>
											<th>Course Module</th>
											<th>Start Time</th>
											<th>End Time</th>
											<th>Spend Time</th>
											<th>Is Finished</th>
                                        </tr>
                                    </thead>
                                    <!-- <tfoot>
                                        <tr>
                                            <th>Sr.No</th>
											<th>View Date</th>
											<th>User</th>
											<th>Course Module</th>
											<th>Start Time</th>
											<th>End Time</th>
											<th>Spend Time</th>
											<th>Is Finished</th>
                                        </tr>
                                    </tfoot> -->
                                    <tbody>
                                    <c:set var="count" value="0" scope="page" />
										<c:forEach items="${courseViewLogDetailsList}" var="cVLDL">
											<tr class="odd gradeX">
												<td>
												<input type="hidden" name="ids" value="${fl.userId}">
												<c:set var="count" value="${count + 1}"
														scope="page" /> <c:out value="${count}" /> </td>
												<td><c:out value="${cVLDL.viewDate}" /></td>
												<td><c:out value="${cVLDL.userId.firstName}"/> <c:out value="${cVLDL.userId.middleName}"/> <c:out value="${cVLDL.userId.lastName}"/></td>
												<td><c:out value="${cVLDL.courseModuleId.name}" /></td>
												<td><c:out value="${cVLDL.startTime}" /></td>
												<td><c:out value="${cVLDL.endTime}" /></td>
												<td><c:out value="${cVLDL.totalSpendTime}" /></td>
												<c:if test="${cVLDL.isCourseFinished eq true}">
												 <td>Yes</td>
												</c:if>
												<c:if test="${cVLDL.isCourseFinished eq false}">
												 <td>No</td>
												</c:if>
																								
                           
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
