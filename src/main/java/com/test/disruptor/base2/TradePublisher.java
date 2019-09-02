package com.test.disruptor.base2;

import java.nio.ByteBuffer;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang.math.Fraction;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.dsl.Disruptor;
import com.test.disruptor.eventProcessor.Trade;

public class TradePublisher implements Callable {
	private CountDownLatch latch;
	private Disruptor<Trade> disruptor;
	int LOOP = 1;
	public TradePublisher(CountDownLatch latch,Disruptor<Trade> disruptor)
	{
		this.latch = latch;
		this.disruptor = disruptor;
	}
	
	@Override
	public Object call() throws Exception {
		TradeEventTranslator tradeEventTranslator = new TradeEventTranslator();
		for (int i = 0; i < LOOP; i++) {
			disruptor.publishEvent(tradeEventTranslator);
		}
		latch.countDown();
		return null;
	}
}

class TradeEventTranslator implements EventTranslator<Trade>
{
	private Random random = new Random();
	@Override
	public void translateTo(Trade event, long sequence) {
		 this.generateTrade(event);
	}
	
	private Trade generateTrade(Trade trade)
	{
		trade.setPrice(random.nextDouble()*9999);
		return trade;
	}
}
