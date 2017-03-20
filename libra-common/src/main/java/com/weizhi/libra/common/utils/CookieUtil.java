package com.weizhi.libra.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieUtil {
    private static final Logger LOG = LoggerFactory.getLogger(CookieUtil.class);


    private CookieUtil() {
    }


    /**
     * looking for the cookie via the cookie's name
     * 
     * @param request
     * @param name
     * @return Cookie that we are looking for
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie cookie = null;

        Cookie[] cookies = request.getCookies();

        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(name)) {
                cookie = cookies[i];
                break;
            }
        }

        return cookie;
    }


    /**
     * @param response
     * @param name
     * @param value
     * @param domain
     * @param maxAge
     */
    public static void setCookie(HttpServletResponse response, String name, String value,
                                 String domain, int maxAge) {
        if (value == null) {
            value = "";
        }
        Cookie cookie;

        String v;
        try {
            v = URLEncoder.encode(value, "utf-8").trim();
            cookie = new Cookie(name, v);
            if (maxAge != -2) {
                cookie.setMaxAge(maxAge);
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage());
        }
    }


    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");

        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
