package com.test.designPatterns.simplefactory;

public class testMain {
	public static void main(String[] args) {
		testPrint test = Factory.creatApi();
		test.systemPrint();
	}
}
