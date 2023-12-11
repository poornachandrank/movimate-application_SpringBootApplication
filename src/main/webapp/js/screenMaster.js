

/*GET ALL CINEMA DETAILS*/
getcinemaData()

function getcinemaData() {

	$.ajax({
		url: '/AllCinemaData',
		type: 'get',
		success: function(cinema) {

			// handle success response
			var cinemaList = cinema;
			// update cinema options
			var cinemaOptions = '<option value="">Select a cinema</option>';
			for (var i = 0; i < cinemaList.length; i++) {
				cinemaOptions += '<option value="' + cinemaList[i].cinemaName + '">' + cinemaList[i].cinemaName + '</option>';
			}
			$('#cinemaName').html(cinemaOptions);


		},
		error: function(xhr, status, error) {
			alert("error")
		}
	});

}
getScreenData();

function getScreenData() {

	$('#table-screen').dataTable().fnDestroy();
	$.ajax({

		url: "/allScreenData",
		method: "GET",
		dataType: "JSON",

		success: function(data) {
			$('#table-screen')
				.dataTable(
					{
						"aaData": data,
						"scrollX": true,
						"aoColumns": [

							{
								"sTitle": "S.NO",
								"mData": "screenId"
							},
							{
								"sTitle": "Screen-name",
								"mData": "screenName"
							},
							{
								"sTitle": "seats",
								"mData": function(row, type, val, meta) {
									if (row.totalSeats && row.totalSeats.seats) {
										return row.totalSeats.seats;
									}
									return "";
								}
							},


							{
								"sTitle": "Edit",
								"mData": "screenId",
								"render": function(
									mData,
									type, row,
									meta) {
									return '<button class="btn btn-success" id="edit" name="edit" onclick="editScreen('
										+ mData
										+ ')">Edit</button>';
								}
							},

							{
								"sTitle": "Delete",
								"mData": "screenId",
								"render": function(
									mData,
									type, row, meta) {
									return '<button class="btn btn-danger" onclick="Screen_delete('
										+ mData
										+ ')">Delete</button>';
								}
							},]
					});

		},

		error: function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}

	});

}

/*	Edit Function*/

function editScreen(ID) {
	var id = ID;
	alert("click")
	var update = document.getElementById("update")
	var save = document.getElementById("save")
	if (update.style.display === "none" && save.style.display === "block") {
		update.style.display = "block";
		save.style.display = "none";
	} else {
		update.style.display = "none";
		save.style.display = "block";
	}

	$.ajax({
		url: "/editScreen/" + id,
		type: "GET",
		dataType: "json",
		success: function(screen) {
			alert("now you can change the data")
			$("#screenid").val(screen.screenId);
			$("#screenform #screenName").val(screen.screenName);


		},

		error: function(xhr, status, error) {
			console.log("Error uploading file: " + error);

		}
	});
}


$(document).ready(function() {



	/*	Reset function*/
	$("#reset").click(function(event) {
		event.preventDefault();
		
		alert(" like to reset ")
		var container = $("#screens-container");
		container.empty();
		  $('#cinemaName  option').prop('selected', function() {
        return this.defaultSelected;
        	
    });
});
});


/*	GET ID FUNCTION*/
function getid() {

	var screenId = parseInt($("#screenid").val());
	updateScreen(screenId);
}
/*UPDATE SCREEN*/
/*UPDATE*/


function updateScreen(screenId) {
	alert("update")
	var formData = new FormData($('#screenform')[0]);

	$.ajax({
		url: "/updateScreen/" + screenId,
		method: "PUT",
		data: formData,
		processData: false,
		contentType: false,
		success: function(data) {

			if (data == "Update success") {
				alert("Screen Updated Successfully");
				console.log("Update successfully");
				getall();
			} else {
				alert("not Updated");
			}
		},
		error: function(xhr, status, error) {
			console.log("Error uploading file: " + error);
		},
	});


}

/*DELETE SCREEN*/
function Screen_delete(id) {
	$.ajax({
		type: 'DELETE',
		url: '/deleteScreen/' + id,
		dataType: 'text',
		data: {
			"screenId": id
		},
		success: function(data) {
			getScreenData();
			if (data == 'success')
				alert("Screen Deleted")
		},
		error: function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	});
}

$(document).ready(function() {
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
					var checkbox = $("<br><input>").attr({
						type: "checkbox",
						name: "screens",
						value: screen.screenId
					});

					// Create the label element for the checkbox
					var label = $("<label>").text(screen.screenName);

					// Create the input field for the seat count
					var input = $("<br><input>").attr({
						type: "number",
						id: "seatCount",
						name: "seatCounts"/*[" + screen.screenId + "]"*/,
						placeholder: "Enter seat count"
					});

					// Add the checkbox, label, and input field to the container
					container.append(checkbox).append(label).append(input);
				});
			}



		});



	});
});

$(document).ready(function() {
	$("#screen-form").submit(function(event) {
		// Prevent the form from submitting normally
		event.preventDefault();

		// Serialize the form data
		var formData = $(this).serializeArray();

		// Send an AJAX request to the server
		$.ajax({
			url: "/save-screens",
			type: "POST",
			data: formData,
			success: function(response) {

				if ("success" == response) {

					alert("Screen Seats are Added")
					console.log("Screens saved successfully");

				}
				getScreenData();
			},
			error: function(xhr, status, error) {
				// Handle the error response
				console.log("Error saving screens: " + error);
			}
		});
	});
});


