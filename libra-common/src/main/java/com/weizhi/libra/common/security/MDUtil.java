package com.weizhi.libra.common.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.Security;

/**
 * Message Digest
 * Created by weizhi on 2015/7/31.
 */
public class MDUtil {

    /**
     * MD2签名
     * @param data
     * @return byte[]
     * @throws Exception
     */
    public static byte[] encodeMD2(byte[] data) throws Exception{
        MessageDigest md = MessageDigest.getInstance("MD2");
        return md.digest(data);
    }

    /**
     * MD2 签名
     * @param data
     * @return String
     * @throws Exception
     */
    public static String encodeMD2ToHex(byte[] data) throws Exception{
        return new String(Hex.encode(encodeMD2(data)));
    }

    /**
     * MD5签名
     * @param data
     * @return  byte[]
     * @throws Exception
     */
    public static byte[] encodeMD5(byte[] data) throws Exception{
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(data);
    }

    /**
     * MD5签名 HEX
     * @param data
     * @return String
     * @throws Exception
     */
    public static String encodeMD5ToHex(byte[] data) throws Exception{
        return new String(Hex.encode(encodeMD5(data)));
    }

    /**
     * MD5签名 Commons Codec
     * @param data
     * @return byte[]
     * @throws Exception
     */
    public static byte[] encodeMD5(String data) throws Exception{
        return DigestUtils.md5(data);
    }

    /**
     * MD5 签名 Commons Codec
     * @param data
     * @return String
     * @throws Exception
     */
    public static String encodeMD5ToHex(String data) throws Exception{
        return DigestUtils.md5Hex(data);
    }

    /**
     * MD4签名 Bouncy Castle
     * @param data
     * @return byte
     * @throws Exception
     */
    public static byte[] encodeMD4(byte[] data) throws Exception{
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest md = MessageDigest.getInstance("MD4");
        return md.digest(data);
    }

    /**
     * MD4签名 HEX
     * @param data
     * @return String
     * @throws Exception
     */
    public static String encodeMD4ToHex(byte[] data) throws Exception{
        return new String(Hex.encode(encodeMD4(data)));
    }
}
