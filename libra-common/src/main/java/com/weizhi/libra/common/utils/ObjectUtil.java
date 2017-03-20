package com.weizhi.libra.common.utils;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * object util
 * 
 * @author linchong
 */
public class ObjectUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ObjectUtil.class);


    /**
     * serialized object
     * 
     * @param obj
     * @return
     */
    public static String serialize(Object obj) {
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bao);
            oos.writeObject(obj);
            return bao.toString("ISO-8859-1");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        } finally {
            try {
                oos.close();
                oos = null;
            } catch (IOException e) {
            }
        }

    }


    /**
     * deserialize object
     * 
     * @param objstr
     * @return
     */
    public static Object deserialize(String objstr) {
        ObjectInputStream ois = null;
        try {
            ByteArrayInputStream bai = new ByteArrayInputStream(objstr.getBytes("ISO-8859-1"));
            ois = new ObjectInputStream(bai);
            return ois.readObject();
        } catch (IOException e) {
            LOG.error(e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage());
            return null;
        } finally {
            try {
                ois.close();
                ois = null;
            } catch (IOException e) {
            }
        }
    }


    /**
     * @description 使用Pattern和Matcher类的方法
     * @param s
     * @return
     */
    public static boolean isNumber(String s) {
        String regex = "^[0-9][0-9]*\\.[0-9]+$|^[0-9][0-9]*$|^0+\\.[0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        char c = s.charAt(0);
        if (c == '+' || c == '-') {
            s = s.substring(1);
        }
        Matcher matcher = pattern.matcher(s);
        boolean bool = matcher.matches();
        if (bool) {
            return true;
        } else {
            return false;
        }
    }

}
