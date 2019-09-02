package com.test.lock.reentrantLock.base;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseReentrantLock {
	private ReentrantLock lock = new ReentrantLock();
	public void method1()
	{
		try {
			lock.lock();
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入method1...");
			Thread.sleep(1000);
			System.out.println("当前线程："+Thread.currentThread().getName()+"退出method1...");
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
		
	}
	
	public void method2()
	{
		try {
			lock.lock();
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入method2...");
			Thread.sleep(2000);
			System.out.println("当前线程："+Thread.currentThread().getName()+"退出method2...");
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		lock.unlock();
	}
	public static void main(String[] args) {
		final UseReentrantLock use = new UseReentrantLock();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				use.method1();
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				use.method2();
			}
		},"t2");
		
		t1.start();
		t2.start();
		
		try {
			Thread.sleep(10);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
