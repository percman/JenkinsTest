package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;
import model.Manager;
import model.Reimbursement;
import service.EmployeeService;
import service.ManagerService;
import service.ReimbursementService;

public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ManagerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		
		String username = request.getParameter("username");
		//String password = request.getParameter("password");
		
		Manager manager = ManagerService.readManager(username);
		List<Reimbursement> reimbursements = ReimbursementService.readReimbursements();
		List<Employee> employees = EmployeeService.readEmployees();
		
		if(manager != null) {
			request.getSession().setAttribute("manager", manager);
			request.getSession().setAttribute("reimbursements", reimbursements);
			request.getSession().setAttribute("employees", employees);
			request.getRequestDispatcher("manager-home.do").forward(request, response);
		} else {
			request.setAttribute("message", "user not found");
			request.getRequestDispatcher("managerlogin.jsp").forward(request, response);
		}
	}

}
