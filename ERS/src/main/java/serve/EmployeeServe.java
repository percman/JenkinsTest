package serve;

import javax.servlet.http.HttpServletRequest;

public class EmployeeServe {

	public static String login(HttpServletRequest request) {
		request.setAttribute("message", "");
		return "employeelogin.jsp";
	}

	public static String home(HttpServletRequest request) {
		return "employeehome.jsp";
	}

	public static String readInformation(HttpServletRequest request) {
		return "employeereadinformation.jsp";
	}
	
	public static String readReimbursement(HttpServletRequest request) {
		return "employeereadreimbursement.jsp";
	}

	public static String updateInformation(HttpServletRequest request) {
		return "employeeupdateinformation.jsp";
	}

	public static String createReimbursement(HttpServletRequest request) {
		return "employeecreatereimbursement.jsp";
	}

	public static String logout(HttpServletRequest request) {
		return "index.do";
	}
}
