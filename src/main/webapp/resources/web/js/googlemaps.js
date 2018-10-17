
function myMap() {
    var mapProp= {
        center:new google.maps.LatLng(59.939095, 30.315868),
        zoom:5,
    };
    var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
    var req = new XMLHttpRequest();
    req.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200){
            var ordersArr = JSON.parse(this.responseText);
            for(var i=0; i < ordersArr.length;i++){
                var orderItem = ordersArr[i];
                // adding markers
                var cityName = orderItem.currentCity;
                var myCenter = getLatlng(cityName);//
                var marker = new google.maps.Marker({position:myCenter});
                marker.setMap(map);
                var contentString = "Order:" + orderItem.description + "; "
                    + "personal number: " + orderItem.personalNumber + "; "
                    + "date: " + orderItem.date + "; "
                    + "status: " + orderItem.status + "; "
                    + "truck: " + orderItem.assignedTruckRegistrationNumber + "; ";
                contentString += "drivers: ";
                if (typeof orderItem.assignedDrivers === 'undefined') {
                    contentString+="no drivers."
                }
                else{
                    for (var j = 0; j < orderItem.assignedDrivers.length; j++) {
                        var driver = orderItem.assignedDrivers[j];
                        contentString += driver + "; ";
                    }
                }
                var infowindow = new google.maps.InfoWindow({
                        content: contentString
                    }
                );
                google.maps.event.addListener(marker, 'click', function () {
                    infowindow.open(map,marker);
                })
            }
        }
    }
    var size = 10;
    //var reqURL = 'http://localhost:8080/gettoporders?size='+size;
    //var reqURL = 'http://localhost:8085/worldwidelogistics/gettoporders?size='+size;
    var reqURL = '/gettoporders?size='+size;
    req.open("GET", reqURL, true);
    req.send();
}





function getLatlng(cityName) {
    var globalLatLng;
    var xmlreq = new XMLHttpRequest();
    xmlreq.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200){
            var res = JSON.parse(this.responseText);
            if (res.status === "OK"){
                var resArrFromGoogle = res.results;
                var lat = resArrFromGoogle[0].geometry.location.lat;
                var lng = resArrFromGoogle[0].geometry.location.lng;
                globalLatLng = new google.maps.LatLng(lat, lng);
            }
        }
    }
    var requestedUrl = "https://maps.googleapis.com/maps/api/geocode/json?address="+cityName+"&key=AIzaSyCGkNSAUquWBQsLlAXFUPV-aYCSG3z_GgM&language=en";
    xmlreq.open('GET',requestedUrl, false);
    xmlreq.send();
    return globalLatLng;
}

