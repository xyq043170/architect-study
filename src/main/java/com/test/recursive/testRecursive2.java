package com.test.recursive;

public class testRecursive2 {
	private static int count = 0;
	public static void main(String[] args) {
		disposeNum(10);
	}

	private static void disposeNum(int i) {
		if(count < 8)
		{
			count++;
			System.out.println(i);
			disposeNum(i+2);
			
		}
		
	}
}
