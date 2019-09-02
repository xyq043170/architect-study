package com.test.blockingqueue.DelayQueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

public class WangBa implements Runnable {
	private DelayQueue<Wangmin> queue = new DelayQueue<Wangmin>();
	public boolean yinye = true;
	
	public void shangji(String name,String id,int money)
	{
		Wangmin man = new Wangmin(name,id,money*1000+System.currentTimeMillis());
		System.out.println("网名："+name+",身份证："+id+",交钱："+money+" 元,开始上机...");
		this.queue.add(man);
	}
	
	public void xiaji(Wangmin min)
	{
		System.out.println("网名："+min.getName()+",身份证："+min.getId()+",时间到下机..");
	}
	@Override
	public void run() {
		while(yinye)
		{
			try {
				Wangmin man=queue.take();
				xiaji(man);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public static void main(String[] args)
	{
		try {
			System.out.println("网吧开始营业");
			WangBa w = new WangBa();
			Thread thread = new Thread(w);
			thread.start();
			
			w.shangji("张三", "123", 10);
			w.shangji("李四", "456", 70);
			w.shangji("王五", "789", 50);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
