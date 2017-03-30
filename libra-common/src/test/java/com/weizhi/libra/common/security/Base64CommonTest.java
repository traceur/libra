package com.weizhi.libra.common.security;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by weizhi on 2015/7/30.
 */
public class Base64CommonTest {
    @Test
    public void test() throws Exception{

        String data = "竹杖芒鞋轻胜马，谁怕？一蓑烟雨任平生。";

        String encryptStr = Base64Common.encoder(data);
        System.out.println("encrypt data:"+encryptStr);

        String encryptStrSafe = Base64Common.encoderSafe(data);
        System.out.println("encrypt data:"+encryptStrSafe);
        String decryptStr = Base64Common.decoder(encryptStr);
        String decryptStrSafe = Base64Common.decoder(encryptStrSafe);
        Assert.assertEquals(data, decryptStr);
        Assert.assertEquals(data, decryptStrSafe);
    }
}
