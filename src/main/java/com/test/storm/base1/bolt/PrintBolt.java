package com.test.storm.base1.bolt;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;


public class PrintBolt extends BaseBasicBolt {
	private static final Logger logger = Logger.getLogger(PrintBolt.class.getName());
	private static final long serialVersionUID = 1L;
	
	//input上个组件发过来的值，collector用于发射数据发射下一个组件
	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		String print1 = input.getStringByField("print1");
		String print2 = input.getStringByField("print2");
		System.out.println("tuple0->"+input.getString(0)+"  "+"tuple1->"+input.getString(1)+"  "+Thread.currentThread().getName()); 
		collector.emit(new Values("tuple0->"+input.getString(0)+"  "+"tuple1->"+input.getString(1)+"  "+Thread.currentThread().getName()));		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		//进行申明,下一个bolt通过wirte取数值
		declarer.declare(new Fields("write"));
	}

	

}
