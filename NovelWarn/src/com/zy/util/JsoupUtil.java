package com.zy.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupUtil {
	
	
	/**
	 * 获取doc对象
	 * @param url
	 * @return
	 */
	public static Document getDoc(String url){
		try {
			return Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
