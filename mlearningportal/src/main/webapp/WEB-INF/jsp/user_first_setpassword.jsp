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
                                <img  width="250px" src="<c:out value="${user.profileImagePath}"/>" alt="<c:out value="${user.profileImagePath}"/>" class="z-depth-1 mb-3 mx-auto" />
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8 mb-4">
                        <div class="card card-cascade narrower">
                            <div class="view gradient-card-header mdb-color lighten-3">
                                <h5 class="mb-0 font-weight-bold">Password Change For The First Time</h5>
                            </div>
                            <div class="card-body text-center">
                               <form:form class="container" action="set_first_password" method="post" modelAttribute="user" id="myForm" onsubmit="return  Validate();">
                                
                                <form:hidden path="userId" value="${user.userId}" />
                                   <div class="row">
                                         <div class="col-md-4 mb-4">
				                            <div class="md-form">
				                                <form:password  class="form-control validate" path="password" id="password"
															pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"/>
				                                <label style="width: 100%" for="password" data-error="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters">Password<span style="color: red;">*</span></label>
				                            </div>
				                            <span id="errorPassword" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
				                        </div>
                                         <div class="col-md-4 mb-4">
				                            <div class="md-form">
				                                <input type="password" class="form-control validate" name="password2" id="password2"
															 pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"/>
				                                <label style="width: 100%" for="password2" data-error="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters">Confirm Password<span style="color: red;">*</span></label>
				                            </div>
				                            <span id="errorPassword" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
				                        </div>
                                    </div>
                                
                                    
                                    <div class="row">
                                        <div class="col-md-12 text-center my-4">
                                       
                                       <button type="submit" id="btn-signup" class="btn btn-info btn-rounded" >Submit</button>
                                        	  
                                        	   
                                       
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </main>
    <%@include file="footer.jsp"%>
    
    <script type="text/javascript">
    function Validate() {
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("password2").value;
        if (password != confirmPassword) {
            alert("Passwords do not match, please try again.");
            document.getElementById("password").value="";
            document.getElementById("password2").value="";
            return false;
        }
        myFormSubmitWithConfirmationAndLoader();
    }
</script>
</body>
</html>