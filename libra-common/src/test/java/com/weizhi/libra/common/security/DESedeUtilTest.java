package com.weizhi.libra.common.security;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;
/**
 * Created by weizhi on 2015/9/7.
 */
public class DESedeUtilTest {

    @Test
    public void test() throws Exception{
        String inputStr = "orderid=201509291415123000001&phone=18112345678&amount=30";

        byte[] intputData = inputStr.getBytes();

        byte[] key = DESedeUtil.initKey();

        System.out.println("key" + Base64.encodeBase64String(key));

        byte[] encryptData = DESedeUtil.encryptData(intputData,key);

        System.out.println("encrypt:" + Base64.encodeBase64String(encryptData));

        byte[] outputData = DESedeUtil.decodeData(encryptData,key);

        String outputStr = new String(outputData);

        Assert.assertEquals(inputStr, outputStr);
    }
}
