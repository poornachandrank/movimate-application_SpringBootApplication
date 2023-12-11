<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Book-Seat</title>
<style>
  /* Seat styles */
  
  .showcase {
  background-color: rgba(0, 0, 0, 0.1);
  padding: 5px 10px;
  border-radius: 5px;
  color: #777;
  list-style-type: none;
  display: flex;
  justify-content: space-between;
}

.showcase li {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 10px;
}

.showcase li small {
  margin-left: 10px;
}
  
  body {
    font-family: 'Lato', sans-serif;
    background: linear-gradient(rgba(0, 18, 50, 0.84), rgba(0, 0, 0, 0.95)),
       bottom;
    background-size: cover;
    color: white;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    background-color: rgb(0, 18, 50);
    padding-top: 25px;
 

}
  .seat {
    display: inline-block;
    width: 30px;
    height: 30px;
    border: 1px solid #000;
    background-color: #ccc;
    text-align: center;
    line-height: 30px;
    font-weight: bold;
    font-family: Arial, sans-serif;
    cursor: pointer;
    margin: 5px;
  }
  
  /* Seat styles when checked */
  .seat:checked {
    background-color: #ffcc00;
    border-color: #ffcc00;
    color: #000;
  }
  
  /* Screen styles */
  .screen {
    background-color: #fff;
    height: 70px;
    width: 100%;
    margin: 15px 0;
    transform: rotateX(-45deg);
    box-shadow: 0 3px 10px rgba(255, 255, 255, .7);
}
</style>

</head>
<body>
	<jsp:include page="../User-logged-page.jsp"></jsp:include>


	<c:forEach var="entry" items="${data}">
		<c:choose>
			<c:when test="${entry.key == 'showObj'}">
				<c:set var="showObj" value="${entry.value}" />
			</c:when>
			<c:when test="${entry.key == 'screenObj'}">
				<c:set var="screenObj" value="${entry.value}" />
			</c:when>
			<c:when test="${entry.key == 'seatobj'}">
				<c:set var="seatObj" value="${entry.value}" />
			</c:when>
		</c:choose>
	</c:forEach>
	<div class="screen">SCREEN</div>


   <ul class="showcase">
      <li>
        <div class="seat">
       
        </div>
        <small>N/A</small>
      </li>

      <li>
        <small>Selected</small>
      </li>

      <li>
        <div class="seat occupied">
        </div>
        
        <small>Occupied</small>
      </li>
    </ul> 
	<c:set var="seatPosition" value="${seatObj.seatPosition}" />
<c:set var="alphabet" value="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z" />
<table>
<caption></caption>

<c:forEach begin="0" end="${seatObj.rowCount-1}" varStatus="outerLoop">
	<c:set var="alphabetArray" value="${alphabet.split(',')}"/>
	
	<tr>
	
	<td style="color: white;"> <p> <c:out value="${alphabetArray[outerLoop.index]}"/></p>
	<c:forEach begin="0" end="${seatObj.colunmCount-1}" varStatus="innerLoop">
	
	<c:choose>
	
  <c:when test="${seatObj.seatPosition[outerLoop.index][innerLoop.index]==1}">
	<input onclick="checkboxClicked(${outerLoop.index},${innerLoop.index})" type="checkbox" id="${outerLoop.index}${innerLoop.index}"name="${alphabetArray[outerLoop.index]}(${innerLoop.index})" class="seat" disabled="disabled">
	     <label style="color: white;" for="${outerLoop.index}${innerLoop.index}" class="seat-label">${innerLoop.index }</label>

  </c:when>

  <c:otherwise>
			<input onclick="checkboxClicked(${outerLoop.index},${innerLoop.index})" type="checkbox" id="${outerLoop.index}${innerLoop.index}"name="${alphabetArray[outerLoop.index]}(${innerLoop.index})" class="seat">
	     <label style="color: white;" for="${outerLoop.index}${innerLoop.index}" class="seat-label">${innerLoop.index}</label>

  </c:otherwise>
</c:choose>
	
	
		
	</c:forEach></td>
	
	</tr>
