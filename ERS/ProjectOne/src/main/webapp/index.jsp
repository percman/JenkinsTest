<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Project One Welcomepage</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet" href="./css/stylesheet.css" class="styleseet">
</head>

<body>
	<%
		request.getSession().getAttribute("");
	%>


	<div class="container">
		<div class="col-md-6 col-md-offset-3">
			<h2>Login</h2>
			<form action="login.do" method="post">
				<div class="form-group">
					<input type="text" name="username" class="form-control" required
						placeholder="Username">
				</div>
				<div class="form-group">
					<input type="password" name="password" class="form-control"
						required placeholder="Password">
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