package com.xugu.hibernate;


public class Tt1 {
	private String id;
	private String name;
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	Tt1() {
	}

	Tt1(String id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Tt1 [name=" + name + ", password=" + password + "]";
	}
}