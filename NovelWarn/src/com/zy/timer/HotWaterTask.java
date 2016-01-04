package com.zy.timer;


public class HotWaterTask{

	private static Long lastTime = System.currentTimeMillis();
	public void execute(){
		Long nowTime = System.currentTimeMillis();
		
		System.out.println("定时器执行，间隔" + (nowTime - lastTime )+"毫秒");
		
		lastTime = nowTime;
	}

}
