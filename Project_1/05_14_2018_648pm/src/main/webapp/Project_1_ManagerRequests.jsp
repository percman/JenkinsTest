<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager Requests</title>
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
			<a href="./first_html.jsp" class="navbar-brand">Welcome Manager <%=manager.getFirstname()%>!
			</a>
		</div>
		<ul class="navbar-nav nav col-md-5">
			<!-- to make the items appear as you want, put the <a> insde a <li> -->
			<li><a href="./Project_1_ManagerHome.jsp">Home</a></li>
			<li><a href="./Project_1_ManagerManagement.jsp">Management</a></li>
			<li class="active"><a href="./Project_1_ManagerRequests.jsp">My Requests</a></li>
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
			<li class="breadcrumb-item active">Requests</li>
		</ol>
	</div>

	<div class="container col-md-10 col-md-offset-1 well"
		style="background-color: rgba(245, 245, 245, .2)">
		<div class="container col-md-5 col-md-offset-1">
			<div class="page-header">
				<h2 class="col-md-offset-2">View My Requests</h2>
			</div>
			<div>
				<p>
					View by: <select class="btn btn-info" required name="request"
						id="requestSelector">
						<option value="4">All Requests</option>
						<option value="0">Pending Requests</option>
						<option value="1">Approved Requests</option>
						<option value="-1">Denied Requests</option>
					</select> <input class="btn btn-info" type="submit" id="getRequests"
						value="View Requests!" style="background-color: rgb(85, 165, 225)">
				</p>
			</div>
			<table class="table table-responsive">
				<thead>
					<tr>
						<th>Category</th>
						<th>Status</th>
						<th>Amount</th>
						<th>Message</th>
						<th>Request Date</th>
					</tr>
				</thead>
				<tbody id="requestsTable">

				</tbody>
			</table>
		</div>
		<div class="container col-md-5">
			<div class="page-header">
				<h2 class="col-md-offset-2">Submit New Request</h2>
			</div>
			<form action="submitManagerRequest.do" method="post">
				<div class="form-group col-md-9 col-md-offset-1">
					<div>
						<p>
							Reimbursement: <select class="btn btn-info" required
								name="request">
								<option value="4">Travel</option>
								<option value="1">Food</option>
								<option value="2">Lodging</option>
								<option value="3">Other</option>
							</select>
						</p>
					</div>
				</div>
				<div>
					<input class="form-control" id="hidden_id" type="text"
						name="employee_id" required value=<%=manager.getId()%>>
				</div>
				<div class="form-group col-md-9 col-md-offset-1">
					<input class="form-control" type="text" name="amount" required
						placeholder="amount">
				</div>
				<div class="form-group col-md-9 col-md-offset-1">
					<input class="form-control" type="text" name="message" required
						placeholder="Request details (limit to 100 characters)">
				</div>
				<div class="form-group col-md-9 col-md-offset-1">
					<input class="form-control" type="file" name="file">
				</div>
				<div class="button-group col-md-9 col-md-offset-1">
					<input class="btn btn-info" type="submit"
						name="employee_request_submit" value="Submit Request"
						style="background-color: rgb(85, 165, 225)">
				</div>
			</form>
		</div>
	</div>
	<script src="./ReimbursementJSON.js" type="text/javascript"></script>
</body>
</html>