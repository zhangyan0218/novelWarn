package com.zy.timer;

import com.zy.jsoupCatch.JsoupCatch;


public class HotWaterTask{

	private static Long lastTime = System.currentTimeMillis();
	public void execute(){
		Long nowTime = System.currentTimeMillis();
		
		System.out.println("定时器执行，间隔" + (nowTime - lastTime )/1000+"秒");
		
		JsoupCatch jc = new JsoupCatch();
		jc.start();
		
		lastTime = nowTime;
	}

}
