<html>
<head>
	<title>Skaggs Corp</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<div class="container">
	<nav class="navbar navbar-inverse">
        <div class="navbar-header col-md-2">
            <a href="./index.html" class="navbar-brand">Skaggs Corp</a>
        </div>
    </nav>
     	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<h1><center>Sign In</center></h1>
					</div>

					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
									<div class="form-group">
										<div class="row">
											<form id="login-form" action="login.do" method="post" role="form">
												<div class="col-sm-6 col-sm-offset-3">
													<input type="text" name="username" id="username" class="form-control" placeholder="Username">
													<input type="password" name="password" id="password" class="form-control"placeholder="Password">
													<input type="submit" name="login-submit" id="login-btn" class="form-control btn btn-submit"  value="Log In">
												</div>
											</form>
										</div>
									</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</div>
</body>
</html>
