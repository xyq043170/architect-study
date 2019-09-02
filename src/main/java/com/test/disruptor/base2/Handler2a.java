package com.test.disruptor.base2;

import com.lmax.disruptor.EventHandler;
import com.test.disruptor.eventProcessor.Trade;

public class Handler2a implements EventHandler<Trade> {

	@Override
	public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("执行当前方法Handler2a...");
		event.setName("2a");
	}

}
