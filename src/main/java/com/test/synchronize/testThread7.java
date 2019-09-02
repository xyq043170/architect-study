package com.test.synchronize;

public class testThread7{
	public void method()
	{
		synchronized(new String("123"))
		{
			try {
				while(true)
				{
					System.out.println(Thread.currentThread().getName()+",method()开始");
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName()+",method()结束");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	public static void main(String[] agrs) throws InterruptedException
	{
		final testThread7 t =new testThread7();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				t.method();
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				t.method();
			}
		},"t2");
		
		t1.start();
		t2.start();
	}
}
