var map;
function initMap() {
	map = new google.maps.Map(document.getElementById('map'), {
		center: {lat: 1.2854999999999999, lng: 103.8565},
		zoom: 12
	});

	// Try HTML5 geolocation.
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var pos = {
					lat: position.coords.latitude,
					lng: position.coords.longitude
			};

			var marker = new google.maps.Marker({
				position: pos,
				map: map,
			});
			
			var cityCircle = new google.maps.Circle({
				strokeColor: '#FF0000',
				strokeOpacity: 0.8,
				strokeWeight: 2,
				fillColor: '#FF0000',
				fillOpacity: 0.35,
				map: map,
				center: {lat: 1.2854999999999999, lng: 103.8565},
				radius: 10000
			});

			map.setCenter(pos);
		}, function() {
			handleLocationError(true, map.getCenter());
		});
	} else {
		// Browser doesn't support Geolocation
		handleLocationError(false, map.getCenter());
	}
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
	infoWindow.setPosition(pos);
	infoWindow.setContent(browserHasGeolocation ?
			'Error: The Geolocation service failed.' :
	'Error: Your browser doesn\'t support geolocation.');
	infoWindow.open(map);
}
