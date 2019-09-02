package com.test.threadthrow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import javax.management.RuntimeErrorException;

import com.mysql.fabric.xmlrpc.base.Array;

public class testThread11{
	private volatile static List list = new ArrayList();
	
	public void add()
	{
		list.add("123");
	}
	
	public int size()
	{
		return list.size();
	}

	
	public static void main(String[] agrs) throws InterruptedException
	{
		final testThread11 t = new testThread11();
//		final Object lock = new Object();
		final CountDownLatch count = new CountDownLatch(1);
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
//					synchronized (lock) {
						for(int i =0;i < 10;i++)
						{
							t.add();
							System.out.println(Thread.currentThread().getName()+",加了一个元素...");
							Thread.sleep(500);
							if(t.size() == 5)
							{
								System.out.println("已发出通知");
//								lock.notify();
								count.countDown();
							}
						}
//					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			 
			@Override
			public void run() {
//				synchronized (lock) {
					if(t.size() != 5)
					{
						try {
							System.out.println("t2进入");
//							lock.wait();
							count.await();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.println(Thread.currentThread().getName()+",list size = 5,停止线程。。。");
					throw new RuntimeException();
//				}
			}
		},"t2");
		
		t2.start();
		t1.start();
	}
}
