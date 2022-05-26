package me.hajoo.jacksonexamples;

import static org.assertj.core.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import me.hajoo.jacksonexamples._2_serialization._01_JsonAnyGetter.ExtendableBean;
import me.hajoo.jacksonexamples._2_serialization._02_JsonGetter.MyBean;
import me.hajoo.jacksonexamples._2_serialization._03_JsonPropertyOrder.MyBean2;
import me.hajoo.jacksonexamples._2_serialization._04_JsonRawValue.RawBean;
import me.hajoo.jacksonexamples._2_serialization._05_JsonValue.TypeEnumWithValue;
import me.hajoo.jacksonexamples._2_serialization._06_JsonRootName.UserWithRoot;
import me.hajoo.jacksonexamples._2_serialization._07_JsonSerialize.EventWithSerializer;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SerializationAnnotationTest {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	@DisplayName("@JsonAnyGetter")
	@Order(0)
	public void jsonAnyGetter() throws Exception {
	    //given
		final ExtendableBean extendableBean = new ExtendableBean();
		extendableBean.setName("홍길동");
		extendableBean.setProperties(Map.of("key", "value"));
		//when
		final String result = objectMapper.writeValueAsString(extendableBean);
		//then
		assertThat(result).isEqualTo("{\"name\":\"홍길동\",\"key\":\"value\"}");
	}

	@Test
	@DisplayName("@JsonGetter")
	@Order(1)
	public void jsonGetter() throws Exception {
		//given
		MyBean bean = new MyBean(1, "My bean");
		//when
		String result = objectMapper.writeValueAsString(bean);
		//then
		assertThat(result).isEqualTo("{\"id\":1,\"name\":\"My bean\"}");
	}

	@Test
	@DisplayName("@JsonPropertyOrder")
	@Order(2)
	public void jsonPropertyOrder() throws Exception {
	    //given
		MyBean2 bean2 = new MyBean2(1, "My bean2");
		//when
		final String result = objectMapper.writeValueAsString(bean2);
		//then
		assertThat(result).isEqualTo("{\"name\":\"My bean2\",\"id\":1}");
	}

	@Test
	@DisplayName("@JsonRawValue")
	@Order(3)
	public void jsonRawValue() throws Exception {
		//given
		RawBean bean = new RawBean("My bean", "{\"attr\":false}");
		//when
		String result = new ObjectMapper().writeValueAsString(bean);
		//then
		assertThat(result).isEqualTo("{\"name\":\"My bean\",\"json\":{\"attr\":false}}");
	}

	@Test
	@DisplayName("@JsonValue")
	@Order(4)
	public void jsonValue() throws Exception {
	    //when
		String result = new ObjectMapper().writeValueAsString(TypeEnumWithValue.TYPE1);
		//then
		assertThat(result).isEqualTo("\"Type A\"");
	}
	
	@Test
	@DisplayName("@JsonRootName")
	@Order(5)
	public void jsonRootName() throws Exception {
	    //given
		UserWithRoot user = new UserWithRoot(1, "John");
		objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		//when
		String result = objectMapper.writeValueAsString(user);
		System.out.println(result);
	    //then
		assertThat(result).isEqualTo("{\"user\":{\"id\":1,\"name\":\"John\"}}");
	}

	@Test
	@DisplayName("@JsonSerialize")
	@Order(6)
	public void jsonSerialize() throws Exception {
	    //given
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		EventWithSerializer event = new EventWithSerializer("party", df.parse("27-05-2022 01:50:00"));
	    //when
		String result = new ObjectMapper().writeValueAsString(event);
		//then
		assertThat(result).isEqualTo("{\"name\":\"party\",\"eventDate\":\"27-05-2022 01:50:00\"}");
	}
}