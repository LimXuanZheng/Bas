function createNew(){
	document.getElementById("overlay").style.display ="block";

}
function confirm(){
	if(document.getElementById("checkifnew").checked){
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
		document.getElementById("NB").innerText = "new1";
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