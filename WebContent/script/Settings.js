function showChangeEmailDiv() {
	var changeEmailDiv = document.getElementById("changeEmailDiv");
	if (changeEmailDiv.style.display = "none") {
		changeEmailDiv.style.display = "block";
	}
	else {
		changeEmailDiv.style.display = "none";
	}
	
}

function showChangePassDiv() {
	var changePassDiv = document.getElementById("changePassDiv");
	var editPassLinkDiv = document.getElementById("editPassLinkDiv");
	var editPassLink = document.getElementById("editPassLink");
	if (changePassDiv.style.display = "none") {
		changePassDiv.style.display = "block";
		editPassLink.innerHTML = "Click to close"
	}
	else {
		changePassDiv.style.display = "none";
		editPassLink.innerHTML = "Click to open"
	}
}