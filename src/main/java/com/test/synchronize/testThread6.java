package com.test.synchronize;

public class testThread6{
	static class main
	{
		public int i = 10;
		public synchronized void send()
		{
			try {
				i--;
				System.out.println("main send i="+i);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	static class Sub extends main
	{
		public synchronized void send2()
		{
			try {
				while(i > 0)
				{
					i--;
					System.out.println("sub send i="+i);
					Thread.sleep(100);
					this.send();
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] agrs) throws InterruptedException
	{
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Sub sub = new Sub();
				sub.send2();
			}
		},"t1");
		
		t1.start();
	}
}
