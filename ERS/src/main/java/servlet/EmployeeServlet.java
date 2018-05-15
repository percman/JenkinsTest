package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;
import model.Information;
import service.EmployeeService;
import service.InformationService;

public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		
		String username = request.getParameter("username");
		//String password = request.getParameter("password");
		
		Employee employee = EmployeeService.readEmployee(username);
		Information information = InformationService.readInformation(username);
		
		if(employee != null) {
			request.getSession().setAttribute("employee", employee);
			request.getSession().setAttribute("information",information);
			request.getRequestDispatcher("employee-home.do").forward(request, response);
		} else {
			request.setAttribute("message", "user not found");
			request.getRequestDispatcher("employeelogin.jsp").forward(request, response);
		}
	}

}
