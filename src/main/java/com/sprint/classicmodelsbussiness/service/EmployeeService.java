package com.sprint.classicmodelsbussiness.service;

import java.util.List;

import com.sprint.classicmodelsbussiness.dto.EmployeeDto;

public interface EmployeeService {

	public EmployeeDto getEmployeeById(Integer employeeNumber);

	public List<EmployeeDto> getAllEmployees();

}
