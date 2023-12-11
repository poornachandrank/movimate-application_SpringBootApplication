$.each(screensList, function(index, screen) {
	// Create the checkbox element
	var checkbox = $("<input>").attr({
		type: "checkbox",
		name: "screens",
		value: screen.screenId
	});

	// Create the label element for the checkbox
	var label = $("<label>").text(screen.screenName);

	// Create the input field for the seat count
	var input = $("<input>").attr({
		type: "number",
		name: "seatCounts"/*[" + screen.screenId + "]"*/,
		placeholder: "Enter seat count"
	});

	// Add the checkbox, label, and input field to the container
	container.append(checkbox).append(label).append(input);
});










//for show master

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
