package com.test.productConsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Provider implements Runnable{
	private BlockingQueue<Data> queue;
	private volatile boolean isRun = true;
	private static AtomicInteger count = new AtomicInteger(0);
	private static Random r = new Random();
	public Provider(BlockingQueue<Data> queue)
	{
		this.queue = queue;
	}
	@Override
	public void run() {
		while(isRun)
		{
			try {
				Thread.sleep(r.nextInt(200));
				int id = count.incrementAndGet();
				Data data = new Data(Integer.toString(id),"数据"+id);
				System.out.println("当前生产线程："+Thread.currentThread().getName()+",获取了数据,id="+id+",进行装载到公共缓存区...");
				if(!this.queue.offer(data, 2, TimeUnit.SECONDS))
				{
					System.out.println("提交缓存区数据失败...");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	public  void stop() {
		// TODO Auto-generated method stub
		this.isRun = false;
	}
}
