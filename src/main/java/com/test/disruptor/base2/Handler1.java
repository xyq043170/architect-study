package com.test.disruptor.base2;

import com.lmax.disruptor.EventHandler;
import com.test.disruptor.eventProcessor.Trade;

public class Handler1 implements EventHandler<Trade> {

	@Override
	public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println("执行当前方法Handler1...");
		event.setName("h1");
//		Thread.sleep(1000);
	}

}
