package com.weizhi.libra.common.security;

import com.sun.org.apache.xml.internal.security.algorithms.JCEMapper;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;

/**
 * Triple DES 加密算法
 * Created by weizhi on 2015/8/6.
 */
public class DESedeUtil {

    /**
     * 密钥算法 支持密钥长度112位和和168
     */
    public static final String KEY_ALGORITHM = "DESede";


    /**
     * 加密/解密算法 / 工作模式 / 填充方式
     * java 6 支持PKSC5Padding填充方式
     * Bouncy Castle支持PKSC7填充方式
     */
    public static final String CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";

    /**
     * 转换密钥
     * @param key 二进制密钥
     * @return
     * @throws Exception
     */
    public static Key genKey(byte[] key) throws Exception{
        // 实例化密钥材料
        DESedeKeySpec dks = new DESedeKeySpec(key);
        //实例化秘密密钥工厂
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        //转换密钥
        return secretKeyFactory.generateSecret(dks);
    }

    /**
     * 初始化 二进制密钥
     * @return
     * @throws Exception
     */
    public static byte[] initKey() throws Exception{
        //实例化
        KeyGenerator keyGen = KeyGenerator.getInstance(KEY_ALGORITHM);
        //支持128和192位密钥
        //KeyGenerator keyGen = KeyGenerator.getInstance(KEY_ALGORITHM,"BC");

        //初始化密钥长度
        keyGen.init(168);
        //keyGen.init(192);
        //生成秘密密钥
        SecretKey key  = keyGen.generateKey();
        //转换成二进制密钥
        return key.getEncoded();
    }

    public static byte[] encryptData(byte[] data,byte[] key) throws Exception{
        //h
        Key k = genKey(key);

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        cipher.init(Cipher.ENCRYPT_MODE,k);

        return cipher.doFinal(data);
    }

    public static byte[] decodeData(byte[] data,byte[] key) throws Exception{
        //h
        Key k = genKey(key);

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        cipher.init(Cipher.DECRYPT_MODE,k);

        return cipher.doFinal(data);
    }

}
