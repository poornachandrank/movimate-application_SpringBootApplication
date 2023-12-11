<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<title>Time-Master</title>

</head>
<body>

	
	<jsp:include page="../Header/header.jsp"></jsp:include>


	<div class="row">

		<div class="col-sm-4">

			<form id="timeForm" name="timeForm">
   
  <br> 
  <br>
				  <input id="timeId" hidden="">
				<div class="form-group">
				<label for="showName">Show Name:</label> 
				<input type="text" class="form-control"	id="showName" name="showName" required />
				</div>
					
				<div class="form-group">
			 <label for="showTime">Show Time:</label>
			 <input class="form-control" type="time" id="showTime" name="showTime" required />
                </div>
          <br>
				
			<div class="form-group" >
	<button type="button" class="btn btn-info" id="save" style="display: block;">Add Time</button>
	<br>
	<button type="submit" class="btn btn-info" id="update" onclick="getid()" style="display: none;">Update Time</button>
	
	<button type="button" class="btn btn-warning" id="reset" onclick="reSet()">Reset</button>
				</div>
			</form>
		</div>

		<div class="col-sm-8">
			<div class="panel-body">
				<table id="table-time" class="table table-primary">
				<caption>table</caption>
					<thead>
					
						<tr>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
						</tr>

					</thead>
				</table>

			</div>
		</div>

	</div>
	
	<script src="../../../component/jquery.validate.min.js"></script>

		<jsp:include page="../Footer/Footer.jsp"></jsp:include>
		<script src="../../../js/timeMaster.js"></script>


</body>
</html>