package com.test.synchronize;

public class testThread2{
	private static int num = 0;
	public static synchronized void Println(String tag) throws InterruptedException
	{
		if(tag.equals("a"))
		{
			num = 100;
			System.out.println("set tag a");
			Thread.sleep(1000);
		}
		else
		{
			num = 200;
			System.out.println("set tag b");
		}
		System.out.println("tag="+tag+", num="+num);
	}
	
	public static void main(String[] agrs)
	{
		final testThread2 t =new testThread2();
		final testThread2 tt =new testThread2();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					t.Println("a");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					tt.Println("b");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
	}
}
