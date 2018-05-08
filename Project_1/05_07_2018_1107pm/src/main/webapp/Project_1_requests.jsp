<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>Employee</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<%@ page import="com.revature.employee.Employee"%>
	<%
		Employee employee = (Employee) request.getSession().getAttribute("authorizedUser");
	%>
	<div class="container">
		<!-- the navbar class creates the navbar styling -->
		<nav class="navbar navbar-inverse">
			<div class="navbar-header col-md-3">
				<!-- Must use navbar-brand to make the style work -->
				<a href="./first_html.jsp" class="navbar-brand">Welcome <%=employee.getFirstname()%>!</a>
			</div>
			<ul class="navbar-nav nav col-md-4">
				<!-- to make the items appear as you want, put the <a> insde a <li> -->
				<li><a href="./home.jsp">Home</a></li>
				<li class="active"><a href="#">Requests</a></li>
				<li><a href="./Project_1_account.jsp">Account</a></li>
			</ul>
			<ul class="navbar-nav nav navbar-right col-md-2 col-md-offset-2">
				<li><a href="./index.jsp">Log Out <span class="glyphicon glyphicon-log-out"></span></a></li>
			</ul>
		</nav>
	</div>

	<!-- breadcrumb example to help show pagination -->
	<div class="container">
		<ol class="breadcrumb">
			<li class="breadcrumb-item">Home</li>
			<li class="breadcrumb-item active">Requests</li>
		</ol>
	</div>
</body>
</html>