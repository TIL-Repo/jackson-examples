package me.hajoo.jacksonexamples._5_polymorphicType;

public class Zoo {

	public Animal animal;

	public Zoo() {
	}

	public Zoo(Animal animal) {
		this.animal = animal;
	}

	public Animal getAnimal() {
		return animal;
	}
}
