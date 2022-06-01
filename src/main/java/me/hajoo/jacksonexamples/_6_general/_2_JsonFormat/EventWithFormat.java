package me.hajoo.jacksonexamples._6_general._2_JsonFormat;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EventWithFormat {

	public String name;

	@JsonFormat(
		shape = JsonFormat.Shape.STRING,
		pattern = "dd-MM-yyyy"
	)
	public Date evenDate;

	public EventWithFormat(String name, Date evenDate) {
		this.name = name;
		this.evenDate = evenDate;
	}
}
