

function createNew(){
	document.getElementById("overlay").style.display ="block";
}

function showHidden(){
	if(document.getElementById("checkifnew").checked){
		document.getElementById("nameOfNewButton").style.display="block";
		document.getElementById("dropdowns").style.display="block";
		
	}
	else if(document.getElementById("checkifmy").checked){
		document.getElementById("nameOfNewButton").style.display="none";
		document.getElementById("dropdowns").style.display="none";
		document.getElementById("smaller").style.display = "none";
	}
	else if(document.getElementById("checkifshare").checked){
		document.getElementById("nameOfNewButton").style.display="none";
		document.getElementById("dropdowns").style.display="block";
	}
}

function asubmit(){
	var dc = document.getElementById("datafilepath").value;
	if(document.getElementById("checkifmy").checked){
		alert("myone");
		document.getElementById("sp").value = "solo";
		document.getElementById("d1").value = "0";
	}
	else if(document.getElementById("checkifshare").checked){
		alert("shareone");
		var table = document.getElementById("smaller");
		var text ="";
		for (var i = 1, row; row = table.rows[i]; i++) {
		   for (var j = 0, col; col = row.cells[j]; j++) {
		    text += col.innerHTML + ";";
		   }  
		}
		alert(text);
		document.getElementById("sp").value = text;
		document.getElementById("d1").value = "1";
	}
	else if(document.getElementById("checkifnew").checked){
		var table = document.getElementById("smaller");
		var text ="";
		for (var i = 1, row; row = table.rows[i]; i++) {
		   for (var j = 0, col; col = row.cells[j]; j++) {
		    text += col.innerHTML + ";";
		   }  
		}
		var btnname = document.getElementById("getBtnName").value;
		document.getElementById("sp").value = text;
		document.getElementById("d1").value = "2";
		document.getElementById("f1").value = btnname;
		
		/*alert(document.getElementById("getBtnName").value);
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
		document.getElementById(btnname).appendChild(link);
		document.getElementById(btnname).innerText = btnname;
		*/
	}
	
	
}

function enablebtn(){
	document.getElementById("submit").disabled = false;
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
		document.getElementById(btnname).appendChild(link);
		document.getElementById(btnname).innerText = btnname;
		//upload to server
		//var filePath1 = document.getElementById("datafilepath").value;
		//var filename1 = filePath1.replace(/^.*[\\\/]/, '');
		//var myfile = new File(filePath1);

		document.getElementById("overlay").style.display ="none";
	}
	else if(document.getElementById("checkifmy").checked){
		var filePath = document.getElementById("datafilepath").value;
		if (filePath == ""){
			alert("Please select a file!");
		}
		else{
			alert("Successful");
			var filename = filePath.replace(/^.*[\\\/]/, '');
			document.getElementById("hidehere").innerHTML = filePath;
			document.getElementById("overlay").style.display ="none";
		}

	}
	else if(document.getElementById("checkifshare").checked){
		document.getElementById("lastOne").onclick = displayallshare();
		document.getElementById("overlay").style.display ="none";
	}

	//document.getElementById("body").style.color ="red";
}
/*function displayallmy(){
	var filePath = document.getElementById("datafilepath").value;
	var filename = filePath.replace(/^.*[\\\/]/, '');
	var lengths = document.getElementById("displaying").row.length
	if(lengths == 1){

	document.getElementById("row1.1").innerHTML = filename;
	var fileInput =  document.getElementById("datafilepath");
	   try{
		   document.getElementById("row1.4").innerHTML = fileInput.files[0].size; // Size returned in bytes.
	   }catch(e){
	       var objFSO = new ActiveXObject("Scripting.FileSystemObject");
	       var e = objFSO.getFile(fileInput.value);
	       var fileSize = e.size;
	       document.getElementById("row1.4").innerHTML = fileSize;  
	   }

	}
	else{
		var lengths = document.getElementById("displaying").row.length;
		var neew = document.createElement("tr");
		neew.setAttribute(id, ("row" + length));
		var details1 = document.createElement("td");
		details1.setAttribute(id, ("row" + lengths + ".1"));
		details1.innerHTML = filename;
		var details2 = document.createElement("td");
		details2.setAttribute(id, ("row" + lengths + ".2"));
		var details3 = document.createElement("td");
		details3.setAttribute(id, ("row" + lengths + ".3"));
		var details4 = document.createElement("td");
		details4.setAttribute(id, ("row" + lengths + ".4"));
		document.getElementById("displaying").appendChild(neew);
		}


}

 */
