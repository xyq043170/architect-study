package com.test.blockingqueue.SynchronousQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;

import javax.management.RuntimeErrorException;

import com.mysql.fabric.xmlrpc.base.Array;

public class testThread15{
	public static void main(String[] agrs) throws InterruptedException
	{
		final SynchronousQueue<String> q = new SynchronousQueue<String>();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println(q.take());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		t1.start();
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				q.add("adasdas");
			}
		});
		
		t2.start();
	}
}
