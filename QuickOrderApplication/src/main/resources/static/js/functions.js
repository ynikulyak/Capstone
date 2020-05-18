console.log("Now: " + new Date());
window.onload = function() {
	var input = document.getElementById("item-qty");

	document.getElementById("plus").onclick = function() {
		input.value = parseInt(input.value, 10) + 1
	}

	document.getElementById("minus").onclick = function() {
		if (input.value == 1) {
			return;
		} else {
			input.value = parseInt(input.value, 10) - 1
		}
	}
}
