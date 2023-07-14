package com.sprint.classicmodelsbussiness.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sprint.classicmodelsbussiness.ClassicModelsBussinessApp;
import com.sprint.classicmodelsbussiness.dto.OrderDto;
import com.sprint.classicmodelsbussiness.dto.ProductDto;
import com.sprint.classicmodelsbussiness.dto.ProductProductLinesDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.entity.ProductLines;
import com.sprint.classicmodelsbussiness.exception.OrderNotFoundException;
import com.sprint.classicmodelsbussiness.service.impl.OrderServiceImpl;

@RunWith(SpringRunner.class)

@SpringBootTest(classes = ClassicModelsBussinessApp.class)
@AutoConfigureMockMvc
@WithMockUser(username = "sanjair83", roles = "ADMIN")
public class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderServiceImpl orderService;

	@InjectMocks
	private OrderController orderController;
	private ResponseDto responseDto = new ResponseDto();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
	}

	@Test
	public void testSaveOrder() throws Exception {
		String orderDate = "2023-07-04";
		// Test data
		OrderDto orderDto = new OrderDto(1, LocalDate.parse(orderDate), LocalDate.now(), LocalDate.now(), "Shipped",
				"Test order", 1);
		// Set orderDto properties
		responseDto.setMessage("Order Created Successfully");
		// Mock the behavior of the OrderService's saveOrder() method
		when(orderService.saveOrder(orderDto)).thenReturn(responseDto);

		// Perform the POST request and assertions
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/orders/").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(orderDto))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		// Verify that the OrderService's saveOrder() method was called exactly once
		// with the expected parameter
		verify(orderService, times(1)).saveOrder(orderDto);
	}

	public String asJsonString(Object object) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testGetAllOrders() throws Exception {
		// Test data
		List<OrderDto> orderDtoList = new ArrayList<>();
		OrderDto orderDto = new OrderDto(1, LocalDate.now(), LocalDate.now(), LocalDate.now(), "Shipped", "Test order",
				1);
		orderDtoList.add(orderDto);

		// Mock the behavior of the OrderService's getAllOrders() method
		when(orderService.getAllOrders()).thenReturn(orderDtoList);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/all_orders").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].orderNumber", is(orderDto.getOrderNumber())));

		// Verify that the OrderService's getAllOrders() method was called exactly once
		verify(orderService, times(1)).getAllOrders();
	}

	@Test
	public void testUpdateShippedDate() throws Exception {
		// Test data
		Integer orderNumber = 1;
		String shippedDate = "2023-07-05";

		responseDto.setMessage("Shipped date updated successfully");
		// Mock the behavior of the OrderService's updateShippedDate() method
		when(orderService.updateShippedDate(orderNumber, shippedDate)).thenReturn(responseDto);

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders
				.put("/api/v1/orders/{order_number}/shipped_date/{shipped_date}", orderNumber, shippedDate)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		// Verify that the OrderService's updateShippedDate() method was called exactly
		// once with the expected parameters
		verify(orderService, times(1)).updateShippedDate(orderNumber, shippedDate);
	}

	@Test
	public void testUpdateOrderStatus() throws Exception {
		// Test data
		Integer orderNumber = 1;
		String status = "Shipped";

		responseDto.setMessage("Order status updated successfully");
		// Mock the behavior of the OrderService's updateOrderStatus() method
		when(orderService.updateOrderStatus(orderNumber, status)).thenReturn(responseDto);

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/orders/{order_number}/status/{status}", orderNumber, status)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		// Verify that the OrderService's updateOrderStatus() method was called exactly
		// once with the expected parameters
		verify(orderService, times(1)).updateOrderStatus(orderNumber, status);
	}

	@Test
	public void testGetOrdersByCustomerNumber() throws Exception {
		// Test data
		Integer customerNumber = 1;
		List<OrderDto> expectedOrders = new ArrayList<>();
		OrderDto order1 = new OrderDto(1, LocalDate.now(), LocalDate.now(), LocalDate.now(), "Shipped", "Comments", 1);
		expectedOrders.add(order1);

		// Mock the behavior of the OrderService's getOrdersByCustomerNumber() method
		when(orderService.getOrdersByCustomerNumber(customerNumber)).thenReturn(expectedOrders);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/customer_number/{customer_number}", customerNumber)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].orderNumber").value(order1.getOrderNumber()));

		// Verify that the OrderService's getOrdersByCustomerNumber() method was called
		// exactly once with the expected parameter
		verify(orderService, times(1)).getOrdersByCustomerNumber(customerNumber);
	}

	@Test
	public void testGetOrdersByOrderNumber() throws Exception {
		// Test data
		Integer orderNumber = 1;
		OrderDto expectedOrder = new OrderDto(1, LocalDate.now(), LocalDate.now(), LocalDate.now(), "Shipped",
				"Comments", 1);

		// Mock the behavior of the OrderService's getOrdersByOrderNumber() method
		when(orderService.getOrdersByOrderNumber(orderNumber)).thenReturn(expectedOrder);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/order_number/{order_number}", orderNumber)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.orderNumber").value(expectedOrder.getOrderNumber()));

		// Verify that the OrderService's getOrdersByOrderNumber() method was called
		// exactly once with the expected parameter
		verify(orderService, times(1)).getOrdersByOrderNumber(orderNumber);
	}

	@Test
	public void testGetOrdersByOrderDate() throws Exception {
		// Test data
		String orderDate = "2023-07-04";
		List<OrderDto> expectedOrders = new ArrayList<>();
		expectedOrders.add(new OrderDto(1, LocalDate.parse(orderDate), LocalDate.now(), LocalDate.now(), "Shipped",
				"Comments", 1));

		// Mock the behavior of the OrderService's getOrdersByOrderDate() method
		when(orderService.getOrdersByOrderDate(orderDate)).thenReturn(expectedOrders);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/order_date/{order_date}", orderDate)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].orderNumber")
						.value(expectedOrders.get(0).getOrderNumber()));

		// Verify that the OrderService's getOrdersByOrderDate() method was called
		// exactly once with the expected parameter
		verify(orderService, times(1)).getOrdersByOrderDate(orderDate);
	}

	@Test
	public void testGetOrdersByRequiredDate() throws Exception {
		// Test data
		String requiredDate = "2023-07-04";
		List<OrderDto> expectedOrders = new ArrayList<>();
		expectedOrders.add(new OrderDto(1, LocalDate.now(), LocalDate.parse(requiredDate), LocalDate.now(), "Shipped",
				"Comments", 1));

		// Mock the behavior of the OrderService's getOrdersByRequiredDate() method
		when(orderService.getOrdersByRequiredDate(requiredDate)).thenReturn(expectedOrders);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/required_date/{required_date}", requiredDate)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].orderNumber")
						.value(expectedOrders.get(0).getOrderNumber()));

		// Verify that the OrderService's getOrdersByRequiredDate() method was called
		// exactly once with the expected parameter
		verify(orderService, times(1)).getOrdersByRequiredDate(requiredDate);
	}

	@Test
	public void testGetOrdersByShippedDate() throws Exception {
		// Test data
		String shippedDate = "2023-07-04";
		List<OrderDto> expectedOrders = new ArrayList<>();
		expectedOrders.add(new OrderDto(1, LocalDate.now(), LocalDate.now(), LocalDate.parse(shippedDate), "Shipped",
				"Comments", 1));

		// Mock the behavior of the OrderService's getOrdersByShippedDate() method
		when(orderService.getOrdersByShippedDate(shippedDate)).thenReturn(expectedOrders);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/shipped_date/{shipped_date}", shippedDate)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].orderNumber")
						.value(expectedOrders.get(0).getOrderNumber()));

		// Verify that the OrderService's getOrdersByShippedDate() method was called
		// exactly once with the expected parameter
		verify(orderService, times(1)).getOrdersByShippedDate(shippedDate);
	}

	@Test
	public void testGetOrdersByOrderStatus() throws Exception {
		// Test data
		String status = "Shipped";
		List<OrderDto> expectedOrders = new ArrayList<>();
		expectedOrders.add(new OrderDto(1, LocalDate.now(), LocalDate.now(), LocalDate.now(), status, "Comments", 1));

		// Mock the behavior of the OrderService's getOrdersByOrderStatus() method
		when(orderService.getOrdersByOrderStatus(status)).thenReturn(expectedOrders);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/status/{status}", status)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].orderNumber")
						.value(expectedOrders.get(0).getOrderNumber()));

		// Verify that the OrderService's getOrdersByOrderStatus() method was called
		// exactly once with the expected parameter
		verify(orderService, times(1)).getOrdersByOrderStatus(status);
	}

	@Test
	public void testGetOrdersByOrderStatusForCustomer() throws Exception {
		// Test data
		Integer customerNumber = 1;
		String status = "Shipped";
		List<OrderDto> expectedOrders = new ArrayList<>();
		expectedOrders.add(
				new OrderDto(1, LocalDate.now(), LocalDate.now(), LocalDate.now(), status, "Comments", customerNumber));

		// Mock the behavior of the OrderService's getOrdersByOrderStatusForCustomer()
		// method
		when(orderService.getOrdersByOrderStatusForCustomer(customerNumber, status)).thenReturn(expectedOrders);

		// Perform the GET request and assertions
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/orders/{customer_number}/get/{status}", customerNumber, status)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isFound()).andExpect(MockMvcResultMatchers
						.jsonPath("$[0].orderNumber").value(expectedOrders.get(0).getOrderNumber()));

		// Verify that the OrderService's getOrdersByOrderStatusForCustomer() method was
		// called exactly once with the expected parameters
		verify(orderService, times(1)).getOrdersByOrderStatusForCustomer(customerNumber, status);
	}

	@Test
	public void testGetProductsByOrderNumber() throws Exception {
		// Test data
		Integer orderNumber = 1;
		List<ProductDto> expectedProducts = new ArrayList<>();
		expectedProducts.add(new ProductDto("P123", "Product 1", "Product Line 1", "Scale 1", "Vendor 1",
				"Description 1", (short) 10, BigDecimal.valueOf(100.00), BigDecimal.valueOf(150.00)));

		// Mock the behavior of the OrderService's getProductsByOrderNumber() method
		when(orderService.getProductsByOrderNumber(orderNumber)).thenReturn(expectedProducts);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/{order_id}/products", orderNumber)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].productCode")
						.value(expectedProducts.get(0).getProductCode()));

		// Verify that the OrderService's getProductsByOrderNumber() method was called
		// exactly once with the expected parameter
		verify(orderService, times(1)).getProductsByOrderNumber(orderNumber);
	}

	@Test
	public void testGetProductsNameByOrderNumber() throws Exception {
		// Test data
		Integer orderNumber = 1;
		List<String> expectedProductNames = Arrays.asList("Product 1", "Product 2", "Product 3");

		// Mock the behavior of the OrderService's getProductsNameByOrderNumber() method
		when(orderService.getProductsNameByOrderNumber(orderNumber)).thenReturn(expectedProductNames);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/{order_id}/product_names", orderNumber)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0]").value(expectedProductNames.get(0)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1]").value(expectedProductNames.get(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[2]").value(expectedProductNames.get(2)));

		// Verify that the OrderService's getProductsNameByOrderNumber() method was
		// called exactly once with the expected parameter
		verify(orderService, times(1)).getProductsNameByOrderNumber(orderNumber);
	}

	@Test
	public void testGetAllProducts() throws Exception {
		// Test data
		List<ProductDto> expectedProducts = Arrays.asList(new ProductDto("P1", "Product 1", "Line 1", "Scale 1",
				"Vendor 1", "Description 1", (short) 10, BigDecimal.valueOf(9.99), BigDecimal.valueOf(19.99)));

		// Mock the behavior of the OrderService's getAllProducts() method
		when(orderService.getAllProducts()).thenReturn(expectedProducts);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order/products").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isFound()).andExpect(MockMvcResultMatchers
						.jsonPath("$[0].productCode").value(expectedProducts.get(0).getProductCode()));
		// Verify that the OrderService's getAllProducts() method was called exactly
		// once
		verify(orderService, times(1)).getAllProducts();
	}

	@Test
	public void testGetOrdersByDelivered() throws Exception {
		// Test data
		String orderStatus = "Delivered";
		List<OrderDto> expectedOrders = Arrays.asList(new OrderDto(1, LocalDate.of(2023, 1, 1),
				LocalDate.of(2023, 1, 10), LocalDate.of(2023, 1, 15), orderStatus, "Comments 1", 1001));

		// Mock the behavior of the OrderService's getOrdersByDelivered() method
		when(orderService.getOrdersByDelivered(orderStatus)).thenReturn(expectedOrders);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/{order_status}/orders", orderStatus)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].orderNumber")
						.value(expectedOrders.get(0).getOrderNumber()));
		// Verify that the OrderService's getOrdersByDelivered() method was called
		// exactly once
		verify(orderService, times(1)).getOrdersByDelivered(orderStatus);
	}

	@Test
	public void testGetProductsForShippedDate() throws Exception {
		// Test data
		List<List<ProductProductLinesDto>> expectedProducts = Arrays
				.asList(Arrays.asList(new ProductProductLinesDto(new ProductDto(), new ProductLines())));
		// Mock the behavior of the OrderService's getProductsForShippedDate() method
		when(orderService.getProductsForShippedDate()).thenReturn(expectedProducts);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/product_and_product_line_details")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0][0].product.productCode")
						.value(expectedProducts.get(0).get(0).getProduct().getProductCode()));
		// Verify that the OrderService's getProductsForShippedDate() method was called
		// exactly once
		verify(orderService, times(1)).getProductsForShippedDate();
	}

	@Test
	public void testUpdateOrderById() throws Exception {
		// Mock request data
		Integer orderNumber = 1;
		OrderDto orderDto = new OrderDto(1, LocalDate.of(2003, 1, 1), LocalDate.now(), LocalDate.now(), "Shipped",
				"Test order", 1);
		// Mock the service response
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("Order details updated successfully");
		when(orderService.updateOrderById(orderNumber, orderDto)).thenReturn(responseDto);

		// Perform the PUT request
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/orders/update/{order_number}", orderNumber)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(orderDto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Order details updated successfully"));

		// Verify the service method invocation
		verify(orderService).updateOrderById(orderNumber, orderDto);
	}

	@Test
	public void testSaveOrder_Negative() throws Exception {

		// Test data
		OrderDto orderDto = new OrderDto(1, LocalDate.of(2003, 1, 1), LocalDate.now(), LocalDate.now(), "Shipped",
				"Test order", 1);

		// Mock the behavior of the OrderService's saveOrder() method
		when(orderService.saveOrder(orderDto)).thenThrow(new OrderNotFoundException("Sorry, cannot add order details"));

		// Perform the POST request and assertions
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/orders/").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(orderDto))).andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the OrderService's saveOrder() method was called exactly once
		// with the expected parameter
		verify(orderService, times(1)).saveOrder(orderDto);
	}

	@Test
	public void testGetAllOrders_Negative() throws Exception {
		// Test data
		List<OrderDto> orderDtoList = new ArrayList<>();

		// Mock the behavior of the OrderService's getAllOrders() method
		when(orderService.getAllOrders()).thenReturn(orderDtoList);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/all_orders").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

		// Verify that the OrderService's getAllOrders() method was called exactly once
		verify(orderService, times(1)).getAllOrders();
	}

	@Test
	public void testUpdateShippedDate_Negative() throws Exception {
		// Test data
		Integer orderNumber = 1;
		String shippedDate = "2023-07-05";

		// Mock the behavior of the OrderService's updateShippedDate() method
		when(orderService.updateShippedDate(orderNumber, shippedDate))
				.thenThrow(new OrderNotFoundException("Order details not found"));

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/orders/{order_number}/shipped_date/{shipped_date}",
				orderNumber, shippedDate)).andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the OrderService's updateShippedDate() method was called exactly
		// once with the expected parameters
		verify(orderService, times(1)).updateShippedDate(orderNumber, shippedDate);
	}

	@Test
	public void testUpdateOrderStatus_Negative() throws Exception {
		// Test data
		Integer orderNumber = 1;
		String status = "Shipped";

		// Mock the behavior of the OrderService's updateOrderStatus() method
		when(orderService.updateOrderStatus(orderNumber, status))
				.thenThrow(new OrderNotFoundException("Order details not found"));

		// Perform the PUT request and assertions
		mockMvc.perform(
				MockMvcRequestBuilders.put("/api/v1/orders/{order_number}/status/{status}", orderNumber, status))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the OrderService's updateOrderStatus() method was called exactly
		// once with the expected parameters
		verify(orderService, times(1)).updateOrderStatus(orderNumber, status);
	}

	@Test
	public void testGetOrdersByCustomerNumber_Negative() throws Exception {
		// Test data
		Integer customerNumber = 1;

		// Mock the behavior of the OrderService's getOrdersByCustomerNumber() method
		when(orderService.getOrdersByCustomerNumber(customerNumber))
				.thenThrow(new OrderNotFoundException("Customer details not found"));

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/customer_number/{customer_number}", customerNumber))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
		// Verify that the OrderService's getOrdersByCustomerNumber() method was called
		// exactly once with the expected parameter
		verify(orderService, times(1)).getOrdersByCustomerNumber(customerNumber);
	}

	@Test
	public void testGetOrdersByOrderNumber_Negative() throws Exception {
		// Test data
		Integer orderNumber = 1;

		// Mock the behavior of the OrderService's getOrdersByOrderNumber() method to
		// throw an exception
		when(orderService.getOrdersByOrderNumber(orderNumber))
				.thenThrow(new OrderNotFoundException("Order details not found"));

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/order_number/{order_number}", orderNumber)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the OrderService's getOrdersByOrderNumber() method was called
		// exactly once with the expected parameter
		verify(orderService, times(1)).getOrdersByOrderNumber(orderNumber);
	}

	@Test
	public void testGetOrdersByOrderDate_Negative() throws Exception {
		// Test data
		String orderDate = "2023-07-04";

		// Mock the behavior of the OrderService's getOrdersByOrderDate() method to
		// throw an exception
		when(orderService.getOrdersByOrderDate(orderDate))
				.thenThrow(new OrderNotFoundException("Orders not found for the specified order date"));

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/order_date/{order_date}", orderDate)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the OrderService's getOrdersByOrderDate() method was called
		// exactly once with the expected parameter
		verify(orderService, times(1)).getOrdersByOrderDate(orderDate);
	}

	@Test
	public void testGetOrdersByRequiredDate_Negative() throws Exception {
		// Test data
		String requiredDate = "2023-07-04";

		// Mock the behavior of the OrderService's getOrdersByRequiredDate() method to
		// throw an exception
		when(orderService.getOrdersByRequiredDate(requiredDate))
				.thenThrow(new OrderNotFoundException("Orders not found for the specified required date"));

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/required_date/{required_date}", requiredDate)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the OrderService's getOrdersByRequiredDate() method was called
		// exactly once with the expected parameter
		verify(orderService, times(1)).getOrdersByRequiredDate(requiredDate);
	}

	@Test
	public void testGetOrdersByShippedDate_Negative() throws Exception {
		// Test data
		String shippedDate = "2023-07-04";

		// Mock the behavior of the OrderService's getOrdersByShippedDate() method to
		// throw an exception
		when(orderService.getOrdersByShippedDate(shippedDate))
				.thenThrow(new OrderNotFoundException("Orders not found for the specified shipped date"));

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/shipped_date/{shipped_date}", shippedDate)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the OrderService's getOrdersByShippedDate() method was called
		// exactly once with the expected parameter
		verify(orderService, times(1)).getOrdersByShippedDate(shippedDate);
	}

	@Test
	public void testGetOrdersByOrderStatus_Negative() throws Exception {
		// Test data
		String status = "Shipped";

		// Mock the behavior of the OrderService's getOrdersByOrderStatus() method to
		// throw an exception
		when(orderService.getOrdersByOrderStatus(status))
				.thenThrow(new OrderNotFoundException("Orders not found for the specified status"));

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/status/{status}", status)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the OrderService's getOrdersByOrderStatus() method was called
		// exactly once with the expected parameter
		verify(orderService, times(1)).getOrdersByOrderStatus(status);
	}

	@Test
	public void testGetOrdersByOrderStatusForCustomer_Negative() throws Exception {
		// Test data
		Integer customerNumber = 1;
		String status = "Shipped";

		// Mock the behavior of the OrderService's getOrdersByOrderStatusForCustomer()
		// method to throw an exception
		when(orderService.getOrdersByOrderStatusForCustomer(customerNumber, status))
				.thenThrow(new OrderNotFoundException("Orders not found for the specified status and customer"));

		// Perform the GET request and assertions
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/orders/{customer_number}/get/{status}", customerNumber, status)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the OrderService's getOrdersByOrderStatusForCustomer() method was
		// called exactly once with the expected parameters
		verify(orderService, times(1)).getOrdersByOrderStatusForCustomer(customerNumber, status);
	}

	@Test
	public void testGetOrdersByDelivered_Negative() throws Exception {
		// Test data
		String orderStatus = "Delivered";

		// Mock the behavior of the OrderService's getOrdersByDelivered() method to
		// throw an exception
		when(orderService.getOrdersByDelivered(orderStatus))
				.thenThrow(new OrderNotFoundException("Orders not found for the specified delivered status"));

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/{order_status}/orders", orderStatus)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the OrderService's getOrdersByDelivered() method was called
		// exactly once
		verify(orderService, times(1)).getOrdersByDelivered(orderStatus);
	}

	@Test
	public void testGetProductsForShippedDate_Negative() throws Exception {
		// Test data
		List<List<ProductProductLinesDto>> expectedProducts = new ArrayList<>();
		;

		// Mock the behavior of the OrderService's getProductsForShippedDate() method to
		// throw an exception
		when(orderService.getProductsForShippedDate()).thenReturn(expectedProducts);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/product_and_product_line_details")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

		// Verify that the OrderService's getProductsForShippedDate() method was called
		// exactly once
		verify(orderService, times(1)).getProductsForShippedDate();
	}

	@Test
	public void testGetProductsByOrderNumber_Negative() throws Exception {
		// Test data
		Integer orderNumber = 1;

		// Mock the behavior of the OrderService's getProductsByOrderNumber() method to
		// throw an exception
		when(orderService.getProductsByOrderNumber(orderNumber))
				.thenThrow(new OrderNotFoundException("Products not found for the specified order number"));

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/{order_id}/products", orderNumber)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the OrderService's getProductsByOrderNumber() method was called
		// exactly once with the expected parameter
		verify(orderService, times(1)).getProductsByOrderNumber(orderNumber);
	}

	@Test
	public void testGetProductsNameByOrderNumber_Negative() throws Exception {
		// Test data
		Integer orderNumber = 1;

		// Mock the behavior of the OrderService's getProductsNameByOrderNumber() method
		// to throw an exception
		when(orderService.getProductsNameByOrderNumber(orderNumber))
				.thenThrow(new OrderNotFoundException("Product names not found for the specified order number"));

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/{order_id}/product_names", orderNumber)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the OrderService's getProductsNameByOrderNumber() method was
		// called exactly once with the expected parameter
		verify(orderService, times(1)).getProductsNameByOrderNumber(orderNumber);
	}

	@Test
	public void testGetAllProducts_Negative() throws Exception {
		// Test data
		List<ProductDto> expectedProducts = new ArrayList<>();

		// Mock the behavior of the OrderService's getAllProducts() method to throw an
		// exception
		when(orderService.getAllProducts()).thenReturn(expectedProducts);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order/products").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

		// Verify that the OrderService's getAllProducts() method was called exactly
		// once
		verify(orderService, times(1)).getAllProducts();
	}

	@Test
	public void testUpdateOrderById_Negative() throws Exception {
		// Mock request data
		Integer orderNumber = 1;
		OrderDto orderDto = new OrderDto(1, LocalDate.of(2003, 1, 1), LocalDate.now(), LocalDate.now(), "Shipped",
				"Test order", 1);
		when(orderService.updateOrderById(orderNumber, orderDto))
				.thenThrow(new OrderNotFoundException("Products not found"));

		// Perform the PUT request
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/orders/update/{order_number}", orderNumber)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(orderDto)))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify the service method invocation
		verify(orderService).updateOrderById(orderNumber, orderDto);
	}
}
