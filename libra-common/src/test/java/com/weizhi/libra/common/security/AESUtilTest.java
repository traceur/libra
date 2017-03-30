package com.weizhi.libra.common.security;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by weizhi on 2015/9/8.
 */
public class AESUtilTest {

    @Test
    public void test() throws Exception{
        String inputStr = "AESAES9999999999999";

        byte[] intputData = inputStr.getBytes();

        byte[] key = AESUtil.initKey();

        System.out.println("key" + Base64.encodeBase64String(key));

        byte[] encryptData = AESUtil.encryptData(intputData,key);

        System.out.println("encrypt:" + Base64.encodeBase64String(encryptData));

        byte[] outputData = AESUtil.decodeData(encryptData,key);

        String outputStr = new String(outputData);

        Assert.assertEquals(inputStr, outputStr);
    }
}
