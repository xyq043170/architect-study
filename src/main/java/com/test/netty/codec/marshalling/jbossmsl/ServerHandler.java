package com.test.netty.codec.marshalling.jbossmsl;

import java.io.File;
import java.io.FileOutputStream;
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
		
		Req req = (Req) msg;
		System.out.println("Server:"+req.getId()+","+req.getName()+","+req.getRequestMessage());
		
		byte[] attachment = GzipUtil.uncompress(req.getAttachment());
		String path = System.getProperty("user.dir")+File.separator+"receive"+File.separator+"timg2.jpg";
		FileOutputStream out = new FileOutputStream(path);
		out.write(attachment);
		out.close();
		
		Resp resp = new Resp();
		resp.setId(req.getId());
		resp.setName("resp"+req.getId());
		resp.setResponseMessage("响应内容"+req.getId());
		ctx.writeAndFlush(resp);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
}
