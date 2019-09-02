package com.test.singleton;
//双重检验
public class Singleton4 {
	private volatile static Singleton4 singleton;
    private Singleton4 (){}
    public static Singleton4 getSingleton() {
	if (singleton == null) {
	    synchronized (Singleton4.class) {
		if (singleton == null) {
		    singleton = new Singleton4();
		}
	    }
	}
	return singleton;
    }
}

/*这样方式实现线程安全地创建实例，而又不会对性能造成太大影响。
 * 它只是第一次创建实例的时候同步，以后就不需要同步了。
由于volatile关键字屏蔽了虚拟机中一些必要的代码优化，所以运行
效率并不是很高，因此建议没有特别的需要不要使用。双重检验锁方
式的单例不建议大量使用，根据情况决定。*/
