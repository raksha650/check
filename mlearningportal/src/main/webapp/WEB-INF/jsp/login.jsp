<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Login</title>
<link rel="shortcut icon" href="images/favicon.png">

<style type="text/css">
.login-body { 
 background: url('images/shakti_bhawan_front.jpg') no-repeat center center fixed; 
 -webkit-background-size: cover;
 -moz-background-size: cover;
 -o-background-size: cover;
 background-size: cover;
}

.panel-default {
 opacity: 0.9;
 margin-top:30px;
}
.form-group.last {
 margin-bottom:0px;
}

.box22{width: 500px;
    background-color:rgba(255,255,255,0.4);
    border-radius: 10px;
    overflow: hidden;
    box-shadow: 0 3px 20px 0px rgba(0, 0, 0, 0.1);
    -moz-box-shadow: 0 3px 20px 0px rgba(0, 0, 0, 0.1);
    -webkit-box-shadow: 0 3px 20px 0px rgba(0, 0, 0, 0.1);
    -o-box-shadow: 0 3px 20px 0px rgba(0, 0, 0, 0.1);
    -ms-box-shadow: 0 3px 20px 0px rgba(0, 0, 0, 0.1);
}
.box011{width: 100%;
    min-height: 100vh;
    display: -webkit-box;
    display: -webkit-flex;
    display: -moz-box;
    display: -ms-flexbox;
    display: flex;
    flex-wrap: wrap;
  /*   justify-content: center; */
    align-items: center;
    padding: 15px;
   
}

