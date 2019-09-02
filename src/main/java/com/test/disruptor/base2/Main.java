package com.test.disruptor.base2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
import com.test.disruptor.eventProcessor.Trade;

public class Main {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException {
		long beginTIme = System.currentTimeMillis();
		int bufferSize =1024;
		ExecutorService pool = Executors.newFixedThreadPool(8);
		
		Disruptor<Trade> disruptor = new Disruptor<Trade>(new EventFactory<Trade>() {

			@Override
			public Trade newInstance() {
				// TODO Auto-generated method stub
				return new Trade();
			}
			
		}, bufferSize, pool, ProducerType.SINGLE, new BusySpinWaitStrategy());
		
		//执行四边形流程图
//		EventHandlerGroup<Trade> handlerGroup = disruptor.handleEventsWith(new Handler1(),new Handler2());
//		handlerGroup.then(new Handler3());
		
		//执行六边形流程图
		/*Handler1a handler1a = new Handler1a();
		Handler1b handler1b = new Handler1b();
		Handler2a handler2a = new Handler2a();
		Handler2b handler2b = new Handler2b();
		Handler3 handler3 = new Handler3();
		disruptor.handleEventsWith(handler1a,handler2a);
		disruptor.after(handler1a).handleEventsWith(handler1b);
		disruptor.after(handler2a).handleEventsWith(handler2b);
		disruptor.after(handler1b,handler2b).handleEventsWith(handler3);*/
		
		//顺序操作
		disruptor.handleEventsWith(new Handler1()).handleEventsWith(new Handler2()).handleEventsWith(new Handler3());
		
		disruptor.start();
		CountDownLatch latch = new CountDownLatch(1);
		
		pool.submit(new TradePublisher(latch,disruptor));
		
		latch.await();
		disruptor.shutdown();
		pool.shutdown();
		System.out.println("总耗时:"+(System.currentTimeMillis() - beginTIme));
		
	}
}
