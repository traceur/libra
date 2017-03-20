package com.weizhi.libra.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Linchong
 * @version Id: StringUtils.java, v 0.1 2017/3/20 10:44 Linchong Exp $$
 * @Description TODO
 */
public class StringUtils {
    private StringUtils() {
    }


    public static String getRemoteIp(HttpServletRequest request) {
        if (request == null) {
            return "127.0.0.1";
        }
        String remoteip = request.getHeader("x-real-ip");
        if (remoteip == null) {
            remoteip = request.getRemoteAddr();
        }
        return remoteip;
    }


    public static boolean isPattern(String sentence, String regex) {
        return sentence.matches(regex);
    }
}
