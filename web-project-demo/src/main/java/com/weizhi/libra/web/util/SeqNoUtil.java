
package com.weizhi.libra.web.util;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

/**
 * @author weizhi
 * @version Id: CodeUtil.java, v 0.1 2016/7/26 19:30 weizhi Exp $$
 * @Description TODO
 */
@Slf4j
public class SeqNoUtil {

    private static final String     DATE_FORMAT = "yyyyMMddHHmmssSSS";
    private static final int        MAX_RAN_NUM = 999;
    private static SimpleDateFormat sdf         = new SimpleDateFormat(DATE_FORMAT);
    private static char             ipc;


    /**
     * 生成3位随机数
     * 
     * @return
     */
    public static String generatRan() {
        Random random = new Random();
        int code = random.nextInt(MAX_RAN_NUM);
        return String.format("%03d", code);
    }


    /**
     * @return 形如 yyyyMMddHHmmssSSS-Z0000019558195832297 的(38位)保证唯一的递增的序列号字符串，
     *         主要用于数据库的主键，方便基于时间点的跨数据库的异步数据同步。
     *         前半部分是currentTimeMillis，后半部分是nanoTime（正数）补齐20位的字符串，
     *         如果通过System.nanoTime()获取的是负数，则通过nanoTime =
     *         nanoTime+Long.MAX_VALUE+1; 转化为正数或零。
     */
//    public static String getTimeMillisSequence() {
//        long nanoTime = System.nanoTime();
//        String preFix = "";
//        if (nanoTime < 0) {
//            preFix = "A";//负数补位A保证负数排在正数Z前面,解决正负临界值(如A9223372036854775807至Z0000000000000000000)问题。
//            nanoTime = nanoTime + Long.MAX_VALUE + 1;
//        } else {
//            preFix = "Z";
//        }
//        String nanoTimeStr = String.valueOf(nanoTime);
//
//        int difBit = String.valueOf(Long.MAX_VALUE).length() - nanoTimeStr.length();
//        for (int i = 0; i < difBit; i++) {
//            preFix = preFix + "0";
//        }
//        nanoTimeStr = preFix + nanoTimeStr;
//        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT); //24小时制
//        String timeMillisSequence = sdf.format(System.currentTimeMillis()) + "-" + nanoTimeStr;
//
//        return timeMillisSequence;
//    }
//

    /**
     * 芝麻请求流水号
     * 
     * @return
     */
    public static String getZhimaTimeMillisSequence() {
        long nanoTime = System.nanoTime();
        if (nanoTime < 0) {
            nanoTime = nanoTime + Long.MAX_VALUE + 1;
        }
        String nanoTimeStr = String.format("%013d", nanoTime);
        if (nanoTimeStr.length() > 13) {
            nanoTimeStr = nanoTimeStr.substring(nanoTimeStr.length() - 13, nanoTimeStr.length());
        }
        return sdf.format(System.currentTimeMillis()) + nanoTimeStr;
    }


    /**
     * 生成日志流水号
     * 
     * @return
     */
    public static String getLogSeqNo() {
        long nanoTime = System.nanoTime();
        String preFix = "";
        if (nanoTime < 0) {
            preFix = "A";//负数补位A保证负数排在正数Z前面,解决正负临界值(如A9223372036854775807至Z0000000000000000000)问题。
            nanoTime = nanoTime + Long.MAX_VALUE + 1;
        } else {
            preFix = "Z";
        }
        String nanoTimeStr = String.valueOf(nanoTime);
        nanoTimeStr = preFix + nanoTimeStr;
        return nanoTimeStr;
    }


    /**
     * 生成操作流水号
     * 
     * @return
     */
    public static String getOperSeg() {
        //24小时制
        return sdf.format(System.currentTimeMillis()) + "" + generatRan() + generatRan();

    }


    /**
     * 生成code
     * 
     * @return
     */
    public static String createCode() {
        return sdf.format(System.currentTimeMillis()) + "" + generatRan();
    }


    /**
     * 获取本机IP最后一位
     *
     * @return
     */
    public static char getLastIpC() {
        if (ipc == '\0') {
            try {
                InetAddress addr = InetAddress.getLocalHost();
                String ip = addr.getHostAddress().toString();
                ipc = ip.charAt(ip.length() - 1);
            } catch (Exception e) {
                ipc = '0';
            }
        }
        return ipc;
    }


    /**
     * 当前线程id最后一位
     * 
     * @return
     */
    public static char getLastThreadId() {
        try {
            String tid = String.valueOf(Thread.currentThread().getId());
            return tid.charAt(tid.length() - 1);
        } catch (Exception e) {
            return '0';
        }
    }


    /**
     * 生成订单流水号
     *
     * @return
     */
    public static String createBillNo() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT); //24小时制
        return String.format("%s%c%c%s", sdf.format(System.currentTimeMillis()), getLastIpC(),
                getLastThreadId(), generatRan());
    }


    public static void main(String[] args) {
        System.out.println(getZhimaTimeMillisSequence());
        System.out.println(getZhimaTimeMillisSequence().length());
    }

}
