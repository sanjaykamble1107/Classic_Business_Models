package com.sprint.classicmodelsbussiness.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.classicmodelsbussiness.dto.CustomersDto;
import com.sprint.classicmodelsbussiness.dto.OfficeDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.service.OfficeService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class OfficeController {

	@Autowired
	private OfficeService officeService;

	@GetMapping("v1/office/{office_code}")
	public ResponseEntity<OfficeDto> getOfficeByCode(@PathVariable Integer office_code) {
		OfficeDto ofcDto = officeService.getOfficeByCode(office_code);

		return new ResponseEntity<OfficeDto>(ofcDto, HttpStatus.OK);
	}

	@GetMapping("v1/office/all_office_details")
	public ResponseEntity<List<OfficeDto>> getAllOffices() {

		List<OfficeDto> employees = officeService.getAllOffices();
		return new ResponseEntity<List<OfficeDto>>(employees, HttpStatus.OK);
	}

	@PostMapping("v1/office")
	public ResponseEntity<ResponseDto> saveOffice(@Valid @RequestBody OfficeDto office) {
		ResponseDto ofcDto = officeService.saveOffice(office);

		return new ResponseEntity<ResponseDto>(ofcDto, HttpStatus.OK);
	}

	@PutMapping("v1/office/{office_code}/{phone}")
	public ResponseEntity<ResponseDto> updateOfficePhone(@Valid @PathVariable Integer office_code,
			@PathVariable String phone) {
		ResponseDto ofcDto = officeService.updateOfficePhone(office_code, phone);

		return new ResponseEntity<ResponseDto>(ofcDto, HttpStatus.OK);
	}

	@PutMapping("v1/office/update/{office_code}")
	public ResponseEntity<ResponseDto> updateOfficeAddress(@Valid @RequestBody OfficeDto office,
			@PathVariable Integer office_code) {
		ResponseDto ofcDto = officeService.updateOfficeAddress(office, office_code);

		return new ResponseEntity<ResponseDto>(ofcDto, HttpStatus.OK);
	}

	@GetMapping("v1/offices/**")
	public ResponseEntity<List<OfficeDto>> getOfficeByCityList(HttpServletRequest request) {
		String citiesPath = extractCitiesPath(request);
		String[] cities = citiesPath.split("/");
		List<OfficeDto> ofcDto = officeService.getOfficeByCityList(cities);

		return new ResponseEntity<List<OfficeDto>>(ofcDto, HttpStatus.FOUND);
	}

	private String extractCitiesPath(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		String pattern = "v1/offices/{cities}/**";
		AntPathMatcher pathMatcher = new AntPathMatcher();
		
		return pathMatcher.extractPathWithinPattern(pattern, requestURI);
	}

	@GetMapping("v1/offices/customers/{office_code}")
	public ResponseEntity<List<CustomersDto>> getCustomersByOfficeCode(@PathVariable Integer office_code) {
		List<CustomersDto> ofcDto = officeService.getCustomersByOfficeCode(office_code);

		return new ResponseEntity<List<CustomersDto>>(ofcDto, HttpStatus.FOUND);
	}
}
