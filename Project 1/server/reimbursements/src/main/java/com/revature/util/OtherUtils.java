package com.revature.util;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;

public class OtherUtils {

    private static Logger logger = Logger.getLogger(OtherUtils.class);

    public static boolean isJSONish(String s) {
        logger.debug("s.charAt(0) in isJSONish = " + s.charAt(0));
        return s.charAt(0) == '{' || s.charAt(0) == '[';
    }

    public static void jsonResponse(String json, HttpServletResponse response) {
        response.setContentType("text/json");
        logger.debug("In jsonResponse");
        try(PrintWriter out = response.getWriter();) {
            out.append(json);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stringReponse(String html, HttpServletResponse response) {
        response.setContentType("text/json");
        logger.debug("In stringReponse");
        try(PrintWriter out = response.getWriter();) {
            out.append("\""+ html+"\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void booleanResponse(boolean bool, HttpServletResponse response) {
        response.setContentType("text/json");
        logger.debug("In booleanResponse");
        try(PrintWriter out = response.getWriter();) {
            out.append(Boolean.toString(bool));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String blobToString(Blob bloob) throws SQLException {
        String s = new String(bloob.getBytes(1l, (int) bloob.length()));
        logger.debug("Blob " + bloob);
        logger.debug("String " + s);
        return s ;
    }
}