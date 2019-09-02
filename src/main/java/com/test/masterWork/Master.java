package com.test.masterWork;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.plaf.FontUIResource;

public class Master {
	private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<Task>();
	private Map<String,Thread> workers = new HashMap<String,Thread>();
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
	
	public Master(Worker worker,int workeCounts)
	{
		//设置worker的引用workQueue用于取任务，resultMap用于任务的提交
		worker.setWorkQueue(this.workQueue);
		worker.setResultMap(this.resultMap);
		
		for(int i =0;i < workeCounts;i++)
		{
			workers.put("子节点"+Integer.toString(i), new Thread(worker));
		}
	}
	//提交接口
	public void submit(Task task)
	{
		this.workQueue.add(task);
	}
	
	//执行方法，启动应用程序让worker工作
	public void execute()
	{
		for(Map.Entry<String,Thread> me : workers.entrySet())
		{
//			System.out.println("开始启动线程名："+me.getKey());
			me.getValue().start();
		}
	}
	public boolean isComplete() {
		for(Map.Entry<String,Thread> me : workers.entrySet())
		{
			if(me.getValue().getState() != Thread.State.TERMINATED)
			{
				return false;
			}
		}
		return true;
	}
	public Integer getResult() {
		Integer ret =0;
		for(Map.Entry<String,Object> me : resultMap.entrySet())
		{
			ret+=(Integer)me.getValue();
		}
		return ret;
	}
	
	
}
