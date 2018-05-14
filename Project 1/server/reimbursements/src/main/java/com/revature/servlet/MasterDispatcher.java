package com.revature.servlet;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.WrongPasswordException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static com.revature.service.DispatchService.*;
import static com.revature.util.OtherUtils.stringReponse;

public class MasterDispatcher {
    private static Logger logger = Logger.getLogger(MasterDispatcher.class);
    private MasterDispatcher() {}

    public static void process(HttpServletRequest request, HttpServletResponse response) {
        String st = request.getRequestURI();
        try {
            switch (st) {
                case "/login.do":
                    dispatchLogin(request, response);
                    break;
                case "/create-reimbursement.do":
                    dispatchCreateReimbursement(request, response);
                    break;
                case "/update-info.do":
                    dispatchUpdateInfo(request, response);
                    break;
                case "/get-all-employees.do":
                    dispatchEmployeeList(request, response);
                    break;
                default:
                    System.out.println(st);
            }
        }  catch (UserNotFoundException e) {
            logger.info("User not found.");
            response.setStatus(403);
            stringReponse("User not found.", response);
        } catch (WrongPasswordException e) {
            logger.info("Wrong password");
            response.setStatus(403);
            stringReponse("Wrong password.", response);
        } catch (JsonProcessingException e) {
            logger.info("Error processing JSON for object");
            response.setStatus(500);
            stringReponse("Error processing JSON for object ", response);
        }
    }
}