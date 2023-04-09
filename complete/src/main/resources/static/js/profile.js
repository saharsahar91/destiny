


$(function () {

	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	var userId = urlParams.get('userId');
	
	$.ajax({
		type: "GET",
	     url: "api/getUserById",
	    data:{
			 userId: userId,
		 },
	     error: function(){
			 var x = 5;
	     },
	     success: function(result){
			 $('#username').val(result.username);
			 $('#email-address').val(result.email);
			 $('#first-name').val(result.first_name);
			 $('#last-name').val(result.last_name);
			 $('#welcome-comment').text("Welcome " + result.first_name);
	     },
	     timeout: 3000000 
	});
	
	$("#save-profile-changes").on('click', function(){
		if (!validate()) return;
		$.ajax({
			type: "POST",
		    url: "api/updateUserProfile",
		    data:{
				 username: $('#username').val(),
				 email: $('#email-address').val(),
				 firstName: $('#first-name').val(),
				 lastName: $('#last-name').val(),
				 userId: userId
			 },
		     error: function(){
				 var x = 5;
		     },
		     success: function(){
		         toastr.success('Profile information are updated successfully!');
		     },
		     timeout: 3000000 
		});
	});
	
	$('#change-password-button').on('click', function(){
		window.location.href = "/change-password?userId=" + userId;
	});
	
	$('#go-to-home').on('click', function(){
		window.location.href = "/landing?userId=" + userId;
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
		
		if($('#first-name').val() == ""){
			$('#first-name').addClass('has-error');
			validate = false;
		} else {
			$('#first-name').removeClass('has-error');
		}
		
		if($('#last-name').val() == ""){
			$('#last-name').addClass('has-error');
			validate = false;
		} else {
			$('#last-name').removeClass('has-error');
		}
		
		return validate;
	}
})