package me.hajoo.jacksonexamples;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import me.hajoo.jacksonexamples._4_jacksonProperty._03_JsonIgnoreType.User;
import me.hajoo.jacksonexamples._6_general._10_Disable.MyBean2;
import me.hajoo.jacksonexamples._6_general._1_JsonProperty.MyBean;
import me.hajoo.jacksonexamples._6_general._2_JsonFormat.EventWithFormat;
import me.hajoo.jacksonexamples._6_general._3_JsonUnwrapped.UnwrappedUser;
import me.hajoo.jacksonexamples._6_general._4_JsonView.Item;
import me.hajoo.jacksonexamples._6_general._4_JsonView.Views;
import me.hajoo.jacksonexamples._6_general._5_JsonManagedReference_JsonBackReference.ItemWithRef;
import me.hajoo.jacksonexamples._6_general._5_JsonManagedReference_JsonBackReference.UserWithRef;
import me.hajoo.jacksonexamples._6_general._6_JsonIdentityInfo.ItemWithIdentity;
import me.hajoo.jacksonexamples._6_general._6_JsonIdentityInfo.UserWithIdentity;
import me.hajoo.jacksonexamples._6_general._7_JsonFilter.BeanWithFilter;
import me.hajoo.jacksonexamples._6_general._8_JacksonAnnotationInside.BeanWithCustomAnnotation;
import me.hajoo.jacksonexamples._6_general._9_MinIn.Item2;
import me.hajoo.jacksonexamples._6_general._9_MinIn.MyMixInForIgnoreType;

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

	@Test
	public void JsonManagedReference_JsonBackReference() throws Exception {
	    // given
		final UserWithRef user = new UserWithRef(1, "John");
		final ItemWithRef item = new ItemWithRef(2, "book", user);
		user.addItem(item);
		// when
		final String result = objectMapper.writeValueAsString(item);
		// then
		assertTrue(result.contains("book"));
		assertTrue(result.contains("2"));
		assertFalse(result.contains("userItems"));
	}

	@Test
	public void JsonIdentityInfo() throws Exception {
	    // given
		final UserWithIdentity user = new UserWithIdentity(1, "John");
		final ItemWithIdentity item = new ItemWithIdentity(2, "book", user);
		user.addItem(item);
		// when
		final String result = objectMapper.writeValueAsString(item);
		// then
		assertTrue(result.contains("book"));
		assertTrue(result.contains("John"));
		assertTrue(result.contains("userItems"));
	}

	@Test
	public void JsonFilter() throws Exception {
	    // given
		final BeanWithFilter bean = new BeanWithFilter(1, "My bean");

		final SimpleFilterProvider filters = new SimpleFilterProvider().addFilter("myFilter",
			SimpleBeanPropertyFilter.filterOutAllExcept("name"));
		// when
		final String result = objectMapper.writer(filters)
			.writeValueAsString(bean);
		// then
		assertTrue(result.contains("My bean"));
		assertFalse(result.contains("id"));
	}
	
	@Test
	public void JacksonAnnotationsInside() throws Exception {
	    // given
		final BeanWithCustomAnnotation bean = new BeanWithCustomAnnotation(1, "My bean", null);
		// when
		final String result = objectMapper.writeValueAsString(bean);
		// then
		assertTrue(result.equals("{\"name\":\"My bean\",\"id\":1}"));
		assertFalse(result.contains("dateCreated"));
	}

	@Test
	public void MinInMethod() throws Exception {
	    // given
		final Item2 item = new Item2(1, "book", null);
		// when, then
		final String result = objectMapper.writeValueAsString(item);
		assertTrue(result.contains("owner"));

		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.addMixIn(User.class, MyMixInForIgnoreType.class);

		final String result2 = objectMapper.writeValueAsString(item);
		assertFalse(result2.contains("owner"));
	}

	@Test
	public void Disable() throws Exception {
	    // given
		final ObjectMapper objectMapper = new ObjectMapper();
		final MyBean2 bean = new MyBean2(1, null);
		// when
		objectMapper.disable(MapperFeature.USE_ANNOTATIONS);
		final String result = objectMapper.writeValueAsString(bean);
		// then
		System.out.println(result);
	}
}
