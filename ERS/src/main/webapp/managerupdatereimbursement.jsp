<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Manager" %>
<%@ page import="model.Employee" %>
<%@ page import="model.Reimbursement" %>
<%@ page import="java.util.List"%>
<% Manager manager = (Manager) request.getSession().getAttribute("manager"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager: reimbursements</title>
</head>
<body>
<div class='container'>
	<h2>Manager review reimbursements</h2>
	<a href='manager-home.do'>Home</a>
	<div id='buttons'>
		<button id='all'>all</button>
		
	</div>
	
	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>Employee</th>
				<th>Manager</th>
				<th>Category</th>
				<th>Image</th>
				<th>Status</th>
				<th>Approve</th>
				<th>Reject</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
		
	</table>
</div>
<script
  src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
  integrity="sha256-3edrmyuQ0w65f8gfBsqowzjJe2iM6n0nKciPUp8y+7E="
  crossorigin="anonymous"></script>
<script type='text/javascript' src='js/managerupdatereimbursement.js'></script>
</body>
</html>