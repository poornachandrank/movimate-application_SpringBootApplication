<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Theater</title>
</head>
<body>
	<jsp:include page="../User-logged-page.jsp"></jsp:include>
	<div class="container mt-4">
		<table class="table table-primary">
		<caption>table</caption>
			<thead class="thead-dark">
				<tr>
					<th scope="col">Cinemas</th>
					<th scope="col">Location</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${allCinemas}" var="data">
					<tr>
						<td><a href="/cinemas/${data.cinemaId}">${data.cinemaName}</a></td>
						<td><a href="#">${data.cinemaAddress}</a></td>
					</tr>

				</c:forEach>
			</tbody>


		</table>
	</div>


</body>
</html>