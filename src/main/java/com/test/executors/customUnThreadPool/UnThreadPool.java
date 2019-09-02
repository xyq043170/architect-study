package com.test.executors.customUnThreadPool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class UnThreadPool implements Runnable{
	private static AtomicInteger count = new AtomicInteger(0);
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Runnable> queue = //new LinkedBlockingQueue<Runnable>();
										new ArrayBlockingQueue<>(6);
		ExecutorService executor = new ThreadPoolExecutor(5, 7, 120L, TimeUnit.SECONDS, queue);
		
		for (int i = 0; i < 14; i++) {
			executor.execute(new UnThreadPool());
		}
		Thread.sleep(1000);
		System.out.println("queue size:"+queue.size());
		Thread.sleep(2000);
	}

	@Override
	public void run() {
		try {
			int temp = count.incrementAndGet();
			System.out.println(df.format(new Date())+"==>任务"+temp);
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
