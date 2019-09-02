package com.test.futrue;

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

public class testThread16{
	public static void main(String[] agrs) throws InterruptedException
	{
		FutrueClient fc = new FutrueClient();
		Data data =fc.request("请求参数");
		System.out.println("请求发送成功...");
		System.out.println("做其他事情...");
		
		String result =data.getRequest();
		System.out.println(result);
		
	}
}
