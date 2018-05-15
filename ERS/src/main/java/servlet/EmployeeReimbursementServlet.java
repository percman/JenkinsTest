package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;
import model.Reimbursement;
import service.ReimbursementService;

public class EmployeeReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeReimbursementServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		String inUsername = employee.getUsername();
		String inCategory = request.getParameter("category");
		
		ReimbursementService.createReimbursementEmployee(inUsername, "pending", "image", inCategory);
		List<Reimbursement> reimbursements = ReimbursementService.readReimbursements(inUsername);
		request.getSession().setAttribute("reimbursements", reimbursements);
		
		request.getRequestDispatcher("employee-read-reimbursement.do").forward(request, response);
	}

}
