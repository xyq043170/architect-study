package com.test.disruptor.base3;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.test.disruptor.eventProcessor.Trade;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		int BUFFER_SIZE=1024*1024;
		int TREAD_NUM = 4;
		
		final RingBuffer<Order> ringBuffer =RingBuffer.createSingleProducer(new EventFactory<Order>() {

			@Override
			public Order newInstance() {
				// TODO Auto-generated method stub
				return new Order();
			}
		}, BUFFER_SIZE, new YieldingWaitStrategy());
		
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();	//处理器处理平衡
		
		Consumer[] consumer = new Consumer[3];
		for (int i = 0; i < consumer.length; i++) {
			consumer[i] = new Consumer("c"+i);
		}
		
		WorkerPool<Order> workerPool = new WorkerPool<Order>(ringBuffer,sequenceBarrier,new IgnoreExceptionHandler(),consumer);
		ringBuffer.addGatingSequences(workerPool.getWorkerSequences());
		
		workerPool.start(pool);
		
		final CountDownLatch latch = new CountDownLatch(1);
		for (int i = 0; i < 100; i++) {
			final Producer p =new Producer(ringBuffer);
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						latch.await();
					} catch (Exception e) {
						// TODO: handle exception
					}
					for (int j = 0; j < 100; j++) {
						p.onData(UUID.randomUUID().toString());
					}
				}
			}).start();
		}
		
		Thread.sleep(2000);
		System.out.println("==================开始生产=================");
		latch.countDown();
		Thread.sleep(5000);
		
		workerPool.halt();//通知事件处理器可以结束了
		pool.shutdown();
		System.out.println("总数："+consumer[0].getCount());
	}
}
