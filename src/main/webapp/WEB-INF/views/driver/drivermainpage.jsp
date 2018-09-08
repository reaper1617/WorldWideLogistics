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



	<div class = "container-fluid fixed-top">
		<h2>Fixed-top container</h2>
		<nav class="nav nav-tabs bg-primary bg-light navbar-light fixed-top">
			<a class="navbar-brand" href="#">Driver account</a>
			<!-- Nav tabs -->
			<ul class="nav nav-tabs bg-primary bg-light navbar-light" role="tablist">
    			    <li class="nav-item">
      		      	    	<button type="button" class="btn btn-primary">Log out</button>
    			    </li>
		       </ul>

		</nav>
 
	</div>
	<br>
	<br>
	<div class = "container-fluid">
		 <h2>Ivanov Petr Sidorovich</h2>
			<div class="media">
				<div class="media-left">
		      			<img src="img_avatar1.png" class="media-object" style="width:60px">
				</div>
				<div class="media-body">
					<h3>Driver details</h3>
					<div>
					<table class="table table-bordered table-active table-hover">
						<tbody>
							<tr>
								<td>Personal number:</td>
								<td> 787f78fj7</td>
								
							</tr>
							<tr>
								<td>Current city</td>
							
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Moscow
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Saint-Petersburg</a></li>
											<li><a href="#">Petrozavodsk</a></li>
    											<li><a href="#">Pskov</a></li>
    											<li><a href="#">Kazan</a></li>
  										</ul>
									</div>
								</td>
							</tr>
							<tr>
								<td>Current status:</td>
							
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Resting
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Driving</a></li>
											<li><a href="#">Second driver</a></li>
    											<li><a href="#">Loading/unloading works</a></li>
    											<li><a href="#">Free</a></li>
  										</ul>
									</div>
								</td>
							</tr>
							<tr>
								<td>Assistants</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show drivers-assistants
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Ivan</a></li>
    											<li><a href="#">Petr</a></li>
    											<li><a href="#">Sidor</a></li>
  										</ul>
									</div>
								</td>
								
							</tr>
							<tr>
								<td>Current truck</td>
								<td>rr37373</td>
								
							</tr>
							<tr>
								<td>Current order</td>
								<td>sjbdgksbg743</td>
								
							</tr>
							<tr>
								<td>Current order state</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Prepared
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Loaded</a></li>
    											<li><a href="#">Shipped</a></li>
    											<li><a href="#">Delivered</a></li>
  										</ul>
									</div>
								</td>
							</tr>
							<tr>
								<td>Route</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Moscow</a></li>
    											<li><a href="#">Petrozavodsk</a></li>
    											<li><a href="#">Sankt-Peterburg</a></li>
  										</ul>
									</div>	
								</td>
								
							</tr>
						</tbody>
					</table>
					</div>
					<div>
						<div class="sticky-top">
							<h3>Order cargo details</h3>
							<div><input class="form-control" id="myInput" type="text" placeholder="Search.."></div>
						</div>
						<table id="myTable" class="table table-bordered table-active table-hover">
							<tr>
								<th>Cargo</th>
								<th>City from</th>
								<th>City to</th>
								<th>Cargo state</th>
							</tr>
							<tr>
								<td>Beer</td>
								<td>Moscow</td>
								<td>Saint-Petersburg</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Prepared
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Loaded</a></li>
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
							<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>

<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>
<tr>
								<td>Vodka</td>
								<td>Petrozavodsk</td>
								<td>Moscow</td>
								<td>
									<div class="dropdown">
  										<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Loaded
  										<span class="caret"></span></button>
  										<ul class="dropdown-menu">
    											<li><a href="#">Unloaded</a></li>
  										</ul>
									</div>
								</td>
							</tr>

						</table>
						<br>
					</div>
			
					
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
