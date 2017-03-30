package com.weizhi.libra.common.security;

import org.bouncycastle.util.encoders.Hex;
import org.junit.Assert;
import org.junit.Test;
/**
 * Created by weizhi on 2015/8/4.
 */
public class MacUtilTest {
    @Test
    public void test() throws  Exception{
        String data = "123455789";
        byte[] key = MACUtil.initHmacMD5Key();
        byte[] encodeResult1 = MACUtil.encodeHmacMD5(data.getBytes(),key);
        System.out.println(new String(Hex.encode(key)));
        byte[] encodeResult2 = MACUtil.encodeHmacMD5(data.getBytes(),key);
        System.out.println(new String(Hex.encode(encodeResult1)));
        Assert.assertArrayEquals(encodeResult1,encodeResult2);
    }
}
