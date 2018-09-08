<!DOCTYPE html>
<html lang="en">
<head>
  <title>Cargo change page</title>
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
			<a class="navbar-brand" href="#">Change cargo</a>
			<!-- Nav tabs -->
		</nav>
 
	</div>
	<br>
	<br>
	<div class = "container-fluid" >
		 <h2>Cargo cargo1954</h2>
			<div class="media" >
				<div class="media-left">
		      			<img src="img_avatar1.png" class="media-object" style="width:100px">
				</div>
				<div>
					<form action="#">
    						<div class="form-group">
					      		<label for="item_id">New cargo id:</label>
      							<input type="text" class="form-control" id="item_id" placeholder="Enter new cargo id" name="cargo_id" style="width:350px">
						</div>
						<div class="form-group">
					      		<label for="cargo_name">New cargo name</label>
      							<input type="text" class="form-control" id="cargo_name" placeholder="Enter new cargo name" name="cargo_name" style="width:350px">
						</div>
						<div class="form-group">
					      		<label for="weight">New weight</label>
      							<input type="text" class="form-control" id="weight" placeholder="Enter new cargo weight" name="cargo_weight" style="width:350px">
						</div>
						<div class="form-group">
				      			<label for="city_from">Change city from</label>
							<div class="dropdown">
  										<button id="city_from" class="btn dropdown-toggle" type="button" data-toggle="dropdown" style="width:350px">Moscow
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
				      			<label for="city_to">Change city to</label>
							<div class="dropdown">
  										<button id="city_to" class="btn dropdown-toggle" type="button" data-toggle="dropdown" style="width:350px">Moscow
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
				      			<label for="current_status">Change status</label>
							<div class="dropdown">
  										<button id="current_status" class="btn dropdown-toggle" type="button" data-toggle="dropdown" style="width:350px">Prepared
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu" style="width:350px">
    											<li><a href="#">Loaded</a></li>
											<li><a href="#">Unloaded</a></li>
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
