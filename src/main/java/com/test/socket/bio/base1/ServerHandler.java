package com.test.socket.bio.base1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler implements Runnable{
	private Socket socket;
	public ServerHandler(Socket socket)
	{
		this.socket = socket;
	}
	@Override
	public void run() {
		System.out.println("进入服务器handler方法...");
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream(),true);
			String body = null;
			while(true)
			{
				body = in.readLine();
				if(body == null)break;
				System.out.println("信息处理...Server:"+body);
				out.println("服务器端响应接收的数据...");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
//			if(in != null)
//			{
//				try {
//					in.close();
//				} catch (Exception e2) {
//					// TODO: handle exception
//				}
//			}
//			
//			if(out != null)
//			{
//				try {
//					out.close();
//				} catch (Exception e2) {
//					// TODO: handle exception
//				}
//			}
//			
//			if(this.socket != null)
//			{
//				try {
//					this.socket.close();
//				} catch (Exception e2) {
//					// TODO: handle exception
//				}
//			}
		}
		
	}
}
