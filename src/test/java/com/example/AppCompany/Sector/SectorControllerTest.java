package com.example.AppCompany.Sector;

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
import static org.hamcrest.Matchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class SectorControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void newSectorTest() throws Exception {
		String json="{\"sectorName\":\"Space\",\"brief\":\"Space Travel Sector\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/sectors")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(4)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.sectorName",Matchers.is("Space")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.brief",Matchers.is("Space Travel Sector")))
				;
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void updateSectorTest() throws Exception {
		String json="{\"id\":1,\"sectorName\":\"Business Updated\",\"brief\":\"Updated Brief\"}";
		mockMvc.perform(MockMvcRequestBuilders.patch("/sectors")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.sectorName",Matchers.is("Business Updated")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.brief",Matchers.is("Updated Brief")))
				;
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void sectorByIdTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/sectors/2"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.sectorName",Matchers.is("Construction")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.brief",Matchers.is("Brief about construction sector.")))
				;
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void allSectorTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/sectors"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				  .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(3)));
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void allCompaniesInSectorTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/sectors/companies/2"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].name",containsInAnyOrder("LandT","GE")));
	}
	
	
	

}
