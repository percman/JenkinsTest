<html>
<head>
	<link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.css">
</head>
<body class="container">
<h2>Hello World!</h2>
<form action="HelloWorldServlet" method="get">
	<input type="submit" value="service()">
</form>

	<div class="container">
		<div class="col-md-6">
			<form action="SecondServlet" method="get">
				<input type="text" name="getInput" placeholder="Enter some text!">
				<input type="submit" class="btn btn-success" value="GET">
			</form>
		</div>
		<div class="col-md-6">
			<form action="SecondServlet" method="post">
				<input type="text" name="postInput" placeholder="Enter some text!">
				<input type="submit" class="btn btn-danger" value="POST">
			</form>
		</div>
	</div>


	<br><br><br><br>


	<div class="container">
	<h2>Login</h2>
		<div class="col-md-6 col-md-offset-3">
			<form action="login.do" method="post">
				<div class="form-group">
					<input type="text" name="username" class="form-control" required placeholder="Username">
				</div>
				<div class="form-group">
					<input type="password" name="password" class="form-control" required placeholder="Password">
				</div>
				<div class="button-group">
					<input type="submit" class="btn btn-success" value="Submit">
					<input type="reset" class="btn btn-danger" value="Reset">
				</div>
			</form>
		</div>	
	</div>











</body>
</html>
