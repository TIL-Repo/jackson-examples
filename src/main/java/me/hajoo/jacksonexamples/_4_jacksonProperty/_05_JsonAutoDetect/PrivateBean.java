package me.hajoo.jacksonexamples._4_jacksonProperty._05_JsonAutoDetect;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PrivateBean {

	private int id;
	private String name;

	public PrivateBean(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
