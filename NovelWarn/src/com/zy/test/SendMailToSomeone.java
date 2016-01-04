package com.zy.test;

import java.util.Map;

import com.zy.util.ReadProperties;
import com.zy.util.SendMail;


public class SendMailToSomeone {
	public static void main(String []args){
		Map<String,String> map = ReadProperties.readProperties(null);
		String sendAccount = map.get("sendMailAccount");
		String sendMailPassword = map.get("sendMailPassword");
		String acceptMailAccount = map.get("acceptMailAccount");
		
		String title = "";
		String content = "";
//		SendMail.sendTemplate("标题aa", "邮件内容hello007", "384804876@qq.com","wshi_zhangyan@163.com", "Qq123456", "smtp.163.com");
		SendMail.sendTemplate("标题aa2", "邮件内容hello007");
	}
}
