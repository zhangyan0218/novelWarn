package com.zy.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Mike
 */
public class JDBCHelper {

	private static String Username = "";
	private static String Password = "";
	private static String appName = "novelwarn";
	private static final String Driver="com.mysql.jdbc.Driver";
	private static String URL="";
    public static HashMap<String, JdbcTemplate> templateMap = new HashMap<String, JdbcTemplate>();

    static{
    	Map<String,String> map = ReadProperties.readProperties(null);
		Username = map.get("saeAccessKey");
		Password = map.get("sarSecretKey");
		appName = map.get("saeAppName");
		URL = map.get("saeUrl");
    }
    public static JdbcTemplate createMysqlTemplate(String templateName) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(Driver);
        dataSource.setUrl(URL + appName);
        dataSource.setUsername(Username);
        dataSource.setPassword(Password);
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(30);
        JdbcTemplate template = new JdbcTemplate(dataSource);
        templateMap.put(templateName, template);
        return template;
    }

    public static JdbcTemplate getJdbcTemplate(String templateName){
        return templateMap.get(templateName);
    }

}
