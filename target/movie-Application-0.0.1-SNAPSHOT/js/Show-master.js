getAllShows();
getdata();
function getdata() {

	$.ajax({
		url: '/getDataToCreateShow',
		type: 'get',
		data: "",
		success: function(response) {
			// handle success response
			var cinemaList = response.cinemaList;
			var movieList = response.movieList;


			// update cinema options
			var cinemaOptions = '<option value="">Select a cinema</option>';
			for (var i = 0; i < cinemaList.length; i++) {
				cinemaOptions += '<option value="' + cinemaList[i].cinemaName + '">' + cinemaList[i].cinemaName + '</option>';
			}
			$('#cinemaName').html(cinemaOptions);

			// update movie options
			var movieOptions = '<option value="">Select a movie</option>';
			for (var i = 0; i < movieList.length; i++) {
				movieOptions += '<option value="' + movieList[i].movieTitle + '">' + movieList[i].movieTitle + '</option>';
			}
			$('#movieTitle').html(movieOptions);


			/* 
			 // update screen options
			 var screenOptions = '<option value="">Select a screen</option>';
			 for (var i = 0; i < screenList.length; i++) {
			   screenOptions += '<option value="' + screenList[i].getScreenName() + '">' + screenList[i].getScreenName() + '</option>';
			 }
			 $('#screen').html(screenOptions);*/
		},
		error: function(xhr, status, error) {
			alert("error")
		}
	});

}

$(function() {
	$("#cinemaName").on("change", function() {

		var name = $("#cinemaName").val();


		$.ajax({

			url: "/screendata/" + name,
			type: "GET",
			dataType: "JSON",
			data: {
				movieName: name
			},
			success: function(screensList) {
				// Get the container element where the checkboxes will be added
				var container = $("#screens-container");

				// Clear any existing checkboxes
				container.empty();

				// Loop through the screensList and create a checkbox for each screen
				$.each(screensList, function(index, screen) {
					// Create the checkbox element
					var checkbox = $("<input>").attr({
						type: "checkbox",
						name: "screens",
						value: screen.screenId
					});

					// Create the label element for the checkbox
					var label = $("<label>").text(screen.screenName);

					// Add the checkbox and label to the container
					container.append(checkbox).append(label);
				});
			}



		});



	});
});

var time;
document.querySelector('input[type="time"]').onchange = function() {
	var hours = this.value.slice(0, 2);
	var minutes = this.value.slice(3, 5);
	var ampm = hours >= 12 ? 'PM' : 'AM';
	hours = hours % 12;
	hours = hours ? hours : 12; // the hour '0' should be '12'
	minutes = minutes < 10 ? minutes : minutes;
	time = hours + ':' + minutes + ' ' + ampm;
	$('#showTime').val(time);
	console.log(time);
}

$(document).ready(function() {
	$('#createShowForm').submit(function(event) {
		event.preventDefault(); // Prevent the form from submitting normally
		var formData = $(this).serialize();
		$.ajax({
			type: 'POST',
			url: '/SaveCreatedShowtoDB', // Replace with the URL of your controller method
			data: formData,
			success: function(response) {
				alert("show Created Successfully")
				$('#createShowForm')[0].reset();
				loadPageData(1);
				// Handle the success response
				console.log(response);
			},
			error: function(xhr, status, error) {
				// Handle the error response
				console.log(error);
			}
		});
	});
});




