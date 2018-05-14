package com.revature.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.WrongPasswordException;
import com.revature.model.Employee;
import org.apache.log4j.Logger;


public class EmployeeService {
    private static EmployeeDao dao = new EmployeeDaoImpl();
    private static final Logger logger = Logger.getLogger(EmployeeService.class);

    public static String getStoredHash(String username) {
        return dao.getStoredHash(username);
    }

    public static String hashPassword(String username, String password) {
        return dao.hashPassword(username, password);
    }

    public static Employee getEmployee(String username) {
        return dao.getEmployee(username);
    }

    public static boolean updateEmployeeInfo(Employee e) {return dao.updateEmployeeInfo(e);}


    public static String login(String username, String password) throws UserNotFoundException, WrongPasswordException,
            JsonProcessingException {
        String storedHash = getStoredHash(username);
        if (storedHash == null) {
            throw new UserNotFoundException();
        }
        String freshHash = hashPassword(username, password);
        Employee em;

        if (!storedHash.equals(freshHash)) {
            throw new WrongPasswordException();
        }
        em = getEmployee(username);
        em.setPassword(password);

        logger.debug("User " + username + " logged in successfully!");
        ObjectMapper om = new ObjectMapper();

        return om.writeValueAsString(em);
    }

}