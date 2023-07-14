package com.sprint.classicmodelsbussiness.service;

import java.math.BigDecimal;
import java.util.List;

import com.sprint.classicmodelsbussiness.dto.ProductDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;

public interface ProductService {

	public ResponseDto saveProduct(ProductDto productDto);

	public List<ProductDto> getAllProducts();

	public ResponseDto updateBuyPrice(String productCode, BigDecimal buyPrice);

	public ResponseDto updateStock(String productCode, Short quantityInStock);

	public ResponseDto updateMsrp(String productCode, BigDecimal msrp);

	public ResponseDto updateVendor(String productCode, String productVendor);

	public ResponseDto updateScale(String productCode, String productScale);

	public ResponseDto updateName(String productCode, String productName);

	public ProductDto getProductByCode(String productCode);

	public List<ProductDto> getProductByName(String productName);

	public List<ProductDto> getProductByScale(String productScale);

	public List<ProductDto> getProductByVendor(String productVendor);

	public Integer getOrderedQuantity(String productCode);

	public ResponseDto getTotalSaleForProduct(String product_code);

	public ResponseDto getTotalSale();

	public ProductDto getHighDemandproduct();

	Boolean existsByProductCode(String productCode);

	ResponseDto updateProductById(String productCode, ProductDto dto);

}
