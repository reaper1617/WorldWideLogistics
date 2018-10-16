
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
                tdDeleteBtn.innerHTML = "<form action=\"${pageContext.request.contextPath}/adminmainpage/2\" method=\"post\" >\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t<button type=\"submit\" class=\"btn btn-danger\">Delete</button>\n" +
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
