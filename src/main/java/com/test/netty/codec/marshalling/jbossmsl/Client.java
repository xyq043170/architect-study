package com.test.netty.codec.marshalling.jbossmsl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class Client {

	public static void main(String[] args) throws InterruptedException, IOException {
		//处理业务操作的线程组
		EventLoopGroup group = new NioEventLoopGroup();
		//3创建辅助启动类，对server进行参数配置
		Bootstrap b = new Bootstrap();
		b.group(group)
		.channel(NioSocketChannel.class)
		.handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
				ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
				ch.pipeline().addLast(new ClientHandler());
			}
		});
		
		ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();
//		ChannelFuture cf2 = b.connect("127.0.0.1", 8764).sync();
		
		for (int i = 0; i < 5; i++) {
			Req req = new Req();
			req.setId(i+"");
			req.setName("pro"+i);
			req.setRequestMessage("数据信息"+i);
			
			String path = System.getProperty("user.dir")+File.separator+"sources"+File.separator+"timg.jpg";
			File file = new File(path);
			FileInputStream in = new FileInputStream(file);
			byte[] data = new byte[in.available()];
			in.read(data);
			in.close();
			req.setAttachment(GzipUtil.compressByte(data));
			
			cf1.channel().writeAndFlush(req);
		}
		
		cf1.channel().closeFuture().sync();
//		cf2.channel().closeFuture().sync();
		
		group.shutdownGracefully();
	}
}
