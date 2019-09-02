package com.test.reflect;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;

public class ReflectCase {
	public static void main(String[] args) throws Exception {
        Proxy target = new Proxy();
        Method method = Proxy.class.getDeclaredMethod("run2");
        method.invoke(target);
    }

    static class Proxy {
        public void run() {
            System.out.println("run");
        }
        
        public void run2() {
            System.out.println("run2");
        }
    }

}
