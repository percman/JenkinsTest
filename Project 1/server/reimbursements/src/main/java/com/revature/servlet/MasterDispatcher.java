package com.revature.servlet;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterDispatcher {

    private MasterDispatcher() {}

    public static String process(HttpServletRequest request, HttpServletResponse response) {
//        String st = request.getRequestURI();
//        switch("/InClassServlets" + st) {
//            case "/InClassServlets" +"/login.do":
//                return LoginService.login(request, response);
//            case "/InClassServlets" +"/home.do":
//                return UserService.home(request, response);
//            default:
//                System.out.println(st);
//                return "404.jsp";
//        }
        return null;
    }
}