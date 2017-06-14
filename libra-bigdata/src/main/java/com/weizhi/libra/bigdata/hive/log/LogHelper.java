package com.weizhi.libra.bigdata.hive.log;

import java.util.List;

import org.apache.hive.jdbc.HiveStatement;

/**
 * @author Linchong
 * @version Id: LogHelper.java, v 0.1 2017/6/13 14:28 Linchong Exp $$
 * @Description TODO
 */
public class LogHelper implements Runnable {
    private HiveStatement stmt;


    public LogHelper(HiveStatement stmt) {
        this.stmt = stmt;
    }


    public void run() {
        while (stmt.hasMoreLogs()) {
            try {
                List<String> logs = stmt.getQueryLog();
                for (String log : logs) {
                    System.out.println(log);
                }

                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }
    }
}
