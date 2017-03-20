package com.weizhi.libra.common.security;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DESEncryptUtil {
    private static final Logger LOG = LoggerFactory.getLogger(DESEncryptUtil.class);

    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM            = "DESede";

    private static final String DEFAULT_CIPHER_ALGORITHM = "DESede/ECB/ISO10126Padding";


    private static byte[] initSecretKey() throws Exception {
        String key = "hkjilkjlkjliou2308765eer";
        return key.getBytes();
    }


    /**
     * 转换密钥
     * 
     * @param key 二进制密钥
     * @return Key 密钥
     * @throws Exception
     */
    private static Key toKey(byte[] key) {
        // 实例化DES密钥规则
        DESedeKeySpec dks;
        SecretKey secretKey = null;
        try {
            dks = new DESedeKeySpec(key);
            // 实例化密钥工厂
            SecretKeyFactory skf = SecretKeyFactory.getInstance(KEY_ALGORITHM);
            // 生成密钥
            secretKey = skf.generateSecret(dks);
        } catch (InvalidKeyException e) {
            LOG.error(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            LOG.error(e.getMessage());
        } catch (InvalidKeySpecException e) {
            LOG.error(e.getMessage());
        }
        return secretKey;
    }


    /**
     * 加密
     * 
     * @param data 待加密数据
     * @param key 密钥
     * @return byte[] 加密数据
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, Key key) {
        return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }


    /**
     * 加密
     * 
     * @param data 待加密数据
     * @param key 密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[] 加密数据
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm) {
        // 实例化
        Cipher cipher = null;
        byte[] ret = null;
        try {
            cipher = Cipher.getInstance(cipherAlgorithm);
            // 使用密钥初始化，设置为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, key);
            ret = cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
        } catch (NoSuchPaddingException e) {
        } catch (InvalidKeyException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (BadPaddingException e) {
        }
        return ret;
    }


    /**
     * 解密
     * 
     * @param data 待解密数据
     * @param key 密钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, Key key) throws Exception {
        return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }


    /**
     * 解密
     * 
     * @param data 待解密数据
     * @param key 密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[] 解密数据
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        // 实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        // 使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key);
        // 执行操作
        return cipher.doFinal(data);
    }


    public static String encrypt(String data) {
        try {
            byte[] key = initSecretKey();
            Key k = toKey(key);
            return new String(Hex.encodeHex(encrypt(data.getBytes(), k)));
        } catch (Exception e) {

        }
        return null;
    }


    public static String decrypt(String data) {
        try {
            byte[] key = initSecretKey();
            Key k = toKey(key);
            byte[] decryptData = decrypt(Hex.decodeHex(data.toCharArray()), k);
            return new String(decryptData);
        } catch (Exception e) {

        }
        return null;
    }

}
