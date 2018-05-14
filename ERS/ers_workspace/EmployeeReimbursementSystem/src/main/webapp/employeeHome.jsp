<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Employee Home.</title>
    </head>
    <html>

    <body>
        <%@ page import="com.revature.model.User" %>
            <% Employee employee = (Employee) request.getSession().getAttribute("currentEmployee"); %>
                <div class="container">
                    <h2>Welcome, <%= employee.getFirstname() %> <%= employee.getLastname() %></h2>
                    <br><br>
                    <p>Account Information:</p>
                    <br>
                    <p>First name: <%= employee.getFirstname() %></p>
                    <br>
                    <p>Middle initial: <%= employee.getMiddleInitial() %></p>
                    <br>
                    <p>Last name: <%= employee.getLastname() %></p>
                    <br>
                    <p>Phone: <%= employee.getPhone() %></p>
                    <br>   
                    <p>Email: <%= employee.getEmail() %></p>
                    <br>
                </div>
                <br>
                <div class="container">
                    <a href="">Logout</a>
                    <br>
                    <br>
                    <a href="changeAccountInfo.jsp">Change Account Information</a>
                    <br>
                    <a href="submitNewReimb.jsp">Submit New Reimbursement</a>
                </div>


    </body>

    </html>