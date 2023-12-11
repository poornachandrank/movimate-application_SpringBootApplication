<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<title>home</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
	crossorigin="anonymous"></script>

<link href="//cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css"
	rel="stylesheet">
<script src="../../../js/jquery.js"></script>
<script src="//cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>

</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="/Dashboard"> <img
				src="../../img/logo4.png" width="45" height="45"
				class="d-inline-block align-top" alt=""  /> Movie
				Meta
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item btn"><a class="nav-link active"
						aria-current="page" href="/movieMasterPage">MOVIE MASTER</a></li>
					<li class="nav-item btn"><a class="nav-link"
						href="/screenMasterPage">SCREEN MASTER</a></li>
					<li class="nav-item btn"><a class="nav-link"
						href="/cinemaMasterPage">CINEMA MASTER</a></li>
					<li class="nav-item btn"><a class="nav-link"
						href="/createShowMasterPage">SHOW MASTER</a></li>
					<li class="nav-item btn"><a class="nav-link" href="/bookings">Bookings
							Details </a></li>
								<li class="nav-item btn"><a class="nav-link" href="/allUsers">Users </a></li>
							
					<li class="nav-item btn"><a class="btn btn-danger"
						href="/logout">logout</a></li>
				</ul>
			</div>
		</div>
	</nav>
</body>

</html>