.showLoaderClass {
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

</style>

<link href="<c:url value="/user/css/bootstrap.min.css"/>" rel="stylesheet">

<script src="<c:url value="/js/validation.js"/>" ></script>
<style>
.input1[type=checkbox]{
	height: 0;
	width: 0;
	visibility: hidden;
}

.label1 {
	cursor: pointer;
	text-indent: -9999px;
	width: 150px;
	height: 28px;
	background: grey;
	display: block;
	border-radius: 100px;
	position: relative;
}

.label1:after {
	content: '';
	position: absolute;
	top: 5px;
	left: 5px;
	width: 20px;
	height: 20px;
	background: #fff;
	border-radius: 90px;
	transition: 0.3s;
}

.input1:checked + .label1 {
	background: #718708;
}

.input1:checked + .label1:after {
	left: calc(100% - 5px);
	transform: translateX(-100%);
}

.label1:active:after {
	width: 130px;
}

.toggle01 {float:left; 
margin:7px 20px 0px 120px; 
font-size:24px; 
font-weight:bold; 
color:#718708;
padding-top:5px;}

.toggle03{ 
 
font-size:24px; 
font-weight:bold; 
color:grey;}

.toggle02{ 
 
font-size:24px; 
font-weight:normal; 
color:#718708;}
</style>

<link
	href="<c:url value="/user/user/jquery.confirm/jquery.confirm.css"/>"
	rel="stylesheet">
</head>


<body class="login-body">
<%-- <jsp:include page="user_header.jsp"/> --%>
<div class="">
<div class="container " >
    <div class="row box011" align="center">
        <div class="col-md-6 box22" >
            <div class="panel panel-default">
                <div class="panel-heading">
                <center><div><img src="user/images/logo.png" alt="M Learning" /></div>
                  <strong> MPPMCL eLearning Portal Login</strong></center>
                  <c:if test="${error ne null}">
				    <div class="alert alert-danger fade in">
				        <a href="#" class="close" data-dismiss="alert">&times;</a>
				        <strong>Error!</strong> ${error}.
				    </div>
				  </c:if>
				   <c:if test="${success ne null}">
				    <div class="alert alert-success ">
				        <a href="#" class="close" data-dismiss="alert">&times;</a>
				        <strong>success!</strong> ${success}.
				    </div>
				  </c:if>
                </div>
                
                <div class="panel-body">
                    <form:form action="user_login" modelAttribute="login" method="post" name="myForm" id="myForm" onsubmit="return myFormSubmitWithConfirmationAndLoader();">
                   <input type="hidden" id="role" name="role" value="User">
                <div class="row">
                 <div class="col-sm-4 toggle03" style="text-align:right" id="userSet">User</div>
                 <div class="col-sm-4"><input type="checkbox" id="switch" class="input1" name="checkUser"  onchange="submitFormCheckUser(this.id)"/>
                 <label for="switch" class="label1">Toggle</label></div>
                <div class="col-sm-4 toggle02" style="text-align:left" id="adminSet">Admin</div>
                 </div>  
                    <!--  <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label padding15">
                            User Type</label>
                        <div class="col-sm-9" style="padding:5px;">
                            <select class="form-control" name="role" id="role">
                            <option value="Admin">Admin</option>
                            <option value="User">User</option>
                            </select>
                        </div>
                        
                    </div> -->
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label padding15">
                            Email</label>
                        <div class="col-sm-9" style="padding:5px;">
                            <!-- <input type="email" class="form-control" id="inputEmail3" placeholder="Email" required=""> -->
                            <input type="email" name="emailId" id="inputEmail3" placeholder="Email" name="email" onblur="validateEmail(this.value)" class="form-control"/>
                            <span id="errorEmail" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-3 control-label"  style="padding:7px;">
                            Password</label>
                        <div class="col-sm-9" style="padding:5px;">
                            <!-- <input type="password" class="form-control" id="inputPassword3" placeholder="Password" required=""> -->
                            <input required="required" type="password" name="password" id="inputPassword3" placeholder="Password" class="form-control"/>
                        </div>
                    </div>
                    <!-- <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox">
                                    Remember me
                                </label>
                            </div>
                        </div>
                    </div> -->
                    <div class="form-group last">
                        <div class="col-sm-9">
                            <button type="submit" id="" class="btn btn-success btn-sm" >
                                Sign in</button>
                                 <button type="reset" class="btn btn-default btn-sm">
                                Reset</button>
                        </div>
                    </div>
                    </form:form>
                </div>
                <!-- <div class="panel-footer">
                    Not Registered? <a href="#">Register here</a></div> -->
            </div>
        </div>
        
      <div class="modal showLoaderClass" style="display: none">
		<div class="center">
			<img alt="" src="images/loader.gif" width="150px" height="150px" />
		</div>
	</div>
        <%-- <div class="col-md-6" style="margin-top:20%;">
            <div class="panel panel-default">
                <div class="panel-heading">
                <center><div><img src="user/images/logo.png" alt="M Learning" /></div>
                  <strong>User Login</strong></center>
                  <c:if test="${error ne null}">
				    <div class="alert alert-danger fade in">
				        <a href="#" class="close" data-dismiss="alert">&times;</a>
				        <strong>Error!</strong> ${error}.
				    </div>
				  </c:if>
                </div>
                
                <div class="panel-body">
                    <form:form action="user_login" modelAttribute="login" method="post">
                    <form:hidden path="role" value="User" />
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label" style="padding:7px;">
                            Email</label>
                        <div class="col-sm-9" style="padding:5px;">
                            <!-- <input type="email" class="form-control" id="inputEmail3" placeholder="Email" required=""> -->
                            <form:input type="email" path="emailId" id="inputEmail3" placeholder="Email" name="email" onblur="validateEmail(this.value)" class="form-control"/>
                            <span id="errorEmail" style="color: #ff0000; font-size: 12px; margin-left: 15px;"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-3 control-label"  style="padding:7px;">
                            Password</label>
                        <div class="col-sm-9" style="padding:5px;">
                            <!-- <input type="password" class="form-control" id="inputPassword3" placeholder="Password" required=""> -->
                            <form:input required="required" type="password" path="password" id="inputPassword3" placeholder="Password" class="form-control"/>
                        </div>
                    </div>
                    <!-- <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox">
                                    Remember me
                                </label>
                            </div>
                        </div>
                    </div> -->
                    <div class="form-group last">
                        <div class="col-sm-offset-3 col-sm-9">
                            <button type="submit" class="btn btn-success btn-sm">
                                Sign in</button>
                                 <button type="reset" class="btn btn-default btn-sm">
                                Reset</button>
                        </div>
                    </div>
                    </form:form>
                </div>
                <!-- <div class="panel-footer">
                    Not Registered? <a href="#">Register here</a></div> -->
            </div>
        </div> --%>
    </div>
</div>
</div>
</body>
<%-- <jsp:include page="user_footer.jsp"/> --%>
<script src="<c:url value="/js/jquery-3.2.1.min.js"/>" ></script>
<script>
$(document).ready(function(){
   
    /* if($("success").is(":visible")){
    	$('#modal').hide();
    } */
});
</script>
<script> 

function submitFormCheckUser(id){
		var user = document.getElementById(id);
		var role = document.getElementById("role");
	  if(user.checked) {
		  role.value="Admin";
		  document.getElementById("adminSet").style.fontWeight = "bold";
		  document.getElementById("userSet").style.fontWeight = "normal";
		  document.getElementById('myForm').action = "loginProcessNew";
	  }else{
		  role.value="User";
		  document.getElementById("adminSet").style.fontWeight = "normal";
		  document.getElementById("userSet").style.fontWeight = "bold";
		  document.getElementById('myForm').action = "user_login";
	  }
	}
	
function submitForm(){
	
	var role = document.getElementById("role");

	var myForm = document.getElementById("myForm");
	
	if(role.value=="Admin"){	
		
		document.getElementById('myForm').action = "loginProcessNew";
		
		
    }else if (role.value=="User"){
    	
    	document.getElementById('myForm').action = "user_login";
    	   	
    	
    }
}
</script>

<script src="<c:url value="/user/user/jquery.confirm/jquery.confirm.js"/>"></script>

<script>
function myFormSubmitWithConfirmationAndLoader(){
			
					  $('.showLoaderClass').show();
					  var myForm = document.getElementById("myForm");
						myForm.submit();
				
	
}
</script>
</html>