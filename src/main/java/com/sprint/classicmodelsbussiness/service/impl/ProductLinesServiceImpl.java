package com.sprint.classicmodelsbussiness.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.classicmodelsbussiness.dto.ProductLinesDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.entity.ProductLines;
import com.sprint.classicmodelsbussiness.exception.ProductLinesNotFoundException;
import com.sprint.classicmodelsbussiness.repository.ProductLinesRepository;
import com.sprint.classicmodelsbussiness.service.ProductLinesService;

@Service
public class ProductLinesServiceImpl implements ProductLinesService {
	@Autowired
	private ProductLinesRepository repository;

	private ResponseDto responseDto = new ResponseDto();

	/**
	 * Saves the details of a product line.
	 *
	 * @param productLinesDto The ProductLinesDto object containing the product line
	 *                        details to be saved.
	 * @return A ResponseDto indicating the success of the operation.
	 * @throws ProductLinesNotFoundException If the product line already exists.
	 */
	@Override
	public ResponseDto saveProductLines(ProductLinesDto productLinesDto) throws ProductLinesNotFoundException {

		if (existsByProductLine(productLinesDto.getProductLine())) {
			throw new ProductLinesNotFoundException("ProductLine already exists");
		}
		try {
			ProductLines ofc = new ProductLines();
			BeanUtils.copyProperties(productLinesDto, ofc);
			repository.save(ofc);
			productLinesDto.setProductLine(ofc.getProductLine());
			responseDto.setMessage("Product line details added successfully");

			return responseDto;
		} catch (Exception e) {
			throw new ProductLinesNotFoundException("Sorry, cannot add product line details");
		}
	}

	/**
	 * Updates the text description of a product line.
	 *
	 * @param productLine     The product line to update.
	 * @param textDescription The new text description for the product line.
	 * @return A ResponseDto indicating the success of the operation.
	 * @throws ProductLinesNotFoundException If the product line is not found.
	 */
	@Override
	public ResponseDto updateTextDescription(String productLine, String textDescription)
			throws ProductLinesNotFoundException {
		try {
			ProductLinesDto ofcDto = getProductLineById(productLine);

			ProductLines office = new ProductLines();
			ofcDto.setTextDescription(textDescription);
			BeanUtils.copyProperties(ofcDto, office);
			repository.save(office);
			responseDto.setMessage("Product's text description updated successfully");
			return responseDto;
		} catch (Exception ex) {
			throw new ProductLinesNotFoundException("Product line details not found");
		}
	}

	/**
	 * Retrieves a product line by its ID.
	 *
	 * @param productLine The ID of the product line to retrieve.
	 * @return The ProductLinesDto object representing the product line.
	 * @throws ProductLinesNotFoundException If the product line with the given ID
	 *                                       is not found.
	 */
	@Override
	public ProductLinesDto getProductLineById(String productLine) throws ProductLinesNotFoundException {
		Optional<ProductLines> findById = repository.findById(productLine);

		if (findById.isPresent()) {
			ProductLinesDto dto = new ProductLinesDto();
			BeanUtils.copyProperties(findById.get(), dto);
			return dto;
		}
		throw new ProductLinesNotFoundException("Product line details not found");
	}

	/**
	 * Retrieves a product line by its full description.
	 *
	 * @param fullDescription The full description of the product line to retrieve.
	 * @return The ProductLinesDto object representing the product line.
	 * @throws ProductLinesNotFoundException If the product line with the given full
	 *                                       description is not found.
	 */
	@Override
	public ProductLinesDto getProductLineByFullDescription(String fullDescription)
			throws ProductLinesNotFoundException {
		fullDescription = fullDescription.strip();
		Optional<ProductLines> findByTextDescription = repository.findByTextDescription(fullDescription);

		if (findByTextDescription.isPresent()) {
			ProductLinesDto dto = new ProductLinesDto();
			BeanUtils.copyProperties(findByTextDescription.get(), dto);
			return dto;
		}
		throw new ProductLinesNotFoundException("Product line details not found");
	}

	/**
	 * Retrieves a list of product lines based on the given text description.
	 *
	 * @param textDescription The text description to search for.
	 * @return A list of ProductLinesDto objects representing the product lines.
	 * @throws ProductLinesNotFoundException If no product lines with the given text
	 *                                       description are found.
	 */
	@Override
	public List<ProductLinesDto> getProductLineByTextDescription(String textDescription)
			throws ProductLinesNotFoundException {
		String strips = textDescription.strip();
		List<ProductLines> findAll = repository.findAll();
		List<ProductLinesDto> dtos = new ArrayList<>();
		for (ProductLines ofc : findAll) {
			if (ofc.getTextDescription().contains(strips)) {
				ProductLinesDto dto = new ProductLinesDto();
				BeanUtils.copyProperties(ofc, dto);
				dtos.add(dto);
			}

		}
		if (dtos.isEmpty()) {
			throw new ProductLinesNotFoundException("Product line details not found");
		}
		return dtos;
	}

	/**
	 * Retrieves all product lines.
	 *
	 * @return A list of ProductLinesDto objects representing all product lines.
	 */
	@Override
	public List<ProductLinesDto> getAllProductLines() {
		List<ProductLines> findAll = repository.findAll();
		List<ProductLinesDto> dtos = new ArrayList<>();
		for (ProductLines ofc : findAll) {
			ProductLinesDto dto = new ProductLinesDto();
			BeanUtils.copyProperties(ofc, dto);
			dtos.add(dto);
		}

		return dtos;
	}

	/**
	 * Checks if a product line with the given ID exists.
	 *
	 * @param productLine The ID of the product line to check.
	 * @return true if the product line exists, false otherwise.
	 */
	@Override
	public Boolean existsByProductLine(String productLine) {
		return repository.existsByProductLine(productLine);
	}

	@Override
	public ResponseDto updateProductLineById(String productLine, ProductLinesDto dto) {
		Optional<ProductLines> productLineOpt = repository.findById(productLine);

		if (!productLineOpt.isPresent()) {
			throw new ProductLinesNotFoundException("ProductLine not found");
		}
		ProductLines productLines = productLineOpt.get();
		BeanUtils.copyProperties(dto, productLines);
		repository.save(productLines);

		responseDto.setMessage("Product details updated successfully");
		return responseDto;
	}

}
