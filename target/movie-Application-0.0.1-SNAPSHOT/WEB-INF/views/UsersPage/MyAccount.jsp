<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

  <title>My Account</title>
<body>
	<jsp:include page="../User-logged-page.jsp"></jsp:include>
<div class="container">
    <h1>My Account</h1>
    <div class="row">
      <div class="col-md-6">
        <h3>User Information</h3>
        
        <div class="form-group">
            <label for="name">UserName:</label>
            <input type="text" class="form-control" id="name" placeholder="" readonly="readonly" value="${userData.userName}">
          </div>
          <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" placeholder=""  value="${userData.fullName}" readonly="readonly">
          </div>
          <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" value="${userData.email}"  readonly="readonly">
          </div>
        
      </div>
    </div>
    <hr>
    <div class="row">
      <div class="col-md-6">
        <h3>Change Password</h3>
        <form id="changePasswordForm" name="changePasswordForm">
          <div class="form-group">
            <label for="currentPassword">Current Password:</label>
            <input type="password" class="form-control" id="currentPassword" name="currentPassword" placeholder="Enter current password">
            <small id="currentPasswordError" class="form-text text-danger"></small>
          </div>
          <div class="form-group">
            <label for="newPassword">New Password:</label>
            <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="Enter new password">
            <small id="newPasswordError" class="form-text text-danger"></small>
          </div>
          <div class="form-group">
            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm new password">
            <small id="confirmPasswordError" class="form-text text-danger"></small>
          </div>
          <button type="submit" class="btn btn-primary">Change Password</button>
        </form>
      </div>
    </div>
  </div>

	<jsp:include page="../Footer/Footer.jsp"></jsp:include>


<script type="text/javascript">




//validation
  document.getElementById('changePasswordForm').addEventListener('submit', function(event) {
      event.preventDefault();
      clearErrors();
      validateForm();
    });

    function validateForm() {
      var currentPassword = document.getElementById('currentPassword').value;
      var newPassword = document.getElementById('newPassword').value;
      var confirmPassword = document.getElementById('confirmPassword').value;

      if (currentPassword === '') {
        showError('currentPassword', 'Please enter the current password.');
      }

      if (newPassword === '') {
        showError('newPassword', 'Please enter the new password.');
      }

      if (confirmPassword === '') {
        showError('confirmPassword', 'Please confirm the new password.');
      } else if (newPassword !== confirmPassword) {
        showError('confirmPassword', 'Passwords do not match.');
      }

      if (currentPassword !== '' && newPassword !== '' && confirmPassword !== '' && newPassword === confirmPassword) {
        changePassword(currentPassword, newPassword);
      }
    }

    function showError(field, errorMessage) {
      var errorElement = document.getElementById(field + 'Error');
      errorElement.textContent = errorMessage;
    }

    function clearErrors() {
      document.getElementById('currentPasswordError').textContent = '';
      document.getElementById('newPasswordError').textContent = '';
      document.getElementById('confirmPasswordError').textContent = '';
    }

    function changePassword(currentPassword, newPassword) {
		var formData = new FormData($('#changePasswordForm')[0]);

     	console.log(formData);
     	$.ajax({
    		url: "/updatePass",
			type: "POST",
			data: formData,
			processData: false,
			contentType: false,
			success: function(data){
				if(data="Success"){
		      	 alert('Password changed successfully!');
		      
		      	
			}},error:function(xhr, status, error){
				
				alert("Current PassWord Is Invalid")
			}
    	}) ;
     
    }
</script>
</body>
</html>



  