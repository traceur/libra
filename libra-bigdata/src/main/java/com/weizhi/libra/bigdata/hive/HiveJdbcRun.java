package com.weizhi.libra.bigdata.hive;

import java.sql.*;

/**
 * @author Linchong
 * @version Id: HiveJdbcRun.java, v 0.1 2017/3/20 10:02 Linchong Exp $$
 * @Description TODO
 */
public class HiveJdbcRun {

    public static void main(String[] args) {
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            //jdbc:hive2://172.16.10.254:10001/default
            //jdbc:hive2://10.3.149.15:10000/default
            Connection conn = DriverManager.getConnection("jdbc:hive2://10.3.149.18:10000/default",
                    "hive", "hive");
            Statement stmt = conn.createStatement();
            //            showtables(stmt);
            System.out.println("=============data=================");
            ResultSet rs = stmt.executeQuery("select * from test limit 1 ");
            ;

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 0; i < meta.getColumnCount(); i++) {
                //                System.out.println(meta);
                String columnName = meta.getColumnLabel(i + 1);
                int dotIndex = columnName.indexOf(".");
                if (dotIndex > 0) {
                    columnName = columnName.substring(dotIndex + 1);
                }
                System.out.println(columnName);

            }

            System.out.println(rs.getFetchSize());

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            //            while (rs.next()) {
            //                ResultSetMetaData meta = rs.getMetaData();
            //                for(int i=1;i<meta.getColumnCount();i++){
            //                    System.out.println(rs.getString(i));
            //                }
            //            }
            conn.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
