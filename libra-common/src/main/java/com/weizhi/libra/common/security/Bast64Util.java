package com.weizhi.libra.common.security;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.UrlBase64;

import java.io.UnsupportedEncodingException;

/**
 * Created by weizhi on 2015/7/30.
 */
public class Bast64Util {

    public final static String ENCODING = "UTF-8";


    /**
     * Base64加密
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encoder(String data) throws UnsupportedEncodingException {
        byte[] b = Base64.encode(data.getBytes(ENCODING));
        return new String(b,ENCODING);
    }

    /**
     * Base64解密
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decoder(String data) throws UnsupportedEncodingException{
        byte[] b = Base64.decode(data.getBytes(ENCODING));
        return new String(b,ENCODING);
    }

    /**
     * Base64 URL 加密
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String urlEncoder(String data) throws UnsupportedEncodingException{
        byte[] b = UrlBase64.encode(data.getBytes(ENCODING));
        return new String(b,ENCODING);
    }

    /**
     * Base64 URL 解密
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String urlDecoder(String data) throws UnsupportedEncodingException{
        byte[] b = UrlBase64.decode(data.getBytes(ENCODING));
        return new String(b,ENCODING);
    }
}
