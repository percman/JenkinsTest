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
<link rel="stylesheet" href="../css/stylesheet.css" class="styleseet">
</head>



<body>

	<%@ page import="com.revature.model.Employee"%>
	<%@ page import="com.revature.factory.Reimbursement"%>
	<%@ page import="com.revature.daoservice.EmployeeDaoService"%>
	<%@ page import="com.revature.daoservice.ReimbursementDaoService"%>
	<%@ page import="java.util.List"%>

	<%
		Employee employee = (Employee) request.getSession().getAttribute("authorizedUser");
		List<Employee> employeelist = EmployeeDaoService.getAllEmployees();
		List<Employee> managerlist = EmployeeDaoService.getAllManagers();
		List<Reimbursement> reimbursementlist = ReimbursementDaoService.getAllReimbursements();
		List<Reimbursement> reimbursementlistp = ReimbursementDaoService.getPendingReimbursements();
		List<Reimbursement> reimbursementlista = ReimbursementDaoService.getApprovedReimbursements();
		List<Reimbursement> reimbursementlistr = ReimbursementDaoService.getRejectedReimbursements();
	%>
	<!-- Main Navbar -->
	<div class="container">
		<nav class="navbar navbar-inverse">
			<div class="navbar-header col-md-2">
				<a href="../index.jsp" class="navbar-brand">ERS</a>
			</div>
			<ul class="navbar-nav nav col-md-6">
				<li class="active"><a href="./home.jsp">Home</a></li>
				<li><a href="./about.jsp">About</a></li>
				<li><a href="./services.jsp">Services</a></li>
				<li><a href="./contact.jsp">Contact</a></li>
			</ul>
		</nav>
	</div>



	<!-- Breadcrumb -->
	<div class="container">
		<ol class="breadcrumb">

		</ol>
	</div>
	
	
	
	
	<div class="container well">
		<div class="container col-md-10 col-md-offset-1">
			<div class="col-md-4 col-md-offset-1">
				<h2>Here is a list of employees:</h2>
				<ul class="list-group">
					<%
						for (Employee e : employeelist) {
					%>
					<li class="list-group-item">
						<h3 class="list-group-item-heading">
							ID:
							<%=e.getId()%></h3>
						<h4 class="list-group-item-text">
							User Name:
							<%=e.getUsername()%></h4>
						<h4 class="list-group-item-text">
							First name:
							<%=e.getFirstname().substring(0, 1).toUpperCase()%><%=e.getFirstname().substring(1)%></h4>
						<h4 class="list-group-item-text">
							Last name:
							<%=e.getLastname().substring(0, 1).toUpperCase()%><%=e.getLastname().substring(1)%></h4>
						<h4 class="list-group-item-text">
							Phone number:
							<%=e.getPhonenumber()%></h4>
						<h4 class="list-group-item-text">
							Email:
							<%=e.getEmail()%></h4>
					</li>
					<%
						}
					%>
				</ul>
			</div>
			<div class="col-md-4 col-md-offset-1">
				<h2>Here is a list of managers:</h2>
				<ul class="list-group">
					<%
						for (Employee e : managerlist) {
					%>
					<li class="list-group-item">
						<h3 class="list-group-item-heading">
							ID:
							<%=e.getId()%></h3>
						<h4 class="list-group-item-text">
							User Name:
							<%=e.getUsername()%></h4>
						<h4 class="list-group-item-text">
							First name:
							<%=e.getFirstname().substring(0, 1).toUpperCase()%><%=e.getFirstname().substring(1)%></h4>
						<h4 class="list-group-item-text">
							Last name:
							<%=e.getLastname().substring(0, 1).toUpperCase()%><%=e.getLastname().substring(1)%></h4>
						<h4 class="list-group-item-text">
							Phone number:
							<%=e.getPhonenumber()%></h4>
						<h4 class="list-group-item-text">
							Email:
							<%=e.getEmail()%></h4>
					</li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
	</div>
	
	
	<script src="./js/hidebutton.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>
	