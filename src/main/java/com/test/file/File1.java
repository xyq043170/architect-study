package com.test.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class File1 {
	public static void main(String[] args) throws IOException {
		FileInputStream in = new FileInputStream("d:"+File.separator+"a.txt");
		
		byte[] bytes = new byte[1024];
		int length=in.read(bytes);
		if(length > 0)
		{
			String str = new String(bytes,0,length);
			System.out.println(str);
		}
		FileOutputStream out = new FileOutputStream("d:"+File.separator+"a.txt");
		out.write("加油努力！将来的你一定会感谢现在奋斗的你！".getBytes());
		out.flush();
		bytes = new byte[1024];
		length=in.read(bytes);
		if(length > 0)
		{
			String str = new String(bytes,0,length);
			System.out.println(str);
		}
		in.close();
		out.close();
	}
}
