package com.revature.util;

public class OtherUtils {

    public static boolean isJSONish(String s) {
        return s.charAt(0) == '{' || s.charAt(0) == '[';
    }
}