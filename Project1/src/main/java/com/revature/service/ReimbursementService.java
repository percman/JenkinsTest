package com.revature.service;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public class ReimbursementService {
	private static final Logger logger = Logger.getLogger(ReimbursementService.class);

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
	    File targetFile = new File("/home/ec2-user/.jenkins/workspace/Project1/Project1/src/main/webapp/images"+fileName);
	    OutputStream outStream = new FileOutputStream(targetFile);
	    outStream.write(buffer);
	    outStream.close();
		Reimbursement reimbursement=new Reimbursement(id, request.getParameter("category"), Double.parseDouble(request.getParameter("amount")), fileName);
		
		URL url = new URL("http://ec2-54-236-11-254.compute-1.amazonaws.com:8090/jenkins/buildByToken/build?job=Project1&token=update");
		  HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		  httpCon.setDoOutput(true);
		  httpCon.setRequestMethod("POST");
		  OutputStreamWriter out = new OutputStreamWriter(
		      httpCon.getOutputStream());
		  System.out.println(httpCon.getResponseCode());
		  System.out.println(httpCon.getResponseMessage());
		  out.close();
		
		if(dao.insertReimbursement(reimbursement)) {
			return "/viewPending.do";
		}
		}catch(ServletException se) {
			logger.warn(se.getMessage());
		}
		catch(IOException ioe) {
			logger.warn(ioe.getMessage());
		}
		
		return "ReimbursementSubmit.jsp";
		
	}
	
	
}
