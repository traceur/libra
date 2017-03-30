package com.weizhi.libra.common.security;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by weizhi on 2015/7/30.
 */
public class Base64Common {

    private final static String CHARSET = "UTF-8";

    /**
     * Base64加密
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encoder(String data) throws UnsupportedEncodingException {
        byte[] b = Base64.encodeBase64(data.getBytes(CHARSET));
        return new String(b,CHARSET);
    }

    /**
     * Base64 安全加密（结尾带换行符\r\n）
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encoderSafe(String data) throws UnsupportedEncodingException{
        byte[] b = Base64.encodeBase64(data.getBytes(CHARSET),true);
        return new String(b,CHARSET);
    }

    /**
     * Base64解密
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decoder(String data) throws UnsupportedEncodingException{
        byte[] b = Base64.decodeBase64(data.getBytes(CHARSET));
        return new String(b,CHARSET);
    }
}
