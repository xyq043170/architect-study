package com.test.disruptor.base3;

import java.nio.ByteBuffer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

public class Producer {
	private RingBuffer<Order> ringBuffer;
	
	public Producer(RingBuffer<Order> ringBuffer)
	{
		this.ringBuffer = ringBuffer;
	}

	private static final EventTranslatorOneArg<Order, String> TRANSLATOR = 
            new EventTranslatorOneArg<Order, String>() { 
                public void translateTo(Order event, long sequence, String data) { 
                    event.setId(data); 
                } 
            };
            
    public void onData(String data) { 
        ringBuffer.publishEvent(TRANSLATOR, data); 
    } 
}
