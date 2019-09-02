package com.test.charat;

/*
 * 如果一串字符如"aaaabbc 中国1512"要分别统计英文字符的数量，中文字符的数量，和数字
字符的数量，假设字符中没有中文字符、英文字符、数字字符之外的其他特殊字符。
 * */
public class testCharat {
	public static void main(String[] args) {
		int engishCount= 0;
		int chineseCount= 0;
		int digitCount = 0;
		String str = "asdasd123214sf是的as83438sfsaf撒打算909dhhjdoajso啊实打实";
		
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if(ch>='0' && ch<='9')
			{
				digitCount++;
			}
			else if((ch>='a' && ch<='z') || (ch>='A' && ch<='Z'))
			{
				engishCount++;
			}
			else
			{
				chineseCount++;
			}
		}
		
		System.out.println("中文字符数量==》"+chineseCount);
		System.out.println("英文字符数量==》"+engishCount);
		System.out.println("数字字符数量==》"+digitCount);
	}
}
