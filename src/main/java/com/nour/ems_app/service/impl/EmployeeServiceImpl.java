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

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployeeDetails) {

        // find the employee corresponding to the ID in the database
        Employee employeeForUpdate = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Does not exist for id" + employeeId));

        // update the fields of the employee that you found by using setters

        // update FirstName
        employeeForUpdate.setFirstName(updatedEmployeeDetails.getFirstName());
        // update email
        employeeForUpdate.setEmail(updatedEmployeeDetails.getEmail());

        // update lastname
        employeeForUpdate.setLastName(updatedEmployeeDetails.getLastName());

        // save the updated employee to the DB

        Employee updatedEmployee = employeeRepository.save(employeeForUpdate);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        // For everything related to doing any DB operations using ID or existing saved
        // objectss we need to always throw back an exception in case the client gives
        // an ID that does not exist => we have to find the employee corresponding to
        // the ID in the database
        Employee employeeToDelete = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Does not exist for id" + employeeId));

        // delete the employee
        employeeRepository.deleteById(employeeId);
    }

}
