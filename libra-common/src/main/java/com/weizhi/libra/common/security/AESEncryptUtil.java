package com.weizhi.libra.common.security;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.weizhi.libra.common.utils.ByteUtil;

/**
 * @author Linchong
 * @version Id: AESEncryptUtil.java, v 0.1 2016/8/12 15:58 Linchong Exp $$
 * @Description TODO
 */
public class AESEncryptUtil {
    /**
     * 算法
     */
    private static final String    ALGORTTHM      = "AES";
    private static final String    CHARSET_UTF8   = "UTF-8";
    private static final String    TRANSFORMATION = "AES/CBC/PKCS5Padding";

    private static final String    RANDOM_AGL     = "SHA1PRNG";
    /**
     * 初始化向量
     */
    private static final String    DEFAULT_IV     = "0123456789ABCDEF";
    /**
     * 秘钥长度
     */
    private static final Integer   KEY_LENGTH     = 128;

    private static IvParameterSpec iv;

    static {
        iv = new IvParameterSpec(DEFAULT_IV.getBytes());
    }


    /**
     * 加密
     * 
     * @param plainText 明文
     * @param password 密钥种子
     * @return 密文字节
     */
    private static byte[] encrypt(String plainText, String password) {
        try {
            Key key = generateKey(password);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            byte[] byteContent = plainText.getBytes(CHARSET_UTF8);
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] result = cipher.doFinal(byteContent);// 加密
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 生成key
     * 
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static Key generateKey(String password) throws NoSuchAlgorithmException {
        KeyGenerator kgen = KeyGenerator.getInstance(ALGORTTHM);
        //ecureRandom 实现完全随操作系统本身的內部状态，除非调用方在调用 getInstance 方法之后又调用了 setSeed 方法；
        // 该实现在 windows 上每次生成的 key 都相同，但是在 solaris 或部分 linux 系统上则不同。
        SecureRandom random = SecureRandom.getInstance(RANDOM_AGL);
        random.setSeed(password.getBytes());
        kgen.init(KEY_LENGTH, random);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORTTHM);
        return key;
    }


    /**
     * 加密
     * 
     * @param content 明文
     * @param password 秘钥种子
     * @return 16进制密文结果
     */
    public static String encryptToHex(String content, String password) {
        return ByteUtil.bytesToHexString(encrypt(content, password));
    }


    /**
     * 解密
     * 
     * @param cipherText 密文字节
     * @param password 密钥种子
     * @return 明文
     */
    private static byte[] decrypt(byte[] cipherText, String password) {
        try {
            Key key = generateKey(password);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            //使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] result = cipher.doFinal(cipherText);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 解密
     * 
     * @param cipherText 16进制密文
     * @param password 秘钥种子
     * @return
     */
    public static String decryptFromHex(String cipherText, String password) {
        return new String(decrypt(ByteUtil.hexStringToBytes(cipherText), password));
    }


    public static void main(String[] args) {
        String content = "test";
        String password = "33333";
        //加密
        System.out.println("加密前：" + content);
        String encryptResult = encryptToHex(content, password);
        System.out.println("加密后(16进制)：" + encryptResult);

        //解密
        System.out.println("解密后：" + decryptFromHex(encryptResult, password));
        System.out.println("解密后：" + decryptFromHex(encryptResult, password));
    }
}
