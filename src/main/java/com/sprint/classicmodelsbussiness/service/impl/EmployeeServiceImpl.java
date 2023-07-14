package com.sprint.classicmodelsbussiness.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.classicmodelsbussiness.dto.EmployeeDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.entity.Employees;
import com.sprint.classicmodelsbussiness.entity.Offices;
import com.sprint.classicmodelsbussiness.exception.EmployeeNotFoundException;
import com.sprint.classicmodelsbussiness.exception.OfficeNotFoundException;
import com.sprint.classicmodelsbussiness.repository.EmployeeRepository;
import com.sprint.classicmodelsbussiness.repository.OfficeRepository;
import com.sprint.classicmodelsbussiness.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Autowired
	private OfficeRepository officeRepository;

	ResponseDto responseDto = new ResponseDto();

	@Override
	public EmployeeDto getEmployeeById(Integer employeeNumber) {

		Optional<Employees> findById = repository.findById(employeeNumber);

		if (findById.isPresent()) {
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
		for (Employees emp : findAll) {
			EmployeeDto dto = new EmployeeDto();
			BeanUtils.copyProperties(emp, dto);
			dto.setOfficeCode(emp.getOfficeCode().getOfficeCode());
			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public ResponseDto saveEmployee(EmployeeDto employeeDto) {
		if (existsByEmail(employeeDto.getEmail())) {
			throw new EmployeeNotFoundException("Employee Email already exists");
		}
		try {

			Employees employee = new Employees();
			BeanUtils.copyProperties(employeeDto, employee);
			employee.setOfficeCode(officeRepository.findById(employeeDto.getOfficeCode()).get());
			Employees emp = repository.save(employee);
			BeanUtils.copyProperties(emp, employeeDto);
			responseDto.setMessage("Employee details added successfully");
			return responseDto;
		} catch (Exception e) {
			throw new EmployeeNotFoundException("Cannot add Employee details");
		}
	}

	@Override
	public ResponseDto updateEmployee(Integer employeeNumber, Integer newemployeeNumber) {

		Optional<Employees> employee = repository.findById(employeeNumber);
		Optional<Employees> newSupervisor = repository.findById(newemployeeNumber);
		if (!employee.isPresent() || !newSupervisor.isPresent()) {
			throw new EmployeeNotFoundException("Employee details not found");
		}
		try {
			employee.get().setReportsTo(newemployeeNumber);
			repository.save(employee.get());
			responseDto.setMessage("Details updated Successfully");
			return responseDto;
		} catch (Exception e) {
			throw new EmployeeNotFoundException("Employee details not found");
		}
	}

	@Override
	public EmployeeDto updateEmployeeRole(Integer employeeNumber, String jobTitle) {

		Optional<Employees> employee = repository.findById(employeeNumber);
		if (!employee.isPresent()) {
			throw new EmployeeNotFoundException("Employee details not found");
		}
		try {
			employee.get().setJobTitle(jobTitle);
			Employees e = repository.save(employee.get());
			EmployeeDto dto = new EmployeeDto();
			BeanUtils.copyProperties(e, dto);

			return dto;
		} catch (Exception e) {
			throw new EmployeeNotFoundException("Employee details not found");
		}
	}

	@Override
	public ResponseDto updateEmployeeOffice(Integer officeCode, Integer employeeNumber) {

		Optional<Employees> employee = repository.findById(employeeNumber);
		Optional<Offices> findByIdOffice = officeRepository.findById(officeCode);

		if (!employee.isPresent()) {
			throw new EmployeeNotFoundException("Employee or office not found.");
		}

		employee.get().setOfficeCode(findByIdOffice.get());
		repository.save(employee.get());
		responseDto.setMessage("Details updated Successfully");
		return responseDto;

	}

	@Override
	public List<EmployeeDto> getAllEmployeeByOfficeCode(Integer officeCode) {
		if (!officeRepository.existsById(officeCode)) {
			throw new OfficeNotFoundException("Office details not found");
		}

		List<Employees> employee = repository.findByOfficeOfficeCode(officeCode);
		
		List<EmployeeDto> dtos = new ArrayList<>();
		if (!employee.isEmpty()) {

			for (Employees entity : employee) {
				EmployeeDto dto = new EmployeeDto();
				BeanUtils.copyProperties(entity, dto);
				dto.setOfficeCode(entity.getOfficeCode().getOfficeCode());
				dtos.add(dto);

			}
			return dtos;

		}

		throw new EmployeeNotFoundException("No Employee found in the Office " + officeCode);

	}

	@Override
	public List<EmployeeDto> getAllEmployeesByCity(String city) {
		// TODO Auto-generated method stub
		Offices office = officeRepository.findByCity(city);
		List<Employees> findByOffice = repository.findByOffice(office);

		if (office == null) {
			throw new OfficeNotFoundException("Offices  Not found.");
		}
		if (findByOffice.isEmpty()) {
			throw new EmployeeNotFoundException("Employees Not found in that cities.");
		}

		List<EmployeeDto> dtos = new ArrayList<>();
		for (Employees entity : findByOffice) {
			EmployeeDto dto = new EmployeeDto();
			BeanUtils.copyProperties(entity, dto);
			dto.setOfficeCode(entity.getOfficeCode().getOfficeCode());
			dtos.add(dto);

		}
		return dtos;
	}

	@Override
	public ResponseDto updateEmployeeById(Integer employeeNumber, EmployeeDto dto) {

		Optional<Employees> employee = repository.findById(employeeNumber);
		Optional<Employees> reportsTo = repository.findById(dto.getReportsTo());
		Optional<Offices> findByIdOffice = officeRepository.findById(dto.getOfficeCode());

		if (!employee.isPresent() || !findByIdOffice.isPresent() || !reportsTo.isPresent()) {
			throw new EmployeeNotFoundException("Employee or Office details not found");
		}

		BeanUtils.copyProperties(dto, employee.get());
		employee.get().setEmployeeNumber(employeeNumber);
		repository.save(employee.get());

		responseDto.setMessage("Employee updated successfully");
		return responseDto;

	}

	private boolean existsByEmail(String email) {
		return repository.existsByEmail(email);
	}

}