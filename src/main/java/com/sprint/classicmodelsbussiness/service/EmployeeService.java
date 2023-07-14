package com.sprint.classicmodelsbussiness.service;

import java.util.List;

import com.sprint.classicmodelsbussiness.dto.EmployeeDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;

public interface EmployeeService {

	public EmployeeDto getEmployeeById(Integer employeeNumber);

	public List<EmployeeDto> getAllEmployees();

	public ResponseDto saveEmployee(EmployeeDto employeeDto);

	public ResponseDto updateEmployee(Integer employeeNumber,Integer newemployeeNumber);

	public EmployeeDto updateEmployeeRole(Integer employeeNumber, String jobTitle);

	public ResponseDto updateEmployeeOffice(Integer officeCode, Integer employeeId);

	public List<EmployeeDto> getAllEmployeeByOfficeCode(Integer officeCode);

	public List<EmployeeDto> getAllEmployeesByCity(String city);

	public ResponseDto updateEmployeeById(Integer employeeNumber,EmployeeDto dto);

}
