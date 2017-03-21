package com.weizhi.libra.test.bit;

/**
 * @author Linchong
 * @version Id: ByteNums.java, v 0.1 2017/3/21 15:35 Linchong Exp $$
 * @Description TODO
 */
public class ByteNums {
    public static void main(String[] args) {
        // java 各基本类型 占的bit位数，除以8 就是字节数

        System.out.println("int:" + Integer.SIZE / 8);
        System.out.println("short:" + Short.SIZE / 8);
        System.out.println("long:" + Long.SIZE / 8);
        System.out.println("byte:" + Byte.SIZE / 8);
        System.out.println("float:" + Float.SIZE / 8);
        System.out.println("double:" + Double.SIZE / 8);

        // char是一个16位二进制的Unicode字符 ,占两个字节，可以存储一个汉字
        System.out.println("char:" + Character.SIZE / 8);
    }
}