function displayallshare(){
	var filePath = document.getElementById("datafilepath").value;
	var filename = filePath.replace(/^.*[\\\/]/, '');
	document.getElementById("row1.1").innerHTML = filename;
	var fileInput =  document.getElementById("datafilepath");
	try{
		document.getElementById("row1.4").innerHTML = fileInput.files[0].size; // Size returned in bytes.
	}catch(e){
		var objFSO = new ActiveXObject("Scripting.FileSystemObject");
		var e = objFSO.getFile(fileInput.value);
		var fileSize = e.size;
		document.getElementById("row1.4").innerHTML = fileSize;  
	}
}

function display5s(){
	var filePath = document.getElementById("datafilepath").value;
	var filename = filePath.replace(/^.*[\\\/]/, '');
	document.getElementById("row1.1").innerHTML = filename;
	var fileInput =  document.getElementById("datafilepath");
	try{
		document.getElementById("row1.4").innerHTML = fileInput.files[0].size; // Size returned in bytes.
	}catch(e){
		var objFSO = new ActiveXObject("Scripting.FileSystemObject");
		var e = objFSO.getFile(fileInput.value);
		var fileSize = e.size;
		document.getElementById("row1.4").innerHTML = fileSize;  
	}
	//var myfile = new File(filePath1);
	//checks from database to see if student have submitted their files
}

function cancel1(){
	document.getElementById("overlay").style.display ="none";


}

function showsomething(){
	document.getElementById("myDropdown").classList.toggle("show");
}

function filterFunction() {
	var input, filter, ul, li, a, i;
	input = document.getElementById("myInput");
	filter = input.value.toUpperCase();
	div = document.getElementById("myDropdown");
	a = div.getElementsById("ppp");
	for (i = 0; i < a.length; i++) {
		if (a[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
			a[i].style.display = "";
		} else {
			a[i].style.display = "none";
		}
	}
}


function dosth(fgfg){
	document.getElementById("pls").value = fgfg;
	document.getElementById("downloadfile").submit();
}

function displayhello(thiss){
	var table = document.getElementById("smaller");
	var text ="";
	for (var i = 1, row; row = table.rows[i]; i++) {
	   for (var j = 0, col; col = row.cells[j]; j++) {
	    text += col.innerHTML + ";";
	   }  
	}
	//alert(text);
	var sd = document.getElementById("smaller");
	document.getElementById("smaller").style.display = "block";
	var dd = document.createElement("tr");
	var tdd = document.createElement("td");
	tdd.setAttribute("align", "center");
	//var tdd2 = document.createElement("td");
	var btttttttttn = document.createElement("BUTTON");
	var t2 = document.createTextNode("Delete");
	btttttttttn.setAttribute("type", "button");
	btttttttttn.setAttribute("style", "background-color:red; border:none; font-size:16px; padding:5px;");
	btttttttttn.appendChild(t2);
	btttttttttn.setAttribute("onclick", "jjjj(this)");
	tdd.innerHTML = thiss.innerHTML;
	dd.appendChild(tdd);
	dd.appendChild(btttttttttn);
	document.getElementById("smaller").appendChild(dd);
	
}

function jjjj(thiss){
	var p=thiss.parentNode;
    p.parentNode.removeChild(p);
	//document.getElementById("smaller").deleteRow(thiss);
	
}

function refreshpage(){
	location.reload();
}









