<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <!DOCTYPE html>
    <html>
    
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>
    
    <body>
        <h1>Employee Home Page.</h1>
        <div class="container">
            <%@ page import="com.revature.model.Employee" 
            import="com.revature.model.Reimbursement"%>
                <% Employee employee = (Employee) request.getSession().getAttribute("currentEmployee"); %>
                    <div class="container">
                        <h2>Welcome,
                            <%= employee.getFirstname() %>
                                <%= employee.getLastname() %>
                        </h2>

                        <p>Account Information:</p>
                        <p>First name:
                            <%= employee.getFirstname() %>
                        </p>
                        <p>Middle initial:
                            <%= employee.getMiddleInitial() %>
                        </p>
                        <p>Last name:
                            <%= employee.getLastname() %>
                        </p>
                        <p>Phone:
                            <%= employee.getPhone() %>
                        </p>
                        <p>Email:
                            <%= employee.getEmail() %>
                        </p>
                    </div>
                    <br>
                    <div class="container">
                        <form action="logout.do" method="POST">
                            <input id="logoutBtn" type="submit" value="Logout">
                        </form>
                        <br>
                        <br>
                        <a href="eUpdateAccount.jsp">Update Account Information</a>
                        <br>
                        <a href="eSubmitReimb.jsp">Submit New Reimbursement</a>
                    </div>

                    <div class="container">
                        <h2>Your Requests:</h2>
                        <div class="container">
                            <h3>Pending: </h3>

                            <table>
                                <thead>
                                    <th>Reimbursement ID</th>
                                    <th>Category</th>
                                    <th>Amount</th>
                                    <th>Date Submitted</th>
                                </thead>
                                <tbody id="pendingTable">

                                </tbody>
                            </table>

                        </div>
                        <br>
                        <div class="container">
                            <h3>Resolved: </h3>
                            <table>
                                <thead>
                                    <th>Reimbursement ID</th>
                                    <th>Category</th>
                                    <th>Amount</th>
                                    <th>Date Submitted</th>
                                    <th>Status</th>
                                    <th>Date Resolved</th>
                                    <th>Approved By</th>
                                </thead>
                                <tbody id="resolvedTable">

                                </tbody>
                            </table>
                        </div>
                    </div>
        </div>
        <script src="reimb.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
    
    </body>

    </html>