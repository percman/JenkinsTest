<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Employee Homepage</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
        crossorigin="anonymous">
    <link rel="stylesheet" href="../CSS/Employee.CSS">
</head>
<body>
    <div class="container">
        <nav class="navbar navbar-inverse">
            <div class="navbar-header">
                <h3 style="color: white;"><strong>GENERIC</strong></h3>
            </div>
            <ul class="navbar-nav nav">
                <li class="active">
                    <a href="./GenericEmployeeHomePage.html">Home</a>
                </li>
                <li>
                    <a href="placeholder for reimbursement page">Reimbursement</a>
                </li>
                <li>
                    <a href="placeholder for employee info page">Myself</a>
                </li>
            </ul>
        </nav>
    </div>
    <div class="container">
        <div class="jumbotron">
            <%@ page import="com.revature.model.Employee" %>
            <%Employee user = (Employee) request.getSession().getAttribute("Employee"); %>
            <h1>Welcome, <%= user.getUsername() %></h1>
        </div>
    </div>
    
</body>
</html>