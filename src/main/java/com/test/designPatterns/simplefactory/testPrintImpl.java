package com.test.designPatterns.simplefactory;

public class testPrintImpl implements testPrint {

	@Override
	public void systemPrint() {
		System.out.println("hello 简单工厂模式");
	}

}
