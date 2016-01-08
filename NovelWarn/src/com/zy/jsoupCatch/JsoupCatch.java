package com.zy.jsoupCatch;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.zy.dao.BaseDao;
import com.zy.util.MathUtil;
import com.zy.util.SendMail;

public class JsoupCatch {
	
	public static void main(String[] args) {
		JsoupCatch jc = new JsoupCatch();
		jc.start();
	}
	
	public void start(){
		DefaultThread dt1 = new DefaultThread(1);
		new Thread(dt1).start();
		DefaultThread dt2 = new DefaultThread(2);
		new Thread(dt2).start();
	}
	
	class DefaultThread implements Runnable {
		private int type;
		public DefaultThread(int type) {	
			this.type = type;
		}
		@Override
		public void run() {
			if(1==type){
				startW();
			}else if(2==type){
				startX();
			}
		}

	}
	
	static Integer maxChapter_w;
	static Integer maxChapter_x;
	static{
		List<Map<String,Object>> list = BaseDao.select("select chapters from novel_warn");
		maxChapter_w = Integer.valueOf(list.get(0).get("chapters").toString());
		maxChapter_x = Integer.valueOf(list.get(1).get("chapters").toString());
	}
	private static String[] url_w = {};
	public void startW(){
		
	}
	
	private static String[] url_x = {
										"http://read.qidian.com/BookReader/Y0Os8m0co-g1.aspx",
										"http://www.xuanjiezhimen.org/0_859/"
									};
	public void startX(){
		for(int i=0 ; i < url_x.length ; i++){
			String url = url_x[i];
			
			switch (url) {
				case "http://read.qidian.com/BookReader/Y0Os8m0co-g1.aspx": catchX_01(url) ;break;
				case "http://www.xuanjiezhimen.org/0_859/": catchX_02(url) ;break;
				default: break;
			}
		}
		
	}
	
	
	public void catchX_01(String url){
		Document doc =getDoc(url);
		Element e = doc.select("#content .list ul").select("li").last();
		if(null == e){
			return;
		}
		String li = e.select("span").text();
		if(null == li){
			return;
		}
		li = li.substring(1,li.indexOf("章"));
		
		checkAndUpdateMail(2, li, url);
	}
	public void catchX_02(String url){
		Document doc =getDoc(url);
		if(null == doc){
			return;
		}
		Element e = doc.select("#list dl").select("a").last();
		if(null == e){
			return;
		}
		String li = e.text();
		if(null == li){
			return;
		}
		li = li.substring(1,li.indexOf("章"));
		
		checkAndUpdateMail(2, li, url);
	}
	
	/**
	 * 判断是否新更新，若是，则修改db且发送mail
	 * @param type			1/w  2/x
	 * @param chapterOrigin 章节
	 * @param chapterLink   链接
	 */
	public void checkAndUpdateMail(Integer type,String chapterOrigin,String chapterLink){
		if(type==1){// W

		}else if(type==2){// X
			int chapter = MathUtil.chineseNumber2Int(chapterOrigin);
			if(chapter > maxChapter_x){// 是新更新    1.更新数据库  2.发送mail
				maxChapter_x = chapter;
				Object[] objs = {chapterLink,chapter};
				BaseDao.update("update novel_warn set url=?,chapters=? where id=2", objs);
				
				SendMail.sendTemplate("玄界之门更新啦 --" + chapter,"玄界之门--" + chapter+ "        详情页:---><a href=\""+chapterLink+"\" target=\"_blank\">哈哈</a>");
				System.out.println("x 更新啦！！");
			}
		}
	}
	
	/**
	 * 获取doc对象
	 * @param url
	 * @return
	 */
	public static Document getDoc(String url){
		for(int i=0 ; i < 10 ; i++){
			try {
				return Jsoup.connect(url).get();
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
		return null;
	}
}
