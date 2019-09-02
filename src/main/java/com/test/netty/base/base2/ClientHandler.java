package com.test.netty.base.base2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends ChannelHandlerAdapter{
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("client channel active!");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			 
//			 ByteBuf buf = (ByteBuf) msg;
//			 byte[] bytes = new byte[buf.readableBytes()];
//			 buf.readBytes(bytes);
//			 String request = new String(bytes,"utf-8");
			 System.out.println("Client:"+msg);
			 //x写
//			 ctx.writeAndFlush(new String("1212121").getBytes());
			 
			 } finally {
			         ReferenceCountUtil.release(msg);
			 }
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
