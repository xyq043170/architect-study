package com.test.lock.reentrantLock.Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class UseManyCondition {
	private ReentrantLock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	
	public void m1()
	{
		try {
			lock.lock();
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入m1...");
			c1.await();
			System.out.println("当前线程："+Thread.currentThread().getName()+"方法m1继续...");
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
		
	}
	
	public void m2()
	{
		try {
			lock.lock();
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入m2...");
			c1.await();
			System.out.println("当前线程："+Thread.currentThread().getName()+"方法m2继续...");
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
		
	}
	
	public void m3()
	{
		try {
			lock.lock();
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入m3...");
			c2.await();
			System.out.println("当前线程："+Thread.currentThread().getName()+"方法m3继续...");
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
		
	}
	
	public void m4()
	{
		try {
			lock.lock();
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入m4唤醒线程...");
			c1.signalAll();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
		
	}
	
	public void m5()
	{
		try {
			lock.lock();
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入m5唤醒线程...");
			c2.signal();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
		
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		final UseManyCondition uc = new UseManyCondition();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				uc.m1();
			}
		},"t1");
		
	Thread t2 = new Thread(new Runnable() {
				
				@Override
				public void run() {
					uc.m2();
				}
			},"t2");

	Thread t3 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			uc.m3();
		}
	},"t3");

	Thread t4 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			uc.m4();
		}
	},"t4");

	Thread t5 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			uc.m5();
		}
	},"t5");
	
	t1.start();
	t2.start();
	t3.start();
	
	Thread.sleep(2000);
	
	t4.start();
	
	Thread.sleep(2000);
	t5.start();
	
	}
}
