package com.test.disruptor.base3;

import java.util.concurrent.atomic.AtomicInteger;

import com.lmax.disruptor.WorkHandler;
import com.test.disruptor.eventProcessor.Trade;

public class Consumer implements WorkHandler<Order>{
	private String name;
	private static AtomicInteger count = new AtomicInteger(0);
	public Consumer(String name){
		this.name = name;
	}
	@Override
	public void onEvent(Order event) throws Exception {
		System.out.println("当前消费者："+this.name+",消费信息："+event.getId());
		count.incrementAndGet();
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return count.get();
	}
}
