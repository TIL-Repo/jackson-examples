package me.hajoo.jacksonexamples._6_general._8_JacksonAnnotationInside;

import java.util.Date;

@CustomAnnotation
public class BeanWithCustomAnnotation {

	public int id;
	public String name;
	public Date dateCreated;

	public BeanWithCustomAnnotation(int id, String name, Date dateCreated) {
		this.id = id;
		this.name = name;
		this.dateCreated = dateCreated;
	}
}
