package servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serve.IndexServe;
import serve.EmployeeServe;
import serve.ManagerServe;

public class Dispatcher {
	public static String dispatch(HttpServletRequest request, HttpServletResponse response) {
		switch(request.getRequestURI()) {
		case "/ers/reimbursements.do":
			return ManagerServe.updateReimbursement(request);
		case "/ers/index.do":
			return IndexServe.index(request);
		case "/ers/employee-login.do":
			return EmployeeServe.login(request);
		case "/ers/employee-logout.do":
			return EmployeeServe.logout(request);
		case "/ers/employee-home.do":
			return EmployeeServe.home(request);
		case "/ers/employee-read-information.do":
			return EmployeeServe.readInformation(request);
		case "/ers/employee-update-information.do":
			return EmployeeServe.updateInformation(request);
		case "/ers/employee-read-reimbursement.do":
			return EmployeeServe.readReimbursement(request);
		case "/ers/employee-create-reimbursement.do":
			return EmployeeServe.createReimbursement(request);
		case "/ers/manager-login.do":
			return ManagerServe.login(request);
		case "/ers/manager-logout.do":
			return ManagerServe.logout(request);
		case "/ers/manager-home.do":
			return ManagerServe.home(request);
		case "/ers/manager-update-reimbursement.do":
			return ManagerServe.updateReimbursement(request);
		case "/ers/manager-read-employee.do":
			return ManagerServe.readEmployee(request);
		default:
			return "404.jsp";
		}
	}
}
