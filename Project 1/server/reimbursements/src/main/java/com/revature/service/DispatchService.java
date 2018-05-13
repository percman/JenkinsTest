package com.revature.service;

import com.revature.model.Employee;
import com.revature.util.Mapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatchService {

    public static String dispatchLogin(HttpServletRequest request, HttpServletResponse response) {
        Employee e = (Employee) Mapper.mapRequest(request, Employee.class);

    }
}