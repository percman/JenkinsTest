package com.revature.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class Mapper {
    public static Mappable mapRequest(HttpServletRequest request, Class<? extends Mappable> mapTo) {
        ObjectMapper om = new ObjectMapper();
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = request.getReader();) {
            br.lines().forEach( (val) -> {
                builder.append(val);
            });
            return om.readValue(builder.toString(), mapTo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}