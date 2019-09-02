package com.test.synchronize;

public class testThread extends Thread {
	private int count =5;
	@Override
	public synchronized void run() {
		count--;
		System.out.println("count="+count+", threadName="+Thread.currentThread().getName());
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.run();
		
	}

	public static void main(String[] agrs)
	{
		testThread t =new testThread();
		Thread t1 = new Thread(t,"t1");
		Thread t2 = new Thread(t,"t2");
		Thread t3 = new Thread(t,"t3");
		Thread t4 = new Thread(t,"t4");
		Thread t5 = new Thread(t,"t5");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}
