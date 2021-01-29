function validatePhone() {

	var phone = document.getElementById('contact').value;

	if (phone.length == 0) {
		producePrompt('Phone number is required.', 'phone-error', 'red');
		return false;
	}

	if (phone.length != 10) {
		producePrompt('Include area code.', 'phone-error', 'red');
		return false;
	}

	if (!phone.match(/^[0-9]{10}$/)) {
		producePrompt('Only digits, please.', 'phone-error', 'red');
		return false;
	}

	producePrompt('Valid', 'phone-error', 'green');
	return true;

}

function onlyAlphabets(e, t) {
	try {
		if (window.event) {
			var charCode = window.event.keyCode;
		} else if (e) {
			var charCode = e.which;
		} else {
			document.getElementById("errorName").textContent = "";
			return true;
		}
		if ((charCode > 64 && charCode < 91)
				|| (charCode > 96 && charCode < 123) || charCode == 32) {
			document.getElementById("errorName").textContent = "";
			return true;
		} else {
			//document.getElementById("errorName").innerHTML = "<spring:message code='label.onlyAlphabets'/>";
			document.getElementById("errorName").innerHTML = "Enter only text !";
			return false;
		}
	} catch (err) {
		//alert(err.Description);
	}
}

function onlyNumber(evt) {

	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		//document.getElementById("errorContact").innerHTML = "<spring:message code='label.onlyNumber'/>";
		document.getElementById("errorContact").innerHTML = "Enter only number !";
		return false;
	}
	document.getElementById("errorContact").textContent = "";
	return true;
}

function validateEmail(email) {  
	
	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	// var address = document.getElementById[email].value;
	if (reg.test(email) == false) {
		//alert(document.getElementById("errorEmail").innerHTML = "<spring:message code='label.invalidEmail'/>");
		document.getElementById("errorEmail").innerHTML = "Invalid Email format !";

		return false;
	} else {
		document.getElementById("errorEmail").textContent = "";

	}
}

function checkPassword(str) {
	// at least one number, one lowercase and one uppercase letter
	// at least six characters that are letters, numbers or the underscore
	var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])\w{6,}$/;
	if (re.test(str) == false) {
		document.getElementById("errorPassword").innerHTML = "Wrong password format !(Should be at least one number, one lowercase and one uppercase letter and at least six characters that are letters, numbers or the underscore)";
		return false;
	}else{
		document.getElementById("errorPassword").textContent = "";
	}
}
	

function loginIdValidate(email) {

	var val = false;

	$
			.ajax({
				url : 'LoginIdAvailablity?loginId=' + email,

				success : function(response) {

					if (jQuery.trim(response.errorEmail) == 'No') {

						val = true;

					} else {
						$("#loginId").val('');
						document.getElementById("errorEmail").innerHTML = "<spring:message code='label.loginidExist'/>";
						val = false;
					}

					return val;
				}

			});
	return val;
}

function mobileValidate(mob) {
	var val = false;

	if (mob.length != 10) {
		$("#contact").val('');
		document.getElementById("errorContact").innerHTML = "<spring:message code='label.ContactLength'/>";
	} else {

		$
				.ajax({
					url : 'mobileAvailablity?mobileNo=' + mob,

					success : function(response) {

						if (jQuery.trim(response.errorContact) == 'No') {

							val = true;

						} else {
							$("#contact").val('');
							document.getElementById("errorContact").innerHTML = "<spring:message code='label.ContactExist'/>";
							val = false;
						}

						return val;
					}

				});
		return val;
	}
}

function dropdownValidation() {
	
	var deptId = document.getElementById("deptId").value;
	var subDeptId = document.getElementById("subDeptId").value;
	
	if(deptId == 0){
		//alert("Please Select department!");
		document.getElementById("errorName").innerHTML = "Please Select department!";
		return false;
	}else{
		return true;
	}
}

