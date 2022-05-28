package me.hajoo.jacksonexamples._3_deserialization._06_JsonAlias;

import com.fasterxml.jackson.annotation.JsonAlias;

public class AliasBean {

	@JsonAlias({ "fName", "f_name" })
	private String firstName;
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
