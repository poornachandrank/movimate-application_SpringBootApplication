<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Movies</title>
</head>
<body>

	<jsp:include page="../User-logged-page.jsp"></jsp:include>

	<div class="container my-5">
		<div class="row">
			<c:forEach var="data" items="${movieLlist}">
				<div class="col-md-4">
					<div class="card">
						<img src="data:image/jpeg;base64,${data.fileName}" width="400px"
							height="300px" style="border-radius: 10px;" class="card-img-top"
							alt="...">

						<div class="card-body">
							<h5 class="card-title">Movie Title: ${data.movieTitle}</h5>
							<p class="card-text">Director:${data.movieDirector}</p>
							<p class="card-text">Year: ${data.movieYear}</p>
							<p class="card-text">Genre: ${data.movieGenre}</p>
							<p class="card-text">Runtime: ${data.movieRunTime}</p>
							<a href="/BookingShows?movie=${data.movieId}"
								class="btn btn-primary">Book</a>
						</div>
					</div>

				</div>
			</c:forEach>
		</div>
	</div>




</body>
</html>