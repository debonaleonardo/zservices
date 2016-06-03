package com.services.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class Customer {

	private String name;
	private String email;
	private Integer age;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
