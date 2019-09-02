package com.test.synchronize;

public class testThread5{
	public synchronized void method1()
	{
		System.out.println("This is method1");
		method2();
	}
	
	public synchronized void method2()
	{
		System.out.println("This is method2");
		method3();
	}
	
	public synchronized void method3()
	{
		System.out.println("This is method3");
	}
	
	public static void main(String[] agrs) throws InterruptedException
	{
		final testThread5 t =new testThread5();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
					t.method1();
			}
		},"t1");
		
		t1.start();
	}
}
