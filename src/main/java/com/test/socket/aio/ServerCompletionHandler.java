package com.test.socket.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,Server>{

	@Override
	public void completed(AsynchronousSocketChannel asc, Server attachment) {
		attachment.assc.accept(attachment, this);
		read(asc);
	}

	@Override
	public void failed(Throwable exc, Server attachment) {
		exc.printStackTrace();
	}

	private void read(final AsynchronousSocketChannel asc) {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		asc.read(buf, buf, new CompletionHandler<Integer, ByteBuffer>() {

			@Override
			public void completed(Integer size, ByteBuffer attachment) {
				attachment.flip();
				System.out.println("Server size:"+size);
				String data = new String(attachment.array()).trim();
				System.out.println("Server data:"+data);
				String response = "服务器响应："+data;
				write(asc,response);
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				exc.printStackTrace();
			}
		});
		
	}
	
	private static void write(AsynchronousSocketChannel asc, String response) {
		try {
			ByteBuffer buf = ByteBuffer.allocate(1024);
			buf.put(response.getBytes());
			buf.flip();
			asc.write(buf).get();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
