<!DOCTYPE html>
<html lang="en">
<head>
  <title>Add new order page</title>
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
			<a class="navbar-brand" href="#">Add new order</a>
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
					      		<label for="item_descr">New order description:</label>
      							<input type="text" class="form-control" id="item_descr" placeholder="Enter order description" name="order_descr" style="width:350px" required="required">
						</div>
						<div class="form-group">
							<label for="add_cargos">Add cargos</label>
							 <select multiple class="form-control" id="add_cargos" size="10" required="required">
        							<option>1</option>
        							<option>2</option>
        							<option>3</option>
        							<option>4</option>
        							<option>5</option>
      							</select>
						</div>
						<div class="form-group">
							<label for="assign_truck">Assign truck</label>
							 <select class="form-control" id="assign_truck">
        							<option>rr44443</option>
        							<option>re45453</option>
        							<option>3</option>
        							<option>4</option>
        							<option>5</option>
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


</body>
</html>
