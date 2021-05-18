package com.example.timelapse.system.util;

import java.util.regex.Pattern;

public class RestUtils {
    public static String getPath(String str){
        Pattern slashStart = Pattern.compile("^/+");
        String validStr = StringUtils.getValue(str);
        return slashStart.matcher(validStr).replaceAll("");
    }
}
