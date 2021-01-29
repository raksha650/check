<%@page import="com.cinfy.mlearning.model.request.LoginUser"%>
<div id="slide-out" class="side-nav fixed" >
<%@ page import="com.cinfy.mlearning.model.UserNew"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	UserNew loginUser321 = (UserNew) request.getSession().getAttribute("loginUser");

%>

             <%
             //UserNew user = (UserNew)request.getAttribute ("loginUser");
             if(loginUser321.getRole().equals("Admin")){ %>
 
            <ul class="custom-scrollbar">
                <li class="logo-sn waves-effect btn-loaderClass">
                    <div class="text-center">
                        <a href="http://www.mppmcl.com" target=_blank class="pl-0"><img src="images/logo_dashboard.png" class="img-responsive" style="width:30%;"></a>
                    </div>
                </li>
            	<li>
                  
                    <div style="background-color: #fff; width: 100%; height: 2px; margin:20px 0px;"></div>
            	</li>
            	<li>
                	<ul class="collapsible collapsible-accordion">
                    	<li>
                        <a href="dashboard" class="collapsible-header waves-effect arrow-r btn-loaderClass">
                        	<i class="fa fa-tachometer"></i> Home
                        </a>
                      
                    </li>
                    <li><a class="collapsible-header waves-effect arrow-r"><i class="fa fa-user"></i> User Management<i class="fa fa-angle-down rotate-icon"></i></a>
                        <div class="collapsible-body">
                            <ul>
                                <li><a href="create_user_new" class="waves-effect btn-loaderClass">Create User</a></li>
                                <li><a href="user_list_new" class="waves-effect btn-loaderClass">User List</a></li>
                              
                            </ul>
                        </div>
                    </li>
                    <!-- <li>
                    	<a href="profile.html" class="collapsible-header waves-effect arrow-r"><i class="fa fa-user"></i> Master Management</a>
                    </li> -->
                    <li><a class="collapsible-header waves-effect arrow-r"><i class="fa fa-database"></i> Master Management<i class="fa fa-angle-down rotate-icon"></i></a>
                        <div class="collapsible-body">
                            <ul>
                             <li><a href="companyMaster" class="waves-effect btn-loaderClass">Company</a></li>
                             <li><a href="officeMaster" class="waves-effect btn-loaderClass">Office</a></li>
                             <li><a href="deptMaster" class="waves-effect btn-loaderClass">Department</a></li>
                             <li><a href="divisionMaster" class="waves-effect btn-loaderClass">Division</a></li>
                             <!-- <li><a href="subDeptMaster" class="waves-effect btn-loaderClass">Sub Department</a></li> -->
                             <li><a href="designationMaster" class="waves-effect btn-loaderClass">Designation</a></li>
                             <li><a href="courseCategory" class="waves-effect btn-loaderClass">Course Category</a></li>
                             <!-- <li><a href="deptCourseMaster" class="waves-effect btn-loaderClass">Department Course Assign</a></li> -->
                             
                             
                               <!-- <li><a href="course" class="waves-effect btn-loaderClass">Course</a></li>
                               <li><a href="courseModule" class="waves-effect btn-loaderClass">Course Module</a></li>
                               <li><a href="deptCourseMaster" class="waves-effect btn-loaderClass">Department Course Assign</a></li> -->
                               
                            </ul>
                        </div>
                    </li>
                    <li><a class="collapsible-header waves-effect arrow-r"><i class="fa fa-book"></i> Course Management<i class="fa fa-angle-down rotate-icon"></i></a>
                        <div class="collapsible-body">
                            <ul>
                             <li><a href="course" class="waves-effect btn-loaderClass">Course</a></li>
                             <li><a href="courseModule" class="waves-effect btn-loaderClass">Module</a></li>
                             <li><a href="question" class="waves-effect btn-loaderClass">Question & Answer</a></li>
                             <li><a href="courseContent" class="waves-effect btn-loaderClass">Course Content Details</a></li>
                             <li><a href="courseContentUpload" class="waves-effect btn-loaderClass">Content Upload</a></li>
                             <li><a href="showCourseContentUploadForm" class="waves-effect btn-loaderClass">View Content</a></li>
                             <li><a href="courseAssignMaster" class="waves-effect btn-loaderClass">Course Assign</a></li>
                               <!-- <li><a href="course" class="waves-effect btn-loaderClass">Course</a></li>
                               <li><a href="courseModule" class="waves-effect btn-loaderClass">Course Module</a></li>
                               <li><a href="deptCourseMaster" class="waves-effect btn-loaderClass">Department Course Assign</a></li> -->
                               
                            </ul>
                        </div>
                    </li>
                    
                    <li><a class="collapsible-header waves-effect arrow-r"><i class="fa fa-history"></i> View Logs<i class="fa fa-angle-down rotate-icon"></i></a>
                        <div class="collapsible-body">
                            <ul>
                             <li><a href="courseViewLogDetails" class="waves-effect btn-loaderClass">Course View Log Details</a></li>
                             <li><a href="assessmentLogDetails" class="waves-effect btn-loaderClass">Assessment Log Details</a></li>
                            </ul>
                        </div>
                    </li>
                    
                </ul>
            	</li>
            </ul>
            <%}else  if(loginUser321.getRole().equals("User") &&  loginUser321.getIsResetPwd() ){%>
             <ul class="custom-scrollbar">
                <li class="logo-sn waves-effect btn-loaderClass">
                    <div class="text-center">
                        <a href="http://www.mppmcl.com" target=_blank class="pl-0"><img src="images/logo_dashboard.png" class="img-responsive" style="width:30%;"></a>
                    </div>
                </li>
            	<li>
                  
                    <div style="background-color: #fff; width: 100%; height: 2px; margin:20px 0px;"></div>
            	</li>
            	<li>
                	<ul class="collapsible collapsible-accordion">
                    	<li>
                        <a href="user_dashboard" class="collapsible-header waves-effect arrow-r btn-loaderClass">
                        	<i class="fa fa-tachometer"></i> Dashboard
                        </a>
                      
                    </li>
                    <c:if test="${courseLoad}">
                    <li><a class="collapsible-header waves-effect arrow-r"><i class="fa fa-user"></i> Compliance Courses<i class="fa fa-angle-down rotate-icon"></i></a>
                        <div class="collapsible-body">
                            <ul>
                            <c:forEach items="${Compliance}" var="sl">
                             <li><a href="user_modules?courseId=${sl.id}" class="waves-effect btn-loaderClass">${sl.name}</a></li>
                            </c:forEach>                           
                              
                            </ul>
                        </div>
                    </li>
                    <li><a class="collapsible-header waves-effect arrow-r"><i class="fa fa-user"></i>Non Compliance Courses<i class="fa fa-angle-down rotate-icon"></i></a>
                        <div class="collapsible-body">
                            <ul>
                                <c:forEach items="${NonCompliance}" var="sl">
                             <li><a href="user_modules?courseId=${sl.id}" class="waves-effect btn-loaderClass">${sl.name}</a></li>
                            </c:forEach>
                              
                            </ul>
                        </div>
                    </li>
                    <li><a class="collapsible-header waves-effect arrow-r"><i class="fa fa-user"></i>Pending Courses<i class="fa fa-angle-down rotate-icon"></i></a>
                        <div class="collapsible-body">
                            <ul>
                                 <c:forEach items="${Others}" var="sl">
                             <li><a href="user_modules?courseId=${sl.id}" class="waves-effect btn-loaderClass">${sl.name}</a></li>
                            </c:forEach>
                              
                            </ul>
                        </div>
                    </li>
                    </c:if>
                    <c:if test="${moduleLoad}">
                    <li><a class="collapsible-header waves-effect arrow-r"><i class="fa fa-user"></i>${course.name}<i class="fa fa-angle-down rotate-icon"></i></a>
                        <div class="collapsible-body">
                            <ul>
                            <c:forEach items="${moduleList}" var="sl">
                             <li><a href="user_modules_detail?courseModuleId=${sl.id}" class="waves-effect btn-loaderClass" style="font-size:14px; padding-left:5px !important;">${sl.name}</a></li>
                            </c:forEach>                           
                              
                            </ul>
                        </div>
                    </li>
                    </c:if>
                   
                  
                    
                    
                    
                </ul>
            	</li>
            </ul>
            
            <%}else if(!loginUser321.getIsResetPwd()){ %>
             <ul class="custom-scrollbar">
                <li class="logo-sn waves-effect btn-loaderClass">
                    <div class="text-center">
                        <a href="http://www.mppmcl.com" target=_blank class="pl-0"><img src="images/logo_dashboard.png" class="img-responsive" style="width:30%;"></a>
                    </div>
                </li>
            	<li>
                  
                    <div style="background-color: #fff; width: 100%; height: 2px; margin:20px 0px;"></div>
            	</li>
            	<li>
                	<ul class="collapsible collapsible-accordion">
                    	<li>
                        <a href="logout" class="collapsible-header waves-effect btn-loaderClass arrow-r">
                        	<i class="fa fa-tachometer"></i> Log Out
                        </a>
                   
                    </li>
                    
                </ul>
            	</li>
            </ul>
           <%} %>
            <ul class="custom-scrollbar" style="top:500px; padding:25px; position:relative; font-size:14px; border:none;">
               
            	
            	<li>
                	<ul class="collapsible collapsible-accordion">
                    	<li>
                      
                        	<i class="fa fa-copyright"></i>Powered By Cinfy Systems
                      
                   
                    </li>
                    
                </ul>
            	</li>
            </ul>
        </div>