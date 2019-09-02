package com.test.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;

import javax.management.RuntimeErrorException;

import org.omg.CORBA.TIMEOUT;

import com.mysql.fabric.xmlrpc.base.Array;
import com.test.blockingqueue.DelayQueue.Task;

public class testThread13{
	public static void main(String[] agrs) throws InterruptedException
	{
		ArrayBlockingQueue<String> array = new ArrayBlockingQueue<String>(5);
		array.add("a");
		array.add("b");
		array.add("c");
		array.add("d");
		array.add("e");
		System.out.println(array.offer("a", 3, TimeUnit.SECONDS));
		
		LinkedBlockingQueue<String> link = new LinkedBlockingQueue<String>();
		link.add("a1");
		link.add("b1");
		link.add("c1");
		link.add("d1");
		link.add("e1");
		link.add("a2");
		link.add("b2");
		link.add("c2");
		link.add("d2");
		link.add("e2");
		System.out.println(link.size());
		
		List<String> lists = new ArrayList<String>();
		System.out.println(link.drainTo(lists, 6));
		System.out.println(lists.size());
		for(String string:lists)
		{
			System.out.println(string);
		}
		System.out.println("123");
		for(String string:link)
		{
			System.out.println(string);
		}
		
//		SynchronousQueue<String> queue = new SynchronousQueue<String>();
//		queue.add("a");
		
		
		PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<Task>();
		
		q.add(new Task(3,"任务1"));
		q.add(new Task(1,"任务2"));
		q.add(new Task(4,"任务3"));
		
//		System.out.println(q);
//		Task t =q.take();
//		System.out.println(t.getId()+","+t.getName());
//		System.out.println(q);
//		Task t2 =q.take();
//		System.out.println(t2.getId()+","+t2.getName());
//		System.out.println(q);
//		Task t3 =q.take(); 
//		System.out.println(t3.getId()+","+t3.getName());
		for (Task task : q) {  
	        try {  
	            System.out.println(q.take().getName());  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }  
	    }
		
//		for (Iterator iterator = q.iterator(); iterator.hasNext();) {
//			Task task = (Task) iterator.next();
//			System.out.println(task.getName());
//		}
	}
}
