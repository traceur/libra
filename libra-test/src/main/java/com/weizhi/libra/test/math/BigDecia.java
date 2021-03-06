
package com.weizhi.libra.test.math;

import java.math.BigDecimal;

/**
 * @author Linchong
 * @version Id: BigDecia.java, v 0.1 2016/9/29 17:20 Linchong Exp $$
 * @Description TODO
 */
public class BigDecia {
    public static void main(String[] args){
        BigDecimal result = new BigDecimal(80).divide(new BigDecimal(100));
        BigDecimal baseDeci = new BigDecimal("0.8");
        BigDecimal baseDec = new BigDecimal("502.5");
        System.out.println(baseDec.toString());

        System.out.println(baseDeci.compareTo(result));

        System.out.println(result.compareTo(baseDeci));

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MIN_VALUE);

        Long va= 1501L;

        double val = va / 100;

        System.out.println((double)va / 100);

    }
}
