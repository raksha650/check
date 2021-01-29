<!-- <footer class="page-footer pt-0 mt-5 rgba-stylish-light pull-bottom" >
    <div class="footer-copyright py-3 text-center">
          <div class="container-fluid">
             Powered By <a href="http://www.cinfysystems.com" target="_blank">Cinfy Systems</a>
        </div>
    </div>
</footer> -->

<script src="<c:url value="/js/jquery-3.2.1.min.js"/>" ></script>
<script src="<c:url value="/js/popper.min.js"/>" ></script>
<script src="<c:url value="/js/bootstrap.js"/>" ></script>
<script src="<c:url value="/js/mdb.min.js"/>" ></script>
<script src="<c:url value="/js/custom.js"/>" ></script>

<script src="<c:url value="/js/jquery.dataTables.min.js"/>" ></script>
<script src="<c:url value="/js/dataTables.bootstrap4.min.js"/>" ></script>
<script src="<c:url value="/js/bootstrap-datepicker.min.js"/>" ></script>

<!-- <script src="https://code.jquery.com/ui/1.12.0-rc.2/jquery-ui.js"></script> -->
<script src="<c:url value="/user/js/jquery-ui.js"/>" ></script>

<script src="<c:url value="/js/canvasjs.min.js"/>" ></script>

<!-- <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script> -->
<!-- 
<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.0/additional-methods.min.js"></script>
 -->

 <script type="text/javascript">
function blockSpecialChar(e) {
	var k = e.keyCode;
	return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8   || (k >= 48 && k <= 57) || (k == 32 ));
}
function blockSpecialChar_numbers(e) {
	var k = e.keyCode;
	return ((k > 64 && k < 91) || (k > 96 && k < 123) || (k == 8 ) || (k == 32 ));
}
function blockSpecialChar_numbers_space(e) {
	var k = e.keyCode;
	return ((k > 64 && k < 91) || (k > 96 && k < 123) || (k == 8 ));
}
function blockAlphabets(e) {
	var k = e.keyCode;
	return (k == 43   || k >= 48 && k <= 57);
}

function blockMessages(e) {
var k = e.keyCode;
document.all ? k = e.keyCode : k = e.which;
return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k == 10 || k == 13 || (k >= 48 && k <= 57));
}

function amount(evt) {
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57))
        return false;

    return true;
}    

</script>

<script>
$(document).ready(function() {
    $('#example').DataTable( {
        "paging":   false
        
    } );
} );
</script>

<script src="<c:url value="/user/user/js/jquery.simple.timer.js"/>" ></script>
<script src="<c:url value="/user/user/jquery.confirm/jquery.confirm.js"/>"></script>
<script src="<c:url value="/user/user/js/script.js"/>" ></script>


<script>
$(document).ready(function(){
    $('.btn-loaderClass').click(function() {
        $('.showLoaderClass').show();
    });
   
});
</script>
<script>
function myFormSubmitWithConfirmationAndLoader(){
	event.preventDefault();
	$.confirm({
		'title'		: 'Save Confirmaton',
		'message'	: 'Do you really want to save record ?',
		'buttons'	: {
			'Yes'	: {
				'class'	: 'blue',
				'action': function(){						
					  $('.showLoaderClass').show();
					  var myForm = document.getElementById("myForm");
						myForm.submit();
				}
			},
			'No'	: {
				'class'	: 'gray',
				'action': function(){
					$('.showLoaderClass').hide();
					 return false;
				}
			}
		}
	});
	
}
</script>

<script>
function myFormSubmitLoader(){
	             event.preventDefault();
			
					  $('.showLoaderClass').show();
					  var myForm = document.getElementById("myForm");
						myForm.submit();
					 
	}
	

</script>