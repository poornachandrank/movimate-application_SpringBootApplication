	<jsp:include page="../User-logged-page.jsp"></jsp:include>


	
	<h1>${cinemaData.cinemaName}</h1>
	<input value="${cinemaData.cinemaName}" readonly="readonly" hidden=""
		id="cinemaName">

	<label>SELECT-DATE</label>
	<select id="date-select">
	</select>
<table class="table table-striped ShowTable" id="movieTable">
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












	<script type="text/javascript">
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
	
	 getMoviesBydateandCinemaName();
		function getMoviesBydateandCinemaName(){
			
			var cinemaName = $('#cinemaName').val();
			var cinema = document.getElementById("date-select");
			var Date = select.value;
		
			$.ajax({
				url: "/getAllMovies?showDate=" + Date + "&cinemaName=" + cinemaName,
				type: "GET",
				dataType: "JSON",
				success: function(data) {
					console.log(data);
				     var showDetails = data;
				  var cinema ="${cinemaData.cinemaName}";
				        // Clear existing table rows
				        $("#movieTable tbody").empty();

				        // Iterate over the showDetails object
				        for (var movie in showDetails) {
				          if (showDetails.hasOwnProperty(movie)) {
				            var showTimes = showDetails[movie];
				         
				            var tableRow = "<tr><td>" + movie + "</td><td>";

				            // Iterate over the show times for the current cinema
				            for (var i = 0; i < showTimes.length; i +=3) {
				              var showTime = showTimes[i];
				              var screen = showTimes[i + 1];
				              var screenId = showTimes[i + 2];
			              var url = "/shows?cinema=" + encodeURIComponent(cinema) +"&screenId="+screenId+"&time=" + encodeURIComponent(showTime)+"&movie="+movie;

				              tableRow += "<a class='btn btn' href=\"" + url + "\">" + showTime +"-"+ screen +" Code-"+screenId+ "</a>" + " | ";
				            }

				            tableRow += "</td></tr>";

				            // Append the table row to the table body
				            $("#movieTable tbody").append(tableRow);
				          }
				        }
				      },
				      error: function (xhr, status, error) {
				        // Handle the error response
				        console.log("Error saving screens: " + error);
				      },
				    });
		}
		
		
		
		 $("#date-select").on("change", function () {
			
			var cinemaName = $('#cinemaName').val();
			var cinema = document.getElementById("date-select");
			var Date = select.value;
		
			$.ajax({
				url: "/getAllMovies?showDate=" + Date + "&cinemaName=" + cinemaName,
				type: "GET",
				dataType: "JSON",
				success: function(data) {
					console.log(data);
				     var showDetails = data;
				     var cinema ='${cinemaData.cinemaName}';
				        // Clear existing table rows
				        $("#movieTable tbody").empty();

				        // Iterate over the showDetails object
				        for (var movie in showDetails) {
				          if (showDetails.hasOwnProperty(movie)) {
				            var showTimes = showDetails[movie];
				         
				            var tableRow = "<tr><td>" + movie + "</td><td>";

				            // Iterate over the show times for the current cinema
				            for (var i = 0; i < showTimes.length; i +=3) {
				              var showTime = showTimes[i];
				              var screen = showTimes[i + 1];
				              var screenId = showTimes[i + 2];
			              var url = "/shows?cinema=" + encodeURIComponent(cinema) +"&screenId="+screenId+"&time=" + encodeURIComponent(showTime)+"&movie="+movie;

				              tableRow += "<a class='btn btn' href=\"" + url + "\">" + showTime +"-"+ screen +" Code-"+screenId+ "</a>" + " | ";
				            }

				            tableRow += "</td></tr>";

				            // Append the table row to the table body
				            $("#movieTable tbody").append(tableRow);
				          }
				        }
				      },
				      error: function (xhr, status, error) {
				        // Handle the error response
				        console.log("Error saving screens: " + error);
				      },
				    });
		});
				
		
			</script>


		<jsp:include page="../Footer/Footer.jsp"></jsp:include>