package com.test.singleton;

//单例静态内部类
public class Singleton3 {
	private static class SingletonHolder {  
	    private static final Singleton3 INSTANCE = new Singleton3();  
	    }  
	    private Singleton3 (){}  
	    public static final Singleton3 getInstance() {  
	    return SingletonHolder.INSTANCE;  
	    }  
}

/*这种方式同样利用了classloder的机制来保证初始化instance时
 * 只有一个线程，这种方式是Singleton类被装载了，instance不
 * 一定被初始化。因为SingletonHolder类没有被主动使用，只有
 * 显示通过调用getInstance方法时，才会显示装载SingletonHolder
 * 类，从而实例化instance。想象一下，如果实例化instance很
 * 消耗资源，我想让他延迟加载！这个时候，这种方式相比第2种
 * 方式就显得很合理。*/
