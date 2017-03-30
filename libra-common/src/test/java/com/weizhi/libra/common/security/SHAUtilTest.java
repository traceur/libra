package com.weizhi.libra.common.security;

import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

/**
 * Created by weizhi on 2015/8/3.
 */
public class SHAUtilTest {

    @Test
    public void test() throws Exception{
        String data = "竹杖芒鞋轻胜马，谁怕？xx一蓑烟雨任平生。";
        byte[] b160 = SHAExtUtil.encodeSHA(data.getBytes());
        byte[] b256 = SHAExtUtil.encodeSHA256(data.getBytes());
        byte[] b512 = SHAExtUtil.encodeSHA512(data.getBytes());
        String b224 = SHAExtUtil.encodeSHA224ToHex(data.getBytes());
        System.out.println("SHA:"+new String(Hex.encode(b160)));
        System.out.println("SHA-256:"+new String(Hex.encode(b256)));
        System.out.println("SHA-512:"+new String(Hex.encode(b512)));
        System.out.println("SHA-224:"+ b224);
    }
}
