package com.revature.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.WrongPasswordException;
import com.revature.model.SimpleEmployee;
import com.revature.util.Mapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.revature.service.EmployeeService.login;
import static com.revature.util.OtherUtils.stringReponse;
import static com.revature.util.OtherUtils.isJSONish;
import static com.revature.util.OtherUtils.jsonResponse;

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
}