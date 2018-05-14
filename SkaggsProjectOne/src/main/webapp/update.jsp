<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Information</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<%@ page import="com.revature.dao.Employee"%>
	<%
		Employee employee = (Employee) request.getSession().getAttribute("authorizedUser");
	%>
	<div class="container">
		<div class="container">
			<nav class="navbar navbar-inverse">
			<div class="navbar-header col-md-2">
				<a href="./index.jsp" class="navbar-brand">Skaggs Corp</a>
			</div>
			<ul class="navbar-nav nav">
				<li><a href="home.jsp">Home</a></li>
			</ul>
			<ul class="navbar-nav nav">
				<li><a href="update.jsp">Update Info</a></li>
			</ul>
			<ul class="navbar-nav nav">
				<li><a href="requestEmployee.jsp">View Reimbursement
						Requests</a></li>
			</ul>
			<ul class="navbar-nav nav navbar-right col-md-2 col-md-offset-2">
				<li><a href="logout.jsp">Log Out <span
						class="glyphicon glyphicon-log-out" id="logout-elmt"> </span></a></li>
			</ul>
			</nav>
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<div class="panel panel-login">
						<div class="panel-heading">
							<h1>
								<center>Update Information</center>
							</h1>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-12">
									<div class="form-group">
										<div class="row">
											<form id="login-form" action="update.do" method="post"
												role="form">
												<div class="col-sm-6 col-sm-offset-3">
													<input type="text" name="firstname" id="firstname"
														class="form-control"
														placeholder=<%=employee.getFirstName()%>> <input
														type="text" name="lastname" id="lastname"
														class="form-control"
														placeholder=<%=employee.getLastName()%>> <input
														type="password" name="password" id="password"
														class="form-control" placeholder="Password"> <input
														type="submit" name="update-submit" id="update-btn"
														class="form-control btn btn-submit" value="Update Info">
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
	</div>
</body>
</html>