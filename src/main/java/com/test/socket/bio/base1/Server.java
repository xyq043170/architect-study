package com.test.socket.bio.base1;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	final static int PROT=8765;
	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(PROT);
			System.out.println("服务器开始启动");
			Socket socket=server.accept();
			System.out.println("服务器与客户端连接成功！");
			new Thread(new ServerHandler(socket)).start();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
//			if(server != null)
//			{
//				try {
//					server.close();
//				} catch (Exception e2) {
//					// TODO: handle exception
//				}
//			}
//			server = null;
		}
	}
}
