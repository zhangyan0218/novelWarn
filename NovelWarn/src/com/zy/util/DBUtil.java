package com.zy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class DBUtil {
	private static String Username = "";
	private static String Password = "";
	private static String appName = "novelwarn";
	private static final String Driver="com.mysql.jdbc.Driver";
	private static String URL="";
	
	static{
		Map<String,String> map = ReadProperties.readProperties(null);
		Username = map.get("saeAccessKey");
		Password = map.get("sarSecretKey");
		appName = map.get("saeAppName");
		URL = map.get("saeUrl");
	}
	
	public static void main(String[] args) {
		Connection ct = getCon();
		System.out.println(1);
	}
	
	public void addTest(){
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getCon();
			stmt = conn.createStatement();
			String sql = "insert into t(name,age) values('fff',18)";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * 获取数据库链接
	 * @return
	 */
	public static Connection getCon(){
	    try {
			Class.forName(Driver).newInstance();
			return DriverManager.getConnection(URL + appName,Username,Password);	
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
