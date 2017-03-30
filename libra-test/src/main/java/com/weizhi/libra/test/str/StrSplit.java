package com.weizhi.libra.test.str;

import java.util.regex.Pattern;

/**
 * @author Linchong
 * @version Id: StrSplit.java, v 0.1 2017/3/28 17:57 Linchong Exp $$
 * @Description TODO
 */
public class StrSplit {
    public static void main(String[] args){


        String sql = "select * from test; select * from test2 where a='1;'";

        System.out.println(sql.replaceAll("'[^'];'",""));

        System.out.println(sql.matches("\'[^\']*\'"));

        String test =  "select a `测试` from  test";
        System.out.println(Pattern.compile("`[^`]*`").matcher(test).matches());
        System.out.println(test.replaceAll("`[^`]*`",""));
    }
}
