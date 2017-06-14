package com.weizhi.libra.bigdata.hive;

import com.weizhi.libra.bigdata.hive.log.LogHelper;
import org.apache.commons.lang.time.StopWatch;
import org.apache.hive.jdbc.HiveConnection;
import org.apache.hive.jdbc.HiveStatement;

import java.sql.*;
import java.util.List;

/**
 * @author Linchong
 * @version Id: HiveJdbcRun.java, v 0.1 2017/3/20 10:02 Linchong Exp $$
 * @Description TODO
 */
public class HiveJdbcRun {

    public static void main(String[] args) {
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            //String url = "jdbc:hive2://172.16.10.254:10001/default"
            String url = "jdbc:hive2://10.3.149.67:10004/ext_metis";
            HiveConnection conn = (HiveConnection) DriverManager
                    .getConnection(url, "ext_metis", "");
            HiveStatement stmt = (HiveStatement)conn.createStatement();
//            ResultSet rs = stmt.executeQuery("select count(1) from ad.teststring1");


//            String useSql = "USE test_cxb;"
//                    +"DROP TABLE IF EXISTS mysql2hive_odl_cxb0152;"
//                    +"CREATE EXTERNAL TABLE test_cxb.mysql2hive_odl_cxb0152"
//                    +"("
//                    +"       `id` bigint,"
//                    +"       `name` string,"
//                    +"       `stat_date` string"
//                    +")"
//                    +"ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.columnar.ColumnarSerDe' WITH SERDEPROPERTIES ('serialization.null.format'='') STORED AS RCFILE "
//                    +"LOCATION '/user/hive/warehouse/test_cxb.db/mysql2hive_odl_cxb0152/';";
//            String[] sqls = useSql.split(";");
//            for(String s:sqls) {
//                boolean ret = stmt.execute(s);
//                System.out.println(ret);
//            }
//            //            showtables(stmt);
//            System.out.println("=============data=================");

            StopWatch stopWatch = new
                    StopWatch();
            stopWatch.start();

            Thread t1 =  new Thread(new LogHelper(stmt));
            t1.start();
            ResultSet rs = stmt.executeQuery("select count(1) from ext_metis.ods_fastdfs_power_log where stat_date = 20170612 limit 1");
            stopWatch.stop();
            System.out.println(stopWatch.toString());

//            t1.interrupt();
            t1.join();
//            ;
//
            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 0; i < meta.getColumnCount(); i++) {
                //                System.out.println(meta);
                String columnName = meta.getColumnLabel(i + 1);
//                int dotIndex = columnName.indexOf(".");
//                if (dotIndex > 0) {
//                    columnName = columnName.substring(dotIndex + 1);
//                }
                System.out.println(columnName);

            }
////
//            System.out.println(rs.getFetchSize());
//
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
//            //            while (rs.next()) {
//            //                ResultSetMetaData meta = rs.getMetaData();
//            //                for(int i=1;i<meta.getColumnCount();i++){
//            //                    System.out.println(rs.getString(i));
//            //                }
//            //            }
//            conn.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
