package com.test.threadthrow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.management.RuntimeErrorException;

import com.mysql.fabric.xmlrpc.base.Array;

public class testThread10{
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
		final testThread10 t = new testThread10();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					for(int i =0;i < 10;i++)
					{
						t.add();
						System.out.println(Thread.currentThread().getName()+",加了一个元素...");
						Thread.sleep(500);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true)
				{
					if(t.size() == 5)
					{
						System.out.println(Thread.currentThread().getName()+",list size = 5,停止线程。。。");
						throw new RuntimeException();
					}
				}
			}
		},"t2");
		
		t1.start();
		t2.start();
	}
}
