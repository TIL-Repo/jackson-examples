package me.hajoo.jacksonexamples._3_deserialization._01_JsonCreator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BeanWithCreator {

	public int id;
	public String name;

	@JsonCreator
	public BeanWithCreator(
		@JsonProperty("id") int id,
		@JsonProperty("theName") String name) {
		this.id = id;
		this.name = name;
	}
}
