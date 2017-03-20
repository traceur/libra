
package com.weizhi.libra.common.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.weizhi.libra.common.utils.ByteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Linchong
 * @version Id: SHAUtil.java, v 0.1 2017/1/18 11:00 Linchong Exp $$
 * @Description TODO
 */
public class SHAUtil {
    private static final Logger LOG              = LoggerFactory.getLogger(SHAUtil.class);
    private static final String ALGORITHM_MD5    = "MD5";
    private static final String ALGORITHM_SHA1   = "SHA";
    private static final String ALGORITHM_SHA256 = "SHA-256";
    private static final String ALGORITHM_SHA512 = "SHA-512";


    /**
     * 计算msg的sha1值(不推荐)
     * 
     * @param msg 消息明文
     * @return
     */
    public static String sha1(String msg) {
        return encode(ALGORITHM_SHA1, msg);
    }


    /**
     * 计算msg的MD5散列值(不推荐)
     * 
     * @param msg 消息明文
     * @return
     */
    public static String md5(String msg) {
        return encode(ALGORITHM_MD5, msg);
    }


    /**
     * 计算msg的sha-256值（推荐）
     * 
     * @param msg 消息明文
     * @return
     */
    public static String sha256(String msg) {
        return encode(ALGORITHM_SHA256, msg);
    }


    /**
     * 计算msg的sha-512值（推荐）
     * 
     * @param msg 消息明文
     * @return
     */
    public static String sha512(String msg) {
        return encode(ALGORITHM_SHA512, msg);
    }


    /**
     * 计算hash值
     * 
     * @param algorithm 算法
     * @param msg 消息明文
     * @return
     */
    private static String encode(String algorithm, String msg) {
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            hash = ByteUtil.bytesToHexString(md.digest(msg.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            LOG.error("SHA failed, algorithm={},msg={},e=", algorithm, msg, e);
        }
        return hash;
    }


    public static void main(String[] args) {
        String msg = "bigdata";
        System.out.println(md5(msg));
        System.out.println(sha1(msg));
        System.out.println(sha256(msg));
        System.out.println(sha512(msg));
        System.out.println(sha512(msg));
    }

}
