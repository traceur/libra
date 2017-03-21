
package com.weizhi.libra.common.security;

/**
 * @author Linchong
 * @version Id: RC4Util.java, v 0.1 2017/3/6 10:30 Linchong Exp $$
 * @Description 加密解密 参数为String 字符串（String data, String key) * * @param data * @param
 *              key *
 */
public class RC4Util {
    /**
     * 加密解密 参数为String 字符串（String data, String key)
     *
     * @param data
     * @param key
     * @return String
     */
    public static final int vactorLen = 256;


    private static String encodeAndDecodeStr(String data, String key) {
        // 1.参数检查
        if (data != null && data.length() > 0 && key != null && key.length() > 0) {
            // 2. 初始化算法
            // 2.1定义矢量vactor
            int[] vactor = new int[vactorLen];
            // 2.2定义临时变量tempk
            byte[] tempK = new byte[vactorLen];
            // 2.3给vactor赋值ֵ0-255
            for (int i = 0; i < vactorLen; i++)
                vactor[i] = i;
            int j = 1;
            // 2.4循环给tempK赋值
            for (short i = 0; i < vactorLen; i++) {
                tempK[i] = (byte) key.charAt((i % key.length()));
            }
            j = 0;
            // 2.5 置换vactor值
            for (int i = 0; i < 255; i++) {
                j = (j + vactor[i] + tempK[i]) % vactorLen;
                int temp = vactor[i];
                vactor[i] = vactor[j];
                vactor[j] = temp;
            }
            int i = 0;
            j = 0;
            // 2.6把data定义成char[]数组dataChar
            char[] dataChar = data.toCharArray();
            // 2.7计算数组dataChar[]长度，用InputCharLen接收。
            int charLen = dataChar.length;
            // 2.8 定义 长度为InputCharLen的char数组iOutputChar[]
            char[] resultChar = new char[charLen];
            // 2.9 加密解密算法
            for (short x = 0; x < charLen; x++) {
                i = (i + 1) % vactorLen;
                j = (j + vactor[i]) % vactorLen;
                int temp = vactor[i];
                vactor[i] = vactor[j];
                vactor[j] = temp;
                char tempChar = (char) vactor[(vactor[i] + (vactor[j] % vactorLen)) % vactorLen];
                resultChar[x] = (char) (dataChar[x] ^ tempChar);
            }
            // 3. 输出结果
            return new String(resultChar);
        }
        return null;
    }


    /**
     * 二进制转换16进制*********
     *
     * @param bytes
     * @return
     */
    private static String parseByte2HexStr(byte bytes[]) {
        // 参数检查
        if (bytes != null && bytes.length > 0) {
            // 定义b长度为bLenth
            int bLenth = bytes.length;
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < bLenth; i++) {
                String hex = Integer.toHexString(bytes[i] & 0xFF);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                stringBuffer.append(hex.toUpperCase());
            }
            return stringBuffer.toString();
        }
        return null;
    }


    public static void main(String[] args) {
        String data = "sTest";
        String key = "test";
        // 原文
        System.out.println("加密前：" + data);
        // 密文
        String str = null;
        if (data != null && data.length() > 0) {
            str = encodeAndDecodeStr(data, key);
            System.out.println("加密后：" + parseByte2HexStr(str.getBytes()));
            // 解密
            System.out.println("解密后：" + new String(encodeAndDecodeStr(str, key)));
        }
    }

}
