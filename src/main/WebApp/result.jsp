<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="css.css" type="text/css" rel="stylesheet"/> 
	<title>result</title>
</head>
<body>
	<div id="map"></div>
	<script>
      function initMap() {
           var directionsDisplay = new google.maps.DirectionsRenderer;
           var map = new google.maps.Map(document.getElementById('map'),{zoom:7, center: {lat: ${lat[0]}, lng: ${lon[0]}}});
		   var latitude=${lat};
		   var longitude=${lon};
		   var mintemp=${mintemp};
		   var maxtemp=${maxtemp};
		   var weacond=${weacond};
		   var length = latitude.length;
		   var polyline=[];
     	   for (var i = 0; i < length ; i++) 
		   {
				var lat=parseFloat(latitude[i]);
				var lon=parseFloat(longitude[i]);
			    var pos = new google.maps.LatLng(lat, lon);
			    polyline.push(pos);
			    var marker = new google.maps.Marker({
			        position: pos,
			        map: map,       
			        title: 'MaxTemperature: '+maxtemp[i]+ 
			        		'\nMinTemperature: '+mintemp[i]+
			        		'\nWeatherConditions: '+weacond[i]
					});
			}

			var pathpolyline = new google.maps.Polyline({
			    path: polyline,
			    geodesic: true,
			    strokeColor: 'red',
			    strokeOpacity: 1.0,
			    strokeWeight: 3
  			});

  			pathpolyline.setMap(map);
      
			directionsDisplay.setMap(map);  
     		}
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=APIKEY&callback=initMap">
    </script>
</body>
</html>