</c:forEach>
</table>
	<div id="checkbox-container"></div>
	<div class="containers">
		<form class="form-horizontal" id="bookingForm">
			<div class="form-group">
				<input value="${seatObj.id}" name="seatId" hidden=""> <input
					value="${showObj.showId}" name="showId" hidden="">
				<div class="form-group">
					<label style="color: white;">AVAILABLE SEATS:</label> <input class="form-control"
						value="${seatObj.seats}" id="availableSeat" readonly="readonly">
				</div>

				<div class="form-group">
					<label style="color: white;">SCREEN </label> <input class="form-control"
						value="${screenObj.screenName}" readonly="readonly"
						name="screenName">
				</div>
				<div class="form-group">
					<label style="color: white;">SEAT COUNT</label> <input name="seatCount"
						type="number" class="form-control" id="seatCount" readonly="readonly"> <br>
					<label style="color: white;">Price :</label> <input class="form-control" value="RS : 0.0"
						type="text" id="totalPrice" name="totalPrice" readonly="readonly"> <br>
				</div>
				<div class="form-group">
					<label style="color: white;">selected Seats</label> <input name="seatName"
						type="text" class="form-control" id="seatName" readonly="readonly"> <br>
						<input id="coordinate" name="coordinate" hidden=""> >
				</div>
				<div class="form-group">
					<button type="submit" id="book" class="btn btn-primary">Place
						Booking</button>
				</div>

			</div>
		</form>
	</div>


	<div class="modal fade" id="ticketModal" tabindex="-1" role="dialog"
		aria-labelledby="ticketModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="ticketModalLabel">Movie Ticket
						Receipt</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="ticketModalBody">
					<!-- Ticket details will be dynamically inserted here -->
				</div>
				<div class="modal-footer">
					<a class="btn btn-secondary" href="/User_Home"> CLOSE </a>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">


	$(document)
		.ready(
			function() {
				$("#book")
					.click(
						function(event) {
							event.preventDefault();
							var formData = $('#bookingForm')
								.serialize();
							$
								.ajax({
									url: "/ticket1",
									type: "GET",
									data: formData,
									processData: false, // Add processData option to prevent automatic data processing
									dataType: "JSON",
									success: function(data) {
										alert("fi");
										var ticketDetails = '';
										ticketDetails += '<p>Booking Status: '
											+ data.bookingStatus
											+ '</p>';
										ticketDetails += '<p>Movie: '
											+ data.showId.movieName.movieTitle
											+ '</p>';
										ticketDetails += '<p>Show date: '
											+ data.showId.showDate
											+ '</p>';
										ticketDetails += '<p>Booking Status: '
											+ data.showId.showTime
											+ '</p>';
										ticketDetails += '<p>Payment Status: '
											+ data.paymentStatus
											+ '</p>';
										ticketDetails += '<p>Seat Count: '
											+ data.seatCount
											+ '</p>';
										ticketDetails += '<p>Total Price: '
											+ data.totalPrice
											+ '</p>';

										$('#ticketModalBody').html(ticketDetails);
										$('#ticketModal').modal('show');
									},

									error: function(
										xhr,
										status,
										error) {
										console
											.log("Error uploading file: "
												+ error);

									}
								});
						});

				$('#seatCount').on(
					'input',
					function() {
						var availableseat = parseInt($(
							'#availableSeat').val());
						var seat = parseInt($('#seatCount')
							.val());

						if (availableseat < seat) {
							alert("Enter below "
								+ availableseat)
							$('#seatCount').val("");
						}

					})

				$('#seatCount').on('input', function() {
					var inputValue = parseFloat($(this).val());
					var total = inputValue * 120.00;
					if (isNaN(total)) {
						$('#totalPrice').val("0.00");
					} else {
						$('#totalPrice').val(total);
					}

				});

			});

	const rows = "${seatObj.rowCount}";
	const cols = "${seatObj.colunmCount}";
	const checkboxArray = new Array(rows)
	for (let i = 0; i <= rows; i++) {
		checkboxArray[i] = new Array(cols).fill(false);
	}
	const clickedCoordinates = [];

	// Function to handle checkbox click event
	function checkboxClicked(row, col) {
		var checkboxes = document.querySelectorAll('input[type="checkbox"]');
		var count = 0;

		checkboxes.forEach(function(checkbox) {
			if (checkbox.checked) {
				count++;
			}
		});


		$('#seatCount').val(count);
		$('#seatCount').trigger('input');

		console.log("Number of clicked checkboxes: " + count);


		const isChecked = checkboxArray[row][col];

		// Update the checkboxArray
		checkboxArray[row][col] = !isChecked;

		// Create coordinate string
		const seatrows = row
		const seatcolumn = col;

		// Store or remove the clicked coordinates in the array
		if (checkboxArray[row][col]) {
			clickedCoordinates.push([seatrows, seatcolumn]);
		} else {
			//	 const index = clickedCoordinates.indexOf(coordinate);
			const index = clickedCoordinates.findIndex(([x, y]) => x === seatrows && y === seatcolumn);

			if (index > -1) {
				clickedCoordinates.splice(index, 1);
			}
		}
		const coordinateInput = document.getElementById('coordinate');
		coordinateInput.value = clickedCoordinates.join(', ');
		console.log('Clicked Coordinates:', clickedCoordinates);
		// Read the names of clicked (checked) input elements and add them to an array
		var clickedInputNames = $('input[type="checkbox"]:checked').map(function() {
			return this.name;
		}).get();

		// Show the clicked input names in an input element with ID "selectedseat"
		$('#seatName').val(clickedInputNames.join(', '));
	}
		

	</script>
</body>
</html>