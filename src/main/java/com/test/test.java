package com.test;

import java.util.concurrent.CountDownLatch;

import com.lmax.disruptor.SleepingWaitStrategy;


public class test {
	public static void main(String[] args) {
		final CountDownLatch count = new CountDownLatch(3);
		System.out.println("开始运行..."+Thread.currentThread().getName());
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("开始运行..."+Thread.currentThread().getName());
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("运行完毕..."+Thread.currentThread().getName());
				count.countDown();
			}
		},"t1").start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("开始运行..."+Thread.currentThread().getName());
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("运行完毕..."+Thread.currentThread().getName());
				count.countDown();
			}
		},"t2").start();

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("开始运行..."+Thread.currentThread().getName());
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("运行完毕..."+Thread.currentThread().getName());
				count.countDown();
			}
		},"t3").start();
		
		try {
			count.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("继续运行..."+Thread.currentThread().getName());
		
	}
}
