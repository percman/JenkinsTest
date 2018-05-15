<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee: login</title>
</head>
<body>
<div class='container'>
	<h2>Employee login</h2>
	<div class='form-group'>
		<form action='EmployeeServlet' method='post'>
			<input type='text' name='username' placeholder='Enter username'>
			<input type='password' name='password' placeholder='Enter password'>
			<input type='submit' value='submit'>
			<%=(String)request.getAttribute("message")%>
		</form>
	</div>	
</div>
</body>
</html>
