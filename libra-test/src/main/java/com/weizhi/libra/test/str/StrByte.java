package com.weizhi.libra.test.str;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author Linchong
 * @version Id: StrByte.java, v 0.1 2017/3/21 17:07 Linchong Exp $$
 * @Description TODO
 */
public class StrByte {
    public static void main(String[] args) throws UnsupportedEncodingException{
        String test = "a";
        byte[] b = test.getBytes();
        
        //// TODO: 2017/3/21
        String s=new String(b,"ascii");//第二个参数指定编码方式
        System.out.print(s);
    }
}
