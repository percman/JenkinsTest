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
			<div class="page-header">
				<h2 class="col-md-offset-4">
					Hello,
					<%=employee.getFirstname().substring(0, 1).toUpperCase()%><%=employee.getFirstname().substring(1)%>
					<%=employee.getLastname().substring(0, 1).toUpperCase()%><%=employee.getLastname().substring(1)%>.
				</h2>
			</div>
			<div>
				<p class="col-md-4 col-md-offset-5">
					Your ID:
					<%=employee.getId()%>
				</p>
				<p class="col-md-4">
					Your User Name:
					<%=employee.getUsername()%>
				</p>
				<p class="col-md-4">
					Phone Number:
					<%=employee.getPhonenumber()%>
				</p>
				<p class="col-md-4">
					Email:
					<%=employee.getEmail()%>
				</p>
			</div>
			
		<button type="button" class="btn btn-info" onclick="hideButton()">Update this information?</button>
		<div id="infoUpdateButton" style="display: none;">
			<h2>Update your information: </h2>
			<form action="update.do" method="post">
				<div class="form-group">
					<input type="text" name="updateusername" class="form-control" 
						placeholder="Username">
				</div>
				<div class="form-group">
					<input type="password" name="updatepassword" class="form-control"
						 placeholder="Password">
				</div>
				<div class="form-group">
					<input type="text" name="updatefirstname" class="form-control"
						 placeholder="First name">
				</div>	
				<div class="form-group">
					<input type="text" name="updatelastname" class="form-control"
						 placeholder="Last name">
				</div>				
				<div class="form-group">
					<input type="text" name="updateemail" class="form-control"
						 placeholder="Email">
				</div>	
				<div class="form-group">
					<input type="phonenumber" name="updatephonenumber" class="form-control"
						 placeholder="Phone number">
				</div>					
				<div class="button-group">
					<input type="submit" class="btn btn-success" value="Submit">
					<input type="reset" class="btn btn-danger" value="Reset">
				</div>
			</form>
		</div>
			
		</div>
	</div>
	<div class="container">
		<a class="btn btn-default" href="./reimbursementpage.jsp" role="button">Reimbursements</a>
		<a class="btn btn-default" href="./employeelist.jsp" role="button">Employee Info</a>
	</div>
	
	
	<script src="./js/hidebutton.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>