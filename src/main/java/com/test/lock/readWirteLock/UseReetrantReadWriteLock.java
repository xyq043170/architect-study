package com.test.lock.readWirteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class UseReetrantReadWriteLock {
	private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
	private ReadLock readLock = rwlock.readLock();
	private WriteLock writeLock =rwlock.writeLock();
	
	public void read()
	{
		try {
			readLock.lock();
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入read...");
			Thread.sleep(3000);
			System.out.println("当前线程："+Thread.currentThread().getName()+"退出read...");
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			readLock.unlock();
		}
	}
	
	public void write()
	{
		try {
			writeLock.lock();
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入write...");
			Thread.sleep(3000);
			System.out.println("当前线程："+Thread.currentThread().getName()+"退出write...");
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			writeLock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final UseReetrantReadWriteLock u = new UseReetrantReadWriteLock();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				u.read();
			}
		},"t1");
		
	Thread t2 = new Thread(new Runnable() {
				
				@Override
				public void run() {
					u.read();
				}
			},"t2");
	
	Thread t3 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			u.write();
		}
	},"t3");
	
	Thread t4 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			u.write();
		}
	},"t4");
	t1.start();
	t2.start();
//	t3.start();
//	t4.start();
	
	}
}
