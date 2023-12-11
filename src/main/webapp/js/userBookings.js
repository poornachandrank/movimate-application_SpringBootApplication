	getAllShows();

		function getAllShows() {
			$('#tbl-bookings').dataTable().fnDestroy();
			$.ajax({

				url : "/allbookings",
				method : "GET",
				dataType : "JSON",
				success : function(data) {
					
					// Assuming the response is an array of objects
					var bookings = data;

					// Create an array to hold the formatted data for DataTables
					var tableData = [];

					// Iterate over the bookings array and format the data
					$.each(bookings, function(index, booking) {
						var rowData = [ booking.bookingId,
							booking.showIds.movieName.movieTitle,
								booking.showIds.cinemaName.cinemaName,
								JSON.stringify(booking.userId.fullName),
								booking.seatCount,
								booking.totalPrice ];
						tableData.push(rowData);
					});

					// Initialize the DataTable plugin with the formatted data
					$('#tbl-bookings').DataTable({
						data : tableData,
						scrollX : true,
						columns : [ {
							title : 'Booking ID'
						}, {
							title : 'Movie Name'
						}, 
						 {
							title : 'Cinema Name'
						},
						{
							title : 'User name'
						},
						{
							title : 'Seat Count'
						},{
							title : 'Total Price'
						} ]
					});
				},

				error : function(e) {
					alert("Error!")
					alert("problem")
					console.log("ERROR IN GETTING RESPOSE: ", e);
				}

			});

		}