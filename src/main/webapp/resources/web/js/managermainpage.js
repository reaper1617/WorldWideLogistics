//todo: make one func with input parameter selectId !

let contextName = "/worldwidelogistics";

function showRoute() {
    // alert("OnshowRoute function!");
    var selectedValuesArr = document.getElementById('add_cargos');
    // var output = [];
    var reqUrl = contextName + "/getorderroute?";
    for(var i = 0; i < selectedValuesArr.options.length; i++){
        opt = selectedValuesArr.options[i];
        if(opt.selected === true){
            // output.push(opt.value||opt.text);
            reqUrl+="selectedVal="+opt.value+"&";
        }
    }
    // reqUrl = reqUrl.substring(0, reqUrl.length - 1);
    reqUrl += "truck=0";
    // alert("output = " + output);
    // alert("url = " + reqUrl);
    // document.getElementById('currentRoute').innerHTML = "<h1>Out:" + reqUrl + "</h1>";

    var req = new XMLHttpRequest();
    req.onreadystatechange = function () {
        // alert("status:" + this.status + " state = " + this.readyState);
        if (this.status === 200 && this.readyState === 4){
            // alert("resp:" + this.responseText);
            var arr = JSON.parse(this.responseText);
            // alert("arr = " + arr);
            var routeString = "";
            for(var i = 0; i < arr.length; i++){
                var city = arr[i];
                routeString += city.name + " -> ";
            }
            routeString = routeString.substring(0, routeString.length - 4);
            document.getElementById('currentRoute').innerHTML = routeString;
        }
    };
    req.open("GET", reqUrl, true);
    req.send();
}

function showRouteOnTruckAssignPage() {
    // alert("OnshowRoute function!");
    var selectedValuesArr = document.getElementById('add_cargos');
    // var output = [];
    var reqUrl = contextName + "/getorderroute?";
    for(var i = 0; i < selectedValuesArr.options.length; i++){
        opt = selectedValuesArr.options[i];
        if(opt.selected === true){
            // output.push(opt.value||opt.text);
            reqUrl+="selectedVal="+opt.value+"&";
        }
    }
    // reqUrl = reqUrl.substring(0, reqUrl.length - 1);

    var selectedTruckEl = document.getElementById('add_truck');
    var truck = null;
    for(var j = 0; j < selectedTruckEl.length; j++){
        opt = selectedTruckEl.options[j];
        if(opt.selected === true){
            // output.push(opt.value||opt.text);
            truck = opt.value;
        }
    }
    reqUrl +="truck="+truck;
    // alert("output = " + output);
    // alert("url = " + reqUrl);
    // document.getElementById('currentRoute').innerHTML = "<h1>Out:" + reqUrl + "</h1>";

    var req = new XMLHttpRequest();
    req.onreadystatechange = function () {
        // alert("status:" + this.status + " state = " + this.readyState);
        if (this.status === 200 && this.readyState === 4){
            // alert("resp:" + this.responseText);
            var arr = JSON.parse(this.responseText);
            // alert("arr = " + arr);
            var routeString = "";
            for(var i = 0; i < arr.length; i++){
                var city = arr[i];
                routeString += city.name + " -> ";
            }
            routeString = routeString.substring(0, routeString.length - 4);
            document.getElementById('currentRoute').innerHTML = routeString;
        }
    };
    req.open("GET", reqUrl, true);
    req.send();
}

function showMessageIfTruckHasDriversHoursOverLimit() {
    // alert("showMessageIfTruckHasDriversHoursOverLimit function!");
    var selectedValuesArr = document.getElementById('add_cargos');
    // var output = [];
    var reqUrl = contextName + "/getdrivershoursoverlimit?";
    for(var i = 0; i < selectedValuesArr.options.length; i++){
        opt = selectedValuesArr.options[i];
        if(opt.selected === true){
            // output.push(opt.value||opt.text);
            reqUrl+="selectedVal="+opt.value+"&";
        }
    }
    // reqUrl = reqUrl.substring(0, reqUrl.length - 1);

    var selectedTruckEl = document.getElementById('add_truck');
    var truck = null;
    for(var j = 0; j < selectedTruckEl.length; j++){
        opt = selectedTruckEl.options[j];
        if(opt.selected === true){
            // output.push(opt.value||opt.text);
            truck = opt.value;
        }
    }
    reqUrl +="truck="+truck;

    var req = new XMLHttpRequest();
    req.onreadystatechange = function () {
        // alert("status:" + this.status + " state = " + this.readyState);
        if (this.status === 200 && this.readyState === 4){
            // alert("resp:" + this.responseText);
            var arr = JSON.parse(this.responseText);
            if (typeof arr.result === 'undefined' ){
                var message = 'Warning: chosen truck has driver(s) whose can not execute this order:\n';
                for(var i = 0; i < arr.length; i++){
                    message += "driver:"
                        + arr[i].firstName + " "
                        + arr[i].middleName + " "
                        + arr[i].lastName + " "
                        + arr[i].personalNumber
                        + " hours worked:" + arr[i].hoursWorked +"\n";
                }
                message += "\n" + "If you want to assign this truck you need to unassign unsuitable driver(s) first." + "\n";
                alert(message);
            }
            else{
                if (arr.driversInTruck === 'empty'){
                    var emptyDriversMessage = "\n" + "Warning: this truck is fit but has no assigned drivers. \n If you want to assign this truck you need assign suitable driver(s) first." + "\n";
                    alert(emptyDriversMessage);
                }
            }
        }
    };
    req.open("GET", reqUrl, true);
    req.send();
}

function makeRedIfTruckHasDriversHoursOverLimit() {
    // alert("showMessageIfTruckHasDriversHoursOverLimit function!");
    var selectedValuesArr = document.getElementById('add_cargos');
    // var output = [];
    var reqUrl = contextName + "/getdrivershoursoverlimit?";
    for(var i = 0; i < selectedValuesArr.options.length; i++){
        opt = selectedValuesArr.options[i];
        if(opt.selected === true){
            // output.push(opt.value||opt.text);
            reqUrl+="selectedVal="+opt.value+"&";
        }
    }
    // reqUrl = reqUrl.substring(0, reqUrl.length - 1);

    var selectedTruckEl = document.getElementById('add_truck');
    var truck = null;
    for(var j = 0; j < selectedTruckEl.length; j++){
        opt = selectedTruckEl.options[j];
        if(opt.selected === true){
            // output.push(opt.value||opt.text);
            truck = opt.value;
        }
    }
    reqUrl +="truck="+truck;

    var req = new XMLHttpRequest();
    req.onreadystatechange = function () {
        // alert("status:" + this.status + " state = " + this.readyState);
        if (this.status === 200 && this.readyState === 4){
            // alert("resp:" + this.responseText);
            var arr = JSON.parse(this.responseText);
            if (typeof arr.result === 'undefined' ){
                let truckOptionId = "truck"+truck;
                let truckOption = document.getElementById(truckOptionId).style.color="#b21f2d";
            }
        }
    };
    req.open("GET", reqUrl, true);
    req.send();
}