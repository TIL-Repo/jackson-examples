package me.hajoo.jacksonexamples;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.hajoo.jacksonexamples._5_polymorphicType.Cat;
import me.hajoo.jacksonexamples._5_polymorphicType.Dog;
import me.hajoo.jacksonexamples._5_polymorphicType.Zoo;

public class PolymorphicTypeTest {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void serializationDog() throws Exception {
	    //given
		final Dog dog = new Dog();
		final Zoo zoo = new Zoo(dog);
		//when
		final String result = objectMapper.writeValueAsString(zoo);
		//then
		assertTrue(result.contains("type"));
		assertTrue(result.contains("dog"));
	}

	@Test
	public void serializationCat() throws Exception {
		//given
		final Cat cat = new Cat();
		final Zoo zoo = new Zoo(cat);
		//when
		final String result = objectMapper.writeValueAsString(zoo);
		//then
		assertTrue(result.contains("type"));
		assertTrue(result.contains("cat"));	}

	@Test
	public void deserializationCat() throws Exception {
	    //given
		String json = "{\"animal\":{\"type\":\"cat\"}}";
	    //when
		final Zoo result = objectMapper.readerFor(Zoo.class)
			.readValue(json);
		//then
		assertTrue(result.getAnimal().getClass().equals(Cat.class));
	}
}
