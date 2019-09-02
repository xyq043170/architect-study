package com.test.threadthrow;

public class testThread8{
	private int i = 0;
	public synchronized void send()
	{
		while(true)
		{
			try {
				i++;
				Thread.sleep(200);
				System.out.println(Thread.currentThread().getName()+", i="+i);
				if(i == 10)
				{
					Integer.parseInt("a");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("log info i ="+i);
			}
		}
		
	}
	
	public static void main(String[] agrs) throws InterruptedException
	{
		final testThread8 t =new testThread8();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				t.send();
			}
		},"t1");
		
		t1.start();
	}
}
