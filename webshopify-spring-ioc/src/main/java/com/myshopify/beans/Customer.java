package com.myshopify.beans;

import java.io.Serializable;

public class Customer implements Serializable {
	private int id;
	private String name;

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	
}
