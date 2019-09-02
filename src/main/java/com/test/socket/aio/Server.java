package com.test.socket.aio;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private ExecutorService pool;
	private AsynchronousChannelGroup threadGroup;
	
	public AsynchronousServerSocketChannel assc;
	
	@SuppressWarnings("unchecked")
	public Server(int port)
	{
		try {
			pool = Executors.newCachedThreadPool();
			threadGroup = AsynchronousChannelGroup.withCachedThreadPool(pool, 1);
			assc = AsynchronousServerSocketChannel.open(threadGroup);
			assc.bind(new InetSocketAddress(port));
			System.out.println("current port:"+port);
			//进行阻塞
			assc.accept(this,new ServerCompletionHandler());
			//一直阻塞不让服务器停止
			Thread.sleep(Integer.MAX_VALUE);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		Server server = new Server(8765);
	}
}
