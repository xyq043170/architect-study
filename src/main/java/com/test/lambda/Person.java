package com.test.lambda;

public class Person {
	private String str1;
	private String str2;
	public static String firstName = "";
	public static String lastName = "";
	
	public Person(){};
	
	public Person(String str1,String str2)
	{
		this.str1 = str1;
		this.str2 = str2;
//		System.out.printf("str1 = %s,str2 = %s",this.str1,this.str2);
		firstName = str1;
		lastName = str2;
	}
	public String getStr1() {
		return str1;
	}
	public void setStr1(String str1) {
		this.str1 = str1;
	}
	public String getStr2() {
		return str2;
	}
	public void setStr2(String str2) {
		this.str2 = str2;
	}
}
