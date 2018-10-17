<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Google maps page</title>
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

		#map {
			height: 100%;
		}
		html, body {
			height: 100%;
			margin: 0;
			padding: 0;
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
					<a class="nav-link active" data-toggle="tab" href="#manageorders">Google view</a>
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
		 <h1>GOOGLE VIEW</h1>
		<div id = respFromGoogle></div>
		<div id="googleMap" style="width:100%;height:500px;"></div>



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

<script src="/resources/web/js/googlemaps.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC3u4AscXSUnSsUvydrR1K1MbN8oJgJo_8&callback=myMap"></script>
</body>
</html>
