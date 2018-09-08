<!DOCTYPE html>
<html lang="en">
<head>
  <title>Manager account</title>
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
			<a class="navbar-brand" href="#">Manager account</a>
			<!-- Nav tabs -->
			<ul class="nav nav-tabs bg-primary bg-light navbar-light" role="tablist">
			    <li class="nav-item">
			    	<a class="nav-link active" data-toggle="tab" href="#home">Home</a>
		    	    </li>
			    <li class="nav-item">
			    	<a class="nav-link" data-toggle="tab" href="#managetrucks">Manage trucks</a>
 			    </li>
			    <li class="nav-item">
				 <a class="nav-link" data-toggle="tab" href="#managedrivers">Manage drivers</a>
			    </li>   
			    <li class="nav-item">
				 <a class="nav-link" data-toggle="tab" href="#managecargos">Manage cargos</a>
			    </li> 
    			    <li class="nav-item">
      		      	    	<button type="button" class="btn btn-primary">Log out</button>
    			    </li>
		       </ul>

		</nav>


	</div>
	<br>
	<div class = "container-fluid">
		 <div class="tab-content">
  			<div id="home" class="tab-pane active ">
    				<div class = "sticky-top">
				<h3>Orders <button class = "btn btn-primary" type = "button">Add new</button> </h3>
				
    				
				<div><input class="form-control" id="myInput" type="text" placeholder="Search.."></div>
				</div>
				<div>
					<table id="myTable" class="table table-bordered table-active table-hover">
    						<thead>
      							<tr>
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
								<tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr>
								<tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr><tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr><tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr><tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr><tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr><tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr><tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr><tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr><tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr><tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr><tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr><tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr><tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr><tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr><tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr><tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr><tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr><tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr><tr>
									<td>8396hg834</td>
									<td>Descr</td>
									<td>11-12-2018 23-12-22</td>
									<td>Prepared</td>
									<td>ry448</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show assigned drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
									</td>
									<td>
										<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show route
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Moscow</a></li>
    													<li><a href="#">SPb</a></li>
    													<li><a href="#">Ptz</a></li>
  												</ul>
										</div>
									</td>
								</tr>
							</tbody>
    					
    					</table>
				</div>
				<br>


  			</div>
  			<div id="managetrucks" class="tab-pane fade">
    				<div class = "sticky-top">
				<h3>Trucks <button class = "btn btn-primary" type = "button">Add new</button></h3>
    				
				<div><input class="form-control" id="myInput2" type="text" placeholder="Search.."></div>
				</div>
				<div>
					<table id="myTable2" class="table table-bordered table-active table-hover">
    						<thead>
      							<tr>
								<th></th>
        							<th>Registration number</th>
        							<th>Number of drivers</th>
								<th>Capacity</th>
								<th>State</th>
        							<th>Current city</th>
								<th>Assigned drivers</th>
        							<th>Assigned order</th>
      							</tr>
						</thead>
						<tbody>
							
								
							
							<tr>
								<td>
								
									<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Manage
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Change</a></li>
    													<li><a href="#">Delete</a></li>
  												</ul>
									</div>
								
								</td>
        							<td>rr38473</td>
        							<td>3</td>
								<td>20</td>
								<td>Ready</td>
        							<td>Moscow</td>
								<td>
									<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show drivers
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Ivan</a></li>
    													<li><a href="#">Petr</a></li>
    													<li><a href="#">Sidor</a></li>
  												</ul>
										</div>
								</td>
        							<td>No assigned orders</td>
      							</tr>
						</tbody>
							
    					</table>
				</div>
  			</div>
  			<div id="managedrivers" class="tab-pane fade">
    				<div class = "sticky-top">
				<h3>Drivers <button class = "btn btn-primary" type = "button">Add new</button></h3>
    				
				<div><input class="form-control" id="myInput3" type="text" placeholder="Search.."></div>
				</div>
				<div>
					<table id="myTable3" class="table table-bordered table-active table-hover">
    						<thead>
      							<tr>
								<th></th>
        							<th>Name</th>
        							<th>Middle name</th>
								<th>Last name</th>
								<th>Personal number</th>
        							<th>Hours worked</th>
								<th>Status</th>
        							<th>Current city</th>
								<th>Current truck</th>
      							</tr>
						</thead>	
						<tbody>
							<td>
								<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Manage
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Change</a></li>
    													<li><a href="#">Delete</a></li>
  												</ul>
								</div>
							</td>
        							<td>Ivan</td>
        							<td>Petrovich</td>
								<td>Sidorov</td>
								<td>7748kshdg74</td>
        							<td>10</td>
								<td>Ready</td>
        							<td>Moscow</td>
								<td>rr48474</td>
						</tbody>				
    					</table>
				</div>
  			</div>
			<div id="managecargos" class="tab-pane fade">
    				<div class = "sticky-top">
				<h3>Cargos <button class = "btn btn-primary" type = "button">Add new</button></h3>
    				
				<div><input class="form-control" id="myInput4" type="text" placeholder="Search.."></div>
				</div>
				<div>
					<table id="myTable4" class="table table-bordered table-active table-hover">
    						<thead>
      							<tr>
								<th></th>
								<th>Item Id</th>
        							<th>Name</th>
        							<th>Weight</th>
								<th>City from</th>
								<th>City to</th>
								<th>Status</th>
      							</tr>
						</thead>	
						<tbody>
							<td>
								<div class="dropdown">
  											<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Manage
  												<span class="caret"></span></button>
  												<ul class="dropdown-menu">
    													<li><a href="#">Change</a></li>
    													<li><a href="#">Delete</a></li>
  												</ul>
								</div>
							</td>
								<td>7865hjkdfghk4</th>
        							<td>Beer</th>
        							<td>30</th>
								<td>Moscow</th>
								<td>Saint-Petersburg</th>
								<td>Prepared</th>
						</tbody>				
    					</table>
				</div>
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
</body>
</html>
