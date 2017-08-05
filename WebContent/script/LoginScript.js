function getLocation(){
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			document.getElementById("latlongLocation").value = "Lat: " + position.coords.latitude + ", Long: " + position.coords.longitude;
		});
	}
}

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
