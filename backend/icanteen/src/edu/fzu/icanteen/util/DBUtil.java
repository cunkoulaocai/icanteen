package edu.fzu.icanteen.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	static String ip = "127.0.0.1";
	static int port = 3306;
	static String database = "icanteen";
	static String encoding = "UTF-8";
	static String loginName = "root";
	static String password = "root";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		String url = String.format("jdbc:mysql://localhost:%d/%s?characterEncoding=%s&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true", port, database, encoding);
		return DriverManager.getConnection(url, loginName, password);
	}

	/* 关闭连接的方法 */
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		System.out.println(getConnection());
	}
}