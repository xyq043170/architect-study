package com.test.socket.bio.base1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	final static String ADDRESS="127.0.0.1";
	final static int PROT = 8765;
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
			socket = new Socket(ADDRESS,PROT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			
			out.println("接收到客户端请求数据...");
			String read = in.readLine();
			System.out.println("Client:"+read);
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
//			if(socket != null)
//			{
//				try {
//					socket.close();
//				} catch (Exception e2) {
//					// TODO: handle exception
//				}
//			}
		}
	}
}
