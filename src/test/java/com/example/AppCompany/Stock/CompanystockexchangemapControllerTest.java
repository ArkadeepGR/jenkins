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
class CompanystockexchangemapControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void mapCompanyStockexchangeTest() throws Exception {
		String json="{\"companyname\":\"GE\",\"stockexchangename\":\"BSE\",\"companyCode\":\"1232\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/map")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(4)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].name",containsInAnyOrder("GE","BSE")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.companyCode",Matchers.is("1232")));
				;
	}
	

}
