package me.hajoo.jacksonexamples._3_deserialization._05_JsonDeserialize;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class EventWithSerializer {

	public String name;

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public Date eventDate;
}
