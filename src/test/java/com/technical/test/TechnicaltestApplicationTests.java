package com.technical.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.technical.test.DTO.User;
import com.technical.test.controller.rest.restController;

@WebMvcTest(restController.class)
@ContextConfiguration(classes = technicaltestApplication.class)
//@SpringBootTest
class TechnicaltestApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void whenGetUsers_thenReturnUsersObjectUsingJackson() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/init/v1/getUsers")).andExpect(status().isOk()).andReturn();
		String json = result.getResponse().getContentAsString();
		CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, User.class);
		List<User> users = objectMapper.readValue(json, typeReference);
		assertNotNull(users);
		assertEquals(users.size(), 7);
	}

	@Test
	void whenGetUsers_thenReturnUsersObjectUsingJacksonOrdered() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/init/v1/getUsers")).andExpect(status().isOk()).andReturn();
		String json = result.getResponse().getContentAsString();
		CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, User.class);
		List<User> users = objectMapper.readValue(json, typeReference);
		assert (users.get(0).getAge() == 20);
		assert (users.get(1).getAge() == 25);
		assert (users.get(2).getAge() == 48);
		assert (users.get(3).getAge() == 55);
		assert (users.get(4).getAge() == 55);
		assert (users.get(5).getAge() == 65);
		assert (users.get(6).getAge() == 65);
	}

	@Test
	void whenGetUser_thenReturnUserObject() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/init/v1/getUser?idValue=1")).andExpect(status().isOk())
				.andReturn();
		String json = result.getResponse().getContentAsString();
		CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, User.class);
		List<User> users = objectMapper.readValue(json, typeReference);
		assert (users.get(0).getAge() == 25);
	}

	@Test
	void whenPutCiudad_thenReturnok() throws Exception {
		MvcResult result = this.mockMvc.perform(put("/init/v1/putCiudad/1/Jaen")).andExpect(status().isOk())
				.andReturn();
		String json = result.getResponse().getContentAsString();
		assert ("[{ok: se ha modificado la ciudad }]".equals(json));
	}
}
