<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Shows</title>
</head>
<body>



	<jsp:include page="../User-logged-page.jsp"></jsp:include>

	<div class="container my-5">

		<input value="${showsBySelectedMovie[0].movieName.movieTitle}"
			class="movieName" id="movieName"> <label>SELECT-DATE</label>
		<select id="date-select">
		</select>


		<table class="table table-striped ShowTable" id="showTimesTable">
			<caption>table</caption>
			<thead>
				<tr>
					<th>Cinema</th>
					<th>Show Times</th>
				</tr>
			</thead>
			<tbody>
				<!-- generated using JavaScript -->
			</tbody>
		</table>



	</div>

	<script type="text/javascript">
		//Get the current date
		var currentDate = new Date();

		// Create an array to hold the dates
		var dates = [];

		// Loop through the next 6 days and add them to the array
		for (var i = 0; i < 7; i++) {
			var date = new Date(currentDate.getTime() + i * 24 * 60 * 60 * 1000);
			dates.push(date);
		}

		// Get the select element from the DOM
		var select = document.getElementById("date-select");

		// Loop through the dates array and add each date as an option to the select element
		for (var i = 0; i < dates.length; i++) {
			var option = document.createElement("option");
			option.value = dates[i].toISOString().slice(8, 10) + "-"
					+ dates[i].toISOString().slice(5, 7) + "-"
					+ dates[i].toISOString().slice(0, 4);
			option.text = dates[i].toISOString().slice(8, 10) + "-"
					+ dates[i].toISOString().slice(5, 7) + "-"
					+ dates[i].toISOString().slice(0, 4);
			select.add(option);
		}

		getCinemaBydateandMovieName();
		function getCinemaBydateandMovieName() {
			var movieTitle = $('#movieName').val();
			var date = $("#date-select").val();
			var name = $("#movieName").val();

			$
					.ajax({
						url : "/movie?showDate=" + date + "&movieName=" + name,
						type : "get",
						dataType : "JSON",
						success : function(data) {
							var showDetails = data;

							// Clear existing table rows
							$("#showTimesTable tbody").empty();

							// Iterate over the showDetails object
							for ( var cinema in showDetails) {
								if (showDetails.hasOwnProperty(cinema)) {
									var showTimes = showDetails[cinema];

									var tableRow = "<tr><td>" + cinema
											+ "</td><td>";

									// Iterate over the show times for the current cinema
									for (var i = 0; i < showTimes.length; i += 3) {
										var showTime = showTimes[i];
										var screen = showTimes[i + 1];
										var screenId = showTimes[i + 2];
										var url = "/shows?cinema="
												+ encodeURIComponent(cinema)
												+ "&screenId=" + screenId
												+ "&time="
												+ encodeURIComponent(showTime)
												+ "&movie=" + movieTitle;

										tableRow += "<a class='btn btn' href=\"" + url + "\">"
												+ showTime
												+ "-"
												+ screen
												+ " Code-"
												+ screenId
												+ "</a>"
												+ " | ";
									}

									tableRow += "</td></tr>";

									// Append the table row to the table body
									$("#showTimesTable tbody").append(tableRow);
								}
							}
						},
						error : function(xhr, status, error) {
							// Handle the error response
							console.log("Error saving screens: " + error);
						},
					});

		}

		$(document)
				.ready(
						function() {
							var movieTitle = $('#movieName').val();

							$("#date-select")
									.on(
											"change",
											function() {
												var date = $("#date-select")
														.val();
												alert(date);
												var name = $("#movieName")
														.val();

												$
														.ajax({
															url : "/movie?showDate="
																	+ date
																	+ "&movieName="
																	+ name,
															type : "get",
															dataType : "JSON",
															success : function(	data) {
																console.log(data)
																var showDetails = data;

																// Clear existing table rows
																$(
																		"#showTimesTable tbody")
																		.empty();

																// Iterate over the showDetails object
																for ( var cinema in showDetails) {
																	if (showDetails
																			.hasOwnProperty(cinema)) {
																		var showTimes = showDetails[cinema];

																		var tableRow = "<tr><td>"
																				+ cinema
																				+ "</td><td>";

																		// Iterate over the show times for the current cinema
																		for (var i = 0; i < showTimes.length; i += 3) {
																			var showTime = showTimes[i];
																			var screen = showTimes[i + 1];
																			var screenId = showTimes[i + 2];
																			var url = "/shows?cinema="
																					+ encodeURIComponent(cinema)
																					+ "&screenId="
																					+ screenId
																					+ "&time="
																					+ encodeURIComponent(showTime)
																					+ "&movie="
																					+ movieTitle;

																			tableRow += "<a class='btn btn' href=\"" + url + "\">"
																					+ showTime
																					+ "-"
																					+ screen
																					+ " Code-"
																					+ screenId
																					+ "</a>"
																					+ " | ";
																		}

																		tableRow += "</td></tr>";

																		// Append the table row to the table body
																		$(
																				"#showTimesTable tbody")
																				.append(
																						tableRow);
																	}
																}
															},
															error : function(
																	xhr,
																	status,
																	error) {
																// Handle the error response
																console
																		.log("Error saving screens: "
																				+ error);
															},
														});
											});
						});
	</script>

</body>
</html>