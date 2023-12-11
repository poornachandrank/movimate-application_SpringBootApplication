<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>MOVIE_META</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
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
<script src="../../../js/jquery.js" type="text/javascript"></script>



</head>

<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container-fluid">
			<a href="/User_Home" class="navbar-brand"> <!-- Logo Image --> <img
				src="../../img/logo4.png" width="45" alt=""
				class="d-inline-block align-middle mr-2" /> <span
				class="text-uppercase">movie-meta</span>
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-between"
				id="collapsibleNavbar">
				<ul class="navbar-nav">
					<li class="nav-item active"><a class="nav-link"
						href="/User_Home">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="/movies">MOVIES</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="/cinemas">CINEMAS</a>
					</li>

					<security:authorize access="hasAnyAuthority('CUSTOMER') ">

						<li class="nav-item"><a class="nav-link" href="/mybookings">My-Bookings</a></li>
						<li class="nav-item"><a class="nav-link" href="/account">MY
								ACCOUNT</a></li>

					</security:authorize>

				</ul>
				<security:authorize access="hasAnyAuthority('CUSTOMER') ">
					<ul class="nav">
						<li><a class="btn btn-warning" href="/logout">Logout</a></li>

					</ul>


				</security:authorize>

			</div>
		</div>
	</nav>

	















	<div id="carouselExampleControls" class="carousel slide"
		data-bs-ride="carousel">
		<div class="carousel-inner">
			<c:forEach var="data" items="${movies}">

				<div class="carousel-item active">
					<img src="data:image/jpeg;base64,${data.fileName}"
						class="d-block w-100" alt="..." height="450px">
				</div>
			</c:forEach>


		</div>



		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleControls" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleControls" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>