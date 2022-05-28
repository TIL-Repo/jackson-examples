package me.hajoo.jacksonexamples._3_deserialization._02_JacksonInject;

import com.fasterxml.jackson.annotation.JacksonInject;

public class BeanWithInject {

	@JacksonInject
	public int id;

	@JacksonInject
	public long id2;

	public String name;
}
