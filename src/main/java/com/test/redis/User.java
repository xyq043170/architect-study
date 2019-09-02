package com.test.redis;

import java.io.Serializable;

public class User implements Serializable{
	private String name;
	private int age;
	private String sex;
	private String id;
	public User()
	{
		
	}
	public User(String id,String name,int age,String sex)
	{
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
