


$(function () {

$('#log-in-button').click(function(e){
	e.stopPropagation();
	var username = $('#username').val();
	var password = $('#password').val();
	if(!validate()) return;
	$.ajax({
		type: "GET",
	     url: "api/getUser",
	    data:{
			 username: username,
			 password: password
		 },
	     error: function(){
			toastr.error("Incorrect Username or Password!")
	     },
	     success: function(result){
	         window.location.href = "http://localhost:8080/landing?userId="+ result.user_id;
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
		
		if($('#password').val() == ""){
			$('#password').addClass('has-error');
			validate = false;
		} else {
			$('#password').removeClass('has-error');
		}
		
		return validate;
	}
})