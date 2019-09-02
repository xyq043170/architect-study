package com.test;

import java.util.concurrent.atomic.AtomicInteger;

public class test2 {
	public static AtomicInteger count = new AtomicInteger(0);
	public static volatile int count2 = 0;
	public synchronized void method1()
	{
		count.incrementAndGet();
		System.out.println("method1..."+Thread.currentThread().getName()+","+count.get());
	}
	
	public synchronized void method2()
	{
		count2++;
		System.out.println("method2..."+Thread.currentThread().getName()+","+count2);
	}
	public static void main(String[] args) {
		final test2 t = new test2();
		for (int i = 0; i < 50; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					t.method1();
					t.method2();
				}
			},"t"+i).start();
		}
	}
}	
