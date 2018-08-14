package com.ccm.bi.task.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PromedJdbcUtils {
	
	private static final String driverUrl = "oracle.jdbc.driver.OracleDriver";

	private static final String url = "jdbc:oracle:thin:@42.159.239.54:1521:promed";//百瑞
	
	private static final String username = "ihis";
	
	private static final String password = "ihis";
	
	public static Connection getConnection(){
		long a = System.currentTimeMillis();
		Connection conn = null;
		try {
			Class.forName(driverUrl);
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			long b = System.currentTimeMillis();
            System.out.println("创建连接用时" + (b - a) + " ms");
		}
		return null;
	}
	
	public static void close(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
     * 打印信息
     * 
     * @return
     */
    public static void print(long startTime, long endTime) {
        System.out.println("用时" + (endTime - startTime) + " ms");
        System.out.println("----------------------------------");
    }
	
	public static void main(String[] args) {
		Connection connection = PromedJdbcUtils.getConnection();
		System.out.println("连接成功："+connection);
	}

}
