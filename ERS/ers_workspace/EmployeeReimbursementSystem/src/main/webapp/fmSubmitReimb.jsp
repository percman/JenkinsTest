<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Submit Reimbursement.</title>
    </head>

    <body>
        <h2>Submit a reimbursement.</h2>
        <%@ page import="com.revature.model.Employee" 
		import="com.revature.model.FinancialManager"
		import="com.revature.model.Reimbursement"%>
            <% Employee employee = (Employee) request.getSession().getAttribute("currentEmployee"); %>
                <div class="container">
                    <a href="finManHome.jsp" id="finManHome">Financial Manager Home Page</a>
                </div>
                <br>
                <br>

                <div class="container">
                    <form action="submitReimb.do" method="POST">
                        <div class="container">
                            <h3>Choose a category:</h3>
                            <div class="col-md-2">
                                <label for="lodging">Lodging </label>
                                <input type="radio" name="lodging" id="lodging" value="1">
                            </div>
                            <br>
                            <div class="col-md-2">
                                <label for="travel">Travel </label>
                                <input type="radio" name="travel" id="travel" value="2">
                            </div>
                            <br>
                            <div class="col-md-2">
                                <label for="food">Food </label>
                                <input type="radio" name="food" id="food" value="3">
                            </div>
                            <br>
                            <div class="col-md-2">
                                <label for="other">Other </label>
                                <input type="radio" name="other" id="other" value="4">
                            </div>
                        </div>
                        <br>
                        <div class="container">
                            <h3>Enter an amout: </h3>
                            <label for="amount">Amount: </label>
                            <input type="number" name="amount" required step=".01">
                        </div>
                        <br>
                        <div class="container" style="display: none;">
                            <h3>Upload a receipt: </h3>
                        </div>
                        <br>
                        <div class="container">
                            <input type="submit" value="Submit" id="submitReimb">
                        </div>
                    </form>
                </div>




    </body>

    </html>