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
		btn.setAttribute("id", btnname);
		document.getElementById("storeButton").appendChild(btn);
		document.getElementById(btnname).onclick = display5s();
		document.getElementById(btnname).onclick = findSize();
		document.getElementById(btnname).appendChild(link);
		document.getElementById(btnname).innerText = btnname;
		//upload to server
		//var filePath1 = document.getElementById("datafilepath").value;
		//var filename1 = filePath1.replace(/^.*[\\\/]/, '');
		//var myfile = new File(filePath1);
		
		document.getElementById("overlay").style.display ="none";
	}
	else{
		document.getElementById("overlay").style.display ="none";
	}
	
	//document.getElementById("body").style.color ="red";
}
function display5s(){
	var filePath = document.getElementById("datafilepath").value;
	var filename = filePath.replace(/^.*[\\\/]/, '');
	document.getElementById("row1.1").innerHTML = filename;
	var fileInput =  document.getElementById("datafilepath");
	   try{
	       alert(fileInput.files[0].size); // Size returned in bytes.
	   }catch(e){
	       var objFSO = new ActiveXObject("Scripting.FileSystemObject");
	       var e = objFSO.getFile(fileInput.value);
	       var fileSize = e.size;
	       document.getElementById("row1.4").innerHTML = fileSize;  
	   }
	//var myfile = new File(filePath1);
}

function cancel(){
	document.getElementById("overlay").style.display ="none";
}




