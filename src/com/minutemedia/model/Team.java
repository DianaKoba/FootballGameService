package com.minutemedia.model;

public class Team {

	private int id;
	private String name;

	public Team(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}