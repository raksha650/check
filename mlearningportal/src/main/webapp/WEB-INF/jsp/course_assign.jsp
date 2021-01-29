<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Course Assign</title>
<meta charset="utf-8">

<style>
[type="date"] {
  background:#fff url(https://cdn1.iconfinder.com/data/icons/cc_mono_icon_set/blacks/16x16/calendar_2.png)  97% 50% no-repeat ;
}
[type="date"]::-webkit-inner-spin-button {
  display: none;
}
[type="date"]::-webkit-calendar-picker-indicator {
  opacity: 0;
}
.datestyle {
  border: 1px solid #c4c4c4;
  border-radius: 5px;
  background-color: #fff;
  padding: 3px 5px;
  box-shadow: inset 0 3px 6px rgba(0,0,0,0.1);
 
}
input[type="date"]:before {
    content: attr(placeholder) !important;
    color: #aaa;
    margin-right: 0.5em;

  }
  input[type="date"]:focus:before,
  input[type="date"]:valid:before {
    content: "";
   
  }
  

</style>

</head>

<body onload="disableDropdown()">
<%@include file="header.jsp"%>
<script type="text/javascript">
	function disableDropdown() {
		if(<c:out value='${courseAssignMaster.modelHindi.id != null}' />){
			$('#courseId').prop('disabled', false);
			$('#courseModuleId').prop('disabled', false);
			$('#officeId').prop('disabled', false);
			$('#deptId').prop('disabled', false);
			$('#divisionId').prop('disabled', false);
				
		}else{
			
		$('#courseId').prop('disabled', true);
		$('#courseModuleId').prop('disabled', true);
		$('#officeId').prop('disabled', true);
		$('#deptId').prop('disabled', true);
		$('#divisionId').prop('disabled', true);
	}
	}
	</script>

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
				document.getElementById("courseModuleId").disabled = false;
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
		$('.loaderImage').show();
		$.ajax({
			type : "POST",
			url : "get_course_list",
			data : {
				"courseCategoryCommonId" : courseCategoryCommonId
			}

		}).done(
				function(courseCategoryList) {
					document.getElementById("courseId").disabled = false;
					var opt = '';
					opt += '<option value="0"> Select Course Category </option>';
					for (i in courseCategoryList) {
						opt += '<option value="'+courseCategoryList[i].modelEnglish.commonId+'">'
								+ courseCategoryList[i].modelEnglish.name +'::'+ courseCategoryList[i].modelHindi.name + '</option>';
					}
					$('.loaderImage').hide();
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

function getDivisionList(deptCommonId) {
	
	$('#divisionId').find('option').remove().end().append(
			'<option value="0">Select Division:</option>').val('');
	$('.divisionLoaderImage').show();
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
					//alert(divisionList[i].modelEnglish.commonId);
					opt += '<option value="'+divisionList[i].modelEnglish.commonId+'">'
							+ divisionList[i].modelEnglish.name +'::'+ divisionList[i].modelHindi.name + '</option>';
				}
				$('.divisionLoaderImage').hide();
				$('#divisionId').append(opt);
				$('#divisionId').material_select();
			});
}

