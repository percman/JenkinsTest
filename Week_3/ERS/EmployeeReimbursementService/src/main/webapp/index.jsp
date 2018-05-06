<html>
<body>
<h2>Hello World!</h2>

<div class="container">
	<div class="col-md-6 col-offset-3">
		<form action="login.do" method="post">
			<div class = "form-group">
				<input type = "text" name = "username" class="form-control" required placeholder="Username">
			</div>
			<div class = "form-group">
				<input type = "password" name = "password" class="form-control" required placeholder="Password">
			</div>
			<div class = "button-group">
				<input type = "submit" class="btn btn-success" value="Submit">
				<input type = "reset" class = "btn btn-danger" value="Reset">
			</div>
		</form>
	</div>
</div>
</body>
</html>
