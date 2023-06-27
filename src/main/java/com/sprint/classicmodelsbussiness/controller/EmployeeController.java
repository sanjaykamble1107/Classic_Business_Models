package com.sprint.classicmodelsbussiness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.classicmodelsbussiness.dto.EmployeeDto;
import com.sprint.classicmodelsbussiness.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	

	@GetMapping("v1/v1/employee/{employeeNumber}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer employeeNumber)
	{
		EmployeeDto emDto = employeeService.getEmployeeById(employeeNumber);
		
		return new ResponseEntity<EmployeeDto>(emDto,HttpStatus.FOUND);
	}
	
	@GetMapping("v1/v1/employee/all_employee_details")
	public ResponseEntity<List<EmployeeDto>> getAllEmployees()
	{
		
		List<EmployeeDto> employees = employeeService.getAllEmployees();
		
		
		return new ResponseEntity<List<EmployeeDto>>(employees,HttpStatus.OK);
	}
	
	
	
}
