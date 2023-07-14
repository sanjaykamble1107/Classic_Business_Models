package com.sprint.classicmodelsbussiness.controller;

import java.util.List;

import javax.validation.Valid;

//import javax.validation.Valid;

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

import com.sprint.classicmodelsbussiness.dto.EmployeeDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("v1/v1/employee/{employeeNumber}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer employeeNumber) {
		EmployeeDto emDto = employeeService.getEmployeeById(employeeNumber);

		return new ResponseEntity<EmployeeDto>(emDto, HttpStatus.OK);
	}

	@GetMapping("v1/v1/employee/all_employee_details")
	public ResponseEntity<List<EmployeeDto>> getAllEmployees() {

		List<EmployeeDto> employees = employeeService.getAllEmployees();

		return new ResponseEntity<List<EmployeeDto>>(employees, HttpStatus.OK);
	}

	@PostMapping("v1/employee")
	public ResponseEntity<ResponseDto> saveEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
		ResponseDto emDto = employeeService.saveEmployee(employeeDto);
		return new ResponseEntity<ResponseDto>(emDto, HttpStatus.OK);
	}

	@PutMapping("v1/employee/{employeeNumber}/reports_to/{newemployeeNumber}")
	public ResponseEntity<ResponseDto> updateEmployee(@Valid @PathVariable Integer employeeNumber,
			@PathVariable Integer newemployeeNumber) {
		ResponseDto msg = employeeService.updateEmployee(employeeNumber, newemployeeNumber);

		// return new ResponseEntity<EmployeeDto>(employee,HttpStatus.OK);
		return new ResponseEntity<ResponseDto>(msg, HttpStatus.OK);
	}

	@PutMapping("v1/employee/{employeeNumber}/{jobTitle}")
	public ResponseEntity<EmployeeDto> updateEmployeeRole(@Valid @PathVariable Integer employeeNumber,
			@PathVariable String jobTitle) {
		EmployeeDto dto = employeeService.updateEmployeeRole(employeeNumber, jobTitle);

		return new ResponseEntity<EmployeeDto>(dto, HttpStatus.OK);
	}

	@PutMapping("v1/employee/mapToOffice/{officeCode}")
	public ResponseEntity<ResponseDto> updateEmployeeOffice(@Valid @PathVariable Integer officeCode,
			@RequestBody EmployeeDto dto) {
		Integer employeeNumber = dto.getEmployeeNumber();
		ResponseDto msg = employeeService.updateEmployeeOffice(officeCode, employeeNumber);

		return new ResponseEntity<ResponseDto>(msg, HttpStatus.OK);
	}

	@PutMapping("v1/employee/{employeeNumber}")
	public ResponseEntity<ResponseDto> updateEmployeeById(@Valid @PathVariable Integer employeeNumber,
			@Valid @RequestBody EmployeeDto dto) {
		return new ResponseEntity<ResponseDto>(employeeService.updateEmployeeById(employeeNumber, dto), HttpStatus.OK);
	}

	@GetMapping("v1/v1/employee/all_employee_details/{officeCode}")
	public ResponseEntity<List<EmployeeDto>> getAllEmployeeByOfficeId(@PathVariable Integer officeCode) {
		List<EmployeeDto> allEmployeeByOfficeCode = employeeService.getAllEmployeeByOfficeCode(officeCode);

		return new ResponseEntity<List<EmployeeDto>>(allEmployeeByOfficeCode, HttpStatus.FOUND);
	}

	@GetMapping("v1/v1/employee/all_employee_details_city/{city}")
	public List<EmployeeDto> getAllEmployeeDetailsByCity(@PathVariable String city) {
		return employeeService.getAllEmployeesByCity(city);
	}

}
