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
	
	
	
	
	
	
	
	
	<div class="container well col-md-10 col-md-offset-1">
		<div class="col-md-8 col-md-offset-1">
			<h2>Add a new reimbursement:</h2>
			<form action="rewreimbursement.do" method="post">
				<div class="form-group">
					    <label for="categoryselect">Category</label>
					    <select class="form-control" name="categoryselect" id="categoryselect" required>
					      <option>Food</option>
					      <option>Lodging</option>
					      <option>Travel</option>
					      <option>Other</option>
					    </select>
				</div>
				<div class="form-group">
				
				</div>
				<div class="form-group">
					<label for="reimbursementamount">Enter the amount: </label> <input
						type="number" step=".01" name="reimbursementamount" class="form-control"
						required>
				</div>
				<div class="form-group">
					<label for="receiptimage">Image of receipt</label>
				    <input type="file" class="form-control-file" id="receiptimage">
				</div>
				<input type="submit" value="Submit Form">
			</form>
		</div>
		<div class="col-md-4 col-md-offset-1">
			<h2>Here is a list of all reimbursements:</h2>
			<ul class="list-group">
				<%
					for (Reimbursement r : reimbursementlist) {
				%>
				<li class="list-group-item">
					<h3 class="list-group-item-heading">
						Category:
						<%=r.getCategory()%></h3>
					<h4 class="list-group-item-text">
						Requestor ID:
						<%=r.getRequestor_id()%></h4> <%
 	if (r.getStatus().equals("pending")) {
 %>
					<h4 class="list-group-item-text">
						Approved Status:
						<%=r.getStatus()%></h4>
					<h4 class="list-group-item-text">
						Time Made:
						<%=r.getTimemade().toGMTString()%></h4> <%
 	} else {
 %>
					<h4 class="list-group-item-text">
						Approver ID:
						<%=r.getApprover_id()%></h4>
					<h4 class="list-group-item-text">
						Approved Status:
						<%=r.getStatus()%></h4>
					<h4 class="list-group-item-text">
						Time Made:
						<%=r.getTimemade().toGMTString()%></h4>
					<h4 class="list-group-item-text">
						Time Approved:
						<%=r.getTimeapproved().toGMTString()%></h4> <%
 	}
 %>
				</li>
				<%
					}
				%>
			</ul>
		</div>
		<div class="col-md-4 col-md-offset-1">
			<h2>Please note, if you made a request,you cannot approve it.</h2>
		</div>
		<div class="col-md-4 col-md-offset-0">
			<h2>Here is a list of pending reimbursements:</h2>
			<ul class="list-group">
				<%
					for (Reimbursement r : reimbursementlistp) {
				%>
				<li class="list-group-item">
					<h3 class="list-group-item-heading">
						Category:
						<%=r.getCategory()%></h3>
					<h4 class="list-group-item-text">
						ID:
						<%=r.getId()%></h4>
					<h4 class="list-group-item-text">
						Requestor ID:
						<%=r.getRequestor_id()%></h4>
					<h4 class="list-group-item-text">
						Approved Status:
						<%=r.getStatus()%></h4>
					<h4 class="list-group-item-text">
						Time Made:
						<%=r.getTimemade().toGMTString()%></h4>
				</li>
				<div>
				<% if(r.getRequestor_id() != employee.getId()){ %>
					<form action="/ProjectOneWeb/ReimbursementApprovalServlet"
						method="POST">
						<input type="checkbox" name="approval"
							value=<%="approved" + r.getId() + r.getCategory()%>>
						<label for="Approve">Approve</label> <input type="checkbox"
							name="approval"
							value=<%="rejected" + r.getId() + r.getCategory()%>>
						<label for="Reject">Reject</label>
						<% } %>
				</div>
				<%
					}
				%>
			</ul>
			<input type="submit" value="Submit Form">
			</form>


		</div>
		<div class="col-md-4 col-md-offset-0">
			<h2>Here is a list of approved reimbursements:</h2>
			<ul class="list-group">
				<%
					for (Reimbursement r : reimbursementlista) {
				%>
				<li class="list-group-item">
					<h3 class="list-group-item-heading">
						Category:
						<%=r.getCategory()%></h3>
					<h4 class="list-group-item-text">
						Requestor ID:
						<%=r.getRequestor_id()%></h4>
					<h4 class="list-group-item-text">
						Approver ID:
						<%=r.getApprover_id()%></h4>
					<h4 class="list-group-item-text">
						Approved Status:
						<%=r.getStatus()%></h4>
					<h4 class="list-group-item-text">
						Time Made:
						<%=r.getTimemade().toGMTString()%></h4>
					<h4 class="list-group-item-text">
						Time Approved:
						<%=r.getTimeapproved().toGMTString()%></h4>
				</li>
				<%
					}
				%>
			</ul>
		</div>
		<div class="col-md-4 col-md-offset-0">
			<h2>Here is a list of rejected reimbursements:</h2>
			<ul class="list-group">
				<%
					for (Reimbursement r : reimbursementlistr) {
				%>
				<li class="list-group-item">
					<h3 class="list-group-item-heading">
						Category:
						<%=r.getCategory()%></h3>
					<h4 class="list-group-item-text">
						Requestor ID:
						<%=r.getRequestor_id()%></h4>
					<h4 class="list-group-item-text">
						Approver ID:
						<%=r.getApprover_id()%></h4>
					<h4 class="list-group-item-text">
						Approved Status:
						<%=r.getStatus()%></h4>
					<h4 class="list-group-item-text">
						Time Made:
						<%=r.getTimemade().toGMTString()%></h4>
					<h4 class="list-group-item-text">
						Time Approved:
						<%=r.getTimeapproved().toGMTString()%></h4>
				</li>
				<%
					}
				%>
			</ul>
		</div>
	</div>



	<script src="./js/hidebutton.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>