package com.test.recursive;

public class testRecursive1 {
	public static void main(String[] args) {
		disposeNum(1237);
	}

	private static void disposeNum(int i) {
		if(i <= 5000)
		{
			System.out.println(i);
			disposeNum(2*i);
		}
		else
		{
			System.out.println(i);
		}
	}
}
