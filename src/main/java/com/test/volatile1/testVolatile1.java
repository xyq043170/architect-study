package com.test.volatile1;

public class testVolatile1 {
	private static volatile boolean bChanged;  
	  
    public static void main(String[] args) throws InterruptedException {  
        new Thread() {  
  
            @Override  
            public void run() {  
                for (;;) {  
                	
                    if (bChanged == !bChanged) {  
                    	System.out.println(Thread.currentThread().getName()+",bChanged="+bChanged);
                    	System.out.println("程序退出");
                        System.exit(0);  
                    }  
                }  
            }  
        }.start();  
        Thread.sleep(1);  
        new Thread() {  
  
            @Override  
            public void run() {  
                for (;;) {  
                    bChanged = !bChanged;  
                    System.out.println(Thread.currentThread().getName()+",bChanged="+bChanged);
                }  
            }  
        }.start();  
    }  
}
