package com.olxlogin.Controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olxlogin.controller.AdvertiseController;
import com.olxlogin.dto.AdvertiseDTO;
import com.olxlogin.service.AdvertiseService;

@WebMvcTest(AdvertiseController.class)
class AdvertiseControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	AdvertiseController advertiseController;
	
	@MockBean
	AdvertiseService advertiseService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	public void testCreateNewAdvertise() throws Exception{
		AdvertiseDTO advertiseDTO =new AdvertiseDTO();
		advertiseDTO.setTitle("Sofa Sale");
		HttpHeaders httpHeaders =new HttpHeaders();
		httpHeaders.set("Authorization","K87DER");
		when(this.advertiseService.createNewAdvertise(advertiseDTO, "K87DER")).thenReturn(advertiseDTO);
		
		MvcResult mvcResult = this.mockMvc.perform(post("http://localhost:9003/olx/advertise/")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(advertiseDTO))
				.headers(httpHeaders)
				)
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("sofa")))
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("sofa"),true);
	}

	@Test
	void testSearchAdvertisesByFilterCriteria() throws Exception{
		List<AdvertiseDTO> advertiseDtoList = new ArrayList<AdvertiseDTO>();
		advertiseDtoList.add(new AdvertiseDTO());
		advertiseDtoList.add(new AdvertiseDTO());
		when(this.advertiseService.searchAdvertisesByFilterCriteria(null, 0, null, null, null, null, null, null, 0, 0)).
			thenReturn(advertiseDtoList);
		
		MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:9003/olx/advertise/search/filtercriteria")
				.param("category","0").param("startIndex","0"))
				.andExpect(status().isOk())
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("title"),true);
	}

}
