<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager Home</title>
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
	<%@ page import="com.revature.manager.Manager"%>
	<%
		Manager manager = (Manager) request.getSession().getAttribute("authorizedUser");
	%>
	<div class="container">
		<!-- the navbar class creates the navbar styling -->
		<nav class="navbar navbar-inverse">
		<div class="navbar-header col-md-3">
			<!-- Must use navbar-brand to make the style work -->
			<a href="./first_html.html" class="navbar-brand">Welcome Manager
				<%=manager.getFirstname()%>!
			</a>
		</div>
		<ul class="navbar-nav nav col-md-5">
			<!-- to make the items appear as you want, put the <a> insde a <li> -->
			<li class="active"><a href="./Project_1_ManagerHome.jsp">Home</a></li>
			<li><a href="./Project_1_ManagerManagement.jsp">Management</a></li>
			<li><a href="./Project_1_ManagerRequests.jsp">My Requests</a></li>
			<li><a href="./Project_1_ManagerAccount.jsp">My Account</a></li>
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
			<li class="breadcrumb-item">Manager Home</li>
		</ol>
	</div>
	
	<div class="container col-md-10 col-md-offset-1">
  		<div class="jumbotron" style="background-color: rgba(245, 245, 245, .2)">
    		<h1>Welcome <%=manager.getFirstname()%>!</h1> 
    		<p>There will be a meeting with the VP at 4pm Thursday. DON'T BE LATE. I'M TALKING TO YOU CAMERON</p> 
      		<p>Associates will turn in project 1 at 12 am on 5/14/2018. Tell Jesse his is the coolest</p> 
  			<p>The best donuts will be kept in the back. Don't miss out</p> 
  		</div>
  </div>
</body>
</html>