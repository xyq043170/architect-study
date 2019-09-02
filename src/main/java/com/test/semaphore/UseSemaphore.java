package com.test.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class UseSemaphore {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		final Semaphore semp = new Semaphore(5);
		for (int i = 0; i < 20; i++) {
			final int NO = i;
			Runnable run = new Runnable() {
				
				@Override
				public void run() {
					try {
						semp.acquire();
						System.out.println("Accessing:"+NO);
						Thread.sleep((long)(Math.random()*10000));
						semp.release();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			};
			pool.execute(run);
		}
		
		try {
			Thread.sleep(10);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