function getAllShows() {

	$('#tbl-Shows').dataTable().fnDestroy();
	$.ajax({

		url: "/getAllShows",
		type: "GET",
		dataType: "JSON",
		success: function(data) {

			$('#tbl-Shows')
				.dataTable(
					{
						"aaData": data,
						"scrollX": true,
						"aoColumns": [

							{
								"sTitle": "S.NO",
								"mData": "showId"
							},
							{
								"sTitle": "CINEMA",
								"mData": "cinemaName.cinemaName"
							},
							{
								"sTitle": "MOVIE",
								"mData": "movieName.movieTitle"
							},

							{
								"sTitle": "DATE",
								"mData": "showDate"
							},
							{
								"sTitle": "TIME",
								"mData": "showTime"
							},
							{
								"sTitle": "Screen ",
								"mData": function(source, type, val) {
									var screenNames = "";
									for (var i = 0; i < source.screensList.length; i++) {
										screenNames += source.screensList[i].screenName + "<br>";
									}
									return screenNames;
								}

							},

							{
								"sTitle": "Delete",
								"mData": "showId",
								"render": function(
									mData, type, row, meta) {
									return '<button class="btn btn-danger" onclick="deleteShow('
										+ mData
										+ ')">Delete</button>';
								}
							},]
					});

		},

		error: function(e) {
			alert("Error!")
			alert("problem")
			console.log("ERROR: ", e);
		}

	});

}

/*DELETE*/

function deleteShow(id) {

	$.ajax({
		type: 'POST',
		url: '/deleteShows/' + id,
		dataType: 'JSON',
		data: {
			"Id": id
		},
		success: function(data) {
			loadPageData(1);
			alert("Show Deleted Successfuly")
		},
		error: function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	});
}
var pSize = parseInt($('#pageSize').val());
loadPageData(1);
var currentpage;
var sortfield;
var sortDirection;
var showid = 'showId';
var cinema = 'cinema_id';
var data = 'date';
var time = 'Time';
var movie = 'movie_id';
function loadPageData(page) {
	$.ajax({

		url: '/getAllShows',
		type: 'GET',
		dataType: 'JSON',
		success: function(response) {
			var tableData = '';
			var tableBody = '';
			var showList = response.userDetails;
			currentpage = response.currentPage;
			sortfield = response.sortField;
			sortDirection = response.reverseSortDir
			console.log(response.currentPage);

			//onclick='handleClick("+response.currentPage,response.reverseSortDir,"showId"+")'
			//tableData += "<thead><tr><th data-order='desc' class='th-sm'><a href='javascript:sort(" + showid + ")' id ='showId' data-sort='showId' >S.NO &#9650</a></th><th><a href='javascript:sort(" + cinema + ")' id ='cinema_id' data-sort='movieName''>CINEMA &#9650</a></th><th><a href='javascript:sort(" + movie + ")' data-sort='movieName' id='movie_id'>MOVIE &#9650</a></th><th><a href='javascript:sort(" + data + ")' id='date' data-sort='showDate' >DATE &#9650</a></th><th><a href='javascript:sort(" + time + ")'id='Time' data-sort='showTime'>TIME &#9650</a></th><th>DELETE</th></tr></thead>";
tableData += '<table>' +
					'<thead>' +
					'<tr>' +
					'<th data-order="desc" class="th-sm">' +
					'<a href="javascript:sort( '+showid +')" id="showId" data-sort="showId" onclick="toggleSortOrder(showId)">' +
					'S.NO &#9650' +
					'</a>' +
					'</th>' +
					'<th>' +
					'<a href="javascript:sort('+ cinema +')" id="cinema_id" data-sort="movieName" onclick="toggleSortOrder(cinema_id)">' +
					'CINEMA &#9650' +
					'</a>' +
					'</th>' +
					'<th>' +
					'<a href="javascript:sort('+ movie +')" id="movie_id" data-sort="movieName" onclick="toggleSortOrder(movie_id)">' +
					'MOVIE &#9650' +
					'</a>' +
					'</th>' +
					'<th>' +
					'<a href="javascript:sort(' + data +')" id="date" data-sort="showDate" onclick="toggleSortOrder(date)">' +
					'DATE &#9650' +
					'</a>' +
					'</th>' +
					'<th>' +
					'<a href="javascript:sort(' + data +')" id="Time" data-sort="showTime" onclick="toggleSortOrder(Time)">' +
					'TIME &#9650' +
					'</a>' +
					'</th>' +
					'<th>DELETE</th>' +
					'</tr>' +
					'</thead>';
			for (var i = 0; i < showList.length; i++) {
				tableData += "<tr><td>"
					+ showList[i].showId
					+ "</td><td>"
					+ showList[i].cinemaName.cinemaName
					+ "</td><td>"
					+ showList[i].movieName.movieTitle
					+ "</td><td>"
					+ showList[i].showDate
					+ "</td><td>"
					+ showList[i].showTime
					+ "</td><td><button class='btn btn-danger' onclick='deleteShow("
					+ showList[i].showId
					+ ")'>Delete</button>" + "</td></tr>";

			}


			tableData += "</tbody>"
			$("#tabledata").html(tableData);

			// Handle the AJAX response and generate the pagination HTML

			const ul = $("<ul>").addClass("pagination");
			console.log(response.currentPage);
			const liPrev = $("<li>").addClass("page-item");
			if (response.currentPage !== 1) {
				const aPrev = $("<a>")
					.addClass("page-link")
					.text("Previous")
					.on(
						"click",
						function(event) {
							event.preventDefault();
							loadDataByPageNum(
								response.currentPage - 1,
								pSize,
								response.sortField,
								response.sortDir);
						});
				liPrev.append(aPrev);
			}
			ul.append(liPrev);

			for (let i = 1; i <= response.totalPages; i++) {
				const li = $("<li>");
				const a = $("<a>").addClass("page-link")
					.text(i).on(
						"click",
						function(event) {
							event.preventDefault();
							loadDataByPageNum(i,
								pSize,
								response.sortField,
								response.sortDir);
						});
				if (response.currentPage === i) {
					li.addClass("page-item");
				}
				li.append(a);
				ul.append(li);
			}

			if (response.currentPage < response.totalPages) {
				const liNext = $("<li>").addClass("page-item");
				const aNext = $("<a>")
					.addClass("page-link")
					.text("Next")
					.on(
						"click",
						function() {
							loadDataByPageNum(
								response.currentPage + 1,
								pSize,
								response.sortField,
								response.sortDir);
						});
				liNext.append(aNext);
				ul.append(liNext);
			}

			// Update the DOM with the generated pagination HTML
			$("#pagination-container").html(ul);

		}

		,
		error: function(xhr, status, error) {
			// Handle the error response if needed
		}

	});
}

