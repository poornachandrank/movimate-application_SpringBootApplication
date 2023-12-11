<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Screen-Master</title>
<body>
	<jsp:include page="../Header/header.jsp"></jsp:include>


	<div class="row">
		<div class="col-sm-4">
			<form id="screen-form" name="screen-form">
				<div class="form-group">
					<label for="cinemaName" class="col-sm-2 control-label">Cinema:</label>
					<div class="col-sm-10">
						<select id="cinemaName" name="cinemaName" class="form-control">
							<option value="nil">Select a cinema</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Screens:</label>
					<div class="col-sm-10">
						<div class="screens-container" id="screens-container"></div>
					</div>
				</div>



				<div class="form-group">
					<button type="submit" class="btn btn-info" id="save"
						style="display: block;">Save Screen</button>
					<button type="submit" class="btn btn-info" id="update"
						onclick="getid()" style="display: none;">Update Screen</button>
					<button type="button" class="btn btn-warning" id="reset"
					>Reset</button>
				</div>
			</form>
		</div>

		<div class="col-sm-8">
			<div class="panel-body">
				<table id="table-screen" class="table table-primary">
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


	<script src="../../../js/screenMaster.js"></script>
	<jsp:include page="../Footer/Footer.jsp"></jsp:include>

</body>
</html>