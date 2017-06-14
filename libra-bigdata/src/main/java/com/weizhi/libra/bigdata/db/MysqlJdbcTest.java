package com.weizhi.libra.bigdata.db;

import java.sql.*;
import java.util.List;
import java.util.Map;

//import org.apache.commons.dbutils.QueryRunner;
//import org.apache.commons.dbutils.handlers.MapListHandler;

public class MysqlJdbcTest {
	//mysql -h10.3.167.249 -P4319 -umeizu_cetus -pLhYP4vExxREI88pm MEIZU_CETUS_QUERY
	public static final String MYSQL_CONN_STR = "jdbc:mysql://172.16.193.232:3306/MEIZU_CETUS?useUnicode=true&characterEncoding=utf-8";
	public static final String MYSQL_USER = "mysqluser";
	public static final String MYSQL_PASSWORD = "mysqluser";
//	public static final QueryRunner q = new QueryRunner();

	public static void main(String[] args) throws Exception {
		String sql = "select * from T_PRODUCT where FPRODUCT_PID = -1";

		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(MYSQL_CONN_STR, MYSQL_USER, MYSQL_PASSWORD);
		conn.setAutoCommit(false);
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		rs = stmt.executeQuery();
		ResultSetMetaData meta = rs.getMetaData();

		for (int i = 0; i < meta.getColumnCount(); i++) {
			String columnName = meta.getColumnLabel(i + 1);
			int dotIndex = columnName.indexOf(".");
			if(dotIndex > 0){
				columnName = columnName.substring(dotIndex + 1);
			}
			System.out.println(columnName);

		}

		while (rs.next()){
			System.out.println(rs.getString(3));
		}
//		List<Map<String, Object>> r = q.query(conn, sql, new MapListHandler());
//
//		boolean printHead = false;
//		for (Map<String, Object> map : r) {
//			if (!printHead) {
//				System.out.println(map.keySet());
//				printHead = true;
//			}
//
//			for (String k : map.keySet()) {
//				Object v = map.get(k);
//				System.out.print(v + " ");
//			}
//			System.out.println();
//		}

		if (conn != null)
			conn.close();
	}
}
