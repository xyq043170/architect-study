package com.test.concurrentLinkedDeque;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class test3{
	public static void main(String[] args) throws InterruptedException {
		ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
		Thread threads[]=new Thread[100];
		for (int i = 0; i < threads.length; i++) {
			AddTask task = new AddTask(list);
			threads[i] = new Thread(task);
			threads[i].start();
		}
		
		System.out.printf("Main: %d AddTask threads have been launched\n", threads.length);
		
		for (int i = 0; i < threads.length; i++) {
			try {  
                threads[i].join();  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            } 
		}
		
		System.out.printf("Main: Size of the List: %d\n", list.size()); 
		
		for (int i = 0; i < threads.length; i++) {  
            RemoveTask task = new RemoveTask(list);  
            threads[i] = new Thread(task);  
            threads[i].start();  
        }  
        System.out.printf("Main: %d RemoveTask threads have been launched\n", threads.length);  
   
        for (int i = 0; i < threads.length; i++) {  
            try {  
                threads[i].join();  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
        System.out.printf("Main: Size of the List: %d\n", list.size());
	}
}
