package me.hajoo.jacksonexamples;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.hajoo.jacksonexamples._6_general._1_JsonProperty.MyBean;
import me.hajoo.jacksonexamples._6_general._2_JsonFormat.EventWithFormat;
import me.hajoo.jacksonexamples._6_general._3_JsonUnwrapped.UnwrappedUser;
import me.hajoo.jacksonexamples._6_general._4_JsonView.Item;
import me.hajoo.jacksonexamples._6_general._4_JsonView.Views;

public class GeneralTest {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void jsonProperty() throws Exception {
	    //given
		final MyBean bean = new MyBean(1, "My bean");
		//when, then
		final String result = objectMapper.writeValueAsString(bean);

		assertTrue(result.contains("name"));
		assertTrue(result.contains("1"));

		final MyBean resultBean = objectMapper.readerFor(MyBean.class)
			.readValue(result);

		assertTrue(resultBean.getTheName().equals("My bean"));
	}

	@Test
	public void JsonFormat() throws Exception {
	    //given
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = df.parse("01-06-2022 11:00:00");
		EventWithFormat event = new EventWithFormat("party", date);
	    //when
		final String result = objectMapper.writeValueAsString(event);
		//then
		assertTrue(result.contains("01-06-2022"));
	}

	@Test
	public void JsonUnwrapped() throws Exception {
	    //given
		final UnwrappedUser.Name name = new UnwrappedUser.Name("John", "Doe");
		final UnwrappedUser unwrappedUser = new UnwrappedUser(1, name);
		//when
		final String result = objectMapper.writeValueAsString(unwrappedUser);
		//then
		assertTrue(result.equals("{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\"}"));
	}

	@Test
	public void JsonView() throws Exception {
	    //given
		final Item item = new Item(2, "book", "John");
		//when
		final String result = objectMapper.writerWithView(Views.Public.class)
			.writeValueAsString(item);
		final String result2 = objectMapper.writerWithView(Views.Internal.class)
			.writeValueAsString(item);
		final String result3 = objectMapper.writerWithView(Views.ExtendPublic.class)
			.writeValueAsString(item);
		//then
		assertTrue(result.equals("{\"id\":2,\"itemName\":\"book\"}"));
		assertTrue(result2.equals("{\"itemName\":\"book\",\"ownerName\":\"John\"}"));
		assertTrue(result3.equals("{\"id\":2,\"itemName\":\"book\",\"ownerName\":\"John\"}"));
	}
}
