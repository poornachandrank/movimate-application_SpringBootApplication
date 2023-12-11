<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ADMIN DASH BOARD</title>
</head>
<body>
	<jsp:include page="../Header/header.jsp"></jsp:include>
	<div class="row">
		<div class="col-sm-4">
			<div class="container">

				<form id="cinemaForm" name="cinemaForm">
					<input id="cinemaID">
					<div class="form-group">
						<label>Cinema Name</label> <input type="text" name="cinemaName"
							id="cinemaName" class="form-control" placeholder="Cinema Name"
							size="30px" required>
					</div>

					<div class="form-group">
						<label>Cinema Address</label> <input type="text"
							name="cinemaAddress" id="cinemaAddress" class="form-control"
							placeholder="Cinema Address" size="30px" required>
					</div>
					<div class="form-group">
						<label>No.Of.Screen</label> <input type="number" name="screen"
							id="screen" class="form-control" placeholder="No.Of.Screens"
							size="30px" required>
					</div>

					<div class="form-group">
						<button type="submit" class="btn btn-info" id="update"
							onclick="getcinemaid()" style="display: none;">Update
							Movie</button>
						<button type="submit" class="btn btn-info" id="save"
							style="display: block;">Add Cinema</button>
						<button class="btn btn-warning" name="reset" type="button"
							id="reset">Reset</button>
					</div>
				</form>
			</div>


		</div>
		<div class="col-sm-8">

			<div class="panel-body">
				<table id="tbl-cinema" class="table table-active">
					<caption>cinema table</caption>
					<thead>
						<tr>
							<th></th>
							<th></th>
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




	<script src="../../../js/common.js"></script>
	<script src="../../../js/cinemaMasterscript.js"></script>
	<jsp:include page="../Footer/Footer.jsp"></jsp:include>
</body>
</html>