<!DOCTYPE html>
<html lang="en">
<head>
  <title>Truck change page</title>
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
			<a class="navbar-brand" href="#">Change truck</a>
			<!-- Nav tabs -->
		</nav>
 
	</div>
	<br>
	<br>
	<div class = "container-fluid" >
		 <h2>Truck tu58587</h2>
			<div class="media" >
				<div class="media-left">
		      			<img src="img_avatar1.png" class="media-object" style="width:100px">
				</div>
				<div>
					<form action="#">
    						<div class="form-group">
					      		<label for="registration_number">New registration number:</label>
      							<input type="text" class="form-control" id="registration_number" placeholder="Enter new registration number" name="registrationnumber" style="width:350px">
						</div>
						<div class="form-group">
					      		<label for="num_of_drivers">New shift size</label>
      							<input type="text" class="form-control" id="num_of_drivers" placeholder="Enter new number of drivers" name="numberofdrivers" style="width:350px">
						</div>
						<div class="form-group">
					      		<label for="capacity">New capacity</label>
      							<input type="text" class="form-control" id="capacity" placeholder="Enter new capacity" name="numberofdrivers" style="width:350px">
						</div>
						<div class="form-group">
				      			<label for="current_status">Change status</label>
							<div class="dropdown">
  										<button id="current_status" class="btn dropdown-toggle" type="button" data-toggle="dropdown" style="width:350px">Ready
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu" style="width:350px">
    											<li><a href="#">Not ready</a></li>
  										</ul>
									</div>
						</div>
						<div class="form-group">
				      			<label for="current_city">Change city</label>
							<div class="dropdown">
  										<button id="current_city" class="btn dropdown-toggle" type="button" data-toggle="dropdown" style="width:350px">Moscow
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu" style="width:350px">
    											<li><a href="#">Saint-Petersburg</a></li>
											<li><a href="#">Petrozavodsk</a></li>
    											<li><a href="#">Pskov</a></li>
    											<li><a href="#">Kazan</a></li>
  										</ul>
							</div>
						</div>
						
						<div class="form-group">
				      			<h2>Manage assigned drivers <button type="button" class="btn btn-primary">Add new</button></h2>
							<table class="table table-bordered table-active table-hover">
								<thead>
									<tr>
										<th></th>
										<th>Name</th>
										<th>Second name</th>
										<th>Last name</th>
										<th>Personal number</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<button type="button" class="btn btn-primary">Delete</button>
										</td>
										<td>Ivan</td>
										<td>Fedorovich</td>
										<td>Kot</td>
										<td>77475gsdg74</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="form-group">
				      			<label for="current_order">Change or <button type="button" class="btn btn-primary">delete</button> assigned order</label>
							<div class="dropdown">
  										<button id="current_order" class="btn dropdown-toggle" type="button" data-toggle="dropdown" style="width:350px">Order1
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu" style="width:350px">
    											<li><a href="#">Order2</a></li>
											<li><a href="#">Order3</a></li>
    											<li><a href="#">Order4</a></li>
    											<li><a href="#">Order5</a></li>
  										</ul>
									</div>
						</div>
						<div>
							<button type="submit" class="btn btn-primary">Save changes</button>
							<button type="submit" class="btn btn-primary">Rollback changes</button>
						</div>
					</form>
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
			    <li class="nav-item">
			    	<a class="nav-link" data-toggle="tab" href="#about">About</a>
 			    </li>
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

</body>
</html>
