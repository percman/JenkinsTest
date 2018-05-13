<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.Employee"%>
<%@ page import="model.Information"%>
<%@ page import="model.Reimbursement"%>
<%@ page import="java.util.List"%>
<%	Employee employee = (Employee) request.getSession().getAttribute("employee");%>
<%	Information information = (Information) request.getSession().getAttribute("information");%>
<%	Reimbursement reimbursement = (Reimbursement) request.getSession().getAttribute("reimbursement");%>
<%	List<Reimbursement> reimbursements = (List<Reimbursement>) request.getSession().getAttribute("reimbursements");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class='container'>
		<h2><%=employee.getUsername()%>
			read reimbursement
		</h2>
		<a href='employee-home.do'>Home</a>
		<table class='table'>
			<thead>
				<tr>
					<td>Image</td>
					<td>Category</td>
					<td>Status</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${reimbursements}" var="reimbursement">
					<tr>
						<td><c:out value="${reimbursement.iamge}" /></td>
						<td><c:out value="${reimbursement.category}" /></td>
						<td><c:out value='${reimbursement.status }'/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<p><%=reimbursements.toString()%>
		<p>
			Image:
			<%=reimbursement.getImage()%></p>
		<p>
			Category:
			<%=reimbursement.getCategory()%></p>
		<p>
			Status:
			<%=reimbursement.getStatus()%></p>
	</div>

</body>
</html>