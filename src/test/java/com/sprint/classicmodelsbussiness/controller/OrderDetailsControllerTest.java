package com.sprint.classicmodelsbussiness.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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
import com.sprint.classicmodelsbussiness.dto.OrderDetailsDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.exception.OrderDetailsNotFoundException;
import com.sprint.classicmodelsbussiness.service.impl.OrderDetailsServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClassicModelsBussinessApp.class)
@AutoConfigureMockMvc
@WithMockUser(username = "sanjair83", roles = "ADMIN")
public class OrderDetailsControllerTest {

	@MockBean
	private OrderDetailsServiceImpl orderDetailsService;

	@InjectMocks
	private OrderDetailsController orderDetailsController;

	@Autowired
	private MockMvc mockMvc;
	private ResponseDto responseDto = new ResponseDto();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(orderDetailsController).build();
	}

	@Test
	public void testGetAllOrderDetails() throws Exception {
		OrderDetailsDto detailsDto = new OrderDetailsDto(123, "product1", 345, new BigDecimal("100.00"), (short) 3);
		List<OrderDetailsDto> orderDetailsList = new ArrayList<>();
		orderDetailsList.add(detailsDto);

		Mockito.when(orderDetailsService.getAllOrderDetails()).thenReturn(orderDetailsList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orderdetails/all_order_details"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].orderNumber").value(123));

		Mockito.verify(orderDetailsService, times(1)).getAllOrderDetails();
	}

	@Test
	public void testSaveOrderDetails() throws Exception {
		OrderDetailsDto orderDetailsDto = new OrderDetailsDto(123, "product1", 345, new BigDecimal("100.00"),
				(short) 3);
		responseDto.setMessage("Record Created Successfully");
		Mockito.when(orderDetailsService.saveOrderDetails(orderDetailsDto)).thenReturn(responseDto);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/orderdetails/").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(orderDetailsDto))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		Mockito.verify(orderDetailsService, times(1)).saveOrderDetails(orderDetailsDto);
	}

	@Test
	public void testUpdateQuantityOrdered() throws Exception {
		Integer orderNumber = 1;
		String productCode = "ABC";
		Integer quantityOrdered = 10;
		responseDto.setMessage("Product Quantity updated successfully");
		Mockito.when(orderDetailsService.updateQuantityOrdered(orderNumber, productCode, quantityOrdered))
				.thenReturn(responseDto);

		mockMvc.perform(
				MockMvcRequestBuilders.put("/api/v1/orderdetails/{order_number}/{product_code}/{quantity_orderd}",
						orderNumber, productCode, quantityOrdered))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		Mockito.verify(orderDetailsService).updateQuantityOrdered(orderNumber, productCode, quantityOrdered);
	}

	private String asJsonString(Object object) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

	@Test
	public void testGetTotalofOrder() throws Exception {
		// Test data
		Integer orderNumber = 123;
		String total = "$1000.00";
		responseDto.setMessage("Total of order is " + total);
		// Mock the behavior of the OrderDetailsService's getTotalofOrder() method
		when(orderDetailsService.getTotalofOrder(orderNumber)).thenReturn(responseDto);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orderdetails/get_total/{order_number}", orderNumber)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		// Verify that the OrderDetailsService's getTotalofOrder() method was called
		// exactly once with the expected parameter
		verify(orderDetailsService, times(1)).getTotalofOrder(orderNumber);
	}

	@Test
	public void testGetAllTotal() throws Exception {
		// Test data
		Integer totalSale = 1000;
		Integer quantityTotal = 345;
		responseDto.setMessage("Total sale amount= " + totalSale + " total quantities sold: " + quantityTotal);
		// Mock the behavior of the OrderDetailsService's getAllTotal() method
		when(orderDetailsService.getAllTotal()).thenReturn(responseDto);

		// Perform the GET request and assertions
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/orderdetails/total_sale").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		// Verify that the OrderDetailsService's getAllTotal() method was called exactly
		// once
		verify(orderDetailsService, times(1)).getAllTotal();
	}

	@Test
	public void testGetOrderDetailsByOrderNumber() throws Exception {
		// Test data
		Integer orderNumber = 123;
		List<OrderDetailsDto> orderDetailsList = new ArrayList<>();
		OrderDetailsDto detailsDto = new OrderDetailsDto();
		// Add test data to the orderDetailsList
		detailsDto.setOrderNumber(orderNumber);
		orderDetailsList.add(detailsDto);
		// Mock the behavior of the OrderDetailsService's getOrderDetailsByOrderNumber()
		// method
		when(orderDetailsService.getOrderDetailsByOrderNumber(orderNumber)).thenReturn(orderDetailsList);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orderdetails/{order_number}", orderNumber)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].orderNumber").value(123));

		// Verify that the OrderDetailsService's getOrderDetailsByOrderNumber() method
		// was called exactly once with the expected parameter
		verify(orderDetailsService, times(1)).getOrderDetailsByOrderNumber(orderNumber);
	}

	@Test
	public void testGetCountByProductCode() throws Exception {
		// Test data
		String productCode = "P123";
		Integer count = 5;
		responseDto.setMessage("Total count is " + count);
		// Mock the behavior of the OrderDetailsService's getCountByProductCode() method
		when(orderDetailsService.getCountByProductCode(productCode)).thenReturn(responseDto);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orderdetails/count/{product_code}", productCode)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		// Verify that the OrderDetailsService's getCountByProductCode() method was
		// called exactly once with the expected parameter
		verify(orderDetailsService, times(1)).getCountByProductCode(productCode);
	}

	@Test
	public void testGetMaxOrderDetails() throws Exception {

		// Add test data to the orderDetailsList
		Integer orderNumber = 123;
		List<OrderDetailsDto> orderDetailsList = new ArrayList<>();
		OrderDetailsDto detailsDto = new OrderDetailsDto();
		// Add test data to the orderDetailsList
		detailsDto.setOrderNumber(orderNumber);
		orderDetailsList.add(detailsDto);
		// Mock the behavior of the OrderDetailsService's getMaxOrderDetails() method
		when(orderDetailsService.getMaxOrderDetails()).thenReturn(orderDetailsList);

		// Perform the GET request and assertions
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/orderdetails/max/price").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].orderNumber").value(123));

		// Verify that the OrderDetailsService's getMaxOrderDetails() method was called
		// exactly once
		verify(orderDetailsService, times(1)).getMaxOrderDetails();
	}

	@Test
	public void testUpdateOrderDetailsById() throws Exception {

		// Create a sample ProductLinesDto object
		OrderDetailsDto orderDetailsDto = new OrderDetailsDto(123, "product1", 345, new BigDecimal("100.00"),
				(short) 3);

		responseDto.setMessage("Product details updated successfully");

		Mockito.when(orderDetailsService.updateOrderDetailsById("product1", 123, orderDetailsDto))
				.thenReturn(responseDto);

		// Perform the PUT request
		mockMvc.perform(MockMvcRequestBuilders
				.put("/api/v1/orderdetails/update/{order_number}/{product_code}", 123, "product1", orderDetailsDto)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(orderDetailsDto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		// Verify the service method invocation
		Mockito.verify(orderDetailsService, times(1)).updateOrderDetailsById("product1", 123, orderDetailsDto);
	}

	@Test
	public void testGetAllOrderDetails_Negative() throws Exception {
		List<OrderDetailsDto> orderDetailsList = new ArrayList<>();

		Mockito.when(orderDetailsService.getAllOrderDetails()).thenReturn(orderDetailsList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orderdetails/all_order_details"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

		Mockito.verify(orderDetailsService, times(1)).getAllOrderDetails();
	}

	@Test
	public void testSaveOrderDetails_Negative() throws Exception {
		OrderDetailsDto orderDetailsDto = new OrderDetailsDto(123, "product1", 345, new BigDecimal("100.00"),
				(short) 3);

		Mockito.when(orderDetailsService.saveOrderDetails(orderDetailsDto))
				.thenThrow(new OrderDetailsNotFoundException("Sorry cannot add OrderDetails"));

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/orderdetails/").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(orderDetailsDto))).andExpect(MockMvcResultMatchers.status().isNotFound());
		Mockito.verify(orderDetailsService, times(1)).saveOrderDetails(orderDetailsDto);
	}

	@Test
	public void testUpdateQuantityOrdered_Negative() throws Exception {
		Integer orderNumber = 1;
		String productCode = "ABC";
		Integer quantityOrdered = 10;
		Mockito.when(orderDetailsService.updateQuantityOrdered(orderNumber, productCode, quantityOrdered))
				.thenThrow(new OrderDetailsNotFoundException("Order details not found"));

		mockMvc.perform(
				MockMvcRequestBuilders.put("/api/v1/orderdetails/{order_number}/{product_code}/{quantity_orderd}",
						orderNumber, productCode, quantityOrdered))
				.andExpect(status().isNotFound());

		Mockito.verify(orderDetailsService).updateQuantityOrdered(orderNumber, productCode, quantityOrdered);
	}

	@Test
	public void testGetTotalofOrder_Negative() throws Exception {
		// Test data
		Integer orderNumber = 123;

		// Mock the behavior of the OrderDetailsService's getTotalofOrder() method
		when(orderDetailsService.getTotalofOrder(orderNumber))
				.thenThrow(new OrderDetailsNotFoundException("Order details not found"));

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orderdetails/get_total/{order_number}", orderNumber))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the OrderDetailsService's getTotalofOrder() method was called
		// exactly once with the expected parameter
		verify(orderDetailsService, times(1)).getTotalofOrder(orderNumber);
	}

	@Test
	public void testGetOrderDetailsByOrderNumber_Negative() throws Exception {
		// Test data
		Integer orderNumber = 123;

		// Mock the behavior of the OrderDetailsService's getOrderDetailsByOrderNumber()
		// method
		when(orderDetailsService.getOrderDetailsByOrderNumber(orderNumber))
				.thenThrow(new OrderDetailsNotFoundException("Order details not found"));

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orderdetails/{order_number}", orderNumber))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the OrderDetailsService's getOrderDetailsByOrderNumber() method
		// was called exactly once with the expected parameter
		verify(orderDetailsService, times(1)).getOrderDetailsByOrderNumber(orderNumber);
	}

	@Test
	public void testGetMaxOrderDetails_Negative() throws Exception {

		List<OrderDetailsDto> orderDetailsList = new ArrayList<>();

		// Mock the behavior of the OrderDetailsService's getMaxOrderDetails() method
		when(orderDetailsService.getMaxOrderDetails()).thenReturn(orderDetailsList);

		// Perform the GET request and assertions
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/orderdetails/max/price").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
		// Verify that the OrderDetailsService's getMaxOrderDetails() method was called
		// exactly once
		verify(orderDetailsService, times(1)).getMaxOrderDetails();
	}

	@Test
	public void testGetCountByProductCode_Negative() throws Exception {
		// Test data
		String productCode = "P123";

		// Mock the behavior of the OrderDetailsService's getCountByProductCode() method
		when(orderDetailsService.getCountByProductCode(productCode))
				.thenThrow(new OrderDetailsNotFoundException("Order details not found"));

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orderdetails/count/{product_code}", productCode))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the OrderDetailsService's getCountByProductCode() method was
		// called exactly once with the expected parameter
		verify(orderDetailsService, times(1)).getCountByProductCode(productCode);
	}

	@Test
	public void testUpdateOrderDetailsById_Negative() throws Exception {

		// Create a sample ProductLinesDto object
		OrderDetailsDto orderDetailsDto = new OrderDetailsDto(123, "product1", 345, new BigDecimal("100.00"),
				(short) 3);

		Mockito.when(orderDetailsService.updateOrderDetailsById("product1", 123, orderDetailsDto))
				.thenThrow(new OrderDetailsNotFoundException("Order details not found"));

		// Perform the PUT request
		mockMvc.perform(MockMvcRequestBuilders
				.put("/api/v1/orderdetails/update/{order_number}/{product_code}", 123, "product1", orderDetailsDto)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(orderDetailsDto)))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify the service method invocation
		Mockito.verify(orderDetailsService, times(1)).updateOrderDetailsById("product1", 123, orderDetailsDto);
	}

}
