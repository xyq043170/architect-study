package com.test.disruptor.eventProcessor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;

public class Main1 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int BUFFER_SIZE=1024;
		int TREAD_NUM = 4;
		
		final RingBuffer<Trade> ringBuffer =RingBuffer.createSingleProducer(new EventFactory<Trade>() {

			@Override
			public Trade newInstance() {
				// TODO Auto-generated method stub
				return new Trade();
			}
		}, BUFFER_SIZE, new YieldingWaitStrategy());
		
		ExecutorService pool = Executors.newFixedThreadPool(TREAD_NUM);
		SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();	//处理器处理平衡
		
		BatchEventProcessor<Trade> transProcessor = new BatchEventProcessor<Trade>(ringBuffer,sequenceBarrier,new TradeHandler());
		//多个消费者需加以下代码
		ringBuffer.addGatingSequences(transProcessor.getSequence());
		
		pool.submit(transProcessor);
		Future<?> future = pool.submit(new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				long seq;
				for (int i = 0; i < 10; i++) {
					seq = ringBuffer.next();
					ringBuffer.get(seq).setPrice(Math.random()*9999);
					ringBuffer.publish(seq);
				}
				return null;
			}
			
		});
		
		future.get();
		Thread.sleep(1000);
		transProcessor.halt();//通知事件处理器可以结束了
		pool.shutdown();
	}
}
