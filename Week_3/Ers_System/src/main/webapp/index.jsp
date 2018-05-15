<html>
<head>
<link rel="stylesheet"
	href="webjars/bootstrap/3.3.7-1/css/bootstrap.css">
</head>
<body>
	<h2>ERS SYSTEM!</h2>

	<div class="container">
		<div class="cod-md-6 col-md-offset-3">
			<form action="login.do" method="post">
				<div class="form-group">
					<input type="text" name="username" class="form-control" required
						placeholder="EmployeeName">
				</div>

				<div class="form-group">
					<input type="password" name="password" class="form-control"
						required placeholder="Employee Password">
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
