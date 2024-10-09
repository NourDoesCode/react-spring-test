package com.nour.ems_app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nour.ems_app.dto.EmployeeDto;
import com.nour.ems_app.exceptions.ResourceNotFoundException;
import com.nour.ems_app.mappers.EmployeeMapper;
import com.nour.ems_app.models.Employee;
import com.nour.ems_app.repositories.EmployeeRepository;
import com.nour.ems_app.service.EmployeeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        // convert employeeDto to employee
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        // save the employee to database
        Employee savedEmployee = employeeRepository.save(employee);

        // we should return the dto entity to the client
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee employeeById = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Does not   exist for id" + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employeeById);

    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

}
