package com.weizhi.libra.common.security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.Security;

/**
 * Secure Hash Algorithm
 * Created by weizhi on 2015/8/3.
 */
public class SHAExtUtil {

    /**
     * SHA 消息摘要
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encodeSHA(byte[] data) throws Exception{
        MessageDigest md = MessageDigest.getInstance("SHA");
        return md.digest(data);
    }

    /**
     * SHA-256 消息摘要
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encodeSHA256(byte[] data) throws Exception{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(data);
    }

    /**
     * SHA-384 消息摘要
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encodeSHA384(byte[] data) throws Exception{
        MessageDigest md = MessageDigest.getInstance("SHA-384");
        return md.digest(data);
    }

    /**
     * SHA-512 消息摘要
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encodeSHA512(byte[] data) throws Exception{
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        return md.digest(data);
    }

    /**
     * SHA-224消息摘要 Bouncy Castle提供
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encodeSHA224(byte[] data) throws Exception{
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest md = MessageDigest.getInstance("SHA-224");
        return md.digest(data);
    }

    public static String encodeSHA224ToHex(byte[] data) throws Exception{
        return new String(Hex.encode(encodeSHA224(data)));
    }
}
