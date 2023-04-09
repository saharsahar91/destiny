


$(function () {

$('#sign-up-button').click(function(e){
	e.stopPropagation();
	var username = $('#username').val();
	var emailAddress = $('#email-address').val();
	var password = $('#password').val();
	var confirmPassword = $('#confirm-password').val();
	if(!validate()) return;
	$.ajax({
		type: "POST",
	     url: "api/createUser",
	    data:{
			 username: username,
			 password: password,
			 email: emailAddress
		 },
	     error: function(){
			 var x = 5;
	         // will fire when timeout is reached
	     },
	     success: function(){
	         window.location.href = "http://localhost:8080/login";
	     },
	     timeout: 3000000 // sets timeout to 3 seconds
		});
	});
	
	function validate(){
		var validate = true;
		if($('#username').val() == ""){
			$('#username').addClass('has-error');
			validate = false;
		} else {
			$('#username').removeClass('has-error');
		}
		if($('#email-address').val() == ""){
			$('#email-address').addClass('has-error');
			validate = false;
		} else {
			$('#email-address').removeClass('has-error');
		}
		if($('#password').val() == ""){
			$('#password').addClass('has-error');
			validate = false;
		} else {
			$('#password').removeClass('has-error');
		}
		if($('#confirm-password').val() == ""){
			$('#confirm-password').addClass('has-error');
			validate = false;
		} else {
			$('#confirm-password').removeClass('has-error');
		}
		return validate;
	}
})