<html>
<head>
<link rel="stylesheet"
	href="webjars/bootstrap/3.3.7-1/css/bootstrap.css">
</head>
<body style="background-color: rgb(105, 10, 245)">
	<div class="container col-md-4 col-md-offset-4"
		style="margin-top: 150px">
		<form action="login.do" method="post"
			style="background-color: rgb(245, 245, 245, 0)">
			<div class="container">
				<h2 style="color: rgb(240, 230, 240)">User Login</h2>
			</div>
			<div class="form-group">
				<input type="text" name="username" class="form-control" required
					placeholder="Username">
			</div>
			<div class="form-group">
				<input type="password" name="password" class="form-control" required
					placeholder="Password">
			</div>
			<div class="form-group button-group">
				<button id="submitbtn" class="btn btn-primary form-control"
					type="submit" value="submit" style="background-color: rgb(85, 165, 225)">Log
					in</button>
			</div>
			<div class="form-group col-md-6">
				<p style="color: rgb(240, 230, 240)">
					New employee? <a href="#" style="color: rgb(240, 230, 240)">Sign
						up</a>
				</p>
			</div>
			<div class="form-group col-md-6">
				<p>
					<a href="#" style="color: rgb(240, 230, 240)">Forgot your
						password?</a>
				</p>
			</div>
		</form>
	</div>
</body>
</html>
