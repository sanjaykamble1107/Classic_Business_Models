package com.sprint.classicmodelsbussiness.controller;

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

import com.sprint.classicmodelsbussiness.dto.ProductLinesDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.service.ProductLinesService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class ProductLinesController {
	@Autowired
	private ProductLinesService productLinesService;

	@GetMapping("v1/product_lines/all_productline_details")
	public ResponseEntity<List<ProductLinesDto>> getAllProductLines() {
		List<ProductLinesDto> prodDto = productLinesService.getAllProductLines();

		return new ResponseEntity<List<ProductLinesDto>>(prodDto, HttpStatus.OK);
	}

	@PostMapping("v1/product_lines/")
	public ResponseEntity<ResponseDto> saveProductLines(@Valid @RequestBody ProductLinesDto product) {
		ResponseDto prodDto = productLinesService.saveProductLines(product);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}

	@PutMapping("v1/product_lines/product_line/{product_line}/text_description/ {text_description}")
	public ResponseEntity<ResponseDto> updateTextDescription(@Valid @PathVariable String product_line,
			@PathVariable String text_description) {
		ResponseDto prodDto = productLinesService.updateTextDescription(product_line, text_description);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}

	@GetMapping("v1/product_lines/full_description/{full_description}")
	public ResponseEntity<ProductLinesDto> getProductLineByFullDescription(@PathVariable String full_description) {
		ProductLinesDto prodDto = productLinesService.getProductLineByFullDescription(full_description);

		return new ResponseEntity<ProductLinesDto>(prodDto, HttpStatus.FOUND);
	}

	@GetMapping("v1/product_lines/text_description/{text_description}")
	public ResponseEntity<List<ProductLinesDto>> getProductLineByTextDescription(
			@PathVariable String text_description) {
		List<ProductLinesDto> prodDto = productLinesService.getProductLineByTextDescription(text_description);

		return new ResponseEntity<List<ProductLinesDto>>(prodDto, HttpStatus.FOUND);
	}

	@GetMapping("v1/product_lines/product_line/{product_line}")
	public ResponseEntity<ProductLinesDto> getProductLineById(@PathVariable String product_line) {
		ProductLinesDto prodDto = productLinesService.getProductLineById(product_line);

		return new ResponseEntity<ProductLinesDto>(prodDto, HttpStatus.FOUND);
	}

	@PutMapping("v1/product_lines/update/{product_line}")
	public ResponseEntity<ResponseDto> updateProductLineById(@Valid @PathVariable String product_line,
			@Valid @RequestBody ProductLinesDto dto) {
		ResponseDto prodDto = productLinesService.updateProductLineById(product_line, dto);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}
}
