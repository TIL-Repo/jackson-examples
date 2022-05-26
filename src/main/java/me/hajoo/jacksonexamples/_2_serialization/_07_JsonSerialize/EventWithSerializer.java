package me.hajoo.jacksonexamples._2_serialization._07_JsonSerialize;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class EventWithSerializer {

	public String name;

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date eventDate;

	public EventWithSerializer(String name, Date eventDate) {
		this.name = name;
		this.eventDate = eventDate;
	}
}
