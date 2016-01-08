package com.zy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.zy.util.JDBCHelper;

public class BaseDao {
	private static JdbcTemplate jdbcTemplate = null;
	static{
		jdbcTemplate = JDBCHelper.createMysqlTemplate("novelWarn_jdbcTemp1");
	}
	
	public static Integer add(String sql,Object[] objs){
		return jdbcTemplate.update(sql,objs);
	}
	
	public static Boolean delete(){
		
		return false;
	}
	
	
	public static Integer update(String sql,Object[] objs){
		return jdbcTemplate.update(sql,objs);
	}
	
	
	public static List<Map<String, Object>> select(String sql,Object[] objs){
		return jdbcTemplate.queryForList(sql,objs);
	}
	
	public static List<Map<String, Object>> select(String sql){
		return jdbcTemplate.queryForList(sql);
	}
}
