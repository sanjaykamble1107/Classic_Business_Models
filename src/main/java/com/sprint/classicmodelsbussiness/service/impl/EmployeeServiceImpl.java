package com.sprint.classicmodelsbussiness.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.classicmodelsbussiness.dto.EmployeeDto;
import com.sprint.classicmodelsbussiness.entity.Employees;
import com.sprint.classicmodelsbussiness.exception.EmployeeNotFoundException;
import com.sprint.classicmodelsbussiness.repository.EmployeeRepository;
import com.sprint.classicmodelsbussiness.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService{

	
	@Autowired
	private EmployeeRepository repository;
	
	@Override
	public EmployeeDto getEmployeeById(Integer employeeNumber) {
		
		Optional<Employees> findById = repository.findById(employeeNumber);
		
		if(findById.isPresent())
		{
			EmployeeDto dto = new EmployeeDto();
			BeanUtils.copyProperties(findById.get(), dto);
			return dto;
		}
		throw new EmployeeNotFoundException("Employee details not found");
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employees> findAll = repository.findAll();
		List<EmployeeDto> dtos = new ArrayList<>();
		for(Employees emp : findAll)
		{
			EmployeeDto dto = new EmployeeDto();
			BeanUtils.copyProperties(emp, dto);
			dtos.add(dto);
		}
		
		return dtos;
	}

}
