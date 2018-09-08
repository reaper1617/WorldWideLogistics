<!DOCTYPE html>
<html lang="en">
<head>
  <title>Add new driver page</title>
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
			<a class="navbar-brand" href="#">Change driver</a>
			<!-- Nav tabs -->
		</nav>
 
	</div>
	<br>
	<br>
	<div class = "container-fluid" >
		
			<div class="media" >
				<div class="media-left">
		      			<img src="img_avatar1.png" class="media-object" style="width:100px">
				</div>
				<div>
					<form action="#">
						<div class="form-group">
					      		<label for="driver_name">Name:</label>
      							<input type="text" class="form-control" id="driver_name" placeholder="Enter driver name" name="name" style="width:350px" required="required">
						</div>
						<div class="form-group">
					      		<label for="driver_middle_name">Middle name:</label>
      							<input type="text" class="form-control" id="driver_middle_name" placeholder="Enter middle name" name="middlename" style="width:350px" required="required">
						</div>
						<div class="form-group">
					      		<label for="driver_last_name">Middle name:</label>
      							<input type="text" class="form-control" id="driver_last_name" placeholder="Enter last name" name="lastname" style="width:350px" required="required">
						</div>
						<div class="form-group">
					      		<label for="hours_worked">Hours worked:</label>
      							<input type="text" class="form-control" id="hours_worked" placeholder="Enter number of hours worked" name="hoursworked" style="width:350px" required="required">
						</div>
						<div class="form-group">
							<label for="driver_current_city">Current city</label>
							 <select class="form-control" id="driver_current_city" required="required">
        							<option>City1</option>
        							<option>City2</option>
        							<option>City3</option>
        							<option>City4</option>
        							<option>City5</option>
      							</select>
						</div>
						<div class="form-group">
							<label for="driver_current_truck">Assign truck:</label>
							 <select class="form-control" id="driver_current_truck">
        							<option>tt48574</option>
        							<option>ur38475</option>
        							<option>fj39485</option>
      							</select>
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
