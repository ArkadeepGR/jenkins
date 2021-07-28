package com.example.AppCompany.User;

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
class UserControllerTest{
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	UserController userController;
	
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void getUserTest() throws Exception {
				mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("admin")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.password",Matchers.is("admin")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is("admin@gmail.com")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.admin",Matchers.is(true)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.role",Matchers.is("ROLE_ADMIN")))
				;
	}

	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void newUserTest() throws Exception {
		String json ="{\"name\":\"Amit\",\"password\":\"123\",\"email\":\"bikashghoshroy@gmail.com\",\"confirmed\":true,\"admin\":true,\"role\":\"ADMIN\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/users/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("Amit")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.password",Matchers.is("123")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is("bikashghoshroy@gmail.com")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.confirmed",Matchers.is(false)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.admin",Matchers.is(false)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.role",Matchers.is("ROLE_USER")))
				;
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void updateUserTest() throws Exception {

		String json ="{\"id\":3,\"name\":\"Amitupdated\",\"password\":\"123\",\"email\":\"amitupdated@gmail.com\",\"confirmed\":true,\"admin\":true,\"role\":\"ROLE_ADMIN\"}";
		mockMvc.perform(MockMvcRequestBuilders.patch("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("Amitupdated")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.password",Matchers.is("123")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is("amitupdated@gmail.com")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.confirmed",Matchers.is(true)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.admin",Matchers.is(true)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.role",Matchers.is("ROLE_ADMIN")))
				;
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void confirmUserTest() throws Exception {
	mockMvc.perform(MockMvcRequestBuilders.get("/confirm-user/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Success"))
				;
	}



}
