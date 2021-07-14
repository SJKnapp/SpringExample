package com.example.demo.data;

public class Duck {
	
	private int id;
	private String name;
	private String age;
	private static int nextId = 0;
	
	public Duck(String name, String age) {
		super();
		this.name = name;
		this.age = age;
		this.setId(++nextId);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
