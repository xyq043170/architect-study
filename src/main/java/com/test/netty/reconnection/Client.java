package com.test.netty.reconnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class Client {
	private static class SingletonHolder{
		static final Client instance = new Client();
	}
	
	public static Client getInstance()
	{
		return SingletonHolder.instance;
	}
	
	private EventLoopGroup group;
	private Bootstrap b;
	private ChannelFuture cf1;
	
	public Client()
	{
		//处理业务操作的线程组
				group = new NioEventLoopGroup();
				//3创建辅助启动类，对server进行参数配置
				b = new Bootstrap();
				b.group(group)
				.channel(NioSocketChannel.class)
				.handler(new LoggingHandler(LogLevel.INFO))
				.handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
						ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
						//超时handler(),当服务器与客户端在指定时间以上没有进行任何通信，则会关闭响应的通道。
						ch.pipeline().addLast(new ReadTimeoutHandler(5)); 
						ch.pipeline().addLast(new ClientHandler());
					}
				});
				
	}
	
	public void connect()
	{
		try {
			this.cf1 = b.connect("127.0.0.1", 8765).sync();
			System.out.println("远程服务器已经连接，可以进行数据交换...");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public ChannelFuture getChannelFuture()
	{
		if(this.cf1 == null){
			this.connect();
		}
		if(!this.cf1.channel().isActive()){
			this.connect();
		}
		return this.cf1;
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		final Client c = Client.getInstance();
//		c.connect();
		
		ChannelFuture cf = c.getChannelFuture();
			
		for (int i = 1; i <= 3; i++) {
			Req req = new Req();
			req.setId(i+"");
			req.setName("pro"+i);
			req.setRequestMessage("数据信息"+i);
			
			cf.channel().writeAndFlush(req);
			TimeUnit.SECONDS.sleep(4);
		}
		//进行阻塞
		cf.channel().closeFuture().sync();
		
		new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("进入子线程...");
                    ChannelFuture cf = c.getChannelFuture();
                    System.out.println(cf.channel().isActive());
                    System.out.println(cf.channel().isOpen());
                    
                    //再次发送数据
                    Req request = new Req();
                    request.setId("" + 4);
                    request.setName("pro" + 4);
                    request.setRequestMessage("数据信息" + 4);
                    cf.channel().writeAndFlush(request);                    
                    cf.channel().closeFuture().sync();
                    System.out.println("子线程结束.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//		cf2.channel().closeFuture().sync();
		System.out.println("断开连接,主线程结束..");
	}
}
