package com.test.disruptor.base;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

public class LongEventProducer {
	private RingBuffer<LongEvent> ringBuffer;
	public LongEventProducer(RingBuffer<LongEvent> ringBuffer)
	{
		this.ringBuffer = ringBuffer;
	}
	
	public void onData(ByteBuffer bb){
		//取ringBuffer下标
		long sequeue = ringBuffer.next();
		try {
			//取得空的model
			LongEvent event = ringBuffer.get(sequeue);
			event.setValue(bb.getLong(0));
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			//发布事件
			ringBuffer.publish(sequeue);
		}
	}
}
