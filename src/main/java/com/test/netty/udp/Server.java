package com.test.netty.udp;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

public class Server {
	private int port;
	public Server(int port) {
		this.port = port;
	}
	public void run() throws InterruptedException {
		//处理业务操作的线程组
				EventLoopGroup group = new NioEventLoopGroup();
				//3创建辅助启动类，对server进行参数配置
				Bootstrap b = new Bootstrap();
				b.group(group)
				.channel(NioDatagramChannel.class)
				.option(ChannelOption.SO_BROADCAST, true)
				.handler(new ServerHandler());
				
				b.bind(port).sync().channel().closeFuture().await();
				
				group.shutdownGracefully();
	}
	public static void main(String[] args) throws InterruptedException {
		int port;
		        if (args.length > 0) {
		            port = Integer.parseInt(args[0]);
		        } else {
		            port = 8765;
		        }
		        new Server(port).run();

	}
}
