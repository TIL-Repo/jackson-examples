package me.hajoo.jacksonexamples._5_polymorphicType;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("dog")
public class Dog implements Animal {

	public double barkVolume;

	public Dog() {}

	public Dog(double barkVolume) {
		this.barkVolume = barkVolume;
	}
}