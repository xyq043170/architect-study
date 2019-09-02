package com.test.netty.base.base3;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class Client {
	public static void main(String[] args) throws InterruptedException {
		//����ҵ��������߳���
		EventLoopGroup group = new NioEventLoopGroup();
		//3�������������࣬��server���в�������
		Bootstrap b = new Bootstrap();
		b.group(group)
		.channel(NioSocketChannel.class)
		.option(ChannelOption.TCP_NODELAY, true)
		.handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new LineBasedFrameDecoder(1024));		//LineBasedFrameDecoder+StringDecoder�ǰ����л����ļ�������
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
