package com.weizhi.libra.common.security;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;
/**
 * Created by weizhi on 2015/8/6.
 */
public class DESUtilTest {
    @Test
    public void test() throws Exception{
        String dataStr = "wei34567890-zh66666666666666666666666666666666666666666i";
        byte[] data = dataStr.getBytes();

        byte[] key = DESUtil.initKey();
        System.out.println("key:"+ Base64.encodeBase64String(key));

        System.out.println("data:"+Base64.encodeBase64String(data));
        byte[] encryptData = DESUtil.encrypt(data,key);
        System.out.println("encryptData:"+Base64.encodeBase64String(encryptData));
        byte[] encryptData2 = DESUtil.encrypt(data,key);
        System.out.println("encryptData2:"+Base64.encodeBase64String(encryptData2));
        byte[] decryptData=DESUtil.decrypt(encryptData,key);

        System.out.println("decryptData:"+Base64.encodeBase64String(decryptData));

        String outStr = new String(decryptData);
        Assert.assertEquals(dataStr,outStr);

    }
}