function createUserValidation(){
	
    var response=false;
	
	var firstName = document.getElementById("firstName").value;
	var middleName = document.getElementById("middleName").value;
	var lastName = document.getElementById("lastName").value;
	var email1 = document.getElementById("email1").value;
	var password = document.getElementById("password").value;
	var contact = document.getElementById("contact").value;
	
	var designation = document.getElementById("designation").value;
	var companyId = document.getElementById("companyId").value;
	var districtId = document.getElementById("districtId").value;
	var role = document.getElementById("role").value;
	
	if(firstName==""){
		
	 	document.getElementById("errorFirstName").innerHTML = "Please enter first name!";
	 	response=false;
	}else
	if ((!/^[a-zA-Z]*$/g.test(firstName)) || firstName.length < 3 ){
	
		document.getElementById("errorFirstName").innerHTML = "Plese enter only alphabets of length minimum 3 character !";
		response=false;
    }else {
    	
    	document.getElementById("errorFirstName").innerHTML = "";
    	response=true;
    }
	
	if ((!/^[a-zA-Z]*$/g.test(middleName)) || middleName.length < 3 && middleName!="" ){
		
		document.getElementById("errorMiddleName").innerHTML = "Plese enter only alphabets of length minimum 3 character !";
		response=false;
    }else {
    	
    	document.getElementById("errorMiddleName").innerHTML = "";
    	response=true;
    }
	
	if(lastName==""){
	 	document.getElementById("errorLastName").innerHTML = "Please enter last name!";
	 	response=false;
	}else if ((!/^[a-zA-Z]*$/g.test(lastName)) || lastName.length < 3 ){
		document.getElementById("errorLastName").innerHTML = "Plese enter only alphabets of length minimum 3 character !";
		response=false;
    }else {
    	document.getElementById("errorLastName").innerHTML = "";
    	response=true;
    }
	
	if(email1==""){
	 	document.getElementById("errorEmail").innerHTML = "Please enter email!";
	 	response=false;
	}else if (/[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/.test(email1)){
    	document.getElementById("errorEmail").innerHTML = "";
    	response=true;
    }else {
    	document.getElementById("errorEmail").innerHTML = "You have entered an invalid email address!";
    	response=false;
    }
      
	if(companyId == 0){
		document.getElementById("errorCompany").innerHTML = "Please select company!";
		response=false;
	}else{
		document.getElementById("errorCompany").textContent = "";
	}    
	if(districtId == 0){
		document.getElementById("errorDistrict").innerHTML = "Please select district!";
		response=false;
	}else{
		document.getElementById("errorDistrict").textContent = "";
	} 
	if(designation == 0){
		
		document.getElementById("errorDesignation").innerHTML = "Please select designation!";
		response=false;
	}else{
		document.getElementById("errorDesignation").textContent = "";
	}
	if(role == 0){
		document.getElementById("errorRole").innerHTML = "Please select role!";
		response=false;
	}else{
		document.getElementById("errorRole").textContent = "";
	}
	if(!response){
		return response;
	}
	
	myFormSubmitWithConfirmationAndLoader();
}

function createSubDeptValidation(){
	
	var subDept = document.getElementById("subDept").value;
	
	if(subDept == 0){
		document.getElementById("errorSubDept").innerHTML = "Please select department!";
		return false;
	}else{
		document.getElementById("errorSubDept").textContent = "";
	}
	myFormSubmitWithConfirmationAndLoader();
}

function createDesignationValidation(){	
	var deptId = document.getElementById("deptId").value;
	
	if(deptId == 0){
		document.getElementById("errorDept").innerHTML = "Please select department!";
		return false;
	}else{
		document.getElementById("errorDept").textContent = "";
	}
	myFormSubmitWithConfirmationAndLoader();
}

function createOfficeValidation(){	
	var companyId = document.getElementById("companyId").value;
	
	if(companyId == 0){
		document.getElementById("errorCompany").innerHTML = "Please select company!";
		return false;
	}else{
		document.getElementById("errorCompany").textContent = "";
	}
	myFormSubmitWithConfirmationAndLoader();
}

function createDepartmentValidation(){	
	var companyId = document.getElementById("companyId").value;
	var officeId = document.getElementById("officeId").value;
	
	if(companyId == 0){
		document.getElementById("errorCompany").innerHTML = "Please select company!";
		return false;
	}else{
		document.getElementById("errorCompany").textContent = "";
	}
	
	if(officeId == 0){
		document.getElementById("errorOffice").innerHTML = "Please select office!";
		return false;
	}else{
		document.getElementById("errorOffice").textContent = "";
	}
	myFormSubmitWithConfirmationAndLoader();
}

function createDivisionValidation(){	
	var companyId = document.getElementById("companyId").value;
	var officeId = document.getElementById("officeId").value;
	var deptId = document.getElementById("deptId").value;
	
	if(companyId == 0){
		document.getElementById("errorCompany").innerHTML = "Please select company!";
		return false;
	}else{
		document.getElementById("errorCompany").textContent = "";
	}
	
	if(officeId == 0){
		document.getElementById("errorOffice").innerHTML = "Please select office!";
		return false;
	}else{
		document.getElementById("errorOffice").textContent = "";
	}
	
	if(deptId == 0){
		document.getElementById("errorDept").innerHTML = "Please select department!";
		return false;
	}else{
		document.getElementById("errorDept").textContent = "";
	}
	myFormSubmitWithConfirmationAndLoader();
}

function createCourseValidation(){	
	var courseCategoryCommonId = document.getElementById("courseCategoryCommonId").value;
	
	if(courseCategoryCommonId == 0){
		document.getElementById("errorCourseCategory").innerHTML = "Please select course category!";
		return false;
	}else{
		document.getElementById("errorCourseCategory").textContent = "";
	}
	 myFormSubmitWithConfirmationAndLoader();
}

function createCourseModuleValidation(){
var courseCommonId = document.getElementById("courseCommonId").value;
	
	if(courseCateroyId == 0){
		document.getElementById("errorCourse").innerHTML = "Please select course !";
		return false;
	}else{
		document.getElementById("errorCourse").textContent = "";
	}
	 myFormSubmitWithConfirmationAndLoader();
}

function createCourseAssignValidation(){
	//alert("Hi---");
	var courseCategory = document.getElementById("courseCategory").value;
	var courseId = document.getElementById("courseId").value;
	//var courseModuleId = document.getElementById("courseModuleId").value;
	var companyId = document.getElementById("companyId").value;
	var completiondate = document.getElementById("date-picker-example").value;
	
	
	if(courseCategory == 0){
		document.getElementById("errorCourseCategory").innerHTML = "Please select course category!";
		return false;
	}else{
		document.getElementById("errorCourseCategory").textContent = "";
	}    	
	if(courseId == 0){
		document.getElementById("errorCourse").innerHTML = "Please select course!";
		return false;
	}else{
		document.getElementById("errorCourse").textContent = "";
	}
//	if(courseModuleId == 0 || courseModuleId == ""){
//		document.getElementById("errorCourseModule").innerHTML = "Please select course module !";
//		return false;
//	}else{
//		document.getElementById("errorCourseModule").textContent = "";
//	}
	if(companyId == 0 || companyId == ""){
		document.getElementById("errorCompany").innerHTML = "Please select company !";
		return false;
	}else{
		document.getElementById("errorCompany").textContent = "";
	}
	
	if(completiondate == ""){
		document.getElementById("errorCompletionDate").innerHTML = "Please Choose completion date !";
		return false;
	}else{
		document.getElementById("errorCompletionDate").textContent = "";
	}
	 myFormSubmitWithConfirmationAndLoader();
}

function createQuestionValidation(){
	var courseCategory = document.getElementById("courseCategory").value;
	var courseId = document.getElementById("courseId").value;
	var courseModuleId = document.getElementById("courseModuleId").value;
	
	if(courseCategory == 0){
		document.getElementById("errorCourseCategory").innerHTML = "Please select course category!";
		return false;
	}else{
		document.getElementById("errorCourseCategory").textContent = "";
	}    	
	if(courseId == 0){
		document.getElementById("errorCourse").innerHTML = "Please select course!";
		return false;
	}else{
		document.getElementById("errorCourse").textContent = "";
	}
	if(courseModuleId == 0 || courseModuleId == ""){
		document.getElementById("errorCourseModule").innerHTML = "Please select course module !";
		return false;
	}else{
		document.getElementById("errorCourseModule").textContent = "";
	}
	myFormSubmitWithConfirmationAndLoader();
}

function createCourseContentValidation(){
	
	var courseCategory = document.getElementById("courseCategory").value;
	var courseId = document.getElementById("courseId").value;
	var courseModuleId = document.getElementById("courseModuleId").value;
	
	if(courseCategory == 0){
		document.getElementById("errorCourseCategory").innerHTML = "Please select course category!";
		return false;
	}else{
		document.getElementById("errorCourseCategory").textContent = "";
	}    	
	if(courseId == 0){
		document.getElementById("errorCourse").innerHTML = "Please select course!";
		return false;
	}else{
		document.getElementById("errorCourse").textContent = "";
	}
	if(courseModuleId == 0 || courseModuleId == ""){
		document.getElementById("errorCourseModule").innerHTML = "Please select course module !";
		return false;
	}else{
		document.getElementById("errorCourseModule").textContent = "";
	}
	 myFormSubmitWithConfirmationAndLoader();
}

function createCourseContentUploadValidation(){
	var courseCategory = document.getElementById("courseCategory").value;
	var courseId = document.getElementById("courseId").value;
	var courseModuleId = document.getElementById("courseModuleId").value;
	
	if(courseCategory == 0){
		document.getElementById("errorCourseCategory").innerHTML = "Please select course category!";
		return false;
	}else{
		document.getElementById("errorCourseCategory").textContent = "";
	}    	
	if(courseId == 0){
		document.getElementById("errorCourse").innerHTML = "Please select course!";
		return false;
	}else{
		document.getElementById("errorCourse").textContent = "";
	}
	if(courseModuleId == 0 || courseModuleId == ""){
		document.getElementById("errorCourseModule").innerHTML = "Please select course module !";
		return false;
	}else{
		document.getElementById("errorCourseModule").textContent = "";
	}
	 myFormSubmitWithConfirmationAndLoader();
}

function showCourseContentUploadValidation(){
	var courseCategory = document.getElementById("courseCategory").value;
	var courseId = document.getElementById("courseId").value;
	var courseModuleId = document.getElementById("courseModuleId").value;
	
	if(courseCategory == 0){
		document.getElementById("errorCourseCategory").innerHTML = "Please select course category!";
		return false;
	}else{
		document.getElementById("errorCourseCategory").textContent = "";
	}    	
	if(courseId == 0){
		document.getElementById("errorCourse").innerHTML = "Please select course!";
		return false;
	}else{
		document.getElementById("errorCourse").textContent = "";
	}
	if(courseModuleId == 0 || courseModuleId == ""){
		document.getElementById("errorCourseModule").innerHTML = "Please select course module !";
		return false;
	}else{
		document.getElementById("errorCourseModule").textContent = "";
	}
	myFormSubmitWithConfirmationAndLoader();
}