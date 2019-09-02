package com.test.netty.base.base1;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class Client {
	public static void main(String[] args) throws InterruptedException {
		//处理业务操作的线程组
		EventLoopGroup group = new NioEventLoopGroup();
		//3创建辅助启动类，对server进行参数配置
		Bootstrap b = new Bootstrap();
		b.group(group)
		.channel(NioSocketChannel.class)
		.handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				//设置特殊分隔符
				ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
				ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
				//设置字符串形式的解码
				ch.pipeline().addLast(new StringDecoder());
				ch.pipeline().addLast(new ClientHandler());
			}
		});
		
		ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();
//		ChannelFuture cf2 = b.connect("127.0.0.1", 8764).sync();
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("aaaa$_".getBytes()));
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("bbb$_".getBytes()));
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("ccccc$_".getBytes()));
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("dddddd$_".getBytes()));
//		cf2.channel().writeAndFlush(Unpooled.copiedBuffer("hello world!".getBytes()));
		
		cf1.channel().closeFuture().sync();
//		cf2.channel().closeFuture().sync();
		
		group.shutdownGracefully();
	}
}
