package serve;

import javax.servlet.http.HttpServletRequest;

public class ManagerServe {

	public static String updateReimbursement(HttpServletRequest request) {
		return "managerupdatereimbursement.jsp";
	}

	public static String home(HttpServletRequest request) {
		return "managerhome.jsp";
	}

	public static String readEmployee(HttpServletRequest request) {
		return "managerreademployee.jsp";
	}

	public static String login(HttpServletRequest request) {
		return "managerlogin.jsp";
	}
	
	public static String logout(HttpServletRequest request) {
		return "index.do";
	}

}
