package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Reimbursement;
import service.ReimbursementService;

public class JacksonReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JacksonReimbursementServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		List<Reimbursement> reimbursements;
		
		if(username == null) 
			reimbursements = new ArrayList<>();
		else if(username.equals("all"))
			reimbursements = ReimbursementService.readReimbursements();
		else
			reimbursements = ReimbursementService.readReimbursements(username);
		
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().write(
				mapper.writeValueAsString(reimbursements));
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get a reference to the ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		List<Reimbursement> reimbursements = ReimbursementService.readReimbursements();
		response.getWriter().write(
				mapper.writeValueAsString(reimbursements));
	}
}
