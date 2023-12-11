<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
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


<script src="../../js/jquery.js"></script>
</head>

<body>
	<nav class="navbar navbar-expand-sm bg-light navbar-light">
		<div class="container-fluid">
			<a href="#" class="navbar-brand"> <!-- Logo Image --> <img
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
					<li class="nav-item active"><a class="nav-link" href="#">Home</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="movies">Movies</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Theaters</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Showtimes</a>
					</li>

				</ul>
				<ul class="nav">
					<li>
						<button type="button" class="btn btn-sm" data-bs-toggle="modal"
							data-bs-target="#signupModel">Sign-Up</button>
					</li>

					<li>
						<button type="button" class="btn btn-sm" data-bs-toggle="modal"
							data-bs-target="#loginModel">Login</button>
					</li>

				</ul>

			</div>
		</div>
	</nav>

	<!-- The Modal -->
	<div class="modal" id="loginModel">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Log-in Form</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form action="/login" method="post">
						<!-- Email input -->
						<div class="form-outline mb-4">
							<input type="email" id="email" name="username" id="username" class="form-control" />
							<label class="form-label" for="email">Email address</label>
						</div>

						<!-- Password input -->
						<div class="form-outline mb-4">
							<input type="password" id="password" name="password"
								class="form-control" /> <label class="form-label"
								for="password">Password</label>
						</div>

						<!-- 2 column grid layout for inline styling -->
						<div class="row mb-4">
							<div class="col d-flex justify-content-center">
								<!-- Checkbox -->
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="form2Example31" checked /> <label
										class="form-check-label" for="form2Example31">
										Remember me </label>
								</div>
							</div>

							<div class="col">
								<!-- Simple link -->
								<a href="#!">Forgot password?</a>
							</div>
						</div>

						<!-- Submit button -->
						<button type="submit" class="btn btn-primary btn-block mb-4">
							Sign in</button>

						<!-- Register buttons -->
						<div class="text-center">
							<p>
								Not a member? <a href="#!">Register</a>
							</p>
							<p>or sign up with:</p>
							<button type="button" class="btn btn-link btn-floating mx-1">
								<i class="fab fa-facebook-f"></i>
							</button>

							<button type="button" class="btn btn-link btn-floating mx-1">
								<i class="fab fa-google"></i>
							</button>

							<button type="button" class="btn btn-link btn-floating mx-1">
								<i class="fab fa-twitter"></i>
							</button>

							<button type="button" class="btn btn-link btn-floating mx-1">
								<i class="fab fa-github"></i>
							</button>
						</div>
					</form>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>










	<!-- The Modal -->
	<div class="modal" id="signupModel">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Sign-Up Form</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form id="signUpForm" class="signUpForm" method="POST">
						<!-- Email input -->
						<!-- Email input -->
						<div class="form-outline mb-4">
							<input type="text" id="fullName" name="fullName"
								class="form-control" placeholder="Enter fullName" /> <label
								class="form-label" for="fullName">Full-Name</label>
						</div>
						<div class="form-outline mb-4">
							<input type="number" id="age" name="age" class="form-control" />
							<label class="form-label" for="age">Age</label>
						</div>

						<!-- Password input -->
						<div class="form-outline mb-4">
							<input type="tel" id="contactNumber" name="contactNumber"
								class="form-control" /> <label class="form-label"
								for="contactNumber">Contact Number</label>
						</div>
						<div class="form-outline mb-4">
							<input type="text" id="userName" name="userName"
								class="form-control" /> <label class="form-label"
								for="userName">User-Name</label>
						</div>
						<div class="form-outline mb-4">
							<input type="email" id="email" name="email" class="form-control" />
							<label class="form-label" for="email">Email-ID</label>
						</div>

						<div class="form-outline mb-4">
							<input type="password" id="password" name="password"
								class="form-control" /> <label class="form-label"
								for="password">Password</label>
						</div>

						<!-- Submit button -->
						<button type="submit" class="btn btn-primary btn-block mb-4">
							Sign in</button>

						<button type="button" class="btn btn-link btn-floating mx-1">
							<i class="fab fa-facebook-f"></i>
						</button>

						<button type="button" class="btn btn-link btn-floating mx-1">
							<i class="fab fa-google"></i>
						</button>

						<button type="button" class="btn btn-link btn-floating mx-1">
							<i class="fab fa-twitter"></i>
						</button>

						<button type="button" class="btn btn-link btn-floating mx-1">
							<i class="fab fa-github"></i>
						</button>
					</form>
				</div>

			</div>

			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
			</div>
		</div>
	</div>




	<div id="carouselExampleControls" class="carousel slide"
		data-bs-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="../../img/Kaari-Tamil-Movie-Poster-696x392.jpg"
					class="d-block w-100" alt="..." height="350px" width="50px">
			</div>
			<div class="carousel-item">
				<img src="../../img/run-baby-run-2023_167342842540.jpg"
					class="d-block w-100" alt="..." height="350px" width="50px">
			</div>
			<div class="carousel-item">
				<img src="../../img/raavana-kottam_167333534310.jpg"
					class="d-block w-100" alt="..." height="350px" width="50px">
			</div>
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





	<script src="../../js/index.js"></script>

</body>
</html>
