package com.sprint.classicmodelsbussiness.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sprint.classicmodelsbussiness.dto.CustomersDto;
import com.sprint.classicmodelsbussiness.dto.PaymentsDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.service.impl.PaymentsServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "sanjair83", roles = "ADMIN")
public class PaymentsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PaymentsServiceImpl paymentsService;

	@Test
	public void testSavePayments() throws Exception {
		PaymentsDto paymentsDto = new PaymentsDto(123, "CHK123", LocalDate.of(2000, 01, 01), new BigDecimal(100.00));
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("Payment details added Successfully");
		when(paymentsService.savePayments(paymentsDto)).thenReturn(responseDto);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		mockMvc.perform(post("/api/v1/payments/save").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(paymentsDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Payment details added Successfully"));

		verify(paymentsService, times(1)).savePayments(paymentsDto);
	}

//    @Test
//    public void testSavePayments_InvalidData() throws Exception {
//        PaymentsDto paymentsDto = new PaymentsDto(123, "CHK123", LocalDate.of(2000, 01, 01), new BigDecimal(-100.00));
//        PaymentsNotFoundException exception = new PaymentsNotFoundException("Payments Details not found");
//        when(paymentsService.savePayments(paymentsDto)).thenThrow(exception);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        mockMvc.perform(post("/api/v1/payments/save")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(paymentsDto)))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.message").value("Payments Details not found"));
//        verify(paymentsService, times(0)).savePayments(any(PaymentsDto.class));
//    }

	public static String asJsonString(Object object) {

		try {

			ObjectMapper objectMapper = new ObjectMapper();

			return objectMapper.writeValueAsString(object);

		} catch (Exception e) {

			throw new RuntimeException(e);

		}

	}

	@Test
	public void testGetAllPaymentDetailsForSpecificCustomer() throws Exception {
		Integer customerNumber = 103;

		// Create a list of mock PaymentsDto objects
		List<PaymentsDto> paymentsDtos = new ArrayList<>();
		PaymentsDto payment1 = new PaymentsDto();
		payment1.setAmount(new BigDecimal("500.00"));
		paymentsDtos.add(payment1);

		PaymentsDto payment2 = new PaymentsDto();
		payment2.setAmount(new BigDecimal("1000.00"));
		paymentsDtos.add(payment2);
		when(paymentsService.getAllPaymentDetailsByCustomerNumber(customerNumber)).thenReturn(paymentsDtos);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/payments/customerNumber/{customerNumber}", customerNumber))
				.andExpect(status().isOk())

				.andExpect(MockMvcResultMatchers.jsonPath("$[0].amount").value(payment1.getAmount().doubleValue()))

				.andExpect(MockMvcResultMatchers.jsonPath("$[1].amount").value(payment2.getAmount().doubleValue()))

				.andReturn();

		verify(paymentsService, times(1)).getAllPaymentDetailsByCustomerNumber(customerNumber);
	}

	@Test
	public void testGetAllPaymentDetailsByCheckNumber() throws Exception {
		String checkNumber = "BO864823";
		PaymentsDto paymentsDto = new PaymentsDto();
		paymentsDto.setAmount(new BigDecimal("14191.12"));
		when(paymentsService.getAllPaymentDetailsByCheckNumber(checkNumber)).thenReturn(paymentsDto);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/payments/checkNumber/{checkNumber}", checkNumber))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(paymentsDto.getAmount().doubleValue()))
				.andReturn();
		verify(paymentsService, times(1)).getAllPaymentDetailsByCheckNumber(checkNumber);
	}

	@Test
	public void testUpdateAmountOfSpecificCheckOfCustomer() throws Exception {
		Integer customerNumber = 112;
		String checkNumber = "BO864823";
		BigDecimal newAmount = new BigDecimal("14191.12");
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("Amount updated successfully");
		when(paymentsService.updateAmountOfSpecificCheckOfCustomer(customerNumber, checkNumber, newAmount))
				.thenReturn(responseDto);
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/payments/amount/{customerNumber}/{checkNumber}/{newAmount}",
				customerNumber, checkNumber, newAmount)).andExpect(status().isFound())
				.andExpect(jsonPath("$.message").value("Amount updated successfully"));
		verify(paymentsService, times(1)).updateAmountOfSpecificCheckOfCustomer(customerNumber, checkNumber, newAmount);
	}

	@Test
	public void testGetTotalAmountOfPaymentMadeBySpecificCustomer() throws Exception {
		Integer customerNumber = 103;
		BigDecimal expectedAmount = new BigDecimal("6066.78");
		when(paymentsService.getTotalAmountOfPaymentMadeBySpecificCustomer(customerNumber)).thenReturn(expectedAmount);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/payments/toatlAmount/{customerNumber}", customerNumber))
				.andExpect(status().isFound())
				.andExpect(MockMvcResultMatchers.content().string(expectedAmount.toString())).andReturn();
		verify(paymentsService, times(1)).getTotalAmountOfPaymentMadeBySpecificCustomer(customerNumber);
	}

	@Test
	public void testGetCustomersByCheckNumber() throws Exception {
		String checkNumber = "BO864823";
		CustomersDto customersDto = new CustomersDto();
		customersDto.setCustomerName("John Doe");
		when(paymentsService.getCustomersByCheckNumber(checkNumber)).thenReturn(customersDto);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/payments/CustomersByCheckNumber/{checkNumber}", checkNumber))
				.andExpect(status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerName").value(customersDto.getCustomerName()))
				.andReturn();
		verify(paymentsService, times(1)).getCustomersByCheckNumber(checkNumber);
	}

	@Test
	public void testGetAllPaymentDetailsByPaymentDate() throws Exception {
		LocalDate paymentDate = LocalDate.of(2023, 7, 6);
		PaymentsDto paymentsDto = new PaymentsDto(123, "CHK123", LocalDate.now(), new BigDecimal(100.00));
		List<PaymentsDto> paymentList = Arrays.asList(paymentsDto);

		when(paymentsService.getAllPaymentDetailsByPaymentDate(paymentDate)).thenReturn(paymentList);
		mockMvc.perform(get("/api/v1/payments/paymentDate/{paymentDate}", paymentDate))
		.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].customerNumber").value(123))
				.andExpect(jsonPath("$[0].checkNumber").value("CHK123"));

	}

	@Test
	public void testGetAllCustomersWhoPaidOnGivenDate() throws Exception {
		LocalDate paymentDate = LocalDate.of(2023, 7, 6);
		CustomersDto customer1 = new CustomersDto(103, "sagar", "Harsh", "Patil", "8907653214", "Kasaba Paith",
				"Shivaji nagar", "Pune", "MH", "India", "411011", 1111, new BigDecimal(21000.00));
		List<CustomersDto> customerList = Arrays.asList(customer1);
		when(paymentsService.getAllCustomersWhoPaidOnGivenDate(paymentDate)).thenReturn(customerList);
		mockMvc.perform(get("/api/v1/payments/allCustomers/{paymentDate}", paymentDate))
		.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].customerNumber").value(103));

	}

	@Test
	public void testGetCustomersDonePaymentInGivenDateRange() throws Exception {
		String startPayDate = "2023-07-01";
		String endPayDate = "2023-07-31";
		CustomersDto customer1 = new CustomersDto(103, "sagar", "Harsh", "Patil", "8907653214", "Kasaba Paith",
				"Shivaji nagar", "Pune", "MH", "India", "411011", 1111, new BigDecimal(21000.00));
		List<CustomersDto> customerList = Arrays.asList(customer1);
		when(paymentsService.getCustomersDonePaymentInGivenDateRange(any(LocalDate.class), any(LocalDate.class)))
				.thenReturn(customerList);
		mockMvc.perform(get("/api/v1/payments/GivenDateRange/{startPayDate}/{endPayDate}", startPayDate, endPayDate))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].customerNumber").value(103));

	}

}