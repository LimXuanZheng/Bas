var chatOpen = false;
var open = true;
function minimiseChat(){
	var elem = document.getElementById("wholeChatBox");  
	if(open){
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
		open = false;
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
		open = true;
	}
}

function closeChat(){
	document.getElementById("wholeChatBox").style.display = "none";
	chatOpen = false;
}

function changeUser(div){
	document.getElementById("userToName").innerHTML = div.children[1].innerHTML;
	if(!chatOpen){
		document.getElementById("wholeChatBox").style.display = "block";
	}
}