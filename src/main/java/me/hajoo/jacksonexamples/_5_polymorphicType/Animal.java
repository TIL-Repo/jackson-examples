package me.hajoo.jacksonexamples._5_polymorphicType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	include = JsonTypeInfo.As.PROPERTY,
	property = "type"
)
@JsonSubTypes({
	@JsonSubTypes.Type(value = Dog.class, name = "dog"),
	@JsonSubTypes.Type(value = Cat.class, name = "cat")
})
public interface Animal {
}
