package com.sprint.classicmodelsbussiness.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.classicmodelsbussiness.ClassicModelsBussinessApp;
import com.sprint.classicmodelsbussiness.dto.EmployeeDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.exception.EmployeeNotFoundException;
import com.sprint.classicmodelsbussiness.service.impl.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClassicModelsBussinessApp.class)
@AutoConfigureMockMvc
@WithMockUser(username = "sanjair83", roles = "ADMIN")
public class EmployeeControllerTest {

	@MockBean
	private EmployeeServiceImpl employeeService;

	@InjectMocks
	private EmployeeController employeeController;

	@Autowired
	private MockMvc mockMvc;
	private ResponseDto responseDto = new ResponseDto();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
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
	public void testGetEmployeeById() throws Exception {
		// Test data
		Integer employeeNumber = 1;
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmployeeNumber(employeeNumber);

		// Mock the behavior of the EmployeeService's getEmployeeById() method
		when(employeeService.getEmployeeById(employeeNumber)).thenReturn(employeeDto);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/v1/employee/{employeeNumber}", employeeNumber)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.employeeNumber").value(employeeNumber));
		// Verify that the EmployeeService's getEmployeeById() method was called exactly
		// once with the expected parameter
		verify(employeeService, times(1)).getEmployeeById(employeeNumber);
	}

	@Test
	public void testGetAllEmployees() throws Exception {
		// Test data
		List<EmployeeDto> employeeList = new ArrayList<>();
		Integer employeeNumber = 1;
		EmployeeDto employeeDto1 = new EmployeeDto();
		employeeDto1.setEmployeeNumber(employeeNumber);
		employeeList.add(employeeDto1);

		// Mock the behavior of the EmployeeService's getAllEmployees() method
		when(employeeService.getAllEmployees()).thenReturn(employeeList);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/v1/employee/all_employee_details")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].employeeNumber").value(employeeNumber));
		// Verify that the EmployeeService's getAllEmployees() method was called exactly
		// once
		verify(employeeService, times(1)).getAllEmployees();
	}

	@Test
	public void testSaveEmployee() throws Exception {
		// Test data
		EmployeeDto employeeDto = new EmployeeDto(123, "Doe", "John", "123456", "johndoe@example.com", 456, 789,
				"Manager");
		responseDto.setMessage("Employee details added successfully");
		// Mock the behavior of the EmployeeService's saveEmployee() method
		when(employeeService.saveEmployee(employeeDto)).thenReturn(responseDto);

		// Perform the POST request and assertions
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employee").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(employeeDto))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(responseDto.getMessage()));

		// Verify that the EmployeeService's saveEmployee() method was called exactly
		// once with the expected parameter
		verify(employeeService, times(1)).saveEmployee(employeeDto);
	}

	@Test
	public void testUpdateEmployee() throws Exception {
		// Test data
		Integer employeeNumber = 123;
		Integer newEmployeeNumber = 456;
		responseDto.setMessage("Employee updated successfully");

		// Mock the behavior of the EmployeeService's updateEmployee() method
		when(employeeService.updateEmployee(employeeNumber, newEmployeeNumber)).thenReturn(responseDto);

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employee/{employeeNumber}/reports_to/{newemployeeNumber}",
				employeeNumber, newEmployeeNumber)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(responseDto.getMessage()));

		// Verify that the EmployeeService's updateEmployee() method was called exactly
		// once with the expected parameters
		verify(employeeService, times(1)).updateEmployee(employeeNumber, newEmployeeNumber);
	}

	@Test
	public void testUpdateEmployeeRole() throws Exception {
		// Test data
		Integer employeeNumber = 123;
		String jobTitle = "Manager";

		// Mock the behavior of the EmployeeService's updateEmployeeRole() method
		EmployeeDto updatedDto = new EmployeeDto(employeeNumber, "John", "Doe", "123456", "john.doe@example.com", 1, 1,
				jobTitle);
		when(employeeService.updateEmployeeRole(employeeNumber, jobTitle)).thenReturn(updatedDto);

		// Perform the PUT request and assertions
		mockMvc.perform(
				MockMvcRequestBuilders.put("/api/v1/employee/{employeeNumber}/{jobTitle}", employeeNumber, jobTitle))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.jobTitle").value(jobTitle));

		// Verify that the EmployeeService's updateEmployeeRole() method was called
		// exactly once with the expected parameters
		verify(employeeService, times(1)).updateEmployeeRole(employeeNumber, jobTitle);
	}

	@Test
	public void testUpdateEmployeeOffice() throws Exception {
		// Test data
		Integer officeCode = 1;
		Integer employeeNumber = 123;
		EmployeeDto employeeDto = new EmployeeDto(employeeNumber, "John", "Doe", "123456", "john.doe@example.com",
				officeCode, 1, "Manager");
		responseDto.setMessage("Employee updated successfully");
		// Mock the behavior of the EmployeeService's updateEmployeeOffice() method

		when(employeeService.updateEmployeeOffice(officeCode, employeeNumber)).thenReturn(responseDto);

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employee/mapToOffice/{officeCode}", officeCode)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(employeeDto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(responseDto.getMessage()));

		// Verify that the EmployeeService's updateEmployeeOffice() method was called
		// exactly once with the expected parameters
		verify(employeeService, times(1)).updateEmployeeOffice(officeCode, employeeNumber);
	}

	@Test
	public void testUpdateEmployeeById() throws Exception {
		// Test data
		Integer employeeNumber = 123;
		EmployeeDto employeeDto = new EmployeeDto(employeeNumber, "John", "Doe", "123456", "john.doe@example.com", 1, 1,
				"Manager");
		responseDto.setMessage("Employee updated successfully");
		// Mock the behavior of the EmployeeService's updateEmployeeById() method

		when(employeeService.updateEmployeeById(employeeNumber, employeeDto)).thenReturn(responseDto);

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employee/{employeeNumber}", employeeNumber)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(employeeDto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(responseDto.getMessage()));

		// Verify that the EmployeeService's updateEmployeeById() method was called
		// exactly once with the expected parameters
		verify(employeeService, times(1)).updateEmployeeById(employeeNumber, employeeDto);
	}

	@Test
	public void testGetAllEmployeeByOfficeId() throws Exception {
		// Test data
		Integer officeCode = 1;
		List<EmployeeDto> employees = new ArrayList<>();
		employees.add(new EmployeeDto(1, "John", "Doe", "123456", "john.doe@example.com", 1, 1, "Manager"));

		// Mock the behavior of the EmployeeService's getAllEmployeeByOfficeCode()
		// method
		when(employeeService.getAllEmployeeByOfficeCode(officeCode)).thenReturn(employees);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/v1/employee/all_employee_details/{officeCode}", officeCode)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].employeeNumber").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].officeCode").value(1));

		// Verify that the EmployeeService's getAllEmployeeByOfficeCode() method was
		// called exactly once with the expected parameter
		verify(employeeService, times(1)).getAllEmployeeByOfficeCode(officeCode);
	}

	@Test
	public void testGetAllEmployeeDetailsByCity() throws Exception {
		// Test data
		String city = "New York";
		List<EmployeeDto> employees = new ArrayList<>();
		employees.add(new EmployeeDto(1, "John", "Doe", "123456", "john.doe@example.com", 1, 1, "Manager"));

		// Mock the behavior of the EmployeeService's getAllEmployeesByCity() method
		when(employeeService.getAllEmployeesByCity(city)).thenReturn(employees);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/v1/employee/all_employee_details_city/{city}", city)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].employeeNumber").value(1));

		// Verify that the EmployeeService's getAllEmployeesByCity() method was called
		// exactly once with the expected parameter
		verify(employeeService, times(1)).getAllEmployeesByCity(city);
	}

	@Test
	public void testGetEmployeeById_Negative() throws Exception {
		// Test data
		Integer employeeNumber = 1;

		// Mock the behavior of the EmployeeService's getEmployeeById() method
		when(employeeService.getEmployeeById(employeeNumber))
				.thenThrow(new EmployeeNotFoundException("Employee details not found"));

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/v1/employee/{employeeNumber}", employeeNumber)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());
		// Verify that the EmployeeService's getEmployeeById() method was called exactly
		// once with the expected parameter
		verify(employeeService, times(1)).getEmployeeById(employeeNumber);
	}

	@Test
	public void testGetAllEmployees_Negative() throws Exception {
		// Test data
		List<EmployeeDto> employeeList = new ArrayList<>();

		// Mock the behavior of the EmployeeService's getAllEmployees() method
		when(employeeService.getAllEmployees()).thenReturn(employeeList);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/v1/employee/all_employee_details"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
		// Verify that the EmployeeService's getAllEmployees() method was called exactly
		// once
		verify(employeeService, times(1)).getAllEmployees();
	}

	@Test
	public void testSaveEmployee_Negative() throws Exception {
		// Test data
		EmployeeDto employeeDto = new EmployeeDto(123, "Doe", "John", "123456", "johndoe@example.com", 456, 789,
				"Manager");
		// Mock the behavior of the EmployeeService's saveEmployee() method
		when(employeeService.saveEmployee(employeeDto))
				.thenThrow(new EmployeeNotFoundException("Cannot add Employee details"));

		// Perform the POST request and assertions
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employee").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(employeeDto))).andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the EmployeeService's saveEmployee() method was called exactly
		// once with the expected parameter
		verify(employeeService, times(1)).saveEmployee(employeeDto);
	}

	@Test
	public void testUpdateEmployee_Negative() throws Exception {
		// Test data
		Integer employeeNumber = 123;
		Integer newEmployeeNumber = 456;
		// Mock the behavior of the EmployeeService's updateEmployee() method
		when(employeeService.updateEmployee(employeeNumber, newEmployeeNumber))
				.thenThrow(new EmployeeNotFoundException("Employee details not found"));

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employee/{employeeNumber}/reports_to/{newemployeeNumber}",
				employeeNumber, newEmployeeNumber)).andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the EmployeeService's updateEmployee() method was called exactly
		// once with the expected parameters
		verify(employeeService, times(1)).updateEmployee(employeeNumber, newEmployeeNumber);
	}

	@Test
	public void testUpdateEmployeeRole_Negative() throws Exception {
		// Test data
		Integer employeeNumber = 123;
		String jobTitle = "Manager";

		// Mock the behavior of the EmployeeService's updateEmployeeRole() method
		when(employeeService.updateEmployeeRole(employeeNumber, jobTitle))
				.thenThrow(new EmployeeNotFoundException("Employee details not found"));

		// Perform the PUT request and assertions
		mockMvc.perform(
				MockMvcRequestBuilders.put("/api/v1/employee/{employeeNumber}/{jobTitle}", employeeNumber, jobTitle))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the EmployeeService's updateEmployeeRole() method was called
		// exactly once with the expected parameters
		verify(employeeService, times(1)).updateEmployeeRole(employeeNumber, jobTitle);
	}

	@Test
	public void testUpdateEmployeeOffice_Negative() throws Exception {
		// Test data
		Integer officeCode = 1;
		Integer employeeNumber = 123;
		EmployeeDto employeeDto = new EmployeeDto(employeeNumber, "John", "Doe", "123456", "john.doe@example.com",
				officeCode, 1, "Manager");
		when(employeeService.updateEmployeeOffice(officeCode, employeeNumber))
				.thenThrow(new EmployeeNotFoundException("Employee or office not found."));

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employee/mapToOffice/{officeCode}", officeCode)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(employeeDto)))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the EmployeeService's updateEmployeeOffice() method was called
		// exactly once with the expected parameters
		verify(employeeService, times(1)).updateEmployeeOffice(officeCode, employeeNumber);
	}

	@Test
	public void testUpdateEmployeeById_Negative() throws Exception {
		// Test data
		Integer employeeNumber = 123;
		EmployeeDto employeeDto = new EmployeeDto(employeeNumber, "John", "Doe", "123456", "john.doe@example.com", 12,
				1, "Manager");
		// Mock the behavior of the EmployeeService's updateEmployeeById() method
		when(employeeService.updateEmployeeById(employeeNumber, employeeDto))
				.thenThrow(new EmployeeNotFoundException("Employee details not found"));

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employee/{employeeNumber}", employeeNumber)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(employeeDto)))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the EmployeeService's updateEmployeeById() method was called
		// exactly once with the expected parameters
		verify(employeeService, times(1)).updateEmployeeById(employeeNumber, employeeDto);
	}

	@Test
	public void testGetAllEmployeeByOfficeId_Negative() throws Exception {
		// Test data
		Integer officeCode = 1;
		List<EmployeeDto> employees = new ArrayList<>();

		// Mock the behavior of the EmployeeService's getAllEmployeeByOfficeCode()
		// method
		when(employeeService.getAllEmployeeByOfficeCode(officeCode)).thenReturn(employees);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/v1/employee/all_employee_details/{officeCode}", officeCode)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

		// Verify that the EmployeeService's getAllEmployeeByOfficeCode() method was
		// called exactly once with the expected parameter
		verify(employeeService, times(1)).getAllEmployeeByOfficeCode(officeCode);
	}

	@Test
	public void testGetAllEmployeeDetailsByCity_Negative() throws Exception {
		// Test data
		String city = "New York";
		List<EmployeeDto> employees = new ArrayList<>();
		// Mock the behavior of the EmployeeService's getAllEmployeesByCity() method
		when(employeeService.getAllEmployeesByCity(city)).thenReturn(employees);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/v1/employee/all_employee_details_city/{city}", city)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

		// Verify that the EmployeeService's getAllEmployeesByCity() method was called
		// exactly once with the expected parameter
		verify(employeeService, times(1)).getAllEmployeesByCity(city);
	}

}