package com.test.CopyOnWrite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;

import javax.management.RuntimeErrorException;

import com.mysql.fabric.xmlrpc.base.Array;

public class testThread14{
	public static void main(String[] agrs) throws InterruptedException
	{
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
		CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<String>();
		
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		
		map.put("1", "123");
		map.put("2", "456");
		map.put("3", "789");
		map.putIfAbsent("4", "kkk");
		
		for(Map.Entry<String, String> me : map.entrySet())
		{
			System.out.println("key="+me.getKey()+",value="+me.getValue());
		}
		
	}
}
