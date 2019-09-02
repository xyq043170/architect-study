package com.test.masterWork;

public class MyWorker extends Worker {
	
	public static Object handle(Task input)
	{
		Object output = null;
		try {
			Thread.sleep(500);
			output =input.getPrice();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return output;
	}
}
