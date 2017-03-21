/**
 * Technology Co., Ltd.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.weizhi.libra.test.bit;

/**
 * @author Linchong
 * @version Id: OperBit.java, v 0.1 2017/3/21 14:58 Linchong Exp $$
 * @Description TODO
 */
public class OperBit {
    public static void main(String args[]) {

        // 1、左移( << )
        //  0000 0000 0000 0000 0000 0000 0000 0001   1 （int 类型 4字节）
        //  0000 0000 0000 0001 0000 0000 0000 0000  左移16位 右边补0   = 2^16 = 65536
        System.out.println(1 << 16);

        // 2、右移( >> ) 高位补符号位
        // 0000 0000 0000 0000 0000 0000 0000 0101  5
        // 0000 0000 0000 0000 0000 0000 0000 0001  右移2位，高位补0   = 1
        System.out.println(5 >> 2);

        //反码+补码  -5  二进制 为 整数 取反+1
        // 0000 0000 0000 0000 0000 0000 0000 0101 -> 取反 +1
        // 1111 1111 1111 1111 1111 1111 1111 1011

        // 3. 负数右移位( >> )
        // 1111 1111 1111 1111 1111 1111 1111 1011  -5
        // 1111 1111 1111 1111 1111 1111 1111 1111  右移3位，左补符号位
        // 0000 0000 0000 0000 0000 0000 0000 0001  取反 +1     = -1
        System.out.println(-5 >> 3);

        // 4. 无符号数右移位( >>> )  高位补0
        // 1111 1111 1111 1111 1111 1111 1111 1011  -5
        // 0001 1111 1111 1111 1111 1111 1111 1111  右移3位，高位补0   = 2^29 -1 = 536870911
        System.out.println(-5 >>> 3);


        // 5、位与( & )   都为1 则 为 1 否则为 0
        // 0000 0000 0000 0000 0000 0000 0000 0101   5
        // 0000 0000 0000 0000 0000 0000 0000 0011   3
        // 0000 0000 0000 0000 0000 0000 0000 0001   1
        System.out.println(5 & 3);// 结果为1

        // 6、位或( | )  其中一个为 1 则为1 否则为 0
        // 0000 0000 0000 0000 0000 0000 0000 0101   5
        // 0000 0000 0000 0000 0000 0000 0000 0011   3
        // 0000 0000 0000 0000 0000 0000 0000 0111   7
        System.out.println(5 | 3);// 结果为7

        // 7、位异或( ^ )   XOR、xor、⊕   相同为0 ，不同为1
        // 0000 0000 0000 0000 0000 0000 0000 0101   5
        // 0000 0000 0000 0000 0000 0000 0000 0011   3
        // 0000 0000 0000 0000 0000 0000 0000 0110   6
        System.out.println(5 ^ 3);//结果为6

        // 8、位非( ~ )  取反
        // 0000 0000 0000 0000 0000 0000 0000 0101   5
        // 1111 1111 1111 1111 1111 1111 1111 1010   有符号数 负数 取反+1
        // 0000 0000 0000 0000 0000 0000 0000 0110   6
        System.out.println(~5);// 结果为-6


        int b = 0xFF;
        System.out.println(b);
    }
}
