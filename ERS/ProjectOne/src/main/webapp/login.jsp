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


	<!-- Main Navbar -->
	<div class="container">
		<nav class="navbar navbar-inverse">
			<div class="navbar-header col-md-2 active">
				<a href="./index.jsp" class="navbar-brand">ERS</a>
			</div>
			<ul class="navbar-nav nav col-md-6">
				<li><a href="./jsp/home.jsp">Home</a></li>
				<li><a href="./jsp/about.jsp">About</a></li>
				<li><a href="./jsp/services.jsp">Services</a></li>
				<li><a href="./jsp/contact.jsp">Contact</a></li>
			</ul>

			<ul class="navbar-nav nav navbar-right col-md-2 col-md-offset-2">
				<li><a href="./jsp/login.jsp">Log In <span
						class="glyphicon glyphicon-log-in"></span>
				</a></li>
			</ul>
		</nav>
	</div>

	<!-- Breadcrumb -->
	<div class="container">
		<ol class="breadcrumb">

		</ol>
	</div>

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