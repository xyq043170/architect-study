package com.test.sort;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 从类似如下的文本文件中读取出所有的姓名，并打印出重复的姓名和重复的
次数，并按重复次数排序：
1,张三,28
2,李四,35
3,张三,28
4,王五,35
5,张三,28
6,李四,35
7,赵六,28
8,田七,35
 * */
public class testSort1 {
	public static void main(String[] args) throws IOException {
		int len = 0;
		List<Integer> list = new ArrayList<Integer>();
		FileInputStream in = new FileInputStream("d:"+File.separator+"b.txt");
		byte[] buf = new byte[1024];
		String str="";
		if((len =in.read(buf)) != -1)
		{
			str = new String(buf, 0, len, "GBK");
		}
//		System.out.println(str);
		Map<String, Integer> map = new HashMap<String, Integer>();
		String[] temp = str.split("\n");
		for (int i = 0; i < temp.length; i++) {
			String name=temp[i].split(",")[1]+"-"+temp[i].split(",")[2];
			if(map.get(name) == null)
			{
				map.put(name, 1);
			}
			else
			{
				map.put(name, (Integer.valueOf(map.get(name).toString())+1));
			}
		}
		
		for (Map.Entry<String, Integer> entry : map.entrySet()) {  
			list.add(entry.getValue());
//		    System.out.println("Key =" + entry.getKey() + ", Value = " + entry.getValue());
		}  
		java.util.Collections.sort(list);
		for (int i = (list.size()-1); i >= 0; i--) {
			for (Map.Entry<String, Integer> entry : map.entrySet()) {  
				if(entry.getValue() == list.get(i))
				{
					System.out.println(entry.getKey()+","+entry.getValue());
					map.remove(entry.getKey());
					break;
				}
			}
		}
	}
}
