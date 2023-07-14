package com.sprint.classicmodelsbussiness.controller;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.classicmodelsbussiness.ClassicModelsBussinessApp;
import com.sprint.classicmodelsbussiness.dto.ProductLinesDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.exception.ProductLinesNotFoundException;
import com.sprint.classicmodelsbussiness.repository.ProductLinesRepository;
import com.sprint.classicmodelsbussiness.service.impl.ProductLinesServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClassicModelsBussinessApp.class)
@AutoConfigureMockMvc

@ComponentScan("com.sprint")
@WithMockUser(username = "sanjair83", roles = "ADMIN")
public class ProductLinesControllerTest {

	@MockBean

	private ProductLinesServiceImpl productLinesService;

	@InjectMocks
	private ProductLinesController productLinesController;

	ProductLinesRepository linesRepository;
	@Autowired
	private MockMvc mockMvc;
	private ResponseDto responseDto = new ResponseDto();

	@BeforeEach
	public void setup() {
//    	MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productLinesController).build();
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
	public void testGetAllProductLines() throws Exception {

		ProductLinesDto productLine1 = new ProductLinesDto("line1", "Text Description 1", "HTML Description 1",
				"image1.jpg");

		List<ProductLinesDto> productLinesList = new ArrayList<>();

		productLinesList.add(productLine1);
		Mockito.when(productLinesService.getAllProductLines()).thenReturn(productLinesList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product_lines/all_productline_details")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].productLine").value("line1"));

		Mockito.verify(productLinesService, times(1)).getAllProductLines();
	}

	@Test
	public void testSaveProductLines() throws Exception {
		// Create a sample ProductLinesDto object
		ProductLinesDto productLine = new ProductLinesDto("line1", "Text Description 1", "HTML Description 1",
				"image1.jpg");

		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("product line details added successfully");

		Mockito.when(productLinesService.saveProductLines(productLine)).thenReturn(responseDto);

		// Perform the POST request
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product_lines/").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(productLine))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		// Verify the service method invocation
		Mockito.verify(productLinesService, times(1)).saveProductLines(productLine);
	}

