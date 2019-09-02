package com.test.storm.base1.spout;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import com.test.netty.frame.correct.TimeClientHandler;

public class PWSpout extends BaseRichSpout {
	
	private static final long serialVersionUID = 1L;
	
	private SpoutOutputCollector collector;
	
	private static final Map<Integer,String> map =new HashMap<Integer,String>();
	int count = 0;
	static{
		map.put(0, "java");
		map.put(1, "php");
		map.put(2, "groovy");
		map.put(3, "python");
		map.put(4, "ruby");
	}
	
	String[] array0 =  {"aa","bb","cc","aa","bb","bb","bb","cc","dd","","jj","mm"};  
    String[] array1 =  {"11","22","33","11","55","22","66","11","33","","44","22"};  
	
	//初始化的方式
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector = collector;
	}

	@Override
	public void nextTuple() {
		// TODO Auto-generated method stub
//		final Random r = new Random();
//		int num = r.nextInt(5);
//		try {
//			Thread.sleep(500);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		//发射到printBolt里
//		this.collector.emit(new Values(map.get(num)));
		if(count>=array0.length){  
            Utils.sleep(10000);  
            return;  
  
        }  
        collector.emit(new Values(array0[count],array1[count]),array0[count]);  
                count++;  
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		//进行申明,下一个bolt通过print取数值
		declarer.declare(new Fields("print1","print2"));
	}

	

}
