package com.weizhi.libra.common.security;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by weizhi on 2015/9/14.
 */
public class RSAUtil {
    //加密解密算法
    private final static String KEY_ALGORITHM = "RSA";

    private final static String RSA_PUBLIC_KEY = "RSAPublicKey";

    private final static String RSA_PRIVATE_KEY = "RSAPrivateKey";

    /**
     * RSA密钥长度，默认1024
     * 必须是64的倍数
     */
    private final static int KEY_SIZE = 1024;
    /**
     * 初始化密钥
     * @return
     */
    public static Map<String,Object> initKey() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
//        keyPairGenerator.initialize(KEY_SIZE);
        keyPairGenerator.initialize(1024,new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        Map<String,Object> keyMap = new HashMap<String, Object>();
        keyMap.put(RSA_PUBLIC_KEY,publicKey);
        keyMap.put(RSA_PRIVATE_KEY,privateKey);

        return keyMap;
    }

    /**
     * 获取公钥
     * @param keyMap 密钥对
     * @return
     */
    public static byte[] getPublicKey(Map<String,Object> keyMap){
        Key key = (Key) keyMap.get(RSA_PUBLIC_KEY);
        return key.getEncoded();
    }

    /**
     * 获取私钥
     * @param keyMap  密钥对
     * @return
     */
    public static byte[] getPrivateKey(Map<String,Object> keyMap){
        Key key = (Key) keyMap.get(RSA_PRIVATE_KEY);

        return key.getEncoded();
    }


    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[]data,byte[] publicKey) throws Exception{
        //获取公钥
        X509EncodedKeySpec  x509EncodedKeySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey1 = keyFactory.generatePublic(x509EncodedKeySpec);
        //加密数据
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,publicKey1);

        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data,byte[] privateKey) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE,privKey);
        return cipher.doFinal(data);
    }


}
