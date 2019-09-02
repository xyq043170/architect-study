package com.test.socket.nio.base2;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server implements Runnable{
	private Selector selector;
	
	private ByteBuffer readBuf =ByteBuffer.allocate(1024);
	
	public Server(int port)
	{
		try {
			this.selector = Selector.open();
			
			ServerSocketChannel ssc = ServerSocketChannel.open();
			ssc.configureBlocking(false);	//设置false为非阻塞模式
			ssc.bind(new InetSocketAddress(port));
			ssc.register(this.selector, SelectionKey.OP_ACCEPT);
			System.out.println("current port:"+port);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		while(true)
		{
			try {
				this.selector.select();
				Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
				while(keys.hasNext())
				{
					SelectionKey key = keys.next();
					keys.remove();
					if(key.isValid())
					{
						if(key.isAcceptable())
						{
							this.accept(key);
						}
						if(key.isReadable())
						{
							this.read(key);
						}
						if(key.isWritable())
						{
							this.write(key);
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	private void write(SelectionKey key) {
		// TODO Auto-generated method stub
		
	}

	private void read(SelectionKey key) {
		try {
			this.readBuf.clear();
			SocketChannel sc = (SocketChannel) key.channel();
			int count = sc.read(this.readBuf);
			if(count == -1)
			{
				key.channel().close();
				key.cancel();
				return;
			}
			
			this.readBuf.flip();
			byte[] bytes = new byte[this.readBuf.remaining()];
			
			this.readBuf.get(bytes);
			
			String body = new String(bytes).trim();
			System.out.println("Server:"+body);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void accept(SelectionKey key) {
		try {
			ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
			SocketChannel sc = ssc.accept();
			sc.configureBlocking(false);
			sc.register(this.selector, SelectionKey.OP_READ);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {
		new Thread(new Server(8765)).start();
	}

	
}
