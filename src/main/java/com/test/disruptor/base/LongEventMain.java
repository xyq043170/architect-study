package com.test.disruptor.base;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class LongEventMain {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		LongEventFactory factory = new LongEventFactory();
		
		int ringBufferSize = 1024*1024;//需要2的n次方
		//1参数工厂类对象
		//2参数参数缓存区大小
		//3参数线程池进行disruptor内部数据接收处理调度
		//4参数 ProducerType.SINGLE表示一个生产者，ProducerType.MULTI表示多个生产者
		//5参数表示一种策略有3中策略
		//BlockingWaitStrategy最低效的策略，对CPU消耗最低并且在各种不同的部署环境中能提供更加一致的性能表现
		//SleepingWaitStrategy跟第一种差不多，但其对生产者线程的影响最小，适用于异步日志类
		//YieldingWaitStrategy性能最好的，适用于低延迟系统，在要求极高的性能且事件处理线数小于CPU逻辑核心数的场景中。
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory,ringBufferSize,pool,ProducerType.SINGLE,new YieldingWaitStrategy());
		//连接消费事件的方法
		disruptor.handleEventsWith(new LongEventHandler());
		
		disruptor.start();
		
		RingBuffer<LongEvent> ringBuffer =disruptor.getRingBuffer();
		
//		LongEventProducer producer = new LongEventProducer(ringBuffer);
		LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);
		ByteBuffer bb = ByteBuffer.allocate(8);
		for (long i = 0; i < 100; i++) {
			bb.putLong(0, i);
			producer.onData(bb);
		}
		
		disruptor.shutdown();
		pool.shutdown();
	}
}
