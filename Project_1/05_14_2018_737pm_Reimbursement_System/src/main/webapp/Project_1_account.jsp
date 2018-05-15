<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>Employee</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<style>
p {
	font-size: 20px;
	font-weight: bold;
}

#hidden_id {
	display: none;
}

</style>
</head>
<body style="background-color: rgb(105, 120, 245)">
	<%@ page import="com.revature.employee.Employee"%>
	<%
		Employee employee = (Employee) request.getSession().getAttribute("authorizedUser");
	%>
	<div class="container">
		<!-- the navbar class creates the navbar styling -->
		<nav class="navbar navbar-inverse">
			<div class="navbar-header col-md-3">
				<!-- Must use navbar-brand to make the style work -->
				<a href="./first_html.html" class="navbar-brand">Welcome <%=employee.getFirstname()%>!
				</a>
			</div>
			<ul class="navbar-nav nav col-md-4">
				<!-- to make the items appear as you want, put the <a> insde a <li> -->
				<li><a href="./home.jsp">Home</a></li>
				<li><a href="./Project_1_requests.jsp">My Requests</a></li>
				<li class="active"><a href="#">My Account</a></li>
			</ul>
			<ul class="navbar-nav nav navbar-right col-md-2 col-md-offset-2">
				<li><a href="./index.jsp">Log Out <span
						class="glyphicon glyphicon-log-out"></span></a></li>
			</ul>
		</nav>
	</div>

	<!-- breadcrumb example to help show pagination -->
	<div class="container">
		<ol class="breadcrumb">
			<li class="breadcrumb-item">Home</li>
			<li class="breadcrumb-item active">Account</li>
		</ol>
	</div>

	<div class="container well"
		style="background-color: rgba(245, 245, 245, .2)">
		<div class="container col-md-5 col-md-offset-1">
			<div class="page-header">
				<h2 class="col-md-offset-2">Employee information</h2>
			</div>
			<div>
				<p class="col-md-offset-2">
					User Name:
					<%=employee.getUsername()%></p>
			</div>
			<div>
				<p class="col-md-offset-2">
					Employee ID: #<%=employee.getId()%></p>
			</div>
			<div>
				<p class="col-md-offset-2">
					First name:
					<%=employee.getFirstname()%></p>
			</div>
			<div>
				<p class="col-md-offset-2">
					Last name:
					<%=employee.getLastname()%></p>
			</div>
		</div>
		<div class="container col-md-5">
			<div class="page-header">
				<h2 class="col-md-offset-2">Change Account Info</h2>
			</div>
			<form action="update.do" method="post">
				<div>
					<input class="form-control" id="hidden_id" type="text" name="employee_id"
						required value=<%=employee.getId()%>>
				</div>
				<div class="form-group col-md-9 col-md-offset-1">
					<input class="form-control" type="text" name="employee_username"
						required value=<%=employee.getUsername()%>>
				</div>
				<div class="form-group col-md-9 col-md-offset-1">
					<input class="form-control" type="text" name="employee_firstname"
						required value=<%=employee.getFirstname()%>>
				</div>
				<div class="form-group col-md-9 col-md-offset-1">
					<input class="form-control" type="text" name="employee_lastname"
						required value=<%=employee.getLastname()%>>
				</div>
				<div class="form-group col-md-9 col-md-offset-1">
					<input class="form-control" type="password" name="employee_password"
						required value=<%=employee.getPassword()%>>
				</div>
				<div class="button-group col-md-9 col-md-offset-1">
					<input class="btn btn-info" type="submit"
						name="employee_password_submit" value="Update Account"
						style="background-color: rgb(85, 165, 225)">
				</div>
			</form>
		</div>
	</div>
</body>
</html>