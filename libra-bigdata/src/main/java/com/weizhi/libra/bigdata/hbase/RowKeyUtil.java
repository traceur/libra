/**
 * MEIZU Technology Co., Ltd.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.weizhi.libra.bigdata.hbase;

import java.util.Random;

import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.MD5Hash;

/**
 * @author weizhansong
 * @version Id: RowKeyUtil.java, v 0.1 2017/3/14 12:23 weizhansong Exp $$
 * @Description TODO
 */
public class RowKeyUtil {
    private static final Integer DEFAULT_PARTITION = 20;


    /**
     * 对rowkey MD5 hash
     * 
     * @param rowKey
     * @return
     */
    public static byte[] hashRowkey(String rowKey) {
        if (rowKey == null) {
            return null;
        }
        return Bytes.toBytes(MD5Hash.getMD5AsHex(Bytes.toBytes(rowKey)));
    }


    /**
     * 字符串反转
     * 
     * @param rowKey
     * @return
     */
    public static byte[] reverseRowKey(String rowKey) {
        return Bytes.toBytes(reverseStrRowKey(rowKey));
    }


    /**
     * 字符串反转
     * 
     * @param rowKey
     * @return
     */
    public static String reverseStrRowKey(String rowKey) {
        if (rowKey == null)
            return null;
        int rowKeyLength = rowKey.length();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < rowKeyLength; i++) {
            buffer.append(rowKey.charAt(rowKeyLength - 1 - i));
        }
        return buffer.toString();
    }


    /**
     * Long 字节反转
     * 
     * @param rowKey
     * @return
     */
    public static byte[] reverseLongRowkey(String rowKey) {
        byte[] reversedArray = null;
        if (rowKey == null) {
            return null;
        }
        try {
            long t = Long.parseLong(rowKey);
            reversedArray = reverseBytes(Bytes.toBytes(t));
        } catch (Exception e) {
        }
        return reversedArray;
    }


    /**
     * Integer字节反转
     * 
     * @param rowKey
     * @return
     */
    public static byte[] reverseIntegerRowkey(String rowKey) {
        if (rowKey == null) {
            return null;
        }
        byte[] reversedArray = null;
        try {
            int t = Integer.parseInt(rowKey);
            reversedArray = reverseBytes(Bytes.toBytes(t));
        } catch (Exception e) {
        }
        return reversedArray;
    }


    /**
     * 字节反转
     * 
     * @param rawArray
     * @return
     */
    public static byte[] reverseBytes(byte[] rawArray) {
        int rowKeyLength = rawArray.length;

        byte[] reversedArray = new byte[rowKeyLength];

        for (int i = 0; i < rowKeyLength; i++) {
            reversedArray[i] = rawArray[rowKeyLength - 1 - i];
        }
        return reversedArray;
    }


    /**
     * rowkey 分区
     * 
     * @param rowKey
     * @param partition
     * @return
     */
    public static byte[] partitionRowkey(String rowKey, Integer partition) {
        if (rowKey == null) {
            return null;
        }
        if (partition == null || partition == 0) {
            partition = DEFAULT_PARTITION;
        }
        byte[] rawBytes = Bytes.toBytes(rowKey);
        int partitionId = sum(rawBytes) % partition;
        return Bytes.add(Bytes.toBytes(partitionId), rawBytes);
    }


    /**
     * Rowkey 分区
     * 
     * @param rowKey
     * @return
     */
    public static byte[] partitionRowkey(String rowKey) {
        return partitionRowkey(rowKey, DEFAULT_PARTITION);
    }

    /**
     * 生成6位随机数
     *
     * @return
     */
    public static String generatRan6() {
        Random random = new Random();
        int code = random.nextInt(999999);
        return String.format("%06d", code);
    }

    /**
     * 生成3位随机数
     *
     * @return
     */
    public static String generatRan3() {
        Random random = new Random();
        int code = random.nextInt(999);
        return String.format("%03d", code);
    }

    public static int sum(byte[] bytes) {
        int sum = 0;
        for (byte b : bytes) {
            sum += Math.abs(b);
        }
        return sum;
    }
}
