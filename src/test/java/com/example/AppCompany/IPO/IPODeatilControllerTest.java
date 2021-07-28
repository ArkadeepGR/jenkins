package com.example.AppCompany.IPO;

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


@AutoConfigureMockMvc
@SpringBootTest
class IPODeatilControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void allIPOTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/ipo"))
				.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(3)));
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void newIPOTest() throws Exception {
		
		String json="{\"ipo\":{\"pricePerShare\":124,\"totalNumberOfShares\":212,\"openDateTime\":\"2021-07-16T11:08:04.017494\",\"remark\":\"Bad\"},\"companyname\":\"GM\"}";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/ipo")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(4)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.pricePerShare",Matchers.is(124.0)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.totalNumberOfShares",Matchers.is(212)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.openDateTime",Matchers.is("2021-07-16T11:08:04.017494")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.remark",Matchers.is("Bad")));	
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void updateIPOTest() throws Exception {
		
		String json="{\"ipo\":{\"id\":2,\"pricePerShare\":122,\"totalNumberOfShares\":230,\"openDateTime\":\"2021-07-16T11:08:04.017494\",\"remark\":\"GOOD Updated\"},\"companyname\":\"LandT\"}";
		mockMvc.perform(MockMvcRequestBuilders.patch("/ipo")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(4)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.pricePerShare",Matchers.is(122.0)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.totalNumberOfShares",Matchers.is(230)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.openDateTime",Matchers.is("2021-07-16T11:08:04.017494")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.remark",Matchers.is("GOOD Updated")));	
	}

}
