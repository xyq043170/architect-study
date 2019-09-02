package com.test.masterWork;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		System.out.println("当前机器的可用Processors数："+Runtime.getRuntime().availableProcessors());
		Master master=new Master(new Worker(),Runtime.getRuntime().availableProcessors());
		Random r = new Random();
		for (int i = 1; i <= 100; i++) {
			Task t =new Task();
			t.setId(i);
			t.setName("任务"+i);
			t.setPrice(r.nextInt(1000));
			master.submit(t);
		}
		
		master.execute();
		long start = System.currentTimeMillis();
		while(true)
		{
			if(master.isComplete())
			{
				long end = System.currentTimeMillis()-start;
				Integer result=master.getResult();
				System.out.println("最终结果= "+result+",执行耗时="+end);
				break;
			}
		}
	}
}
