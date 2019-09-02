package com.test.executors.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class Temp implements Runnable{
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public void run() {
		System.out.println(df.format(new Date())+"==>"+Thread.currentThread().getName()+" run");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class UseExecutors {
	public static void main(String[] args) {
		Thread threads[] = new Thread[10];
		for (int i = 0; i < 10; i++) {
			Thread t1 = new Thread(new Temp(),"t"+(i+1));
			threads[i] = t1;
		}
		
		ScheduledExecutorService pool=Executors.newScheduledThreadPool(10);
		System.out.println("lengtg=="+threads.length);
		//初始化5s，每隔1s执行temp()
		for (int i = 0; i < threads.length; i++) {
			ScheduledFuture<?> scheduleTask = pool.scheduleWithFixedDelay(threads[i], 1, 1, TimeUnit.SECONDS);	//初始化1s，间隔5s循环一次
		}
		
//		ExecutorService pool = Executors.newFixedThreadPool(3);
//		for (int i = 0; i < threads.length; i++) {
//			pool.execute(new Temp());
//		}
		
		System.out.println("main end===========================================");
		 //new ThreadPoolExecutor
//		ExecutorService pool2 = Executors.newCachedThreadPool();
		
	}
}
