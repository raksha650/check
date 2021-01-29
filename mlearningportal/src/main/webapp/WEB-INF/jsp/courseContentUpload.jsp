<%@page import="com.cinfy.mlearning.model.CourseUploadForm"%>
<html>
<head>
<title>Course Content</title>
<meta charset="utf-8">
<style>
</style>

<script type="text/javascript">
	

	function getCourseModule(courseCommonId) {

		$('#courseModuleId').find('option').remove();
		$('.courseLoaderImage').show();
		$.ajax({
			type : "POST",
			url : "get_course_module_model__list",
			data : {
				"courseCommonId" : courseCommonId
			}

		}).done(
				function(courseModuleList) {
					document.getElementById("courseModuleId").disabled = false;

					var opt = '';
					opt += '<option value=""> Select Course Module </option>';
					for (i in courseModuleList) {
						opt += '<option value="'+courseModuleList[i].id+'">'
								+ courseModuleList[i].name + '</option>';
					}
					$('.courseLoaderImage').hide();
					$('#courseModuleId').html(opt);
					$('#courseModuleId').material_select();

				});
	}

	function getCourse(courseCategoryCommonId) {

		$('#courseId').find('option').remove();
		$('.loaderImage').show();

		$
				.ajax({
					type : "POST",
					url : "get_course_list",
					data : {
						"courseCategoryCommonId" : courseCategoryCommonId
					}

				})
				.done(
						function(courseCategoryList) {
							document.getElementById("courseId").disabled = false;

							var opt = '';
							opt += '<option value=""> Select Course </option>';
							for (i in courseCategoryList) {
								opt += '<option value="'+courseCategoryList[i].modelEnglish.commonId+'">'
										+ courseCategoryList[i].modelEnglish.name
										+ '::'
										+ courseCategoryList[i].modelHindi.name
										+ '</option>';
							}
							$('.loaderImage').hide();
							$('#courseId').html(opt);
							$('#courseId').material_select();
							

						});
	}
</script>

