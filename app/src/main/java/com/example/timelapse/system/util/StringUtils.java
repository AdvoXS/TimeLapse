package com.example.timelapse.system.util;

public class StringUtils {
    public static String getValue(String str) {
        return str == null || str.equalsIgnoreCase("null") ? "" : str;
    }
}