</script>
	<div class="container-fluid">
        	<section class="section card mb-5">
            	<div class="card-body">
                	<h1 class="bg-color heading">Course Assign</h1>
                    <!-- <h5 class="pb-5">Input fields</h5> -->
                    
                    <c:if test="${not empty success}">
						<div class="alert alert-success" id="success">
							<a href="#" class="close" data-dismiss="alert">&times;</a><strong>
							${success};</strong>
						</div>
					</c:if>
					<!-- onsubmit="return createDivisionValidation();" -->
					<form:form action="saveOrUpdateCourseAssignMaster" id="myForm"
						modelAttribute="courseAssignMaster" method="post" onsubmit="return createCourseAssignValidation();">
						<%-- onsubmit="return createCourseAssignValidation();"> --%>
						<form:hidden path="modelHindi.id" />
						<form:hidden path="modelEnglish.id" />
						<form:hidden path="modelEnglish.commonId" />
						<form:hidden path="modelHindi.commonId" />
					
					<div class="row">
					   <div class="col-lg-4 col-md-3">
                            <form:select class="mdb-select" path="modelEnglish.courseId.courseCategoryId.commonId"
                             required="true" onchange="getCourse(this.value)" id="courseCategory">
									<form:option value="0">Select Course Category</form:option>
									<c:forEach items="${courseCategoryList}" var="ccl">
										<form:option value="${ccl.modelEnglish.commonId}">${ccl.modelEnglish.courseType} :: ${ccl.modelHindi.courseType}</form:option>
									</c:forEach>
							</form:select>
							<span id="errorCourseCategory" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                        </div>
                        
                        <img class="loaderImage" src="images/ajax-loader.gif" style="display:none; margin-top:12px;" width="20px" height="20px">
                        
                        <div class="col-lg-4 col-md-3">
                            <form:select class="mdb-select" path="modelEnglish.courseId.commonId"
                             required="true" id="courseId">
									<form:option value="0">Select Course</form:option>
									<c:forEach items="${courseMasterList}" var="ccl">
										<form:option value="${ccl.modelEnglish.commonId}">${ccl.modelEnglish.name} :: ${ccl.modelHindi.name}</form:option>
									</c:forEach>
							</form:select>
							<span id="errorCourse" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                        </div>
                        
                        
					                        
                        <%-- <div class="col-lg-4 col-md-3">
                            <form:select class="mdb-select" path="modelEnglish.courseModuleId.commonId" 
                            required="true" id="courseModuleId">
									<form:option value="0">Select Course Module</form:option>
									<c:forEach items="${courseModuleList}" var="ccl">
										<form:option value="${ccl.modelEnglish.commonId}">${ccl.modelEnglish.name} :: ${ccl.modelHindi.name}</form:option>
									</c:forEach>
							</form:select>
							<span id="errorCourseModule" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                        </div> --%>
                    </div>
					
					
					<div class="row">						
					  <div class="col-md-4">
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
                      <img class="officeLoaderImage img-responsive" src="images/ajax-loader.gif" style="display:none; margin-top: 35px;" width="20px" height="20px">
                      <div class="col-md-4">
                         <div class="md-form">
                            <form:select class="mdb-select validate"
							   path="modelEnglish.officeId.commonId" id="officeId" onchange="getDeptList(this.value);" >
								<form:option value="0">Select Office:ऑफिस का चयन करें </form:option>
								<c:forEach items="${officeMasterList}" var="oml">
									<form:option value="${oml.modelEnglish.commonId}">${oml.modelEnglish.name}:${oml.modelHindi.name}</form:option>
								</c:forEach>
				            </form:select>
                         </div>
                      <!--    <span id="errorOffice" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span> -->
                      </div>
                      <img class="deptLoaderImage" src="images/ajax-loader.gif" style="display:none; margin-top:35px;" width="20px" height="20px">
                      <div class="col-md-3">
                         <div class="md-form">
                            <form:select class="mdb-select validate"
							   path="modelEnglish.deptId.commonId" id="deptId" onchange="getDivisionList(this.value);" >
								<form:option value="0">Select Department:विभाग का चयन करें </form:option>
								<c:forEach items="${deptMasterList}" var="dml">
									<form:option value="${dml.modelEnglish.commonId}">${dml.modelEnglish.deptName}:${dml.modelHindi.deptName}</form:option>
								</c:forEach>
				            </form:select>
                         </div>
                        <!--  <span id="errorDept" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span> -->
                      </div>
                      
                    </div>  
					
					<div class="row">	
					 <img class="divisionLoaderImage" src="images/ajax-loader.gif" style="display:none; margin-top:35px;" width="20px" height="20px">
					 <div class="col-md-4">
                         <div class="md-form">
                            <form:select class="mdb-select validate"
							   path="modelEnglish.divisionId.commonId" id="divisionId">
								<form:option value="0">Select Division:डिवीजन का चयन करें </form:option>
								<c:forEach items="${divisionMasterList}" var="dml">
									<form:option value="${dml.modelEnglish.commonId}">${dml.modelEnglish.name} :: ${dml.modelHindi.name}</form:option>
								</c:forEach>
				            </form:select>
                         </div>
                        <!--  <span id="errorDept" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span> -->
                      </div>						
						
                        
                        <div class="col-md-4">
                            <div class="md-form" style="margin-top:40px;">
                            <form:input type="date"  placeholder="Choose a Date" path="modelEnglish.completionDate" name="dateofbirth" id="dateofbirth" class="datestyle form-controll" required="true" />
                            <%-- <form:input id="input-date date-picker-example" type="date" path="modelEnglish.completionDate" /> --%>
                           
						   <!--   <label for="date-picker-example">Choose completion date...<i class="fa fa-calendar" style="padding-left:150px;"></i></label> -->
                            </div>
                            <span id="errorCompletionDate" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
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
										<th>Division:डिवीजन</th>
										<th>Course:कोर्स </th>									
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="count" value="0" scope="page" />
									<c:forEach items="${courseAssignMasterList}" var="dml">
										<tr class="odd gradeX">
											<td><c:set var="count" value="${count + 1}" scope="page" />
												<c:out value="${count}" /></td>
											<td>${dml.modelEnglish.companyId.name}:${dml.modelHindi.companyId.name}</td>
											<td>${not empty dml.modelEnglish.officeId.name ? dml.modelEnglish.officeId.name : 'All'}:${not empty dml.modelHindi.officeId.name ? dml.modelHindi.officeId.name : 'सभी'}</td>
											<td>${not empty dml.modelEnglish.deptId.deptName ? dml.modelEnglish.deptId.deptName : 'All'}:${not empty dml.modelHindi.deptId.deptName ? dml.modelHindi.deptId.deptName : 'सभी'}</td>
											<td>${not empty dml.modelEnglish.divisionId.name ? dml.modelEnglish.divisionId.name : 'All'}:${not empty dml.modelHindi.divisionId.name ? dml.modelHindi.divisionId.name : 'सभी'}</td>
											<td>${dml.modelEnglish.courseId.name}:${dml.modelHindi.courseId.name}</td> 
                                            <td><a href="<c:url value="/courseAssignMaster?commonId=${dml.modelEnglish.commonId} "/>"
											 class="btn-floating btn-sm blue-gradient" title="Edit">
											        <i class="fa fa-edit"></i></a>
											    <a href="<c:url value="/deleteCourseAssignMaster?commonId=${dml.modelEnglish.commonId}" /> "
											     class="btn-floating btn-sm blue-gradient" onclick="return confirm('Do you want to delete the record?')" title="Delete">
											          <i class="fa fa-trash-o"></i></a>
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
