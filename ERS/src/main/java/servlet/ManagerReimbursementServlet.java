package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Manager;
import model.Reimbursement;
import service.ReimbursementService;

public class ManagerReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ManagerReimbursementServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		int reimbursementid = Integer.parseInt(request.getParameter("id"));
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		int managerid = manager.getId();
		
		ReimbursementService.updateReimbursement(reimbursementid, status, managerid);
		List<Reimbursement> reimbursements = ReimbursementService.readReimbursements();
		request.getSession().setAttribute("reimbursements", reimbursements);
		request.getRequestDispatcher("manager-update-reimbursement.do").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
