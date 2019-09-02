package com.test.synchronize;

public class testThread4{
	private String user="admin";
	private String pwd = "123";
	
	public synchronized void setValue(String user,String pwd)
	{
		this.user = user;
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.pwd = pwd;
		System.out.println("current user= "+user+",pwd="+pwd);
	}
	
	public synchronized void getValue()
	{
		System.out.println("current user= "+this.user+",pwd="+this.pwd);
	}
	
	public static void main(String[] agrs) throws InterruptedException
	{
		final testThread4 t =new testThread4();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
					t.setValue("add", "456");
			}
		},"t1");
		
		t1.start();
		Thread.sleep(1000);
		t.getValue();
	}
}
