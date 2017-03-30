package com.weizhi.libra.common.security;

import org.junit.Test;

import java.util.zip.CRC32;

/**
 * Created by weizhi on 2015/7/31.
 */
public class CRCTest {
    @Test
    public void testCRC32() throws Exception{
        String str = "Test CRC32";
        CRC32 crc = new CRC32();
        crc.update(str.getBytes());

        String hexStr = Long.toHexString(crc.getValue());
        System.out.println(hexStr);

    }
}
