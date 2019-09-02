package com.test.netty.base.base1;

import java.nio.ByteBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ServerHandler extends ChannelHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		 try {
//			 
//			 ByteBuf buf = (ByteBuf) msg;
//			 byte[] bytes = new byte[buf.readableBytes()];
//			 buf.readBytes(bytes);
//			 String request = new String(bytes,"utf-8");
//			 System.out.println("Server:"+request);
//			 //x写
//			 String response = "Server ：Hi Client！";
//			 ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()))
//			 //写发送后会断开连接
//			 .addListener(ChannelFutureListener.CLOSE);
//			 
//			 } finally {
//			         ReferenceCountUtil.release(msg);
//			 }
		System.out.println("Seerver:"+msg);
		
		String response = "服务器响应:"+msg+"$_";
		 ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
}
