package me.hajoo.jacksonexamples._6_general._10_Disable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "id"})
public class MyBean2 {

	public int id;
	public String name;

	public MyBean2(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
