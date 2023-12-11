<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Bookings</title>
</head>
<body>
	<jsp:include page="../User-logged-page.jsp"></jsp:include>

	<div class="container">
		<h1>Booked Show Details</h1>
		<table class="table table-primary">
			<caption>table</caption>
			<thead>
				<tr>
					<th>Movie Name</th>
					<th>CIMENA</th>
					<th>Date</th>
					<th>Time</th>
					<th>Amount</th>
					<th>Seat Count</th>
					<th>Seats</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="data" items="${userBookingList}">

					<tr>

						<td>${data.showId.movieName.movieTitle}</td>
						<td>${data.showId.cinemaName.cinemaName}</td>
						<td>${data.showId.showDate}</td>
						<td>${data.showId.showTime}</td>
						<td>${data.totalPrice}</td>
						<td>${data.seatCount}</td>
						<td>${data.seatName}</td>


					</tr>


				</c:forEach>



			</tbody>
		</table>
	</div>

</body>
</html>





