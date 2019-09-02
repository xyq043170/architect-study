package com.test.disruptor.eventProcessor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;

public class Main2 {
	@SuppressWarnings("unchecked")
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
		
		WorkHandler<Trade> handler = new TradeHandler();
		
		WorkerPool<Trade> workerPool = new WorkerPool<Trade>(ringBuffer,sequenceBarrier,new IgnoreExceptionHandler(),handler);
		
		workerPool.start(pool);
		
				for (int i = 0; i < 8; i++) {
					long seq = ringBuffer.next();
					ringBuffer.get(seq).setPrice(Math.random()*9999);
					ringBuffer.publish(seq);
				}
		
		Thread.sleep(1000);
		workerPool.halt();//通知事件处理器可以结束了
		pool.shutdown();
	}
}
