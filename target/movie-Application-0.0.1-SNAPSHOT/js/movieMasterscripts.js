displayMovies();
var frm = document.getElementById('movieForm');



$(document).ready(function() {
 

	$("#reset").click(function(event) {
		event.preventDefault();
		alert(" like to reset ")
		$("#movieForm input[type='text'], #movieForm select").val("");
		$("#movieForm input[type='text'], #movieForm textarea").val("");
		$("#movieForm input[type='number'], #movieForm select").val("");
	});



	$("#movieForm").submit(function(event) {

		event.preventDefault();

		var formData = new FormData($('#movieForm')[0]);
		
		
	

		/*	$.post("/AddMoviestoDB", formData, function(data, status, xhr) {
				if (data == "success") {
					alert("movie saved Successfully");
					console.log("File uploaded successfully");
					frm.reset();
					displayMovies();
				} else {
					alert("not saved");
				}
			}, "text")
				.fail(function(xhr, status, error) {
					console.log("Error uploading file: " + error);
				});*/
		$.post({
			url: "/AddMoviestoDB",
			method: "post",
			data: formData,
			processData: false,
			contentType: false,
			success: function(data) {
				displayMovies();
				if (data == "success") {
					alert("movie saved Successfully");

					console.log("File uploaded successfully");
                 $('#createShowForm')[0].reset();
					$('#movieForm')[0].reset();
					//displayMovies();
				} else {
					alert("not saved");
				}

			},
			error: function(xhr, status, error) {
				console.log("Error uploading file: " + error);
			},
		});
	});
});

function displayMovies() {
	
	$(document).ajaxSend(function(e, xhr, opt){
    $("#loading").append("<p>Requesting " + opt.url + "</p>");
  });
	$('#moviesTable').DataTable({
		ajax: {
			url: '/show-Movies-Data-Table',
			type: 'GET',
			dataType: 'json',
			dataSrc: '',
		},
		columns: [
			{
				data: 'movieId'
			},
			{
				data: 'fileName',
				render: function(data, type, row) {
					return '<img src="data:image/jpeg;base64,' + data + '" width="200" height="200" style="border-radius:10px;"/>';
				}
			},
			{ data: 'movieTitle' },
			{ data: 'movieDirector' },
			{ data: 'movieYear' },
			{ data: 'language' },
			{ data: 'movieGenre' },
			{ data: 'movieRunTime' },
			{ data: 'movieCast' },

			{
				data: 'movieId',
				render: function(data, type, row) {
					return '<a class="btn btn-warning" onclick="EditMovie(' + data + ')">EDIT</a>';
				}
			},
			{
				data: 'movieId',
				render: function(data, type, row) {
					return '<a class="btn btn-danger" onclick="deleteMovie(' + data + ')">DELETE</a>';
				}
			},
		],
	});
}

/*DETETE MOVIES */



function deleteMovie(id) {
	var movieId = $("#deleteid").val();

	$.ajax({
		url: "/deleteMovie/" + id,
		type: "DELETE",
		data: movieId,
		processData: false,
		contentType: false,
		success: function(data) {
			displayMovies();
			if (data == "Movie deleted successfully") {
				alert("movie deleted Successfully");

				console.log("File uploaded successfully");
			} else {
				alert("not saved");
			}
		},
		error: function(xhr, status, error) {
			console.log("Error uploading file: " + error);
		},
	});
}



/* Edit product*/

function EditMovie(ID) {
	alert("Now you can Edit The Data in Form")
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
		url: "/editMovie/" + ID,
		type: "GET",
		dataType: "json",
		success: function(Movie) {
			$("#movieId").val(Movie.movieId);
			$("#movieForm #movieTitle").val(Movie.movieTitle);
			$("#movieForm #movieDirector").val(Movie.movieDirector);
			$("#movieForm #language").val(Movie.language);
			$("#movieForm #movieYear").val(Movie.movieYear);
			$("#movieForm #movieGenre").val(Movie.movieGenre);
			$("#movieForm #movieRunTime").val(Movie.movieRunTime);
			$("#movieForm #movieCast").val(Movie.movieCast);
			$(".movieposter-preview").attr(
				"src",
				"data:image/jpeg;base64," + Movie.fileName
			);
		},
	});
}


/*	GET ID FUNCTION*/
function getid() {

	var ID = parseInt($("#movieId").val());
	console.log(typeof ID)
	alert(ID);

	updateMovie(ID);
}
/*UPDATE*/
function updateMovie(ID) {


	console.log(typeof ID);
	var formData = new FormData($('#movieForm')[0]);

	$.ajax({
		url: "/UpdateMovie/" + ID,
		method: "PUT",
		data: formData,
		processData: false,
		contentType: false,
		success: function(data) {
			displayMovies();

			if (data == "update Done") {
				alert("movie Updated Successfully");
				console.log("File uploaded successfully");
			} else {
				alert("not Updated");
			}
		},
		error: function(xhr, status, error) {
			console.log("Error uploading file: " + error);
		},
	});





}









