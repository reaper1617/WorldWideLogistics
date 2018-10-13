<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Admin account</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	<style>
		
		.gradientbackgr{
  			background: #fffff0;
	 		background: radial-gradient(#0ed61f, #bfc2ff);
		}
	</style>
</head>
<body class="gradientbackgr">


<div class="container-fluid">



	<div class = "container-fluid">
		<h2>Fixed-top container</h2>
 		<nav class="nav nav-tabs bg-primary bg-light navbar-light fixed-top">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/adminmainpage/0">Admin account</a>
			<!-- Nav tabs -->
			<ul class="nav nav-tabs bg-primary bg-light navbar-light" role="tablist">
				<li class="nav-item">
					<a class="nav-link active" data-toggle="tab" href="#manageorders">Manage orders</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" data-toggle="tab" href="#managetrucks">Manage trucks</a>
 			    </li>
			    <li class="nav-item">
				 	<a class="nav-link" data-toggle="tab" href="#manageusers">Manage users</a>
			    </li>   
			    <li class="nav-item">
				 	<a class="nav-link" data-toggle="tab" href="#managecargos">Manage cargos</a>
			    </li>
				<li class="nav-item">
					<a class="nav-link" data-toggle="tab" href="#managecities">Manage cities</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" data-toggle="tab" href="#manageroutes">Manage routes</a>
				</li>
				<form action="${pageContext.request.contextPath}/logout" method="get">
					<li class="nav-item">
							<button type="submit" class="btn btn-primary">Log out</button>
					</li>
				</form>
		       </ul>

		</nav>


	</div>
	<br>
	<div class = "container-fluid">
		 <div class="tab-content">
			 <h1>PGNTD page</h1>
  			<div id="manageorders" class="tab-pane active ">
    				<div class = "sticky-top">
						<form action="${pageContext.request.contextPath}/addneworderpage", method="get">
							<h3>Orders <button class = "btn btn-primary" type = "submit">Add new</button> </h3>
						</form>

				<div><input class="form-control" id="myInput" type="text" placeholder="Search.."></div>
				</div>
				<div>
					<table id="myTable" class="table table-bordered table-active table-hover">
    						<thead>
      							<tr>
									<th></th>
									<th></th>
        							<th>Id</th>
        							<th>Description</th>
									<th>Date/time</th>
									<th>Status</th>
        							<th>Assigned truck</th>
									<th>Assigned drivers</th>
        							<th>Route</th>
      							</tr>
						</thead>
							<tbody>
							<c:if test="${ordersPgntd != null}">
								<c:forEach items="${ordersPgntd}" var="order">
									<tr>
										<td>
											<form action="${pageContext.request.contextPath}/adminmainpage/1" method="post" >
												<button type="submit" class="btn btn-primary">Edit</button>
													<input type="text" hidden name="id" value="${order.id}">
											</form>
										</td>
										<td>
											<form action="${pageContext.request.contextPath}/adminmainpage/2" method="post" >
												<button type="submit" class="btn btn-danger">Delete</button>
												<input type="text" hidden name="id" value="${order.id}">
											</form>
										</td>
										<td>${order.personalNumber}</td>
										<td>${order.description}</td>
										<td>${order.date}</td>
										<td>${order.status}</td>
										<c:if test="${order.assignedTruckRegistrationNumber != null}">
											<td>${order.assignedTruckRegistrationNumber}</td>
										</c:if>
										<c:if test="${order.assignedTruckRegistrationNumber == null}">
											<td>No assigned truck</td>
										</c:if>
										<td>
											<div class="dropdown">
												<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
													<span class="caret"></span></button>
													<ul class="dropdown-menu">
                                                        <c:if test="${order.assignedDrivers != null}">
                                                                <c:forEach items="${order.assignedDrivers}" var="driverInTruck">
                                                                    <li><a href="#">${driverInTruck}</a></li>
                                                                </c:forEach>
                                                            <c:if test="${order.assignedDrivers == null}">
                                                                <li><a href="#">No assigned drivers</a></li>
                                                            </c:if>
                                                            <c:if test="${empty order.assignedDrivers}">
                                                                <li><a href="#">No assigned drivers</a></li>
                                                            </c:if>
                                                        </c:if>
                                                    </ul>
											</div>
										</td>
										<td>
											<div class="dropdown">
												<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
													<span class="caret"></span></button>
													<ul class="dropdown-menu">
                                                        <c:if test="${order.route != null}">
														<c:forEach items="${order.route}" var="city">
															<li><a href="#">${city}</a></li>
														</c:forEach>
                                                        </c:if>
                                                        <c:if test="${order.route == null}">
                                                                <li><a href="#">No route</a></li>
                                                        </c:if>
                                                        <c:if test="${empty order.route}">
                                                            <li><a href="#">No route</a></li>
                                                        </c:if>
													</ul>
											</div>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							</tbody>

    					</table>
				</div>
				<br>
                <div align="center">
				    <button id="showMoreBtn" class="btn btn-primary" type="button" value="Show more orders" onclick="showMoreOrders()">Show more orders</button>
                </div>
				<input id="currentOrderPage" value="0" hidden>
  			</div>
  			<%--<div id="managetrucks" class="tab-pane fade">--%>
    				<%--<div class = "sticky-top">--%>
						<%--<form action="${pageContext.request.contextPath}/addnewtruckpage" method="get">--%>
							<%--<h3>Trucks <button class = "btn btn-primary" type = "submit">Add new</button></h3>--%>
						<%--</form>--%>
				<%--<div><input class="form-control" id="myInput2" type="text" placeholder="Search.."></div>--%>
				<%--</div>--%>
				<%--<div>--%>
					<%--<table id="myTable2" class="table table-bordered table-active table-hover">--%>
    						<%--<thead>--%>
      							<%--<tr>--%>
									<%--<th></th>--%>
									<%--<th></th>--%>
        							<%--<th>Registration number</th>--%>
									<%--<th>Number of drivers</th>--%>
									<%--<th>Capacity</th>--%>
									<%--<th>State</th>--%>
									<%--<th>Current city</th>--%>
									<%--<th>Assigned drivers</th>--%>
									<%--<th>Assigned order</th>--%>
      							<%--</tr>--%>
						<%--</thead>--%>
						<%--<tbody>--%>
						<%--<c:if test="${trucksList != null}">--%>
							<%--<c:forEach items="${trucksList}" var="cell">--%>
								<%--<tr>--%>
									<%--<td>--%>
										<%--<form action="${pageContext.request.contextPath}/adminmainpage/3" method="post" >--%>
											<%--<button type="submit" class="btn btn-primary">Edit</button>--%>
												<%--<input type="text" hidden name="id" value="${cell.id}">--%>
										<%--</form>--%>
									<%--</td>--%>
									<%--<td>--%>
										<%--<form action="${pageContext.request.contextPath}/adminmainpage/4" method="post" >--%>
											<%--<button type="submit" class="btn btn-danger">Delete</button>--%>
												<%--<input type="text" hidden name="id" value="${cell.id}">--%>
										<%--</form>--%>
									<%--</td>--%>
								<%--<td>${cell.registrationNumber}</td>--%>
								<%--<td>${cell.numOfDrivers}</td>--%>
								<%--<td>${cell.capacity}</td>--%>
								<%--<td>${cell.state}</td>--%>
								<%--<td>${cell.currentCity.name}</td>--%>
								<%--<td>--%>
									<%--<div class="dropdown">--%>
										<%--<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show drivers--%>
											<%--<span class="caret"></span></button>--%>
										<%--<ul class="dropdown-menu">--%>
											<%--<c:if test="${cell.driversInTruck != null}">--%>
												<%--<c:forEach items="${cell.driversInTruck}" var="driver">--%>
													<%--<li><a href="#">${driver.user.name} ${driver.user.middleName} ${driver.user.lastName}</a></li>--%>
												<%--</c:forEach>--%>
											<%--</c:if>--%>
											<%--<c:if test="${empty cell.driversInTruck}">--%>
												<%--<li><a href="#">No assigned driver</a></li>--%>
											<%--</c:if>--%>
										<%--</ul>--%>
									<%--</div>--%>
								<%--</td>--%>

								<%--<c:if test="${cell.assignedOrder != null}">--%>
									<%--<td>${cell.assignedOrder}</td>--%>
								<%--</c:if>--%>
								<%--<c:if test="${cell.assignedOrder == null}">--%>
									<%--<td>No assigned order</td>--%>
								<%--</c:if>--%>
								<%--</tr>--%>
                            <%--</c:forEach>--%>
						<%--</c:if>--%>
						<%--</tbody>--%>
    					<%--</table>--%>
				<%--</div>--%>
  			<%--</div>--%>
  			<%--<div id="manageusers" class="tab-pane fade">--%>
    				<%--<div class = "sticky-top">--%>
						<%--<form action="${pageContext.request.contextPath}/addnewuserpage" method="get">--%>
							<%--<h3>Users <button class = "btn btn-primary" type = "submit">Add new</button></h3>--%>
						<%--</form>--%>
				<%--<div><input class="form-control" id="myInput3" type="text" placeholder="Search.."></div>--%>
				<%--</div>--%>
				<%--<div>--%>
					<%--<table id="myTable3" class="table table-bordered table-active table-hover">--%>
    						<%--<thead>--%>
      							<%--<tr>--%>
									<%--<th></th>--%>
									<%--<th></th>--%>
        							<%--<th>First name</th>--%>
        							<%--<th>Middle name</th>--%>
									<%--<th>Last name</th>--%>
									<%--<th>Personal number</th>--%>
        							<%--<th>Role</th>--%>
      							<%--</tr>--%>
						<%--</thead>	--%>
						<%--<tbody>--%>
						<%--<c:if test="${usersList != null}">--%>
                            <%--<c:forEach items="${usersList}" var="user">--%>
                                <%--<tr>--%>
                                    <%--<td>--%>
                                        <%--<form action="${pageContext.request.contextPath}/adminmainpage/5" method="post" >--%>
                                            <%--<button type="submit" class="btn btn-primary">Edit</button>--%>
                                            <%--<input type="text" hidden name="id" value="${user.id}">--%>
                                        <%--</form>--%>
                                    <%--</td>--%>
									<%--<td>--%>
										<%--<form action="${pageContext.request.contextPath}/adminmainpage/6" method="post" >--%>
											<%--<button type="submit" class="btn btn-danger">Delete</button>--%>
                                            <%--<input type="text" hidden name="id" value="${user.id}">--%>
										<%--</form>--%>
									<%--</td>--%>
                                    <%--<td>${user.name}</td>--%>
                                    <%--<td>${user.middleName}</td>--%>
                                    <%--<td>${user.lastName}</td>--%>
                                    <%--<td>${user.personalNumber}</td>--%>

									<%--<c:if test="${user.driver != null}">--%>
                                    	<%--<td>DRIVER</td>--%>
									<%--</c:if>--%>
									<%--<c:if test="${user.manager != null}">--%>
										<%--<td>MANAGER</td>--%>
									<%--</c:if>--%>
									<%--<c:if test="${user.admin != null}">--%>
										<%--<td>ADMIN</td>--%>
									<%--</c:if>--%>
                                <%--</tr>--%>
                            <%--</c:forEach>--%>
						<%--</c:if>--%>
						<%--</tbody>				--%>
    					<%--</table>--%>
				<%--</div>--%>
  			<%--</div>--%>
			<%--<div id="managecargos" class="tab-pane fade">--%>
				<%--<div class = "sticky-top">--%>
					<%--<form action="${pageContext.request.contextPath}/addnewcargopage" method="get">--%>
						<%--<h3>Cargos <button class = "btn btn-primary" type="submit">Add new</button></h3>--%>
					<%--</form>--%>
				<%--<div><input class="form-control" id="myInput4" type="text" placeholder="Search.."></div>--%>
				<%--</div>--%>
				<%--<div>--%>
					<%--<table id="myTable4" class="table table-bordered table-active table-hover">--%>
    						<%--<thead>--%>
      							<%--<tr>--%>
									<%--<th></th>--%>
									<%--<th></th>--%>
									<%--<th>Item Id</th>--%>
        							<%--<th>Name</th>--%>
        							<%--<th>Weight</th>--%>
									<%--<th>City from</th>--%>
									<%--<th>City to</th>--%>
									<%--<th>Status</th>--%>
      							<%--</tr>--%>
						<%--</thead>	--%>
						<%--<tbody>--%>
						<%--<c:if test="${cargoList != null}">--%>
							<%--<c:forEach items="${cargoList}" var="cell">--%>
								<%--<tr>--%>
									<%--<td>--%>
										<%--<form action="${pageContext.request.contextPath}/adminmainpage/7" method="post" >--%>
											<%--<button type="submit" class="btn btn-primary">Edit</button>--%>
												<%--<input type="text" hidden name="id" value="${cell.id}">--%>
										<%--</form>--%>
									<%--</td>--%>
									<%--<td>--%>
										<%--<form action="${pageContext.request.contextPath}/adminmainpage/8" method="post" >--%>
											<%--<button type="submit" class="btn btn-danger">Delete</button>--%>
                                            <%--<input type="text" hidden name="id" value="${cell.id}">--%>
										<%--</form>--%>
									<%--</td>--%>
									<%--<td>${cell.personalNumber}</td>--%>
									<%--<td>${cell.name}</td>--%>
									<%--<td>${cell.weight}</td>--%>
									<%--<td>${cell.route.cityFrom.name}</td>--%>
									<%--<td>${cell.route.cityTo.name}</td>--%>
									<%--<td>${cell.status}</td>--%>
								<%--</tr>--%>
							<%--</c:forEach>--%>
						<%--</c:if>--%>
						<%--</tbody>				--%>
    					<%--</table>--%>
				<%--</div>--%>
  			<%--</div>--%>
			 <%--<div id="managecities" class="tab-pane fade">--%>
				 <%--<div class = "sticky-top">--%>
					 <%--<form action="${pageContext.request.contextPath}/addnewcitypage" method="get">--%>
						 <%--<h3>Cities <button class = "btn btn-primary" type = "submit">Add new</button></h3>--%>
					 <%--</form>--%>
					 <%--<div><input class="form-control" id="myInput5" type="text" placeholder="Search.."></div>--%>
				 <%--</div>--%>
				 <%--<div>--%>
					 <%--<table id="myTable5" class="table table-bordered table-active table-hover">--%>
						 <%--<thead>--%>
						 <%--<tr>--%>
							 <%--<th></th>--%>
							 <%--<th></th>--%>
							 <%--<th>Name</th>--%>
							 <%--<th>Has agency</th>--%>
							 <%--<th>Drivers in city</th>--%>
							 <%--<th>Trucks in city</th>--%>
						 <%--</tr>--%>
						 <%--</thead>--%>
						 <%--<tbody>--%>
						 <%--<c:forEach items="${citiesList}" var="city">--%>
						 <%--<tr>--%>
							 <%--<td>--%>
								 <%--<form action="${pageContext.request.contextPath}/adminmainpage/9" method="post" >--%>
									 <%--<button type="submit" class="btn btn-primary">Edit</button>--%>
									 <%--<input type="text" hidden name="id" value="${city.id}">--%>
								 <%--</form>--%>
							 <%--</td>--%>
							 <%--<td>--%>
								 <%--<form action="${pageContext.request.contextPath}/adminmainpage/10" method="post" >--%>
									 <%--<button type="submit" class="btn btn-danger">Delete</button>--%>
									 <%--<input type="text" hidden name="id" value="${city.id}">--%>
								 <%--</form>--%>
							 <%--</td>--%>
							 <%--<td>${city.name}</td>--%>
							 <%--<td>${city.hasAgency}</td>--%>

							 <%--<c:if test="${city.driversInCity != null}">--%>
								 <%--<c:if test="${not empty city.driversInCity}">--%>
									 <%--<td>--%>
										<%--<div class="dropdown">--%>
											<%--<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show drivers--%>
												<%--<span class="caret"></span></button>--%>
											<%--<ul class="dropdown-menu">--%>
												<%--<c:forEach items="${city.driversInCity}" var="driver">--%>
													<%--<li><a href="#">${driver.user.name} ${driver.user.middleName} ${driver.user.lastName}</a></li>--%>
												<%--</c:forEach>--%>
											<%--</ul>--%>
										<%--</div>--%>
									<%--</td>--%>
								 <%--</c:if>--%>
								 <%--<c:if test="${empty city.driversInCity}">--%>
									 <%--<td>--%>
										 <%--<div class="dropdown">--%>
											 <%--<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show drivers--%>
												 <%--<span class="caret"></span></button>--%>
											 <%--<ul class="dropdown-menu">--%>
													 <%--<li><a href="#">No drivers in city</a></li>--%>
											 <%--</ul>--%>
										 <%--</div>--%>
									 <%--</td>--%>
								 <%--</c:if>--%>
							 <%--</c:if>--%>
							 <%--<c:if test="${city.driversInCity == null}">--%>
								 <%--<td>--%>
									 <%--<div class="dropdown">--%>
										 <%--<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show drivers--%>
											 <%--<span class="caret"></span></button>--%>
										 <%--<ul class="dropdown-menu">--%>
												 <%--<li><a href="#">No drivers in city</a></li>--%>
										 <%--</ul>--%>
									 <%--</div>--%>
								 <%--</td>--%>
							 <%--</c:if>--%>

							 <%--<c:if test="${city.trucksInCity != null}">--%>
								 <%--<c:if test="${not empty city.trucksInCity}">--%>
									 <%--<td>--%>
										 <%--<div class="dropdown">--%>
											 <%--<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show trucks--%>
												 <%--<span class="caret"></span></button>--%>
											 <%--<ul class="dropdown-menu">--%>
												 <%--<c:forEach items="${city.trucksInCity}" var="truck">--%>
													 <%--<li><a href="#">${truck.registrationNumber}</a></li>--%>
												 <%--</c:forEach>--%>
											 <%--</ul>--%>
										 <%--</div>--%>
									 <%--</td>--%>
								 <%--</c:if>--%>
								 <%--<c:if test="${empty city.trucksInCity}">--%>
									 <%--<td>--%>
										 <%--<div class="dropdown">--%>
											 <%--<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show trucks--%>
												 <%--<span class="caret"></span></button>--%>
											 <%--<ul class="dropdown-menu">--%>
												 <%--<li><a href="#">No trucks in city</a></li>--%>
											 <%--</ul>--%>
										 <%--</div>--%>
									 <%--</td>--%>
								 <%--</c:if>--%>
							 <%--</c:if>--%>
							 <%--<c:if test="${city.trucksInCity == null}">--%>
								 <%--<td>--%>
									 <%--<div class="dropdown">--%>
										 <%--<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show trucks--%>
											 <%--<span class="caret"></span></button>--%>
										 <%--<ul class="dropdown-menu">--%>
											 <%--<li><a href="#">No trucks in city</a></li>--%>
										 <%--</ul>--%>
									 <%--</div>--%>
								 <%--</td>--%>
							 <%--</c:if>--%>
						 <%--</tr>--%>
						 <%--</c:forEach>--%>
						 <%--</tbody>--%>
					 <%--</table>--%>
				 <%--</div>--%>
			 <%--</div>--%>
			 <%--<div id="manageroutes" class="tab-pane fade">--%>
				 <%--<div class = "sticky-top">--%>
					 <%--<form action="${pageContext.request.contextPath}/addnewroutepage" method="get">--%>
						 <%--<h3>Routes <button class = "btn btn-primary" type = "submit">Add new</button></h3>--%>
					 <%--</form>--%>
					 <%--<div><input class="form-control" id="myInput6" type="text" placeholder="Search.."></div>--%>
				 <%--</div>--%>
				 <%--<div>--%>
					 <%--<table id="myTable6" class="table table-bordered table-active table-hover">--%>
						 <%--<thead>--%>
						 <%--<tr>--%>
							 <%--<th></th>--%>
							 <%--<th></th>--%>
							 <%--<th>City from</th>--%>
							 <%--<th>City to</th>--%>
							 <%--<th>Distance</th>--%>
						 <%--</tr>--%>
						 <%--</thead>--%>
						 <%--<tbody>--%>
						 <%--<c:if test="${routesList != null}">--%>
							 <%--<c:forEach items="${routesList}" var="route">--%>
								<%--<tr>--%>
									<%--<td>--%>
										<%--<form action="${pageContext.request.contextPath}/adminmainpage/11" method="post" >--%>
											<%--<button type="submit" class="btn btn-primary">Edit</button>--%>
											<%--<input type="text" hidden name="id" value="${route.id}">--%>
										<%--</form>--%>
									<%--</td>--%>
									<%--<td>--%>
										<%--<form action="${pageContext.request.contextPath}/adminmainpage/12" method="post" >--%>
											<%--<button type="submit" class="btn btn-danger">Delete</button>--%>
											<%--<input type="text" hidden name="id" value="${route.id}">--%>
										<%--</form>--%>
									<%--</td>--%>
									<%--<td>${route.cityFrom.name}</td>--%>
									<%--<td>${route.cityTo.name}</td>--%>
									<%--<td>${route.distance}</td>--%>
								<%--</tr>--%>
							 <%--</c:forEach>--%>
						 <%--</c:if>--%>
						 <%--</tbody>--%>
					 <%--</table>--%>
				 <%--</div>--%>
                 <%--<br>--%>
                 <%--<br>--%>
			 </div>
		</div>


		
	</div>
	<div class = "container-fluid ">
		<nav class="nav nav-tabs bg-light navbar-light fixed-bottom">
			<a class="navbar-brand" href="#">WorldWideLogistics</a>
			<!-- Nav tabs -->
			<ul class="nav nav-tabs bg-light navbar-light" role="tablist">
			    <li class="nav-item">
			    	<a class="nav-link active " data-toggle="tab" href="#home">Home</a>
		    	    </li>
			    <%--<li class="nav-item">--%>
			    	<%--<a class="nav-link" data-toggle="tab" href="#about">About</a>--%>
 			    <%--</li>--%>
		       </ul>

		</nav>
	</div>
	
</div>


<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>

<script>
$(document).ready(function(){
  $("#myInput2").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable2 tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>

<script>
$(document).ready(function(){
  $("#myInput3").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable3 tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>

<script>
$(document).ready(function(){
  $("#myInput4").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable4 tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>
<script>
    $(document).ready(function(){
        $("#myInput5").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#myTable5 tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
<script>
    $(document).ready(function(){
        $("#myInput6").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#myTable6 tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>

<script>
	function showMoreOrders() {
		// alert("Gonna show more orders!");
		var req = new XMLHttpRequest();
        // alert("Making onreadystatechangefunction!");
		req.onreadystatechange = function () {
		    alert("readyState=" + this.readyState + "status = " + this.status);
			if (this.readyState === 4 && this.status === 200){
			    // alert("resp text:" + this.responseText);
			    var ordersArr = JSON.parse(this.responseText);
			    // alert("parsed:" + ordersArr);
			    for(var i = 0; i < ordersArr.length; i++){
			        alert(ordersArr[i]);
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
                        for(var i = 0; i < item.assignedDrivers.length; i++){
                            resultString += "                             <li><a href=\"#\">" + item.assignedDrivers[i] + "</a></li>\n"
                        }
                        resultString +=  "                        </ul>\n" +
                            "                        </div>"
                        tdAssignedDrivers.innerHTML = resultString;
                    }

                    if (typeof item.route === 'undefined'){
                        tdRoute.innerHTML = "No route";
                    }
                    else{
                        var resultString = "<div class=\"dropdown\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<button class=\"btn btn-primary dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\">Show route\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"caret\"></span></button>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t<ul class=\"dropdown-menu\">\n";

                        for(var i = 0; i < item.route.length; i++) {
                            resultString += "                                                                <li><a href=\"#\">" +item.route[i]+ "</a></li>\n";
                        }
                        resultString +=     "\t\t\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t</div>";
                        tdRoute.innerHTML = resultString;
                    }

				}
				var currentOrderPageNum = document.getElementById('currentOrderPage').getAttribute('value');
			    var newNum = (Number)(currentOrderPageNum + 1);
				document.getElementById('currentOrderPage').setAttribute('value',newNum);
				alert("current order page=" + newNum);
			}
        };
		var pageSize = 2; //document.getElementById('currentOrderPage').value();
		var pageNumber = document.getElementById('currentOrderPage').getAttribute('value');
		var neseccaryPageNumber = (Number)(pageNumber + 1);
		// alert("pageNumberT = " + pageNumberT);
		// var pageNumber=1;
		var reqURL = 'http://localhost:8080/getpaginatedorderslist?pageSize='+pageSize+'&pageNumber='+neseccaryPageNumber;
		// alert("req opening... :" + reqURL);
		req.open("GET", reqURL, true);
        // alert("req sending...");
		req.send();
    }



</script>


</body>
</html>
