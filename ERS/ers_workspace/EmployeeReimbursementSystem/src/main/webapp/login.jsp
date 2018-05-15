<!DOCTYPE html>
<html>

<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>

    <div class="container" disabled>
        <div class="jumbotron">
            <h1>Login Page.</h1>
        </div>
        <br>
        <form action="login.do" method="POST">
            <label for="username">Username:</label>
            <br>
            <input type="text" name="username" id="username" required placeholder="Please enter username">
            <br>
            <br>
            <label for="password">Password:</label>
            <br>
            <input type="text" name="password" id="password" required placeholder="Please enter password">
            <br>
            <br>
            <input id="loginBtn" type="submit" value="Login">
        </form>

    </div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>