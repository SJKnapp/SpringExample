package com.example.demo.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.data.Duck;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:Duck-schema.sql",
		"classpath:Duck-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

public class DuckControllerIntergrationTest {

	@Autowired // tells Spring to inject the MockMVC object into this class
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper; // yanks the class Spring uses to convert java to JSON

	@Test
	void postMethordTest() throws Exception {
		Duck testKit = new Duck("ducked", 10);

		String testKitAsJSON = this.mapper.writeValueAsString(testKit);

		RequestBuilder request = post("/duck/").contentType(MediaType.APPLICATION_JSON).content(testKitAsJSON);

		ResultMatcher checkStatus = status().is(201);

		Duck testCreatedKit = new Duck("ducked", 10);
		testCreatedKit.setId(3);
		String testCreatedKitAsJSON = this.mapper.writeValueAsString(testCreatedKit);

		ResultMatcher checkBody = content().json(testCreatedKitAsJSON);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void getMethordTest() throws Exception {
		int id = 1;
		Duck testKit = new Duck("ducks", 2);
		testKit.setId(id);

		String testCreatedKitAsJSON = this.mapper.writeValueAsString(testKit);
		ResultMatcher checkBody = content().json(testCreatedKitAsJSON);

		this.mockMVC.perform(get("/duck/" + id)).andExpect(status().isOk()).andExpect(checkBody);
	}

	@Test
	void getMethordByName() throws Exception {
		String name = "ducks";
		List<Duck> expected = new ArrayList<>();
		Duck testKit = new Duck(name, 2);
		expected.add(testKit);
		testKit.setId(1);
		String testCreatedKitAsJSON = this.mapper.writeValueAsString(expected);
		ResultMatcher checkBody = content().json(testCreatedKitAsJSON);

		this.mockMVC.perform(get("/duck/findByName/?name=" + name)).andExpect(status().isOk()).andExpect(checkBody);
	}

	@Test
	void deleteMethord() throws Exception {
		int id = 2;
		this.mockMVC.perform(delete("/duck/" + id + "/delete")).andExpect(status().isNoContent())
				.andExpect(content().string("true"));
	}

	@Test
	void putMothordTest() throws Exception {
		int id = 1;
		Duck testKit = new Duck("ducked", 10);

		String testKitAsJSON = this.mapper.writeValueAsString(testKit);

		RequestBuilder request = put("/duck/" + id + "/put").contentType(MediaType.APPLICATION_JSON)
				.content(testKitAsJSON);

		Duck testCreatedKit = new Duck("ducked", 10);
		testCreatedKit.setId(id);
		String testCreatedKitAsJSON = this.mapper.writeValueAsString(testCreatedKit);

		ResultMatcher checkBody = content().json(testCreatedKitAsJSON);

		this.mockMVC.perform(request).andExpect(status().isOk()).andExpect(content().string("true"));
	}

	@Test
	void putNegativeMothordTest() throws Exception {
		int id = 4;
		Duck testKit = new Duck("ducked", 10);

		String testKitAsJSON = this.mapper.writeValueAsString(testKit);

		RequestBuilder request = put("/duck/" + id + "/put").contentType(MediaType.APPLICATION_JSON)
				.content(testKitAsJSON);

		Duck testCreatedKit = new Duck("ducked", 10);
		testCreatedKit.setId(id);
		String testCreatedKitAsJSON = this.mapper.writeValueAsString(testCreatedKit);

		ResultMatcher checkBody = content().json(testCreatedKitAsJSON);

		this.mockMVC.perform(request).andExpect(status().isNotFound()).andExpect(content().string("false"));
	}
}