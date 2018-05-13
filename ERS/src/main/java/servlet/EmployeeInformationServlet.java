package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;
import model.Information;
import service.InformationService;

public class EmployeeInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeInformationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee employee = (Employee) request.getSession().getAttribute("employee");
	
		String inUsername = employee.getUsername();
		String inFirstname = request.getParameter("first");
		String inMiddlename = request.getParameter("middle");
		String inLastname = request.getParameter("last");
		
		//TODO
		//boolean result 
		InformationService.updateInformation(inUsername, inFirstname, inMiddlename, inLastname);
		
		Information information = InformationService.readInformation(inUsername);
		
		System.out.println(employee);
		System.out.println(information);
		
		request.getSession().setAttribute("information", information);
		request.getRequestDispatcher("employee-read-information.do").forward(request, response);
	}

}
