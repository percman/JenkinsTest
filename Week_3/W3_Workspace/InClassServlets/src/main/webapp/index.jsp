<html>
<head>
<link rel="stylesheet"
	href="webjars/bootstrap/3.3.7-1/css/bootstrap.css">
</head>

<body class="container">
	<div class="container col-md-6 col-md-offset-3">
		<div>
			<h2>Hello World!</h2>
			<form action="HelloWorldServlet" method="get">
				<input type="submit" value="service()">
			</form>
		</div>

		<div class="container col-md-6 col-md-offset-3">
			<form action="SecondServlet" method="get">
				<input type="text" name="getInput" placeholder="Enter some text!">
				<input type="submit" class="btn btn-success" value="GET">
			</form>
		</div>

		<div class="container col-md-6 col-md-offset-3">
			<form action="SecondServlet" method="post">
				<input type="text" name="postInput"
					placeholder="Enter some more text!"> <input type="submit"
					class="btn btn-danger" value="POST">
			</form>
		</div>
	</div>
	
	<div class="container">
		<div class="col-md-6 col-md-offset-3">
			<form action="login.do" method="post">
				<div>
					<input type="text" name="username" class="form-control" required placeholder="Username">
				</div>
				<div>
					<input type="password" name="password" class="form-control" required placeholder="Password">
				</div>
				<div class="button-group">
					<input type="submit" class="btn btn-success" value="Success">
					<input type="reset" class="btn btn-danger" value="Reset">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
