<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<title>Movie-Master</title>
</head>

<body>
	<jsp:include page="../Header/header.jsp"></jsp:include>
	
	<div id="loading"></div>
	<div class="row">
		<div class="col-sm-4">
			<div class="container">
				

				<form id="movieForm" method="post" enctype="multipart/">
						<input id="movieId" type="number" name="movieId" hidden="" ></input>
				
					<div class="form-group">
						<label for="movieTitle">Title</label> <input type="text"
							class="form-control" id="movieTitle" name="movieTitle"
							placeholder="Enter movie title" required />
					</div>
					<div class="form-group">
						<label for="movieDirector">Director</label> <input type="text"
							class="form-control" id="movieDirector" name="movieDirector"
							placeholder="Enter movie director" required />
					</div>
					<div class="form-group">
						<label for="movieYear">Year</label> <input type="date"
							class="form-control" id="movieYear" name="movieYear"
							placeholder="Enter movie year" required />
					</div>
					<div class="form-group">
						<label for="language">Language:</label> <input type="text"
							class="form-control" id="language" name="language"
							placeholder="Enter movie language" required />
					</div>
					<div class="form-group">
						<label for="movieGenre">Genre:</label> <input type="text"
							class="form-control" id="movieGenre" name="movieGenre"
							placeholder="Enter movie genre" required />
					</div>
					<div class="form-group">
						<label for="movieRunTime">RunTime:</label> <input type="text"
							class="form-control" id="movieRunTime" name="movieRunTime"
							placeholder="Enter movie runTime" required />
					</div>
					<div class="form-group">
						<label for="movieCast">Cast:</label>
						<textarea class="form-control" id="movieCast" name="movieCast"
							rows="5px" cols="5px" required placeholder="Enter cast names"></textarea>
					</div>
					<div>
						<small></small> <img class="movieposter-preview" alt="" src=""
							width="50" height="50" />
					</div>
					<div class="form-group">
						<label for="movieposter">SelectPoster:</label> <input type="file"
							class="form-control" id="movieposter" name="movieposter" required />
						<br>
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-info" id="update"
							onclick="getid()" style="display: none;">Update Movie</button>

						<button type="submit" class="btn btn-info" id="save"
							style="display: block;">Add Movie</button>
						<button class="btn btn-warning" name="reset" type="button"
							id="reset">Reset</button>
					</div>

				</form>

			</div>
		</div>





		<div class="col-sm-8">
			<div class="panel-body">
				<table id="moviesTable" class="display" style="width: 100%">
					<caption>MOVIE TABLE</caption>
					<thead>
						<tr>
							<th>S.NO</th>
							<th>Poster</th>
							<th>Title</th>
							<th>Director</th>
							<th>Release-date</th>
							<th>language</th>
							<th>Genre</th>
							<th>Runtime</th>
							<th>Cast</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>

			</div>
		</div>
	</div>

	<script src="../../../js/movieMasterscripts.js"></script>
	<script src="../../../component/jquery.validate.min.js"></script>
	<script src="../../../component/jquery-3.4.1.min.js"></script>
	<script src="../../../component/DataTables/datatables.min.js"></script>

	<jsp:include page="../Footer/Footer.jsp"></jsp:include>
</body>

</html>