$(document).ready(function(){
	
	
	
	$('.cancelConfirmAssessment').click(function(){
		$.confirm({
			'title'		: 'Starting the Assessment?',
			'message'	: 'Do you really want to leave this page and start the Assessment ?',
			'buttons'	: {
				'Yes'	: {
					'class'	: 'blue',
					'action': function(){
						var myForm = document.getElementById("myForm");
						document.getElementById('myForm').action = "user_question_paper";
						myForm.submit();
					}
				},
				'No'	: {
					'class'	: 'gray',
					'action': function(){
						$('.showLoaderClass').hide();
						
					}
				}
			}
		});
		
	});
	
	
	$('.cancelConfirmPaperCancel').click(function(){
		$.confirm({
			'title'		: 'Cancel Assessment',
			'message'	: 'Do you really want to cancel Assessment ?',
			'buttons'	: {
				'Yes'	: {
					'class'	: 'blue',
					'action': function(){
						window.location.href = "user_dashboard";
					}
				},
				'No'	: {
					'class'	: 'gray',
					'action': function(){
						$('.showLoaderClass').hide();
						
					}
				}
			}
		});
		
	});
	
	$('.cancelConfirmPaperSubmit').click(function(){
		$.confirm({
			'title'		: 'Submit Assessment',
			'message'	: 'Do you really want to submit Assessment ?',
			'buttons'	: {
				'Yes'	: {
					'class'	: 'blue',
					'action': function(){
						var myForm = document.getElementById("myForm");
						document.getElementById('myForm').submit();
						
					}
				},
				'No'	: {
					'class'	: 'gray',
					'action': function(){
						$('.showLoaderClass').hide();
						
					}
				}
			}
		});
		
	});
	
	$('.cancelConfirmFinishReading').click(function(){
		$.confirm({
			'title'		: 'Course Exit Confirmation',
			'message'	: 'Do you really want to finish the course and skip the exam for now?',
			'buttons'	: {
				'Yes'	: {
					'class'	: 'blue',
					'action': function(){
						var myForm = document.getElementById("myForm");
						document.getElementById('myForm').action = "user_modules_slides_reading_finish";
						document.getElementById('myForm').submit();
					}
				},
				'No'	: {
					'class'	: 'gray',
					'action': function(){
						$('.showLoaderClass').hide();
					}
				}
			}
		});
		
	});
	
	$('.cancelConfirmStartExam').click(function(){
		$.confirm({
			'title'		: 'Course reading Confirmation',
			'message'	: 'Sorry! You have not completed the course yet, do you want to start the course now?',
			'buttons'	: {
				'Yes'	: {
					'class'	: 'blue',
					'action': function(){
						document.getElementById('myForm').action = "user_modules_slides";
						document.getElementById('myForm').submit();
					}
				},
				'No'	: {
					'class'	: 'gray',
					'action': function(){
						 $('.showLoaderClass').hide();
						 
					}
				}
			}
		});
		
	});
	
	
	$('.cancelConfirm1').click(function(){
		$.confirm({
			'title'		: 'Cancel Confirmation',
			'message'	: 'Do you really want to cancel this course?',
			'buttons'	: {
				'Yes'	: {
					'class'	: 'blue',
					'action': function(){
						document.getElementById("isCourseFinished").value=false;
						document.getElementById('myForm').action = "user_modules_slides_reading_finish";
						document.getElementById('myForm').submit();
					 
					}
				},
				'No'	: {
					'class'	: 'gray',
					'action': function(){
						$('.showLoaderClass').hide();
						
					}
				}
			}
		});
		
	});
	
	$('.cancelConfirm2').click(function(){
		$.confirm({
			'title'		: 'Cancel Confirmation',
			'message'	: 'Do you really want to cancel this course?',
			'buttons'	: {
				'Yes'	: {
					'class'	: 'blue',
					'action': function(){
						window.location.href = "safe-social-networking.html";
					}
				},
				'No'	: {
					'class'	: 'gray',
					'action': function(){}
				}
			}
		});
		
	});
	
	$('.cancelConfirm3').click(function(){
		$.confirm({
			'title'		: 'Cancel Confirmation',
			'message'	: 'Do you really want to cancel this course?',
			'buttons'	: {
				'Yes'	: {
					'class'	: 'blue',
					'action': function(){
						window.location.href = "course-detail.html";
					}
				},
				'No'	: {
					'class'	: 'gray',
					'action': function(){}
				}
			}
		});
		
	});
	
	$('.cancelConfirm4').click(function(){
		$.confirm({
			'title'		: 'Cancel Confirmation',
			'message'	: 'Do you really want to cancel this course?',
			'buttons'	: {
				'Yes'	: {
					'class'	: 'blue',
					'action': function(){
						window.location.href = "course-detail.html";
					}
				},
				'No'	: {
					'class'	: 'gray',
					'action': function(){}
				}
			}
		});
		
	});
	
	$('.cancelConfirm5').click(function(){
		$.confirm({
			'title'		: 'Cancel Confirmation',
			'message'	: 'Do you really want to cancel this course?',
			'buttons'	: {
				'Yes'	: {
					'class'	: 'blue',
					'action': function(){
						window.location.href = "course-detail.html";
					}
				},
				'No'	: {
					'class'	: 'gray',
					'action': function(){}
				}
			}
		});
		
	});
	
	$('.cancelConfirm6').click(function(){
		$.confirm({
			'title'		: 'Cancel Confirmation',
			'message'	: 'Do you really want to cancel this course?',
			'buttons'	: {
				'Yes'	: {
					'class'	: 'blue',
					'action': function(){
						window.location.href = "course-detail.html";
					}
				},
				'No'	: {
					'class'	: 'gray',
					'action': function(){}
				}
			}
		});
		
	});
	
	
	
});
