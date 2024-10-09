package com.nour.ems_app.service;

import java.util.List;

import com.nour.ems_app.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployeeDetails);

    void deleteEmployee(Long employeeId);
}
