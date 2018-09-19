<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Driver account</title>
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
			<a class="navbar-brand" href="#">Driver account</a>
			<!-- Nav tabs -->
			<ul class="nav nav-tabs bg-primary bg-light navbar-light" role="tablist">
			    <li class="nav-item">
			    	<a class="nav-link active" data-toggle="tab" href="#home">Home</a>
		    	    </li>
    			    <li class="nav-item">
      		      	    	<button type="button" class="btn btn-primary">Log out</button>
    			    </li>
		       </ul>

		</nav>


	</div>
	<br>
	<div class = "container-fluid">
		 <div>
			 <c:if test="${loggedDriver != null}">
			 	<h2>Driver: ${loggedDriver.name} ${loggedDriver.middleName} ${loggedDriver.lastName}</h2>
			 </c:if>
			 <c:if test="${loggedDriver == null}">
				 <h2>Driver: undefined</h2>
			 </c:if>
		 </div>
		<div>
			<b>Info:</b>
			<div>
				<table id="driverInfo" class="table table-bordered table-active table-hover">
					<thead>
						<th>Personal number</th>
						<th>Assistants</th>
						<th>Assigned truck</th>
						<th>Current order</th>
						<th>Route</th>
					</thead>
					<tbody>
						<tr>
							<td>${loggedDriver.personalNumber}</td>
							<td>
								<div class="dropdown">
									<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assistants
										<span class="caret"></span></button>
									<ul class="dropdown-menu">
										<c:if test="${loggedDriver.driver.currentTruck != null}">
											<c:if test="${not empty loggedDriver.driver.currentTruck.driversInTruck}">
												<c:forEach items="${loggedDriver.driver.currentTruck.driversInTruck}" var="driver">
													<c:if test="${driver.user.personalNumber != loggedDriver.personalNumber}">
														<li><a href="#">${driver.user.name} ${driver.user.middleName} ${driver.user.lastName}</a></li>
													</c:if>
												</c:forEach>
											</c:if>
											<c:if test="${loggedDriver.driver.currentTruck.driversInTruck.size() == 1}">
												<li><a href="#">No assistants</a></li>
											</c:if>
										</c:if>
									</ul>
								</div>
							</td>
							<td>${loggedDriver.driver.currentTruck.registrationNumber}</td>
							<td>${loggedDriver.driver.currentTruck.assignedOrder.description}</td>
							<td>
								<div class="dropdown">
									<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
										<span class="caret"></span></button>
									<ul class="dropdown-menu">
										<c:if test="${orderWithRoute != null}">
											<c:if test="${not empty orderWithRoute.route}">
												<c:forEach items="${orderWithRoute.route}" var="city">
													<li><a href="#">${city.name}</a></li>
												</c:forEach>
											</c:if>
											<c:if test="${empty orderWithRoute.route}">
													<li><a href="#">No route</a></li>
											</c:if>
										</c:if>
									</ul>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>


		</div>
		<div>
			<form action="/drivermainpage/0" method="post" id="updateStatus">
				<input hidden type="text" value="${loggedDriver.id}" name="driverId">
				<b>Your status info:</b>
				<table id="driverStatusInfo" class="table table-active table-hover" style="width: 900px">
					<thead></thead>
					<tbody>
						<tr>
							<td><h3>Current status:</h3></td>
							<td>
								<select class="form-control" id="driver_status" name="driverStatus" style="width: 200px">
									<c:if test="${loggedDriver.driver != null}">
										<option style="color: #0ed61f">${loggedDriver.driver.status}</option>
										<c:forEach items="${driverStatusList}" var="status">
											<c:if test="${status != loggedDriver.driver.status}">
												<option>${status}</option>
											</c:if>
										</c:forEach>
									</c:if>
								</select>
							</td>
							<td><button type="submit" class="btn btn-primary" form="updateStatus" style="width: 200px">Update status</button></td>
							<td>
								<c:if test="${driverStatusUpdatedSuccessfully != null}">
									<h5 style="color: darkcyan; width: 200px">${driverStatusUpdatedSuccessfully}</h5>
								</c:if>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<div>
			<form action="/drivermainpage/1" method="post" id="order_details">
				<c:if test="${loggedDriver.driver.currentTruck != null}">
					<c:if test="${loggedDriver.driver.currentTruck.assignedOrder != null}">
						<input hidden type="text" value="${loggedDriver.driver.currentTruck.assignedOrder.id}" name="orderId">
					</c:if>
				</c:if>
				<b>Your order info:</b>
				<table id="driverOrderInfo" class="table table-active table-hover" style="width: 900px">
					<thead></thead>
					<tbody>
					<tr>
						<td><h3>Order status:</h3></td>
						<td>
							<select class="form-control" id="order_status" name="orderStatus" style="width: 200px">
								<c:if test="${loggedDriver.driver.currentTruck != null}">
									<c:if test="${loggedDriver.driver.currentTruck.assignedOrder != null}">
										<option style="color: #0ed61f">${loggedDriver.driver.currentTruck.assignedOrder.status}</option>
										<c:forEach items="${orderStatusList}" var="orderStatus">
											<c:if test="${orderStatus != loggedDriver.driver.currentTruck.assignedOrder.status}">
												<option>${orderStatus}</option>
											</c:if>
										</c:forEach>
									</c:if>
									<c:if test="${loggedDriver.driver.currentTruck.assignedOrder == null}">
										<option>No assigned order</option>
									</c:if>
								</c:if>
								<c:if test="${loggedDriver.driver.currentTruck == null}">
										<option>No assigned order</option>
								</c:if>
							</select>
						</td>
						<td><button type="submit" class="btn btn-primary" form="order_details" style="width: 200px">Update order status</button></td>
						<td>
							<c:if test="${orderStatusUpdatedSuccessfully != null}">
								<h5 style="color: darkcyan; width: 200px">${orderStatusUpdatedSuccessfully}</h5>
							</c:if>
						</td>
					</tr>
					</tbody>
				</table>

			</form>
		</div>
		<div>
			<form action="/drivermainpage/2" method="post" id="order_cargo_details">
				<div class="sticky-top">
					<b>Cargo details:</b>
					<div><input class="form-control" id="myCargoDetailsInput" type="text" placeholder="Search.."></div>
				</div>
				<div>
					<table id="myCargoDetailsTable" class="table table-bordered table-active table-hover">
						<thead>
							<th>Personal number</th>
							<th>Name</th>
							<th>City From</th>
							<th>City To</th>
							<th>Status</th>
							<th></th>
						</thead>
						<tbody>
							<c:if test="${loggedDriver.driver.currentTruck != null}">
								<c:if test="${loggedDriver.driver.currentTruck.assignedOrder != null}">
									<c:if test="${loggedDriver.driver.currentTruck.assignedOrder.cargosInOrder != null}">
										<c:forEach items="${loggedDriver.driver.currentTruck.assignedOrder.cargosInOrder}" var="cargo">
											<tr>
												<input hidden type="text" value="${cargo.id}" name="cargoId">
												<td>${cargo.personalNumber}</td>
												<td>${cargo.name}</td>
												<td>${cargo.route.cityFrom.name}</td>
												<td>${cargo.route.cityTo.name}</td>
												<td>
													<select class="form-control" id="cargo_status" name="cargoStatus" style="width: 200px">
														<option style="color: #0ed61f">${cargo.status}</option>
														<c:if test="${cargoStatusList != null}">
															<c:forEach items="${cargoStatusList}" var="cargoStatus">
																<c:if test="${cargoStatus != cargo.status}">
																	<option>${cargoStatus}</option>
																</c:if>
															</c:forEach>
														</c:if>
													</select>
												</td>
												<td><button type="submit" class="btn btn-primary" form="order_cargo_details" style="width: 200px">Update cargo status</button></td>
											</tr>
										</c:forEach>
									</c:if>
								</c:if>
							</c:if>
						</tbody>
					</table>
				</div>
			</form>
			<br>
			<br>
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
			    <li class="nav-item">
			    	<a class="nav-link" data-toggle="tab" href="#about">About</a>
 			    </li>
		       </ul>

		</nav>
	</div>
	
</div>


<script>
$(document).ready(function(){
  $("#myCargoDetailsInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myCargoDetailsTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>


</body>
</html>
