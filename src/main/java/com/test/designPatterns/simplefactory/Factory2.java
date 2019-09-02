package com.test.designPatterns.simplefactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Factory2 {
	public static testPrint creatApi()
	{
		Properties p = new Properties();
		InputStream in = null;
		try {
			in = Factory.class.getResourceAsStream(
			"FactoryTest.properties");
			p.load(in);
		} catch (IOException e) {
			System.out.println(
			"装载工厂配置文件出错了，具体的堆栈信息如下：");
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		testPrint api=null;
		try {
			api = (testPrint)Class.forName(p.getProperty("ImplClass")).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return api;
	}
}
