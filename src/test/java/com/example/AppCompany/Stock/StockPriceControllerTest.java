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
class StockPriceControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void allStockPriceTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/stockprice"))
				.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(10)));
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void newStockpriceTest() throws Exception {
		String json="{\"exchangename\":\"BSE\",\"companycode\":\"001\",\"date\":\"2014-01-01\",\"time\":\"10:23:00\",\"shareprice\":459.67}";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/stockprice")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.exchangename",Matchers.is("BSE")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.companycode",Matchers.is("001")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.companyname",Matchers.is("Axis")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.date",Matchers.is("2014-01-01")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.shareprice",Matchers.is(459.67)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.time",Matchers.is("10:23:00")));	
	}
	
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void stockpriceByCompanyNameTest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/stockprice/company/GE"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].id",containsInAnyOrder(9,10)));
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void stockpriceByCompanyNamePeriodDiffDateTest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/stockprice/company/Axis/2021-03-20/12:00:00/2021-03-30/12:00:00"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(4)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].id",containsInAnyOrder(3,4,5,6)));
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void stockpriceByCompanyNamePeriodSameDateTest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/stockprice/company/Axis/2021-03-21/12:00:00/2021-03-21/14:00:00"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].id",containsInAnyOrder(4)));
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void stockpriceByCompanyNamePeriodSameDateWrongTimeTest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/stockprice/company/Axis/2021-03-21/12:00:00/2021-03-21/10:00:00"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(0)));
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void stockpriceByCompanyNamePeriodSWrongDateTest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/stockprice/company/Axis/2021-03-21/12:00:00/2021-03-19/14:00:00"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(0)));
	}
	
	/////////////	
	
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void stockpriceByCompanyCodeTest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/stockprice/companycode/003"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].id",containsInAnyOrder(9,10)));
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void stockpriceByCompanyCodePeriodTest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/stockprice/companycode/001/2021-03-20/12:00:00/2021-03-30/12:00:00"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(4)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].id",containsInAnyOrder(3,4,5,6)));
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void stockpriceByCompanyCodePeriodSameDateTest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/stockprice/companycode/001/2021-03-21/12:00:00/2021-03-21/14:00:00"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].id",containsInAnyOrder(4)));
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void stockpriceByCompanyCodePeriodSameDateWrongTimeTest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/stockprice/companycode/001/2021-03-21/12:00:00/2021-03-21/10:00:00"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(0)));
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void stockpriceByCompanyCodePeriodSWrongDateTest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/stockprice/companycode/001/2021-03-21/12:00:00/2021-03-19/14:00:00"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(0)));

	}
}
