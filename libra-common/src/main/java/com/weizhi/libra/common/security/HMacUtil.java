
package com.weizhi.libra.common.security;

import com.weizhi.libra.common.utils.ByteUtil;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Linchong
 * @version Id: HMacUtil.java, v 0.1 2016/11/9 10:02 Linchong Exp $$
 * @Description TODO
 */
public class HMacUtil {
    private static final String ALGORITHM_HMAC_SHA256 = "HmacSHA256";

    private static final String CHARSET_UTF8          = "UTF-8";


    /**
     * 初始化密钥
     *
     * @return byte[] 密钥
     */
    public static String initHmacSHA256Key() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM_HMAC_SHA256);
        SecretKey secretKey = keyGenerator.generateKey();
        return ByteUtil.bytesToHexString(secretKey.getEncoded());
    }


    /**
     * 消息摘要
     *
     * @param data 明文字节数据
     * @param key 密钥
     * @return byte[]
     */
    public static byte[] encodeHmacSHA256(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM_HMAC_SHA256);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }


    /**
     * 消息摘要算法
     * 
     * @param data 明文字符
     * @param key key字符
     * @return
     */
    public static String encodeHmacSHA256(String data, String key) throws Exception {
        return ByteUtil.bytesToHexString(encodeHmacSHA256(data.getBytes(CHARSET_UTF8),
                ByteUtil.hexStringToBytes(key)));
    }


    public static void main(String args[]) throws Exception {
        String data = "未知";
        System.out.println(initHmacSHA256Key());
        String key = "43fa78663b11d166f79ab0be0cb9a3ba0c60cc8494bcc9e135552dd1d577fd2d";
        String result = encodeHmacSHA256(data, key);
        System.out.println(result);
    }
}
