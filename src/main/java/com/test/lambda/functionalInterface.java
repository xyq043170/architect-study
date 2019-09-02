package com.test.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class functionalInterface {
	public static void main(String[] args) {
		//Predicate接口
		Predicate<String> predicate = (s) -> s.length() > 0;
		              
		System.out.println(predicate.test("foo"));		// true
		System.out.println(predicate.negate().test("foo"));		// false
		
		Predicate<Boolean> nonNull = Objects::nonNull;
		Predicate<Boolean> isNull = Objects::isNull;

		Predicate<String> isEmpty = String::isEmpty;
		Predicate<String> isNotEmpty = isEmpty.negate();
		
		//Function 接口
		Function<String, Integer> toInteger = Integer::valueOf;
		Function<String, String> backToString = toInteger.andThen(String::valueOf);
		backToString.apply("123");     // "123"
		
		//Supplier 接口
		Supplier<Person> personSupplier = Person::new;
		personSupplier.get();   // new Person
		
		//Consumer 接口
		Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
		greeter.accept(new Person("Luke", "Skywalker"));
		
		//Comparator 接口
		Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
		 

		Person p1 = new Person("John", "Doe");
		Person p2 = new Person("Alice", "Wonderland");

		System.out.println(comparator.compare(p1, p2));             // > 0
		System.out.println(comparator.reversed().compare(p1, p2));  // < 0
		
		//Optional 接口
		Optional<String> optional = Optional.of("bam");
		 

		System.out.println(optional.isPresent());           // true
		System.out.println(optional.get());                 // "bam"
		System.out.println(optional.orElse("fallback"));    // "bam"
		optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
		
		//Stream 接口
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");
		
		//Filter 过滤
		stringCollection
	    .stream()
	    .filter((s) -> s.startsWith("a"))
	    .forEach(System.out::println);
		// "aaa2", "aaa1"
		
		//Sort 排序
		stringCollection
	    .stream()
	    .sorted()
	    .filter((s) -> s.startsWith("a"))
	    .forEach(System.out::println);
		// "aaa1", "aaa2"
		
		System.out.println(stringCollection);
		
		//Map 映射
		stringCollection
	    .stream()
	    .map(String::toUpperCase)
	    .sorted((a, b) -> b.compareTo(a))
	    .forEach(System.out::println);
		// "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"
		
		//Match 匹配
		boolean anyStartsWithA = 
			    stringCollection
			        .stream()
			        .anyMatch((s) -> s.startsWith("a"));
			 

			System.out.println(anyStartsWithA);      // true

			boolean allStartsWithA = 
			    stringCollection
			        .stream()
			        .allMatch((s) -> s.startsWith("a"));

			System.out.println(allStartsWithA);      // false

			boolean noneStartsWithZ = 
			    stringCollection
			        .stream()
			        .noneMatch((s) -> s.startsWith("z"));

			System.out.println(noneStartsWithZ);      // true
		
		//Count 计数
			long startsWithB = 
				    stringCollection
				        .stream()
				        .filter((s) -> s.startsWith("b"))
				        .count();
				 

				System.out.println(startsWithB);    // 3
		
		//Reduce 规约
		Optional<String> reduced =
		stringCollection
		.stream()
		.sorted()
		.reduce((s1, s2) -> s1 + "#" + s2);
					 

		reduced.ifPresent(System.out::println);
		// "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"
					
		//并行Streams
		int max = 1000000;
		List<String> values = new ArrayList<>(max);
		for (int i = 0; i < max; i++) {
			UUID uuid = UUID.randomUUID();
			values.add(uuid.toString());
		}
		
		//串行排序
		long t0 = System.nanoTime();
		long count = values.stream().sorted().count();
		System.out.println(count);
		long t1 = System.nanoTime();
		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("sequential sort took: %d ms", millis));
		
		//并行排序
		t0 = System.nanoTime();
		count = values.parallelStream().sorted().count();
		System.out.println(count);

		t1 = System.nanoTime();

		millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("parallel sort took: %d ms", millis));
		
		//Map
		Map<Integer, String> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
		    map.putIfAbsent(i, "val" + i);	//putIfAbsent 不需要我们做额外的存在性检查
		}
		map.forEach((id, val) -> System.out.println(val));
		
		map.computeIfPresent(3, (num, val) -> val + num);
		System.out.println(map.get(3));             // val33
		 
		map.computeIfPresent(9, (num, val) -> null);
		System.out.println(map.containsKey(9));     // false

		map.computeIfAbsent(23, num -> "val" + num);
		System.out.println(map.containsKey(23));    // true

		map.computeIfAbsent(3, num -> "bam");
		System.out.println(map.get(3));             // val33
		
		map.remove(3, "val3");
		System.out.println(map.get(3));             // val33
		 
		map.remove(3, "val33");
		System.out.println(map.get(3));             // null
		
		System.out.println(map.getOrDefault(42, "not found"));  // not found
		
		map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
		System.out.println(map.get(9));             // val9
		 
		map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
		System.out.println(map.get(9));             // val9concat
	}
}