</head>
<body onload="disableDropdown()">

	<script type="text/javascript">
		function ShowFileControls(varRowId) {
			//alert("show");
			var slideFileId = 'slideFile' + varRowId;
			var audioFileId = 'audioFile' + varRowId;
			/* $("#dataTables-example").find("input[id='" + slideFileId+"']").each(function () {
			    $(this).show();
			}); */
			$("#dataTables-example").find("input[id='" + audioFileId + "']")
					.each(function() {
						$(this).show();
					});
		}

		function HideFileControls(varRowId) {
			//alert("hide");
			var slideFileId = 'slideFile' + varRowId;
			var audioFileId = 'audioFile' + varRowId;
			/*  $("#dataTables-example").find("input[id='" + slideFileId + "']").each(function () {
			     $(this).hide();
			 }); */
			$("#dataTables-example").find("input[id='" + audioFileId + "']")
					.each(function() {
						$(this).hide();
					});
		}
	</script>

	<%@include file="header.jsp"%>
	
	
	<script type="text/javascript">
	function disableDropdown() {
		if(<c:out value='${courseUpload.id != null}' />){
			$('#courseId').prop('disabled', false);
			$('#courseModuleId').prop('disabled', false);
				
		}else{
			
		$('#courseId').prop('disabled', true);
		$('#courseModuleId').prop('disabled', true);
	}
	}
	</script>
	
	<%@include file="sidenev.jsp"%>
	<main>
	<div class="container-fluid">
		<section class="section card mb-1">
			<div class="card-body">

				<h1 class="bg-color heading">Course Content Upload</h1>


				<!-- <h5 class="pb-5">Input fields</h5> -->

				<c:if test="${not empty success}">
					<div class="alert alert-success">
						<a href="#" class="close" data-dismiss="alert">&times;</a><strong>Success!</strong>
						${param.success}
					</div>
				</c:if>
				<c:if test="${not empty error}">
					<div class="alert alert-error">
						<a href="#" class="close" data-dismiss="alert">&times;</a><strong>Error!</strong>
						${error}
					</div>
				</c:if>





				<form:form method="POST" id="myForm"
					action="saveOrUpdateCourseContentUpload"
					modelAttribute="courseUpload" enctype="multipart/form-data"
					onsubmit="return createCourseContentUploadValidation();">
					<form:hidden path="audioPath" />
					<form:hidden path="slidePath" />
					<form:hidden path="id" />				
					<form:hidden path="createdBy" />
					<form:hidden path="updatedBy" />
					<div class="row">
						<div class="col-lg-5 col-md-3">
							<form:select class="mdb-select" onchange="getCourse(this.value);"
								id="courseCategory" path="courseModuleId.courseId.courseCategoryId.commonId">
								<form:option value="">Select Course Category</form:option>
								<c:forEach items="${courseCategoryList}" var="ccl">
									<form:option value="${ccl.modelEnglish.commonId}">${ccl.modelEnglish.courseType}:: ${ccl.modelHindi.courseType}</form:option>
								</c:forEach>
							</form:select> <span id="errorCourseCategory"
								style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
						</div>
						<img class="loaderImage" src="images/ajax-loader.gif"
							style="display: none; margin-top: 12px;" width="20px"
							height="20px">
						<div class="col-lg-5 col-md-3 ">
							<form:select class="mdb-select" id="courseId" path="courseModuleId.courseId.commonId"
								onchange="getCourseModule(this.value)">
								<form:option value="">Select Course</form:option>
								<c:forEach items="${courseList}" var="ccl">
									<form:option value="${ccl.modelEnglish.commonId}">${ccl.modelEnglish.name}
										:: ${ccl.modelHindi.name}</form:option>
								</c:forEach>
							</form:select> <span id="errorCourse"
								style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
						</div>
					</div>
					<div class="row">
						<img class="courseLoaderImage" src="images/ajax-loader.gif"
							style="display: none; margin-top: 12px;" width="20px"
							height="20px">
						<div class="col-lg-8 col-md-3">
							<form:select class="mdb-select" id="courseModuleId"	path="courseModuleId.id">
								<option value="">Select Course Module</option>
								<c:forEach items="${courseModuleList}" var="ccl">
									<form:option value="${ccl.id}">${ccl.name}</form:option>
								</c:forEach>
							</form:select>
							<span id="errorCourseModule"
								style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
						</div>
					</div>

					<div class="row">
						<div class="col-lg-4 col-md-3">
							<span>Slide Video/Image</span><br />
							<form:radiobutton path="isImgOrVideo" value="true"
								id="radioVedio" />
							Video &nbsp;&nbsp;
							<form:radiobutton path="isImgOrVideo" value="false"
								id="radioImage" />
							Image
						</div>

						<div class="col-lg-4 col-md-3">
							<span>Slide Video/Image</span>
							<div class="md-form" style="margin-top: 10px;">
								<div class="file-field">
									<div class="btn btn-primary btn-sm float-left">
										<span>Choose file</span>
										<form:input type="file" path="slidePathMultipart" />
									</div>
									<div class="file-path-wrapper">
										<input class="file-path" id="userImg"
											required="required" readonly="readonly" type="text"
											placeholder="Upload Vedio">
									</div>
									<span id="errorUserImg"
										style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
								</div>
								<c:if test="${courseUpload.isImgOrVideo && (not empty courseUpload.slidePath)}">
									<video width="200" controls>
										<source src="${courseUpload.slidePath}" type="video/mp4">
									</video>

								</c:if>
								<c:if test="${not courseUpload.isImgOrVideo && (not empty courseUpload.slidePath)}">
									<img alt="NA" src="${courseUpload.slidePath}" width="400">

								</c:if>

							</div>
							<!-- <input class="form-control" type="file" name="slideFiles" id="slideFile" /> -->
						</div>

						<div class="col-lg-4 col-md-3" id="slideAudio">
							<span>Slide Audio</span>
							<div class="md-form" style="margin-top: 10px;">
								<div class="file-field">
									<div class="btn btn-primary btn-sm float-left">
										<span>Choose file</span>
										<form:input type="file" path="audioPathMultipart" />
									</div>
									<div class="file-path-wrapper">
										<input class="file-path " id="userImg"
											required="required" readonly="readonly" type="text"
											placeholder="Upload Slide">
									</div>
									<span id="errorUserImg"
										style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
								</div>
								<c:if test="${not courseUpload.isImgOrVideo  && (not empty courseUpload.audioPath) }">
									<audio controls id="audio<c:out value='${count}'/>"
										style="height: 85px;" controlsList="nodownload">
										<source src="${courseUpload.audioPath}" type="audio/ogg">
									</audio>
								</c:if>
							</div>
							<!-- <input class="form-control" type="file" name="audioFiles" id="audioFile"/> -->
						</div>
					</div>


					<div class="form-group">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<button id="btn-signup" type="submit"
								class="btn blue-gradient btn-rounded">
								<i class="icon-hand-right"></i> Save
							</button>
							<c:if test="${courseUpload.id!= null }">
							<a id="btn-signup1" href="showCourseContentUpload?courseModuleId=${courseUpload.courseModuleId.id }"
								class="btn blue-gradient btn-rounded btn-loaderClass">
								<i class="icon-hand-right"></i> Cancel
							</a>
							</c:if>
							<c:if test="${courseUpload.id== null }">
							<button id="btn-signup1" type="reset"
								class="btn blue-gradient btn-rounded">
								<i class="icon-hand-right"></i> Reset
							</button>
							</c:if>
						</div>
					</div>
				</form:form>
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
			wheelSpeed : 2,
			wheelPropagation : true,
			minScrollbarLength : 20
		});

		$(document).ready(function() {
			$('#datatables').DataTable();
		});

		// Material Select Initialization
		$(document).ready(function() {
			$('select[name="datatables_length"]').material_select();
		});

		$("#radioVedio").click(function() {
			$("#slideAudio").hide();
		});

		$("#radioImage").click(function() {
			$("#slideAudio").show();
		});
	</script>

</body>
</html>