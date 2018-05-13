package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;
import model.Information;
import model.Reimbursement;
import service.EmployeeService;
import service.InformationService;
import service.ReimbursementService;

public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		//String password = request.getParameter("password");
		
		Employee employee = EmployeeService.readEmployee(username);
		Information information = InformationService.readInformation(username);
		Reimbursement reimbursement = ReimbursementService.readReimbursement(username);
		List<Reimbursement> reimbursements = ReimbursementService.readReimbursements();
		
		System.out.println(employee);
		System.out.println(information);
		System.out.println(reimbursement);
		
		if(employee != null) {
			request.getSession().setAttribute("employee", employee);
			request.getSession().setAttribute("information",information);
			request.getSession().setAttribute("reimbursement", reimbursement);
			request.getSession().setAttribute("reimbursements", reimbursements);
			request.getRequestDispatcher("employee-home.do").forward(request, response);
		} else {
			request.setAttribute("message", "user not found");
			request.getRequestDispatcher("employeelogin.jsp").forward(request, response);
		}
	}

}
