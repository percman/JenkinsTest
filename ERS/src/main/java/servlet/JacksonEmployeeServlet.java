package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Employee;
import service.EmployeeService;

public class JacksonEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JacksonEmployeeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		ObjectMapper mapper = new ObjectMapper();
		List<Employee> employees = EmployeeService.readEmployees();
		response.getWriter().write(
				mapper.writeValueAsString(employees));
	}

}
