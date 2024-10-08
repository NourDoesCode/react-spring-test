package com.nour.ems_app.service.impl;

import org.springframework.stereotype.Service;

import com.nour.ems_app.dto.EmployeeDto;
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

}
