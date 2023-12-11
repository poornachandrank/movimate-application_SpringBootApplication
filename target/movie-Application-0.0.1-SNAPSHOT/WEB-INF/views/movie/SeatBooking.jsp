<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Movie Meta</title>
</head>
<body>

	<h1>Movie Seat Booking</h1>
	<div class="container">
		<div class="screen">Screen</div>
		<div class="seats">
			<div class="seat"></div>
			<!-- Repeat the seat div for each seat -->
		</div>
		<p class="text">
			You have selected: <span id="selectedSeat"></span>
		</p>
		<button class="btn" id="bookBtn">Book Seat</button>
	</div>

	<script src="script.js"></script>
</body>
</html>


