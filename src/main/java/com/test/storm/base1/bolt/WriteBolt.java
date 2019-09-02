package com.test.storm.base1.bolt;

import java.io.FileWriter;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

public class WriteBolt extends BaseBasicBolt {
	private static final Logger logger = Logger.getLogger(WriteBolt.class.getName());
	private static final long serialVersionUID = 1L;
	
	private FileWriter writer;
	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		String text = input.getStringByField("write");
		try {
			if(writer == null)
			{
				if(System.getProperty("os.name").equals("Windows 10")){
					writer = new FileWriter("D:\\099_test\\"+this);
				}else if(System.getProperty("os.name").equals("Windows 8.1")){
					writer = new FileWriter("D:\\099_test\\"+this);
				}else if(System.getProperty("os.name").equals("Windows 7")){
					writer = new FileWriter("D:\\099_test\\"+this);
				}else if(System.getProperty("os.name").equals("Linux")){
					System.out.println("----:"+System.getProperty("os.name"));
					writer = new FileWriter("/usr/local/temp/"+this);
				}
			}
			
			System.out.println("写入文件");
			writer.write(text);
			writer.write("\n");
			writer.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}

	
}
