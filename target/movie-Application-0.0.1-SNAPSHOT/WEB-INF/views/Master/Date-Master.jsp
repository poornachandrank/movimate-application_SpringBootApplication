<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Date-Master</title>
</head>

<body>

	<jsp:include page="../Header/header.jsp"></jsp:include>
	<div class="row">
		<div class="col-sm-4">
			<form id="dateform" name="dateform">
				<input id="dateid">
				<div class="form-group">
					<label>Show Date:</label> <input type="date" value="2023s-12-12"
						name="showDate" id="showDate" class="form-control"
						placeholder="Show Date" size="30px" required>
				</div>
				<div class="form-group">
					<label for="showStatus">Show Status:</label> <select
						id="showStatus" name="showStatus" class="form-control">
						<option value="">SELECT SHOW STATUS</option>
						<option value="OPEN">open</option>
						<option value="CLOSE">close</option>
					</select>
				</div>
				<br>

				<div class="form-group">

					<button type="button" class="btn btn-info" id="save"
						style="display: block;">Add Date</button>
					<br>
					<button type="submit" class="btn btn-info" id="update"
						onclick="getid()" style="display: none;">Update Date</button>

					<button type="button" class="btn btn-warning" id="reset"
						onclick="reSet()">Reset</button>
				</div>
			</form>
		</div>

		<div class="col-sm-8">
			<div class="panel-body">
				<table id="dateTab" class="table table-primary">
					<caption>date</caption>
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





	<script src="//cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>

	<script src="../../../js/dateMaster.js"></script>
	<jsp:include page="../Footer/Footer.jsp"></jsp:include>


</body>
</html>