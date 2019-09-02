package com.test.singleton;

//单例饿汉模式
public class Singleton2 {
	private static Singleton2 instance = new Singleton2();  
    private Singleton2 (){}  
    public static Singleton2 getInstance() {  
    return instance;  
    }  
}

/*这种方式基于classloder机制避免了多线程的同步问题，
 * instance在类装载时就实例化。目前java单例是指一个
 * 虚拟机的范围，因为装载类的功能是虚拟机的，所以一个
 * 虚拟机在通过自己的ClassLoader装载饿汉式实现单例类
 * 的时候就会创建一个类的实例。这就意味着一个虚拟机里
 * 面有很多ClassLoader，而这些classloader都能装载某
 * 个类的话，就算这个类是单例，也能产生很多实例。当然
 * 如果一台机器上有很多虚拟机，那么每个虚拟机中都有至
 * 少一个这个类的实例的话，那这样 就更不会是单例了。
 * (这里讨论的单例不适合集群！)*/
 