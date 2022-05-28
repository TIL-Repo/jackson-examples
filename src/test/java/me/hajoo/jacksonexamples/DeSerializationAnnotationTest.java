package me.hajoo.jacksonexamples;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;

import me.hajoo.jacksonexamples._3_deserialization._01_JsonCreator.BeanWithCreator;
import me.hajoo.jacksonexamples._3_deserialization._02_JacksonInject.BeanWithInject;
import me.hajoo.jacksonexamples._3_deserialization._03_JsonAnySetter.ExtendableBean;
import me.hajoo.jacksonexamples._3_deserialization._04_JsonSetter.MyBean;
import me.hajoo.jacksonexamples._3_deserialization._05_JsonDeserialize.EventWithSerializer;
import me.hajoo.jacksonexamples._3_deserialization._06_JsonAlias.AliasBean;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeSerializationAnnotationTest {

	@Test
	@DisplayName("@JsonCreator")
	@Order(0)
	public void jsonCreator() throws Exception {
	    //given
		String json = "{\"id\":1,\"theName\":\"My bean\"}";
		//when
		BeanWithCreator bean = new ObjectMapper()
			.readerFor(BeanWithCreator.class)
			.readValue(json);
		//then
		assertEquals("My bean",bean.name);
	}

	@Test
	@DisplayName("@JacksonInject")
	@Order(1)
	public void jacksonInject() throws Exception {
	    //given
		String json = "{\"name\":\"My bean\"}";
	    //when
		InjectableValues inject = new InjectableValues.Std()
			.addValue(int.class, 1)
			.addValue(long.class, 3);
		BeanWithInject bean = new ObjectMapper().reader(inject)
			.forType(BeanWithInject.class)
			.readValue(json);
	    //then
		assertEquals("My bean", bean.name);
		assertEquals(1, bean.id);
		assertEquals(3, bean.id2);
	}

	@Test
	@DisplayName("@JsonAnySetter")
	@Order(2)
	public void jsonAnySetter() throws Exception {
	    //given
		String json
			= "{\"name\":\"My bean\",\"attr2\":\"val2\",\"attr1\":\"val1\"}";
		//when
		ExtendableBean bean = new ObjectMapper()
			.readerFor(ExtendableBean.class)
			.readValue(json);
	    //then
		assertEquals("My bean", bean.name);
		assertEquals("val1", bean.getProperties().get("attr1"));
		assertEquals("val2", bean.getProperties().get("attr2"));
	}

	@Test
	@DisplayName("@JsonSetter")
	@Order(3)
	public void jsonSetter() throws Exception {
	    //given
		String json = "{\"id\":1,\"name\":\"My bean\"}";
	    //when
		MyBean bean = new ObjectMapper()
			.readerFor(MyBean.class)
			.readValue(json);
	    //then
		assertEquals("My bean", bean.getTheName());
	}

	@Test
	@DisplayName("@JsonDeserialize")
	@Order(4)
	public void jsonDeserialize() throws Exception {
	    //given
		String json = "{\"name\":\"party\",\"eventDate\":\"28-05-2022 08:50:00\"}";
	    //when
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		EventWithSerializer event = new ObjectMapper()
			.readerFor(EventWithSerializer.class)
			.readValue(json);
	    //then
		assertEquals("28-05-2022 08:50:00", df.format(event.eventDate));
	}

	@Test
	@DisplayName("@JsonAlias")
	@Order(5)
	public void jsonAlias() throws Exception {
	    //given
		String json = "{\"fName\": \"John\", \"lastName\": \"Green\"}";
	    //when
		AliasBean aliasBean = new ObjectMapper().readerFor(AliasBean.class).readValue(json);
		//then
		assertEquals("John", aliasBean.getFirstName());

		//given
		String json2 = "{\"f_name\": \"John\", \"lastName\": \"Green\"}";
		//when
		AliasBean aliasBean2 = new ObjectMapper().readerFor(AliasBean.class).readValue(json2);
		//then
		assertEquals("John", aliasBean2.getFirstName());
	}
}