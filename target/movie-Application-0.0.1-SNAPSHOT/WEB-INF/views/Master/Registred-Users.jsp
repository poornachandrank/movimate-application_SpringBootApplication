<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>registered Users</title>
</head>
<body>
	<jsp:include page="../Header/header.jsp"></jsp:include>


	<div class="panel-body">
		<table id="table-screen" class="table table-primary">
			<caption>table</caption>
			<thead>
				<tr>
					<th>S.NO</th>
					<th>Name</th>
					<th>USER-NAME</th>
					<th>AGE</th>
					<th>CONTACT</th>
					<th>E-MAIL</th>
				</tr>

			</thead>
			<tbody>
   <c:forEach items="${UserData}" var="Data">

				<tr>
					<td>${Data.userId}</td>
					<td>${Data.fullName}</td>
					<td>${Data.userName}</td>
					<td>${Data.age}</td>
					<td>${Data.contactNumber}</td>
					<td>${Data.email}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
		<jsp:include page="../Footer/Footer.jsp"></jsp:include>
</body>
</html>