package com.test.disruptor.base2;

import com.lmax.disruptor.EventHandler;
import com.test.disruptor.eventProcessor.Trade;

public class Handler3 implements EventHandler<Trade> {

	@Override
	public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println("执行当前方法Handler3...name："+event.getName()+",id:"+event.getId()+",price:"+event.getPrice()+","+event.toString());
	}

}
