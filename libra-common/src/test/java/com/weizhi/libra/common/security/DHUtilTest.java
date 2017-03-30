package com.weizhi.libra.common.security;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.util.Map;

/**
 * Created by weizhi on 2015/9/13.
 */
public class DHUtilTest {
    private byte[] publicKey1;
    private byte[] privateKey1;
    private byte[] key1;
    private byte[] publicKey2;
    private byte[] privateKey2;
    private byte[] key2;
    @Test
    public void test() throws Exception{
        Map<String,Object> keyMap = DHUtil.initKeyPair();
        publicKey1 = DHUtil.getPublicKey(keyMap);
        privateKey1 = DHUtil.getPrivateKey(keyMap);

        System.out.println(Base64.encodeBase64String(publicKey1));

        System.out.println(Base64.encodeBase64String(privateKey1));
//        byte[] key4 = DHUtil.getSecretKey(publicKey1,privateKey1);


//        byte[] key3 = DHUtil.getSecretKey(publickey2,privateKey2);


        System.out.println("=================================================");
        Map<String,Object> keyMap2 = DHUtil.initKeyPair(publicKey1);

        publicKey2 = DHUtil.getPublicKey(keyMap2);
        privateKey2 = DHUtil.getPrivateKey(keyMap2);
        System.out.println(Base64.encodeBase64String(publicKey2));
        System.out.println(Base64.encodeBase64String(privateKey2));

        key1 = DHUtil.getSecretKey(publicKey2,privateKey1);
        key2 = DHUtil.getSecretKey(publicKey1,privateKey2);
        System.out.println("key2:"+Base64.encodeBase64String(key2));
        System.out.println("key1:"+Base64.encodeBase64String(key1));

        System.out.println("=================================================");
        keyMap2 = DHUtil.initKeyPair(publicKey1);


        publicKey2 = DHUtil.getPublicKey(keyMap2);
        privateKey2 = DHUtil.getPrivateKey(keyMap2);

        System.out.println(Base64.encodeBase64String(publicKey2));
        System.out.println(Base64.encodeBase64String(privateKey2));


        key1 = DHUtil.getSecretKey(publicKey2,privateKey1);
        key2 = DHUtil.getSecretKey(publicKey1,privateKey2);
        System.out.println("key2:"+Base64.encodeBase64String(key2));
        System.out.println("key1:"+Base64.encodeBase64String(key1));

        byte[] key3 = DHUtil.getSecretKey(publicKey2,privateKey1);
        byte[] key4 = DHUtil.getSecretKey(publicKey1,privateKey2);
        System.out.println("key3:"+Base64.encodeBase64String(key3));
        System.out.println("key4:"+Base64.encodeBase64String(key4));
    }
}
