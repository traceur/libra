package com.weizhi.libra.common.security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;

/**
 * Data Encryption Standard
 * Created by weizhi on 2015/8/4.
 */
public class DESUtil {

    /**
     *  密钥算法，java 6支持56位密钥
     *  Bouncy Castle 支持64位密钥
     */
    public static String KEY_ALGORITHM = "DES";

    /**
     *  加密解密算法 / 工作模式 / 填充算法
     */
    public static String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";

    /**
     * 密钥生成
     * @return
     * @throws Exception
     */
    public static byte[] initKey() throws  Exception{
        /**
         * java 算法 56位密钥
         */
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        kg.init(56);
        /**
         * Bouncy Castle 算法 64位密钥
         */
        //KeyGenerator kg = KeyGenerator.getInstance(CIPHER_ALGORITHM,"BC");
        //kg.init(64);


        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * 转换密钥
     * @param key 二进制密钥
     * @return  Key 密钥
     * @throws Exception
     */
    public static Key createKey(byte[] key) throws Exception{
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        SecretKey secretKey = secretKeyFactory.generateSecret(dks);
        return secretKey;
    }

    /**
     * DES 加密
     * @param data 被加密数据
     * @param key 二进制密钥
     * @return byte[] 二进制加密字符串
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,byte[] key) throws Exception{
        Key k = createKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE,k);
        return cipher.doFinal(data);
    }

    /**
     * DES 解密
     * @param data 带解密数据
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,byte[] key) throws Exception{
        Key k = createKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,k);
        return cipher.doFinal(data);
    }
}
