package com.test.netty.reconnection;

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
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

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
					.option(ChannelOption.SO_BACKLOG, 1024)	//设置tcp缓冲区大小
					//设置日志
					.handler(new LoggingHandler(LogLevel.INFO))
					//指定具体的事件处理器
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
							ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
							ch.pipeline().addLast(new ReadTimeoutHandler(5)); 
							ch.pipeline().addLast(new ServerHandler());
						}
					});
					
					
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
