package com.revature.servlet;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.exceptions.AlreadySetException;
import com.revature.exceptions.SelfSetException;
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
                case "/get-my-reimbursements.do":
                    dispatchMyReimbursements(request, response);
                    break;
                case "/get-all-reimbursements.do":
                    dispatchAllReimbusements(request, response);
                    break;
                case "/set-rstatus.do":
                    dispatchSetRStatus(request, response);
                    break;
                default:
                    response.setStatus(400);
                    logger.debug(st);
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
        } catch (AlreadySetException e) {
            logger.info("This request has already been approved or denied.");
            response.setStatus(400);
            stringReponse("This request has already been approved or denied.", response);
        } catch (SelfSetException e) {
            logger.info("You can't approve yrself.");
            response.setStatus(400);
            stringReponse("You can't approve yourself", response);
        }
    }
}