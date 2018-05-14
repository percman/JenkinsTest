<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Home</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<style>
p {
	font-size: 20px;
	font-weight: bold;
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
			<li class="active"><a href="./home.jsp">Home</a></li>
			<li><a href="./Project_1_requests.jsp">My Requests</a></li>
			<li><a href="./Project_1_account.jsp">My Account</a></li>
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
		</ol>
	</div>
	
	<div class="container col-md-10 col-md-offset-1">
  <div class="jumbotron" style="background-color: rgba(245, 245, 245, .2)">
    <h1>Welcome <%=employee.getFirstname()%>!</h1> 
    	<p>There will be donuts in the employee lounge after 3pm today. Don't forget to submit your time sheets!</p> 
      	<p>Project 1 due 5/15/2018 @ 12:00 am</p> 
  		<p>Presentations will be that morning</p> 
  </div>
</div>
</body>
</html>