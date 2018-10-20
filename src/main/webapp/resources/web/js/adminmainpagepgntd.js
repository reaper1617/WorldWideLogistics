
$(document).ready(function(){
    $("#myInput").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});



$(document).ready(function(){
    $("#myInput2").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#myTable2 tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});



$(document).ready(function(){
    $("#myInput3").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#myTable3 tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});



$(document).ready(function(){
    $("#myInput4").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#myTable4 tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});


$(document).ready(function(){
    $("#myInput5").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#myTable5 tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});


$(document).ready(function(){
    $("#myInput6").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#myTable6 tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});

function showMoreOrders() {
    // alert("Gonna show more orders!");
    console.log("Gonna show more orders!");
    var req = new XMLHttpRequest();
    // alert("Making onreadystatechangefunction!");
    console.log("Making onreadystatechangefunction!");
    req.onreadystatechange = function () {
        // alert("readyState=" + this.readyState + "status = " + this.status);
        console.log("readyState=" + this.readyState + "status = " + this.status);
        if (this.readyState === 4 && this.status === 200){
            alert("resp text:" + this.responseText);
            console.log("resp text:" + this.responseText);
            var ordersArr = JSON.parse(this.responseText);
            // alert("parsed:" + ordersArr);
            console.log("parsed: " + ordersArr);
            alert("ordersArr length = " + ordersArr.length);
            for(var i = 0; i < ordersArr.length; i++){
                alert("ordersArr["+i+"] = " + ordersArr[i]);
                console.log("ordersArr[i] = " + ordersArr[i]);
                var item = ordersArr[i];
                var tbody = document.getElementById('myTable').getElementsByTagName('TBODY')[0];
                var row = document.createElement("TR");
                tbody.appendChild(row);
                var tdEditBtn = document.createElement("TD");
                var tdDeleteBtn = document.createElement("TD");
                var tdId = document.createElement("TD");
                var tdDescr = document.createElement("TD");
                var tdDate = document.createElement("TD");
                var tdStatus = document.createElement("TD");
                var tdAssignedTruck = document.createElement("TD");
                var tdAssignedDrivers = document.createElement("TD");
                var tdRoute = document.createElement("TD");
                row.appendChild(tdEditBtn);
                row.appendChild(tdDeleteBtn);
                row.appendChild(tdId);
                row.appendChild(tdDescr);
                row.appendChild(tdDate);
                row.appendChild(tdStatus);
                row.appendChild(tdAssignedTruck);
                row.appendChild(tdAssignedDrivers);
                row.appendChild(tdRoute);
                tdEditBtn.innerHTML = "<form action=\"${pageContext.request.contextPath}/adminmainpage/1\" method=\"post\" >\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t<button type=\"submit\" class=\"btn btn-primary\">Edit</button>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" hidden name=\"id\" value=\"" + item.id + "\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t</form>";
                // tdDeleteBtn.innerHTML = "<form action=\"${pageContext.request.contextPath}/adminmainpage/2\" method=\"post\" >\n" +
                tdDeleteBtn.innerHTML = "<form action='#'>\n" +
                    "<button type=\"submit\" id=\"del+${order.id}\" class=\"btn btn-danger\" onclick=\"deleteOrder("+item.id+")\">Delete</button>" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" hidden name=\"id\" value=\"" +item.id+"\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t</form>";
                tdId.innerHTML = item.personalNumber;
                tdDescr.innerHTML = item.description;
                tdDate.innerHTML = item.date;
                tdStatus.innerHTML = item.status;
                if (typeof item.assignedTruckRegistrationNumber === 'undefined') {
                    tdAssignedTruck.innerHTML = "No assigned truck";
                }
                else {
                    tdAssignedTruck.innerHTML = item.assignedTruckRegistrationNumber;
                }
                if (typeof item.assignedDrivers === 'undefined') {
                    tdAssignedDrivers.innerHTML = " <div class=\"dropdown\">\n" +
                        "                            <button class=\"btn btn-primary dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\">Show assigned drivers\n" +
                        "                        <span class=\"caret\"></span></button>\n" +
                        "                        <ul class=\"dropdown-menu\">\n" +
                        "                             <li><a href=\"#\">No assigned drivers</a></li>\n" +
                        "                        </ul>\n" +
                        "                        </div>"
                }
                else {

                    var resultString = " <div class=\"dropdown\">\n" +
                        "                            <button class=\"btn btn-primary dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\">Show assigned drivers\n" +
                        "                        <span class=\"caret\"></span></button>\n" +
                        "                        <ul class=\"dropdown-menu\">\n";
                    for(var j = 0; j < item.assignedDrivers.length; j++){
                        resultString += "                             <li><a href=\"#\">" + item.assignedDrivers[j] + "</a></li>\n"
                    }
                    resultString +=  "                        </ul>\n" +
                        "                        </div>"
                    tdAssignedDrivers.innerHTML = resultString;
                }

                if (typeof item.route === 'undefined'){
                    tdRoute.innerHTML = "No route";
                }
                else{
                    var resultString2 = "<div class=\"dropdown\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t<button class=\"btn btn-primary dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\">Show route\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"caret\"></span></button>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t<ul class=\"dropdown-menu\">\n";

                    for(var k = 0; k < item.route.length; k++) {
                        resultString2 += "                                                                <li><a href=\"#\">" +item.route[k]+ "</a></li>\n";
                    }
                    resultString2 +=     "\t\t\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t</div>";
                    tdRoute.innerHTML = resultString2;
                }

            }
            var currentOrderPageNum = (Number)(document.getElementById('currentOrderPage').getAttribute('value'));
            var newNum = (Number)(currentOrderPageNum + 1);
            document.getElementById('currentOrderPage').setAttribute('value',newNum);
            alert("current order page=" + newNum);
        }
    };
    var pageSize = 2; //document.getElementById('currentOrderPage').value();
    var pageNumber = (Number)(document.getElementById('currentOrderPage').getAttribute('value'));
    var necessaryPageNumber = (Number)(pageNumber + 1);
    // alert("pageNumberT = " + pageNumberT);
    // var pageNumber=1;
    //var reqURL = 'http://localhost:8080/getpaginatedorderslist?pageSize='+pageSize+'&pageNumber='+necessaryPageNumber;

    //var reqURL = 'http://localhost:8085/worldwidelogistics/getpaginatedorderslist?pageSize='+pageSize+'&pageNumber='+necessaryPageNumber;
    var reqURL = '/getpaginatedorderslist?pageSize='+pageSize+'&pageNumber='+necessaryPageNumber;
    // alert("req opening... :" + reqURL);
    req.open("GET", reqURL, true);
    // alert("req sending...");
    req.send();
}

function deleteOrder(orderId) {
    // alert("in deleteOrder!");
    // alert("orderId:" + orderId);
    var sureDelete = confirm("Do you really want to delete this order?");
    if (sureDelete) {
        var req = new XMLHttpRequest();
        req.onreadystatechange = function () {
            if (this.status === 200 && this.readyState === 4) {
                alert("resp:" + this.responseText);
                var res = JSON.parse(this.responseText);
                // alert("resp:" + res);
                if (res === "ERROR_CAN_NOT_DELETE_ORDER_WITH_SUCH_STATUS"){
                    alert("Can not delete order! Error message: " + res);
                }
                else {
                    alert("Order deleted successfully!");
                }
            }
        }
        var url = "/deleteorder?orderId=" + orderId;
        req.open('GET', url, true);
        req.send();
    }
}

function showMoreTrucks() {
    alert("Gonna show more trucks!");
    console.log("Gonna show more orders!");
    var req = new XMLHttpRequest();
    // alert("Making onreadystatechangefunction!");
    console.log("Making onreadystatechangefunction!");
    req.onreadystatechange = function () {
        alert("readyState=" + this.readyState + "status = " + this.status);
    //     console.log("readyState=" + this.readyState + "status = " + this.status);
        if (this.readyState === 4 && this.status === 200){
            alert("resp text:" + this.responseText);
            var trucksArr = JSON.parse(this.responseText);
            alert("parsed:" + trucksArr);
    //         console.log("parsed: " + ordersArr);
    //         alert("ordersArr length = " + ordersArr.length);
            for(var i = 0; i < trucksArr.length; i++){
                alert("trucksArr["+i+"] = " + trucksArr[i]);
    //             console.log("ordersArr[i] = " + ordersArr[i]);
                var item = trucksArr[i];
                var tbody = document.getElementById('myTable2').getElementsByTagName('TBODY')[0];
                var row = document.createElement("TR");
                tbody.appendChild(row);
                var tdEditBtn = document.createElement("TD");
                var tdDeleteBtn = document.createElement("TD");
                var tdRegNum = document.createElement("TD");
                var tdNumOfDrivers = document.createElement("TD");
                var tdCapacity = document.createElement("TD");
                var tdState = document.createElement("TD");
                var tdCurrentCity = document.createElement("TD");
                var tdAssignedDrivers = document.createElement("TD");
                var tdOrder = document.createElement("TD");
                row.appendChild(tdEditBtn);
                row.appendChild(tdDeleteBtn);
                row.appendChild(tdRegNum);
                row.appendChild(tdNumOfDrivers);
                row.appendChild(tdCapacity);
                row.appendChild(tdState);
                row.appendChild(tdCurrentCity);
                row.appendChild(tdAssignedDrivers);
                row.appendChild(tdOrder);
                tdEditBtn.innerHTML = "<form action=\"/adminmainpage/3\" method=\"post\" >\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<button type=\"submit\" class=\"btn btn-primary\">Edit</button>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" hidden name=\"id\" value=\"" + item.id +"\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t</form>";


                tdDeleteBtn.innerHTML = "<form action=\"#\" >\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<button type=\"submit\" class=\"btn btn-danger\" onclick=\"deleteTruck("+item.id+")\">Delete</button>\n" +
                    "                                            <input type=\"text\" hidden name=\"id\" value=\"" +item.id+"\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t</form>";

                tdRegNum.innerHTML = item.registrationNumber;
                tdNumOfDrivers.innerHTML = item.numberOfDrivers;
                tdCapacity.innerHTML = item.capacity;
                tdState.innerHTML = item.state;
                tdCurrentCity.innerHTML = item.currentCity;
                if (typeof item.assignedDriversNames === 'undefined'){
                    tdAssignedDrivers.innerHTML = "<div class=\"dropdown\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t<button class=\"btn btn-primary dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\">Show drivers\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<span class=\"caret\"></span></button>\n" +
                        "\t\t\t\t\t\t\t\t\t\t<ul class=\"dropdown-menu\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t<li><a href=\"#\">No assigned drivers</a></li>\n" +
                        "\t\t\t\t\t\t\t\t\t\t</ul>\n" +
                        "\t\t\t\t\t\t\t\t\t</div>";
                }
                else {
                    var driversStr = "<div class=\"dropdown\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t<button class=\"btn btn-primary dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\">Show drivers\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<span class=\"caret\"></span></button>\n" +
                        "\t\t\t\t\t\t\t\t\t\t<ul class=\"dropdown-menu\">\n";
                    for(var j = 0; j < item.assignedDriversNames.length; j++){
                        driversStr += "\t\t\t\t\t\t\t\t\t\t\t\t\t<li><a href=\"#\">" + item.assignedDriversNames[j] + "</a></li>\n";
                    }
                    driversStr +="\t\t\t\t\t\t\t\t\t\t</ul>\n" +
                        "\t\t\t\t\t\t\t\t\t</div>";
                    tdAssignedDrivers.innerHTML = driversStr;

                }
                if (typeof item.assignedOrderDescription === 'undefined'){
                    tdOrder.innerHTML = "No assigned order";
                }
                else {
                    tdOrder.innerHTML = item.assignedOrderDescription;
                }
            }
            var currentTruckPageNum = (Number)(document.getElementById('currentTruckPage').getAttribute('value'));
            var newNum = (Number)(currentTruckPageNum + 1);
            document.getElementById('currentTruckPage').setAttribute('value',newNum);
            alert("current truck page=" + newNum);
        }
    };
    var pageSize = 2; //document.getElementById('currentOrderPage').value();
    var pageNumber = (Number)(document.getElementById('currentTruckPage').getAttribute('value'));
    var necessaryPageNumber = (Number)(pageNumber + 1);
    // alert("pageNumberT = " + pageNumberT);
    // var pageNumber=1;
    //var reqURL = 'http://localhost:8080/getpaginatedorderslist?pageSize='+pageSize+'&pageNumber='+necessaryPageNumber;

    //var reqURL = 'http://localhost:8085/worldwidelogistics/getpaginatedorderslist?pageSize='+pageSize+'&pageNumber='+necessaryPageNumber;
    var reqURL = '/getpaginatedtruckslist?pageSize='+pageSize+'&pageNumber='+necessaryPageNumber;
    // alert("req opening... :" + reqURL);
    req.open("GET", reqURL, true);
    // alert("req sending...");
    req.send();
}

function deleteTruck(truckId) {
    alert("in deleteTruck!");
    alert("truckId:" + truckId);
    var sureDelete = confirm("Do you really want to delete this truck?");
    if (sureDelete) {
        var req = new XMLHttpRequest();
        req.onreadystatechange = function () {
            alert("readyState=" + this.readyState + "status = " + this.status);
            if (this.status === 200 && this.readyState === 4) {
                alert("resp:" + this.responseText);
                var res = JSON.parse(this.responseText);
                alert("resp:" + res);
                if (res === "TRUCK_DELETED"){
                    alert("Truck del!");
                }
                else {
                    alert("res not TRUCK_DELETED");
                }
                //             if (res === "ERROR_CAN_NOT_DELETE_ORDER_WITH_SUCH_STATUS"){
                //                 alert("Can not delete order! Error message: " + res);
                //             }
                //             else {
                //                 alert("Order deleted successfully!");
                //             }
                //         }
            }
        }
        var url = "/deletetruck?truckId=" + truckId;
        req.open('POST', url, true);
        req.send();
    }
}