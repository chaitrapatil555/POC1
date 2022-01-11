package com.neosoft;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.*;


import com.neosoft.Controller.UserController;
import com.neosoft.Poc.Service.UserService;
import com.neosoft.Repository.UserRepository;
import com.neosoft.model.User;

@WebMvcTest(UserController.class)
class SpringPoc1Task1ApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;
	// ObjectMapper provides functionality for reading and writing JSON,
	// either to and from basic POJOs

	@MockBean
	UserService userService;

	@MockBean
	UserRepository usersRepository;

	User RECORD_1 = new User(10L, "Abc", "Def",  new Date(1999 - 8 - 11), new Date(2021 - 12 - 10),
			   "9876543210","577126",false);
	User RECORD_2 = new User(20L, "Ghi", "Jkl",  new Date(1999 - 10 - 21), new Date(2021 - 11 - 20),
			   "9876543211","577127",false);
	User RECORD_3 = new User(30L, "Mno", "Pqr",  new Date(1999 - 12 - 31), new Date(2021 - 10 - 30),
			   "9876543212","577128",false);

	@Test
	void contextLoads() {
	}

	@Test
	public void getAllRecords_success() throws Exception {
		List<User> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

		Mockito.when(userService.GetUser()).thenReturn(records);
		// When findAll called then ready with records (No DB calls)
		mockMvc.perform(MockMvcRequestBuilders.get("/User").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) // 200
				.andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[2].fname", is("Mno")));
	}
	
	
	
	@Test
	public void deleteUsersById_success() throws Exception {

		Mockito.when(userService.deleteUser(RECORD_2.getId())).thenReturn(Optional.of(RECORD_2));

		mockMvc.perform(MockMvcRequestBuilders.delete("/User/delete/2").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	@Test
	public void getUsersByFirstName_success() throws Exception {
		List<User> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
		Mockito.when(usersRepository.findByFname(RECORD_1.getFname())).thenReturn(records);

		mockMvc.perform(MockMvcRequestBuilders.get("/User/search/fname/Abc").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$[0].fname", is("Abc")));
	}

	@Test
	public void getUsersBySurname_success() throws Exception {
		List<User> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
		Mockito.when(usersRepository.findBySurname(RECORD_1.getSurname())).thenReturn(records);

		mockMvc.perform(MockMvcRequestBuilders.get("/User/search/surname/Def").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$[0].surname", is("Def")));
	}
	
	
}