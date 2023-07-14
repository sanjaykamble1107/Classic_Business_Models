package com.sprint.classicmodelsbussiness.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.classicmodelsbussiness.ClassicModelsBussinessApp;
import com.sprint.classicmodelsbussiness.dto.CustomersDto;
import com.sprint.classicmodelsbussiness.dto.OfficeDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.exception.OfficeNotFoundException;
import com.sprint.classicmodelsbussiness.service.impl.OfficeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClassicModelsBussinessApp.class)
@AutoConfigureMockMvc
@WithMockUser(username = "sanjair83", roles = "ADMIN")
public class OfficeControllerTest {

	@MockBean
	private OfficeServiceImpl officeService;

	@InjectMocks
	private OfficeController officeController;

	@Autowired
	private MockMvc mockMvc;

	private ResponseDto responseDto = new ResponseDto();

	@Test

	public void testGetOfficeByCode() throws Exception {

		int officeCode = 120987653;
		OfficeDto officeDto = new OfficeDto();
		officeDto.setOfficeCode(officeCode);

		when(officeService.getOfficeByCode(officeCode)).thenReturn(officeDto);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/office/{office_code}", officeCode)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(officeDto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.officeCode").value(officeDto.getOfficeCode()));

		verify(officeService, times(1)).getOfficeByCode(officeCode);
	}

	public static String asJsonString(Object object) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testGetAllOffices() throws Exception {
		OfficeDto officeDto1 = new OfficeDto();

		List<OfficeDto> officeDtoList = Arrays.asList(officeDto1);

		when(officeService.getAllOffices()).thenReturn(officeDtoList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/office/all_office_details"))
				.andExpect(MockMvcResultMatchers.status().isOk());

		verify(officeService, times(1)).getAllOffices();
	}

	@Test
	public void testSaveOffice() throws Exception {
		OfficeDto officeDto = new OfficeDto(1, "City", "1234567890", "Address Line 1", "Address Line 2", "State",
				"Country", "12345", "Territory");
		responseDto.setMessage("Office saved successfully");
		when(officeService.saveOffice(officeDto)).thenReturn(responseDto);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/office").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(officeDto))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		verify(officeService, times(1)).saveOffice(officeDto);
	}

	@Test
	public void testUpdateOfficePhone() throws Exception {
		int officeCode = 123;

		String phone = "1234567890";
		OfficeDto officeDto = new OfficeDto();
		officeDto.setOfficeCode(officeCode);
		officeDto.setPhone(phone);
		responseDto.setMessage("Phone updated successfully");
		when(officeService.updateOfficePhone(officeCode, phone)).thenReturn(responseDto);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/office/{office_code}/{phone}", officeCode, phone))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		verify(officeService, times(1)).updateOfficePhone(officeCode, phone);
	}

	@Test
	public void testUpdateOfficeAddress() throws Exception {
		int officeCode = 1;
		OfficeDto officeDto = new OfficeDto(1, "City", "1234567890", "Address Line 1", "Address Line 2", "State",
				"Country", "12345", "Territory");

		responseDto.setMessage("Office address updated successfully");
		when(officeService.updateOfficeAddress(officeDto, officeCode)).thenReturn(responseDto);
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/office/update/{office_code}", officeCode)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(officeDto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		verify(officeService, times(1)).updateOfficeAddress(officeDto, officeCode);

	}

	@Test
	public void testGetOfficeByCityList() throws Exception {
		String[] cities = { "City1", "City2", "City3" };

		OfficeDto officeDto1 = new OfficeDto();
		officeDto1.setOfficeCode(1);

		List<OfficeDto> officeDtoList = Arrays.asList(officeDto1);

		when(officeService.getOfficeByCityList(cities)).thenReturn(officeDtoList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/offices/City1/City2/City3"))
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].officeCode").value(officeDto1.getOfficeCode()));

		verify(officeService, times(1)).getOfficeByCityList(cities);
	}

	@Test
	public void testGetCustomersByOfficeCode() throws Exception {
		int officeCode = 123;

		CustomersDto customersDto1 = new CustomersDto();
		customersDto1.setCustomerNumber(1);

		List<CustomersDto> customersDtoList = Arrays.asList(customersDto1);

		when(officeService.getCustomersByOfficeCode(officeCode)).thenReturn(customersDtoList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/offices/customers/{office_code}", officeCode))
				.andExpect(MockMvcResultMatchers.status().isFound()).andExpect(
						MockMvcResultMatchers.jsonPath("$[0].customerNumber").value(customersDto1.getCustomerNumber()));

		verify(officeService, times(1)).getCustomersByOfficeCode(officeCode);
	}

	@Test
	public void testGetOfficeByCode_Negative() throws Exception {
		int office_code = 123;

		when(officeService.getOfficeByCode(office_code)).thenThrow(new OfficeNotFoundException("Office not found"));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/office/{office_code}", office_code))
				.andExpect(status().isNotFound());

		verify(officeService, times(1)).getOfficeByCode(office_code);
	}

	@Test
	public void testGetAllOffices_Negative() throws Exception {

		List<OfficeDto> officeDtoList = new ArrayList<>();

		when(officeService.getAllOffices()).thenReturn(officeDtoList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/office/all_office_details"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

		verify(officeService, times(1)).getAllOffices();
	}

	@Test
	public void testSaveOffice_Negative() throws Exception {
		OfficeDto officeDto = new OfficeDto(1, "City", "1234567890", "Address Line 1", "Address Line 2", "State",
				"Country", "12345", "Territory");

		when(officeService.saveOffice(officeDto))
				.thenThrow(new OfficeNotFoundException("Failed to add office details"));

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/office").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(officeDto))).andExpect(MockMvcResultMatchers.status().isNotFound());

		verify(officeService, times(1)).saveOffice(officeDto);
	}

	@Test
	public void testUpdateOfficePhone_Negative() throws Exception {
		int officeCode = 123;

		String phone = "1234567890";
		OfficeDto officeDto = new OfficeDto();
		officeDto.setOfficeCode(officeCode);
		officeDto.setPhone(phone);

		when(officeService.updateOfficePhone(officeCode, phone))
				.thenThrow(new OfficeNotFoundException("Office details not found"));

		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/office/{office_code}/{phone}", officeCode, phone))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		verify(officeService, times(1)).updateOfficePhone(officeCode, phone);
	}

	@Test
	public void testUpdateOfficeAddress_Negative() throws Exception {
		int officeCode = 1;
		OfficeDto officeDto = new OfficeDto(1, "City", "1234567890", "Address Line 1", "Address Line 2", "State",
				"Country", "12345", "Territory");

		when(officeService.updateOfficeAddress(officeDto, officeCode))
				.thenThrow(new OfficeNotFoundException("Office details not found"));
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/office/update/{office_code}", officeCode)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(officeDto)))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		verify(officeService, times(1)).updateOfficeAddress(officeDto, officeCode);

	}

	@Test
	public void testGetOfficeByCityList_Negative() throws Exception {
		String[] cities = { "City1", "City2", "City3" };

		OfficeDto officeDto1 = new OfficeDto();
		officeDto1.setOfficeCode(1);

		List<OfficeDto> officeDtoList = new ArrayList<>();

		when(officeService.getOfficeByCityList(cities)).thenReturn(officeDtoList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/offices/City1/City2/City3"))
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

		verify(officeService, times(1)).getOfficeByCityList(cities);
	}

	@Test
	public void testGetCustomersByOfficeCode_Negative() throws Exception {
		int officeCode = 123;

		List<CustomersDto> customersDtoList = new ArrayList<>();

		when(officeService.getCustomersByOfficeCode(officeCode)).thenReturn(customersDtoList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/offices/customers/{office_code}", officeCode))
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

		verify(officeService, times(1)).getCustomersByOfficeCode(officeCode);
	}

}
