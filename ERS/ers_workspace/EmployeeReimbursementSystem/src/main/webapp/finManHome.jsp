<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Financial Manager Home Page.</title>
    </head>
    <html>

    <body>
        <h1>Financial Manager Home Page.</h1>
        <%@ page import="com.revature.model.Employee" 
            import="com.revature.model.FinancialManager"
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
                <br>
                <div class="container">
                    <form action="logout.do" method="POST">
                        <input id="logoutBtn" type="submit" value="Logout">
                    </form>
                    <br>
                    <br>
                    <a href="fmUpdateAccount.jsp">Update Account Information</a>
                    <br>
                    <a href="fmSubmitReimb.jsp">Submit New Reimbursement</a>
                </div>
                <br>
                <div class="container">
                    <a href="pendingReimb.jsp">View Pending Reimbursements</a>
                    <br>
                    <a href="reimbHistory.jsp">View Reimbursement History</a>
                    <br>
                    <a href="allEmployees.jsp">View All Employees</a>
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

                <script src="reimb.js"></script>

    </body>

    </html>