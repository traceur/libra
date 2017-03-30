package com.weizhi.libra.common.security;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Message Authentication Code
 * Created by weizhi on 2015/8/4.
 */
public class MACUtil {

    /**
     * 生成 HmacMD5密钥
     * @return
     * @throws Exception
     */
    public static byte[] initHmacMD5Key() throws Exception{
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
        SecretKey key = keyGen.generateKey();
        return key.getEncoded();
    }

    /**
     * HmacMD5 消息摘要
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encodeHmacMD5(byte[] data,byte[] key) throws  Exception{
        SecretKey secretKey = new SecretKeySpec(key,"HmacMD5");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }
}
