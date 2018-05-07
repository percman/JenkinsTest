<html>
<head>
	<link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.css">
</head>
<body>
<h2>Hello World!</h2>
<form action="HelloWorldServlet" method="get">
	<input type="submit" value="service()">
</form>

	<div class="container">
		<div class="col-md-6">
			<form action="SecondServlet" method="get">
				<input type="text" name="getInput" placeholder="Enter some text">
				<input type="submit" class="btn btn-success" value="GET">
			</form>
		</div>
		<div class="col-md-6">
			<form action="SecondServlet" method="post">
				<input type="text" name="postInput" placeholder="Enter some more text">
				<input type="submit" class="btn btn-danger" value="POST">
			</form>
		</div>
	</div>
	
	<br><br><br><br><br><br>
	
	<div class="container">
		<div class="col-md-6 cold-md-offset-3">
			<form action="Logic.do" method="post">
				<div class="form-group">
					<input type="text" name="username" class="form-control" required placeholder="Type a username">
				</div>
				<div class="form-group">
					<input type="password" name="password" class="form-control" required placeholder="Type a password">
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
