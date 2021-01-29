<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<style>
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
</head>


<body style="height: 100%; width: 100%;">

	<div style="display: none !important"><%@include
			file="header.jsp"%></div>
	<div style="display: none !important"><%@include
			file="sidenev.jsp"%></div>

	<main _ngcontent-c0="" class="pt-5 mx-lg-5" style="padding:0px !important"
		ng-reflect-klass="pt-5 mx-lg-5" ng-reflect-ng-class="[object Object]">
	<div _ngcontent-c0="" class="mt-5">
		<router-outlet _ngcontent-c0=""></router-outlet>
		<app-dashboard1 _nghost-c3="">
	

	
		 <section _ngcontent-c4="" >
			
		 <div style="display:${assessmentLogDetails.status=='pass'?'block':'none' }"> 	
<div  id="review-page">
	<div  >
    	<div class="modal-content">
      		<div class=" text-center" style="background-color:#046076; padding:10px">
        		<h4 class="modal-title text-white">Review Page</h4>
        		<!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
      		</div>
      		<div class="modal-body">
        		<div class="row">
                	<div class="col-md-12">
                	
                    	<div class="total-marks modal-title"><strong class="${assessmentLogDetails.status=='pass'?'text-green padding-l-15':'text-red padding-l-15' } pull-left">Result: ${fn:toUpperCase(assessmentLogDetails.status)}</strong><strong>Total Marks : ${assessmentLogDetails.questionCorrected }/${assessmentLogDetails.totalNoOfQuestion }</strong></div>
                        <div class="row margin-b-10">
                        	<div class="col-md-12">
                        	   <c:forEach items="${quesListDb}" var="sl" varStatus="status">
                            	<div class="card">
                                    <div class="card-header green-bg text-white">
                                         Question# ${status.count} - ${sl.question }:
                                    </div>
                                    <div class="card" style="margin:15px !important;">
                                    <div class="row">
                                    <div class="col-sm-6">
                                    <div class="card-body">
                                    <div class="form-group">
                                          <div class="checkbox"><strong>1.</strong>
                                              
                                                <label class="checkbox-label">${sl.option1 }</label>
                                           </div>
                                      </div> 
                                
                                     <div class="form-group">
                                         <div class="checkbox"><strong>2.</strong>
                                              
                                                <label class="checkbox-label">${sl.option2 }</label>
                                           </div>
                                      </div> 
                                    </div>
                                    </div>
                                    <div class="col-sm-6">
                                    <div class="card-body">
                                    <div class="form-group">
                                          <div class="radio"><strong>3.</strong>
                                             
                                                <label class="checkbox-label">${sl.option3 }</label>
                                           </div>
                                      </div> 
                                
                                     <div class="form-group">
                                         <div class="radio"><strong>4.</strong>
                                             
                                                <label class="checkbox-label">${sl.option4 }</label>
                                           </div>
                                      </div> 
                                    </div>
                                    </div>
                                    </div></div>
                                   
                                      <c:forEach items="${quesListUser}" var="element" varStatus="status1"> 
                                       <c:if test="${status1.count eq status.count}">  
                                    <div class="card-body row">
                                        <div class="a-heading col-sm-3">
                                            <span class="text-red"><i class="fa fa-check-square-o"></i></span>
                                            <span><strong>Marked Answer :</strong></span>
                                           
                                           
                                           <strong class="text-red padding-l-15">${element.answer }</strong>
                                           
                                           
                                        </div>
                                        <div class="c-heading col-sm-3">
                                            <span class="text-green"><i class="fa fa-check-square-o"></i></span>
                                            <span><strong>Correct Answer :</strong></span>
                                            <strong class="text-green padding-l-15">${sl.answer }</strong>
                                        </div>
                                        
                                         <div class="c-heading col-sm-5 "><span class="marks pull-right"><strong>Marks : ${element.answer.equals(sl.answer)?"1":"0" }/1</strong></span></div>
                                       
                                    </div>
                                     </c:if>
                                    </c:forEach>
                                   
                                    <div class="card-footer">
                                        <div><strong>Explanation :</strong></div><div>${sl.description }</div>
                                    </div>
                    			</div>
                    			</c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
      		<div class="modal-footer">
      		<form:form action="certificate" method="post" modelAttribute="assessmentLogDetails">
      		<form:hidden path="userId"/>
      		<form:hidden path="courseModuleId"/>
        	<div align="center">	<a class="btn btn-success pull-center btn-loaderClass" href="user_dashboard">Finish</a></div>
        		</form:form>
      		</div>
    	</div>
  	</div>
</div>
</div>
		
		
	
		<div style="display:${assessmentLogDetails.status=='fail'?'block':'none' }">		

		<div class="row" style="margin-bottom: 320px;">
                                                <div class="col-md-12">
                                                    <div class="box-border" style="background:#fff;">
                                                        <div class="text-center" style="background-color:#046076; padding:10px">
                                                        <h4 class="modal-title text-white">Review Page</h4>
                                                        </div>
                                                        <div class="row padding-b-none margin-b-none" align="center"	>
                                                            <div class="col-md-12" style="padding-bottom:5px;">  
                                                                <p class="test-heading">Result:<strong class="${assessmentLogDetails.status=='pass'?'text-green padding-l-15':'text-red padding-l-15' } ">${fn:toUpperCase(assessmentLogDetails.status)}</strong></p> 
                                                           
                                                                <p class="test-heading"><strong>Total Marks : ${assessmentLogDetails.questionCorrected }/${assessmentLogDetails.totalNoOfQuestion }</strong></p>    
                                                                <p class="test-heading" style="color:#333333;">You are fail in this test please try next time.</p>                                              
                                                            </div>
                                                        </div> 
                                                        <div align="center">
                                                        <form action="user_modules_slides" method="post" >
                                                        <input type="hidden" name="courseModuleId" value="${assessmentLogDetails.courseModuleId.id}">
                                                        <button type="submit" class="action text-capitalize cancel btn my-btn btn-success btn-loaderClass">Read The Course Again</button>
                                                       <a class="btn my-btn btn-success btn-loaderClass" href="user_dashboard">Exit</a>
                                                        </form>
                                                        </div>
                                                    </div>
                                                </div>
                                               
                                            </div>
                                            
                                         
                                            </div>
		</section>
		</div>
		</app-dashboard1>
	</div>
	</main>
<style>
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
<div class="modal showLoaderClass" style="display: none">
		<div class="center">
			<img alt="" src="images/loader.gif" width="150px" height="150px" />
		</div>
	</div>
	<script>
$(document).ready(function(){
    $('.btn-loaderClass').click(function() {
        $('.showLoaderClass').show();
    });
   
});
</script>
<%@include file="footer.jsp"%>


<div class="modal showLoaderClass" style="display: none">
		<div class="center">
			<img alt="" src="images/loader.gif" width="150px" height="150px" />
		</div>
	</div>




</body>

</html>
