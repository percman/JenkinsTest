<%@ page import="com.revature.model.Employee"%>
<% Employee employee = (Employee) request.getSession().getAttribute("authorizedUser");%>

<div class="container">
			<div class="container">
		<div class="col-md-6 col-md-offset-3">
			<form action="update.do" method="post">
				<div class="form-group"><label for "username">Username: </label>
					<input type="text" name="username" id="username" class="form-control" required value=<%=employee.getUsername() %>>
				</div>
				<div class="form-group"><label for "password">Password:</label>
					<input type="password" name="password" id="password" class="form-control" required placeholder="Enter your password">
				</div>
				<div class="form-group"><label for "firstName">First Name:</label>
					<input type="text" name="firstName" class="form-control" id="firstname" required value=<%=employee.getFirstname() %>>
				</div>
				<div class="form-group"><label for "middleInitial">Middle Initial:</label>
					<input type="text" name="middleInitial" id="middleInitial" class="form-control" maxlength="1" required value=<%=employee.getMiddleInit() %>>
				</div>
				<div class="form-group"><label for "lastName">Last Name</label>
					<input type="text" name="lastName" id="lastName" class="form-control" required value=<%=employee.getLastName() %>>
				</div>
				<div class="button-group">
					<input type="submit" class="btn btn-success" value="Submit">
					<input type="reset" class="btn btn-danger" value="Reset">
				</div>
			</form>
		</div>
	</div>
		</div>