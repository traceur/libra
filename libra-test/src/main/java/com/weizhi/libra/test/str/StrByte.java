package com.weizhi.libra.test.str;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author Linchong
 * @version Id: StrByte.java, v 0.1 2017/3/21 17:07 Linchong Exp $$
 * @Description TODO
 */
public class StrByte {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String test = "&!@#@#$%^&*";
        Long x = 1L;
        byte[] b = test.getBytes();

        //// TODO: 2017/3/21

        System.out.print(Arrays.toString(b));

        System.out.print(Arrays.toString(x.toString().getBytes()));

        byte[] bs = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };

        System.out.println(new String(bs));
    }
}
