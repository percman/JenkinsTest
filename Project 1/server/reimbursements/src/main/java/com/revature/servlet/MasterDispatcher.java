package com.revature.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.util.Mapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class MasterDispatcher {

    private MasterDispatcher() {}

    public static String process(HttpServletRequest request, HttpServletResponse response) {
        String st = request.getRequestURI();
        switch(st) {
            case "/login.do":



                break;
            default:
                System.out.println(st);
        }

//                return LoginService.login(request, response);
//            case "/InClassServlets" +"/home.do":
//                return UserService.home(request, response);
//            default:
//                System.out.println(st);
//                return "404.jsp";
//        }
        // System.out.println(Mapper.mapRequest(request, Employee.class));



        return "Boop";
    }
}