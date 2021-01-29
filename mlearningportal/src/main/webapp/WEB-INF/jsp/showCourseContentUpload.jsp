<html>
<head>
<title>Course Content</title>
<meta charset="utf-8">


<script type="text/javascript">

function disableDropdown() {
	document.getElementById("courseId").disabled= true;	
	document.getElementById("courseModuleId").disabled = true;
}

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
					opt += '<option value=""> Select Course </option>';
					for (i in courseCategoryList) {
						opt += '<option value="'+courseCategoryList[i].modelEnglish.commonId+'">'
								+ courseCategoryList[i].modelEnglish.name +'::'+ courseCategoryList[i].modelHindi.name + '</option>';
					}
					$('.loaderImage').hide();
					$('#courseId').html(opt);
					$('#courseId').material_select();
				});
	}
</script>

</head>
<body onload="disableDropdown()">


<%@include file="header.jsp"%>
<%@include file="sidenev.jsp"%> 
<main>  
<div class="container-fluid">
        	<section class="section card mb-1">
            	<div class="card-body">
            		
            			<h1 class="bg-color heading">Show Upload Course Content</h1>
					
                	
                    <!-- <h5 class="pb-5">Input fields</h5> -->
                    
                    <c:if test="${not empty param.success}">
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
                    
                    <form  method="GET" action="showCourseContentUpload" id="myForm" onsubmit="return myFormSubmitLoader();">
					<div class="row">
					   <div class="col-lg-5 col-md-3">
                            <select class="mdb-select" onchange="getCourse(this.value)" id="courseCategory" required>
									<option value="">Select Course Category</option>
									<c:forEach items="${courseCategoryList}" var="ccl">
										<option value="${ccl.modelEnglish.commonId}">${ccl.modelEnglish.courseType} :: ${ccl.modelHindi.courseType}</option>
									</c:forEach>
							</select>
							<span id="errorCourseCategory" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                        </div>
                        <img class="loaderImage" src="images/ajax-loader.gif" style="display:none; margin-top:12px;" width="20px" height="20px">
                        <div class="col-lg-5 col-md-3">
                            <select class="mdb-select" id="courseId" onchange="getCourseModule(this.value)" required>
									<option value="">Select Course</option>
									<c:forEach items="${courseList}" var="ccl">
										<option value="${ccl.modelEnglish.commonId}">${ccl.modelEnglish.name} :: ${ccl.modelHindi.name}</option>
									</c:forEach>
							</select>
							<span id="errorCourse" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                        </div>
                    </div>
					<div class="row">
					<img class="courseLoaderImage" src="images/ajax-loader.gif" style="display:none; margin-top:12px;" width="20px" height="20px">                       
                        <div class="col-lg-8 col-md-3">
                            <select class="mdb-select" id="courseModuleId" name="courseModuleId" required>
									<option value="">Select Course Module</option>
									<c:forEach items="${courseModuleList}" var="ccl">
										<option value="${ccl.id}">${ccl.name}</option>
									</c:forEach>
							</select>
							<span id="errorCourseModule" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                        </div>
                    </div>       
                   
                    
                    <div class="form-group">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<button id="btn-signup" type="submit" class="btn blue-gradient btn-rounded">
								<i class="icon-hand-right"></i> Show Upload
							</button>
							<button id="btn-signup1" type="reset" class="btn blue-gradient btn-rounded">
								<i class="icon-hand-right"></i> Reset
							</button>
							

						</div>
					</div>                   
                 </form> 
                </div>
            </section>
        </div>
  	        
		<div class="container-fluid mb-5">
        	<section>
                <div class="row"> 
                	<div class="col-md-12">                    
                    	<div class="card">
                            <div class="card-body">
                                <table id="datatables" class="table table-striped table-bordered table-responsive-md" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th>Seq. No.</th>
											<th>Is Video/Image</th>
											<th>Slide Video/Image</th>
											<th>Slide Audio</th>
											<th>Action</th>
                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                    <c:set var="count" value="0" scope="page" />
									<c:forEach items="${courseUploadList}" var="cul">									   
									   <%-- <input type="text" name="courseContentId" value="${courseContent.id}"> --%>
										<tr class="odd gradeX">
											<td><c:set var="count" value="${count + 1}" scope="page" /><c:out value="${count}"/></td>											
											<td><c:out value="${cul.isImgOrVideo eq true ? 'Video': 'Image'}"/></td>
									 	   <td >	<c:if test="${cul.isImgOrVideo}">
											<video width="200" controls >
											  <source src="${cul.slidePath}" type="video/mp4">											
											</video>
											
											</c:if>
											<c:if test="${cul.isImgOrVideo eq false}">
											<button id="slide<c:out value="${count}"/>" value="<c:out value="${cul.slidePath}"/>" onclick="playMe(<c:out value="${count}"/>)" ><img alt="NA" src="${cul.slidePath}" width="200" /> </button>
										
											</c:if>
											</td>
											<td >
											<c:choose>
											<c:when test="${(cul.audioPath eq 'NA') or (empty  cul.audioPath)}">
											</c:when>
											<c:otherwise>
											<audio controls id="audio<c:out value='${count}'/>" style="height: 85px;" controlsList="nodownload" >
											<source src="${cul.audioPath}" type="audio/ogg">											
											</audio>
											</c:otherwise>
											</c:choose>
											</td>
											
											
											
											
											 <td><a href="<c:url value="/courseContentUpload?id=${cul.id}"/>"
											 class="btn-floating btn-sm blue-gradient btn-loaderClass" title="Edit">
											        <i class="fa fa-edit"></i>            
											    </a>
											    <a href="<c:url value="/courseContentUpload?id=${cul.id}"/> "
											     class="btn-floating btn-sm blue-gradient" onclick="return confirm('Do you want to delete the record?')" title="Delete">
											          <i class="fa fa-trash-o"></i>     
											    </a>
											</td>




								<%-- 	<c:out value="${cul.isImgOrVideo eq true ? 'Video': 'Image'}"/></td>
											<td><a id="playvideo" onclick="playMe(<c:out value="${cul.slidePath}"/>)"><c:out value="${cul.slidePath}"/></a></td>
											<td><input type="button" id="slide<c:out value="${count}"/>" value="<c:out value="${cul.slidePath}"/>" onclick="playMe(<c:out value="${count}"/>)"></td>
											<c:choose>
											<c:when test="${cul.audioPath eq 'NA'}">
											<td><input type="button" id="audio<c:out value="${count}"/>" value="<c:out value="${cul.audioPath}"/>" ></td>
											</c:when>
											<c:otherwise>
											<td><input type="button" id="audio<c:out value="${count}"/>" value="<c:out value="${cul.audioPath}"/>" onclick="playAudio(<c:out value="${count}"/>)"></td>
											</c:otherwise>
											</c:choose>  --%>
										</tr>
									</c:forEach>
                                        
                                    </tbody>
                                </table>
                            </div>
                            
                            <div>
								<iframe id="video1" style="display: none;"  width="100%" height="360"  frameborder="0" ></iframe>
							</div>
														
							<audio id="audioFiles" style="display: none;" controls="controls"></audio>
                            
                        </div>
                    </div>
                </div>
            </section>
        </div>
</main>

<script type="text/javascript">
function playMe(id) {
	//alert("hi-----");
	var slideId = 'slide' + id;
	var dbPath = document.getElementById(slideId).value;
	/* $('.mdb-select').material_select();
	$('.mdb-select').material_select(); */
	//alert(dbPath);
	//document.getElementById("video1").src = 'file:///D:/MLearning/slide/'+dbPath;
	document.getElementById("video1").src = dbPath;
	document.getElementById("audioFiles").style.display = "none";
	document.getElementById("video1").style.display = "";
	}
</script>

<script type="text/javascript">
function playAudio(id) {
	//alert("hi-----");
	var audioId = 'audio' + id;
	var dbPath = document.getElementById(audioId).value;
	//document.getElementById("video1").src = 'file:///D:/MLearning/slide/'+dbPath;
	document.getElementById("audioFiles").src = dbPath;
	document.getElementById("audioFiles").style.display = "";
	document.getElementById("video1").style.display = "none";
	}
</script>

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