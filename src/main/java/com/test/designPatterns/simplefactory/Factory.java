package com.test.designPatterns.simplefactory;

public class Factory {
	public static testPrint creatApi()
	{
		testPrint test = new testPrintImpl();
		return test;
	}
}
