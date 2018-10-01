<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Assign truck to order page</title>
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



	<div class = "container-fluid fixed-top">
		<h2>Fixed-top container</h2>
		<nav class="nav nav-tabs bg-primary bg-light navbar-light fixed-top">
			<a class="navbar-brand" href="#">Assign truck to order</a>
			<!-- Nav tabs -->
		</nav>
 
	</div>
	<br>
	<br>
	<div class = "container-fluid" >
		 
			<div class="media" >
				<%--<div class="media-left">--%>
		      			<%--<img src="img_avatar1.png" class="media-object" style="width:100px">--%>
				<%--</div>--%>
				<div>
					<form action="/assigntrucktoorderpage", method="post">
						<div class="form-group">
							<input type="text" hidden class="form-control" id="item_descr"  name="description" style="width:350px" value="${orderDTO.description}">
						</div>
						<div class="form-group">
							<select hidden multiple class="form-control" id="add_cargos"  name="cargosInOrder">
								<c:if test="${chosenCargos != null}">
									<c:if test="${not empty chosenCargos}">
										<c:forEach items="${chosenCargos}" var="cargo">
											<option selected value="${cargo.id}">${cargo.name}</option>
										</c:forEach>
									</c:if>
									<c:if test="${empty chosenCargos}">
										<option selected>No cargos available</option>
									</c:if>
								</c:if>
							</select>
						</div>
						<div class="form-group">
							<label for="add_truck">Assign truck:</label>
							 <select  class="form-control" id="add_truck" size="10" required="required" name="assignedTruckId">
        						<c:if test="${availableTrucks != null}">
									<c:if test="${not empty availableTrucks}">
										<c:forEach items="${availableTrucks}" var="truck">
								 			<option value="${truck.id}">${truck.registrationNumber}</option>
										</c:forEach>
									</c:if>
									<c:if test="${empty availableTrucks}">
											<option>No trucks available</option>
									</c:if>
								</c:if>
      							</select>
						</div>
						<%--<div class="form-group">--%>
							<%--<label for="assign_truck">Assign truck</label>--%>
							 <%--<select class="form-control" id="assign_truck">--%>
        							<%--<option>rr44443</option>--%>
        							<%--<option>re45453</option>--%>
        							<%--<option>3</option>--%>
        							<%--<option>4</option>--%>
        							<%--<option>5</option>--%>
      							<%--</select>--%>
						<%--</div>--%>
						<div>
							<button type="submit" class="btn btn-primary">Create order with chosen truck</button>
							<button type="submit" class="btn btn-primary" form="rollback">Rollback changes</button>
						</div>
					</form>
					<form action="/index" method="get" id="rollback"></form>
				</div>
			
					
    			</div>
  			
	</div>
	<div class = "container-fluid fixed-bottom">
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


</body>
</html>
