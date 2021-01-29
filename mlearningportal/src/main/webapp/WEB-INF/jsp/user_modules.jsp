<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<style>
.scrollbar
{
	
	float: left;
	height: 100px;
	width: 100%;
	background: #fff;
	overflow-y: scroll;
	margin-bottom: 25px;
}

.force-overflow
{
	min-height: 200px;
}

#style-4::-webkit-scrollbar-track
{
	-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
	background-color: #F5F5F5;
}

#style-4::-webkit-scrollbar
{
	width: 5px;
	background-color: #F5F5F5;
}

#style-4::-webkit-scrollbar-thumb
{
	background-color: #000000;
	border: 2px solid #555555;
}


</style>

</head>


<body>

	<%@include file="header.jsp"%>
	<%@include file="sidenev.jsp"%>

	<main _ngcontent-c0="" class="pt-5 mx-lg-5"
		ng-reflect-klass="pt-5 mx-lg-5" ng-reflect-ng-class="[object Object]">
	<div _ngcontent-c0="" class="mt-5">
		<router-outlet _ngcontent-c0=""></router-outlet>
		<app-dashboard1 _nghost-c3="">
		
		
		
		 <section _ngcontent-c4="" >
			
	
	

      <div class="row span4">
        <div class="span4 col-sm-12">
        <h4>${course.name}</h4>
        <div class=""><img class="img-left  img-responsive" src="${course.courseImagePath}" ></div>
         
          <div><div class="content-heading"><h3>Detail Description</h3></div>
          <p class=" text-justify">
                            	${course.shortDesc}  
                            </p>
                           <p class=" text-justify">
                            	${course.longDesc}  
                            </p></div>
        </div>
     </div>
   
 
   
    

		</section>
	
 <section _ngcontent-c4="" >
		
	<div class="row">
	
	
	 <c:forEach items="${moduleList}" var="sl">
                 <div class="col-md-4 col-sm-6" style="margin-top:30px;" >
                    <div class="pricingTable">
                       <%--  <img src="${sl.courseId.courseImagePath}" class="img-responsive" width="50%"/> --%>
                        <div class="pricing-content">
                            <ul>
                                <li>${sl.name} </li>
                                <li> 
                                <div class="scrollbar" id="style-4">
                                 <div class="force-overflow">
		                       <p>${sl.shortDesc}</p>
		                       </div>
	                         </div>
                                
                                </li>
                            </ul>
                            <a href="user_modules_detail?courseModuleId=${sl.id}" class="pricingTable-signup btn-loaderClass">Read More...</a>
                        </div>
                    </div>
                </div>
                            </c:forEach>
	      
	
               
               
	</div>
    </section>  		
		
		
		
		
		
		
		
		</app-dashboard1>
	</div>
	</main>

<%@include file="footer.jsp"%>

</body>

</html>
