package com.zy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class DBUtil {
	private static String Username = "";
	private static String Password = "";
	private static String appName = "novelwarn";
	
	static{
		Map<String,String> map = ReadProperties.readProperties(null);
		Username = map.get("saeAccessKey");
		Password = map.get("sarSecretKey");
		appName = map.get("saeAppName");
	}
	
	public static void main(String[] args) {
		Connection ct = getCon();
		
		System.out.println(1);
	}
	
	public void test(){
		//app_name为创建的应用名
	    String URL="jdbc:mysql://r.rdc.sae.sina.com.cn:3307/"+appName;
	    //在SAE上创建的studnt表
	    String sql = "SELECT * FROM student";
	    // 通过SaeUserInfo提供的静态方法获取应用的access_key和secret_key  对应的SaeUserInfo是自己创建的，使用SAE提供的key
	    String Driver="com.mysql.jdbc.Driver";
	    try{
	    Class.forName(Driver).newInstance();
	    Connection con=DriverManager.getConnection(URL,Username,Password);	
	    PreparedStatement pstmt = con.prepareStatement("select * from student");
	    ResultSet rs = (ResultSet) pstmt.executeQuery();
	    while(rs.next()){
	      rs.getString("username");
	      System.out.println(rs.getInt("id"));
	    }
	    }catch(Exception e){
	      e.printStackTrace();
	    }
	}
	
	/**
	 * 获取数据库链接
	 * @return
	 */
	public static Connection getCon(){
		//app_name为创建的应用名
	    String URL="jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_"+appName;
	    String Driver="com.mysql.jdbc.Driver";
	    try {
			Class.forName(Driver).newInstance();
			return DriverManager.getConnection(URL,Username,Password);	
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
