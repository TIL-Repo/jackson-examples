package me.hajoo.jacksonexamples._6_general._7_JsonFilter;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("myFilter")
public class BeanWithFilter {

	public int id;
	public String name;

	public BeanWithFilter(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
