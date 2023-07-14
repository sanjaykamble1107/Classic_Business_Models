package com.sprint.classicmodelsbussiness.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
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
import com.sprint.classicmodelsbussiness.dto.CustomersDto;
import com.sprint.classicmodelsbussiness.dto.PaymentsDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.exception.CustomersNotFoundException;
import com.sprint.classicmodelsbussiness.service.impl.CustomersServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "sanjair83", roles = "ADMIN")
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomersServiceImpl customersService;

	@Test
	public void testSaveCustomers() throws Exception {
		CustomersDto customersDto = new CustomersDto(103, "sagar", "Harsh", "Patil", "8907653214", "Kasaba Paith",
				"Shivaji nagar", "Pune", "MH", "India", "411011", 1111, new BigDecimal(21000.00));

		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("Record Created Successfully");

		when(customersService.saveCustomer(customersDto)).thenReturn(responseDto);

		mockMvc.perform(
				post("/api/v1/customers").contentType(MediaType.APPLICATION_JSON).content(asJsonString(customersDto)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.message").value("Record Created Successfully"));

		verify(customersService, times(1)).saveCustomer(any(CustomersDto.class));
	}

	@Test
	public void testSaveCustomers_ErrorSavingRecord() throws Exception {
		CustomersDto customersDto = new CustomersDto(103, "sagar", "Harsh", "Patil", "8907653214", "Kasaba Paith",
				"Shivaji nagar", "Pune", "MH", "India", "411011", 1111, new BigDecimal(21000.00));

		when(customersService.saveCustomer(customersDto))
				.thenThrow(new CustomersNotFoundException("Error occurred while saving the record"));

		mockMvc.perform(
				post("/api/v1/customers").contentType(MediaType.APPLICATION_JSON).content(asJsonString(customersDto)))
				.andExpect(status().isNotFound());
		verify(customersService, times(1)).saveCustomer(any(CustomersDto.class));
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
	public void testGetCustomerById() throws Exception {
		Integer customerNumber = 103;

		CustomersDto customersDto = new CustomersDto();
		customersDto.setCustomerNumber(customerNumber);
		when(customersService.getCustomerById(customerNumber)).thenReturn(customersDto);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/v1/customers/{customerNumber}", customerNumber))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerNumber").value(customerNumber));
		verify(customersService, times(1)).getCustomerById(customerNumber);

	}

	@Test
	public void testGetCustomerById_CustomerNotFound() throws Exception {
		Integer customerNumber = 103;
		when(customersService.getCustomerById(customerNumber))
				.thenThrow(new CustomersNotFoundException("Customer Not Found with ID: 103"));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/v1/customers/{customerNumber}", customerNumber))
				.andExpect(status().isNotFound());
		verify(customersService, times(1)).getCustomerById(customerNumber);
	}

	@Test
	public void testGetCustomerByCustomerName() throws Exception {
		String customerName = "Atelier graphique";
		CustomersDto customersDto = new CustomersDto();
		customersDto.setCustomerName(customerName);
		List<CustomersDto> customerDtos = Arrays.asList(customersDto);
		when(customersService.getCustomerByCustomerName(customerName)).thenReturn(customerDtos);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/customerName/{customerName}", customerName))
				.andExpect(status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].customerName").value(customerName));
		verify(customersService, times(1)).getCustomerByCustomerName(customerName);
	}

	@Test
	public void testGetCustomerByCustomerNameInvalidCustomerName() throws Exception {
		String customerName = "Sandesh";
		when(customersService.getCustomerByCustomerName(customerName))
				.thenThrow(new CustomersNotFoundException("Customer Not Found with name: Sandesh"));
		mockMvc.perform(get("/api/v1/customers/customerName/{customerName}", customerName))
				.andExpect(status().isNotFound());
		verify(customersService, times(1)).getCustomerByCustomerName(customerName);

	}

	@Test
	public void testGetCustomerByCity() throws Exception {
		String city = "Nantes";
		CustomersDto customersDto = new CustomersDto();
		customersDto.setCity(city);
		List<CustomersDto> customersDtos = Arrays.asList(customersDto);
		when(customersService.getCustomersByCity(city)).thenReturn(customersDtos);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/city/{city}", city)).andExpect(status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].city").value(city));
		verify(customersService, times(1)).getCustomersByCity(city);

	}

	@Test
	public void testGetCustomerByCity_NoCustomersFound() throws Exception {
		String city = "Nantes";

		when(customersService.getCustomersByCity(city))
				.thenThrow(new CustomersNotFoundException("Customer Not Found with City:Nantes"));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/city/{city}", city))
				.andExpect(status().isNotFound());
		verify(customersService, times(1)).getCustomersByCity(city);
	}

	@Test
	public void testGetCustomerByCountry() throws Exception {
		String country = "France";
		CustomersDto customersDto = new CustomersDto();
		customersDto.setCountry(country);
		List<CustomersDto> customersDtos = Arrays.asList(customersDto);
		when(customersService.getCustomersByCountry(country)).thenReturn(customersDtos);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/country/{country}", country))
				.andExpect(status().isFound()).andExpect(MockMvcResultMatchers.jsonPath("$[0].country").value(country));
		verify(customersService, times(1)).getCustomersByCountry(country);
	}

	@Test
	public void testGetCustomerByCountry_InvalidRequest() throws Exception {
		String country = "";
		when(customersService.getCustomersByCountry(country))
				.thenThrow(new CustomersNotFoundException("Customer Not Found with Country"));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/country/{country}", country))
				.andExpect(status().isNotFound());
		verify(customersService, times(0)).getCustomersByCountry(country);
	}

	@Test
	public void testGetCustomerByPhone() throws Exception {
		String phone = "7025551838";
		CustomersDto customersDto = new CustomersDto();
		customersDto.setPhone(phone);
		List<CustomersDto> customersDtos = Arrays.asList(customersDto);
		when(customersService.getCustomerByPhone(phone)).thenReturn(customersDtos);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/phone/{phone}", phone))
				.andExpect(status().isFound()).andExpect(MockMvcResultMatchers.jsonPath("$[0].phone").value(phone));
		verify(customersService, times(1)).getCustomerByPhone(phone);
	}

	@Test
	public void testGetCustomerByPhone_InvalidRequest() throws Exception {
		String phone = "";
		when(customersService.getCustomerByPhone(phone))
				.thenThrow(new CustomersNotFoundException("Customer Not Found with Phone number"));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/phone/{phone}", phone))
				.andExpect(status().isNotFound());
		verify(customersService, times(0)).getCustomerByPhone(phone);
	}

	@Test
	public void testGetCustomerBycontactLastName() throws Exception {
		String contactLastName = "Schmitt";
		CustomersDto customersDto = new CustomersDto();
		customersDto.setContactLastName(contactLastName);
		List<CustomersDto> customersDtos = Arrays.asList(customersDto);
		when(customersService.getCustomerBycontactLastName(contactLastName)).thenReturn(customersDtos);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/customers/contactLastName/{contactLastName}", contactLastName))
				.andExpect(status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].contactLastName").value(contactLastName));
		verify(customersService, times(1)).getCustomerBycontactLastName(contactLastName);

	}

	@Test
	public void testGetCustomerBycontactLastName_InvalidRequest() throws Exception {
		String contactLastName = "";
		when(customersService.getCustomerBycontactLastName(contactLastName))
				.thenThrow(new CustomersNotFoundException("Customer Not Found with LastName"));
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/customers/contactLastName/{contactLastName}", contactLastName))
				.andExpect(status().isNotFound());
		verify(customersService, times(0)).getCustomerBycontactLastName(contactLastName);
	}

	@Test
	public void testGetCustomerBycontactFirstName() throws Exception {
		String contactFirstName = "Carine ";
		CustomersDto customersDto = new CustomersDto();
		customersDto.setContactFirstName(contactFirstName);
		List<CustomersDto> customersDtos = Arrays.asList(customersDto);
		when(customersService.getCustomerBycontactFirstName(contactFirstName)).thenReturn(customersDtos);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/customers/contactFirstName/{contactFirstName}", contactFirstName))
				.andExpect(status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].contactFirstName").value(contactFirstName));
		verify(customersService, times(1)).getCustomerBycontactFirstName(contactFirstName);

	}

	@Test
	public void testGetCustomerBycontactFirstName_InvalidRequest() throws Exception {
		String contactFirstName = null;
		when(customersService.getCustomerBycontactFirstName(contactFirstName))
				.thenThrow(new CustomersNotFoundException("Customer Not Found with firstName"));
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/customers/contactFirstName/{contactFirstName}", contactFirstName))
				.andExpect(status().isNotFound());
		verify(customersService, times(0)).getCustomerBycontactFirstName(contactFirstName);
	}

	@Test
	public void testGetCustomersByCreditLimit() throws Exception {
		BigDecimal creditLimit = new BigDecimal(21000.00);
		CustomersDto customersDto = new CustomersDto();
		customersDto.setCreditLimit(creditLimit);
		List<CustomersDto> customersDtos = Arrays.asList(customersDto);
		when(customersService.getCustomersByCreditLimit(creditLimit)).thenReturn(customersDtos);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/creditLimit/{creditLimit}", creditLimit))
				.andExpect(status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].creditLimit").value(creditLimit));
		verify(customersService, times(1)).getCustomersByCreditLimit(creditLimit);

	}

	@Test
	public void testGetCustomersByCreditLimit_Null() throws Exception {
		BigDecimal creditLimit = null;
		when(customersService.getCustomersByCreditLimit(creditLimit))
				.thenThrow(new CustomersNotFoundException("Customer Not Found with firstName"));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/creditLimit/{creditLimit}", creditLimit))
				.andExpect(status().isNotFound());
		verify(customersService, times(0)).getCustomersByCreditLimit(creditLimit);
	}

	@Test
	public void testGetCustomersByPostalCode() throws Exception {
		String postalCode = "83030";
		CustomersDto customersDto = new CustomersDto();
		customersDto.setPostalCode(postalCode);
		List<CustomersDto> customersDtos = Arrays.asList(customersDto);
		when(customersService.getCustomersByPostalCode(postalCode)).thenReturn(customersDtos);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/postalCode/{postalCode}", postalCode))
				.andExpect(status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].postalCode").value(postalCode));
		verify(customersService, times(1)).getCustomersByPostalCode(postalCode);
	}

	@Test
	public void testGetCustomersByPostalCode_Negative() throws Exception {
		String postalCode = "";

		// Create an exception to be thrown by the
		// customersService.getCustomersByPostalCode() method
		CustomersNotFoundException exception = new CustomersNotFoundException("Customers not found");

		// Mock the customersService.getCustomersByPostalCode() method to throw the
		// exception
		when(customersService.getCustomersByPostalCode(postalCode)).thenThrow(exception);

		// Perform the GET request
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/postalCode/{postalCode}", postalCode))
				.andExpect(status().isNotFound());

		// Verify that the customersService.getCustomersByPostalCode() method was called
		// with the correct parameters
		verify(customersService, times(0)).getCustomersByPostalCode(postalCode);
	}

	@Test
	public void testGetCustomersBySalesRepEmployeeNumber() throws Exception {
		Integer salesRepEmployeeNumber = 1370;
		CustomersDto customersDto = new CustomersDto();
		customersDto.setSalesRepEmployeeNumber(salesRepEmployeeNumber);
		List<CustomersDto> customersDtos = Arrays.asList(customersDto);
		when(customersService.getCustomersBySalesRepEmployeeNumber(salesRepEmployeeNumber)).thenReturn(customersDtos);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/salesRepEmployeeNumber/{salesRepEmployeeNumber}",
				salesRepEmployeeNumber)).andExpect(status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].salesRepEmployeeNumber").value(salesRepEmployeeNumber));
		verify(customersService, times(1)).getCustomersBySalesRepEmployeeNumber(salesRepEmployeeNumber);

	}

	@Test
	public void testGetCustomersBySalesRepEmployeeNumber_Negative() throws Exception {
		Integer salesRepEmployeeNumber = 1370;

		// Create an exception to be thrown by the
		// customersService.getCustomersBySalesRepEmployeeNumber() method
		CustomersNotFoundException exception = new CustomersNotFoundException("Customers not found");

		// Mock the customersService.getCustomersBySalesRepEmployeeNumber() method to
		// throw the exception
		when(customersService.getCustomersBySalesRepEmployeeNumber(salesRepEmployeeNumber)).thenThrow(exception);

		// Perform the GET request
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/salesRepEmployeeNumber/{salesRepEmployeeNumber}",
				salesRepEmployeeNumber)).andExpect(status().isNotFound());

		// Verify that the customersService.getCustomersBySalesRepEmployeeNumber()
		// method was called with the correct parameters
		verify(customersService, times(1)).getCustomersBySalesRepEmployeeNumber(salesRepEmployeeNumber);
	}

	@Test
	public void testGetAllCustomers() throws Exception {

		CustomersDto customersDto = new CustomersDto();
		List<CustomersDto> customersDtos = Arrays.asList(customersDto);
		when(customersService.getAllCustomers()).thenReturn(customersDtos);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/all_customers")).andExpect(status().isOk());
		verify(customersService, times(1)).getAllCustomers();

	}

	@Test
	public void testGetAllCustomers_Negative() throws Exception {

		// Create an exception to be thrown by the customersService.getAllCustomers()
		// method
		CustomersNotFoundException exception = new CustomersNotFoundException("Customers not found");

		// Mock the customersService.getAllCustomers() method to throw the exception
		when(customersService.getAllCustomers()).thenThrow(exception);

		// Perform the GET request
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/all_customers")).andExpect(status().isNotFound());

		// Verify that the customersService.getAllCustomers() method was called
		verify(customersService, times(1)).getAllCustomers();
	}

	@Test
	public void testUpdateNameOfCustomerByCustomerNumber() throws Exception {
		Integer customerNumber = 103;
		String newCustomerName = "Shubham";
		CustomersDto updatedCustomerDto = new CustomersDto();
		updatedCustomerDto.setCustomerNumber(customerNumber);
		updatedCustomerDto.setCustomerName(newCustomerName);

		when(customersService.updateNameOfCustomerByCustomerNumber(customerNumber, newCustomerName))
				.thenReturn(updatedCustomerDto);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customers/customerName/{customerNumber}/{customerName}",
				customerNumber, newCustomerName)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerNumber").value(customerNumber))
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerName").value(newCustomerName));

		verify(customersService, times(1)).updateNameOfCustomerByCustomerNumber(customerNumber, newCustomerName);
	}

	@Test
	public void testUpdateNameOfCustomerByCustomerNumber_Negative() throws Exception {
		Integer customerNumber = 103;
		String newCustomerName = "Shubham";

		// Create an exception to be thrown by the
		// customersService.updateNameOfCustomerByCustomerNumber() method
		CustomersNotFoundException exception = new CustomersNotFoundException("Customer not found");

		// Mock the customersService.updateNameOfCustomerByCustomerNumber() method to
		// throw the exception
		when(customersService.updateNameOfCustomerByCustomerNumber(customerNumber, newCustomerName))
				.thenThrow(exception);

		// Perform the PUT request
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customers/customerName/{customerNumber}/{customerName}",
				customerNumber, newCustomerName)).andExpect(status().isNotFound());

		// Verify that the customersService.updateNameOfCustomerByCustomerNumber()
		// method was called
		verify(customersService, times(1)).updateNameOfCustomerByCustomerNumber(customerNumber, newCustomerName);

	}

	@Test
	public void testUpdateNameOfCustomerByCustomerNumber_CustomerNotFound() throws Exception {
		Integer customerNumber = 103;
		String newCustomerName = "Shubham";

		when(customersService.updateNameOfCustomerByCustomerNumber(customerNumber, newCustomerName))
				.thenThrow(new CustomersNotFoundException("Customer not found"));

		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customers/customerName/{customerNumber}/{customerName}",
				customerNumber, newCustomerName)).andExpect(status().isNotFound());

		verify(customersService, times(1)).updateNameOfCustomerByCustomerNumber(customerNumber, newCustomerName);
	}

	@Test
	public void testUpdateContactLastNameByCustomerNumber() throws Exception {
		Integer customerNumber = 101;
		String newContactLastName = "Patil";

		CustomersDto updatedCustomerDto = new CustomersDto();
		updatedCustomerDto.setCustomerNumber(customerNumber);
		updatedCustomerDto.setContactLastName(newContactLastName);

		when(customersService.updateContactLastNameByCustomerNumber(customerNumber, newContactLastName))
				.thenReturn(updatedCustomerDto);

		mockMvc.perform(
				MockMvcRequestBuilders.put("/api/v1/customers/contactLastName/{customerNumber}/{contactLastName}",
						customerNumber, newContactLastName))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerNumber").value(customerNumber))
				.andExpect(MockMvcResultMatchers.jsonPath("$.contactLastName").value(newContactLastName));
		verify(customersService, times(1)).updateContactLastNameByCustomerNumber(customerNumber, newContactLastName);
	}

	@Test
	public void testUpdateContactLastNameByCustomerNumber_CustomerNotFound() throws Exception {
		Integer customerNumber = 101;
		String newContactLastName = "Patil";

		// Create an exception to be thrown by the
		// customersService.updateContactLastNameByCustomerNumber() method
		CustomersNotFoundException exception = new CustomersNotFoundException("Customer not found");

		// Mock the customersService.updateContactLastNameByCustomerNumber() method to
		// throw the exception
		when(customersService.updateContactLastNameByCustomerNumber(customerNumber, newContactLastName))
				.thenThrow(exception);

		// Perform the PUT request
		mockMvc.perform(
				MockMvcRequestBuilders.put("/api/v1/customers/contactLastName/{customerNumber}/{contactLastName}",
						customerNumber, newContactLastName))
				.andExpect(status().isNotFound());

		// Verify that the customersService.updateContactLastNameByCustomerNumber()
		// method was called
		verify(customersService, times(1)).updateContactLastNameByCustomerNumber(customerNumber, newContactLastName);
	}

	@Test
	public void testUpdateContactFirstNameByCustomerNumber() throws Exception {
		Integer customerNumber = 101;
		String newContactFirstName = "Bhosale";

		CustomersDto updatedCustomerDto = new CustomersDto();
		updatedCustomerDto.setCustomerNumber(customerNumber);
		updatedCustomerDto.setContactFirstName(newContactFirstName);
		when(customersService.updateContactFirstNameByCustomerNumber(customerNumber, newContactFirstName))
				.thenReturn(updatedCustomerDto);

		mockMvc.perform(
				MockMvcRequestBuilders.put("/api/v1/customers/contactFirstName/{customerNumber}/{contactFirstName}",
						customerNumber, newContactFirstName))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerNumber").value(customerNumber))
				.andExpect(MockMvcResultMatchers.jsonPath("$.contactFirstName").value(newContactFirstName));
		verify(customersService, times(1)).updateContactFirstNameByCustomerNumber(customerNumber, newContactFirstName);
	}

	@Test
	public void testUpdateContactFirstNameByCustomerNumber_CustomerNotFound() throws Exception {
		Integer customerNumber = 101;
		String newContactFirstName = "Shubhash";

		// Create an exception to be thrown by the
		// customersService.updateContactFirstNameByCustomerNumber() method
		CustomersNotFoundException exception = new CustomersNotFoundException("Customer not found");

		// Mock the customersService.updateContactFirstNameByCustomerNumber() method to
		// throw the exception
		when(customersService.updateContactFirstNameByCustomerNumber(customerNumber, newContactFirstName))
				.thenThrow(exception);

		// Perform the PUT request
		mockMvc.perform(
				MockMvcRequestBuilders.put("/api/v1/customers/contactFirstName/{customerNumber}/{contactFirstName}",
						customerNumber, newContactFirstName))
				.andExpect(status().isNotFound());

		// Verify that the customersService.updateContactFirstNameByCustomerNumber()
		// method was called
		verify(customersService, times(1)).updateContactFirstNameByCustomerNumber(customerNumber, newContactFirstName);
	}

	@Test
	public void testGetCustomersBetweenGivenRangeOfCredit() throws Exception {
		BigDecimal start = new BigDecimal("1000.00");
		BigDecimal end = new BigDecimal("5000.00");

		List<CustomersDto> customers = new ArrayList<>();
		CustomersDto customer = new CustomersDto();
		customer.setCustomerNumber(112);
		customer.setCustomerName("John Doe");

		customers.add(customer);

		when(customersService.getCustomersBetweenGivenRangeOfCredit(start, end)).thenReturn(customers);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/givenRangeOfCredit/{start}/{end}", start, end))
				.andExpect(status().isFound()).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(customers.size())))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].customerNumber")
						.value(customers.get(0).getCustomerNumber()))
				.andExpect(
						MockMvcResultMatchers.jsonPath("$[0].customerName").value(customers.get(0).getCustomerName()))
				.andReturn();

		verify(customersService, times(1)).getCustomersBetweenGivenRangeOfCredit(start, end);
	}

	@Test
	public void testGetCustomersBetweenGivenRangeOfCredit_NoCustomersFound() throws Exception {
		BigDecimal start = new BigDecimal("1000.00");
		BigDecimal end = new BigDecimal("5000.00");

		CustomersNotFoundException exception = new CustomersNotFoundException("Customer not found");

		// Mock the customersService.getCustomersBetweenGivenRangeOfCredit() method to
		// return the empty list
		when(customersService.getCustomersBetweenGivenRangeOfCredit(start, end)).thenThrow(exception);

		// Perform the GET request
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/givenRangeOfCredit/{start}/{end}", start, end))
				.andExpect(status().isNotFound());

		// Verify that the customersService.getCustomersBetweenGivenRangeOfCredit()
		// method was called
		verify(customersService, times(1)).getCustomersBetweenGivenRangeOfCredit(start, end);
	}

	@Test
	public void testGetCustomersWhereCreditlimitIsLowerThan() throws Exception {
		BigDecimal creditLimit = new BigDecimal("50000.00");

		List<CustomersDto> customers = new ArrayList<>();
		CustomersDto customer = new CustomersDto();
		customer.setCustomerNumber(112);
		customer.setCustomerName("Rohit");
		customers.add(customer);

		when(customersService.getCustomersWhereCreditlimitIsLowerThan(creditLimit)).thenReturn(customers);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/customers/creditlimitIsLowerThan/{creditlimit}", creditLimit))
				.andExpect(status().isFound()).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(customers.size())))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].customerNumber")
						.value(customers.get(0).getCustomerNumber()))
				.andExpect(
						MockMvcResultMatchers.jsonPath("$[0].customerName").value(customers.get(0).getCustomerName()))
				.andReturn();

		verify(customersService, times(1)).getCustomersWhereCreditlimitIsLowerThan(creditLimit);
	}

	@Test
	public void testGetCustomersWhereCreditlimitIsLowerThan_NoCustomersFound() throws Exception {
		BigDecimal creditLimit = null;

		CustomersNotFoundException exception = new CustomersNotFoundException("Customer not found");

		// Mock the customersService.getCustomersWhereCreditlimitIsLowerThan() method to
		// return the empty list
		when(customersService.getCustomersWhereCreditlimitIsLowerThan(creditLimit)).thenThrow(exception);

		// Perform the GET request
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/customers/creditlimitIsLowerThan/{creditlimit}", creditLimit))
				.andExpect(status().isNotFound());

		// Verify that the customersService.getCustomersWhereCreditlimitIsLowerThan()
		// method was called
		verify(customersService, times(0)).getCustomersWhereCreditlimitIsLowerThan(creditLimit);
	}

	@Test
	public void testGetCustomersWhereCreditLimitIsGreaterThan() throws Exception {
		BigDecimal creditLimit = new BigDecimal("10000.00");

		List<CustomersDto> customers = new ArrayList<>();
		CustomersDto customersDto = new CustomersDto();
		customersDto.setCustomerNumber(103);
		customersDto.setCustomerName("sagar");
		customersDto.setContactFirstName("Harsh");
		customersDto.setContactLastName("Patil");
		customersDto.setPhone("8907653214");
		customersDto.setAddressLine1("Kasaba Paith");
		customersDto.setAddressLine2("Shivaji Nagar");
		customersDto.setCity("Pune");
		customersDto.setState("MH");
		customersDto.setCountry("India");
		customersDto.setPostalCode("411011");
		customersDto.setSalesRepEmployeeNumber(1111);
		customersDto.setCreditLimit(new BigDecimal(21000.00));
		customers.add(customersDto);

		when(customersService.getCustomersWhereCreditLimitIsGreaterThan(creditLimit)).thenReturn(customers);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/customers/creditlimitIsGreaterThan/{creditlimit}", creditLimit))
				.andExpect(status().isFound()).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(customers.size())))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].customerNumber")
						.value(customers.get(0).getCustomerNumber()))
				.andExpect(
						MockMvcResultMatchers.jsonPath("$[0].customerName").value(customers.get(0).getCustomerName()))

				.andReturn();

		verify(customersService, times(1)).getCustomersWhereCreditLimitIsGreaterThan(creditLimit);
	}

	@Test
	public void testGetCustomersWhereCreditLimitIsGreaterThan_NoCustomersFound() throws Exception {
		BigDecimal creditLimit = null;

		CustomersNotFoundException exception = new CustomersNotFoundException("Customer not found");

		// Mock the customersService.getCustomersWhereCreditLimitIsGreaterThan() method
		// to return the empty list
		when(customersService.getCustomersWhereCreditLimitIsGreaterThan(creditLimit)).thenThrow(exception);

		// Perform the GET request
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/customers/creditlimitIsGreaterThan/{creditlimit}", creditLimit))
				.andExpect(status().isNotFound());

		// Verify that the customersService.getCustomersWhereCreditLimitIsGreaterThan()
		// method was called
		verify(customersService, times(0)).getCustomersWhereCreditLimitIsGreaterThan(creditLimit);
	}

	@Test
	public void testUpdateAddressByCustomerNumber() throws Exception {
		int customerNumber = 123;
		CustomersDto customersDto = new CustomersDto();
		customersDto.setAddressLine1("New Address Line 1");
		customersDto.setAddressLine2("New Address Line 2");
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("Address updated successfully");
		when(customersService.updateAddressByCustomerNumber(eq(customerNumber), any(CustomersDto.class)))
				.thenReturn(responseDto);
		mockMvc.perform(put("/api/v1/customers/address/{customerNumber}", customerNumber)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(customersDto))).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.message").value("Address updated successfully"));
	}

	@Test
	public void testUpdateAddressByCustomerNumber_CustomerNotFound() throws Exception {
		int customerNumber = 123;
		CustomersDto customersDto = new CustomersDto();
		customersDto.setAddressLine1("New Address Line 1");
		customersDto.setAddressLine2("New Address Line 2");

		// Create an exception to be thrown by the
		// customersService.updateAddressByCustomerNumber() method
		CustomersNotFoundException exception = new CustomersNotFoundException("Customer not found");

		// Mock the customersService.updateAddressByCustomerNumber() method to throw the
		// exception
		when(customersService.updateAddressByCustomerNumber(eq(customerNumber), any(CustomersDto.class)))
				.thenThrow(exception);

		// Perform the PUT request
		mockMvc.perform(put("/api/v1/customers/address/{customerNumber}", customerNumber)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(customersDto)))
				.andExpect(status().isNotFound());

		// Verify that the customersService.updateAddressByCustomerNumber() method was
		// called
		verify(customersService, times(1)).updateAddressByCustomerNumber(eq(customerNumber), any(CustomersDto.class));
	}

	@Test
	public void testGetPaymentDetailsForParticularCustomer() throws Exception {
		int customerNumber = 123;
		PaymentsDto paymentsDto = new PaymentsDto(123, "BO864823", LocalDate.now(), new BigDecimal(100.00));
		List<PaymentsDto> paymentList = Arrays.asList(paymentsDto);
		when(customersService.getPaymentDetailsForParticularCustomer(customerNumber)).thenReturn(paymentList);
		mockMvc.perform(get("/api/v1/customers/paymentDetails/{customerNumber}", customerNumber))
				.andExpect(status().isFound()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].customerNumber").value(123))
				.andExpect(jsonPath("$[0].checkNumber").value("BO864823"));

	}

	@Test
	public void testGetPaymentDetailsForParticularCustomer_CustomerNotFound() throws Exception {
		int customerNumber = 123;

		// Create an exception to be thrown by the
		// customersService.getPaymentDetailsForParticularCustomer() method
		CustomersNotFoundException exception = new CustomersNotFoundException("Customer not found");

		// Mock the customersService.getPaymentDetailsForParticularCustomer() method to
		// throw the exception
		when(customersService.getPaymentDetailsForParticularCustomer(customerNumber)).thenThrow(exception);

		// Perform the GET request
		mockMvc.perform(get("/api/v1/customers/paymentDetails/{customerNumber}", customerNumber))
				.andExpect(status().isNotFound());

		// Verify that the customersService.getPaymentDetailsForParticularCustomer()
		// method was called
		verify(customersService, times(1)).getPaymentDetailsForParticularCustomer(customerNumber);
	}

	@Test
	public void testGetCustomersByFirstnameLike() throws Exception {
		String fn = "Sa";
		List<CustomersDto> customersDtos = new ArrayList<>();
		when(customersService.getCustomersByFirstnameLike(fn)).thenReturn(customersDtos);
		mockMvc.perform(get("/api/v1/customers/getbyfirstnamelike/{fn}", fn)).andExpect(status().isFound())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	public void testGetCustomersByFirstnameLike_NoCustomersFound() throws Exception {
		String fn = "Sa";
		CustomersNotFoundException exception = new CustomersNotFoundException("Customer not found");

		// Mock the customersService.getCustomersByFirstnameLike() method to return the
		// empty list
		when(customersService.getCustomersByFirstnameLike(fn)).thenThrow(exception);

		// Perform the GET request
		mockMvc.perform(get("/api/v1/customers/getbyfirstnamelike/{fn}", fn)).andExpect(status().isNotFound());

		// Verify that the customersService.getCustomersByFirstnameLike() method was
		// called
		verify(customersService, times(1)).getCustomersByFirstnameLike(fn);
	}

	@Test
	public void testGetCustomersOnPage() throws Exception {
		int pageSize = 10;
		int pageNo = 1;
		String sortBy = "lastName";
		String sortDir = "asc";

		// Create a list of CustomersDto objects for the expected result
		List<CustomersDto> expectedCustomersPage = new ArrayList<>();

		// Mock the customersService.getCustomersOnPage() method to return the expected
		// result
		when(customersService.getCustomersOnPage(pageSize, pageNo, sortBy, sortDir)).thenReturn(expectedCustomersPage);

		// Perform the GET request
		mockMvc.perform(MockMvcRequestBuilders.get("/api/{pageSize}/{pageNo}/{sortBy}/{sortDir}", pageSize, pageNo,
				sortBy, sortDir)).andExpect(status().isOk());

		// Verify that the customersService.getCustomersOnPage() method was called with
		// the correct parameters
		verify(customersService, times(1)).getCustomersOnPage(pageSize, pageNo, sortBy, sortDir);
	}

	@Test
	public void testGetCustomersOnPage_Negative() throws Exception {
		int pageSize = 10;
		int pageNo = 1;
		String sortBy = "lastName";
		String sortDir = "asc";
		// Create an exception to be thrown by the customersService.getCustomersOnPage()
		// method
		CustomersNotFoundException exception = new CustomersNotFoundException("Customer not found");

		// Mock the customersService.getCustomersOnPage() method to throw the exception
		when(customersService.getCustomersOnPage(pageSize, pageNo, sortBy, sortDir)).thenThrow(exception);

		// Perform the GET request
		mockMvc.perform(MockMvcRequestBuilders.get("/api/{pageSize}/{pageNo}/{sortBy}/{sortDir}", pageSize, pageNo,
				sortBy, sortDir)).andExpect(status().isNotFound());

		// Verify that the customersService.getCustomersOnPage() method was called
		verify(customersService, times(1)).getCustomersOnPage(pageSize, pageNo, sortBy, sortDir);
	}

}