package com.sprint.classicmodelsbussiness.dto;

import com.sprint.classicmodelsbussiness.entity.ProductLines;

public class ProductProductLinesDto {

	ProductDto product;
	ProductLines productLines;

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

	public ProductLines getProductLines() {
		return productLines;
	}

	public void setProductLines(ProductLines productLines) {
		this.productLines = productLines;
	}

	public ProductProductLinesDto(ProductDto product, ProductLines productLines) {
		super();
		this.product = product;
		this.productLines = productLines;
	}

	public ProductProductLinesDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
