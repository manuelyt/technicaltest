package com.technical.test.DTO;

import java.util.List;

public class User {
	private Long id;
	private String name;
	private int age;
	private String city;
	private List<userData> userData;
	private boolean deleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<userData> getUserData() {
		return userData;
	}

	public void setUserData(List<userData> userData) {
		this.userData = userData;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
