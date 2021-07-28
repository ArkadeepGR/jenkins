package com.example.AppCompany.Company;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@SpringBootTest
@AutoConfigureMockMvc
class CompanyControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void allCompaniesTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
				.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(4)));
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void companyByNameTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/companies/Axis"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(1)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("Axis")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.turnover",Matchers.is(1200.0)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.ceo",Matchers.is("CEO Name")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.boardOfDirectors",Matchers.is("Axis Directors")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.companyBrief",Matchers.is("Axis brief")));	
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void newCompanyTest() throws Exception {
		
		String json="{\"company\":{\"name\":\"HSBC\",\"turnover\":3000,\"ceo\":\"HSBC CEO\",\"boardOfDirectors\":\"Directors of HSBC\",\"companyBrief\":\"Brief\"},\"sectorId\":1}";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/companies")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(5)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("HSBC")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.turnover",Matchers.is(3000.0)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.ceo",Matchers.is("HSBC CEO")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.boardOfDirectors",Matchers.is("Directors of HSBC")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.companyBrief",Matchers.is("Brief")));	
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void UpdateCompanyTest() throws Exception {
		
		String json="{\"company\":{\"id\":4,\"name\":\"GM Banking\",\"turnover\":1222,\"ceo\":\"GM CEO\",\"boardOfDirectors\":\"Directors of GM\",\"companyBrief\":\"Brief\"},\"sectorId\":1}";
		mockMvc.perform(MockMvcRequestBuilders.patch("/companies")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(4)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("GM Banking")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.turnover",Matchers.is(1222.0)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.ceo",Matchers.is("GM CEO")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.boardOfDirectors",Matchers.is("Directors of GM")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.companyBrief",Matchers.is("Brief")));	
	}
	
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void getCompanyByNameCompanyTest() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/companies/LandT"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(2)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("LandT")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.turnover",Matchers.is(1300.0)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.ceo",Matchers.is("CEO Name")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.boardOfDirectors",Matchers.is("LandT Directors")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.companyBrief",Matchers.is("LandT brief")));	
		
	}
	

	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void companySectorByNameTest() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/companies/sector/LandT"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.sectorName",Matchers.is("Construction")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.brief",Matchers.is("Brief about construction sector.")));
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void companyIPOByNameTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/companies/ipo/Axis"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.pricePerShare",Matchers.is(121.0)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.totalNumberOfShares",Matchers.is(232)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.remark",Matchers.is("Good")));	
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void companySEByNameTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/companies/stockexchanges/Axis"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(3)))
			.andExpect(MockMvcResultMatchers.jsonPath("$[*].name",containsInAnyOrder("BSE","NYSE","DBE")));
	}
	
	
	
}
