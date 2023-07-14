package com.sprint.classicmodelsbussiness.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.classicmodelsbussiness.ClassicModelsBussinessApp;
import com.sprint.classicmodelsbussiness.dto.ProductDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.exception.ProductNotFoundException;
import com.sprint.classicmodelsbussiness.service.impl.ProductServiceImpl;

@RunWith(SpringRunner.class)

@SpringBootTest(classes = ClassicModelsBussinessApp.class)
@AutoConfigureMockMvc

@ComponentScan("com.sprint")
@WithMockUser(username = "sanjair83", roles = "ADMIN")
public class ProductControllerTest {

	@MockBean
	private ProductServiceImpl productService;

	@InjectMocks
	private ProductController productController;
	@Autowired
	private MockMvc mockMvc;
	private ResponseDto responseDto = new ResponseDto();

	@Test
	public void testGetAllProducts() throws Exception {

		List<ProductDto> products = new ArrayList<>();
		products.add(new ProductDto("P001", "Product 1", "Line 1", "Scale 1", "Vendor 1", "Description 1", (short) 10,
				new BigDecimal("100.00"), new BigDecimal("150.00")));

		when(productService.getAllProducts()).thenReturn(products);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/all_product_details")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].productCode").value("P001"));
		verify(productService, times(1)).getAllProducts();
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
	public void testSaveProduct() throws Exception {
		ProductDto productToSave = new ProductDto("P001", "Product 1", "Line 1", "Scale 1", "Vendor 1", "Description 1",
				(short) 10, new BigDecimal("100.00"), new BigDecimal("150.00"));
		responseDto.setMessage("Product details added successfully");
		when(productService.saveProduct(productToSave)).thenReturn(responseDto);

		// Perform the POST request and assertions
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products/").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(productToSave))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));
		verify(productService, times(1)).saveProduct(productToSave);
	}

	@Test
	public void testUpdateBuyPrice() throws Exception {
		// Test data
		String productCode = "P001";
		BigDecimal buyPrice = BigDecimal.valueOf(20.0);

		// Create a new ProductDto object
		ProductDto productDto = new ProductDto();
		productDto.setProductCode(productCode);
		productDto.setBuyPrice(buyPrice);
		responseDto.setMessage("Product's buy price updated successfully");
		// Mocking the ProductService's updateBuyPrice() method
		when(productService.updateBuyPrice(productCode, buyPrice)).thenReturn(responseDto);

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders
				.put("/api/v1/products/update_buy_price/{product_code}/{buy_price}", productCode, buyPrice)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));
		verify(productService, times(1)).updateBuyPrice(productCode, buyPrice);
	}

	@Test
	public void testUpdateStock() throws Exception {
		// Test data
		String productCode = "P001";
		Short quantityInStock = 50;
		responseDto.setMessage("Product's quantity  updated successfully");
		// Mock the behavior of the ProductService's updateStock() method
		when(productService.updateStock(productCode, quantityInStock)).thenReturn(responseDto);

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders
				.put("/api/v1/products/update_stock/{product_code}/{quantity_in_stock}", productCode, quantityInStock)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		// Verify that the ProductService's updateStock() method was called exactly once
		// with the expected parameters
		verify(productService, times(1)).updateStock(productCode, quantityInStock);
	}

	@Test
	public void testUpdateMsrp() throws Exception {
		// Test data
		String productCode = "P001";
		BigDecimal msrp = new BigDecimal("100.00");
		responseDto.setMessage("Product's MSRP  updated successfully");
		// Mock the behavior of the ProductService's updateMsrp() method
		when(productService.updateMsrp(productCode, msrp)).thenReturn(responseDto);

		// Perform the PUT request and assertions
		mockMvc.perform(
				MockMvcRequestBuilders.put("/api/v1/products/update_msrp/{product_code}/{msrp}", productCode, msrp)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		// Verify that the ProductService's updateMsrp() method was called exactly once
		// with the expected parameters
		verify(productService, times(1)).updateMsrp(productCode, msrp);
	}

	@Test
	public void testUpdateVendor() throws Exception {
		// Test data
		String productCode = "P001";
		String productVendor = "Vendor XYZ";
		responseDto.setMessage("Product's vendor  updated successfully");
		// Mock the behavior of the ProductService's updateVendor() method
		when(productService.updateVendor(productCode, productVendor)).thenReturn(responseDto);

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders
				.put("/api/v1/products/update_vendor/{product_code}/{product_vendor}", productCode, productVendor)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		// Verify that the ProductService's updateVendor() method was called exactly
		// once with the expected parameters
		verify(productService, times(1)).updateVendor(productCode, productVendor);
	}

	@Test
	public void testUpdateScale() throws Exception {
		// Test data
		String productCode = "P001";
		String productScale = "1:10";
		responseDto.setMessage("Product's scale updated successfully");
		// Mock the behavior of the ProductService's updateScale() method
		when(productService.updateScale(productCode, productScale)).thenReturn(responseDto);

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders
				.put("/api/v1/products/update_scale/{product_code}/{product_scale}", productCode, productScale)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		// Verify that the ProductService's updateScale() method was called exactly once
		// with the expected parameters
		verify(productService, times(1)).updateScale(productCode, productScale);
	}

	@Test
	public void testUpdateName() throws Exception {
		// Test data
		String productCode = "P001";
		String productName = "New Product Name";
		responseDto.setMessage("Product's name updated successfully");
		// Mock the behavior of the ProductService's updateName() method
		when(productService.updateName(productCode, productName)).thenReturn(responseDto);

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders
				.put("/api/v1/products/update_name/{product_code}/{product_name}", productCode, productName)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		// Verify that the ProductService's updateName() method was called exactly once
		// with the expected parameters
		verify(productService, times(1)).updateName(productCode, productName);
	}

	@Test
	public void testGetProductByCode() throws Exception {
		// Test data
		String productCode = "P001";

		// Create a mock ProductDto
		ProductDto productDto = new ProductDto();
		productDto.setProductCode(productCode);

		// Mocking the ProductService's getProductByCode() method
		when(productService.getProductByCode(productCode)).thenReturn(productDto);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/getby_code/{product_code}", productCode)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.productCode").value(productCode));

		// Assert other properties as needed
		verify(productService, times(1)).getProductByCode(productCode);
	}

	@Test
	public void testGetProductByName() throws Exception {
		// Test data
		String productName = "Test Product";
		List<ProductDto> products = new ArrayList<>();
		ProductDto product = new ProductDto();
		product.setProductName(productName);
		products.add(product);

		// Mock the behavior of the ProductService's getProductByName() method
		when(productService.getProductByName(productName)).thenReturn(products);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/getby_name/{product_name}", productName)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].productName").value(productName));

		// Verify that the ProductService's getProductByName() method was called exactly
		// once with the expected parameter
		verify(productService, times(1)).getProductByName(productName);
	}

	@Test
	public void testGetProductByScale() throws Exception {
		// Test data
		String productScale = "1:10";
		List<ProductDto> products = new ArrayList<>();
		ProductDto product = new ProductDto();
		product.setProductScale(productScale);
		products.add(product);

		// Mock the behavior of the ProductService's getProductByScale() method
		when(productService.getProductByScale(productScale)).thenReturn(products);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/getby_scale/{product_scale}", productScale)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].productScale").value(productScale));

		// Verify that the ProductService's getProductByScale() method was called
		// exactly once with the expected parameter
		verify(productService, times(1)).getProductByScale(productScale);
	}

	@Test
	public void testGetProductByVendor() throws Exception {
		// Test data
		String productVendor = "ABC Company";
		List<ProductDto> products = new ArrayList<>();
		ProductDto product = new ProductDto();
		product.setProductVendor(productVendor);
		products.add(product);

		// Mock the behavior of the ProductService's getProductByVendor() method
		when(productService.getProductByVendor(productVendor)).thenReturn(products);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/getby_vendor/{product_vendor}", productVendor)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].productVendor").value(productVendor));

		// Verify that the ProductService's getProductByVendor() method was called
		// exactly once with the expected parameter
		verify(productService, times(1)).getProductByVendor(productVendor);
	}

	@Test
	public void testGetOrderedQuantity() throws Exception {
		// Test data
		String productCode = "P123";
		int orderedQuantity = 10;
		responseDto.setMessage("Total ordered quantity: " + orderedQuantity);
		// Mock the behavior of the ProductService's getOrderedQuantity() method
		when(productService.getOrderedQuantity(productCode)).thenReturn(orderedQuantity);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/{product_code}/total_ordered_qty", productCode)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		// Verify that the ProductService's getOrderedQuantity() method was called
		// exactly once with the expected parameter
		verify(productService, times(1)).getOrderedQuantity(productCode);
	}

	@Test
	public void testGetTotalSaleForProduct() throws Exception {
		// Test data
		String productCode = "P123";
		String totalSale = "$1000.00";
		responseDto.setMessage("total sale of product is Rs " + totalSale);
		// Mock the behavior of the ProductService's getTotalSaleForProduct() method
		when(productService.getTotalSaleForProduct(productCode)).thenReturn(responseDto);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/{product_code}/total_sale", productCode)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		// Verify that the ProductService's getTotalSaleForProduct() method was called
		// exactly once with the expected parameter
		verify(productService, times(1)).getTotalSaleForProduct(productCode);
	}

	@Test
	public void testGetTotalSale() throws Exception {
		// Test data
		String totalSale = "$5000.00";
		responseDto.setMessage("total sale of All product is Rs " + totalSale);
		// Mock the behavior of the ProductService's getTotalSale() method
		when(productService.getTotalSale()).thenReturn(responseDto);

		// Perform the GET request and assertions
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/products/total_sale").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		// Verify that the ProductService's getTotalSale() method was called exactly
		// once
		verify(productService, times(1)).getTotalSale();
	}

	@Test
	public void testGetHighDemandproduct() throws Exception {
		// Test data
		ProductDto highDemandProduct = new ProductDto("P001", "High Demand Product", "Product Line", "Product Scale",
				"Product Vendor", "Product Description", (short) 100, new BigDecimal("50.00"),
				new BigDecimal("100.00"));

		// Mock the behavior of the ProductService's getHighDemandproduct() method
		when(productService.getHighDemandproduct()).thenReturn(highDemandProduct);

		// Perform the GET request and assertions
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/products/product_details").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.productCode").value(highDemandProduct.getProductCode()));

		// Verify that the ProductService's getHighDemandproduct() method was called
		// exactly once
		verify(productService, times(1)).getHighDemandproduct();
	}

	@Test
	public void testGetAllProducts_Negative() throws Exception {

		List<ProductDto> products = new ArrayList<>();
		when(productService.getAllProducts()).thenReturn(products);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/all_product_details")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
		verify(productService, times(1)).getAllProducts();
	}

	@Test
	public void testSaveProduct_Negative() throws Exception {
		ProductDto productToSave = new ProductDto("P001", "Product 1", "Line 1", "Scale 1", "Vendor 1", "Description 1",
				(short) 10, new BigDecimal("100.00"), new BigDecimal("150.00"));

		when(productService.saveProduct(productToSave))
				.thenThrow(new ProductNotFoundException("Cannot add product details"));

		// Perform the POST request and assertions
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products/").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(productToSave))).andExpect(MockMvcResultMatchers.status().isNotFound());

		verify(productService, times(1)).saveProduct(productToSave);
	}

	@Test
	public void testUpdateBuyPrice_Negative() throws Exception {
		// Test data
		String productCode = "P001";
		BigDecimal buyPrice = BigDecimal.valueOf(20.0);

		// Mocking the ProductService's updateBuyPrice() method
		when(productService.updateBuyPrice(productCode, buyPrice))
				.thenThrow(new ProductNotFoundException("Product details not found"));

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/update_buy_price/{product_code}/{buy_price}",
				productCode, buyPrice)).andExpect(MockMvcResultMatchers.status().isNotFound());

		verify(productService, times(1)).updateBuyPrice(productCode, buyPrice);
	}

	@Test
	public void testUpdateStock_Negative() throws Exception {
		// Test data
		String productCode = "P001";
		Short quantityInStock = 50;

		// Mock the behavior of the ProductService's updateStock() method
		when(productService.updateStock(productCode, quantityInStock))
				.thenThrow(new ProductNotFoundException("Product details not found"));

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/update_stock/{product_code}/{quantity_in_stock}",
				productCode, quantityInStock)).andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the ProductService's updateStock() method was called exactly once
		// with the expected parameters
		verify(productService, times(1)).updateStock(productCode, quantityInStock);
	}

	@Test
	public void testUpdateMsrp_Negative() throws Exception {
		// Test data
		String productCode = "P001";
		BigDecimal msrp = new BigDecimal("100.00");
		// Mock the behavior of the ProductService's updateMsrp() method
		when(productService.updateMsrp(productCode, msrp))
				.thenThrow(new ProductNotFoundException("Product details not found"));

		// Perform the PUT request and assertions
		mockMvc.perform(
				MockMvcRequestBuilders.put("/api/v1/products/update_msrp/{product_code}/{msrp}", productCode, msrp))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the ProductService's updateMsrp() method was called exactly once
		// with the expected parameters
		verify(productService, times(1)).updateMsrp(productCode, msrp);
	}

	@Test
	public void testUpdateVendor_Negative() throws Exception {
		// Test data
		String productCode = "P001";
		String productVendor = "Vendor XYZ";
		// Mock the behavior of the ProductService's updateVendor() method
		when(productService.updateVendor(productCode, productVendor))
				.thenThrow(new ProductNotFoundException("Product details not found"));

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/update_vendor/{product_code}/{product_vendor}",
				productCode, productVendor)).andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the ProductService's updateVendor() method was called exactly
		// once with the expected parameters
		verify(productService, times(1)).updateVendor(productCode, productVendor);
	}

	@Test
	public void testUpdateScale_Negative() throws Exception {
		// Test data
		String productCode = "P001";
		String productScale = "1:10";
		// Mock the behavior of the ProductService's updateScale() method
		when(productService.updateScale(productCode, productScale))
				.thenThrow(new ProductNotFoundException("Product details not found"));

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/update_scale/{product_code}/{product_scale}",
				productCode, productScale)).andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the ProductService's updateScale() method was called exactly once
		// with the expected parameters
		verify(productService, times(1)).updateScale(productCode, productScale);
	}

	@Test
	public void testUpdateName_Negative() throws Exception {
		// Test data
		String productCode = "P001";
		String productName = "New Product Name";
		// Mock the behavior of the ProductService's updateName() method
		when(productService.updateName(productCode, productName))
				.thenThrow(new ProductNotFoundException("Product details not found"));

		// Perform the PUT request and assertions
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/update_name/{product_code}/{product_name}",
				productCode, productName)).andExpect(MockMvcResultMatchers.status().isNotFound());
		// Verify that the ProductService's updateName() method was called exactly once
		// with the expected parameters
		verify(productService, times(1)).updateName(productCode, productName);
	}

	@Test
	public void testGetProductByCode_Negative() throws Exception {
		// Test data
		String productCode = "P001";

		// Mocking the ProductService's getProductByCode() method
		when(productService.getProductByCode(productCode))
				.thenThrow(new ProductNotFoundException("Product details not found"));

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/getby_code/{product_code}", productCode))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Assert other properties as needed
		verify(productService, times(1)).getProductByCode(productCode);
	}

	@Test
	public void testGetProductByName_Negative() throws Exception {
		// Test data
		String productName = "Test Product";
		List<ProductDto> products = new ArrayList<>();

		// Mock the behavior of the ProductService's getProductByName() method
		when(productService.getProductByName(productName)).thenReturn(products);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/getby_name/{product_name}", productName)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

		// Verify that the ProductService's getProductByName() method was called exactly
		// once with the expected parameter
		verify(productService, times(1)).getProductByName(productName);
	}

	@Test
	public void testGetProductByScale_Negative() throws Exception {
		// Test data
		String productScale = "1:10";
		List<ProductDto> products = new ArrayList<>();

		// Mock the behavior of the ProductService's getProductByScale() method
		when(productService.getProductByScale(productScale)).thenReturn(products);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/getby_scale/{product_scale}", productScale)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

		// Verify that the ProductService's getProductByScale() method was called
		// exactly once with the expected parameter
		verify(productService, times(1)).getProductByScale(productScale);
	}

	@Test
	public void testGetProductByVendor_Negative() throws Exception {
		// Test data
		String productVendor = "ABC Company";
		List<ProductDto> products = new ArrayList<>();

		// Mock the behavior of the ProductService's getProductByVendor() method
		when(productService.getProductByVendor(productVendor)).thenReturn(products);

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/getby_vendor/{product_vendor}", productVendor)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

		// Verify that the ProductService's getProductByVendor() method was called
		// exactly once with the expected parameter
		verify(productService, times(1)).getProductByVendor(productVendor);
	}

//
	@Test
	public void testGetOrderedQuantity_Negative() throws Exception {
		// Test data
		String productCode = "P123";

		// Mock the behavior of the ProductService's getOrderedQuantity() method
		when(productService.getOrderedQuantity(productCode))
				.thenThrow(new ProductNotFoundException("Product details not found"));

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/{product_code}/total_ordered_qty", productCode))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the ProductService's getOrderedQuantity() method was called
		// exactly once with the expected parameter
		verify(productService, times(1)).getOrderedQuantity(productCode);
	}

	@Test
	public void testGetTotalSaleForProduct_Negative() throws Exception {
		// Test data
		String productCode = "P123";
		// Mock the behavior of the ProductService's getTotalSaleForProduct() method
		when(productService.getTotalSaleForProduct(productCode))
				.thenThrow(new ProductNotFoundException("Product details not found"));

		// Perform the GET request and assertions
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/{product_code}/total_sale", productCode))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the ProductService's getTotalSaleForProduct() method was called
		// exactly once with the expected parameter
		verify(productService, times(1)).getTotalSaleForProduct(productCode);
	}

}