<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Show-Master</title>
</head>
<body>
	<jsp:include page="../Header/header.jsp"></jsp:include>


	<div class="row">
		<div class="col-sm-4">
			<form id="createShowForm" name="createShowForm"
				class="form-horizontal">
				<div class="form-group">
					<label for="cinemaName" class="col-sm-2 control-label">Cinema:</label>
					<div class="col-sm-10">
						<select id="cinemaName" name="cinemaName" class="form-control">
							<option value="">Select a cinema</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="movieTitle" class="col-sm-2 control-label">Movie:</label>
					<div class="col-sm-10">
						<select id="movieTitle" name="movieTitle" class="form-control">
							<option value="">Select a movie</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="showDate" class="col-sm-2 control-label">Date:</label>
					<div class="col-sm-10">
						<input type="date" name="showDate" id="showDates"
							class="form-control">
					</div>
				</div>

				<div class="form-group">
					<label for="showTime" class="col-sm-2 control-label">Time:</label>
					<div class="col-sm-10">
						<input type="time" name="time" id="time" class="form-control">
						<input type="hidden" name="showTime" id="showTime"
							class="form-control">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Screens:</label>
					<div class="col-sm-10">
						<div class="screens-container" id="screens-container"></div>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" id="save" class="btn btn-primary">Create
							Show</button>
					</div>
				</div>
			</form>


		</div>
		<div class="col-sm-8">
			<div class="panel-body">
				<div class="container">

					<label>show</label> <select name="pageSize" id="pageSize">
						<option value="5">5</option>
						<option value="10">10</option>
						<option value="20">20</option>
					</select> <label>entries</label>
					<table  id="tabledata" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
						<caption class="default namespace">User Data</caption>

					</table>
				</div>
				<nav class="mt-5 " id="pagination"
					aria-label="Page navigation example">
					<div id="pagination-container"></div>
				</nav>
			</div>

		</div>
	</div>









	<script src="../../../js/Show-master.js"></script>
	<jsp:include page="../Footer/Footer.jsp"></jsp:include>

	

</body>
</html>