package com.zy.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadProperties {
	
	/**
	 * 读取发送的邮箱配置
	 * @param fileName
	 * @return map
	 */
	public static Map<String,String> readProperties(String fileName){
		if(null == fileName){
			fileName = "config.properties";
		}
		InputStream in = ReadProperties.class.getClassLoader().getResourceAsStream(fileName);
		Properties properties = new Properties();
		try {
			properties.load(in);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("sendMailAccount", properties.getProperty("sendMailAccount"));
		map.put("sendMailPassword", properties.getProperty("sendMailPassword"));
		map.put("acceptMailAccount", properties.getProperty("acceptMailAccount"));

		map.put("saeAccessKey", properties.getProperty("saeAccessKey"));
		map.put("sarSecretKey", properties.getProperty("sarSecretKey"));
		map.put("saeAppName", properties.getProperty("saeAppName"));
		map.put("saeUrl", properties.getProperty("saeUrl"));
		
		return map;
	}
}
