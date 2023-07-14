package com.sprint.classicmodelsbussiness.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.classicmodelsbussiness.dto.ProductDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.service.ProductService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;

	// Retrieve all products
	@GetMapping("v1/products/all_product_details")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		List<ProductDto> prodDto = productService.getAllProducts();

		return new ResponseEntity<List<ProductDto>>(prodDto, HttpStatus.OK);
	}

	// Save a new product
	@PostMapping("v1/products/")
	public ResponseEntity<ResponseDto> saveProduct(@Valid @RequestBody ProductDto product) {
		ResponseDto prodDto = productService.saveProduct(product);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}

	// Update the buy price of a product
	@PutMapping("v1/products/update_buy_price/{product_code}/{buy_price}")
	public ResponseEntity<ResponseDto> updateBuyPrice(@Valid @PathVariable String product_code,
			@Valid @PathVariable BigDecimal buy_price) {
		ResponseDto prodDto = productService.updateBuyPrice(product_code, buy_price);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}

	// Update the stock quantity of a product
	@PutMapping("v1/products/update_stock/{product_code}/{quantity_in_stock}")
	public ResponseEntity<ResponseDto> updateStock(@Valid @PathVariable String product_code,
			@PathVariable Short quantity_in_stock) {
		ResponseDto prodDto = productService.updateStock(product_code, quantity_in_stock);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}

	// Update the MSRP (Manufacturer's Suggested Retail Price) of a product
	@PutMapping("v1/products/update_msrp/{product_code}/{msrp}")
	public ResponseEntity<ResponseDto> updateMsrp(@Valid @PathVariable String product_code,
			@PathVariable BigDecimal msrp) {
		ResponseDto prodDto = productService.updateMsrp(product_code, msrp);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}

	// Update the vendor of a product
	@PutMapping("v1/products/update_vendor/{product_code}/{product_vendor}")
	public ResponseEntity<ResponseDto> updateVendor(@Valid @PathVariable String product_code,
			@PathVariable String product_vendor) {
		ResponseDto prodDto = productService.updateVendor(product_code, product_vendor);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}

	// Update the scale of a product
	@PutMapping("v1/products/update_scale/{product_code}/{product_scale}")
	public ResponseEntity<ResponseDto> updateScale(@Valid @PathVariable String product_code,
			@PathVariable String product_scale) {
		ResponseDto prodDto = productService.updateScale(product_code, product_scale);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}

	// Update the name of a product
	@PutMapping("v1/products/update_name/{product_code}/{product_name}")
	public ResponseEntity<ResponseDto> updateName(@Valid @PathVariable String product_code,
			@PathVariable String product_name) {
		ResponseDto prodDto = productService.updateName(product_code, product_name);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}

	// Get a product by its code
	@GetMapping("v1/products/getby_code/{product_code}")
	public ResponseEntity<ProductDto> getProductByCode(@PathVariable String product_code) {
		ProductDto prodDto = productService.getProductByCode(product_code);

		return new ResponseEntity<ProductDto>(prodDto, HttpStatus.FOUND);
	}

	// Get products by their name
	@GetMapping("v1/products/getby_name/{product_name}")
	public ResponseEntity<List<ProductDto>> getProductByName(@PathVariable String product_name) {
		List<ProductDto> prodDto = productService.getProductByName(product_name);

		return new ResponseEntity<List<ProductDto>>(prodDto, HttpStatus.FOUND);
	}

	// Get products by their scale
	@GetMapping("v1/products/getby_scale/{product_scale}")
	public ResponseEntity<List<ProductDto>> getProductByScale(@PathVariable String product_scale) {
		List<ProductDto> prodDto = productService.getProductByScale(product_scale);

		return new ResponseEntity<List<ProductDto>>(prodDto, HttpStatus.FOUND);
	}

	// Get products by their vendor
	@GetMapping("v1/products/getby_vendor/{product_vendor}")
	public ResponseEntity<List<ProductDto>> getProductByVendor(@PathVariable String product_vendor) {
		List<ProductDto> prodDto = productService.getProductByVendor(product_vendor);

		return new ResponseEntity<List<ProductDto>>(prodDto, HttpStatus.FOUND);
	}

	// Get the total ordered quantity of a product
	@GetMapping("v1/products/{product_code}/total_ordered_qty")
	public ResponseEntity<ResponseDto> getOrderedQuantity(@PathVariable String product_code) {
		Integer prodDto = productService.getOrderedQuantity(product_code);
		ResponseDto dto = new ResponseDto("Total ordered quantity: " + prodDto);

		return new ResponseEntity<ResponseDto>(dto, HttpStatus.FOUND);
	}

	// Get the total sale amount for a product
	@GetMapping("v1/products/{product_code}/total_sale")
	public ResponseEntity<ResponseDto> getTotalSaleForProduct(@PathVariable String product_code) {
		ResponseDto prodDto = productService.getTotalSaleForProduct(product_code);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.FOUND);
	}

	// Get the total sale amount for all products
	@GetMapping("v1/products/total_sale")
	public ResponseEntity<ResponseDto> getTotalSale() {
		ResponseDto prodDto = productService.getTotalSale();

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.FOUND);
	}

	// Get the product with the highest demand
	@GetMapping("v1/products/product_details")
	public ResponseEntity<ProductDto> getHighDemandProduct() {
		ProductDto prodDto = productService.getHighDemandproduct();

		return new ResponseEntity<ProductDto>(prodDto, HttpStatus.FOUND);
	}

	@PutMapping("v1/products/update/{product_code}")
	public ResponseEntity<ResponseDto> updateProductById(@Valid @PathVariable String product_code,
			@Valid @RequestBody ProductDto dto) {
		ResponseDto prodDto = productService.updateProductById(product_code, dto);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}
}
