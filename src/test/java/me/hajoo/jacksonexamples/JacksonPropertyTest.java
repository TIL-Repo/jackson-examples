package me.hajoo.jacksonexamples;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.hajoo.jacksonexamples._4_jacksonProperty._01_JsonIgnoreProperties.BeanWithIgnoreProperties;
import me.hajoo.jacksonexamples._4_jacksonProperty._02_JsonIgnore.BeanWithIgnore;
import me.hajoo.jacksonexamples._4_jacksonProperty._03_JsonIgnoreType.User;
import me.hajoo.jacksonexamples._4_jacksonProperty._04_JsonInclude.MyBean;
import me.hajoo.jacksonexamples._4_jacksonProperty._05_JsonAutoDetect.PrivateBean;

public class JacksonPropertyTest {

	@Test
	public void jsonIgnoreProperties() throws Exception {
	    //given
		final BeanWithIgnoreProperties bean = new BeanWithIgnoreProperties(1, "My bean");
		//when
		final String result = new ObjectMapper().writeValueAsString(bean);
		//then
		assertTrue(result.contains("My bean"));
		assertFalse(result.contains("id"));
	}

	@Test
	public void jsonIgnore() throws Exception {
	    //given
		final BeanWithIgnore bean = new BeanWithIgnore(1, "My Bean");
		//when
		final String result = new ObjectMapper().writeValueAsString(bean);
		//then
		assertTrue(result.contains("My Bean"));
		assertFalse(result.contains("id"));
	}

	@Test
	public void jsonIgnoreType() throws Exception {
	    //given
		final User.Name name = new User.Name("John", "Doe");
		final User user = new User(1, name);
		//when
		final String result = new ObjectMapper().writeValueAsString(user);
		//then
		assertTrue(result.contains("1"));
		assertFalse(result.contains("name"));
		assertFalse(result.contains("John"));
	}

	@Test
	public void jsonInclude() throws Exception {
	    //given
		final MyBean bean = new MyBean(1, null);
		//when
		final String result = new ObjectMapper().writeValueAsString(bean);
		//then
		assertTrue(result.contains("1"));
		assertFalse(result.contains("name"));
	}

	@Test
	public void jsonAutoDetect() throws Exception {
	    //given
		final PrivateBean bean = new PrivateBean(1, "My bean");
		//when
		final String result = new ObjectMapper().writeValueAsString(bean);
		//then
		assertTrue(result.contains("1"));
		assertTrue(result.contains("My bean"));
	}
}
