package com.test.atomiclock;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;

import javax.management.RuntimeErrorException;

import com.mysql.fabric.xmlrpc.base.Array;

public class testThread12{
	private volatile static LinkedList<Object> list=new LinkedList<Object>();
	private static AtomicInteger count = new AtomicInteger(0);
	private int minSize = 0;
	private int maxSize = 0;
	private final Object lock = new Object();
	
	public testThread12(int maxSize)
	{
		this.maxSize = maxSize;
	}
	
	
	public void put(Object obj)
	{
		synchronized (lock) {
			while(count.get() == this.maxSize)
			{
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			list.add(obj);
			count.incrementAndGet();
			System.out.println("queue提示加入元素一个,"+obj);
			lock.notify();
		}
	}
	
	public Object take()
	{
		Object ret = null;
		synchronized (lock) {
			
			while(count.get() == minSize)
			{
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			ret =list.removeFirst();
			count.decrementAndGet();
			System.out.println("queue提示移除元素一个");
			lock.notify();
		}
		
		return ret;
	}

	public int getSize()
	{
		return this.count.get();
	}
	
	public static void main(String[] agrs) throws InterruptedException
	{
		final testThread12 queue = new testThread12(5);
		queue.put("123");
		queue.put("456");
		queue.put("789");
		queue.put("101");
		queue.put("102");
		
		System.out.println(queue.getSize());
//		int count = queue.getSize();
//		for(int i = 0;i < count;i++)
//		{
//			System.out.println(queue.take().toString());
//		}
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				queue.put("111");
				queue.put("222");
			}
		},"t1");
		
		t1.start();
		Thread.sleep(1000);
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				Object o1 = queue.take();
				System.out.println("线程移除元素为"+o1);
				Object o2 = queue.take();
				System.out.println("线程移除元素为"+o2);
			}
		},"t2");
		
		t2.start();
	}
}
