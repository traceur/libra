package com.weizhi.libra.common.security;

import org.bouncycastle.util.encoders.Hex;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by weizhi on 2015/7/31.
 */
public class MDUtilTest {
    @Test
    public void testMD2AndMD5() throws Exception{
        String data = "Test";
        byte[] b2_1 = MDUtil.encodeMD2(data.getBytes());
        byte[] b4_1 = MDUtil.encodeMD4(data.getBytes());
        byte[] b5_2 = MDUtil.encodeMD5(data.getBytes());
        System.out.println(new String(Hex.encode(b2_1)));
        System.out.println(new String(Hex.encode(b4_1)));
        System.out.println(new String(Hex.encode(b5_2)));
        String b5_3 = MDUtil.encodeMD5ToHex(data);
        Assert.assertNotSame(b2_1, b5_2);
        Assert.assertNotSame(b4_1, b5_2);
    }
}
