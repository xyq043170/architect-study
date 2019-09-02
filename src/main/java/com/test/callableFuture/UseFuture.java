package com.test.callableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class UseFuture implements Callable<String>{
	private String query;
	public UseFuture(String query)
	{
		this.query=query;
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String query = "query";
		FutureTask<String> future = new FutureTask<String>(new UseFuture(query));
		FutureTask<String> future2 = new FutureTask<String>(new UseFuture(query));
		ExecutorService pool = Executors.newFixedThreadPool(2);
		Future f =pool.submit(future);
		Future f2 =pool.submit(future2);
		System.out.println("请求完毕");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("数据:"+future.get());
		System.out.println("数据:"+future2.get());
		System.out.println("1221321321");
		pool.shutdown();
	}
	
	@Override
	public String call() throws Exception {
		Thread.sleep(5000);
		String ret = this.query+"处理完成";
		return ret;
	}
	
}
