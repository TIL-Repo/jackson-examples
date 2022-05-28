package me.hajoo.jacksonexamples._3_deserialization._03_JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class ExtendableBean {

	public String name;
	private Map<String, String> properties = new HashMap<>();

	@JsonAnySetter
	public void add(String key, String value) {
		properties.put(key, value);
	}

	public Map<String, String> getProperties() {
		return properties;
	}
}
