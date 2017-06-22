function showPopup() {
	var popup = document.getElementById("popupDiv");
	popup.style.display = "block";
}
function closePopup() {
	var popup = document.getElementById("popupDiv");
	var pInputError = document.getElementById("pInputError");
	popup.style.display = "none";
	pInputError.style.display = "none";
}
window.onclick = function(event) {
	if (event.target == popup) {
		var popup = document.getElementById("popupDiv");
		popup.style.display = "none";
	}
}
function renewPass() {
	var changeEmail = document.getElementById("changeEmail").value;
	var pInputError = document.getElementById("pInputError");
	if (changeEmail == null || changeEmail == "") {
		pInputError.style.display = "block";
	}
}
function check() {
	var username = document.getElementById("userID").value;
	var password = document.getElementById("passID").value;
	
	
}