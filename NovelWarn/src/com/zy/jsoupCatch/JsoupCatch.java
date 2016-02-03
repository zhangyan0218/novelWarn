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
	static Integer maxChapter_w;
	static Integer maxChapter_x;
	static Integer maxChapter_z;
	static{
		List<Map<String,Object>> list = BaseDao.select("select chapters from novel_warn");
		maxChapter_w = Integer.valueOf(list.get(0).get("chapters").toString());
		maxChapter_x = Integer.valueOf(list.get(1).get("chapters").toString());
		maxChapter_z = Integer.valueOf(list.get(1).get("chapters").toString());
	}
	
	public static void main(String[] args) {
		JsoupCatch jc = new JsoupCatch();
		jc.start();
	}
	
	public void start(){
		DefaultThread dt1 = new DefaultThread(1);
		new Thread(dt1).start();
		DefaultThread dt2 = new DefaultThread(2);
		new Thread(dt2).start();
		DefaultThread dt3 = new DefaultThread(3);
		new Thread(dt3).start();
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
			}else if(3==type){
				startZ();
			}
		}

	}
	
	// 真武世界 的网站爬取列表
	private static String[] url_z = {
										"http://www.biquge.la/book/11032/",
										"http://www.23wx.com/html/57/57036/",
										"http://www.lingdiankanshu.com/html/24/24917/"
									};
	public void startZ(){
		for(int i=0 ; i < url_z.length ; i++){
			String url = url_z[i];
			
			switch (url) {
				case "http://www.biquge.la/book/11032/": catchZ_01(url) ;break;
				case "http://www.23wx.com/html/57/57036/": catchZ_02(url) ;break;
				case "http://www.lingdiankanshu.com/html/24/24917/": catchZ_03(url) ;break;
				default: break;
			}
		}
	}
	
	public void catchZ_01(String url){
		Document doc =getDoc(url);
		Element e = doc.select("#list dl a").last();
		if(null == e){
			return;
		}
		String li = e.text();
		if(null == li){
			return;
		}
		String[] ss = li.split(" ");
		String contentTitle = "";
		if(ss.length > 1){
			contentTitle = li.split(" ")[ss.length-1];
		}
		li = li.substring(li.lastIndexOf("第")+1,li.lastIndexOf("章"));
		
		checkAndUpdateMail(3, li, url,contentTitle);
	}
	public void catchZ_02(String url){
		Document doc =getDoc(url);
		Element e = doc.select("#at a").last();
		if(null == e){
			return;
		}
		String li = e.text();
		if(null == li){
			return;
		}
		String[] ss = li.split(" ");
		String contentTitle = "";
		if(ss.length > 1){
			contentTitle = li.split(" ")[ss.length-1];
		}
		
		int i1 = li.lastIndexOf("第");
		int i2 = li.lastIndexOf("章");
		if(i1 > -1 && i2 > -1 && i1 < i2){
			
		}else{
			return;
		}
		
		li = li.substring(i1+1,i2);
		
		checkAndUpdateMail(3, li, url,contentTitle);
	}
	public void catchZ_03(String url){
		Document doc =getDoc(url);
		Element e = doc.select("#list a").last();
		if(null == e){
			return;
		}
		String li = e.text();
		if(null == li){
			return;
		}
		String[] ss = li.split(" ");
		String contentTitle = "";
		if(ss.length > 1){
			contentTitle = li.split(" ")[ss.length-1];
		}
		
		int i1 = li.lastIndexOf("第");
		int i2 = li.lastIndexOf("章");
		if(i1 > -1 && i2 > -1 && i1 < i2){
			
		}else{
			return;
		}
		
		li = li.substring(i1+1,i2);
		
		checkAndUpdateMail(3, li, url,contentTitle);
	}
	
	// 我欲封天 的网站爬取列表
	private static String[] url_w = {
										"http://www.biquge.la/book/1/",
										"http://www.exiaoshuo.com/woyufengtian/"
									};
	public void startW(){
		for(int i=0 ; i < url_w.length ; i++){
			String url = url_w[i];
			
			switch (url) {
				case "http://www.biquge.la/book/1/": catchW_01(url) ;break;
				case "http://www.exiaoshuo.com/woyufengtian/": catchW_02(url) ;break;
				default: break;
			}
		}
	}
	
	public void catchW_01(String url){
		Document doc =getDoc(url);
		Element e = doc.select("#list dl a").last();
		if(null == e){
			return;
		}
		String li = e.text();
		if(null == li){
			return;
		}
		String[] ss = li.split(" ");
		String contentTitle = "";
		if(ss.length > 1){
			contentTitle = li.split(" ")[ss.length-1];
		}
		int i1 = li.lastIndexOf("第");
		int i2 = li.lastIndexOf("章");
		if(i1 > -1 && i2 > -1 && i1 < i2){
			
		}else{
			return;
		}
		
		li = li.substring(i1+1,i2);
		
		checkAndUpdateMail(1, li, url,contentTitle);
	}
	public void catchW_02(String url){
		Document doc =getDoc(url);
		Element e = doc.select("#list dl a").last();
		if(null == e){
			return;
		}
		String li = e.text();
		if(null == li){
			return;
		}
		String[] ss = li.split(" ");
		String contentTitle = "";
		if(ss.length > 1){
			contentTitle = li.split(" ")[ss.length-1];
		}
		int i1 = li.lastIndexOf("第");
		int i2 = li.lastIndexOf("章");
		if(i1 > -1 && i2 > -1 && i1 < i2){
			
		}else{
			return;
		}
		
		li = li.substring(i1+1,i2);
		
		checkAndUpdateMail(1, li, url,contentTitle);
	}
	
	// 玄界之门 的网站爬取列表
	private static String[] url_x = {
										"http://read.qidian.com/BookReader/Y0Os8m0co-g1.aspx",
										"http://www.xuanjiezhimen.org/0_859/",
										"http://www.bxwx.org/b/137/137517/index.html"
									};
	public void startX(){
		for(int i=0 ; i < url_x.length ; i++){
			String url = url_x[i];
			
			switch (url) {
				case "http://read.qidian.com/BookReader/Y0Os8m0co-g1.aspx": catchX_01(url) ;break;
				case "http://www.xuanjiezhimen.org/0_859/": catchX_02(url) ;break;
				case "http://www.bxwx.org/b/137/137517/index.html": catchX_03(url) ;break;
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
		String[] ss = li.split(" ");
		String contentTitle = "";
		if(ss.length > 1){
			contentTitle = li.split(" ")[1];
		}
		li = li.substring(1,li.indexOf("章"));
		
		checkAndUpdateMail(2, li, url,contentTitle);
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
		String[] ss = li.split(" ");
		String contentTitle = "";
		if(ss.length > 1){
			contentTitle = li.split(" ")[1];
		}
		
		li = li.substring(1,li.indexOf("章"));
		
		checkAndUpdateMail(2, li, url,contentTitle);
	}
	public void catchX_03(String url){
		Document doc =getDoc(url);
		if(null == doc){
			return;
		}
		Element e = doc.select("#TabCss dl a").last();
		if(null == e){
			return;
		}
		String li = e.text();
		if(null == li){
			return;
		}
		String[] ss = li.split(" ");
		String contentTitle = "";
		if(ss.length > 1){
			contentTitle = li.split(" ")[1];
		}
		
		li = li.substring(1,li.indexOf("章"));
		
		checkAndUpdateMail(2, li, url,contentTitle);
	}
	
	/**
	 * 判断是否新更新，若是，则修改db且发送mail
	 * @param type			1/w  2/x
	 * @param chapterOrigin 章节
	 * @param chapterLink   链接
	 * @param contentTitle  章节标题
	 */
	public void checkAndUpdateMail(Integer type,String chapterOrigin,String chapterLink,String contentTitle){
		if(type==1){// W
			int chapter = Integer.valueOf(chapterOrigin);
			if(chapter > maxChapter_w){// 是新更新    1.更新数据库  2.发送mail
				maxChapter_w = chapter;
				Object[] objs = {chapterLink,chapter};
				BaseDao.update("update novel_warn set url=?,chapters=? where id=1", objs);
				
				SendMail.sendTemplate("我欲封天更新啦 --" + chapter,"我欲封天--" + chapter+ "        详情页:---><a href=\""+chapterLink+"\" target=\"_blank\">"+contentTitle+"</a>");
				System.out.println("w 更新啦！！");
			}
		}else if(type==2){// X
			int chapter = MathUtil.chineseNumber2Int(chapterOrigin);
			if(chapter > maxChapter_x){// 是新更新    1.更新数据库  2.发送mail
				maxChapter_x = chapter;
				Object[] objs = {chapterLink,chapter};
				BaseDao.update("update novel_warn set url=?,chapters=? where id=2", objs);
				
				SendMail.sendTemplate("玄界之门更新啦 --" + chapter,"玄界之门--" + chapter+ "        详情页:---><a href=\""+chapterLink+"\" target=\"_blank\">"+contentTitle+"</a>");
				System.out.println("x 更新啦！！");
			}
		}else if(type==3){
			int chapter = MathUtil.chineseNumber2Int(chapterOrigin);
			if(chapter > maxChapter_z){// 是新更新    1.更新数据库  2.发送mail
				maxChapter_z = chapter;
				Object[] objs = {chapterLink,chapter};
				BaseDao.update("update novel_warn set url=?,chapters=? where id=3", objs);
				
				SendMail.sendTemplate("真武世界更新啦 --" + chapter,"真武世界--" + chapter+ "        详情页:---><a href=\""+chapterLink+"\" target=\"_blank\">"+contentTitle+"</a>");
				System.out.println("z 更新啦！！");
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
