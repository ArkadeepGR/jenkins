package com.example.AppCompany.Stock;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class StockexchangeControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void allStockExchangesTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/stockexchanges"))
				.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(4)));
	}
	
		
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void stockExchangesByIdTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/stockexchanges/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("BSE")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.brief",Matchers.is("An Indian Stock Excahnge")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.address",Matchers.is("BOMBAY")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.remark",Matchers.is("Good")));	
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void stockExchangesByNameTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/stockexchanges/name/BSE"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("BSE")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.brief",Matchers.is("An Indian Stock Excahnge")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.address",Matchers.is("BOMBAY")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.remark",Matchers.is("Good")));	
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void newStockexchangeTest() throws Exception {
		
		String json="{\"name\":\"RSE\",\"brief\":\"Russian Stock Exchange\",\"address\":\"Russia\",\"remark\":\"Good\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/stockexchanges")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("RSE")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.brief",Matchers.is("Russian Stock Exchange")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.address",Matchers.is("Russia")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.remark",Matchers.is("Good")));	
	}
	
	

	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void updateStockexchangeTest() throws Exception {
		
		String json="{\"id\":3,\"name\":\"DBE\",\"brief\":\"Dubai Stock Exchange Updated\",\"address\":\"Dubai\",\"remark\":\"Poor\"}";
		mockMvc.perform(MockMvcRequestBuilders.patch("/stockexchanges")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("DBE")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.brief",Matchers.is("Dubai Stock Exchange Updated")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.address",Matchers.is("Dubai")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.remark",Matchers.is("Poor")));	
	}
	
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void stockexchangeCompaniesById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/stockexchanges/companies/1"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(2)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[*].name",containsInAnyOrder("Axis","LandT")));
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void stockexchangeCompaniesByName() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/stockexchanges/companies/name/BSE"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(2)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[*].name",containsInAnyOrder("Axis","LandT")));
	}

}
