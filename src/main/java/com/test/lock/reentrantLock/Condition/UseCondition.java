package com.test.lock.reentrantLock.Condition;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseCondition {
	private Lock lock = new ReentrantLock();
	private Condition condition=lock.newCondition();
	public void method1()
	{
		try {
			lock.lock();
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入method1等待状态...");
			Thread.sleep(3000);
			System.out.println("当前线程："+Thread.currentThread().getName()+"method1释放锁...");
			condition.await();
			System.out.println("当前线程："+Thread.currentThread().getName()+"method1继续执行...");
		} catch (Exception e) {
			// TODO: handle exception
		}
		lock.unlock();
	}
	
	public void method2()
	{
		try {
			lock.lock();
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入method2...");
			Thread.sleep(3000);
			System.out.println("当前线程："+Thread.currentThread().getName()+"method2发出唤醒...");
			condition.signal();
			System.out.println("当前线程："+Thread.currentThread().getName()+"不释放锁，继续执行...");
		} catch (Exception e) {
			// TODO: handle exception
		}
		lock.unlock();
	}
	
	public static void main(String[] args) {
		CopyOnWriteArrayList a= new CopyOnWriteArrayList<>();
		final UseCondition uc = new UseCondition();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				uc.method1();
			}
		},"t1");
		t1.start();
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				uc.method2();
			}
		},"t2");
		
		
		t2.start();
	}
}
