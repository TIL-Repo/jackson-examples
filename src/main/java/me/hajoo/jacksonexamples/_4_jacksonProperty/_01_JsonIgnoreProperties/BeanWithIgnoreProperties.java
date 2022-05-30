package me.hajoo.jacksonexamples._4_jacksonProperty._01_JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"id"})
public class BeanWithIgnoreProperties {

	public int id;
	public String name;

	public BeanWithIgnoreProperties(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
