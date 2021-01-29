<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.cinfy.mlearning.model.UserNew"%>
<%-- <%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%> --%>
<!-- <meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge"> -->
<title>eLearning Portal Of MPPMCL</title>
<meta charset="utf-8">
<meta name="description" content="eLearning MPPMCL cinfy systems">
<!-- for validation -->
<script src="<c:url value="/js/validation.js"/>"></script>



<link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/css/font-awesome.css"/>" rel="stylesheet">
<link href="<c:url value="/css/mdb.css"/>" rel="stylesheet">

<link href="<c:url value="/css/dataTables.bootstrap4.min.css"/>"
	rel="stylesheet">
<!-- <link rel="stylesheet" href="css/dataTables.bootstrap4.min.css"> -->
<link href="<c:url value="/css/bootstrap-datetimepicker.css"/>"
	rel="stylesheet">
<link href="<c:url value="/css/jquery-ui.css"/>" rel="stylesheet">
<link href="<c:url value="/user/user/css/custom.css"/>" rel="stylesheet">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed&family=Roboto+Mono:ital,wght@1,500&display=swap" rel="stylesheet">
<link
	href="<c:url value="/user/user/jquery.confirm/jquery.confirm.css"/>"
	rel="stylesheet">
<%
	UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
	//request.setAttribute ("loginUser", loginUser);
	if (loginUser == null) {
%>
<script>
	window.location = "index";
</script>
<%
	}
%>

<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>">


</head>

<body class="fixed-sn white-skin">
	<header>

		<nav
			class="navbar fixed-top navbar-expand-lg scrolling-navbar double-nav">
			<div class="float-left">
				<a href="#" data-activates="slide-out"
					class="button-collapse black-text"><i class="fa fa-bars"></i></a>
			</div>
			<div class="breadcrumb-dn mr-auto">
				<p><font face='Roboto Mono', monospace, size="5" >Welcome To eLearning Portal Of MPPMCL</font></p>
			</div>
			<ul class="nav navbar-nav nav-flex-icons ml-auto">
				<li class="nav-item dropdown notifications-nav">
					<!-- <a class="nav-link dropdown-toggle waves-effect" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
                        aria-expanded="false">
                        <span class="badge red">3</span> <i class="fa fa-bell"></i>
                        <span class="d-none d-md-inline-block">Notifications</span>
                    </a> -->
					<div class="dropdown-menu dropdown-primary"
						aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="#"> <i class="fa fa-money mr-2"
							aria-hidden="true"></i> <span>New order received</span> <span
							class="float-right"><i class="fa fa-clock-o"
								aria-hidden="true"></i> 13 min</span>
						</a> <a class="dropdown-item" href="#"> <i
							class="fa fa-money mr-2" aria-hidden="true"></i> <span>New
								order received</span> <span class="float-right"><i
								class="fa fa-clock-o" aria-hidden="true"></i> 33 min</span>
						</a> <a class="dropdown-item" href="#"> <i
							class="fa fa-line-chart mr-2" aria-hidden="true"></i> <span>Your
								campaign is about to end</span> <span class="float-right"><i
								class="fa fa-clock-o" aria-hidden="true"></i> 53 min</span>
						</a>
					</div>
				</li>
				<!-- <li class="nav-item">
                    <a class="nav-link waves-effect" data-toggle="modal" data-target="#exampleModal" ><i class="fa fa-calendar"></i> <span class="clearfix d-none d-sm-inline-block">Calendar</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link waves-effect"><i class="fa fa-comments-o"></i> <span class="clearfix d-none d-sm-inline-block">Support</span></a>
                </li> -->
				<%
					if (loginUser.getIsResetPwd()) {
				%>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle waves-effect" href="#"
					id="userDropdown" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <i class="fa fa-user"></i> <span
						class="clearfix d-none d-sm-inline-block"><%=(loginUser.getFirstName() + " "
						+ (loginUser.getMiddleName() != null ? loginUser.getMiddleName() : "") + " "
						+ loginUser.getLastName())%></span></a>
					</a>
					<div class="dropdown-menu dropdown-menu-right"
						aria-labelledby="userDropdown">
						<a class="dropdown-item btn-loaderClass" href="user_profile_new">My account</a> <a
							class="dropdown-item btn-loaderClass" href="logout">Log Out</a>
					</div></li>
				<%
					}
				%>
				<%-- <li class="dropdown user user-menu p-ph-res"> 
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
                            <img src="${loginUser.profileImagePath}" class="user-image"> 
                            <span class="hidden-xs">${loginUser.firstName} ${not empty loginUser.middleName ? loginUser.middleName : ''} ${loginUser.lastName}</span> 
                        </a><c:out value="${cul.isImgOrVideo eq true ? 'Video': 'Image'}"/>
                        <ul class="dropdown-menu">
                            <li class="user-header">
                                <div class="pull-left user-img"><img src="${loginUser.profileImagePath}" class="img-responsive img-circle" ></div>
                                <p class="text-left">${loginUser.firstName} ${not empty loginUser.middleName ? loginUser.middleName : ''} ${loginUser.lastName} <small>${loginUser.email}</small> </p>
                            </li>
                            <li><a href="#"><i class="icon-profile-male"></i> My Profile</a></li>
                            <!-- <li><a href="#"><i class="icon-wallet"></i> My Balance</a></li>
                            <li><a href="#"><i class="icon-envelope"></i> Inbox</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#"><i class="icon-gears"></i> Account Setting</a></li> -->
                            <li role="separator" class="divider"></li>
                            <li><a href="logout"><i class="fa fa-power-off"></i> Logout</a></li>
                        </ul>
                    </li>
                 --%>



			</ul>
		</nav>
	</header>
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Calendar</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="embed-responsive embed-responsive-16by9">
						<iframe class="embed-responsive-item" src="/calendar/"
							allowfullscreen></iframe>
					</div>
				</div>

			</div>
		</div>
	</div>
	

	<div class="modal showLoaderClass" style="display: none">
		<div class="center">
			<img alt="" src="images/loader.gif" width="150px" height="150px" />
		</div>
	</div>