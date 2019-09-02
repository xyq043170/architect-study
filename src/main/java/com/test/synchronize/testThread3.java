package com.test.synchronize;

public class testThread3{
	private static int num = 0;
	
	public synchronized void Println1()
	{
		System.out.println(Thread.currentThread().getName());
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void Println2()
	{
		System.out.println(Thread.currentThread().getName());
	}
	
	public static void main(String[] agrs)
	{
		final testThread3 t =new testThread3();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
					t.Println1();
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				t.Println2();
			}
		},"t2");
		
		t1.start();
		t2.start();
	}
}
