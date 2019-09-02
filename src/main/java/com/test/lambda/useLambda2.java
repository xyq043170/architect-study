package com.test.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class useLambda2 {
	public static void main(String[] args) {
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp","a");
		 
//	    System.out.println("Languages which starts with J :");
//	    filter(languages, (str)->((String) str).startsWith("J"));
//	 
//	    System.out.println("Languages which ends with a ");
//	    filter(languages, (str)->((String) str).endsWith("a"));
//	 
//	    System.out.println("Print all languages :");
//	    filter(languages, (str)->true);
//	 
//	    System.out.println("Print no language : ");
//	    filter(languages, (str)->false);
//	 
//	    System.out.println("Print language whose length greater than 4:");
//	    filter(languages, (str)->((String) str).length() > 4);
		
		
		//lambda表达式中加入Predicate	and()、or()和xor()
		Predicate<String> startsWithJ = (n) -> n.startsWith("J");
		Predicate<String> fourLetterLong = (n) -> n.length() == 4;
		languages.stream()
		    .filter(startsWithJ.and(fourLetterLong))
		    .forEach((n) -> System.out.println("nName, which starts with 'J' and four letter long is : " + n));
		
		// 创建一个字符串列表，每个字符串长度大于2
		List<String> filtered = languages.stream().filter(x -> x.length()> 2).collect(Collectors.toList());
		System.out.printf("Original List : %s, filtered list : %s %n", languages, filtered);
	}
	
	//lambda表达式和函数式接口Predicate
//	public static void filter(List<String> names, Predicate condition) {
//	    for(String name: names)  {
//	        if(condition.test(name)) {
//	            System.out.println(name + " ");
//	        }
//	    }
//	}
	
	//lambda表达式和函数式接口Predicate
	public static void filter(List<String> names, Predicate condition) {
		names.stream().filter((name) -> (condition.test(name))).forEach((name)->{
			System.out.println(name+" ");
		});
	}
}
