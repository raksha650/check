<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile</title>
<meta charset="utf-8">	
</head>
<body>
<%@include file="header.jsp"%>
<%@include file="sidenev.jsp"%>
 <main>
    	<div class="container-fluid">
        	<section class="section">
            	<div class="row">
                	<div class="col-lg-4 mb-4">
                        <div class="card card-cascade narrower">
                            <div class="view gradient-card-header mdb-color lighten-3">
                                <h5 class="mb-0 font-weight-bold">Profile Photo</h5>
                            </div>
                            
                            <div class="card-body text-center">                         
                                <img  src="<c:out value="${user.profileImagePath}"/>" alt="<c:out value="${user.profileImagePath}"/>" class="z-depth-1 mb-3 mx-auto" width="250px"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8 mb-4">
                        <div class="card card-cascade narrower">
                            <div class="view gradient-card-header mdb-color lighten-3">
                                <h5 class="mb-0 font-weight-bold">Edit Account</h5>
                            </div>
                            <div class="card-body text-center">
                                <form action="create_user_new" method="GET" id="myForm" onsubmit="return myFormSubmitWithConfirmationAndLoader();">
                                
                                <input type="hidden" name="id" value="<c:out value="${user.userId}"/>">
                                   <div class="row">
                                        <div class="col-md-6">
                                            <div class="md-form">
                                                <input type="text" id="form81" class="form-control validate" value="<c:out value="${user.firstName}"/>">
                                                <label for="form81" data-error="wrong" data-success="right">First name</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="md-form">
                                                <input type="text" id="form82" class="form-control validate" value="<c:out value="${user.middleName}"/>">
                                                <label for="form82" data-error="wrong" data-success="right">Middle name</label>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="md-form">
                                                <input type="text" id="form76" class="form-control validate" value="<c:out value="${user.lastName}"/>">
                                                <label for="form76">LastName</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="md-form">
                                                <input type="email" id="form76" class="form-control validate" value="<c:out value="${user.email}"/>">
                                                <label for="form76">Email address</label>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="md-form">
                                                <input type="text" id="form1" class="form-control validate" value="<c:out value="${user.companyId.name}"/>">
                                                <label for="form1" data-error="wrong" data-success="right">Company</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="md-form">
                                                <input type="text" id="form2" class="form-control validate" value="<c:out value="${user.officeId.name}"/>">
                                                <label for="form2" data-error="wrong" data-success="right">Office</label>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="md-form">
                                                <input type="text" id="form1" class="form-control validate" value="<c:out value="${user.deptId.deptName}"/>">
                                                <label for="form1" data-error="wrong" data-success="right">Department</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="md-form">
                                                <input type="text" id="form2" class="form-control validate" value="<c:out value="${user.districtId.name}"/>">
                                                <label for="form2" data-error="wrong" data-success="right">District</label>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    
                                    
                                    
                                    <%-- <div class="row">
                                        <div class="col-md-6">
                                            <div class="md-form">
                                                <input type="text" id="form76" class="form-control validate" value="<c:out value="${user.stateId.name}"/>">
                                                <label for="form76">State</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="md-form">
                                                <input type="text" id="form77" class="form-control validate" value="<c:out value="${user.cityId.name}"/>">
                                                <label for="form77" data-error="wrong" data-success="right">City</label>
                                            </div>
                                        </div>
                                    </div> --%>
                                    <!-- <div class="row">
                                        <div class="col-md-12">
                                            <div class="md-form">
                                                <textarea type="text" id="form78" class="md-textarea form-control" rows="3"></textarea>
                                                <label for="form78">About me</label>
                                            </div>
                                        </div>
                                    </div> -->
                                    <div class="row">
                                        <div class="col-md-12 text-center my-4">
                                        <% if(loginUser.getRole().equals("Admin")){ %>
                                            <input type="submit" id="btn-signup" value="Edit Profile" class="btn btn-info btn-rounded btn-loaderClass">
                                           <% }else{%>
                                        	   <a href="user_dashboard" class="btn btn-info btn-rounded btn-loaderClass">Exit</a>
                                        	   
                                           <% }%>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </main>
    <%@include file="footer.jsp"%>
</body>
</html>