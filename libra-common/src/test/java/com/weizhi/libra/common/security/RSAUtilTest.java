package com.weizhi.libra.common.security;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by weizhi on 2015/9/20.
 */
public class RSAUtilTest {
    @Test
    public void test() {
        String dataStr = "未知sdghjkl;wertyuiodfghjkp[]dfghjkl;'" +
                "sdfghjkghjkl;'dfghjkl;dfghjkl;dfghjkl;'edrtyuiopxcvbnm,.";
        try {
            //生产密钥
            Map<String, Object> keyMap = RSAUtil.initKey();
            byte[] data = dataStr.getBytes();

            byte[] publicKey = RSAUtil.getPublicKey(keyMap);
            byte[] privateKey = RSAUtil.getPrivateKey(keyMap);
            System.out.println("公钥1:" + Base64.encode(publicKey));
            System.out.println("私钥1:" + Base64.encode(privateKey));

            Map<String, Object> keyMap2 = RSAUtil.initKey();

            byte[] publicKey2 = RSAUtil.getPublicKey(keyMap2);
            byte[] privateKey2 = RSAUtil.getPrivateKey(keyMap2);
            System.out.println("公钥2:" + Base64.encode(publicKey2));
            System.out.println("私钥2:" + Base64.encode(privateKey2));

            //数据加密
            byte[] encryptData = RSAUtil.encryptByPublicKey(data,publicKey);
            System.out.println("公钥加密1_1:" + Base64.encode(encryptData));
            byte[] encryptData2 = RSAUtil.encryptByPublicKey(data,publicKey);
            System.out.println("公钥加密1_2:" + Base64.encode(encryptData2));

            //数据解密
            byte[] decryptData = RSAUtil.decryptByPrivateKey(encryptData,privateKey);
            byte[] decryptData2 = RSAUtil.decryptByPrivateKey(encryptData2,privateKey);
            System.out.println("私钥解密1_1:" + Base64.encode(decryptData));
            System.out.println("私钥解密1_2:" + Base64.encode(decryptData2));

            String decryptDataStr = new String(decryptData);

            String decryptDataStr2 = new String(decryptData2);
            System.out.println("私钥解密1_1:" + decryptDataStr);
            System.out.println("私钥解密1_2:" + decryptDataStr2);
            Assert.assertEquals(dataStr,decryptDataStr);
            Assert.assertEquals(dataStr,decryptDataStr2);
        }
        catch(Exception e){

        }

    }
}
