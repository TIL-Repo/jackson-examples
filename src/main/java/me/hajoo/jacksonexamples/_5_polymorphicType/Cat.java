package me.hajoo.jacksonexamples._5_polymorphicType;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("cat")
public class Cat implements Animal {

	boolean likesCream;
	public int lives;

	public Cat() {}

	public Cat(boolean likesCream, int lives) {
		this.likesCream = likesCream;
		this.lives = lives;
	}
}