function loadDataByPageNum(i, pageSize, sortField, sortDir) {
	$("#tabledata").empty();

	$
		.ajax({
			url: "/page/" + i + "?sortField=" + sortField + "&sortDir=" + sortDir + "&pageSize=" + pageSize,
			type: 'GET',
			dataType: 'JSON',
			success: function(response) {
				sortDirection = response.reverseSortDir
				console.log(response)
				var tableData = '';
				var showList = response.userDetails;
				console.log(response.currentPage);
				/*			tableData += "<thead><tr><th class='th-sm'  data-order='desc'><a href='javascript:sort(" + showid + ")' onclick='toggleSortOrder('showId')id ='showId' data-sort='showId' >S.NO &#9650</a></th><th><a href='javascript:sort(" + cinema + ")' id ='cinema_id' data-sort='movieName''>CINEMA &#9650</a></th><th><a href='javascript:sort(" + movie + ")' data-sort='movieName' id='movie_id'>MOVIE &#9650</a></th><th><a href='javascript:sort(" + data + ")
				' id='date' data-sort='showDate' >DATE &#9650</a></th><th><a href='
				javascript:sort(" + time + ")'id='Time' data-sort='showTime'>TIME &#9650</a></th><th>DELETE</th></tr></thead>";
				*/
				tableData += '<table>' +
					'<thead>' +
					'<tr>' +
					'<th data-order="desc" class="th-sm">' +
					'<a href="javascript:sort( '+showid +')" id="showId" data-sort="showId" onclick="toggleSortOrder(showId)">' +
					'S.NO &#9650' +
					'</a>' +
					'</th>' +
					'<th>' +
					'<a href="javascript:sort('+ cinema +')" id="cinema_id" data-sort="movieName" onclick="toggleSortOrder(cinema_id)">' +
					'CINEMA &#9650' +
					'</a>' +
					'</th>' +
					'<th>' +
					'<a href="javascript:sort('+ movie +')" id="movie_id" data-sort="movieName" onclick="toggleSortOrder(movie_id)">' +
					'MOVIE &#9650' +
					'</a>' +
					'</th>' +
					'<th>' +
					'<a href="javascript:sort(' + data +')" id="date" data-sort="showDate" onclick="toggleSortOrder(date)">' +
					'DATE &#9650' +
					'</a>' +
					'</th>' +
					'<th>' +
					'<a href="javascript:sort(' + data +')" id="Time" data-sort="showTime" onclick="toggleSortOrder(Time)">' +
					'TIME &#9650' +
					'</a>' +
					'</th>' +
					'<th>DELETE</th>' +
					'</tr>' +
					'</thead>';

				for (var i = 0; i < showList.length; i++) {

					tableData += "<tr><td>"
						+ showList[i].showId
						+ "</td><td>"
						+ showList[i].cinemaName.cinemaName
						+ "</td><td>"
						+ showList[i].movieName.movieTitle
						+ "</td><td>"
						+ showList[i].showDate
						+ "</td><td>"
						+ showList[i].showTime
						+ "</td><td><button class='btn btn-danger' onclick='deleteShow("
						+ showList[i].showId
						+ ")'>Delete</button>" + "</td></tr>";

				}
				$("#tabledata").html(tableData);
				const ul = $("<ul>").addClass("pagination");
				console.log(response.currentPage);
				const liPrev = $("<li>").addClass("page-item");
				if (response.currentPage !== 1) {
					const aPrev = $("<a>")
						.addClass("page-link")
						.text("Previous")
						.on(
							"click",
							function(event) {
								event.preventDefault();
								loadDataByPageNum(
									response.currentPage - 1,
									pSize,
									response.sortField,
									response.sortDir);
							});
					liPrev.append(aPrev);
				}
				ul.append(liPrev);

				for (let i = 1; i <= response.totalPages; i++) {
					const li = $("<li>");
					const a = $("<a>").addClass("page-link")
						.text(i).on(
							"click",
							function(event) {
								event.preventDefault();
								loadDataByPageNum(i,
									pSize,
									response.sortField,
									response.sortDir);
							});
					if (response.currentPage === i) {
						li.addClass("page-item");
					}
					li.append(a);
					ul.append(li);
				}

				if (response.currentPage < response.totalPages) {
					const liNext = $("<li>").addClass("page-item");
					const aNext = $("<a>")
						.addClass("page-link")
						.text("Next")
						.on(
							"click",
							function() {
								loadDataByPageNum(
									response.currentPage + 1,
									pSize,
									response.sortField,
									response.sortDir);
							});
					liNext.append(aNext);
					ul.append(liNext);
				}

				// Update the DOM with the generated pagination HTML
				$("#pagination-container").html(ul);

			},
			error: function(xhr, error, data) {
			}
		}

		)
};


$(document).ready(() => {
	$('#pageSize').on('change', function() {

		var pageSize = parseInt($('#pageSize').val());
		pSize = pageSize;
		loadDataByPageNum(currentpage, pSize, sortfield, sortDirection)
	});



});
function sort(sortName) {
	console.log(sortName)

	var pageSize = parseInt($('#pageSize').val());
	var sortBy = sortName.getAttribute('data-sort')
	/*	alert(sortBy);*/
	loadDataByPageNum(currentpage, pageSize, sortBy, sortDirection);




}





function toggleSortOrder(columnId) {
console.log()
	const columnAnchor = document.getElementById(columnId);

	if (columnAnchor) {
		const currentOrder = columnAnchor.getAttribute('data-order');
		const newOrder = currentOrder === 'desc' ? 'asc' : 'desc';

		columnAnchor.setAttribute('data-order', newOrder);

		// Update the sorting symbol in the anchor text
		const arrowSymbol = newOrder === 'asc' ? '▲' : '▼';
		const currentText = columnAnchor.innerHTML;
		const newText = currentText.replace(/▲|▼/, arrowSymbol);
		columnAnchor.innerHTML = newText;
	}
}