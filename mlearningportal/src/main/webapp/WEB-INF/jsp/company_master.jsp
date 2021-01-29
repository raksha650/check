<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Company Master</title>
<meta charset="utf-8">
<!-- <style>
    #showLoader {
       background-color: rgba(0,0,0,.2);
        color: #666666;
        position: fixed;
        height: 100%;
        width: 100%;
        z-index: 5000;
        top: 0;
        left:0;
        float: left;
        padding-top: 20%;
        text-align: center;
        
    }
</style> -->
</head>

<body>

<%@include file="header.jsp"%>
<%@include file="sidenev.jsp"%> 
<main>   
<div class="container-fluid">
        	<section class="section card mb-5">
            	<div class="card-body">
                	<h1 class="bg-color heading">Company Master</h1>
                    <!-- <h5 class="pb-5">Input fields</h5> -->
                    
                    <c:if test="${not empty success}">
						<div class="alert alert-success">
							<a href="#" class="close" data-dismiss="alert">&times;</a><strong>
							${success};</strong>
						</div>
					</c:if>
                    
                    <form:form action="saveOrUpdateCompanyMaster" 
						modelAttribute="companyMaster" method="post" enctype="multipart/form-data"
						id="myForm" onsubmit="return myFormSubmitWithConfirmationAndLoader();">
						<form:hidden path="modelHindi.id" />
						<form:hidden path="modelEnglish.id" />
						<form:hidden path="modelEnglish.commonId" />
						<form:hidden path="modelHindi.commonId" />
						<form:hidden path="modelEnglish.commonId" />
						<form:hidden path="modelHindi.commonId" />
						<form:hidden path="modelEnglish.logoPath" />
						<form:hidden path="modelHindi.logoPath" />
								
					
                    <div class="row">
                    	<div class="col-md-6 mb-6">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="modelEnglish.companyId" pattern="^[_A-z0-9]*((-|\s)*[_A-z0-9])*$"
											id="companyId1"  maxlength="10" required="true"/>
                                <label style="width: 100%" for="companyId1" data-error="Only characters and number allow">Company Id</label>
                            </div>
                        </div>
                        
                        <div class="col-md-6 mb-6">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="modelHindi.companyId" 
											 id="companyId2" required="true" maxlength="100"/>
                                <label for="companyId2" >कंपनी आईडी  </label>
                            </div>
                        </div>
                                             
                    </div>
                     <div class="row">
                    	<div class="col-md-6 mb-6">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="modelEnglish.name"  pattern="[A-Za-z ]{3,}" maxlength="20"
											id="name"   required="true"/>
                                <label style="width: 100%" for="name" data-error="Only characters with min 3 max 20 length" >Company Name</label>
                            </div>
                        </div>
                        
                        <div class="col-md-6 mb-6">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="modelHindi.name"
											 id="name1" required="true" maxlength="100"/>
                                <label for="name1" >कंपनी का नाम   </label>
                            </div>
                        </div>
                                             
                    </div>
                     <div class="row">
                    	<div class="col-md-6 mb-6">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="modelEnglish.addressId"
											id="addressId"  maxlength="100" required="true"/>
                                <label style="width: 100%" for="addressId" >Company Address</label>
                            </div>
                        </div>
                        
                        <div class="col-md-6 mb-6">
                            <div class="md-form">
                                <form:input type="text" class="form-control validate" path="modelHindi.addressId"
											 id="addressId1" required="true" maxlength="100"/>
                                <label for="addressId1" >कंपनी पता </label>
                            </div>
                        </div>
                     </div>
                     <div class="row">
                         <%-- <div class="col-md-6 mb-6">
                            <div class="md-form">
                            Company Logo
                                <form:input type="file" class="form-control validate" path="modelEnglish.multipartLogoFile"
											 id="multipartLogoFile" required="true" />
                                
                            </div>
                        </div>  --%>
                        
                        <div class="col-lg-4 col-md-6 mb-6">
	                        <div class="md-form" style="margin-top: 10px;">
	                           <div class="file-field">
	                               <div class="btn btn-primary btn-sm float-left">
	                                   <span>Choose file</span>
	                                   <form:input type="file" class="form-control validate" path="modelEnglish.multipartLogoFile"
											 id="multipartLogoFile" required="true" />
	                               </div>
	                               <div class="file-path-wrapper">
	                                   <input class="file-path validate" id="userImg" required="required" readonly="readonly" type="text" placeholder="Upload company image">
	                               </div>
	                               <span id="errorUserImg" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
	                           </div>
	                         </div>
                         </div>             
                    </div>
                    
                    <div class="form-group">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<button id="" type="submit" class="btn blue-gradient btn-rounded">
								<i class="icon-hand-right"></i> Save
							</button>
							<button id="btn-signup1" type="reset" class="btn blue-gradient btn-rounded">
								<i class="icon-hand-right"></i> Reset
							</button>
						</div>
					</div>                   
                 </form:form> 
                </div>
            </section>
        </div>
        
        <div class="modal" id="showLoader" style="display: none">
		    <div class="center">
		        <img alt="" src="images/loader.gif" width="150px" height="150px"/>
		    </div>
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
                                            <th>Sr.No.</th>
											<th>CompanyId</th>
											<th>कंपनी आईडी </th>
											<th>Company Name</th>
											<th>कंपनी नाम</th>
											<th>Company Address</th>
											<th>कंपनी पता </th>
											<th>Image</th>
											<th>Action</th>
                                        </tr>
                                    </thead>
                                   <!--  <tfoot>
                                        <tr>
                                            <th>Sr.No.</th>
											<th>CompanyId</th>
											<th>कंपनी आईडी </th>
											<th>Company Name</th>
											<th>कंपनी नाम</th>
											<th>Company Address</th>
											<th>कंपनी पता </th>
											<th>Image</th>
											<th>Action</th>
                                        </tr>
                                    </tfoot> -->
                                    <tbody>
                                    <c:set var="count" value="0" scope="page" />
									<c:forEach items="${companyMasterList}" var="ccl">
										<tr class="odd gradeX">
											<td><c:set var="count" value="${count + 1}" scope="page" />
												<c:out value="${count}" /></td>
												
											<td><c:out value="${ccl.modelEnglish.companyId}" /></td>
											<td><c:out value="${ccl.modelHindi.companyId}" /></td>
											
											<td><c:out value="${ccl.modelEnglish.name}" /></td>
											<td><c:out value="${ccl.modelHindi.name}" /></td>
											
											<td><c:out value="${ccl.modelEnglish.addressId}" /></td>
											<td><c:out value="${ccl.modelHindi.addressId}" /></td>
											
											<td><img src="${ccl.modelEnglish.logoPath}" width="100" heigh="100" /></td>
											

											<td><a href="<c:url value="/companyMaster?commonId=${ccl.modelEnglish.commonId} "/>"
											 class="btn-floating btn-sm blue-gradient" title="Edit">
											        <i class="fa fa-edit"></i>            
											    </a>
											    <a href="<c:url value="/deleteCompanyMaster?commonId=${ccl.modelEnglish.commonId}" /> "
											     class="btn-floating btn-sm blue-gradient" onclick="return confirm('Do you want to delete the record?')" title="Delete">
											          <i class="fa fa-trash-o"></i>     
											    </a>
											</td>
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
$(document).ready(function(){
    $('#btn-signup').click(function() {
    	$('#showLoader').show();
        
    });
    if($("success").is(":visible")){
    	$('#modal').hide();
    }
});
</script>
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