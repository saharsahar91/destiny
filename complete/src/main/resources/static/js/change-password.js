


$(function () {

	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	var userId = urlParams.get('userId');
	
	$("#save-password-changes").on('click', function(){
		if (!validate()) return;
		$.ajax({
			type: "POST",
		    url: "api/changePassword",
		    data:{
				userId: userId,
				 oldPassword: $('#old-password').val(),
				 newPassword: $('#new-password').val(),
			 },
		     error: function(){
				 var x = 5;
		     },
		     success: function(result){
				 if(result == true) {
		         	toastr.success('Password updated successfully!');
		         	window.location.href = "/login";
	         	} else {
         		 	toastr.error('Password update failed!');
         		 	}
		     },
		     timeout: 3000000 
		});
	});
	
	$('#change-password-button').on('click', function(){
		window.location.href = "/change-password";
	})
		
	function validate(){
		var validate = true;
		if($('#old-password').val() == ""){
			$('#old-password').addClass('has-error');
			validate = false;
		} else {
			$('#old-password').removeClass('has-error');
		}
		
		if($('#new-password').val() == ""){
			$('#new-password').addClass('has-error');
			validate = false;
		} else {
			$('#new-password').removeClass('has-error');
		}
		
		if($('#confirm-new-password').val() == ""){
			$('#confirm-new-password').addClass('has-error');
			validate = false;
		} else {
			$('#confirm-new-password').removeClass('has-error');
		}
		
		if($('#confirm-new-password').val() !== "" && $('#confirm-new-password').val() !== ""
		&& $('#confirm-new-password').val() != $('#confirm-new-password').val()){
			$('#confirm-new-password').addClass('has-error');
			$('#new-password').addClass('has-error');
			validate = false;
		} else {
			$('#confirm-new-password').removeClass('has-error');
			$('#new-password').removeClass('has-error');
		}
		
		return validate;
	}
})