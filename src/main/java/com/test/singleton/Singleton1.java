package com.test.singleton;

//单例懒汉模式
public class Singleton1 {
	private static Singleton1 instance;
    private Singleton1 (){}

    public static synchronized Singleton1 getInstance() {
	if (instance == null) {
	    instance = new Singleton1();
	}
	return instance;
    }
}

/*这种写法能够在多线程中很好的工作，而且看起来它也具备很好的lazy loading，但是，遗憾的是，效率很低，99%情况下不需要同步。*/