	@Test
	public void testUpdateTextDescription() throws Exception {
		// Mock request data
		String productLine = "line1";
		String textDescription = "Updated Text Description";
		responseDto.setMessage("Product's text description updated successfully");
		// Mock the service response
		Mockito.when(productLinesService.updateTextDescription(productLine, textDescription)).thenReturn(responseDto);

		// Perform the PUT request
		mockMvc.perform(MockMvcRequestBuilders.put(
				"/api/v1/product_lines/product_line/{product_line}/text_description/ {text_description}", productLine,
				textDescription)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		// Verify the service method invocation
		Mockito.verify(productLinesService).updateTextDescription(productLine, textDescription);
	}

	@Test
	public void testGetProductLineByTextDescription() throws Exception {
		String textDescription = "Test Description";
		ProductLinesDto productLine = new ProductLinesDto();
		productLine.setTextDescription(textDescription);
		List<ProductLinesDto> productLinesDtos = new ArrayList<>();
		productLinesDtos.add(productLine);
		Mockito.when(productLinesService.getProductLineByTextDescription(textDescription)).thenReturn(productLinesDtos);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/product_lines/text_description/{text_description}", textDescription)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].textDescription").value(textDescription));
		// Verify that the service method was called
		Mockito.verify(productLinesService, times(1)).getProductLineByTextDescription(textDescription);
	}

	@Test
	public void testgetProductLineById() throws Exception {
		String product_line = "Test classic";
		ProductLinesDto productLineDto = new ProductLinesDto();
		productLineDto.setProductLine(product_line);

		Mockito.when(productLinesService.getProductLineById(product_line)).thenReturn(productLineDto);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product_lines/product_line/{product_line}", product_line)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.productLine").value(product_line));
		// Verify that the service method was called
		Mockito.verify(productLinesService, times(1)).getProductLineById(product_line);
	}

	@Test
	public void testUpdateProductLineById() throws Exception {
		String productLine = "line1";
		// Create a sample ProductLinesDto object
		ProductLinesDto productLineDto = new ProductLinesDto(productLine, "Text Description 1", "HTML Description 1",
				"image1.jpg");

		responseDto.setMessage("Product details updated successfully");

		Mockito.when(productLinesService.updateProductLineById(productLine, productLineDto)).thenReturn(responseDto);

		// Perform the PUT request
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/product_lines/update/{product_line}", productLine)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(productLineDto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.message").value(responseDto.getMessage()));

		// Verify the service method invocation
		Mockito.verify(productLinesService, times(1)).updateProductLineById(productLine, productLineDto);
	}

	@Test
	public void testgetProductLineById_Negative() throws Exception {
		String product_line = "Test classic";
		ProductLinesDto productLineDto = new ProductLinesDto();
		productLineDto.setProductLine(product_line);

		Mockito.when(productLinesService.getProductLineById(product_line))
				.thenThrow(new ProductLinesNotFoundException("ProductLine Not Found"));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product_lines/product_line/{product_line}", product_line))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the service method was called
		Mockito.verify(productLinesService, times(1)).getProductLineById(product_line);
	}

	@Test
	public void testSaveProductLines_Negative() throws Exception {
		// Create a sample ProductLinesDto object
		ProductLinesDto productLine = new ProductLinesDto("line1", "Text Description 1", "HTML Description 1",
				"image1.jpg");

		Mockito.when(productLinesService.saveProductLines(productLine))
				.thenThrow(new ProductLinesNotFoundException("Cannot add ProductLine details"));

		// Perform the POST request
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product_lines/").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(productLine))).andExpect(status().isNotFound());

		// Verify the service method invocation
		Mockito.verify(productLinesService, times(1)).saveProductLines(productLine);
	}

	@Test
	public void testGetAllProductLines_Negative() throws Exception {

		List<ProductLinesDto> productLinesList = new ArrayList<>();
		Mockito.when(productLinesService.getAllProductLines()).thenReturn(productLinesList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product_lines/all_productline_details")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

		Mockito.verify(productLinesService, times(1)).getAllProductLines();
	}

	@Test
	public void testUpdateTextDescription_Negative() throws Exception {
		// Mock request data
		String productLine = "line1";
		String textDescription = "Updated Text Description";

		// Mock the service response
		Mockito.when(productLinesService.updateTextDescription(productLine, textDescription))
				.thenThrow(new ProductLinesNotFoundException("ProductLine Not Found"));

		// Perform the PUT request
		mockMvc.perform(MockMvcRequestBuilders.put(
				"/api/v1/product_lines/product_line/{product_line}/text_description/ {text_description}", productLine,
				textDescription)).andExpect(status().isNotFound());

		// Verify the service method invocation
		Mockito.verify(productLinesService).updateTextDescription(productLine, textDescription);
	}

	@Test
	public void testGetProductLineByTextDescription_Negative() throws Exception {
		String textDescription = "Test Description";

		Mockito.when(productLinesService.getProductLineByTextDescription(textDescription))
				.thenThrow(new ProductLinesNotFoundException("ProductLine Not Found"));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product_lines/text_description/{text_description}",
				textDescription)).andExpect(status().isNotFound());
		// Verify that the service method was called
		Mockito.verify(productLinesService, times(1)).getProductLineByTextDescription(textDescription);
	}

	@Test
	public void testUpdateProductLineById_Negative() throws Exception {
		String productLine = "line";
		// Create a sample ProductLinesDto object
		ProductLinesDto productLineDto = new ProductLinesDto(productLine, "Text Description 1", "HTML Description 1",
				"image1.jpg");

		Mockito.when(productLinesService.updateProductLineById(productLine, productLineDto))
				.thenThrow(new ProductLinesNotFoundException("Product not found."));

		// Perform the PUT request
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/product_lines/update/{product_line}", productLine)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(productLineDto)))
				.andExpect(status().isNotFound());
		// Verify the service method invocation
		Mockito.verify(productLinesService, times(1)).updateProductLineById(productLine, productLineDto);
	}
}
