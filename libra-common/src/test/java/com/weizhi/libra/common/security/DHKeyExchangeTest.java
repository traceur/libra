package com.weizhi.libra.common.security;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @author Linchong
 * @version Id: DHKeyExchangeTest.java, v 0.1 2017/4/14 10:17 Linchong Exp $$
 * @Description Diffie-Hellman密钥交换算法
 */
public class DHKeyExchangeTest {
    public static void main(String[] args) {
        // Client端素数-会发给server端
        Integer clientPrimeNum = createPrime();
        // Server端素数-会发给client端
        Integer serverPrimeNum = createPrime();

        System.out.println("A=" + clientPrimeNum + " B=" + serverPrimeNum);

        // Client端随机数（暂定10以内），不共享
        int clientRandom = createRandom();
        // Server端随机数（暂定10以内），不共享
        int serverRandom = createRandom();

        System.out.println("x=" + clientRandom + " y=" + serverRandom);
        ;
        Long clientValue = new BigDecimal(Math.pow(serverPrimeNum, clientRandom)).longValue()
                % clientPrimeNum;
        Long serverValue = new BigDecimal(Math.pow(serverPrimeNum, serverRandom)).longValue()
                % clientPrimeNum;

        System.out.println(clientValue + "  " + serverValue);

        Long clientKey = new BigDecimal(Math.pow(serverValue, clientRandom)).longValue()
                % clientPrimeNum;
        Long serverKey = new BigDecimal(Math.pow(clientValue, serverRandom)).longValue()
                % clientPrimeNum;

        System.out.println(clientKey + "  " + serverKey);

    }


    /**
     * 生成随机数
     * 
     * @return
     */
    public static Integer createRandom() {
        Random ran = new Random();
        return ran.nextInt(10);
    }


    /**
     * 生成素数 暂定100以内
     * 
     * @return
     */
    public static Integer createPrime() {
        Random ran = new Random();
        while (true) {
            int ret = ran.nextInt(100);
            if (isPrime(ret)) {
                return ret;
            }
        }
    }


    /**
     * 判断是否素数
     * 
     * @param num
     * @return
     */
    public static boolean isPrime(Integer num) {
        if (num == 0 || num == 1 || num == 2) {
            return false;
        }
        int k = (int) Math.sqrt(num);
        for (int j = 2; j <= k; j++) {
            if (num % j == 0) {
                return false;
            }
        }
        return true;
    }

}
