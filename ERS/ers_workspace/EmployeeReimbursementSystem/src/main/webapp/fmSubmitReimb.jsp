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
			<a href="finManHome.jsp" id="finManHome" >Financial Manager Home Page</a>
		</div>
		<br><br>

        <div class="container">
            <form>
                <div class="container">
                    <div class="col-md-2">
                        <label for="lodging">Lodging</label><br>
                        <input type="radio" name="lodging" id="lodging" value="1">
                    </div>
                    <div class="col-md-2">
                        <label for="travel">Travel</label><br>
                        <input type="radio" name="travel" id="travel" value="2">
                    </div>
                    <div class="col-md-2">
                        <label for="food">Food</label><br>
                        <input type="radio" name="food" id="food" value="3">
                    </div>
                    <div class="col-md-2">
                        <label for="other">Other</label><br>
                        <input type="radio" name="other" id="other" value="4">
                    </div>
                </div>
                <div class="container">
                    <label for="amount"></label>
                    <input type="number" name="amount" required>
                </div>
                <div class="container">
                    <input type="submit" value="Submit" id="submitReimb">
                </div>
            </form>
        </div>
	



	</body>

	</html>