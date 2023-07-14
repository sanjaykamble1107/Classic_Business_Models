package com.sprint.classicmodelsbussiness.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.classicmodelsbussiness.dto.OrderDetailsDto;
import com.sprint.classicmodelsbussiness.dto.ProductDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.entity.ProductLines;
import com.sprint.classicmodelsbussiness.entity.Products;
import com.sprint.classicmodelsbussiness.exception.ProductNotFoundException;
import com.sprint.classicmodelsbussiness.repository.OrderDetailsRepository;
import com.sprint.classicmodelsbussiness.repository.ProductLinesRepository;
import com.sprint.classicmodelsbussiness.repository.ProductRepository;
import com.sprint.classicmodelsbussiness.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;
	{
	}
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	@Autowired
	private ProductLinesRepository productLinesRepository;

	public ProductServiceImpl(ProductRepository repository) {
		super();
		this.repository = repository;
	}

	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	private ResponseDto responseDto = new ResponseDto();

	/**
	 * Saves a new product.
	 *
	 * @param productDto The DTO containing the product details to be saved.
	 * @return A ResponseDto indicating the result of the operation.
	 * @throws ProductNotFoundException If the product with the same product code
	 *                                  already exists.
	 */
	@Override
	public ResponseDto saveProduct(ProductDto productDto) throws ProductNotFoundException {
		if (existsByProductCode(productDto.getProductCode())) {
			throw new ProductNotFoundException("ProductCode already exists");
		}
		try {
			Products prod = new Products();
			prod.setProductLine(productLinesRepository.findById(productDto.getProductLine()).get());
			BeanUtils.copyProperties(productDto, prod);
			repository.save(prod);
			responseDto.setMessage("Product details added successfully");
			return responseDto;
		} catch (Exception e) {
			throw new ProductNotFoundException("Cannot add product details");
		}
	}

	/**
	 * Retrieves all products.
	 *
	 * @return A list of ProductDto objects representing all the products.
	 */
	@Override
	public List<ProductDto> getAllProducts() {
		List<Products> findAll = repository.findAll();
		List<ProductDto> dtos = new ArrayList<>();
		for (Products prod : findAll) {
			ProductDto dto = new ProductDto();
			dto.setProductLine(prod.getProductLine().getProductLine());
			BeanUtils.copyProperties(prod, dto);
			dtos.add(dto);
		}
		return dtos;
	}

	/**
	 * Updates the buy price of a product.
	 *
	 * @param productCode The code of the product to update.
	 * @param buyPrice    The new buy price to set.
	 * @return A ResponseDto indicating the result of the operation.
	 * @throws ProductNotFoundException If the product with the given code is not
	 *                                  found.
	 */
	@Override
	public ResponseDto updateBuyPrice(String productCode, BigDecimal buyPrice) throws ProductNotFoundException {
		try {
			ProductDto productDto = getProductByCode(productCode);
			Products prod = new Products();
			prod.setProductLine(productLinesRepository.findById(productDto.getProductLine()).get());
			productDto.setBuyPrice(buyPrice);
			BeanUtils.copyProperties(productDto, prod);
			repository.save(prod);
			responseDto.setMessage("Product's buy price updated successfully");
			return responseDto;
		} catch (Exception ex) {
			throw new ProductNotFoundException("Product details not found");
		}
	}

	/**
	 * Updates the stock quantity of a product.
	 *
	 * @param productCode     The code of the product to update.
	 * @param quantityInStock The new stock quantity to set.
	 * @return A ResponseDto indicating the result of the operation.
	 * @throws ProductNotFoundException If the product with the given code is not
	 *                                  found.
	 */
	@Override
	public ResponseDto updateStock(String productCode, Short quantityInStock) throws ProductNotFoundException {
		try {
			ProductDto productDto = getProductByCode(productCode);
			Products prod = new Products();
			prod.setProductLine(productLinesRepository.findById(productDto.getProductLine()).get());
			productDto.setQuantityInStock(quantityInStock);
			BeanUtils.copyProperties(productDto, prod);
			repository.save(prod);
			responseDto.setMessage("Product's quantity updated successfully");
			return responseDto;
		} catch (Exception ex) {
			throw new ProductNotFoundException("Product details not found");
		}
	}

	/**
	 * Updates the MSRP of a product.
	 *
	 * @param productCode The code of the product to update.
	 * @param msrp        The new MSRP to set.
	 * @return A ResponseDto indicating the result of the operation.
	 * @throws ProductNotFoundException If the product with the given code is not
	 *                                  found.
	 */
	@Override
	public ResponseDto updateMsrp(String productCode, BigDecimal msrp) throws ProductNotFoundException {
		try {
			ProductDto productDto = getProductByCode(productCode);
			Products prod = new Products();
			prod.setProductLine(productLinesRepository.findById(productDto.getProductLine()).get());
			productDto.setMsrp(msrp);
			BeanUtils.copyProperties(productDto, prod);
			repository.save(prod);
			responseDto.setMessage("Product's MSRP updated successfully");
			return responseDto;
		} catch (Exception ex) {
			throw new ProductNotFoundException("Product details not found");
		}
	}

	/**
	 * Updates the vendor of a product.
	 *
	 * @param productCode   The code of the product to update.
	 * @param productVendor The new vendor to set.
	 * @return A ResponseDto indicating the result of the operation.
	 * @throws ProductNotFoundException If the product with the given code is not
	 *                                  found.
	 */
	@Override
	public ResponseDto updateVendor(String productCode, String productVendor) throws ProductNotFoundException {
		try {
			ProductDto productDto = getProductByCode(productCode);
			Products prod = new Products();
			prod.setProductLine(productLinesRepository.findById(productDto.getProductLine()).get());
			productDto.setProductVendor(productVendor);
			BeanUtils.copyProperties(productDto, prod);
			repository.save(prod);
			responseDto.setMessage("Product's vendor updated successfully");
			return responseDto;
		} catch (Exception ex) {
			throw new ProductNotFoundException("Product details not found");
		}
	}

	/**
	 * Updates the scale of a product.
	 *
	 * @param productCode  The code of the product to update.
	 * @param productScale The new scale to set.
	 * @return A ResponseDto indicating the result of the operation.
	 * @throws ProductNotFoundException If the product with the given code is not
	 *                                  found.
	 */
	@Override
	public ResponseDto updateScale(String productCode, String productScale) throws ProductNotFoundException {
		try {
			ProductDto productDto = getProductByCode(productCode);
			Products prod = new Products();
			prod.setProductLine(productLinesRepository.findById(productDto.getProductLine()).get());
			productDto.setProductScale(productScale);
			BeanUtils.copyProperties(productDto, prod);
			repository.save(prod);
			responseDto.setMessage("Product's scale updated successfully");
			return responseDto;
		} catch (Exception ex) {
			throw new ProductNotFoundException("Product details not found");
		}
	}

	/**
	 * Updates the name of a product.
	 *
	 * @param productCode The code of the product to update.
	 * @param productName The new name to set.
	 * @return A ResponseDto indicating the result of the operation.
	 * @throws ProductNotFoundException If the product with the given code is not
	 *                                  found.
	 */
	@Override
	public ResponseDto updateName(String productCode, String productName) throws ProductNotFoundException {
		try {
			ProductDto productDto = getProductByCode(productCode);
			Products prod = new Products();
			prod.setProductLine(productLinesRepository.findById(productDto.getProductLine()).get());
			productDto.setProductName(productName);
			BeanUtils.copyProperties(productDto, prod);
			repository.save(prod);
			responseDto.setMessage("Product's name updated successfully");
			return responseDto;
		} catch (Exception ex) {
			throw new ProductNotFoundException("Product details not found");
		}
	}

	/**
	 * Retrieves a product based on the given product code.
	 *
	 * @param productCode The code of the product to retrieve.
	 * @return The ProductDto object representing the product.
	 * @throws ProductNotFoundException If the product with the given code is not
	 *                                  found.
	 */
	@Override
	public ProductDto getProductByCode(String productCode) throws ProductNotFoundException {
		Optional<Products> findById = repository.findById(productCode);

		if (findById.isPresent()) {
			ProductDto dto = new ProductDto();
			dto.setProductLine(findById.get().getProductLine().getProductLine());
			BeanUtils.copyProperties(findById.get(), dto);
			return dto;
		}
		throw new ProductNotFoundException("Product details not found");
	}

	/**
	 * Retrieves a list of products based on the given product name.
	 *
	 * @param productName The name of the product to retrieve.
	 * @return A list of ProductDto objects representing the products.
	 * @throws ProductNotFoundException If no products with the given name are
	 *                                  found.
	 */
	@Override
	public List<ProductDto> getProductByName(String productName) throws ProductNotFoundException {
		Optional<Products> findByName = repository.findByProductName(productName);
		List<ProductDto> dtos = new ArrayList<>();
		if (findByName.isPresent()) {

			ProductDto dto = new ProductDto();
			dto.setProductLine(findByName.get().getProductLine().getProductLine());
			BeanUtils.copyProperties(findByName.get(), dto);
			dtos.add(dto);

			return dtos;
		}
		throw new ProductNotFoundException("Product details not found");
	}

	/**
	 * Retrieves a list of products based on the given product scale.
	 *
	 * @param productScale The scale of the products to retrieve.
	 * @return A list of ProductDto objects representing the products.
	 * @throws ProductNotFoundException If no products with the given scale are
	 *                                  found.
	 */
	@Override
	public List<ProductDto> getProductByScale(String productScale) throws ProductNotFoundException {
		List<Products> findByScale = repository.findByProductScale(productScale);
		List<ProductDto> dtos = new ArrayList<>();

		for (Products prod : findByScale) {
			ProductDto dto = new ProductDto();
			dto.setProductLine(prod.getProductLine().getProductLine());
			BeanUtils.copyProperties(prod, dto);
			dtos.add(dto);
		}
		if (dtos.isEmpty()) {
			throw new ProductNotFoundException("Product details not found");
		}
		return dtos;
	}

	/**
	 * Retrieves a list of products based on the given product vendor.
	 *
	 * @param productVendor The vendor of the products to retrieve.
	 * @return A list of ProductDto objects representing the products.
	 * @throws ProductNotFoundException If no products with the given vendor are
	 *                                  found.
	 */
	@Override
	public List<ProductDto> getProductByVendor(String productVendor) throws ProductNotFoundException {
		List<Products> findByVendor = repository.findByProductVendor(productVendor);
		List<ProductDto> dtos = new ArrayList<>();

		for (Products prod : findByVendor) {
			ProductDto dto = new ProductDto();
			dto.setProductLine(prod.getProductLine().getProductLine());
			BeanUtils.copyProperties(prod, dto);
			dtos.add(dto);
		}
		if (dtos.isEmpty()) {
			throw new ProductNotFoundException("Product details not found");
		}
		return dtos;
	}

	/**
	 * Retrieves the total ordered quantity of a product.
	 *
	 * @param productCode The code of the product to retrieve the ordered quantity.
	 * @return The total ordered quantity of the product.
	 * @throws ProductNotFoundException If the product with the given code is not
	 *                                  found.
	 */
	@Override
	public Integer getOrderedQuantity(String productCode) throws ProductNotFoundException {
		try {
			OrderDetailsServiceImpl orderDetailsServiceImpl = new OrderDetailsServiceImpl(orderDetailsRepository);
			List<OrderDetailsDto> orderDetailsList = orderDetailsServiceImpl.getOrderDetailsByProductCode(productCode);
			Integer total = 0;
			for (OrderDetailsDto detailsDto : orderDetailsList) {
				total += detailsDto.getQuantityOrdered();
			}
			return total;

		} catch (Exception ex) {
			throw new ProductNotFoundException("Product details not found");
		}
	}

	/**
	 * Retrieves the total sale amount for a specific product.
	 *
	 * @param productCode The code of the product to retrieve the total sale amount.
	 * @return A ResponseDto indicating the total sale amount for the product.
	 * @throws ProductNotFoundException If the product with the given code is not
	 *                                  found.
	 */
	@Override
	public ResponseDto getTotalSaleForProduct(String productCode) throws ProductNotFoundException {
		try {
			OrderDetailsServiceImpl orderDetailsServiceImpl = new OrderDetailsServiceImpl(orderDetailsRepository);
			List<OrderDetailsDto> orderDetailsList = orderDetailsServiceImpl.getOrderDetailsByProductCode(productCode);
			BigDecimal total = new BigDecimal(0);
			for (OrderDetailsDto detailsDto : orderDetailsList) {
				total = total.add(detailsDto.getPriceEach());
			}

			responseDto.setMessage("Total sale of the product is Rs " + total);
			return responseDto;
		} catch (Exception ex) {
			throw new ProductNotFoundException("Product details not found");
		}
	}

	/**
	 * Retrieves the total sale amount for all products.
	 *
	 * @return A ResponseDto indicating the total sale amount for all products.
	 */
	@Override
	public ResponseDto getTotalSale() {

		OrderDetailsServiceImpl orderDetailsServiceImpl = new OrderDetailsServiceImpl(orderDetailsRepository);
		List<OrderDetailsDto> orderDetailsList = orderDetailsServiceImpl.getAllOrderDetails();
		BigDecimal total = new BigDecimal(0);
		for (OrderDetailsDto detailsDto : orderDetailsList) {
			total = total.add(detailsDto.getPriceEach());
		}

		responseDto.setMessage("Total sale of all products is Rs " + total);
		return responseDto;
	}

	/**
	 * Retrieves the product with the highest demand.
	 *
	 * @return The ProductDto representing the product with the highest demand.
	 */
	@Override
	public ProductDto getHighDemandproduct() {
		Integer max = 0;
		ProductDto maxDto = new ProductDto();
		List<ProductDto> findAll = getAllProducts();
		for (ProductDto product : findAll) {
			try {
				Integer quant = getOrderedQuantity(product.getProductCode());
				if (quant > max) {
					max = quant;
					maxDto = product;
				}
			} catch (Exception e) {
				continue;
			}
		}
		return maxDto;
	}

	/**
	 * Checks if a product with the given product code exists.
	 *
	 * @param productCode The code of the product to check.
	 * @return true if the product exists, false otherwise.
	 */
	@Override
	public Boolean existsByProductCode(String productCode) {
		return repository.existsByProductCode(productCode);
	}

	@Override
	public ResponseDto updateProductById(String productCode, ProductDto dto) {
		Optional<Products> productOpt = repository.findById(productCode);

		Optional<ProductLines> prodLine = productLinesRepository.findById(dto.getProductLine());
		if (!productOpt.isPresent() || !prodLine.isPresent()) {
			throw new ProductNotFoundException("Product or ProductLine not found.");
		}
		Products product = productOpt.get();
		BeanUtils.copyProperties(dto, product);
		product.setProductCode(productCode);
		product.setProductLine(prodLine.get());

		repository.save(product);

		responseDto.setMessage("Product details updated successfully");
		return responseDto;
	}

}
