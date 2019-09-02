package com.test.lambda;

import java.util.Arrays;
import java.util.List;

public class useLambda1 {
	public static void main(String[] args) {
		//java 8之前
		List<String> features = Arrays.asList("a","b","c");
		for (String feature : features) {
		    System.out.println(feature);
		}
		//java 8 lambda表达式
		Arrays.asList("a","b","c").forEach(e -> System.out.println(e));
		
		Arrays.asList("a","b","c").forEach(System.out::println);
		
		Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
		
		//java 8之前
		new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+" start ...");
			}
		},"T1").start();
		
		//java 8 lambda表达式
		new Thread(() -> {
				System.out.println(Thread.currentThread().getName()+" start ...");
				System.out.println(Thread.currentThread().getName()+" start2 ...");}
				,"T2").start();
	}
}
