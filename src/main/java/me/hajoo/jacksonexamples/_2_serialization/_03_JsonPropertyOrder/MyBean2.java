package me.hajoo.jacksonexamples._2_serialization._03_JsonPropertyOrder;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "id" })
public class MyBean2 {

	public int id;
	public String name;

	public MyBean2(int id, String name) {
		this.id = id;
		this.name = name;
	}
}