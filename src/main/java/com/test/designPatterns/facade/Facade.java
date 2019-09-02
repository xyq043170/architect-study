package com.test.designPatterns.facade;

public class Facade {
	public void test()
	{
		AmoduleApi a = new AmoduleImpl();
		a.testA();
		BmoduleApi b = new BmoduleImpl();
		b.testB();
		CmoduleApi c = new CmoduleImpl();
		c.testC();
	}
	
	//重写示例
	public void generate()
	{
		new AmoduleImpl().testA();
		new BmoduleImpl().testB();
		new CmoduleImpl().testC();
	}
}
