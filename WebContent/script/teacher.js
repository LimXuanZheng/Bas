function createNew(){
	document.getElementById("overlay").style.display ="block";
	}

function showHidden(){
	if(document.getElementById("checkifnew").checked){
		document.getElementById("nameOfNewButton").style.display="block";
	}
	else if(document.getElementById("checkifmy").checked){
		document.getElementById("nameOfNewButton").style.display="none";
	}
	else if(document.getElementById("checkifshare").checked){
		document.getElementById("nameOfNewButton").style.display="none";
	}
}

function confirm(){
	if(document.getElementById("checkifnew").checked){
		var btnname = document.getElementById("getBtnName").value;
		var link  = document.createElement("link");
		link.rel  = "stylesheet";
	    link.type = "text/css";
	    link.href = "css/teacherSharing.css";
		var btn =  document.createElement("button");
		//btn.setAttribute("style","color:white; display: block; margin: 5px; padding: 7px 35px; font: 300 150% langdon; background-color: #0022FF; border: 3px solid black; cursor: pointer; width:18%;");
		btn.setAttribute("class", "button");
		btn.setAttribute("id", "NB");
		document.getElementById("storeButton").appendChild(btn);
		document.getElementById("NB").appendChild(link);
		document.getElementById("NB").innerText = btnname;
		document.getElementById("overlay").style.display ="none";
	}
	else{
		document.getElementById("overlay").style.display ="none";
	}
	
	//document.getElementById("body").style.color ="red";
}

function cancel(){
	document.getElementById("overlay").style.display ="none";
}