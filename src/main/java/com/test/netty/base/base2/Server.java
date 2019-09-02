package com.test.netty.base.base2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class Server {
	private int port;
	public Server(int port) {
		this.port = port;
	}
	public void run() throws InterruptedException {
		//1线程组用于接收client端的连接的
				EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
				//2线程组用于读写实际业务操作的
				EventLoopGroup workerGroup = new NioEventLoopGroup();
				try {
					//3创建辅助启动类，对server进行参数配置
					ServerBootstrap b = new ServerBootstrap();
					//4把2个工作线程组加进来
					b.group(bossGroup, workerGroup)
					//使用指定通道tcp协议的用NioServerSocketChannel
					.channel(NioServerSocketChannel.class)
					//指定具体的事件处理器
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							//设置定长字符串接收
							ch.pipeline().addLast(new FixedLengthFrameDecoder(5));
							//设置字符串形式的解码
							ch.pipeline().addLast(new StringDecoder());
							ch.pipeline().addLast(new ServerHandler());
						}
					})
					.option(ChannelOption.SO_BACKLOG, 1024)	//设置tcp缓冲区大小
					.option(ChannelOption.SO_SNDBUF, 32*1024)	//设置发送缓冲区大小
					.option(ChannelOption.SO_RCVBUF, 32*1024)	//设置接收缓冲区大小
					.childOption(ChannelOption.SO_KEEPALIVE, true);//保持连接
					
					System.out.println("Server port:"+port);
					//绑定指定端口进行监听
					ChannelFuture f1 = b.bind(port).sync();
//					ChannelFuture f2 = b.bind(8764).sync();
					
					//在关闭之前进行阻塞，等待关闭
					f1.channel().closeFuture().sync();
//					f2.channel().closeFuture().sync();
				} finally {
					workerGroup.shutdownGracefully();
					bossGroup.shutdownGracefully();

				}
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
