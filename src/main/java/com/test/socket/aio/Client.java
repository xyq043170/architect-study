package com.test.socket.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public class Client implements Runnable{
	private AsynchronousSocketChannel asc;
	
	public Client() throws IOException {
		asc = AsynchronousSocketChannel.open();
	}
	
	public void connect()
	{
		asc.connect(new InetSocketAddress("127.0.0.1", 8765));
	}
	
	public void write(String request)
	{
		try {
			asc.write(ByteBuffer.wrap(request.getBytes())).get();
			read();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void read() {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		try {
			asc.read(buf).get();
			buf.flip();
			byte[] bytes=new byte[buf.remaining()];
			buf.get(bytes);
			System.out.println(new String(bytes,"utf-8").trim());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void run() {
		while(true)
		{
			
		}
	}
	public static void main(String[] args) throws IOException, InterruptedException {
		Client c1 = new Client();
		c1.connect();
		
		Client c2 = new Client();
		c2.connect();
		
		Client c3 = new Client();
		c3.connect();
		
		new Thread(c1,"c1").start();
		new Thread(c2,"c2").start();
		new Thread(c3,"c3").start();
		
		Thread.sleep(1000);
		c1.write("c1 aaa");
		c2.write("c2 bbbb");
		c3.write("c3 ccccc");
		
	}

	
}
