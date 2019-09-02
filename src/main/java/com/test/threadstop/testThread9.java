package com.test.threadstop;

import java.util.concurrent.atomic.AtomicInteger;

public class testThread9 extends Thread{
	private boolean isRun = true;
	private void  setIsRun(boolean isRun)
	{
		this.isRun = isRun;
	}
	
	public boolean isRun() {
		return this.isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	@Override
	public void run() {
		System.out.println("进入run方法...");
		while(isRun() == true)
		{
			System.out.println(isRun());
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("线程停止...");
	}
	
	
	public static void main(String[] agrs) throws InterruptedException
	{
		testThread9 t =new testThread9();
		t.start();
		Thread.sleep(3000);
		t.setIsRun(false);
		System.out.println("isRun设置为false");
		Thread.sleep(2000);
		System.out.println(t.isRun);
		
	}
}
