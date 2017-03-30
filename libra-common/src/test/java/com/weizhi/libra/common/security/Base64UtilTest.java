package com.weizhi.libra.common.security;


import org.junit.Assert;
import org.junit.Test;

/**
 * Created by weizhi on 2015/7/30.
 */
public class Base64UtilTest {
    @Test
    public void test() throws Exception{
        //Base64
        String data = "竹杖芒鞋轻胜马，谁怕？xx一蓑烟雨任平生。";

        String encryptStr = Bast64Util.encoder(data);
        System.out.println("encrypt data:"+encryptStr);

        String decryptStr = Bast64Util.decoder(encryptStr);
        Assert.assertEquals(data,decryptStr);

        //base64 url
        String dataUrl = "竹杖芒鞋轻胜马，谁怕？xx一蓑烟雨任平生。";

        String urlEncryptStr = Bast64Util.urlEncoder(data);
        System.out.println("url encrypt data:"+urlEncryptStr);

        String urlDecryptStr = Bast64Util.urlDecoder(urlEncryptStr);
        Assert.assertEquals(dataUrl,urlDecryptStr);
    }


}
