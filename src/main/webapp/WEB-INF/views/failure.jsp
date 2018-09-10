<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" >
<head>
  <title>Action failed!</title>
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
<div class="gradientstyle">

	<div class="container-fluid">

		<div class = "container-fluid fixed-top">

		</div>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<div class = "container-fluid">
			<div align="center" >
				<h1 class="text-primary">Action failed!</h1>
				<br>
				<c:if test="${actionFailed != null}">
					<h1>${actionFailed}</h1>
				</c:if>
				<c:if test="${actionFailed == null}">
					<h1>Whatever you did, don't do it again! :( </h1>
				</c:if>
			</div>
		</div>
		<div class = "container-fluid fixed-bottom">
			<h5 align="center">WorldWideLogistics 2018</h5>
		</div>
	</div>
</div>
</body>
</html>
