var open = false;
function animateNotification(){
	var div = document.getElementById('notificationTab'); 
	var badge = document.getElementById('notificationBadge'); 
	var info = document.getElementById('InfoBox')
	
	if(open == false){
		var width = 50; 
		var height = 50;
		var infoHeight = 0;
	    var pos = 35;
	    var id = setInterval(frame, 1);
	    function frame() {
	   		if (pos == 285 && width == 300) {
	   			info.style.visibility = "visible"
	   			if(height == 250 && infoHeight == 200)
	   				clearInterval(id);
	   			else{
	   				height += 5;
	   				infoHeight += 5;
	   				div.style.height = height + 'px';
	   				info.style.height = infoHeight + 'px';
	   			}
	    	}else{
		        pos += 5; 
		        width += 5;
		        div.style.width = width + 'px';
		        badge.style.right = pos + 'px'; 
		    }
		}
	    document.getElementById('notiIcon').innerHTML = "Announcement <span class='glyphicon glyphicon-bullhorn'></span>";
	    open = true;
	}else{
		var width = 300;
		var height = 250;
		var infoHeight = 200;
	    var pos = 285;
	    var id = setInterval(frame, 1);
	    function frame() {
	    	if(height == 50 && infoHeight == 0){
	    		info.style.visibility = "hidden"
		   		if (pos == 35 && width == 50) {
		    		clearInterval(id);
		    	}else{
			        pos -= 5; 
			        width -= 5;
			        div.style.width = width + 'px';
			        badge.style.right = pos + 'px'; 
			    }
	    	}else{
	    		infoHeight -= 5;
	    		height -= 5;
	    		info.style.height = height + 'px';
	    		div.style.height = height + 'px';
	    		
	    	}
		}
	    document.getElementById('notiIcon').innerHTML = "<span class='glyphicon glyphicon-bullhorn'></span>";
	    open = false;
	}
}

var chatOpen = false;
var opening = true;
function minimiseChat(){
	var elem = document.getElementById("wholeChatBox");  
	if(opening){
		var pos = 0;
		var id = setInterval(frame, 5);
		function frame() {
			if (pos == -210) {
				clearInterval(id);
			} else {
				pos -= 10; 
				elem.style.bottom = pos + 'px';
			}
		}
		opening = false;
	}else{
		var pos = -210;
		var id = setInterval(frame, 5);
		function frame() {
			if (pos == 0) {
				clearInterval(id);
			} else {
				pos += 10; 
				elem.style.bottom = pos + 'px';
			}
		}
		opening = true;
	}
}

function closeChat(){
	document.getElementById("wholeChatBox").style.display = "none";
	chatOpen = false;
}

function changeUser(div){
	if(document.getElementById("userToName").innerHTML == div.children[1].innerHTML){
		
	}else{
		document.getElementById("hiddenInput").value = div.children[2].innerHTML;
		if(!chatOpen){
			document.getElementById("hiddenInput1").value = "Show";
		}
		
		document.getElementById("hiddenInput2").value = div.children[1].innerHTML;
		document.getElementById("someform").submit();
	}
}