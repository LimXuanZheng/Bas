function showChangeEmailDiv() {
	var changeEmailDiv = document.getElementById("changeEmailDiv");
	var inputError = document.getElementById("inputError");
	if (changeEmailDiv.style.display == "none") {
		changeEmailDiv.style.display = "block";
		inputError.style.display = "none";
	}
	else {
		changeEmailDiv.style.display = "none";
	}
	
}

function showChangePassDiv() {
	var changePassDiv = document.getElementById("changePassDiv");
	var editPassLinkDiv = document.getElementById("editPassLinkDiv");
	var editPassLink = document.getElementById("editPassLink");
	var inputPError1 = document.getElementById("inputPError1");
	var inputPError2 = document.getElementById("inputPError2");
	var inputPError3 = document.getElementById("inputPError3");
	if (changePassDiv.style.display == "none") {
		changePassDiv.style.display = "block";
		editPassLink.innerHTML = "Click to close"
		inputPError1.style.display = "none";
		inputPError2.style.display = "none";
		inputPError3.style.display = "none";
	}
	else {
		changePassDiv.style.display = "none";
		editPassLink.innerHTML = "Click to change password"
	}
}

function checkEmail(inputEmail, oldEmail) {
	var changeEmailDiv = document.getElementById("changeEmailDiv");
	//var inputEmail = document.getElementById("inputEmail").value;
	var inputError = document.getElementById("inputError");
	//var emailForm = document.getElementById("emailForm");
	
	if (inputEmail === oldEmail) {
		changeEmailDiv.style.display = "block";
		inputError.style.display = "block";
	}
}