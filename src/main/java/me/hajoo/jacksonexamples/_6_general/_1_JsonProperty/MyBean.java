package me.hajoo.jacksonexamples._6_general._1_JsonProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyBean {

	public int id;
	private String name;

	public MyBean() {
	}

	public MyBean(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@JsonProperty("name")
	public void setTheName(String name) {
		this.name = name;
	}

	@JsonProperty("name")
	public String getTheName() {
		return name;
	}
}
