package com.test.masterWork;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable {
	private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<Task>();
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
	
	
	@Override
	public void run() {
		while(true)
		{
			Task input =this.workQueue.poll();
			if(input == null)
			{
				break;
			}
			
			//业务逻辑处理
			Object output=MyWorker.handle(input);
			
			this.resultMap.put(input.getId()+"", output);
		}
	}
	
	public static Object handle(Task input){
		return null;
	}

	public ConcurrentLinkedQueue<Task> getWorkQueue() {
		return workQueue;
	}


	public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue) {
		this.workQueue = workQueue;
	}


	public ConcurrentHashMap<String, Object> getResultMap() {
		return resultMap;
	}


	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	
}
