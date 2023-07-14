package com.sprint.classicmodelsbussiness.service;

import java.util.List;

import com.sprint.classicmodelsbussiness.dto.ProductLinesDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;

public interface ProductLinesService {

	ResponseDto saveProductLines(ProductLinesDto productLinesDto);

	List<ProductLinesDto> getAllProductLines();

	ResponseDto updateTextDescription(String productLine, String textDescription);

	ProductLinesDto getProductLineById(String productLine);

	ProductLinesDto getProductLineByFullDescription(String full_description);

	List<ProductLinesDto> getProductLineByTextDescription(String text_description);

	Boolean existsByProductLine(String productLine);

	ResponseDto updateProductLineById(String productLine, ProductLinesDto dto);

}
