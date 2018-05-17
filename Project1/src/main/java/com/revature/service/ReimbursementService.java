package com.revature.service;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public class ReimbursementService {

	private ReimbursementService() {}
	private static ReimbursementDao dao = ReimbursementDaoImpl.getInstance();
	
	public static String insertReimbursement (HttpServletRequest request, HttpServletResponse response){
		Employee employee=(Employee)request.getSession().getAttribute("authorizedUser");
		int id=employee.getId();
		try {
		Part filePart=request.getPart("fileToUpload");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		InputStream fileContent = filePart.getInputStream();
		byte[] buffer = new byte[fileContent.available()];
	    fileContent.read(buffer);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
	    fileName=id+"-"+LocalDateTime.now().format(formatter)+fileName.substring(fileName.indexOf("."));
	    File targetFile = new File("/home/ec2-user/.jenkins/workspace/Project1/Project1/src/main/webapp/images/"+fileName);
	    OutputStream outStream = new FileOutputStream(targetFile);
	    outStream.write(buffer);
	    outStream.close();
	    try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reimbursement reimbursement=new Reimbursement(id, request.getParameter("category"), Double.parseDouble(request.getParameter("amount")), fileName);
		if(dao.insertReimbursement(reimbursement)) {
			return "/viewPending.do";
		}
		}catch(ServletException se) {
			se.getMessage();
		}
		catch(IOException ioe) {
			ioe.getMessage();
		}
		
		return "ReimbursementSubmit.jsp";
		
	}
	
	
}
