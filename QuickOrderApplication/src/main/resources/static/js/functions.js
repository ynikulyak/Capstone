console.log("Now: " + new Date());
$(document).ready(function() {
	var input = document.getElementById("item-qty");

	document.getElementById("plus").onclick = function() {
	    var current = parseInt(input.value, 10) || 1;
	    current = current < 1 ? 1 : current;
	    current = current > 9 ? 9 : current;
		input.value = current + 1;
	}

	document.getElementById("minus").onclick = function() {
	    var current = parseInt(input.value, 10) || 1;
        current = current < 1 ? 1 : current;

		if (current <= 1) {
			return;
		} else {
			input.value = current - 1
		}
	}
});
