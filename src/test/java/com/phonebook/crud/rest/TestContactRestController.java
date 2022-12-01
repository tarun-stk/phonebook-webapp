package com.phonebook.crud.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonebook.crud.entity.Contact;
import com.phonebook.crud.service.ContactService;

@WebMvcTest(value=ContactRestController.class)
public class TestContactRestController {
	
	@MockBean
	private ContactService contactService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testSaveContact() throws Exception {
		
		// defining behaviour for the mock object.
		Mockito.when(contactService.createContact(Mockito.any())).thenReturn(true);
		
		Contact c = new Contact(101, "Tarun", "89898989", "tarun@gmail.com");
		
		String json = null;
		
		// converting java obj to josn. using mapper
		ObjectMapper mapper = new ObjectMapper();
		json = mapper.writeValueAsString(c);
		
		// creating the post request.
		MockHttpServletRequestBuilder reqBuilder = 
				MockMvcRequestBuilders.post("/api/contacts")
									.contentType("application/json")
									.content(json);
		
		// performing the rest call.
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		
		// getting the response from performed api call
		MockHttpServletResponse response = mvcResult.getResponse();
		
		// getting the status code from response
		int status = response.getStatus();
		
		// making the assertEquals
		assertEquals(201, status);
		
	}
	

}








