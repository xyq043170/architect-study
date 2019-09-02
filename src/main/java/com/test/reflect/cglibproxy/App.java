package com.test.reflect.cglibproxy;

import com.test.reflect.jdkproxy.UserDao;

public class App {
	public static void main(String[] args) {
		//目标对象
        UserDao target = new UserDao();

        //代理对象
        UserDao proxy = (UserDao)new ProxyFactory(target).getProxyInstance();

        //执行代理对象的方法
        proxy.save();
	}
}
