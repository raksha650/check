<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Department Course Assign Master</title>
<meta charset="utf-8">

<script>
	function getSubDeptList(deptCommonId) {

		$('#subDept').find('option').remove().end().append(
				'<option value="">Select Sub Department</option>').val('');
		$
				.ajax({
					type : "POST",
					url : "get_subdept_list",
					data : {
						"deptCommonId" : deptCommonId
					}

				})
				.done(
						function(subDeptList) {

							var opt = '';
							for (i in subDeptList) {
								opt += '<option value="'+subDeptList[i].modelEnglish.commonId+'">'
										+ subDeptList[i].modelEnglish.subDeptName
										+ '::'
										+ subDeptList[i].modelHindi.subDeptName
										+ '</option>';
							}

							$('#subDept').append(opt);
							$('#subDept').material_select();
						});
	}
</script>
</head>

<body>


	<%@include file="header.jsp"%>
	<%@include file="sidenev.jsp"%>
	<main>
	<div class="container-fluid">
		<section class="section card mb-5">
			<div class="card-body">
				<h1 class="bg-color heading">Department Course Assign Master</h1>
				<!-- <h5 class="pb-5">Input fields</h5> -->

				<c:if test="${not empty success}">
					<div class="alert alert-success">
						<a href="#" class="close" data-dismiss="alert">&times;</a><strong>
							${success}</strong>
					</div>
				</c:if>
				<c:if test="${not empty failure}">
					<div class="alert alert-danger">
						<a href="#" class="close" data-dismiss="alert">&times;</a><strong>
							${failure}</strong>
					</div>
				</c:if>
				<form:form action="saveOrUpdateDeptCourseMaster" id="myForm" onsubmit="return myFormSubmitWithConfirmationAndLoader();"
					modelAttribute="deptCourseMaster" method="post" name="targets"
					>
					<form:hidden path="modelHindi.id" />
					<form:hidden path="modelEnglish.id" />
					<form:hidden path="modelEnglish.commonId" />
					<form:hidden path="modelHindi.commonId" />
					<form:hidden path="modelEnglish.createdBy" />
					<form:hidden path="modelHindi.createdBy" />



					<div class="row">

						<div class="col-lg-4 col-md-6 mb-4">
							<form:select class="mdb-select" id="deptId"
								path="modelEnglish.deptId.commonId"
								onchange="getSubDeptList(this.value);">
								<form:option value="0">Select Department</form:option>
								<c:forEach items="${deptMasterList}" var="dl">
									<form:option value="${dl.modelEnglish.commonId}">${dl.modelEnglish.deptName} :: ${dl.modelHindi.deptName}</form:option>
								</c:forEach>
							</form:select>
							<span id="errorDept" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
						</div>
						<div class="col-lg-4 col-md-6 mb-4">
							<form:select class="mdb-select"
								path="modelEnglish.subDeptId.commonId" id="subDept">
								<form:option value="0">Select Sub Department</form:option>
								<c:forEach items="${subDeptMasterList}" var="cl">
									<form:option value="${cl.modelEnglish.commonId}">${cl.modelEnglish.subDeptName}::${cl.modelHindi.subDeptName}</form:option>
								</c:forEach>
							</form:select>
							<span id="errorSubDept" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
						</div>
						<div class="col-lg-3 col-md-3">
							<form:select class="mdb-select"
								path="modelEnglish.courseId.commonId" id="course">
								<form:option value="0">Select Course</form:option>
								<c:forEach items="${courseMasterList}" var="ccl">
									<form:option value="${ccl.modelEnglish.commonId}">${ccl.modelEnglish.name} :: ${ccl.modelHindi.name}</form:option>
								</c:forEach>
							</form:select>
							<span id="errorCourse" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
						</div>


						<%-- <div class="md-form col-lg-3 col-md-3">
						  <form:input path="modelEnglish.completionDate" type="text" id="date-picker-example" class="form-control datepicker"/>
						  <label for="date-picker-example">Choose completion date...</label>
						</div> --%>
						<%-- <div class="col-lg-3 col-md-3">
						  <form:input path="modelEnglish.completionDate" type="text" id="date" class="form-control datepicker" data-dtp="dtp_OgW73"/>
						  <label for="date">Choose completion date...</label>
						</div> --%>
						<%-- <div class="col-lg-3 col-md-3">
							<div class="md-form">
								<form:input path="modelEnglish.completionDate"
									placeholder="Selected date" type="date" class="form-control" />

							</div>
						</div> --%>



					</div>
					
					<div class="row">
                    	<div class="col-md-4 mb-4">
                            <div class="md-form">
                               <form:input path="modelEnglish.completionDate" type="text" id="date-picker-example" class="form-control datepicker"/>
						       <label for="date-picker-example">Choose completion date...</label>
                            </div>
                        </div>
                        <span id="errorCompletionDate" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>                      
                    </div>
                    
					<div class="form-group">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<!-- <button id="btn-signup" type="submit"
								class="btn blue-gradient btn-rounded" data-toggle="modal" data-target="#basicExampleModal">
								<i class="icon-hand-right"></i> Save
							</button> -->
							<input id="Save" type="submit" id="btn-signup"
								class="btn blue-gradient btn-rounded" value="Save"/>
							
							<button id="btn-signup" type="reset"
								class="btn blue-gradient btn-rounded">
								<i class="icon-hand-right"></i> Reset
							</button>


						</div>
					</div>
					<br />
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
							<table id="datatables"
								class="table table-striped table-bordered table-responsive-md"
								cellspacing="0" width="100%">

								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Department:विभाग</th>
										<th>SubDepartment:उप-विभाग</th>
										<th>Course:कोर्स</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="count" value="0" scope="page" />
									<c:forEach items="${deptCourseMasterList}" var="fl">
										<tr class="odd gradeX">
											<td><c:set var="count" value="${count + 1}" scope="page" />
												<c:out value="${count}" /></td>
											<td>${fl.modelEnglish.deptId.deptName}:${fl.modelHindi.deptId.deptName}</td>
											<td>${fl.modelEnglish.subDeptId.subDeptName}:${fl.modelHindi.subDeptId.subDeptName}</td>
											<td>${fl.modelEnglish.courseId.name}:${fl.modelHindi.courseId.name}</td>


											<td><a
												href="<c:url value="/deptCourseMaster?commonId=${fl.modelEnglish.commonId} "/>"
												class="btn-floating btn-sm blue-gradient" title="Edit">
													<i class="fa fa-edit"></i>
											</a> <a
												href="<c:url value="/deleteDeptCourseMaster?commonId=${fl.modelEnglish.commonId}" /> "
												class="btn-floating btn-sm blue-gradient"
												onclick="return confirm('Do you want to delete the record?')"
												title="Delete"> <i class="fa fa-trash-o"></i>
											</a></td>

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
//jQuery.noConflict();
jQuery(document).ready(function(){
	jQuery('#Save').click(function(event) {
    	//alert("Hi");
        event.preventDefault();
        var currentForm = jQuery(this).closest('form');
        var deptId = jQuery('#deptId').val();
    	var subDept = jQuery("#subDept").val();
    	var course = jQuery("#course").val();
    	var completionDate = jQuery("#date-picker-example").val();
    	
    	if(deptId == 0){
    		//alert("Please Select department!");
    		document.getElementById("errorDept").innerHTML = "Please Select department!";
    		return false;
    	}else{
    		document.getElementById("errorDept").textContent = "";
    	}
    	if(subDept == 0){
    		//alert("Please Select sub department!");
    		document.getElementById("errorSubDept").innerHTML = "Please Select sub department!";
    		return false;
    	}else{
    		document.getElementById("errorSubDept").textContent = "";
    	}
    	if(course == 0){
    		//alert("Please Select course!");
    		document.getElementById("errorCourse").innerHTML = "Please Select course!";
    		return false;
    	}else{
    		document.getElementById("errorCourse").textContent = "";
    	}
    	if(completionDate == null || completionDate == ""){
    		//alert("Please Choose Completion Date!");
    		document.getElementById("errorCompletionDate").innerHTML = "Please Choose completion date !";
    		return false;
    	}else{
    		document.getElementById("errorCompletionDate").textContent = "";
    	}
    		
        /** Create div element for delete confirmation dialog*/
      var dynamicDialog = jQuery('<div id="conformBox">'+
        '<span class="ui-icon ui-icon-alert" style="float:left; margin:100px 20px 20px 0;">'+
        '</span>Are you sure to save the changes?</div>');      
        
        dynamicDialog.dialog({
                title : "Are you sure?",
                closeOnEscape: true,
                modal : true,
        
               buttons : 
                        [{
                                text : "Yes",
                                click : function() {
                                	jQuery(this).dialog("close");
                                        currentForm.submit();
                                }
                        },
                        {
                                text : "No",
                                click : function() {
                                	jQuery(this).dialog("close");
                                }
                        }]
        });
        return false;
    });
}); 
</script>
	<script type="text/javascript">
	  // Data Picker Initialization
	  jQuery('.datepicker').pickadate();
	  //$('#date').bootstrapMaterialDatePicker({ weekStart : 0, time: false });
	</script>
	
	<!-- <script type="text/javascript">
		$(function() {
			$('#datetimepicker1').datetimepicker({
				locale : 'ru'
			});
		});
	</script>
	<script>
		// Initialize Boostrap-Datepicker
		$('.input-datepicker, .input-group.date, .bootstrap-datepicker-inline')
				.datepicker({
					autoclose : true,
					orientation : "top auto",
					todayBtn : "linked",
					todayHighlight : true
				});
		$('.input-daterange').datepicker({
			autoclose : true,
			todayBtn : "linked",
			todayHighlight : true
		});
	</script> -->

</body>

</html>
