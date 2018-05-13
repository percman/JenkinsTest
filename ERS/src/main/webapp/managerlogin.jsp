<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class='container'>
	<h2>Manager login</h2>
	<div class='form-group'>
		<form action='ManagerServlet' method='post'>
			<input type='text' name='username' value='Enter username'>
			<input type='password' name='password' value='Enter password'>
			<input type='submit' value='submit'>
			<%=(String)request.getAttribute("message")%>
		</form>
	</div>	
</div>
</body>
</html>