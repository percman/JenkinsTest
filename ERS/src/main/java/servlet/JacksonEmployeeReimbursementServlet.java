package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Employee;
import model.Reimbursement;
import service.ReimbursementService;

public class JacksonEmployeeReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JacksonEmployeeReimbursementServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		String username = employee.getUsername();
		
		ObjectMapper mapper = new ObjectMapper();
		List<Reimbursement> reimbursements = ReimbursementService.readReimbursements(username);
		
		response.getWriter().write(
				mapper.writeValueAsString(reimbursements));
	}
}
