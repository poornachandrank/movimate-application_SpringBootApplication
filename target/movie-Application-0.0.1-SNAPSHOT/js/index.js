
$(document).ready(function() {

	$('#signUpForm').submit(function(event) {
		event.preventDefault(); // prevent the form from submitting normally
		var formData = new FormData($('#signUpForm')[0]);
		alert("hi")
		$.ajax({
			url: "/Signup",
			method: "POST",
			data: formData,
			processData: false,
			contentType: false,
			success: function(data) {
				if (data == 'saved') {
					console.log(data);
					alert('User has been registered successfully!');
				}
			},
			error: function(xhr, status, error) {
				console.log("Error uploading file: " + error);
			},
		});

	});
});







