package com.revature.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.WrongPasswordException;
import com.revature.model.CreateReimbursementModel;
import com.revature.model.SimpleEmployee;
import com.revature.util.Mapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.revature.service.EmployeeService.login;
import static com.revature.service.ReimbursementService.createReimbursement;
import static com.revature.util.OtherUtils.*;

public class DispatchService {
    private static Logger logger = Logger.getLogger(DispatchService.class);
    public static void dispatchLogin(HttpServletRequest request, HttpServletResponse response) throws UserNotFoundException, WrongPasswordException, JsonProcessingException {
        SimpleEmployee e = (SimpleEmployee) Mapper.mapRequest(request, SimpleEmployee.class);
        logger.debug(e);
        String outStr = login(e.getUsername(), e.getPassword());
        if (isJSONish(outStr)) {
            jsonResponse(outStr, response);
        } else {
            stringReponse(outStr, response);
        }
    }
    public static void dispatchCreateReimbursement(HttpServletRequest request, HttpServletResponse response) {
        CreateReimbursementModel crm = (CreateReimbursementModel) Mapper.mapRequest(request, CreateReimbursementModel.class);
        logger.debug(crm);
        booleanResponse(createReimbursement(crm), response);
    }
}