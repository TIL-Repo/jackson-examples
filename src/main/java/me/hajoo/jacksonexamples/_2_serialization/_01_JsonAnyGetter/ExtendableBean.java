package me.hajoo.jacksonexamples._2_serialization._01_JsonAnyGetter;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

public class ExtendableBean {

	public String name;
	private Map<String, String> properties;

	@JsonAnyGetter
	public Map<String, String> getProperties() {
		return properties;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
}