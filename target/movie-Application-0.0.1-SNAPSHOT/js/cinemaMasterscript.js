
//ajax function for making AJAX calls
function makeAjaxCall(url, method, data, datatype, ProcessData, type, successCallback, errorCallback) {
	$.ajax({
		url: url,
		type: method,
		data: data,
		dataType: datatype,
		processData: ProcessData,
		contentType: type,
		success: successCallback,
		error: errorCallback
	});
}

/*CinemaMaster*/

$(document).ready(function() {

	getAllCinema();
	$("#save").click(function(event) {
		event.preventDefault();
		var formData = new FormData($('#cinemaForm')[0]);
		makeAjaxCall("/saveNewCinemaToDB", "POST", formData, "", false, false,
			function(data) {
				getAllCinema();
				if (data == "success") {
					alert("cinema saved Successfully");
					$('#cinemaForm')[0].reset();
				} else {
					alert("not saved");
				}
			},
			function(xhr, status, error) {
				console.log("Error saving cinema: " + error);
			}
		);
	});



});
// DELETE
function deleteCinema(id) {

	makeAjaxCall('/delete/' + id, 'POST', false, false, "json", {
		"Id": id
	}
		, function(data) {

			getAllCinema();
		},
		function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		})
}



// GET ALL DATA FROM DB
function getAllCinema() {

	$('#tbl-cinema').dataTable().fnDestroy();

	makeAjaxCall("/AllCinemaData", "GET", "", false, false, "json", function(data) {

		$('#tbl-cinema')
			.dataTable(
				{
					"aaData": data,
					"scrollX": true,
					"aoColumns": [

						{
							"sTitle": "S.NO",
							"mData": "cinemaId"
						},
						{
							"sTitle": "NAME",
							"mData": "cinemaName"
						},
						{
							"sTitle": "Location",
							"mData": "cinemaAddress"
						},
						{
							"sTitle": "Screen Count",
							"mData": "screen"
						},
						{
							"sTitle": "Screen name ",
							"mData": function(source, type, val) {
								var screenNames = "";
								for (var i = 0; i < source.screensList.length; i++) {
									screenNames += source.screensList[i].screenName + "<br>";
								}
								return screenNames;
							}

						},
						{
							"sTitle": "Edit",
							"mData": "cinemaId",
							"render": function(
								mData,
								type, row,
								meta) {
								return '<button class="btn btn-success" onclick="editCinema('
									+ mData
									+ ')">Edit</button>';
							}
						},

						{
							"sTitle": "Delete",
							"mData": "cinemaId",
							"render": function(
								mData,
								type, row,
								meta) {
								return '<button class="btn btn-danger" onclick="deleteCinema('
									+ mData
									+ ')">Delete</button>';
							}
						},]
				});

	},

		function(e) {
			alert("Error!")
			alert("problem")
			console.log("ERROR: ", e);
		}
	)
}


//EDIT FUNCTION
function editCinema(ID) {
	alert("edit")
	var update = document.getElementById("update")
	var save = document.getElementById("save")

	if (update.style.display === "none" && save.style.display === "block") {
		update.style.display = "block";
		save.style.display = "none";
	} else {
		update.style.display = "none";
		save.style.display = "block";
	}

	makeAjaxCall("/editCinema/" + ID, "GET", "", false, false, "JSON", function(product) {
		$("#cinemaID").val(product.cinemaId);
		$("#cinemaForm #cinemaName").val(product.cinemaName);
		$("#cinemaForm #cinemaAddress").val(product.cinemaAddress);
		$("#screen").val(product.screen)

	}, "")
}

//	GET-ID-FUNCTION
function getcinemaid() {

	var cinemaId = parseInt($("#cinemaID").val());
	console.log(cinemaId);
	alert(cinemaId);
	updateCineam(cinemaId);

}

// UPDATE-Cinema DATA
// UPDATE
function updateCineam(cinemaId) {
	var formData = new FormData($('#cinemaForm')[0]);
	makeAjaxCall("/updateCinema/" + cinemaId, "PUT", formData, "", false, false, function(data) {

		if (data == "Update success") {
			alert("Cinema Updated Successfully");
			console.log("Update successfully");
			getAllCinema();
		} else {
			alert("not Updated");
		}
	}, function(xhr, status, error) {
		console.log("Error uploading file: " + error);
	});

}












