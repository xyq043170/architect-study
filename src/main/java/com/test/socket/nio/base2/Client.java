package com.test.socket.nio.base2;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
	public static void main(String[] args) {
		InetSocketAddress address = new InetSocketAddress("127.0.0.1",8765);
		
		SocketChannel sc = null;
		
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		try {
			sc = SocketChannel.open();
			sc.connect(address);
			
			while(true)
			{
				byte[] bytes=new byte[1024];
				System.in.read(bytes);
				
				buf.put(bytes);
				buf.flip();
				sc.write(buf);
				buf.clear();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(sc!=null)
			{
				try {
					sc.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	}
}
