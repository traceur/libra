package com.weizhi.libra.common.security;

import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by weizhi on 2015/9/13.
 * D-H 密钥交换算法
 */
public class DHUtil {

    /**
     * 非对称加密算法
     */
    public final static String KEY_ALGORITHM = "DH";
    /**
     * 密钥长度
     */
    public final static int KEY_SIZE = 512;

    /**
     * 公钥
     */
    public final static String PUBLIC_KEY="DHPublicKey";
    /**
     * 私钥
     */
    public final static String PRIVATE_KEY = "DHPrivateKey";

    /**
     * 本地密钥算法， 对称加密算法
     */
    public final static String SECRET_ALGORITHM = "AES";

    /**
     * 生产甲方密钥
     * @return
     * @throws Exception
     */
    public static Map<String,Object> initKeyPair()throws  Exception{
        //实例化密钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥生成器
        keyPairGenerator.initialize(KEY_SIZE);
        //生产密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //生成公钥
        DHPublicKey dhPublicKey = (DHPublicKey)keyPair.getPublic();
        //生成私钥
        DHPrivateKey dhPrivateKey = (DHPrivateKey) keyPair.getPrivate();

        Map<String,Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY,dhPublicKey);
        keyMap.put(PRIVATE_KEY,dhPrivateKey);
        return keyMap;
    }

    /**
     * 根据甲方公钥构建乙方密钥对
     * @param key 甲方公钥
     * @return
     * @throws Exception
     */
    public static Map<String,Object> initKeyPair(byte[] key) throws Exception{
        //解析甲方公钥
        //转换公钥材料
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        //由甲方公钥构建乙方密钥
        DHParameterSpec dhParameterSpec = ((DHPublicKey)publicKey).getParams();
        //实例化密钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥生成器
        keyPairGenerator.initialize(dhParameterSpec);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        DHPublicKey dhPublicKey =(DHPublicKey)keyPair.getPublic();

        DHPrivateKey dhPrivateKey = (DHPrivateKey)keyPair.getPrivate();

        Map<String,Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY,dhPublicKey);
        keyMap.put(PRIVATE_KEY,dhPrivateKey);
        return keyMap;
    }

    /**
     * 生产对称密钥
     * @param publicKey
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] getSecretKey(byte[] publicKey,byte[] privateKey) throws Exception{
        //实例化密钥生成器
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥、密钥材料转换
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey);
        //产生公钥
        PublicKey publicKey1 = keyFactory.generatePublic(x509EncodedKeySpec);

        //初始化私钥、密钥材料转换
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey);
        //产生私钥
        PrivateKey privateKey1 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        //
        KeyAgreement keyAgreement = KeyAgreement.getInstance(keyFactory.getAlgorithm());
        keyAgreement.init(privateKey1);
        keyAgreement.doPhase(publicKey1,true);
        SecretKey secretKey = keyAgreement.generateSecret(SECRET_ALGORITHM);
        return secretKey.getEncoded();
    }

    /**
     * 获取私钥
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static byte[] getPrivateKey(Map<String,Object> keyMap) throws Exception{
        Key key = (Key)keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    /**
     * 获取私钥
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static byte[] getPublicKey(Map<String,Object> keyMap) throws Exception{
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }


}
