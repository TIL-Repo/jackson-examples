package me.hajoo.jacksonexamples._4_jacksonProperty._04_JsonInclude;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyBean {

	public int id;
	public String name;

	public MyBean(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
