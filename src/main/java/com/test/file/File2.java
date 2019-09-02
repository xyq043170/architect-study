package com.test.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class File2 {
	public static void main(String[] args) throws Exception {
		File srcDir = new File("D:"+File.separator);
		if(!(srcDir.exists()&& srcDir.isDirectory()))
			throw new Exception("目录不存在");
		File[] files= srcDir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.contains(".jar");
			}
		});
		System.out.println(files.length);
		
		File tarDir = new File("D:"+File.separator+"jad");
		if(!tarDir.exists())tarDir.mkdir();
		for(File f:files){
			FileInputStream fis = new FileInputStream(f);
			String tarName = f.getName().replaceAll("\\.jar$", ".jad");
			FileOutputStream fos = new FileOutputStream(new File(tarDir,tarName));
			copy(fis,fos);
			fis.close();
			fos.close();
		}
	}

	private static void copy(FileInputStream fis, FileOutputStream fos) throws IOException {
		int len = 0;
		byte[] buf = new byte[1024];
		while((len = fis.read(buf)) != -1)
		{
			fos.write(buf,0,len);
		}
	}
}
