package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Manager;
import service.ManagerService;

public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ManagerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		
		Manager manager = null;
		try {
			manager = ManagerService.readManager("andrew");
			request.setAttribute("message", "welcome " + username);
			request.setAttribute("manager", manager);
			request.getRequestDispatcher("manager-home.do").forward(request, response);
			return;
		} catch(NullPointerException e){
			request.setAttribute("message", "user not found");
			request.getRequestDispatcher("managerlogin.jsp").forward(request, response);
		} 
	}

}
