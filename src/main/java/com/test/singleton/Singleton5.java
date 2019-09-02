package com.test.singleton;

//单例饱汉模式
public class Singleton5 {
	private final static Singleton5 instance =new Singleton5();
	public static Singleton5 getInstance(){
		return instance;
	}
}
