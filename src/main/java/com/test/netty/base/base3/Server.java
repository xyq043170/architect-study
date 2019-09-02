package com.test.netty.base.base3;

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
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class Server {
	private int port;
	public Server(int port) {
		this.port = port;
	}
	public void run() throws InterruptedException {
		//1�߳������ڽ���client�˵����ӵ�
				EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
				//2�߳������ڶ�дʵ��ҵ�������
				EventLoopGroup workerGroup = new NioEventLoopGroup();
				try {
					//3�������������࣬��server���в�������
					ServerBootstrap b = new ServerBootstrap();
					//4��2�������߳���ӽ���
					b.group(bossGroup, workerGroup)
					//ʹ��ָ��ͨ��tcpЭ�����NioServerSocketChannel
					.channel(NioServerSocketChannel.class)
					//ָ��������¼�������
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
							ch.pipeline().addLast(new StringDecoder());
							ch.pipeline().addLast(new ServerHandler());
						}
					})
					.option(ChannelOption.SO_BACKLOG, 1024);	//����tcp��������С
					
					System.out.println("Server port:"+port);
					//��ָ���˿ڽ��м���
					ChannelFuture f1 = b.bind(port).sync();
//					ChannelFuture f2 = b.bind(8764).sync();
					
					//�ڹر�֮ǰ�����������ȴ��ر�
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
