package com.weizhi.libra.test.str;

import java.util.regex.Pattern;

/**
 * @author Linchong
 * @version Id: StrSplit.java, v 0.1 2017/3/28 17:57 Linchong Exp $$
 * @Description TODO
 */
public class StrSplit {
    private static final Long MSEC_PER_DAY    = 24 * 60 * 60 * 1000L;
    private static final Long MSEC_PER_HOUR   = 60 * 60 * 1000L;
    private static final Long MSEC_PER_MINUTE = 60 * 1000L;
    private static final Long MSEC_PER_SECOND = 1000L;


    public static void main(String[] args) {

        String sql = "select * from test; select * from test2 where a='1;'";

        System.out.println(sql.replaceAll("'[^'];'", ""));

        System.out.println(sql.matches("\'[^\']*\'"));

        String test = "select a `测试` from  test";
        System.out.println(Pattern.compile("`[^`]*`").matcher(test).matches());
        System.out.println(test.replaceAll("`[^`]*`", ""));

        String cpu = "0 days 1 hours 16 minutes 45 seconds 440 msec";

        System.out.println(cpu.trim());
        String[] cpuTimeArr = cpu.trim().split(" ");
        Long cpuTime = 0L;
        for (int i = 0; i < cpuTimeArr.length; i = i + 2) {
            if ("days".equalsIgnoreCase(cpuTimeArr[i + 1].trim())) {
                cpuTime += tryStr2Int(cpuTimeArr[i]) * MSEC_PER_DAY;
                continue;
            }
            if ("hours".equalsIgnoreCase(cpuTimeArr[i + 1].trim())) {
                cpuTime += tryStr2Int(cpuTimeArr[i]) * MSEC_PER_HOUR;
                continue;
            }

            if ("minutes".equalsIgnoreCase(cpuTimeArr[i + 1].trim())) {
                cpuTime += tryStr2Int(cpuTimeArr[i]) * MSEC_PER_MINUTE;
                continue;
            }

            if ("seconds".equalsIgnoreCase(cpuTimeArr[i + 1].trim())) {
                cpuTime += tryStr2Int(cpuTimeArr[i]) * MSEC_PER_SECOND;
                continue;
            }
            if ("msec".equalsIgnoreCase(cpuTimeArr[i + 1].trim())) {
                cpuTime += tryStr2Int(cpuTimeArr[i]);
                continue;
            }
        }

        System.out.println(cpuTime);

        String sqlx = "insert into --xxxxx  \n  select * from aa --tab";

        String[] sqlArr = sqlx.split("\n");
        for(String s:sqlArr){
            System.out.println(s.indexOf("--"));
            System.out.println(s.substring(0,s.indexOf("--")));
        }


//        Pattern p = Pattern.compile("(?ms)/\\*.*?\\*/|^\\s*//.*?$");
//        // 将注释替换成""
//        String presult = p.matcher(sqlx).replaceAll("");
//        System.out.println(presult);

    }


    public static Integer tryStr2Int(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }
}
